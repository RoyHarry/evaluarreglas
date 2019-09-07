//package pe.com.claro.venta.evaluarreglas.domain.test;
//
//import java.net.ConnectException;
//import java.net.SocketTimeoutException;
//
//import javax.xml.ws.BindingProvider;
//
//import pe.com.claro.common.property.Constantes;
//import pe.com.claro.common.resource.exception.WSClientException;
//import pe.com.claro.common.util.JAXBUtilitarios;
//import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.AuditType;
//import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.ConsultaSegmentoRequest;
//import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.ConsultaSegmentoResponse;
//import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.EbsConsultaSegmentoPortType;
//import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.EbsConsultaSegmentoService;
//import pe.com.claro.venta.evaluarreglas.integration.client.impl.ConsultaSegmentoClienteSoapLocalImpl;
//
//public class Test {
//	public static void main(String[] args) {
//		ConsultaSegmentoClienteSoapLocalImpl consultaSegmentoClienteSoapLocalImpl = new ConsultaSegmentoClienteSoapLocalImpl();
//		
//		
//		String mensajeTrxClient;
//		String methodName = new String(Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
//		mensajeTrxClient = "[" + methodName + "]";
//		ConsultaSegmentoRequest request = new ConsultaSegmentoRequest();
//		AuditType auditType = new AuditType();
//		auditType.setAplicacion("127.0.0.1");
//		auditType.setIdTransaccion("123456789001");
//		auditType.setIpAplicacion("APK");
//		auditType.setUsrApp("USRAPK");
//		request.setLongitudDocumento("21");
//		request.setTipoDocumento("D");
//		request.setNroDocumento("08752867".concat(Constantes.LLENARPORLADODERECHOSEGMENTOCLIENTE));
//		request.setAudit(auditType);
//		String urlClienteWS = "http://172.19.74.118:7909/ConsultaSegmentoClienteWS/ebsConsultaSegmento12SB?WSDL";
//		String nombreClienteWS = Constantes.nombreClienteClaroEvalClientesReglas;
//		EbsConsultaSegmentoPortType ebsConsultaSegmentoPortType = null;
//		EbsConsultaSegmentoService service = new  EbsConsultaSegmentoService();		
//		ebsConsultaSegmentoPortType = service.getEbsConsultaSegmento11SB();	
//		ConsultaSegmentoResponse response = ebsConsultaSegmentoPortType.consultaSegmento(request);
//		String codResp = Constantes.CONSTANTE_VACIA;
//		String msjResp = Constantes.CONSTANTE_VACIA;
//		try {
////			mensajeLog += "[" + Constantes.NOMBRE_SUBMETODO_1 + "]";
////			LOG.info(mensajeLog + "[INICIO de metodo: " + Constantes.NOMBRE_SUBMETODO_1 + "]");
////			LOG.info(mensajeLog + "Se invoca, metodo=consultaSegmentoCliente, URL={}", propertiesExternos.wsConsultaSegmentoClienteWSDL);
//			BindingProvider bindingProvider = (BindingProvider) ebsConsultaSegmentoPortType;
//			bindingProvider.getRequestContext().put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
//					"http://172.19.74.118:7909/ConsultaSegmentoClienteWS/ebsConsultaSegmento12SB?WSDL");
////			LOG.info(mensajeLog + "Tiempo de Timeout de Conexion: "
////					+ propertiesExternos.wsConsultaSegmentoClienteTimeOut);
//			System.out.println("Datos de entrada Body:" + JAXBUtilitarios.anyObjectToXmlText(request));
//			long tiempoInicioWS = System.currentTimeMillis();
////			LOG.info(mensajeLog + "Tiempo total de proceso del llamado del Web Services: {} Metodo: {} (ms):{} ",
////					"ConsultaSegmentoResponse", "consultaSegmentoCliente", System.currentTimeMillis() - tiempoInicioWS);
//			System.out.println("Datos de salida:" + JAXBUtilitarios.anyObjectToXmlText(response));
//		} catch (Exception e) {
////			LOG.error(mensajeTrxClient + "ERROR[Exception]: Se produjo una excepcion al ejecutar WS " + urlClienteWS
////					+ ": " + e);
//
////			if (e.toString().contains(
////					SocketTimeoutException.class.toString().replace("class", Constantes.CONSTANTE_VACIA).trim())) {
//////				LOG.error(mensajeTrxClient + "Error producido por Timeout");
////
////				codResp = propertiesExternos.idt2Codigo;
////				msjResp = propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEWS)
////						.replace(Constantes.REPLACERECURSO, nombreClienteWS)
////						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONMETODO)
////						.replace(Constantes.REPLACEMETODOSPRECURSO, methodName);
////
////			} else if (e.toString()
////					.contains(ConnectException.class.toString().replace("class", Constantes.CONSTANTE_VACIA).trim())) {
//////				LOG.error(mensajeTrxClient + "Error producido por No Disponilidad");
////
////				codResp = propertiesExternos.idt1Codigo;
////				msjResp = propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEWS)
////						.replace(Constantes.REPLACERECURSO, nombreClienteWS)
////						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONMETODO)
////						.replace(Constantes.REPLACEMETODOSPRECURSO, methodName);
////			} else {
////				codResp = propertiesExternos.idt3Codigo;
////				msjResp = propertiesExternos.idt3Mensaje.replace(Constantes.replaceExceptionMessage,
////						String.valueOf(e.getCause()));
////			}
////			throw new WSClientException(codResp, msjResp, e);
//
//			e.printStackTrace();
//		}
//	}	
//}	
