package pe.com.claro.venta.evaluarreglas.integration.client;

import javax.ejb.Local;

import pe.com.claro.common.bean.HeaderRequest;
import pe.com.claro.common.resource.exception.WSException;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.eai.ebs.ventas.ebscreditosws.ws.types.ConsultarDatosDCResponse;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes.EvaluarDatosClienteRequestWS;

@Local
public interface IEbsCreditosWS {

	public ConsultarDatosDCResponse evaluarCredito(PropertiesExternos properties, String mensajeLog, EvaluarDatosClienteRequestWS request, HeaderRequest header) throws WSException;
}
