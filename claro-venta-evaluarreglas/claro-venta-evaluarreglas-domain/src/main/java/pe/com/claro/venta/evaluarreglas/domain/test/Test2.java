package pe.com.claro.venta.evaluarreglas.domain.test;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes.EvaluarDatosClienteRequestWS;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes.ClaroEvalClienteAuxiliarBeanWS;

public class Test2 {
	public static void main(String[] args) {
		
		EvaluarDatosClienteRequestWS request = new EvaluarDatosClienteRequestWS();
		request.setPlazoDeAcuerdo("");
		ClaroEvalClienteAuxiliarBeanWS objClaroEvalBeanAux = new ClaroEvalClienteAuxiliarBeanWS();		
		objClaroEvalBeanAux.setPlazoDeAcuerdoOf(request.getPlazoDeAcuerdo()!= null && request.getPlazoDeAcuerdo().equals(Constantes.VACIO) ? Constantes.SINPLAZODEACUERDO : request.getPlazoDeAcuerdo());
		if(objClaroEvalBeanAux.getPlazoDeAcuerdoOf() == null) {
			objClaroEvalBeanAux.setPlazoDeAcuerdoOf(Constantes.VACIO);
		}
		request.setControlDeConsumo("");		
		objClaroEvalBeanAux.setControlDeConsumo(request.getControlDeConsumo() != Constantes.NULLOBJECTO && !request.getControlDeConsumo().equals(Constantes.VACIO)  ? request.getControlDeConsumo().toUpperCase() : Constantes.VACIO);
		
		String modalidadCedente = "";
		request.setTransaccion(Constantes.RENOVACION);
		if(request.getTransaccion().equals(Constantes.RENOVACION)) {
			modalidadCedente = null;
		}
		objClaroEvalBeanAux.setModalidadCedente(modalidadCedente);
//		if(request.getPlazoDeAcuerdo() != Constantes.nullObjeto) {
//			objClaroEvalBeanAux.setPlazoDeAcuerdoOf(request.getPlazoDeAcuerdo());
//		}else if(Constantes.VACIO.equals(request.getPlazoDeAcuerdo())){
//			objClaroEvalBeanAux.setPlazoDeAcuerdoOf(Constantes.SINPLAZODEACUERDO);
//		}else if(Constantes.nullObjeto == request.getPlazoDeAcuerdo()) {
//			objClaroEvalBeanAux.setPlazoDeAcuerdoOf(Constantes.VACIO);
//		}
		
		System.out.println("objClaroEvalBeanAux.getPlazoDeAcuerdoOf(): "+objClaroEvalBeanAux.getPlazoDeAcuerdoOf());
		System.out.println("objClaroEvalBeanAux.getControlDeConsumo: "+objClaroEvalBeanAux.getControlDeConsumo());				
	}
	
}
