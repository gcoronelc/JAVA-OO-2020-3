//
//  java1802.java
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
//     Creacion: 23-May-1999  12:16:34
//     Revision: 09-Feb-2002  14:11:04
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo de servlet maneja una lista de correo, manipulando los
 * datos a través de un formulario que se presenta al cliente.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Vector;

public class java1802 extends HttpServlet {
  private ServletContext sc;
  private Vector direcciones;
  private String fichero;

  public void init( ServletConfig config ) throws ServletException {
    super.init( config );
    sc = config.getServletContext();

    // Se debe haber fijado la propiedad que indica el fichero de correo
    // a utilizar para mantener actualizados los datos que llegan de los
    // clientes
    fichero = config.getInitParameter( "fichero_correo" );
    if( fichero == null ) {
      throw new UnavailableException(
        "La propiedad \"fichero_correo\" debe ser un nombre de fichero" );
      }
    try {
      ObjectInputStream in = new ObjectInputStream(
        new FileInputStream( sc.getRealPath(fichero) ) );
      direcciones = (Vector)in.readObject();
      in.close();
    } catch( FileNotFoundException e ) {
      direcciones = new Vector();
    } catch( Exception e ) {
      throw new UnavailableException(
        "Error leyendo el fichero de direcciones: "+e );
      }
    }


  protected void doGet( HttpServletRequest req,
    HttpServletResponse res ) throws ServletException,IOException {
    res.setContentType( "text/html" );
    res.setHeader( "pragma","no-cache" );
    PrintWriter out = res.getWriter();

    // Genera la página de respuesta al cliente, informando de las direcciones
    // que contiene la lista y el formulario de entrada de nueva dirección
    // para que el cliente pueda dar de alta o baja direcciones
    out.print( "<HTML>\n" );
    out.print( "<HEAD>\n" );
    out.print( "<TITLE>Tutorial de Java, Servlets</TITLE>\n" );
    out.print( "</HEAD>\n" );
    out.print( "<BODY BGCOLOR=\"white\" TEXT=\"black\" LINK=\"blue\" VLINK=\"purple\" ALINK=\"yellow\">\n");
    out.print( "<H2>Servlets, Lista de Correo</H2>\n" );
    out.print( "<H3>Miembros:</H3><UL>\n" );
    for( int i=0; i < direcciones.size(); i++ )
      out.print( "<LI>"+direcciones.elementAt(i) );
    out.print( "</UL><HR>\n" );
    out.print( "<FORM METHOD=POST>" );
    out.print( "Introduce tu dirección de correo electrónico: <INPUT TYPE=TEXT NAME=email><BR>\n" );
    out.print( "<INPUT TYPE=SUBMIT NAME=accion VALUE=alta>\n" );
    out.print( "<INPUT TYPE=SUBMIT NAME=accion VALUE=baja>\n" );
    out.print( "</FORM>\n" );
    out.print( "<table width=70% cellspacing=0 cellpadding=0 border=0>\n");
    out.print( "<tr bgcolor=#0000ff><td align=center height=16>\n");
    out.print( "<font face=Arial,Helvetica size=1\n");
    out.print( "color=#FFFFFF>&copy; 2002, Agustin Froufe</font></td>\n");
    out.print( "</tr></table>\n");
    out.print( "</BODY>\n" );
    out.print( "</HTML>" );
    out.close();
    }


  protected void doPost( HttpServletRequest req,
    HttpServletResponse res ) throws ServletException,IOException {
    String email = req.getParameter( "email" );
    String msg;

    // Informa de la acción realizada, sea alta, baja o indica el error
    // que se ha producido al realizar la acción que se ha solicitado
    if( email == null )
      msg = "No has indicado la dirección de correo.";

    if( req.getParameter( "accion" ).equals( "alta" ) ) {
      if( alta(email) )
        msg = "La dirección " + email + " ha sido dada de alta.";
      else
        msg = "La dirección " + email + " ya figuraba en la lista.";
      }
    else {
      if( baja(email) )
        msg = "La dirección " + email + " ha causado baja.";
      else
        msg = "La dirección " + email + " no se ha suscrito.";
      }

    res.setContentType( "text/html" );
    res.setHeader( "pragma","no-cache" );
    PrintWriter out = res.getWriter();
    out.print( "<HTML>\n" );
    out.print( "<HEAD>\n" );
    out.print( "<TITLE>Tutorial de Java, Servlets</TITLE>\n" );
    out.print( "</HEAD>\n" );
    out.print( "<BODY BGCOLOR=\"white\" TEXT=\"black\" LINK=\"blue\" VLINK=\"purple\" ALINK=\"yellow\">\n");
    out.print(msg);
    out.print( "<HR><A HREF=\"" );
    out.print( req.getRequestURI() );
    out.print( "\">Ver la Lista</A>\n");
    out.print( "</BODY>\n" );
    out.print( "</HTML>" );
    out.close();
    }


  private synchronized boolean alta( String email ) throws IOException {
    // Comprueba si la dirección está ya el la lista
    if( direcciones.contains( email ) )
      return( false );
    // Añade la nueva dirección y graba el fichero
    direcciones.addElement( email );
    grabar();

    return( true );
    }


  private synchronized boolean baja( String email ) throws IOException {
    // Intenta eliminar la dirección
    if( !direcciones.removeElement( email ) )
      return( false );
    // Graba el resultado
    grabar();

    return( true );
    }


  // Graba el contenido del Vector que maneja las direcciones en el
  // fichero que se haya especificado
  private void grabar() throws IOException {
    ObjectOutputStream out = new ObjectOutputStream(
      new FileOutputStream( sc.getRealPath(fichero) ) );
    out.writeObject( direcciones );
    out.close();
    }


  // Devuelve la información de Copyright
  public String getServletInto() {
    return "java1802 Servlet, Tutorial de Java (C)A.Froufe";
    }
  }

//------------------------------------------ Final del fichero java1802.java