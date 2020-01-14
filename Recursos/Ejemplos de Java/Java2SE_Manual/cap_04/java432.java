//
//  java432.java
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
//     Creacion: 20-Feb-1998  04:10:49
//     Revision: 02-Feb-2002  20:50:10
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo utiliza los metodos 'synchronized' de la nueva libreria
 * de Colecciones
 */
import java.util.*;

public class java432 {
  public static void main( String args[] ) {
    Collection c = Collections.synchronizedCollection(
      new ArrayList() );
    List list = Collections.synchronizedList(
      new ArrayList() );
    Set s = Collections.synchronizedSet(
      new HashSet() );
    Map m = Collections.synchronizedMap(
      new HashMap() );
    }
  }

//------------------------------------------- Final del fichero java432.java