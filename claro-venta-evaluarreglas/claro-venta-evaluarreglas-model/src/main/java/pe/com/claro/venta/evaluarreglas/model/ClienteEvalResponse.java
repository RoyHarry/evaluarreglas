package pe.com.claro.venta.evaluarreglas.model;

import java.util.List;

import pe.com.claro.common.bean.BodyResponse;

public class ClienteEvalResponse {
	private List<DatosResponse> listDatosRequest;
	private List<?> listDetalleRequest;
	private String poCodigoRespuesta;
	private String poMensajeRespuesta;
	private String idTransaccion;
	
	public ClienteEvalResponse() {		
	}
	
	public ClienteEvalResponse(BodyResponse response) {
		super();		
	}
	
	public List<DatosResponse> getListDatosRequest() {
		return listDatosRequest;
	}
	public void setListDatosRequest(List<DatosResponse> listDatosRequest) {
		this.listDatosRequest = listDatosRequest;
	}
	public List<?> getListDetalleRequest() {
		return listDetalleRequest;
	}
	public void setListDetalleRequest(List<?> listDetalleRequest) {
		this.listDetalleRequest = listDetalleRequest;
	}
	public String getPoCodigoRespuesta() {
		return poCodigoRespuesta;
	}
	public void setPoCodigoRespuesta(String poCodigoRespuesta) {
		this.poCodigoRespuesta = poCodigoRespuesta;
	}
	public String getPoMensajeRespuesta() {
		return poMensajeRespuesta;
	}
	public void setPoMensajeRespuesta(String poMensajeRespuesta) {
		this.poMensajeRespuesta = poMensajeRespuesta;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	@Override
	public String toString() {
		return "ClienteEvalResponse [listDatosRequest=" + listDatosRequest + ", listDetalleRequest="
				+ listDetalleRequest + ", poCodigoRespuesta=" + poCodigoRespuesta + ", poMensajeRespuesta="
				+ poMensajeRespuesta + ", idTransaccion=" + idTransaccion + "]";
	}
	
	
}
