//
//  java1713.java
//  Copyright (c) 1999,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  da±os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da±o.
//
//   Compilador: javac 1.4.0,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 21-Jan-1999  09:12:34
//     Revision: 03-Feb-2002  09:57:40
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo mejora el ejemplo java1709 añadiéndole soporte para
 * el protocolo echo a través del puerto 7, pero a través de
 * datagramas UDP.
 * En el ejemplo se implementan tres servidores diferentes sobre red IP,
 * y solamente se pretende que sea ilustrativo, no para utilizarlo como
 * algo serio. Si se hace así, el autor declina toda responsabilidad.
 * El primer servidor que se implementa es el servidor de echo "UDP"
 * a través de un hilo de ejecución que monitoriza un socket de tipo
 * datagrama. Este servidor devuelve el array de bytes que contenga el
 * datagrama que le llegue por ese puerto. El puerto 7 es el estándar
 * del protocolo echo, tanto para TCP como para UDP.
 * Otro de los servidores que implementa, es un servidor de "echo", creado
 * a través de un hilo de ejecución que monitoriza el puerto 7. Lo que hace
 * es devolver la cadena que recibe al cliente.
 * El otro servidor implementa el protocolo HTTP, a través de un hilo de
 * ejecución que monitoriza el puerto 80, estándar de este protocolo. Este
 * servidor tiene la capacidad de responder al comando GET que se solicite
 * desde el navegador y servir el fichero como un stream de bytes.
 * El hecho de que se implementen los dos servidores a la vez, sobre
 * distintos puertos, ilustra también la forma en que se puede utilizar el
 * mecanismo de los hilos de ejecución que proporciona Java para servir a
 * múltiples puertos, y en este caso, también muestra como en un mismo
 * programa se pueden mezclar comunicaciones TCP y UDP.
 * Hay un controlador de seguridad implementado, para prevenir que el
 * servidor envíe algún fichero no deseado. Solamente está autorizado a
 * servir los ficheros que se encuentren en el directorio actual y sus
 * subdirectorios. De otra forma, el servidor estaría abierto y cualquiera
 * podría solicitar cualquier fichero del disco. Cuidado si se instala en
 * un red y se deja desatendido, porque se pueden conectar clientes y tomar
 * el control del ordenador, o hacer cosas extrañas.
 * La parte HTTP del programa se puede comprobar utilizando cualquier
 * navegador indicando que el servidor es "localhost". También se puede
 * comprobar utilizando el ejemplo Sockets06, que está pensado para
 * testear específicamente el controlador de seguridad que se ha
 * instalado en este ejemplo.
 * La parte de "echo" se puede comprobar con el programa Sockets05, pensado
 * específicamente para ese propósito, realizando test tanto de la porción
 * UDP como de la porción TCP del programa.
 */
import java.net.*;
import java.io.*;
import java.util.*;

public class java1713 {
  public static void main( String[] args ) {
    // Se instancia el controlador de seguridad propio nuestro
    System.setSecurityManager( new MiSecurityManager() );
    // Se instancia un objeto servidor para escuchar el puerto 80
    ServidorHttp servidorHttp = new ServidorHttp();
    // Se instancia un objeto servidor para escuchar el puerto 7
    ServidorEco servidorEcho = new ServidorEco();
    // Se instancia un objeto servidor UDP para escuchar el puerto 7
    ServidorEcoUdp servidorEchoUdp = new ServidorEcoUdp();
    }
  }


// Esta clase se utiliza para instanciar un controlador de seguridad que
// solamente permita descargar los ficheros que se encuentren en el
// directorio actual o en cualquier subdirectorio de ese directorio.
// Evidentemente, no instalar este servidor en una red, sin antes haber
// hecho cambios para que el control sea más exhaustivo, porque aquí
// se ha dejado muy abierto, ya que solamente se pretende utilizar para
// pruebas
class MiSecurityManager extends SecurityManager {
  // Este método sobreescrito es el que controla que el servidor solamente
  // permita descargar los ficheros del directorio actual y sus ramas,
  // es decir, que no permite navegar hacia arriba en el árbol, ni
  // permite la descarga de una dirección absoluta, pero tiene
  // muchos agujeros de seguridad, así que no debe utilizarse a no ser
  // que se complete adecuadamente, y además se implemente la respuesta
  // que se va a dar en los demás métodos del controlador de Seguridad
  public void checkRead( String str ) {
    if( new File(str).isAbsolute() || (str.indexOf("..") != -1 ) )
       throw new SecurityException( "\n"+str+": Acceso denegado.");
    }

  // Ahora se sobreescriben los demás métodos, que es lo que hace que el
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
  // public void checkRead( String s ){} // Este es el único sobreescrito
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

// Esta es la clase que se utiliza para instanciar un hilo de ejecución
// para el servidor Udp que se encarga de escuchar el puerto 7, que es
// el definido como estándar para el protocolo de "echo"
class ServidorEcoUdp extends Thread {
  // Constructor
  ServidorEcoUdp() {
    // Arrancamos el hilo e invocamos al método run() para que empiece
    // a correr
    start();
    }

  public void run() {
    try {
      // Se instancia un objeto sobre el puerto 7
      DatagramSocket socketDgrama = new DatagramSocket( 7 );
      System.out.println( "Servidor Udp escuchando el 7" );
      // Entramos en bucle infinito ecuchando el puerto. Cuando se
      // produce una llamada, se lanza un hilo de ejecución de
      // ConexionEchoUdp para atenderla
      while( true ) {
        // Limitamos la cadena de eco a 1024 bytes
        DatagramPacket paquete = new DatagramPacket( new byte[1024],1024 );
        // Nos quedamos parados en el receive(), y devolvemos un socket
        // cuando se recibe la llamada. Este socket es el que se pasa
        // como parámetro al nuevo hilo de ejecución que se crrea
        socketDgrama.receive( paquete );
        new ConexionEcoUdp( paquete );
      }
    } catch( IOException e ) {
      e.printStackTrace();
      }
    }
  }

// Esta clase se utiliza la lanzar un hilo de ejecución que atienda
// la llamada recibida a través del puerto 7, el puerto de eco
class ConexionEcoUdp extends Thread {
  DatagramPacket paquete;

  // Constructor
  ConexionEcoUdp( DatagramPacket paquete ) {
    System.out.println( "Recibida una llamada en el puerto 7" );
    this.paquete = paquete;
    // Trabajamos por debajo de la prioridad de los otros puertos
    setPriority( NORM_PRIORITY-1 );
    // Se arranca el hilo y se pone a correr
    start();
    }

  public void run() {
    System.out.println( "Lanzado el hilo UDP de atencion del puerto 7" );

    // Se crea el paquete de eco basándonos en los datos del paquete
    // que se ha recibido como parámetro
    DatagramPacket paqueteEnvio = new DatagramPacket(
      paquete.getData(),paquete.getLength(),
      paquete.getAddress(),paquete.getPort() );
    // Declaramos el socket datagrama
    DatagramSocket socketDgrama = null;

    try {
      // Abrimos un socket datagrama
      socketDgrama = new DatagramSocket();
      // Se utiliza el nuevo socket datagrama para enviar el mensaje
      // y cerrar el socket
      socketDgrama.send( paqueteEnvio );
      socketDgrama.close();
      System.out.println("Socket UDP cerrado." );
    }catch( UnknownHostException e ) {
      socketDgrama.close();
      System.out.println("Socket UDP cerrado." );
      e.printStackTrace();
    }catch( SocketException e ) {
      socketDgrama.close();
      System.out.println("Socket UDP cerrado." );
      e.printStackTrace();
    }catch( IOException e ) {
      socketDgrama.close();
      System.out.println("Socket UDP cerrado." );
      e.printStackTrace();
      }
    }
  }


// Esta es la clase que se utiliza para instanciar un hilo de ejecución
// para el servidor que se encarga de escuchar el puerto 7, que es el
// definido como estándar para el protocolo de "echo"
class ServidorEco extends Thread {
  // Constructor
  ServidorEco() {
    // Arrancamos el hilo e invocamos al método run() para que empiece
    // a correr
    start();
    }

  public void run() {
    try {
      // Se instancia un objeto sobre el puerto 7
      ServerSocket socket = new ServerSocket( 7 );
      System.out.println( "Servidor escuchando el 7" );
      // Entramos en bucle infinito ecuchando el puerto. Cuando se
      // produce una llamada, se lanza un hilo de ejecución de
      // ConexionEcho para atenderla
      while( true )
        // Nos quedamos parados en el accept(), y devolvemos un socket
        // cuando se recibe la llamada. Este socket es el que se pasa
        // como parámetro al nuevo hilo de ejecución que se crea
        new ConexionEcho( socket.accept() );
    } catch( IOException e ) {
      e.printStackTrace();
      }
    }
  }

// Esta clase se utiliza la lanzar un hilo de ejecución que atienda
// la llamada recibida a través del puerto 7, el puerto de eco
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
      // con liberación automática (autoflush)
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



// Esta es la clase que se utiliza para instanciar un hilo de ejecución
// para el servidor que se encarga de escuchar el puerto 80, que es el
// definido como estándar para el protocolo de "http"
class ServidorHttp extends Thread {
  // Constructor
  ServidorHttp() {
    // Arrancamos el hilo e invocamos al método run() para que empiece
    // a correr
    start();
    }

  public void run(){
    try {
      // Se instancia un objeto sobre el puerto 80
      ServerSocket socket = new ServerSocket( 80 );
      System.out.println( "Servidor escuchando el puerto 80" );
      // Entramos en bucle infinito ecuchando el puerto. Cuando se
      // produce una llamada, se lanza un hilo de ejecución de
      // ConexionHttp para atenderla
      while( true )
        // Nos quedamos parados en el accept(), y devolvemos un socket
        // cuando se recibe la llamada. Este socket es el que se pasa
        // como parámetro al nuevo hilo de ejecución que se crea
        new ConexionHttp( socket.accept() );
    } catch( IOException e ) {
      e.printStackTrace();
      }
    }
  }


// Esta clase se utiliza la lanzar un hilo de ejecución que atienda
// la llamada recibida a través del puerto 80, el puerto de http
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
      // con liberación automática (autoflush)
      salida = new PrintWriter( new OutputStreamWriter(
        socket.getOutputStream() ),true );

      // Ahora conseguimos un canal de salida para enviar el contenido
      // del ficero que haya solicitado el usuario
      pagina = new DataOutputStream( socket.getOutputStream() );

      // Recogemos la cadena que llegue al puerto
      String peticion = entrada.readLine();
      System.out.println( "Recibida peticion: "+peticion );
      // Analizamos esa petición e intentamos responder. Solamente se
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
          // Se instancia un array de bytes igual al número de bytes que
          // se pueden leer del canal de entrada sin bloquearlo.
          // Y luego se rellena con los datos
          byte[] datos = new byte[fichero.available()];
          fichero.read( datos );

          // Se envía el array de datos al cliente
          pagina.write( datos );
          pagina.flush();
        } else
          // La petición que se ha hecho no es un GET
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

//------------------------------------------ Final del fichero java1713.java