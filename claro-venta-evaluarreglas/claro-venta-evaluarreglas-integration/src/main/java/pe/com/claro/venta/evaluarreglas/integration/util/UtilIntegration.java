package pe.com.claro.venta.evaluarreglas.integration.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import pe.com.claro.common.bean.HeaderRequest;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.GeneralRuntimeException;
import pe.com.claro.common.util.ClaroUtil;

public class UtilIntegration extends Application {
	
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);
	
	@Override
	public Map<String, Object> getProperties() {
		String nombrePropertieExterno = Constantes.PROPERTIESEXTERNOS;//PROPERTIESINTERNOS
		Map<String, Object> dataProperties = new HashMap<>();
		try {
			dataProperties.putAll(readProperties(nombrePropertieExterno, false));
		} catch (IOException e) {
			LOG.info(e.getMessage());
		}

		return dataProperties;
	}

	private Map<String, Object> readProperties(String fileInClasspath, Boolean interno) throws IOException {
		InputStream is = null;
		String urlServer = Constantes.TEXTO_VACIO;
		Map<String, Object> map = null;
		try {
			if (interno) {
				is = this.getClass().getClassLoader().getResourceAsStream(fileInClasspath);
			} else {
				urlServer = System.getProperty(Constantes.PROPERTIESKEY) + Constantes.NOMBRERECURSO + File.separator
						+ fileInClasspath;
				is = new FileInputStream(urlServer);
			}
			map = new HashMap<>();
			Properties properties = new Properties();
			properties.load(is);
			map.putAll(properties.entrySet().stream()
					.collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue())));
			is.close();
			return map;
		} catch (Exception e) {
			throw new GeneralRuntimeException("No se puede leer el archivo " + fileInClasspath + " - " + urlServer, e);
		}
		finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
					LOG.info(ex.getMessage());
				}
			}
		}
		}

	public void setHeader(WebResource webResource, HeaderRequest header) {
		webResource.header(Constantes.IDTRANSACCION, header.getIdTransaccion());
		webResource.header(Constantes.TIMESTAMP,
				ClaroUtil.dateAString(header.getTimestamp(), "yyyy-MM-dd'T'HH:mm:ss+00:00"));
		webResource.header(Constantes.ACCEPT, header.getAccept());
		webResource.header(Constantes.MSGID, header.getMsgid());
		webResource.header(Constantes.USERID, header.getUserId());
	}

	public void setHeaderBuilder(Builder webResource, HeaderRequest header) {
		webResource.header(Constantes.IDTRANSACCION, header.getIdTransaccion());
		webResource.header(Constantes.TIMESTAMP,"2018-09-29T18:46:19Z");
		webResource.header(Constantes.ACCEPT, header.getAccept());
		webResource.header(Constantes.MSGID, header.getMsgid());
		webResource.header(Constantes.USERID, header.getUserId());
	}

	public void writeLog(String data) {
		LOG.info("Request: {}", data);
	}
}
