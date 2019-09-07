package pe.com.claro.venta.evaluarreglas.canonical.response.webservice.parametro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.claro.common.bean.BodyResponse;

public class ParametrosResponseWs implements Serializable {

	private static final long serialVersionUID = 1L;

	private BodyResponse header;
	private List<ParametroResponseWs> data;

	public ParametrosResponseWs() {
		super();
		header = new BodyResponse();
		data = new ArrayList<>();
	}

	public BodyResponse getHeader() {
		return header;
	}

	public void setHeader(BodyResponse header) {
		this.header = header;
	}

	public List<ParametroResponseWs> getData() {
		return data;
	}

	public void setData(List<ParametroResponseWs> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PlanWsResponse [header=" + header + ", data=" + data + "]";
	}

}
