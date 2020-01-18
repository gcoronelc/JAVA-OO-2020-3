package pe.egcc.app.model;

/**
 *
 * @author Gustavo Coronel
 * @blog http://gcoronelc.blogspot.com
 * @email gcoronelc@gmail.com
 */
public class Producto {

  private String nombre;
  private double precio;
  private int stock;
  private boolean activo;

  public Producto() {

    this.nombre = "Camioneta 4x4";
    this.precio = 50000.0;
    this.stock = 60;
    this.activo = true;
    
    System.out.println("Producto creado.");
    
  }

  @Override
  protected void finalize() throws Throwable {
    System.err.println("Chau producto .......");
  }
    
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public boolean isActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }

}
