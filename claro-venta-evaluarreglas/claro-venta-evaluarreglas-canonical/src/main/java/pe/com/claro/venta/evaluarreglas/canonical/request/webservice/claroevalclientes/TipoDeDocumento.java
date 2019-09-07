package pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes;

import java.io.Serializable;

public class TipoDeDocumento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String value;
	private TipoDeDocumento(String v)
	  {
	    this.value = v;
	  }
	  
	  public String value()
	  {
	    return this.value;
	  }
	  
}
