package pe.com.claro.common.bean;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ParametrosType {
	
	private String campo;
	private String valor;
	
	public static ParametrosType fromString(String jsonRepresentation) {
        ObjectMapper mapper = new ObjectMapper();
        ParametrosType  o = null;
        try {
        	o = mapper.readValue(jsonRepresentation, ParametrosType.class );
        } catch (IOException e) {
        	o = null;
        }
        return o;
	}
	
	public ParametrosType() {
		super();
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
