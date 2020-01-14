//
//  HolaMundoServlet.java
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
//     Creacion: 21-May-1999  05:28:10
//     Revision: 09-Feb-2002  14:03:53
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HolaMundoServlet extends HttpServlet {

  protected void doGet(
    HttpServletRequest req,HttpServletResponse res )
    throws ServletException,IOException {

    res.setContentType( "text/html" );
    PrintWriter out = res.getWriter();
    out.println(
      "<HTML><HEAD><TITLE>Hola Mundo!</TITLE>" +
      "</HEAD><BODY>Hola Mundo!</BODY></HTML>" );
    out.close();
    }

  public String getServletInfo() {
    return "HolaMundoServlet, Tutorial de Java (C)A.Froufe";
    }
  }

//---------------------------------- Final del fichero HolaMundoServlet.java