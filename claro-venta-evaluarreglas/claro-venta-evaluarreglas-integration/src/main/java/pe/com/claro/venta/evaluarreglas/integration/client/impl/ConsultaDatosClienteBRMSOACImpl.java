package pe.com.claro.venta.evaluarreglas.integration.client.impl;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.util.JAXBUtilitarios;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.esb.message.test.consultardeudacuentabrms_db.v1.ConsultarDeudaCuentaBRMSDbRequestType;
import pe.com.claro.esb.message.test.consultardeudacuentabrms_db.v1.ConsultarDeudaCuentaBRMSDbResponseType;
import pe.com.claro.esb.venta.dat_consultadatosbrmsoac.v1.DATConsultaDatosBRMSOACPort;
import pe.com.claro.esb.venta.dat_consultadatosbrmsoac.v1.DATConsultaDatosBRMSOACSOAP11BindingQSService;
import pe.com.claro.venta.evaluarreglas.integration.client.ConsultaDatosClienteBRMSOAC;

@Stateless
public class ConsultaDatosClienteBRMSOACImpl implements ConsultaDatosClienteBRMSOAC{
	
	private static final Logger LOG = LoggerFactory.getLogger(ConsultaDatosClienteBRMSOACImpl.class);

	@Override
	public ConsultarDeudaCuentaBRMSDbResponseType consultarDeudaCuentaBRMSDb(PropertiesExternos propertiesExternos,
			String mensajeLog, String trx, ConsultarDeudaCuentaBRMSDbRequestType consultarDeudaCuentaBRMSDbRequestType) throws WSClientException {
		ConsultarDeudaCuentaBRMSDbResponseType consultarDeudaCuentaBRMSDbResponseType = new ConsultarDeudaCuentaBRMSDbResponseType(); 
		
		DATConsultaDatosBRMSOACPort datConsultaDatosBRMSOACPort = null;
		DATConsultaDatosBRMSOACSOAP11BindingQSService datConsultaDatosBRMSOACSOAP11BindingQSService = new DATConsultaDatosBRMSOACSOAP11BindingQSService();
		datConsultaDatosBRMSOACPort = datConsultaDatosBRMSOACSOAP11BindingQSService.getDATConsultaDatosBRMSOACSOAP11BindingQSPort();
		String nombreClienteWS = Constantes.NOMBRECLIENTECONSULTA_DATOS_BRMSOAC;
		long tiempoInicioWS = System.currentTimeMillis();
		String mensajeTrxClient = mensajeLog;
		String methodName = Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName();
		mensajeTrxClient = mensajeLog + "[" + methodName + "]";
		LOG.info(mensajeTrxClient, "{} [INICIO] - Sub Metodo[ {} ", methodName, "]" );
        String codResp = Constantes.CONSTANTE_VACIA;
        String msjResp = Constantes.CONSTANTE_VACIA;
		        
		try {
			LOG.info(mensajeLog+ " [INICIO de metodo: consultarDeudaCuentaBRMSDb]");
			LOG.info(mensajeLog+ " Se invoca, metodo=consultarDeudaCuentaBRMSDb, URL={}", propertiesExternos.wsConsultaDatosClienteBRMSOAC);
			LOG.info(mensajeLog+ " Datos de entrada Body:{} ",  JAXBUtilitarios.anyObjectToXmlText(consultarDeudaCuentaBRMSDbRequestType));						
			consultarDeudaCuentaBRMSDbResponseType = datConsultaDatosBRMSOACPort.consultarDeudaCuentaBRMSDb(consultarDeudaCuentaBRMSDbRequestType);
			BindingProvider bindingProvider = (BindingProvider) datConsultaDatosBRMSOACPort;
			bindingProvider.getRequestContext().put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,propertiesExternos.wsConsultaDatosClienteBRMSOAC);
			LOG.info(mensajeLog+ " Tiempo de Timeout de Conexion: {}", propertiesExternos.wsConsultaDatosClienteBRMSOACTimeOut);
			LOG.info(mensajeLog+ " Tiempo total de proceso del llamado del Web Services: {} Metodo: {} (ms):{} ", "consultarDeudaCuentaBRMSDb", "consultarDeudaCuentaBRMSDb", System.currentTimeMillis() - tiempoInicioWS);
			LOG.info(mensajeLog+ " Datos de salida:{} ", JAXBUtilitarios.anyObjectToXmlText(consultarDeudaCuentaBRMSDbResponseType));
		} catch (Exception ex) {
			if ( ex.toString().contains( SocketTimeoutException.class.toString().replace( "class", Constantes.CONSTANTE_VACIA ).trim() ) ) {
                
            	LOG.error(mensajeTrxClient+ " Error producido por Timeout");
                codResp=propertiesExternos.idt2Codigo;
                msjResp=propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEWS )
                									  .replace(Constantes.REPLACERECURSO, nombreClienteWS )
                									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONMETODO)
                									  .replace(Constantes.REPLACEMETODOSPRECURSO, methodName);
                
            }else if ( ex.toString().contains( ConnectException.class.toString().replace( "class", Constantes.CONSTANTE_VACIA ).trim() ) ) {
                
            	LOG.error(mensajeTrxClient+ " Error producido por No Disponilidad");
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
    			LOG.info(mensajeLog+ " Tiempo total de proceso(ms):{} "+ (System.currentTimeMillis() - tiempoInicioWS));
    			LOG.info(mensajeLog+ " [Fin de metodo: " + Constantes.NOMBRECLIENTECLAROEVALCLIENTESREGLAS + "]");
    		}
		return consultarDeudaCuentaBRMSDbResponseType;
	}
}
