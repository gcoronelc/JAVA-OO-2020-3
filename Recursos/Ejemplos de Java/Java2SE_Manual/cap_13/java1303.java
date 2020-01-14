//
//  java1303.java
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
//     Creacion: 06-Mar-1998  11:58:21
//     Revision: 05-Feb-2002  05:37:19
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo imprime un mensaje cada vez que se pulsa uno de los
 * objetos Button, que queda seleccionado. Se instancia tambien un
 * objeto receptor para recoger los eventos windowClosing que se
 * generen sobre el Frame, que haran que se cierre la ventana y se
 * termine la aplicacion
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class java1303 {
  public static void main( String args[] ) {
    // Se instancia un objeto Interface Hombre-maquina
    IHM ihm = new IHM();
    }
  }


// Clase de la Interface grafica
class IHM {
  // Constructor de la clase
  public IHM() {
    // Se crea un objeto CheckboxGroup
    CheckboxGroup miCheckboxGroup = new CheckboxGroup();

    // Ahora se crea un objeto Button y se registra un objeto
    // ActionListener sobre el
    Button miBoton = new Button( "Aceptar" );
    miBoton.addActionListener( new MiActionListener( miCheckboxGroup ) );

    // Se crea un objeto Frame para contener los objetos Checkbox y el
    // objeto Button. Se fija un FlowLayout, se incorporan a el los
    // objetos, se fija el tamaño y se hace visible
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    miFrame.setLayout( new FlowLayout() );
    miFrame.add( new Checkbox( "A",true,miCheckboxGroup ) );
    miFrame.add( new Checkbox( "B",false,miCheckboxGroup ) );
    miFrame.add( new Checkbox( "C",false,miCheckboxGroup ) );
    miFrame.add( new Checkbox( "D",false,miCheckboxGroup ) );
    miFrame.add( miBoton );
    miFrame.setSize( 250,100 );
    miFrame.setVisible( true );

    // Instanciamos y registramos un receptor para terminar la
    // ejecucion de la aplicacion, cuando el ususrio cierre la
    // ventana
    miFrame.addWindowListener( new Conclusion() );
    }
  }


// Esta clase indica la caja de seleccion que esta seleccionada
// cuando se pulsa el boton de Aceptar
class MiActionListener implements ActionListener {
  CheckboxGroup oCheckBoxGroup;

  MiActionListener( CheckboxGroup checkBGroup ) {
    oCheckBoxGroup = checkBGroup;
    }

  public void actionPerformed( ActionEvent evt ) {
    System.out.println(oCheckBoxGroup.getSelectedCheckbox().getName()+
      " " + oCheckBoxGroup.getSelectedCheckbox().getLabel() );
    }
  }


// Concluye la aplicacion cuando el usuario cierra la ventana
class Conclusion extends WindowAdapter {
  public void windowClosing( WindowEvent evt ) {
    System.exit( 0 );
    }
  }

//------------------------------------------ Final del fichero java1303.java