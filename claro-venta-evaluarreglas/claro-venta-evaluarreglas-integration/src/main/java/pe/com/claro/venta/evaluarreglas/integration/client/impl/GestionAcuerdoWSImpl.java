package pe.com.claro.venta.evaluarreglas.integration.client.impl;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.resource.exception.WSException;
import pe.com.claro.common.util.JAXBUtilitarios;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.ConsultaSegmentoResponse;
import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.EbsConsultaSegmentoPortType;
import pe.com.claro.eai.ebs.ws.postventa.consultasegmentocliente.EbsConsultaSegmentoService;
import pe.com.claro.eai.ws.postventa.gestionacuerdo.GestionAcuerdoWSPortType;
import pe.com.claro.eai.ws.postventa.gestionacuerdo.GestionAcuerdoWSService;
import pe.com.claro.eai.ws.postventa.gestionacuerdo.types.ObtenerConsultaAcuerdoRequest;
import pe.com.claro.eai.ws.postventa.gestionacuerdo.types.ObtenerConsultaAcuerdoResponse;
import pe.com.claro.eai.ws.postventa.gestionacuerdo.types.ObtenerReintegroEquipoRequest;
import pe.com.claro.eai.ws.postventa.gestionacuerdo.types.ObtenerReintegroEquipoResponse;
import pe.com.claro.venta.evaluarreglas.integration.client.GestionAcuerdoWS;

@Stateless
public class GestionAcuerdoWSImpl implements GestionAcuerdoWS {

	private static final Logger LOG = LoggerFactory.getLogger(GestionAcuerdoWSImpl.class);
	
	@Override
	public ObtenerReintegroEquipoResponse obtenerReintegroEquipo(PropertiesExternos propertiesExternos,
			ObtenerReintegroEquipoRequest request, String mensajeLog) throws WSException, WSClientException {

		String methodName = Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName();
		String nombreClienteWS = "obtenerReintegroEquipo";
		GestionAcuerdoWSPortType ebsGestionAcuerdoWSPortType = null;
		ObtenerReintegroEquipoResponse response = null;	
		String codResp = Constantes.CONSTANTE_VACIA;
		String msjResp = Constantes.CONSTANTE_VACIA;
		long tiempoInicioWS = System.currentTimeMillis();
		try {
			mensajeLog += "[ obtenerReintegroEquipo ]";
			LOG.info(mensajeLog+ " [INICIO de metodo: " + "obtenerReintegroEquipo" + "]");
			LOG.info(mensajeLog+ " Se invoca, metodo=obtenerReintegroEquipo, URL={}", propertiesExternos.gestionAcuerdoWSDL);
			LOG.info(mensajeLog+ "Datos de entrada:{} ", JAXBUtilitarios.anyObjectToXmlText(request));			
			GestionAcuerdoWSService service = new GestionAcuerdoWSService();		
			ebsGestionAcuerdoWSPortType = service.getEbsGestionAcuerdoSB11();
			BindingProvider bindingProvider = (BindingProvider) ebsGestionAcuerdoWSPortType;
			bindingProvider.getRequestContext().put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY, propertiesExternos.gestionAcuerdoWSDL);
			LOG.info(mensajeLog+ " Tiempo de Timeout de Conexion: {} ", propertiesExternos.gestionAcuerdoTimeOut);
			LOG.info(mensajeLog+ " Datos de entrada Body: {}" , JAXBUtilitarios.anyObjectToXmlText(request));
			response = ebsGestionAcuerdoWSPortType.obtenerReintegroEquipo(request);
			
			LOG.info(mensajeLog+ " Tiempo total de proceso del llamado del Web Services: {} Metodo: {} (ms):{} ", "obtenerReintegroEquipo", "obtenerReintegroEquipo", System.currentTimeMillis() - tiempoInicioWS);
			LOG.info(mensajeLog+ " Datos de salida:" + JAXBUtilitarios.anyObjectToXmlText(response));
		} catch (Exception e) {
			if (e.toString().contains(
					SocketTimeoutException.class.toString().replace("class", Constantes.CONSTANTE_VACIA).trim())) {				
				codResp = propertiesExternos.idt2Codigo;
				msjResp = propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEWS)
						.replace(Constantes.REPLACERECURSO, nombreClienteWS)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONMETODO)
						.replace(Constantes.REPLACEMETODOSPRECURSO, methodName);

			} else if (e.toString()
					.contains(ConnectException.class.toString().replace("class", Constantes.CONSTANTE_VACIA).trim())) {
				codResp = propertiesExternos.idt1Codigo;
				msjResp = propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEWS)
						.replace(Constantes.REPLACERECURSO, nombreClienteWS)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONMETODO)
						.replace(Constantes.REPLACEMETODOSPRECURSO, methodName);
			} else {
				codResp = propertiesExternos.idt3Codigo;
				msjResp = propertiesExternos.idt3Mensaje.replace(Constantes.REPLACEEXCEPTIONMESSAGE,
						String.valueOf(e.getCause()));
			}
			throw new WSClientException(codResp, msjResp, e);			
		}
		finally{
			LOG.info(mensajeLog+ " Tiempo total de proceso(ms):{} "+ (System.currentTimeMillis() - tiempoInicioWS));
			LOG.info(mensajeLog+ " [Fin de metodo: obtenerReintegroEquipo ]");
		}
		return response;		
	}

}
