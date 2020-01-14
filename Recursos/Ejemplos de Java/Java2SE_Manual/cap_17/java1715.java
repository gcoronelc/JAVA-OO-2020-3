//
//  java1715.java
//  Copyright (c) 2001,2002 Agustin Froufe
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
//     Creacion: 29-Dic-2001  08:32:08
//     Revision: 03-Feb-2002  09:55:48
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta apliaci�n implementa un servidor HTTP que admite hasta 10
 * conexiones simult�neas (configurable) y escucha en el puerto 8080
 * (configurable)
 * Utiliza la clase SesionHTTP para tratar las peticiones HTTP que
 * recibe, lanz�ndola en una tarea idependiente para cada una de las
 * peticiones que reciba.
 * A la hora de lanzar el programa, es importante indicar correctamente
 * el directorio ra�z desde donde se servir�n las p�ginas html, ya que
 * el uso de may�suculas y min�sculas es cr�tico a la hora de validar
 * un archivo. Por ejemplo, si se invoca de la forma (en Windows):
 *   java java1715 c:\jdk1.4.0\docs
 * es probable que se obtenga como resultado una p�gina de acceso
 * prohibido, ya que la letra de las unidades de disco, el sistema las
 * devuelve en may�scula. Luego la llamada correcta ser�a:
 *   java java1715 C:\jdk1.4.0\docs
 */

import java.io.*;
import java.net.*;

public class java1715 {
  // N�mero m�ximo de conexiones simult�neas por defecto
  public static final int NUM_MAX_CONEXIONES = 10;
  // Pueto por defecto de conexi�n del servidor
  public static final int PUERTO_DEFECTO = 8080;
  // Direcci�n IP del servidor
  InetAddress direccionIP;
  // Socket en el que escuchamos y atendemos peticiones
  ServerSocket socket;
  // Puerto Http utilizado
  int puertoHttp;
  // N�mero m�ximo de conexiones simult�neas que admitimos
  int maxConexiones;
  int backlog;
  // N�mero de conexiones activas
  int conexionesActivas;
  // Controla la ejecuci�n del servidor, controla su funcionamiento
  boolean enEjecucion;
  // Directorio donde se encuentra el documento ra�z
  String docRaiz;

  // Clase que corresponde a la tarea que se lanza con cada una de
  // las conexiones que se establecen con el servidor
  class ThreadSesionHttp extends Thread {
    // Constructor de la tarea
    public ThreadSesionHttp( Runnable r ) {
      super( r );
      }

    // Arrancamos la tarea que asiste a la conexi�n.
    // Primero incrementamos el n�mero de conexiones activas y la
    // mantenemos activa mientras dure la sesi�n, para luego
    // decrementar el n�mero de conexiones y dejar posibilidad de
    // establecer otra conexi�n
    public void run() {
      incrementaNumConexiones();
      super.run();
      decrementaNumConexiones();
      }
    }

  // Incrementa el n�mero de conexiones activas, hasta alcanzar
  // el m�ximo admitido
  protected synchronized int incrementaNumConexiones() {
    return( ++conexionesActivas );
    }

  // Decrementea el n�mero de conexiones activas
  protected synchronized int decrementaNumConexiones() {
    return( --conexionesActivas );
    }

  // Contructor que admite solamente el directorio raiz desde donde
  // se publican los documentos, utilizando en el resto de los
  // par�metros los valores por defecto
  public java1715( String docRaiz ) {
    this( docRaiz,PUERTO_DEFECTO,NUM_MAX_CONEXIONES );
    }

  // Constructor
  public java1715( String raiz,int puerto,int maxConexiones ) {
    puertoHttp = puerto;
    this.maxConexiones = backlog = maxConexiones;
    if( maxConexiones > (NUM_MAX_CONEXIONES << 1) ) {
      backlog  = maxConexiones >> 1;
      }
    direccionIP = null;
    enEjecucion = false;
    conexionesActivas = 0;
    docRaiz = raiz;
    }

  // Devuelve el puerto Http utilizado por el servidor
  public int getPuerto() {
    return( puertoHttp );
    }

  // Devuelve el n�mero m�ximo de conexiones simult�neas admitidas
  public int getNumMaxConexiones() {
    return( maxConexiones );
    }

  // Devuelve el n�mero actual de conexiones activas
  public int getConexionesActivas() {
    return( conexionesActivas );
    }

  // Fija la direcci�n IP
  public void setDireccionIP( InetAddress dirIP ) {
    direccionIP = dirIP;
    }

  // Indica si el servidor est� en ejecuci�n o no
  public boolean estaEnEjecucion() {
    return( enEjecucion );
    }

  // Arranca el servidor
  public void start() throws IOException {
    Socket cliente;
    Thread sesion;

    // Si tenemos una direcci�n IP para el servidor, la usamos, y sino
    // usamos el constructor por defecto que abre el socket en la
    // propia m�quina
    if( direccionIP != null )
      socket = new ServerSocket( puertoHttp,backlog,direccionIP );
    else
      socket = new ServerSocket( puertoHttp,backlog );

    // Indicamos que el servidor se encuentra en ejecuci�n
    enEjecucion = true;
    System.out.println( "Servidor HTTP arrancado..." );

    while( enEjecucion ) {
      // Aceptamos la conexi�n con el cliente
      cliente  = socket.accept();
      if( conexionesActivas >= maxConexiones ) {
        // Si nos pasamos del m�ximo admitido, cerramos esa conexi�n
        cliente.close();
        continue;
        }
      // Esta implementaci�n es muy ineficiente. Si el lector la desea
      // utilizar en sus aplicaciones deber�a emplear un pool de
      // objetos y threads
      sesion = new ThreadSesionHttp( new SesionHTTP(
        docRaiz,cliente.getInputStream(),cliente.getOutputStream()) );
      sesion.start();
      }
    }

  // Detiene el servidor
  public void stop() {
    enEjecucion = false;
    }

  // M�todo principal de la aplicaci�n, que arranca el servidor HTTP
  public static void main( String[] args ) {
    java1715 servidor;
    // Hay que indicar el directorio donde se encuentra el documento
    // raiz del �rbol de p�ginas que queremos servir
    if( args.length != 1 ) {
      System.err.println( "Uso: java java1715 docRaiz" );
      System.exit( 1 );
      }

    // Creamos una instancia del servidor
    servidor = new java1715( args[0] );
    try {
      // ...y lo arrancamos
      servidor.start();
    } catch( IOException e ) {
      e.printStackTrace();
      }
    }
  }

//------------------------------------------ Final del fichero java1715.java
