package pe.com.claro.venta.evaluarreglas.model;

public class Autonomia {	
	  private int cantidadDeLineasAdicionalesRUC;
	  private int cantidadDeLineasMaximas;
	  private String cantidadDeLineasRenovaciones;
	  private double montoCFParaRUC;
	  private String tipoDeAutonomiaCargoFijo;
	public int getCantidadDeLineasAdicionalesRUC() {
		return cantidadDeLineasAdicionalesRUC;
	}
	public void setCantidadDeLineasAdicionalesRUC(int cantidadDeLineasAdicionalesRUC) {
		this.cantidadDeLineasAdicionalesRUC = cantidadDeLineasAdicionalesRUC;
	}
	public int getCantidadDeLineasMaximas() {
		return cantidadDeLineasMaximas;
	}
	public void setCantidadDeLineasMaximas(int cantidadDeLineasMaximas) {
		this.cantidadDeLineasMaximas = cantidadDeLineasMaximas;
	}
	public String getCantidadDeLineasRenovaciones() {
		return cantidadDeLineasRenovaciones;
	}
	public void setCantidadDeLineasRenovaciones(String cantidadDeLineasRenovaciones) {
		this.cantidadDeLineasRenovaciones = cantidadDeLineasRenovaciones;
	}
	public double getMontoCFParaRUC() {
		return montoCFParaRUC;
	}
	public void setMontoCFParaRUC(double montoCFParaRUC) {
		this.montoCFParaRUC = montoCFParaRUC;
	}
	public String getTipoDeAutonomiaCargoFijo() {
		return tipoDeAutonomiaCargoFijo;
	}
	public void setTipoDeAutonomiaCargoFijo(String tipoDeAutonomiaCargoFijo) {
		this.tipoDeAutonomiaCargoFijo = tipoDeAutonomiaCargoFijo;
	}
	  
}
