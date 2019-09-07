package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class BRMSBioMovilRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal brmsId;
	private String brmsIdTransaccion;
	private String brmsTipOperacion;
	private Date brmsFechaRegistro;
	private String brmsTipDocumento;
	private String brmsNroDocumento;
	private String brmsTipServicio;
	private BigDecimal cantidadDeAplicacionesRenta;
	private BigDecimal cantidadDeLineasAdicionalesRuc;
	private BigDecimal cantidadDeLineasMaximas;
	private BigDecimal cantidadDeLineasRenovaciones;
	private String capacidadDePago;
	private BigDecimal comportamientoConsolidado;
	private BigDecimal comportamientoDePagoC1;
	private String controlDeConsumo;
	private BigDecimal costoDeInstalacion;
	private Double costoTotalEquipos;
	private BigDecimal factorDeEndeudamiento;
	private BigDecimal factorDeRenovacion;
	private BigDecimal frecuenciaDeAplicacionMensual;
	private BigDecimal limiteDeCrecitoCobranza;
	private BigDecimal mesInicioRentas;
	private BigDecimal montoCfParaRuc;
	private BigDecimal montoDeGarantia;
	private BigDecimal montoTopeAutomatico;
	private BigDecimal precioDeVentaTotalEquipos;
	private String prioridadPublicar;
	private String exoneracionDeRentas;
	private String idValidator;
	private String validacionInternaClaro;
	private String publicar;
	private String restriccion;
	private String riesgoEnClaro;
	private String riesgoOferta;
	private String riesgoTotalEquipo;
	private String riesgoTotalReplegales;
	private String tipoDeAutonomiaCargoFijo;
	private String tipoDeCobro;
	private String tipoDeGarantia;
	private String mensajeWS;
	private BigDecimal solinGrupoSEC;
	private String autonomiaRenovacion;
	private String cantidadDePlanesPorProducto;
	private String facturacionPromedioClaro;
	private String facturacionPromedioProducto;
	private String tiempoDePermanencia;
	private String tipo;
	private String ofertaCasoEspecial;
	private String equipoCosto;
	private String equipoCuotas;
	private String equipoFormaDePago;
	private String equipoGama;
	private String equipoModelo;
	private String equipoMontoDeCuota;
	private String equipoPorcenCuotaInicial;
	private String equipoPrecioDeVenta;
	private String equipoTipoDeDeco;
	private String equipoTipoOperacionKit;
	private Date fechaEjecucion;
	private BigDecimal cantidadDeLineasActivas;
	private String modalidadCedente;
	private BigDecimal mesOperadorCedente;
	private BigDecimal montoCFSEC;
	private String tipoDeOperacion;
	private String buroConsultado;
	private String segmento;
	private String codigoPDV;
	private String deuda;
	private String comportamientoDePago;
	
	//Nuevas 7 variables
	private Double montoDeudaVencida;
	private Double montoDeudaCastigada;
	private Double montoDisputa;
	private Integer cantidadMontoDisputa;
	private Integer antiguedadMontoDisputa;
	private Double montoTotalDeuda;
	private Integer antiguedadDeuda;
	
	//Nuevas 7 otras variables al brms
	private Double montoPendienteCuotasSistema;
	private int cantidadPlanesCuotasPendientesSistema;
	private int cantidadMaximaCuotasPendientesSistema;
	private Double montoPendienteCuotasUltimasVentas;
	private int cantidadPlanesCuotasPendientesUltimasVentas;
	private int cantidadMaximaCuotasPendientesUltimasVentas;
	private int cantidadCuotasPendientes;
	private Double montoPendienteCuotas;
		
	public BigDecimal getBrmsId() {
		return brmsId;
	}
	public void setBrmsId(BigDecimal brmsId) {
		this.brmsId = brmsId;
	}
	public String getBrmsIdTransaccion() {
		return brmsIdTransaccion;
	}
	public void setBrmsIdTransaccion(String brmsIdTransaccion) {
		this.brmsIdTransaccion = brmsIdTransaccion;
	}
	public String getBrmsTipOperacion() {
		return brmsTipOperacion;
	}
	public void setBrmsTipOperacion(String brmsTipOperacion) {
		this.brmsTipOperacion = brmsTipOperacion;
	}
	public Date getBrmsFechaRegistro() {
		return brmsFechaRegistro;
	}
	public void setBrmsFechaRegistro(Date brmsFechaRegistro) {
		this.brmsFechaRegistro = brmsFechaRegistro;
	}
	public String getBrmsTipDocumento() {
		return brmsTipDocumento;
	}
	public void setBrmsTipDocumento(String brmsTipDocumento) {
		this.brmsTipDocumento = brmsTipDocumento;
	}
	public String getBrmsNroDocumento() {
		return brmsNroDocumento;
	}
	public void setBrmsNroDocumento(String brmsNroDocumento) {
		this.brmsNroDocumento = brmsNroDocumento;
	}
	public String getBrmsTipServicio() {
		return brmsTipServicio;
	}
	public void setBrmsTipServicio(String brmsTipServicio) {
		this.brmsTipServicio = brmsTipServicio;
	}
	public BigDecimal getCantidadDeAplicacionesRenta() {
		return cantidadDeAplicacionesRenta;
	}
	public void setCantidadDeAplicacionesRenta(BigDecimal cantidadDeAplicacionesRenta) {
		this.cantidadDeAplicacionesRenta = cantidadDeAplicacionesRenta;
	}
	public BigDecimal getCantidadDeLineasAdicionalesRuc() {
		return cantidadDeLineasAdicionalesRuc;
	}
	public void setCantidadDeLineasAdicionalesRuc(BigDecimal cantidadDeLineasAdicionalesRuc) {
		this.cantidadDeLineasAdicionalesRuc = cantidadDeLineasAdicionalesRuc;
	}
	public BigDecimal getCantidadDeLineasMaximas() {
		return cantidadDeLineasMaximas;
	}
	public void setCantidadDeLineasMaximas(BigDecimal cantidadDeLineasMaximas) {
		this.cantidadDeLineasMaximas = cantidadDeLineasMaximas;
	}
	public BigDecimal getCantidadDeLineasRenovaciones() {
		return cantidadDeLineasRenovaciones;
	}
	public void setCantidadDeLineasRenovaciones(BigDecimal cantidadDeLineasRenovaciones) {
		this.cantidadDeLineasRenovaciones = cantidadDeLineasRenovaciones;
	}
	public String getCapacidadDePago() {
		return capacidadDePago;
	}
	public void setCapacidadDePago(String capacidadDePago) {
		this.capacidadDePago = capacidadDePago;
	}
	public BigDecimal getComportamientoConsolidado() {
		return comportamientoConsolidado;
	}
	public void setComportamientoConsolidado(BigDecimal comportamientoConsolidado) {
		this.comportamientoConsolidado = comportamientoConsolidado;
	}
	public BigDecimal getComportamientoDePagoC1() {
		return comportamientoDePagoC1;
	}
	public void setComportamientoDePagoC1(BigDecimal comportamientoDePagoC1) {
		this.comportamientoDePagoC1 = comportamientoDePagoC1;
	}
	public String getControlDeConsumo() {
		return controlDeConsumo;
	}
	public void setControlDeConsumo(String controlDeConsumo) {
		this.controlDeConsumo = controlDeConsumo;
	}
	public BigDecimal getCostoDeInstalacion() {
		return costoDeInstalacion;
	}
	public void setCostoDeInstalacion(BigDecimal costoDeInstalacion) {
		this.costoDeInstalacion = costoDeInstalacion;
	}
	public Double getCostoTotalEquipos() {
		return costoTotalEquipos;
	}
	public void setCostoTotalEquipos(Double costoTotalEquipos) {
		this.costoTotalEquipos = costoTotalEquipos;
	}
	public BigDecimal getFactorDeEndeudamiento() {
		return factorDeEndeudamiento;
	}
	public void setFactorDeEndeudamiento(BigDecimal factorDeEndeudamiento) {
		this.factorDeEndeudamiento = factorDeEndeudamiento;
	}
	public BigDecimal getFactorDeRenovacion() {
		return factorDeRenovacion;
	}
	public void setFactorDeRenovacion(BigDecimal factorDeRenovacion) {
		this.factorDeRenovacion = factorDeRenovacion;
	}
	public BigDecimal getFrecuenciaDeAplicacionMensual() {
		return frecuenciaDeAplicacionMensual;
	}
	public void setFrecuenciaDeAplicacionMensual(BigDecimal frecuenciaDeAplicacionMensual) {
		this.frecuenciaDeAplicacionMensual = frecuenciaDeAplicacionMensual;
	}
	public BigDecimal getLimiteDeCrecitoCobranza() {
		return limiteDeCrecitoCobranza;
	}
	public void setLimiteDeCrecitoCobranza(BigDecimal limiteDeCrecitoCobranza) {
		this.limiteDeCrecitoCobranza = limiteDeCrecitoCobranza;
	}
	public BigDecimal getMesInicioRentas() {
		return mesInicioRentas;
	}
	public void setMesInicioRentas(BigDecimal mesInicioRentas) {
		this.mesInicioRentas = mesInicioRentas;
	}
	public BigDecimal getMontoCfParaRuc() {
		return montoCfParaRuc;
	}
	public void setMontoCfParaRuc(BigDecimal montoCfParaRuc) {
		this.montoCfParaRuc = montoCfParaRuc;
	}
	public BigDecimal getMontoDeGarantia() {
		return montoDeGarantia;
	}
	public void setMontoDeGarantia(BigDecimal montoDeGarantia) {
		this.montoDeGarantia = montoDeGarantia;
	}
	public BigDecimal getMontoTopeAutomatico() {
		return montoTopeAutomatico;
	}
	public void setMontoTopeAutomatico(BigDecimal montoTopeAutomatico) {
		this.montoTopeAutomatico = montoTopeAutomatico;
	}
	public BigDecimal getPrecioDeVentaTotalEquipos() {
		return precioDeVentaTotalEquipos;
	}
	public void setPrecioDeVentaTotalEquipos(BigDecimal precioDeVentaTotalEquipos) {
		this.precioDeVentaTotalEquipos = precioDeVentaTotalEquipos;
	}
	public String getPrioridadPublicar() {
		return prioridadPublicar;
	}
	public void setPrioridadPublicar(String prioridadPublicar) {
		this.prioridadPublicar = prioridadPublicar;
	}
	public String getExoneracionDeRentas() {
		return exoneracionDeRentas;
	}
	public void setExoneracionDeRentas(String exoneracionDeRentas) {
		this.exoneracionDeRentas = exoneracionDeRentas;
	}
	public String getIdValidator() {
		return idValidator;
	}
	public void setIdValidator(String idValidator) {
		this.idValidator = idValidator;
	}
	public String getValidacionInternaClaro() {
		return validacionInternaClaro;
	}
	public void setValidacionInternaClaro(String validacionInternaClaro) {
		this.validacionInternaClaro = validacionInternaClaro;
	}
	public String getPublicar() {
		return publicar;
	}
	public void setPublicar(String publicar) {
		this.publicar = publicar;
	}
	public String getRestriccion() {
		return restriccion;
	}
	public void setRestriccion(String restriccion) {
		this.restriccion = restriccion;
	}
	public String getRiesgoEnClaro() {
		return riesgoEnClaro;
	}
	public void setRiesgoEnClaro(String riesgoEnClaro) {
		this.riesgoEnClaro = riesgoEnClaro;
	}
	public String getRiesgoOferta() {
		return riesgoOferta;
	}
	public void setRiesgoOferta(String riesgoOferta) {
		this.riesgoOferta = riesgoOferta;
	}
	public String getRiesgoTotalEquipo() {
		return riesgoTotalEquipo;
	}
	public void setRiesgoTotalEquipo(String riesgoTotalEquipo) {
		this.riesgoTotalEquipo = riesgoTotalEquipo;
	}
	public String getRiesgoTotalReplegales() {
		return riesgoTotalReplegales;
	}
	public void setRiesgoTotalReplegales(String riesgoTotalReplegales) {
		this.riesgoTotalReplegales = riesgoTotalReplegales;
	}
	public String getTipoDeAutonomiaCargoFijo() {
		return tipoDeAutonomiaCargoFijo;
	}
	public void setTipoDeAutonomiaCargoFijo(String tipoDeAutonomiaCargoFijo) {
		this.tipoDeAutonomiaCargoFijo = tipoDeAutonomiaCargoFijo;
	}
	public String getTipoDeCobro() {
		return tipoDeCobro;
	}
	public void setTipoDeCobro(String tipoDeCobro) {
		this.tipoDeCobro = tipoDeCobro;
	}
	public String getTipoDeGarantia() {
		return tipoDeGarantia;
	}
	public void setTipoDeGarantia(String tipoDeGarantia) {
		this.tipoDeGarantia = tipoDeGarantia;
	}
	public String getMensajeWS() {
		return mensajeWS;
	}
	public void setMensajeWS(String mensajeWS) {
		this.mensajeWS = mensajeWS;
	}
	public BigDecimal getSolinGrupoSEC() {
		return solinGrupoSEC;
	}
	public void setSolinGrupoSEC(BigDecimal solinGrupoSEC) {
		this.solinGrupoSEC = solinGrupoSEC;
	}
	public String getAutonomiaRenovacion() {
		return autonomiaRenovacion;
	}
	public void setAutonomiaRenovacion(String autonomiaRenovacion) {
		this.autonomiaRenovacion = autonomiaRenovacion;
	}
	public String getCantidadDePlanesPorProducto() {
		return cantidadDePlanesPorProducto;
	}
	public void setCantidadDePlanesPorProducto(String cantidadDePlanesPorProducto) {
		this.cantidadDePlanesPorProducto = cantidadDePlanesPorProducto;
	}
	public String getFacturacionPromedioClaro() {
		return facturacionPromedioClaro;
	}
	public void setFacturacionPromedioClaro(String facturacionPromedioClaro) {
		this.facturacionPromedioClaro = facturacionPromedioClaro;
	}
	public String getFacturacionPromedioProducto() {
		return facturacionPromedioProducto;
	}
	public void setFacturacionPromedioProducto(String facturacionPromedioProducto) {
		this.facturacionPromedioProducto = facturacionPromedioProducto;
	}
	public String getTiempoDePermanencia() {
		return tiempoDePermanencia;
	}
	public void setTiempoDePermanencia(String tiempoDePermanencia) {
		this.tiempoDePermanencia = tiempoDePermanencia;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getOfertaCasoEspecial() {
		return ofertaCasoEspecial;
	}
	public void setOfertaCasoEspecial(String ofertaCasoEspecial) {
		this.ofertaCasoEspecial = ofertaCasoEspecial;
	}
	public String getEquipoCosto() {
		return equipoCosto;
	}
	public void setEquipoCosto(String equipoCosto) {
		this.equipoCosto = equipoCosto;
	}
	public String getEquipoCuotas() {
		return equipoCuotas;
	}
	public void setEquipoCuotas(String equipoCuotas) {
		this.equipoCuotas = equipoCuotas;
	}
	public String getEquipoFormaDePago() {
		return equipoFormaDePago;
	}
	public void setEquipoFormaDePago(String equipoFormaDePago) {
		this.equipoFormaDePago = equipoFormaDePago;
	}
	public String getEquipoGama() {
		return equipoGama;
	}
	public void setEquipoGama(String equipoGama) {
		this.equipoGama = equipoGama;
	}
	public String getEquipoModelo() {
		return equipoModelo;
	}
	public void setEquipoModelo(String equipoModelo) {
		this.equipoModelo = equipoModelo;
	}
	public String getEquipoMontoDeCuota() {
		return equipoMontoDeCuota;
	}
	public void setEquipoMontoDeCuota(String equipoMontoDeCuota) {
		this.equipoMontoDeCuota = equipoMontoDeCuota;
	}
	public String getEquipoPorcenCuotaInicial() {
		return equipoPorcenCuotaInicial;
	}
	public void setEquipoPorcenCuotaInicial(String equipoPorcenCuotaInicial) {
		this.equipoPorcenCuotaInicial = equipoPorcenCuotaInicial;
	}
	public String getEquipoPrecioDeVenta() {
		return equipoPrecioDeVenta;
	}
	public void setEquipoPrecioDeVenta(String equipoPrecioDeVenta) {
		this.equipoPrecioDeVenta = equipoPrecioDeVenta;
	}
	public String getEquipoTipoDeDeco() {
		return equipoTipoDeDeco;
	}
	public void setEquipoTipoDeDeco(String equipoTipoDeDeco) {
		this.equipoTipoDeDeco = equipoTipoDeDeco;
	}
	public String getEquipoTipoOperacionKit() {
		return equipoTipoOperacionKit;
	}
	public void setEquipoTipoOperacionKit(String equipoTipoOperacionKit) {
		this.equipoTipoOperacionKit = equipoTipoOperacionKit;
	}
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}
	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
	public BigDecimal getCantidadDeLineasActivas() {
		return cantidadDeLineasActivas;
	}
	public void setCantidadDeLineasActivas(BigDecimal cantidadDeLineasActivas) {
		this.cantidadDeLineasActivas = cantidadDeLineasActivas;
	}
	public String getModalidadCedente() {
		return modalidadCedente;
	}
	public void setModalidadCedente(String modalidadCedente) {
		this.modalidadCedente = modalidadCedente;
	}
	public BigDecimal getMesOperadorCedente() {
		return mesOperadorCedente;
	}
	public void setMesOperadorCedente(BigDecimal mesOperadorCedente) {
		this.mesOperadorCedente = mesOperadorCedente;
	}
	public BigDecimal getMontoCFSEC() {
		return montoCFSEC;
	}
	public void setMontoCFSEC(BigDecimal montoCFSEC) {
		this.montoCFSEC = montoCFSEC;
	}
	public String getTipoDeOperacion() {
		return tipoDeOperacion;
	}
	public void setTipoDeOperacion(String tipoDeOperacion) {
		this.tipoDeOperacion = tipoDeOperacion;
	}
	public String getBuroConsultado() {
		return buroConsultado;
	}
	public void setBuroConsultado(String buroConsultado) {
		this.buroConsultado = buroConsultado;
	}	
	public String getSegmento() {
		return segmento;
	}
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	public String getCodigoPDV() {
		return codigoPDV;
	}
	public void setCodigoPDV(String codigoPDV) {
		this.codigoPDV = codigoPDV;
	}
	public String getDeuda() {
		return deuda;
	}
	public void setDeuda(String deuda) {
		this.deuda = deuda;
	}
	public Double getMontoDeudaVencida() {
		return montoDeudaVencida;
	}
	public void setMontoDeudaVencida(Double montoDeudaVencida) {
		this.montoDeudaVencida = montoDeudaVencida;
	}
	public Double getMontoDeudaCastigada() {
		return montoDeudaCastigada;
	}
	public void setMontoDeudaCastigada(Double montoDeudaCastigada) {
		this.montoDeudaCastigada = montoDeudaCastigada;
	}
	public Double getMontoDisputa() {
		return montoDisputa;
	}
	public void setMontoDisputa(Double montoDisputa) {
		this.montoDisputa = montoDisputa;
	}
	public Integer getCantidadMontoDisputa() {
		return cantidadMontoDisputa;
	}
	public void setCantidadMontoDisputa(Integer cantidadMontoDisputa) {
		this.cantidadMontoDisputa = cantidadMontoDisputa;
	}
	public Integer getAntiguedadMontoDisputa() {
		return antiguedadMontoDisputa;
	}
	public void setAntiguedadMontoDisputa(Integer antiguedadMontoDisputa) {
		this.antiguedadMontoDisputa = antiguedadMontoDisputa;
	}
	public Double getMontoTotalDeuda() {
		return montoTotalDeuda;
	}
	public void setMontoTotalDeuda(Double montoTotalDeuda) {
		this.montoTotalDeuda = montoTotalDeuda;
	}
	public Integer getAntiguedadDeuda() {
		return antiguedadDeuda;
	}
	public void setAntiguedadDeuda(Integer antiguedadDeuda) {
		this.antiguedadDeuda = antiguedadDeuda;
	}
	public Double getMontoPendienteCuotasSistema() {
		return montoPendienteCuotasSistema;
	}
	public void setMontoPendienteCuotasSistema(Double montoPendienteCuotasSistema) {
		this.montoPendienteCuotasSistema = montoPendienteCuotasSistema;
	}
	public int getCantidadPlanesCuotasPendientesSistema() {
		return cantidadPlanesCuotasPendientesSistema;
	}
	public void setCantidadPlanesCuotasPendientesSistema(int cantidadPlanesCuotasPendientesSistema) {
		this.cantidadPlanesCuotasPendientesSistema = cantidadPlanesCuotasPendientesSistema;
	}
	public int getCantidadMaximaCuotasPendientesSistema() {
		return cantidadMaximaCuotasPendientesSistema;
	}
	public void setCantidadMaximaCuotasPendientesSistema(int cantidadMaximaCuotasPendientesSistema) {
		this.cantidadMaximaCuotasPendientesSistema = cantidadMaximaCuotasPendientesSistema;
	}
	public Double getMontoPendienteCuotasUltimasVentas() {
		return montoPendienteCuotasUltimasVentas;
	}
	public void setMontoPendienteCuotasUltimasVentas(Double montoPendienteCuotasUltimasVentas) {
		this.montoPendienteCuotasUltimasVentas = montoPendienteCuotasUltimasVentas;
	}
	public int getCantidadPlanesCuotasPendientesUltimasVentas() {
		return cantidadPlanesCuotasPendientesUltimasVentas;
	}
	public void setCantidadPlanesCuotasPendientesUltimasVentas(int cantidadPlanesCuotasPendientesUltimasVentas) {
		this.cantidadPlanesCuotasPendientesUltimasVentas = cantidadPlanesCuotasPendientesUltimasVentas;
	}
	public int getCantidadMaximaCuotasPendientesUltimasVentas() {
		return cantidadMaximaCuotasPendientesUltimasVentas;
	}
	public void setCantidadMaximaCuotasPendientesUltimasVentas(int cantidadMaximaCuotasPendientesUltimasVentas) {
		this.cantidadMaximaCuotasPendientesUltimasVentas = cantidadMaximaCuotasPendientesUltimasVentas;
	}
	public int getCantidadCuotasPendientes() {
		return cantidadCuotasPendientes;
	}
	public void setCantidadCuotasPendientes(int cantidadCuotasPendientes) {
		this.cantidadCuotasPendientes = cantidadCuotasPendientes;
	}
	public Double getMontoPendienteCuotas() {
		return montoPendienteCuotas;
	}
	public void setMontoPendienteCuotas(Double montoPendienteCuotas) {
		this.montoPendienteCuotas = montoPendienteCuotas;
	}
	public String getComportamientoDePago() {
		return comportamientoDePago;
	}
	public void setComportamientoDePago(String comportamientoDePago) {
		this.comportamientoDePago = comportamientoDePago;
	}
	
	
}
