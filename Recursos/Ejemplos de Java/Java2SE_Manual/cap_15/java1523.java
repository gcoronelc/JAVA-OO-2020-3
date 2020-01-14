//
//  java1523.java
//  Copyright (c) 2002 Agustin Froufe
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
//     Creacion: 03-Ene-2002  20:51:19
//     Revision: 08-Feb-2002  06:11:37
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Este ejemplo permite ver en acción los doce tipos de constantes que
 * define la clase AlphaComposite, una para cada una de las reglas
 * Porter-Duff, y permite al lector comprobar el efecto de los distintos
 * niveles de transparecia en todas ellas.
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.lang.reflect.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class java1523 extends JFrame {
  JSlider origen = new JSlider();
  JSlider destino = new JSlider();
  JComboBox reglas = new JComboBox();
  DrawingPanel panelDibujo = new DrawingPanel();

  public java1523() {
    super( "Tutorial de Java, Gráficos" );
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    JPanel panel = (JPanel)this.getContentPane();
    Dictionary rotulos = new Hashtable();
    rotulos.put( new Integer(0),new JLabel("0.0") );
    rotulos.put( new Integer(25),new JLabel("0.25") );
    rotulos.put( new Integer(33),new JLabel("0.33") );
    rotulos.put( new Integer(50),new JLabel("0.50") );
    rotulos.put( new Integer(67),new JLabel("0.67") );
    rotulos.put( new Integer(75),new JLabel("0.75") );
    rotulos.put( new Integer(100),new JLabel("1.00") );

    origen.setOrientation( JSlider.VERTICAL );
    origen.setLabelTable( rotulos);
    origen.setPaintTicks( true );
    origen.setPaintLabels( true );
    origen.setValue( 100 );
    origen.addChangeListener( new ChangeListener() {
      public void stateChanged( ChangeEvent evt ) {
        int valor = origen.getValue();
        panelDibujo.setSourcePercentage( valor/100.0f );
        }
      } );

    destino.setOrientation(JSlider.VERTICAL);
    destino.setLabelTable(rotulos);
    destino.setPaintTicks(true);
    destino.setPaintLabels(true);
    destino.setValue(100);
    destino.addChangeListener( new ChangeListener() {
      public void stateChanged( ChangeEvent evt ) {
        int valor = destino.getValue();
        panelDibujo.setDestinationPercentage( valor/100.0f );
        }
      } );

    String txtReglas[] = {
      "CLEAR, no se ve nada",
      "DST, sólo se ve el destino",
      "DST_ATOP, intersección y origen",
      "DST_IN, intersección destino y origen",
      "DST_OUT, destino que no pisa a origen",
      "DST_OVER, destino sobre origen",
      "SRC, sólo se ve el origen",
      "SRC_ATOP, intersección y destino",
      "SRC_IN, intersección origen y destino",
      "SRC_OUT, origen que no pisa a destino",
      "SRC_OVER, origen sobre destino",
      "XOR, operación XOR"};
    ComboBoxModel modelo = new DefaultComboBoxModel( txtReglas );
    reglas.setModel( modelo );
    reglas.setSelectedItem( "XOR, operación XOR" );
    reglas.addActionListener( new ActionListener() {
      public void actionPerformed( ActionEvent evt ) {
        StringTokenizer token =
          new StringTokenizer( reglas.getSelectedItem().toString(),"," );
        String valorAlfa = token.nextToken();
        Class claseAlfa = AlphaComposite.class;
        try {
          Field campo = claseAlfa.getDeclaredField( valorAlfa );
          int regla = ((Integer)campo.get(AlphaComposite.Clear)).intValue();
          panelDibujo.setCompositeRule( regla );
        } catch( Exception e ) {
          e.printStackTrace();
          }
        }
      } );
    panel.add( origen,BorderLayout.WEST );
    panel.add( destino,BorderLayout.EAST );
    panel.add( reglas,BorderLayout.SOUTH );
    panel.add( panelDibujo,BorderLayout.CENTER );
    pack();
    }

  public static void main( String args[] ) {
    new java1523().show();
    }

  class DrawingPanel extends JPanel {
    GeneralPath sourcePath,destPath;
    BufferedImage source,dest;
    float origen = 1;
    float destino = 1;
    int compositeRule = AlphaComposite.XOR;
    Dimension dimension = new Dimension(200, 200);

    public DrawingPanel() {
      sourcePath = new GeneralPath();
      sourcePath.moveTo( 4,4 );
      sourcePath.lineTo( 150,4 );
      sourcePath.lineTo( 150,100 );
      sourcePath.lineTo( 4,196 );
      sourcePath.closePath();
      source = new BufferedImage( 200,200,BufferedImage.TYPE_INT_ARGB );
      destPath = new GeneralPath();
      destPath.moveTo( 196,4 );
      destPath.lineTo( 50,4 );
      destPath.lineTo( 50,100 );
      destPath.lineTo( 196,196 );
      destPath.closePath();
      dest = new BufferedImage( 200,200,BufferedImage.TYPE_INT_ARGB );
      }

    public void setSourcePercentage( float valor ) {
      origen = valor;
      repaint();
      }

    public void setDestinationPercentage( float valor ) {
      destino = valor;
      repaint();
      }

    public void setCompositeRule( int valor ) {
      compositeRule = valor;
      repaint();
      }

    public void paintComponent( Graphics g ) {
      super.paintComponent( g );
      Graphics2D g2d = (Graphics2D)g;
      Graphics2D sourceG = source.createGraphics();
      Graphics2D destG = dest.createGraphics();
      destG.setComposite( AlphaComposite.Clear );
      destG.fillRect( 0,0,200,200 );
      destG.setComposite( AlphaComposite.getInstance(
        AlphaComposite.XOR,destino) );
      destG.setPaint( Color.red );
      destG.fill( destPath );
      sourceG.setComposite( AlphaComposite.Clear );
      sourceG.fillRect( 0,0,200,200 );
      sourceG.setComposite( AlphaComposite.getInstance(
        AlphaComposite.XOR,origen) );
      sourceG.setPaint( Color.green );
      sourceG.fill( sourcePath );
      destG.setComposite( AlphaComposite.getInstance(compositeRule) );
      destG.drawImage( source,0,0,null );
      g2d.drawImage( dest,0,0,this );
      }

    public Dimension getPreferredSize() {
      return( dimension );
      }
    }
  }

//------------------------------------------ Final del fichero java1427.java
