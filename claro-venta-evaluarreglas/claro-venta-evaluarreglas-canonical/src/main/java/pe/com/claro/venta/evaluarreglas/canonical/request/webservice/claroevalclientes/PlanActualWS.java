package pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes;

import java.io.Serializable;

public class PlanActualWS implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int mesesParaCubrirApadece;
	private double plazoDeAcuerdo;
	private int tiempoPermanencia;
	public int getMesesParaCubrirApadece() {
		return mesesParaCubrirApadece;
	}
	public void setMesesParaCubrirApadece(int mesesParaCubrirApadece) {
		this.mesesParaCubrirApadece = mesesParaCubrirApadece;
	}
	public double getPlazoDeAcuerdo() {
		return plazoDeAcuerdo;
	}
	public void setPlazoDeAcuerdo(double plazoDeAcuerdo) {
		this.plazoDeAcuerdo = plazoDeAcuerdo;
	}
	public int getTiempoPermanencia() {
		return tiempoPermanencia;
	}
	public void setTiempoPermanencia(int tiempoPermanencia) {
		this.tiempoPermanencia = tiempoPermanencia;
	}
	
	
}
