//
//  Filtro1.java
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
//     Creacion: 27-Ene-2002  11:34:23
//     Revision: 09-Feb-2002  14:23:41
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.io.*;
import javax.servlet.*;

public class Filtro1 implements Filter {
  private ServletContext ctx;

  public void init( FilterConfig fc ) {
    ctx = fc.getServletContext();
    ctx.log( "Creando el Filtro 1...");
    }

  public void doFilter( ServletRequest request,
    ServletResponse response,FilterChain chain ) {

    try {
      System.out.println ("Dentro del Primer filtro... ");
      System.out.println( "  Filtrando la peticion..." );

      chain.doFilter( request,response );

      System.out.println ("Dentro del Primer filtro... ");
      System.out.println( "  Filtrando la respuesta..." );
    } catch( IOException ei ) {
      System.out.println( "IOException en el Filtro 1" );
      ei.printStackTrace();
    } catch( ServletException es ) {
      System.out.println( "ServletException en el Filtro 1" );
      es.printStackTrace();
      }
    }

  public void destroy() {
    ctx.log( "Destruyendo el Filtro 1...");
    }
  }

//------------------------------------------- Final del fichero Filtro1.java
