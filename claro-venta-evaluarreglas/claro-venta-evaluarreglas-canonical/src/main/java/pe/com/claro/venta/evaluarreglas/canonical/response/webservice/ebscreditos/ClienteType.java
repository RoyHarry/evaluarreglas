package pe.com.claro.venta.evaluarreglas.canonical.response.webservice.ebscreditos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Clase Bean para el objeto del Cliente del Response General del servicio
 * @author everis
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numeroOperacion;
	private String accion;
	private String lcDisponible;
	private String tipoCliente;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombres;
	private String codigoModelo;
	private String regsCalificacion;
	private String direcciones;
	private String limiteCredito;
	private String claseCliente;
	private String nvLcMaximo;
	private String nvTotalCargosFijos;
	private String explicacion;
	private String score;
	private String creditScore;
	private String tipoProducto;
	private String lineaCreditoSistema;
	private String edad;
	private String ingresoOLC;
	private String tipoTarjeta;
	private String topDiesMil;
	private String antiguedadLaboral;
	private String numeroDocumento;
	private String razones;
	private String analisis;
	private String scoreRankinOperativo;
	private String numeroEntidadesRankinOperativo;
	private String puntaje;
	private String limiteCreditoDataCredito;
	private String limiteCreditoBaseExterno;
	private String limiteCreditoClaro;
	private String fechaNacimiento;
	private String respuesta;
	private String fechaConsulta;
	
	public ClienteType() { }

	/**
	 * @return the numeroOperacion
	 */
	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	/**
	 * @param numeroOperacion the numeroOperacion to set
	 */
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return the lcDisponible
	 */
	public String getLcDisponible() {
		return lcDisponible;
	}

	/**
	 * @param lcDisponible the lcDisponible to set
	 */
	public void setLcDisponible(String lcDisponible) {
		this.lcDisponible = lcDisponible;
	}

	/**
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente the tipoCliente to set
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return the codigoModelo
	 */
	public String getCodigoModelo() {
		return codigoModelo;
	}

	/**
	 * @param codigoModelo the codigoModelo to set
	 */
	public void setCodigoModelo(String codigoModelo) {
		this.codigoModelo = codigoModelo;
	}

	/**
	 * @return the regsCalificacion
	 */
	public String getRegsCalificacion() {
		return regsCalificacion;
	}

	/**
	 * @param regsCalificacion the regsCalificacion to set
	 */
	public void setRegsCalificacion(String regsCalificacion) {
		this.regsCalificacion = regsCalificacion;
	}

	/**
	 * @return the direcciones
	 */
	public String getDirecciones() {
		return direcciones;
	}

	/**
	 * @param direcciones the direcciones to set
	 */
	public void setDirecciones(String direcciones) {
		this.direcciones = direcciones;
	}

	/**
	 * @return the limiteCredito
	 */
	public String getLimiteCredito() {
		return limiteCredito;
	}

	/**
	 * @param limiteCredito the limiteCredito to set
	 */
	public void setLimiteCredito(String limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	/**
	 * @return the claseCliente
	 */
	public String getClaseCliente() {
		return claseCliente;
	}

	/**
	 * @param claseCliente the claseCliente to set
	 */
	public void setClaseCliente(String claseCliente) {
		this.claseCliente = claseCliente;
	}

	/**
	 * @return the nvLcMaximo
	 */
	public String getNvLcMaximo() {
		return nvLcMaximo;
	}

	/**
	 * @param nvLcMaximo the nvLcMaximo to set
	 */
	public void setNvLcMaximo(String nvLcMaximo) {
		this.nvLcMaximo = nvLcMaximo;
	}

	/**
	 * @return the nvTotalCargosFijos
	 */
	public String getNvTotalCargosFijos() {
		return nvTotalCargosFijos;
	}

	/**
	 * @param nvTotalCargosFijos the nvTotalCargosFijos to set
	 */
	public void setNvTotalCargosFijos(String nvTotalCargosFijos) {
		this.nvTotalCargosFijos = nvTotalCargosFijos;
	}

	/**
	 * @return the explicacion
	 */
	public String getExplicacion() {
		return explicacion;
	}

	/**
	 * @param explicacion the explicacion to set
	 */
	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}

	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}

	/**
	 * @return the creditScore
	 */
	public String getCreditScore() {
		return creditScore;
	}

	/**
	 * @param creditScore the creditScore to set
	 */
	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}

	/**
	 * @return the tipoProducto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * @param tipoProducto the tipoProducto to set
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	/**
	 * @return the lineaCreditoSistema
	 */
	public String getLineaCreditoSistema() {
		return lineaCreditoSistema;
	}

	/**
	 * @param lineaCreditoSistema the lineaCreditoSistema to set
	 */
	public void setLineaCreditoSistema(String lineaCreditoSistema) {
		this.lineaCreditoSistema = lineaCreditoSistema;
	}

	/**
	 * @return the edad
	 */
	public String getEdad() {
		return edad;
	}

	/**
	 * @param edad the edad to set
	 */
	public void setEdad(String edad) {
		this.edad = edad;
	}

	/**
	 * @return the ingresoOLC
	 */
	public String getIngresoOLC() {
		return ingresoOLC;
	}

	/**
	 * @param ingresoOLC the ingresoOLC to set
	 */
	public void setIngresoOLC(String ingresoOLC) {
		this.ingresoOLC = ingresoOLC;
	}

	/**
	 * @return the tipoTarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * @param tipoTarjeta the tipoTarjeta to set
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * @return the topDiesMil
	 */
	public String getTopDiesMil() {
		return topDiesMil;
	}

	/**
	 * @param topDiesMil the topDiesMil to set
	 */
	public void setTopDiesMil(String topDiesMil) {
		this.topDiesMil = topDiesMil;
	}

	/**
	 * @return the antiguedadLaboral
	 */
	public String getAntiguedadLaboral() {
		return antiguedadLaboral;
	}

	/**
	 * @param antiguedadLaboral the antiguedadLaboral to set
	 */
	public void setAntiguedadLaboral(String antiguedadLaboral) {
		this.antiguedadLaboral = antiguedadLaboral;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the razones
	 */
	public String getRazones() {
		return razones;
	}

	/**
	 * @param razones the razones to set
	 */
	public void setRazones(String razones) {
		this.razones = razones;
	}

	/**
	 * @return the analisis
	 */
	public String getAnalisis() {
		return analisis;
	}

	/**
	 * @param analisis the analisis to set
	 */
	public void setAnalisis(String analisis) {
		this.analisis = analisis;
	}

	/**
	 * @return the scoreRankinOperativo
	 */
	public String getScoreRankinOperativo() {
		return scoreRankinOperativo;
	}

	/**
	 * @param scoreRankinOperativo the scoreRankinOperativo to set
	 */
	public void setScoreRankinOperativo(String scoreRankinOperativo) {
		this.scoreRankinOperativo = scoreRankinOperativo;
	}

	/**
	 * @return the numeroEntidadesRankinOperativo
	 */
	public String getNumeroEntidadesRankinOperativo() {
		return numeroEntidadesRankinOperativo;
	}

	/**
	 * @param numeroEntidadesRankinOperativo the numeroEntidadesRankinOperativo to set
	 */
	public void setNumeroEntidadesRankinOperativo(String numeroEntidadesRankinOperativo) {
		this.numeroEntidadesRankinOperativo = numeroEntidadesRankinOperativo;
	}

	/**
	 * @return the puntaje
	 */
	public String getPuntaje() {
		return puntaje;
	}

	/**
	 * @param puntaje the puntaje to set
	 */
	public void setPuntaje(String puntaje) {
		this.puntaje = puntaje;
	}

	/**
	 * @return the limiteCreditoDataCredito
	 */
	public String getLimiteCreditoDataCredito() {
		return limiteCreditoDataCredito;
	}

	/**
	 * @param limiteCreditoDataCredito the limiteCreditoDataCredito to set
	 */
	public void setLimiteCreditoDataCredito(String limiteCreditoDataCredito) {
		this.limiteCreditoDataCredito = limiteCreditoDataCredito;
	}

	/**
	 * @return the limiteCreditoBaseExterno
	 */
	public String getLimiteCreditoBaseExterno() {
		return limiteCreditoBaseExterno;
	}

	/**
	 * @param limiteCreditoBaseExterno the limiteCreditoBaseExterno to set
	 */
	public void setLimiteCreditoBaseExterno(String limiteCreditoBaseExterno) {
		this.limiteCreditoBaseExterno = limiteCreditoBaseExterno;
	}

	/**
	 * @return the limiteCreditoClaro
	 */
	public String getLimiteCreditoClaro() {
		return limiteCreditoClaro;
	}

	/**
	 * @param limiteCreditoClaro the limiteCreditoClaro to set
	 */
	public void setLimiteCreditoClaro(String limiteCreditoClaro) {
		this.limiteCreditoClaro = limiteCreditoClaro;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the fechaConsulta
	 */
	public String getFechaConsulta() {
		return fechaConsulta;
	}

	/**
	 * @param fechaConsulta the fechaConsulta to set
	 */
	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClienteType [numeroOperacion=" + numeroOperacion + ", accion=" + accion + ", lcDisponible="
				+ lcDisponible + ", tipoCliente=" + tipoCliente + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", nombres=" + nombres + ", codigoModelo=" + codigoModelo
				+ ", regsCalificacion=" + regsCalificacion + ", direcciones=" + direcciones + ", limiteCredito="
				+ limiteCredito + ", claseCliente=" + claseCliente + ", nvLcMaximo=" + nvLcMaximo
				+ ", nvTotalCargosFijos=" + nvTotalCargosFijos + ", explicacion=" + explicacion + ", score=" + score
				+ ", creditScore=" + creditScore + ", tipoProducto=" + tipoProducto + ", lineaCreditoSistema="
				+ lineaCreditoSistema + ", edad=" + edad + ", ingresoOLC=" + ingresoOLC + ", tipoTarjeta=" + tipoTarjeta
				+ ", topDiesMil=" + topDiesMil + ", antiguedadLaboral=" + antiguedadLaboral + ", numeroDocumento="
				+ numeroDocumento + ", razones=" + razones + ", analisis=" + analisis + ", scoreRankinOperativo="
				+ scoreRankinOperativo + ", numeroEntidadesRankinOperativo=" + numeroEntidadesRankinOperativo
				+ ", puntaje=" + puntaje + ", limiteCreditoDataCredito=" + limiteCreditoDataCredito
				+ ", limiteCreditoBaseExterno=" + limiteCreditoBaseExterno + ", limiteCreditoClaro="
				+ limiteCreditoClaro + ", fechaNacimiento=" + fechaNacimiento + ", respuesta=" + respuesta
				+ ", fechaConsulta=" + fechaConsulta + "]";
	}
}
