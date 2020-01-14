//
//  java1516.java
//  Copyright (c) 1999,2002 Agustin Froufe
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
//     Creacion: 06-May-1999  06:24:54
//     Revision: 08-Feb-2002  05:54:43
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * En este ejemplo se muestra la utilización de todos los métodos de dibujo
 * de figuras que permite la plataforma Java 2
 */
import java.awt.*;
import java.awt.event.*;

// Clase de control del ejemplo
class java1516 extends Frame {

  // Función de control de la aplicación
  public static void main( String[] args ) {
    // Se instancia un objeto de la clase
    new java1516();
    }

  // Contructor de la clase
  public java1516() {
    this.setTitle( "Tutorial de Java, Gráficos" );
    this.setSize( 475,275 );
    this.setVisible( true );

    // Clase anidada que permite terminar la ejecución de la animación
    this.addWindowListener(
      // Definición de la clase anónima para controlar el cierre de
      // la ventana
      new WindowAdapter() {
        public void windowClosing( WindowEvent evt ) {
          // Se concluye el programa
          System.exit( 0 );
        }
      } );
    }

    // Se sobrecarga el método paint()
  public void paint( Graphics g ){
    g.setColor( Color.red );

    // Trasladamos el origen de coordenadas que se sitúa en la
    // esquina superior izquierda, para evitar el problema que se
    // produce con insets. De este modo el origen de coordenadas sí
    // que lo dejamos situado en la zona cliente del objeto Frame
    // que es la que se utiliza para pintas
    g.translate( this.getInsets().left,this.getInsets().top );

    // Línea simple
    g.drawLine( 10,0,50,50 );
    g.setColor( Color.black );
    g.drawString( "drawLine",10,62 );
    g.setColor( Color.red );

    // Se crean dos arrays de coordenadas para pintar una
    // polilínea
    int x1Datos[] = {80,130,80,130};
    int y1Datos[] = {0,50,50,0};
    g.drawPolyline( x1Datos,y1Datos,4 );
    g.setColor( Color.black );
    g.drawString( "drawPolyline",70,62 );
    g.setColor( Color.red );

    // Rectángulo
    g.drawRect( 150,0,50,50 );
    g.setColor( Color.black );
    g.drawString( "drawRect",150,62 );
    g.setColor( Color.red );

    // Rectángulo relleno
    g.fillRect( 220,0,50,50 );
    g.setColor( Color.black );
    g.drawString( "fillRect",225,62 );
    g.setColor( Color.red );

    // Rectángulo redondeado
    g.drawRoundRect( 300,0,50,50,10,10 );
    g.setColor( Color.black );
    g.drawString( "drawRoundRect",280,62 );
    g.setColor( Color.red );

    // Rectángulo redondeado relleno
    g.fillRoundRect( 385,0,50,50,10,10 );
    g.setColor( Color.black );
    g.drawString( "fillRoundRect",375,62 );

    // Pinta un rectángulo 3D, sobresaliendo de la pantalla
    // No parece demasiado 3D
    g.setColor( Color.gray );
    g.draw3DRect( 10,90,55,25,true );
    // Rectángulo 3D, pulsado
    g.draw3DRect( 70,90,50,25,false );
    g.setColor( Color.black );
    g.drawString( "draw3DRect",30,140 );

    // Rectángulo 3D relleno. Se ha puesto un fondo gris
    // con lo cual se puede apreciar mucho mejor el efecto
    // de tres dimensiones
    // Fondo gris
    g.setColor( Color.gray );
    g.fillRect( 145,75,130,55 );
    g.fill3DRect( 155,90,50,25,true );
    // Rectángulo 3D relleno, pulsado
    g.fill3DRect( 215,90,50,25,false );
    g.setColor( Color.red );
    // De todos modos, la apariencia de tres dimensiones
    // con 3DRect no es demasiado buena, porque es necesario
    // seleccionar muy bien la paleta de colores para que
    // se genere la ilusión de 3D
    g.setColor( Color.black );
    g.drawString( "fill3DRect",180,140 );
    g.setColor( Color.red );

    // Pinta un ángulo de 255 grados inscrito en un rectángulo
    g.drawRect( 300,77,50,50 );
    g.drawArc( 300,77,50,50,0,225 );
    g.setColor( Color.black );
    g.drawString( "drawArc",305,140 );
    g.setColor( Color.red );

    // Angulo relleno de 255 grados inscrito en un rectángulo
    g.drawRect( 385,77,50,50 );
    g.fillArc( 385,77,50,50,0,225 );
    g.setColor( Color.black );
    g.drawString( "fillArc",395,140 );
    g.setColor( Color.red );

    // Elipse, con el eje grande horizontal
    g.drawOval( 10,165,50,25 );
    // Círculo
    g.drawOval( 70,150,50,50 );
    g.setColor( Color.black );
    g.drawString( "drawOval",35,218 );
    g.setColor( Color.red );

    // Elipse rellena, con el eje grande vertical
    g.fillOval( 170,150,25,50 );
    // Círculo relleno
    g.fillOval( 210,150,50,50 );
    g.setColor( Color.black );
    g.drawString( "fillOval",185,218 );
    g.setColor( Color.red );

    // Polígono
    int x2Datos[] = {300,350,300,350};
    int y2Datos[] = {150,200,200,150};
    g.drawPolygon( x2Datos,y2Datos,4 );
    g.setColor( Color.black );
    g.drawString( "drawPolygon",290,218 );
    g.setColor( Color.red );

    // Polígono relleno
    int x3Datos[] = {385,435,385,435};
    int y3Datos[] = {150,200,200,150};
    g.fillPolygon( x3Datos,y3Datos,4 );
    g.setColor( Color.black );
    g.drawString( "fillPolygon",385,218 );
  }
}

//------------------------------------------ Final del fichero java1516.java