//
//  java1436.java
//  Copyright (c) 2002 Agustin Froufe
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
//     Creacion: 07-Feb-2002  05:38:23
//     Revision: 07-Feb-2002  05:56:57
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra el uso de los tres tipos de Elementos de control
 * de selección de tipo JSpinner
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class java1436 extends JPanel {
  JSpinner spinFecha1;
  JSpinner spinFecha2;
  JSpinner spinNumero;
  JSpinner spinLista;

  public java1436() {
    // No usamos un layout para colocar los elementos en donde se quiera
    setLayout( null );

    // Creamos un selector que va a permitir ir avanzando día a día,
    // es el que utiliza el modelo de Fecha por defecto.
    JLabel labFecha1 = new JLabel(  "Selecciona un día:" );
    labFecha1.setBounds( 5,10,120,24 );
    add( labFecha1 );
    SpinnerDateModel modeloFecha1 = new SpinnerDateModel();
    modeloFecha1.setCalendarField( Calendar.DAY_OF_WEEK );
    spinFecha1 = new JSpinner( modeloFecha1 );
    spinFecha1.setBounds( 160,10,120,24 );
    add( spinFecha1 );

    // Creamos un selector de fechaque va a permitir ir avanzando de
    // semana en semana, ya que en el campo correspondiente se indica
    // que se debe mostrar la semana del mes
    JLabel labFecha2 = new JLabel( "Selecciona una fecha:" );
    labFecha2.setBounds( 5,40,150,24 );
    add( labFecha2 );
    SpinnerDateModel modeloFecha2 = new SpinnerDateModel();
    modeloFecha2.setCalendarField( Calendar.WEEK_OF_MONTH );
    spinFecha2 = new JSpinner( modeloFecha2 );
    // Fijamos el formato con que se va a presentar la fecha en el
    // campo de selección
    JSpinner.DateEditor editor = new JSpinner.DateEditor(
      spinFecha2, "dd MMMMM yyyy");
    spinFecha2.setEditor( editor );
    spinFecha2.setBounds( 160,40,120,24 );
    add( spinFecha2 );

    // Se crea un elemento de selección que permite ir seleccionando
    // de forma ascendente o descendente, números con un intervalo fijado
    // en 0,5
    JLabel labNumero = new JLabel( "Selecciona un número:" );
    labNumero.setBounds( 5,70,150,24 );
    add( labNumero );
    SpinnerNumberModel modeloNumero = new SpinnerNumberModel( 1,0.0,1000.0,0.5 );
    spinNumero = new JSpinner( modeloNumero );
    spinNumero.setBounds( 160,70,120,24 );
    add( spinNumero );

    // Se crea un elemento de selección que permite elegir entre los
    // distintos colores que integran la lista
    String[] colores = { "Amarillo","Marrón","Azul","Rojo","Naranja",
      "Negro","Blanco","Morado" };
    JLabel labLista = new JLabel( "Selecciona un color:" );
    labLista.setBounds( 5,100,120,24 );
    add( labLista );
    SpinnerListModel modeloLista = new SpinnerListModel( colores );
    spinLista = new JSpinner( modeloLista );
    spinLista.setValue( "Azul" );
    spinLista.setBounds( 160,100,120,24 );
    add( spinLista );
    }

  public static void main( String args[] ) {
    JFrame frame = new JFrame( "Tutorial de Java, Swing" );
    frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
    frame.getContentPane().add( new java1436(),BorderLayout.CENTER );
    frame.setSize( 300,170 );
    frame.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1436.java