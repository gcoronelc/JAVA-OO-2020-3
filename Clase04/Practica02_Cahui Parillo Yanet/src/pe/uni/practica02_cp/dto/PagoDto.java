
package pe.uni.practica02_cp.dto;


public class PagoDto {
    
    //Datos
    
    private double horas;
    private double dias;
    private double pagohora;
    
    //Resultado
    private double sueldo_bruto;
    private double sueldo_neto;
    private double retencion;
    
    
    //Constructor por defecto
    public PagoDto() {
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public double getDias() {
        return dias;
    }

    public void setDias(double dias) {
        this.dias = dias;
    }

    public double getPagohora() {
        return pagohora;
    }

    public void setPagohora(double pagohora) {
        this.pagohora = pagohora;
    }

    public double getSueldo_bruto() {
        return sueldo_bruto;
    }

    public void setSueldo_bruto(double sueldo_bruto) {
        this.sueldo_bruto = sueldo_bruto;
    }

    public double getSueldo_neto() {
        return sueldo_neto;
    }

    public void setSueldo_neto(double sueldo_neto) {
        this.sueldo_neto = sueldo_neto;
    }

    public double getRetencion() {
        return retencion;
    }

    public void setRetencion(double retencion) {
        this.retencion = retencion;
    }

 
    
    
}
