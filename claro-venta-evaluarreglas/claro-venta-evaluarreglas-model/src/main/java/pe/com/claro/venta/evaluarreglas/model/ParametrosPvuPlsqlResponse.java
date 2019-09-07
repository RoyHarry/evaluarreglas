package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;
import java.util.List;

public class ParametrosPvuPlsqlResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	List<ParametroPvuPlsqlResponse> parametros;

	public List<ParametroPvuPlsqlResponse> getParametros() {
		return parametros;
	}

	public void setParametros(List<ParametroPvuPlsqlResponse> parametros) {
		this.parametros = parametros;
	}

	@Override
	public String toString() {
		return "PlanesPvuPlsqlResponse [parametros=" + parametros + "]";
	}

}
