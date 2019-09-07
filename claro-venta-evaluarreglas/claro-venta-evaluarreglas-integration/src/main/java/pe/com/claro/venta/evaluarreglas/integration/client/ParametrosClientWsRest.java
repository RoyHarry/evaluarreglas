package pe.com.claro.venta.evaluarreglas.integration.client;

import javax.ejb.Local;

import pe.com.claro.common.bean.HeaderRequest;
import pe.com.claro.common.resource.exception.WSException;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.parametro.ParametroRequest;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.parametro.ParametrosResponseWs;

@Local
public interface ParametrosClientWsRest {
	ParametrosResponseWs getParametros(ParametroRequest request, HeaderRequest headerRequest, PropertiesExternos propertiesExternos) throws WSException;
}
