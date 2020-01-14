//
//  java1320.java
//  Copyright (c) 1996,2002 Agustin Froufe
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
//     Creacion: 25-Jul-1996  19:55:42
//     Revision: 05-Feb-2002  05:56:12
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.awt.*;
import java.io.*;
import java.applet.Applet;

// Clase para pintar casi todos los componentes que ofrece el AWT
// de Java y poder visualizar su apariencia en la pantalla
public class java1320 extends Applet {
  TextArea edicion;
  miCanvas dibujo;
  Label edicionLab,lapizLab;
  List colores;
  Button imprimir,borrar;
  Choice figuras;
  Checkbox relleno;
  Scrollbar lapizBar;
  TextField lapizTex;
  Panel panelIzq,panelDch,panelBot,panelDib;

  public void init() {
    // Creamos los paneles con sus layout managers
    panelIzq = new Panel();
    panelIzq.setLayout( new BorderLayout() );

    panelDch = new Panel();
    panelDch.setLayout( new BorderLayout() );

    panelBot = new Panel();
    panelBot.setLayout( new GridLayout( 4,0 ) );

    panelDib = new Panel();
    panelDib.setLayout( new BorderLayout() );
    panelDib.setBounds( 1,1,200,20 );

    // Construimos el lado izquierdo de la ventana
    // Creamos la lista de colores
    colores = new List( 6,false );
    colores.add( "Rojo" );
    colores.add( "Naranja" );
    colores.add( "Amarillo" );
    colores.add( "Verde" );
    colores.add( "Azul" );
    colores.add( "Morado" );
    colores.add( "Negro" );
    colores.add( "Blanco" );
    // Añadimos la lista de colores al panel izquierdo
    panelIzq.add( "West",colores );

    // Creamos un nuevo Canvas
    dibujo = new miCanvas();
    dibujo.setBounds( 0,0,100,100 );
    // Añadimos el canvas al panel izquierdo
    panelIzq.add( "Center",dibujo );

    // Creamos los botones
    borrar = new Button( "Borrar" );
    imprimir = new Button( "Imprimir" );
    figuras = new Choice();
    figuras.addItem( "Cuadrado" );
    figuras.addItem( "Círculo" );
    figuras.addItem( "Triángulo" );
    relleno = new Checkbox( "Relleno" );
    // Añadimos los botones a su propio panel
    panelBot.add( borrar );
    panelBot.add( imprimir );
    panelBot.add( figuras );
    panelBot.add( relleno );
    // Añadimos el panel de botones al lado derecho
    panelIzq.add( "East",panelBot );

    // Creamos el area del lápiz
    lapizLab = new Label( "Lápiz" );
    lapizBar = new Scrollbar( Scrollbar.HORIZONTAL,1,1,1,10 );
    lapizBar.setBounds( 1,1,100,5 );
    lapizTex = new TextField( "1",8 );
    // Añadimos las partes anteriores a su propio panel
    panelDib.add( "North",lapizLab );
    panelDib.add( "Center",lapizBar );
    panelDib.add( "East",lapizTex );
    // Añadimos el panel a la parte inferior
    panelIzq.add( "South",panelDib );

    // Construimos el lado derecho de la ventana
    edicionLab = new Label( "Editor" );
    edicion = new TextArea( "Aqui se puede escribir",8,30 );
    // Añadimos la etiqueta y el área de texto al lado derecho
    panelDch.add( "North",edicionLab );
    panelDch.add( "South",edicion );

    // Incorporamos los dos paneles al applet que utiliza
    // FlowLayout por defecto
    add( panelIzq );
    add( panelDch );
    }

  public static void main( String args[] ) {
    Frame f = new Frame( "Tutorial de Java, AWT" );
    java1320 ejemplo = new java1320();

    ejemplo.init();

    f.add( "Center",ejemplo );
    f.pack();
    f.show();
    }
  }


// Creamos una clase para poder pintar una zona de dibujo y que
// se muestre el canvas. Pintamos un rectangulo alrededor suyo
class miCanvas extends Canvas {
  public void paint( Graphics g ) {
    int w = getSize().width;
    int h = getSize().height;
    g.drawRect( 0,0,w-1,h-1 );
    g.drawString( "Canvas",
      ( w-g.getFontMetrics().stringWidth( "Canvas" ) )/2,10 );
    }
  }

//------------------------------------------ Final del fichero java1320.java