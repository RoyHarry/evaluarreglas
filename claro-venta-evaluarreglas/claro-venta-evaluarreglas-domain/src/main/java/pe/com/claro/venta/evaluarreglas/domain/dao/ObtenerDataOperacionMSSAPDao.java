package pe.com.claro.venta.evaluarreglas.domain.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import pe.com.claro.common.exception.DBException;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.evaluarreglas.domain.repository.ObtenerDataOperacionMSSAPRepository;
import pe.com.claro.venta.evaluarreglas.model.RequestPrecioBase;
import pe.com.claro.venta.evaluarreglas.model.ResponsePrecioBase;

@Stateless
public class ObtenerDataOperacionMSSAPDao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
    private ObtenerDataOperacionMSSAPRepository obtenerDataOperacionMSSAPRepository;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public List<ResponsePrecioBase> obtenerPrecioBaseEquipo(PropertiesExternos propertiesExternos, RequestPrecioBase request, String mensajeLog)throws DBException{
		return obtenerDataOperacionMSSAPRepository.obtenerPrecioBaseEquipo(propertiesExternos, request, mensajeLog);
	}
}
