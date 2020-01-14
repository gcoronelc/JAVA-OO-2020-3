//
//  java1326.java
//  Copyright (c) 1996,2002 Agustin Froufe
//  Todos los derechos reservados.
//
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  daños o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho daño.
//
//   Compilador: javac 1.2.2,  Java 2 SDK
//        Autor: Agustin Froufe
//     Creacion: 06-Jun-1998  20:12:24
//     Revision: 05-Feb-2002  06:02:03
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Demostracion del uso del GridBagLayout
 * Vamos a crear nueve botones, fijando los Constraints para
 * que se pueda ver la flexibilidad que permite el uso de este
 * layout, junto con la complicación que supone el que si se cambia
 * alguna de las características, todo el resto del conjunto se ve
 * afectado, y resultará sumamente latoso el volver a recomponer el
 * conjunto del interfaz.
 */
import java.awt.*;

public class java1326 {
  public static void main( String args[] ) {
    IHM ihm = new IHM();
    }
  }

class IHM {
  public IHM() {
    Frame miFrame = new Frame( "Tutorial de Java, AWT" );
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    miFrame.setLayout( gridbag );

    // Para este grupo fijamos la anchura y vamos variando alguna
    // de las caracteristicas en los siguientes, de tal forma que
    // los botones que aparecen en cada una de las lineas tengan
    // apariencia diferente en pantalla
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    Button boton0 = new Button( "Botón 0" );
    gridbag.setConstraints( boton0,gbc );
    miFrame.add( boton0 );
    Button boton1 = new Button( "Botón 1" );
    gridbag.setConstraints( boton1,gbc );
    miFrame.add( boton1 );
    Button boton2 = new Button( "Botón 2" );
    gridbag.setConstraints( boton2,gbc );
    miFrame.add( boton2 );

    gbc.gridwidth = GridBagConstraints.REMAINDER;
    Button boton3 = new Button( "Botón 3" );
    gridbag.setConstraints( boton3,gbc );
    miFrame.add( boton3 );

    gbc.weightx = 0.0;
    Button boton4 = new Button( "Botón 4" );
    gridbag.setConstraints( boton4,gbc );
    miFrame.add( boton4 );

    gbc.gridwidth = GridBagConstraints.RELATIVE;
    Button boton5 = new Button( "Botón 5" );
    gridbag.setConstraints( boton5,gbc );
    miFrame.add( boton5 );

    gbc.gridwidth = GridBagConstraints.REMAINDER;
    Button boton6 = new Button( "Botón 6" );
    gridbag.setConstraints( boton6,gbc );
    miFrame.add( boton6 );

    gbc.gridwidth = 1;
    gbc.gridheight = 2;
    gbc.weighty = 1.0;
    Button boton7 = new Button( "Botón 7" );
    gridbag.setConstraints( boton7,gbc );
    miFrame.add( boton7 );

    gbc.weighty = 0.0;
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.gridheight = 1;
    Button boton8 = new Button( "Botón 8" );
    gridbag.setConstraints( boton8,gbc );
    miFrame.add( boton8 );
    Button boton9 = new Button( "Botón 9" );
    gridbag.setConstraints( boton9,gbc );
    miFrame.add( boton9 );
    miFrame.setSize( 250,150 );
    miFrame.setVisible( true );
    }
  }

//------------------------------------------- Final del fichero java1326.java