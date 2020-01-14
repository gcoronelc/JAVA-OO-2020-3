//
//  java1807.java
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
//     Creacion: 31-May-1999  22:32:02
//     Revision: 09-Feb-2002  14:19:33
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este servlet permite la autorizaci�n de usuarios a trav�s del
 * mecanismo b�sico de HTTP, que est� implementado en el navegador
 * que se est� utilizando.
 * Presenta la ventana de entrada del navegador y contrasta el
 * identificador de usuario y la cotnrase�a que se introduzca
 * contra los que se encuentran codificados en este c�digo.
 * Para hacer que fuese m�s �til, habr�a que guardar la lista de
 * usuario en una base de datos y contrastar con ella la informaci�n
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

    // Recoge la cabecera de autorizaci�n HTTP
    String acceso = req.getHeader( "Authorization" );

    // Do we allow that user?
    if( !controlAcceso( acceso ) ) {
      // No tiene acceso, as� que devolvemos al nevegador la negativa
      // al acceso del usaurio
      res.setHeader( "WWW-Authenticate","BASIC realm=\"usuarios\"" );
      res.sendError (res.SC_UNAUTHORIZED );
      // Aqu� se puede proporcionar informaci�n al usuario para
      // que la pr�xima vez acierte
      }
    else {
      // Acceso permitido, le presentamos la p�gina de saludo
      res.setContentType( "text/html" );
      out.println( "<HTML><HEAD><TITLE>Tutorial de Java, Servlets</TITLE></HEAD>"+
        "<BODY BGCOLOR=white TEXT=black LINK=blue VLINK=purple>"+
        "<H2>Servlets, Autenticaci�n</H2>" );
      out.println( "<H1><font color=#0000ff>Acceso a la zona restringida</font>"+
        "<br><font color=#11ff11><I>CONCEDIDO</I></font></H1>" );
      // Env�a el pie de la p�gina
      out.println( "<table width=70% cellspacing=0 cellpadding=0 border=0>"+
        "<tr bgcolor=#0000ff><td align=center height=16>"+
        "<font face=Arial,Helvetica size=1 "+
        "color=#FFFFFF>&copy; 2002, Agustin Froufe</font></td>"+
        "</tr></table>"+
        "</BODY></HTML>" );
      }
    }

  // Este m�todo comprueba la informaci�n que introduce el usuario en
  // la cabecera Authorization del protocolo WWW conta la lista de
  // usuarios que se mantiene en la tabla codificada en el c�digo
  protected boolean controlAcceso( String acceso ) throws IOException {
    if( acceso == null ) {
      // Acceso denegado
      return( false );
      }

    if( !acceso.toUpperCase().startsWith( "BASIC " ) ) {
      // Acceso  b�sico solamente
      return( false );
      }

    // Cogemos el usuario y contrase�a codificados que vienen
    // despu�s de "BASIC "
    String claveCodificada = acceso.substring( 6 );

    // Se decodifica utilizando cualquier decodificador base64
    sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
    String claveDecodificada = new String(dec.decodeBuffer(claveCodificada));

    // Comprobamos contra la lista de usuarios y ese usuario y
    // contrase�a tienen acceso permitido
    if( "acceso".equals( usuarios.get( claveDecodificada ) ) )
      return( true );
    else
      return( false );
    }

  // Devuelve la informaci�n de Copyright
  public String getServletInto() {
    return "java1807 Servlet, Tutorial de Java (C)A.Froufe";
    }
  }

//------------------------------------------ Final del fichero java1807.java