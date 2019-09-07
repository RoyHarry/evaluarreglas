package pe.com.claro.venta.evaluarreglas.domain.repository;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
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
import pe.com.claro.venta.evaluarreglas.model.SgaCabeceraInfCurBean;
import pe.com.claro.venta.evaluarreglas.model.SgaClienteCurResponse;
import pe.com.claro.venta.evaluarreglas.model.SgaClienteRequest;
import pe.com.claro.venta.evaluarreglas.model.SgaGetInformacionClienteBeanRequest;
import pe.com.claro.venta.evaluarreglas.model.SgaGetInformacionClienteBeanResponse;

@Stateful
public class ObtenerDataOperacionSGARepository extends AbstractRepository<Object> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ObtenerDataOperacionSGARepository.class);
	
	@PersistenceContext(unitName = "pe.com.claro.venta.data.source.sga", type = PersistenceContextType.EXTENDED)
	public void setPersistenceUnit00(final EntityManager em) {
		LOG.info("entre al constructor");
	    this.entityManager=em;
	}
	@Override
	protected Predicate[] getSearchPredicates(Root<Object> root, Object example) {
		return new Predicate[0];
	}
	
	public SgaGetInformacionClienteBeanResponse obtenerInfoClienteSGA(PropertiesExternos propertiesExternos, SgaGetInformacionClienteBeanRequest dataRequest, String mensajeLog) throws DBException {
		long tiempoInicio = System.currentTimeMillis();
		SgaGetInformacionClienteBeanResponse paramGeneralBean = new SgaGetInformacionClienteBeanResponse();
		StringBuilder storedProcedure= new StringBuilder();
		try {
			LOG.info(mensajeLog+ " [INICIO de metodo: " + Constantes.NOMBRE_SUBMETODO_14 + "]"+Constantes.REPOSITORY);
			LOG.info(mensajeLog+ " Inicio - obtenerParametroGeneralPVU");
			String strOwnerPvu = propertiesExternos.dbSgaOwner;
			String strPkgGeneral3Play = propertiesExternos.pkgPqConsultaSiacSrv;			
			if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
				storedProcedure.append(strOwnerPvu);
				storedProcedure.append(Constantes.PUNTO);
			}
			storedProcedure.append(strPkgGeneral3Play);
			Session session = entityManager.unwrap( Session.class );
			session.doWork(new Work() {
					
				@Override
				public void execute(final Connection connection) throws SQLException {
					LOG.info(mensajeLog+ " Ejecutando SP : {}", storedProcedure);
					LOG.info(mensajeLog+ " PARAMETROS [INPUT]: \n {}", JAXBUtilitarios.anyObjectToXmlText(dataRequest));
					ResultSet rsParamGeneral = null;
					try (CallableStatement call = connection.prepareCall("call " + storedProcedure.toString() +" (?,?,?,?,?)" )){
						
						call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
						LOG.info(mensajeLog+ " Tiempo de Timeout de Conexion: {} {}", propertiesExternos.conexionTimeoutRegistraOperacion, " Segundo(s)");
	
						call.setString(Constantes.UNO, dataRequest.getPdocumento());
						call.setString(Constantes.DOS, dataRequest.getpTipoDocumento());
						call.setInt(Constantes.TRES, Constantes.CERO);
						call.registerOutParameter(Constantes.CUATRO, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());
						call.registerOutParameter(Constantes.CINCO, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());
						long tiempoInicioSP = System.currentTimeMillis();
						call.execute();					
						LOG.info(mensajeLog+ " Se invocó con éxito el SP: {} ", storedProcedure);
						rsParamGeneral = (ResultSet) call.getObject(Constantes.CUATRO);
						List<SgaCabeceraInfCurBean> listSgaCabeceraInfCur = new ArrayList<>();
						while (rsParamGeneral.next()) {						
							SgaCabeceraInfCurBean sgaCabeceraInfCurBean =new SgaCabeceraInfCurBean();
							sgaCabeceraInfCurBean.setCodcli(rsParamGeneral.getString("CODCLI"));
							sgaCabeceraInfCurBean.setDocumento(rsParamGeneral.getString("DOCUMENTO"));
							sgaCabeceraInfCurBean.setApepat(rsParamGeneral.getString("APEPAT"));
							sgaCabeceraInfCurBean.setApemat(rsParamGeneral.getString("APEMAT"));
							sgaCabeceraInfCurBean.setNomcli(rsParamGeneral.getString("NOMCLI"));
							sgaCabeceraInfCurBean.setRazon_social(rsParamGeneral.getString("RAZON_SOCIAL"));
							sgaCabeceraInfCurBean.setCf_servicios(rsParamGeneral.getInt("CF_SERVICIOS"));
							sgaCabeceraInfCurBean.setDeuda_venc(rsParamGeneral.getInt("DEUDA_VENC"));
							sgaCabeceraInfCurBean.setBloqueo(rsParamGeneral.getInt("BLOQUEO"));
							sgaCabeceraInfCurBean.setNum_bloq(rsParamGeneral.getInt("NUM_BLOQ"));
							sgaCabeceraInfCurBean.setSuspendido(rsParamGeneral.getInt("SUSPENDIDO"));
							sgaCabeceraInfCurBean.setNum_susp(rsParamGeneral.getInt("NUM_SUSP"));
							sgaCabeceraInfCurBean.setProm_fac(rsParamGeneral.getInt("PROM_FAC"));
							sgaCabeceraInfCurBean.setMonto_no_fac(rsParamGeneral.getInt("MONTO_NO_FAC"));
							sgaCabeceraInfCurBean.setDias_deuda(rsParamGeneral.getString("DIAS_DEUDA"));
							listSgaCabeceraInfCur.add(sgaCabeceraInfCurBean);
						}
						paramGeneralBean.setP_cant_pp_cabecera_inf(listSgaCabeceraInfCur);
						LOG.info(mensajeLog+" Tiempo total de proceso del llamado del SP {} (ms):{} ", storedProcedure, System.currentTimeMillis() - tiempoInicioSP);					
						LOG.info(mensajeLog+  " PARAMETROS DE SALIDA de obtenerInfoClienteSGA ", JAXBUtilitarios.anyObjectToXmlText(paramGeneralBean));	
					}catch (SQLException e) {
							LOG.info(mensajeLog + " Error Catch Stored Procedure: \n{}", e.getMessage(), e);
						} finally {
							if(rsParamGeneral!= null) {
							rsParamGeneral.close();																
							}
							connection.close();							
						}
					}
			});
		} catch (Exception e2) {
			StringWriter errors = new StringWriter();
			e2.printStackTrace(new PrintWriter(errors));			
			throw new DBException(propertiesExternos.codigoRespuestaIDT1Generico, propertiesExternos.idt1Mensaje.replace("$sp", storedProcedure), e2);			
		}
		finally{
			LOG.info("Tiempo total de proceso(ms):{} ", System.currentTimeMillis() - tiempoInicio);
			LOG.info("[Fin de metodo: " + Constantes.NOMBRE_SUBMETODO_14 + "]");
		}
		return paramGeneralBean;
	}	
}
