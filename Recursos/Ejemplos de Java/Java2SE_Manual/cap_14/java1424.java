//
//  java1424.java
//  Copyright (c) 1996,2002 Agustin Froufe
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
//     Creacion: 21-Sep-1998  17:06:23
//     Revision: 07-Feb-2002  06:21:32
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra el uso de los paneles desplazables. En el superior
 * se carga el código fuente de este ejemplo, y en el inferior se carga una
 * imagen. Desplazando la barra en la zona superior de la ventana, se moverá
 * al unísono la imagen de la zona inferior de la ventana
 */
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class java1424 extends JPanel implements ChangeListener {
  private JSplitPane panelVert;
  private JScrollPane panelScro1;
  private JScrollPane panelScro2;

  public java1424() {
    setLayout( new BorderLayout() );
    // Se crea una zona para presentar el texto correspondiente al fichero
    creaPanelSup();
    // Se crea una zona inferior para mostrar el gráfico
    creaPanelInf();
    // Se crea un panel dividido verticalmente
    panelVert = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
    add( panelVert,BorderLayout.CENTER );
    // Se incorporan las dos zonas que se habían creado a las dos
    // partes en que se ha dividido el panel principal
    panelVert.setLeftComponent( panelScro1 );
    panelVert.setRightComponent( panelScro2 );
    }


  public void stateChanged( ChangeEvent evt ) {
    // Si el evento proviene del panel principal, seguimos...
    if ( evt.getSource() == panelScro1.getViewport() ) {
      // Cogemos la posición actual dentro de la vista correspondiente
      // al panel principal
      Point point = panelScro1.getViewport().getViewPosition();
      // Ahora determinamos la escala correcta para las vistas, para las
      // dos zonas del panel principal
      Dimension dim1 = panelScro1.getViewport().getViewSize();
      Dimension dim2 = panelScro2.getViewport().getViewSize();
      float escalaX = 1;
      float escalaY = 1;
      if ( dim1.width > dim2.width ) {
        escalaX = (float)dim1.width / (float)dim2.width;
        escalaY = (float)dim1.height / (float)dim2.height;
        // Escalamos en función del movimiento
        point.x /= escalaX;
        point.y /= escalaY;
        }
      else {
        escalaX = (float)dim2.width / (float)dim1.width;
        escalaY = (float)dim2.height / (float)dim1.height;
        // Escalamos en función del movimiento
        point.x *= escalaX;
        point.y *= escalaY;
        }
      // Movemos la otra vista en función de lo que movamos la de texto
      panelScro2.getViewport().setViewPosition( point );
      }
    }


  private void creaPanelSup() {
    // Creamos el panel de la zona de texto
    JTextArea areaTexto = new JTextArea();
    // Se carga el fichero en el área de texto, cuidando de capturar
    // todas las excepciones que se puedan producir
    try {
      FileReader fileStream = new FileReader( "java1424.java" );
      areaTexto.read( fileStream, "java1424.java" );
    } catch( FileNotFoundException e ) {
      System.out.println( "Fichero no encontrado" );
    } catch( IOException e ) {
      System.out.println( "Error por IOException" );
      }

    // Creamos el panel desplazable para el área de texto
    panelScro1 = new JScrollPane();
    panelScro1.getViewport().add( areaTexto );
    panelScro1.getViewport().addChangeListener( this );
    }


  private void creaPanelInf() {
    // Cargamos el gráfico, o imagen , en la panatalla
    Icon imagenP2 = new ImageIcon( "main.gif" );
    JLabel etiqP2 = new JLabel( imagenP2 );

    // Creamos el panel para el gráfico
    panelScro2 = new JScrollPane();
    panelScro2.setVerticalScrollBarPolicy(
      ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
    panelScro2.setHorizontalScrollBarPolicy(
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
    panelScro2.getViewport().add( etiqP2 );
    panelScro2.getViewport().addChangeListener( this );
    }


  public static void main( String args[] ) {
    JFrame ventana = new JFrame( "Tutorial de Java, Swing" );
    ventana.setDefaultCloseOperation( ventana.EXIT_ON_CLOSE );
    ventana.getContentPane().add( new java1424(),BorderLayout.CENTER );
    ventana.setSize( 460,300 );
    ventana.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1424.java