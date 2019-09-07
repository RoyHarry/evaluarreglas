package pe.com.claro.venta.evaluarreglas.canonical.request.webservice.parametro;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParametroRequest{

	private String sistema;
	private String version;
	private String tipoOperacion;
	private String grupo;
	private String codigo;

	public ParametroRequest() {
		super();
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "ParametroRequest [sistema=" + sistema + ", version=" + version + ", tipoOperacion=" + tipoOperacion
				+ ", grupo=" + grupo + ", codigo=" + codigo + "]";
	}

}
