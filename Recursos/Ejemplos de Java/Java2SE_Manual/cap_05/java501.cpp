//  
//  java501.cpp
//  Copyright (c) 1997,1999, Agustin Froufe
//  Todos los derechos reservados.
//  
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  daños o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho daño.
// 
//   Compilador: GNU C++ Compiler 2.7
//        Autor: Agustin Froufe
//     Creacion: 29-Sep-1997  15:26:26
//     Revision: 27-May-1999  21:03:36
// 
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------


// Este programa muestra la forma de instanciacion estatica y dinamica 
// tanto de variables primitivas como de objetos. Tambien ilustra la
// instanciacion de arrays dinamicos de variables y objetos
//
// La salida que se obtiene a la ejecucion del programa es semejante a la
// que se reproduce a continuacion
// miVariableInt contiene 6
// miPunteroInt apunta a 7
// miArrayPunterosInt apunta a 8 9 10
// miObjetoEstatico contiene 11
// miPunteroObj apunta a 12
// miArrayPunterosObj apunta a 13 14 15

#include <iostream.h>

class java501 {
  // Instancia una variable de clase
  int datoObj;

  public:
    // Constructor por defecto    
    java501() {}

    // Constructor parametrizado
    java501( int dato ) {
      datoObj = dato;
      }

    // Metodo para recupera datos
    int getData() {
      return datoObj;
      } 

    // Metodo para introducir datos
    void putData( int dato ) {
      datoObj = dato;
      }

    // Metodo de la clase que realiza las acciones del ejemplo
    static void clasePrincipal() {
      // Instancia e inicializa una variable primitiva estatica 
      int miVariableInt = 6;
      cout << "miVariableInt contiene " << miVariableInt << endl;

	    // Instancia e inicializa una variable primitiva dinamica
      int *miPunteroInt = new int( 7 );
      cout << "miPunteroInt apunta a " << *miPunteroInt << endl;

	    // Instancia un array dinamico para variables primitivas
	    //    La inicializacion no esta soportada en el caso de
	    //    arrays dinamicos
      int *miArrayPunterosInt = new int[3];

	    // Se introducen unos cuantos datos en el array
      for( int i=0; i < 3; i++ ) 
        *(miPunteroInt+i) = i+8;
	    // Y se presentan en la pantalla
      cout << "miArrayPunterosInt apunta a ";
      for( int i=0; i < 3; i++ ) 
        cout << *(miPunteroInt+i) << " ";
      cout << endl;

	    // Ahora vamos a realizar lo mismo pero con objetos en vez
	    // de variables primitivas
	    // Se instancia e inicializa un objeto estatico
      java501 miObjetoEstatico( 11 );
      cout << "miObjetoEstatico contiene " 
        << miObjetoEstatico.getData() << endl;

      // Se instancia e inicializa un objeto dinamico
      java501 *miPunteroObj = new java501( 12 );
      cout << "miPunteroObj apunta a " 
        << miPunteroObj->getData() << endl;

      // Se instancia un array dinamico de objetos
      //   La inicializacion de arrays dinamicos no esta
      //   soportada
      java501 *miArrayPunterosObj = new java501[3];

	    // Se introudcen unos cuantos datos en el array
      for( int i=0; i < 3; i++ ) 
        (miArrayPunterosObj+i)->putData( i+13 );
	    // Y se presentan en la pantalla
      cout << "miArrayPunterosObj apunta a ";
      for( int i=0; i < 3; i++ )
        cout << (miArrayPunterosObj+i)->getData() << " ";
      cout << endl;
      }
    };


  void main() {
    // Se invoca al metodo principal de la clase
    java501::clasePrincipal();
    }

//------------------------------------------- Final del fichero java501.cpp

