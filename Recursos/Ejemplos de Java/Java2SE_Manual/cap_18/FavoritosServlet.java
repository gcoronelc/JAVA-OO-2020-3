//
//  java1526.java
//  Copyright (c) 2002, Agustin Froufe
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
//     Creacion: 12-Ene-2002  11:46:30
//     Revision: 09-Feb-2002  14:12:28
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class FavoritosServlet extends HttpServlet{
  // Definimos las constantes para facilitar la actualizaci�n del
  // servlet en caso de querer cambiar los directorios
  private static final String pathHtml="/favoritos/";
  private static final String pathJsp="/";

  // El m�todo init()  registra el servlet con su padre, para luego
  // inicializar la base de datos de los usuarios, que en este caso
  // equivale al uso del fichero favoritos.txt
  public void init( ServletConfig conf ) throws ServletException {
    super.init( conf );

    ServletContext sc = conf.getServletContext();
    UsuariosDB.abrirBD( sc.getRealPath("favoritos.txt") );
    }

  // Si se realiza una petici�n GET, pasamos el contenido a POST,
  // que es la que verdaderamente tratamos
  public void doGet( HttpServletRequest req,
    HttpServletResponse resp ) {
    this.doPost( req,resp );
    }

  // Este es el m�todo que trata la petici�n. Primero fija la sesi�n
  // y luego realiza las acciones correspondientes a la acci�n
  // solicitada por el usuario
  public void doPost( HttpServletRequest req,
    HttpServletResponse resp ) {

    // Recogemos la sesi�n asociada al cliente. Si no existe ninguna
    // se le asigna una nueva, por ello pasamos el par�metro TRUE
    HttpSession sesion = req.getSession( true );
    // Determinamos la acci�n que se va a realizar
    String accion = req.getParameter( "accion" );

    try {
      // Si la entrada no la entendemos, redirigmos al usuario a la
      // p�gina de error
      if( accion == null ) {
        resp.sendRedirect( pathHtml+"registrar.html" );
        return;
        }

      // Ahora controlamos por medio de sentencias if-else, la
      // acci�n que ha seleccionado el usuario y solicitado al servlet.
      // El m�todo getParameter() del objeto correspondiente a la
      // petici�n es el que se utiliza para determinar los valores de
      // entrada. En cada una de las sentencias se chequea si estamos
      // ante una nueva sesi�n (ser�a la primera petici�n que hiciese
      // el usuario) utilizando el m�todo isNew() sobre la sesi�n.
      if( accion.equals("login") ) {
        // Si el usuario est� entrando, recogemos los datos que
        // corresponden a su identificaci�n
        FavoritosDB nombre = UsuariosDB.getUsuario(
          req.getParameter("usuario"),
          req.getParameter("password") );


        // Si los datos introducidos son correctos, guardamos la
        // informaci�n del usuario en el objeto Sesi�n utilizando el
        // m�todo setAttribute() y pasamos el control a la p�gina
        // JSP que permitir� la interacci�n al usuario
        if( nombre != null ) {
          sesion.setAttribute( "usuario",nombre );
          passItOn( req,resp,pathJsp+"Favoritos.jsp" );
          }
        // Si no es correcta la informaci�n, mandamos al usuario a la
        // p�gina de error para que la corrija
      else {
        resp.sendRedirect( pathHtml+"registrar.html" );
        }
      }
    else if( accion.equals("nueva") ) {
      // Comprobamos si estamos ante la primera petici�n, porque si es
      // as� reenviamos al usuario al login, indicando que no se puede
      // entrar directamente en esta p�gina
      if( sesion.isNew() ) {
        resp.sendRedirect( pathHtml+"login.html" );
        }
      else {
        // Recogemos la informaci�n del usuario del objeto Sesi�n, le
        // a�adimos el enlace favorito, volvemos a guardar la nueva
        // informaci�n en el objeto Sesi�n y pasamos la petici�n a la
        // p�gina JSP
        FavoritosDB nombre = (FavoritosDB)sesion.getAttribute( "usuario" );
        nombre.nuevoFavorito( req.getParameter("nombre"),
          req.getParameter("favorito") );
        sesion.setAttribute( "usuario",nombre );
        passItOn( req,resp,pathJsp+"Favoritos.jsp" );
        }
      }
    else if( accion.equals("eliminar") ) {
      // Comprobamos si estamos ante la primera petici�n, porque si es
      // as� reenviamos al usuario al login, indicando que no se puede
      // entrar directamente en esta p�gina
      if( sesion.isNew() ) {
        resp.sendRedirect( pathHtml+"login.html" );
        }
      else {
        // En este caso recuperamos la informaci�n del usuario del
        // objeto Sesi�n, eliminamos el enlace, volvemos a guardar los
        // datos en el objeto Sesi�n y pasamos el control a la p�gina
        // JSP que controla las acciones del usuario
        FavoritosDB nombre = (FavoritosDB)sesion.getAttribute( "usuario" );
        nombre.eliminaFavorito( req.getParameter("favorito") );
        sesion.setAttribute( "usuario",nombre );
        passItOn( req,resp,pathJsp+"Favoritos.jsp" );
        }
      }
    else if( accion.equals("registrar") ) {
      // Insertamos los datos del usuario en la base de datos
      UsuariosDB.nuevoUsuario( req.getParameter("usuario"),
        req.getParameter("password") );
      FavoritosDB nombre = UsuariosDB.getUsuario(
        req.getParameter("usuario"),
        req.getParameter("password") );

      // Ahora guardamos esa misma informaci�n en el objeto Sesi�n
      // utilizando el m�todo setAttribute(), pasando el control a
      // la p�gina JSP
      if( nombre != null ) {
        sesion.setAttribute( "usuario",nombre );
        passItOn( req,resp,pathJsp+"Favoritos.jsp" );
        UsuariosDB.actualizaBD();
        }
      else {
        resp.sendRedirect( pathHtml+"error.html" );
        }
      }
    else if( accion.equals("salir") ) {
      // Guardamos los datos del usuario, por si acaso
      UsuariosDB.actualizaBD();
      // Conclu�mos la sesi�n asignada al usuario
      sesion.invalidate();
      // Presentamos la p�gina de salida
      resp.sendRedirect( pathHtml+"salir.html" );
      }
  } catch( Exception e ) {
    e.printStackTrace();
    }
  }

  // Este es el m�todo que utilizamos para redirigir la peticiones
  // realizadas al servlet a otros recursos
  private void passItOn( HttpServletRequest req,
    HttpServletResponse resp,String pagina )
    throws ServletException,IOException {
    RequestDispatcher disp =
      getServletContext().getRequestDispatcher( pagina );
    disp.forward( req,resp );
    }

  // Este m�todo se invoca cuando el servlet es descargado por
  // parte del servidor. En este caso, lo �nico que hacemos es
  // cerrar la base de datos
  public void destroy() {
    UsuariosDB.cerrarBD();
    }
  }

//---------------------------------- Final del fichero FavoritosServlet.java
