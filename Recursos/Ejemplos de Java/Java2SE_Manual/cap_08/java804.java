//
//  java804.java
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
//     Creacion: 02-Jun-1999  06:58:32
//     Revision: 03-Feb-2002  08:20:45
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esta es la versión servlet de los ejemplos presentados en esta sección,
 * graba en fichero el texto que se pasa a través del formulario y en la
 * página de respuesta devuelve al usuario el contenido del fichero que
 * contiene el texto que se va a recuperar
 * Para poder ejecutar el ejemplo, una copia de esta aplicación debe
 * colocarse en el directorio del servidor de servlets que se utilice.
 * Si se utiliza Jakarta-Tomcat, es necesario crear la siguiente estructura
 * de directorios bajo el directorio "webapps" de Tomcat:
 * <webapps>
 *    |
 *    +--- cap_08
 *           |
 *           +--- WEB-INF  (aquí se coloca el archivo "web.xml"
 *                  |
 *                  +--- classes   (aquí se coloca el archivo "java804.class"
 *
 * El contenido del archivo descriptor del ejemplo "web.xml" debe tener el
 * siguiente contenido:
 * <?xml version="1.0" encoding="ISO-8859-1"?>
 * <!DOCTYPE web-app
 *     PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 *     "http://java.sun.com/j2ee/dtds/web-app_2_3.dtd">
 *
 * <web-app>
 *   <servlet>
 *     <servlet-name>java804</servlet-name>
 *     <servlet-class>java804</servlet-class>
 *   </servlet>
 *
 *   <servlet-mapping>
 *     <servlet-name>java804</servlet-name>
 *     <url-pattern>/*</url-pattern>
 *   </servlet-mapping>
 * </web-app>
 *
 * Además, es necesario introducir una entrada en el archivo de configuración
 * general de Tomcat, "server.xml" de la forma:
 *
 * <Context path="/java804" docBase="cap_08" debug="0"/>
 *
 * Ahora se lanza Tomcat con el comando "startup" y desde un navegador
 * se podrá ejecutar el servlet, introduciendo la dirección:
 *
 *  http://localhost:8080/java804
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class java804 extends HttpServlet {

  // Este método presenta el formulario de entrada al usaurio, para que
  // pueda introducir el texto que se va a grabar en fichero
  protected void doGet( HttpServletRequest req,
    HttpServletResponse res ) throws ServletException,IOException {
    res.setContentType( "text/html" );
    PrintWriter out = res.getWriter();
    out.print( "<HTML><HEAD><TITLE>Tutorial de Java, Ficheros</TITLE></HEAD>"+
      "<BODY BGCOLOR=\"white\" TEXT=\"black\" LINK=\"blue\" "+
      "VLINK=\"purple\" ALINK=\"yellow\">"+
      "<H1>Tutorial de Java, Ficheros</H1>"+
      "<FORM METHOD=POST>"+
      "Introduce en el campo de texto la cadena que quieras grabar en<BR>"+
      "el fichero controlado por el servlet:<BR>"+
      "<INPUT TYPE=TEXT NAME=texto SIZE=40><BR>\n"+
      "<INPUT TYPE=SUBMIT NAME=accion VALUE=Grabar>\n"+
      "</FORM><TABLE WIDTH=70% CELLSPACING=0 CELLPADDING=0 BORDER=0>\n"+
      "<TR BGCOLOR=#0000FF><TD ALIGN=CENTER HEIGHT=16>\n"+
      "<FONT FACE=Arial,Helvetica SIZE=1\n"+
      "COLOR=#FFFFFF>&copy; 2002, Agustin Froufe</FONT></TD>\n"+
      "</BODY></HTML>" );
    out.close();
    }

  // Este es el método de respuesta a la acción del botón del formulario
  // de la página presentada por el servlet, informa del texto que ha
  // legado a través del formulario y del contenido del fichero
  public void doPost( HttpServletRequest req,
    HttpServletResponse res ) throws ServletException,IOException {
    // Directorio raiz del usuario
    String dir = System.getProperty( "user.home" );

    res.setContentType( "text/html" );
    PrintWriter out = res.getWriter();
    out.println( "<HTML>" );
    out.println( "<HEAD>" );
    out.println( "<TITLE>Tutorial de Java, Ficheros</TITLE>" );
    out.println( "</HEAD>" );
    out.println( "<BODY BGCOLOR=\"white\" TEXT=\"black\"" );
    out.println( "LINK=\"blue\" VLINK=\"purple\" ALINK=\"yellow\">");
    out.println("<H1>Tutorial de Java, Ficheros</H1>");

    // Se recupera la cadena introducida en el formulario
    String texto = req.getParameter( "texto" );
    if( texto != null ) {
      out.println( "<STRONG>Texto recibido del formulario:</STRONG>" );
      out.println( texto );
      }
    else {
      // Se ha pulsado el botón con el campo de texto vacío
      out.println("No has mandado nada a grabar.");
      }

    // Este es el bloque try-catch que engloba las acciones que se
    // realizan con ficheros, para poder recoger las excepciones que
    // pueda lanzar el sistema
    try {
      // Se crea un objeto File correspondiente al fichero donde se va a
      // grabar el texto
      File fichSalida = new File( dir +File.separatorChar+ "textoe.txt" );
      FileWriter salida = new FileWriter( fichSalida );
      // Se escribe el texto
      salida.write( texto );
      // Se cierra el fichero
      salida.close();

      // Se crea un objeto File para referenciar al fichero del que se va
      // a recuperar el contenido
      File fichEntrada = new File( dir +File.separatorChar+ "textol.txt" );
      FileReader entrada = new FileReader( fichEntrada );
      char c[] = new char[(char)fichEntrada.length()];
      int numBytes = entrada.read( c );
      String cadena = new String( c );
      out.println( "<P><STRONG>Texto recuperado del fichero:</STRONG>" );
      out.println( cadena );
      entrada.close();
    } catch( IOException e ) {
      e.printStackTrace();
      }

    // Parte final de la página, para que se pueda volver a relanzar
    // el servlet
    out.println( "<BR><BR><A HREF=\"" );
    out.println( req.getRequestURI() );
    out.println( "\">Volver al Formulario</A>" );
    out.println( "<TABLE WIDTH=70% CELLSPACING=0 CELLPADDING=0 BORDER=0>\n" );
    out.println( "<TR BGCOLOR=#0000FF><TD ALIGN=CENTER HEIGHT=16>\n" );
    out.println( "<FONT FACE=Arial,Helvetica SIZE=1\n" );
    out.println( "COLOR=#FFFFFF>&copy; 2002, Agustin Froufe</FONT></TD>\n" );
    out.println( "</BODY>" );
    out.println( "</HTML>" );
    out.close();
    }

  // Devuelve la información de Copyright
  public String getServletInto() {
    return "java804 Servlet, Tutorial de Java (C)A.Froufe";
    }
  }

//------------------------------------------- Final del fichero java804.java