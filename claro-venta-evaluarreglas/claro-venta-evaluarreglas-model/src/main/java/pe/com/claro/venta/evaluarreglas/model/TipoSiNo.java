package pe.com.claro.venta.evaluarreglas.model;

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
