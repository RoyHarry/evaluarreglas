package pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes;

import java.io.Serializable;
import java.util.List;

public class PlanSolicitado implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String paquete;
	private List<Servicio> servicio;
	public String getPaquete() {
		return paquete;
	}
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}
	public List<Servicio> getServicio() {
		return servicio;
	}
	public void setServicio(List<Servicio> servicio) {
		this.servicio = servicio;
	}
	
}
