//
//  java1007.java
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
//     Creacion: 09-Sep-1996  05:32:54
//     Revision: 03-Feb-2002  12:04:33
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

class Productor extends Thread {
  private Tuberia tuberia;
  private String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  public Productor( Tuberia t ) {
    // Mantiene una copia propia del objeto compartido
    tuberia = t;
    }

  public void run() {
    char c;

    // Mete 10 letras en la tubería
    for( int i=0; i < 10; i++ ) {
      c = alfabeto.charAt( (int)(Math.random()*26 ) );
      tuberia.lanzar( c );
      // Imprime un registro con lo añadido
      System.out.println( "Lanzado "+c+" a la tuberia." );
      // Espera un poco antes de añadir más letras
      try {
        sleep( (int)(Math.random() * 100 ) );
      } catch( InterruptedException e ) {
        ;
        }
      }
    }
  }


class Consumidor extends Thread {
  private Tuberia tuberia;

  public Consumidor( Tuberia t ) {
    // Mantiene una copia propia del objeto compartido
    tuberia = t;
    }

  public void run() {
    char c;

    // Consume 10 letras de la tubería
    for( int i=0; i < 10; i++ ) {
      c = tuberia.recoger();
      // Imprime las letras retiradas
      System.out.println( "Recogido el caracter "+c );
      // Espera un poco antes de coger más letras
      try {
        sleep( (int)(Math.random() * 2000 ) );
      } catch( InterruptedException e ) {
        ;
        }
      }
    }
  }



class Tuberia {
  private char buffer[] = new char[6];
  private int siguiente = 0;
  // Flags para saber el estado del buffer
  private boolean estaLlena = false;
  private boolean estaVacia = true;

  // Método para retirar letras del buffer
  public synchronized char recoger() {
    // No se puede consumir si el buffer está vacío
    while( estaVacia == true ) {
      try {
        wait(); // Se sale cuando estaVacia cambia a false
      } catch( InterruptedException e ) {
        ;
        }
      }
    // Decrementa la cuenta, ya que va a consumir una letra
    siguiente--;
    // Comprueba si se retiró la última letra
    if( siguiente == 0 )
      estaVacia = true;
    // El buffer no puede estar lleno, porque acabamos de consumir
    estaLlena = false;
    notify();

    // Devuelve la letra al thread consumidor
    return( buffer[siguiente] );
    }

  // Método para añadir letras al buffer
  public synchronized void lanzar( char c ) {
    // Espera hasta que haya sitio para otra letra
    while( estaLlena == true ) {
      try {
        wait(); // Se sale cuando estaLlena cambia a false
      } catch( InterruptedException e ) {
        ;
      }
    }
    // Añade una letra en el primer lugar disponible
    buffer[siguiente] = c;
    // Cambia al siguiente lugar disponible
    siguiente++;
    // Comprueba si el buffer está lleno
    if( siguiente == 6 )
      estaLlena = true;
    estaVacia = false;
      notify();
    }
  }


class java1007 {
  public static void main( String args[] ) {
    Tuberia t = new Tuberia();
    Productor p = new Productor( t );
    Consumidor c = new Consumidor( t );

    p.start();
    c.start();
    }
  }

//------------------------------------------ Final del fichero java1007.java