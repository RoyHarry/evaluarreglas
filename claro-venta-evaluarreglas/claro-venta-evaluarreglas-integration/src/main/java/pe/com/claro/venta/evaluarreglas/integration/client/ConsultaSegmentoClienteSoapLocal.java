package pe.com.claro.venta.evaluarreglas.integration.client;

import javax.ejb.Local;

import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.ConsultaSegmentoRequest;
import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.ConsultaSegmentoResponse;

@Local
public interface ConsultaSegmentoClienteSoapLocal {
	public ConsultaSegmentoResponse consultaSegmentoCliente(PropertiesExternos propertiesExternos, ConsultaSegmentoRequest request, String mensajeLog) throws WSClientException;
}
