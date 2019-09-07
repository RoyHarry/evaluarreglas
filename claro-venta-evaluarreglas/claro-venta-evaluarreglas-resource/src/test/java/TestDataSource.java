import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.util.JAXBUtilitarios;
import pe.com.claro.eai.oac.consultadeudacuenta.ClienteType;
import pe.com.claro.eai.oac.consultadeudacuenta.ConsultaDeuda;
import pe.com.claro.eai.oac.consultadeudacuenta.ConsultaDeudaCuenta;
import pe.com.claro.eai.oac.consultadeudacuenta.ConsultaDeudaCuenta_Service;
import pe.com.claro.eai.oac.consultadeudacuenta.ConsultaDeudaResponse;
import pe.com.claro.eai.servicecommons.AuditType;

public class TestDataSource {
	
	public static void main(String[] args) {
		
		ConsultaDeudaResponse response = new ConsultaDeudaResponse();
		ConsultaDeuda reqConsultaDeudaCuenta = new ConsultaDeuda();

		reqConsultaDeudaCuenta.setTxId("1234");
		reqConsultaDeudaCuenta.setPUsuarioAplic("SISACT");
		reqConsultaDeudaCuenta.setPCodAplicacion("109");
		reqConsultaDeudaCuenta.setPCliNroDocIdent(String.valueOf("47174471"));
		reqConsultaDeudaCuenta.setPCliTipoDocIdent(Constantes.CODTIPODOCUMENTODNIOAC);
		
//		response = consultaDeuda("12345", reqConsultaDeudaCuenta);
				
		String dblUmbralDeuda = "5";//obtenerPConvValorPVU(mensajeLog, respParametrosGneralesPVU, Constantes.CONSTCODUMBRALDEUDA);
		String dblPorcentajeDeuda = "5"; //obtenerPConvValorPVU(mensajeLog, respParametrosGneralesPVU, Constantes.PORCENTAJEDEUDA);									
		
		BigDecimal xDeudaVencidaMovil = new BigDecimal(0);
		BigDecimal xDeudaCastigada = new BigDecimal(0);
		BigDecimal xDeudaTotal = new BigDecimal(Constantes.CERO);					
		
//		xDeudaVencidaMovil =  response.getXDeuda().getXDetalleCliente().get(Constantes.CERO).getXDeudaVencidaMovil().add(response.getXDeuda().getXDetalleCliente().get(Constantes.CERO).getXDeudaVencidaFija());
//		xDeudaCastigada = response.getXDeuda().getXDetalleCliente().get(Constantes.CERO).getXDeudaCastigadaFija().add(response.getXDeuda().getXDetalleCliente().get(Constantes.CERO).getXDeudaCastigadaMovil());
		xDeudaTotal = xDeudaVencidaMovil.add(xDeudaCastigada);
		
		
		boolean blnDeudaEvaluacion = false;
		
		if ((xDeudaTotal.doubleValue() > Double.parseDouble(dblUmbralDeuda)) && (xDeudaTotal.doubleValue() > (Double.parseDouble(dblPorcentajeDeuda) * 108.20 / 100)))
		{
			blnDeudaEvaluacion = true;
			System.out.println("Entre");
		}		
		if (blnDeudaEvaluacion) {
			System.out.println("SI");
		}else{
			System.out.println("NO");;
		}
	}
	
	public static ConsultaDeudaResponse consultaDeuda (String trx, ConsultaDeuda request) {
		ConsultaDeudaCuenta_Service consultaDeudaCuenta_Service = new ConsultaDeudaCuenta_Service();
		ConsultaDeudaCuenta consultaDeuda = consultaDeudaCuenta_Service.getConsultaDeudaCuentaSOAP();
		
		String urlClienteWS = "http://172.19.74.71:8909/OAC_Services/Inquiry/ConsultaDeudaCuenta?wsdl";
		String nombreClienteWS = Constantes.NOMBRECLIENTECONSULTADEUDACUENTA;
		
		ConsultaDeudaResponse response = new ConsultaDeudaResponse();
		
		String mensajeTrxClient = "";
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
//		mensajeTrxClient = mensajeLog + "[" + methodName + "]";
//		LOG.info(mensajeTrxClient + "[INICIO] - Sub Metodo[" + methodName + "]" );
        String codResp = Constantes.CONSTANTE_VACIA;
        String msjResp = Constantes.CONSTANTE_VACIA;
		
        Holder<AuditType> auditType = new Holder<AuditType>();
        Holder<ClienteType> clienteType = new Holder<ClienteType>();
        
		try {
//			LOG.info(mensajeTrxClient + "WSDL de servicio: "+ urlClienteWS);
//			LOG.info(mensajeTrxClient + "Parametros de [ENTRADA]: \n" + JAXBUtilitarios.anyObjectToXmlText(request));
	        System.out.println("Parametros de [ENTRADA]: \n" + JAXBUtilitarios.anyObjectToXmlText(request));
			BindingProvider bindingProvider = (BindingProvider) consultaDeuda;
			bindingProvider.getRequestContext().put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,urlClienteWS);
//			LOG.info(trx + "Tiempo de Timeout de Conexion: "
//					+ propertiesExternos.wsConsultaDeudaTimeOut);
			
			consultaDeuda.consultaDeuda(trx, request.getPCodAplicacion(), request.getPUsuarioAplic(), request.getPCliTipoDocIdent(), request.getPCliNroDocIdent(), auditType, clienteType);
            
			response.setAudit(auditType.value);
			response.setXDeuda(clienteType.value);
			System.out.println("PARAMETROS [SALIDA]: \n" + JAXBUtilitarios.anyObjectToXmlText(response));
//			LOG.info(mensajeTrxClient + "PARAMETROS [SALIDA]: \n" + JAXBUtilitarios.anyObjectToXmlText(response));
			
		} catch (Exception ex) {
//			LOG.error(mensajeTrxClient + "ERROR[Exception]: Se produjo una excepcion al ejecutar WS " + urlClienteWS + ": " + ex);
//            
//            if ( ex.toString().contains( SocketTimeoutException.class.toString().replace( "class", Constantes.CONSTANTE_VACIA ).trim() ) ) {
//                
//            	LOG.error(mensajeTrxClient + "Error producido por Timeout");
//                codResp=propertiesExternos.idt2Codigo;
//                msjResp=propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEWS )
//                									  .replace(Constantes.REPLACERECURSO, nombreClienteWS )
//                									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONMETODO)
//                									  .replace(Constantes.REPLACEMETODOSPRECURSO, methodName);
//                
//            }else if ( ex.toString().contains( ConnectException.class.toString().replace( "class", Constantes.CONSTANTE_VACIA ).trim() ) ) {
//                
//            	LOG.error(mensajeTrxClient + "Error producido por No Disponilidad");
//                codResp=propertiesExternos.idt1Codigo;
//                msjResp=propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEWS )
//                									  .replace(Constantes.REPLACERECURSO, nombreClienteWS )
//                									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONMETODO)
//                									  .replace(Constantes.REPLACEMETODOSPRECURSO, methodName);				
//            }else{
//            	codResp=propertiesExternos.idt3Codigo;
//            	msjResp=propertiesExternos.idt3Mensaje.replace(Constantes.replaceExceptionMessage, String.valueOf(ex.getCause()));
//            }
//            throw new WSClientException(codResp, msjResp, ex); 
			
		}finally{
//			LOG.info(mensajeTrxClient + "[FIN] - Metodo[" + new String (Thread.currentThread().getStackTrace()[1].getMethodName()) + "]");
		}
		return response;
		
	}
}
