package pe.com.claro.venta.evaluarreglas.domain.webservice;

import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.parametro.ParametroRequest;

public class ParametroModel {
public ParametroRequest cargarDataParametroRequest(PropertiesExternos propertiesExternos) {
		
		ParametroRequest parametroRequest = new ParametroRequest();
		parametroRequest.setSistema(propertiesExternos.parametroRequestSistema);
		parametroRequest.setVersion(propertiesExternos.parametroRequestVersion);
		parametroRequest.setTipoOperacion(propertiesExternos.parametroRequestTipoOperacion);
		parametroRequest.setGrupo(propertiesExternos.parametroRequestGrupo);
		parametroRequest.setCodigo(propertiesExternos.parametroRequestCodigo);

		return parametroRequest;
	}
}
