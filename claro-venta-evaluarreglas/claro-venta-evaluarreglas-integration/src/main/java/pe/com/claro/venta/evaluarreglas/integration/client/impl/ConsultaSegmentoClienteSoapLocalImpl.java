package pe.com.claro.venta.evaluarreglas.integration.client.impl;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.util.JAXBUtilitarios;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.ConsultaSegmentoRequest;
import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.ConsultaSegmentoResponse;
import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.EbsConsultaSegmentoPortType;
import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.EbsConsultaSegmentoService;
import pe.com.claro.venta.evaluarreglas.integration.client.ConsultaSegmentoClienteSoapLocal;

@Stateless
public class ConsultaSegmentoClienteSoapLocalImpl implements ConsultaSegmentoClienteSoapLocal {

	private static final Logger LOG = LoggerFactory.getLogger(ConsultaSegmentoClienteSoapLocalImpl.class);

	@Override
	public ConsultaSegmentoResponse consultaSegmentoCliente(PropertiesExternos propertiesExternos,
			ConsultaSegmentoRequest request, String mensajeLog) throws WSClientException {
		String methodName = Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName();
		String nombreClienteWS = Constantes.NOMBRECLIENTECLAROEVALCLIENTESREGLAS;
		EbsConsultaSegmentoPortType ebsConsultaSegmentoPortType = null;
		ConsultaSegmentoResponse response = null;	
		String codResp = Constantes.CONSTANTE_VACIA;
		String msjResp = Constantes.CONSTANTE_VACIA;
		long tiempoInicioWS = System.currentTimeMillis();
		try {
			mensajeLog += "[" + Constantes.NOMBRE_SUBMETODO_1 + "]";
			LOG.info(mensajeLog+ " [INICIO de metodo: " + "consultaSegmentoCliente" + "]");
			LOG.info(mensajeLog+ " Se invoca, metodo=consultaSegmentoCliente, URL={}", propertiesExternos.wsConsultaSegmentoClienteWSDL);
			LOG.info(mensajeLog+ " Datos de entrada: {} " + JAXBUtilitarios.anyObjectToXmlText(request));			
			EbsConsultaSegmentoService service = new  EbsConsultaSegmentoService();		
			ebsConsultaSegmentoPortType = service.getEbsConsultaSegmento11SB();
			BindingProvider bindingProvider = (BindingProvider) ebsConsultaSegmentoPortType;
			bindingProvider.getRequestContext().put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,propertiesExternos.wsConsultaSegmentoClienteWSDL);
			LOG.info(mensajeLog+ " Tiempo de Timeout de Conexion: {} ", propertiesExternos.wsConsultaSegmentoClienteTimeOut);
			LOG.info(mensajeLog+ " Datos de entrada Body: {}", JAXBUtilitarios.anyObjectToXmlText(request));
			response = ebsConsultaSegmentoPortType.consultaSegmento(request);
			LOG.info(mensajeLog+ " Tiempo total de proceso del llamado del Web Services: {} Metodo: {} (ms):{} ", "ConsultaSegmentoResponse", "consultaSegmentoCliente", System.currentTimeMillis() - tiempoInicioWS);
			LOG.info(mensajeLog+ " Datos de salida: {}", JAXBUtilitarios.anyObjectToXmlText(response));
		} catch (Exception e) {
			if (e.toString().contains(
					SocketTimeoutException.class.toString().replace("class", Constantes.CONSTANTE_VACIA).trim())) {
				codResp = propertiesExternos.idt2Codigo;
				msjResp = propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEWS)
						.replace(Constantes.REPLACERECURSO, nombreClienteWS)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONMETODO)
						.replace(Constantes.REPLACEMETODOSPRECURSO, methodName);
			} else if (e.toString()
					.contains(ConnectException.class.toString().replace("class", Constantes.CONSTANTE_VACIA).trim())) {				
				codResp = propertiesExternos.idt1Codigo;
				msjResp = propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEWS)
						.replace(Constantes.REPLACERECURSO, nombreClienteWS)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONMETODO)
						.replace(Constantes.REPLACEMETODOSPRECURSO, methodName);
			} else {
				codResp = propertiesExternos.idt3Codigo;
				msjResp = propertiesExternos.idt3Mensaje.replace(Constantes.REPLACEEXCEPTIONMESSAGE,
						String.valueOf(e.getCause()));
			}
			throw new WSClientException(codResp, msjResp, e);			
		}
		finally{
			LOG.info(mensajeLog+ " Tiempo total de proceso(ms):{} "+ (System.currentTimeMillis() - tiempoInicioWS));
			LOG.info(mensajeLog+ " [Fin de metodo: " + Constantes.NOMBRECLIENTECLAROEVALCLIENTESREGLAS + "]");
		}
		return response;
	}

}
