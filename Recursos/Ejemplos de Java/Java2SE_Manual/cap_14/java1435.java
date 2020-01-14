//
//  java1435.java
//  Copyright (c) 2002 Agustin Froufe
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
//     Creacion: 07-Feb-2002  05:34:01
//     Revision: 07-Feb-2002  05:51:46
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra el uso de la nueva caracter�stica de las barras
 * de progreso ques e ha incorporado a Swing.
 * Esta nueva caracter�stica permite que la barra de progreso est�
 * realizando alg�n tipo de animaci�n para indicar al usuario que el
 * sistema est� ocupado. Se utiliza cuando no se sabe exactamente el
 * tiempo que se va a consumir en la realizaci�n de la acci�n que se ha
 * encomendado a la aplicaci�n o el tama�o exacto del fichero, datos, o
 * cualquier otra informaci�n que se est� manipulando.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class java1435 extends JPanel {
  JProgressBar barra = new JProgressBar();
  JButton btArrancar = new JButton();
  JButton btParar = new JButton();

  public java1435() {
    // No usamos un layout para colocar los elementos en donde se
    // quiera
    setLayout( null );

    // Fijamos el tama�o de la barra de progreso
    barra.setBounds( 5,10,295,25 );
    // La comocamos en la pantalla
    add( barra );

    // Colocamos el bot�n que permitir� mover la barra
    btArrancar.setText( " Arrancar ");
    btArrancar.setBounds( 20,50,100,25 );
    add( btArrancar );
    btArrancar.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        // Cambiamos la activaci�n de los botones
        btArrancar.setEnabled( false );
        btParar.setEnabled( true );
        // Fijamos los valores de la barra de progreso. Intervalo de
        // repintado
        UIManager.put( "ProgressBar.repaintInterval",new Integer(250) );
        // Ciclo de tiempo
        UIManager.put( "ProgressBar.cycleTime",new Integer(6000) );
        // Esta es la l�nea importante, ya que es donde se indica el
        // tipo de barra de progreso que se va a utilizar y se inicia
        // la animaci�n de la barra
        barra.setIndeterminate( true );
        }
      } );

    // Colocamos el bot�n que va a permitir parar el movimiento de
    // la barra
    btParar.setText( " Parar " );
    // En principio aparecer� desactivado
    btParar.setEnabled( false );
    btParar.setBounds( 185,50,100,25 );
    add( btParar );
    btParar.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        // Detenemos el movimiento de la barra
        barra.setIndeterminate( false );
        // Cambiamos la activaci�n de los botones
        btArrancar.setEnabled( true );
        btParar.setEnabled( false );
        }
      } );
    }

  public static void main( String args[] ) {
    JFrame frame = new JFrame( "Tutorial de Java, Swing" );
    frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
    frame.getContentPane().add( new java1435(),BorderLayout.CENTER );
    frame.setSize( 315,120 );
    frame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1435.java