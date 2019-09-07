package pe.com.claro.venta.evaluarreglas.domain.dao;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import pe.com.claro.common.exception.DBException;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.evaluarreglas.domain.repository.ObtenerDataOperacionSGARepository;
import pe.com.claro.venta.evaluarreglas.model.SgaGetInformacionClienteBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.SgaGetInformacionClienteBeanResponse;

@Stateless
public class ObtenerDataOperacionSGADao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
    private ObtenerDataOperacionSGARepository obtenerDataOperacionSGARepository;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public SgaGetInformacionClienteBeanResponse obtenerInfoClienteSGA(PropertiesExternos propertiesExternos, SgaGetInformacionClienteBeanRequest request, String mensajeLog)throws DBException{
		return obtenerDataOperacionSGARepository.obtenerInfoClienteSGA(propertiesExternos, request, mensajeLog);
	}	
}
