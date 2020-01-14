//
//  Conexion.java
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
//     Creacion: 07-Dic-2001  18:44:26
//     Revision: 09-Feb-2002  13:54:56
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Es la clase que maneja todas las conexiones con los clientes. Envía
 * los mensajes a todos los usuarios o solamente a algunos de ellos, en
 * cuyo caso es necesario haber seleccionado el grupo de ellos. Esto
 * permite el envío de mensajes privados entre clientes.
 * Cada cliente que se conecta dispone de un objeto de este tipo
 */
import java.io.*;
import java.net.*;
import java.util.logging.*;
import java.util.*;

public class Conexion extends Thread {
  // Socket cliente pasado desde el servidor
  protected Socket cliente;
  // Canal de salida hacia el socket cliente
  protected ObjectOutputStream salida;
  // Canal de entrada desde el socket cliente
  protected ObjectInputStream entrada;
  // Dirección IP del cliente
  protected InetAddress ipCliente;
  // Puerto de conexión del cliente
  protected int puertoCliente;
  // Nombre identificativo del cliente
  protected String nombreCliente;
  // Ultimo instante de actividad del cliente
  protected long ultimoEnvio;
  // Grupo específico de clientes a los que enviar un mensaje
  protected volatile Set gupoPrivado = null;
  // Objeto serializable de comunicación entre cliente y servidor
  protected ObjSerie objSerie = null;
  // Mapa de todos los usuarios de la Charla
  protected Map usuarios;
  // Referencia al log del servidor
  protected Logger log;

  // Inicializamos los canales de comunicaciones y arrancamos la
  // tarea
  public Conexion( Socket sockCliente,ServidorChat servidor ) {
    cliente = sockCliente;
    ultimoEnvio = (new Date()).getTime();
    usuarios = servidor.usuarios;
    log = servidor.log;
    log.info( "Conexión aceptada. Lanzando tareas..." );

    try {
      entrada = new ObjectInputStream( cliente.getInputStream() );
      log.info( "Creado el canal de entrada para el nuevo socket" );
      salida = new ObjectOutputStream( cliente.getOutputStream() );
      log.info( "Creado el canal de salida para el nuevo socket" );
    } catch( IOException e ) {
      try {
        cliente.close();
        log.info( "Socket cliente cerrado.." );
        eliminaCliente();
      } catch( IOException e2 ) {
        e.printStackTrace();
        }
      log.warning( "Error abriedo canales: "+e );
      return;
      }

    ipCliente = cliente.getInetAddress();
    puertoCliente = cliente.getPort();
    nombreCliente = ipCliente + ":" + puertoCliente;
    log.info( "Recogiendo conexión de: "+nombreCliente );
    this.start();
    }

  // Método de ejecución de la tarea
  public void run() {
    String linea;
    int longitud;

    try {
      // Bucle infinito
      for( ;; ) {
        // Leemos el objeto serializado desde el canal de entrada
        objSerie = (ObjSerie)entrada.readObject();
        // Obtenemos el mensaje
        linea = objSerie.getMensaje();
        if( linea != null ) {
          log.info( "Mensaje: "+linea );
          }
        // Actualizamos el momento de comunicación
        ultimoEnvio = (new Date()).getTime();
        // Si se nos indica la desconexión de la charla, lo pregonamos
        // a todo el mundo
        if( objSerie.Salida() ) {
          envioGeneral( "El usuario " +nombreCliente+
            " abandona la charla." );
          log.info( "Recibida petición de desconexión.." );
          eliminaCliente();
          break;
          }
        // Si se nos indica una nueva incorporación a la charla, hacemos
        // las oportunas comprobaciones y también lo pregonamos
        if( objSerie.Entrada() ) {
          // Obtenemos el identificador del nuevo cliente
          nombreCliente = objSerie.getNombreUsuario();
          // Si no existe nadie con ese identificador, aceptamos la
          // incorporación y lo metemos en la lista de usuarios
          if( !usuarios.containsKey(nombreCliente) ){
            incorporaCliente( nombreCliente,this );
            salida.writeObject( new ObjSerie("Entrada aceptada...",0) );
            salida.flush();
            envioGeneral( "El usuario " +nombreCliente+
              " se incorpora a la charla." );
            }
          // Sino, le indicamos que elija otro identificador
          else {
            salida.writeObject(
              new ObjSerie("Elige otro nick, por favor",1) );
            salida.flush();
            }
          continue;
          }
        // Si es un mensaje privado, lo mandamos solamente a la lista
        // de usuarios que se nos indique
        if( objSerie.Restriccion() ) {
          String[] arrayUsuarios = objSerie.getArrayUsuarios();
          envioGeneral( nombreCliente+ "> "+linea,arrayUsuarios );
          continue;
          }
        // En cualquier otro caso, enviamos el mensaje a todo cliente
        // que se encuentre conectado a la charla
        envioGeneral( nombreCliente+ "> "+linea );
        }
    } catch( IOException e ) {
      log.warning( "Excepción I/O hablando con " +
        nombreCliente+ "("+ipCliente+":"+puertoCliente+ ") :"+e );
    } catch( ClassNotFoundException e ) {
      log.warning( "Excepción por no encontrar clase hablanco con " +
        nombreCliente+ "("+ipCliente+":"+puertoCliente+") :"+e );
    } finally {
      log.info( "Conexión con " +ipCliente+ ":" +puertoCliente+
        " no activa. Cerrando..." );
      try {
        cliente.close();
        log.info( "Cerrado el socket roto con " +
          ipCliente+ ":"+puertoCliente );
        eliminaCliente();
      } catch( IOException e2 ) {
        log.warning( "Excepción cerrando el socket para " +
          ipCliente+ ":" +puertoCliente+" :"+e2 );
      } finally {
        cliente = null;
        log.info( "Socket roto eliminado..." );
        usuarios.remove( ipCliente+ ":" +puertoCliente );
        envioGeneral( nombreCliente+ " (" +ipCliente+ ":" +
          puertoCliente+ ") abandona la charla." );
        }
      }
    }

  // Devuelve el momento en que se realizó la última actividad
  // del cliente. Se utiliza en lá investigación de la inactividad
  // para dar de baja a un cliente
  synchronized public long getUltimaActividad() {
    return( ultimoEnvio );
    }

  // Devuelve al servidor el nombre del cliente
  public String getNombreCliente() {;
    return( nombreCliente );
    }

  // Envía un mensaje a todos los clientes activos de la charla
  synchronized public void envioGeneral( String s ) {
    // Creamos una segunda lista para evitar los errores provocados
    // por la serialización.
    // La forma de evitarlos es crear esa segunda lista y convirtiendo
    // cada usuario en una cadena que se añade a la segunda lista
    Set lista = usuarios.keySet();
    Set lista2 = new HashSet();

    for( Iterator i=lista.iterator(); i.hasNext(); ) {
      lista2.add( i.next().toString() );
      }
    for( Iterator i = usuarios.entrySet().iterator(); i.hasNext(); ) {
      Map.Entry e = (Map.Entry) i.next();
      Conexion cnx = (Conexion)e.getValue();
      try {
        cnx.salida.writeObject( new ObjSerie(s,lista2) );
        cnx.salida.flush();
      } catch( IOException err ) {
        log.warning( "Excepción en envío a " +
          nombreCliente+ "("+ipCliente+":"+puertoCliente+") :"+err );
        }
      }
    log.info( "Enviando mensaje general: "+s );
    }

  // Se envía un mensaje a un grupo determinado de clientes de
  // la charla
  synchronized public void envioGeneral( String s,String[] grupo ) {
    Set lista = usuarios.keySet();
    Set lista2 = new HashSet();
    for( Iterator i=lista.iterator(); i.hasNext(); ) {
      lista2.add( i.next().toString() );
      }
    for( int i=0; i < grupo.length; i++ ) {
      Conexion cnx = (Conexion)usuarios.get( grupo[i] );
      try {
        cnx.salida.writeObject( new ObjSerie(s,lista2) );
        cnx.salida.flush();
      } catch( IOException err ) {
        log.warning( "Excepción en envío a " +
          nombreCliente+ "("+ipCliente+":"+puertoCliente+") :"+err );
        }
      }
    log.info( "Enviando mensaje grupo: "+s );
    }

  // Se envía un mensaje a un determinado cliente, a uno solo
  synchronized public void envioGeneral( String s,Conexion usuario ) {
    try {
      usuario.salida.writeObject( new ObjSerie(s) );
      usuario.salida.flush();
    } catch( IOException err ) {
      log.warning( "Excepción en envío a " +
        nombreCliente+ "("+ipCliente+ ":"+puertoCliente+") :"+err );
      }
    log.info( "Enviando mensaje individual: "+s );
    }

  // Elimina un usuario de la colección de control de usuarios
  synchronized public void eliminaCliente() {
    usuarios.remove( nombreCliente );
    }

  // Añade un usuario a la colección de control de usuarios
  synchronized public void incorporaCliente( String nombreCliente,
    Conexion cnx ) {
    usuarios.put( nombreCliente,cnx );
    }
  }

//------------------------------------------ Final del fichero Conexion.java
