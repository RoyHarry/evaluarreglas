package pe.com.claro.venta.evaluarreglas.model;

public class PvuCalculoLCxProductoBeanRequest {
	private String p_riesgo;
	private String p_tipo_doc;
	private String p_essalud_sunat;
	private String p_cliente_nuevo;
	private float p_lc_dc;
	
	public String getP_riesgo() {
		return p_riesgo;
	}
	public void setP_riesgo(String p_riesgo) {
		this.p_riesgo = p_riesgo;
	}
	public String getP_tipo_doc() {
		return p_tipo_doc;
	}
	public void setP_tipo_doc(String p_tipo_doc) {
		this.p_tipo_doc = p_tipo_doc;
	}
	public String getP_essalud_sunat() {
		return p_essalud_sunat;
	}
	public void setP_essalud_sunat(String p_essalud_sunat) {
		this.p_essalud_sunat = p_essalud_sunat;
	}
	public String getP_cliente_nuevo() {
		return p_cliente_nuevo;
	}
	public void setP_cliente_nuevo(String p_cliente_nuevo) {
		this.p_cliente_nuevo = p_cliente_nuevo;
	}
	public float getP_lc_dc() {
		return p_lc_dc;
	}
	public void setP_lc_dc(float p_lc_dc) {
		this.p_lc_dc = p_lc_dc;
	}
}
