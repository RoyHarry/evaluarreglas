package pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;

public class ClaroEvalClienteAuxiliarBeanWS {

	private Integer mesOperadorCedente;
	private String buroConsultado;
	private Integer cantidadDeLineasActivas;
	private Integer cantidadDePlanesPorProducto;
	private Integer comportamientoDePago;
	private BigDecimal edad;
	private BigDecimal creditScore;
	private String riesgo;
	private Double facturacionPromedioClaro;
	private Double facturacionPromedioProducto;
	private Double limiteDeCreditoDisponible;
	private String sexo;
	private Integer tiempoDePermanencia;
	private String tipoCliente;
	private XMLGregorianCalendar  fechaEjecucion;
	private String calidadVendedor;
	private String canal;
	private String codigo;
	private String descipcion;
	private String departamento;
	private String distrito;
	private String provincia;
	private String region;
	private String tipoCampana;
	private Integer cantidadLineasSEC;
	private String controlDeConsumo;
	private String modalidadCedente;
	private Double montoCFSEC;
	private String operadorCedente;
	private Double cargoFijo;
	private String descripcionPlanSolicitado;
	private String grupoServicioPlanSolicOf;
	private String nombreServicioPlanSolicOf;
	private String plazoDeAcuerdoOf;
	private String tipoDeProducto;
	private String tipoDeOperacion;
	private String deuda;
	private String costoEquipo;	
	private String modeloEquipo;
	private double precioVenta;
	private String riesgoClaro;
	private Integer cuotaInicial;
	private Float montoCuota;
	private Double montoCuotaComercial;
	private Double montoCuotaInicialComercial;
	private String segmentoCliente;
	private String formaDePago;
	
	//Nuevas 7 variables al brms
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
	
	//Renovacion
	private String planActualCargoFijo;
	private String planActualDescripcion;
	private String planActualCantidadCuotasPendientes;
	private String planActualMesesParaCubrirApadece;
	private String planActualMontoPendienteCuotas;
	private String planActualPlazoAcuerdo;
	private String planActualTiempoPermanencia;
	
	private String equipoPorcenCuotaInicial;
	private String brmsTipOperacion;
	
	public String getDeuda() {
		return deuda;
	}
	public void setDeuda(String deuda) {
		this.deuda = deuda;
	}
	public String getTipoDeOperacion() {
		return tipoDeOperacion;
	}
	public void setTipoDeOperacion(String tipoDeOperacion) {
		this.tipoDeOperacion = tipoDeOperacion;
	}
	public String getTipoDeProducto() {
		return tipoDeProducto;
	}
	public void setTipoDeProducto(String tipoDeProducto) {
		this.tipoDeProducto = tipoDeProducto;
	}
	public String getPlazoDeAcuerdoOf() {
		return plazoDeAcuerdoOf;
	}
	public void setPlazoDeAcuerdoOf(String plazoDeAcuerdoOf) {
		this.plazoDeAcuerdoOf = plazoDeAcuerdoOf;
	}
	public String getGrupoServicioPlanSolicOf() {
		return grupoServicioPlanSolicOf;
	}
	public void setGrupoServicioPlanSolicOf(String grupoServicioPlanSolicOf) {
		this.grupoServicioPlanSolicOf = grupoServicioPlanSolicOf;
	}
	public String getNombreServicioPlanSolicOf() {
		return nombreServicioPlanSolicOf;
	}
	public void setNombreServicioPlanSolicOf(String nombreServicioPlanSolicOf) {
		this.nombreServicioPlanSolicOf = nombreServicioPlanSolicOf;
	}
	public String getDescripcionPlanSolicitado() {
		return descripcionPlanSolicitado;
	}
	public void setDescripcionPlanSolicitado(String descripcionPlanSolicitado) {
		this.descripcionPlanSolicitado = descripcionPlanSolicitado;
	}
	public Double getCargoFijo() {
		return cargoFijo;
	}
	public void setCargoFijo(Double cargoFijo) {
		this.cargoFijo = cargoFijo;
	}
	public String getOperadorCedente() {
		return operadorCedente;
	}
	public void setOperadorCedente(String operadorCedente) {
		this.operadorCedente = operadorCedente;
	}
	public Double getMontoCFSEC() {
		return montoCFSEC;
	}
	public void setMontoCFSEC(Double montoCFSEC) {
		this.montoCFSEC = montoCFSEC;
	}
	public String getModalidadCedente() {
		return modalidadCedente;
	}
	public void setModalidadCedente(String modalidadCedente) {
		this.modalidadCedente = modalidadCedente;
	}
	public String getControlDeConsumo() {
		return controlDeConsumo;
	}
	public void setControlDeConsumo(String controlDeConsumo) {
		this.controlDeConsumo = controlDeConsumo;
	}
	public Integer getCantidadLineasSEC() {
		return cantidadLineasSEC;
	}
	public void setCantidadLineasSEC(Integer cantidadLineasSEC) {
		this.cantidadLineasSEC = cantidadLineasSEC;
	}
	public String getTipoCampana() {
		return tipoCampana;
	}
	public void setTipoCampana(String tipoCampana) {
		this.tipoCampana = tipoCampana;
	}
	public Integer getMesOperadorCedente() {
		return mesOperadorCedente;
	}
	public void setMesOperadorCedente(Integer mesOperadorCedente) {
		this.mesOperadorCedente = mesOperadorCedente;
	}
	public String getBuroConsultado() {
		return buroConsultado;
	}
	public void setBuroConsultado(String buroConsultado) {
		this.buroConsultado = buroConsultado;
	}
	public Integer getCantidadDeLineasActivas() {
		return cantidadDeLineasActivas;
	}
	public void setCantidadDeLineasActivas(Integer cantidadDeLineasActivas) {
		this.cantidadDeLineasActivas = cantidadDeLineasActivas;
	}
	public Integer getCantidadDePlanesPorProducto() {
		return cantidadDePlanesPorProducto;
	}
	public void setCantidadDePlanesPorProducto(Integer cantidadDePlanesPorProducto) {
		this.cantidadDePlanesPorProducto = cantidadDePlanesPorProducto;
	}
	public Integer getComportamientoDePago() {
		return comportamientoDePago;
	}
	public void setComportamientoDePago(Integer comportamientoDePago) {
		this.comportamientoDePago = comportamientoDePago;
	}
	public BigDecimal getEdad() {
		return edad;
	}
	public void setEdad(BigDecimal edad) {
		this.edad = edad;
	}
	public BigDecimal getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(BigDecimal creditScore) {
		this.creditScore = creditScore;
	}
	public String getRiesgo() {
		return riesgo;
	}
	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}
	public Double getFacturacionPromedioClaro() {
		return facturacionPromedioClaro;
	}
	public void setFacturacionPromedioClaro(Double facturacionPromedioClaro) {
		this.facturacionPromedioClaro = facturacionPromedioClaro;
	}
	public Double getFacturacionPromedioProducto() {
		return facturacionPromedioProducto;
	}
	public void setFacturacionPromedioProducto(Double facturacionPromedioProducto) {
		this.facturacionPromedioProducto = facturacionPromedioProducto;
	}
	public Double getLimiteDeCreditoDisponible() {
		return limiteDeCreditoDisponible;
	}
	public void setLimiteDeCreditoDisponible(Double limiteDeCreditoDisponible) {
		this.limiteDeCreditoDisponible = limiteDeCreditoDisponible;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Integer getTiempoDePermanencia() {
		return tiempoDePermanencia;
	}
	public void setTiempoDePermanencia(Integer tiempoDePermanencia) {
		this.tiempoDePermanencia = tiempoDePermanencia;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public XMLGregorianCalendar getFechaEjecucion() {
		return fechaEjecucion;
	}
	public void setFechaEjecucion(XMLGregorianCalendar fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
	public String getCalidadVendedor() {
		return calidadVendedor;
	}
	public void setCalidadVendedor(String calidadVendedor) {
		this.calidadVendedor = calidadVendedor;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescipcion() {
		return descipcion;
	}
	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCostoEquipo() {
		return costoEquipo;
	}
	public void setCostoEquipo(String costoEquipo) {
		this.costoEquipo = costoEquipo;
	}
	public String getModeloEquipo() {
		return modeloEquipo;
	}
	public void setModeloEquipo(String modeloEquipo) {
		this.modeloEquipo = modeloEquipo;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public String getRiesgoClaro() {
		return riesgoClaro;
	}
	public void setRiesgoClaro(String riesgoClaro) {
		this.riesgoClaro = riesgoClaro;
	}
	public Integer getCuotaInicial() {
		return cuotaInicial;
	}
	public void setCuotaInicial(Integer cuotaInicial) {
		this.cuotaInicial = cuotaInicial;
	}
	public Float getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(Float montoCuota) {
		this.montoCuota = montoCuota;
	}
	public Double getMontoCuotaComercial() {
		return montoCuotaComercial;
	}
	public void setMontoCuotaComercial(Double montoCuotaComercial) {
		this.montoCuotaComercial = montoCuotaComercial;
	}
	public Double getMontoCuotaInicialComercial() {
		return montoCuotaInicialComercial;
	}
	public void setMontoCuotaInicialComercial(Double montoCuotaInicialComercial) {
		this.montoCuotaInicialComercial = montoCuotaInicialComercial;
	}
	public String getSegmentoCliente() {
		return segmentoCliente;
	}
	public void setSegmentoCliente(String segmentoCliente) {
		this.segmentoCliente = segmentoCliente;
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
	public String getPlanActualCargoFijo() {
		return planActualCargoFijo;
	}
	public void setPlanActualCargoFijo(String planActualCargoFijo) {
		this.planActualCargoFijo = planActualCargoFijo;
	}
	public String getPlanActualDescripcion() {
		return planActualDescripcion;
	}
	public void setPlanActualDescripcion(String planActualDescripcion) {
		this.planActualDescripcion = planActualDescripcion;
	}
	public String getPlanActualCantidadCuotasPendientes() {
		return planActualCantidadCuotasPendientes;
	}
	public void setPlanActualCantidadCuotasPendientes(String planActualCantidadCuotasPendientes) {
		this.planActualCantidadCuotasPendientes = planActualCantidadCuotasPendientes;
	}
	public String getPlanActualMesesParaCubrirApadece() {
		return planActualMesesParaCubrirApadece;
	}
	public void setPlanActualMesesParaCubrirApadece(String planActualMesesParaCubrirApadece) {
		this.planActualMesesParaCubrirApadece = planActualMesesParaCubrirApadece;
	}
	public String getPlanActualMontoPendienteCuotas() {
		return planActualMontoPendienteCuotas;
	}
	public void setPlanActualMontoPendienteCuotas(String planActualMontoPendienteCuotas) {
		this.planActualMontoPendienteCuotas = planActualMontoPendienteCuotas;
	}
	public String getPlanActualPlazoAcuerdo() {
		return planActualPlazoAcuerdo;
	}
	public void setPlanActualPlazoAcuerdo(String planActualPlazoAcuerdo) {
		this.planActualPlazoAcuerdo = planActualPlazoAcuerdo;
	}
	public String getPlanActualTiempoPermanencia() {
		return planActualTiempoPermanencia;
	}
	public void setPlanActualTiempoPermanencia(String planActualTiempoPermanencia) {
		this.planActualTiempoPermanencia = planActualTiempoPermanencia;
	}	
	
	public String getEquipoPorcenCuotaInicial() {
		return equipoPorcenCuotaInicial;
	}
	public void setEquipoPorcenCuotaInicial(String equipoPorcenCuotaInicial) {
		this.equipoPorcenCuotaInicial = equipoPorcenCuotaInicial;
	}
	public String getBrmsTipOperacion() {
		return brmsTipOperacion;
	}
	public void setBrmsTipOperacion(String brmsTipOperacion) {
		this.brmsTipOperacion = brmsTipOperacion;
	}
	public String getFormaDePago() {
		return formaDePago;
	}
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	
}
