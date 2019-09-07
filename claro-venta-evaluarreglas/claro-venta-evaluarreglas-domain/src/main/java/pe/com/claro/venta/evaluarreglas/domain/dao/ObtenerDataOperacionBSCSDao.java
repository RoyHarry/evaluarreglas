package pe.com.claro.venta.evaluarreglas.domain.dao;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import pe.com.claro.common.exception.DBException;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.evaluarreglas.domain.repository.ObtenerDataOperacionBSCSRepository;
import pe.com.claro.venta.evaluarreglas.model.BscsDetalleCFXContrato;
import pe.com.claro.venta.evaluarreglas.model.BscsDetalleXLineaBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.BscsTim127ComPagoBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.BscsTim127ComPagoBeanResponse;

@Stateless
public class ObtenerDataOperacionBSCSDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
    private ObtenerDataOperacionBSCSRepository obtenerDataOperacionBSCSRepository;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public BscsDetalleXLineaBeanResponse timComPagoBSCS(PropertiesExternos propertiesExternos, BscsTim127ComPagoBeanRequest request, String mensajeLog)throws DBException{
		return obtenerDataOperacionBSCSRepository.timComPagoBSCS(propertiesExternos, request, mensajeLog);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public BscsTim127ComPagoBeanResponse tim127ComPagoBSCS(PropertiesExternos propertiesExternos, BscsTim127ComPagoBeanRequest request, String mensajeLog)throws DBException{
		return obtenerDataOperacionBSCSRepository.tim127ComPagoBSCS(propertiesExternos, request, mensajeLog);
	}	
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BscsDetalleCFXContrato obtenerCargoFijoBscs(PropertiesExternos propertiesExternos, String coId, String mensajeLog)throws DBException{
		return obtenerDataOperacionBSCSRepository.obtenerCargoFijoBscs(propertiesExternos, coId, mensajeLog);
	}	
}
