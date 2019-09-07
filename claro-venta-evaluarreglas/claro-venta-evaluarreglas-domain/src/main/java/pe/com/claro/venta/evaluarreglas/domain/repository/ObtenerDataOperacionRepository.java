package pe.com.claro.venta.evaluarreglas.domain.repository;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.dialect.OracleTypesHelper;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.domain.repository.AbstractRepository;
import pe.com.claro.common.exception.DBException;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.util.JAXBUtilitarios;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.evaluarreglas.canonical.request.webservice.parametro.ParametroRequest;
import pe.com.claro.venta.evaluarreglas.domain.util.Utilitarios;
import pe.com.claro.venta.evaluarreglas.model.BRMSBioMovilRequest;
import pe.com.claro.venta.evaluarreglas.model.BRMSBioMovilResponse;
import pe.com.claro.venta.evaluarreglas.model.ClienteEvalRequest;
import pe.com.claro.venta.evaluarreglas.model.ClienteEvalResponse;
import pe.com.claro.venta.evaluarreglas.model.DatosResponse;
import pe.com.claro.venta.evaluarreglas.model.DetalleResponse;
import pe.com.claro.venta.evaluarreglas.model.ParametroPvuPlsqlResponse;
import pe.com.claro.venta.evaluarreglas.model.ParametrosPvuPlsqlResponse;
import pe.com.claro.venta.evaluarreglas.model.Pvu3P6DetalleLineaCurCABBean;
import pe.com.claro.venta.evaluarreglas.model.Pvu3P6DetalleLineaCurDETBean;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ConDetalleLineaBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ConDetalleLineaBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ParamGeneralBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.Pvu3Play6ParamGeneralBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuCalculoLCxProductoBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuCalculoLCxProductoBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuCalculoLCxProductoCurBean;
import pe.com.claro.venta.evaluarreglas.model.PvuConTipoCuotaResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuConsultaCVEPendRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuConsultaCVEPendResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuCuotaIniComercialRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuCuotaIniComercialResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuFacturaXProductoBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuFacturaXProductoBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuFacturaXProductoCurBean;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanBilleteraBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanBilleteraBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanBilleteraCurBean;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanXProductoBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanXProductoBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.PvuPlanXProductoCurBean;

@Stateful
public class ObtenerDataOperacionRepository extends AbstractRepository<Object> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ObtenerDataOperacionRepository.class);
	private static final String CALL = "call ";
	private static final String SEGUNDOS = " Segundo(s)";
	private static final String TIEMPO_CONEXION = "{} Tiempo de Timeout de Conexion: ";
	private static final String ERROR_DISPONIBILIDAD = "{} Error de Disponibilidad en BD: ";
	private static final String PARAMETRO = "{}";
	private static final String TIEMPO_PROCESO = "{} Tiempo total de proceso(ms):{} ";
	
	Utilitarios utilImpl = new Utilitarios();
	
	@PersistenceContext(unitName = "pe.com.claro.venta.data.source.pvu", type = PersistenceContextType.EXTENDED)
	public void setPersistenceUnit00(final EntityManager em) {
		LOG.info("entre al constructor");
	    this.entityManager=em;
	}
	@Override
	protected Predicate[] getSearchPredicates(Root<Object> root, Object example) {
		return new Predicate[0];
	}
		
	public ClienteEvalResponse getDataValidaClienteReglasV2(PropertiesExternos propertiesExternos, ClienteEvalRequest dataRequest, String mensajeLog ) throws DBException {
		long tiempoInicio = System.currentTimeMillis();
		ClienteEvalResponse response = new ClienteEvalResponse();
		
		String codResp = Constantes.CONSTANTE_VACIA;
		String msjResp = Constantes.CONSTANTE_VACIA;
		String nombreBD = propertiesExternos.pvuOwner;
		StringBuilder storedProcedure= new StringBuilder();
		try {
			LOG.info(mensajeLog+ " [INICIO de metodo: " + Constantes.NOMBRE_METODO_1 + "]"+Constantes.REPOSITORY);
			String strOwnerPvu = propertiesExternos.pvuOwner;
			String strPkgBiomovil = propertiesExternos.pkgBiomovil;			
			if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
				storedProcedure.append(strOwnerPvu);
				storedProcedure.append(Constantes.PUNTO);
			}
			storedProcedure.append(strPkgBiomovil);
			Session session = entityManager.unwrap( Session.class );
			session.doWork(new Work() {
					
				@Override
				public void execute(final Connection connection) throws SQLException {
					LOG.info(mensajeLog+ PARAMETRO+ " Ejecutando SP : " + storedProcedure);
					LOG.info(mensajeLog+ PARAMETRO+ " PARAMETROS [INPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(dataRequest));
					ResultSet rsDetalleResponse = null;
					ResultSet rsDatosResponse = null;					
					try (CallableStatement call = connection.prepareCall(CALL + storedProcedure.toString() +" (?,?,?,?,?,?,?,?,?,?,?)" )){
						
						call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
						LOG.info(mensajeLog+ TIEMPO_CONEXION + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);
						call.setInt(Constantes.UNO, dataRequest.getPiBuroCod());
						call.setString(Constantes.DOS, dataRequest.getPiTipoDocCod());
						call.setString(Constantes.TRES, dataRequest.getPiNumDoc());
						call.setString(Constantes.CUATRO, dataRequest.getPiOficina());
						call.setString(Constantes.CINCO, dataRequest.getPiNroOperacion());
						call.setString(Constantes.SEIS, dataRequest.getPiLineasActivas());
						call.setString(Constantes.SIETE, dataRequest.getPiLineaEvaluada());					
						call.registerOutParameter(Constantes.OCHO, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());
						call.registerOutParameter(Constantes.NUEVE, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());
						call.registerOutParameter(Constantes.DIEZ, java.sql.Types.FLOAT);
						call.registerOutParameter(Constantes.ONCE, java.sql.Types.VARCHAR);
										
						long tiempoInicioSP = System.currentTimeMillis();
						call.execute();
						LOG.info(mensajeLog+ PARAMETRO+ " Se invocó con éxito el SP:  "+ storedProcedure);
						rsDetalleResponse = (ResultSet) call.getObject(Constantes.OCHO);
						rsDatosResponse = (ResultSet) call.getObject(Constantes.NUEVE);
						List<DetalleResponse> listDetalleResponse = new ArrayList<>();
						List<DatosResponse> listDatosResponse = new ArrayList<>();
							while (rsDetalleResponse.next()) {
								DetalleResponse detalleResponse = new DetalleResponse();
								detalleResponse.setCuenta(rsDetalleResponse.getString("CUENTA"));
								detalleResponse.setPlan(rsDetalleResponse.getString("PLAN"));
								detalleResponse.setPlanSisAct(rsDetalleResponse.getString("PLAN_SISACT"));
								detalleResponse.setTelefono(rsDetalleResponse.getString("TELEFONO"));
								detalleResponse.setServicio(rsDetalleResponse.getString("SERVICIO"));
								detalleResponse.setCargoFijo(rsDetalleResponse.getBigDecimal("CARGO_FIJO"));
								detalleResponse.setSolinSumCarCon(rsDetalleResponse.getBigDecimal("SOLIN_SUM_CAR_CON"));
								detalleResponse.setFechaActivacion(rsDetalleResponse.getString("FECHA_ACTIVACION"));
								detalleResponse.setFechaEstado(rsDetalleResponse.getString("FECHA_ESTADO"));
								detalleResponse.setEstado(rsDetalleResponse.getString("ESTADO"));
								detalleResponse.setMotivoBloqueo(rsDetalleResponse.getString("MOTIVO_BLOQUEO"));
								detalleResponse.setMotivoSuspension(rsDetalleResponse.getString("MOTIVO_SUSPENSION"));
								detalleResponse.setCampana(rsDetalleResponse.getString("CAMPANA"));
								detalleResponse.setPlanBSCS(rsDetalleResponse.getBigDecimal("PLAN_BSCS"));
								listDetalleResponse.add(detalleResponse);
							}										
							while (rsDatosResponse.next()) {
								DatosResponse datosResponse = new DatosResponse();
								datosResponse.setCodigo(rsDatosResponse.getString("CODIGO"));
								datosResponse.setPdv(rsDatosResponse.getString("PDV"));
								datosResponse.setCanal(rsDatosResponse.getString("CANAL"));
								datosResponse.setDepartamento(rsDatosResponse.getString("DEPARTAMENTO"));
								datosResponse.setProvincia(rsDatosResponse.getString("PROVINCIA"));
								datosResponse.setDistrito(rsDatosResponse.getString("DISTRITO"));
								datosResponse.setRegion(rsDatosResponse.getString("REGION"));
								datosResponse.setCalidadVendedor(rsDatosResponse.getString("CALIDAD_VENDEDOR"));
								datosResponse.setBuroDes(rsDatosResponse.getString("BURO_DES"));
								datosResponse.setFactorSubMenor(rsDatosResponse.getBigDecimal("FACTOR_SUB_MENOR"));
								datosResponse.setFactorSubMayor(rsDatosResponse.getBigDecimal("FACTOR_SUB_MAYOR"));
								datosResponse.setSopodFecActivacion(rsDatosResponse.getDate("SOPOD_FEC_ACTIVACION"));
								datosResponse.setNumeroOperacion(rsDatosResponse.getBigDecimal("NUMERO_OPERACION"));
								datosResponse.setRiesgo(rsDatosResponse.getString("RIESGO"));
								datosResponse.setPuntaje(rsDatosResponse.getString("PUNTAJE"));
								datosResponse.setEdad(rsDatosResponse.getBigDecimal("EDAD"));
								listDatosResponse.add(datosResponse);
							}
						response.setListDatosRequest(listDatosResponse);
						response.setListDetalleRequest(listDetalleResponse);
						response.setPoCodigoRespuesta(call.getString(Constantes.DIEZ)!= null ? call.getString(Constantes.DIEZ) : Constantes.VACIO);
						response.setPoMensajeRespuesta(call.getString(Constantes.ONCE)!= null ? call.getString(Constantes.ONCE) : Constantes.VACIO);					
						LOG.info(mensajeLog+ PARAMETRO+" Tiempo total de proceso del llamado del SP"+ storedProcedure + " (ms): "+ (System.currentTimeMillis() - tiempoInicioSP));
						LOG.info(mensajeLog+ PARAMETRO+" PARAMETROS [OUTPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(response));						
					}catch (SQLException e) {
						LOG.info(mensajeLog+ PARAMETRO + " Error Catch Stored Procedure: \n {}", e.getMessage(), e);
					} finally {
						if(rsDatosResponse!= null) {
							rsDatosResponse.close();
						}
						if(rsDetalleResponse!= null) {
							rsDetalleResponse.close();
						}						
						if(connection!= null) {
							LOG.info(mensajeLog+ PARAMETRO+ " Cerrando la conexion del metodo: " + Constantes.NOMBRE_METODO_1);							
							connection.close();
						}						
					}					
				}
			});
		} catch (Exception e2) {
				if (e2.toString().contains(Constantes.EXCEPTIONTIMEOUT)) {
				LOG.error(mensajeLog+ PARAMETRO+ " Error de Timeout en BD: " + nombreBD, e2);

				codResp = propertiesExternos.idt2Codigo;
				msjResp = propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.pvuOwner + propertiesExternos.pkgBiomovil);
				throw new DBException(codResp, msjResp, e2);
			} else {
				codResp = propertiesExternos.idt1Codigo;
				msjResp = propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.pvuOwner + propertiesExternos.pkgBiomovil);
				throw new DBException(codResp, msjResp, e2);
			}		
		}
		finally{
			LOG.info(mensajeLog+ TIEMPO_PROCESO, System.currentTimeMillis() - tiempoInicio);
			LOG.info(mensajeLog+ " [Fin de metodo: "+ Constantes.NOMBRE_METODO_1+ "]");
		}
		return response;
	}
		
	
	public BRMSBioMovilResponse insertBRMSBioMovilV2(PropertiesExternos propertiesExternos, BRMSBioMovilRequest dataRequest, String idTx, String mensajeLog ) throws DBException {		
		BRMSBioMovilResponse response = new BRMSBioMovilResponse();
		long tiempoInicio = System.currentTimeMillis();
		String codResp = Constantes.CONSTANTE_VACIA;
		String msjResp = Constantes.CONSTANTE_VACIA;
		String nombreBD = propertiesExternos.pvuOwner;
		
		StringBuilder storedProcedure= new StringBuilder();
		try {
			LOG.info(mensajeLog+ " [INICIO de metodo: ",  Constantes.NOMBRE_SUBMETODO_6 + "] ", Constantes.REPOSITORY);
			String strOwnerPvu = propertiesExternos.pvuOwner;
			String strPkgBiomovil = propertiesExternos.pkgBrmsBiomovil;
			if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
				storedProcedure.append(strOwnerPvu);
				storedProcedure.append(Constantes.PUNTO);
			}
			storedProcedure.append(strPkgBiomovil);
			Session session = entityManager.unwrap( Session.class );
			session.doWork(new Work() {
					
				@Override
				public void execute(final Connection connection) throws SQLException {
					LOG.info(mensajeLog+ " Ejecutando SP : "+ storedProcedure);
					LOG.info(mensajeLog+ " PARAMETROS [INPUT]: \n "+ JAXBUtilitarios.anyObjectToXmlText(dataRequest));
					try (CallableStatement call = connection.prepareCall(CALL + storedProcedure.toString() +" " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
							+ "																						?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
							+ "																					    ?,?,?,?,?,?)" )){
						call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
						LOG.info(mensajeLog+ TIEMPO_CONEXION + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);					
							call.setString(Constantes.UNO, idTx);
							call.setString(Constantes.DOS, dataRequest.getBrmsTipOperacion());
							call.setString(Constantes.TRES, dataRequest.getBrmsTipDocumento());
							call.setString(Constantes.CUATRO, dataRequest.getBrmsNroDocumento());
							call.setString(Constantes.CINCO, dataRequest.getBrmsTipServicio());
							call.setBigDecimal(Constantes.SEIS, dataRequest.getCantidadDeAplicacionesRenta());
							call.setBigDecimal(Constantes.SIETE, dataRequest.getCantidadDeLineasAdicionalesRuc());
							call.setBigDecimal(Constantes.OCHO, dataRequest.getCantidadDeLineasMaximas());
							call.setBigDecimal(Constantes.NUEVE, dataRequest.getCantidadDeLineasRenovaciones());
							call.setString(Constantes.DIEZ, dataRequest.getCapacidadDePago());
							call.setBigDecimal(Constantes.ONCE, dataRequest.getComportamientoConsolidado());
							call.setBigDecimal(Constantes.DOCE, dataRequest.getComportamientoDePagoC1());
							call.setString(Constantes.TRECE, dataRequest.getControlDeConsumo());
							call.setBigDecimal(Constantes.CATORCE, dataRequest.getCostoDeInstalacion());
							call.setDouble(Constantes.QUINCE, dataRequest.getCostoTotalEquipos());
							call.setBigDecimal(Constantes.DIECISEIS, dataRequest.getFactorDeEndeudamiento());
							call.setBigDecimal(Constantes.DIECISIETE, dataRequest.getFactorDeRenovacion());
							call.setBigDecimal(Constantes.DIECIOCHO, dataRequest.getFrecuenciaDeAplicacionMensual());
							call.setBigDecimal(Constantes.DIECINUEVE, dataRequest.getLimiteDeCrecitoCobranza());
							call.setBigDecimal(Constantes.VEINTE, dataRequest.getMesInicioRentas());
							call.setBigDecimal(Constantes.VEINTIUNO, dataRequest.getMontoCfParaRuc());
							call.setBigDecimal(Constantes.VEINTIDOS, dataRequest.getMontoDeGarantia());
							call.setBigDecimal(Constantes.VEINTITRES, dataRequest.getMontoTopeAutomatico());
							call.setBigDecimal(Constantes.VEINTICUATRO, dataRequest.getPrecioDeVentaTotalEquipos());
							call.setString(Constantes.VEINTICINCO, dataRequest.getPrioridadPublicar());
							call.setString(Constantes.VEINTISEIS, dataRequest.getExoneracionDeRentas());
							call.setString(Constantes.VEINTISIETE, dataRequest.getIdValidator());
							call.setString(Constantes.VEINTIOCHO, dataRequest.getValidacionInternaClaro());
							call.setString(Constantes.VEINTINUEVE, dataRequest.getPublicar());
							call.setString(Constantes.TREINTA, dataRequest.getRestriccion());
							call.setString(Constantes.TREINTA_Y_UNO, dataRequest.getRiesgoEnClaro());
							call.setString(Constantes.TREINTA_Y_DOS, dataRequest.getRiesgoOferta());
							call.setString(Constantes.TREINTA_Y_TRES, dataRequest.getRiesgoTotalEquipo());
							call.setString(Constantes.TREINTA_Y_CUATRO, dataRequest.getRiesgoTotalReplegales());
							call.setString(Constantes.TREINTA_Y_CINCO, dataRequest.getTipoDeAutonomiaCargoFijo());
							call.setString(Constantes.TREINTA_Y_SEIS, dataRequest.getTipoDeCobro());
							call.setString(Constantes.TREINTA_Y_SIETE, dataRequest.getTipoDeGarantia());
							call.setString(Constantes.TREINTA_Y_OCHO, dataRequest.getMensajeWS());
							call.setBigDecimal(Constantes.TREINTA_Y_NUEVE, dataRequest.getSolinGrupoSEC());
							call.setString(Constantes.CUARENTA, dataRequest.getAutonomiaRenovacion());
							call.setString(Constantes.CUARENTA_Y_UNO, dataRequest.getCantidadDePlanesPorProducto());
							call.setString(Constantes.CUARENTA_Y_DOS, dataRequest.getFacturacionPromedioClaro());
							call.setString(Constantes.CUARENTA_Y_TRES, dataRequest.getFacturacionPromedioProducto());
							call.setString(Constantes.CUARENTA_Y_CUATRO, dataRequest.getTiempoDePermanencia());
							call.setString(Constantes.CUARENTA_Y_CINCO, dataRequest.getSegmento()+Constantes.PALOTE+dataRequest.getCodigoPDV()+Constantes.PALOTE+dataRequest.getDeuda()+Constantes.PALOTE+dataRequest.getComportamientoDePago());
							call.setString(Constantes.CUARENTA_Y_SEIS, dataRequest.getOfertaCasoEspecial());
							call.setString(Constantes.CUARENTA_Y_SIETE, dataRequest.getEquipoCosto());
							call.setString(Constantes.CUARENTA_Y_OCHO, dataRequest.getEquipoCuotas());
							call.setString(Constantes.CUARENTA_Y_NUEVE, dataRequest.getEquipoFormaDePago());
							call.setString(Constantes.CINCUENTA, dataRequest.getEquipoGama());
							call.setString(Constantes.CINCUENTA_Y_UNO, dataRequest.getEquipoModelo());
							call.setString(Constantes.CINCUENTA_Y_DOS, dataRequest.getEquipoMontoDeCuota());
							call.setString(Constantes.CINCUENTA_Y_TRES, dataRequest.getEquipoPorcenCuotaInicial());
							call.setString(Constantes.CINCUENTA_Y_CUATRO, dataRequest.getEquipoPrecioDeVenta());
							call.setString(Constantes.CINCUENTA_Y_CINCO, dataRequest.getEquipoTipoDeDeco());
							call.setString(Constantes.CINCUENTA_Y_SEIS, dataRequest.getEquipoTipoOperacionKit());
							call.setDate(Constantes.CINCUENTA_Y_SIETE, dataRequest.getFechaEjecucion());
							call.setBigDecimal(Constantes.CINCUENTA_Y_OCHO, dataRequest.getCantidadDeLineasActivas());
							call.setString(Constantes.CINCUENTA_Y_NUEVE, dataRequest.getModalidadCedente());
							call.setBigDecimal(Constantes.SESENTA, dataRequest.getMesOperadorCedente());
							call.setBigDecimal(Constantes.SESENTA_Y_UNO, dataRequest.getMontoCFSEC());
							call.setString(Constantes.SESENTA_Y_DOS, dataRequest.getTipoDeOperacion());
							call.setString(Constantes.SESENTA_Y_TRES, dataRequest.getBuroConsultado());
							//Otra nuevas 6 variables
							call.setBigDecimal(Constantes.SESENTA_Y_CUATRO, new BigDecimal(dataRequest.getCantidadMaximaCuotasPendientesSistema()) );
							call.setBigDecimal(Constantes.SESENTA_Y_CINCO, new BigDecimal(dataRequest.getCantidadMaximaCuotasPendientesUltimasVentas()));
							call.setBigDecimal(Constantes.SESENTA_Y_SEIS,  new BigDecimal(dataRequest.getCantidadPlanesCuotasPendientesSistema()));
							call.setBigDecimal(Constantes.SESENTA_Y_SIETE,  new BigDecimal(dataRequest.getCantidadPlanesCuotasPendientesUltimasVentas()));
							call.setBigDecimal(Constantes.SESENTA_Y_OCHO,  BigDecimal.valueOf(dataRequest.getMontoPendienteCuotasSistema().doubleValue()));
							call.setBigDecimal(Constantes.SESENTA_Y_NUEVE,  BigDecimal.valueOf(dataRequest.getMontoPendienteCuotasUltimasVentas().doubleValue()));
														
							call.registerOutParameter(Constantes.SETENTA, java.sql.Types.NUMERIC);
							call.registerOutParameter(Constantes.SETENTA_Y_UNO, java.sql.Types.VARCHAR);									
							long tiempoInicioSP = System.currentTimeMillis();
							call.execute();					
							LOG.info( mensajeLog+ " Se invocó con éxito el SP: "+ storedProcedure);
							response.setPoCodigoRespuesta(call.getInt(Constantes.SETENTA));
							response.setPoMensajeRespuesta(call.getString(Constantes.SETENTA_Y_UNO)!= null ? call.getString(Constantes.SETENTA_Y_UNO) : Constantes.VACIO);
							LOG.info(mensajeLog+ PARAMETRO+" Tiempo total de proceso del llamado del SP"+ storedProcedure + " (ms): "+ (System.currentTimeMillis() - tiempoInicioSP));
							LOG.info(mensajeLog+ " PARAMETROS [OUTPUT]: \n {}", JAXBUtilitarios.anyObjectToXmlText(response));
					}catch (SQLException e) {
						LOG.info(mensajeLog+ " Error Catch Stored Procedure: \n {} {}", e.getMessage(), e);
					} finally {						
						if(connection!= null) {
							LOG.info(mensajeLog+ " Cerrando la conexion del metodo: {} ", Constantes.NOMBRE_SUBMETODO_6);							
							connection.close();
						}						
					}
				}
			});
		} catch (Exception e2) {
				if (e2.toString().contains(Constantes.EXCEPTIONTIMEOUT)) {
				codResp = propertiesExternos.idt2Codigo;
				msjResp = propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.pvuOwner + propertiesExternos.pkgBrmsBiomovil);
				throw new DBException(codResp, msjResp, e2);
			} else {
				codResp = propertiesExternos.idt1Codigo;
				msjResp = propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.pvuOwner + propertiesExternos.pkgBrmsBiomovil);
				throw new DBException(codResp, msjResp, e2);
			}
		}
		finally{
			LOG.info(mensajeLog+ TIEMPO_PROCESO+ (System.currentTimeMillis() - tiempoInicio));
			LOG.info(mensajeLog+ " [Fin de metodo: {}", Constantes.NOMBRE_SUBMETODO_6, "]");
			
		}
		return response;
	}
	
	public List<Pvu3Play6ParamGeneralBeanResponse> obtenerParametroGeneralPVU(PropertiesExternos propertiesExternos, Pvu3Play6ParamGeneralBeanRequest dataRequest, String mensajeLog) throws DBException {
		long tiempoInicio = System.currentTimeMillis();
		List<Pvu3Play6ParamGeneralBeanResponse> listParamGeneralResponse = new ArrayList<>();		
		String codResp = Constantes.CONSTANTE_VACIA;
		String msjResp = Constantes.CONSTANTE_VACIA;
		String nombreBD = propertiesExternos.pvuOwner;		
		StringBuilder storedProcedure= new StringBuilder();
		try {
			LOG.info(mensajeLog+ " req.getP_codigo obtenerParametroGeneralPVU: {} ", dataRequest.getP_codigo());
			LOG.info(mensajeLog+ " [INICIO de metodo: "+ Constantes.NOMBRE_SUBMETODO_7+ "] {}",Constantes.REPOSITORY);
			String strOwnerPvu = propertiesExternos.pvuOwner;
			String strPkgGeneral3Play = propertiesExternos.pkgGenral3Play;			
			if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
				storedProcedure.append(strOwnerPvu);
				storedProcedure.append(Constantes.PUNTO);
			}
			storedProcedure.append(strPkgGeneral3Play);
			Session session = entityManager.unwrap( Session.class );
			session.doWork(new Work() {
					
				@Override
				public void execute(final Connection connection) throws SQLException {
					LOG.info(mensajeLog+ "{} Ejecutando SP :  "+ storedProcedure);
					LOG.info(mensajeLog+ "{} PARAMETROS [INPUT]: \n "+ JAXBUtilitarios.anyObjectToXmlText(dataRequest));
					ResultSet rsParamGeneral = null;
					try (CallableStatement call = connection.prepareCall(CALL + storedProcedure.toString() +" (?,?)" )){
						call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
						LOG.info(mensajeLog+ TIEMPO_CONEXION + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);	
						call.setString(Constantes.UNO, dataRequest.getP_codigo());
						call.registerOutParameter(Constantes.DOS, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());									
						long tiempoInicioSP = System.currentTimeMillis();
						call.execute();					
						LOG.info(mensajeLog+ "{} Se invocó con éxito el SP:  {} ", storedProcedure);
						rsParamGeneral = (ResultSet) call.getObject(Constantes.DOS);
							while (rsParamGeneral.next()) {
								Pvu3Play6ParamGeneralBeanResponse paramGeneralBean = new Pvu3Play6ParamGeneralBeanResponse();
								paramGeneralBean.setpConiCodigo(rsParamGeneral.getString("PCONI_CODIGO"));
								paramGeneralBean.setpConvDescripcion(rsParamGeneral.getString("PCONV_DESCRIPCION"));
								paramGeneralBean.setpConvValor(rsParamGeneral.getString("PCONV_VALOR"));						
								listParamGeneralResponse.add(paramGeneralBean);
							}
							LOG.info(mensajeLog+ PARAMETRO+" Tiempo total de proceso del llamado del SP"+ storedProcedure + " (ms): "+ (System.currentTimeMillis() - tiempoInicioSP));
//						LOG.info(mensajeLog+ "{} PARAMETROS [OUTPUT]: \n {} ", JAXBUtilitarios.anyObjectToXmlText(listParamGeneralResponse));
					}catch (SQLException e) {
						LOG.info(mensajeLog+ "{} Error Catch Stored Procedure: \n{}", e.getMessage(), e);						
					} finally {
						if(rsParamGeneral != null) {
							LOG.info(mensajeLog+ "{} Cerrando cursor: rsParamGeneral");										
							rsParamGeneral.close();
						}						
						if(connection!= null) {
							LOG.info(mensajeLog+ "{} Cerrando la conexion del metodo: " + Constantes.NOMBRE_SUBMETODO_6);							
							connection.close();
						}
						
					}
					}
			});
		} catch (Exception e2) {
			if (e2.toString().contains(Constantes.EXCEPTIONTIMEOUT)) {

				LOG.error(mensajeLog+ "{} Error de Timeout en BD: {}" + nombreBD, e2);

				codResp = propertiesExternos.idt2Codigo;
				msjResp = propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.pvuOwner+"."+ propertiesExternos.pkgGenral3Play);
				throw new DBException(codResp, msjResp, e2);

			} else {

				LOG.error(mensajeLog+ ERROR_DISPONIBILIDAD + nombreBD, e2);
				codResp = propertiesExternos.idt1Codigo;
				msjResp = propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.pvuOwner + "." + propertiesExternos.pkgGenral3Play);
				throw new DBException(codResp, msjResp, e2);
			}
		}
		finally{
			LOG.info(mensajeLog+ TIEMPO_PROCESO+ (System.currentTimeMillis() - tiempoInicio));
			LOG.info(mensajeLog+ " [Fin de metodo: "+ Constantes.NOMBRE_SUBMETODO_7+ "]");
		}
		return listParamGeneralResponse;
	}
		
	public PvuFacturaXProductoBeanResponse obtenerMontoFactxProductoPVU(PropertiesExternos propertiesExternos, PvuFacturaXProductoBeanRequest dataRequest, String mensajeLog)throws DBException{
		
		long tiempoInicio = System.currentTimeMillis();
		PvuFacturaXProductoBeanResponse pvuFacturaXProductoBeanResponse = new PvuFacturaXProductoBeanResponse();
		List<PvuFacturaXProductoCurBean> listPvuFacturaXProductoCurBean = new ArrayList<>();		
				
		String codResp = Constantes.CONSTANTE_VACIA;
	    String msjResp = Constantes.CONSTANTE_VACIA;
	    
	    String nombreBD = propertiesExternos.pvuOwner;	    
		StringBuilder storedProcedure= new StringBuilder();
		
		try {
			LOG.info(mensajeLog+ " req.getP_codigo obtenerParametroGeneralPVU "+dataRequest.getP_lista_planes());
			LOG.info(mensajeLog+ " [INICIO de metodo: " + Constantes.NOMBRE_SUBMETODO_8 + "]"+Constantes.REPOSITORY);
			String strOwnerPvu = propertiesExternos.pvuOwner;
			String strPkgGeneral3Play = propertiesExternos.pkgEvaluacionUni;			
			if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
				storedProcedure.append(strOwnerPvu);
				storedProcedure.append(Constantes.PUNTO);
			}
			storedProcedure.append(strPkgGeneral3Play);
			Session session = entityManager.unwrap( Session.class );
			session.doWork(new Work() {
					
				@Override
				public void execute(final Connection connection) throws SQLException {
					LOG.info(mensajeLog+ "{} Ejecutando SP : " + storedProcedure);
					LOG.info(mensajeLog+ "{} PARAMETROS [INPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(dataRequest));
					ResultSet rsParamGeneral = null;					
					try (CallableStatement call = connection.prepareCall(CALL + storedProcedure.toString() +" (?,?)" )){						
						call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
						LOG.info(mensajeLog+ TIEMPO_CONEXION + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);	
						call.setString(Constantes.UNO, dataRequest.getP_lista_planes());
						call.registerOutParameter(Constantes.DOS, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());									
						long tiempoInicioSP = System.currentTimeMillis();
						call.execute();					
						LOG.info(mensajeLog+ " Se invocó con éxito el SP:  "+ storedProcedure);
						rsParamGeneral = (ResultSet) call.getObject(Constantes.DOS);
							while (rsParamGeneral.next()) {							
								PvuFacturaXProductoCurBean paramGeneralBean = new PvuFacturaXProductoCurBean();
								paramGeneralBean.setCodigo(rsParamGeneral.getInt("CODIGO"));
								paramGeneralBean.setDescripcion(rsParamGeneral.getString("DESCRIPCION"));
								paramGeneralBean.setValor(rsParamGeneral.getString("VALOR"));						
								listPvuFacturaXProductoCurBean.add(paramGeneralBean);
							}
							pvuFacturaXProductoBeanResponse.setC_producto_fact(listPvuFacturaXProductoCurBean);					
							LOG.info(mensajeLog+ PARAMETRO+" Tiempo total de proceso del llamado del SP"+ storedProcedure + " (ms): "+ (System.currentTimeMillis() - tiempoInicioSP));
						LOG.info(mensajeLog+ "{} PARAMETROS [OUTPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(pvuFacturaXProductoBeanResponse));
						}catch (SQLException e) {
							LOG.info(mensajeLog+ "{} Error Catch Stored Procedure: \n{}", e.getMessage(), e);
						} finally {
							if(rsParamGeneral != null) {
								LOG.info(mensajeLog+ "{} Cerrando cursor: rsParamGeneral");										
								rsParamGeneral.close();
							}							
							if(connection!= null) {
								LOG.info(mensajeLog+ " Cerrando la conexion del metodo: " + Constantes.NOMBRE_SUBMETODO_6);							
								connection.close();
							}							
						}
					}
			});
		}catch (Exception  ex) {
				
			if ( ex.toString().contains( Constantes.EXCEPTIONTIMEOUT ) ) {	
				
				LOG.error(mensajeLog, "{} Error de Timeout en BD: "+ nombreBD, ex);
				
	            codResp=propertiesExternos.idt2Codigo;
	            msjResp=propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
											  .replace(Constantes.REPLACERECURSO, nombreBD )
											  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
											  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pkgEvaluacionUni);
				throw new DBException(codResp,msjResp,ex);
				
			}else{
				
				LOG.error(mensajeLog, ERROR_DISPONIBILIDAD+ nombreBD, ex);
	            codResp=propertiesExternos.idt1Codigo;
	            msjResp=propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
	            									  .replace(Constantes.REPLACERECURSO, nombreBD )
	            									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
	            									  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pkgEvaluacionUni);		
	            throw new DBException(codResp,msjResp,ex);
			}
		}finally {
			LOG.info(mensajeLog+ TIEMPO_PROCESO+ (System.currentTimeMillis() - tiempoInicio));
			LOG.info(mensajeLog+ " [Fin de metodo: " + Constantes.NOMBRE_SUBMETODO_8 + "]");
		}
		return pvuFacturaXProductoBeanResponse;
	}
	
	
public PvuPlanXProductoBeanResponse obtenerPlanXProductoPVU(PropertiesExternos propertiesExternos, PvuPlanXProductoBeanRequest dataRequest, String mensajeLog)throws DBException{
		
		long tiempoInicio = System.currentTimeMillis();
		PvuPlanXProductoBeanResponse pvuPlanXProductoBeanResponse = new PvuPlanXProductoBeanResponse();
		List<PvuPlanXProductoCurBean> listPvuPlanXProductoCurBean = new ArrayList<>();						
		String codResp = Constantes.CONSTANTE_VACIA;
	    String msjResp = Constantes.CONSTANTE_VACIA;	    
	    String nombreBD = propertiesExternos.pvuOwner;	    
		StringBuilder storedProcedure= new StringBuilder();
		
		try {
			LOG.info(mensajeLog+" req.getP_codigo obtenerParametroGeneralPVU {}", dataRequest.getP_planes());
			LOG.info(mensajeLog+" [INICIO de metodo: {}"+ Constantes.NOMBRE_SUBMETODO_9 + "]"+Constantes.REPOSITORY);
			String strOwnerPvu = propertiesExternos.pvuOwner;
			String strPkgGeneral3Play = propertiesExternos.pkgSPplanXProducto;			
			if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
				storedProcedure.append(strOwnerPvu);
				storedProcedure.append(Constantes.PUNTO);
			}
			storedProcedure.append(strPkgGeneral3Play);
			Session session = entityManager.unwrap( Session.class );
			session.doWork(new Work() {
					
				@Override
				public void execute(final Connection connection) throws SQLException {
					LOG.info(mensajeLog +" Ejecutando SP : {}", storedProcedure);
					LOG.info(mensajeLog +" PARAMETROS [INPUT]: \n {}", JAXBUtilitarios.anyObjectToXmlText(dataRequest));
					ResultSet rsParamGeneral = null;					
					try (CallableStatement call = connection.prepareCall(CALL + storedProcedure.toString() +" (?,?)" )){						
						call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
						LOG.info(mensajeLog + TIEMPO_CONEXION + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);	
						call.setString(Constantes.UNO, dataRequest.getP_planes());
						call.registerOutParameter(Constantes.DOS, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());									
						long tiempoInicioSP = System.currentTimeMillis();
						call.execute();					
						LOG.info(mensajeLog + " Se invocó con éxito el SP:  "+ storedProcedure);
						rsParamGeneral = (ResultSet) call.getObject(Constantes.DOS);
							while (rsParamGeneral.next()) {							
								PvuPlanXProductoCurBean paramGeneralBean = new PvuPlanXProductoCurBean();
								paramGeneralBean.setPlan(rsParamGeneral.getString("PLAN"));
								paramGeneralBean.setProducto(rsParamGeneral.getString("PRODUCTO"));
								paramGeneralBean.setDescripcion(rsParamGeneral.getString("DESCRIPCION"));						
								listPvuPlanXProductoCurBean.add(paramGeneralBean);
							}
							pvuPlanXProductoBeanResponse.setP_cursor(listPvuPlanXProductoCurBean);					
							LOG.info(mensajeLog+ PARAMETRO+" Tiempo total de proceso del llamado del SP"+ storedProcedure + " (ms): "+ (System.currentTimeMillis() - tiempoInicioSP));
						LOG.info(mensajeLog+ "  PARAMETROS [OUTPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(pvuPlanXProductoBeanResponse));
					}catch (SQLException e) {
						LOG.info(mensajeLog+ " Error Catch Stored Procedure: \n{}", e.getMessage(), e);
					} finally {
						if(rsParamGeneral != null) {
							LOG.info(mensajeLog+ " Cerrando cursor: rsParamGeneral");										
							rsParamGeneral.close();
						}
						if(connection!= null) {
							LOG.info(mensajeLog+ " Cerrando la conexion del metodo: " + Constantes.NOMBRE_SUBMETODO_6);							
							connection.close();
						}
						
					}
				}
			});
		}catch (Exception  ex) {
				
			if ( ex.toString().contains( Constantes.EXCEPTIONTIMEOUT ) ) {	
				
				LOG.error(mensajeLog+ " Error de Timeout en BD: "+ nombreBD, ex);
				
	            codResp=propertiesExternos.idt2Codigo;
	            msjResp=propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
											  .replace(Constantes.REPLACERECURSO, nombreBD )
											  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
											  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pkgSPplanXProducto);
				throw new DBException(codResp,msjResp,ex);
				
			}else{
				
				LOG.error(mensajeLog+ ERROR_DISPONIBILIDAD+ nombreBD, ex);
	            codResp=propertiesExternos.idt1Codigo;
	            msjResp=propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
	            									  .replace(Constantes.REPLACERECURSO, nombreBD )
	            									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
	            									  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pkgSPplanXProducto);		
	            throw new DBException(codResp,msjResp,ex);
			}
		}finally {
			LOG.info(mensajeLog+ TIEMPO_PROCESO+ (System.currentTimeMillis() - tiempoInicio));
			LOG.info(mensajeLog+ " [Fin de metodo: " + Constantes.NOMBRE_SUBMETODO_9 + "]");
		}
		return pvuPlanXProductoBeanResponse;
	}

public Pvu3Play6ConDetalleLineaBeanResponse obtenerDetalleLinea3play6PVU(PropertiesExternos propertiesExternos, Pvu3Play6ConDetalleLineaBeanRequest dataRequest, String mensajeLog)throws DBException{
	
	long tiempoInicio = System.currentTimeMillis();
	Pvu3Play6ConDetalleLineaBeanResponse pvu3Play6ConDetalleLineaBeanResponse = new Pvu3Play6ConDetalleLineaBeanResponse();	
	List<Pvu3P6DetalleLineaCurCABBean> listPvu3P6DetalleLineaCurCABBean = new ArrayList<>();			
	List<Pvu3P6DetalleLineaCurDETBean> listPvu3P6DetalleLineaCurDETBean = new ArrayList<>();					
	String codResp = Constantes.CONSTANTE_VACIA;
    String msjResp = Constantes.CONSTANTE_VACIA;    
    String nombreBD = propertiesExternos.pvuOwner;	    
	StringBuilder storedProcedure= new StringBuilder();
	
	try {
		LOG.info(mensajeLog+ "{} req.getP_codigo obtenerParametroGeneralPVU "+dataRequest.getP_cliec_num_doc());
		LOG.info(mensajeLog+ "{} [INICIO de metodo: " + Constantes.NOMBRE_SUBMETODO_10 + "]"+Constantes.REPOSITORY);
		String strOwnerPvu = propertiesExternos.pvuOwner;
		String strPkgGeneral3Play = propertiesExternos.pkgSPconDetalleLinea;			
		if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
			storedProcedure.append(strOwnerPvu);
			storedProcedure.append(Constantes.PUNTO);
		}
		storedProcedure.append(strPkgGeneral3Play);
		Session session = entityManager.unwrap( Session.class );
		session.doWork(new Work() {
				
			@Override
			public void execute(final Connection connection) throws SQLException {
				LOG.info(mensajeLog+ " Ejecutando SP : " + storedProcedure);
				LOG.info(mensajeLog+ " PARAMETROS [INPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(dataRequest));
				ResultSet rsParamGeneral = null;
				ResultSet rsDetalle = null;				
				try (CallableStatement call = connection.prepareCall(CALL + storedProcedure.toString() +" (?,?,?,?,?)" )){					
					call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
					LOG.info(mensajeLog+ TIEMPO_CONEXION + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);
					
					call.registerOutParameter(Constantes.UNO, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());	
					call.registerOutParameter(Constantes.DOS, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());					
					call.setString(Constantes.TRES, dataRequest.getP_tdocc_codigo());
					call.setString(Constantes.CUATRO, dataRequest.getP_cliec_num_doc());
					call.setString(Constantes.CINCO, dataRequest.getP_lista_telefono());
					long tiempoInicioSP = System.currentTimeMillis();
					call.execute();					
					LOG.info(mensajeLog+ " Se invocó con éxito el SP:  "+ storedProcedure);
					rsParamGeneral = (ResultSet) call.getObject(Constantes.UNO);
						while (rsParamGeneral.next()) {							
							Pvu3P6DetalleLineaCurCABBean pvu3P6DetalleLineaCurCABBean = new Pvu3P6DetalleLineaCurCABBean();
							pvu3P6DetalleLineaCurCABBean.setCustomer_id(rsParamGeneral.getString("CUSTOMER_ID"));
							pvu3P6DetalleLineaCurCABBean.setEstado(rsParamGeneral.getString("ESTADO"));
							pvu3P6DetalleLineaCurCABBean.setBloqueo(rsParamGeneral.getString("BLOQUEO"));
							pvu3P6DetalleLineaCurCABBean.setSuspension(rsParamGeneral.getString("SUSPENSION"));
							pvu3P6DetalleLineaCurCABBean.setDeuda_vencida(rsParamGeneral.getString("DEUDA_VENCIDA"));
							pvu3P6DetalleLineaCurCABBean.setDeuda_castiga(rsParamGeneral.getString("DEUDA_CASTIGA"));
							pvu3P6DetalleLineaCurCABBean.setRazon_social(rsParamGeneral.getString("RAZON_SOCIAL"));
							pvu3P6DetalleLineaCurCABBean.setNombre(rsParamGeneral.getString("NOMBRE"));
							pvu3P6DetalleLineaCurCABBean.setApellido_pat(rsParamGeneral.getString("APELLIDO_PAT"));
							pvu3P6DetalleLineaCurCABBean.setApellido_mat(rsParamGeneral.getString("APELLIDO_MAT"));
							pvu3P6DetalleLineaCurCABBean.setDias_deuda(rsParamGeneral.getString("DIAS_DEUDA"));
							pvu3P6DetalleLineaCurCABBean.setProm_fact(rsParamGeneral.getString("PROM_FACT"));
							pvu3P6DetalleLineaCurCABBean.setCant_servicio(rsParamGeneral.getString("CANT_SERVICIO"));						
							listPvu3P6DetalleLineaCurCABBean.add(pvu3P6DetalleLineaCurCABBean);
						}
						pvu3Play6ConDetalleLineaBeanResponse.setP_cursor_cab(listPvu3P6DetalleLineaCurCABBean);	
						
						LOG.info(mensajeLog+ " Se invocó con éxito el SP:  "+ storedProcedure);
						rsDetalle = (ResultSet) call.getObject(Constantes.DOS);
							while (rsDetalle.next()) {							
								Pvu3P6DetalleLineaCurDETBean pvu3P6DetalleLineaCurDETBean = new Pvu3P6DetalleLineaCurDETBean();
								pvu3P6DetalleLineaCurDETBean.setCuenta(rsDetalle.getString("CUENTA"));
								pvu3P6DetalleLineaCurDETBean.setPlan(rsDetalle.getString("PLAN"));
								pvu3P6DetalleLineaCurDETBean.setPlan_sisact(rsDetalle.getString("PLAN_SISACT"));
								pvu3P6DetalleLineaCurDETBean.setTelefono(rsDetalle.getString("TELEFONO"));
								pvu3P6DetalleLineaCurDETBean.setServicio(rsDetalle.getString("SERVICIO"));
								pvu3P6DetalleLineaCurDETBean.setCargo_fijo(rsDetalle.getString("CARGO_FIJO"));
								pvu3P6DetalleLineaCurDETBean.setSolin_sum_car_con(rsDetalle.getString("SOLIN_SUM_CAR_CON"));
								pvu3P6DetalleLineaCurDETBean.setFecha_activacion(rsDetalle.getString("FECHA_ACTIVACION"));
								pvu3P6DetalleLineaCurDETBean.setFecha_estado(rsDetalle.getString("FECHA_ESTADO"));
								pvu3P6DetalleLineaCurDETBean.setEstado(rsDetalle.getString("ESTADO"));
								pvu3P6DetalleLineaCurDETBean.setMotivo_bloqueo(rsDetalle.getString("MOTIVO_BLOQUEO"));
								pvu3P6DetalleLineaCurDETBean.setMotivo_suspension(rsDetalle.getString("MOTIVO_SUSPENSION"));
								pvu3P6DetalleLineaCurDETBean.setCampana(rsDetalle.getString("CAMPANA"));						
								pvu3P6DetalleLineaCurDETBean.setPlan_bscs(rsDetalle.getString("PLAN_BSCS"));													
								listPvu3P6DetalleLineaCurDETBean.add(pvu3P6DetalleLineaCurDETBean);
							}
							pvu3Play6ConDetalleLineaBeanResponse.setP_cursor_cab(listPvu3P6DetalleLineaCurCABBean);
							pvu3Play6ConDetalleLineaBeanResponse.setP_cursor_det(listPvu3P6DetalleLineaCurDETBean);
							LOG.info(mensajeLog+ PARAMETRO+" Tiempo total de proceso del llamado del SP"+ storedProcedure + " (ms): "+ (System.currentTimeMillis() - tiempoInicioSP));
					LOG.info(mensajeLog+ " PARAMETROS [OUTPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(pvu3Play6ConDetalleLineaBeanResponse));
				}catch (SQLException e) {
					LOG.info(mensajeLog+ "{} Error Catch Stored Procedure: \n{}", e.getMessage(), e);
				} finally {
					if(rsParamGeneral != null) {
						LOG.info(mensajeLog+ "{} Cerrando cursor: rsParamGeneral");										
						rsParamGeneral.close();
					}
					if(rsDetalle != null) {
						LOG.info(mensajeLog+ "{} Cerrando cursor: rsParamGeneral");										
						rsDetalle.close();
					}					
					if(connection!= null) {
						LOG.info(mensajeLog+  "{} Cerrando la conexion del metodo: " + Constantes.NOMBRE_SUBMETODO_6);							
						connection.close();
					}					
				}
				}
		});
	}catch (Exception  ex) {
			
		if ( ex.toString().contains( Constantes.EXCEPTIONTIMEOUT ) ) {	
			
			LOG.error(" Error de Timeout en BD: "+ nombreBD, ex);
			
            codResp=propertiesExternos.idt2Codigo;
            msjResp=propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
										  .replace(Constantes.REPLACERECURSO, nombreBD )
										  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
										  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pkgSPconDetalleLinea);
			throw new DBException(codResp,msjResp,ex);
			
		}else{
			
			LOG.error("Error de Disponibilidad en BD: "+ nombreBD, ex);
            codResp=propertiesExternos.idt1Codigo;
            msjResp=propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
            									  .replace(Constantes.REPLACERECURSO, nombreBD )
            									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
            									  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pkgSPconDetalleLinea);		
            throw new DBException(codResp,msjResp,ex);
		}
	}finally {
		LOG.info(mensajeLog+ TIEMPO_PROCESO+ (System.currentTimeMillis() - tiempoInicio));
		LOG.info(mensajeLog+ "[Fin de metodo: " + Constantes.NOMBRE_SUBMETODO_10 + "]");
	}
	return pvu3Play6ConDetalleLineaBeanResponse;
}
	

public PvuPlanBilleteraBeanResponse obtenerPlanBilleteraPVU(PropertiesExternos propertiesExternos, PvuPlanBilleteraBeanRequest dataRequest, String mensajeLog)throws DBException{
	
	long tiempoInicio = System.currentTimeMillis();
	PvuPlanBilleteraBeanResponse pvuPlanBilleteraBeanResponse = new PvuPlanBilleteraBeanResponse();	
	List<PvuPlanBilleteraCurBean> listPvuPlanBilleteraCurBean = new ArrayList<>();			
	String codResp = Constantes.CONSTANTE_VACIA;
    String msjResp = Constantes.CONSTANTE_VACIA;
    String nombreBD = propertiesExternos.pvuOwner;	    
	StringBuilder storedProcedure= new StringBuilder();
	
	try {
		LOG.info(mensajeLog+ " req.getP_codigo obtenerParametroGeneralPVU "+dataRequest.getP_sistema());
		LOG.info(mensajeLog+ " [INICIO de metodo: " + Constantes.NOMBRE_SUBMETODO_11 + "]"+Constantes.REPOSITORY);
		String strOwnerPvu = propertiesExternos.pvuOwner;
		String strPkgGeneral3Play = propertiesExternos.pkgSPplanXBilletera;			
		if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
			storedProcedure.append(strOwnerPvu);
			storedProcedure.append(Constantes.PUNTO);
		}
		storedProcedure.append(strPkgGeneral3Play);
		Session session = entityManager.unwrap( Session.class );
		session.doWork(new Work() {
				
			@Override
			public void execute(final Connection connection) throws SQLException {
				LOG.info(mensajeLog+" Ejecutando SP : " + storedProcedure);
				LOG.info(mensajeLog+" PARAMETROS [INPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(dataRequest));				
				ResultSet rsParamGeneral = null;
				try (CallableStatement call = connection.prepareCall("call " + storedProcedure.toString() +" (?,?)" )){				
					call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
					LOG.info(TIEMPO_CONEXION + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);					
					call.registerOutParameter(Constantes.UNO, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());						
					call.setInt(Constantes.DOS, dataRequest.getP_sistema());
					long tiempoInicioSP = System.currentTimeMillis();
					call.execute();					
					LOG.info(mensajeLog+" Se invocó con éxito el SP:  "+ storedProcedure);
					rsParamGeneral = (ResultSet) call.getObject(Constantes.UNO);
						while (rsParamGeneral.next()) {							
							PvuPlanBilleteraCurBean pvuPlanBilleteraCurBean = new PvuPlanBilleteraCurBean();
							pvuPlanBilleteraCurBean.setSolv_codigo(rsParamGeneral.getString("SOLV_CODIGO"));
							pvuPlanBilleteraCurBean.setPrclv_codigo(rsParamGeneral.getInt("PRCLV_CODIGO"));											
							listPvuPlanBilleteraCurBean.add(pvuPlanBilleteraCurBean);
						}
						pvuPlanBilleteraBeanResponse.setP_consulta(listPvuPlanBilleteraCurBean);
						LOG.info(mensajeLog+ PARAMETRO+" Tiempo total de proceso del llamado del SP"+ storedProcedure + " (ms): "+ (System.currentTimeMillis() - tiempoInicioSP));
				}catch (SQLException e) {
					throw new SQLException(e);
				} finally {
					if(rsParamGeneral != null) {
						LOG.info(mensajeLog + "Cerrando cursor: rsParamGeneral");										
						rsParamGeneral.close();
					}
					if(connection!= null) {
						LOG.info(mensajeLog + "Cerrando la conexion del metodo: " + Constantes.NOMBRE_SUBMETODO_6);							
						connection.close();
					}
					
				}
				}
		});
	}catch (Exception  ex) {
			
		if ( ex.toString().contains( Constantes.EXCEPTIONTIMEOUT ) ) {				
			LOG.error("Error de Timeout en BD: "+ nombreBD, ex);			
            codResp=propertiesExternos.idt2Codigo;
            msjResp=propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
										  .replace(Constantes.REPLACERECURSO, nombreBD )
										  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
										  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pkgSPplanXBilletera);
			throw new DBException(codResp,msjResp,ex);			
		}else{			
			LOG.error("Error de Disponibilidad en BD: "+ nombreBD, ex);
            codResp=propertiesExternos.idt1Codigo;
            msjResp=propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
            									  .replace(Constantes.REPLACERECURSO, nombreBD )
            									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
            									  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pkgSPplanXBilletera);		
            throw new DBException(codResp,msjResp,ex);
		}
	}finally {
		LOG.info(mensajeLog+ TIEMPO_PROCESO+ (System.currentTimeMillis() - tiempoInicio));
		LOG.info("[Fin de metodo: " + Constantes.NOMBRE_SUBMETODO_11 + "]");
	}
	return pvuPlanBilleteraBeanResponse;
}



public PvuCalculoLCxProductoBeanResponse calculoLCxProductoPVU(PropertiesExternos propertiesExternos, PvuCalculoLCxProductoBeanRequest dataRequest, String mensajeLog)throws DBException{
	
	long tiempoInicio = System.currentTimeMillis();
	PvuCalculoLCxProductoBeanResponse pvuCalculoLCxProductoBeanResponse = new PvuCalculoLCxProductoBeanResponse();
	
	List<PvuCalculoLCxProductoCurBean> listPvuPlanBilleteraCurBean = new ArrayList<>();				
			
	String codResp = Constantes.CONSTANTE_VACIA;
    String msjResp = Constantes.CONSTANTE_VACIA;
    
    String nombreBD = propertiesExternos.pvuOwner;	    
	StringBuilder storedProcedure= new StringBuilder();
	
	try {
		LOG.info(mensajeLog+ " req.getP_codigo obtenerParametroGeneralPVU "+dataRequest.getP_cliente_nuevo());
		LOG.info(mensajeLog+ " [INICIO de metodo: " + Constantes.NOMBRE_SUBMETODO_12 + "]"+Constantes.REPOSITORY);
		String strOwnerPvu = propertiesExternos.pvuOwner;
		String strPkgGeneral3Play = propertiesExternos.pKGSPcalculoLcXProducto;			
		if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
			storedProcedure.append(strOwnerPvu);
			storedProcedure.append(Constantes.PUNTO);
		}
		storedProcedure.append(strPkgGeneral3Play);
		Session session = entityManager.unwrap( Session.class );
		session.doWork(new Work() {
				
			@Override
			public void execute(final Connection connection) throws SQLException {
				LOG.info(mensajeLog+ " Ejecutando SP : " + storedProcedure);
				LOG.info(mensajeLog+ " PARAMETROS [INPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(dataRequest));
				ResultSet rsParamGeneral = null;
				try (CallableStatement call = connection.prepareCall("call " + storedProcedure.toString() +" (?,?,?,?,?,?)" )){					
					call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
					LOG.info(TIEMPO_CONEXION + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);															
					call.setString(Constantes.UNO, dataRequest.getP_riesgo());
					call.setString(Constantes.DOS, dataRequest.getP_tipo_doc());
					call.setString(Constantes.TRES, dataRequest.getP_essalud_sunat());
					call.setString(Constantes.CUATRO, dataRequest.getP_cliente_nuevo());
					call.setFloat(Constantes.CINCO, dataRequest.getP_lc_dc());				
					call.registerOutParameter(Constantes.SEIS, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());
					long tiempoInicioSP = System.currentTimeMillis();
					call.execute();					
					LOG.info(mensajeLog+ " Se invocó con éxito el SP:  "+ storedProcedure);
					rsParamGeneral = (ResultSet) call.getObject(Constantes.SEIS);
						while (rsParamGeneral.next()) {							
							PvuCalculoLCxProductoCurBean pvuPlanBilleteraCurBean = new PvuCalculoLCxProductoCurBean();
							pvuPlanBilleteraCurBean.setProducto_cod(rsParamGeneral.getInt("PRODUCTO_COD"));
							pvuPlanBilleteraCurBean.setDescripcion(rsParamGeneral.getString("DESCRIPCION"));
							pvuPlanBilleteraCurBean.setProducto_lc(rsParamGeneral.getInt("PRODUCTO_LC"));
							listPvuPlanBilleteraCurBean.add(pvuPlanBilleteraCurBean);
						}
						pvuCalculoLCxProductoBeanResponse.setC_producto_lc(listPvuPlanBilleteraCurBean);
						LOG.info(mensajeLog+ PARAMETRO+" Tiempo total de proceso del llamado del SP"+ storedProcedure + " (ms): "+ (System.currentTimeMillis() - tiempoInicioSP));
					LOG.info(mensajeLog+ " PARAMETROS [OUTPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(pvuCalculoLCxProductoBeanResponse));
				}catch (SQLException e) {
					LOG.info(mensajeLog+ " Error Catch Stored Procedure: \n{}", e.getMessage(), e);
				} finally {
					if(rsParamGeneral != null) {
						LOG.info(mensajeLog+ " Cerrando cursor: rsParamGeneral");										
						rsParamGeneral.close();
					}					
					if(connection!= null) {
						LOG.info(mensajeLog+ " Cerrando la conexion del metodo: " + Constantes.NOMBRE_SUBMETODO_6);							
						connection.close();
					}
					
				}
			}
		});
	}catch (Exception  ex) {
			
		if ( ex.toString().contains( Constantes.EXCEPTIONTIMEOUT ) ) {	
			
			LOG.error(mensajeLog+ "Error de Timeout en BD: "+ nombreBD, ex);
			 
            codResp=propertiesExternos.idt2Codigo;
            msjResp=propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
										  .replace(Constantes.REPLACERECURSO, nombreBD )
										  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
										  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pKGSPcalculoLcXProducto);
			throw new DBException(codResp,msjResp,ex);
			
		}else{
			
			LOG.error(mensajeLog+ " Error de Disponibilidad en BD: "+ nombreBD, ex);
            codResp=propertiesExternos.idt1Codigo;
            msjResp=propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
            									  .replace(Constantes.REPLACERECURSO, nombreBD )
            									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
            									  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pKGSPcalculoLcXProducto);		
            throw new DBException(codResp,msjResp,ex);
		}
	}finally {
		LOG.info(mensajeLog+ TIEMPO_PROCESO, System.currentTimeMillis() - tiempoInicio);
		LOG.info(mensajeLog+ " [Fin de metodo: " + Constantes.NOMBRE_SUBMETODO_12 + "]");
	}
	return pvuCalculoLCxProductoBeanResponse;
}


public PvuCuotaIniComercialResponse obtenerCuotaIniComercial(PropertiesExternos propertiesExternos, PvuCuotaIniComercialRequest dataRequest, String mensajeLog)throws DBException{
	
	long tiempoInicio = System.currentTimeMillis();
	PvuCuotaIniComercialResponse pvuCalculoLCxProductoBeanResponse = new PvuCuotaIniComercialResponse();			
	String codResp = Constantes.CONSTANTE_VACIA;
    String msjResp = Constantes.CONSTANTE_VACIA;
    
    String nombreBD = propertiesExternos.pvuOwner;	    
	StringBuilder storedProcedure= new StringBuilder();
	
	try {
		LOG.info(mensajeLog+ "dataRequest.getP_CODIGOLISTAPRECIO() obtenerCuotaIniComercial "+dataRequest.getP_CODIGOLISTAPRECIO());
		LOG.info(mensajeLog+ "[INICIO de metodo: " + Constantes.NOMBRE_SUBMETODO_18 + "]"+Constantes.REPOSITORY);
		LOG.info(mensajeLog+"Inicio - obtenerCuotaIniComercial");
		String strOwnerPvu = propertiesExternos.pvuOwner;
		String strPkgGeneral3Play = propertiesExternos.pKGNuevaListaPre;			
		if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
			storedProcedure.append(strOwnerPvu);
			storedProcedure.append(Constantes.PUNTO);
		}
		storedProcedure.append(strPkgGeneral3Play);
		Session session = entityManager.unwrap( Session.class );
		session.doWork(new Work() {
				
			@Override
			public void execute(final Connection connection) throws SQLException {
				LOG.info(mensajeLog+ "Ejecutando SP : " + storedProcedure);
				LOG.info(mensajeLog+"PARAMETROS [INPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(dataRequest));				
				try (CallableStatement call = connection.prepareCall("call " + storedProcedure.toString() +" (?,?,?,?,?,?,?)" )){
					
					call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
					LOG.info(TIEMPO_CONEXION + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);
															
					call.setString(Constantes.UNO, dataRequest.getP_CODIGOLISTAPRECIO());
					call.setString(Constantes.DOS, dataRequest.getP_CODMATERIAL());
					call.setString(Constantes.TRES, dataRequest.getP_CODPLAZO());
					call.registerOutParameter(Constantes.CUATRO, java.sql.Types.NUMERIC);
					call.registerOutParameter(Constantes.CINCO, java.sql.Types.DOUBLE);
					call.registerOutParameter(Constantes.SEIS, java.sql.Types.INTEGER);
					call.registerOutParameter(Constantes.SIETE, java.sql.Types.VARCHAR);
					long tiempoInicioSP = System.currentTimeMillis();
					call.execute();					
					LOG.info("Se invocó con éxito el SP:  "+ storedProcedure);
					pvuCalculoLCxProductoBeanResponse.setP_CUOTAINICIAL(call.getDouble(Constantes.CUATRO));
					pvuCalculoLCxProductoBeanResponse.setP_MONTOCUOTA(call.getDouble(Constantes.CINCO));				
					pvuCalculoLCxProductoBeanResponse.setP_RESULTADO(call.getInt(Constantes.SEIS));
					pvuCalculoLCxProductoBeanResponse.setP_MENSAJE(call.getString(Constantes.SIETE)!= null ? call.getString(Constantes.SIETE) : Constantes.VACIO);				
					LOG.info(mensajeLog+ PARAMETRO+" Tiempo total de proceso del llamado del SP"+ storedProcedure + " (ms): "+ (System.currentTimeMillis() - tiempoInicioSP));
					LOG.info("PARAMETROS [OUTPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(pvuCalculoLCxProductoBeanResponse));
				}catch (SQLException e) {
					LOG.info(mensajeLog + " Error Catch Stored Procedure: \n{}", e.getMessage(), e);
					throw new SQLException(e);
				} finally {					
					if(connection!= null) {
						LOG.info(mensajeLog + "Cerrando la conexion del metodo: " + Constantes.NOMBRE_SUBMETODO_6);							
						connection.close();
					}					
				}
			}
		});
	}catch (Exception  ex) {
			
		if ( ex.toString().contains( Constantes.EXCEPTIONTIMEOUT ) ) {			
			LOG.error("Error de Timeout en BD: "+ nombreBD, ex);			
            codResp=propertiesExternos.idt2Codigo;
            msjResp=propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
										  .replace(Constantes.REPLACERECURSO, nombreBD )
										  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
										  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pKGNuevaListaPre);
			throw new DBException(codResp,msjResp,ex);			
		}else{
			
			LOG.error(mensajeLog+ "{} Error de Disponibilidad en BD: "+ nombreBD, ex);
            codResp=propertiesExternos.idt1Codigo;
            msjResp=propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
            									  .replace(Constantes.REPLACERECURSO, nombreBD )
            									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
            									  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pKGNuevaListaPre);		
            throw new DBException(codResp,msjResp,ex);
		}
	}finally {
		LOG.info(mensajeLog+ TIEMPO_PROCESO, System.currentTimeMillis() - tiempoInicio);
		LOG.info(mensajeLog+"[Fin de metodo: " + Constantes.NOMBRE_SUBMETODO_18 + "]");
	}
	return pvuCalculoLCxProductoBeanResponse;
}

public List<PvuConTipoCuotaResponse> obtenerConTipoCuota(String mensajeLog, PropertiesExternos propertiesExternos) throws DBException {
	long tiempoInicio = System.currentTimeMillis();
	List<PvuConTipoCuotaResponse> listPvuContipoCuotasResponse = new ArrayList<>();
	StringBuilder storedProcedure= new StringBuilder();
	
	String codResp = Constantes.CONSTANTE_VACIA;
    String msjResp = Constantes.CONSTANTE_VACIA;    
    String nombreBD = propertiesExternos.pvuOwner;	
	
	try {
		LOG.info(mensajeLog + "[INICIO de metodo: " + Constantes.NOMBRE_SUBMETODO_17 + "]"+Constantes.REPOSITORY);
		LOG.info(mensajeLog + "Inicio - obtenerConTipoCuota");
		String strOwnerPvu = propertiesExternos.pvuOwner;
		String strPkgBiomovil = propertiesExternos.pKGGeneral3PlaySpConTipoCuota;			
		if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
			storedProcedure.append(strOwnerPvu);
			storedProcedure.append(Constantes.PUNTO);
		}
		storedProcedure.append(strPkgBiomovil);
		Session session = entityManager.unwrap( Session.class );
		session.doWork(new Work() {
				
			@Override
			public void execute(final Connection connection) throws SQLException {
				LOG.info(mensajeLog + "Ejecutando SP : " + storedProcedure);
				LOG.info(mensajeLog + "PARAMETROS [INPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(Constantes.VACIO));
				ResultSet rsDetallePvuCuotaResponse = null;
				try (CallableStatement call = connection.prepareCall("call " + storedProcedure.toString() +" (?)" )){					
					call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
					LOG.info(mensajeLog + TIEMPO_CONEXION + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);
					call.registerOutParameter(Constantes.UNO, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());								
					long tiempoInicioSP = System.currentTimeMillis();
					call.execute();					
					LOG.info(mensajeLog + "Se invocó con éxito el SP:  "+ storedProcedure);
					rsDetallePvuCuotaResponse = (ResultSet) call.getObject(Constantes.UNO);				
						while (rsDetallePvuCuotaResponse.next()) {
							PvuConTipoCuotaResponse pvuConTipoCuotaResponse = new PvuConTipoCuotaResponse();
							pvuConTipoCuotaResponse.setCuocCodigo(rsDetallePvuCuotaResponse.getString("CUOC_CODIGO"));
							pvuConTipoCuotaResponse.setCuovDescripcion(rsDetallePvuCuotaResponse.getString("CUOV_DESCRIPCION"));
							pvuConTipoCuotaResponse.setCuonVigencia(rsDetallePvuCuotaResponse.getInt("CUON_VIGENCIA"));
							listPvuContipoCuotasResponse.add(pvuConTipoCuotaResponse);
						}										
						LOG.info(mensajeLog + "Antes de entrar:  ");					
						LOG.info(mensajeLog+ PARAMETRO+" Tiempo total de proceso del llamado del SP"+ storedProcedure + " (ms): "+ (System.currentTimeMillis() - tiempoInicioSP));
					LOG.info(mensajeLog + "PARAMETROS [OUTPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(listPvuContipoCuotasResponse));
					}catch (SQLException e) {
						LOG.info(mensajeLog + " Error Catch Stored Procedure: \n{}", e.getMessage(), e);
						throw new SQLException(e);
					} finally {
						if(rsDetallePvuCuotaResponse != null) {
							LOG.info(mensajeLog + "Cerrando cursor: rsParamGeneral");										
							rsDetallePvuCuotaResponse.close();
						}
						if(connection!= null) {
							LOG.info(mensajeLog + "Cerrando la conexion del metodo: " + Constantes.NOMBRE_SUBMETODO_6);							
							connection.close();
						}
					}
				}
		});
	} catch (Exception e2) {
		if ( e2.toString().contains( Constantes.EXCEPTIONTIMEOUT ) ) {			
			LOG.error(mensajeLog + "Error de Timeout en BD: "+ nombreBD, e2);			
            codResp=propertiesExternos.idt2Codigo;
            msjResp=propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
										  .replace(Constantes.REPLACERECURSO, nombreBD )
										  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
										  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pKGGeneral3PlaySpConTipoCuota);
			throw new DBException(codResp,msjResp,e2);			
		}else{			
			LOG.error(mensajeLog + "Error de Disponibilidad en BD: "+ nombreBD, e2);
            codResp=propertiesExternos.idt1Codigo;
            msjResp=propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
            									  .replace(Constantes.REPLACERECURSO, nombreBD )
            									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
            									  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pKGGeneral3PlaySpConTipoCuota);		
            throw new DBException(codResp,msjResp,e2);
		}
	}
	finally{
		LOG.info(mensajeLog+  TIEMPO_PROCESO+  (System.currentTimeMillis() - tiempoInicio));
		LOG.info(mensajeLog + "[Fin de metodo: " + Constantes.NOMBRE_SUBMETODO_17 + "]");
	}
	return listPvuContipoCuotasResponse;
}

//Obtener parametros

public ParametrosPvuPlsqlResponse obtenerDataParametros(ParametroRequest parametroRequest, String idTx, PropertiesExternos propertiesExternos, String mensajeLog) throws DBException {
	long tiempoInicio = System.currentTimeMillis();
	String idTransaccion = idTx;
	StringBuilder storedProcedure = new StringBuilder();
	ParametrosPvuPlsqlResponse parametrosPvuPlsqlResponse = new ParametrosPvuPlsqlResponse();
	String codResp = Constantes.CONSTANTE_VACIA;
    String msjResp = Constantes.CONSTANTE_VACIA;    
    String nombreBD = propertiesExternos.pvuOwner;
	try {		
		storedProcedure.append(propertiesExternos.pvuOwner + Constantes.PUNTO);
		storedProcedure.append(propertiesExternos.bdPvuPkgConfVentas + Constantes.PUNTO);
		storedProcedure.append(propertiesExternos.bdPvuSpParametro);
		LOG.info(mensajeLog + "[ INICIO de metodo: {} ] ", idTx, storedProcedure);		
		Session session = entityManager.unwrap( Session.class );
		session.doWork(new Work() {			
			@Override
			public void execute(final Connection connection) throws SQLException {
				String idTransaccion = idTx;
				LOG.info(mensajeLog + " Ejecutando SP : ", idTransaccion);
				LOG.info(mensajeLog + " PARAMETROS [INPUT]: {}", idTransaccion, parametroRequest);
				ResultSet cursorParametros = null;
				try (CallableStatement call = connection.prepareCall("call " + storedProcedure.toString() +" (?,?,?,?,?,?,?,?)" )){					
					call.setQueryTimeout(Integer.parseInt(propertiesExternos.bsPvuConexionTimeOutLimiteParametro));					
					cargarDataInputOutputPlsql(parametroRequest, call);						
					long tiempoInicioSP = System.currentTimeMillis();
					call.execute();					
					LOG.info(mensajeLog+ PARAMETRO+" Tiempo total de proceso del llamado del SP"+ storedProcedure + " (ms): "+ (System.currentTimeMillis() - tiempoInicioSP));
					LOG.info(mensajeLog +" Se invocó con éxito el SP:  {}", idTransaccion, storedProcedure);					
					cursorParametros = cargarDataStoredProcedure(parametrosPvuPlsqlResponse, call);					
					LOG.info(mensajeLog + " PARAMETROS [OUTPUT]: {}", idTransaccion , parametrosPvuPlsqlResponse);
				} catch (SQLException e) {
					String descripcionError = seterValorExceptionLog(e);
					LOG.info(mensajeLog + " Error Catch Stored Procedure: {}", idTransaccion , descripcionError);					
					throw new SQLException(e);
				}
				finally {
					if(cursorParametros != null){
						cursorParametros.close();
					}
					if(connection != null) {
						connection.close();
					}
				}
			}

			private void cargarDataInputOutputPlsql(ParametroRequest parametroRequest, CallableStatement call)	throws SQLException {
				call.setString(Constantes.UNO, parametroRequest.getSistema());
				call.setString(Constantes.DOS, parametroRequest.getVersion());
				call.setString(Constantes.TRES, parametroRequest.getTipoOperacion());
				call.setString(Constantes.CUATRO, parametroRequest.getGrupo());
				call.setString(Constantes.CINCO, parametroRequest.getCodigo());						
				
				call.registerOutParameter(Constantes.SEIS, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());//CURSOR
				call.registerOutParameter(Constantes.SIETE, java.sql.Types.VARCHAR); //CODIGO_RESPUESTA
				call.registerOutParameter(Constantes.OCHO, java.sql.Types.VARCHAR); //PO_MENSAJE_RESPUESTA	
			}
			
			private ResultSet cargarDataStoredProcedure(ParametrosPvuPlsqlResponse parametrosPvuPlsqlResponse, CallableStatement call) throws SQLException {
				ResultSet cursorParametros;
				cursorParametros = (ResultSet) call.getObject(Constantes.SEIS);
				List<ParametroPvuPlsqlResponse> parametros = new ArrayList<>();
				
				if (cursorParametros != null) {
					while (cursorParametros.next()) {
						ParametroPvuPlsqlResponse parametro = setearDatosEnviadosPorCursor(cursorParametros);								
						parametros.add(parametro);
					}
				}						
				
				parametrosPvuPlsqlResponse.setParametros(parametros);
				
				return cursorParametros;
			}

			private ParametroPvuPlsqlResponse setearDatosEnviadosPorCursor(ResultSet cursorParametros) throws SQLException {
				ParametroPvuPlsqlResponse parametro = new ParametroPvuPlsqlResponse();
				
				parametro.setGrupo(cursorParametros.getInt(Constantes.UNO)!= Constantes.CERO?cursorParametros.getInt(Constantes.UNO):Constantes.CERO);																
				parametro.setCodigo(cursorParametros.getInt(Constantes.DOS)!= Constantes.CERO?cursorParametros.getInt(Constantes.DOS):Constantes.CERO);
				parametro.setData(cursorParametros.getString(Constantes.TRES)!= null?cursorParametros.getString(Constantes.TRES):Constantes.TEXTO_VACIO);
				parametro.setDescripcionGrupo(cursorParametros.getString(Constantes.CUATRO)!= null?cursorParametros.getString(Constantes.CUATRO):Constantes.TEXTO_VACIO);
				parametro.setDescripcionData(cursorParametros.getString(Constantes.CINCO)!= null?cursorParametros.getString(Constantes.CINCO):Constantes.TEXTO_VACIO);
				parametro.setSistema(cursorParametros.getString(Constantes.SEIS)!= null?cursorParametros.getString(Constantes.SEIS):Constantes.TEXTO_VACIO);
				parametro.setVersion(cursorParametros.getString(Constantes.SIETE)!= null?cursorParametros.getString(Constantes.SIETE):Constantes.TEXTO_VACIO);
				parametro.setTipoOperacion(cursorParametros.getString(Constantes.OCHO)!= null?cursorParametros.getString(Constantes.OCHO):Constantes.TEXTO_VACIO);				
				return parametro;
			}

		});
	} catch (Exception ex) {
		if ( ex.toString().contains( Constantes.EXCEPTIONTIMEOUT ) ) {						
				LOG.error("Error de Timeout en BD: "+ nombreBD, ex);					
	            codResp=propertiesExternos.idt2Codigo;
	            msjResp=propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
											  .replace(Constantes.REPLACERECURSO, nombreBD )
											  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
											  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pKGNuevaListaPre);
				throw new DBException(codResp,msjResp,ex);
				
			}else{
				
				LOG.error("Error de Disponibilidad en BD: "+ nombreBD, ex);
	            codResp=propertiesExternos.idt1Codigo;
	            msjResp=propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
	            									  .replace(Constantes.REPLACERECURSO, nombreBD )
	            									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
	            									  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pKGNuevaListaPre);		
	            throw new DBException(codResp,msjResp,ex);
			}
	}finally{
		LOG.info(mensajeLog+ TIEMPO_PROCESO+ (System.currentTimeMillis() - tiempoInicio));
		LOG.info(mensajeLog+ "[ Fin de metodo: ]", idTransaccion , Constantes.METHODO_OBTENERPARAMETRO);
	}
	
	return parametrosPvuPlsqlResponse;
}

	private String seterValorExceptionLog(Exception e) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		
		return String.valueOf(errors.toString());
	}
	
	//Nuevas otras 7 varibales
	
	public PvuConsultaCVEPendResponse obtenerCVEPend(String mensajeLog, PropertiesExternos propertiesExternos, String idTx, PvuConsultaCVEPendRequest dataRequest) throws DBException {
		long tiempoInicio = System.currentTimeMillis();
		
		StringBuilder storedProcedure= new StringBuilder();
		PvuConsultaCVEPendResponse pvuConsultaCVEPend = new PvuConsultaCVEPendResponse();
		String codResp = Constantes.CONSTANTE_VACIA;
	    String msjResp = Constantes.CONSTANTE_VACIA;    
	    String nombreBD = propertiesExternos.pvuOwner;			
		try {
			LOG.info(mensajeLog + "[INICIO de metodo: " + Constantes.NOMBRE_SUBMETODO_17 + "]"+Constantes.REPOSITORY);
			LOG.info(mensajeLog + "Inicio - obtenerCVEPend");
			String strOwnerPvu = propertiesExternos.pvuOwner;
			String strPkgBiomovil = propertiesExternos.pkgSiactDraVe6;			
			if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
				storedProcedure.append(strOwnerPvu);
				storedProcedure.append(Constantes.PUNTO);
			}
			storedProcedure.append(strPkgBiomovil);
			Session session = entityManager.unwrap( Session.class );
			session.doWork(new Work() {					
				@Override
				public void execute(final Connection connection) throws SQLException {
					LOG.info(mensajeLog+ "Ejecutando SP : " + storedProcedure);
					LOG.info(mensajeLog+ "PARAMETROS [INPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(Constantes.VACIO));					
				try (CallableStatement call = connection.prepareCall("call " + storedProcedure.toString() +" (?,?,?,?,?,?,?)" );){					
					call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
					LOG.info(TIEMPO_CONEXION + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);
					call.setString(Constantes.UNO, dataRequest.getP_tipo_doc());
					call.setString(Constantes.DOS, dataRequest.getP_nro_doc());
					call.registerOutParameter(Constantes.TRES, java.sql.Types.NUMERIC);
					call.registerOutParameter(Constantes.CUATRO, java.sql.Types.NUMERIC);
					call.registerOutParameter(Constantes.CINCO, java.sql.Types.NUMERIC);
					call.registerOutParameter(Constantes.SEIS, java.sql.Types.VARCHAR);
					call.registerOutParameter(Constantes.SIETE, java.sql.Types.VARCHAR);										
					long tiempoInicioSP = System.currentTimeMillis();
					call.execute();					
					LOG.info(mensajeLog + "Se invocó con éxito el SP:  "+ storedProcedure);										
					pvuConsultaCVEPend.setP_tot_imp_sol_pend(call.getBigDecimal(Constantes.TRES));
					pvuConsultaCVEPend.setP_tot_cant_lin(call.getBigDecimal(Constantes.CUATRO));				
					pvuConsultaCVEPend.setP_cant_max_cuo(call.getBigDecimal(Constantes.CINCO));
					pvuConsultaCVEPend.setP_cod_resp(call.getString(Constantes.SEIS));
					pvuConsultaCVEPend.setP_msg_resp(call.getString(Constantes.SIETE));										
					LOG.info(mensajeLog+ "Antes de entrar:  ");					
					LOG.info(mensajeLog+ PARAMETRO+" Tiempo total de proceso del llamado del SP"+ storedProcedure + " (ms): "+ (System.currentTimeMillis() - tiempoInicioSP));
					LOG.info(mensajeLog+ "PARAMETROS [OUTPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(pvuConsultaCVEPend));
				}catch (SQLException e) {
					LOG.info(mensajeLog + " Error Catch Stored Procedure: \n{}", e.getMessage(), e);
					throw new SQLException(e);
				} finally {					
					if(connection!= null) {
						LOG.info(mensajeLog + "Cerrando la conexion del metodo: " + Constantes.NOMBRE_SUBMETODO_6);							
						connection.close();
					}
				}
				}
			});
		} catch (Exception e2) {
			LOG.error("error: "+e2+e2.getCause()+e2.getMessage()+e2.toString());
			StringWriter errors = new StringWriter();
			e2.printStackTrace(new PrintWriter(errors));

			String descripcionError=String.valueOf(errors.toString());
			LOG.error("error: "+descripcionError);
			
			if ( e2.toString().contains( Constantes.EXCEPTIONTIMEOUT ) ) {	
				
				LOG.error("Error de Timeout en BD: "+ nombreBD, e2);
				
	            codResp=propertiesExternos.idt2Codigo;
	            msjResp=propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
											  .replace(Constantes.REPLACERECURSO, nombreBD )
											  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
											  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pkgSiactDraVe6);
				throw new DBException(codResp,msjResp,e2);
				
			}else{
				
				LOG.error("Error de Disponibilidad en BD: "+ nombreBD, e2);
	            codResp=propertiesExternos.idt1Codigo;
	            msjResp=propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD )
	            									  .replace(Constantes.REPLACERECURSO, nombreBD )
	            									  .replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
	            									  .replace(Constantes.REPLACEMETODOSPRECURSO, propertiesExternos.pvuOwner+propertiesExternos.pkgSiactDraVe6);		
	            throw new DBException(codResp,msjResp,e2);
			}
		}
		finally{
			LOG.info(mensajeLog+ TIEMPO_PROCESO+ (System.currentTimeMillis() - tiempoInicio));
			LOG.info("[Fin de metodo: obtenerCVEPend]");
		}
		return pvuConsultaCVEPend;
	}
	
	public ClienteEvalResponse getDataValidaClienteReglasRenovacion(PropertiesExternos propertiesExternos, ClienteEvalRequest dataRequest, String mensajeLog ) throws DBException {
		long tiempoInicio = System.currentTimeMillis();
		ClienteEvalResponse response = new ClienteEvalResponse();
		
		String codResp = Constantes.CONSTANTE_VACIA;
		String msjResp = Constantes.CONSTANTE_VACIA;
		String nombreBD = propertiesExternos.pvuOwner;
		StringBuilder storedProcedure= new StringBuilder();
		try {
			LOG.info(mensajeLog+ "{} [INICIO de metodo: getDataValidaClienteReglasRenovacion ]"+Constantes.REPOSITORY);
			String strOwnerPvu = propertiesExternos.pvuOwner;
			String strPkgBiomovil = propertiesExternos.pkgGeneralDetalleLinea;			
			if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
				storedProcedure.append(strOwnerPvu);
				storedProcedure.append(Constantes.PUNTO);
			}
			storedProcedure.append(strPkgBiomovil);
			Session session = entityManager.unwrap( Session.class );
			session.doWork(new Work() {
					
				@Override
				public void execute(final Connection connection) throws SQLException {
					LOG.info(mensajeLog+ PARAMETRO+ " Ejecutando SP : " + storedProcedure);
					LOG.info(mensajeLog+ PARAMETRO+ " PARAMETROS [INPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(dataRequest));
					ResultSet rsDetalleResponse = null;
					ResultSet rsDatosResponse = null;					
					try (CallableStatement call = connection.prepareCall(CALL + storedProcedure.toString() +" (?,?,?,?,?,?,?)" )){
						
						call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
						LOG.info(mensajeLog+ TIEMPO_CONEXION + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);
						call.registerOutParameter(Constantes.UNO, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());
						call.registerOutParameter(Constantes.DOS, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());						
						call.registerOutParameter(Constantes.TRES, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());												
						call.setString(Constantes.CUATRO, dataRequest.getPiOficina());
						call.setString(Constantes.CINCO, dataRequest.getPiTipoDocCod());
						call.setString(Constantes.SEIS, dataRequest.getPiNumDoc());
						call.setString(Constantes.SIETE, Constantes.VACIO);
										
						long tiempoInicioSP = System.currentTimeMillis();
						call.execute();					
						LOG.info(mensajeLog+ PARAMETRO+ " Se invocó con éxito el SP:  "+ storedProcedure);
						rsDetalleResponse = (ResultSet) call.getObject(Constantes.UNO);
						rsDatosResponse = (ResultSet) call.getObject(Constantes.DOS);
						List<DatosResponse> listDetalleResponse = new ArrayList<>();					
						List<DatosResponse> listDatosResponse = new ArrayList<>();
							while (rsDetalleResponse.next()) {
								DatosResponse detalleResponse = new DatosResponse();					
								detalleResponse.setCodigo(rsDetalleResponse.getString("CODIGO"));
								detalleResponse.setPdv(rsDetalleResponse.getString("PDV"));
								detalleResponse.setCanal(rsDetalleResponse.getString("CANAL"));
								detalleResponse.setDepartamento(rsDetalleResponse.getString("DEPARTAMENTO"));								
								detalleResponse.setProvincia(rsDetalleResponse.getString("PROVINCIA")!= null ? rsDetalleResponse.getString("PROVINCIA") : Constantes.VACIO);
								detalleResponse.setDistrito(rsDetalleResponse.getString("DISTRITO")!= null ? rsDetalleResponse.getString("DISTRITO") : Constantes.VACIO);
								detalleResponse.setRegion(rsDetalleResponse.getString("REGION")!= null ? rsDetalleResponse.getString("REGION") : Constantes.VACIO);
								detalleResponse.setCalidadVendedor(rsDetalleResponse.getString("CALIDAD_VENDEDOR") != null ? rsDetalleResponse.getString("CALIDAD_VENDEDOR") : Constantes.VACIO);
								listDetalleResponse.add(detalleResponse);
							}										
							LOG.info(mensajeLog+ "{} Antes de entrar:  ");
							while (rsDatosResponse.next()) {
								LOG.info(mensajeLog+ "{} en los datosResponse:");
								DatosResponse datosResponse = new DatosResponse();//Cliente
								datosResponse.setRiesgo(rsDatosResponse.getString("RIESGO"));
								datosResponse.setPuntaje(rsDatosResponse.getString("PUNTAJE"));
								datosResponse.setEdad(rsDatosResponse.getBigDecimal("EDAD"));
								listDatosResponse.add(datosResponse);
								LOG.info(mensajeLog+ PARAMETRO+ " saliendo de datosResponse:"+datosResponse);
							}
						response.setListDatosRequest(listDatosResponse);
						response.setListDetalleRequest(listDetalleResponse);
						LOG.info(mensajeLog+ PARAMETRO+" Tiempo total de proceso del llamado del SP"+ storedProcedure + " (ms): "+ (System.currentTimeMillis() - tiempoInicioSP));
						LOG.info(mensajeLog+ PARAMETRO+" PARAMETROS [OUTPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(response));						
					}catch (SQLException e) {
						LOG.info(mensajeLog+ PARAMETRO + " Error Catch Stored Procedure: \n {}", e.getMessage(), e);
					} finally {
						if(rsDatosResponse!= null) {
							rsDatosResponse.close();
						}
						if(rsDetalleResponse!= null) {
							rsDetalleResponse.close();
						}						
						if(connection!= null) {
							LOG.info(mensajeLog+ PARAMETRO+ " Cerrando la conexion del metodo: getDataValidaClienteReglasRenovacion" );							
							connection.close();
						}						
					}					
				}
			});
		} catch (Exception e2) {
				if (e2.toString().contains(Constantes.EXCEPTIONTIMEOUT)) {
				LOG.error(mensajeLog+ PARAMETRO+ " Error de Timeout en BD: " + nombreBD, e2);

				codResp = propertiesExternos.idt2Codigo;
				msjResp = propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.pvuOwner + propertiesExternos.pkgBiomovil);
				throw new DBException(codResp, msjResp, e2);
			} else {
				codResp = propertiesExternos.idt1Codigo;
				msjResp = propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.pvuOwner + propertiesExternos.pkgBiomovil);
				throw new DBException(codResp, msjResp, e2);
			}		
		}
		finally{
			LOG.info(mensajeLog+ TIEMPO_PROCESO, System.currentTimeMillis() - tiempoInicio);
			LOG.info(mensajeLog+ "[Fin de metodo: getDataValidaClienteReglasRenovacion]");
		}
		return response;
	}
	
}