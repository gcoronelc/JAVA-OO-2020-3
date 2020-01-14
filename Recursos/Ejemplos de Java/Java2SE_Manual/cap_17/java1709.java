//
//  java1709.java
//  Copyright (c) 1999,2002 Agustin Froufe
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
//     Creacion: 21-Jan-1999  09:12:34
//     Revision: 01-Feb-2002  19:27:08
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se implementan dos servidores diferentes sobre red IP,
 * y solamente se pretende que sea ilustrativo, no para utilizarlo como
 * algo serio. Si se hace as�, el autor declina toda responsabilidad.
 * Uno de los servidores que implementa, es un servidor de "echo", creado
 * a trav�s de un hilo de ejecuci�n que monitoriza el puerto 7. Lo que hace
 * es devolver la cadena que recibe al cliente.
 * El otro servidor implementa el protocolo HTTP, a trav�s de un hilo de
 * ejecuci�n que monitoriza el puerto 80, est�ndar de este protocolo. Este
 * servidor tiene la capacidad de responder al comando GET que se solicite
 * desde el navegador y servir el fichero como un stream de bytes.
 * El hecho de que se implementen los dos servidores a la vez, sobre
 * distintos puertos, ilustra tambi�n la forma en que se puede utilizar el
 * mecanismo de los hilos de ejecuci�n que proporciona Java para servir a
 * m�ltiples puertos.
 * Hay un controlador de seguridad implementado, para prevenir que el
 * servidor env�e alg�n fichero no deseado. Solamente est� autorizado a
 * servir los ficheros que se encuentren en el directorio actual y sus
 * subdirectorios. De otra forma, el servidor estar�a abierto y cualquiera
 * podr�a solicitar cualquier fichero del disco. Cuidado si se instala en
 * un red y se deja desatendido, porque se pueden conectar clientes y tomar
 * el control del ordenador, o hacer cosas extra�as.
 * La parte de "echo" se puede comprobar con el programa java1710, pensado
 * espec�ficamente para ese prop�sito.
 * La parte HTTP del programa se puede comprobar utilizando cualquier
 * navegador indicando que el servidor es "localhost". Tambi�n se puede
 * comprobar utilizando el ejemplo java1711, que est� pensado para
 * testear espec�ficamente el controlador de seguridad que se ha
 * instalado en este ejemplo.
 */
import java.net.*;
import java.io.*;
import java.util.*;

public class java1709 {
  public static void main( String[] args ) {
    // Se instancia el controlador de seguridad propio nuestro
    System.setSecurityManager( new MiSecurityManager() );
    // Se instancia un objeto servidor para escuchar el puerto 80
    ServidorHttp servidorHttp = new ServidorHttp();
    // Se instancia un objeto servidor para escuchar el puerto 7
    ServidorEco servidorEcho = new ServidorEco();
    }
  }


// Esta clase se utiliza para instanciar un controlador de seguridad que
// solamente permita descargar los ficheros que se encuentren en el
// directorio actual o en cualquier subdirectorio de ese directorio.
// Evidentemente, no instalar este servidor en una red, sin antes haber
// hecho cambios para que el control sea m�s exhaustivo, porque aqu�
// se ha dejado muy abierto, ya que solamente se pretende utilizar para
// pruebas
class MiSecurityManager extends SecurityManager {
  // Este m�todo sobreescrito es el que controla que el servidor solamente
  // permita descargar los ficheros del directorio actual y sus ramas,
  // es decir, que no permite navegar hacia arriba en el �rbol, ni
  // permite la descarga de una direcci�n absoluta, pero tiene
  // muchos agujeros de seguridad, as� que no debe utilizarse a no ser
  // que se complete adecuadamente, y adem�s se implemente la respuesta
  // que se va a dar en los dem�s m�todos del controlador de Seguridad
  public void checkRead( String str ) {
    if( new File(str).isAbsolute() || (str.indexOf("..") != -1 ) )
       throw new SecurityException( "\n"+str+": Acceso denegado.");
    }

  // Ahora se sobreescriben los dem�s m�todos, que es lo que hace que el
  // tema de seguridad quede totalmente abierto.
  public void checkAccept( String s,int i ){}
  public void checkAccess( Thread t ){}
  public void checkAccess( ThreadGroup g ){}
  public void checkAwtEventQueueAccess(){}
  public void checkConnect( String s,int i ){}
  public void checkConnect( String s,int i,Object o ){}
  public void checkCreateClassLoader(){}
  public void checkDelete( String s ){}
  public void checkExec( String s ){}
  public void checkExit( int i ){}
  public void checkLink( String s ){}
  public void checkListen( int i ){}
  public void checkMemberAccess( Class c,int i ){}
  public void checkMulticast( InetAddress a ){}
  public void checkPackageAccess( String s ){}
  public void checkPackageDefinition( String s ){}
  public void checkPrintJobAccess(){}
  public void checkPropertiesAccess(){}
  public void checkPropertyAccess( String s ){}
  public void checkRead( FileDescriptor f ){}
  // public void checkRead( String s ){} // Este es el �nico sobreescrito
  public void checkRead( String s,Object o ){}
  public void checkSecurityAccess( String s ){}
  public void checkSetFactory(){}
  public void checkSystemClipboardAccess(){}
  public boolean checkTopLevelWindow( Object o ) {
    return true;
    }
  public void checkWrite( FileDescriptor f ){}
  public void checkWrite( String s ){}
  }


// Esta es la clase que se utiliza para instanciar un hilo de ejecuci�n
// para el servidor que se encarga de escuchar el puerto 7, que es el
// definido como est�ndar para el protocolo de "echo"
class ServidorEco extends Thread {
  // Constructor
  ServidorEco() {
    // Arrancamos el hilo e invocamos al m�todo run() para que empiece
    // a correr
    start();
    }

  public void run() {
    try {
      // Se instancia un objeto sobre el puerto 7
      ServerSocket socket = new ServerSocket( 7 );
      System.out.println( "Servidor escuchando el 7" );
      // Entramos en bucle infinito ecuchando el puerto. Cuando se
      // produce una llamada, se lanza un hilo de ejecuci�n de
      // ConexionEcho para atenderla
      while( true )
        // Nos quedamos parados en el accept(), y devolvemos un socket
        // cuando se recibe la llamada. Este socket es el que se pasa
        // como par�metro al nuevo hilo de ejecuci�n que se crea
        new ConexionEcho( socket.accept() );
    } catch( IOException e ) {
      e.printStackTrace();
      }
    }
  }

// Esta clase se utiliza la lanzar un hilo de ejecuci�n que atienda
// la llamada recibida a trav�s del puerto 7, el puerto de eco
class ConexionEcho extends Thread {
  Socket socket;

  // Constructor
  ConexionEcho( Socket socket ) {
    System.out.println( "Recibida una llamada en el puerto 7" );
    this.socket = socket;
    // Trabajamos por debajo de la prioridad de los otros puertos
    setPriority( NORM_PRIORITY-1 );
    // Se arranca el hilo y se pone a correr
    start();
    }

  public void run() {
    System.out.println( "Lanzado el hilo de atencion del puerto 7" );
    BufferedReader entrada = null;
    PrintWriter salida = null;

    try {
      // Conseguimos un canal de entrada desde el socket
      entrada = new BufferedReader( new InputStreamReader(
        socket.getInputStream() ) );
      // Conseguimos un canal de salida hacia el socket. El canal es
      // con liberaci�n autom�tica (autoflush)
      salida = new PrintWriter( new OutputStreamWriter(
        socket.getOutputStream()),true );

      // Recogemos la cadena que llegue al puerto y la devolvemos en
      // el mismo instante
      String cadena = entrada.readLine();
      salida.println( cadena );
      System.out.println( "Recibido: "+cadena );
      socket.close();
      System.out.println( "Socket cerrado" );
    } catch( IOException e ) {
      e.printStackTrace();
      }
    }
  }



// Esta es la clase que se utiliza para instanciar un hilo de ejecuci�n
// para el servidor que se encarga de escuchar el puerto 80, que es el
// definido como est�ndar para el protocolo de "http"
class ServidorHttp extends Thread {
  // Constructor
  ServidorHttp() {
    // Arrancamos el hilo e invocamos al m�todo run() para que empiece
    // a correr
    start();
    }

  public void run(){
    try {
      // Se instancia un objeto sobre el puerto 80
      ServerSocket socket = new ServerSocket( 80 );
      System.out.println( "Servidor escuchando el puerto 80" );
      // Entramos en bucle infinito ecuchando el puerto. Cuando se
      // produce una llamada, se lanza un hilo de ejecuci�n de
      // ConexionHttp para atenderla
      while( true )
        // Nos quedamos parados en el accept(), y devolvemos un socket
        // cuando se recibe la llamada. Este socket es el que se pasa
        // como par�metro al nuevo hilo de ejecuci�n que se crea
        new ConexionHttp( socket.accept() );
    } catch( IOException e ) {
      e.printStackTrace();
      }
    }
  }


// Esta clase se utiliza la lanzar un hilo de ejecuci�n que atienda
// la llamada recibida a trav�s del puerto 80, el puerto de http
class ConexionHttp extends Thread {
  Socket socket;
  BufferedReader entrada = null;
  PrintWriter salida = null;
  DataOutputStream pagina = null;

  // Constructor
  ConexionHttp( Socket socket ) {
    System.out.println( "Recibida una llamada en el puerto 80" );
    this.socket = socket;
    // Trabajamos por debajo de la prioridad de los otros puertos
    setPriority( NORM_PRIORITY-1 );
    // Se arranca el hilo y se pone a correr
    start();
    }

  public void run() {
    System.out.println( "Lanzado el hilo de atencion al puerto 80" );
    try{
      // Conseguimos un canal de entrada desde el socket
      entrada = new BufferedReader( new InputStreamReader(
        socket.getInputStream() ) );

      // Conseguimos un canal de salida hacia el socket. El canal es
      // con liberaci�n autom�tica (autoflush)
      salida = new PrintWriter( new OutputStreamWriter(
        socket.getOutputStream() ),true );

      // Ahora conseguimos un canal de salida para enviar el contenido
      // del ficero que haya solicitado el usuario
      pagina = new DataOutputStream( socket.getOutputStream() );

      // Recogemos la cadena que llegue al puerto
      String peticion = entrada.readLine();
      System.out.println( "Recibida peticion: "+peticion );
      // Analizamos esa petici�n e intentamos responder. Solamente se
      // contesta a las peticiones GET, cualquier otra no tiene
      // respuesta alguna
      StringTokenizer st = new StringTokenizer( peticion );
       if( ( st.countTokens() >= 2 )
          && st.nextToken().equals("GET") ) {
          System.out.println( "Primer tag GET, correcto" );
          if( (peticion = st.nextToken()).startsWith("/") ) {
            System.out.println(
              "Siguiente toque que empieza por /, se le quita" );
            peticion = peticion.substring( 1 );
            }
          if( peticion.endsWith("/") || peticion.equals("") ) {
            System.out.println( "Peticion terminada en / o blanco, "+
              "se le incorpora: index.html" );
            peticion = peticion + "index.html";
            System.out.println( "Peticion modificada: "+peticion );
            }

          System.out.println( "Intento de desacarga de: "+peticion );
          FileInputStream fichero = new FileInputStream( peticion );
          // Se instancia un array de bytes igual al n�mero de bytes que
          // se pueden leer del canal de entrada sin bloquearlo.
          // Y luego se rellena con los datos
          byte[] datos = new byte[fichero.available()];
          fichero.read( datos );

          // Se env�a el array de datos al cliente
          pagina.write( datos );
          pagina.flush();
        } else
          // La petici�n que se ha hecho no es un GET
          salida.println( "<HTML><BODY><P>400 Petici&oactute;n "+
            "Err&oacute;nea<P></BODY></HTML>" );
      socket.close();
      System.out.println( "Socket cerrado" );
    }catch( FileNotFoundException e ) {
      salida.println(
        "<HTML><BODY><P>404 No Encontrado<P></BODY></HTML>" );
      try {
        socket.close();
        System.out.println( "Socket cerrado" );
      }catch( IOException evt ) {
        evt.printStackTrace();
        }
      }catch( SecurityException e ){
        e.printStackTrace();
        salida.println(
          "<HTML><BODY><P>403 Acceso denegado<P></BODY></HTML>" );
        salida.println( e );
        try {
          socket.close();
          System.out.println( "Socket cerrado" );
        }catch( IOException evt ) {
          evt.printStackTrace();
          }
      }catch( IOException e ) {
        e.printStackTrace();
        try {
          socket.close();
          System.out.println( "Socket cerrado" );
        }catch( IOException evt ) {
          System.out.println(evt);
        }
      }
    }
  }

//------------------------------------------ Final del fichero java1709.java