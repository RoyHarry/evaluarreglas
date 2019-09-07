package pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes;

import java.io.Serializable;

public class PlanProactivoWS implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double cargoFijo;
	private String descripcion;
	private double montoCFSEC;
	private double precioDeVenta;
	public double getCargoFijo() {
		return cargoFijo;
	}
	public void setCargoFijo(double cargoFijo) {
		this.cargoFijo = cargoFijo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getMontoCFSEC() {
		return montoCFSEC;
	}
	public void setMontoCFSEC(double montoCFSEC) {
		this.montoCFSEC = montoCFSEC;
	}
	public double getPrecioDeVenta() {
		return precioDeVenta;
	}
	public void setPrecioDeVenta(double precioDeVenta) {
		this.precioDeVenta = precioDeVenta;
	}
	
}
