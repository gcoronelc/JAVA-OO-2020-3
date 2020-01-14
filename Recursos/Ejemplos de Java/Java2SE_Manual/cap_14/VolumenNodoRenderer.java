//
//  VolumenNodoRenderer.java
//  Copyright (c) 2001,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  daños o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho daño.
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
 * para incorporarlas en el renderizado de cada nodo del árbol, de este
 * modo se puede incorporar la información de la magnitud de cada uno
 * de los nodos a las presentación en pantalla de la expansión del
 * árbol
 */

import java.awt.*;
import java.awt.image.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

public class VolumenNodoRenderer extends DefaultTreeCellRenderer {
  // Constante que define la separación en pixels que hay entre el
  // icono que representa el tamaño del nodo y el texto que identifica
  // a ese nodo
  private static final int TAM_BORDE_IMAGEN = 3;
  // Formato utilizado para presentar el tanto por ciento en los
  // mensajes que se presentan al pasar el cursor sobre un nodo
  private static final NumberFormat tantoPorCiento =
    NumberFormat.getPercentInstance();
  // Formato utilizado para presentar los tamaños de los nodos
  private static DecimalFormat decFormato;
  // Constantes usadas en el calculo del tamaño de los nodos
  private static final double kiloByte = (double)1024;
  private static final double megaByte = (double)(1024 * 1024);
  private static final double gigaByte = (double)(1024 * 1024 * 1024);

  static {
    // Número de dígitos decimales del porcentaje
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
  // Figuras utilizadas para representar los tamaños a la hora de
  // visualizar los nodos
  private Image[] imagenes;
  // Figura que indica que se está calculando el tamaño de un nodo
  // por lo que todavía su incorporación a la jerarquía no es
  // completa
  private Image imgCalculando;
  // Imagen para representar nodos de muy pequeño tamaño, casi vacíos
  private Image imgVacio;
  // Umbral por debajo del cual se considera un nodo casi vacío
  private double umbralVacio;
  // Imagen actual del nodo
  private Image imagenSel = null;
  // Controlador de las imágenes que se proporcionan en el constructor
  // para asegurar que la vista se refresca una vez concluida la carga
  // de las imágenes
  private CargaImagenes imgTrack;
  private Object imgTrackBloq = new Object();
  // Modo actual de visualización
  private JVolumenTree.ModoPresentacion modo;
  // Indica si la presentación es por tamaño o por número de nodos
  private transient boolean modoTam;
  // Indica si la presentación es relativa al nodo raíz o al nodo
  // padre
  private transient boolean comparaRaiz;

  public VolumenNodoRenderer( final Image[] _imagenes,
    final Image _imgVacio,final Image _imgCalculando ) {
    // Almacenamos los parámetros en las variables miembro de la
    // clase
    imagenes = _imagenes;
    imgVacio = _imgVacio;
    imgCalculando = _imgCalculando;
    umbralVacio = (1.0 / (_imagenes.length - 1)) / 2.0;

    // Fijamos el modo de presetnación inicial
    setModoPresentacion( JVolumenTree.TAM_RELATIVO_RAIZ );
    // Preparamos el controlador de las imágenes, indicándole su
    // tamaño para que controle su carga
    imgTrack = new CargaImagenes( imagenes,imgVacio,imgCalculando );
    }

  // Este método permite fijar el modo de presentación del nodo
  public void setModoPresentacion( final JVolumenTree.ModoPresentacion _modo ) {
    modo = _modo;

    // Indicamos si la presentación es relativa al nodo raíz o al nodo
    // padre
    comparaRaiz = ( (_modo == JVolumenTree.TAM_RELATIVO_RAIZ) ||
                     (_modo == JVolumenTree.NUM_RELATIVO_RAIZ) );

    // Indicamos si la presentación es por tamaño o por número de nodos
    modoTam = ( (_modo == JVolumenTree.TAM_RELATIVO_RAIZ) ||
                     (_modo == JVolumenTree.TAM_RELATIVO_PADRE) );
    }

  // Este método permite conocer el modo actual de presentación de
  // un árbol
  public JVolumenTree.ModoPresentacion getModoPresentacion() {
    return( modo );
    }

  // Este método es el que permite personalizar el componente que se
  // utiliza en la visualización del nodo
  public Component getTreeCellRendererComponent( final JTree tree,
    final Object valor,final boolean seleccionado,final boolean expandido,
    final boolean hoja,final int fila,final boolean conFoco ) {
    // Recogemos el componente de la superclase para personalizarlo
    final Component componente = super.getTreeCellRendererComponent(
      tree,valor,seleccionado,expandido,hoja,fila,conFoco );

    // La clase que da forma a la celda ha de estar derivada de JLabel,
    // si no es así, no podemos hacer nada
    if( componente instanceof JLabel ) {
      // Sincronizamos para evitar que salten excepciones si no se
      // han cargado las imágenes
      synchronized( imgTrackBloq ) {
        if( imgTrack != null )
          imgTrack.setTree( tree );
        }
      // Moldeamos el componente a un JLabel para poder llamar a los
      // métodos de esta clase
      final JLabel jLabel = (JLabel)componente;
      // Texto que aparece al pasar el cursor sobre un nodod
      String textoTooltip = null;
      // El nodo que estamos visualizando
      final VolumenNodo nodo = (VolumenNodo)valor;

      // Nodo con el que se realiza la comparación, dependiendo del
      // modo de presentación. Puede ser el nodo raíz o el nodo
      // padre.
      final VolumenNodo nodoCompara = comparaRaiz ?
        (VolumenNodo)nodo.getRoot() : (VolumenNodo)nodo.getParent();

      // Si el nodo con el que comparar es nulo, quiere decir que
      // este nodo actual es el raíz, luego le asignamos el 100%
      double proporcion = 1.0;
      // Si no es el nodo raíz, calculamos el porcentaje relativo
      if( nodoCompara != null ) {
        // Nodo actual y tamaño total
        long volNodo = 0;
        long volCompara = 1;

        // Si el modo de presentación es por tamaño, el tamaño de
        // este nodo es comparado con el total del porcentaje relativo
        if( modoTam ) {
          volNodo = nodo.getTamanoTotal();
          volCompara = nodoCompara.getTamanoTotal();
          textoTooltip = formateaMagnitud( volNodo ) +" de "+
            formateaMagnitud( volCompara );
          }
        // Si el modo de presentación es por número de nodos, el
        // número de nodos de este nodo es comparado con el total
        // relativo al raíz o al padre
        else {
          volNodo = nodo.getLeafCount();
          volCompara = nodoCompara.getLeafCount();
          textoTooltip = volNodo +" items de "+ volCompara;
          }

        // Calcula la proporción
        proporcion = (double)volNodo / (double)volCompara;
        }

      // Seleccionamos la figura que corresponda en función del
      // tamaño relativo del nodo, en comparación con el nodo
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

  // Sobrescribiendo el método paint() nos aseguramos que el
  // componente va a pintar correctamente el nodo. Asumimos que
  // estamos tratando con un JLabel.
  public void paint( final Graphics g ) {
    // Obtenemos primero el icono que Java asigna al nodo y el texto
    // que lo identifica, luego colocamos la imagen que visualiza
    // su tamaño entre ellos
    super.paint( g );
    if( imagenSel != null )
      g.drawImage( imagenSel,
        getIcon().getIconWidth() + TAM_BORDE_IMAGEN,
        (getHeight()-imagenSel.getHeight(null)) / 2,null );
    }

  // Este es el método encargado de seleccionar la imagen que
  // corresponde al tamaño del nodo que se indica
  private Image selImagen( final double proporcion,
    final VolumenNodo nodo,final VolumenNodo nodoCompara ) {
    Image imagen;

    // Si todavía no tenemos imagen seleccionada y el nodo o el que
    // sirve como referencia no ha sido añadido todavía, mostramos
    // la imagen de que estamos calculando
    if( (imgCalculando != null) && ( !nodo.estaCalculado() ||
      ( (nodoCompara != null) && !nodoCompara.estaCalculado()) ) )
      imagen = imgCalculando;
    // Si el tamaño es mayor que cero, pero está por debajo del
    // umbral del tamaño que sirve de límite para indicar que un
    // nodo está casi vacío, peresentamos esa imagen
    else if( (imgVacio != null) && (proporcion > 0.0) &&
      (proporcion < umbralVacio) )
      imagen = imgVacio;
    // En cualquier otro caso, presentamos la imagen que corresponda
    // a la proporción del tamaño del nodo
    else {
      final int index = (int)Math.round(
        (imagenes.length-1) * Math.min(1.0,proporcion) );
      imagen = imagenes[index];
      }
    return( imagen );
    }

  // Devolvemos el tamaño en forma de cadena, formateado en función
  // de la cantidas en bytes
  private static String formateaMagnitud( final long bytes ) {
    String resultado = null;
    // Controlamos el tamaño bruto
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

  // Esta clase permite que se refresque el árbol tan pronto como
  // la imagen que se necesite esté cargada.
  // La clase fija un observador para la carga de las imágenes,
  // guardando las que se están cargando en un vector, de forma
  // que cuando se detecte que todas las imágenes están cargadas,
  // la clase anula la referencia a él desde la clase padre, de
  // forma que queda lista para ser eliminada por el recolector de
  // basura
  private class CargaImagenes implements ImageObserver {
    private final Toolkit tkt = Toolkit.getDefaultToolkit();
    // Almacena las imágenes que no están completamente cargadas
    private final Vector vImagenes;
    // referencia al árbol que debe ser repintado cuando la carga
    // de las imágenes esté completa
    private JTree arbol = null;

    // Constructor al que se pasa la referencia a las imágenes
    public CargaImagenes( final Image[] _imagenes,
      final Image _imgVacio,final Image _imgCalculando ) {
      // Inicializa el vector con suficientes elementos para todas
      // las imágenes
      vImagenes = new Vector( _imagenes.length+2 );

      // Sincronizamos para evitar que se produzca una notificación
      // de carga completa de las imágenes antes de que éstas se
      // coloquen en el vector
      synchronized( this ) {
        // Comprobamos si cada imagen está ya cargada, añadiéndola
        // al vector si no lo está
        for( int i=0; i < _imagenes.length; i++ ) {
          compruebaImagen( _imagenes[i] );
          }
        compruebaImagen( _imgVacio );
        compruebaImagen( _imgCalculando );
        }
      // Comprobamos si todas las imágenes están cargadas
      compruebaCarga();
      }

    // Comprueba la imagen que se indica para ver si no es nula,
    // ni está almacenada en el vector de imágenes, ni está ya
    // cargada. Si todo se cumple la añadimos al vector.
    // Iniciamos la carga si es necesaria y los aseguramos de que
    // seremos notificados de los eventos de carga de imágenes
    private void compruebaImagen( final Image imagen ) {
      if( (imagen != null) && !vImagenes.contains(imagen) &&
        !tkt.prepareImage(imagen,-1,-1,this) )
        vImagenes.addElement( imagen );
      }

    // Método llamado desde getTreeCellRendererComponent() del
    // padre para que el JTree sepa que debe refrescar las
    // imágenes cuando estén cargadas
    public void setTree( final JTree _arbol ) {
      arbol = _arbol;
      }

    // Comprueba si la carga de la imagen está completa. Si es así
    // refresca el JTree si dispone de la referencia, elimina la
    // imagen del vector y comprueba si era la última de las
    // imágenes en la carga
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

    // Comprueba si todas las imágenes ya están descargadas. Si es así
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
