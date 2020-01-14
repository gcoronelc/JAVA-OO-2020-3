//
//  java431.java
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
//     Creacion: 20-Feb-1998  04:02:15
//     Revision: 02-Feb-2002  20:49:11
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se utilizan los metodos 'unmodifiable' de la
 * libreria de Colecciones
 */
import java.util.*;

public class java431 {
  public static void main( String args[] ) {
    Collection c = new ArrayList();
    java421.fill( c );                  // Añade datos útiles
    c = Collections.unmodifiableCollection( c );
    java421.print( c );                 // La lectura es correcta
    //c.add( "uno" );                     // No se puede cambiar

    List a = new ArrayList();
    java421.fill( a );
    a = Collections.unmodifiableList( a );
    ListIterator lit = a.listIterator();
    System.out.println( lit.next() );   // La lectura es correcta
    //lit.add( "uno" );                   // No se puede cambiar

    Set s = new HashSet();
    java421.fill( s );
    s = Collections.unmodifiableSet( s );
    java421.print( s );                 // La lectura es correcta
    //s.add( "uno" );                     // No se puede cambiar

    Map m = new HashMap();
    java425.fill( m,java425.datosPrueba1 );
    m = Collections.unmodifiableMap( m );
    java425.print( m );                 // La lectura es correcta
    //m.put( "CV","Caballo de Vapor" );   // No se puede cambiar
    }
  }

//------------------------------------------- Final del fichero java431.java