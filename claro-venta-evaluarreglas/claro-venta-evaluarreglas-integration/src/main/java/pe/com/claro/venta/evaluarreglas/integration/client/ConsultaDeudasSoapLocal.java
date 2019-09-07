package pe.com.claro.venta.evaluarreglas.integration.client;

import javax.ejb.Local;

import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.eai.oac.consultadeudacuenta.ConsultaDeuda;
import pe.com.claro.eai.oac.consultadeudacuenta.ConsultaDeudaResponse;

@Local
public interface ConsultaDeudasSoapLocal {
	public ConsultaDeudaResponse consultaDeuda(PropertiesExternos configuration, String mensajeLog, String trx,
			ConsultaDeuda request) throws WSClientException;
}
