package pe.com.claro.venta.evaluarreglas.domain.repository;

import java.io.Serializable;
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
import pe.com.claro.venta.evaluarreglas.model.BscsDXLpCurClienteBean;
import pe.com.claro.venta.evaluarreglas.model.BscsDXLpCurDetalleBean;
import pe.com.claro.venta.evaluarreglas.model.BscsDetalleCFXContrato;
import pe.com.claro.venta.evaluarreglas.model.BscsDetalleXLineaBeanResponse;
import pe.com.claro.venta.evaluarreglas.model.BscsListaServiciosResponse;
import pe.com.claro.venta.evaluarreglas.model.BscsTim127ComPagoBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.BscsTim127ComPagoBeanResponse;

@Stateful
public class ObtenerDataOperacionBSCSRepository extends AbstractRepository<Object> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ObtenerDataOperacionBSCSRepository.class);
	private static final String CALL = "call ";
	private static final String SEGUNDOS = " Segundo(s)";	
	private static final String INICIO_METODO = "{} [INICIO de metodo: ";		
	private static final String EJECUTANDO_SP = "{} Ejecutando SP : ";			
	private static final String PARAMETROS_OUTPUT = "{} PARAMETROS [INPUT]: \n {} ";
	private static final String TIEMPO_TOTAL = "{} Tiempo total de proceso(ms):{} ";
	private static final String FIN_METODO = "{} [Fin de metodo: {} ";	
	
	@PersistenceContext(unitName = "pe.com.claro.venta.data.source.bscs", type = PersistenceContextType.EXTENDED)
	public void setPersistenceUnit00(final EntityManager em) {
		LOG.info("entre al constructor");
	    this.entityManager=em;
	}
	@Override
	protected Predicate[] getSearchPredicates(Root<Object> root, Object example) {
		return new Predicate[0];
	}
	
	public BscsDetalleXLineaBeanResponse timComPagoBSCS(PropertiesExternos propertiesExternos, BscsTim127ComPagoBeanRequest dataRequest, String mensajeLog) throws DBException {
		BscsDetalleXLineaBeanResponse listBscsDetalleXLineaBeanResponse = new BscsDetalleXLineaBeanResponse();
		List<BscsDXLpCurClienteBean> listBscsDXLpCurClienteBean = new ArrayList<>();
		List<BscsDXLpCurDetalleBean> listBscsDXLpCurDetalleBean = new ArrayList<>();
		long tiempoInicio = System.currentTimeMillis();
		String codResp = Constantes.CONSTANTE_VACIA;
		String msjResp = Constantes.CONSTANTE_VACIA;
		String nombreBD = propertiesExternos.dbBSCSOwner;
		StringBuilder storedProcedure= new StringBuilder();
		
		try {
			LOG.info(mensajeLog+ INICIO_METODO + Constantes.NOMBRE_SUBMETODO_3 + "]"+Constantes.REPOSITORY);
			LOG.info(mensajeLog+ " Inicio - timComPagoBSCS");
			String strOwnerPvu = propertiesExternos.dbBSCSOwner;
			String strPkgConsulta = propertiesExternos.pkgParametrico;			
			if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
				storedProcedure.append(strOwnerPvu);
				storedProcedure.append(Constantes.PUNTO);
			}
			storedProcedure.append(strPkgConsulta);
			Session session = entityManager.unwrap( Session.class );
			session.doWork(new Work() {
					
				@Override
				public void execute(final Connection connection) throws SQLException {
					LOG.info(mensajeLog+  EJECUTANDO_SP, storedProcedure);
					LOG.info(mensajeLog+  PARAMETROS_OUTPUT, JAXBUtilitarios.anyObjectToXmlText(dataRequest));
					ResultSet rsBscsDXLpCurClienteBean = null;
					ResultSet rsBscsDXLpCurDetalleBean = null;					
					try (CallableStatement call = connection.prepareCall(CALL + storedProcedure.toString() +" (?,?,?,?)" )) {
						call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
						LOG.info(mensajeLog+ " Tiempo de Timeout de Conexion: {} {}", propertiesExternos.conexionTimeoutRegistraOperacion , SEGUNDOS);
						call.setInt(Constantes.UNO, dataRequest.getP_tip_doc());
						call.setString(Constantes.DOS, dataRequest.getP_num_doc());
						call.registerOutParameter(Constantes.TRES, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());
						call.registerOutParameter(Constantes.CUATRO, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());
						
						long tiempoInicioSP = System.currentTimeMillis();
						call.execute();					
						LOG.info(mensajeLog+ " Se invocó con éxito el SP:  "+ storedProcedure);
						rsBscsDXLpCurClienteBean = (ResultSet) call.getObject(Constantes.TRES);
												
						while (rsBscsDXLpCurClienteBean.next()) {						
							BscsDXLpCurClienteBean bscsDXLpCurClienteBean =new BscsDXLpCurClienteBean();
							bscsDXLpCurClienteBean.setCUSTOMER_ID(rsBscsDXLpCurClienteBean.getString("CUSTOMER_ID"));
							bscsDXLpCurClienteBean.setPLANES(rsBscsDXLpCurClienteBean.getInt("PLANES"));
							bscsDXLpCurClienteBean.setCF(rsBscsDXLpCurClienteBean.getInt("CF"));
							bscsDXLpCurClienteBean.setBLOQ(rsBscsDXLpCurClienteBean.getInt("BLOQ"));
							bscsDXLpCurClienteBean.setSUSP(rsBscsDXLpCurClienteBean.getInt("SUSP"));
							bscsDXLpCurClienteBean.setDEUV(rsBscsDXLpCurClienteBean.getInt("DEUV"));
							bscsDXLpCurClienteBean.setDEUC(rsBscsDXLpCurClienteBean.getInt("DEUC"));
							bscsDXLpCurClienteBean.setRAZON_SOCIAL(rsBscsDXLpCurClienteBean.getString("RAZON_SOCIAL"));
							bscsDXLpCurClienteBean.setNOMBRES(rsBscsDXLpCurClienteBean.getString("NOMBRES"));
							bscsDXLpCurClienteBean.setAPELLIDOS(rsBscsDXLpCurClienteBean.getString("APELLIDOS"));
							bscsDXLpCurClienteBean.setDIAS_DEUDA(rsBscsDXLpCurClienteBean.getString("DIAS_DEUDA"));
							bscsDXLpCurClienteBean.setPROM_FACT(rsBscsDXLpCurClienteBean.getInt("PROM_FACT"));
							bscsDXLpCurClienteBean.setMONTO_NO_FACT(rsBscsDXLpCurClienteBean.getInt("MONTO_NO_FACT"));
							bscsDXLpCurClienteBean.setNRO_7(rsBscsDXLpCurClienteBean.getInt("NRO_7"));
							bscsDXLpCurClienteBean.setNRO_30(rsBscsDXLpCurClienteBean.getInt("NRO_30"));
							bscsDXLpCurClienteBean.setNRO_90(rsBscsDXLpCurClienteBean.getInt("NRO_90"));
							bscsDXLpCurClienteBean.setNRO_90_MAS(rsBscsDXLpCurClienteBean.getInt("NRO_90_MAS"));
							bscsDXLpCurClienteBean.setNRO_180(rsBscsDXLpCurClienteBean.getInt("NRO_180"));
							bscsDXLpCurClienteBean.setNRO_180_MAS(rsBscsDXLpCurClienteBean.getInt("NRO_180_MAS"));
							bscsDXLpCurClienteBean.setAfil_recibo_x_correo(rsBscsDXLpCurClienteBean.getString("AFIL_RECIBO_X_CORREO"));
							bscsDXLpCurClienteBean.setEmail(rsBscsDXLpCurClienteBean.getString("EMAIL"));						
							listBscsDXLpCurClienteBean.add(bscsDXLpCurClienteBean);
						}
						LOG.info(mensajeLog+ " Tiempo total de proceso del llamado del SP {} (ms):{} ", storedProcedure, System.currentTimeMillis() - tiempoInicioSP);					
						rsBscsDXLpCurDetalleBean = (ResultSet) call.getObject(Constantes.CUATRO);						
						while (rsBscsDXLpCurDetalleBean.next()) {						
							BscsDXLpCurDetalleBean bscsDXLpCurDetalleBean =new BscsDXLpCurDetalleBean();
							bscsDXLpCurDetalleBean.setCUSTOMER_ID(rsBscsDXLpCurDetalleBean.getString("CUSTOMER_ID"));
							bscsDXLpCurDetalleBean.setCUENTA(rsBscsDXLpCurDetalleBean.getString("CUENTA"));
							bscsDXLpCurDetalleBean.setCO_ID(rsBscsDXLpCurDetalleBean.getInt("CO_ID"));
							bscsDXLpCurDetalleBean.setTMCODE(rsBscsDXLpCurDetalleBean.getInt("TMCODE"));
							bscsDXLpCurDetalleBean.setPLAN(rsBscsDXLpCurDetalleBean.getString("PLAN"));
							bscsDXLpCurDetalleBean.setPRODUCTO(rsBscsDXLpCurDetalleBean.getString("PRODUCTO/SERVICIO"));
							bscsDXLpCurDetalleBean.setNUMERO(rsBscsDXLpCurDetalleBean.getString("NUMERO"));
							bscsDXLpCurDetalleBean.setCF_CONTRATO(rsBscsDXLpCurDetalleBean.getInt("CF_CONTRATO"));
							bscsDXLpCurDetalleBean.setPROM_FACT(rsBscsDXLpCurDetalleBean.getDouble("PROM_FACT"));
							bscsDXLpCurDetalleBean.setFECHA_ACTIVACION(rsBscsDXLpCurDetalleBean.getString("FECHA_ACTIVACION"));
							bscsDXLpCurDetalleBean.setESTADO(rsBscsDXLpCurDetalleBean.getString("ESTADO"));
							bscsDXLpCurDetalleBean.setCOD_BLOQ(rsBscsDXLpCurDetalleBean.getString("COD_BLOQ"));
							bscsDXLpCurDetalleBean.setCOD_SUSP(rsBscsDXLpCurDetalleBean.getString("COD_SUSP"));
							bscsDXLpCurDetalleBean.setMOT_BLOQ(rsBscsDXLpCurDetalleBean.getString("MOT_BLOQ"));
							bscsDXLpCurDetalleBean.setMOT_SUSP(rsBscsDXLpCurDetalleBean.getString("MOT_SUSP"));
							bscsDXLpCurDetalleBean.setFECHA_ESTADO(rsBscsDXLpCurDetalleBean.getString("FECHA_ESTADO"));
							bscsDXLpCurDetalleBean.setMONTO_VENCIDO(rsBscsDXLpCurDetalleBean.getInt("MONTO_VENCIDO"));
							bscsDXLpCurDetalleBean.setMONTO_CASTIGO(rsBscsDXLpCurDetalleBean.getInt("MONTO_CASTIGO"));
							bscsDXLpCurDetalleBean.setCAMPANA(rsBscsDXLpCurDetalleBean.getString("CAMPANA"));
							bscsDXLpCurDetalleBean.setMONTO_NO_FACT(rsBscsDXLpCurDetalleBean.getDouble("MONTO_NO_FACT"));
							bscsDXLpCurDetalleBean.setNRO_MOVIL(rsBscsDXLpCurDetalleBean.getInt("NRO_MOVIL"));
							bscsDXLpCurDetalleBean.setNRO_INTERNET_FIJO(rsBscsDXLpCurDetalleBean.getInt("NRO_INTERNET_FIJO"));
							bscsDXLpCurDetalleBean.setNRO_CLARO_TV(rsBscsDXLpCurDetalleBean.getInt("NRO_CLARO_TV"));
							bscsDXLpCurDetalleBean.setNRO_TELEF_FIJA(rsBscsDXLpCurDetalleBean.getInt("NRO_TELEF_FIJA"));
							bscsDXLpCurDetalleBean.setNRO_BAM(rsBscsDXLpCurDetalleBean.getInt("NRO_BAM"));
							bscsDXLpCurDetalleBean.setNRO_BLOQ(rsBscsDXLpCurDetalleBean.getInt("NRO_BLOQ"));
							bscsDXLpCurDetalleBean.setNRO_SUSP(rsBscsDXLpCurDetalleBean.getInt("NRO_SUSP"));
							bscsDXLpCurDetalleBean.setDEUDA_REINT_EQUIPO(rsBscsDXLpCurDetalleBean.getInt("DEUDA_REINT_EQUIPO"));
							bscsDXLpCurDetalleBean.setNRO_PLANES(rsBscsDXLpCurDetalleBean.getInt("NRO_PLANES"));
							
							listBscsDXLpCurDetalleBean.add(bscsDXLpCurDetalleBean);
						}
						listBscsDetalleXLineaBeanResponse.setP_CUR_CLIENTE(listBscsDXLpCurClienteBean);
						listBscsDetalleXLineaBeanResponse.setP_CUR_DETALLE(listBscsDXLpCurDetalleBean);
						LOG.info(mensajeLog+  " PARAMETROS DE SALIDA de timComPagoBSCS ===> ", JAXBUtilitarios.anyObjectToXmlText(listBscsDetalleXLineaBeanResponse));
				} catch (SQLException e) {
					LOG.info(mensajeLog+ " Error Catch Stored Procedure: \n {} {}", e.getMessage(), e);
				} finally {
					if (rsBscsDXLpCurClienteBean != null) {
						LOG.info(mensajeLog+ " Cerrando cursor: rsBscsDXLpCurClienteBean");
						rsBscsDXLpCurClienteBean.close();
					}
					if(rsBscsDXLpCurDetalleBean != null){
						LOG.info(mensajeLog+ " Cerrando cursor: rsBscsDXLpCurDetalleBean ");					
						rsBscsDXLpCurDetalleBean.close();
					}					
					if(connection != null ) {
						LOG.info(mensajeLog+ " Cerrando la conexion del metodo: timComPagoBSCS ");						
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
								propertiesExternos.dbBSCSOwner + propertiesExternos.pkgParametrico);
				throw new DBException(codResp, msjResp, e2);

			} else {

				codResp = propertiesExternos.idt1Codigo;
				msjResp = propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.pvuOwner + propertiesExternos.pkgParametrico);
				throw new DBException(codResp, msjResp, e2);
			}
		}
		finally{			
			LOG.info(mensajeLog+ TIEMPO_TOTAL + (System.currentTimeMillis() - tiempoInicio));
			LOG.info(mensajeLog+ FIN_METODO+  Constantes.NOMBRE_SUBMETODO_3 + "]");
		}
		return listBscsDetalleXLineaBeanResponse;
	}
	
	
	public BscsTim127ComPagoBeanResponse tim127ComPagoBSCS(PropertiesExternos propertiesExternos, BscsTim127ComPagoBeanRequest dataRequest, String mensajeLog) throws DBException {
		BscsTim127ComPagoBeanResponse response = new BscsTim127ComPagoBeanResponse();
		long tiempoInicio = System.currentTimeMillis();
		StringBuilder storedProcedure= new StringBuilder();
		String codResp = Constantes.CONSTANTE_VACIA;
		String msjResp = Constantes.CONSTANTE_VACIA;
		String nombreBD = propertiesExternos.dbBSCSOwner;
		try {
			LOG.info(mensajeLog+ INICIO_METODO + Constantes.NOMBRE_SUBMETODO_4 + "]"+Constantes.REPOSITORY);
			LOG.info(mensajeLog+ " Inicio - tim127ComPagoBSCS");
			String strOwnerPvu = propertiesExternos.dbBSCSOwner;
			String strPkgConsulta = propertiesExternos.bscsFUNCtim127CompPago;			
			if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
				storedProcedure.append(strOwnerPvu);
				storedProcedure.append(Constantes.PUNTO);
			}
			storedProcedure.append(strPkgConsulta);
			Session session = entityManager.unwrap( Session.class );
			session.doWork(new Work() {
					
				@Override
				public void execute(final Connection connection) throws SQLException {
					LOG.info(mensajeLog+ EJECUTANDO_SP + storedProcedure);
					LOG.info(mensajeLog+ PARAMETROS_OUTPUT + JAXBUtilitarios.anyObjectToXmlText(dataRequest));
					try (CallableStatement call = connection.prepareCall(CALL + storedProcedure.toString() +" (?,?,?,?,?)" );){						
						call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
						LOG.info(mensajeLog+ " Tiempo de Timeout de Conexion:  " + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);
						call.setInt(Constantes.UNO, dataRequest.getP_tip_doc());
						call.setString(Constantes.DOS, dataRequest.getP_num_doc());
						call.registerOutParameter(Constantes.TRES, java.sql.Types.INTEGER);
						call.registerOutParameter(Constantes.CUATRO, java.sql.Types.INTEGER);
						call.registerOutParameter(Constantes.CINCO, java.sql.Types.VARCHAR);					
						call.execute();			
						LOG.info(mensajeLog+ " Se invocó con éxito el SP:  "+ storedProcedure);
						response.setP_indicador(call.getInt(Constantes.TRES));
						response.setP_cod_err(call.getInt(Constantes.CUATRO));					
						response.setP_msg_err(call.getString(Constantes.CINCO)!= null ? call.getString(Constantes.CINCO) : Constantes.VACIO);
						LOG.info(mensajeLog+ "PARAMETROS DE SALIDA" + JAXBUtilitarios.anyObjectToXmlText(response));
					}catch (SQLException e) {
						LOG.info(mensajeLog+ " Error Catch Stored Procedure: \n" + e.getMessage(), e);						
					} finally {						
						if(connection!= null) {
							LOG.info(mensajeLog+ " Cerrando la conexion del metodo: tim127ComPagoBSCS ");							
							connection.close();
						}						
					}
				}
			});
		} catch (Exception e2) {
			if (e2.toString().contains(Constantes.EXCEPTIONTIMEOUT)) {

				LOG.error(mensajeLog+ " Error de Timeout en BD: " + nombreBD, e2);

				codResp = propertiesExternos.idt2Codigo;
				msjResp = propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.dbBSCSOwner + propertiesExternos.bscsFUNCtim127CompPago);
				throw new DBException(codResp, msjResp, e2);

			} else {

				LOG.error(mensajeLog+ " Error de Disponibilidad en BD: " + nombreBD, e2);
				codResp = propertiesExternos.idt1Codigo;
				msjResp = propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.dbBSCSOwner + Constantes.PUNTO + propertiesExternos.bscsFUNCtim127CompPago);
				throw new DBException(codResp, msjResp, e2);
			}
		}
		finally{
			LOG.info(mensajeLog+ TIEMPO_TOTAL+ (System.currentTimeMillis() - tiempoInicio));
			LOG.info(mensajeLog+ FIN_METODO+ Constantes.NOMBRE_SUBMETODO_4 + "]");
		}
		return response;
	}
			
	//ObtenerCargoFijo
	public BscsDetalleCFXContrato obtenerCargoFijoBscs(PropertiesExternos propertiesExternos, String coId, String mensajeLog) throws DBException {
		BscsDetalleCFXContrato response = new BscsDetalleCFXContrato();
		long tiempoInicio = System.currentTimeMillis();
		StringBuilder storedProcedure= new StringBuilder();
		String codResp = Constantes.CONSTANTE_VACIA;
		String msjResp = Constantes.CONSTANTE_VACIA;
		String nombreBD = propertiesExternos.dbBSCSOwner;
		try {
			LOG.info(mensajeLog+ INICIO_METODO + "obtenerCargoFijoBscs" + "]"+Constantes.REPOSITORY);
			LOG.info(mensajeLog+ " Inicio - obtenerCargoFijoBscs");
			String strOwnerPvu = propertiesExternos.dbBSCSOwner;
			String strPkgConsulta = propertiesExternos.sisactsDetalleCFXContrato;			
			if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
				storedProcedure.append(strOwnerPvu);
				storedProcedure.append(Constantes.PUNTO);
			}
			storedProcedure.append(strPkgConsulta);
			Session session = entityManager.unwrap( Session.class );
			session.doWork(new Work() {
					
				@Override
				public void execute(final Connection connection) throws SQLException {
					LOG.info(mensajeLog+ EJECUTANDO_SP + storedProcedure);
					LOG.info(mensajeLog+ PARAMETROS_OUTPUT + JAXBUtilitarios.anyObjectToXmlText(coId));					 
//					try(CallableStatement call = connection.prepareCall(CALL + storedProcedure.toString() +" (?,?,?,?)" );) {
					try(CallableStatement call = connection.prepareCall(CALL + storedProcedure.toString() +" (?,?)" );) {						
					call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
					LOG.info(mensajeLog+ "{} Tiempo de Timeout de Conexion: " + propertiesExternos.conexionTimeoutRegistraOperacion + SEGUNDOS);
					call.setBigDecimal(Constantes.UNO, new BigDecimal(coId));					
					call.registerOutParameter(Constantes.DOS, java.sql.Types.FLOAT);
//					call.registerOutParameter(Constantes.TRES, java.sql.Types.INTEGER);
//					call.registerOutParameter(Constantes.CUATRO, java.sql.Types.VARCHAR);
					call.execute();					
					LOG.info(mensajeLog+ "{} Se invocó con éxito el SP:  "+ storedProcedure);
					response.setPoCargoFijo(call.getBigDecimal(Constantes.DOS));
//					response.setPoCodResp(call.getInt(Constantes.TRES));
//					response.setPoMsgResp(call.getString(Constantes.CUATRO));					
					LOG.info(mensajeLog+ "PARAMETROS DE SALIDA" + JAXBUtilitarios.anyObjectToXmlText(response));
					} catch (SQLException e) {
						LOG.info(mensajeLog + " Error Catch Stored Procedure: \n{}", e.getMessage(), e);
					} finally {						
						if(connection != null) {
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
								propertiesExternos.dbBSCSOwner + Constantes.PUNTO +propertiesExternos.sisactsDetalleCFXContrato);
				throw new DBException(codResp, msjResp, e2);

			} else {
				codResp = propertiesExternos.idt1Codigo;
				msjResp = propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.dbBSCSOwner + Constantes.PUNTO +propertiesExternos.sisactsDetalleCFXContrato);
				throw new DBException(codResp, msjResp, e2);
			}
		}
		finally{
			LOG.info(mensajeLog+ TIEMPO_TOTAL + (System.currentTimeMillis() - tiempoInicio));
			LOG.info(mensajeLog+ FIN_METODO + " obtenerCargoFijoBscs" + "]");
		}
		return response;
	}
	
}
