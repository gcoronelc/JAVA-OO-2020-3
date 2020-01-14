//
//  HolaIte.java
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
//     Creacion: 17-Sep-1996  06:03:59
//     Revision: 03-Feb-2002  10:38:11
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.awt.*;
import java.applet.Applet;

public class HolaIte extends Applet {
  private int i = 0;
  private String Saludos[] = {
    "Hola Mundo!",
    "HOLA Mundo!",
    "HOLA MUNDO!!"
    };


  public void paint( Graphics g ) {
    try {
      g.drawString( Saludos[i],25,25 );
      } catch( ArrayIndexOutOfBoundsException e ) {
        g.drawString( "Saludos desbordado",25,25 );
    } catch( Exception e ) {
      // Cualquier otra excepción
      System.out.println( e.toString() );
    } finally {
      System.out.println( "Esto se imprime siempre!" );
      }
    i++;
    }
  }

//------------------------------------------- Final del fichero HolaIte.java