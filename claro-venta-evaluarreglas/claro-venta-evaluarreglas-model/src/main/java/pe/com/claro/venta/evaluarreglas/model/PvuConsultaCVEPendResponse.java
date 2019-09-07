package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PvuConsultaCVEPendResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal p_tot_imp_sol_pend;
    private BigDecimal p_tot_cant_lin;
    private BigDecimal p_cant_max_cuo;
    private String p_cod_resp;
    private String p_msg_resp;
        
	public BigDecimal getP_tot_imp_sol_pend() {
		return p_tot_imp_sol_pend;
	}
	public void setP_tot_imp_sol_pend(BigDecimal p_tot_imp_sol_pend) {
		this.p_tot_imp_sol_pend = p_tot_imp_sol_pend;
	}
	public BigDecimal getP_tot_cant_lin() {
		return p_tot_cant_lin;
	}
	public void setP_tot_cant_lin(BigDecimal p_tot_cant_lin) {
		this.p_tot_cant_lin = p_tot_cant_lin;
	}
	public BigDecimal getP_cant_max_cuo() {
		return p_cant_max_cuo;
	}
	public void setP_cant_max_cuo(BigDecimal p_cant_max_cuo) {
		this.p_cant_max_cuo = p_cant_max_cuo;
	}
	public String getP_cod_resp() {
		return p_cod_resp;
	}
	public void setP_cod_resp(String p_cod_resp) {
		this.p_cod_resp = p_cod_resp;
	}
	public String getP_msg_resp() {
		return p_msg_resp;
	}
	public void setP_msg_resp(String p_msg_resp) {
		this.p_msg_resp = p_msg_resp;
	}
    
    
    
}
