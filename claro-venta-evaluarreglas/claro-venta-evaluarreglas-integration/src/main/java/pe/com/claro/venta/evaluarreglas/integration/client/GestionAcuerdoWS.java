package pe.com.claro.venta.evaluarreglas.integration.client;

import javax.ejb.Local;

import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.resource.exception.WSException;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.eai.ws.postventa.gestionacuerdo.types.ObtenerConsultaAcuerdoRequest;
import pe.com.claro.eai.ws.postventa.gestionacuerdo.types.ObtenerReintegroEquipoRequest;
import pe.com.claro.eai.ws.postventa.gestionacuerdo.types.ObtenerReintegroEquipoResponse;

@Local
public interface GestionAcuerdoWS {
	
	public ObtenerReintegroEquipoResponse obtenerReintegroEquipo(PropertiesExternos propertiesExternos,
			ObtenerReintegroEquipoRequest request, String mensajeLog) throws WSException, WSClientException;	
}
