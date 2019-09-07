package pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes;

public enum TipoSiNo {
	SI,  NO;
	  
	  private TipoSiNo() {}
	  
	  public String value()
	  {
	    return name();
	  }
	  
	  public static TipoSiNo fromValue(String v)
	  {
	    return valueOf(v);
	  }
}
