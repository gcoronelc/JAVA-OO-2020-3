/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea02;

import javax.swing.JTextField;

/**
 *
 * @author Mili
 */
public class PromedioAlumno {

  /**
   *
   * @param n1 Nota 1
   * @param n2 Nota 2
   * @param n3 Nota 3
   * @param n4 Nota 4
   * @param n5 Nota 5
   * @return Devuelve la nota mayor
   */
  public double NotaMayor(double n1, double n2, double n3, double n4, double n5) {
    double res = n1;

    if (n2 > res) {
      res = n2;
    }
    if (n3 > res) {
      res = n3;
    }
    if (n4 > res) {
      res = n4;
    }
    if (n5 > res) {
      res = n5;
    }

    return res;

  }

  /**
   *
   * @param n1 Nota 1
   * @param n2 Nota 2
   * @param n3 Nota 3
   * @param n4 Nota 4
   * @param n5 Nota 5
   * @return Devuelve la nota mayor
   */
  public double NotaMenor(double n1, double n2, double n3, double n4, double n5) {
    double res = n1;

    if (n2 < res) {
      res = n2;
    }
    if (n3 < res) {
      res = n3;
    }
    if (n4 < res) {
      res = n4;
    }
    if (n5 < res) {
      res = n5;
    }

    return res;
  }

  /**
   *
   * @param n1 Nota 1
   * @param n2 Nota 2
   * @param n3 Nota 3
   * @param n4 Nota 4
   * @param n5 Nota 5
   * @return Devuelve el promedio de las cuatro mejores notas
   */
  public double NotaPromedio(double n1, double n2, double n3, double n4, double n5) {
    double res = 0;

    double NMenor = NotaMenor(n1, n2, n3, n4, n5);
    res = (n1 + n2 + n3 + n4 + n5 - NMenor) / 4;
    return res;
  }

  /**
   *
   * @param promedio
   * @return verdadero si está aprobado y falso si está jalado
   */
  public boolean Aprobado(double promedio) {
    boolean res = true;
    if (promedio < 14) {
      res = false;
    }
    return res;
  }

  double NotaMayor(JTextField Tnota1, JTextField Tnota2, JTextField Tnota3, JTextField Tnota4, JTextField Tnota5) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
