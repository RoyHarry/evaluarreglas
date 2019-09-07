package pe.com.claro.common.resource.exception;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement
public class ErrorMessage {
	private static final Logger LOG = LoggerFactory.getLogger(ErrorMessage.class);	
	
	int status;
	int code;
	String message;
	String developerMessage;	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	
	public ErrorMessage(ApiException ex){
		try {
			BeanUtils.copyProperties(this, ex);
		} catch (IllegalAccessException e) {
			LOG.error("{} " + "Error en BD" + ex.getMessage());
		} catch (InvocationTargetException e) {
			LOG.error("{} " + "Error en BD" + e.getMessage());
		}
	}
	
	public ErrorMessage(NotFoundException ex){
		this.status = Response.Status.NOT_FOUND.getStatusCode();
		this.code=ex.getCode();
		this.developerMessage=ex.getDeveloperMessage();		
		this.message = ex.getMessage();
	}

	public ErrorMessage(BadRequestException ex){
		this.status = Response.Status.NOT_FOUND.getStatusCode();
		this.code=ex.getCode();
		this.developerMessage=ex.getDeveloperMessage();		
		this.message = ex.getMessage();
	}
	
	public ErrorMessage(BadUsageException ex){
		this.status = Response.Status.NOT_FOUND.getStatusCode();
		this.code=ex.getCode();
		this.developerMessage=ex.getDeveloperMessage();		
		this.message = ex.getMessage();
	}
	public ErrorMessage() {}
}
