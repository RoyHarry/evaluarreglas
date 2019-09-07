package pe.com.claro.venta.evaluarreglas.integration.client;

import javax.ejb.Local;

import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.eai.oacservices.inquiry.consultacuotacliente.ConsultaCuotaClienteRequest;
import pe.com.claro.eai.oacservices.inquiry.consultacuotacliente.ConsultaCuotaClienteResponse;

@Local
public interface ConsultaCuotaClienteOAC {
	public ConsultaCuotaClienteResponse consultarCuotaCliente(PropertiesExternos configuration, String mensajeLog, String trx,
			ConsultaCuotaClienteRequest request) throws WSClientException;
}
