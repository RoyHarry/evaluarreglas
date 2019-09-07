package pe.com.claro.venta.evaluarreglas.canonical.response.webservice.claroevalclientes;


public enum TipoDeCobro {
	  ABSOLUTO,  REFERENCIAL;
	  
	  private TipoDeCobro() {}
	  
	  public String value()
	  {
	    return name();
	  }
	  
	  public static TipoDeCobro fromValue(String v)
	  {
	    return valueOf(v);
	  }
}
