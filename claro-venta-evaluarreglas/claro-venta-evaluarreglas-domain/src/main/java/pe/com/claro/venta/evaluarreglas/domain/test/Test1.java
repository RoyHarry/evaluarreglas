//package pe.com.claro.venta.evaluarreglas.domain.test;
//
//import javax.xml.ws.BindingProvider;
//
//import pe.com.claro.common.bean.HeaderRequest;
//import pe.com.claro.common.property.Constantes;
//import pe.com.claro.common.util.JAXBUtilitarios;
//
//import java.io.IOException;
//
//import javax.ws.rs.core.MediaType;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.UniformInterfaceException;
//import com.sun.jersey.api.client.WebResource;
//import com.sun.jersey.api.client.WebResource.Builder;
//
//import pe.com.claro.esb.message.test.consultardeudacuentabrms_db.v1.ConsultarDeudaCuentaBRMSDbRequestType;
//import pe.com.claro.esb.message.test.consultardeudacuentabrms_db.v1.ConsultarDeudaCuentaBRMSDbResponseType;
//import pe.com.claro.esb.venta.dat_consultadatosbrmsoac.v1.ClaroFault;
//import pe.com.claro.esb.venta.dat_consultadatosbrmsoac.v1.DATConsultaDatosBRMSOACPort;
//import pe.com.claro.esb.venta.dat_consultadatosbrmsoac.v1.DATConsultaDatosBRMSOACSOAP11BindingQSService;
//import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.parametro.ParametroRequest;
//import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.parametro.ParametrosResponseWs;
//import pe.com.claro.venta.evaluarreglas.integration.client.impl.ConsultaSegmentoClienteSoapLocalImpl;
//import pe.com.claro.venta.evaluarreglas.integration.util.UtilIntegration;
//
//public class Test1 {
//	public static void main(String[] args) throws UniformInterfaceException, IOException {
//		ConsultaSegmentoClienteSoapLocalImpl consultaSegmentoClienteSoapLocalImpl = new ConsultaSegmentoClienteSoapLocalImpl();
//		UtilIntegration util = new UtilIntegration();
//		
////		LOG.info(" [{} 1. INICIO - SERVICIO PARAMETRO]", headerRequestBean.getIdTransaccion());
//		
//		
//		ParametroRequest request = new ParametroRequest();
//		request.setCodigo("");
//		request.setGrupo("1015");
//		request.setSistema("APPVENTAS");
//		request.setTipoOperacion("");
//		request.setVersion("1.0");
//		ObjectMapper mapper = new ObjectMapper();
//		
//		Client clientRest = Client.create();
//		clientRest.setReadTimeout(Integer.parseInt("3000"));
//		WebResource webResource = clientRest.resource("http://172.17.26.37:20000/claro-venta-parametro-resource/api/venta/parametro/v1.0.0/" + "obtenerparametro");
//		Builder builder = webResource.type(Constantes.TYPEREQUEST);
//		HeaderRequest headerRequestBean = new HeaderRequest();
//		headerRequestBean.setAccept("application/json");
//		headerRequestBean.setIdTransaccion("1342");
//		headerRequestBean.setMsgid("msgid");
//		headerRequestBean.setNombreAplicacion("");
//		headerRequestBean.setUserId("userId");
//		setHeaderBuilder(builder, headerRequestBean);
//		ConsultarDeudaCuentaBRMSDbRequestType consultarDeudaCuentaBRMSDbRequestType = new ConsultarDeudaCuentaBRMSDbRequestType();
//		
//		consultarDeudaCuentaBRMSDbRequestType.setPNNROMESESDISPUTA("3");
//		consultarDeudaCuentaBRMSDbRequestType.setPVCLITIPODOCIDENT(Constantes.CODTIPODOCUMENTODNIOAC);
//		consultarDeudaCuentaBRMSDbRequestType.setPVCLINRODOCIDENT("47174471");
//		consultarDeudaCuentaBRMSDbRequestType.setPVTRXIDWS("123456");
//		consultarDeudaCuentaBRMSDbRequestType.setPVCODAPLICACION(Constantes.APLICACION);
//		consultarDeudaCuentaBRMSDbRequestType.setPVUSUARIOAPLIC(Constantes.USRAPLICACION);
////		LOG.info("[{}] 2.1 SERVICIO PARAMETRO, URL: {}", headerRequestBean.getIdTransaccion(), propertiesExternos.PARAMETRO_REST_CLIENT_URL + propertiesExternos.PARAMETRO_REST_CLIENT_METHOD);
////		LOG.info("[{}] 2.2 SERVICIO PARAMETRO, DATA REQUEST: {}", headerRequestBean.getIdTransaccion(), JAXBUtilitarios.anyObjectToXmlText(request));
//		System.out.println("[{}] 2.1 SERVICIO PARAMETRO, URL: {}"+ "124332"+ "http://172.17.26.37:20000/claro-venta-parametro-resource/api/venta/parametro/v1.0.0/" + "obtenerparametro");
//		System.out.println("[{}] 2.2 SERVICIO PARAMETRO, DATA REQUEST: {}"+ "124332"+ JAXBUtilitarios.anyObjectToXmlText(request));
//		ClientResponse response = builder.post(ClientResponse.class, mapper.writeValueAsString(request));
//		String data = response.getEntity(String.class);
//		ParametrosResponseWs parametrosResponseWs = mapper.readValue(data, ParametrosResponseWs.class);
//		System.out.println("[{}] 3. SERVICIO PARAMETRO RESPONSE: {}"+ JAXBUtilitarios.anyObjectToXmlText(parametrosResponseWs));
////		LOG.info("[{}] 3. SERVICIO PARAMETRO RESPONSE: {}", headerRequestBean.getIdTransaccion(), JAXBUtilitarios.anyObjectToXmlText(parametrosResponseWs));
////		LOG.info("[{} 4. FIN - PARAMETRO]", headerRequestBean.getIdTransaccion());
//		
////		ConsultarDeudaCuentaBRMSDbRequestType consultarDeudaCuentaBRMSDbRequestType = new ConsultarDeudaCuentaBRMSDbRequestType();
////					
////		consultarDeudaCuentaBRMSDbRequestType.setPNNROMESESDISPUTA("3");
////		consultarDeudaCuentaBRMSDbRequestType.setPVCLITIPODOCIDENT("1");
////		consultarDeudaCuentaBRMSDbRequestType.setPVCLINRODOCIDENT("47174471");
////		consultarDeudaCuentaBRMSDbRequestType.setPVTRXIDWS("1234567");	
////		consultarDeudaCuentaBRMSDbRequestType.setPVCODAPLICACION("AppVentas");	
////		consultarDeudaCuentaBRMSDbRequestType.setPVUSUARIOAPLIC("USRBIMOVI");		
//		
//		ConsultarDeudaCuentaBRMSDbResponseType consultarDeudaCuentaBRMSDbResponseType = new ConsultarDeudaCuentaBRMSDbResponseType(); 
//		
//		DATConsultaDatosBRMSOACPort datConsultaDatosBRMSOACPort = null;
//		DATConsultaDatosBRMSOACSOAP11BindingQSService datConsultaDatosBRMSOACSOAP11BindingQSService = new DATConsultaDatosBRMSOACSOAP11BindingQSService();
//		datConsultaDatosBRMSOACPort = datConsultaDatosBRMSOACSOAP11BindingQSService.getDATConsultaDatosBRMSOACSOAP11BindingQSPort();
//		try {
//			consultarDeudaCuentaBRMSDbResponseType = datConsultaDatosBRMSOACPort.consultarDeudaCuentaBRMSDb(consultarDeudaCuentaBRMSDbRequestType);
//		} catch (ClaroFault e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		
//		
//		try {
////			mensajeLog += "[" + Constantes.NOMBRE_SUBMETODO_1 + "]";
////			LOG.info(mensajeLog + "[INICIO de metodo: " + Constantes.NOMBRE_SUBMETODO_1 + "]");
////			LOG.info(mensajeLog + "Se invoca, metodo=consultaSegmentoCliente, URL={}", propertiesExternos.wsConsultaSegmentoClienteWSDL);
//			BindingProvider bindingProvider = (BindingProvider) datConsultaDatosBRMSOACPort;
//			bindingProvider.getRequestContext().put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
//					"http://172.17.26.54:21000/DAT_ConsultaDatosBRMSOAC/SRV_Consulta/Service/Mediation/MED_DAT_ConsultarDatosBRMSOACPipelineProxyService?WSDL");
////			LOG.info(mensajeLog + "Tiempo de Timeout de Conexion: "
////					+ propertiesExternos.wsConsultaSegmentoClienteTimeOut);
//			System.out.println("Datos de entrada Body:" + JAXBUtilitarios.anyObjectToXmlText(consultarDeudaCuentaBRMSDbRequestType));
//			long tiempoInicioWS = System.currentTimeMillis();
////			LOG.info(mensajeLog + "Tiempo total de proceso del llamado del Web Services: {} Metodo: {} (ms):{} ",
////					"ConsultaSegmentoResponse", "consultaSegmentoCliente", System.currentTimeMillis() - tiempoInicioWS);
//			System.out.println("Datos de salida:" + JAXBUtilitarios.anyObjectToXmlText(consultarDeudaCuentaBRMSDbResponseType));
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
//	
//	public static void setHeaderBuilder(Builder webResource, HeaderRequest header) {
//		webResource.header(Constantes.IDTRANSACCION, header.getIdTransaccion());
//		webResource.header(Constantes.TIMESTAMP,"2018-09-29T18:46:19Z");
//		webResource.header(Constantes.ACCEPT, header.getAccept());
//		webResource.header(Constantes.MSGID, header.getMsgid());
//		webResource.header(Constantes.USERID, header.getUserId());
//	}
//}	
