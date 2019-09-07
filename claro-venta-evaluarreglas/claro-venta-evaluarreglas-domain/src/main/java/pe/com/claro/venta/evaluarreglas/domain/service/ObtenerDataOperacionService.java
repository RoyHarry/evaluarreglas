package pe.com.claro.venta.evaluarreglas.domain.service;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.ClaroEvalClientesReglasRequest;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.ClaroEvalClientesReglasResponse;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.RespuestaProactiva;

import pe.com.claro.common.bean.HeaderRequest;
import pe.com.claro.common.bean.HeaderResponse;
import pe.com.claro.common.bean.ParametrosTypeWS;
import pe.com.claro.common.bean.ParametrosTypeWS.ObjetoOpcional;
import pe.com.claro.common.exception.DBException;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.resource.exception.WSException;
import pe.com.claro.common.util.JAXBUtilitarios;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.eai.ebs.ventas.ebscreditosws.ws.types.ConsultarDatosDCResponse;
import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.AuditType;
import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.ConsultaSegmentoRequest;
import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.ConsultaSegmentoResponse;
import pe.com.claro.eai.oacservices.inquiry.consultacuotacliente.ConsultaCuotaClienteRequest;
import pe.com.claro.eai.oacservices.inquiry.consultacuotacliente.ConsultaCuotaClienteResponse;
import pe.com.claro.eai.ws.baseschema.AuditRequestType;
import pe.com.claro.eai.ws.postventa.gestionacuerdo.types.ObtenerReintegroEquipoRequest;
import pe.com.claro.eai.ws.postventa.gestionacuerdo.types.ObtenerReintegroEquipoResponse;
import pe.com.claro.esb.message.test.consultardeudacuentabrms_db.v1.ConsultarDeudaCuentaBRMSDbRequestType;
import pe.com.claro.esb.message.test.consultardeudacuentabrms_db.v1.ConsultarDeudaCuentaBRMSDbResponseType;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes.EvaluarDatosClienteRequestWS;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.parametro.ParametroRequest;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.ClaroEvalClienteAuxiliarBeanWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.EvaluarDatosClienteResponseWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.OfrecimientoWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.PlanesTypesWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.RespClaroEvalPlanSolic;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.ebscreditos.ClienteType;
import pe.com.claro.venta.evaluarreglas.domain.bean.CalcLimiteCreditoDisponibleBean;
import pe.com.claro.venta.evaluarreglas.domain.dao.ObtenerDataOperacionBSCSDao;
import pe.com.claro.venta.evaluarreglas.domain.dao.ObtenerDataOperacionDao;
import pe.com.claro.venta.evaluarreglas.domain.dao.ObtenerDataOperacionMSSAPDao;
import pe.com.claro.venta.evaluarreglas.domain.dao.ObtenerDataOperacionSGADao;
import pe.com.claro.venta.evaluarreglas.domain.dto.UtilService;
import pe.com.claro.venta.evaluarreglas.domain.util.UtilClaroEvalParametros;
import pe.com.claro.venta.evaluarreglas.domain.util.Utilitarios;
import pe.com.claro.venta.evaluarreglas.integration.client.ClaroEvalSoapLocal;
import pe.com.claro.venta.evaluarreglas.integration.client.ConsultaCuotaClienteOAC;
import pe.com.claro.venta.evaluarreglas.integration.client.ConsultaDatosClienteBRMSOAC;
import pe.com.claro.venta.evaluarreglas.integration.client.ConsultaDeudasSoapLocal;
import pe.com.claro.venta.evaluarreglas.integration.client.ConsultaSegmentoClienteSoapLocal;
import pe.com.claro.venta.evaluarreglas.integration.client.GestionAcuerdoWS;
import pe.com.claro.venta.evaluarreglas.integration.client.IEbsCreditosWS;
import pe.com.claro.venta.evaluarreglas.integration.client.impl.ClaroEvalSoapLocalImpl;
import pe.com.claro.venta.evaluarreglas.integration.client.impl.ConsultaCuotaClienteOACImpl;
import pe.com.claro.venta.evaluarreglas.integration.client.impl.ConsultaDatosClienteBRMSOACImpl;
import pe.com.claro.venta.evaluarreglas.integration.client.impl.ConsultaDeudasSoapLocalImpl;
import pe.com.claro.venta.evaluarreglas.integration.client.impl.ConsultaSegmentoClienteSoapLocalImpl;
import pe.com.claro.venta.evaluarreglas.integration.client.impl.EbsCreditosWSImpl;
import pe.com.claro.venta.evaluarreglas.integration.client.impl.GestionAcuerdoWSImpl;
import pe.com.claro.venta.evaluarreglas.model.BRMSBioMovilRequest;
import pe.com.claro.venta.evaluarreglas.model.BRMSBioMovilResponse;
import pe.com.claro.venta.evaluarreglas.model.BscsDetalleCFXContrato;
import pe.com.claro.venta.evaluarreglas.model.BscsDetalleXLineaBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.BscsTim127ComPagoBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.BscsTim127ComPagoBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.CalcFacturacionPromedioBean;
import pe.com.claro.venta.evaluarreglas.model.ClienteEvalRequest;
import pe.com.claro.venta.evaluarreglas.model.ClienteEvalResponse;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ConDetalleLineaBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ParamGeneralBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ParamGeneralBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuConTipoCuotaResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuConsultaCVEPendRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuConsultaCVEPendResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuCuotaIniComercialRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuCuotaIniComercialResponse;
import pe.com.claro.venta.evaluarreglas.model.RequestPrecioBase;
import pe.com.claro.venta.evaluarreglas.model.ResponsePrecioBase;
import pe.com.claro.venta.evaluarreglas.model.SgaGetInformacionClienteBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.SgaGetInformacionClienteBeanResponse;

@Stateless
public class ObtenerDataOperacionService {

	private static final Logger LOG = LoggerFactory.getLogger(ObtenerDataOperacionService.class);

	@EJB
	private ObtenerDataOperacionDao obtenerDataOperacionDao;

	@EJB
	private ObtenerDataOperacionMSSAPDao obtenerDataOperacionMSSAPDao;

	@EJB
	private ObtenerDataOperacionBSCSDao obtenerDataOperacionBSCSDao;
	
	@EJB
	private ObtenerDataOperacionSGADao obtenerDataOperacionSGADao;

	@EJB
	private UtilClaroEvalParametros utilInterface;

	@EJB
	private Utilitarios utilImpl;
	
	@EJB
	private UtilService dtoUtilService;
	
	ClaroEvalSoapLocal claroEvalSoapLocalImpl = new ClaroEvalSoapLocalImpl();
	
	ConsultaDeudasSoapLocal consultaDeudaCuentaClientImpl = new ConsultaDeudasSoapLocalImpl();
	
	ConsultaDatosClienteBRMSOAC consultaDatosClienteBRMSOACImpl = new ConsultaDatosClienteBRMSOACImpl();
		
	ConsultaSegmentoClienteSoapLocal consultaSegmentoClienteSoapLocalImpl = new ConsultaSegmentoClienteSoapLocalImpl();
	
	ConsultaCuotaClienteOAC consultaCuotaClienteOACImpl = new ConsultaCuotaClienteOACImpl();
	
	GestionAcuerdoWS gestionAcuerdoWSImpl = new GestionAcuerdoWSImpl();
	
	IEbsCreditosWS ebsCreditosWSImpl = new EbsCreditosWSImpl();		

	public EvaluarDatosClienteResponseWS evaluarDatosCliente(PropertiesExternos propertiesExternos,
			EvaluarDatosClienteRequestWS request, String idTx, HeaderRequest headerRequest) throws UnknownHostException {
		
		dtoUtilService.eliminarNulos(request);
		
		String methodName = Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName();

		LOG.info("============================== [1.0 INICIO METODO PRINICIPAL] - " + methodName + " ==============================");
		LOG.info("Parametros de entrada del metodo[ "+ methodName+ "]: \n " + JAXBUtilitarios.anyObjectToXmlText(request));
		HeaderResponse auditResponse = new HeaderResponse();

		String mensajeLog = "IdTrx" + idTx  + ": ] " + "[ " + methodName + " ]";

		EvaluarDatosClienteResponseWS response = new EvaluarDatosClienteResponseWS();
		HeaderResponse respCamposObligatorios = new HeaderResponse();
		LOG.info(mensajeLog + "[1.1 INICIO Validar Campos Obligatorios]");		
		respCamposObligatorios = dtoUtilService.validarCamposObligatorios(propertiesExternos, mensajeLog, request, idTx);
		LOG.info(mensajeLog + "[1.1 FIN Validar Campos Obligatorios]");				
		try {
			String nroDocumentoCompleto = Constantes.CONSTANTE_VACIA;
			String cerosAdicionales = Constantes.CONSTANTE_VACIA;
			LOG.info(mensajeLog + "Validando Tamanio de numero de documento");
			if (request.getNumeroDocumento().length() < Constantes.DNI_LONGITUD) {
				LOG.info(mensajeLog + " El Tamanio del numero de documento es menor a: "+ Constantes.DNI_LONGITUD);
				for (int i = Constantes.UNO; i <= (Constantes.DNI_LONGITUD - request.getNumeroDocumento().length()); i++) {
					cerosAdicionales += Constantes.CERO;
				}
				nroDocumentoCompleto = cerosAdicionales + request.getNumeroDocumento();
				request.setNumeroDocumento(nroDocumentoCompleto);
				LOG.info(mensajeLog + "Numero de Documento Completo: [" + request.getNumeroDocumento()+ "]");
			} else {
				LOG.info(mensajeLog + "La longitud del numero de documento es correcta. Continuando con el flujo");
			}

			if (!respCamposObligatorios.getCodigoRespuesta().equalsIgnoreCase(Constantes.CODIGOESTANDAREXITO)) {
				auditResponse.setCodigoRespuesta(respCamposObligatorios.getCodigoRespuesta());
				auditResponse.setMensajeRespuesta(respCamposObligatorios.getMensajeRespuesta());
				auditResponse.setIdTransaccion(idTx);
			} else {
				
				LOG.info(mensajeLog+ " Validacion de parametros obligatorios exitosa");
				LOG.info(mensajeLog+ " [INICIO 1.2 Obtener datos para ClaroEvalClienteReglas]");
				LOG.info(mensajeLog+ " [INICIO 1.2.1 Obtener Parametros Generales PVU]");
				List<Pvu3Play6ParamGeneralBeanResponse> respParametrosGneralesPVU = new ArrayList<>();
				Pvu3Play6ParamGeneralBeanRequest req = new Pvu3Play6ParamGeneralBeanRequest();
				req.setP_codigo(Constantes.SUNO);
				respParametrosGneralesPVU = obtenerDataOperacionDao.obtenerParametroGeneralPVU(propertiesExternos, req, mensajeLog);			
				LOG.info(mensajeLog+ " [FIN 1.2.1 Obtener Parametros Generales PVU]");
				
				LOG.info(mensajeLog+ " [INICIO 1.2.2 Obtener precio base equipo]");				
				RequestPrecioBase requestPrecioBase = new RequestPrecioBase();
				requestPrecioBase.setCodMaterial(request.getCodigoMaterial());
				List<ResponsePrecioBase> listResponsePrecioBase = obtenerDataOperacionMSSAPDao.obtenerPrecioBaseEquipo(propertiesExternos, requestPrecioBase, mensajeLog);								
				ResponsePrecioBase responsePrecioBase = dtoUtilService.cargarResponsePrecioBase(listResponsePrecioBase);				
				LOG.info(mensajeLog+ " Costo del equipo "+ responsePrecioBase.getPrecioCompra());
				LOG.info(mensajeLog+ " [FIN 1.2.2 Obtener precio base equipo]");
				
				LOG.info(mensajeLog+ " [INICIO 1.2.3 Obtener cantidad de meses para cliente SGA]");				
				String cantMesesFormulaRecurr = Constantes.CONSTANTE_VACIA;
				cantMesesFormulaRecurr = dtoUtilService.obtenerPConvValorPVU(mensajeLog, respParametrosGneralesPVU, propertiesExternos.paramGenralCodigo);
				LOG.info(mensajeLog+ " Se obtuvo el valor buscado para SGA Exitosamente: ["+cantMesesFormulaRecurr+"]");
				LOG.info(mensajeLog+ " [FIN 1.2.3 Obtener cantidad de meses para cliente SGA]");
				
				ClaroEvalClienteAuxiliarBeanWS objClaroEvalBeanAux = new ClaroEvalClienteAuxiliarBeanWS();				
				LOG.info(mensajeLog+ " [INICIO 1.2.4 Obtener informacion de pago BSCS]");				
				BscsTim127ComPagoBeanRequest reqComPago = new BscsTim127ComPagoBeanRequest();
				reqComPago.setP_num_doc(String.valueOf(request.getNumeroDocumento()));
				reqComPago.setP_tip_doc(Constantes.CODTIPODOCUMENTODNIBSCS);
				BscsTim127ComPagoBeanResponse respTim127ComPagoBSCS = obtenerDataOperacionBSCSDao.tim127ComPagoBSCS(propertiesExternos, reqComPago, mensajeLog);								
				LOG.info(mensajeLog+ " Parametros de salida del metodo procesaComPagoBSCS "+ JAXBUtilitarios.anyObjectToXmlText(respTim127ComPagoBSCS));
				LOG.info(mensajeLog+ " [FIN 1.2.4 Obtener informacion de pago BSCS]");
				
				LOG.info(mensajeLog+ " [INICIO 1.2.5 Obtener informacion de Detalle de Linea BSCS]");				
								
				BscsTim127ComPagoBeanRequest bscsTim127ComPagoBeanRequest = new BscsTim127ComPagoBeanRequest();
				bscsTim127ComPagoBeanRequest.setP_num_doc(String.valueOf(request.getNumeroDocumento()));
				bscsTim127ComPagoBeanRequest.setP_tip_doc(Constantes.CODTIPODOCUMENTODNIBSCS);
				BscsDetalleXLineaBeanResponse respDetalleXLineaBSCS = obtenerDataOperacionBSCSDao.timComPagoBSCS(propertiesExternos,bscsTim127ComPagoBeanRequest, mensajeLog);				
				LOG.info(mensajeLog+ " Parametros de salida del metodo procesaDetalleXLineaBSCS " + JAXBUtilitarios.anyObjectToXmlText(respDetalleXLineaBSCS));
				LOG.info(mensajeLog+ " [FIN 1.2.5 Obtener informacion de Detalle de Linea BSCS]");
				
				LOG.info(mensajeLog+ " [INICIO 1.2.6 Obtener informacion de cliente SGA]");				
				SgaGetInformacionClienteBeanResponse respClienteServiciosSGA = new SgaGetInformacionClienteBeanResponse();
				respClienteServiciosSGA = procesaClienteServiciosSGA(propertiesExternos, request, String.valueOf(cantMesesFormulaRecurr), mensajeLog);				
				LOG.info(mensajeLog+ " Parametros de salida del metodo procesaClienteServiciosSGA "+ JAXBUtilitarios.anyObjectToXmlText(respClienteServiciosSGA));
				LOG.info(mensajeLog+ " [FIN 1.2.6 Obtener informacion de cliente SGA]");
				
				LOG.info(mensajeLog+ " [INICIO 1.2.7 Calcular la facturacion Promedio]");
				CalcFacturacionPromedioBean respCalcFacturacionPromedio = new CalcFacturacionPromedioBean();
				respCalcFacturacionPromedio = utilInterface.calcFacturacionPromedio(propertiesExternos, mensajeLog, request, respDetalleXLineaBSCS, respClienteServiciosSGA);				
				LOG.info(mensajeLog+ " Parametros de salida del metodo utilInterface calcFacturacionPromedio: "+ JAXBUtilitarios.anyObjectToXmlText(respCalcFacturacionPromedio));//
				ArrayList<Double> listaFacturacionPromedio = new ArrayList<>();
				listaFacturacionPromedio = respCalcFacturacionPromedio.getListaCalcFacturacionPromedio();
				objClaroEvalBeanAux.setFacturacionPromedioClaro(listaFacturacionPromedio.get(Constantes.CERO));				
				objClaroEvalBeanAux.setFacturacionPromedioProducto(listaFacturacionPromedio.get(Constantes.UNO));				
				Pvu3Play6ConDetalleLineaBeanResponse obj3PlayDetalleLineaPVU = new Pvu3Play6ConDetalleLineaBeanResponse();
				obj3PlayDetalleLineaPVU = respCalcFacturacionPromedio.getObj3PlayDetalleLineaPVU();				
				LOG.info(mensajeLog+ " Parametros de salida del metodo obj3PlayDetalleLineaPVU "+ JAXBUtilitarios.anyObjectToXmlText(respCalcFacturacionPromedio.getObj3PlayDetalleLineaPVU()));
				LOG.info(mensajeLog+ " Parametros de salida del metodo respCalcFacturacionPromedio.getObj3PlayDetalleLineaPVU() " +JAXBUtilitarios.anyObjectToXmlText(obj3PlayDetalleLineaPVU));
				LOG.info(mensajeLog+ " [FIN 1.2.7 Calcular la facturacion Promedio]");				
				
				LOG.info(mensajeLog+  " [INICIO 1.2.8 Generar cadena de lineas Activas]");				
				String cadenaLineasActivasBSCS = Constantes.CONSTANTE_VACIA;
				cadenaLineasActivasBSCS = dtoUtilService.obtenerCadenaLineasActivasBSCS(mensajeLog, respDetalleXLineaBSCS.getP_CUR_DETALLE());				
				LOG.info(mensajeLog+ " Lista de lineas Activas: [ "+ cadenaLineasActivasBSCS +"]");
				LOG.info(mensajeLog+ " [FIN 1.2.8 Generar cadena de lineas Activas]");												
				ClienteEvalRequest respDatsEvalCliReglasPVU = new ClienteEvalRequest();				
				respDatsEvalCliReglasPVU.setPiBuroCod(request.getCodigoBuroPVU());
				respDatsEvalCliReglasPVU.setPiLineasActivas(Constantes.SCERO);
				respDatsEvalCliReglasPVU.setPiNroOperacion(request.getNroOperacionPVU());
				respDatsEvalCliReglasPVU.setPiNumDoc(String.valueOf(request.getNumeroDocumento()));
				respDatsEvalCliReglasPVU.setPiOficina(request.getOficinaPVU());
				respDatsEvalCliReglasPVU.setPiTipoDocCod(Constantes.CODIGO_01);
				ClienteEvalResponse clienteEvalResponse = null;
				if(request.getTipoDeOperacion().equalsIgnoreCase(Constantes.RENOVACION) || request.getTipoDeOperacion().equalsIgnoreCase(Constantes.ALTARENOVACION)) {
					LOG.info(mensajeLog+ " [INICIO 1.2.9.1 Obtener informacion de Evaluacion de Cliente PVU para Renovacion]");
					LOG.info(mensajeLog+ " Parametros de entrada del metodo procesarDatsEvalCliReglasPVU "+ JAXBUtilitarios.anyObjectToXmlText(respDatsEvalCliReglasPVU));				
					clienteEvalResponse = obtenerDataOperacionDao.getDataValidaClienteReglasRenovacion(propertiesExternos, respDatsEvalCliReglasPVU, mensajeLog);				
					LOG.info(mensajeLog+ " [FIN 1.2.9.1 Obtener informacion de Evaluacion de Cliente PVU para Renovacion]");
					
					LOG.info(mensajeLog+ " [INICIO 1.2.10 Obtener Meses Pendientes, Plazo de Acuerdo y Tiempo Permanencia de GestionAcuerdoWS]");				
					ObtenerReintegroEquipoResponse obtenerReintegroEquipoResponse = obtenerReintegroEquipo(propertiesExternos, mensajeLog, request, headerRequest);
					objClaroEvalBeanAux.setPlanActualMesesParaCubrirApadece(obtenerReintegroEquipoResponse.getMesesPendientes()!= null ? obtenerReintegroEquipoResponse.getMesesPendientes() : "0");
					objClaroEvalBeanAux.setPlanActualPlazoAcuerdo(obtenerReintegroEquipoResponse.getAcuerdoVigenciaMeses()!= null ? obtenerReintegroEquipoResponse.getAcuerdoVigenciaMeses() : "0");
					objClaroEvalBeanAux.setPlanActualTiempoPermanencia(obtenerReintegroEquipoResponse.getAcuerdoFechaInicio()!= null ? String.valueOf(utilImpl.calcularCantidadMesesTranscurridos(mensajeLog, obtenerReintegroEquipoResponse.getAcuerdoFechaInicio())) : "0");
					LOG.info(mensajeLog+ " [FIN 1.2.10 Obtener Meses Pendientes, Plazo de Acuerdo y Tiempo Permanencia de GestionAcuerdoWS]");				
					
					LOG.info(mensajeLog+ " [INICIO 1.2.11 Obtener Descripcion de Cargo Fijo de plan actual]");								
					String descripcionCargoFIjo = dtoUtilService.obtenerDescripcionCargoFijo(respDetalleXLineaBSCS.getP_CUR_DETALLE(), request);
					objClaroEvalBeanAux.setPlanActualDescripcion(descripcionCargoFIjo.toUpperCase());
					LOG.info(mensajeLog+ " [FIN 1.2.11 Obtener Descripcion de cargo fijo de plan actual]");				
					
					LOG.info(mensajeLog+ " [INICIO 1.2.12 Obtener cargo fijo de plan actual]");								
					BscsDetalleCFXContrato bscsDetalleCFXContrato = obtenerDataOperacionBSCSDao.obtenerCargoFijoBscs(propertiesExternos, (obtenerReintegroEquipoResponse.getCoId() != null ? String.valueOf(obtenerReintegroEquipoResponse.getCoId()) : Constantes.SCERO), mensajeLog);
					
					objClaroEvalBeanAux.setPlanActualCargoFijo(bscsDetalleCFXContrato.getPoCargoFijo() != null ? String.valueOf(bscsDetalleCFXContrato.getPoCargoFijo().doubleValue()) : Constantes.SCERO);
					LOG.info(mensajeLog+ " [FIN 1.2.12 Obtener cargo fijo de plan actual]");
					
				}else {
					LOG.info(mensajeLog+ " [INICIO 1.2.9 Obtener informacion de Evaluacion de Cliente PVU]");
					LOG.info(mensajeLog+ " Parametros de entrada del metodo procesarDatsEvalCliReglasPVU "+ JAXBUtilitarios.anyObjectToXmlText(respDatsEvalCliReglasPVU));				
					clienteEvalResponse = obtenerDataOperacionDao.dataOperacionPvuV2(propertiesExternos, respDatsEvalCliReglasPVU, mensajeLog);				
					LOG.info(mensajeLog+ " [FIN 1.2.9 Obtener informacion de Evaluacion de Cliente PVU]");
					
					objClaroEvalBeanAux.setPlanActualMesesParaCubrirApadece(null);
					objClaroEvalBeanAux.setPlanActualPlazoAcuerdo(null);
					objClaroEvalBeanAux.setPlanActualTiempoPermanencia(null);
					objClaroEvalBeanAux.setPlanActualDescripcion(null);
					objClaroEvalBeanAux.setPlanActualCargoFijo(null);
				}
				LOG.info(mensajeLog+ " [INICIO 1.2.13 Consultar Segmento de Cliente]");
				ConsultaSegmentoResponse consultaSegmentoResponse = new ConsultaSegmentoResponse();
				consultaSegmentoResponse = procesaConsultaSegmentoClienteWS(propertiesExternos, mensajeLog, request, idTx);
				LOG.info(mensajeLog+ " [FIN 1.2.13 Consultar Segmento de Cliente]");
								
				LOG.info(mensajeLog+ " [INICIO 1.2.14 Calcular cantidad de Lineas Activas Total]");				
				int cantRegClaroEvalPVU = clienteEvalResponse.getListDetalleRequest()!= null ? clienteEvalResponse.getListDetalleRequest().size() : Constantes.CERO;
				
				LOG.info(mensajeLog+ " Cantidad de Registros obtenidos de PVU del cursor Po_cursor_detalle: ["+ cantRegClaroEvalPVU+ "]");
				LOG.info(mensajeLog+ " Inicio Obteniendo Cantidad de lineas ACTIVAS en BSCS");
				
				int cantidadLineasActivasBSCS = Constantes.CERO;
				if (respDetalleXLineaBSCS.getP_CUR_CLIENTE().size() > Constantes.CERO) {
					cantidadLineasActivasBSCS = respDetalleXLineaBSCS.getP_CUR_CLIENTE().get(Constantes.CERO).getPLANES();
				} else {
					LOG.info(mensajeLog+ " El cliente NO cuenta con lineas activas en BSCS");
				}
				
				LOG.info(mensajeLog+ " Cantidad de lineas ACTIVAS en BSCS obtenidas (PLANES): ["+ cantidadLineasActivasBSCS+ "]");
				LOG.info(mensajeLog+ " Fin Obteniendo Cantidad de lineas ACTIVAS en BSCS");

				int sumatoriaCantSRVSGA = Constantes.CERO;
				if (respClienteServiciosSGA.getP_detalles_inf() != null) {
					if (respClienteServiciosSGA.getP_detalles_inf().size() > 0) {
						sumatoriaCantSRVSGA = dtoUtilService.getSumatoriaDeCampoXCantidadRegistrosSGA(mensajeLog, respClienteServiciosSGA.getP_detalles_inf());
					}
				}

				LOG.info(mensajeLog+ " Sumatoria del campo ["+ Constantes.CANTSRV+ "] buscado en SGA: ["+ sumatoriaCantSRVSGA+ "]");

				int cantLineasActivasTotal = cantidadLineasActivasBSCS + sumatoriaCantSRVSGA;
				
				LOG.info(mensajeLog+ " Cantidad de Total de lineas Activas: ["+ cantLineasActivasTotal+ "]");
				LOG.info(mensajeLog+ " [FIN 1.2.14 Calcular cantidad de Lineas Activas Total]");
				
				LOG.info(mensajeLog+ " INICIO 1.2.15 Calculo de los meses de Permanencia");				
				int intMesesPermanencia = Constantes.CERO;				
				LOG.info(mensajeLog+ " Validando si se cuenta con DATA suficiente para realizar las operaciones requeridas");
				if (respClienteServiciosSGA.getP_detalles_inf() != null) {
					if (respClienteServiciosSGA.getP_detalles_inf().size() > Constantes.CERO && respDetalleXLineaBSCS.getP_CUR_DETALLE().size() > Constantes.CERO) {
						LOG.info(mensajeLog+ " El cursor p_detalles_inf de SGA contiene data y el Cursor P_CUR_DETALLE tambien ");
					} else {
						if (respClienteServiciosSGA.getP_detalles_inf().size() == Constantes.CERO && respDetalleXLineaBSCS.getP_CUR_DETALLE().size() == Constantes.CERO) {
							LOG.info(mensajeLog+ " Alerta! No se cuenta con data de los cursores p_detalles_inf de SGA y P_CUR_DETALLE de BSCS para realizar las operaciones pendientes");
						} else if (respDetalleXLineaBSCS.getP_CUR_DETALLE().size() == Constantes.CERO) {
							LOG.info(mensajeLog+ " Alerta! No se cuenta con data sufiente de los cursores P_CUR_DETALLE de BSCS para realizar las operaciones pendientes");
						} else {
							LOG.info(mensajeLog+ " Alerta! No se cuenta con data sufiente de los cursores p_detalles_inf de SGA para realizar las operaciones pendientes");
						}
					}
				}
				intMesesPermanencia = utilImpl.obtenerMesesDePermanencia(propertiesExternos, mensajeLog, respClienteServiciosSGA, respDetalleXLineaBSCS);
				
				LOG.info(mensajeLog+ " Meses de Permanencia FINAL: ["+ intMesesPermanencia+ "]");
				LOG.info(mensajeLog+ " FIN 1.2.15 Calculo de los meses de Permanencia");
				LOG.info(mensajeLog+ " Evaluando cual es el tipo del cliente segun el tiempo de permanencia...");				
				
				LOG.info(mensajeLog+ " INICIO 1.2.16 Obteniendo el umbral de permanencia");
				String umbral = Constantes.CONSTANTE_VACIA;
				umbral = dtoUtilService.obtenerPConvValorPVU(mensajeLog, respParametrosGneralesPVU, propertiesExternos.paramGeneralCodigoUmbral);				
				LOG.info(mensajeLog+ " FIN 1.2.16 Obteniendo el umbral de permanencia. Valor obtenido: ["+ umbral+ "]");
				
				LOG.info(mensajeLog+ " INICIO 1.2.17 Obtener tipo de cliente");
				String tipoCliente = Constantes.CLIENTENUEVO;
				String tipoClienteSec = Constantes.SCERO;
				if (intMesesPermanencia >= Integer.parseInt(umbral)) {
					tipoClienteSec = Constantes.SUNO;
					tipoCliente = Constantes.CLIENTECLARO;
				} else {
					tipoClienteSec = Constantes.SCERO;
					tipoCliente = Constantes.CLIENTENUEVO;
				}
				LOG.info(mensajeLog+ " El tipo de Cliente es: ["+ tipoCliente+ "]");
				LOG.info(mensajeLog+ " FIN 1.2.17 Obtener tipo de cliente");
				
				if (clienteEvalResponse.getListDatosRequest()!= null && clienteEvalResponse.getListDatosRequest().size() != Constantes.CERO) {

					objClaroEvalBeanAux.setBuroConsultado(clienteEvalResponse.getListDatosRequest().get(Constantes.CERO).getBuroDes());
					objClaroEvalBeanAux.setCantidadDeLineasActivas(cantLineasActivasTotal);
					int cantidadPlanesxProducto = cantLineasActivasTotal;
					objClaroEvalBeanAux.setCantidadDePlanesPorProducto(cantidadPlanesxProducto);
					objClaroEvalBeanAux.setComportamientoDePago(respTim127ComPagoBSCS.getP_indicador());
					objClaroEvalBeanAux.setEdad(clienteEvalResponse.getListDatosRequest().get(Constantes.CERO).getEdad());
					objClaroEvalBeanAux.setCreditScore(new BigDecimal(clienteEvalResponse.getListDatosRequest().get(Constantes.CERO).getPuntaje()));
					objClaroEvalBeanAux.setRiesgo(String.valueOf(clienteEvalResponse.getListDatosRequest().get(Constantes.CERO).getRiesgo()));

					LOG.info(mensajeLog+ " [INICIO 1.2.18 Calcular Limite de Credito Disponible]");					
					CalcLimiteCreditoDisponibleBean objLimitCredito = new CalcLimiteCreditoDisponibleBean();
					Double lcDisponible = 0.0;
					String strMontosxBilletera = Constantes.CONSTANTE_VACIA;
					objLimitCredito = utilInterface.calcularLimiteCreditoDisponible(propertiesExternos, mensajeLog, request, cantLineasActivasTotal);
					lcDisponible = objLimitCredito.getLcDisponible();
					strMontosxBilletera = objLimitCredito.getStrMontosxBilletera();
					objClaroEvalBeanAux.setLimiteDeCreditoDisponible(lcDisponible);					
					LOG.info(mensajeLog+ " [FIN 1.2.18 Calcular Limite de Credito Disponible]");
										
					String dblUmbralDeuda = dtoUtilService.obtenerPConvValorPVU(mensajeLog, respParametrosGneralesPVU, Constantes.CONSTCODUMBRALDEUDA);
					String dblPorcentajeDeuda = dtoUtilService.obtenerPConvValorPVU(mensajeLog, respParametrosGneralesPVU, Constantes.PORCENTAJEDEUDA);														
					LOG.info(mensajeLog+" [dblUmbralDeuda]: "+ dblUmbralDeuda);					
					LOG.info(mensajeLog+" [dblPorcentajeDeuda]: "+ dblPorcentajeDeuda);					
					LOG.info(mensajeLog+" [respCalcFacturacionPromedio.getMontoFacturadoTotal()]: "+ respCalcFacturacionPromedio.getMontoFacturadoTotal());	
					LOG.info(mensajeLog+ " [INICIO 1.2.19 Obtener Monto deuda vencida, castigada, disputa, cantidad disputa, antiguedad disputa]");											
					ConsultarDeudaCuentaBRMSDbResponseType consultarDeudaCuentaBRMSDbResponseType = new ConsultarDeudaCuentaBRMSDbResponseType();
					consultarDeudaCuentaBRMSDbResponseType = procesaConsultaDatosClienteBRMSOAC(propertiesExternos, mensajeLog, request, idTx);
					BigDecimal xDeudaVencidaMovil = new BigDecimal(Constantes.CERO);
					BigDecimal xDeudaCastigada = new BigDecimal(Constantes.CERO);
					BigDecimal xDeudaTotal = new BigDecimal(Constantes.CERO);
					if(consultarDeudaCuentaBRMSDbResponseType!= null && consultarDeudaCuentaBRMSDbResponseType.getResponseData().getConsultar().get(0).getXVSTATUS().equalsIgnoreCase(String.valueOf(Constantes.CERO))) {
						xDeudaVencidaMovil =  new BigDecimal(consultarDeudaCuentaBRMSDbResponseType.getResponseData().getConsultar()!= null ? consultarDeudaCuentaBRMSDbResponseType.getResponseData().getConsultar().get(Constantes.CERO).
								getXTESTADOCUENTA().get(Constantes.CERO).getDEUDAVENCIDA() : "0");
						xDeudaCastigada = new BigDecimal(consultarDeudaCuentaBRMSDbResponseType.getResponseData().getConsultar()!= null ?
								consultarDeudaCuentaBRMSDbResponseType.getResponseData().getConsultar().get(Constantes.CERO).getXTESTADOCUENTA().get(Constantes.CERO).getDEUDACASTIGADA() : "0");
						xDeudaTotal = xDeudaVencidaMovil.add(xDeudaCastigada);
						//Nuevas varibales
						objClaroEvalBeanAux.setMontoDeudaVencida(xDeudaVencidaMovil.doubleValue());
						objClaroEvalBeanAux.setMontoDeudaCastigada(xDeudaCastigada.doubleValue());
						objClaroEvalBeanAux.setMontoDisputa(Double.parseDouble(consultarDeudaCuentaBRMSDbResponseType.getResponseData().getConsultar().get(Constantes.CERO).getXTESTADOCUENTA().get(Constantes.CERO).getMONTODISPUTA()));
						objClaroEvalBeanAux.setCantidadMontoDisputa(Integer.parseInt(consultarDeudaCuentaBRMSDbResponseType.getResponseData().getConsultar().get(Constantes.CERO).getXTESTADOCUENTA().get(Constantes.CERO).getCANTIDADDISPUTA()));
						objClaroEvalBeanAux.setAntiguedadMontoDisputa(Integer.parseInt(consultarDeudaCuentaBRMSDbResponseType.getResponseData().getConsultar().get(Constantes.CERO).getXTESTADOCUENTA().get(Constantes.CERO).getANTDISPUTA()));
						objClaroEvalBeanAux.setMontoTotalDeuda(Constantes.CERODOUBLE);
						objClaroEvalBeanAux.setAntiguedadDeuda(Integer.parseInt(consultarDeudaCuentaBRMSDbResponseType.getResponseData().getConsultar().get(Constantes.CERO).getXTESTADOCUENTA().get(Constantes.CERO).getANTDEUDA()));						
					}else {
						xDeudaVencidaMovil =  new BigDecimal(Constantes.CERO);
						xDeudaCastigada = new BigDecimal(Constantes.CERO);
						xDeudaTotal = xDeudaVencidaMovil.add(xDeudaCastigada);						
						//Nuevas varibales
						objClaroEvalBeanAux.setMontoDeudaVencida(xDeudaVencidaMovil.doubleValue());
						objClaroEvalBeanAux.setMontoDeudaCastigada(xDeudaCastigada.doubleValue());
						objClaroEvalBeanAux.setMontoDisputa(Double.parseDouble(Constantes.SCERO));
						objClaroEvalBeanAux.setCantidadMontoDisputa(Integer.parseInt(Constantes.SCERO));
						objClaroEvalBeanAux.setAntiguedadMontoDisputa(Integer.parseInt(Constantes.SCERO));
						objClaroEvalBeanAux.setMontoTotalDeuda(Constantes.CERODOUBLE);
						objClaroEvalBeanAux.setAntiguedadDeuda(Integer.parseInt(Constantes.SCERO));
					}
					LOG.info(mensajeLog+ " [FIN 1.2.19 Obtener Monto deuda vencida, castigada, disputa, cantidad disputa, antiguedad disputa]");																
					LOG.info(mensajeLog+ " [xDeudaTotal] : "+ xDeudaTotal.doubleValue());		
					
					LOG.info(mensajeLog+ " [INICIO 1.2.20 Calcular deuda del cliente]");
					boolean blnDeudaEvaluacion = false;					
					if ((xDeudaTotal.doubleValue() > Double.parseDouble(dblUmbralDeuda)) && (xDeudaTotal.doubleValue() > (Double.parseDouble(dblPorcentajeDeuda) * respCalcFacturacionPromedio.getMontoFacturadoTotal() / 100)))
					{
						blnDeudaEvaluacion = true;
					}					
					objClaroEvalBeanAux.setDeuda( blnDeudaEvaluacion ? Constantes.SI : Constantes.NO);
					LOG.info(mensajeLog+ " [FIN 1.2.20 Calcular deuda del cliente]");
					objClaroEvalBeanAux.setSegmentoCliente(consultaSegmentoResponse.getSegmento()!= null ? consultaSegmentoResponse.getSegmento() : Constantes.VACIO);
					
					LOG.info(mensajeLog+ " [objClaroEvalBeanAux.getDeuda()] :  "+ objClaroEvalBeanAux.getDeuda());										
					LOG.info(mensajeLog+ " [FIN 1.2.20 Calcular deuda del cliente]");					
					
					LOG.info(mensajeLog+ " [INICIO 1.2.21 Obtener montos cantidades pendientes de cuotas en sistemas]");
					//OTRAS NUEVAS 7 VARIBALES
					ConsultaCuotaClienteResponse consultaCuotaClienteResponse = null;					
					consultaCuotaClienteResponse = procesaConsultaCuotaclienteWS(propertiesExternos, mensajeLog, request, idTx);
					if(consultaCuotaClienteResponse!= null) {
						LOG.info("consultaCuotaClienteResponse.getTotalPendCuo(): "+ consultaCuotaClienteResponse.getTotalPendCuo());						
						objClaroEvalBeanAux.setMontoPendienteCuotasSistema(!consultaCuotaClienteResponse.getTotalPendCuo().trim().equalsIgnoreCase(Constantes.VACIO) ? Double.parseDouble(consultaCuotaClienteResponse.getTotalPendCuo()) : Constantes.CERODOUBLE);
						objClaroEvalBeanAux.setCantidadPlanesCuotasPendientesSistema(!consultaCuotaClienteResponse.getCantLineasCuoPend().trim().equals(Constantes.VACIO)? Integer.parseInt(consultaCuotaClienteResponse.getCantLineasCuoPend()) : Constantes.CERO);
						objClaroEvalBeanAux.setCantidadMaximaCuotasPendientesSistema(!consultaCuotaClienteResponse.getCantCuoPend().trim().equals(Constantes.VACIO)? Integer.parseInt(consultaCuotaClienteResponse.getCantCuoPend()) : Constantes.CERO);
						objClaroEvalBeanAux.setCantidadCuotasPendientes(!consultaCuotaClienteResponse.getCantCuoPendLinea().trim().equals(Constantes.VACIO)? Integer.parseInt(consultaCuotaClienteResponse.getCantCuoPendLinea()) : Constantes.CERO);
						objClaroEvalBeanAux.setMontoPendienteCuotas(!consultaCuotaClienteResponse.getMontoPendCuoLinea().trim().equals(Constantes.VACIO) ? Double.parseDouble(consultaCuotaClienteResponse.getMontoPendCuoLinea()) : Constantes.CERODOUBLE);
					}else {
						objClaroEvalBeanAux.setMontoPendienteCuotasSistema(Constantes.CERODOUBLE);
						objClaroEvalBeanAux.setCantidadPlanesCuotasPendientesSistema(Constantes.CERO);
						objClaroEvalBeanAux.setCantidadMaximaCuotasPendientesSistema(Constantes.CERO);
						objClaroEvalBeanAux.setCantidadCuotasPendientes(Constantes.CERO);
						objClaroEvalBeanAux.setMontoPendienteCuotas(Constantes.CERODOUBLE);
					}
					LOG.info(mensajeLog+ " [FIN INICIO 1.2.21 Obtener montos, cantidades pendientes de cuotas en sistemas]");
					LOG.info(mensajeLog+ " [INICIO 1.2.22 Obtener montos, cantidades pendientes de cuotas en ultimas ventas]");
					PvuConsultaCVEPendRequest pvuConsultaCVEPendRequest = new PvuConsultaCVEPendRequest();
					pvuConsultaCVEPendRequest.setP_tipo_doc(Constantes.CODTIPODOCUMENTODNIOAC);
					pvuConsultaCVEPendRequest.setP_nro_doc(request.getNumeroDocumento());
					PvuConsultaCVEPendResponse pvuConsultaCVEPendResponse = obtenerCVEPend(mensajeLog, propertiesExternos, idTx, pvuConsultaCVEPendRequest);
					if(pvuConsultaCVEPendResponse!= null) {
						objClaroEvalBeanAux.setCantidadPlanesCuotasPendientesUltimasVentas(pvuConsultaCVEPendResponse.getP_tot_cant_lin()!= null ? pvuConsultaCVEPendResponse.getP_tot_cant_lin().intValue() : Constantes.CERO);
						objClaroEvalBeanAux.setCantidadMaximaCuotasPendientesUltimasVentas(pvuConsultaCVEPendResponse.getP_tot_imp_sol_pend()!= null ? pvuConsultaCVEPendResponse.getP_tot_imp_sol_pend().intValue() : Constantes.CERO);
						objClaroEvalBeanAux.setMontoPendienteCuotasUltimasVentas(pvuConsultaCVEPendResponse.getP_cant_max_cuo()!= null ? pvuConsultaCVEPendResponse.getP_cant_max_cuo().doubleValue() : Constantes.CERODOUBLE);
					}else {
					objClaroEvalBeanAux.setCantidadPlanesCuotasPendientesUltimasVentas(Constantes.CERO);
					objClaroEvalBeanAux.setCantidadMaximaCuotasPendientesUltimasVentas(Constantes.CERO);
					objClaroEvalBeanAux.setMontoPendienteCuotasUltimasVentas(Constantes.CERODOUBLE);
					}
					LOG.info(mensajeLog+ " [FIN 1.2.22 Obtener montos, cantidades pendientes de cuotas en ultimas ventas]");
					
					objClaroEvalBeanAux.setCalidadVendedor(clienteEvalResponse.getListDatosRequest().get(Constantes.CERO).getCalidadVendedor());															
					objClaroEvalBeanAux.setCanal(clienteEvalResponse.getListDatosRequest().get(Constantes.CERO).getCanal());
					objClaroEvalBeanAux.setCodigo(clienteEvalResponse.getListDatosRequest().get(Constantes.CERO).getCodigo());
					objClaroEvalBeanAux.setDescipcion(clienteEvalResponse.getListDatosRequest().get(Constantes.CERO).getPdv());
					objClaroEvalBeanAux.setDepartamento(clienteEvalResponse.getListDatosRequest().get(Constantes.CERO).getDepartamento());
					objClaroEvalBeanAux.setDistrito(clienteEvalResponse.getListDatosRequest().get(Constantes.CERO).getDistrito());
					objClaroEvalBeanAux.setProvincia(clienteEvalResponse.getListDatosRequest().get(Constantes.CERO).getProvincia());
					objClaroEvalBeanAux.setRegion(clienteEvalResponse.getListDatosRequest().get(Constantes.CERO).getRegion());
					String fechaActivacion = dtoUtilService.obtenerFechaActivacionSopocPVU(mensajeLog, clienteEvalResponse.getListDatosRequest());
					objClaroEvalBeanAux.setMesOperadorCedente(utilImpl.cantidadMesesOperadorCedente(mensajeLog, fechaActivacion));
					objClaroEvalBeanAux.setTipoCampana(request.getTipoCampana() != Constantes.NULLOBJECTO ? request.getTipoCampana().toUpperCase() : Constantes.VACIO);
					objClaroEvalBeanAux.setCantidadLineasSEC(Constantes.UNO);
					objClaroEvalBeanAux.setControlDeConsumo(request.getControlDeConsumo());
					String modalidadCedente = Constantes.CONSTANTE_VACIA;
					if (request.getModalidadCedente().equals(Constantes.CODIGO_01) && request.getModalidadCedente() != null) {
						modalidadCedente = Constantes.MODALIDAD_CEDENTE_PREPAGO;
					}
					if (request.getModalidadCedente().equals(Constantes.CODIGO_02) && request.getModalidadCedente() != null) {
						modalidadCedente = Constantes.MODALIDAD_CEDENTE_POSTPAGO;
					}
					if(request.getTipoDeOperacion().equals(Constantes.RENOVACION)) {
						modalidadCedente = null;
						objClaroEvalBeanAux.setOperadorCedente(null);
					}else {
						objClaroEvalBeanAux.setOperadorCedente(request.getOperadorCedente() != Constantes.NULLOBJECTO ? request.getOperadorCedente().toUpperCase() : Constantes.VACIO);						
					}
					objClaroEvalBeanAux.setModalidadCedente(modalidadCedente);
					objClaroEvalBeanAux.setMontoCFSEC(request.getMontoCFSEC() != Constantes.NULLOBJECTO ? request.getMontoCFSEC() : Constantes.CERODOUBLE);					
					if(request.getTipoDeOperacion().equalsIgnoreCase(Constantes.RENOVACION) || request.getTipoDeOperacion().equalsIgnoreCase(Constantes.ALTARENOVACION)) {
						objClaroEvalBeanAux.setCargoFijo(request.getCargoFijo() - Double.parseDouble(objClaroEvalBeanAux.getPlanActualCargoFijo()));
					}else {
						objClaroEvalBeanAux.setCargoFijo(request.getCargoFijo() != Constantes.NULLOBJECTO ? request.getCargoFijo() : Constantes.CERODOUBLE);						
					}
					objClaroEvalBeanAux.setDescripcionPlanSolicitado(request.getDescripcionPlan().toUpperCase());
					objClaroEvalBeanAux.setPlazoDeAcuerdoOf(request.getPlazoDeAcuerdo()!= null && request.getPlazoDeAcuerdo().equals(Constantes.VACIO) ? Constantes.SINPLAZODEACUERDO : request.getPlazoDeAcuerdo());
					if(objClaroEvalBeanAux.getPlazoDeAcuerdoOf() == null) {
						objClaroEvalBeanAux.setPlazoDeAcuerdoOf(Constantes.VACIO);
					}
					objClaroEvalBeanAux.setTipoDeProducto(Constantes.TIPODEPRODUCTOMOVIL);
					objClaroEvalBeanAux.setTipoDeOperacion(request.getTipoDeOperacion() != Constantes.NULLOBJECTO ? request.getTipoDeOperacion(): Constantes.VACIO);
					objClaroEvalBeanAux.setTiempoDePermanencia(intMesesPermanencia);
					objClaroEvalBeanAux.setCostoEquipo(responsePrecioBase.getPrecioCompra());
					objClaroEvalBeanAux.setModeloEquipo(responsePrecioBase.getDesMaterial());
					objClaroEvalBeanAux.setTipoCliente(tipoCliente);
					objClaroEvalBeanAux.setPrecioVenta(request.getPrecioDeVenta() != null ? request.getPrecioDeVenta() : 0.0);										
					objClaroEvalBeanAux.setFormaDePago((request.getFlagModoOperacion().equals(Constantes.MODO_OPERACION_CONTADO) ||  request.getFlagModoOperacion().equals(Constantes.MODO_OPERACION_CONTADO_RENOVACION))? propertiesExternos.brmsEquipoFormaPago : propertiesExternos.brmsEquipoFormaPagoCuotas);
					Date cDate = new Date();
					GregorianCalendar c = new GregorianCalendar();
					c.setTime(cDate);
					XMLGregorianCalendar xmlGregorian = DatatypeFactory.newInstance().newXMLGregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR), c.get(Calendar.MINUTE), c.get(Calendar.SECOND), c.get(Calendar.MILLISECOND), DatatypeConstants.FIELD_UNDEFINED);								
					objClaroEvalBeanAux.setFechaEjecucion(xmlGregorian);
					
					LOG.info(mensajeLog+ " Fecha de Ejecucion:  "+ objClaroEvalBeanAux.getFechaEjecucion());
					LOG.info(mensajeLog+ " Creando Datos Adicionales....\nDatos Adicionales para el ClaroEvalReglas: "+ JAXBUtilitarios.anyObjectToXmlText(objClaroEvalBeanAux));

					List<PvuConTipoCuotaResponse> listPvuConTipoCuotaResponse = new ArrayList<>();
										
					if (request.getFlagModoOperacion().equals(Constantes.MODO_OPERACION_NORMAL)) {
						LOG.info(mensajeLog+ "+ [INICIO 1.2.23 Obtener cuota inicial comercial]");
						listPvuConTipoCuotaResponse = obtenerDataOperacionDao.obtenerConTipoCuota(mensajeLog, propertiesExternos);
						PvuCuotaIniComercialResponse pvuCuotaIniComercialResponse = new PvuCuotaIniComercialResponse();
						LOG.info(mensajeLog+ " Parametros de salida del metodo obtenerConTipoCuota "+ JAXBUtilitarios.anyObjectToXmlText(listPvuConTipoCuotaResponse));												
						PvuCuotaIniComercialRequest pvuCuotaIniComercialRequest = new PvuCuotaIniComercialRequest();
						String [] tempcodListaPrecio; 
						if (request.getTransaccion().equalsIgnoreCase(Constantes.TRANSACCIONEVALUACIONCREDITICIA)) {
							if(request.getCodListaPrecio()!= null) {
							tempcodListaPrecio = request.getCodListaPrecio().split("\\|");	
							pvuCuotaIniComercialRequest.setP_CODIGOLISTAPRECIO(tempcodListaPrecio[0]);
							}else {
								pvuCuotaIniComercialRequest.setP_CODIGOLISTAPRECIO(Constantes.VACIO);
							}
						}else {
							pvuCuotaIniComercialRequest.setP_CODIGOLISTAPRECIO(request.getCodListaPrecio());
						}	
						pvuCuotaIniComercialRequest.setP_CODMATERIAL(request.getCodigoMaterial());
						pvuCuotaIniComercialRequest.setP_CODPLAZO(request.getCodigoplazo());
						pvuCuotaIniComercialResponse = obtenerDataOperacionDao.obtenerCuotaIniComercial(propertiesExternos, pvuCuotaIniComercialRequest, mensajeLog);
						
						objClaroEvalBeanAux.setMontoCuotaComercial(pvuCuotaIniComercialResponse.getP_MONTOCUOTA());
						objClaroEvalBeanAux.setMontoCuotaInicialComercial(pvuCuotaIniComercialResponse.getP_CUOTAINICIAL());
						LOG.info(mensajeLog+" Parametros de salida del metodo obtenerCuotaIniComercial: "+ JAXBUtilitarios.anyObjectToXmlText(pvuCuotaIniComercialResponse));
						LOG.info(mensajeLog+ " Fin de metodo para obtener Monto de cuota Inicial:  ["+ pvuCuotaIniComercialResponse+ "]");
						LOG.info(mensajeLog+ " [FIN 1.2.23 Obtener cuota inicial comercial]");
					}
					
					LOG.info(mensajeLog+ " [FIN Actividad 1.2 Obtener datos para ClaroEvalClienteReglas]");
					LOG.info(mensajeLog+ " [INICIO 1.3 Realizar evaluacion de plan solicitado]");
					ClaroEvalClientesReglasResponse respClaroEval = procesaClaroEvalClienteReglas(propertiesExternos, mensajeLog, request, objClaroEvalBeanAux);
					LOG.info(mensajeLog+ " [FIN 1.3 Realizar evaluacion de plan solicitado]");
						
					LOG.info(mensajeLog+ "[Inicio  1.4 Consulta la ebsCreditosWS]");					
					if(request.getTipoDeOperacion().equalsIgnoreCase(Constantes.RENOVACION) && respClaroEval.getOfrecimiento().getOfrecimiento().getAutonomia().getCantidadDeLineasRenovaciones().equalsIgnoreCase(Constantes.REQUIEREEVALUACION)) {
						request.setTipoDeOperacion(Constantes.ALTARENOVACION);
						ConsultarDatosDCResponse ebsCreditosResponse = ebsCreditosWSImpl.evaluarCredito(propertiesExternos, mensajeLog, request, headerRequest);
						if (ebsCreditosResponse != null
								&& ebsCreditosResponse.getAuditResponseType().getCodigoRespuesta().equals(Constantes.CERO)
								&& (ebsCreditosResponse.getCadena().contains(propertiesExternos.ebsCreditoswsburoRespuesta13)
										|| ebsCreditosResponse.getCadena()
												.contains(propertiesExternos.ebsCreditoswsburoRespuesta14))) {
							response.setBuroConsultado(ebsCreditosResponse.getBuroConsultado());
							List<ClienteType> listaCliente = new ArrayList<>();
							listaCliente.add(utilImpl.obtenerClienteXML(ebsCreditosResponse.getCadena()));
							response.setCliente(listaCliente);
							dtoUtilService.actualizarDatosClienteBuro(listaCliente, request);
						}						
						LOG.info("[INICIO 1.4.1. Regresar al metodo inicial]");
						evaluarDatosCliente(propertiesExternos, request, idTx, headerRequest);
						LOG.info("[FIN 1.4.1. Regresar al metodo inicial]");
					}else if(request.getTipoDeOperacion().equalsIgnoreCase(Constantes.RENOVACION) && respClaroEval.getOfrecimiento().getOfrecimiento().getAutonomia().getCantidadDeLineasRenovaciones().equalsIgnoreCase(Constantes.APROBADO)){
						LOG.info(mensajeLog+ " Excelente... El Cliente aplica para una renovacion ");
						auditResponse.setCodigoRespuesta(propertiesExternos.idf0Codigo);
						auditResponse.setMensajeRespuesta(propertiesExternos.idf0Mensaje);
						auditResponse.setIdTransaccion(idTx);
					}
					LOG.info(mensajeLog+ "[[Fin  1.4 Consulta la ebsCreditosWS]");
					
					String riesgoClaro = Constantes.CONSTANTE_VACIA;
					Integer comportaPago = Constantes.CERO;
					riesgoClaro = respClaroEval.getOfrecimiento().getOfrecimiento().getResultadosAdicionales().getRiesgoEnClaro();
					comportaPago = respClaroEval.getOfrecimiento().getOfrecimiento().getResultadosAdicionales().getComportamientoConsolidado();					
					ParametrosTypeWS parametrosRespuesta = new ParametrosTypeWS();
					
					OfrecimientoWS ofrecimientoWS = new OfrecimientoWS();
					ofrecimientoWS = dtoUtilService.cargarOfrecimiento(respClaroEval.getOfrecimiento().getOfrecimiento());
					response.setOfrecimiento(ofrecimientoWS);
					response.getOfrecimiento().setPorcentajeMaximoCuota(Double.parseDouble(propertiesExternos.porcentajeMaximoCuota));
					response.getOfrecimiento().setDeuda(objClaroEvalBeanAux.getDeuda());
					dtoUtilService.filtrarPvuConTipoCuotaResponse(listPvuConTipoCuotaResponse, respClaroEval, response);

					PlanesTypesWS listaPlanesType = new PlanesTypesWS();
					List<RespClaroEvalPlanSolic> detallePlanSolicitado = new ArrayList<>();
					RespClaroEvalPlanSolic respPlanSolcitado = new RespClaroEvalPlanSolic();
					respPlanSolcitado.setDescripcionPlan(request.getDescripcionPlan() != null ? request.getDescripcionPlan() : "");
					respPlanSolcitado.setRestriccion(String.valueOf(respClaroEval.getOfrecimiento().getOfrecimiento().getRestriccion()) != null ? String.valueOf(respClaroEval.getOfrecimiento().getOfrecimiento().getRestriccion()) : "");
					respPlanSolcitado.setCantidadDeLineasMaximas(respClaroEval.getOfrecimiento().getOfrecimiento().getAutonomia().getCantidadDeLineasMaximas());
					respPlanSolcitado.setCantidadDeAplicacionesRenta(respClaroEval.getOfrecimiento().getOfrecimiento().getGarantia().getCantidadDeAplicacionesRenta());
					respPlanSolcitado.setCargoFijo(request.getCargoFijo());
					respPlanSolcitado.setMontoDeGarantia(respClaroEval.getOfrecimiento().getOfrecimiento().getGarantia().getMontoDeGarantia());
					detallePlanSolicitado.add(respPlanSolcitado);
					listaPlanesType.setDetallePlanSolicitado(detallePlanSolicitado);
					listaPlanesType.getDetallePlanSolicitado().add(respPlanSolcitado);

					LOG.info(mensajeLog+ " Evaluando si el Plan Solicitado es Aprobado");

					String planes = Constantes.CONSTANTE_VACIA;

					LOG.info(mensajeLog+ " INICIO de Evaluacion del PLAN SOLICITADO:  ["+ respPlanSolcitado.getDescripcionPlan()+ "]");
					LOG.info(mensajeLog+ " Evaluando si el cliente posee Restriccion para el plan solicitado...");
					LOG.info(mensajeLog+ " Restriccion del plan Solicitado: "+ respPlanSolcitado.getRestriccion());
						if (request.getTransaccion().equalsIgnoreCase(Constantes.TRANSACCIONVENTAENCUOTAS)) {
							LOG.info(mensajeLog+ " INICIO de Obtencion de cuotas: ["+ request.getTransaccion() + "]");
							LOG.info(mensajeLog+ " Validando si el cliente aplica para una venta en cuotas...");
							if (respClaroEval.getOfrecimiento().getOfrecimiento().getCuota().size() != Constantes.CERO || respClaroEval.getOfrecimiento().getOfrecimiento().getCuota() != Constantes.NULLOBJECTO) {
								LOG.info(mensajeLog+ " Excelente... El Cliente aplica para las siguientes cuotas "+ JAXBUtilitarios.anyObjectToXmlText(respClaroEval.getOfrecimiento().getOfrecimiento().getCuota()));
								auditResponse.setCodigoRespuesta(propertiesExternos.idf0Codigo);
								auditResponse.setMensajeRespuesta(propertiesExternos.idf0Mensaje);
								auditResponse.setIdTransaccion(idTx);
							} else {
								LOG.info(mensajeLog+ " Para la proxima sera . ... El Cliente no aplica para una venta en cuotas");
								auditResponse.setCodigoRespuesta(propertiesExternos.idf1Codigo);
								auditResponse.setMensajeRespuesta(propertiesExternos.idf1Mensaje);
								auditResponse.setIdTransaccion(idTx);
							}
							} else {
							if (!respPlanSolcitado.getRestriccion().equalsIgnoreCase(Constantes.NO)) {
	
								LOG.info(mensajeLog+ " Evaluacion de Plan ["+ respPlanSolcitado.getDescripcionPlan()+ "] tiene Restriccion +  Desaprobado...");
	
								auditResponse.setCodigoRespuesta(propertiesExternos.idf2Codigo);
								auditResponse.setMensajeRespuesta(propertiesExternos.idf2Mensaje);
								auditResponse.setIdTransaccion(idTx);
							} else {
								LOG.info(mensajeLog+ " Genial!... El Cliente no posee Restricion para el Plan Solicitado : ["+ respPlanSolcitado.getDescripcionPlan()+ "]");	
								LOG.info(mensajeLog+ " Cantidad de Lineas Maximas permitidas segun resultado de la evaluacion: ["+ respPlanSolcitado.getCantidadDeLineasMaximas()+ "]");
								LOG.info(mensajeLog+ " Cantidad de lineas Activas Total: ["+ (cantidadLineasActivasBSCS + sumatoriaCantSRVSGA)+ "]");
								LOG.info(mensajeLog+ " Cantidad de Lineas Evaluadas: ["+ propertiesExternos.cantidadLineasEvaluadas+ "]");
								LOG.info(mensajeLog+ " Evaluando Si el cliente podra superar la Cantidad de lineas maximas con el plan solicitado");
								
								
								boolean cantidadLineasMaximas = true;
								if(Constantes.ALTARENOVACION.equalsIgnoreCase(request.getTipoDeOperacion())) {
									cantidadLineasMaximas = respPlanSolcitado.getCantidadDeLineasMaximas() >= ((cantidadLineasActivasBSCS + sumatoriaCantSRVSGA) + Integer.parseInt(propertiesExternos.cantidadLineasEvaluadas)  - Constantes.UNO);
								}else {
									cantidadLineasMaximas = respPlanSolcitado.getCantidadDeLineasMaximas() >= (cantidadLineasActivasBSCS + sumatoriaCantSRVSGA) + Integer.parseInt(propertiesExternos.cantidadLineasEvaluadas) ;
								}								
								if (cantidadLineasMaximas) {
	
									LOG.info(mensajeLog+ " El Cliente NO superara la cantidad de Lineas Maximas Permitidas para el Plan Solicitado. Continuando con las validaciones.");
									LOG.info(mensajeLog+ " Monto de garantia:" + respClaroEval.getOfrecimiento().getOfrecimiento().getGarantia().getMontoDeGarantia());
	
									if (respClaroEval.getOfrecimiento().getOfrecimiento().getGarantia().getMontoDeGarantia() == Constantes.CERODOUBLE) {
										LOG.info(mensajeLog+ " Excelente... El cliente no tiene Monto de Garantia: ["+ respPlanSolcitado.getMontoDeGarantia()+ "]");
										LOG.info(mensajeLog+ " Agregando el plan solicitado inicialmente a la lista de Planes Aprobados ya que las validaciones resultaron Exitosas");
	
										planes = respPlanSolcitado.getDescripcionPlan() + Constantes.PUNTOCOMA + respPlanSolcitado.getCargoFijo() + Constantes.PALOTE;
										auditResponse.setCodigoRespuesta(propertiesExternos.idf0Codigo);
										auditResponse.setMensajeRespuesta(propertiesExternos.idf0Mensaje);
										auditResponse.setIdTransaccion(idTx);
	
									} else {										
										LOG.info(mensajeLog+ "  Tiene un monto de Garantia igual a:  "+ respClaroEval.getOfrecimiento().getOfrecimiento().getGarantia().getMontoDeGarantia());
										LOG.info(mensajeLog+ " Se Requiere que se le realice una Evaluacion Crediticia");
										auditResponse.setCodigoRespuesta(propertiesExternos.idf1Codigo);
										auditResponse.setMensajeRespuesta(propertiesExternos.idf1Mensaje);
										auditResponse.setIdTransaccion(idTx);
									}
								} else {
	
									LOG.info(mensajeLog+ " El Cliente ha superado la cantidad de Lineas Maximas Permitidas para el Plan Evaluado");
									LOG.info(mensajeLog+ " Se Requiere que se le realice una Evaluacion Crediticia");
									auditResponse.setCodigoRespuesta(propertiesExternos.idf1Codigo);
									auditResponse.setMensajeRespuesta(propertiesExternos.idf1Mensaje);
									auditResponse.setIdTransaccion(idTx);
								}
							}
						}					
					
					LOG.info(mensajeLog+ " FIN de Evaluacion del PLAN SOLICITADO: ["+ respPlanSolcitado.getDescripcionPlan()+ "]");
					LOG.info(mensajeLog+ " Validando si el ClaroEvalClienteReglas retorne la lista de Planes Adicionales Completa");

					if (respClaroEval.getOfrecimiento().getOfrecimiento().getVLISTAPLAN() != Constantes.NULLOBJECTO) {
										
						//Renovacion Inicio
						if(request.getTransaccion().equalsIgnoreCase(Constantes.TRANSACCIONEVALUACIONCREDITICIA) && request.getFlagModoOperacion().equalsIgnoreCase(Constantes.MODO_OPERACION_NORMAL) ) {
							if(request.getCodListaPrecio()!= null) {
								String [] temporalPorcentajeCuoIni = request.getCodListaPrecio().split("\\|");
								objClaroEvalBeanAux.setEquipoPorcenCuotaInicial(temporalPorcentajeCuoIni.length > Constantes.UNO ? temporalPorcentajeCuoIni[1] : Constantes.SCERO);
							}
						}else if(request.getFlagModoOperacion().equalsIgnoreCase(Constantes.MODO_OPERACION_NORMAL)){
							objClaroEvalBeanAux.setEquipoPorcenCuotaInicial(propertiesExternos.brmsEquipoPorcentajeCuotaInicial);
						}else if(Constantes.MODO_OPERACION_CONTADO.equals(request.getFlagModoOperacion()) || Constantes.VACIO.equals(request.getTransaccion())) {
							objClaroEvalBeanAux.setEquipoPorcenCuotaInicial(propertiesExternos.brmsEquipoPorcentajeCuotaInicial);//Contado							
						}
						
						if (request.getTipoDeOperacion().equalsIgnoreCase(Constantes.OPERACION_PORTABILIDAD)) {
							objClaroEvalBeanAux.setModalidadCedente(objClaroEvalBeanAux.getModalidadCedente());
						} else {
							objClaroEvalBeanAux.setModalidadCedente(Constantes.CONSTANTE_VACIA);
						}
						if (request.getTipoDeOperacion().equalsIgnoreCase(Constantes.OPERACION_PORTABILIDAD)) {
							objClaroEvalBeanAux.setBrmsTipOperacion(Constantes.TIPO_OPERACION_PORTABILIDAD);
						} else if(request.getTipoDeOperacion().equals(Constantes.OPERACION_ALTA)){
							objClaroEvalBeanAux.setBrmsTipOperacion(Constantes.TIPO_OPERACION_ALTA);
						} else if(request.getTipoDeOperacion().equals(Constantes.OPERACION_RENOVACION) || request.getTipoDeOperacion().equals(Constantes.OPERACION_ALTA_RENOVACION)) {
							objClaroEvalBeanAux.setBrmsTipOperacion(Constantes.TIPO_OPERACION_RENOVACION);
						}
						
						//Renovacion Fin																										
						if (request.getTransaccion().equalsIgnoreCase(Constantes.TRANSACCIONEVALUACIONCREDITICIA) || request.getTransaccion().equalsIgnoreCase(Constantes.VACIO)){
							LOG.info(mensajeLog+ " [1.5 INICIO Insertar datos en BRMS]");
							BRMSBioMovilResponse responseInsertarDatosBRMS = new BRMSBioMovilResponse();
							BRMSBioMovilRequest respDatosEvaluacion = dtoUtilService.cargarRequestInsertBRMSBiomovil(request, respClaroEval, objClaroEvalBeanAux, propertiesExternos);
							responseInsertarDatosBRMS = obtenerDataOperacionDao.insertBRMSBioMovilV2(propertiesExternos, respDatosEvaluacion, idTx, mensajeLog);
							if (Constantes.CERO == responseInsertarDatosBRMS.getPoCodigoRespuesta().intValue()) {
								LOG.info(mensajeLog+ " [SE REGISTRO EXITOSAMENTE LOS DATOS EN EL BRMS]");
							} else {
								LOG.info(mensajeLog+ " [OCURRIO UN ERROR AL REGISTRAR LOS DATOS EN EL BRMS]");
							}
							LOG.info(mensajeLog+ " [1.5 FIN Insertar datos en BRMS]");	
						}
							
						response.setListaDetallePlanes(listaPlanesType);

						ObjetoOpcional objOpcional2 = new ObjetoOpcional();
						objOpcional2.setCampo(Constantes.PLANESSINRA);
						String listaPlanesAprobadosFINAL = Constantes.CONSTANTE_VACIA;
						String[] arrayPlanesAprobadosPalotes = planes.split(Constantes.PALOTESEPARADORSPLIT);
						List<String> distinctList = Arrays.asList(arrayPlanesAprobadosPalotes).stream().distinct().collect(Collectors.toList());
						for (String a : distinctList) {
							listaPlanesAprobadosFINAL += a + Constantes.PALOTE;
						}

						String[] arrayPlanesDesordenados = listaPlanesAprobadosFINAL.split(Constantes.PALOTESEPARADORSPLIT);
						if (arrayPlanesDesordenados.length > Constantes.UNO) {

							String planAux = Constantes.CONSTANTE_VACIA;
							String planesOrdenados = Constantes.CONSTANTE_VACIA;

							for (int i = Constantes.CERO; i < (arrayPlanesDesordenados.length - Constantes.UNO); i++) {

								for (int j = i + Constantes.UNO; j < arrayPlanesDesordenados.length; j++) {

									Double cFUNO = Constantes.CERODOUBLE;
									Double cFDOS = Constantes.CERODOUBLE;

									String[] arrayCFPlanUNO = arrayPlanesDesordenados[i].split(Constantes.PUNTOCOMA);
									String[] arrayCFPlanDOS = arrayPlanesDesordenados[j].split(Constantes.PUNTOCOMA);

									cFUNO = Double.parseDouble(arrayCFPlanUNO[Constantes.UNO]);
									cFDOS = Double.parseDouble(arrayCFPlanDOS[Constantes.UNO]);

									if (cFUNO > cFDOS) {
										planAux = arrayPlanesDesordenados[i];
										arrayPlanesDesordenados[i] = arrayPlanesDesordenados[j];
										arrayPlanesDesordenados[j] = planAux;
									}
								}
							}

							for (int i = Constantes.CERO; i < arrayPlanesDesordenados.length; i++) {
								planesOrdenados += arrayPlanesDesordenados[i] + Constantes.PALOTE;
							}
							planes = planesOrdenados;
						}

						objOpcional2.setValor(listaPlanesAprobadosFINAL);

						ObjetoOpcional objOpcionalLCDisponible = new ObjetoOpcional();
						objOpcionalLCDisponible.setCampo(Constantes.LC_DISPONIBLE);
						objOpcionalLCDisponible.setValor(String.valueOf(lcDisponible));

						ObjetoOpcional objOpcionalRiesgoClaro = new ObjetoOpcional();
						objOpcionalRiesgoClaro.setCampo(Constantes.RIESGO_CLARO);
						objOpcionalRiesgoClaro.setValor(riesgoClaro);

						ObjetoOpcional objOpcionalComportaPago = new ObjetoOpcional();
						objOpcionalComportaPago.setCampo(Constantes.COMPORTA_PAGO);
						objOpcionalComportaPago.setValor(String.valueOf(comportaPago));

						ObjetoOpcional objOpcionalstrMontosxBilletera = new ObjetoOpcional();
						objOpcionalstrMontosxBilletera.setCampo(Constantes.STRMONTOSXBILLETERA);
						objOpcionalstrMontosxBilletera.setValor(String.valueOf(strMontosxBilletera));

						ObjetoOpcional objOpcionalTipoClienteSec = new ObjetoOpcional();
						objOpcionalTipoClienteSec.setCampo(Constantes.STIPO_CLIENTE_SEC);
						objOpcionalTipoClienteSec.setValor(String.valueOf(tipoClienteSec));
						
						ObjetoOpcional objOpcionalMesesPermanenciaEnClaro = new ObjetoOpcional();
						objOpcionalMesesPermanenciaEnClaro.setCampo(Constantes.MESES_PERMANENCIA_EN_CLARO);
						objOpcionalMesesPermanenciaEnClaro.setValor(String.valueOf(intMesesPermanencia));
						
						parametrosRespuesta.getObjetoOpcional().add(objOpcional2);
						parametrosRespuesta.getObjetoOpcional().add(objOpcionalLCDisponible);
						parametrosRespuesta.getObjetoOpcional().add(objOpcionalRiesgoClaro);
						parametrosRespuesta.getObjetoOpcional().add(objOpcionalComportaPago);
						parametrosRespuesta.getObjetoOpcional().add(objOpcionalstrMontosxBilletera);						
						parametrosRespuesta.getObjetoOpcional().add(objOpcionalTipoClienteSec);
						parametrosRespuesta.getObjetoOpcional().add(objOpcionalMesesPermanenciaEnClaro);
						
						if (!planes.equalsIgnoreCase(Constantes.CONSTANTE_VACIA)) {
							auditResponse.setCodigoRespuesta(propertiesExternos.idf0Codigo);
							auditResponse.setMensajeRespuesta(propertiesExternos.idf0Mensaje);
							auditResponse.setIdTransaccion(idTx);
						}

						String descripcionPlan = Constantes.CONSTANTE_VACIA;
						for (RespuestaProactiva objResProactiva : respClaroEval.getOfrecimiento().getOfrecimiento().getVLISTAPLAN()) {
							descripcionPlan += objResProactiva.getDescripcion() + ", ";
						}

						LOG.info(mensajeLog+ " Planes Obtenidos de ClaroEvalCliente reglas:  ["+ descripcionPlan+"]");

						response.setListaResponseOpcional(parametrosRespuesta);
						
					} else {
						auditResponse.setCodigoRespuesta(propertiesExternos.idt3Codigo);
						auditResponse.setMensajeRespuesta(propertiesExternos.idt3Mensaje.replace(Constantes.REPLACEEXCEPTIONMESSAGE,"Lista VLISTAPLAN del servicio ClaroEvalClienteReglas es nulo o vacio"));
						auditResponse.setIdTransaccion(idTx);
					}

				} else {
					auditResponse.setCodigoRespuesta(propertiesExternos.idt3Codigo);
					auditResponse.setMensajeRespuesta(propertiesExternos.idt3Mensaje.replace(Constantes.REPLACEEXCEPTIONMESSAGE, "El SP: [" + respDatsEvalCliReglasPVU + "] de la BD: ["+ respDatsEvalCliReglasPVU + "] no retorno data de la validacion de buro previa"));
					auditResponse.setIdTransaccion(idTx);
				}
			}
		} catch (DBException ex) {
			LOG.error(mensajeLog + "Ocurrio un error inesperado DBException: "+ ex);
			auditResponse.setCodigoRespuesta(ex.getCode());
			auditResponse.setMensajeRespuesta(ex.getMessage());
			auditResponse.setIdTransaccion(idTx);
		} catch (WSClientException ex) {
			LOG.error(mensajeLog + "Ocurrio un error inesperado WSClientException: "+ ex);
			auditResponse.setCodigoRespuesta(ex.getCode());
			auditResponse.setMensajeRespuesta(ex.getMessage());
			auditResponse.setIdTransaccion(idTx);
		} catch (Exception ex) {
			LOG.error(mensajeLog + "Ocurrio un error inesperado: "+ ex);
			auditResponse.setCodigoRespuesta(propertiesExternos.idt3Codigo);
			auditResponse.setMensajeRespuesta(propertiesExternos.idt3Mensaje.replace(Constantes.REPLACEEXCEPTIONMESSAGE,String.valueOf(ex.getCause())));
			auditResponse.setIdTransaccion(idTx);
		} finally {

			if (!request.getNumeroDocumento().equalsIgnoreCase(Constantes.CONSTANTE_VACIA)) {
				LOG.info(mensajeLog+ " Fin de validacion de DocIdentidad: ["+ request.getNumeroDocumento()+ "]");
			}
			response.setAuditResponse(auditResponse);
			LOG.info(mensajeLog+ " Response del metodo principal: ["+ methodName+ "]...\n"+ JAXBUtilitarios.anyObjectToXmlText(response));			
			LOG.info(mensajeLog+ "  ============================== [FIN METODO PRINCIPAL] - "+ methodName+ " ==============================" + "\n\n");
		}
		return response;
	}

	private ClaroEvalClientesReglasResponse procesaClaroEvalClienteReglas(PropertiesExternos propertiesExternos,
			String mensajeLog, EvaluarDatosClienteRequestWS request, ClaroEvalClienteAuxiliarBeanWS objClaroEvalBeanAux)
			throws WSException {
		ClaroEvalClientesReglasResponse respClaroEval = null;
		ClaroEvalClientesReglasRequest reqClaroEval = new ClaroEvalClientesReglasRequest();
		dtoUtilService.cargarRequestDesicionService(propertiesExternos, mensajeLog ,request, objClaroEvalBeanAux, reqClaroEval);
		try {
			respClaroEval = claroEvalSoapLocalImpl.claroEvalClientesReglasV2(propertiesExternos, reqClaroEval,mensajeLog);			
		} catch (WSClientException e) {
			LOG.error(mensajeLog+" Ocurrio un error en el servicio  claroEvalClientesReglasV2: " + e.getMessage());
			LOG.error(mensajeLog+ " Parametros de salida del metodo procesaClaroEvalClienteReglas " + JAXBUtilitarios.anyObjectToXmlText(respClaroEval));
		}
		return respClaroEval;
	}
	
	private SgaGetInformacionClienteBeanResponse procesaClienteServiciosSGA(PropertiesExternos propertiesExternos,
			EvaluarDatosClienteRequestWS request, String cantidadParametro, String mensajeLog) throws DBException{

		SgaGetInformacionClienteBeanResponse respClienteServicios;
		SgaGetInformacionClienteBeanRequest reqInformacionCliente = new SgaGetInformacionClienteBeanRequest();
		if (cantidadParametro.equals(Constantes.VACIO)) {
			cantidadParametro = Constantes.SCERO;
			reqInformacionCliente.setpCantProm(Integer.parseInt(cantidadParametro));
		}
		reqInformacionCliente.setPdocumento(String.valueOf(request.getNumeroDocumento()));
		reqInformacionCliente.setpTipoDocumento(Constantes.CODTIPODOCUMENTODNISGA);
		respClienteServicios = obtenerDataOperacionSGADao.obtenerInfoClienteSGA(propertiesExternos, reqInformacionCliente, mensajeLog);

		return respClienteServicios;
	}
	
	private ConsultarDeudaCuentaBRMSDbResponseType procesaConsultaDatosClienteBRMSOAC(PropertiesExternos propertiesExternos, String mensajeLog,
			EvaluarDatosClienteRequestWS request, String idTx) throws WSClientException{
		
		ConsultarDeudaCuentaBRMSDbRequestType consultarDeudaCuentaBRMSDbRequestType = new ConsultarDeudaCuentaBRMSDbRequestType();
		ConsultarDeudaCuentaBRMSDbResponseType response = null;
		ParametroRequest parametroRequest = new ParametroRequest();
		dtoUtilService.cargarParametroRequest(parametroRequest, propertiesExternos);
		consultarDeudaCuentaBRMSDbRequestType.setPNNROMESESDISPUTA(String.valueOf(Constantes.TRES));
		consultarDeudaCuentaBRMSDbRequestType.setPVCLITIPODOCIDENT(Constantes.CODTIPODOCUMENTODNIOAC);
		consultarDeudaCuentaBRMSDbRequestType.setPVCLINRODOCIDENT(request.getNumeroDocumento());
		consultarDeudaCuentaBRMSDbRequestType.setPVTRXIDWS(idTx);
		consultarDeudaCuentaBRMSDbRequestType.setPVCODAPLICACION(Constantes.APLICACION);
		consultarDeudaCuentaBRMSDbRequestType.setPVUSUARIOAPLIC(Constantes.USRAPLICACION);		
		response = consultaDatosClienteBRMSOACImpl.consultarDeudaCuentaBRMSDb(propertiesExternos, mensajeLog, idTx, consultarDeudaCuentaBRMSDbRequestType);
		return response;
	}
	
	private ConsultaCuotaClienteResponse procesaConsultaCuotaclienteWS(PropertiesExternos propertiesExternos, String mensajeLog,
			EvaluarDatosClienteRequestWS request, String idTx) throws WSClientException {

		ConsultaCuotaClienteResponse response = null;	
		ConsultaCuotaClienteRequest consultaCuotaClienteRequest = new ConsultaCuotaClienteRequest();
		consultaCuotaClienteRequest.setTipoDocumento(Constantes.CODTIPODOCUMENTODNIOAC);
		consultaCuotaClienteRequest.setNumeroDocumento(request.getNumeroDocumento());
		consultaCuotaClienteRequest.setLineaDelCliente(request.getLineaCliente());		
		response = consultaCuotaClienteOACImpl.consultarCuotaCliente(propertiesExternos, mensajeLog, idTx, consultaCuotaClienteRequest);
		return response;
	}
	
	private ConsultaSegmentoResponse procesaConsultaSegmentoClienteWS(PropertiesExternos propertiesExternos, String mensajeLog,
			EvaluarDatosClienteRequestWS request, String idTx) throws WSClientException{

		ConsultaSegmentoResponse response = null;
		ConsultaSegmentoRequest consultaSegmentoRequest = new ConsultaSegmentoRequest();
		if(request.getTipoDocumento().equalsIgnoreCase(Constantes.TIPODOCUMENTODNISEGMENTOCLIENTE)) {
			consultaSegmentoRequest.setTipoDocumento(Constantes.DESTIPODOCUMENTODNISEGMENTOCLIENTE);
		}
		consultaSegmentoRequest.setNroDocumento(request.getNumeroDocumento().concat(Constantes.LLENARPORLADODERECHOSEGMENTOCLIENTE));
		consultaSegmentoRequest.setLongitudDocumento(request.getNumeroDocumento()!= Constantes.NULLOBJECTO ? String.valueOf(request.getNumeroDocumento().length()) : String.valueOf(Constantes.VEINTIUNO));
		AuditType auditType = new AuditType();
		auditType.setUsrApp(Constantes.USRAPLICACION);	
		auditType.setIpAplicacion(Constantes.IPAPLICACION);
		auditType.setAplicacion(Constantes.APLICACION);
		auditType.setIdTransaccion(idTx);
		consultaSegmentoRequest.setAudit(auditType);
		response = consultaSegmentoClienteSoapLocalImpl.consultaSegmentoCliente(propertiesExternos, consultaSegmentoRequest, mensajeLog);
		return response;
	}
	
	private ObtenerReintegroEquipoResponse obtenerReintegroEquipo(PropertiesExternos propertiesExternos, String mensajeLog,
			EvaluarDatosClienteRequestWS request, HeaderRequest httpHeader) throws WSClientException, WSException {

		ObtenerReintegroEquipoResponse response = null;
		ObtenerReintegroEquipoRequest gestionAcuerdoRequest = new ObtenerReintegroEquipoRequest();		
		
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		AuditRequestType audit = new AuditRequestType();
		audit.setIdTransaccion(httpHeader.getIdTransaccion());
		audit.setIpAplicacion(Constantes.IPAPLICACION);
		audit.setNombreAplicacion(httpHeader.getNombreAplicacion());
		audit.setUsuarioAplicacion(httpHeader.getUserId());
		gestionAcuerdoRequest.setAuditRequest(audit);
		gestionAcuerdoRequest.setMsisdn(request.getLineaCliente());
		gestionAcuerdoRequest.setCoId(Constantes.SCERO);
		gestionAcuerdoRequest.setFechaTransaccion(dateFormat.format(date));
		gestionAcuerdoRequest.setCargoFijoNuevo(Constantes.SCERO);
		gestionAcuerdoRequest.setMotivoApadece(Constantes.SCERO);
		gestionAcuerdoRequest.setFlagEquipo(Constantes.SCERO);
		
		response = gestionAcuerdoWSImpl.obtenerReintegroEquipo(propertiesExternos, gestionAcuerdoRequest, mensajeLog);
		return response;
	}
	
	public PvuConsultaCVEPendResponse obtenerCVEPend(String mensajeLog, PropertiesExternos propertiesExternos, String idTx,PvuConsultaCVEPendRequest dataRequest) throws DBException{
		LOG.info(mensajeLog+ "[ INICIO ] 1. Buscando plan en BD PVU");				
		PvuConsultaCVEPendResponse pvuConsultaCVEPendResponse = obtenerDataOperacionDao.obtenerCVEPend(mensajeLog, propertiesExternos, idTx, dataRequest);
		LOG.info(mensajeLog+ "[  FIN ] 2. Obtener Parametros ");		
		return pvuConsultaCVEPendResponse;
	}					
		
}