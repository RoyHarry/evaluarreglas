package pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes;

import java.io.Serializable;

public class Campana implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String grupo;
	private String tipo;
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
