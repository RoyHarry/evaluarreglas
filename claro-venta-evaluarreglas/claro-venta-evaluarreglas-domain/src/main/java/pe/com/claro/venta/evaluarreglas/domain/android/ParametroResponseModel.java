package pe.com.claro.venta.evaluarreglas.domain.android;

import java.util.ArrayList;
import java.util.List;

import pe.com.claro.common.bean.HeaderRequest;
import pe.com.claro.common.resource.exception.WSException;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.parametro.ParametroRequest;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.parametro.ParametroResponse;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.parametro.ParametroResponseWs;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.parametro.ParametrosResponseWs;
import pe.com.claro.venta.evaluarreglas.domain.webservice.ParametroModel;
import pe.com.claro.venta.evaluarreglas.integration.client.impl.ParametrosClientWsRestImpl;
import pe.com.claro.venta.evaluarreglas.integration.client.ParametrosClientWsRest;

public class ParametroResponseModel {

	public ParametrosResponseWs obtenerDataParametros(HeaderRequest headerRequest, PropertiesExternos propertiesExternos) throws WSException {
		ParametrosClientWsRest parametrosRestClient = new ParametrosClientWsRestImpl();
		ParametroModel parametroModel = new ParametroModel();
		
		ParametroRequest parametroRequest = parametroModel.cargarDataParametroRequest(propertiesExternos);
		
		return parametrosRestClient.getParametros(parametroRequest, headerRequest, propertiesExternos);
	}
	
	public List<ParametroResponse> filtrarDataResponseAndroid(ParametrosResponseWs parametrosResponseWs) {

		List<ParametroResponseWs> listaParametrosWs = parametrosResponseWs.getData();
		List<ParametroResponse> listaParametrosAndroid = new ArrayList<>();
		
		for (ParametroResponseWs parametroResponseWs : listaParametrosWs) {
			ParametroResponse parametroResponse = new ParametroResponse();
			
			parametroResponse.setCodigo(parametroResponseWs.getCodigo());
			parametroResponse.setData(parametroResponseWs.getData());
			parametroResponse.setDescripcionData(parametroResponseWs.getDescripcionData());
			parametroResponse.setGrupo(parametroResponseWs.getGrupo());
			
			listaParametrosAndroid.add(parametroResponse);
		}
		
		return listaParametrosAndroid;		
	}
}
