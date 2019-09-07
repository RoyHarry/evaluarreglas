package pe.com.claro.venta.evaluarreglas.resource.util;


import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.bean.HeaderRequest;


public class ValidSolicitudHttp {

	public boolean isValidaDataHeaderBean(HeaderRequest headerBean) {
		
		if (headerBean.getIdTransaccion() == null)
			return false;
		
		if (headerBean.getAccept() == null)
			return false;


		return headerBean.getAccept().equals(Constantes.TYPEREQUEST);
	}

}
