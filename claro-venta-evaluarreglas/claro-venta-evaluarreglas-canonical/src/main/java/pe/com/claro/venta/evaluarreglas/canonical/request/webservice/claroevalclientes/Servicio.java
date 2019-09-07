package pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes;

import java.io.Serializable;

public class Servicio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String grupo;
	private String nombre;
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
