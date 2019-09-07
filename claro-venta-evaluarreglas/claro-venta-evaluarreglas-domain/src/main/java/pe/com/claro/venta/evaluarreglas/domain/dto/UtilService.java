package pe.com.claro.venta.evaluarreglas.domain.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.Autonomia;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.ClaroEvalClientesReglasRequest;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.ClaroEvalClientesReglasResponse;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.Cliente;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.Cuota;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.Direccion;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.Documento;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.Equipo;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.Garantia;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.Oferta;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.Ofrecimiento;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.OpcionDeCuotas;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.PlanActual;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.PlanSolicitado;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.PuntodeVenta;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.RespuestaProactiva;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.ResultadosAdicionales;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.Servicio;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.TipoDeDocumento;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.param.Solicitud;

import pe.com.claro.common.bean.HeaderResponse;
import pe.com.claro.common.exception.DBException;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.util.ClaroUtil;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.esb.message.test.consultardeudacuentabrms_db.v1.ConsultarDeudaCuentaBRMSDbRequestType;
import pe.com.claro.esb.message.test.consultardeudacuentabrms_db.v1.ConsultarDeudaCuentaBRMSDbResponseType;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes.EvaluarDatosClienteRequestWS;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes.TipoSiNo;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.parametro.ParametroRequest;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.AutonomiaWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.ClaroEvalClienteAuxiliarBeanWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.CuotaWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.EvaluarDatosClienteResponseWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.GarantiaWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.OfrecimientoWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.OpcionDeCuotasWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.PvuConTipoCuotaResponseWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.RespuestaProactivaWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.ResultadosAdicionalesWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.TipoDeCobro;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.ebscreditos.ClienteType;
import pe.com.claro.venta.evaluarreglas.model.BRMSBioMovilRequest;
import pe.com.claro.venta.evaluarreglas.model.BscsDXLpCurDetalleBean;
import pe.com.claro.venta.evaluarreglas.model.DatosResponse;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ParamGeneralBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuConTipoCuotaResponse;
import pe.com.claro.venta.evaluarreglas.model.ResponsePrecioBase;
import pe.com.claro.venta.evaluarreglas.model.SgaDetalleInfCurBean;

@Stateless
public class UtilService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UtilService.class);
	
	public BRMSBioMovilRequest cargarRequestInsertBRMSBiomovil(EvaluarDatosClienteRequestWS request, ClaroEvalClientesReglasResponse respClaroEval, ClaroEvalClienteAuxiliarBeanWS objClaroEvalBeanAux, PropertiesExternos propertiesExternos) {
		BRMSBioMovilRequest respDatosEvaluacion = new BRMSBioMovilRequest();
		respDatosEvaluacion.setBrmsTipOperacion(objClaroEvalBeanAux.getBrmsTipOperacion());
		respDatosEvaluacion.setBrmsTipDocumento(request.getTipoDocumento());
		respDatosEvaluacion.setBrmsNroDocumento(request.getNumeroDocumento());
		respDatosEvaluacion.setBrmsTipServicio(Constantes.SCERO);
		respDatosEvaluacion.setCantidadDeAplicacionesRenta(new BigDecimal(respClaroEval.getOfrecimiento().getOfrecimiento().getGarantia().getCantidadDeAplicacionesRenta()));
		respDatosEvaluacion.setCantidadDeLineasAdicionalesRuc(new BigDecimal(respClaroEval.getOfrecimiento().getOfrecimiento().getAutonomia().getCantidadDeLineasAdicionalesRUC()));
		respDatosEvaluacion.setCantidadDeLineasMaximas(new BigDecimal(respClaroEval.getOfrecimiento().getOfrecimiento().getAutonomia().getCantidadDeLineasMaximas()));
		//Porta chip y porta pack
		respDatosEvaluacion.setCantidadDeLineasRenovaciones(new BigDecimal(Constantes.CERO));
		respDatosEvaluacion.setCapacidadDePago(respClaroEval.getOfrecimiento().getOfrecimiento().getResultadosAdicionales().getCapacidadDePago());
		respDatosEvaluacion.setComportamientoConsolidado(new BigDecimal(respClaroEval.getOfrecimiento().getOfrecimiento().getResultadosAdicionales().getComportamientoConsolidado()));
		respDatosEvaluacion.setComportamientoDePagoC1(new BigDecimal(respClaroEval.getOfrecimiento().getOfrecimiento().getResultadosAdicionales().getComportamientoDePagoC1()));
		respDatosEvaluacion.setComportamientoDePago(String.valueOf(objClaroEvalBeanAux.getComportamientoDePago()));
		respDatosEvaluacion.setControlDeConsumo(objClaroEvalBeanAux.getControlDeConsumo());
		respDatosEvaluacion.setCostoDeInstalacion(BigDecimal.valueOf(respClaroEval.getOfrecimiento().getOfrecimiento().getCostoDeInstalacion()));
		respDatosEvaluacion.setCostoTotalEquipos(Double.parseDouble(request.getCostoTotalEquipo()));
		respDatosEvaluacion.setFactorDeEndeudamiento(BigDecimal.valueOf(respClaroEval.getOfrecimiento().getOfrecimiento().getResultadosAdicionales().getFactorDeEndeudamientoCliente()));
		respDatosEvaluacion.setFactorDeRenovacion(BigDecimal.valueOf(respClaroEval.getOfrecimiento().getOfrecimiento().getResultadosAdicionales().getFactorDeRenovacionCliente()));
		respDatosEvaluacion.setFrecuenciaDeAplicacionMensual(new BigDecimal(respClaroEval.getOfrecimiento().getOfrecimiento().getGarantia().getFrecuenciaDeAplicacionMensual()));
		respDatosEvaluacion.setLimiteDeCrecitoCobranza(BigDecimal.valueOf(respClaroEval.getOfrecimiento().getOfrecimiento().getLimiteDeCreditoCobranza()));
		respDatosEvaluacion.setMesInicioRentas(new BigDecimal(respClaroEval.getOfrecimiento().getOfrecimiento().getGarantia().getMesInicioRentas()));
		respDatosEvaluacion.setMontoCfParaRuc(BigDecimal.valueOf(respClaroEval.getOfrecimiento().getOfrecimiento().getAutonomia().getMontoCFParaRUC()));
		respDatosEvaluacion.setMontoDeGarantia(BigDecimal.valueOf(respClaroEval.getOfrecimiento().getOfrecimiento().getGarantia().getMontoDeGarantia()));
		respDatosEvaluacion.setMontoTopeAutomatico(BigDecimal.valueOf(respClaroEval.getOfrecimiento().getOfrecimiento().getMontoTopeAutomatico()));
		respDatosEvaluacion.setPrecioDeVentaTotalEquipos(BigDecimal.valueOf(respClaroEval.getOfrecimiento().getOfrecimiento().getResultadosAdicionales().getPrecioDeVentaTotalEquipos()));
		respDatosEvaluacion.setPrioridadPublicar(respClaroEval.getOfrecimiento().getOfrecimiento().getPrioridadPublicar().toString());
		respDatosEvaluacion.setExoneracionDeRentas(respClaroEval.getOfrecimiento().getOfrecimiento().getProcesoDeExoneracionDeRentas().toString());
		respDatosEvaluacion.setIdValidator(respClaroEval.getOfrecimiento().getOfrecimiento().getProcesoIDValidator());
		respDatosEvaluacion.setValidacionInternaClaro(respClaroEval.getOfrecimiento().getOfrecimiento().getProcesoValidacionInternaClaro());
		respDatosEvaluacion.setPublicar(respClaroEval.getOfrecimiento().getOfrecimiento().getPublicar().toString());
		respDatosEvaluacion.setRestriccion(respClaroEval.getOfrecimiento().getOfrecimiento().getRestriccion().toString());
		respDatosEvaluacion.setRiesgoEnClaro(respClaroEval.getOfrecimiento().getOfrecimiento().getResultadosAdicionales().getRiesgoEnClaro());
		respDatosEvaluacion.setRiesgoOferta(respClaroEval.getOfrecimiento().getOfrecimiento().getResultadosAdicionales().getRiesgoOferta());
		respDatosEvaluacion.setRiesgoTotalEquipo(String.valueOf(respClaroEval.getOfrecimiento().getOfrecimiento().getResultadosAdicionales().getRiesgoTotalEquipo()));
		respDatosEvaluacion.setRiesgoTotalReplegales(respClaroEval.getOfrecimiento().getOfrecimiento().getResultadosAdicionales().getRiesgoTotalRepLegales());
		respDatosEvaluacion.setTipoDeAutonomiaCargoFijo(respClaroEval.getOfrecimiento().getOfrecimiento().getAutonomia().getTipoDeAutonomiaCargoFijo());
		respDatosEvaluacion.setTipoDeCobro(respClaroEval.getOfrecimiento().getOfrecimiento().getGarantia().getTipodecobro().toString());
		respDatosEvaluacion.setTipoDeGarantia(respClaroEval.getOfrecimiento().getOfrecimiento().getGarantia().getTipoDeGarantia());
		respDatosEvaluacion.setSolinGrupoSEC(null);
		respDatosEvaluacion.setAutonomiaRenovacion(respClaroEval.getOfrecimiento().getOfrecimiento().getAutonomia().getCantidadDeLineasRenovaciones() != null ? respClaroEval.getOfrecimiento().getOfrecimiento().getAutonomia().getCantidadDeLineasRenovaciones() : Constantes.VACIO);
		respDatosEvaluacion.setCantidadDePlanesPorProducto(String.valueOf(objClaroEvalBeanAux.getCantidadDePlanesPorProducto() != null ? objClaroEvalBeanAux.getCantidadDePlanesPorProducto() : Constantes.CERO));// VALIDAR ESTE PARAMETRO
		respDatosEvaluacion.setFacturacionPromedioClaro(String.valueOf(objClaroEvalBeanAux.getFacturacionPromedioClaro() != null ? objClaroEvalBeanAux.getFacturacionPromedioClaro() : Constantes.CERO));// VALIDAR ESTE PARAMETRO
		respDatosEvaluacion.setFacturacionPromedioProducto(String.valueOf(objClaroEvalBeanAux.getFacturacionPromedioProducto() != null ? objClaroEvalBeanAux.getFacturacionPromedioProducto() : Constantes.CERO));// VALIDAR ESTE PARAMETRO
		respDatosEvaluacion.setTiempoDePermanencia(String.valueOf(objClaroEvalBeanAux.getTiempoDePermanencia() != null ? objClaroEvalBeanAux.getTiempoDePermanencia() : Constantes.CERO));// VALIDAR ESTE PARAMETRO
		respDatosEvaluacion.setTipo(Constantes.VACIO);
		respDatosEvaluacion.setOfertaCasoEspecial(Constantes.REGULAR);
		respDatosEvaluacion.setEquipoCosto(request.getCostoTotalEquipo() != null ? request.getCostoTotalEquipo() : Constantes.SCERO);
		respDatosEvaluacion.setEquipoCuotas(String.valueOf(request.getNumeroCuotas()));
		respDatosEvaluacion.setEquipoFormaDePago(objClaroEvalBeanAux.getFormaDePago());
		respDatosEvaluacion.setEquipoGama(propertiesExternos.brmsEquipoGama);
		respDatosEvaluacion.setEquipoModelo(request.getDescEquipo());
		respDatosEvaluacion.setEquipoMontoDeCuota(request.getMontoCuota()!= null ? String.valueOf(request.getMontoCuota()) : String.valueOf(Constantes.CERO));
		respDatosEvaluacion.setEquipoPorcenCuotaInicial(objClaroEvalBeanAux.getEquipoPorcenCuotaInicial());
		respDatosEvaluacion.setEquipoPrecioDeVenta(String.valueOf(request.getPrecioDeVenta()));
		respDatosEvaluacion.setEquipoTipoDeDeco(Constantes.VACIO);
		respDatosEvaluacion.setEquipoTipoOperacionKit(Constantes.VACIO);
		respDatosEvaluacion.setCantidadDeLineasActivas(new BigDecimal(objClaroEvalBeanAux.getCantidadDeLineasActivas().intValue()));
		respDatosEvaluacion.setMesOperadorCedente(new BigDecimal(objClaroEvalBeanAux.getMesOperadorCedente()));
		respDatosEvaluacion.setSegmento(objClaroEvalBeanAux.getSegmentoCliente());
		respDatosEvaluacion.setCodigoPDV(request.getOficinaPVU());
		respDatosEvaluacion.setDeuda(objClaroEvalBeanAux.getDeuda());
//		respDatosEvaluacion.setDeuda(objClaroEvalBeanAux.getDeuda());
		
		respDatosEvaluacion.setFechaEjecucion(new java.sql.Date(System.currentTimeMillis()));
		respDatosEvaluacion.setModalidadCedente(objClaroEvalBeanAux.getModalidadCedente());
		respDatosEvaluacion.setMontoCFSEC(BigDecimal.valueOf((objClaroEvalBeanAux.getMontoCFSEC())));
		respDatosEvaluacion.setTipoDeOperacion(objClaroEvalBeanAux.getTipoDeOperacion());
		respDatosEvaluacion.setBuroConsultado(objClaroEvalBeanAux.getBuroConsultado());
		
		//Nuevas 7 Variables
		respDatosEvaluacion.setMontoDeudaVencida(objClaroEvalBeanAux.getMontoDeudaVencida());
		respDatosEvaluacion.setMontoDeudaCastigada(objClaroEvalBeanAux.getMontoDeudaCastigada());
		respDatosEvaluacion.setMontoDisputa(objClaroEvalBeanAux.getMontoDisputa());
		respDatosEvaluacion.setCantidadMontoDisputa(objClaroEvalBeanAux.getCantidadMontoDisputa());
		respDatosEvaluacion.setAntiguedadMontoDisputa(objClaroEvalBeanAux.getAntiguedadMontoDisputa());
		respDatosEvaluacion.setMontoTotalDeuda(objClaroEvalBeanAux.getMontoTotalDeuda());
		respDatosEvaluacion.setAntiguedadDeuda(objClaroEvalBeanAux.getAntiguedadDeuda());				
		
		//Otras Nuevas 6 Variables
		respDatosEvaluacion.setCantidadMaximaCuotasPendientesSistema(objClaroEvalBeanAux.getCantidadMaximaCuotasPendientesSistema());
		respDatosEvaluacion.setCantidadMaximaCuotasPendientesUltimasVentas(objClaroEvalBeanAux.getCantidadMaximaCuotasPendientesUltimasVentas());
		respDatosEvaluacion.setCantidadPlanesCuotasPendientesSistema(objClaroEvalBeanAux.getCantidadPlanesCuotasPendientesSistema());
		respDatosEvaluacion.setCantidadPlanesCuotasPendientesUltimasVentas(objClaroEvalBeanAux.getCantidadPlanesCuotasPendientesUltimasVentas());
		respDatosEvaluacion.setMontoPendienteCuotasSistema(objClaroEvalBeanAux.getMontoPendienteCuotasSistema());
		respDatosEvaluacion.setMontoPendienteCuotasUltimasVentas(objClaroEvalBeanAux.getMontoPendienteCuotasUltimasVentas());
		
		return respDatosEvaluacion;
	}
	
	public void eliminarNulos(EvaluarDatosClienteRequestWS request){
		request.setNumeroDocumento(request.getNumeroDocumento() != null ? request.getNumeroDocumento() : Constantes.VACIO);
		request.setOficinaPVU(request.getOficinaPVU() != null ? request.getOficinaPVU() : Constantes.VACIO);
		request.setNroOperacionPVU(request.getNroOperacionPVU() != null ? request.getNroOperacionPVU() : Constantes.VACIO);
		request.setRiesgoPVU(request.getRiesgoPVU() != null ? request.getRiesgoPVU() : Constantes.VACIO);
		request.setPlanesxProductoPVU(request.getPlanesxProductoPVU() != null ? request.getPlanesxProductoPVU() : Constantes.VACIO);
		request.setIdPlan(request.getIdPlan() != null ? request.getIdPlan() : Constantes.VACIO);
		request.setTipoCampana(request.getTipoCampana() != null ? request.getTipoCampana() : Constantes.VACIO);
		request.setModalidadCedente(request.getModalidadCedente() != null ? request.getModalidadCedente() : Constantes.VACIO);
		request.setOperadorCedente(request.getOperadorCedente() != null ? request.getOperadorCedente() : Constantes.VACIO);
		request.setTipoDeOperacion(request.getTipoDeOperacion() != null ? request.getTipoDeOperacion() : Constantes.VACIO);
		request.setDescripcionPlan(request.getDescripcionPlan() != null ? request.getDescripcionPlan() : Constantes.VACIO);
		request.setControlDeConsumo(request.getControlDeConsumo() != null ? request.getControlDeConsumo() : Constantes.VACIO);
		request.setTipoDocumento(request.getTipoDocumento() != null ? request.getTipoDocumento() : Constantes.VACIO);
		request.setCodigoplazo(request.getCodigoplazo() != null ? request.getCodigoplazo() : Constantes.VACIO);
		request.setFlagModoOperacion(request.getFlagModoOperacion() != null ? request.getFlagModoOperacion() : Constantes.VACIO);
		request.setPlazoDeAcuerdo(request.getPlazoDeAcuerdo() != null ? request.getPlazoDeAcuerdo() : Constantes.VACIO);
		request.setEssaludSunatPVU(request.getEssaludSunatPVU() != null ? request.getEssaludSunatPVU() : Constantes.VACIO);
		request.setCostoTotalEquipo(request.getCostoTotalEquipo() != null ? request.getCostoTotalEquipo() : Constantes.VACIO);
		request.setDescEquipo(request.getDescEquipo() != null ? request.getDescEquipo() : Constantes.VACIO);
		request.setCodListaPrecio(request.getCodListaPrecio() != null ? request.getCodListaPrecio() : Constantes.VACIO);
		request.setLineaCliente(request.getLineaCliente()!= null ? request.getLineaCliente() : Constantes.VACIO);
		
		request.setCodigoMaterial(request.getCodigoMaterial() != null ? request.getCodigoMaterial() : Constantes.VACIO);
		request.setTransaccion(request.getTransaccion()!= null ? request.getTransaccion() : Constantes.VACIO);
		request.setFormaDePago(request.getFormaDePago()!= null ? request.getFormaDePago() : Constantes.VACIO);
		request.setPorcentajeCuotaInicial(request.getPorcentajeCuotaInicial()!= null ? request.getPorcentajeCuotaInicial() : Constantes.CERODOUBLE);
		request.setCuotaInicial(request.getCuotaInicial()!= null ? request.getCuotaInicial() : Constantes.CERODOUBLE);
		request.setMontoCuota(request.getMontoCuota()!= null ? request.getMontoCuota() :  Constantes.CERODOUBLE);
	}
	
	public OfrecimientoWS cargarOfrecimiento(Ofrecimiento ofrecimientoIbm) {
		com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.param.Ofrecimiento ofrecimientoParam = new com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.param.Ofrecimiento();
		ofrecimientoParam.setOfrecimiento(ofrecimientoIbm);
		OfrecimientoWS ofrecimiento = new OfrecimientoWS();
		AutonomiaWS autonomiaWS = cargarAutonomia(ofrecimientoIbm.getAutonomia());
		ofrecimiento.setAutonomia(autonomiaWS);
		ofrecimiento.setControlDeConsumo(ofrecimientoIbm.getControlDeConsumo());
		ofrecimiento.setCostoDeInstalacion(ofrecimientoIbm.getCostoDeInstalacion());
		GarantiaWS garantiaWS = cargarGarantia(ofrecimientoIbm.getGarantia());
		ofrecimiento.setGarantia(garantiaWS);
		ofrecimiento.setLimiteDeCreditoCobranza(ofrecimientoIbm.getLimiteDeCreditoCobranza());
		ofrecimiento.setMontoTopeAutomatico(ofrecimientoIbm.getMontoTopeAutomatico());
		OpcionDeCuotasWS opcionDeCuotasWS = cargarOpcionDeCuotas(ofrecimientoIbm.getOpcionDeCuotas());
		ofrecimiento.setOpcionDeCuotas(opcionDeCuotasWS);
		ofrecimiento.setPrioridadPublicar(TipoSiNo.fromValue(ofrecimientoIbm.getPrioridadPublicar().toString()));
		ofrecimiento.setProcesoDeExoneracionDeRentas(TipoSiNo.fromValue(ofrecimientoIbm.getProcesoDeExoneracionDeRentas().toString()));
		ofrecimiento.setProcesoIDValidator(ofrecimientoIbm.getProcesoIDValidator());
		ofrecimiento.setPublicar(TipoSiNo.valueOf(ofrecimientoIbm.getPublicar().toString()));
		ofrecimiento.setRestriccion(TipoSiNo.valueOf(ofrecimientoIbm.getRestriccion().toString()));
		ResultadosAdicionalesWS resultadosAdicionalesWS = cargarResultadosAdicionales(ofrecimientoIbm.getResultadosAdicionales());
		ofrecimiento.setResultadosAdicionales(resultadosAdicionalesWS);
		List<RespuestaProactivaWS> listRespuestaProactivaWS = cargarVListaPlan(ofrecimientoIbm.getVLISTAPLAN());
		ofrecimiento.setVlistaplan(listRespuestaProactivaWS);
		List<CuotaWS> cuotaWSs = cargarCuotaLista(ofrecimientoIbm.getCuota());
		ofrecimiento.setCuota(cuotaWSs);
		return ofrecimiento;
	}

	public AutonomiaWS cargarAutonomia(Autonomia autonomiaIbm) {
		AutonomiaWS autonomia = new AutonomiaWS();
		autonomia.setCantidadDeLineasAdicionalesRUC(autonomiaIbm.getCantidadDeLineasAdicionalesRUC());
		autonomia.setCantidadDeLineasMaximas(autonomiaIbm.getCantidadDeLineasMaximas());
		autonomia.setMontoCFParaRUC(autonomiaIbm.getMontoCFParaRUC());
		autonomia.setTipoDeAutonomiaCargoFijo(autonomiaIbm.getTipoDeAutonomiaCargoFijo());
		return autonomia;
	}

	public List<CuotaWS> cargarCuotaLista(List<Cuota> cuotaIbm) {
		List<CuotaWS> listcuota = new ArrayList<>();
		for (Cuota cuotaTemp : cuotaIbm) {
			CuotaWS cuotaResponse = new CuotaWS();
			cuotaResponse.setCantidad(cuotaTemp.getCantidad());
			cuotaResponse.setPorcentajeInicial(cuotaTemp.getPorcentajeInicial());
			listcuota.add(cuotaResponse);
		}
		return listcuota;
	}

	public GarantiaWS cargarGarantia(Garantia garantiaIbm) {
		GarantiaWS garantiaWS = new GarantiaWS();
		garantiaWS.setCantidadDeAplicacionesRenta(garantiaIbm.getCantidadDeAplicacionesRenta());
		garantiaWS.setFrecuenciaDeAplicacionMensual(garantiaIbm.getFrecuenciaDeAplicacionMensual());
		garantiaWS.setMesInicioRentas(garantiaIbm.getMesInicioRentas());
		garantiaWS.setMontoDeGarantia(garantiaIbm.getMontoDeGarantia());
		garantiaWS.setTipoDeGarantia(garantiaIbm.getTipoDeGarantia());
		garantiaWS.setTipodecobro(TipoDeCobro.valueOf(garantiaIbm.getTipodecobro().toString()));
		return garantiaWS;
	}

	public OpcionDeCuotasWS cargarOpcionDeCuotas(OpcionDeCuotas opcionDeCuotasIbm) {
		OpcionDeCuotasWS opcionDeCuotasWS = new OpcionDeCuotasWS();
		opcionDeCuotasWS.setCuotaMaxima(opcionDeCuotasIbm.getCuotaMaxima());
		opcionDeCuotasWS.setMostrarRespuesta(opcionDeCuotasIbm.getMostrarRespuesta());
		opcionDeCuotasWS.setTopeMaximo(opcionDeCuotasIbm.getTopeMaximo());
		return opcionDeCuotasWS;
	}

	public ResultadosAdicionalesWS cargarResultadosAdicionales(ResultadosAdicionales resultadosAdicionalesIbm) {
		ResultadosAdicionalesWS resultadosAdicionalesWS = new ResultadosAdicionalesWS();
		resultadosAdicionalesWS.setComportamientoConsolidado(resultadosAdicionalesIbm.getComportamientoConsolidado());
		resultadosAdicionalesWS.setComportamientoDePagoC1(resultadosAdicionalesIbm.getComportamientoDePagoC1());
		resultadosAdicionalesWS.setCostoTotalEquipos(resultadosAdicionalesIbm.getCostoTotalEquipos());
		resultadosAdicionalesWS.setFactorDeEndeudamientoCliente(resultadosAdicionalesIbm.getFactorDeEndeudamientoCliente());
		resultadosAdicionalesWS.setFactorDeRenovacionCliente(resultadosAdicionalesIbm.getFactorDeRenovacionCliente());
		resultadosAdicionalesWS.setPrecioDeVentaTotalEquipos(resultadosAdicionalesIbm.getPrecioDeVentaTotalEquipos());
		resultadosAdicionalesWS.setRiesgoTotalEquipo(resultadosAdicionalesIbm.getRiesgoTotalEquipo());
		resultadosAdicionalesWS.setCapacidadDePago(resultadosAdicionalesIbm.getCapacidadDePago());
		resultadosAdicionalesWS.setRiesgoEnClaro(resultadosAdicionalesIbm.getRiesgoEnClaro());
		return resultadosAdicionalesWS;
	}

	public List<RespuestaProactivaWS> cargarVListaPlan(List<RespuestaProactiva> respuestaProactivaIbm) {
		List<RespuestaProactivaWS> respuestaProactivaWS = new ArrayList<>();
		for (RespuestaProactiva respuestaProactivaTemp : respuestaProactivaIbm) {
			RespuestaProactivaWS respuestaProactivaResponse = new RespuestaProactivaWS();
			respuestaProactivaResponse.setCantidadDeLineasAdicionalesRUC(respuestaProactivaTemp.getCantidadDeLineasAdicionalesRUC());
			respuestaProactivaResponse.setCantidadDeLineasMaximas(respuestaProactivaTemp.getCantidadDeLineasMaximas());
			respuestaProactivaResponse.setFactorDeEndeudamientoCliente(respuestaProactivaTemp.getFactorDeEndeudamientoCliente());
			respuestaProactivaResponse.setFactorDeRenovacionCliente(respuestaProactivaTemp.getFactorDeRenovacionCliente());
			respuestaProactivaResponse.setMontoCFParaRUC(respuestaProactivaTemp.getMontoCFParaRUC());
			respuestaProactivaResponse.setMontoDeGarantia(respuestaProactivaTemp.getMontoDeGarantia());
			respuestaProactivaResponse.setPrecioDeVentaTotalEquipos(respuestaProactivaTemp.getPrecioDeVentaTotalEquipos());
			respuestaProactivaResponse.setRiesgoTotalEquipo(respuestaProactivaTemp.getRiesgoTotalEquipo());
			respuestaProactivaResponse.setDescripcion(respuestaProactivaTemp.getDescripcion());
			respuestaProactivaWS.add(respuestaProactivaResponse);
		}
		return respuestaProactivaWS;
	}	
	
	public void filtrarPvuConTipoCuotaResponse(List<PvuConTipoCuotaResponse> listPvuConTipoCuotaResponse,
			ClaroEvalClientesReglasResponse responseWS, EvaluarDatosClienteResponseWS evaluarDatosClienteResponseWS) {
		PvuConTipoCuotaResponseWS pvuConTipoCuotaResponseWSTemp = new PvuConTipoCuotaResponseWS();
		for (PvuConTipoCuotaResponse pvuConTipoCuotaResponse : listPvuConTipoCuotaResponse) {
			for (int i = 0; i < responseWS.getOfrecimiento().getOfrecimiento().getCuota().size(); i++) {
				if (responseWS.getOfrecimiento().getOfrecimiento().getCuota().get(i).getCantidad() == pvuConTipoCuotaResponse.getCuonVigencia()) {
					pvuConTipoCuotaResponseWSTemp.setCuocCodigo(pvuConTipoCuotaResponse.getCuocCodigo());
					pvuConTipoCuotaResponseWSTemp.setCuonVigencia(pvuConTipoCuotaResponse.getCuonVigencia());
					pvuConTipoCuotaResponseWSTemp.setCuovDescripcion(pvuConTipoCuotaResponse.getCuovDescripcion());
					evaluarDatosClienteResponseWS.getOfrecimiento().getCuota().get(i).setPvuConTipoCuotaResponseWS(pvuConTipoCuotaResponseWSTemp);
				}
			}
		}
	}
	
	public void cargarRequestDesicionService(PropertiesExternos propertiesExternos,
			String mensajeLog, EvaluarDatosClienteRequestWS request, ClaroEvalClienteAuxiliarBeanWS objClaroEvalBeanAux, ClaroEvalClientesReglasRequest reqClaroEval) {
		
//		ClaroEvalClientesReglasRequest reqClaroEval = new ClaroEvalClientesReglasRequest();
		reqClaroEval.setSolicitud(new Solicitud());
		reqClaroEval.getSolicitud().setSolicitud(new com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.Solicitud());
		reqClaroEval.getSolicitud().getSolicitud().setBuroConsultado(objClaroEvalBeanAux.getBuroConsultado());
		reqClaroEval.getSolicitud().getSolicitud().setCliente(new Cliente());
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setCantidadDeLineasActivas(objClaroEvalBeanAux.getCantidadDeLineasActivas());
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setCantidadDePlanesPorProducto(objClaroEvalBeanAux.getCantidadDePlanesPorProducto());
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setCantidadDeRepresentantesLegales(Constantes.CERO);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setCantidadMaximaCuotasPendientes(Constantes.CERO);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setCantidadMaximaCuotasPendientesSistema(Constantes.CERO);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setCantidadMaximaCuotasPendientesUltimasVentas(Constantes.CERO);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setCantidadTotalPlanesCuotasPendientes(Constantes.CERO);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setComportamientoConsolidado(Constantes.CERO);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setComportamientoDePago(objClaroEvalBeanAux.getComportamientoDePago() != null ? objClaroEvalBeanAux.getComportamientoDePago().intValue() : 0);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setComportamientoDePagoC1(Constantes.CERO);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setCreditScore(objClaroEvalBeanAux.getCreditScore() != null ? objClaroEvalBeanAux.getCreditScore().doubleValue() : 0.0);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setDeuda(objClaroEvalBeanAux.getDeuda());
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setDocumento(new Documento());
		reqClaroEval.getSolicitud().getSolicitud().getCliente().getDocumento().setDescripcion(TipoDeDocumento.DNI);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().getDocumento().setNumero(String.valueOf(request.getNumeroDocumento()));
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setEdad(objClaroEvalBeanAux.getEdad() != null ? objClaroEvalBeanAux.getEdad().intValue() : 0);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setFactorDeEndeudamiento(Constantes.CERODOUBLE);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setFactorDeRenovacion(Constantes.CERODOUBLE);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setFacturacionPromedioClaro(objClaroEvalBeanAux.getFacturacionPromedioClaro() != null ? objClaroEvalBeanAux.getFacturacionPromedioClaro() : 0.0);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setFacturacionPromedioProducto(objClaroEvalBeanAux.getFacturacionPromedioProducto() != null ? objClaroEvalBeanAux.getFacturacionPromedioProducto() : 0.0);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setLimiteDeCreditoDisponible(objClaroEvalBeanAux.getLimiteDeCreditoDisponible() != null ? objClaroEvalBeanAux.getLimiteDeCreditoDisponible() : 0.0);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setRiesgo(objClaroEvalBeanAux.getRiesgo() != null ? objClaroEvalBeanAux.getRiesgo() : "");
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setSexo(Constantes.VACIO);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setTiempoDePermanencia(objClaroEvalBeanAux.getTiempoDePermanencia() != null ? objClaroEvalBeanAux.getTiempoDePermanencia() : 0);
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setTipo(objClaroEvalBeanAux.getTipoCliente() != null ? objClaroEvalBeanAux.getTipoCliente() : "");
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setSegmento(objClaroEvalBeanAux.getSegmentoCliente());
		//7 Nuevas variables BRMS
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setMontoDeudaVencida(objClaroEvalBeanAux.getMontoDeudaVencida());
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setMontoDeudaCastigada(objClaroEvalBeanAux.getMontoDeudaCastigada());
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setMontoDisputa(objClaroEvalBeanAux.getMontoDisputa());
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setCantidadMontoDisputa(objClaroEvalBeanAux.getCantidadMontoDisputa());
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setAntiguedadMontoDisputa(objClaroEvalBeanAux.getAntiguedadMontoDisputa());
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setMontoTotalDeuda(objClaroEvalBeanAux.getMontoTotalDeuda());
		reqClaroEval.getSolicitud().getSolicitud().getCliente().setAntiguedadDeuda(objClaroEvalBeanAux.getAntiguedadDeuda());				
		reqClaroEval.getSolicitud().getSolicitud().setFechaEjecucion(objClaroEvalBeanAux.getFechaEjecucion());		
		reqClaroEval.getSolicitud().getSolicitud().setFlagDeLicitacion(com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.TipoSiNo.NO);
		reqClaroEval.getSolicitud().getSolicitud().setHoraEjecucion(objClaroEvalBeanAux.getFechaEjecucion().getHour());
		reqClaroEval.getSolicitud().getSolicitud().setOferta(new Oferta());
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setCampana(new com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.Campana());
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getCampana().setTipo(objClaroEvalBeanAux.getTipoCampana() != null ? objClaroEvalBeanAux.getTipoCampana() : "");		
		if (objClaroEvalBeanAux.getTipoDeOperacion().equals(Constantes.OPERACION_PORTABILIDAD)){
			reqClaroEval.getSolicitud().getSolicitud().getOferta().setModalidadCedente(objClaroEvalBeanAux.getModalidadCedente());
			reqClaroEval.getSolicitud().getSolicitud().getOferta().setOperadorCedente(objClaroEvalBeanAux.getOperadorCedente().toUpperCase());
		}else if (objClaroEvalBeanAux.getTipoDeOperacion().equals(Constantes.OPERACION_ALTA)){
			reqClaroEval.getSolicitud().getSolicitud().getOferta().setModalidadCedente(null);
			reqClaroEval.getSolicitud().getSolicitud().getOferta().setOperadorCedente(null);
		}		
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setCantidadLineasSEC(objClaroEvalBeanAux.getCantidadLineasSEC() != null ? objClaroEvalBeanAux.getCantidadLineasSEC() : 0);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setCasoEpecial(Constantes.REGULAR);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setCombo(Constantes.VACIO);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setKitDeInstalacion(Constantes.VACIO);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setControlDeConsumo(request.getControlDeConsumo());
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setMesOperadorCedente(objClaroEvalBeanAux.getMesOperadorCedente() != null ? objClaroEvalBeanAux.getMesOperadorCedente() : 0);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setMontoCFSEC(objClaroEvalBeanAux.getMontoCFSEC() != null ? objClaroEvalBeanAux.getMontoCFSEC() : 0.0);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setPlanSolicitado(new PlanSolicitado());
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getPlanSolicitado().setCargoFijo(objClaroEvalBeanAux.getCargoFijo() != null ? objClaroEvalBeanAux.getCargoFijo() : 0.0);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getPlanSolicitado().setDescripcion(objClaroEvalBeanAux.getDescripcionPlanSolicitado() != null ? objClaroEvalBeanAux.getDescripcionPlanSolicitado() : "");
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getPlanSolicitado().setPaquete(Constantes.VACIO);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getPlanSolicitado().getServicio().add(new Servicio());
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getPlanSolicitado().getServicio().get(Constantes.CERO).setGrupo(objClaroEvalBeanAux.getGrupoServicioPlanSolicOf());
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getPlanSolicitado().getServicio().get(Constantes.CERO).setNombre(objClaroEvalBeanAux.getNombreServicioPlanSolicOf());
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setPlazoDeAcuerdo(objClaroEvalBeanAux.getPlazoDeAcuerdoOf());
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setProductoComercial(Constantes.TIPODEPRODUCTOMOVIL);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setSegmentoDeOferta(Constantes.SEGMENTODEOFERTAMASIVO);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setTipoDeOperacionEmpresa(Constantes.CONSTANTE_VACIA);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setTipoDeProducto(objClaroEvalBeanAux.getTipoDeProducto());
		//Plan Actual
		reqClaroEval.getSolicitud().getSolicitud().getOferta().setPlanActual(new PlanActual());
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getPlanActual().setCargoFijo(objClaroEvalBeanAux.getPlanActualCargoFijo()!= null ? Double.parseDouble(objClaroEvalBeanAux.getPlanActualCargoFijo()) : Constantes.CERODOUBLE);;
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getPlanActual().setDescripcion(objClaroEvalBeanAux.getPlanActualDescripcion()!= null ? objClaroEvalBeanAux.getPlanActualDescripcion() : Constantes.VACIO);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getPlanActual().setCantidadCuotasPendientes(Constantes.CERO);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getPlanActual().setMesesParaCubrirApadece(objClaroEvalBeanAux.getPlanActualMesesParaCubrirApadece()!= null ? Integer.parseInt(objClaroEvalBeanAux.getPlanActualMesesParaCubrirApadece()) : Constantes.CERO);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getPlanActual().setMontoPendienteCuotas(Constantes.CERO);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getPlanActual().setPlazoDeAcuerdo(objClaroEvalBeanAux.getPlanActualPlazoAcuerdo()!= null ? Double.parseDouble(objClaroEvalBeanAux.getPlanActualPlazoAcuerdo()) : Constantes.CERODOUBLE);
		reqClaroEval.getSolicitud().getSolicitud().getOferta().getPlanActual().setTiempoPermanencia(objClaroEvalBeanAux.getPlanActualTiempoPermanencia()!= null ? Integer.parseInt(objClaroEvalBeanAux.getPlanActualTiempoPermanencia()) : Constantes.CERO);
		
		reqClaroEval.getSolicitud().getSolicitud().setPuntodeVenta(new PuntodeVenta());
		reqClaroEval.getSolicitud().getSolicitud().getPuntodeVenta().setCalidadDeVendedor(objClaroEvalBeanAux.getCalidadVendedor()!= null ? objClaroEvalBeanAux.getCalidadVendedor() : Constantes.SCERO);
		reqClaroEval.getSolicitud().getSolicitud().getPuntodeVenta().setCanal(objClaroEvalBeanAux.getCanal() != null ? objClaroEvalBeanAux.getCanal() : "");
		reqClaroEval.getSolicitud().getSolicitud().getPuntodeVenta().setCodigo(objClaroEvalBeanAux.getCodigo() != null ? objClaroEvalBeanAux.getCodigo() : "");
		reqClaroEval.getSolicitud().getSolicitud().getPuntodeVenta().setDescripcion(objClaroEvalBeanAux.getDescipcion() != null ? objClaroEvalBeanAux.getDescipcion() : "");
		reqClaroEval.getSolicitud().getSolicitud().getPuntodeVenta().setDireccion(new Direccion());
		reqClaroEval.getSolicitud().getSolicitud().getPuntodeVenta().getDireccion().setCodigoDePlano(Constantes.CONSTANTE_VACIA);
		reqClaroEval.getSolicitud().getSolicitud().getPuntodeVenta().getDireccion().setDepartamento(objClaroEvalBeanAux.getDepartamento() != null ? objClaroEvalBeanAux.getDepartamento() : "");
		reqClaroEval.getSolicitud().getSolicitud().getPuntodeVenta().getDireccion().setDistrito(objClaroEvalBeanAux.getDistrito() != null ? objClaroEvalBeanAux.getDistrito() : "");
		reqClaroEval.getSolicitud().getSolicitud().getPuntodeVenta().getDireccion().setProvincia(objClaroEvalBeanAux.getProvincia() != null ? objClaroEvalBeanAux.getProvincia() : "");
		reqClaroEval.getSolicitud().getSolicitud().getPuntodeVenta().getDireccion().setRegion(objClaroEvalBeanAux.getRegion() != null ? objClaroEvalBeanAux.getRegion() : "");
		reqClaroEval.getSolicitud().getSolicitud().setTipoDeBolsa(Constantes.CONSTANTE_VACIA);
		reqClaroEval.getSolicitud().getSolicitud().setTipoDeDespacho(Constantes.TIPODEDESPACHOPDV);
		reqClaroEval.getSolicitud().getSolicitud().setTipoDeOperacion(request.getTipoDeOperacion() != null ? request.getTipoDeOperacion() : "");
		Equipo brmsEquipo = new Equipo();		
		brmsEquipo.setFormaDePago((request.getFlagModoOperacion().equals(Constantes.MODO_OPERACION_CONTADO) ||  request.getFlagModoOperacion().equals(Constantes.MODO_OPERACION_CONTADO_RENOVACION))? propertiesExternos.brmsEquipoFormaPago : propertiesExternos.brmsEquipoFormaPagoCuotas);
		brmsEquipo.setFactorDePagoInicial(Constantes.CERODOUBLE);
		brmsEquipo.setGama(propertiesExternos.brmsEquipoGama);
		if(request.getTipoDeOperacion().equals(Constantes.OPERACION_ALTA) || request.getTransaccion().equals(Constantes.VACIO)) {
			brmsEquipo.setModelo(Constantes.VACIO);
			brmsEquipo.setCosto(Constantes.CERODOUBLE);
		}else {
			brmsEquipo.setModelo(objClaroEvalBeanAux.getModeloEquipo()!= null ? objClaroEvalBeanAux.getModeloEquipo() : "");
			brmsEquipo.setCosto(objClaroEvalBeanAux.getCostoEquipo()!= null ? Double.parseDouble(objClaroEvalBeanAux.getCostoEquipo()) : 0.0);
		}
		brmsEquipo.setPrecioDeVenta(objClaroEvalBeanAux.getPrecioVenta());
		brmsEquipo.setTipoDeDeco(Constantes.VACIO);
		brmsEquipo.setTipoOperacionKit(Constantes.VACIO);
		if (request.getTransaccion().equalsIgnoreCase(Constantes.TRANSACCIONVENTAENCUOTAS)) {
			brmsEquipo.setMontoDeCuota(Constantes.CERODOUBLE);
			brmsEquipo.setMontoDeCuotaComercial(objClaroEvalBeanAux.getMontoCuotaComercial()!= null ? objClaroEvalBeanAux.getMontoCuotaComercial() : Constantes.CERODOUBLE);
			brmsEquipo.setMontoDeCuotaInicialComercial(objClaroEvalBeanAux.getMontoCuotaInicialComercial() !=null ? objClaroEvalBeanAux.getMontoCuotaInicialComercial() : Constantes.CERODOUBLE);
			brmsEquipo.setPorcentajecuotaInicial(Double.parseDouble(propertiesExternos.brmsEquipoPorcentajeCuotaInicial));
		} else if (request.getTransaccion().equalsIgnoreCase(Constantes.TRANSACCIONEVALUACIONCREDITICIA) && request.getFlagModoOperacion().equals(Constantes.MODO_OPERACION_NORMAL)) {
			if(request.getCodListaPrecio()!= null) {
				String [] temporalPorcentajeCuoIni = request.getCodListaPrecio().split("\\|");
				brmsEquipo.setPorcentajecuotaInicial(temporalPorcentajeCuoIni.length > Constantes.UNO ? Double.parseDouble(temporalPorcentajeCuoIni[1]) : Constantes.CERODOUBLE);//Eliminar split despues que pase el parametro en datapower
			}
			brmsEquipo.setMontoDeCuota(request.getMontoCuota() != Constantes.NULLOBJECTO ? request.getMontoCuota() : Constantes.CERODOUBLE);
			brmsEquipo.setMontoDeCuotaComercial(objClaroEvalBeanAux.getMontoCuotaComercial() != null ? objClaroEvalBeanAux.getMontoCuotaComercial() : Constantes.CERODOUBLE);			
			brmsEquipo.setMontoDeCuotaInicialComercial(request.getCuotaInicial()!= null ? request.getCuotaInicial() : Constantes.CERODOUBLE);						
		}else if(Constantes.MODO_OPERACION_CONTADO.equals(request.getFlagModoOperacion()) || Constantes.VACIO.equals(request.getTransaccion())) {
			brmsEquipo.setPorcentajecuotaInicial(Double.parseDouble(propertiesExternos.brmsEquipoPorcentajeCuotaInicial));
		}else if(Constantes.MODO_OPERACION_CONTADO_RENOVACION.equals(request.getFlagModoOperacion())) {
			brmsEquipo.setPorcentajecuotaInicial(Double.parseDouble(propertiesExternos.brmsEquipoPorcentajeCuotaInicial));
		}
		brmsEquipo.setCuotas(request.getNumeroCuotas());
		brmsEquipo.setFactorDeSubsidio(Constantes.CERODOUBLE);
		brmsEquipo.setRiesgo(Integer.parseInt(propertiesExternos.brmsEquipoRiesgo));
		brmsEquipo.setTipoOperacionKit(Constantes.VACIO);
		reqClaroEval.getSolicitud().getSolicitud().getEquipo().add(brmsEquipo);
		reqClaroEval.getSolicitud().getSolicitud().setTotalPlanes(Constantes.UNO);
		reqClaroEval.getSolicitud().getSolicitud().setTransaccion(!request.getTransaccion().equals(Constantes.VACIO) ? request.getTransaccion() : Constantes.TRANSACCIONEVALUACIONCREDITICIA);
	}
	
	public String obtenerFechaActivacionSopocPVU(String mensajeLog, List<DatosResponse> responseLista) {

		Date fechaActivacion = null;
		String fechaActivacionString = "";
		LOG.info(mensajeLog+ " Extrayendo fecha de Activacion de PVU...");
		for (DatosResponse obj : responseLista) {
			fechaActivacion = obj.getSopodFecActivacion();
			LOG.info(mensajeLog+ " Se obtuvo exitosamente la fecha de Activacion de PVU...");
		}
		LOG.info(mensajeLog+ " Fin de Busqueda de fecha de Activacion. Fecha obtenida:"+ fechaActivacion);
		fechaActivacionString = ClaroUtil.dateAString(fechaActivacion);
		return fechaActivacionString;
	}
	
	public void actualizarDatosClienteBuro(List<ClienteType> listaCliente, EvaluarDatosClienteRequestWS request) {
		request.setLcDcPVU(Float.parseFloat(listaCliente.get(Constantes.CERO).getLcDisponible()));
		request.setEssaludSunatPVU(listaCliente.get(Constantes.CERO).getTipoCliente());
		request.setRiesgoPVU(listaCliente.get(Constantes.CERO).getAccion());		
	}
	
	public String obtenerDescripcionCargoFijo(List<BscsDXLpCurDetalleBean> listBscsCurDetalle, EvaluarDatosClienteRequestWS request) {
		
		String descripcionCargoFijo = Constantes.VACIO;		
		for(BscsDXLpCurDetalleBean bscsDXLpCurDetalleBean  : listBscsCurDetalle) {
			if(request.getLineaCliente().equals(bscsDXLpCurDetalleBean.getNUMERO())) {
				descripcionCargoFijo = bscsDXLpCurDetalleBean.getPLAN();
				break;
			}
		}
		return descripcionCargoFijo;
	}
	
	public void cargarParametroRequest(ParametroRequest parametroRequest, PropertiesExternos propertiesExternos) {
		parametroRequest.setSistema(propertiesExternos.parametroRequestSistema);
		parametroRequest.setVersion(propertiesExternos.parametroRequestVersion);
		parametroRequest.setTipoOperacion(propertiesExternos.parametroRequestTipoOperacion);
		parametroRequest.setGrupo(propertiesExternos.parametroRequestGrupo);
		parametroRequest.setCodigo(propertiesExternos.parametroRequestCodigo);
	}
	
	public String obtenerCadenaLineasActivasBSCS(String mensajeLog, List<BscsDXLpCurDetalleBean> responseLista){

		LOG.info(mensajeLog+ " INICIO Obtener lineas activas (ESTADO = "+ Constantes.VALORCONDICIONALACTIVO+ ") del cliente en BSCS");
		String cadenaLineasActivas = Constantes.CONSTANTE_VACIA;
		for (BscsDXLpCurDetalleBean object : responseLista) {
			if (object.getESTADO().equalsIgnoreCase(Constantes.VALORCONDICIONALACTIVO)) {
				cadenaLineasActivas += Constantes.PALOTE + object.getNUMERO();
			}
		}
		if (cadenaLineasActivas.equalsIgnoreCase(Constantes.CONSTANTE_VACIA)) {
			LOG.info(mensajeLog+ " El cliente NO cuenta con lineas Activas en BSCS. Lineas Activas: [ "+ cadenaLineasActivas+ "]");
		} else {
			LOG.info(mensajeLog+ " El cliente SI cuenta con lineas Activas en BSCS. Lineas Activas: [  "+ cadenaLineasActivas+ "]");
		}
		LOG.info(mensajeLog, " FIN Obtener lineas activas (ESTADO =  "+ Constantes.VALORCONDICIONALACTIVO + ") del cliente en BSCS");
		return cadenaLineasActivas;
	}
	
	public ResponsePrecioBase cargarResponsePrecioBase(List<ResponsePrecioBase> listResponsePrecioBase){

		ResponsePrecioBase response = new ResponsePrecioBase();
		for (ResponsePrecioBase responsePrecioBase : listResponsePrecioBase) {
			response.setCodMaterial(responsePrecioBase.getCodMaterial());
			response.setDesMaterial(responsePrecioBase.getDesMaterial());
			response.setPrecioCompra(responsePrecioBase.getPrecioCompra());
			response.setPrecioBase(responsePrecioBase.getPrecioBase());
		}
		return response;
	}
	
	public String obtenerPConvValorPVU(String mensajeLog, List<Pvu3Play6ParamGeneralBeanResponse> response,
			String codigoGeneral){

		String valorBuscado = Constantes.CONSTANTE_VACIA;

		LOG.info(mensajeLog+ " Buscando el valor[PCONV_VALOR] para el codigo:  ["+ codigoGeneral+ "] del cursor");

		if (response != null) {

			List<Pvu3Play6ParamGeneralBeanResponse> listaGnral = response;
			for (Pvu3Play6ParamGeneralBeanResponse obj : listaGnral) {
				if (obj.getpConiCodigo().equalsIgnoreCase(codigoGeneral)) {
					valorBuscado = obj.getpConvValor();
				}
			}
		} else {
			LOG.info(mensajeLog+ " El cursor se encuentra Nulo o Vacio");
		}
		return valorBuscado;
	}
	
	public HeaderResponse validarCamposObligatorios(PropertiesExternos propertiesExternos, String mensajeLog,
			EvaluarDatosClienteRequestWS request, String idTx) {

		HeaderResponse auditResponse = new HeaderResponse();
		if (request.getFlagModoOperacion() == Constantes.NULLOBJECTO || request.getFlagModoOperacion().isEmpty()
				|| request.getNumeroDocumento() == Constantes.NULLOBJECTO || request.getNumeroDocumento().isEmpty()
				|| request.getOficinaPVU() == Constantes.NULLOBJECTO || request.getOficinaPVU().isEmpty()
				|| request.getTipoDeOperacion() == Constantes.NULLOBJECTO || request.getTipoDeOperacion().isEmpty()) {
			LOG.info(mensajeLog+ " Campos Obligatiorios faltantes.");
			auditResponse.setCodigoRespuesta(propertiesExternos.idf3Codigo);
			auditResponse.setMensajeRespuesta(propertiesExternos.idf3Mensaje.replace(Constantes.REPLACECAMPOS, "auditRequest/nombreAplicacion, FlagModoOperacion, NumeroDocumento, OficinaPVU, ModalidadCedente, TipoDeOperacion"));
			auditResponse.setIdTransaccion(idTx);
		} else {
			LOG.info(mensajeLog+ " Campos Obligatiorios Completos.");
			auditResponse.setCodigoRespuesta(propertiesExternos.idf0Codigo);
			auditResponse.setMensajeRespuesta(propertiesExternos.idf0Mensaje);
			auditResponse.setIdTransaccion(idTx);
		}
		LOG.info(mensajeLog+ " [Fin 1.1 Validar Campos Obligatorios validarCamposObligatorios()]");
		return auditResponse;
	}
	
	public void cargarProcesaConsultaDatosClienteBRMSOAC(PropertiesExternos propertiesExternos, String mensajeLog,
			EvaluarDatosClienteRequestWS request, String idTx) throws WSClientException, DBException {
		
		ConsultarDeudaCuentaBRMSDbRequestType consultarDeudaCuentaBRMSDbRequestType = new ConsultarDeudaCuentaBRMSDbRequestType();
		ParametroRequest parametroRequest = new ParametroRequest();
		cargarParametroRequest(parametroRequest, propertiesExternos);
		consultarDeudaCuentaBRMSDbRequestType.setPNNROMESESDISPUTA(String.valueOf(Constantes.TRES));
		consultarDeudaCuentaBRMSDbRequestType.setPVCLITIPODOCIDENT(Constantes.CODTIPODOCUMENTODNIOAC);
		consultarDeudaCuentaBRMSDbRequestType.setPVCLINRODOCIDENT(request.getNumeroDocumento());
		consultarDeudaCuentaBRMSDbRequestType.setPVTRXIDWS(idTx);
		consultarDeudaCuentaBRMSDbRequestType.setPVCODAPLICACION(Constantes.APLICACION);
		consultarDeudaCuentaBRMSDbRequestType.setPVUSUARIOAPLIC(Constantes.USRAPLICACION);
		}
	
	public Integer getSumatoriaDeCampoXCantidadRegistrosSGA(String mensajeLog, List<SgaDetalleInfCurBean> lista){

		int cantSRVAcumulado = Constantes.CERO;
		for (SgaDetalleInfCurBean obj : lista) {
			if (obj.getEstado().equalsIgnoreCase(Constantes.VALORCONDICIONALACTIVO)) {
				cantSRVAcumulado += Integer.parseInt(obj.getCant_srv());
			}
		}
		LOG.info(mensajeLog+ " [Valor encontrado cantSRVAcumulado: "+ cantSRVAcumulado);
		return cantSRVAcumulado;
	}
	
}
