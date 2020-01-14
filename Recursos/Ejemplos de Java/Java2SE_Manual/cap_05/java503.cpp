//  
//  java503.cpp
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
//     Creacion: 29-Sep-1997  16:54:34
//     Revision: 27-May-1999  21:47:01
// 
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

// En este ejemplo se muestra el uso del destrcutor para liberar memoria
// en C++
//
// La salida que produce el programa en su ejecucion es:
//  Dentro del Constructor
//  Dentro del metodo muestraDatos. Dato vale 5
//  Abandona el bloque de reduccion de ambito. El Objeto sera destruido.
//  Dentro del Destructor
//  Fuera del bloque de reduccion de ambito
//  Terminando el programa
//

#include <iostream.h>
#include <new.h>
#include <stdlib.h>

// El programa instanciara un objeto de este tipo
class miClase { 
  // El programa instanciara un objeto de este tipo
  // para utilizarlo como puntero a memoria dinamica
  int *ptrNuevoDato; 

  public:
    // Constructor parametrizado
    miClase( int dato ) { 
      cout << "Dentro del Constructor\n";

      // Se instancia e inicializa el objeto en memoria dinamica
      ptrNuevoDato = new int( dato );
      if( !ptrNuevoDato ) 
        exit( 1 );
      }


    // Destructor
    ~miClase() {
      cout << "Dentro del Destructor\n";
      // Devuelve la memoria al sistema operativo
      delete ptrNuevoDato;
      }


    void muestraDatos() {
      cout << "Dentro del metodo muestraDatos. Dato vale "
        << *ptrNuevoDato << endl;
      }   
  };


// Simulacion de la clase principal de control de Java
class java503{
  public:
    // Simulacion del metodo main de Java
    static void clasePrincipal() {
      set_new_handler( 0 ); 

      { // Inicio de la reduccion de ambito
        // Instancia e inicializa el objeto
        miClase miObjeto( 5 ); 
        // Presenta en pantalla el contenido del objeto
        miObjeto.muestraDatos(); 
        cout << "Abandona el bloque de reduccion de ambito. El Objeto sera destruido\n";
      } // Final de la reduccion de ambito

      cout << "Fuera del bloque de reduccion de ambito\n";
      }
    };


void main() {
  // Invoca al metodo principal de la clase
  java503::clasePrincipal();
  }

//------------------------------------------- Final del fichero java503.cpp