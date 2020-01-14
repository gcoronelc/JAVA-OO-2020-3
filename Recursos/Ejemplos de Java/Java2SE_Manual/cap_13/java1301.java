//
//  java1301.java
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
//     Creacion: 30-Jun-1996  09:10:23
//     Revision: 05-Feb-2002  05:35:36
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

import java.awt.*;

public class java1301 extends Frame {

  public java1301() {
    Panel bottomPanel = new Panel();
    Panel centerPanel = new Panel();
    MenuBar mb = new MenuBar();
    Menu m = new Menu( "Menú" );
    m.add( new MenuItem( "Menú Selección 1" ) );
    m.add( new CheckboxMenuItem( "Menú Selección 2" ) );
    m.add( new MenuItem( "Menú Selección 3" ) );
    mb.add( m );
    setMenuBar( mb );

    // Incoporamos los elementos pequeños en la zona inferior
    bottomPanel.add( new TextField( "Campo de Texto" ) );
    bottomPanel.add( new Button( "Botón" ) );
    bottomPanel.add( new Checkbox( "Casilla Verificación" ) );

    Choice c = new Choice();
    c.addItem( "Selección Item 1" );
    c.addItem( "Selección Item 2" );
    c.addItem( "Selección Item 3" );
    bottomPanel.add( c );

    // En la zona central colocamos los elementos mayores
    centerPanel.setLayout( new GridLayout( 1,2 ) );

    // En la columna izquierda posicionamos un canvas
    centerPanel.add( new MiCanvas() );

    // En la zona central colocamos una etiqueta y una zona de texto
    Panel p = new Panel();
    p.setLayout( new BorderLayout() );
    p.add( "North",new Label( "Etiqueta",Label.CENTER ) );
    p.add( "Center",new TextArea( "Zona de Texto",5,20 ) );
    centerPanel.add( p );

    setLayout( new BorderLayout() );
    add( "South",bottomPanel );
    add( "Center",centerPanel );

    // En la columna derecha colocamos una lista de selección
    List l = new List( 3,false );
    l.add( "Lista item 1");
    l.add( "Lista item 2");
    l.add( "Lista item 3");
    l.add( "Lista item 4");
    l.add( "Lista item 5");
    l.add( "Lista item 6");
    l.add( "Lista item 7");
    l.add( "Lista item 8");
    l.add( "Lista item 9");
    add( "East",l );
    }


  public static void main( String args[] ) {
    java1301 ventana = new java1301();

    ventana.setTitle( "Tutorial de Java, Componentes AWT" );
    ventana.setSize( 400,250 );
    ventana.setVisible( true );
    }
  }


class MiCanvas extends Canvas {
  public void paint( Graphics g ) {
    int w = getSize().width;
    int h = getSize().height;
    g.drawRect( 0,0,w-1,h-1 );
    g.drawString( "Canvas",
      ( w-g.getFontMetrics().stringWidth( "Canvas" ) )/2,10 );

    g.setFont( new Font( "Helvetica",Font.PLAIN,8 ) );
    g.drawLine( 10,10,100,100 );
    g.fillRect( 9,9,3,3 );
    g.drawString( "(10,10)",13,10 );
    g.fillRect( 49,49,3,3);
    g.drawString( "(50,50)",53,50 );
    g.fillRect( 99,99,3,3);
    g.drawString( "(100,100)",103,100 );
    }
  }

//------------------------------------------ Final del fichero java1301.java