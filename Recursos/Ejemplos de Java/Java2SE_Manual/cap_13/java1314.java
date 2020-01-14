//
//  java1314.java
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
//     Creacion: 08-Oct-1997  17:07:11
//     Revision: 05-Feb-2002  05:49:36
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo instancia y registra un objeto receptor de eventos
 * windowClosing() sobre un objeto Frame, de forma que se termime la
 * ejecucion del programa cuando se cierre el Frame. Sin embargo, el
 * Frame no se podra cerrar mientras el Dialogo Modal este presente
 * en la pantalla
 */
import java.awt.*;
import java.awt.event.*;

public class java1314 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }


class IHM {
  public IHM() {
    // Instancia un objeto Frame para servir como padre de dos objetos
    // Dialog, y como sera el padre, hay que instanciarlo el primero
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );

    // Se crea un objeto Dialog no modal conteniendo un boton para
    // cerrarlo con un objeto receptor de acciones registrado con el
    Dialog dialogoNoModal = new Dialog( miFrame,"Dialogo No Modal" );
    Button botonCerrarNoModal = new Button( "Cerrar" );
    dialogoNoModal.add( botonCerrarNoModal );
    botonCerrarNoModal.addActionListener(
      new closeDialogListener( dialogoNoModal ) );

    // Se crea un objeto Dialog modal conteniendo un boton para
    // cerrarlo con un objeto receptor de acciones registrado con el
    Dialog dialogoModal = new Dialog( miFrame,"Dialogo Modal",true );
    Button botonCerrarModal = new Button( "Cerrar" );
    dialogoModal.add( botonCerrarModal );
    botonCerrarModal.addActionListener(
      new closeDialogListener( dialogoModal ) );

    // Se crean dos botones que apareceran sobre los objetos Frame. Un
    // boton mostrara el Dialogo no Modal y el otro boton mostrara el
    // objeto Dialog modal. Se instancian y registran receptores de
    // de acciones sobre ambos botones que mostrararan el objeto
    // Dialog pasado como parametro, en la posicion X/Y que tambien
    // se pasa como parametro. Los objetos receptores de eventos
    // Action tambien controlan el tamaño de los objetos Dialog.
    Button mostrarBotonNoModal =
    new Button( "Muestra Dialogo No Modal" );
    mostrarBotonNoModal.addActionListener(
      new showDialogListener( dialogoNoModal,100 ) );
    Button mostrarBotonModal = new Button( "Muestra Dialogo Modal" );
    mostrarBotonModal.addActionListener(
      new showDialogListener( dialogoModal,200 ) );

    // Porfin, terminamos la construccion del objeto Frame
    miFrame.setLayout( new FlowLayout() );
    miFrame.add( mostrarBotonModal );
    miFrame.add( mostrarBotonNoModal );
    miFrame.setSize( 250,150 );
    miFrame.setVisible( true );

    // Se instancia y registra un receptor sobre la ventana para
    // concluir la ejecucion del programa cuando se cierre el Frame
    miFrame.addWindowListener( new Conclusion() );
    }
  }


// Clase para proporcionar un receptor de acciones que mostrara el
// Dialogo especificado como parametro en la posicion X/Y que
// tambiense le pasa como parametro. Este objeto receptor de eventos
// Action tambien controla el tamaño de los objetos Dialog
class showDialogListener implements ActionListener {
  Dialog oDialog;
  int oOffset;

  showDialogListener( Dialog dialogo,int offset ) {
    oDialog = dialogo;
    oOffset = offset;
    }

  public void actionPerformed( ActionEvent evt ) {
    // Seguir este orden es critico para un dialogo modal
    oDialog.setBounds( oOffset,oOffset,150,100 );
    oDialog.show();
    }
  }


// Clase para proporcionar un receptor de eventos que cierre el
// Dialogo que se le pase como parametro
class closeDialogListener implements ActionListener {
  Dialog oDialog;

  closeDialogListener( Dialog dialogo ) {
    oDialog = dialogo;
    }

  public void actionPerformed( ActionEvent evt ) {
    oDialog.setVisible( false );
    }
  }


class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    // Concluye la aplicacion cuando el usuario cierra la ventana
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1314.java