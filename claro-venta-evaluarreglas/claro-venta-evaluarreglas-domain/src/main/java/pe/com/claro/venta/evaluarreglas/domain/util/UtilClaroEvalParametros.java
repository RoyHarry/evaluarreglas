package pe.com.claro.venta.evaluarreglas.domain.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.exception.DBException;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.util.JAXBUtilitarios;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes.EvaluarDatosClienteRequestWS;
import pe.com.claro.venta.evaluarreglas.domain.bean.CalcLimiteCreditoDisponibleBean;
import pe.com.claro.venta.evaluarreglas.domain.dao.ObtenerDataOperacionDao;
import pe.com.claro.venta.evaluarreglas.model.BEBilletera;
import pe.com.claro.venta.evaluarreglas.model.BEPlanBilletera;
import pe.com.claro.venta.evaluarreglas.model.BEPlanBilletera.TIPO_FACTURADOR;
import pe.com.claro.venta.evaluarreglas.model.BEPlanBilletera.TIPO_PLAN;
import pe.com.claro.venta.evaluarreglas.model.BscsDXLpCurDetalleBean;
import pe.com.claro.venta.evaluarreglas.model.BscsDetalleXLineaBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.CalcFacturacionPromedioBean;
import pe.com.claro.venta.evaluarreglas.model.Pvu3P6DetalleLineaCurDETBean;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ConDetalleLineaBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ConDetalleLineaBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuCalculoLCxProductoBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuCalculoLCxProductoBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuCalculoLCxProductoCurBean;
import pe.com.claro.venta.evaluarreglas.model.PvuFacturaXProductoBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuFacturaXProductoBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuFacturaXProductoCurBean;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanBilleteraBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanBilleteraBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanBilleteraCurBean;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanXProductoBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanXProductoBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanXProductoCurBean;
import pe.com.claro.venta.evaluarreglas.model.SgaDetalleInfCurBean;
import pe.com.claro.venta.evaluarreglas.model.SgaGetInformacionClienteBeanResponse;

@Stateless
public class UtilClaroEvalParametros {
	
	private static final Logger LOG = LoggerFactory.getLogger(UtilClaroEvalParametros.class);
	
	@EJB
	private ObtenerDataOperacionDao obtenerDataOperacionDao;
	
	private Double montoFacturadoTotal = Double.valueOf(Constantes.CERO);
	private Double montoNoFacturadoTotal = Double.valueOf(Constantes.CERO);
	
	String listaTelefono = Constantes.CONSTANTE_VACIA;
	ArrayList<BEPlanBilletera> objListaMontoFactura = null;

	ArrayList<BEBilletera> oMontoFacturadoxBilletera = new ArrayList<>();
	ArrayList<BEBilletera> oMontoNoFacturadoxBilletera = new ArrayList<>();

	ArrayList<BEBilletera> objLCDisponiblexProducto = new ArrayList<>();
	ArrayList<BEBilletera> arrBilleteraPlan = new ArrayList<>();
	
	Pvu3Play6ConDetalleLineaBeanResponse obj3PlayDetalleLineaPVU = new Pvu3Play6ConDetalleLineaBeanResponse();
	
	String flagLogBD = Constantes.FALSE;
	
	public CalcFacturacionPromedioBean calcFacturacionPromedio(PropertiesExternos propertiesExternos, String mensajeLog, EvaluarDatosClienteRequestWS request,
			BscsDetalleXLineaBeanResponse respDetalleXLineaBSCS,
			SgaGetInformacionClienteBeanResponse respClienteServiciosSGA) throws DBException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
		
		
		montoFacturadoTotal = new Double(Constantes.CERO);
		montoNoFacturadoTotal = new Double(Constantes.CERO);
		
		listaTelefono = Constantes.CONSTANTE_VACIA;
		objListaMontoFactura = null;

		oMontoFacturadoxBilletera = new ArrayList<>();
		oMontoNoFacturadoxBilletera = new ArrayList<>();

		objLCDisponiblexProducto = new ArrayList<>();
		arrBilleteraPlan = new ArrayList<>();
		
		obj3PlayDetalleLineaPVU = new Pvu3Play6ConDetalleLineaBeanResponse();

		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		LOG.info(mensajeLog  + "INICIO Calculo de facturacion Promedio");

		CalcFacturacionPromedioBean respCalcFacturacionPromedio = new CalcFacturacionPromedioBean();
				
		ArrayList<Double> listaFacturacionPromedio = new ArrayList<Double>();
		LOG.info(mensajeLog + "[INICIO 1.2.7.1 Procesar Detalle BSCS]");
		ProcesarDetalleBSCS(propertiesExternos, mensajeLog, respDetalleXLineaBSCS);
		LOG.info(mensajeLog + "[FIN 1.2.7.1 Procesar Detalle BSCS]");
		if(respClienteServiciosSGA.getP_detalles_inf()!= null) {
			LOG.info(mensajeLog + "[INICIO 1.2.7.2 Procesar Detalle SGA]");
			ProcesarDetalleSGA(propertiesExternos, mensajeLog, respClienteServiciosSGA);
			LOG.info(mensajeLog + "[FIN 1.2.7.2 Procesar Detalle SGA]");
		}
		LOG.info(mensajeLog + "[INICIO 1.2.7.3 Procesar Detalle SISACT]");		
		ProcesarDetalleSISACT(propertiesExternos ,mensajeLog, String.valueOf(request.getNumeroDocumento()));
		LOG.info(mensajeLog + "[FIN 1.2.7.3 Procesar Detalle SISACT]");
		
		LOG.info(mensajeLog + "[INICIO 1.2.7.4 Obtener Billetera por Plan]");		
		List<BEPlanBilletera> objProductoPlanesEval = ObtenerBilleteraxPlan(propertiesExternos, mensajeLog, request.getPlanesxProductoPVU());
		LOG.info(mensajeLog + "ObtenerBilleteraxPlan ==========>>>>: "+ JAXBUtilitarios.anyObjectToXmlText(objProductoPlanesEval));		
		LOG.info(mensajeLog + "[FIN 1.2.7.4 Obtener Billetera por Plan]");
		
		arrBilleteraPlan = new ArrayList<>();		
		for (BEPlanBilletera arrProductoPlanesEval : objProductoPlanesEval) {
			for (BEBilletera arrBilletera : arrProductoPlanesEval.getoBilletera()) {
				if (arrProductoPlanesEval.getplan().equalsIgnoreCase(request.getIdPlan()) ) {
					arrBilleteraPlan.add(arrBilletera);
				}
			}
		}
		
		LOG.info(mensajeLog + "arrBilleteraPlan ==========>>>>: "+ JAXBUtilitarios.anyObjectToXmlText(arrBilleteraPlan));
		LOG.info(mensajeLog + "[INICIO 1.2.7.5 Obtener Monto Facturado Por Billetera]");		
		ObtenerMontoFactxBilletera(propertiesExternos, mensajeLog, objListaMontoFactura);		
		LOG.info(mensajeLog + "[FIN 1.2.7.5 Obtener Monto Facturado Por Billetera]");		
		
		LOG.info(mensajeLog + "[INICIO 1.2.7.6 Calcular Monto por producto]");
		double dblMontoFacturado = CalcularMontoxProducto(mensajeLog, oMontoFacturadoxBilletera, arrBilleteraPlan);
		double dblMontoNoFacturado = CalcularMontoxProducto(mensajeLog, oMontoNoFacturadoxBilletera, arrBilleteraPlan);
		LOG.info(mensajeLog + "[FIN 1.2.7.6 Calcular Monto por producto]");
		
		double facturacionPromedioProducto = dblMontoFacturado + dblMontoNoFacturado;
		double facturacionPromedioClaro = montoFacturadoTotal + montoNoFacturadoTotal;

		LOG.info(mensajeLog + "Formula de Calculo de Facturacion Promedio Producto: [Monto Facturado + Monto No Facturado]");
		LOG.info(mensajeLog + "Monto Facturado: [" + dblMontoFacturado + "]");
		LOG.info(mensajeLog + "Monto No Facturado: [" + dblMontoNoFacturado + "]");
		LOG.info(mensajeLog + "Facturacion Promedio Producto Calculado: [" + facturacionPromedioProducto + "]");
		LOG.info(mensajeLog + "Formula de Calculo de Facturacion Promedio Claro: [Monto Facturado Total + Monto No Facturado Total]");
		LOG.info(mensajeLog + "Monto Facturado Total: [" + montoFacturadoTotal + "]");
		LOG.info(mensajeLog + "Monto No Facturado Total: [" + montoNoFacturadoTotal + "]");
		LOG.info(mensajeLog + "Facturacion Promedio Claro Calculado: [" + facturacionPromedioClaro + "]");
		
		facturacionPromedioClaro = Utilitarios.round(facturacionPromedioClaro, Constantes.DOS);		
		listaFacturacionPromedio.add(facturacionPromedioClaro);
		listaFacturacionPromedio.add(facturacionPromedioProducto);

		LOG.info(mensajeLog  + "FIN Calculo de facturacion Promedio");
		
		respCalcFacturacionPromedio.setListaCalcFacturacionPromedio(listaFacturacionPromedio);
		respCalcFacturacionPromedio.setObj3PlayDetalleLineaPVU(obj3PlayDetalleLineaPVU);
		respCalcFacturacionPromedio.setMontoFacturadoTotal(montoFacturadoTotal);		
		return respCalcFacturacionPromedio;
	}
	
	
	public CalcLimiteCreditoDisponibleBean calcularLimiteCreditoDisponible(PropertiesExternos propertiesExternos, String mensajeLog, EvaluarDatosClienteRequestWS request, Integer cantLineasActivasTotal)
			throws DBException, NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, ClassNotFoundException {

		objLCDisponiblexProducto = new ArrayList<>();
		
		obj3PlayDetalleLineaPVU = new Pvu3Play6ConDetalleLineaBeanResponse();
		
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		LOG.info(mensajeLog + "INICIO del Calculo de Limite de Credito Disponible");
		
		CalcLimiteCreditoDisponibleBean beanLimitCredito = new CalcLimiteCreditoDisponibleBean();

		String clienteNuevo = Constantes.CONSTANTE_VACIA;
		
		LOG.info(mensajeLog + "Validacion si el cliente existe en PVU");
		
		if(cantLineasActivasTotal > Constantes.CERO){
			LOG.info(mensajeLog + "El cliente existe. Estableciendo el valor de 'N' en Cliente Nuevo temporalmente para calculos posteriores de limite de Credito");
			clienteNuevo = Constantes.N;
		}else{
			LOG.info(mensajeLog + "El cliente no existe. Estableciendo el valor de 'S' en Cliente Nuevo temporalmente para calculos posteriores de limite de Credito");
			clienteNuevo = Constantes.S;
		}

		String strEsSaludSunat = request.getEssaludSunatPVU();
		String strClienteNuevo = clienteNuevo;
		String strCodRiesgo = request.getRiesgoPVU();
		Float dblLC = request.getLcDcPVU();
		
		strEsSaludSunat = strEsSaludSunat.equals(Constantes.SCERO) ? propertiesExternos.esSaludSunatNegativo : propertiesExternos.esSaludSunatPositivo;
		
		CalcularLCDisponible(propertiesExternos, mensajeLog, strCodRiesgo, strEsSaludSunat, strClienteNuevo, dblLC, request);
		

		double dblLCDisponible = CalcularMontoxProducto(mensajeLog, objLCDisponiblexProducto, arrBilleteraPlan); 
		
		LOG.info(mensajeLog + "Tamanio de objLCDisponiblexProducto: [" + objLCDisponiblexProducto.size()+"]");
		LOG.info(mensajeLog + "Tamanio de oMontoFacturadoxBilletera: [" + oMontoFacturadoxBilletera.size()+"]");
		LOG.info(mensajeLog + "Tamanio de oMontoNoFacturadoxBilletera: [" + oMontoNoFacturadoxBilletera.size()+"]");

		LOG.info(mensajeLog + " INICIO CONTENIDO objLCDisponiblexProducto ");
		
		for (BEBilletera itemLCDisp : objLCDisponiblexProducto) {
			LOG.info(mensajeLog + "\n" + JAXBUtilitarios.anyObjectToXmlText(itemLCDisp));
		}
		LOG.info(mensajeLog + " FIN CONTENIDO objLCDisponiblexProducto ");
		
		LOG.info(mensajeLog + " INICIO CONTENIDO oMontoFacturadoxBilletera ");
		for( BEBilletera itemMontoFact : oMontoFacturadoxBilletera){
			LOG.info(mensajeLog + "\n" + JAXBUtilitarios.anyObjectToXmlText(itemMontoFact));
		}
		
		LOG.info(mensajeLog + " INICIO CONTENIDO oMontoNoFacturadoxBilletera ");
		for( BEBilletera itemMontoNOFact : oMontoNoFacturadoxBilletera){
			LOG.info(mensajeLog + "\n" + JAXBUtilitarios.anyObjectToXmlText(itemMontoNOFact));
		}
		LOG.info(mensajeLog + " FIN CONTENIDO oMontoNoFacturadoxBilletera ");

		String strMontosxBilletera = Constantes.CONSTANTE_VACIA;
		
		for (BEBilletera itemLCDisp : objLCDisponiblexProducto) {
			
			double montoFacturado = Constantes.CERODOUBLE;
			double montoNOFacturado = Constantes.CERODOUBLE;
			
			if(oMontoFacturadoxBilletera != null){
				for( BEBilletera itemMontoFact : oMontoFacturadoxBilletera){
					if(itemLCDisp.getbilletera().equalsIgnoreCase(itemMontoFact.getbilletera())){
						montoFacturado = itemMontoFact.getmonto();
						LOG.info(mensajeLog + "monto Facturado Capturado: [" + montoFacturado + "]");
					}
				}
				
				for( BEBilletera itemMontoNOFact : oMontoNoFacturadoxBilletera){
					if(itemLCDisp.getbilletera().equalsIgnoreCase(itemMontoNOFact.getbilletera())){
						
						montoNOFacturado = itemMontoNOFact.getmonto();
						LOG.info(mensajeLog + "monto NO Facturado Capturado: [" + montoNOFacturado + "]");
					}
				}
				
				LOG.info(mensajeLog + "Montos Facturado/NoFacturado: [" + montoFacturado + "/" + montoNOFacturado + "]");
				
			}else{
				
				LOG.info(mensajeLog + "La lista oMontoFacturadoxBilletera se encuentra Nula");
			}

			strMontosxBilletera += Constantes.PALOTE;
			strMontosxBilletera += itemLCDisp.getidBilletera();
			strMontosxBilletera += Constantes.PUNTOCOMA;
			strMontosxBilletera += itemLCDisp.getmonto();
			strMontosxBilletera += Constantes.PUNTOCOMA;
			strMontosxBilletera += montoFacturado;
			strMontosxBilletera += Constantes.PUNTOCOMA;
			strMontosxBilletera += montoNOFacturado;
		}

		LOG.info(mensajeLog + "Limite de Credito Disponible No final: [" + dblLCDisponible + "]");
		if(dblLCDisponible < Constantes.CERODOUBLE){
			dblLCDisponible = Constantes.CERODOUBLE;
		}
		LOG.info(mensajeLog + "Limite de Credito Disponible FINAL: [" + dblLCDisponible + "]");
		LOG.info(mensajeLog + "Cadena Montos X Billetera: ["+ strMontosxBilletera +"]");
		LOG.info(mensajeLog + "FIN del Calculo de Limite de Credito Disponible.");
		
		beanLimitCredito.setLcDisponible(dblLCDisponible);
		beanLimitCredito.setStrMontosxBilletera(strMontosxBilletera);
		
		return beanLimitCredito;
	}
	
	
	public void  CalcularLCDisponible(PropertiesExternos propertiesExternos, String mensajeLog, String strCodRiesgo, String strEsSaludSunat, String strClienteNuevo, Float dblLC, EvaluarDatosClienteRequestWS request) throws DBException{

		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		
		LOG.info(mensajeLog + "INICIO CalcularLCDisponible ");
		ArrayList<BEBilletera> objLCxProducto = new ArrayList<>();
		 
		PvuCalculoLCxProductoBeanResponse responseCalcLCxProd = new PvuCalculoLCxProductoBeanResponse();
		PvuCalculoLCxProductoBeanRequest requestCalcLCxProd = new PvuCalculoLCxProductoBeanRequest();

		requestCalcLCxProd.setP_cliente_nuevo(strClienteNuevo);
		requestCalcLCxProd.setP_essalud_sunat(strEsSaludSunat);
		requestCalcLCxProd.setP_lc_dc(dblLC);
		requestCalcLCxProd.setP_riesgo(strCodRiesgo);
		requestCalcLCxProd.setP_tipo_doc(Constantes.CODTIPODOCUMENTODNIPVU);
		
		responseCalcLCxProd = obtenerDataOperacionDao.calculoLCxProductoPVU(propertiesExternos, mensajeLog, requestCalcLCxProd, flagLogBD);
		
		objLCxProducto = (ObtenerLCxBilletera(mensajeLog, responseCalcLCxProd)); 
		
		ArrayList<BEBilletera> oMontoFacturadoxProducto = oMontoFacturadoxBilletera;    
        ArrayList<BEBilletera> oMontoNoFacturadoxProducto = oMontoNoFacturadoxBilletera;

        
        objLCDisponiblexProducto = new ArrayList<BEBilletera>();
        
        double dblLCxBilletera;
        LOG.info(mensajeLog + "Tamanio de objLCxProducto: [" + objLCxProducto.size() + "]");

        for (BEBilletera objBilletera : objLCxProducto){
        	
        	dblLCxBilletera = Constantes.CERODOUBLE;
            if (oMontoFacturadoxProducto != null){
                 for (BEBilletera objMonto : oMontoFacturadoxProducto){
                       if (objBilletera.getidBilletera() == objMonto.getidBilletera()){
                             dblLCxBilletera = objMonto.getmonto();
                             break;
                       }
                 }
            }
            
            if (oMontoNoFacturadoxProducto != null){
                 for (BEBilletera objMonto : oMontoNoFacturadoxProducto){
                        if (objBilletera.getidBilletera() == objMonto.getidBilletera()){
                             dblLCxBilletera += objMonto.getmonto();
                             break;
                       }
                 }
            }
            if (objBilletera.getmonto() >= dblLCxBilletera){
                 objBilletera.setmonto(objBilletera.getmonto() - dblLCxBilletera);
            }
            else{
                 objBilletera.setmonto(Constantes.CERODOUBLE);
            }
            
            objBilletera.setmonto(objBilletera.getmonto());
            objLCDisponiblexProducto.add(objBilletera);
        }
	}
	
	public ArrayList<BEBilletera> ObtenerLCxBilletera(String mensajeLog, PvuCalculoLCxProductoBeanResponse responseCalcLCxProd) {

		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		LOG.info(mensajeLog + "INICIO ObtenerLCxBilletera");
		ArrayList<BEBilletera> objLista = new ArrayList<BEBilletera>();

		BEBilletera objItem;

		
        LOG.info(mensajeLog + "Tamanio de C_producto_lc: [" + responseCalcLCxProd.getC_producto_lc().size() + "]");
		
		for (PvuCalculoLCxProductoCurBean dr : responseCalcLCxProd.getC_producto_lc()) {
			objItem = new BEBilletera();
			objItem.setidBilletera(dr.getProducto_cod().intValue());
			objItem.setbilletera(dr.getDescripcion());
			objItem.setmonto(dr.getProducto_lc().doubleValue());
			objLista.add(objItem);
		}
		
		  LOG.info(mensajeLog + "Tamanio de objLista: [" + objLista.size() + "]");

		LOG.info(mensajeLog + "FIN ObtenerLCxBilletera");
		
		return objLista;
	}
	
	
	@SuppressWarnings("unused")
	private double CalcularMontoxProducto(String mensajeLog, ArrayList<BEBilletera> objLista, ArrayList<BEBilletera> objBilleteraActual) {
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		LOG.info(mensajeLog + "INICIO Calcular Monto x Producto");
		double dblMonto = Constantes.CERODOUBLE;
		if (objLista != null) {
			if(objBilleteraActual!= null) {
			LOG.info(mensajeLog+ " Cantidad de items en objLista: [" + objLista.size() + "]");			
			LOG.info(mensajeLog+ " El objLista Si tiene registros. Procediendo a recorrer objLista x Billetera");
			LOG.info(mensajeLog+ " Cantidad de items en objBilleteraActual: [" + objBilleteraActual.size() + "]");			
				for (BEBilletera obj : objLista) {
					for (BEBilletera obj1 : objBilleteraActual) {
						LOG.info(mensajeLog + "obj.getidBilletera(): "+obj.getidBilletera());
						LOG.info(mensajeLog + "obj1.getidBilletera(): "+obj1.getidBilletera());
						if (obj.getidBilletera() == obj1.getidBilletera()) {
							LOG.info(mensajeLog + "obj.getmonto(): "+obj.getmonto());
							LOG.info(mensajeLog + "obj1.getmonto(): "+obj1.getmonto());
							dblMonto += obj.getmonto();
							break;
						}
					}
				}
			}
		}else{
			LOG.info(mensajeLog + "El objeto objLista Se encuentra VACIA");
		}
		LOG.info(mensajeLog + "dblMonto CalcularMontoxProducto"+dblMonto);		
		LOG.info(mensajeLog + "FIN Calcular Monto x Producto");
		
		return dblMonto;
	}
	
	public void ProcesarDetalleBSCS(PropertiesExternos propertiesExternos,String mensajeLog, BscsDetalleXLineaBeanResponse respDetalleXLineaBSCS) {

		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		LOG.info(mensajeLog + "INICIO de ProcesarDetalleBSCS" );
		
		for (BscsDXLpCurDetalleBean dr : respDetalleXLineaBSCS.getP_CUR_DETALLE()) {	
			listaTelefono = Constantes.FORMATOLISTA2TELEFONO.replace("{0}", listaTelefono).replace("{1}", dr.getNUMERO());
		}

		if (objListaMontoFactura == null) {
			objListaMontoFactura = new ArrayList<>();
		}

		ArrayList<String> objListaCuenta = new ArrayList<>();
		BEPlanBilletera objBilletera;
		String strCuenta;

		for (BscsDXLpCurDetalleBean dr : respDetalleXLineaBSCS.getP_CUR_DETALLE()) {
			strCuenta = dr.getCUENTA();

			if (!objListaCuenta.contains(strCuenta)) {
				if (dr.getESTADO().equalsIgnoreCase(Constantes.ACTIVO)) {
					objBilletera = new BEPlanBilletera();
					objBilletera.setcuenta(strCuenta);
					objBilletera.setmontoFacturado(dr.getPROM_FACT());
					objBilletera.setmontoNoFacturado(dr.getMONTO_NO_FACT());

					int intCodBilletera = Constantes.CERO;
					if (dr.getNRO_MOVIL() > Constantes.CERO) {
						intCodBilletera += Constantes.MOVIL;
					}
					if (dr.getNRO_INTERNET_FIJO() > Constantes.CERO) {
						intCodBilletera += Constantes.INTERNET;
					}
					if (dr.getNRO_CLARO_TV() > Constantes.CERO) {
						intCodBilletera += Constantes.CLAROTV;
					}
					if (dr.getNRO_TELEF_FIJA() > Constantes.CERO) {
						intCodBilletera += Constantes.TELEFONIA;
					}
					if (dr.getNRO_BAM() > Constantes.CERO) {
						intCodBilletera += Constantes.BAM;
					}

					objBilletera.setidBilletera(intCodBilletera);
					objListaMontoFactura.add(objBilletera);
					objListaCuenta.add(strCuenta);
				}
			}
		}
		LOG.info(mensajeLog + "FIN de ProcesarDetalleBSCS. Tamanio de objListaMontoFactura: [" + objListaMontoFactura.size() + "]");
	}
	
	public void ProcesarDetalleSGA(PropertiesExternos propertiesExternos,String mensajeLog, SgaGetInformacionClienteBeanResponse respClienteServiciosSGA)
			throws DBException, NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, ClassNotFoundException {

		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		LOG.info(mensajeLog + "INICIO de ProcesarDetalleSGA" );
		
		BEPlanBilletera objPlan;
		int intSistemaSGA = Constantes.UNO;

		PvuPlanBilleteraBeanResponse objPLanBilletera = new PvuPlanBilleteraBeanResponse();
		objPLanBilletera = procesarPlanBilleteraPVU(propertiesExternos, mensajeLog, intSistemaSGA);
		
		ArrayList<BEPlanBilletera> objLista = new ArrayList<>();
		ArrayList<String> objListaPlan = new ArrayList<>();
		BEPlanBilletera objItem;

		BEBilletera objBilletera;

		String plan;

		for (PvuPlanBilleteraCurBean dr : objPLanBilletera.getP_consulta()) {

			plan = dr.getSolv_codigo();
			objBilletera = new BEBilletera(dr.getPrclv_codigo(), Constantes.UNO);

			if (!objListaPlan.contains(plan)) {
				objItem = new BEPlanBilletera();
				objItem.setplan(plan);
				objItem.setoBilletera(new ArrayList<BEBilletera>());
				objItem.getoBilletera().add(objBilletera);
				objLista.add(objItem);
				objListaPlan.add(plan);
			} else {
				for (BEPlanBilletera objPlan2 : objLista) {
					if (plan.equalsIgnoreCase(objPlan2.getplan())) {
						objPlan2.getoBilletera().add(objBilletera);
						break;
					}
				}
			}
		}

		if (objListaMontoFactura == null) {
			objListaMontoFactura = new ArrayList<>();
		}

		ArrayList<BEPlanBilletera> objListaPlan2 = new ArrayList<>();

		for (SgaDetalleInfCurBean dr : respClienteServiciosSGA.getP_detalles_inf()) {

			if (dr.getEstado().equalsIgnoreCase("ACTIVO")) {

				objPlan = new BEPlanBilletera();
				objPlan.setplan(dr.getIdpaq());
				objPlan.setmontoFacturado(dr.getProm_fac());
				objPlan.setmontoNoFacturado(dr.getMonto_no_fac());

				objPlan.settipoFacturador(TIPO_FACTURADOR.SGA);
				objPlan.setnroPlanes(Constantes.UNO);

				ArrayList<BEBilletera> objListaBilletera = null;

				for (BEPlanBilletera obj : objListaPlan2) {
					if (obj.getplan().equalsIgnoreCase(objPlan.getplan())) {
						objListaBilletera = obj.getoBilletera();
						break;
					}
				}

				if (objListaBilletera != null) {

					objPlan.setoBilletera(new ArrayList<BEBilletera>());

					int idBilleteraAux = Constantes.CERO;
					for (BEBilletera obj : objListaBilletera) {

						idBilleteraAux += obj.getidBilletera();
						objPlan.setidBilletera(idBilleteraAux);
						objPlan.getoBilletera().add(new BEBilletera(obj.getidBilletera(), Constantes.UNO));
					}
				} else {
					objPlan.setidBilletera(Constantes.TRIPLEPLAY);

					objPlan.setoBilletera(new ArrayList<BEBilletera>());
					objPlan.getoBilletera().add(new BEBilletera(Constantes.INTERNET, Constantes.UNO));
					objPlan.getoBilletera().add(new BEBilletera(Constantes.CLAROTV, Constantes.UNO));
					objPlan.getoBilletera().add(new BEBilletera(Constantes.TELEFONIA, Constantes.UNO));
				}

				objListaMontoFactura.add(objPlan);
			}

		}
		LOG.info(mensajeLog + "FIN de ProcesarDetalleSGA Tamanio de objListaMontoFactura: [" + objListaMontoFactura.size() + "]" );
	}
	
	public void ProcesarDetalleSISACT(PropertiesExternos propertiesExternos, String mensajeLog, String numeroDocumento)
			throws DBException{
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		LOG.info(mensajeLog + "INICIO de ProcesarDetalleSISACT" );
		
		
		obj3PlayDetalleLineaPVU = procesar3playConDetalleLineaPVU(propertiesExternos, mensajeLog, numeroDocumento, listaTelefono);

		BEPlanBilletera objPlan;
		int intSistemaSISACT = Constantes.DOS;

		PvuPlanBilleteraBeanResponse objPlanBilleteraPVU = new PvuPlanBilleteraBeanResponse();
		objPlanBilleteraPVU = procesarPlanBilleteraPVU(propertiesExternos, mensajeLog, intSistemaSISACT);
		
		if (objListaMontoFactura == null) {
			objListaMontoFactura = new ArrayList<>();
		}

		LOG.info(mensajeLog + "Convirtiendo Cursor a Arreglo");

		BEPlanBilletera objItem;

		BEBilletera objBilletera;
		ArrayList<String> objListaPlanString = new ArrayList<>();

		String plan;
		ArrayList<BEPlanBilletera> objListaPlan = new ArrayList<>();
		for (PvuPlanBilleteraCurBean dr : objPlanBilleteraPVU.getP_consulta()) {

			plan = dr.getSolv_codigo();
			objBilletera = new BEBilletera(dr.getPrclv_codigo(), Constantes.UNO);

			if (!objListaPlan.contains(plan)) {
				objItem = new BEPlanBilletera();
				objItem.setplan(plan);
				objItem.setoBilletera(new ArrayList<BEBilletera>());
				objItem.getoBilletera().add(objBilletera);
				objListaPlan.add(objItem);
				objListaPlanString.add(plan);
			} else {
				for (BEPlanBilletera objPlan2 : objListaPlan) {
					LOG.info(mensajeLog + "plan "+plan);
					LOG.info(mensajeLog + "objPlan2.getplan() "+objPlan2.getplan());					
					if (plan.equalsIgnoreCase(objPlan2.getplan())) {	
						objPlan2.getoBilletera().add(objBilletera);
						break;
					}
				}
			}
		}

		for (Pvu3P6DetalleLineaCurDETBean dr : obj3PlayDetalleLineaPVU.getP_cursor_det()) {

			objPlan = new BEPlanBilletera();

			objPlan.setnroSEC(dr.getCuenta());
			objPlan.setplan(dr.getPlan_bscs());
			objPlan.setmontoFacturado(Constantes.CERO);
			objPlan.setmontoNoFacturado(Double.parseDouble(dr.getCargo_fijo()));
			objPlan.settipoPlan(TIPO_PLAN.MOVIL);

			objPlan.settipoFacturador(TIPO_FACTURADOR.BSCS);
			objPlan.setnroPlanes(Constantes.UNO);

			ArrayList<BEBilletera> objListaBilletera = null;

			for (BEPlanBilletera obj : objListaPlan) {
				if (obj.getplan().equalsIgnoreCase(objPlan.getplan())) {
					objListaBilletera = obj.getoBilletera();
					break;
				}
			}

			if (objListaBilletera != null) {

				objPlan.setoBilletera(new ArrayList<BEBilletera>());

				int idBilleteraAux = Constantes.CERO;
				for (BEBilletera obj : objListaBilletera) {
					idBilleteraAux += obj.getidBilletera();
					objPlan.setidBilletera(idBilleteraAux);
					objPlan.getoBilletera().add(new BEBilletera(obj.getidBilletera(), 1));

					if (obj.getidBilletera() == Constantes.BAM) {
						objPlan.settipoPlan(TIPO_PLAN.DATOS);

					}
				}
			} else {
				objPlan.setidBilletera(Constantes.DOS);
				objPlan.setoBilletera(new ArrayList<BEBilletera>());
				objPlan.getoBilletera().add(new BEBilletera(Constantes.DOS, Constantes.UNO));
			}
			LOG.info(mensajeLog + "Valor de objPlan.getmontoFacturado() : [" + objPlan.getmontoFacturado() + "]");
			LOG.info(mensajeLog + "Valor de objPlan.getmontoNOFacturado(): [" + objPlan.getmontoNoFacturado() + "]");			
			objListaMontoFactura.add(objPlan);

		}
		LOG.info(mensajeLog + "FIN de ProcesarDetalleSISACT. Tamanio de objListaMontoFactura: [" + objListaMontoFactura.size() + "]");
	}
	
	private void ObtenerMontoFactxBilletera(PropertiesExternos propertiesExternos, String mensajeLog, List<BEPlanBilletera> objListaMontoFactura)
	
			throws DBException, NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, ClassNotFoundException {
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		LOG.info(mensajeLog + "INICIO de ObtenerMontoFactxBilletera" );
		String strMontoFacturado = "";
		String strMontoNoFacturado = "";
		
		if(objListaMontoFactura != null) {
			LOG.info(mensajeLog + "Tamanio de ListaMontoFactura: " + objListaMontoFactura.size());				
		}
		
		int regMontos = Constantes.CERO;
		for (BEPlanBilletera obj : objListaMontoFactura) {
			if (obj.getmontoFacturado() > Constantes.CERO) {
				LOG.info(mensajeLog + "REG ["+regMontos+"] Monto Facturado encontrado: [" + obj.getmontoFacturado() + "]");
				
				strMontoFacturado = Constantes.FORMATOLISTAMONTOFACTURADO
									.replace("{0}", strMontoFacturado)
									.replace("{1}", String.valueOf(obj.getidBilletera()))
									.replace("{2}", String.valueOf(obj.getmontoFacturado()));
									
			}else{
				LOG.info(mensajeLog + "REG ["+regMontos+"] Monto Facturado es CERO o menos: [" + obj.getmontoFacturado() + "]");
			}
			if (obj.getmontoNoFacturado() > Constantes.CERO) {
				
				strMontoNoFacturado = Constantes.FORMATOLISTAMONTONOFACTURADO
												.replace("{0}", strMontoNoFacturado)
												.replace("{1}", String.valueOf(obj.getidBilletera()))
												.replace("{2}", String.valueOf(obj.getmontoNoFacturado()));

				LOG.info(mensajeLog + "REG ["+regMontos+"] Monto NO Facturado encontrado: [" + obj.getmontoNoFacturado() + "]");
			}else{
				LOG.info(mensajeLog + "REG ["+regMontos+"] Monto NO Facturado es CERO o menos: [" + obj.getmontoNoFacturado() + "]");
			}
			LOG.info(mensajeLog + "REG ["+regMontos+"] Cargando lista de planes Facturados: [" + strMontoFacturado+"]");
			LOG.info(mensajeLog + "REG ["+regMontos+"] Cargando lista de planes No Facturados: [" + strMontoNoFacturado+"]");
			
			regMontos++;

			montoFacturadoTotal += obj.getmontoFacturado();
			montoNoFacturadoTotal += obj.getmontoNoFacturado();
		}

		oMontoFacturadoxBilletera = obtenerMontoFactxBilletera(propertiesExternos, mensajeLog, strMontoFacturado);
		LOG.info(mensajeLog + "Parametro de salida de obtenerMontoFactxBilletera para monto facturado"+ oMontoFacturadoxBilletera);
		oMontoNoFacturadoxBilletera = obtenerMontoFactxBilletera(propertiesExternos, mensajeLog, strMontoNoFacturado);
		LOG.info(mensajeLog + "Parametro de salida de obtenerMontoFactxBilletera para monto no facturado"+ oMontoNoFacturadoxBilletera);		
		LOG.info(mensajeLog + "FIN de ObtenerMontoFactxBilletera" );

	}
	
	public ArrayList<BEPlanBilletera> ObtenerBilleteraxPlan(PropertiesExternos propertiesExternos, String mensajeLog, String strListaPlan)
			throws DBException{
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		LOG.info(mensajeLog + "INICIO de ObtenerBilleteraxPlan" );
		PvuPlanXProductoBeanResponse objPlanxProducto = new PvuPlanXProductoBeanResponse();
		objPlanxProducto = procesarPlanXProducto(propertiesExternos, mensajeLog, strListaPlan);

		ArrayList<BEPlanBilletera> objLista = new ArrayList<>();
		ArrayList<String> objListaPlan = new ArrayList<>();
		BEPlanBilletera objItem;
		BEBilletera objBilletera;
		String plan;

		for (PvuPlanXProductoCurBean dr : objPlanxProducto.getP_cursor()) {

			plan = dr.getPlan();
			objBilletera = new BEBilletera(Integer.parseInt(dr.getProducto()), dr.getDescripcion());

			if (!objListaPlan.contains(plan)) {
				objItem = new BEPlanBilletera();
				objItem.setplan(plan);
				objItem.setoBilletera(new ArrayList<BEBilletera>());
				objItem.getoBilletera().add(objBilletera);

				objLista.add(objItem);
				objListaPlan.add(plan);
			} else {
				for (BEPlanBilletera objPlan : objLista) {
					if (plan.equals(objPlan.getplan())) {
						objPlan.getoBilletera().add(objBilletera);
						break;
					}
				}
			}
		}
		LOG.info(mensajeLog + "FIN de ObtenerBilleteraxPlan" );
		return objLista;
	}
	
	public ArrayList<BEBilletera> obtenerMontoFactxBilletera(PropertiesExternos propertiesExternos, String mensajeLog, String listaPlanesMontos)
			throws DBException{
		
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		LOG.info(mensajeLog + "INICIO de obtenerMontoFactxBilletera" );
		PvuFacturaXProductoBeanResponse objFactXProductoPVU = new PvuFacturaXProductoBeanResponse();
		objFactXProductoPVU = procesarObtenerMontoFactxProductoPVU(propertiesExternos, mensajeLog, listaPlanesMontos);

		BEBilletera objItem;

		ArrayList<BEBilletera> objLista = new ArrayList<BEBilletera>();
		LOG.info(" eeeeeeeeeeeeeeeeeeeennnnnnnnnnnnnnneeccio antes de entrar asssaasd");
		LOG.info(" objFactXProductoPVU.getC_producto_fact().size() "+objFactXProductoPVU.getC_producto_fact().size());		
		if(objFactXProductoPVU.getC_producto_fact()!=null) {
			LOG.info(" Entreeeeeeeeeeeeeeeeee");
			for (PvuFacturaXProductoCurBean dr : objFactXProductoPVU.getC_producto_fact()) {
				objItem = new BEBilletera();
				objItem.setidBilletera(dr.getCodigo());
				objItem.setbilletera(dr.getDescripcion());
				objItem.setmonto(Double.parseDouble(dr.getValor()));
				LOG.info("codigo ==========>"+dr.getCodigo());
				LOG.info("Descripcion =========>"+dr.getDescripcion());
				LOG.info("Valor =============>"+Double.parseDouble(dr.getValor()));
				LOG.info("Monto ============>"+objItem.getmonto());				
				objLista.add(objItem);
			}
		}
		
		LOG.info(mensajeLog + "FIN de obtenerMontoFactxBilletera" );
		return objLista;
	}
	
	private PvuPlanXProductoBeanResponse procesarPlanXProducto(PropertiesExternos propertiesExternos, String mensajeLog, String strListaPlan)
			throws DBException{
		
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		LOG.info(mensajeLog + "INICIO de procesarPlanXProducto" );
		PvuPlanXProductoBeanResponse respPlanXProducto = new PvuPlanXProductoBeanResponse();
		PvuPlanXProductoBeanRequest reqPlanXProducto = new PvuPlanXProductoBeanRequest();
		reqPlanXProducto.setP_planes(strListaPlan);

		respPlanXProducto = obtenerDataOperacionDao.obtenerPlanXProductoPVU(propertiesExternos, mensajeLog, reqPlanXProducto, flagLogBD);
		LOG.info(mensajeLog + "FIN de procesarPlanXProducto" );
		return respPlanXProducto;
	}
	
	public PvuFacturaXProductoBeanResponse procesarObtenerMontoFactxProductoPVU(PropertiesExternos propertiesExternos,String mensajeLog,
			String p_lista_planes) throws DBException{
		
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		LOG.info(mensajeLog + "INICIO de procesarObtenerMontoFactxProductoPVU" );
		PvuFacturaXProductoBeanRequest reqFactXproducto = new PvuFacturaXProductoBeanRequest();
		PvuFacturaXProductoBeanResponse respFactxProducto = new PvuFacturaXProductoBeanResponse();

		reqFactXproducto.setP_lista_planes(p_lista_planes);
		respFactxProducto = obtenerDataOperacionDao.obtenerMontoFactxProductoPVU(propertiesExternos, mensajeLog, reqFactXproducto, flagLogBD);
		
		LOG.info("Salida de respFactxProducto \n" + JAXBUtilitarios.anyObjectToXmlText(respFactxProducto));
		LOG.info(mensajeLog + "FIN de procesarObtenerMontoFactxProductoPVU" );
		return respFactxProducto;
	}
			
	private Pvu3Play6ConDetalleLineaBeanResponse procesar3playConDetalleLineaPVU(PropertiesExternos propertiesExternos,String mensajeLog,
			String numeroDocumento, String listaTelefono) throws DBException{
		
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		LOG.info(mensajeLog + "INICIO de procesar3playConDetalleLineaPVU" );
		Pvu3Play6ConDetalleLineaBeanResponse resp = new Pvu3Play6ConDetalleLineaBeanResponse();
		Pvu3Play6ConDetalleLineaBeanRequest req = new Pvu3Play6ConDetalleLineaBeanRequest();

		req.setP_cliec_num_doc(numeroDocumento);
		req.setP_lista_telefono(listaTelefono);
		req.setP_tdocc_codigo(Constantes.CODTIPODOCUMENTODNIPVU);

		resp = obtenerDataOperacionDao.obtenerDetalleLinea3play6PVU(propertiesExternos, mensajeLog, req, flagLogBD);
		LOG.info(mensajeLog + "FIN de procesar3playConDetalleLineaPVU" );
		return resp;
	}
			
	public PvuPlanBilleteraBeanResponse procesarPlanBilleteraPVU(PropertiesExternos propertiesExternos ,String mensajeLog, Integer sistema)
			throws DBException{
		
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "[" + methodName + "] ";
		
		LOG.info(mensajeLog + "[INICIO Obtener Plan X Billetera PVU]");
		PvuPlanBilleteraBeanRequest reqProcesarPlanBilletera = new PvuPlanBilleteraBeanRequest();
		PvuPlanBilleteraBeanResponse respProcesarPlanBilletera = new PvuPlanBilleteraBeanResponse();

		reqProcesarPlanBilletera.setP_sistema(sistema);
		respProcesarPlanBilletera = obtenerDataOperacionDao.obtenerPlanBilleteraPVU(propertiesExternos, mensajeLog, reqProcesarPlanBilletera, flagLogBD);
		LOG.info(mensajeLog + "[FIN Obtener Plan X Billetera PVU]");
		return respProcesarPlanBilletera;
	}
		
}