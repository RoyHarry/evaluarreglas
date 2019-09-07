package pe.com.claro.venta.evaluarreglas.integration.client.impl;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.util.JAXBUtilitarios;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.eai.oac.consultadeudacuenta.ClienteType;
import pe.com.claro.eai.oac.consultadeudacuenta.ConsultaDeuda;
import pe.com.claro.eai.oac.consultadeudacuenta.ConsultaDeudaCuenta;
import pe.com.claro.eai.oac.consultadeudacuenta.ConsultaDeudaCuenta_Service;
import pe.com.claro.eai.oac.consultadeudacuenta.ConsultaDeudaResponse;
import pe.com.claro.eai.servicecommons.AuditType;
import pe.com.claro.venta.evaluarreglas.integration.client.ConsultaDeudasSoapLocal;

@Stateless
public class ConsultaDeudasSoapLocalImpl implements ConsultaDeudasSoapLocal{

	private static final Logger LOG = LoggerFactory.getLogger(ConsultaDeudasSoapLocalImpl.class);
	
	@Override
	public ConsultaDeudaResponse consultaDeuda(PropertiesExternos propertiesExternos, String mensajeLog, String trx,
			ConsultaDeuda request) throws WSClientException {
		
		
		ConsultaDeudaCuenta_Service consultaDeudaCuentaService = new ConsultaDeudaCuenta_Service();
		ConsultaDeudaCuenta consultaDeuda = consultaDeudaCuentaService.getConsultaDeudaCuentaSOAP();		
		String urlClienteWS = propertiesExternos.wsConsultaDeudaCuentaWSDL;
		String nombreClienteWS = Constantes.NOMBRECLIENTECONSULTADEUDACUENTA;		
		ConsultaDeudaResponse response = new ConsultaDeudaResponse();
		String methodName = Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName();
		LOG.info(mensajeLog+ " [INICIO] - Sub Metodo[ {}", methodName, "]" );
        String codResp = Constantes.CONSTANTE_VACIA;
        String msjResp = Constantes.CONSTANTE_VACIA;
        long tiempoInicioWS = System.currentTimeMillis();
        Holder<AuditType> auditType = new Holder<>();
        Holder<ClienteType> clienteType = new Holder<>();
        
		try {
			LOG.info(mensajeLog+ " WSDL de servicio: {} ", urlClienteWS);
			LOG.info(mensajeLog+ " Parametros de [ENTRADA]: \n {} " , JAXBUtilitarios.anyObjectToXmlText(request));
	        
			BindingProvider bindingProvider = (BindingProvider) consultaDeuda;
			bindingProvider.getRequestContext().put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					propertiesExternos.wsConsultaDeudaCuentaWSDL);
			LOG.info(mensajeLog+ " Tiempo de Timeout de Conexion: {}", propertiesExternos.wsConsultaDeudaTimeOut);
			
			consultaDeuda.consultaDeuda(trx, request.getPCodAplicacion(), request.getPUsuarioAplic(), request.getPCliTipoDocIdent(), request.getPCliNroDocIdent(), auditType, clienteType);
            
			response.setAudit(auditType.value);
			response.setXDeuda(clienteType.value);
			
			LOG.info(mensajeLog+ " PARAMETROS [SALIDA]: \n {}", JAXBUtilitarios.anyObjectToXmlText(response));
			
		} catch (Exception ex) {			
            if ( ex.toString().contains( SocketTimeoutException.class.toString().replace( "class", Constantes.CONSTANTE_VACIA ).trim() ) ) {                
            	codResp=propertiesExternos.idt2Codigo;
                msjResp=propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEWS )
                									  .replace(Constantes.REPLACERECURSO, nombreClienteWS )
                									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONMETODO)
                									  .replace(Constantes.REPLACEMETODOSPRECURSO, methodName);
                
            }else if ( ex.toString().contains( ConnectException.class.toString().replace( "class", Constantes.CONSTANTE_VACIA ).trim() ) ) {                
                codResp=propertiesExternos.idt1Codigo;
                msjResp=propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEWS )
                									  .replace(Constantes.REPLACERECURSO, nombreClienteWS )
                									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONMETODO)
                									  .replace(Constantes.REPLACEMETODOSPRECURSO, methodName);				
            }else{
            	codResp=propertiesExternos.idt3Codigo;
            	msjResp=propertiesExternos.idt3Mensaje.replace(Constantes.REPLACEEXCEPTIONMESSAGE, String.valueOf(ex.getCause()));
            }
            throw new WSClientException(codResp, msjResp, ex); 
		}
            finally{
    			LOG.info(mensajeLog+ " Tiempo total de proceso(ms):{} ", System.currentTimeMillis() - tiempoInicioWS);
    			LOG.info(mensajeLog+ " [Fin de metodo: " + Constantes.NOMBRECLIENTECLAROEVALCLIENTESREGLAS + "]");
    		}
		return response;
	}
}
