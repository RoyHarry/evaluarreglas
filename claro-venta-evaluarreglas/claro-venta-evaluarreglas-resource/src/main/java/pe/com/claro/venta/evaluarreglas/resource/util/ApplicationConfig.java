/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.claro.venta.evaluarreglas.resource.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Singleton;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.GeneralRuntimeException;
import pe.com.claro.common.resource.exception.ProviderExceptionMapper;

@Singleton
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {
	
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();

		resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
		resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
		resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
		resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
		addRestResourceClasses(resources);
		return resources;
	}

	@Override
	public Map<String, Object> getProperties() {
		String filename = "config.properties";
		String nombrePropertieExterno = ".properties";
        Map<String, Object> dataProperties = new HashMap<>();
        dataProperties.putAll(readProperties(filename, true));
        dataProperties.putAll(readProperties(nombrePropertieExterno, false));
		return dataProperties;
	}

	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(pe.com.claro.venta.evaluarreglas.resource.EvaluarReglasResource.class);
		resources.add(ProviderExceptionMapper.class);
	}

	private Map<String, Object> readProperties(String fileInClasspath, Boolean interno) {
		InputStream is = null;
		String urlServer = Constantes.VACIO;
		try {
			if (interno) {
                is = this.getClass().getClassLoader().getResourceAsStream(fileInClasspath);
                LOG.info(" [fileInClasspath] {}",fileInClasspath);
            } else {
            	urlServer = System.getProperty("claro.properties") + Constantes.NOMBRE_API+"v2" + File.separator + fileInClasspath;
                is = new FileInputStream(urlServer);
                LOG.info(" [urlServer] {}", urlServer);
            }
			Map<String, Object> map = new HashMap<>();
			Properties properties = new Properties();
			properties.load(is);
			map.putAll(properties.entrySet().stream()
					.collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue())));
			is.close();
			return map;
		} catch (Exception e) {			
			throw new GeneralRuntimeException("No se puede leer el archivo " + fileInClasspath + " - " + urlServer , e );
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

}
