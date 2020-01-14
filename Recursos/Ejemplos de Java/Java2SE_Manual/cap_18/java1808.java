//
//  java1808.java
//  Copyright (c) 2002, Agustin Froufe
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
//     Creacion: 12-Ene-2002  11:46:30
//     Revision: 09-Feb-2002  14:21:04
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class java1808 extends HttpServlet{
  private ServletContext ctx;

  public void init( ServletConfig conf ) throws ServletException {
    super.init( conf );
    ServletContext ctx = conf.getServletContext();
    ctx.log( "Creando el servlet..." );
    }

  // Este es el método que trata la petición.
  public void doGet( HttpServletRequest req,HttpServletResponse resp )
    throws IOException,ServletException {
    System.out.println( "Dentro del Servlet..." );

    resp.setContentType( "text/html" );
    PrintWriter pw = resp.getWriter();
    pw.println( "<html><head>");
    pw.println( "<title>Tutorial de Java, Servlets</title>");
    pw.println( "</head><body>");
    pw.println( "<h2>Servlets, Filtros encadenados</h2>");
    pw.println( "<p>La salida se observa en la consola.</p>");
    pw.println( "<table width=70% cellspacing=0 cellpadding=0 border=0>");
    pw.println( "<tr bgcolor=#0000ff>");
    pw.println( "<td align=center height=16><font face=Arial,Helvetica size=1");
    pw.println( "color=#FFFFFF>&copy; 2002, Agustin Froufe</font></td>");
    pw.println( "</tr></table>");
    pw.println( "</body></html>");
  }

  // Este método se invoca cuando el servlet es descargado por
  // parte del servidor.
  public void destroy() {
    ctx.log( "Destruyendo el servlet..." );
    }
  }

//------------------------------------------ Final del fichero java1808.java
