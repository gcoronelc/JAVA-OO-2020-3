//
//  java1904.java
//  Copyright (c) 1998,2002 Agustin Froufe
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
//     Creacion: 19-Dec-1998  21:05:32
//     Revision: 09-Feb-2002  19:30:21
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo presenta un interfaz para atacar a bases de datos
 * a través del driver JDBC-ODBC.
 * En los campos de texto que aparecen al ejecutarlo se debe
 * indicar la base de datos con la que se desea establecer conexión
 * y pulsar el botón. Una vez establecida la conexión, ya se puede
 * realizar cualquier consulta a las tablas contenidas en la
 * base de datos a través de sentencias SQL, escribiendo la
 * consulta que se desea en el campo al efecto y pulsando el botón
 * de ejecución de la consulta.
 * En el campo de texto inferior se van mostrando los resultados
 * correspondientes a cada una de las acciones que se van realizando
 * o a los mensajes de la aplicación.
 */
import java.net.URL;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class java1904 extends Frame implements MouseListener {
  // Se utiliza el itnerfaz MouseListener para poder capturar
  // los piques de ratón

  // Estos son los objetos que se van a utilizar en la aplicación
  Button botConexion = new Button( "  Conexión a la Base de Datos  " );
  Button botConsulta = new Button( " Ejecutar Consulta " );

  TextField txfConsulta = new TextField( 40 );
  TextArea txaSalida = new TextArea( 10,75 );
  TextField txfUsuario = new TextField( 40 );
  TextField txfClave = new TextField( 40 );
  TextField txfUrl = new TextField( 40 );
  String strUrl = "";
  String strUsuario = "";
  String strClave = "";
  // El objeto Connection es parte del API de JDBC, y debe ser lo
  // primero que se obtenga, ya que representa la conexión efectiva
  // con la Base de Datos
  Connection con;


  // Constructor de la clase, que es el que construye el interfaz
  // que se va a mostrar en la ventana
  public java1904() {
    // Se hacen todos los campos de texto editables para que se
    // puedan introducir datos, y no se permite que se escriba en
    // el área de texto que se va a utilizar como salida de los
    // resultados de las acciones del usuario y las respuestas que
    // se obtengan de la base de datos a las consultas que se
    // realicen
    txfConsulta.setEditable( true );
    txfUsuario.setEditable( true );
    txfUrl.setEditable( true );
    txaSalida.setEditable( false );

    // Se va a utilizar el GridBagLayout, que aunque complicado
    // en su uso, tiene la flexibilidad que se necesita en este
    // caso concreto
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints gbCon = new GridBagConstraints();
    // Lo fijamos como el layout a utilizar
    setLayout( gridbag );

    // Se fija el color y la fuente de caracteres a usar
    setFont( new Font( "Helvetica",Font.PLAIN,12 ) );
    setBackground( Color.orange );

    // No se han fijado los setConstraints para el Label, para que
    // se asuman los de defecto. El campo de texto txfUsuario es
    // el último componente en su fila, a través de gbCon, y
    // luego se añade al interfaz de usuario
    gbCon.weightx = 1.0;
    gbCon.weighty = 0.0;
    gbCon.anchor = GridBagConstraints.CENTER;
    gbCon.fill = GridBagConstraints.NONE;
    gbCon.gridwidth = GridBagConstraints.REMAINDER;
    add( new Label( "Usuario" ) );
    gridbag.setConstraints( txfUsuario,gbCon );
    add( txfUsuario );
    add( new Label( "Clave de Acceso" ) );
    gridbag.setConstraints( txfClave,gbCon );
    add( txfClave );
    add( new Label( "URL de la Base de Datos" ) );
    gridbag.setConstraints( txfUrl,gbCon );
    add( txfUrl );
    // Ahora viene la fila en que está el botón de Conexión a la
    // base de datos, fijamos los constraints para que eso sea así
    // y lo añadimos
    gridbag.setConstraints( botConexion,gbCon );
    add( botConexion );

    // Ahora registramos el botón para que reciba los eventos del
    // raton a través del interfaz MouseListener
    botConexion.addMouseListener( this );

    // Ahora viene la zona que permite introducir el texto de la
    // consulta que se quiere realizar y el botón que va a permitir
    // su envío al driver JDBC
    add( new Label( "Consulta SQL" ) );
    gridbag.setConstraints( txfConsulta,gbCon );
    add( txfConsulta );
    gridbag.setConstraints( botConsulta,gbCon );
    add( botConsulta );
    botConsulta.addMouseListener( this );

    // Ahora se coloca una etiqueta en su propia línea para rotular
    // el área de texto en la que se van a presentar los resultados
    // de las consultas que se realicen
    Label labResultado = new Label( "Resultado" );
    labResultado.setFont( new Font( "Helvetica",Font.PLAIN,16 ) );
    labResultado.setForeground( Color.blue );
    gridbag.setConstraints( labResultado,gbCon );
    gbCon.weighty = 1.0;
    add( labResultado );

    // Ahora se cambia la forma de extensión de la ventana, para que
    // si se agranda la ventana tenga la mayor parte de espacio
    // posible en la zona de texto en donde se presentan los
    // resultados
    gridbag.setConstraints( txaSalida,gbCon );
    txaSalida.setForeground( Color.white );
    txaSalida.setBackground( Color.black );
    add( txaSalida );
    }


  public void mouseClicked( MouseEvent evt ) {
    // Cuando se pulsa el botón Consulta, se recoge el contenido del
    // campo de texto txfConsulta y se le pasa al método Select, que
    // es el que va a realizar la consulta y devolver el resultado
    // que se va a presentar en la zona de salida
    if( evt.getComponent() == botConsulta ) {
      System.out.println( txfConsulta.getText() );
      txaSalida.setText( Select( txfConsulta.getText() ) );
      }

    // Si se pulsa el botón de Conexión, se intenta establecer la
    // conexión con la base de datos indicada en el campo de texto
    // correspondiente a URL, con el usuario y clave que se hayan
    // indicado en los campos correspondientes
    if( evt.getComponent() == botConexion ) {
      // Se fijan las variables globales de usaurio, clave y url a
      // los valores que se hayan introducido en los campos
      strUsuario = txfUsuario.getText();
      strClave = txfClave.getText();
      strUrl = txfUrl.getText();

      // La creación de la conexión con la base de datos lanza una
      // excepción en caso de que haya problemas al establecer esa
      // conexión con los aprámetros que se le indiquen, por ello
      // es imprescindible colocar el método getConnection en un
      // bloque try-catch. Si se produce algún problema y se lanza
      // la excepción, aparecerá reflejada en la consola y en el
      // área que se ha destinado en la ventana a ver los resultados
      try {
        // Ahora se intenta crear una nueva instancia del driver que se
        // va a utilizar. Hay varias formas de especificar el driver que
        // se quiere, e incluso se puede dejar que sea el propio
        // DriverManager de JDBC que seleccione el que considere más
        // adecuado para conectarse a una fuente de datos determinada
        Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
        // La conexión aquí se realiza indicando la URL de la base de
        // datos y el usuario y clave que dan acceso a ella
        con = DriverManager.getConnection( strUrl,strUsuario,strClave );
        // Si la conexión ha sido satisfactoria, cambiamos el rótulo
        // del botón de conexión, para que indique que si se pulsa lo
        // que se realiza será la "Reconexión"
        botConexion.setLabel( "Reconexión a la Base de Datos" );
        txaSalida.setText( "Conexion establecida con "+strUrl );
      } catch( Exception e ) {
        // Se presenta la información correspondiente al error, tanto
        // en la consola como en la zona de salida de la ventana
        e.printStackTrace();
        txaSalida.setText( e.getMessage() );
        }
      }
    }


  // Se implementan vacíos el resto de los métodos del interfaz de
  // eventos del ratón, MouseListener. Si se quiere evitar, también es
  // posible utilizar MouseAdapter, que tiene implementados, pero sin
  // acciones asignadas, todos estos métodos
  public void mouseEntered( MouseEvent evt ) {}
  public void mouseExited( MouseEvent evt ) {}
  public void mousePressed( MouseEvent evt ) {}
  public void mouseReleased( MouseEvent evt ) {}


  // Este es el método que realiza la consulta
  public String Select( String consulta ) {
    String resultado="";
    int cols;
    int pos;

    // Hay varios métos que se van a emplear y que lanzan excepciones
    // en caso de que haya algún problema con la consulta, o si se
    // rompe la conexión, etc
    try {
      // En primer lugar, se instancia la clase Statement, que es
      // necesaria para ejecutar la consulta. La clase Connection
      // devuelve un objeto Statement que se enlaza a la conexión
      // abierta para pasar de nuevo el objeto Statement. Así es
      // como la instancia "sentencia" se enlaza a la conexión actual
      // con la base de datos
      Statement sentencia = con.createStatement();

      // El objeto resultSet también es enlazado con la conexión a la
      // base de datos a través de la clase Statement, que contiene el
      // método executeQuery, que devuelve un objeto ResultSet.
      ResultSet rs = sentencia.executeQuery( consulta );

      // Ahora se utiliza el método getMetaData en el resultado para
      // devolver un objeto MetaData, que contiene el método getColumnCount
      // usado para determinar cuántas columnas de datos están presentes
      // en el resultado.
      cols = ( rs.getMetaData() ).getColumnCount();

      // Aquí se utiliza el método next de la instancia "rs" de
      // ResultSet para recorrer todas las filas, una a una. Hay formas
      // más optimizadas de hacer esto, utilizando la característica
      // inputStream del driver JDBC
      while( rs.next() ) {
        // Se recorre ahora cada una de las columnas de la fila, es
        // decir, cada celda, una a una
        for( pos=1; pos <= cols; pos++ ) {
          // Este es el método general para obetener un resultado. el
          // método getString intentará moldear el resultado a un String.
          // En este caso solamente se recoge el resultado y se le añade
          // un espacio y todo se añade a la variable "resultado"
          resultado += rs.getString( pos )+" ";
          }

        // Para cada fila que se revise, se le añade un retorno de
        // carro, para que la siguiente fila empiece en otra línea
        resultado += "\n";
        }

      // Se cierra la "sentencia". En realidad se cierran todos los
      // canales abiertos para la consulta pero la conexión con la
      // base de datos permanece
      sentencia.close();
    } catch( Exception e ) {
      e.printStackTrace();
      resultado = e.getMessage();
      }

    // Antes de salir, se devuelve el resultado obtenido
    return( resultado );
    }


  public static void main( String args[] ) {
    java1904 ventana = new java1904();

    // Se recoge el evento de cierre de la ventana
    ventana.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
        }
      } );

    ventana.setSize( 450,300 );
    ventana.setTitle( "Tutorial de Java, JDBC" );
    ventana.pack();
    ventana.setVisible( true );
    }
  }

//------------------------------------------ Final del fichero java1904.java