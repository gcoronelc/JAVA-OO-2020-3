//
//  java1102.java
//  Copyright (c) 1997,2002 Agustin Froufe
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
//     Creacion: 30-Sep-1997  16:19:59
//     Revision: 03-Feb-2002  12:17:14
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa instancia un objteo receptor para porcesar los eventos
 * generados por el raton. Cuando se pulsa uno de los botones del raton
 * sobre el objeto Frame, el programa recoge las coordenadas de la
 * posicion en que se encontraba el cursor y las presenta en pantalla
 * cerca del punto en que se ha producido el click.
 * La coordenada Y devuelta por getY() parece ser 30 pixels menor que
 * la real
 */
import java.awt.*;
import java.awt.event.*;      // Este es un paquete nuevo del JDK 1.1

public class java1102 {
  public static void main( String args[] ) {
    // Aqui se instancia un objeto de tipo Interfaz Hombre-Maquina
    IHM ihm = new IHM();
    }
  }


// Se crea una subclase de Frame para poder sobreescribir el metodo
// paint(), y presentar en pantalla las coordenadas donde se haya
// producido el click del raton
class MiFrame extends Frame {
  int ratonX;
  int ratonY;

  public void paint( Graphics g ) {
    g.drawString( ""+ratonX+", "+ratonY,ratonX,ratonY );
    }
  }


// Esta clase se utiliza para instaciar un objeto de tipo interfaz de
// usuario
class IHM {
  public IHM() {
    MiFrame ventana = new MiFrame();

    ventana.setSize( 300,300 );
    ventana.setTitle( "Tutorial de Java, Eventos" );
    ventana.setVisible( true );

    // Se instancia y registra un objeto receptor de eventos
    // para terminar la ejecucion del programa cuando el
    // usuario decida cerrar la ventana
    Proceso1 procesoVentana1 = new Proceso1();
    ventana.addWindowListener( procesoVentana1 );

    // Se instancia y registra un objeto receptor de eventos
    // que sera el encargado de procesas los eventos del raton
    // para determinar y presentar las coordenadas en las que
    // se encuentra el cursor cuando el usuario pulsa el boton
    // del raton
    ProcesoRaton procesoRaton = new ProcesoRaton( ventana );
    ventana.addMouseListener( procesoRaton );
    }
  }


// Esta clase Receptora monitoriza las pulsaciones de los botones
// del raton y presenta las coordenadas en las que se ha producido
// el click
// Se trata de una clase Adpater, luego solo se redefinen los metodos
// que resulten utiles para el objetivo de la aplicacion
class ProcesoRaton extends MouseAdapter {
  MiFrame ventanaRef; // Referencia a la ventana

  // Constructor
  ProcesoRaton( MiFrame ventana ) {
    // Guardamos una referencia a la ventana
    ventanaRef = ventana;
    }

  // Se sobreescribe el metodo mousePressed para determinar y
  // presentar en pantalla las coordenadas del cursor cuando
  // se pulsa el raton
  public void mousePressed( MouseEvent evt ) {
    // Recoge las coordenadas X e Y de la posicion del cursor
    // y las almacena en el objeto Frame
    ventanaRef.ratonX = evt.getX();
    ventanaRef.ratonY = evt.getY();
    // Finalmente, presenta los valores de las coordenadas
    ventanaRef.repaint();
    }
  }


// Este repector de eventos de la ventana se utiliza para concluir
// la ejecucion del programa cuando el usuario pulsa sobre el boton
// de cierre del Frame
class Proceso1 extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    System.exit( 0 );
    }
  }

//---------------------------------------- Final del fichero java1102.java
