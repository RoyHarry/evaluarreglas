package pe.com.claro.common.property;

public final class Constantes {
	
	private Constantes() {		
	}
	
	public static final String CONSTANTE_VACIA = "";

	public static final String NOMBRE_API = "claro-venta-evaluarreglas";

	public static final String SEPARADOR_PUNTO = ".";

	public static final String NOMBRE_METODO_1 = "getDataValidaClienteReglas";
	public static final String NOMBRE_METODO_2 = "insertDataBrmsMovil";
	public static final String PATH = "/venta/evaluarreglas/v1.0.0";
	public static final String NOMBRE_SUBMETODO_1 = "claroEvalClientesReglas";

	public static final String PATHMETODO1 = "/getDataValidaClienteReglas";	
	public static final String TEXTO_NULL = "null";
	public static final String TEXTO_VACIO = "";

	public static final String FORMATO_FECHA_DEFAULT = "dd/MM/yyyy HH:mm:ss";
	public static final String FORMATO_FECHA_SP = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMATO_FECHA_Z = "yyyy-MM-dd'T'HH:mm:ss+00:00";
	public static final String FORMATO_FECHA_DD_MM_YYYY = "dd/MM/yyyy";
	public static final String TIMEOUTEXCEPTION = "Timeout";
	public static final String TIMEOUTEXCEPTION2 = "Timed out";
	public static final String PERSISTENCEEXCEPTION = "javax.persistence.PersistenceException";
	public static final String GENERICJDBCEXCEPTION = "org.hibernate.exception";
	public static final String HIBERNATEJDBCEXCEPTION = "The application must supply JDBC connections";							

	public static final String SMENOS_UNO = "-1";

	public static final int MENOS_UNO = -1;
	public static final int CERO = 0;
	public static final String SCERO = "0";

	public static final char COMA = ',';
	public static final String STRING_COMA = ",";
	public static final char PUNTO = '.';
	public static final char DOSPUNTOS = ':';
	public static final char GUIONBAJO = '_';
	public static final String PALOTE = "|";
	public static final String PALOTESEPARADORSPLIT = "\\|";
	public static final String NO_MINUSCULA = "no";
	public static final String TT = "TT";
	public static final String COMACONESPACIO = ", ";
	public static final String PUNTOCONESPACIO = ". ";
	public static final String CODIGOESTANDAREXITO = "0";
	public static final String PUNTOCOMA = ";";
	
	public static final String VACIO = "";

	public static final boolean VERDADERO = true;
	public static final boolean FALSO = false;
	public static final Object NULO = null;

	public static final String ACCEPT = "accept";
	public static final String IDTRANSACCION = "idTransaccion";
	public static final String MSGID = "msgid";
	public static final String TIMESTAMP = "timestamp";
	public static final String USERID = "userId";

	public static final String NOMBRERECURSO = "claro-venta-evaluarreglaspostpago";

	public static final String PROPERTIESINTERNOS = "config.properties";
	public static final String PROPERTIESEXTERNOS = ".properties";

	public static final String PROPERTIESKEY = "claro.properties";

	public static final String DEFAULTENCODINGPROPERTIES = "ISO-8859-1";
	public static final String DEFAULTENCODINGAPI = "UTF-8";

	public static final String CHAR_PARENTESIS_IZQUIERDO = "(";
	public static final String CHAR_PARENTESIS_DERECHO = ")";
	public static final String CHAR_INTERROGACION = "?";
	public static final String CHAR_COMA = ",";
	public static final String OK = "OK";
	public static final String ERROR = "ERROR";

	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	public static final String BOLSA_ID6 = "6";

	public static final  String SQL_TIMEOUTEXCEPTION = "SQLTIMEOUTEXCEPTION";

	public static final int STATUS_TIME_OUT = 504;
	public static final int STATUS_DISPONIBILIDAD = 404;
	
	/*Tipo Operacion Eval cliente*/
	public static final String CUOTAS_WITH_DATA = "C";
	public static final String CUOTAS_WITHOUT_DATA = "C1";
	public static final String CONTADO = "CON";
	
	/* Repository */
	public static final String PERSISTENCE_CONTEXT_PVU = "pe.com.claro.venta.data.source.pvu";
	public static final String PERSISTENCE_CONTEXT_SGA = "pe.com.claro.venta.data.source.sga";
	public static final String REPOSITORY = " - REPOSITORY(DAO)";
	
	public static final String NOMBRE_SUBMETODO_3 ="timComPagoBSCS";
	public static final String NOMBRE_SUBMETODO_4 ="tim127ComPagoBSCS";
	public static final String NOMBRE_SUBMETODO_5 ="obtenerPrecioBaseEquipo";
	public static final String NOMBRE_SUBMETODO_6 ="insertBRMSBioMovilV2";
	public static final String NOMBRE_SUBMETODO_7 ="obtenerParametroGeneralPVU";
	public static final String NOMBRE_SUBMETODO_8 ="obtenerMontoFactxProductoPVU";
	public static final String NOMBRE_SUBMETODO_9 ="obtenerPlanXProductoPVU";
	public static final String NOMBRE_SUBMETODO_10 ="obtenerDetalleLinea3play6PVU";
	public static final String NOMBRE_SUBMETODO_11 ="obtenerPlanBilleteraPVU";
	public static final String NOMBRE_SUBMETODO_12 ="calculoLCxProductoPVU";
	public static final String NOMBRE_SUBMETODO_13 ="obtenerCuotaIniComercial";
	public static final String NOMBRE_SUBMETODO_14 ="obtenerInfoClienteSGA";
	public static final String NOMBRE_SUBMETODO_15 ="pConsulta";
	public static final String NOMBRE_SUBMETODO_16 ="getDataValidaClienteReglas";
	public static final String NOMBRE_SUBMETODO_17 ="obtenerConTipoCuota";
	public static final String NOMBRE_SUBMETODO_18 ="obtenerCuotaIniComercial";	
	public static final String OBTENERCOIDBSCS ="obtenerCoidBscs";
	public static final String OBTENERLISTASERVICIOSBSCS ="obtenerListaServiciosBscs";		
	
	public static final String METHODO_OBTENERPARAMETRO ="obtenerDataParametros";
	
	
	public static final int UNO = 1;
	public static final int DOS = 2;
	public static final int TRES = 3;
	public static final int CUATRO = 4;
	public static final int CINCO = 5;
	public static final int SEIS=6;
    public static final int SIETE=7;
    public static final int OCHO=8;
    public static final int NUEVE=9;
    public static final int DIEZ=10;
    public static final int ONCE=11;
    public static final int DOCE=12;
    public static final int TRECE=13;
    public static final int CATORCE=14;
    public static final int QUINCE=15;
    public static final int DIECISEIS=16;
    public static final int DIECISIETE=17;
    public static final int DIECIOCHO=18;
    public static final int DIECINUEVE=19;
    public static final int VEINTE=20;
    public static final int VEINTIUNO=21;
    public static final int VEINTIDOS=22;
    public static final int VEINTITRES=23;
    public static final int VEINTICUATRO=24;
    public static final int VEINTICINCO=25;
    public static final int VEINTISEIS=26;
    public static final int VEINTISIETE=27;
    public static final int VEINTIOCHO=28;
    public static final int VEINTINUEVE=29;
    public static final int TREINTA=30;
    public static final int TREINTA_Y_UNO=31;
    public static final int TREINTA_Y_DOS=32;
    public static final int TREINTA_Y_TRES=33;
    public static final int TREINTA_Y_CUATRO=34;
    public static final int TREINTA_Y_CINCO=35;
    public static final int TREINTA_Y_SEIS=36;
    public static final int TREINTA_Y_SIETE=37;
    public static final int TREINTA_Y_OCHO=38;
    public static final int TREINTA_Y_NUEVE=39;
    public static final int CUARENTA = 40;
    public static final int CUARENTA_Y_UNO=41;
    public static final int CUARENTA_Y_DOS=42;
    public static final int CUARENTA_Y_TRES=43;
    public static final int CUARENTA_Y_CUATRO=44;
    public static final int CUARENTA_Y_CINCO=45;
    public static final int CUARENTA_Y_SEIS=46;
    public static final int CUARENTA_Y_SIETE=47;
    public static final int CUARENTA_Y_OCHO=48;
    public static final int CUARENTA_Y_NUEVE=49;
    public static final int CINCUENTA=50;
    public static final int CINCUENTA_Y_UNO=51;
    public static final int CINCUENTA_Y_DOS=52;
    public static final int CINCUENTA_Y_TRES=53;
    public static final int CINCUENTA_Y_CUATRO=54;
    public static final int CINCUENTA_Y_CINCO=55;
    public static final int CINCUENTA_Y_SEIS=56;
    public static final int CINCUENTA_Y_SIETE=57;
    public static final int CINCUENTA_Y_OCHO=58;
    public static final int CINCUENTA_Y_NUEVE=59;
    public static final int SESENTA=60;
    public static final int SESENTA_Y_UNO=61;
    public static final int SESENTA_Y_DOS=62;
    public static final int SESENTA_Y_TRES=63;
    public static final int SESENTA_Y_CUATRO=64;
    public static final int SESENTA_Y_CINCO=65;
    public static final int SESENTA_Y_SEIS=66;
    public static final int SESENTA_Y_SIETE=67;
    public static final int SESENTA_Y_OCHO=68;
    public static final int SESENTA_Y_NUEVE=69;
    public static final int SETENTA=70;
    public static final int SETENTA_Y_UNO=71;
    public static final int SETENTA_Y_DOS=72;
    
    public static final String STRING_SESENTA_Y_CINCO="65";
	/* RESTBUtilitarios */
    public static final String TYPEREQUEST="application/json";    
    
    public static final String NOMBRECLIENTECLAROEVALCLIENTESREGLAS = "ClaroEvalClientesReglas";
    public static final String NOMBRE_CLIENTE_CONSULTA_DATOS_CLIENTEOAC = "ConsultasDatosClienteOAC-Client";
    
    
    public static final String REQUESTSTR = "Request";
    public static final String AUDITREQUESTSTR = "auditRequest";
    public static final String IDTRANSACCIONSTR = "idTransaccion";
	public static final String IPAPLICACIONSTR = "ipAplicacion";
	public static final String NOMBREAPLICACIONSTR = "nombreAplicacion";
	public static final String USUARIOAPLICACIONSTR = "usuarioAplicacion";
	
	public static final String REPLACEEXCEPTIONMESSAGE = "[EXCEPTION_MESSAGE]";
	
	public static final Object NULLOBJECTO = null;
	
	public static final String REPLACECAMPOS = "[CAMPOS]";
	
	public static final int DNI_LONGITUD = 8;
	
	public static final String FALSE = "False";
	
	public static final String ESSALUD_SUNAT_F1 = "F1";
    public static final String ESSALUD_SUNAT_F2= "F2";
    
    public static final String REGULAR = "REGULAR";
    public static final String TIPODEPRODUCTOMOVIL = "MOVIL";
    public static final String SINPLAZODEACUERDO = "SIN PLAZO/POR TIEMPO ILIMITADO";
    public static final String SEGMENTODEOFERTAMASIVO= "MASIVO";
    public static final String TIPODEDESPACHOPDV = "PDV";
    public static final String TRANSACCIONEVALUACIONCREDITICIA = "EVALUACION CREDITICIA";
    public static final String CONTROLDECONSUMO = "SIN TOPE DE CONSUMO";
    
    
    public static final String SI = "SI";
    public static final String NO = "NO";
    
    public static final String CODTIPODOCUMENTODNIPVU = "01";
    

    public static final String CLIENTECLARO = "CLIENTE CLARO";
    public static final String CLIENTENUEVO = "CLIENTE NUEVO";
    
    public static final String CODIGO_01 = "01";
    public static final String CODIGO_02 = "02";
    
    public static final String MODALIDAD_CEDENTE_PREPAGO = "PREPAGO";
    public static final String MODALIDAD_CEDENTE_POSTPAGO = "POSTPAGO";
    
    public static final String OPERACION_PORTABILIDAD = "PORTABILIDAD";    
    public static final String OPERACION_ALTA = "VENTA NORMAL / ALTA";
    public static final String OPERACION_RENOVACION = "RENOVACION";        
    public static final String OPERACION_ALTA_RENOVACION = "ALTA-RENOVACION";    
    
    public static final String TIPO_OPERACION_PORTABILIDAD = "BIOMOVIL07";
    public static final String TIPO_OPERACION_ALTA = "BIOMOVIL06";
    public static final String TIPO_OPERACION_RENOVACION = "BIOMOVIL09";    
    
    public static final String LC_DISPONIBLE = "limiteCreditoDisponible";
    public static final String RIESGO_CLARO = "riesgoClaro";
    public static final String COMPORTA_PAGO = "comportaPago";
    public static final String STRMONTOSXBILLETERA = "strMontosxBilletera";
    public static final String STIPO_CLIENTE_SEC = "sTipoClienteSec";
    public static final String MESES_PERMANENCIA_EN_CLARO = "mesesPermanenciaEnClaro";    
    
    public static final Double CERODOUBLE = 0.0;
    
    public static final String PLANESSINRA = "PlanesSinRA";
    
    public static final String TRUE = "True";
    
    public static final int INT_MESES_ANO = 12;
    
    public static final String CODTIPODOCUMENTODNISGA = "002";
    
    public static final Integer CODTIPODOCUMENTODNIBSCS = 2;
    
    public static final String CODTIPODOCUMENTODNIOAC = "1";
    
    public static final String NOMBRECLIENTECONSULTADEUDACUENTA="ConsultaDeudaCuenta";
    
    public static final String NOMBRECLIENTECONSULTA_DATOS_BRMSOAC="ConsultaDatosBRMSOAC";    
    
    public static final String REPLACERECURSO = "[RECURSO]";
	public static final String REPLACEERRORRECURSO = "[ERROR_RECURSO]";
	public static final String REPLACEDBWS = "[BD_WS]";
	public static final String REPLACEMETSP = "[MET_SP]";
	public static final String REPLACEMETODOSPRECURSO = "[METODO_SP_RECURSO]";
	public static final String REPLACENOMBRESP = "[NOMBRE_SP]";
	public static final String REPLACENOMBREBD = "[NOMBRE_BD]";
	
	public static final String TIPOCLIENTEWS = "WS";
	public static final String TIPOOPERACIONMETODO = "METODO";
   	
	public static final String MODO_OPERACION_CONTADO = "1";	
	public static final String MODO_OPERACION_SIMPLE = "2";
	public static final String MODO_OPERACION_NORMAL = "3";
	public static final String MODO_OPERACION_CONTADO_RENOVACION = "4";
	public static final String MODO_OPERACION_CUOTAS_RENOVACION = "5";	
	
	public static final String SUNO = "1";
	
	public static final String VALORCONDICIONALACTIVO= "Activo";
	
	public static final Integer MOVIL = 2;
    public static final Integer INTERNET = 4;
    public static final Integer CLAROTV = 8;
    public static final Integer TELEFONIA = 16;
    public static final Integer BAM = 32;
    public static final Integer TRIPLEPLAY = 28;
    
    public static final String ACTIVO = "ACTIVO";
    
    public static final String FORMATOLISTA2TELEFONO= "{0}|{1}";
    public static final String FORMATOLISTAMONTOFACTURADO = "{0}|{1};{2}";
    public static final String FORMATOLISTAMONTONOFACTURADO = "{0}|{1};{2}";
    
    public static final String TIPOCLIENTEBD = "BD";
	public static final String TIPOOPERACIONSP = "SP";
	
	public static final String EXCEPTIONTIMEOUT = "SQLTimeoutException";
	
	public static final Long MIL = (long) 1000;
	
	public static final String S = "S";
    public static final String N = "N";
    
    public static final int INT_MES_TREINTA = 30;
    public static final int INT_MES_TREINTA_Y_UNO = 31;
    
    public static final String CANTSRV = "CANT_SRV";
    
    public static final String TRANSACCIONVENTAENCUOTAS = "VENTA EN CUOTAS";    
    
    public static final String CONSTCODUMBRALDEUDA = "85";
    public static final String PORCENTAJEDEUDA = "26";
    
    public static final String TIPODOCUMENTODNISEGMENTOCLIENTE = "01";
    public static final String DESTIPODOCUMENTODNISEGMENTOCLIENTE = "D";
    
    public static final String LLENARPORLADODERECHOSEGMENTOCLIENTE="XXXXXXXXXXXXX";
    public static final String IPAPLICACION="127.0.0.1";
    public static final String USRAPLICACION="USRAPK";
    public static final String APLICACION="APK";
    public static final String SISTEMA="APPVENTAS";
    public static final String VERSION="1.0";    
    
    public static final String PLAZO_ACUERDO="SIN PLAZO/POR TIEMPO ILIMITADO";
    public static final String TOP_CONSUMO="TOPE DE CONSUMO AUTOMATICO";    
    
    public static final String RENOVACION="RENOVACION";
    public static final String ALTARENOVACION="ALTA-RENOVACION";    
    public static final String REQUIEREEVALUACION="REQUIERE EVALUACION CREDITICIA";
    public static final String APROBADO="APROBADO";
    
    
  //USUARIO EBS CREDITO RENOVACION
    public static final String CONSTANTE_NUMERO_OPERACION ="NUMEROOPERACION";
    public static final String CONSTANTE_CODIGO_MODELO ="CODIGOMODELO";
    public static final String CONSTANTE_REGS_CALIFICACION ="RegsCalificacion";
    public static final String CONSTANTE_ACCION ="ACCION";
    public static final String CONSTANTE_DIRECCIONES ="DIRECCIONES";
    public static final String CONSTANTE_LIMITE_CREDITO ="LIMITE_DE_CREDITO";
    public static final String CONSTANTE_CLASE_CLIENTE ="CLASE_DE_CLIENTE";
    public static final String CONSTANTE_LC_DISPONIBLE ="LC_DISPONIBLE";
    public static final String CONSTANTE_NV_LC_MAXIMO ="NV_LC_MAXIMO";
    public static final String CONSTANTE_NV_TOTAL_CARGOS_FIJOS ="NV_TOTAL_CARGOS_FIJOS";
    public static final String CONSTANTE_EXPLICACION ="EXPLICACION";
    public static final String CONSTANTE_SCORE =" SCORE=";
    public static final String CONSTANTE_CREDIT_SCORE ="CREDIT_SCORE";
    public static final String CONSTANTE_TIPO_DE_PRODUCTO ="TIPO_DE_PRODUCTO";
    public static final String CONSTANTE_LINEA_CREDITO_SISTEMA ="LINEA_DE_CREDITO_EN_EL_SISTEMA";
    public static final String CONSTANTE_EDAD =" EDAD=";
    public static final String CONSTANTE_INGRESO_O_LC ="INGRESO_O_LC";
    public static final String CONSTANTE_TIPO_CLIENTE ="TIPO_DE_CLIENTE";
    public static final String CONSTANTE_TIPO_TARJETA ="TIPO_DE_TARJETA";
    public static final String CONSTANTE_TOP_10000 ="TOP_10000";
    public static final String CONSTANTE_ANTIGUEDAD_LABORAL ="ANTIGUEDAD_LABORAL";
    public static final String CONSTANTE_NUMERO_DOCUMENTO ="NUMERO_DOCUMENTO";
    public static final String CONSTANTE_APELLIDO_PATERNO ="APELLIDO_PATERNO";
    public static final String CONSTANTE_APELLIDO_MATERNO ="APELLIDO_MATERNO";
    public static final String CONSTANTE_NOMBRES ="NOMBRES";
    public static final String CONSTANTE_RAZONES ="RAZONES";
    public static final String CONSTANTE_ANALISIS ="ANALISIS";
    public static final String CONSTANTE_SCORE_RANKIN_OPERATIVO ="SCORE_RANKIN_OPERATIVO";
    public static final String CONSTANTE_NUMERO_ENTIDADES_RANKIN_OPERATIVO ="NUMERO_ENTIDADES_RANKIN_OPERATIVO";
    public static final String CONSTANTE_PUNTAJE ="PUNTAJE";
    public static final String CONSTANTE_LIMITE_CREDITO_DATA_CREDITO ="limiteCreditoDataCredito";
    public static final String CONSTANTE_LIMITE_CREDITO_BASE_EXTERNA ="limiteCreditoBaseExterna";
    public static final String CONSTANTE_LIMITE_CREDITO_CLARO ="limiteCreditoClaro";
    public static final String CONSTANTE_FECHA_NACIMIENTO ="fechaNacimiento";
    public static final String CONSTANTE_RESPUESTA ="respuesta";
    public static final String CONSTANTE_FECHA_CONSULTA ="fechaConsulta";
    public static final String CONSTANTE_FIN_TAG ="/>";
    
    public static final String CONSTANTE_COMILLA = "\"";
    public static final String CONSTANTE_X = "X";
}
