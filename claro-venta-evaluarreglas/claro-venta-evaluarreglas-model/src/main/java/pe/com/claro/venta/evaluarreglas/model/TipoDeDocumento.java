package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;

public class TipoDeDocumento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String value;
	private TipoDeDocumento(String v)
	  {
	    this.value = v;
	  }
	  
	  public String value()
	  {
	    return this.value;
	  }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	  
}
