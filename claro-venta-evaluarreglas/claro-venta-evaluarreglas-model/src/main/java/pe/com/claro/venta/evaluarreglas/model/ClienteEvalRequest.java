package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;

import pe.com.claro.common.bean.BodyResponse;

public class ClienteEvalRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer piBuroCod;
	private String piTipoDocCod;
	private String piNumDoc;
	private String piOficina;
	private String piNroOperacion;
	private String piLineasActivas;
	private String piLineaEvaluada;

	public Integer getPiBuroCod() {
		return piBuroCod;
	}
	public void setPiBuroCod(Integer piBuroCod) {
		this.piBuroCod = piBuroCod;
	}
	public String getPiTipoDocCod() {
		return piTipoDocCod;
	}
	public void setPiTipoDocCod(String piTipoDocCod) {
		this.piTipoDocCod = piTipoDocCod;
	}
	public String getPiNumDoc() {
		return piNumDoc;
	}
	public void setPiNumDoc(String piNumDoc) {
		this.piNumDoc = piNumDoc;
	}
	public String getPiOficina() {
		return piOficina;
	}
	public void setPiOficina(String piOficina) {
		this.piOficina = piOficina;
	}
	public String getPiNroOperacion() {
		return piNroOperacion;
	}
	public void setPiNroOperacion(String piNroOperacion) {
		this.piNroOperacion = piNroOperacion;
	}
	public String getPiLineasActivas() {
		return piLineasActivas;
	}
	public void setPiLineasActivas(String piLineasActivas) {
		this.piLineasActivas = piLineasActivas;
	}
	public String getPiLineaEvaluada() {
		return piLineaEvaluada;
	}
	public void setPiLineaEvaluada(String piLineaEvaluada) {
		this.piLineaEvaluada = piLineaEvaluada;
	}
	
	
	
}
