package pe.com.claro.venta.evaluarreglas.resource.mapper;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.core.HttpHeaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.bean.BodyResponse;
import pe.com.claro.common.bean.HeaderRequest;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.evaluarreglas.model.ClienteEvalRequest;
import pe.com.claro.venta.evaluarreglas.model.ClienteEvalResponse;

public class CastingMapper {
	
	private static final Logger LOG = LoggerFactory.getLogger(CastingMapper.class);

	/**
	 * ********************************
	 * CONFIGURABLE POR EL AP
	 * *********************************
     * Instancia y enlaza los objetos request con sus respectivos Response canonicos
     *
     * @param1 Object request (Para la comparacion de instancia)
     * @param2 Object Body Response el cual contendra el mensaje personalizado llenando al objeto response.
     * @return Objeto response correspondiente al request enviado.
     */
	public static Object responseInstanceOfBeans(Object beanRequest, BodyResponse response) {
		if (beanRequest instanceof ClienteEvalRequest) {
			return new ClienteEvalResponse(response);
		}
		return Constantes.NULO;
	}
	
	/**
	 * * ********************************
	 * CONFIGURABLE POR EL AP
	 * *********************************
     * [FASE 1] - Metodo de evaluacion de parametros Mandatorios, se agregara configuracion adicional cuando el Bean tenga SUB objetos, ejemplo Insertar Pago
     *
     * @param1 Object request
     * @return String Retornara el mensaje de error, si no existe retornara vacio.
     */
	public static String requestConfirmValues(Object beanRequest) {
		String msgError = Constantes.VACIO;
		
		if (beanRequest instanceof ClienteEvalRequest) {
			msgError = beanHaveViolations(beanRequest);
		}
		return msgError;
	}
	
	/**
	 * * ********************************
	 * NO ES NECESARIO SER CONFIGURADO
	 * *********************************
     * Instancia y enlaza los objetos request con sus respectivos Response canonicos (Se envia ).
     * (vacio).
     *
     * @param1 HTTPHeaders valores de cabecera
     * @param2 Body: Modelo canonico para la evaluacion.
     * @return Objeto del tipo response canonico.
     */
	public static Object mapperValidacionParametrosEntrada(PropertiesExternos propExternos ,HttpHeaders httpHeaders, Object beanRequest){
		BodyResponse response = null;
		LOG.debug("############################################################################################## ");
		LOG.info("########################### [PRE-REQUISITOS]....FASE 1: VALIDANDO PARAMETROS OBLIGATORIOS #### ");
		LOG.debug("############################################################################################## ");
		LOG.debug("########################### ...........[Header] Evaluando Header ....  "); 
		LOG.info(" Parametros Obligatorios de Header:[idTransaccion]-[accept]");
		HeaderRequest header = new HeaderRequest(httpHeaders);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<HeaderRequest>> constraintViolations = validator.validate(header);
		if (constraintViolations.size() >= Constantes.UNO) {
			response = new BodyResponse();
			response.setCodigoRespuesta(propExternos.codigoRespuestaIDF1EvaluarReglas);
			response.setMensajeRespuesta(propExternos.descripcionRespuestaIDF1EvaluarReglas);
			response.setMensajeError("Datos de Cabecera incompletos, VERIFICAR:  " + header.toString());
			LOG.error("########################### [PRE-REQUISITOS]...........[Header] Faltan parametros obligatorios ....  "); 
			return responseInstanceOfBeans(beanRequest, response);
		}
		LOG.debug("########################### ...........[Body] Evaluando Body .... ");
		String sBody = requestConfirmValues(beanRequest);
		if (!sBody.isEmpty()) {
			response = new BodyResponse();
			response.setCodigoRespuesta(propExternos.codigoRespuestaIDF1EvaluarReglas);
			response.setMensajeRespuesta(propExternos.descripcionRespuestaIDF1EvaluarReglas);
			response.setMensajeError("Datos de Body incompletos, VERIFICAR:  " + sBody);
			LOG.error("########################### [PRE-REQUISITOS]...........[Body] Faltan parametros obligatorios. "); 
			return responseInstanceOfBeans(beanRequest, response);
		}
		LOG.debug("############################################################################################## ");
		LOG.info("########################### ....EXITO, todos los valores son correctos #### ");
		LOG.debug("############################################################################################## ");
		return response;
	}
	
	/**
	 * * ********************************
	 * NO ES NECESARIO SER CONFIGURADO
	 * *********************************
     * Instancia y enlaza los objetos request con sus respectivos Response canonicos (Se envia ).
     * (vacio).
     *
     * @param1 Objeto request
     * @return String si existio un error de validacion retorna el detalle del error.
     */
	public static String beanHaveViolations(Object request){
		
		String msgError = Constantes.VACIO;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
		if (constraintViolations.size() >= Constantes.UNO)
			for (ConstraintViolation<Object> cv : constraintViolations) {
				msgError = cv.getPropertyPath() + " - " + cv.getMessage();
			}
		return msgError;
	}
}