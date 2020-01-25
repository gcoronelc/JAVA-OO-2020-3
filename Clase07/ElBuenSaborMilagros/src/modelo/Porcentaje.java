/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Mili
 */
public class Porcentaje {

	private double valor;
	private double porciento;
	private double resultado;

	public double calcula() {
		//    valor = n1;
		//    porciento = n2;
		resultado = valor * porciento / 100;
		return resultado;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getPorciento() {
		return porciento;
	}

	public void setPorciento(double porciento) {
		this.porciento = porciento;
	}

	public double getResultado() {
		return resultado;
	}

	public void setResultado(double resultado) {
		this.resultado = resultado;
	}

}
