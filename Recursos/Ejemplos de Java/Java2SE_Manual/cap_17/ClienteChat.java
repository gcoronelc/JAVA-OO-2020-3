//
//  ClienteChat.java
//  Copyright (c) 2001,2002 Agustin Froufe
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
//     Creacion: 07-Dic-2001  18:14:28
//     Revision: 09-Feb-2002  13:54:43
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Clase que implementa al cliente gráfico del Chat que permite la
 * conexión al Servidor.
 * Presenta una ventana gráfica que tanto se puede utilizar como
 * aplicación o en una página web en forma de applet. En este caso
 * el lector puede utilizar la página "ClienteChat.html", asegurándose
 * de que las clases se encuentran en el directorio correcto para que
 * el navegador, o el appletviewer las encuentren.
 */
import java.io.*;
import java.net.*;
import java.applet.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class ClienteChat extends Applet {
  // Puerto por defecto de conexión al Servidor
  public static final int PUERTO_DEFECTO = 5000;
  // Por defecto el servidor corre en el propio ordenador
  public static final String HOST_DEFECTO = "localhost";
  // Número máximo de caracteres en un mismo mensaje, para establecer
  // un límite medio lógico
  public static final int NUM_MAX_CARS = 1000;

  public GridBagLayout miLayout = new GridBagLayout();
  public GridBagConstraints miConstraints = new GridBagConstraints();
  static final Font fuenteChica = new Font("Helvetica", Font.PLAIN, 12);
  static final Font fuenteGrande = new Font("Helvetica", Font.PLAIN, 14);

  protected Recibir receptor = null;
  protected Enviar remitente = null;
  // Puerto del servidor al que nos conectamos
  protected int puerto = PUERTO_DEFECTO;
  // Servidor al que nos conectamos
  protected String host = HOST_DEFECTO;
  // Número másximo de caracteres a enviar en un único mesnaje
  protected int maxChars = NUM_MAX_CARS;
  // Socket de comunicación
  protected Socket s = null;
  // Lista de usuarios para enviar mensajes privados
  protected volatile Set listaClientes = null;
  // Copia auxiliar para actualizar la lista de usuarios anterior
  protected volatile Set listaAux = null;
  // Indice a la lista de usuarios anteriro
  protected int[] idxClientes = null;
  // Nombre del usuario en la pantalla
  protected String nombreCliente = null;
  // Usuarios seleccionados de la lista para envío privado
  protected String[] clientes = null;
  // Mensaje a enviar a la charla
  protected String mensaje = null;
  // Indica si el servidor ha aceptado el login o no
  protected boolean loginOk = false;
  // Indica si estamos corriendo en un appleto o no
  protected boolean esApplet = true;

  // Izquierda
  protected Label labCharla;
  protected Label labTexto;
  protected TextField txtMensaje;
  protected TextArea txtCharla;

  // Centro
  protected Label labUsuario;
  protected TextField txtUsuario;
  protected Label labParticipantes;
  // Lista de usuarios participantes en la charla
  protected volatile java.awt.List lstParticipantes = null;
  protected Button botEnviar;

  // Derecha
  protected Button botEntrar;
  protected Button botSalir;


  // Clase que controla las acciones correspondientes a los tres
  // botones de que dispone la interfaz gráfica del cliente
  protected class ButtonAction implements ActionListener {
    public void actionPerformed( ActionEvent event ) {
      Button boton = (Button)event.getSource();
      String txBoton = boton.getLabel();

      if( "Entrar".equals(txBoton) ) {
        nombreCliente = txtUsuario.getText();
        remitente.login();
        }
      else if( "Enviar".equals(txBoton) ) {
        compruebaEnvio();
        }
      else if( "Salir".equals(txBoton) ) {
        remitente.logoff();
        }
      }
    }


  // Clase que controla la entrada de texto en el campo destinado
  // a la introducción del mensaje que se quiere enviar a la
  // charla
  protected class MiTextListener implements TextListener {
    public void textValueChanged( TextEvent event ) {
      TextComponent tc = (TextComponent)event.getSource();
      mensaje = tc.getText();
      }
   }


  // Clase que controla la pulsación de la tecla Enter en el campo
  // de texto, para que realice la misma acción  que cuando se
  // pulsa cobre el botón "Enviar"
  protected class MiKeyListener extends KeyAdapter {
    public void keyReleased( KeyEvent e ) {
      // Cuando se pulse la tecla se comprueba si toda la información
      // necesaria para enviar el mensaje está lista
      if( e.getKeyCode() == KeyEvent.VK_ENTER ) {
        compruebaEnvio();
        }
      }
    }


  // Clase encargada del control de los usuarios que integran
  // la lista de participantes en la Charla
  protected class ListaUsuarios implements ItemListener {
    public void itemStateChanged( ItemEvent evt ) {
      java.awt.List listaVentana = (java.awt.List)evt.getSource();

      if( evt.getStateChange() == ItemEvent.SELECTED ) {
        clientes = listaVentana.getSelectedItems();
        idxClientes = listaVentana.getSelectedIndexes();

        // Actualiza la lista de usuarios para el envío
        actualizaClientes( clientes,false );
        }

      if( evt.getStateChange() == ItemEvent.DESELECTED ) {
        if( !listaClientes.isEmpty() ) {
          listaClientes.clear();
          }

        for( int i=0; i < idxClientes.length; i++ ) {
          if( listaVentana.isIndexSelected(i) ) {
            listaVentana.deselect( i );
            listaClientes.remove( listaVentana.getItem(idxClientes[i]) );
            }
          }
        clientes = listaVentana.getSelectedItems();
        }
      }
    }


  // Este constructor es el invocado cuando se lanza el cliente
  // como una aplicación.
  // Fija los valores por defecto de los diferentes parámetros
  // si no se les han asignado valores en la llamada desde la
  // línea de comandos
  public ClienteChat( String[] args ) {
    this();
    esApplet = false;

    switch( args.length ) {
      // Número máximo de caracteres que se permiten en un mismo
      // mensaje
      case 3:
        try {
          maxChars = Integer.parseInt( args[2] );
        } catch( NumberFormatException e ) {
          maxChars = NUM_MAX_CARS;
          }
        System.out.println( "Caracteres Máx: "+maxChars );
      // Puerto de conexión al servidor, en el cual está esperando las
      // conexiones de clientes
      case 2:
        try {
          puerto = Integer.parseInt( args[1] );
        } catch( NumberFormatException e ) {
          puerto = PUERTO_DEFECTO;
          }
        System.out.println( "Puerto de conexión: "+puerto );
      // Host en el cual se encuentra en ejecución el servidor de la
      // charla
      case 1:
        host = args[0];
        if( host == null ) {
          host = HOST_DEFECTO;
          }
        System.out.println( "Host de conexión: "+host );
      case 0:
        break;
      default:
        break;
      }
    // crea el socket, ventanas, tareas
    arrancar();
    }

  // Este es el constructor que se encarga de crear la interfaz
  // gráfica que aparece en la vetnana o en el navegador, dependiendo
  // de cómo se haya lanzado el cliente
  public ClienteChat () {
    miConstraints.fill = miConstraints.BOTH;
    setLayout( miLayout );
    setBackground( Color.lightGray );
    miConstraints.insets = new Insets( 0,5,0,5 );
    ButtonAction accionBoton = new ButtonAction();

    // Zona de presentación de los mensajes que se intercambian entre
    // los usuarios de la charla
    labCharla = new Label( "Charla:" );
    labCharla.setFont( fuenteChica );
    miConstraints.gridx = 0; miConstraints.gridy = 0;
    miConstraints.gridheight = 1; miConstraints.gridwidth = 1;
    miConstraints.anchor = miConstraints.WEST;
    miLayout.setConstraints( labCharla,miConstraints );
    add( labCharla );

    txtCharla = new TextArea( "",10,50,TextArea.SCROLLBARS_VERTICAL_ONLY );
    txtCharla.setFont( fuenteChica );
    txtCharla.setEditable( false );
    miConstraints.gridx = 0; miConstraints.gridy = 1;
    miConstraints.gridheight = 7; miConstraints.gridwidth = 3;
    miLayout.setConstraints( txtCharla, miConstraints );
    add( txtCharla );

    // Zona para introducir el texto del mensaje que se quiere enviar
    // a la charla en general o a un grupo de usuarios específico
    labTexto = new Label( "Mensaje:" );
    labTexto.setFont( fuenteChica );
    miConstraints.gridx = 0; miConstraints.gridy = 8;
    miConstraints.gridheight = 1; miConstraints.gridwidth = 1;
    miConstraints.anchor = miConstraints.WEST;
    miLayout.setConstraints( labTexto,miConstraints );
    add( labTexto );

    txtMensaje = new TextField( 20 );
    txtMensaje.setFont( fuenteGrande );
    txtMensaje.addTextListener( new MiTextListener() );
    txtMensaje.addKeyListener( new MiKeyListener() );
    miConstraints.gridx = 0; miConstraints.gridy = 9;
    miConstraints.gridheight = 1; miConstraints.gridwidth = 3;
    miLayout.setConstraints( txtMensaje,miConstraints );
    add( txtMensaje );

    botEnviar = new Button( "Enviar" );
    botEnviar.addActionListener( accionBoton );
    miConstraints.gridx = 3; miConstraints.gridy = 9;
    miConstraints.gridheight = 1; miConstraints.gridwidth = 1;
    miConstraints.anchor = miConstraints.WEST;
    miConstraints.insets.right = 5; miConstraints.insets.left = 0;
    miLayout.setConstraints( botEnviar,miConstraints );
    add( botEnviar );

    // Zona para introducir en principio, y visualizar después, el
    // identificador del usuario en la charla
    labUsuario = new Label( "Usuario:" );
    labUsuario.setFont( fuenteChica );
    miConstraints.gridx = 3; miConstraints.gridy = 0;
    miConstraints.gridheight = 1; miConstraints.gridwidth = 1;
    miConstraints.anchor = miConstraints.WEST;
    miConstraints.insets.right = 5; miConstraints.insets.left = 0;
    miLayout.setConstraints( labUsuario,miConstraints );
    add( labUsuario );

    txtUsuario = new TextField( 10 );
    txtUsuario.setFont( fuenteGrande );
    miConstraints.gridx = 3; miConstraints.gridy = 1;
    miConstraints.gridheight = 1; miConstraints.gridwidth = 1;
    miConstraints.anchor = miConstraints.WEST;
    miConstraints.insets.right = 5; miConstraints.insets.left = 0;
    miLayout.setConstraints( txtUsuario,miConstraints );
    add( txtUsuario );

    // Zona que presenta la lista de participantes en la charla
    labParticipantes = new Label( "Participantes:" );
    labParticipantes.setFont( fuenteChica );
    miConstraints.gridx = 3; miConstraints.gridy = 3;
    miConstraints.gridheight = 1; miConstraints.gridwidth = 1;
    miConstraints.anchor = miConstraints.WEST;
    miConstraints.insets.right = 5; miConstraints.insets.left = 0;
    miLayout.setConstraints( labParticipantes,miConstraints );
    add( labParticipantes );

    lstParticipantes = new java.awt.List( 5,true );
    lstParticipantes.setFont( fuenteChica );
    lstParticipantes.addItemListener( new ListaUsuarios() );
    miConstraints.gridx = 3; miConstraints.gridy = 4;
    miConstraints.gridheight = 3; miConstraints.gridwidth = 1;
    miConstraints.anchor = miConstraints.WEST;
    miConstraints.insets.right = 5; miConstraints.insets.left = 0;
    miLayout.setConstraints( lstParticipantes,miConstraints );
    add( lstParticipantes );

    // Zona de control general del Cliente, que dispone de los botones
    // de entrada y salida de la charla
    botEntrar = new Button( "Entrar" );
    botEntrar.addActionListener( accionBoton );
    miConstraints.gridx = 4; miConstraints.gridy = 0;
    miConstraints.gridheight = 1; miConstraints.gridwidth = 1;
    miConstraints.anchor = miConstraints.WEST;
    miConstraints.insets.right = 5; miConstraints.insets.left = 0;
    miLayout.setConstraints( botEntrar,miConstraints );
    add( botEntrar );

    botSalir = new Button( "Salir" );
    botSalir.addActionListener( accionBoton );
    miConstraints.gridx = 4; miConstraints.gridy = 1;
    miConstraints.gridheight = 1; miConstraints.gridwidth = 1;
    miConstraints.anchor = miConstraints.WEST;
    miConstraints.insets.right = 5; miConstraints.insets.left = 0;
    miLayout.setConstraints( botSalir,miConstraints );
    add( botSalir );

    // Colección que mantiene la lista de participantes en la charla
    // a los cuales se quiere enviar un mensaje de forma específica,
    // independientemente de la lista general
    listaClientes = new HashSet();
    }

  // Este método es el que se invoca cuando el Cliente es lanzado
  // desde un navegador.
  // Se encarga de recoger el valor de los argumentos que se pasan
  // a través de la invocación del applet y asignarlos a los
  // parámetros de la aplicaicón
  public void init() {
    // Puerto de conexión al servidor, en el cual está esperando las
    // conexiones de clientes
    try {
      puerto = Integer.parseInt( getParameter("puerto") );
    } catch( NumberFormatException e ) {
      puerto = PUERTO_DEFECTO;
      }
    // Host en el cual se encuentra en ejecución el servidor de la
    // charla
    host = getParameter( "host" );
    if( host == null ) {
      host = HOST_DEFECTO;
      }
    // Número máximo de caracteres que se permiten en un mismo mensaje
    try {
      maxChars = Integer.parseInt(getParameter("maxChars"));
    } catch( NumberFormatException e ) {
      maxChars = NUM_MAX_CARS;
      }

    System.out.println( "host = "+host +
      "\npuerto = "+puerto +
      "\nmaxChars = "+ maxChars );

    // Crea el socket, ventanas y tareas
    arrancar();
    }

  // Este es el método que crea el socket con el servidor, las ventanas
  // y lanza las tareas que se encargan de recibir y enviar los mensajes
  // de y a la charla
  public void arrancar() {
    // Creamos el socket de conexión con el servidor
    try {
      s = new Socket( host,puerto );
      System.err.println("Conectado al Servidor "+ s.getInetAddress() + ":"+ s.getPort() );
    } catch( IOException e ) {
      System.err.println( "No se puede crear el Socket: "+e );
      this.eliminarTareas();
      return;
      }
    // Tareas de envío y recepción de mensajes
    remitente = new Enviar( s,txtMensaje,txtUsuario,this );
    receptor = new Recibir( s,txtCharla,this );
    }

  // Método invocado por la tarea Enviar para enviar un mensaje a todos
  // los clientes de la charla
  public Set getClientes() {
    return( listaClientes );
    }

  // Método invocado por la tarea Enviar para recuperar la lista de
  // usuarios (si la hay) a los cuales se debe enviar un mensaje, en
  // caso de que no se deba enviar a todo el mundo
  public String[] getArrayClientes(){
    String[] listaLocal = clientes;
    return( listaLocal );
    }

  // Método invocado por la tarea Enviar para conocer el nombre del
  // usuario que está controlando a este cliente
  public String getNombreCliente(){
    return( nombreCliente );
    }

  // Método invocado por la tarea Recibir cuando recibe del servidor
  // un mensaje de actualización de la lista de usuarios de la charla.
  // Se actualiza la lista de clientes que se presenta en la ventana.
  public void setClientes( Set nuevosUsuarios ) {
    String[] arrayUsuarios = new String[nuevosUsuarios.size()];
    int c = 0;

    for( Iterator i=nuevosUsuarios.iterator(); i.hasNext(); ) {
      arrayUsuarios[c] = (String)i.next();
      c++;
      }
    actualizaClientes( nuevosUsuarios );
    }

  // Una vez que se recibe la confirmación de Login del servidor,
  // hacemos que el campo de texto en que se ha introducido el
  // identificador no pueda ser editado, y quede solamente como
  // información
  protected void setLogin( boolean loginOk ) {
    System.out.println( "Incorporación aceptada..." );
    this.loginOk = loginOk;
    // Hacemos el campo NO editable
    if( loginOk )
      txtUsuario.setEditable( false );
    }

  // Comprobamos si hay algo que enviar, presentando una ventana
  // de aviso en caso de que el mensaje esté vacío
  protected void compruebaEnvio() {
    // Si no hay mensaje presentamos la ventana de aviso
    if( mensaje == null || "".equals(mensaje) ) {
      JOptionPane.showMessageDialog( new Frame(),
        "NO HAY TEXTO QUE ENVIAR","Advertencia",
        JOptionPane.WARNING_MESSAGE );
      }
    else {
      // Invocamos a la tarea Enviar para que envíe el mensaje a la
      // charla
      if( remitente != null ) {
        remitente.enviaMensaje();
        }
      }
    }

  // Refresca la lista de Clientes/Usuarios internamente
  protected void actualizaClientes( String[] arrayUsuarios,
    boolean referescar ) {
    if( referescar ) {
      listaClientes.clear();
      lstParticipantes.removeAll();
      // Lo que hacemos es regenerarla por completo
      for( int i=0; i < arrayUsuarios.length; i++ )
        lstParticipantes.add( (String)arrayUsuarios[i] );
      }
    else {
      listaClientes.clear();
      for( int i=0; i < arrayUsuarios.length; i++ )
        listaClientes.add( (String)arrayUsuarios[i] );
      }
    listaClientes.add( nombreCliente );
    repaint();
    }

  // Actualiza la lista de Clientes/Usuarios con el conjunto de
  // nuevos nombres que lleguen del servidor
  protected void actualizaClientes( Set nuevosUsuarios ) {
    String[] listaActual = lstParticipantes.getItems();

    for( int i=0; i < listaActual.length; i++ ) {
      if( !nuevosUsuarios.contains(listaActual[i]) )
        lstParticipantes.remove( listaActual[i] );
      }

    listaActual = lstParticipantes.getItems();
    for( Iterator i=nuevosUsuarios.iterator(); i.hasNext(); ) {
      String nuevoCliente = (String)i.next();
      boolean noEncontrado = true;

      for( int j=0; j < listaActual.length; j++ ) {
        if( listaActual[j].equals(nuevoCliente) ) {
          noEncontrado = false;
          break;
          }
        }

      if( noEncontrado )
        lstParticipantes.add( nuevoCliente );
      }
    repaint();
    }

  // Detiene la tarea propia y todas las demás
  public void stop() {
    eliminarTareas();
    }

  // Método que se encarga de eliminar las tareas, cerrar el socket
  // con el servidor y concluir la aplicación
  public void eliminarTareas() {
    // Paramos y eliminamos la tarea de envío
    if( remitente != null && remitente.enEjecucion ) {
      System.err.println( "Tarea Enviar eliminada..." );
      remitente = null;
      }
    System.err.println( "Tarea Enviar eliminada..." );
    // Paramos y eliminanos la tarea de recepción de mensajes
    if( receptor != null && receptor.enEjecucion ) {
      System.err.println( "Tarea Recibir eliminada..." );
      receptor = null;
      }
    System.err.println( "Tarea Recibir eliminada..." );
    // Eliminamos las ventanas
    removeAll();
    // Cerramos el socket de conexión con el servidor
    try {
      if( s != null ) {
        s.close();
        System.err.println( "Socket cerrado..." );
        }
    } catch( IOException e ) {
      System.err.println( "No se puede cerrar el Socket: "+e );
      }
    s = null;

    // Si no estamos en ejecución como applet, cerramos la aplicación
    if( !esApplet ) {
      System.exit(0);
      }
    repaint();
    }

  // Sobreescribimos el método de repintado general
  public void paint( Graphics g ) {
    g.setColor( Color.lightGray );
    g.fillRect( 0,0,getSize().width,getSize().height );
    if( remitente == null && receptor == null && s == null ) {
      g.setFont( fuenteGrande );
      g.drawString( "Sin conexión al Servidor. Tareas paradas...",20,180 );
      }
    g.setColor( Color.lightGray );
    }

  // Actualiza la zona de presentación de todos los mensajes de la charla.
  // Debe estar sincronizado porque puede ser invocado desde la tarea
  // que se encarga de recibir los mensajes del servidor o desde la
  // propia aplicación
  synchronized public void actualizaZonaMensajes( String nTexto ) {
    TextArea aCharla = txtCharla;
    String texto = aCharla.getText();
    while( texto.length() + nTexto.length() > maxChars ) {
      int indice = texto.indexOf( "\n" );
      aCharla.replaceRange( "",0,indice+1 );
      texto = aCharla.getText();
      }
    aCharla.append( nTexto );
    }

  // Método principal que se invoca cuando el Cliente se ejecuta como
  // una aplicación aislada
  public static void main( String[] args ) {
    Frame frame = new Frame( "Tutorial de Java, Charla" );
    Applet charla = new ClienteChat( args );
    frame.add( charla,"Center" );
    frame.pack();

    frame.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent e ) {
        System.exit( 0 );
        }
      } );
    frame.setSize( 580,280 );
    frame.show();
    }
  }

//--------------------------------------- Final del fichero ClienteChat.java
