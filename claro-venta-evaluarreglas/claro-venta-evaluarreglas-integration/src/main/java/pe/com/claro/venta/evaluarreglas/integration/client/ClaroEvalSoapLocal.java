package pe.com.claro.venta.evaluarreglas.integration.client;

import javax.ejb.Local;

import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.ClaroEvalClientesReglasRequest;
import com.ibm.rules.decisionservice.claroevalclienteapp.claroevalclientesreglas.ClaroEvalClientesReglasResponse;

import pe.com.claro.common.resource.exception.WSClientException;
import pe.com.claro.common.resource.exception.WSException;
import pe.com.claro.common.util.PropertiesExternos;

@Local
public interface ClaroEvalSoapLocal {
	public ClaroEvalClientesReglasResponse claroEvalClientesReglasV2(PropertiesExternos propertiesExternos, ClaroEvalClientesReglasRequest request, String mensajeLog) throws WSException, WSClientException;
}
