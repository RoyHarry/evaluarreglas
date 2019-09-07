package pe.com.claro.venta.evaluarreglas.integration.client.impl;

import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.com.claro.eai.ebs.ventas.ebscreditosws.ws.types.BEUsuario;
import pe.com.claro.common.bean.HeaderRequest;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.WSException;
import pe.com.claro.common.util.JAXBUtilitarios;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.eai.ebs.ventas.ebscreditosws.ws.EbsCreditosWSPortType;
import pe.com.claro.eai.ebs.ventas.ebscreditosws.ws.EbsCreditosWSService;
import pe.com.claro.eai.ebs.ventas.ebscreditosws.ws.types.ConsultarDatosDCRequest;
import pe.com.claro.eai.ebs.ventas.ebscreditosws.ws.types.ConsultarDatosDCResponse;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes.EvaluarDatosClienteRequestWS;
import pe.com.claro.venta.evaluarreglas.integration.client.IEbsCreditosWS;

/**
 * Clase que implementa la interfaz IEbsCreditosWS, contiene los métodos de conexión al servicio SOAP EbsCreditosWS
 * @author everis
 * @version 1.0
 */
@Stateless
public class EbsCreditosWSImpl implements IEbsCreditosWS{

	private static final Logger LOG = LoggerFactory.getLogger(EbsCreditosWSImpl.class);
	
	/**
	 * Método que permite la conexión con el método ValidacionDeudaRules del componente SOAP ValidacionDeudaRuleApp
	 * @param properties PropertiesExternos
	 * @param mensajeLog
	 * @param consultarDatosDCRequest ConsultarDatosDCRequest objeto que contiene los parámetros a enviar
	 * @param header HeaderRequestBean objeto que contiene los datos para auditoría
	 * @return response ConsultarDatosDCResponse objeto que contiene la información de respuesta del ws
	 */
	@Override
	public ConsultarDatosDCResponse evaluarCredito(PropertiesExternos properties, String mensajeLog, 
			EvaluarDatosClienteRequestWS request, HeaderRequest header) throws WSException {
		
		ConsultarDatosDCResponse response = null;
		long inicioTime = System.currentTimeMillis();
		
		try {
			LOG.info("{} ############ INICIO de metodo: {} ############", mensajeLog, new Object() {}.getClass().getEnclosingMethod().getName());
			LOG.info("{} Se invoca, metodo= {}, URL= {}", mensajeLog, new Object() {}.getClass().getEnclosingMethod().getName(), 
					properties.ebsCreditosWsUrl);
			
			EbsCreditosWSService service = new EbsCreditosWSService();
			EbsCreditosWSPortType port = service.getEbsCreditosSB11();
			
			BindingProvider bindingProvider = (BindingProvider) port;
			bindingProvider.getRequestContext().put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.ebsCreditosWsUrl);
			
			LOG.info(mensajeLog + " Tiempo de Timeout de Conexion: " + properties.ebsCreditosWsCnxTimeout);
			bindingProvider.getRequestContext().put("com.sun.xml.ws.connect.timeout", Integer.parseInt(properties.ebsCreditosWsCnxTimeout));
			
			LOG.info(mensajeLog + " Tiempo de Timeout de Ejecucion: " + properties.ebsCreditosWsExecTimeout);
			bindingProvider.getRequestContext().put("com.sun.xml.ws.request.timeout", Integer.parseInt(properties.ebsCreditosWsExecTimeout));
						
			ConsultarDatosDCRequest consultarDatosDCRequest = new ConsultarDatosDCRequest();

			pe.com.claro.eai.ebs.ventas.ebscreditosws.ws.types.AuditRequestType auditType = new pe.com.claro.eai.ebs.ventas.ebscreditosws.ws.types.AuditRequestType();
			auditType.setIdTransaccion(header.getIdTransaccion());
			auditType.setIpAplicacion(Constantes.IPAPLICACION);
			auditType.setNombreAplicacion(header.getNombreAplicacion());
			auditType.setUsuarioAplicacion(header.getUserId());
			
			BEUsuario beUsuario = new BEUsuario();
			beUsuario.setApellidoMat(properties.ebsusuarioApellidoMaterno);
			beUsuario.setApellidoPat(properties.ebsusuarioApellidoPaterno);
			beUsuario.setArea(properties.ebsusuarioArea);
			beUsuario.setCadenaOpcionesPagina(properties.ebsusuarioCadenaOpcionesPagina);
			beUsuario.setCadenaPerfil(properties.ebsusuarioCadenaPerfil);
			beUsuario.setCanalVenta(properties.ebsusuarioCanalVenta);
			beUsuario.setCanalVentaDescripcion(properties.ebsusuarioCanalVentaDescripcion);
			beUsuario.setEstadoAcceso(properties.ebsusuarioEstadoAcceso);
			beUsuario.setHost(properties.ebsusuarioHost);
			beUsuario.setIdArea(properties.ebsusuarioIdArea);
			beUsuario.setIdCuentaRed(properties.ebsusuarioIdCuentaRed);
			beUsuario.setIdUsuario(properties.ebsusuarioIdUsuario);
			beUsuario.setIdUsuarioSisact(properties.ebsusuarioIdUsuarioSisact);
			beUsuario.setIdVendedorSap(properties.ebsusuarioIdVendedorSap);
			beUsuario.setLogin(properties.ebsusuarioLogin);
			beUsuario.setNombre(properties.ebsusuarioNombre);
			beUsuario.setNombreCompleto(properties.ebsusuarioNombreCompleto);
			beUsuario.setOficinaVenta(properties.ebsusuarioOficinaVenta);
			beUsuario.setOficinaVentaDescripcion(properties.ebsusuarioOficinaVentaDescripcion);
			beUsuario.setPerfil149(properties.ebsusuarioPerfil149);
			beUsuario.setTerminal(properties.ebsusuarioTerminal);
			beUsuario.setTipoOficina(properties.ebsusuarioTipoOficina);
			
			consultarDatosDCRequest.setAuditRequestType(auditType);
			consultarDatosDCRequest.setFlagConsulta(String.valueOf(Constantes.CERO));
			consultarDatosDCRequest.setNroDocumento(request.getNumeroDocumento());
			consultarDatosDCRequest.setObjUsuario(beUsuario);
			consultarDatosDCRequest.setOficina(request.getOficinaPVU());
			consultarDatosDCRequest.setStrApeMaterno(Constantes.CONSTANTE_X);
			consultarDatosDCRequest.setStrApePaterno(Constantes.VACIO);
			consultarDatosDCRequest.setStrNombres(Constantes.CONSTANTE_X);
			consultarDatosDCRequest.setTipoDocumento(request.getTipoDocumento().equals(request.getTipoDocumento())
					? properties.tipoDocDniEbscredito : Constantes.TEXTO_VACIO);
			
			LOG.info(mensajeLog + " Datos de entrada: " + JAXBUtilitarios.anyObjectToXmlText(consultarDatosDCRequest));
			
			response = new ConsultarDatosDCResponse();
			response = port.evaluarCredito(consultarDatosDCRequest);
			
		} catch (Exception e) {
			if (e.getMessage().toUpperCase().contains(Constantes.TIMEOUTEXCEPTION.toUpperCase())
					|| e.getMessage().toUpperCase().contains(Constantes.TIMEOUTEXCEPTION2.toUpperCase())) {
				throw new WSException(properties.codigoRespuestaIDT1Generico,
									properties.descripcionRespuestaIDT1Generico
								.replace("$componente", "EbsCreditosWS, metodo: evaluarCredito"),
								e.getMessage(), e, Constantes.STATUS_TIME_OUT);
			} else {
				throw new WSException(properties.codigoRespuestaIDT2Generico,
									properties.descripcionRespuestaIDT2Generico
								.replace("$componente", "EbsCreditosWS, metodo: evaluarCredito"),
								e.getMessage(), e, Constantes.STATUS_DISPONIBILIDAD);
			}
		} finally {
			LOG.info(mensajeLog + " Datos de salida: " + JAXBUtilitarios.anyObjectToXmlText(response));
			LOG.info(mensajeLog + " [Tiempo de ejecucion: " + (System.currentTimeMillis() - inicioTime) + " milisegundos.");
			LOG.info("{} ############ FIN de metodo: {} ############", mensajeLog, new Object() {}.getClass().getEnclosingMethod().getName());
		}
		
		return response;
	}

}
