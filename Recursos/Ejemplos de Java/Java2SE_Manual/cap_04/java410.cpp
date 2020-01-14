//  
//  java410.cpp
//  Copyright (c) 1997,1999, Agustin Froufe
//  Todos los derechos reservados.
//  
//  No se asume ninguna  responsabilidad por el  uso o  alteracion  de este
//  software.  Este software se proporciona COMO ES, sin garantia de ningun
//  tipo de su funcionamiento y en ningun caso sera el autor responsable de
//  da�os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da�o.
// 
//   Compilador: GNU C++ Compiler 2.7
//        Autor: Agustin Froufe
//     Creacion: 01-Aug-1997  20:46:54
//     Revision: 27-May-1999  21:23:12
// 
//--------------------------------------------------------------------------
//  Esta informacion no es necesariamente definitiva y esta sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//--------------------------------------------------------------------------

// Ilustra el almacenamiento de una variable en memoria din�mica
// en una funci�n y el retorno de un puntero a esa variable.
// Como la variable se encuentra en memoria din�mica, la memoria
// que ocupa la variable local no se sobreescribe una vez que
// concluye la funci�n, por lo que el puntero todav�a se puede
// utilizar para presentar el valor de la zona a la que correspond�a
// la variable

#include <iostream.h>
#include <new.h>
#include <stdlib.h>

int *test() {
  // El sistema devolver� nulo si hay un falo de alocataci�n
  set_new_handler( 0 );
  // Declaramos una variable en la funci�n
  int *puntero = new int( 6 );

  if( !puntero ) 
    // Salimos si no se puede reservar memoria
    exit( 1 ); 

  // Devuelve un puntero a la variable local
  return puntero;
  }


void main() {
  // Recuperamos el puntero devuelto por la funci�n
  int *punteroMain = test();
  // Machacamos una zona de memoria
  int memArray[5] = { 10,11,12,13,14 };

  // Presenta el contenido de la memoria apuntada por el puntero
  cout << "El valor devuelto es " << *punteroMain;
  }

//------------------------------------------- Final del fichero java410.cpp