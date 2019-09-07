package pe.com.claro.venta.evaluarreglas.domain.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import pe.com.claro.common.exception.DBException;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.parametro.ParametroRequest;
import pe.com.claro.venta.evaluarreglas.domain.repository.ObtenerDataOperacionRepository;
import pe.com.claro.venta.evaluarreglas.model.BRMSBioMovilRequest;
import pe.com.claro.venta.evaluarreglas.model.BRMSBioMovilResponse;
import pe.com.claro.venta.evaluarreglas.model.ClienteEvalRequest;
import pe.com.claro.venta.evaluarreglas.model.ClienteEvalResponse;
import pe.com.claro.venta.evaluarreglas.model.ParametrosPvuPlsqlResponse;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ConDetalleLineaBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ConDetalleLineaBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ParamGeneralBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ParamGeneralBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuCalculoLCxProductoBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuCalculoLCxProductoBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuConTipoCuotaResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuConsultaCVEPendRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuConsultaCVEPendResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuCuotaIniComercialRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuCuotaIniComercialResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuFacturaXProductoBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuFacturaXProductoBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanBilleteraBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanBilleteraBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanXProductoBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanXProductoBeanResponse;

@Stateless
public class ObtenerDataOperacionDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
    private ObtenerDataOperacionRepository obtenerDataOperacionRepository;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ClienteEvalResponse dataOperacionPvuV2(PropertiesExternos propertiesExternos, ClienteEvalRequest dataRequest, String mensajeLog) throws DBException{		
		return obtenerDataOperacionRepository.getDataValidaClienteReglasV2(propertiesExternos, dataRequest, mensajeLog);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BRMSBioMovilResponse insertBRMSBioMovilV2(PropertiesExternos propertiesExternos, BRMSBioMovilRequest dataRequest, String idTx, String mensajeLog ) throws DBException{
		
		return obtenerDataOperacionRepository.insertBRMSBioMovilV2(propertiesExternos, dataRequest, idTx, mensajeLog);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Pvu3Play6ParamGeneralBeanResponse> obtenerParametroGeneralPVU(PropertiesExternos propertiesExternos, Pvu3Play6ParamGeneralBeanRequest request, String mensajeLog)throws DBException{
		return obtenerDataOperacionRepository.obtenerParametroGeneralPVU(propertiesExternos, request, mensajeLog);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PvuFacturaXProductoBeanResponse obtenerMontoFactxProductoPVU(PropertiesExternos propertiesExternos, String mensajeLog, PvuFacturaXProductoBeanRequest request, String flagLog)throws DBException{
		return obtenerDataOperacionRepository.obtenerMontoFactxProductoPVU(propertiesExternos, request, mensajeLog);
	}	
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PvuPlanXProductoBeanResponse obtenerPlanXProductoPVU(PropertiesExternos propertiesExternos, String mensajeLog, PvuPlanXProductoBeanRequest request, String flagLog)throws DBException{
		return obtenerDataOperacionRepository.obtenerPlanXProductoPVU(propertiesExternos, request, mensajeLog);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Pvu3Play6ConDetalleLineaBeanResponse obtenerDetalleLinea3play6PVU(PropertiesExternos propertiesExternos, String mensajeLog, Pvu3Play6ConDetalleLineaBeanRequest request, String flagLog)throws DBException{
		return obtenerDataOperacionRepository.obtenerDetalleLinea3play6PVU(propertiesExternos, request, mensajeLog);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PvuPlanBilleteraBeanResponse obtenerPlanBilleteraPVU(PropertiesExternos propertiesExternos, String mensajeLog, PvuPlanBilleteraBeanRequest request, String flagLog)throws DBException{
		return obtenerDataOperacionRepository.obtenerPlanBilleteraPVU(propertiesExternos, request, mensajeLog);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PvuCalculoLCxProductoBeanResponse calculoLCxProductoPVU(PropertiesExternos propertiesExternos,String mensajeLog, PvuCalculoLCxProductoBeanRequest request, String flagLog)throws DBException{
		return obtenerDataOperacionRepository.calculoLCxProductoPVU(propertiesExternos, request, mensajeLog);
			
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PvuCuotaIniComercialResponse obtenerCuotaIniComercial(PropertiesExternos propertiesExternos, PvuCuotaIniComercialRequest request, String mensajeLog)throws DBException{
		return obtenerDataOperacionRepository.obtenerCuotaIniComercial(propertiesExternos, request, mensajeLog);
			
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PvuConTipoCuotaResponse> obtenerConTipoCuota(String mensajeLog, PropertiesExternos propertiesExternos)throws DBException{
		return obtenerDataOperacionRepository.obtenerConTipoCuota(mensajeLog, propertiesExternos);			
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ParametrosPvuPlsqlResponse dataParametros(ParametroRequest parametroRequest , String idTx, PropertiesExternos propertiesExternos, String mensajeLog) throws DBException{
		return obtenerDataOperacionRepository.obtenerDataParametros(parametroRequest, idTx, propertiesExternos, mensajeLog);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PvuConsultaCVEPendResponse obtenerCVEPend(String mensajeLog, PropertiesExternos propertiesExternos, String idTx,PvuConsultaCVEPendRequest dataRequest) throws DBException{
		return obtenerDataOperacionRepository.obtenerCVEPend(mensajeLog, propertiesExternos, idTx, dataRequest);
	}
	 	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ClienteEvalResponse getDataValidaClienteReglasRenovacion(PropertiesExternos propertiesExternos, ClienteEvalRequest dataRequest, String mensajeLog) throws DBException{		
		return obtenerDataOperacionRepository.getDataValidaClienteReglasRenovacion(propertiesExternos, dataRequest, mensajeLog);
	}
}
