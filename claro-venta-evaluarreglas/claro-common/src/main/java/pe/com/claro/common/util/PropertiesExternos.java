package pe.com.claro.common.util;

import javax.ws.rs.core.Configuration;

public class PropertiesExternos {
	
	public final String codigoRespuestaIDF1EvaluarReglas;
	public final String descripcionRespuestaIDF1EvaluarReglas;
	public final String codigoRespuestaIDT1Generico;
	public final String descripcionRespuestaIDT1Generico;
	public final String codigoRespuestaIDT2Generico;
	public final String descripcionRespuestaIDT2Generico;
	public final String codigoRespuestaIDT3Generico;
	public final String descripcionRespuestaIDT3Generico;
	public final String pvuBd;
	public final String pvuOwner;
	public final String pkgBiomovil;
	public final String pkgGeneralDetalleLinea;	
	public final String pkgBrmsBiomovil;
	public final String conexionTimeoutRegistraOperacion;
	public final String pkgGenral3Play;
	public final String wsClaroEvalClienteReglasWSDL;
	public final String wsClaroEvalClienteReglasTimeOut;
	public final String wsConsultaSegmentoClienteWSDL;
	public final String wsConsultaSegmentoClienteTimeOut;
	public final String gestionAcuerdoWSDL;
	public final String gestionAcuerdoTimeOut;		
	public final String idf0Codigo;
	public final String idf0Mensaje;
	public final String brmsEquipoCosto;
	public final String brmsEquipoCuotas;
	public final String brmsFactorPagoInicial;
	public final String brmsEquipoFactorSubsidio;
	public final String brmsEquipoFormaPago;
	public final String brmsEquipoFormaPagoCuotas;
	public final String brmsEquipoGama;
	public final String brmsEquipoMontoCuota;
	public final String brmsEquipoPorcentajeCuotaInicial;
	public final String brmsEquipoPrecioVenta;
	public final String brmsEquipoRiesgo;
	public final String cantidadLineasEvaluadas;
	public final String canal;
	public final String paramGeneralCodigoUmbral;
	public final String paramGenralCodigo;
	public final String dbSgaOwner;
	public final String pkgPqConsultaSiacSrv;
	public final String dbBSCSOwner;
	public final String pkgParametrico;
	public final String bscsFUNCtim127CompPago;
	public final String tFun006GetCoIdFromDn;
	public final String listaServicios;
	public final String sisactsDetalleCFXContrato;	
	public final String consultaDeudaCuentaUsuAplicacion;
	public final String consultaDeudaCuentaCodAplicaion;
	public final String wsConsultaDeudaCuentaWSDL;
	public final String wsConsultaDeudaTimeOut;
	public final String idt1Codigo;
	public final String idt1Mensaje;
	public final String idt2Codigo;
	public final String idt2Mensaje;
	public final String idt3Codigo;
	public final String idt3Mensaje;
	public final String tipoDeProducto;
	public final String dbMsSapOwner;
	public final String pkgConsulta;
	public final String idf1Codigo;
	public final String idf1Mensaje;
	public final String idf2Codigo;
	public final String idf2Mensaje;
	public final String idf3Mensaje;
	public final String idf3Codigo;
	public final String pkgEvaluacionUni;
	public final String pkgSPplanXProducto;
	public final String pkgSPconDetalleLinea;
	public final String pkgSPplanXBilletera;
	public final String pKGSPcalculoLcXProducto;
	public final String pKGNuevaListaPre;
	public final String pKGGeneral3PlaySpConTipoCuota;
	public final String esSaludSunatPositivo;
	public final String esSaludSunatNegativo;
	public final String porcentajeMinimoCuota;
	public final String porcentajeMaximoCuota;	
	public final String wsConsultaDatosClienteBRMSOAC;
	public final String wsConsultaDatosClienteBRMSOACTimeOut;		
	//SERVICIO PARAMETRO
	public final String parametroRestClientURL;
	public final String parametroRestClientMethod;
	public final String parametroRestClientTimeOut;
	public final String parametroRestClientConsumirServicio;		
	//REQUEST BODY SERVICIO PARAMETRO
	public final String parametroRequestSistema;
	public final String parametroRequestVersion;
	public final String parametroRequestTipoOperacion;
	public final String parametroRequestGrupo;
	public final String parametroRequestCodigo;	
	//PARAMETRO
	public final String bdPvuPkgConfVentas;
	public final String bdPvuSpParametro;
	public final String bsPvuConexionTimeOutLimiteParametro;	
	//CONSULTACUOTACLIENTE
	public final String wsConsultaCuotaCliente;
	public final String wsConsultaCuotaClienteTimeOut;	
	public final String pkgSiactDraVe6;
	
	
	// WS EbsCreditosWS
	public final String ebsCreditosWsUrl;
	public final String ebsCreditosWsCnxTimeout;
	public final String ebsCreditosWsExecTimeout;

	public final String ebsusuarioApellidoMaterno;
	public final String ebsusuarioApellidoPaterno;
	public final String ebsusuarioArea;
	public final String ebsusuarioCadenaOpcionesPagina;
	public final String ebsusuarioCadenaPerfil;
	public final String ebsusuarioCanalVenta;
	public final String ebsusuarioCanalVentaDescripcion;
	public final String ebsusuarioEstadoAcceso;
	public final String ebsusuarioHost;
	public final String ebsusuarioIdArea;
	public final String ebsusuarioIdCuentaRed;
	public final Long ebsusuarioIdUsuario;
	public final Long ebsusuarioIdUsuarioSisact;
	public final String ebsusuarioIdVendedorSap;
	public final String ebsusuarioLogin;
	public final String ebsusuarioNombre;
	public final String ebsusuarioNombreCompleto;
	public final String ebsusuarioOficinaVenta;
	public final String ebsusuarioOficinaVentaDescripcion;
	public final Boolean ebsusuarioPerfil149;
	public final String ebsusuarioTerminal;
	public final String ebsusuarioTipoOficina;
	
	public final String ebsCreditoswsburoRespuesta13;
	public final String ebsCreditoswsburoRespuesta14;
	
	public final String tipoDocDniEbscredito;
	
	public final String codigoGenericoErrorIdt1;
	public final String mensajeGenericoErrorIdt1;
	public final String codigoGenericoErrorIdt2;
	public final String mensajeGenericoErrorIdt2;
	
	public PropertiesExternos(Configuration configuration) {
		super();
		this.codigoRespuestaIDF1EvaluarReglas = ClaroUtil.convertProperties(configuration.getProperty("codigo.valida.parametros.entrada.idf1"));
		this.descripcionRespuestaIDF1EvaluarReglas = ClaroUtil.convertProperties(configuration.getProperty("mensaje.valida.parametros.entrada.idf1"));
		this.codigoRespuestaIDT1Generico = ClaroUtil.convertProperties(configuration.getProperty("idt.1.codigo"));
		this.descripcionRespuestaIDT1Generico = ClaroUtil.convertProperties(configuration.getProperty("idt.1.mensaje"));
		this.codigoRespuestaIDT2Generico = ClaroUtil.convertProperties(configuration.getProperty("idt.2.codigo"));
		this.descripcionRespuestaIDT2Generico = ClaroUtil.convertProperties(configuration.getProperty("idt.2.mensaje"));
		this.codigoRespuestaIDT3Generico = ClaroUtil.convertProperties(configuration.getProperty("idt.3.codigo"));
		this.descripcionRespuestaIDT3Generico = ClaroUtil.convertProperties(configuration.getProperty("idt.3.mensaje"));
		this.pvuBd = ClaroUtil.convertProperties(configuration.getProperty("pvu.bd"));
		this.pvuOwner = ClaroUtil.convertProperties(configuration.getProperty("pvu.owner"));
		this.pkgBiomovil = ClaroUtil.convertProperties(configuration.getProperty("pkg.biomovil"));
		this.pkgBrmsBiomovil = ClaroUtil.convertProperties(configuration.getProperty("pkg.BrmsBiomovil"));
		this.conexionTimeoutRegistraOperacion = ClaroUtil.convertProperties(configuration.getProperty("conexion.timeout.registra.operacion"));
		this.wsClaroEvalClienteReglasWSDL = ClaroUtil.convertProperties(configuration.getProperty("wsclient.claroevalclientereglas.wsdl"));
		this.wsClaroEvalClienteReglasTimeOut = ClaroUtil.convertProperties(configuration.getProperty("wsclient.claroevalclientereglas.timeout"));
		this.wsConsultaSegmentoClienteWSDL = ClaroUtil.convertProperties(configuration.getProperty("wssegmentocliente.ods.cod.aplicacion"));
		this.wsConsultaSegmentoClienteTimeOut = ClaroUtil.convertProperties(configuration.getProperty("segmentocliente.ods.cod.timeout"));		
		this.idt3Codigo = ClaroUtil.convertProperties(configuration.getProperty("idt.3.codigo"));
		this.idt3Mensaje = ClaroUtil.convertProperties(configuration.getProperty("idt.3.mensaje"));
		this.brmsEquipoCosto = ClaroUtil.convertProperties(configuration.getProperty("brms.equipo.costo"));
		this.brmsEquipoCuotas = ClaroUtil.convertProperties(configuration.getProperty("brms.equipo.cuotas"));
		this.brmsFactorPagoInicial = ClaroUtil.convertProperties(configuration.getProperty("brms.equipo.factor.pago.inicial"));
		this.brmsEquipoFactorSubsidio = ClaroUtil.convertProperties(configuration.getProperty("brms.equipo.factor.subsidio"));
		this.brmsEquipoFormaPago = ClaroUtil.convertProperties(configuration.getProperty("brms.equipo.forma.pago"));
		this.brmsEquipoFormaPagoCuotas = ClaroUtil.convertProperties(configuration.getProperty("brms.equipo.forma.pago.cuotas"));
		this.brmsEquipoGama = ClaroUtil.convertProperties(configuration.getProperty("brms.equipo.gama"));
		this.brmsEquipoMontoCuota = ClaroUtil.convertProperties(configuration.getProperty("brms.equipo.monto.cuota"));
		this.brmsEquipoPorcentajeCuotaInicial = ClaroUtil.convertProperties(configuration.getProperty("brms.equipo.porcentaje.cuota.inicial"));
		this.brmsEquipoPrecioVenta = ClaroUtil.convertProperties(configuration.getProperty("brms.equipo.precio.venta"));
		this.brmsEquipoRiesgo = ClaroUtil.convertProperties(configuration.getProperty("brms.equipo.riesgo"));
		this.idf0Codigo = ClaroUtil.convertProperties(configuration.getProperty("idf.0.codigo"));
		this.idf0Mensaje = ClaroUtil.convertProperties(configuration.getProperty("idf.0.mensaje"));
		this.idf2Codigo = ClaroUtil.convertProperties(configuration.getProperty("idf.2.codigo"));
		this.idf2Mensaje = ClaroUtil.convertProperties(configuration.getProperty("idf.2.mensaje"));
		this.idf3Mensaje = ClaroUtil.convertProperties(configuration.getProperty("idf.3.mensaje"));
		this.idf3Codigo = ClaroUtil.convertProperties(configuration.getProperty("idf.3.codigo"));
		this.cantidadLineasEvaluadas = ClaroUtil.convertProperties(configuration.getProperty("cantidad.lineas.evaluadas"));
		this.canal = ClaroUtil.convertProperties(configuration.getProperty("solicitud.puntodeventa.canal"));
		this.paramGeneralCodigoUmbral = ClaroUtil.convertProperties(configuration.getProperty("pvu.param.general.codigo.umbral"));
		this.paramGenralCodigo = ClaroUtil.convertProperties(configuration.getProperty("pvu.param.general.codigo"));
		this.pkgGenral3Play = ClaroUtil.convertProperties(configuration.getProperty("pkg.Genral3Play"));
		this.dbSgaOwner = ClaroUtil.convertProperties(configuration.getProperty("sga.owner"));
		this.pkgPqConsultaSiacSrv = ClaroUtil.convertProperties(configuration.getProperty("sga.pkgPqConsultaSiacSrv"));
		this.dbBSCSOwner = ClaroUtil.convertProperties(configuration.getProperty("bscs.owner"));
		this.pkgParametrico = ClaroUtil.convertProperties(configuration.getProperty("bscs.pkg.parametrico.detalle.lineas"));
		this.dbMsSapOwner = ClaroUtil.convertProperties(configuration.getProperty("mssap.owner"));
		this.pkgConsulta = ClaroUtil.convertProperties(configuration.getProperty("mssap.pkgconsulta"));
		this.bscsFUNCtim127CompPago = ClaroUtil.convertProperties(configuration.getProperty("bscs.pkg.bscsFUNCtim127CompPago"));
		this.tFun006GetCoIdFromDn = ClaroUtil.convertProperties(configuration.getProperty("bscs.pkg.tfun006_get_coid_from_dn"));
		this.listaServicios = ClaroUtil.convertProperties(configuration.getProperty("bscs.pkg.lista_servicios"));		
		this.consultaDeudaCuentaUsuAplicacion = ClaroUtil.convertProperties(configuration.getProperty("consultadeudacuenta.oac.usuario.aplicacion"));
		this.consultaDeudaCuentaCodAplicaion = ClaroUtil.convertProperties(configuration.getProperty("consultadeudacuenta.oac.cod.aplicacion"));
		this.wsConsultaDeudaCuentaWSDL = ClaroUtil.convertProperties(configuration.getProperty("wsconsultadeudacuenta.oac.cod.aplicacion"));
		this.wsConsultaDeudaTimeOut = ClaroUtil.convertProperties(configuration.getProperty("consultadeudacuenta.oac.cod.timeout"));
		this.idt1Codigo = ClaroUtil.convertProperties(configuration.getProperty("idt.1.codigo"));
		this.idt1Mensaje = ClaroUtil.convertProperties(configuration.getProperty("idt.1.mensaje"));
		this.idt2Codigo = ClaroUtil.convertProperties(configuration.getProperty("idt.2.codigo"));
		this.idt2Mensaje = ClaroUtil.convertProperties(configuration.getProperty("idt.2.mensaje"));
		this.tipoDeProducto = ClaroUtil.convertProperties(configuration.getProperty("tipo.de.producto"));
		this.idf1Codigo = ClaroUtil.convertProperties(configuration.getProperty("idf.1.codigo"));
		this.idf1Mensaje = ClaroUtil.convertProperties(configuration.getProperty("idf.1.mensaje"));
		this.pkgEvaluacionUni = ClaroUtil.convertProperties(configuration.getProperty("pvu.pkg.sisact.evaluacion.uni.sp.factura.x.producto"));
		this.pkgSPplanXProducto = ClaroUtil.convertProperties(configuration.getProperty("pvu.pkg.sisact.evaluacion.uni.sp.plan.x.producto"));
		this.pkgSPconDetalleLinea = ClaroUtil.convertProperties(configuration.getProperty("pvu.pkg.sisact.general.3play.6.sp.con.detalle.linea"));
		this.pkgSPplanXBilletera = ClaroUtil.convertProperties(configuration.getProperty("pvu.pkg.sisact.evaluacion.uni.sp.plan.x.billetera"));
		this.pKGSPcalculoLcXProducto = ClaroUtil.convertProperties(configuration.getProperty("pvu.pkg.sisact.evaluacion.uni.sp.calculo.lc.x.producto"));
		this.pKGNuevaListaPre = ClaroUtil.convertProperties(configuration.getProperty("pvu.pkg.sisact.nueva.listapre.sp.calculo.ini.comercial"));
		this.pKGGeneral3PlaySpConTipoCuota = ClaroUtil.convertProperties(configuration.getProperty("pvu.pkg.sisact.pkg.general.3play.6"));
		this.esSaludSunatPositivo = ClaroUtil.convertProperties(configuration.getProperty("essaludsunatpositivo"));
		this.esSaludSunatNegativo = ClaroUtil.convertProperties(configuration.getProperty("essaludsunatnegativo"));
		this.porcentajeMinimoCuota = ClaroUtil.convertProperties(configuration.getProperty("porcentaje.minimo.cuota"));
		this.porcentajeMaximoCuota = ClaroUtil.convertProperties(configuration.getProperty("porcentaje.maximo.cuota"));		
		this.wsConsultaDatosClienteBRMSOAC = ClaroUtil.convertProperties(configuration.getProperty("wsconsultaDatosClienteBRMSOAC.oac.cod.aplicacion"));
		this.wsConsultaDatosClienteBRMSOACTimeOut = ClaroUtil.convertProperties(configuration.getProperty("wsconsultaDatosClienteBRMSOAC.oac.cod.timeout"));		
		this.parametroRestClientURL=ClaroUtil.convertProperties(configuration.getProperty("parametro.rest.client.url"));
		this.parametroRestClientMethod=ClaroUtil.convertProperties(configuration.getProperty("parametro.rest.client.method"));
		this.parametroRestClientTimeOut=ClaroUtil.convertProperties(configuration.getProperty("parametro.rest.client.timeout"));
		this.parametroRestClientConsumirServicio=ClaroUtil.convertProperties(configuration.getProperty("parametro.rest.client.consumirServicio"));		
		this.bdPvuPkgConfVentas = ClaroUtil.convertProperties(configuration.getProperty("pvu.bd.pkg.conf.ventas"));
		this.bdPvuSpParametro = ClaroUtil.convertProperties(configuration.getProperty("pvu.bd.sp.parametro"));
		this.bsPvuConexionTimeOutLimiteParametro = ClaroUtil.convertProperties(configuration.getProperty("pvu.bd.conexion.timeout.limite.parametro"));
		this.parametroRequestGrupo = ClaroUtil.convertProperties(configuration.getProperty("parametro.request.grupo"));
		this.parametroRequestSistema = ClaroUtil.convertProperties(configuration.getProperty("parametro.request.sistema"));
		this.parametroRequestVersion = ClaroUtil.convertProperties(configuration.getProperty("parametro.request.version"));
		this.parametroRequestTipoOperacion = ClaroUtil.convertProperties(configuration.getProperty("parametro.request.tipoOperacion"));
		this.parametroRequestCodigo = ClaroUtil.convertProperties(configuration.getProperty("parametro.request.codigo"));		
		this.wsConsultaCuotaCliente = ClaroUtil.convertProperties(configuration.getProperty("wsconsultaCuotaCliente.oac.cod.aplicacion"));
		this.wsConsultaCuotaClienteTimeOut = ClaroUtil.convertProperties(configuration.getProperty("wsconsultaCuotaCliente.oac.cod.timeout"));		
		this.gestionAcuerdoWSDL = ClaroUtil.convertProperties(configuration.getProperty("wsgestionacuerdo.wsdl.aplicacion"));
		this.gestionAcuerdoTimeOut = ClaroUtil.convertProperties(configuration.getProperty("wsgestionacuerdo.wsdl.aplicacion.timeout"));				
		this.pkgSiactDraVe6 = ClaroUtil.convertProperties(configuration.getProperty("pvu.pkg.sisact.dra.cve.6"));		
		this.pkgGeneralDetalleLinea = ClaroUtil.convertProperties(configuration.getProperty("pvu.pkg.sisact.pkg.general"));				
		
		//renovacion	
		this.ebsCreditosWsUrl = ClaroUtil.convertProperties(configuration.getProperty("ebsCreditoswsburo.ebsCreditossb11.endpoint.url"));
		this.ebsCreditosWsCnxTimeout = ClaroUtil.convertProperties(configuration.getProperty("ebsCreditoswsburo.ebsCreditossb11.conexion.timeout"));
		this.ebsCreditosWsExecTimeout = ClaroUtil.convertProperties(configuration.getProperty("ebsCreditoswsburo.ebsCreditossb11.execution.timeout"));
		this.ebsusuarioApellidoMaterno = ClaroUtil.convertProperties(configuration.getProperty("usuario.apellido.materno"));
		this.ebsusuarioApellidoPaterno = ClaroUtil.convertProperties(configuration.getProperty("usuario.apellido.paterno"));
		this.ebsusuarioArea = ClaroUtil.convertProperties(configuration.getProperty("usuario.area"));
		this.ebsusuarioCadenaOpcionesPagina = ClaroUtil.convertProperties(configuration.getProperty("usuario.cadena.opciones.pagina"));
		this.ebsusuarioCadenaPerfil = ClaroUtil.convertProperties(configuration.getProperty("usuario.cadena.perfil"));
		this.ebsusuarioCanalVenta = ClaroUtil.convertProperties(configuration.getProperty("usuario.canal.venta"));
		this.ebsusuarioCanalVentaDescripcion = ClaroUtil.convertProperties(configuration.getProperty("usuario.canal.venta.descripcion"));
		this.ebsusuarioEstadoAcceso = ClaroUtil.convertProperties(configuration.getProperty("usuario.estado.acceso"));
		this.ebsusuarioHost = ClaroUtil.convertProperties(configuration.getProperty("usuario.host"));
		this.ebsusuarioIdArea = ClaroUtil.convertProperties(configuration.getProperty("usuario.id_area"));
		this.ebsusuarioIdCuentaRed = ClaroUtil.convertProperties(configuration.getProperty("usuario.id.cuenta.red"));
		this.ebsusuarioIdUsuario = ClaroUtil.convertProperties(configuration.getProperty("usuario.id.usuario")) != null && 
										!ClaroUtil.convertProperties(configuration.getProperty("usuario.id.usuario")).isEmpty() ?
												Long.parseLong(ClaroUtil.convertProperties(configuration.getProperty("usuario.id.usuario"))) : null;
		this.ebsusuarioIdUsuarioSisact = ClaroUtil.convertProperties(configuration.getProperty("usuario.id.usuario.sisact")) != null && 
											!ClaroUtil.convertProperties(configuration.getProperty("usuario.id.usuario.sisact")).isEmpty() ? 
								Long.parseLong(ClaroUtil.convertProperties(configuration.getProperty("usuario.id.usuario.sisact"))) : null;
		this.ebsusuarioIdVendedorSap = ClaroUtil.convertProperties(configuration.getProperty("usuario.id.vendedor.sap"));
		this.ebsusuarioLogin = ClaroUtil.convertProperties(configuration.getProperty("usuario.login"));
		this.ebsusuarioNombre = ClaroUtil.convertProperties(configuration.getProperty("usuario.nombre"));
		this.ebsusuarioNombreCompleto = ClaroUtil.convertProperties(configuration.getProperty("usuario.nombre.completo"));
		this.ebsusuarioOficinaVenta = ClaroUtil.convertProperties(configuration.getProperty("usuario.oficina.venta"));
		this.ebsusuarioOficinaVentaDescripcion = ClaroUtil.convertProperties(configuration.getProperty("usuario.oficina.venta.descripcion"));
		this.ebsusuarioPerfil149 = ClaroUtil.convertProperties(configuration.getProperty("usuario.perfil149")) != null && 
										!ClaroUtil.convertProperties(configuration.getProperty("usuario.perfil149")).isEmpty() ?
											Boolean.parseBoolean(ClaroUtil.convertProperties(configuration.getProperty("usuario.perfil149"))) : null;
		this.ebsusuarioTerminal = ClaroUtil.convertProperties(configuration.getProperty("usuario.terminal"));
		this.ebsusuarioTipoOficina = ClaroUtil.convertProperties(configuration.getProperty("usuario.tipo.oficina"));
		
		this.ebsCreditoswsburoRespuesta13 = ClaroUtil.convertProperties(configuration.getProperty("ebsCreditoswsburo.respuesta.13"));
		this.ebsCreditoswsburoRespuesta14 = ClaroUtil.convertProperties(configuration.getProperty("ebsCreditoswsburo.respuesta.14"));
		
		this.tipoDocDniEbscredito = ClaroUtil.convertProperties(configuration.getProperty("tipo.doc.dni.ebscredito"));		
		
		this.codigoGenericoErrorIdt1 = ClaroUtil.convertProperties(configuration.getProperty("codigo.generico.error.idt1"));
		this.mensajeGenericoErrorIdt1 = ClaroUtil.convertProperties(configuration.getProperty("mensaje.generico.error.idt1"));
		this.codigoGenericoErrorIdt2 = ClaroUtil.convertProperties(configuration.getProperty("codigo.generico.error.idt2"));
		this.mensajeGenericoErrorIdt2 = ClaroUtil.convertProperties(configuration.getProperty("mensaje.generico.error.idt2"));
		this.sisactsDetalleCFXContrato = ClaroUtil.convertProperties(configuration.getProperty("bscs.pkg.sisact.detalle.cf.x.contrato"));		
		
	}
}