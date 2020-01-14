//
//  java1205.java
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
//     Creacion: 30-Jan-1998  16:08:14
//     Revision: 03-Feb-2002  12:42:45
//
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

/**
 * Esto es un ejemplo de una aplicacion especifica para control de sistemas
 * en una sola clase. Las clases anidadas permite encapsular diferente
 * funcionalidad para cada tipo de evento
 */
public class java1205 extends java1204 {
  private boolean luces = false;
  private boolean ducha = false;
  private String calefaccion = "Dia";

  private class EncenderLuces extends java1203 {
    public EncenderLuces( long horaEvento ) {
      super( horaEvento );
      }
    public void accion() {
      // Aqui se colocaria el codigo para el control hardware que
      // fisicamente encenderia las luces. En las demas clases, en
      // el metodo accion() se colocaria tambien el control
      // hardware de los dispositivos que correspondan: ducha,
      // calefacción y timbre
      luces = true;
      }
    public String descripcion() {
      return( "Luces encendidas" );
      }
    }

  private class ApagarLuces extends java1203 {
    public ApagarLuces( long horaEvento ) {
      super( horaEvento );
      }
    public void accion() {
      luces = false;
      }
    public String descripcion() {
      return( "Luces apagadas" );
      }
    }

  private class AbrirDucha extends java1203 {
    public AbrirDucha( long horaEvento ) {
      super( horaEvento );
      }
    public void accion() {
      ducha = true;
      }
    public String descripcion() {
      return( "Duchita caliente abierta" );
      }
    }

  private class CerrarDucha extends java1203 {
    public CerrarDucha( long horaEvento ) {
      super( horaEvento );
      }
    public void accion() {
      ducha = false;
      }
    public String descripcion() {
      return( "Ducha caliente cerrada" );
      }
    }

  private class CalefaccionNoche extends java1203 {
    public CalefaccionNoche( long horaEvento ) {
      super( horaEvento );
      }
    public void accion() {
      calefaccion = "Noche";
      }
    public String descripcion() {
      return( "Calefaccion a temperatura de Noche" );
      }
    }

  private class CalefaccionDia extends java1203 {
    public CalefaccionDia( long horaEvento ) {
      super( horaEvento );
      }
    public void accion() {
      calefaccion = "Dia";
      }
    public String descripcion() {
      return( "Calefaccion a temperatura de Dia" );
      }
    }

  // En el siguiente metodo accion() se inserta un
  // nuevo evento en la lista
  private int timbrazos;
  private class Timbre extends java1203 {
    public Timbre( long horaEvento ) {
      super( horaEvento );
      }
    public void accion() {
      // Hace sonar el timbre cada dos segundos
      System.out.println( "Riiiiiinnnngggg!" );
      if ( --timbrazos > 0 )
        anadeEvento( new Timbre(System.currentTimeMillis()+2000) );
      }
    public String descripcion() {
      return( "Timbre sonando" );
      }
    }

  private class Reinicializacion extends java1203 {
    public Reinicializacion( long horaEvento ) {
      super( horaEvento );
      }
    public void accion() {
      long tm = System.currentTimeMillis();

      timbrazos = 5;
      anadeEvento( new CalefaccionNoche( tm ) );
      anadeEvento( new EncenderLuces( tm+1000 ) );
      anadeEvento( new ApagarLuces( tm+2000 ) );
      anadeEvento( new AbrirDucha( tm+3000 ) );
      anadeEvento( new CerrarDucha( tm+8000 ) );
      anadeEvento( new Timbre( tm+9000 ) );
      anadeEvento( new CalefaccionDia( tm+10000 ) );
      // Se puede incorporar un evento de reinicializacion
      anadeEvento( new Reinicializacion( tm+20000 ) );
      }
    public String descripcion() {
      return( "--->Reinicializando el Sistema" );
      }
    }

  public static void main( String args[] ) {
    java1205 gc = new java1205();
    long tm = System.currentTimeMillis();

    gc.anadeEvento( gc.new Reinicializacion( tm ) );
    gc.ejecutar();
    }
  }

//------------------------------------------ Final del fichero java1205.java