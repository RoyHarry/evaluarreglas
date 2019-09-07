package pe.com.claro.venta.evaluarreglas.domain.util;

import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.YEARS;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.evaluarreglas.canonical.response.webservice.ebscreditos.ClienteType;
import pe.com.claro.venta.evaluarreglas.model.BscsDXLpCurDetalleBean;
import pe.com.claro.venta.evaluarreglas.model.BscsDetalleXLineaBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.SgaGetInformacionClienteBeanResponse;


@Stateless
public class Utilitarios {
	
	private static final Logger LOG = LoggerFactory.getLogger(UtilClaroEvalParametros.class);
	
	public void clogger(String mensajeTransaccion, List<?> responseLista, Object bean, String nameCursor) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		
		ArrayList<String> paramOutCur = new ArrayList<>();
		for(Field f :bean.getClass().getFields()) {paramOutCur.add(f.getName());}
		
		int iLista = Constantes.CERO;
		
		for(int j = Constantes.CERO; j<responseLista.size(); j++){
			ArrayList<Object> valorOUTPUTCursorUno = new ArrayList<>();
			for(Field f: responseLista.get(iLista).getClass().getFields() ){
				Field field;
		    	field = bean.getClass().getDeclaredField(f.getName());
				field.setAccessible(true);
				Object value = field.get(responseLista.get(iLista));
				valorOUTPUTCursorUno.add(value);
			}
			
			String mensajeRegistro = Constantes.CONSTANTE_VACIA;
			for(int i = Constantes.CERO ; i < paramOutCur.size() ;i++){mensajeRegistro += paramOutCur.get(i) + ":[" + valorOUTPUTCursorUno.get(i) + "], ";}
			LOG.info( mensajeTransaccion + "SP [OUPUT]["+nameCursor+"]: Fila ["+ (iLista + Constantes.UNO) +"]: "+mensajeRegistro);
			iLista++;
		}
	}
	
	public int obtenerMesesDePermanencia(PropertiesExternos propertiesExternos, String mensajeLog, SgaGetInformacionClienteBeanResponse respClienteServiciosSGA , BscsDetalleXLineaBeanResponse respDetalleXLineaBSCS) throws ParseException{
		
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "["+methodName+"]";
		LOG.info(mensajeLog + "INICIO Obtener meses de permanencia");
		
		int intMesesPermanencia = Constantes.CERO;
		int intMesesPermanenciaSGA = Constantes.CERO;
		Integer mesesPermanenciaBSCS = Constantes.CERO;
		
		
		if(respClienteServiciosSGA.getP_detalles_inf()!= null) {
			if(respClienteServiciosSGA.getP_detalles_inf().size() > Constantes.CERO){
				
				Date fechaActivacionSGA = respClienteServiciosSGA.getP_detalles_inf().get(Constantes.CERO).getFecha_activacion();	
				DateFormat df = new SimpleDateFormat(Constantes.FORMATO_FECHA_DEFAULT);
				String fechaActivacionSGAString = df.format(fechaActivacionSGA);
	
				LOG.info(mensajeLog + "Convirtiendo fecha de Activacion de SGA al formato: [" + Constantes.FORMATO_FECHA_DEFAULT +"]" );
				LOG.info(mensajeLog + "Fecha de Activacion en SGA obtenido: [" + fechaActivacionSGAString +"]");
				
				if(fechaActivacionSGA != null){
					LOG.info(mensajeLog + "Calculando Meses Transcurridos desde la fecha de Activacion en SGA");
					if(respClienteServiciosSGA.getP_detalles_inf().get(Constantes.CERO).getEstado().equalsIgnoreCase(Constantes.ACTIVO)){
				        intMesesPermanenciaSGA = calcularCantidadMesesTranscurridos(mensajeLog,   fechaActivacionSGAString);	
					}else{
				        intMesesPermanenciaSGA = Constantes.CERO;
					}
					LOG.info(mensajeLog + "Meses de Permamencia en SGA calculado: [" + intMesesPermanenciaSGA + "]");
				}else{
					LOG.info(mensajeLog + "La fecha de activacion es Nula");
				}
			}
		}
				
		if(respDetalleXLineaBSCS.getP_CUR_DETALLE().size() > Constantes.CERO){
			
			ArrayList<Integer> listaAntiguedadesMeses = new ArrayList<>();
			
			for(BscsDXLpCurDetalleBean objLista : respDetalleXLineaBSCS.getP_CUR_DETALLE()){
				LOG.info(mensajeLog + "Obteniendo el ESTADO y la Fecha de Activacion en BSCS");
				LOG.info(mensajeLog + "Validando si el estado es Activo y la fecha no se ecnuentre vacia");
				
				String EstadoBSCS = objLista.getESTADO();
				String FechaActivacionBSCS = objLista.getFECHA_ACTIVACION();
				
				LOG.info(mensajeLog + "ESTADO en BSCS: [" + EstadoBSCS + "]. Fecha de Activacion BSCS: [" + FechaActivacionBSCS + "]");
				if(EstadoBSCS.equalsIgnoreCase(Constantes.VALORCONDICIONALACTIVO) ){
					
					if(!FechaActivacionBSCS.equalsIgnoreCase(Constantes.CONSTANTE_VACIA)){
						
						LOG.info(mensajeLog + "Paso validacion de Estado y fecha");
						LOG.info(mensajeLog + "Calculando Meses Transcurridos desde la fecha de Activacion en BSCS: [" + FechaActivacionBSCS+ "]");
						mesesPermanenciaBSCS = calcularCantidadMesesTranscurridos(mensajeLog, FechaActivacionBSCS) ;
						LOG.info(mensajeLog + "Meses de Permanencia en BSCS calculado: [" + mesesPermanenciaBSCS + "]");
						
						listaAntiguedadesMeses.add(mesesPermanenciaBSCS);
					}else{
						LOG.info(mensajeLog + "No paso validacion de Estado Activo y Fecha no Vacia");
					}
				}else{
					LOG.info(mensajeLog + "No paso validacion de Estado Activo y Fecha no Vacia");
				}
			}
			
			
			if(listaAntiguedadesMeses.size()>Constantes.UNO){
				LOG.info(mensajeLog + "Existe mas de una linea activa en BSCS");
				Integer planAux = Constantes.CERO;
				
		        for( int i = Constantes.CERO ; i< (listaAntiguedadesMeses.size() - Constantes.UNO); i++ ){
		        	
		        	for (int j = i + Constantes.UNO; j< listaAntiguedadesMeses.size(); j++){
		 
		        		Integer laMUno = listaAntiguedadesMeses.get(i);
		        		Integer laMDos = listaAntiguedadesMeses.get(j);
		        		
		        		if(laMUno > laMDos){
		        			planAux = listaAntiguedadesMeses.get(i);
		        			listaAntiguedadesMeses.set(i, listaAntiguedadesMeses.get(j));
		        			listaAntiguedadesMeses.set(j, planAux);
		        		}
		        	}
		        }
		        
		        for(int a : listaAntiguedadesMeses){
		        	mesesPermanenciaBSCS = a;
		        }
			}else{
				if(listaAntiguedadesMeses.size()==Constantes.UNO){
					LOG.info(mensajeLog + "Existe SOLO una linea activa en BSCS");
					mesesPermanenciaBSCS = listaAntiguedadesMeses.get(Constantes.CERO);
				}else{
					LOG.info(mensajeLog + "El cliente no cuenta con lineas ACTIVAS en BSCS:");
					mesesPermanenciaBSCS = Constantes.CERO;
				}				
			}
			
			 LOG.info(mensajeLog + "Mes de permanencia mas antigua de lineas activas en BSCS:" + mesesPermanenciaBSCS);
		}
		
		LOG.info(mensajeLog + "Comparando los meses de permanencia entre SGA ("+ intMesesPermanenciaSGA + ")  y BSCS ("+ mesesPermanenciaBSCS +")");
		intMesesPermanencia = (mesesPermanenciaBSCS > intMesesPermanenciaSGA) ? mesesPermanenciaBSCS : intMesesPermanenciaSGA;
		
		LOG.info(mensajeLog + "FIN Obtener meses de permanencia. Meses de permamencia Obtenido: [" + intMesesPermanencia +"]");
		return intMesesPermanencia;
	}
	
	public int calcularCantidadMesesTranscurridos(String mensajeLog, String fechaPasada ) throws ParseException{
		
		String methodName = new String (Thread.currentThread().getStackTrace()[Constantes.UNO].getMethodName());
		mensajeLog = mensajeLog + "["+methodName+"]";
		
		LOG.info(mensajeLog + "INICIO de calcularCantidadMesesTranscurridos");
		
		
		DateFormat df = new SimpleDateFormat(Constantes.FORMATO_FECHA_DD_MM_YYYY);
		Date today = Calendar.getInstance().getTime();        
		String fechaActual = df.format(today);
		String fechaPasadaSinHora=fechaPasada;
		LOG.info(mensajeLog + "fecha pasada:"+fechaPasadaSinHora);
		if(fechaPasada.length()>10){
			DateFormat outputFormat = new SimpleDateFormat(Constantes.FORMATO_FECHA_DD_MM_YYYY);
			DateFormat inputFormat = new SimpleDateFormat(Constantes.FORMATO_FECHA_DEFAULT);
			
			Date date = inputFormat.parse(fechaPasada);
			fechaPasadaSinHora = outputFormat.format(date);
			LOG.info(mensajeLog + "fecha pasada sin hora:"+fechaPasadaSinHora);
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.FORMATO_FECHA_DD_MM_YYYY);
		
        ChronoLocalDate from = ChronoLocalDate.from(formatter.parse(fechaPasadaSinHora));
        
        ChronoLocalDate to = ChronoLocalDate.from(formatter.parse(fechaActual));
        ChronoPeriod period = ChronoPeriod.between(from, to);
        
        LOG.info(mensajeLog + "fecha actual:"+ fechaActual);

        int intMeses = (int) period.get(MONTHS)+ ((int) period.get(YEARS)*Constantes.INT_MESES_ANO);
        LOG.info(mensajeLog + "Resultado de calculo de cantidad de meses Transcurridos: [" + intMeses + "]");
        LOG.info(mensajeLog + "FIN de calcularCantidadMesesTranscurridos");
        return intMeses;
	}
	
	public Calendar addDaysCalendar(Calendar date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date.getTime());
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal;
    }
	
	//Renovacion
	public ClienteType obtenerClienteXML(String clienteXML){
		
		ClienteType objectCliente = new ClienteType();
		
		Integer indexNumOperacion =clienteXML.lastIndexOf(Constantes.CONSTANTE_NUMERO_OPERACION);
		Integer indexCodigoModelo =clienteXML.lastIndexOf(Constantes.CONSTANTE_CODIGO_MODELO);
		Integer indexRegsCalificacion = clienteXML.lastIndexOf(Constantes.CONSTANTE_REGS_CALIFICACION);
		Integer indexAccion = clienteXML.lastIndexOf(Constantes.CONSTANTE_ACCION);
		Integer indexDirecciones = clienteXML.lastIndexOf(Constantes.CONSTANTE_DIRECCIONES);
		Integer indexLimiteCredito = clienteXML.lastIndexOf(Constantes.CONSTANTE_LIMITE_CREDITO);
		Integer indexClaseCliente = clienteXML.lastIndexOf(Constantes.CONSTANTE_CLASE_CLIENTE);
		Integer indexLcDisponible = clienteXML.lastIndexOf(Constantes.CONSTANTE_LC_DISPONIBLE);
		Integer indexNvLcMaximo = clienteXML.lastIndexOf(Constantes.CONSTANTE_NV_LC_MAXIMO);
		Integer indexNvTotalCargosFijos = clienteXML.lastIndexOf(Constantes.CONSTANTE_NV_TOTAL_CARGOS_FIJOS);
		Integer indexExplicacion = clienteXML.lastIndexOf(Constantes.CONSTANTE_EXPLICACION);
		Integer indexScore = clienteXML.lastIndexOf(Constantes.CONSTANTE_SCORE);
		Integer indexCreditoScore = clienteXML.lastIndexOf(Constantes.CONSTANTE_CREDIT_SCORE);
		Integer indexTipoProducto = clienteXML.lastIndexOf(Constantes.CONSTANTE_TIPO_DE_PRODUCTO);
		Integer indexLineaCreditoSistema = clienteXML.lastIndexOf(Constantes.CONSTANTE_LINEA_CREDITO_SISTEMA);
		Integer indexEdad = clienteXML.lastIndexOf(Constantes.CONSTANTE_EDAD);
		Integer indexIngresoOLc = clienteXML.lastIndexOf(Constantes.CONSTANTE_INGRESO_O_LC);
		Integer indexTipoCliente = clienteXML.lastIndexOf(Constantes.CONSTANTE_TIPO_CLIENTE);
		Integer indexTipoTarjeta = clienteXML.lastIndexOf(Constantes.CONSTANTE_TIPO_TARJETA);
		Integer indexTop10000 = clienteXML.lastIndexOf(Constantes.CONSTANTE_TOP_10000);
		Integer indexAntiguedadLaboral = clienteXML.lastIndexOf(Constantes.CONSTANTE_ANTIGUEDAD_LABORAL);
		Integer indexNumeroDocumento = clienteXML.lastIndexOf(Constantes.CONSTANTE_NUMERO_DOCUMENTO);
		Integer indexApellidoPaterno = clienteXML.lastIndexOf(Constantes.CONSTANTE_APELLIDO_PATERNO);
		Integer indexApellidoMaterno = clienteXML.lastIndexOf(Constantes.CONSTANTE_APELLIDO_MATERNO);
		Integer indexNombres = clienteXML.lastIndexOf(Constantes.CONSTANTE_NOMBRES);
		Integer indexRazones = clienteXML.lastIndexOf(Constantes.CONSTANTE_RAZONES);
		Integer indexAnalisis = clienteXML.lastIndexOf(Constantes.CONSTANTE_ANALISIS);
		Integer indexScoreRankinOperativo = clienteXML.lastIndexOf(Constantes.CONSTANTE_SCORE_RANKIN_OPERATIVO);
		Integer indexNumeroEntidadesRankinOperativo = clienteXML.lastIndexOf(Constantes.CONSTANTE_NUMERO_ENTIDADES_RANKIN_OPERATIVO);
		Integer indexPuntaje = clienteXML.lastIndexOf(Constantes.CONSTANTE_PUNTAJE);
		Integer indexLimiteCreditoDataCredito = clienteXML.lastIndexOf(Constantes.CONSTANTE_LIMITE_CREDITO_DATA_CREDITO);
		Integer indexLimiteCreditoBaseExterna = clienteXML.lastIndexOf(Constantes.CONSTANTE_LIMITE_CREDITO_BASE_EXTERNA);
		Integer indexLimiteCreditoClaro = clienteXML.lastIndexOf(Constantes.CONSTANTE_LIMITE_CREDITO_CLARO);
		Integer indexFechaNacimiento = clienteXML.lastIndexOf(Constantes.CONSTANTE_FECHA_NACIMIENTO);
		Integer indexRespuesta = clienteXML.lastIndexOf(Constantes.CONSTANTE_RESPUESTA);
		Integer indexFechaConsulta = clienteXML.lastIndexOf(Constantes.CONSTANTE_FECHA_CONSULTA);
		Integer indexFinTag = clienteXML.lastIndexOf(Constantes.CONSTANTE_FIN_TAG);
		
		String numeroOperacion;
		String codigoModelo;
		String regsCalificacion;
		String accion;
		String direcciones; 
		String limiteCredito; 
		String claseCliente ;
		String lcDisponible ;
		String nvLcMaximo ;
		String nvTotalCargosFijos; 
		String explicacion; 
		String score; 
		String creditoScore; 
		String tipoProducto; 
		String lineaCreditoSistema; 
		String edad; 
		String ingresoOLc; 
		String tipoCliente; 
		String tipoTarjeta; 
		String top10000; 
		String antiguedadLaboral; 
		String numeroDocumento; 
		String apellidoPaterno; 
		String apellidoMaterno; 
		String nombres; 
		String razones; 
		String analisis; 
		String scoreRankinOperativo; 
		String numeroEntidadesRankinOperativo; 
		String puntaje; 
		String limiteCreditoDataCredito; 
		String limiteCreditoBaseExterna; 
		String limiteCreditoClaro; 
		String fechaNacimiento; 
		String respuesta; 
		String fechaConsulta; 

		if (indexNumOperacion < indexFechaNacimiento){
			numeroOperacion = clienteXML.substring(indexNumOperacion+Constantes.CONSTANTE_NUMERO_OPERACION.length()+Constantes.DOS,indexCodigoModelo-Constantes.DOS);
			codigoModelo = clienteXML.substring(indexCodigoModelo+Constantes.CONSTANTE_CODIGO_MODELO.length()+Constantes.DOS,indexRegsCalificacion-Constantes.DOS);
			regsCalificacion = clienteXML.substring(indexRegsCalificacion+Constantes.CONSTANTE_REGS_CALIFICACION.length()+Constantes.DOS,indexAccion-Constantes.DOS);
			accion = clienteXML.substring(indexAccion+Constantes.CONSTANTE_ACCION.length()+Constantes.DOS,indexDirecciones-Constantes.DOS);
			direcciones = clienteXML.substring(indexDirecciones+Constantes.CONSTANTE_DIRECCIONES.length()+Constantes.DOS,indexLimiteCredito-Constantes.DOS);
			limiteCredito = clienteXML.substring(indexLimiteCredito+Constantes.CONSTANTE_LIMITE_CREDITO.length()+Constantes.DOS,indexClaseCliente-Constantes.DOS);
			claseCliente = clienteXML.substring(indexClaseCliente+Constantes.CONSTANTE_CLASE_CLIENTE.length()+Constantes.DOS,indexLcDisponible-Constantes.DOS);
			lcDisponible = clienteXML.substring(indexLcDisponible+Constantes.CONSTANTE_LC_DISPONIBLE.length()+Constantes.DOS,indexNvLcMaximo-Constantes.DOS);
			nvLcMaximo = clienteXML.substring(indexNvLcMaximo+Constantes.CONSTANTE_NV_LC_MAXIMO.length()+Constantes.DOS,indexNvTotalCargosFijos-Constantes.DOS);
			nvTotalCargosFijos = clienteXML.substring(indexNvTotalCargosFijos+Constantes.CONSTANTE_NV_TOTAL_CARGOS_FIJOS.length()+Constantes.DOS,indexExplicacion-Constantes.DOS);		
			explicacion = clienteXML.substring(indexExplicacion+Constantes.CONSTANTE_EXPLICACION.length()+Constantes.DOS,indexScore-Constantes.UNO);
			score = clienteXML.substring(indexScore+Constantes.CONSTANTE_SCORE.length()+Constantes.UNO,indexCreditoScore-Constantes.DOS);
			creditoScore = clienteXML.substring(indexCreditoScore+Constantes.CONSTANTE_CREDIT_SCORE.length()+Constantes.DOS,indexTipoProducto-Constantes.DOS);
			tipoProducto = clienteXML.substring(indexTipoProducto+Constantes.CONSTANTE_TIPO_DE_PRODUCTO.length()+Constantes.DOS,indexLineaCreditoSistema-Constantes.DOS);		
			lineaCreditoSistema = clienteXML.substring(indexLineaCreditoSistema+Constantes.CONSTANTE_LINEA_CREDITO_SISTEMA.length()+Constantes.DOS,indexEdad-Constantes.UNO);
			edad = clienteXML.substring(indexEdad+Constantes.CONSTANTE_EDAD.length()+Constantes.UNO,indexIngresoOLc - Constantes.DOS);
			ingresoOLc = clienteXML.substring(indexIngresoOLc+Constantes.CONSTANTE_INGRESO_O_LC.length()+Constantes.DOS,indexTipoCliente-Constantes.DOS);
			tipoCliente = clienteXML.substring(indexTipoCliente+Constantes.CONSTANTE_TIPO_CLIENTE.length()+Constantes.DOS,indexTipoTarjeta-Constantes.DOS);
			tipoTarjeta = clienteXML.substring(indexTipoTarjeta+Constantes.CONSTANTE_TIPO_TARJETA.length()+Constantes.DOS,indexTop10000-Constantes.DOS);
			top10000 = clienteXML.substring(indexTop10000+Constantes.CONSTANTE_TOP_10000.length()+Constantes.DOS,indexAntiguedadLaboral-Constantes.DOS);
			antiguedadLaboral = clienteXML.substring(indexAntiguedadLaboral+Constantes.CONSTANTE_ANTIGUEDAD_LABORAL.length()+Constantes.DOS,indexNumeroDocumento-Constantes.DOS);
			numeroDocumento = clienteXML.substring(indexNumeroDocumento+Constantes.CONSTANTE_NUMERO_DOCUMENTO.length()+Constantes.DOS,indexApellidoPaterno-Constantes.DOS);
			apellidoPaterno = obtenerDato(clienteXML, indexApellidoPaterno.intValue(), Constantes.CONSTANTE_APELLIDO_PATERNO.length());
			apellidoMaterno = indexApellidoMaterno > 0 ? clienteXML.substring(indexApellidoMaterno+Constantes.CONSTANTE_APELLIDO_MATERNO.length()+Constantes.DOS,indexNombres-Constantes.DOS) : null;
			nombres = clienteXML.substring(indexNombres+Constantes.CONSTANTE_NOMBRES.length()+Constantes.DOS,indexRazones-Constantes.DOS);
			razones = clienteXML.substring(indexRazones+Constantes.CONSTANTE_RAZONES.length()+Constantes.DOS,indexAnalisis-Constantes.DOS);
			analisis = clienteXML.substring(indexAnalisis+Constantes.CONSTANTE_ANALISIS.length()+Constantes.DOS,indexScoreRankinOperativo-Constantes.DOS);
			scoreRankinOperativo = clienteXML.substring(indexScoreRankinOperativo+Constantes.CONSTANTE_SCORE_RANKIN_OPERATIVO.length()+Constantes.DOS,indexNumeroEntidadesRankinOperativo-Constantes.DOS);
			numeroEntidadesRankinOperativo = clienteXML.substring(indexNumeroEntidadesRankinOperativo+Constantes.CONSTANTE_NUMERO_ENTIDADES_RANKIN_OPERATIVO.length()+Constantes.DOS,indexPuntaje-Constantes.DOS);
			puntaje = clienteXML.substring(indexPuntaje+Constantes.CONSTANTE_PUNTAJE.length()+Constantes.DOS,indexLimiteCreditoDataCredito-Constantes.DOS);
			limiteCreditoDataCredito = clienteXML.substring(indexLimiteCreditoDataCredito+Constantes.CONSTANTE_LIMITE_CREDITO_DATA_CREDITO.length()+Constantes.DOS,indexLimiteCreditoBaseExterna-Constantes.DOS);
			limiteCreditoBaseExterna = clienteXML.substring(indexLimiteCreditoBaseExterna+Constantes.CONSTANTE_LIMITE_CREDITO_BASE_EXTERNA.length()+Constantes.DOS,indexLimiteCreditoClaro-Constantes.DOS);
			limiteCreditoClaro = clienteXML.substring(indexLimiteCreditoClaro+Constantes.CONSTANTE_LIMITE_CREDITO_CLARO.length()+Constantes.DOS,indexFechaNacimiento-Constantes.DOS);
			fechaNacimiento = clienteXML.substring(indexFechaNacimiento+Constantes.CONSTANTE_FECHA_NACIMIENTO.length()+Constantes.DOS,indexRespuesta-Constantes.DOS);
			respuesta = clienteXML.substring(indexRespuesta+Constantes.CONSTANTE_RESPUESTA.length()+Constantes.DOS,indexFechaConsulta-Constantes.DOS);
			fechaConsulta = clienteXML.substring(indexFechaConsulta+Constantes.CONSTANTE_FECHA_CONSULTA.length()+Constantes.DOS,indexFechaConsulta+Constantes.CONSTANTE_FECHA_CONSULTA.length()+Constantes.DOCE);
		}else{
			fechaNacimiento = clienteXML.substring(indexFechaNacimiento+Constantes.CONSTANTE_FECHA_NACIMIENTO.length()+Constantes.DOS,indexLimiteCreditoClaro-Constantes.DOS);
			limiteCreditoClaro = clienteXML.substring(indexLimiteCreditoClaro+Constantes.CONSTANTE_LIMITE_CREDITO_CLARO.length()+Constantes.DOS,indexLimiteCreditoBaseExterna-Constantes.DOS);
			limiteCreditoBaseExterna = clienteXML.substring(indexLimiteCreditoBaseExterna+Constantes.CONSTANTE_LIMITE_CREDITO_BASE_EXTERNA.length()+Constantes.DOS,indexLimiteCreditoDataCredito-Constantes.DOS);
			limiteCreditoDataCredito = clienteXML.substring(indexLimiteCreditoDataCredito+Constantes.CONSTANTE_LIMITE_CREDITO_DATA_CREDITO.length()+Constantes.DOS,indexPuntaje-Constantes.DOS);
			puntaje = clienteXML.substring(indexPuntaje+Constantes.CONSTANTE_PUNTAJE.length()+Constantes.DOS,indexNumeroEntidadesRankinOperativo-Constantes.DOS);
			numeroEntidadesRankinOperativo = clienteXML.substring(indexNumeroEntidadesRankinOperativo+Constantes.CONSTANTE_NUMERO_ENTIDADES_RANKIN_OPERATIVO.length()+Constantes.DOS,indexScoreRankinOperativo-Constantes.DOS);
			scoreRankinOperativo = clienteXML.substring(indexScoreRankinOperativo+Constantes.CONSTANTE_SCORE_RANKIN_OPERATIVO.length()+Constantes.DOS,indexAnalisis-Constantes.DOS);
			analisis = clienteXML.substring(indexAnalisis+Constantes.CONSTANTE_ANALISIS.length()+Constantes.DOS,indexRazones-Constantes.DOS);
			razones = clienteXML.substring(indexRazones+Constantes.CONSTANTE_RAZONES.length()+Constantes.DOS,indexNombres-Constantes.DOS);
			nombres = obtenerDato(clienteXML, indexNombres.intValue(), Constantes.CONSTANTE_NOMBRES.length());
			apellidoMaterno = indexApellidoMaterno > 0 ? clienteXML.substring(indexApellidoMaterno+Constantes.CONSTANTE_APELLIDO_MATERNO.length()+Constantes.DOS,indexApellidoPaterno-Constantes.DOS) : null;
			apellidoPaterno = clienteXML.substring(indexApellidoPaterno+Constantes.CONSTANTE_APELLIDO_PATERNO.length()+Constantes.DOS,indexNumeroDocumento-Constantes.DOS);
			numeroDocumento = clienteXML.substring(indexNumeroDocumento+Constantes.CONSTANTE_NUMERO_DOCUMENTO.length()+Constantes.DOS,indexAntiguedadLaboral-Constantes.DOS);
			antiguedadLaboral = clienteXML.substring(indexAntiguedadLaboral+Constantes.CONSTANTE_ANTIGUEDAD_LABORAL.length()+Constantes.DOS,indexTop10000-Constantes.DOS);
			top10000 = clienteXML.substring(indexTop10000+Constantes.CONSTANTE_TOP_10000.length()+Constantes.DOS,indexTipoTarjeta-Constantes.DOS);
			tipoTarjeta = clienteXML.substring(indexTipoTarjeta+Constantes.CONSTANTE_TIPO_TARJETA.length()+Constantes.DOS,indexTipoCliente-Constantes.DOS);
			tipoCliente = clienteXML.substring(indexTipoCliente+Constantes.CONSTANTE_TIPO_CLIENTE.length()+Constantes.DOS,indexIngresoOLc-Constantes.DOS);			
			ingresoOLc = clienteXML.substring(indexIngresoOLc+Constantes.CONSTANTE_INGRESO_O_LC.length()+Constantes.DOS,indexEdad-Constantes.DOS);
			edad = clienteXML.substring(indexEdad+Constantes.CONSTANTE_EDAD.length()+Constantes.UNO,indexLineaCreditoSistema - Constantes.DOS);
			lineaCreditoSistema = clienteXML.substring(indexLineaCreditoSistema+Constantes.CONSTANTE_LINEA_CREDITO_SISTEMA.length()+Constantes.DOS,indexTipoProducto-Constantes.DOS);
			tipoProducto = clienteXML.substring(indexTipoProducto+Constantes.CONSTANTE_TIPO_DE_PRODUCTO.length()+Constantes.DOS,indexCreditoScore-Constantes.DOS);
			creditoScore = clienteXML.substring(indexCreditoScore+Constantes.CONSTANTE_CREDIT_SCORE.length()+Constantes.DOS,indexScore-Constantes.UNO);
			score = clienteXML.substring(indexScore+Constantes.CONSTANTE_SCORE.length()+Constantes.UNO,indexExplicacion-Constantes.DOS);
			explicacion = clienteXML.substring(indexExplicacion+Constantes.CONSTANTE_EXPLICACION.length()+Constantes.DOS,indexNvTotalCargosFijos-Constantes.DOS);
			nvTotalCargosFijos = clienteXML.substring(indexNvTotalCargosFijos+Constantes.CONSTANTE_NV_TOTAL_CARGOS_FIJOS.length()+Constantes.DOS,indexNvLcMaximo-Constantes.DOS);
			nvLcMaximo = clienteXML.substring(indexNvLcMaximo+Constantes.CONSTANTE_NV_LC_MAXIMO.length()+Constantes.DOS,indexLcDisponible-Constantes.DOS);
			lcDisponible = clienteXML.substring(indexLcDisponible+Constantes.CONSTANTE_LC_DISPONIBLE.length()+Constantes.DOS,indexClaseCliente-Constantes.DOS);
			claseCliente = clienteXML.substring(indexClaseCliente+Constantes.CONSTANTE_CLASE_CLIENTE.length()+Constantes.DOS,indexLimiteCredito-Constantes.DOS);
			limiteCredito = clienteXML.substring(indexLimiteCredito+Constantes.CONSTANTE_LIMITE_CREDITO.length()+Constantes.DOS,indexDirecciones-Constantes.DOS);
			direcciones = clienteXML.substring(indexDirecciones+Constantes.CONSTANTE_DIRECCIONES.length()+Constantes.DOS,indexAccion-Constantes.DOS);
			accion = clienteXML.substring(indexAccion+Constantes.CONSTANTE_ACCION.length()+Constantes.DOS,indexRegsCalificacion-Constantes.DOS);
			regsCalificacion = clienteXML.substring(indexRegsCalificacion+Constantes.CONSTANTE_REGS_CALIFICACION.length()+Constantes.DOS,indexCodigoModelo-Constantes.DOS);
			codigoModelo = clienteXML.substring(indexCodigoModelo+Constantes.CONSTANTE_CODIGO_MODELO.length()+Constantes.DOS,indexNumOperacion-Constantes.DOS);
			numeroOperacion = clienteXML.substring(indexNumOperacion+Constantes.CONSTANTE_NUMERO_OPERACION.length()+Constantes.DOS,indexRespuesta-Constantes.DOS);
			respuesta = clienteXML.substring(indexRespuesta+Constantes.CONSTANTE_RESPUESTA.length()+Constantes.DOS,indexFechaConsulta-Constantes.DOS);
			fechaConsulta = clienteXML.substring(indexFechaConsulta+Constantes.CONSTANTE_FECHA_CONSULTA.length()+Constantes.DOS,indexFinTag-Constantes.UNO);
		}
		objectCliente.setNumeroOperacion(numeroOperacion);
		objectCliente.setCodigoModelo(codigoModelo);
		objectCliente.setRegsCalificacion(regsCalificacion);
		objectCliente.setAccion(accion);
		objectCliente.setDirecciones(direcciones);
		objectCliente.setLimiteCredito(limiteCredito);
		objectCliente.setClaseCliente(claseCliente);
		objectCliente.setLcDisponible(lcDisponible);
		objectCliente.setNvLcMaximo(nvLcMaximo);
		objectCliente.setNvTotalCargosFijos(nvTotalCargosFijos);
		objectCliente.setExplicacion(explicacion);
		objectCliente.setScore(score);
		objectCliente.setCreditScore(creditoScore);
		objectCliente.setTipoProducto(tipoProducto);
		objectCliente.setLineaCreditoSistema(lineaCreditoSistema);
		objectCliente.setEdad(edad);
		objectCliente.setIngresoOLC(ingresoOLc);
		objectCliente.setTipoCliente(tipoCliente);
		objectCliente.setTipoTarjeta(tipoTarjeta);
		objectCliente.setTopDiesMil(top10000);
		objectCliente.setAntiguedadLaboral(antiguedadLaboral);
		objectCliente.setNumeroDocumento(numeroDocumento);
		objectCliente.setApellidoPaterno(apellidoPaterno);
		objectCliente.setApellidoMaterno(apellidoMaterno);
		objectCliente.setNombres(nombres);
		objectCliente.setRazones(razones);
		objectCliente.setAnalisis(analisis);
		objectCliente.setScoreRankinOperativo(scoreRankinOperativo);
		objectCliente.setNumeroEntidadesRankinOperativo(numeroEntidadesRankinOperativo);
		objectCliente.setPuntaje(puntaje);
		objectCliente.setLimiteCreditoDataCredito(limiteCreditoDataCredito);
		objectCliente.setLimiteCreditoBaseExterno(limiteCreditoBaseExterna);
		objectCliente.setLimiteCreditoClaro(limiteCreditoClaro);
		objectCliente.setFechaNacimiento(fechaNacimiento);
		objectCliente.setRespuesta(respuesta);
		objectCliente.setFechaConsulta(fechaConsulta);		
		
		return objectCliente;
	}

	private static String obtenerDato(String clienteXML, int index, int tamanioVariable){
		String substring = clienteXML.substring(index + tamanioVariable + Constantes.DOS);
		return substring.substring(Constantes.CERO, substring.indexOf(Constantes.CONSTANTE_COMILLA));
	}
	
	
	public int cantidadMesesOperadorCedente(String mensajeLog, String fechaActivacionCP) {

		Calendar fechaActivacionOCP = Calendar.getInstance();
		fechaActivacionOCP.setTime(new Date(Long.MIN_VALUE));
		Calendar fechaProceso = Calendar.getInstance();

		LOG.info(mensajeLog+ " Procediendo a obtener la cantidad de meses de operador cedente con la fecha de activacion obtenida: [ "+ fechaActivacionCP+ "]");
		LOG.info(mensajeLog+ " Intentando parsear a Date la fecha de activacion obtenida: [ "+fechaActivacionCP+ "]");

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date date;
			date = df.parse(fechaActivacionCP);
			LOG.info(mensajeLog+ " Conversion de Fecha de Activacion Exitosa: [ "+ date+ "]");
			fechaActivacionOCP.setTime(date);

		} catch (ParseException | NullPointerException e) {
			LOG.info(mensajeLog + "La de fecha de activacion no cumple con el formato requerido. Estableciendo el valor de ["
					+ Constantes.MENOS_UNO + "]");
			return Constantes.MENOS_UNO;
		}

		if ((fechaActivacionOCP.compareTo(fechaProceso)) < Constantes.CERO) {
			fechaProceso = ((fechaProceso.get(Calendar.MONTH) == Constantes.DOS)
					&& (fechaActivacionOCP.get(Calendar.MONTH) != fechaProceso.get(Calendar.MONTH)))
							? addDaysCalendar(fechaProceso, Constantes.UNO)
							: fechaProceso;
			fechaProceso = (fechaProceso.get(Calendar.MONTH) != Constantes.DOS
					&& ((fechaProceso.get(Calendar.DAY_OF_MONTH) == Constantes.INT_MES_TREINTA
							&& fechaProceso.getActualMaximum(Calendar.DAY_OF_MONTH) == Constantes.INT_MES_TREINTA)
							&& (fechaActivacionOCP.get(Calendar.DAY_OF_MONTH) == Constantes.INT_MES_TREINTA_Y_UNO
									&& fechaActivacionOCP.getActualMaximum(
											Calendar.DAY_OF_MONTH) == Constantes.INT_MES_TREINTA_Y_UNO)))
													? addDaysCalendar(fechaProceso, Constantes.UNO)
													: fechaProceso;
			int factorCP = fechaProceso.get(Calendar.DAY_OF_MONTH) >= fechaActivacionOCP.get(Calendar.DAY_OF_MONTH)
					? Constantes.CERO
					: Constantes.MENOS_UNO;
			return Math.abs(factorCP
					+ ((fechaProceso.get(Calendar.YEAR) - fechaActivacionOCP.get(Calendar.YEAR))
							* Constantes.INT_MESES_ANO)
					+ fechaProceso.get(Calendar.MONTH) - fechaActivacionOCP.get(Calendar.MONTH));
		} else {
			return Constantes.MENOS_UNO;
		}
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
