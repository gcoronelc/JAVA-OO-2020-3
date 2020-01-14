//
//  java436.java
//  Copyright (c) 2001,2002 Agustin Froufe
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
//     Creacion: 15-Dic-2001  13:38:27
//     Revision: 02-Feb-2002  20:40:26
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo muestra el uso de las colecciones de tipo Set y Map, tanto
 * en su forma ordenada, desordenada como, en el caso de Map, del orden
 * de acceso que se haya realizado a los elementos que constituyen el
 * Mapa.
 */
import java.text.*;
import java.util.*;

public class java436 {
  public static void main( String args[] ) {
    // Obtenemos un array con los meses en español
    String meses[] = new DateFormatSymbols().getMonths();
    // Obtenemos un array con los meses en inglés
    String mesesIta[] = new DateFormatSymbols(Locale.ENGLISH).getMonths();

    // Convertimos en lista el array de meses en español para
    // generar un conjutno ordenado y otro desordenado
    List lista = Arrays.asList( meses );
    Set setOrdenado = new LinkedHashSet( lista );
    Set setDesordenado = new HashSet( lista );
    System.out.println( "Set Ordenado:    "+setOrdenado );
    System.out.println( "Set Desordenado: "+setDesordenado );

    // Contruimos dos mapas con los arrays de meses, uno ordenado
    // y el otro desordenado
    Map mapaOrdenado = new LinkedHashMap();
    Map mapaDesordenado = new HashMap();
    for( int i=0,j=meses.length; i < j; i++ ) {
      mapaOrdenado.put( meses[i],mesesIta[i] );
      mapaDesordenado.put( meses[i],mesesIta[i] );
      }
    System.out.println( "Map Ordenado:    "+mapaOrdenado );
    System.out.println( "Map Desordenado: "+mapaDesordenado );

    // Imprimimos la lista de meses ingleses, perfectamente ordenada
    Collection coleccion = mapaOrdenado.values();
    Iterator iterador = coleccion.iterator();
    while( iterador.hasNext() )
      System.out.println( iterador.next() );

    // Creamos un mapa para acceder a él y que se guarde el orden
    // de acceso
    Map mapaAcceso = new LinkedHashMap( 20,.80f,true );
    for( int i=0,j=meses.length; i < j; i++ ) {
      mapaAcceso.put( meses[i],mesesIta[i] );
      }
    // Accedemos a algunos de los meses, para que se altere el orden
    // de acceso al mapa de meses
    mapaAcceso.get( "agosto" );
    mapaAcceso.get( "septiembre" );
    mapaAcceso.get( "julio" );
    mapaAcceso.get( "febrero" );
    // Imprimimos la lista de meses en función de los últimos accesos
    System.out.println( "Map Orden Acceso: "+mapaAcceso );
    }
  }

//------------------------------------------- Final del fichero java436.java