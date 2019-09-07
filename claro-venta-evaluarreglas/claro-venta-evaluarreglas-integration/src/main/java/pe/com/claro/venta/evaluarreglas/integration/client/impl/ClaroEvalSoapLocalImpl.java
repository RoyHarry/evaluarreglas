package pe.com.claro.venta.evaluarreglas.integration.client.impl;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.ClaroEvalClientesReglasDecisionService;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.ClaroEvalClientesReglasDecisionService_Service;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.ClaroEvalClientesReglasRequest;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.ClaroEvalClientesReglasResponse;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.util.JAXBUtilitarios;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.evaluarreglas.integration.client.ClaroEvalSoapLocal;

@Stateless
public class ClaroEvalSoapLocalImpl implements ClaroEvalSoapLocal {

	private static final Logger LOG = LoggerFactory.getLogger(ClaroEvalSoapLocalImpl.class);
	
	@Override
	public ClaroEvalClientesReglasResponse claroEvalClientesReglasV2(PropertiesExternos propertiesExternos, ClaroEvalClientesReglasRequest request, String mensajeLog) throws WSClientException {
		ClaroEvalClientesReglasResponse response = null;
		
		String methodName = Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName();		
		String nombreClienteWS = Constantes.NOMBRECLIENTECLAROEVALCLIENTESREGLAS;
		long tiempoInicioWS = System.currentTimeMillis();
		String codResp = Constantes.CONSTANTE_VACIA;
		String msjResp = Constantes.CONSTANTE_VACIA;
		try {
			mensajeLog += "[" + Constantes.NOMBRE_SUBMETODO_1 + "]";
			LOG.info(mensajeLog+ " [INICIO de metodo: {}", Constantes.NOMBRE_SUBMETODO_1, "]");
			LOG.info(mensajeLog+ " Se invoca, metodo=claroEvalClientesReglas, URL={}", propertiesExternos.wsClaroEvalClienteReglasWSDL);
			ClaroEvalClientesReglasDecisionService_Service service = new  ClaroEvalClientesReglasDecisionService_Service();
			ClaroEvalClientesReglasDecisionService service2 = service.getClaroEvalClienteAppClaroEvalClientesReglasPort();
			BindingProvider bindingProvider = (BindingProvider) service2;
			bindingProvider.getRequestContext().put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					propertiesExternos.wsClaroEvalClienteReglasWSDL);
			LOG.info(mensajeLog+ " Tiempo de Timeout de Conexion: {} ", propertiesExternos.wsClaroEvalClienteReglasTimeOut);
			response = service2.claroEvalClientesReglas(request);
			LOG.info(mensajeLog+ " Datos de entrada Body: {} ", JAXBUtilitarios.anyObjectToXmlText(request));
			
			LOG.info(mensajeLog+ " Tiempo total de proceso del llamado del Web Services: {} Metodo: {} (ms):{} ",
					"ClaroEvalClientesReglasResponse", "claroEvalClientesReglas", System.currentTimeMillis() - tiempoInicioWS);
			LOG.info(mensajeLog+ " Datos de salida: {} ", JAXBUtilitarios.anyObjectToXmlText(response));
		} catch (Exception e) {
				if (e.toString().contains(SocketTimeoutException.class.toString().replace("class", Constantes.CONSTANTE_VACIA).trim())) {
				codResp = propertiesExternos.idt2Codigo;
				msjResp = propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEWS)
						.replace(Constantes.REPLACERECURSO, nombreClienteWS)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONMETODO)
						.replace(Constantes.REPLACEMETODOSPRECURSO, methodName);

			} else if (e.toString().contains(ConnectException.class.toString().replace("class", Constantes.CONSTANTE_VACIA).trim())) {
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
			LOG.info(mensajeLog+ " Tiempo total de proceso(ms): {} ", System.currentTimeMillis() - tiempoInicioWS);
			LOG.info(mensajeLog+ " [Fin de metodo: " + Constantes.NOMBRECLIENTECLAROEVALCLIENTESREGLAS + "]");
		}
		return response;
	}

}
