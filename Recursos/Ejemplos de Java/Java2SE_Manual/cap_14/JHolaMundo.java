//
//  JHolaMundo.java
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
//     Creacion: 09-Sep-1998  09:10:29
//     Revision: 07-Feb-2002  05:25:02
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Versión Swing del saludo de lenguaje
 */
import javax.swing.*;

public class JHolaMundo extends JFrame {

  public static void main( String argv[] ) {
    new JHolaMundo();
    }

  JHolaMundo() {
    JLabel hola = new JLabel( "Hola Mundo!" );

    getContentPane().add( hola,"Center" );
    setSize( 200,100);
    setVisible( true );
    }
  }

//---------------------------------------- Final del fichero JHolaMundo.java