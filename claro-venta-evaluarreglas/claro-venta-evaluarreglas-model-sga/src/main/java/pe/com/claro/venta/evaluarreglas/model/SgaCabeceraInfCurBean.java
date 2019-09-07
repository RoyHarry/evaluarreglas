package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;
import java.sql.Date;

public class SgaCabeceraInfCurBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codcli;
	private String documento;
	private String apepat;
	private String apemat;
	private String nomcli;
	private String razon_social;
	private Integer cf_servicios;
	private Integer deuda_venc;
	private Integer deuda_cast;
	private Integer num_plan;
	private Integer bloqueo;
	private Integer num_bloq;
	private Integer suspendido;
	private Integer num_susp;
	private Integer prom_fac;
	private Integer monto_no_fac;
	private String dias_deuda;
	
	public String getCodcli() {
		return codcli;
	}
	public void setCodcli(String codcli) {
		this.codcli = codcli;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getApepat() {
		return apepat;
	}
	public void setApepat(String apepat) {
		this.apepat = apepat;
	}
	public String getApemat() {
		return apemat;
	}
	public void setApemat(String apemat) {
		this.apemat = apemat;
	}
	public String getNomcli() {
		return nomcli;
	}
	public void setNomcli(String nomcli) {
		this.nomcli = nomcli;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public Integer getCf_servicios() {
		return cf_servicios;
	}
	public void setCf_servicios(Integer cf_servicios) {
		this.cf_servicios = cf_servicios;
	}
	public Integer getDeuda_venc() {
		return deuda_venc;
	}
	public void setDeuda_venc(Integer deuda_venc) {
		this.deuda_venc = deuda_venc;
	}
	public Integer getDeuda_cast() {
		return deuda_cast;
	}
	public void setDeuda_cast(Integer deuda_cast) {
		this.deuda_cast = deuda_cast;
	}
	public Integer getNum_plan() {
		return num_plan;
	}
	public void setNum_plan(Integer num_plan) {
		this.num_plan = num_plan;
	}
	public Integer getBloqueo() {
		return bloqueo;
	}
	public void setBloqueo(Integer bloqueo) {
		this.bloqueo = bloqueo;
	}
	public Integer getNum_bloq() {
		return num_bloq;
	}
	public void setNum_bloq(Integer num_bloq) {
		this.num_bloq = num_bloq;
	}
	public Integer getSuspendido() {
		return suspendido;
	}
	public void setSuspendido(Integer suspendido) {
		this.suspendido = suspendido;
	}
	public Integer getNum_susp() {
		return num_susp;
	}
	public void setNum_susp(Integer num_susp) {
		this.num_susp = num_susp;
	}
	public Integer getProm_fac() {
		return prom_fac;
	}
	public void setProm_fac(Integer prom_fac) {
		this.prom_fac = prom_fac;
	}
	public Integer getMonto_no_fac() {
		return monto_no_fac;
	}
	public void setMonto_no_fac(Integer monto_no_fac) {
		this.monto_no_fac = monto_no_fac;
	}
	public String getDias_deuda() {
		return dias_deuda;
	}
	public void setDias_deuda(String dias_deuda) {
		this.dias_deuda = dias_deuda;
	}
}
