package pe.com.claro.common.bean;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.HttpHeaders;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.util.ClaroUtil;

@Entity
public class HeaderRequest {

	@NotNull
	private String idTransaccion;
	@NotNull
	private String msgid;
	
	private Date timestamp;
	private String userId;
	@NotNull
	private String accept;
	@NotNull
	private String nombreAplicacion;		
	
	public HeaderRequest(String idTransaccion, String msgid, Date timestamp, String userId, String accept, String nombreAplicacion) {
		super();
		this.idTransaccion = idTransaccion;
		this.msgid = msgid;
		this.timestamp = timestamp;
		this.userId = userId;
		this.accept = accept;
		this.nombreAplicacion = nombreAplicacion;
	}



	public HeaderRequest(HttpHeaders httpHeaders) {
		super();
		this.idTransaccion = httpHeaders.getRequestHeader(Constantes.IDTRANSACCION) != null
				? httpHeaders.getRequestHeader(Constantes.IDTRANSACCION).get(Constantes.CERO) : Constantes.VACIO;
		this.msgid = httpHeaders.getRequestHeader(Constantes.MSGID) != null
				? httpHeaders.getRequestHeader(Constantes.MSGID).get(Constantes.CERO) : Constantes.VACIO;
		this.userId = httpHeaders.getRequestHeader(Constantes.USERID) != null
				? httpHeaders.getRequestHeader(Constantes.USERID).get(Constantes.CERO) : Constantes.VACIO;
		this.accept = httpHeaders.getRequestHeader(Constantes.ACCEPT) != null
				? httpHeaders.getRequestHeader(Constantes.ACCEPT).get(Constantes.CERO) : Constantes.VACIO;
		this.nombreAplicacion = httpHeaders.getRequestHeader(Constantes.ACCEPT) != null
						? httpHeaders.getRequestHeader(Constantes.ACCEPT).get(Constantes.CERO) : Constantes.VACIO;
		Calendar a = ClaroUtil.toCalendar(httpHeaders.getRequestHeader(Constantes.TIMESTAMP) != null
				? httpHeaders.getRequestHeader(Constantes.TIMESTAMP).get(Constantes.CERO) : Constantes.VACIO);
		if (a != null)
			this.timestamp = a.getTime();
	}
	
	

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}

	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}

	@Override
	public String toString() {
		return "HeaderRequest [idTransaccion=" + idTransaccion + ", msgid=" + msgid + ", timestamp=" + timestamp
				+ ", userId=" + userId + ", accept=" + accept + ", nombreAplicacion=" + nombreAplicacion + "]";
	}



	

}
