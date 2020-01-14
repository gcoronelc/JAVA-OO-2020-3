//
//  VolumenNodoRenderer.java
//  Copyright (c) 2001,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  da�os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da�o.
//
//   Compilador: javac 1.4.0,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 28-Dic-2001  12:43:23
//     Revision: 07-Feb-2002  06:01:18
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta clase utiliza las imagenes que se proporcionan en el constructor
 * para incorporarlas en el renderizado de cada nodo del �rbol, de este
 * modo se puede incorporar la informaci�n de la magnitud de cada uno
 * de los nodos a las presentaci�n en pantalla de la expansi�n del
 * �rbol
 */

import java.awt.*;
import java.awt.image.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

public class VolumenNodoRenderer extends DefaultTreeCellRenderer {
  // Constante que define la separaci�n en pixels que hay entre el
  // icono que representa el tama�o del nodo y el texto que identifica
  // a ese nodo
  private static final int TAM_BORDE_IMAGEN = 3;
  // Formato utilizado para presentar el tanto por ciento en los
  // mensajes que se presentan al pasar el cursor sobre un nodo
  private static final NumberFormat tantoPorCiento =
    NumberFormat.getPercentInstance();
  // Formato utilizado para presentar los tama�os de los nodos
  private static DecimalFormat decFormato;
  // Constantes usadas en el calculo del tama�o de los nodos
  private static final double kiloByte = (double)1024;
  private static final double megaByte = (double)(1024 * 1024);
  private static final double gigaByte = (double)(1024 * 1024 * 1024);

  static {
    // N�mero de d�gitos decimales del porcentaje
    tantoPorCiento.setMaximumFractionDigits( 2 );
    // Inicializamos el formato decimal, si el "locale" lo soporta
    final NumberFormat numFormato = NumberFormat.getInstance();
    if( numFormato instanceof DecimalFormat ) {
      decFormato = (DecimalFormat)numFormato;
      decFormato.applyPattern( "0.#" );
      }
    // Sino, lo dejamos a nulo
    else
      decFormato = null;
    }
  // Figuras utilizadas para representar los tama�os a la hora de
  // visualizar los nodos
  private Image[] imagenes;
  // Figura que indica que se est� calculando el tama�o de un nodo
  // por lo que todav�a su incorporaci�n a la jerarqu�a no es
  // completa
  private Image imgCalculando;
  // Imagen para representar nodos de muy peque�o tama�o, casi vac�os
  private Image imgVacio;
  // Umbral por debajo del cual se considera un nodo casi vac�o
  private double umbralVacio;
  // Imagen actual del nodo
  private Image imagenSel = null;
  // Controlador de las im�genes que se proporcionan en el constructor
  // para asegurar que la vista se refresca una vez concluida la carga
  // de las im�genes
  private CargaImagenes imgTrack;
  private Object imgTrackBloq = new Object();
  // Modo actual de visualizaci�n
  private JVolumenTree.ModoPresentacion modo;
  // Indica si la presentaci�n es por tama�o o por n�mero de nodos
  private transient boolean modoTam;
  // Indica si la presentaci�n es relativa al nodo ra�z o al nodo
  // padre
  private transient boolean comparaRaiz;

  public VolumenNodoRenderer( final Image[] _imagenes,
    final Image _imgVacio,final Image _imgCalculando ) {
    // Almacenamos los par�metros en las variables miembro de la
    // clase
    imagenes = _imagenes;
    imgVacio = _imgVacio;
    imgCalculando = _imgCalculando;
    umbralVacio = (1.0 / (_imagenes.length - 1)) / 2.0;

    // Fijamos el modo de presetnaci�n inicial
    setModoPresentacion( JVolumenTree.TAM_RELATIVO_RAIZ );
    // Preparamos el controlador de las im�genes, indic�ndole su
    // tama�o para que controle su carga
    imgTrack = new CargaImagenes( imagenes,imgVacio,imgCalculando );
    }

  // Este m�todo permite fijar el modo de presentaci�n del nodo
  public void setModoPresentacion( final JVolumenTree.ModoPresentacion _modo ) {
    modo = _modo;

    // Indicamos si la presentaci�n es relativa al nodo ra�z o al nodo
    // padre
    comparaRaiz = ( (_modo == JVolumenTree.TAM_RELATIVO_RAIZ) ||
                     (_modo == JVolumenTree.NUM_RELATIVO_RAIZ) );

    // Indicamos si la presentaci�n es por tama�o o por n�mero de nodos
    modoTam = ( (_modo == JVolumenTree.TAM_RELATIVO_RAIZ) ||
                     (_modo == JVolumenTree.TAM_RELATIVO_PADRE) );
    }

  // Este m�todo permite conocer el modo actual de presentaci�n de
  // un �rbol
  public JVolumenTree.ModoPresentacion getModoPresentacion() {
    return( modo );
    }

  // Este m�todo es el que permite personalizar el componente que se
  // utiliza en la visualizaci�n del nodo
  public Component getTreeCellRendererComponent( final JTree tree,
    final Object valor,final boolean seleccionado,final boolean expandido,
    final boolean hoja,final int fila,final boolean conFoco ) {
    // Recogemos el componente de la superclase para personalizarlo
    final Component componente = super.getTreeCellRendererComponent(
      tree,valor,seleccionado,expandido,hoja,fila,conFoco );

    // La clase que da forma a la celda ha de estar derivada de JLabel,
    // si no es as�, no podemos hacer nada
    if( componente instanceof JLabel ) {
      // Sincronizamos para evitar que salten excepciones si no se
      // han cargado las im�genes
      synchronized( imgTrackBloq ) {
        if( imgTrack != null )
          imgTrack.setTree( tree );
        }
      // Moldeamos el componente a un JLabel para poder llamar a los
      // m�todos de esta clase
      final JLabel jLabel = (JLabel)componente;
      // Texto que aparece al pasar el cursor sobre un nodod
      String textoTooltip = null;
      // El nodo que estamos visualizando
      final VolumenNodo nodo = (VolumenNodo)valor;

      // Nodo con el que se realiza la comparaci�n, dependiendo del
      // modo de presentaci�n. Puede ser el nodo ra�z o el nodo
      // padre.
      final VolumenNodo nodoCompara = comparaRaiz ?
        (VolumenNodo)nodo.getRoot() : (VolumenNodo)nodo.getParent();

      // Si el nodo con el que comparar es nulo, quiere decir que
      // este nodo actual es el ra�z, luego le asignamos el 100%
      double proporcion = 1.0;
      // Si no es el nodo ra�z, calculamos el porcentaje relativo
      if( nodoCompara != null ) {
        // Nodo actual y tama�o total
        long volNodo = 0;
        long volCompara = 1;

        // Si el modo de presentaci�n es por tama�o, el tama�o de
        // este nodo es comparado con el total del porcentaje relativo
        if( modoTam ) {
          volNodo = nodo.getTamanoTotal();
          volCompara = nodoCompara.getTamanoTotal();
          textoTooltip = formateaMagnitud( volNodo ) +" de "+
            formateaMagnitud( volCompara );
          }
        // Si el modo de presentaci�n es por n�mero de nodos, el
        // n�mero de nodos de este nodo es comparado con el total
        // relativo al ra�z o al padre
        else {
          volNodo = nodo.getLeafCount();
          volCompara = nodoCompara.getLeafCount();
          textoTooltip = volNodo +" items de "+ volCompara;
          }

        // Calcula la proporci�n
        proporcion = (double)volNodo / (double)volCompara;
        }

      // Seleccionamos la figura que corresponda en funci�n del
      // tama�o relativo del nodo, en comparaci�n con el nodo
      // del que es hijo directo
      imagenSel = selImagen( proporcion,nodo,nodoCompara );

      // Fijamos el espacio entre el icono y el texto, de forma que
      // se visualice el icono claramente
      if( imagenSel != null )
        jLabel.setIconTextGap( imagenSel.getWidth(null)+
          (TAM_BORDE_IMAGEN * 2) );

      // Fijamos el texto del tool tip
      jLabel.setToolTipText( tantoPorCiento.format(proporcion) +
        " - "+ textoTooltip );
      }

    return( componente );
    }

  // Sobrescribiendo el m�todo paint() nos aseguramos que el
  // componente va a pintar correctamente el nodo. Asumimos que
  // estamos tratando con un JLabel.
  public void paint( final Graphics g ) {
    // Obtenemos primero el icono que Java asigna al nodo y el texto
    // que lo identifica, luego colocamos la imagen que visualiza
    // su tama�o entre ellos
    super.paint( g );
    if( imagenSel != null )
      g.drawImage( imagenSel,
        getIcon().getIconWidth() + TAM_BORDE_IMAGEN,
        (getHeight()-imagenSel.getHeight(null)) / 2,null );
    }

  // Este es el m�todo encargado de seleccionar la imagen que
  // corresponde al tama�o del nodo que se indica
  private Image selImagen( final double proporcion,
    final VolumenNodo nodo,final VolumenNodo nodoCompara ) {
    Image imagen;

    // Si todav�a no tenemos imagen seleccionada y el nodo o el que
    // sirve como referencia no ha sido a�adido todav�a, mostramos
    // la imagen de que estamos calculando
    if( (imgCalculando != null) && ( !nodo.estaCalculado() ||
      ( (nodoCompara != null) && !nodoCompara.estaCalculado()) ) )
      imagen = imgCalculando;
    // Si el tama�o es mayor que cero, pero est� por debajo del
    // umbral del tama�o que sirve de l�mite para indicar que un
    // nodo est� casi vac�o, peresentamos esa imagen
    else if( (imgVacio != null) && (proporcion > 0.0) &&
      (proporcion < umbralVacio) )
      imagen = imgVacio;
    // En cualquier otro caso, presentamos la imagen que corresponda
    // a la proporci�n del tama�o del nodo
    else {
      final int index = (int)Math.round(
        (imagenes.length-1) * Math.min(1.0,proporcion) );
      imagen = imagenes[index];
      }
    return( imagen );
    }

  // Devolvemos el tama�o en forma de cadena, formateado en funci�n
  // de la cantidas en bytes
  private static String formateaMagnitud( final long bytes ) {
    String resultado = null;
    // Controlamos el tama�o bruto
    if( decFormato != null ) {
      if( bytes >= gigaByte )
        resultado = decFormato.format( (double)bytes/gigaByte)+"Gb";
      else if( bytes >= megaByte )
        resultado = decFormato.format( (double)bytes/megaByte)+"Mb";
      else if (bytes >= kiloByte)
        resultado = decFormato.format( (double)bytes/kiloByte)+"Kb";
      }
    // Si no llega ni a 1 Kb es que solamente ocupa bytes
    if( resultado == null )
      resultado = bytes+" bytes";

    return( resultado );
    }

  // Esta clase permite que se refresque el �rbol tan pronto como
  // la imagen que se necesite est� cargada.
  // La clase fija un observador para la carga de las im�genes,
  // guardando las que se est�n cargando en un vector, de forma
  // que cuando se detecte que todas las im�genes est�n cargadas,
  // la clase anula la referencia a �l desde la clase padre, de
  // forma que queda lista para ser eliminada por el recolector de
  // basura
  private class CargaImagenes implements ImageObserver {
    private final Toolkit tkt = Toolkit.getDefaultToolkit();
    // Almacena las im�genes que no est�n completamente cargadas
    private final Vector vImagenes;
    // referencia al �rbol que debe ser repintado cuando la carga
    // de las im�genes est� completa
    private JTree arbol = null;

    // Constructor al que se pasa la referencia a las im�genes
    public CargaImagenes( final Image[] _imagenes,
      final Image _imgVacio,final Image _imgCalculando ) {
      // Inicializa el vector con suficientes elementos para todas
      // las im�genes
      vImagenes = new Vector( _imagenes.length+2 );

      // Sincronizamos para evitar que se produzca una notificaci�n
      // de carga completa de las im�genes antes de que �stas se
      // coloquen en el vector
      synchronized( this ) {
        // Comprobamos si cada imagen est� ya cargada, a�adi�ndola
        // al vector si no lo est�
        for( int i=0; i < _imagenes.length; i++ ) {
          compruebaImagen( _imagenes[i] );
          }
        compruebaImagen( _imgVacio );
        compruebaImagen( _imgCalculando );
        }
      // Comprobamos si todas las im�genes est�n cargadas
      compruebaCarga();
      }

    // Comprueba la imagen que se indica para ver si no es nula,
    // ni est� almacenada en el vector de im�genes, ni est� ya
    // cargada. Si todo se cumple la a�adimos al vector.
    // Iniciamos la carga si es necesaria y los aseguramos de que
    // seremos notificados de los eventos de carga de im�genes
    private void compruebaImagen( final Image imagen ) {
      if( (imagen != null) && !vImagenes.contains(imagen) &&
        !tkt.prepareImage(imagen,-1,-1,this) )
        vImagenes.addElement( imagen );
      }

    // M�todo llamado desde getTreeCellRendererComponent() del
    // padre para que el JTree sepa que debe refrescar las
    // im�genes cuando est�n cargadas
    public void setTree( final JTree _arbol ) {
      arbol = _arbol;
      }

    // Comprueba si la carga de la imagen est� completa. Si es as�
    // refresca el JTree si dispone de la referencia, elimina la
    // imagen del vector y comprueba si era la �ltima de las
    // im�genes en la carga
    public boolean imageUpdate( final Image imagen,final int infoFlags,
      final int x,final int y,final int width,final int height ) {
      if( (infoFlags & ImageObserver.ALLBITS) != 0 ) {
        if( arbol != null )
          arbol.repaint();
        vImagenes.removeElement( imagen );
        compruebaCarga();
        }
      return( true );
      }

    // Comprueba si todas las im�genes ya est�n descargadas. Si es as�
    // marca la clase para que la elimine el recolector de basura
    private void compruebaCarga() {
      if( vImagenes.size() == 0 ) {
        synchronized( imgTrackBloq ) {
          // Ponemos la referencia a null para eliminarla
          imgTrack = null;
          }
        }
      }
    }
  }

//------------------------------- Final del fichero VolumenNodoRenderer.java
