package pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes;

public class CuotaWS {
	
	private int cantidad;
	private Double porcentajeInicial;
	private PvuConTipoCuotaResponseWS pvuConTipoCuotaResponseWS;
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPorcentajeInicial() {
		return porcentajeInicial;
	}
	public void setPorcentajeInicial(Double porcentajeInicial) {
		this.porcentajeInicial = porcentajeInicial;
	}
	public PvuConTipoCuotaResponseWS getPvuConTipoCuotaResponseWS() {
		return pvuConTipoCuotaResponseWS;
	}
	public void setPvuConTipoCuotaResponseWS(PvuConTipoCuotaResponseWS pvuConTipoCuotaResponseWS) {
		this.pvuConTipoCuotaResponseWS = pvuConTipoCuotaResponseWS;
	}
	  	  
}
