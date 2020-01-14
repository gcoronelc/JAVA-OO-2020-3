//  
//  java301.cpp
//  Copyright (c) 1997,1999, Agustin Froufe
//  Todos los derechos reservados.
//  
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  daños o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho daño.
// 
//   Compilador: Borland C++, 5.0
//        Autor: Agustin Froufe
//     Creacion: 20-Oct-1997  03:49:46
//     Revision: 27-May-1999  21:29:54
// 
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

#include <iostream.h>

class java301 {
  public:
    static void MiClase( int argc,char *argv[] );
  };


// El bucle requiere que esta función esté definida fuera de la
// clase (los bucles en C++ no permiten funciones inline)
void java301::MiClase( int argc,char *argv[] ) {
  for( int i=0; i < argc; i++ )
    cout << argv[i] << endl;
  }


void main( int argc,char *argv[] ) {
  java301::MiClase( argc,argv );
  }

//------------------------------------------- Final del fichero java301.cpp
