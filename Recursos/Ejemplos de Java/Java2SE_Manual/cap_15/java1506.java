//
//  java1506.java
//  Copyright (c) 1999,2002 Agustin Froufe
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
//     Creacion: 07-May-1999  11:25:23
//     Revision: 08-Feb-2002  05:49:18
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra como se puede reemplazar el controlador de repintado
 * RepaintManager
 * En la consola de salida se indica el componente que se va repintando
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Clase de control del ejemplo
public class java1506 {
  public static void main( String[] args ) {
    // Aquí reemplazamos el RepaintManager clobalmente
    RepaintManager.setCurrentManager( new MiRepaintManager() );

    JFrame f = new JFrame("Tutorial de Java, Gráficos");
    // Clase anidada para controlar el cierre de la ventana y
    // concluir la aplicación
    f.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
        }
      });

    // Nos creamos un panel con algunos datos
    JPanel panel = new JPanel();
    JLabel etiqueta = new JLabel( "Nombre:" );
    etiqueta.setName( "etiqueta" );
    JTextField campo = new JTextField( 20 );
    campo.setName( "campo" );
    JButton boton = new JButton( "Aceptar" );
    boton.setName( "boton" );
    panel.add( etiqueta );
    panel.add( campo );
    panel.add( boton );
    // Incorporamos los componentes al panel
    f.getContentPane().add( panel,BorderLayout.CENTER );
    f.pack();
    // Y lo hacemos todo visible
    f.show();
    }
  }

// Esta es la clase que implementa el nuevo controlador del
// repintado
class MiRepaintManager extends RepaintManager {

  public synchronized void addDirtyRegion( JComponent c,
    int x,int y,int w,int h ) {
    System.out.println(
      "Incoporando DirtyRegion: "+c.getName()+
      ", "+x+","+y+" "+w+"x"+h);
    super.addDirtyRegion( c,x,y,w,h );
    }

  public void paintDirtyRegions() {
    // Como casi todas las variables que controlan el estado del
    // RepaintManager son privadas, no se puede saber desde aquí
    // el estado en que se encuentra, así que lo único que se puede
    // indicar es que está pintando
    System.out.println( "Pintando DirtyRegions" );
    super.paintDirtyRegions();
    }
  }

//------------------------------------------ Final del fichero java1506.java