package pe.com.claro.venta.evaluarreglas.resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import pe.com.claro.common.bean.BodyResponse;
import pe.com.claro.common.bean.HeaderRequest;
import pe.com.claro.common.bean.HeaderResponse;
import pe.com.claro.common.exception.MappingResponseException;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.CastException;
import pe.com.claro.common.resource.exception.DBException;
import pe.com.claro.common.resource.exception.WSException;
import pe.com.claro.common.util.ClaroUtil;
import pe.com.claro.common.util.JAXBUtilitarios;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.evaluarreglas.domain.service.ObtenerDataOperacionService;
import pe.com.claro.venta.evaluarreglas.integration.client.impl.ClaroEvalSoapLocalImpl;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes.EvaluarDatosClienteRequestWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.EvaluarDatosClienteResponseWS;

@Stateless
@Path(Constantes.PATH)
@Api(value = "venta/consultas/V1.0.0/evaluarreglas", description = "¡Bienvenido!")
@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
public class EvaluarReglasResource {

	@Context
	private Configuration configuration;
	private PropertiesExternos propertiesExternos;
		
	@EJB
	private ObtenerDataOperacionService obtenerDataOperacionService;
	
	ClaroEvalSoapLocalImpl claroEvalSoapLocalImpl = new ClaroEvalSoapLocalImpl();
	
	public void initProperties() {
		propertiesExternos = new PropertiesExternos(configuration);
	}

	private static final Logger LOG = LoggerFactory.getLogger(EvaluarReglasResource.class);
	
	@POST
	@Path(Constantes.PATHMETODO1)
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "[getDataValidaClienteReglas] - Operación para evaluar al cliente.", notes = "", response = EvaluarDatosClienteResponseWS.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Operación Exitosa o Errores funcionales"),
			@ApiResponse(code = 400, message = "Errores Tecnicos controlados por el API") })
		public Response claroEvalClientesReglas(@Context HttpHeaders httpHeaders,
				EvaluarDatosClienteRequestWS request)
				throws WSException, JsonProcessingException, Exception {
		initProperties();
		EvaluarDatosClienteResponseWS response = null;
		String result = null;
		long tiempoInicio = System.currentTimeMillis();
		HeaderRequest headerRequest = new HeaderRequest(httpHeaders);
		String idTx = headerRequest.getIdTransaccion();
		String msgid = headerRequest.getMsgid();
		String trazabilidad = "[" + Constantes.NOMBRE_METODO_1 + " idTx=" + idTx + " msgid=" + msgid + "] ";
		LOG.info(trazabilidad + "################################### [Inicio de metodo {}] #####################################", Constantes.NOMBRE_METODO_1);		
		Response resJSON = Response.ok().entity(Constantes.VACIO).build();
		LOG.info("restJson ********: " + ClaroUtil.printPrettyJSONString(resJSON));
		HeaderResponse auditResponse = new HeaderResponse();
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		String responsePrint = null;
		EvaluarDatosClienteRequestWS requestTemplate = new EvaluarDatosClienteRequestWS();
		String requestPrint = ClaroUtil.printPrettyJSONString(request);
		LOG.info(requestPrint + "Request print ....  \n" + requestPrint);
		int cantidadAtributos = requestTemplate.getClass().getDeclaredFields().length;
		String objetoNulo = Constantes.CONSTANTE_VACIA;
		
		try {
			LOG.info("2. [[INICIO] evaluarDatosCliente]");
			response = this.obtenerDataOperacionService.evaluarDatosCliente(propertiesExternos, request, idTx, headerRequest);
			LOG.info("2. [[FIN] evaluarDatosCliente]");
			result = ClaroUtil.printPrettyJSONString(response);
			Status httpCode = Status.OK;
			responsePrint = ClaroUtil.printPrettyJSONString(response);
			resJSON = Response.status(httpCode).entity(result).build();
		} catch (Exception e) {
			LOG.error("ERROR: " + e + "mensaje :" + e.getMessage());
			LOG.error("Cantidad de atributos por defecto del Request: " + cantidadAtributos);
			response = new EvaluarDatosClienteResponseWS();
			if(request == null ){
				objetoNulo = objetoNulo + Constantes.REQUESTSTR;
			}
			LOG.error("Ocurrio un error inesperado: ", e);			
			auditResponse.setIdTransaccion(idTx);
			auditResponse.setCodigoRespuesta(propertiesExternos.idt3Codigo);			
			if(!objetoNulo.equalsIgnoreCase(Constantes.CONSTANTE_VACIA)){
				auditResponse.setMensajeRespuesta(propertiesExternos.idt3Mensaje.replace(Constantes.REPLACEEXCEPTIONMESSAGE, String.valueOf(e.getCause()) + Constantes.PUNTOCONESPACIO + "Objeto(s) " + objetoNulo + " Nulo(s)"));
			}else{
				auditResponse.setMensajeRespuesta(propertiesExternos.idt3Mensaje.replace(Constantes.REPLACEEXCEPTIONMESSAGE, String.valueOf(e.getCause()) + Constantes.PUNTO));
			}
			response.setAuditResponse(auditResponse);
			LOG.error("Response del metodo principal: [" + methodName + "]...\n" + JAXBUtilitarios.anyObjectToXmlText(response));
			MappingResponseException res = this.mappingResponseException(trazabilidad, headerRequest, e);
			responsePrint = res.getResponsePrint();
			resJSON = res.getResJSON();			
			return resJSON;
		}finally {
			LOG.info(trazabilidad + "Response General :\n{}", responsePrint);
			LOG.info(trazabilidad + "Tiempo total de proceso(ms): {} milisegundos.",
					System.currentTimeMillis() - tiempoInicio);
			LOG.info(trazabilidad
					+ "################################### [FIN de metodo {}] #####################################",
					Constantes.NOMBRE_METODO_1);
		}
		return resJSON;
	}
	
	private MappingResponseException mappingResponseException(String trazabilidad, HeaderRequest header, Exception e)
			throws JsonProcessingException {
		LOG.info("entrando al MappingResponseException********");
		String result = Constantes.VACIO;
		String responsePrint = Constantes.VACIO;
		Response resJSON = null;
		int status = Constantes.STATUS_DISPONIBILIDAD;
		LOG.error(trazabilidad + "ERROR: [Exception] - [" + e.getMessage() + "] ", e);
		if (e instanceof WSException) {
			LOG.info("ENTRO AL IF DE WSException");
			WSException x = (WSException) e;
			status = x.getStatus();
			result = ClaroUtil.printPrettyJSONString(
					new BodyResponse(header.getIdTransaccion(), x.getMessageDeveloper(), x.getCode(), x.getMessage()));
			LOG.info("********result BodyResponse******: ... \n{}", JAXBUtilitarios.anyObjectToXmlText(result));
			responsePrint = ClaroUtil.printPrettyJSONString(new BodyResponse(header.getIdTransaccion(),
					x.getMessageDeveloper(), (x.getCode()), x.getMessage()));
		} else if (e instanceof DBException) {
			LOG.info("ENTRO AL  else if DE WSException");
			DBException x = (DBException) e;
			status = x.getStatus();
			result = ClaroUtil.printPrettyJSONString(
					new BodyResponse(header.getIdTransaccion(), x.getMessageDeveloper(), x.getCode(), x.getMessage()));
			responsePrint = ClaroUtil.printPrettyJSONString(new BodyResponse(header.getIdTransaccion(),
					x.getMessageDeveloper(), (x.getCode()), x.getMessage()));
		} else if (e instanceof CastException) {
			LOG.info("ENTRO AL  else if DE CastException");
			CastException x = (CastException) e;
			result = ClaroUtil.printPrettyJSONString(new BodyResponse(header.getIdTransaccion(),
					x.getMessageDeveloper(), (x.getCode()), x.getMessage()));
			responsePrint = ClaroUtil.printPrettyJSONString(new BodyResponse(header.getIdTransaccion(),
					x.getMessageDeveloper(), (x.getCode()), x.getMessage()));
		} else {
			LOG.info("ENTRANDO AL ELSE DE mappingResponseException*****");
			result = ClaroUtil.printPrettyJSONString(new BodyResponse(header.getIdTransaccion(),
					ClaroUtil.getExceptionToMensaje(e), propertiesExternos.codigoRespuestaIDT3Generico,
					propertiesExternos.descripcionRespuestaIDT3Generico));
			responsePrint = ClaroUtil.printPrettyJSONString(new BodyResponse(header.getIdTransaccion(),
					ClaroUtil.getExceptionToMensaje(e), propertiesExternos.codigoRespuestaIDT3Generico,
					propertiesExternos.descripcionRespuestaIDT3Generico));
			LOG.info("SALIENDO DEL ELSE DE mappingResponseException****");
		}
		resJSON = Response.status(status).entity(result).build();
		return new MappingResponseException(result, responsePrint, resJSON);
	}
}
