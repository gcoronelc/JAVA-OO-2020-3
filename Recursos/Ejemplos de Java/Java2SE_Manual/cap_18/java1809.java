//
//  java1809.java
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
//     Revision: 09-Feb-2002  14:07:42
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este eejemplo muestra el uso de los Eventos de Aplicación en el
 * servlet.
 * Cuando se inicializa la aplicación, se indicará el instante justo
 * en que se produce, ya que se invoca a la clase AplicacionEvt que
 * implementa la interfaz ServletContextListener, y es la que fija
 * el instante de inicio de la aplicación.
 * También se indica el número de sesiones activas establecidas con
 * el servidor, mediante la clase SesionEvt, que implementa la interfaz
 * HttpSessionListener, que es invocada cada vez que se crea o invalida
 * una sesión con el servidor.
 */

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class java1809 extends HttpServlet {
  private SesionEvt evSesion = new SesionEvt();
  private AplicacionEvt evAplicacion = new AplicacionEvt();
  // Formato de la fecha de la cabecera
  static final String FECHA = "EEE, d MMM yyyy hh:mm:ss z";
  SimpleDateFormat formatoFecha;

  public void init( ServletConfig conf ) throws ServletException {
    super.init( conf );
    // Ajustamos el formato para presentar la fecha
    formatoFecha = new SimpleDateFormat( FECHA );
    formatoFecha.setTimeZone( new SimpleTimeZone(0,"GMT") );
    }

  // Este es el método que trata la petición.
  public void doGet( HttpServletRequest req,HttpServletResponse resp )
    throws IOException,ServletException {

    resp.setContentType( "text/html" );
    PrintWriter pw = resp.getWriter();
    pw.println( "<html><head>" );
    pw.println( "<title>Tutorial de Java, Servlets</title>" );
    pw.println( "</head><body>" );
    pw.println( "<h2>Servlets, Eventos</h2>" );
    pw.println( "<p>Arranque aplicación:&nbsp;&nbsp;&nbsp;<b>" );
    pw.println(
      formatoFecha.format( new Date( evAplicacion.getInicializacion())) );
    pw.println( "</b><br>" );
    pw.println( "Sesiones activas:&nbsp;&nbsp;&nbsp;<b>" );
    pw.println( evSesion.getSesionesActivas() );
    pw.println( "</b></p>" );
    pw.println( "<table width=70% cellspacing=0 cellpadding=0 border=0>\n");
    pw.println( "<tr bgcolor=#0000ff><td align=center height=16>\n");
    pw.println( "<font face=Arial,Helvetica size=1\n");
    pw.println( "color=#FFFFFF>&copy; 2002, Agustin Froufe</font></td>\n");
    pw.println( "</tr></table>\n");
    pw.println( "</body></html>" );
  }

  // Este método se invoca cuando el servlet es descargado por
  // parte del servidor.
  public void destroy() {}
  }

//------------------------------------------ Final del fichero java1809.java
