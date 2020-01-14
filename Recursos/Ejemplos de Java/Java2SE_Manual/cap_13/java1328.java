//
//  java1328.java
//  Copyright (c) 1998,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  daños o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho daño.
//
//   Compilador: javac 1.2.2,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 07-Jun-1998  10:01:06
//     Revision: 05-Feb-2002  06:05:31
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa ilustra el uso de Layouts. Por simplicidad, no se
 * se proporciona ningun controlador para los eventos del boton
 * ni para el boton de cierre del Frame, por lo que hay que
 * terminar de forma brusca
 */
import java.awt.*;
import java.awt.event.*;

public class java1328 {
  public static void main( String args[] ) {
    // Instancia un objeto de tipo Interfaz Hombre-Maquina
    IHM ihm = new IHM();
    }
  }


// La siguiente clase se utiliza para instanciar un objeto de tipo
// Interfaz Grafica de Usuario
class IHM {
  public IHM() {
    // Se crea un objeto Button con el texto que se pasa como
    // parametro y el tamaño y posicion indicadas dentro de
    // su contenedor (en pixels)
    Button miBoton = new Button( "Boton" );
    // Al rectamgulo se le pasan los parametros: x,y,ancho,alto
    miBoton.setBounds( new Rectangle( 25,20,100,75 ) );

    // Se crea un objeto Label con el texto que se indique como
    // parametro en la llamada y el tamaño especificado y en la
    // posicion que se indique dentro de su contenedor (en pixels)
    // Se pone en amarillo para que destaque
    Label miEtiqueta = new Label( "Tutorial de Java" );
    miEtiqueta.setBounds( new Rectangle( 100,75,100,75 ) );
    miEtiqueta.setBackground( Color.yellow );

    // Se crea un objeto Frame con el titulo que se indica en la
    // lamada y sin ningun layout
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    miFrame.setLayout( null );

    // Añade los dos componentes al Frame, fijando su tamaño en
    // pixels y lo hace visible
    miFrame.add( miBoton );
    miFrame.add( miEtiqueta );
    miFrame.setSize( 250,175 );
    miFrame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1328.java