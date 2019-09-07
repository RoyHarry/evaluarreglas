package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;

public class ParametroPvuPlsqlResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private int grupo;

	private int codigo;

	private String data;

	private String descripcionGrupo;

	private String descripcionData;

	private String sistema;

	private String version;

	private String tipoOperacion;

	public ParametroPvuPlsqlResponse() {
		super();
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescripcionGrupo() {
		return descripcionGrupo;
	}

	public void setDescripcionGrupo(String descripcionGrupo) {
		this.descripcionGrupo = descripcionGrupo;
	}

	public String getDescripcionData() {
		return descripcionData;
	}

	public void setDescripcionData(String descripcionData) {
		this.descripcionData = descripcionData;
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

	@Override
	public String toString() {
		return "ParametroResponse [grupo=" + grupo + ", codigo=" + codigo + ", data=" + data + ", descripcionGrupo="
				+ descripcionGrupo + ", descripcionData=" + descripcionData + ", sistema=" + sistema + ", version="
				+ version + ", tipoOperacion=" + tipoOperacion + "]";
	}

}
