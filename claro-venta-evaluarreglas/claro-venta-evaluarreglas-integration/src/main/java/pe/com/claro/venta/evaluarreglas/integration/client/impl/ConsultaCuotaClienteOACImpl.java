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
import pe.com.claro.eai.oacservices.inquiry.consultacuotacliente.ConsultaCuotaCliente;
import pe.com.claro.eai.oacservices.inquiry.consultacuotacliente.ConsultaCuotaClienteRequest;
import pe.com.claro.eai.oacservices.inquiry.consultacuotacliente.ConsultaCuotaClienteResponse;
import pe.com.claro.eai.oacservices.inquiry.consultacuotacliente.ConsultarCuotaClientePorType;
import pe.com.claro.venta.evaluarreglas.integration.client.ConsultaCuotaClienteOAC;

@Stateless
public class ConsultaCuotaClienteOACImpl implements ConsultaCuotaClienteOAC{
	
	private static final Logger LOG = LoggerFactory.getLogger(ConsultaCuotaClienteOACImpl.class);	
	
	@Override
	public ConsultaCuotaClienteResponse consultarCuotaCliente(PropertiesExternos propertiesExternos, String mensajeLog,
			String trx, ConsultaCuotaClienteRequest request) throws WSClientException {
		String methodName = Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName();
		String nombreClienteWS = Constantes.NOMBRE_CLIENTE_CONSULTA_DATOS_CLIENTEOAC;
		ConsultaCuotaClienteResponse consultaCuotaClienteResponse= null;
		String codResp = Constantes.CONSTANTE_VACIA;
		String msjResp = Constantes.CONSTANTE_VACIA;
		long tiempoInicioWS = System.currentTimeMillis();
		try {
			mensajeLog += "[" + Constantes.NOMBRE_SUBMETODO_1 + "]";
			LOG.info(mensajeLog+ " [INICIO de metodo: " + "consultarCuotaCliente" + "]");
			LOG.info(mensajeLog+ " Se invoca, metodo=consultarCuotaCliente, URL={}", propertiesExternos.wsConsultaCuotaCliente);
			ConsultaCuotaCliente service = new  ConsultaCuotaCliente();
			ConsultarCuotaClientePorType consultarCuotaClientePorType = service.getConsultarCuotaClienteSOAP();
			BindingProvider bindingProvider = (BindingProvider) consultarCuotaClientePorType;
			bindingProvider.getRequestContext().put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					propertiesExternos.wsConsultaCuotaCliente);
			LOG.info(mensajeLog+ " Tiempo de Timeout de Conexion: {} ", propertiesExternos.wsClaroEvalClienteReglasTimeOut);
			consultaCuotaClienteResponse = consultarCuotaClientePorType.consultarCuotaCliente(request);
			LOG.info(mensajeLog+ " Datos de entrada Body: {} ", JAXBUtilitarios.anyObjectToXmlText(request));			
			LOG.info(mensajeLog+ " Tiempo total de proceso del llamado del Web Services: {} Metodo: {} (ms):{} ",
					"ConsultaCuotaClienteOAC", "ConsultaCuotaClienteOAC-Client", System.currentTimeMillis() - tiempoInicioWS);
			LOG.info(mensajeLog+ " Datos de salida:" + JAXBUtilitarios.anyObjectToXmlText(consultaCuotaClienteResponse));
		} catch (Exception e) {
				if (e.toString().contains(SocketTimeoutException.class.toString().replace("class", Constantes.CONSTANTE_VACIA).trim())) {
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
			LOG.info(mensajeLog+ "{} Tiempo total de proceso(ms):{} ", System.currentTimeMillis() - tiempoInicioWS);
			LOG.info(mensajeLog+ "{} [Fin de metodo: " + Constantes.NOMBRECLIENTECLAROEVALCLIENTESREGLAS + "]");
		}
		return consultaCuotaClienteResponse;
	}	
}
