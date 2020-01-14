//
//  java1311.java
//  Copyright (c) 1998,2002 Agustin Froufe
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
//     Creacion: 06-Mar-1998  23:56:14
//     Revision: 05-Feb-2002  05:44:35
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa implementa un ejemplo de uso de la clase Scrollbar.
 * Crea una barra de desplazamiento horizontal, que permite la selección
 * de valores entre 0 y 255, presentando visualmente el número que
 * indica o está seleccionado en la barra. Se indican los valores que
 * permite el rango de selección de la barra a través de un objeto
 * Label
 */
import java.awt.*;
import java.awt.event.*;

class java1311 extends Frame {
  public static void main( String args[] ) {
    // Se instancia un objeto del tipo de la clase
    new java1311();
    }

  public java1311() {
    Scrollbar miBarra;
    TextField miTexto;
    Label miEtiqueta;

    miBarra = new Scrollbar( Scrollbar.HORIZONTAL,0,1,0,255 );
    miTexto = new TextField( "0",5 );
    miEtiqueta = new Label( "Rojo (0-254)" );

    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    miFrame.setLayout( new GridLayout( 1,3 ) );

    // Se incorporan la barra de desplazamiento, texto y etiqueta
    // al objeto Frame, indicando previamente que el campo de texto
    // no permite la edición de valores en él
    miTexto.setEditable( false );
    miFrame.add( miEtiqueta );
    miFrame.add( miBarra );
    miFrame.add( miTexto );

    // Se fija el tamaño del Frame y se hace que aparezca todo
    // en pantalla
    miFrame.setSize( 350,50 );
    miFrame.setVisible( true );

    // Se instancia un objeto para recibir los eventos de la
    // barra de desplazamiento y se registra para ser notificado
    // de los eventos de ajuste
    ProcesoBarra procesoBarra = new ProcesoBarra( miTexto );
    miBarra.addAdjustmentListener( procesoBarra );

    // Se instancia y registra un objeto receptor de eventos de la
    // ventana para poder concluir la aplicación cuando el usuario
    // cierre el Frame
    Conclusion conclusion = new Conclusion();
    miFrame.addWindowListener( conclusion );
    }
  }


// Al contrario que otros componentes que disponen de su propio
// interfaz receptor, esta clase no puede implementar un interfaz
// del tipo ScrollbarListener porque no existe. En su lugar,
// implementa el interfaz AdjustmentListener que es el utilizado
// como receptor de los eventos de las barras de desplazamiento
class ProcesoBarra implements AdjustmentListener {
  // A través de esta variable se indicará la psoición del
  // indicador en la barra de desplazamiento
  TextField posicion;

  ProcesoBarra( TextField iPosicion ) {
    posicion = iPosicion;
    }

  public void adjustmentValueChanged( AdjustmentEvent evt ) {
    // Presentamos el valor que representa la posición del
    // marcador en el campo de texto
    posicion.setText( Integer.toString( evt.getValue() ) );
    }
  }


// Concluye la ejecucion de la aplicacion cuando el usuario cierra la
// ventana, porque se genera un evento windowClosing
class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1311.java