//
//  java1807.java
//  Copyright (c) 1999,2002 Agustin Froufe
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
//     Creacion: 31-May-1999  22:32:02
//     Revision: 09-Feb-2002  14:19:33
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este servlet permite la autorización de usuarios a través del
 * mecanismo básico de HTTP, que esté implementado en el navegador
 * que se esté utilizando.
 * Presenta la ventana de entrada del navegador y contrasta el
 * identificador de usuario y la cotnraseña que se introduzca
 * contra los que se encuentran codificados en este código.
 * Para hacer que fuese más útil, habría que guardar la lista de
 * usuario en una base de datos y contrastar con ella la información
 * de login que introduzca el usuario.
 */
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class java1807 extends HttpServlet {
  Hashtable usuarios = new Hashtable();

  public void init( ServletConfig config ) throws ServletException {
    super.init( config );
    usuarios.put( "gandalf:primavera","acceso" );
    usuarios.put( "trancos:chorra","acceso" );
    usuarios.put( "gollum:queso","acceso" );
    usuarios.put( "pinguino:demonio","acceso" );
  }

  public void doGet( HttpServletRequest req,
    HttpServletResponse res ) throws ServletException,IOException {
    res.setContentType( "text/plain" );
    PrintWriter out = res.getWriter();

    // Recoge la cabecera de autorización HTTP
    String acceso = req.getHeader( "Authorization" );

    // Do we allow that user?
    if( !controlAcceso( acceso ) ) {
      // No tiene acceso, así que devolvemos al nevegador la negativa
      // al acceso del usaurio
      res.setHeader( "WWW-Authenticate","BASIC realm=\"usuarios\"" );
      res.sendError (res.SC_UNAUTHORIZED );
      // Aquí se puede proporcionar información al usuario para
      // que la próxima vez acierte
      }
    else {
      // Acceso permitido, le presentamos la página de saludo
      res.setContentType( "text/html" );
      out.println( "<HTML><HEAD><TITLE>Tutorial de Java, Servlets</TITLE></HEAD>"+
        "<BODY BGCOLOR=white TEXT=black LINK=blue VLINK=purple>"+
        "<H2>Servlets, Autenticación</H2>" );
      out.println( "<H1><font color=#0000ff>Acceso a la zona restringida</font>"+
        "<br><font color=#11ff11><I>CONCEDIDO</I></font></H1>" );
      // Envía el pie de la página
      out.println( "<table width=70% cellspacing=0 cellpadding=0 border=0>"+
        "<tr bgcolor=#0000ff><td align=center height=16>"+
        "<font face=Arial,Helvetica size=1 "+
        "color=#FFFFFF>&copy; 2002, Agustin Froufe</font></td>"+
        "</tr></table>"+
        "</BODY></HTML>" );
      }
    }

  // Este método comprueba la información que introduce el usuario en
  // la cabecera Authorization del protocolo WWW conta la lista de
  // usuarios que se mantiene en la tabla codificada en el código
  protected boolean controlAcceso( String acceso ) throws IOException {
    if( acceso == null ) {
      // Acceso denegado
      return( false );
      }

    if( !acceso.toUpperCase().startsWith( "BASIC " ) ) {
      // Acceso  básico solamente
      return( false );
      }

    // Cogemos el usuario y contraseña codificados que vienen
    // después de "BASIC "
    String claveCodificada = acceso.substring( 6 );

    // Se decodifica utilizando cualquier decodificador base64
    sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
    String claveDecodificada = new String(dec.decodeBuffer(claveCodificada));

    // Comprobamos contra la lista de usuarios y ese usuario y
    // contraseña tienen acceso permitido
    if( "acceso".equals( usuarios.get( claveDecodificada ) ) )
      return( true );
    else
      return( false );
    }

  // Devuelve la información de Copyright
  public String getServletInto() {
    return "java1807 Servlet, Tutorial de Java (C)A.Froufe";
    }
  }

//------------------------------------------ Final del fichero java1807.java