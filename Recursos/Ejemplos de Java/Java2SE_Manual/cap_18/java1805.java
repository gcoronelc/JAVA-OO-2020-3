//
//  java1805.java
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
//     Creacion: 25-May-1999  06:06:12
//     Revision: 09-Feb-2002  14:14:59
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este servlet genera una imegen que descarga al navegador. El formato
 * que utiliza ex XPM, de forma que si el sistema dispone de visor
 * específico para este tipo MIME, se lanzará, presentándose en la
 * pantalla la imagen. Caso de Linux y Electric Eyes.
 * En caso de que no esté soprotado el tipo MIME x-pixmap, el navegador
 * solicitará la descarga del contendio de la imagen a un archivo.
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class java1805 extends HttpServlet {
  protected void doGet(HttpServletRequest req,
    HttpServletResponse res ) throws ServletException,IOException {

    // Se recupera el parámetro "color", que si no está fijado, nos obliga
    // a utilizar el color blanco
    String color = req.getParameter( "color" );
    if( color == null )
      color = "FFFFFF";

    // Fijamos el tipo de contenido a la imagen XPM
    res.setContentType( "image/x-pixmap" );
    // Indicamos que no es cacheable
    res.setHeader( "pragma","no-cache" );

    // Creamos el stream de respuesta para enviar la imagen
    ServletOutputStream out = res.getOutputStream();
    out.print( "/* XPM */\n"+
      "static char * x_xpm[] = {\n"+
      "\"17 13 2 1\",\n"+
      "\" \tc #000000\",\n"+
      "\"X\tc #"+color+"\",\n"+
      "\"XXXXX       XXXXX\",\n"+
      "\" XXXXX     XXXXX \",\n"+
      "\"  XXXXX   XXXXX  \",\n"+
      "\"   XXXXX XXXXX   \",\n"+
      "\"    XXXXXXXXX    \",\n"+
      "\"     XXXXXXX     \",\n"+
      "\"      XXXXX      \",\n"+
      "\"     XXXXXXX     \",\n"+
      "\"    XXXXXXXXX    \",\n"+
      "\"   XXXXX XXXXX   \",\n"+
      "\"  XXXXX   XXXXX  \",\n"+
      "\" XXXXX     XXXXX \",\n"+
      "\"XXXXX       XXXXX\"};\n");
    out.close();
    }

  // Devuelve la información de Copyright
  public String getServletInto() {
    return "java1805 Servlet, Tutorial de Java (C)A.Froufe";
    }
  }

//------------------------------------------ Final del fichero java1805.java