<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- 
  Esta es la página JSP que permite el control del contenido del
  fichero favoritos.txt, en base a las acciones sobre el servlet
  FavoritosServlet.
  Presenta la lista de favoritos para el usuario que corresponda,
  permitiéndole acceder a cualquiera de ellos, eliminar alguno de
  los existentes y añadir cualquier favorito nuevo.
--%>
<html>
<head>
<title>Tutorial de Java, Servlets</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="generator"    content="htm4l version 2.00">
<meta name="autor"        content="Agustin Froufe">
<meta name="copyright"    content="(c) 2002">
<meta name="creacion"     content="12-Ene-2002 10:19:12">
<meta name="modificacion" content="12-Ene-2002 10:19:12">
</head>

<%--  
  Importamos las clases necesarias e indicamos al servidor que
  debe mantener la sesión.
--%>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ page import="FavoritosDB" %>
<%@ page session="true" %>
<%! private FavoritosDB nombre; %>
<%! private static final String servletUrl = 
   "http://localhost:8080/favoritos/FavoritosServlet"; %>
<%
// Recuperamos el identificador del usuario en esta sesión
nombre = (FavoritosDB)session.getValue("usuario");
%>
<body>

<%-- Se utiliza una expresión JSP para saludar al usuario --%>
<h1 align="center">Bienvenido, <%= nombre.usuario %></h1>
<table width="100%">
  <tr>
    <td>
    <%-- Codificamos la acción de salir de la aplicación --%>
    <b><a href="<%=response.encodeURL(servletUrl+"?accion=salir")%>">
      SALIR</a></b></td>
    <td><b>Favorito</b></td>
    <td><b>URL Favorito</b></td>
  </tr>
  <tr><td colspan="3"><hr></td>
  </tr>

<%
// Imprimimos cada uno de los enlaces en una línea separada
for( Enumeration enum=nombre.favoritos.keys(); 
  enum.hasMoreElements(); ) {

  String favNombre = (String)enum.nextElement();
  String favUrl = (String)nombre.favoritos.get(favNombre);

  // Codificamos la URL para el seguimiento de la sesión mediante
  // el método response.encodeURL()
  // La URL codifica la información pasada al servidor mediante
  // el método URLEncoder.encode()
  // Esto es necesario hacerlo, porque el parámetro que se pase puede
  // tener espacios o caracteres que no puedan ser entendibles por
  // el servidor como una URL
  String eliminarUrl = response.encodeURL(servletUrl + 
    "?accion=eliminar&favorito=" + URLEncoder.encode(favNombre));
%>
<tr>
  <td><a href="<%= eliminarUrl %>">Eliminar</a></td>
  <td><a href="<%= favUrl %>" target="_blank"><%= favNombre %></a></td>
  <td><a href="<%= favUrl %>" target="_blank"><%= favUrl %></a></td>
</tr>
<%
}
%>
</table>
<hr>

<%-- Opción de añadir un nuevo enlace favorito --%>
<form action="<%= response.encodeURL(servletUrl) %>" method="post">
<input type="hidden" name="accion" value="nueva">
Nuevo Enlace<input type="text" size="30" name="nombre"><br>
&nbsp;&nbsp;URL Enlace <input type="text" size="30" name="favorito">
<input type="submit" name="nuevoFavorito" value=" Añadir ">
</form>

</body>
</html>
