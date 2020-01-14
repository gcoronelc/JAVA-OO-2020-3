//
//  ServidorChat.java
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
//     Creacion: 07-Dec-2001  18:53:19
//     Revision: 09-Feb-2002  13:56:12
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Clase que implementa el servidor de Chat, y que se encarga de
 * crear las nuevas conexiones de los clientes que intenten entrar
 * en la conversación.
 */
import java.io.*;
import java.net.*;
import java.util.logging.*;
import java.util.*;

public class ServidorChat extends Thread implements LatidoIf {
  // Puerto por defecto en donde escucha el servidor
  public final static int PUERTO_DEFECTO = 5000;
  // Tiempo de espera para avisar al usuario de que está inactivo
  public final static int AVISO_INACTIVIDAD = 5*60;
  // Tiempo antes de eliminar al usuario de la charla, transcurrido el
  // cual se cerrará la conexión
  public final static int DESCONEXION = 60;
  // Intervalo en el que se comprueban las conexiones no activas
  public final static int INTERVALO_CHEQUEO = 10*1000;
  // Segundos después de los cuales se envía el mensaje de aviso de
  // inactividad, inicializado al valor de defecto
  protected int segsAviso = AVISO_INACTIVIDAD;
  // Segundos después del aviso de inactividad, a los que se cerrará
  // la conexión
  protected int segsExtra = DESCONEXION;
  // Puerto por el cual se reciben las conexiones de clientes
  protected int puerto = PUERTO_DEFECTO;
  // Período máximo de inactividad, tras el cual el cliente es eliminado
  protected int maxInactividad;
  // Socket del servidor
  protected ServerSocket socketServidor;
  // Colección que mantiene la lista de usuarios conectados
  protected Map usuarios;
  // Referencia a la tarea que invoca al servidor en busca de clientes que
  // estén en inactividad
  protected Latido latido;
  // Nombre del fichero de log
  protected String ficheroLog = "log.xml";
  // Referencia para el log
  protected Logger log;
  // Controlador para la presentación de los mensajes de log en la consola
  private static ConsoleHandler ch;
  // Controlador para el almacenamiento de los mensajes de log en fichero
  private static FileHandler fh;


  // Constructor utilizado para leer la línea de comandos o tomar los
  // valores por defecto de los distintos parámetros de control de la
  // charla
  public ServidorChat( String[] args ) {
    // Eliminamos los controladores globales de log
//    LogManager lm = LogManager.getLogManager();
//    lm.removeAllGlobalHandlers();
    log = Logger.getLogger( "charla" );

    // Procesamos los argumentos. Hay que tener cuidado con los "case",
    // porque no se utiliza "break" para que se procesen todos los
    // argumentos comenzando por el correcto
    switch( args.length ) {
      // Nombre del fichero de log
      case 4:
        ficheroLog = args[3];
      // Puerto por el cual se reciben las conexiones de clientes
      case 3:
        try {
          puerto = Integer.parseInt( args[2] );
        } catch( NumberFormatException e ) {
          uso();
          }
      // Segundos después del aviso de inactividad, a los que se
      // cerrará la conexión
      case 2:
        try {
          segsExtra = Integer.parseInt( args[1] );
        } catch( NumberFormatException e ) {
          uso();
          }
      // Segundos después de los cuales se envía el mensaje de aviso
      // de inactividad
      case 1:
        try {
          segsAviso = Integer.parseInt( args[0] );
        } catch( NumberFormatException e ) {
          uso();
          }
      case 0:
        break;
      default:
        uso();
      }

    try {
      fh = new FileHandler( ficheroLog );
    } catch ( Exception e ) {
      e.printStackTrace();
      }
    log.addHandler( fh );

    // Para ver también los mensajes de log en la consola
    try {
      ch = new ConsoleHandler();
    } catch ( Exception e ) {
      e.printStackTrace();
      }
    // Comentar la siguiente línea si no se quiere que aparezcan los
    // mensajes de log en la ventana
    log.addHandler( ch );
    log.setLevel( Level.ALL );

    // Imprimimos el valor de los parámetros
    System.out.println( "\nsegs_aviso   " +segsAviso
      + "\nsegs_extra   " + segsExtra
      + "\npuerto       " + puerto
      + "\nfichero_log  " + ficheroLog
      + "\n\nServidor activo y en espera de conexiones..." );

    // Transformamos los segundos a milisegundos
    segsAviso *= 1000;
    segsExtra *= 1000;
    maxInactividad = segsAviso + segsExtra;

    log.info( "Servidor arrancado..." );

    // Abrimos el puerto a la ecucha, esperando conexiones
    try {
      socketServidor = new ServerSocket( puerto );
    } catch( IOException e ) {
      e.printStackTrace();
      }

    // Creamos la colección para los usuarios
    usuarios = new TreeMap();
    // Creamosel comprobador de que no hya nadie inactivo
    latido = new Latido( INTERVALO_CHEQUEO,this );
    //... y arrancamos
    this.start();
    }


  // Este es el método en el que nos quedamos escuchando a ver si se
  // solicitan nuevas conexiones. Para cada una de ellas, arrancaremos
  // un nuevo objeto de tipo Conexion
  public void run() {
    try {
      while( true ) {
        Socket sockCliente = socketServidor.accept();
        Conexion c = new Conexion( sockCliente,this );
        }
    } catch( IOException e ) {
      e.printStackTrace();
      }
    }


  // Todas las conexiones de las que no se haya recibido dato alguno
  // y se haya sobrepasado el periodo de timeout, primero recibirán
  // un aviso de que están inactivas y luego serán eliminadas de la
  // charla
  public void latido() {
    Map aEliminar = new HashMap();
    Date d = new Date();
    long hora = d.getTime();

    // Recorremos la lista de conexiones avisando de la inactividad
    for( Iterator i=usuarios.entrySet().iterator(); i.hasNext(); ) {
      Map.Entry e = (Map.Entry)i.next();
      Conexion cnx = (Conexion)e.getValue();
      long inactividad = hora - cnx.getUltimaActividad();
      if( inactividad > maxInactividad ) {
        aEliminar.put( e.getKey(),cnx );
        }
      else if( inactividad > segsAviso ) {
        try {
          long timeout = (maxInactividad - inactividad)/1000;
          log.info( "Aviso de inactividad..." );
          String warn =
            new String( "AVISO DE INACTIVIDAD: serás eliminado en "+
            timeout+ " segundos" );
          cnx.envioGeneral( warn,cnx );
        } catch( Exception err ) {
          log.warning( "No se puede hablar con el cliente" );
          err.printStackTrace();
          }
        }
      }

    // Recorremos la lista de conexiones eliminando las inactivas
    for( Iterator i=aEliminar.entrySet().iterator(); i.hasNext(); ) {
      Map.Entry me = (Map.Entry)i.next();
      Conexion cnx = (Conexion)me.getValue();
      log.info( "Eliminado " +cnx.getNombreCliente() );
      cnx.envioGeneral( me.getKey() +" eliminado por Inactividad",
        cnx );
      cnx.eliminaCliente();

      try {
        cnx.cliente.close();
        log.info( "Cliente eliminado..." );
      } catch( IOException e2 ) {
        log.warning( "No se puede eliminar el cliente" );
        e2.printStackTrace();
        }
      }
    }

  // Método para indicar al usaurio los parámetros de lanzamiento
  // del servidor
  public static void uso() {
    System.out.println( "USO (todos los argumentos son opcionales):\n"
      + " segsAviso segsExtra puerto fichero_log\n"
      + "   segsAviso  Segundos de inactividad antes de avisar\n"
      + "   segsExtra  Segundos antes de aboratar la sesión\n"
      + "   puerto      Puerto en el que se atienden las conexiones\n"
      + "   fichero_log Fichero de almacén de lo que va charlando\n" );
    System.exit( 1 );
    }

  // Método principal del Servidor
  public static void main( String[] args ) {
    new ServidorChat( args );
    }
  }

//-------------------------------------- Final del fichero ServidorChat.java
