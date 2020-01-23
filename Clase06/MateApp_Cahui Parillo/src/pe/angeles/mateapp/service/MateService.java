package pe.angeles.mateapp.service;

public class MateService {

	public long factorial(int n) {
		//Variables
		long fact;
		//Proceso
		fact = 1;
		while (n > 1) {
			fact *= n--;
		}

		//Reporte
		return fact;

	}

	public int mcd(int n1, int n2) {
		//variables
		int mcd = n1;
		//Proceso
		if (n1 > n2) {
			mcd = n2;
		}
		while ((n1 % mcd != 0 || n2 % mcd != 0) && mcd > 1) {
			mcd--;
		}
		//while ((a%divisor!=0 || b%divisor!=0) && divisor>1)
		//Reporte
		return mcd;

	}

	public int mcm(int n1, int n2) {
		//variables
		int mcm = n2;
		//Proceso
		if (n1 > n2) {
			mcm = n1;
		}
		while (mcm % n1 != 0 || mcm % n2 != 0) {
			mcm++;
		}

		//Reporte
		return mcm;
	}

	public String fibonacc(int n) {
		if( n == 1){
			return "0";
		}
		if( n == 2 ){
			return "0,1";
		}
		String fibo = "0,1";
		int num1, num2, suma;
		num1 = 0;
		num2 = 1;
		for (int i = 1; i <= (n-2); i++) {
			suma = num1 + num2;
			num1 = num2;
			num2 = suma;
			fibo = fibo + ", " + suma;

		}

		return fibo;
	}

	public String esPrimo(int n) {
		//Variables
		boolean primo = true;
		//Proceso
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				primo = false;
				break;
			}
		}
		if (primo) {
			return ("Es primo");
		} else {
			return ("No es primo");
		}
	}

}
