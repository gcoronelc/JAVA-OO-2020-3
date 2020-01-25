package modelo;

public class ConsumoCliente {

	private double consumo;
	private double impuesto;
	private double total;
	private double servicio;
	private double tGeneral;
	private int porceImpuesto = 18;
	private int porceServicio = 10;
	private Porcentaje calc = new Porcentaje();

	public double calcula() {

		calc.setValor(total);
		calc.setPorciento(porceImpuesto);
		impuesto = calc.calcula();
		calc.setPorciento(porceServicio);
		servicio = calc.calcula();
		consumo = total - impuesto;
		tGeneral = total + servicio;

		return total;
	} // fin calcula

	public int getPorceImpuesto() {
		return porceImpuesto;
	}

	public void setPorceImpuesto(int porceImpuesto) {
		this.porceImpuesto = porceImpuesto;
	}

	public int getPorceServicio() {
		return porceServicio;
	}

	public void setPorceServicio(int porceServicio) {
		this.porceServicio = porceServicio;
	}

// GETS Y SETS
	public double getConsumo() {
		return consumo;
	}

//    public void setConsumo(double consumo) {
//        this.consumo = consumo;
//    }
	public double getImpuesto() {
		return impuesto;
	}

//    public void setImpuesto(double impuesto) {
//        this.impuesto = impuesto;
//    }
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
		this.calcula();
	}

	public double getServicio() {
		return servicio;
	}

//    public void setServicio(double servicio) {
//        this.servicio = servicio;
//    }
	public double gettGeneral() {
		return tGeneral;
	}

//    public void settGeneral(double tGeneral) {
//        this.tGeneral = tGeneral;
//    }
}// fin ConsumoCliente
