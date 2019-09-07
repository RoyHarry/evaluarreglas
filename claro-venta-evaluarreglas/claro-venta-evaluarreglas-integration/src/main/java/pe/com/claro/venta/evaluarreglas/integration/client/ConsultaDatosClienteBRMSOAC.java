package pe.com.claro.venta.evaluarreglas.integration.client;

import javax.ejb.Local;

import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.esb.message.test.consultardeudacuentabrms_db.v1.ConsultarDeudaCuentaBRMSDbRequestType;
import pe.com.claro.esb.message.test.consultardeudacuentabrms_db.v1.ConsultarDeudaCuentaBRMSDbResponseType;

@Local
public interface ConsultaDatosClienteBRMSOAC {
	public ConsultarDeudaCuentaBRMSDbResponseType consultarDeudaCuentaBRMSDb(PropertiesExternos configuration, String mensajeLog, String trx,
			ConsultarDeudaCuentaBRMSDbRequestType request) throws WSClientException;
}
