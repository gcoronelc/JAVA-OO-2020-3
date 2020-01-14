//
//  java1428.java
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
//     Creacion: 30-Sep-1998  09:06:15
//     Revision: 07-Feb-2002  05:52:34
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este es un ejemplo dinámico de utilización de la barra de progreso.
 * Cuando se arranca el hilo de ejecución, pulsando en botón Arrancar,
 * se actualizan al unísono el campo de texto y la barra de progreso, para
 * indicar el estado de la cuenta/carga, estabecida por defecto entre
 * 0 y 100
 */
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class java1428 extends JPanel {
  Thread hilo;
  Object objeto = new Object();
  boolean pideParar = false;
  JTextField texto;
  JProgressBar barra;

  public java1428() {
    setLayout( new BorderLayout() );

    texto = new JTextField();
    add( texto,BorderLayout.NORTH );

    JPanel panelInferior = new JPanel();
    barra = new JProgressBar();
    panelInferior.setLayout( new GridLayout(0,1) );
    panelInferior.add( barra );
    panelInferior.add( new JLabel( "Cargando..." ) );

    JPanel panelBotones = new JPanel();
    JButton botonArranque = new JButton( "Arrancar" );
    botonArranque.setBackground( SystemColor.control );
    panelBotones.add( botonArranque );
    botonArranque.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        iniciaCuenta();
        }
      } );

    JButton botonParar = new JButton( "Parar" );
    botonParar.setBackground( SystemColor.control );
    panelBotones.add( botonParar );
    botonParar.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        detieneCuenta();
        }
      } );

    panelInferior.add( panelBotones );
    add( panelInferior,BorderLayout.SOUTH );
    }


  public void iniciaCuenta() {
    if( hilo == null ) {
      hilo = new ThreadCarga();
      pideParar = false;
      hilo.start();
      }
    }


  public void detieneCuenta() {
    synchronized( objeto ) {
      pideParar = true;
      objeto.notify();
      }
    }


  class ThreadCarga extends Thread {
    public void run() {
      int min = 0;
      int max = 100;

      barra.setValue( min );
      barra.setMinimum( min );
      barra.setMaximum( max );

      for( int i=min; i <= max; i++ ) {
        barra.setValue( i );
        texto.setText( ""+i );
        synchronized( objeto ) {
          if( pideParar )
            break;
          try {
            objeto.wait( 100 );
          } catch( InterruptedException e ) {
            // Se ignoran las excepciones
            }
          }
        }
      hilo = null;
      }
    }


  public static void main( String args[] ) {
    JFrame frame = new JFrame( "Tutorial de Java, Swing" );
    frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
    frame.getContentPane().add( new java1428(),BorderLayout.CENTER );
    frame.setSize( 400,150 );
    frame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1428.java