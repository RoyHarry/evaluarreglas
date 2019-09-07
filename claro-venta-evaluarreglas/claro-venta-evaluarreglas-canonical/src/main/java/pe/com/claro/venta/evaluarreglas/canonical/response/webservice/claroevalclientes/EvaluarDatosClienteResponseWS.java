package pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes;

import java.util.List;

import pe.com.claro.common.bean.HeaderResponse;
import pe.com.claro.common.bean.ParametrosTypeWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.ebscreditos.ClienteType;

public class EvaluarDatosClienteResponseWS {
	  
    private HeaderResponse auditResponse;
      
    private PlanesTypesWS listaDetallePlanes;
      
    private ParametrosTypeWS listaResponseOpcional;
      
    private OfrecimientoWS ofrecimiento;
    
    private PvuConTipoCuotaResponseWS pvuConTipoCuotaResponseWS;   

	private List<ClienteType> cliente;
	
	private String buroConsultado;
    
	public HeaderResponse getAuditResponse() {
		return auditResponse;
	}

	public void setAuditResponse(HeaderResponse auditResponse) {
		this.auditResponse = auditResponse;
	}

	public PlanesTypesWS getListaDetallePlanes() {
		return listaDetallePlanes;
	}

	public void setListaDetallePlanes(PlanesTypesWS listaDetallePlanes) {
		this.listaDetallePlanes = listaDetallePlanes;
	}

	public ParametrosTypeWS getListaResponseOpcional() {
		return listaResponseOpcional;
	}

	public void setListaResponseOpcional(ParametrosTypeWS listaResponseOpcional) {
		this.listaResponseOpcional = listaResponseOpcional;
	}

	public OfrecimientoWS getOfrecimiento() {
		return ofrecimiento;
	}

	public void setOfrecimiento(OfrecimientoWS ofrecimiento) {
		this.ofrecimiento = ofrecimiento;
	}

	public PvuConTipoCuotaResponseWS getPvuConTipoCuotaResponseWS() {
		return pvuConTipoCuotaResponseWS;
	}

	public void setPvuConTipoCuotaResponseWS(PvuConTipoCuotaResponseWS pvuConTipoCuotaResponseWS) {
		this.pvuConTipoCuotaResponseWS = pvuConTipoCuotaResponseWS;
	}

	public List<ClienteType> getCliente() {
		return cliente;
	}

	public void setCliente(List<ClienteType> cliente) {
		this.cliente = cliente;
	}

	public String getBuroConsultado() {
		return buroConsultado;
	}

	public void setBuroConsultado(String buroConsultado) {
		this.buroConsultado = buroConsultado;
	}
	
}
