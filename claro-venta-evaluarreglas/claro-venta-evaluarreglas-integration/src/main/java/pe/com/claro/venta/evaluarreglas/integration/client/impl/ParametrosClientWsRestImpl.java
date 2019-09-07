package pe.com.claro.venta.evaluarreglas.integration.client.impl;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import pe.com.claro.common.bean.HeaderRequest;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.util.JAXBUtilitarios;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.restclient.AbstractRestClient;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.parametro.ParametroRequest;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.parametro.ParametrosResponseWs;
import pe.com.claro.common.resource.exception.WSException;
import pe.com.claro.venta.evaluarreglas.integration.client.ParametrosClientWsRest;
import pe.com.claro.venta.evaluarreglas.integration.util.UtilIntegration;

@Stateless
public class ParametrosClientWsRestImpl extends AbstractRestClient implements ParametrosClientWsRest{	
	
	private static final Logger LOG = LoggerFactory.getLogger(ParametrosClientWsRestImpl.class);

	private UtilIntegration util = new UtilIntegration();
	
	@Override
	public ParametrosResponseWs getParametros(ParametroRequest request, HeaderRequest headerRequestBean,
			PropertiesExternos propertiesExternos) throws WSException {
		try {
			
			LOG.info(" [{} 1. INICIO - SERVICIO PARAMETRO]", headerRequestBean.getIdTransaccion());
			ObjectMapper mapper = new ObjectMapper();
			
			Client clientRest = Client.create();
			clientRest.setReadTimeout(Integer.parseInt(propertiesExternos.parametroRestClientTimeOut));
			WebResource webResource = clientRest.resource(propertiesExternos.parametroRestClientURL + propertiesExternos.parametroRestClientMethod);
			Builder builder = webResource.type(Constantes.TYPEREQUEST);
			util.setHeaderBuilder(builder, headerRequestBean);
			
			LOG.info("[{}] 2.1 SERVICIO PARAMETRO, URL: {}", headerRequestBean.getIdTransaccion(), propertiesExternos.parametroRestClientURL + propertiesExternos.parametroRestClientMethod);
			LOG.info("[{}] 2.2 SERVICIO PARAMETRO, DATA REQUEST: {}", headerRequestBean.getIdTransaccion(), JAXBUtilitarios.anyObjectToXmlText(request));
			ClientResponse response = builder.post(ClientResponse.class, mapper.writeValueAsString(request));
			String data = response.getEntity(String.class);
			ParametrosResponseWs parametrosResponseWs = mapper.readValue(data, ParametrosResponseWs.class);
			
			LOG.info("[{}] 3. SERVICIO PARAMETRO RESPONSE: {}", headerRequestBean.getIdTransaccion(), JAXBUtilitarios.anyObjectToXmlText(parametrosResponseWs));
			LOG.info("[{} 4. FIN - PARAMETRO]", headerRequestBean.getIdTransaccion());

			return parametrosResponseWs;
		}
		catch (Exception e) {
			LOG.info("[{} EXCEPTION PARAMETRO: {}", headerRequestBean.getIdTransaccion(), e.getMessage());

			if (e.getMessage().toUpperCase().contains(Constantes.TIMEOUTEXCEPTION.toUpperCase()) || e.getMessage().toUpperCase().contains(Constantes.TIMEOUTEXCEPTION2.toUpperCase())) {
				throw new WSException(propertiesExternos.idt1Codigo,
						propertiesExternos.idt1Mensaje.replace("$ws", "claro-venta-parametro").replace("$metodo", "obtenerparametro"),
						e.getMessage(), e, Constantes.STATUS_TIME_OUT);
			} else {
				throw new WSException(propertiesExternos.idt2Codigo,
						propertiesExternos.idt2Mensaje.replace("$ws", "claro-venta-parametro").replace("$metodo", "obtenerparametro"),
						e.getMessage(), e, Constantes.STATUS_DISPONIBILIDAD);
			}
		}
	}
}
