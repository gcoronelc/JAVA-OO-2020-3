//
//  java1308.java
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
//     Creacion: 06-Mar-1998  19:16:18
//     Revision: 05-Feb-2002  05:42:09
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este programa implementa un ejemplo de uso de la clase Canvas.
 * También muestra cómo se instancias receptores de eventos que puedan
 * manipular objetos fuentes de eventos sobre los que están registrados,
 * sin necesidad de pasar referencias a estos objetos origen de eventos
 * cuando son instanciados los objetos.
 * Aparecen en pantalla las coordenadas del cursos cuando se pulsa el
 * botón del ratón con el cursos situado en el interior de la zona
 * delimitada por el Canvas.
 */
import java.awt.*;
import java.awt.event.*;

// Se crea una subclase de Canvas para poder sobrescribir el método
// paint() y poder cambiar el fondo a color verde
class MiCanvas extends Canvas {
  int posicionX;
  int posicionY;

  public MiCanvas() {
    this.setBackground( Color.green );
    }

  // Se sobrescribe el método paint()
  public void paint( Graphics g ) {
    g.drawString( "" + posicionX + ", " + posicionY,
      posicionX,posicionY );
    }
  }


class java1308 extends Frame {
  public static void main( String args[] ) {
    // Se instancia un objeto del tipo de la clase
    new java1308();
    }

  public java1308() {
    // Se crea un borderlayout y se fija el espaciado entre los
    // componentes que va a albergar
    BorderLayout miLayout = new BorderLayout();
    miLayout.setVgap( 30 );
    miLayout.setHgap( 30 );

    this.setLayout( miLayout );
    this.setTitle( "Tutorial de Java, AWT" );
    this.setSize( 300,300 );

    // Se instancia un objeto de MiCanvas
    MiCanvas miObjCanvas = new MiCanvas();

    // Se añade el objeto MiCanvas creado al Centro del objeto
    // Frame a través del BorderLayout
    this.add( miObjCanvas,"Center" );

    // Se añaden los botones no-funcionales en los bordes del
    // objeto Frame a través del BorderLayout
    this.add( new Button( "Norte" ),"North" );
    this.add( new Button( "Sur" ),"South" );
    this.add( new Button( "Este" ),"East" );
    this.add( new Button( "Oeste" ),"West" );
    // Ahora se podrán ver
    this.setVisible( true );

    // Se instancia y registra un objeto receptor de eventos de la
    // ventana para poder concluir la aplicación cuando el usuario
    // cierre el Frame
    Conclusion conclusion = new Conclusion();
    this.addWindowListener( conclusion );

    // Se instancia y registra un objeto Listener para procesar los
    // eventos del ratón y poder determinar las coordenadas en que se
    // encuentra el cursor cada vez que el usuario pulse el botón sobre
    // el objeto MiCanvas.
    // El objeto receptor de eventos es instanciado anónimamente y no
    // tiene ninguna referencia de MiCanvas, ya que no se le pasa nada
    // en el constructor
    miObjCanvas.addMouseListener( new ProcRaton() );
    }
  }


// Esta es la clase que monitoriza las pulsaciones de los botones del ratón
// y presenta las coordenadas en que se encuentra el cursor cuando el usuario
// realiza la pulsación con el cursor situado en el interior del objeto para
// el cual se ha registrado
class ProcRaton extends MouseAdapter {
  // Se sobrescribe el método mousePressed() para que haga lo que se ha
  // indicado
  public void mousePressed( MouseEvent evt ) {
    // Recoge las coordenadas x e y de la posición del cursor y las
    // almacena en variables de instancia del objeto MiCanvas. Es necesario
    // el casting para poder acceder a las variables de instancia
    ((MiCanvas)evt.getComponent()).posicionX = evt.getX();
    ((MiCanvas)evt.getComponent()).posicionY = evt.getY();
    // Se presentan las coordenadas en pantalla
    evt.getComponent().repaint();
    }
  }


// Concluye la ejecucion de la aplicacion cuando el usuario cierra la
// ventana, porque se genera un evento windowClosing
class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1308.java