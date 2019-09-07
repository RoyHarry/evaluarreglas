package pe.com.claro.common.exception;

import java.io.Serializable;

import javax.ws.rs.core.Response;

public class MappingResponseException extends Exception implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String result;
	private final String responsePrint;
	private final Response resJSON;
	
	public MappingResponseException(String result, String responsePrint, Response resJSON) {
		super();
		this.result = result;
		this.responsePrint = responsePrint;
		this.resJSON = resJSON;
	}
	public String getResult() { 
		return result; 
	}
	
	public String getResponsePrint() { 
		return responsePrint; 
	}
	public Response getResJSON() { 
		return resJSON; 
	}
}
