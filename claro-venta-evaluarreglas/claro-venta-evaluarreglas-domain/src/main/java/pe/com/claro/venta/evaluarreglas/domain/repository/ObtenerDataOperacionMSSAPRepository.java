package pe.com.claro.venta.evaluarreglas.domain.repository;

import java.io.Serializable;
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
import pe.com.claro.venta.evaluarreglas.model.RequestPrecioBase;
import pe.com.claro.venta.evaluarreglas.model.ResponsePrecioBase;

@Stateful
public class ObtenerDataOperacionMSSAPRepository extends AbstractRepository<Object> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ObtenerDataOperacionMSSAPRepository.class);
	
	@PersistenceContext(unitName = "pe.com.claro.venta.data.source.sinergia_mssap", type = PersistenceContextType.EXTENDED)
	public void setPersistenceUnit00(final EntityManager em) {
		LOG.info("entre al constructor");
	    this.entityManager=em;
	}
	@Override
	protected Predicate[] getSearchPredicates(Root<Object> root, Object example) {
		return new Predicate[0];
	}
	
	public List<ResponsePrecioBase> obtenerPrecioBaseEquipo(PropertiesExternos propertiesExternos, RequestPrecioBase dataRequest, String mensajeLog) throws DBException {
		List<ResponsePrecioBase> listResponsePrecioBase = new ArrayList<>();
		long tiempoInicio = System.currentTimeMillis();
		StringBuilder storedProcedure= new StringBuilder();
		String codResp = Constantes.CONSTANTE_VACIA;
		String msjResp = Constantes.CONSTANTE_VACIA;
		String nombreBD = propertiesExternos.dbMsSapOwner;
		try {
			LOG.info(mensajeLog+ "[ INICIO de metodo: {}",  Constantes.NOMBRE_SUBMETODO_5 + "]"+Constantes.REPOSITORY);
			LOG.info(mensajeLog+ " Inicio - obtenerParametroGeneralPVU");
			String strOwnerPvu = propertiesExternos.dbMsSapOwner;
			String strPkgConsulta = propertiesExternos.pkgConsulta;			
			if(strOwnerPvu != null && !strOwnerPvu.isEmpty()){
				storedProcedure.append(strOwnerPvu);
				storedProcedure.append(Constantes.PUNTO);
			}
			storedProcedure.append(strPkgConsulta);
			Session session = entityManager.unwrap( Session.class );
			session.doWork(new Work() {
					
				@Override
				public void execute(final Connection connection) throws SQLException {
					LOG.info(mensajeLog+ " Ejecutando SP : " + storedProcedure);
					LOG.info(mensajeLog+ " PARAMETROS [INPUT]: \n {} " + JAXBUtilitarios.anyObjectToXmlText(dataRequest));
					ResultSet rsParamGeneral = null;
					
					try (CallableStatement call = connection.prepareCall("call " + storedProcedure.toString() +" (?,?,?)" )){						
						call.setQueryTimeout(Integer.parseInt(propertiesExternos.conexionTimeoutRegistraOperacion));
						LOG.info(mensajeLog+ " Tiempo de Timeout de Conexion: " + propertiesExternos.conexionTimeoutRegistraOperacion + " Segundo(s)");
	
						call.setString(Constantes.UNO, dataRequest.getCodMaterial());
						call.setString(Constantes.DOS, dataRequest.getDesMaterial());
						call.registerOutParameter(Constantes.TRES, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType());
						long tiempoInicioSP = System.currentTimeMillis();
						call.execute();					
						LOG.info(mensajeLog+ " Se invocó con éxito el SP:  "+ storedProcedure);
						rsParamGeneral = (ResultSet) call.getObject(Constantes.TRES);					
						while (rsParamGeneral.next()) {						
							ResponsePrecioBase responsePrecioBase =new ResponsePrecioBase();
							responsePrecioBase.setCodMaterial(rsParamGeneral.getString("MATEC_CODMATERIAL"));
							responsePrecioBase.setDesMaterial(rsParamGeneral.getString("MATEV_DESCMATERIAL"));
							responsePrecioBase.setPrecioBase(rsParamGeneral.getString("MATEN_PRECIOBASE"));
							responsePrecioBase.setPrecioCompra(rsParamGeneral.getString("MATEN_PRECIOCOMPRA"));
							listResponsePrecioBase.add(responsePrecioBase);
						}
						LOG.info(mensajeLog+ " Tiempo total de proceso del llamado del SP {} (ms):{} ", storedProcedure, System.currentTimeMillis() - tiempoInicioSP);					
//						LOG.info(mensajeLog+ " PARAMETROS [OUTPUT]: \n" + JAXBUtilitarios.anyObjectToXmlText(listResponsePrecioBase));
						}catch (SQLException e) {
							throw new SQLException(e);
						} finally {
							if(rsParamGeneral != null) {
								LOG.info(mensajeLog, "{} Cerrando cursor: rsParamGeneral");										
								rsParamGeneral.close();
							}							
							if(connection!= null) {
								LOG.info(mensajeLog, "{} Cerrando la conexion del metodo: " + Constantes.NOMBRE_SUBMETODO_6);							
								connection.close();
							}
						}
					}
			});
		} catch (Exception e2) {
			if (e2.toString().contains(Constantes.EXCEPTIONTIMEOUT)) {
				LOG.error(mensajeLog + "Error de Timeout en BD: " + nombreBD, e2);
				codResp = propertiesExternos.idt2Codigo;
				msjResp = propertiesExternos.idt2Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.dbMsSapOwner + propertiesExternos.pkgConsulta);
				throw new DBException(codResp, msjResp, e2);
			} else {

				LOG.error(mensajeLog + "Error de Disponibilidad en BD: " + nombreBD, e2);
				codResp = propertiesExternos.idt1Codigo;
				msjResp = propertiesExternos.idt1Mensaje.replace(Constantes.REPLACEDBWS, Constantes.TIPOCLIENTEBD)
						.replace(Constantes.REPLACERECURSO, nombreBD)
						.replace(Constantes.REPLACEMETSP, Constantes.TIPOOPERACIONSP)
						.replace(Constantes.REPLACEMETODOSPRECURSO,
								propertiesExternos.dbMsSapOwner + propertiesExternos.pkgConsulta);
				throw new DBException(codResp, msjResp, e2);
			}
			
		}
		finally{
			LOG.info(mensajeLog+ "{} Tiempo total de proceso(ms):{} ", System.currentTimeMillis() - tiempoInicio);
			LOG.info(mensajeLog+ "{} [Fin de metodo: " + Constantes.NOMBRE_SUBMETODO_5 + "]");
		}
		return listResponsePrecioBase;
	}
}
