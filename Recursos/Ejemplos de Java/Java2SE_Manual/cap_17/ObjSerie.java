//
//  ObjSerie.java
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
//     Creacion: 07-Dic-2001  18:49:40
//     Revision: 09-Feb-2002  13:54:29
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta es la clase que implementa el protocolo de la aplicación
 * que simplemente consiste en el paso de información entre el
 * cliente y el servidor, ya sea de los mensajes que se intercambian
 * los componente s de la charla como otros del estado de los
 * usuarios o de las acciones que desean realizar.
 * Dispone de varios constructores, en función de la información que
 * debe trasladar. Los objetos que se creen de este tipo, ya sea
 * por parte del cliente o por parte del servidor serán serializados
 */
import java.io.*;
import java.util.*;

public class ObjSerie implements Serializable {
  // Mensaje de texto
  protected String mensaje = null;
  // Lista de participantes en la charla
  protected Set usuarios;
  // Flas para indicar la entrada en la charla
  protected boolean logon = false;
  // Flag para indicar la salida de la charla
  protected boolean logoff = false;
  // Nombre del usuario que seraliza el objeto
  protected String nombreUsuario = null;
  // Flag que indica al servidor si solamente se debe enviar el
  // mensaje a un usuario determinado de la lista
  protected boolean restringido = false;
  protected String[] arrayUsuarios = null;
  // Flag indicando si se acepta o no la incorporación a la charla
  protected boolean confirmacion = false;

  // Constructor utilizando solamente un mensaje
  public ObjSerie( String mensaje ) {
    this.mensaje = mensaje;
    }

  // Constructor utilizado para actualizar la lista de usuarios
  public ObjSerie( Set usuarios ) {
    this.usuarios = usuarios;
    }

  // Constructor utilizado para confirmar o no la entrada en la charla
  public ObjSerie( String mensaje,int login ) {
    this.mensaje = mensaje;
    if( login == 0 ) {
      confirmacion = true;
      }
    }

  // Constructor utilizado por servidor y cliente para enviar la
  // petición de desconexión
  public ObjSerie( String mensaje,boolean logoff ) {
    this.mensaje = mensaje;
    this.logoff = logoff;
    }

  // Constructor utilizado por el cleinte para enviar la petición
  // de incorporación a la charla
  public ObjSerie( boolean logon,String nombreUsuario ) {
    this.nombreUsuario = nombreUsuario;
    this.logon = logon;
    }

  // Constructor utilizado por el servidor para enviar mensajes y
  // la lista de usuario actualizada
  public ObjSerie( String mensaje,Set usuarios ) {
    this.mensaje = mensaje;
    this.usuarios = usuarios;
    this.restringido = true;
    }

  // Constructor utilizado por el cliente para enviar un mensaje y
  // un array de usuarios a los que enviar selectivamente ese mensaje
  public ObjSerie( String mensaje,String[] usuarios ) {
    this.mensaje = mensaje;
    this.arrayUsuarios = usuarios;
    this.restringido = true;
    }

  // Indica si un cliente quiere entrar en la charla
  public boolean Entrada() {
    return( logon );
    }

  // Método utilizado por el cliente o el servidor cuando se solicita
  // o se obliga a la desconexión
  public boolean Salida() {
    return( logoff );
    }

  // Indica si un cliente quiere enviar el mensaje a un grupo
  // restringido de usuarios
  public boolean Restriccion() {
    return( restringido );
    }

  // Devuelve la lista de participantes en la charla
  public Set getUsuarios() {
    return( usuarios );
    }

  // Devuelve la lista a la que un cliente quiere enviar su mensaje
  public String[] getArrayUsuarios() {
    return( arrayUsuarios );
    }

  // Devuelve el nombre del usuario que ha introducido en el login
  public String getNombreUsuario() {
    return( nombreUsuario );
    }

  // Devuelve el mensaje
  public String getMensaje(){
    return( mensaje );
    }

  // Método utilizado por el cliente para comprobar si el servidor ha
  // aceptado su participación en la charla
  public boolean loginOK() {
    return( confirmacion );
    }
  }

//------------------------------------------ Final del fichero ObjSerie.java
