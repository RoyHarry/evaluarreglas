package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;
import java.sql.Date;

public class SgaDetalleInfCurBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codcli;
	private String grupo;
	private String idpaq;
	private String paquete;
	private String servicio;
	private String estado;
	private Integer cantidad_bloq;
	private String motivo_bloq;
	private Integer cantidad_susp;
	private String motivo_susp;
	private Date fecha_activacion;
	private Integer monto_cr;
	private Integer prom_fac;
	private Integer monto_no_fac;
	private Integer monto_vencido;
	private Integer monto_castigo;
	private String cant_srv;
	
	public String getCodcli() {
		return codcli;
	}
	public void setCodcli(String codcli) {
		this.codcli = codcli;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getIdpaq() {
		return idpaq;
	}
	public void setIdpaq(String idpaq) {
		this.idpaq = idpaq;
	}
	public String getPaquete() {
		return paquete;
	}
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getCantidad_bloq() {
		return cantidad_bloq;
	}
	public void setCantidad_bloq(Integer cantidad_bloq) {
		this.cantidad_bloq = cantidad_bloq;
	}
	public String getMotivo_bloq() {
		return motivo_bloq;
	}
	public void setMotivo_bloq(String motivo_bloq) {
		this.motivo_bloq = motivo_bloq;
	}
	public Integer getCantidad_susp() {
		return cantidad_susp;
	}
	public void setCantidad_susp(Integer cantidad_susp) {
		this.cantidad_susp = cantidad_susp;
	}
	public String getMotivo_susp() {
		return motivo_susp;
	}
	public void setMotivo_susp(String motivo_susp) {
		this.motivo_susp = motivo_susp;
	}
	public Date getFecha_activacion() {
		return fecha_activacion;
	}
	public void setFecha_activacion(Date fecha_activacion) {
		this.fecha_activacion = fecha_activacion;
	}
	public Integer getMonto_cr() {
		return monto_cr;
	}
	public void setMonto_cr(Integer monto_cr) {
		this.monto_cr = monto_cr;
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
	public Integer getMonto_vencido() {
		return monto_vencido;
	}
	public void setMonto_vencido(Integer monto_vencido) {
		this.monto_vencido = monto_vencido;
	}
	public Integer getMonto_castigo() {
		return monto_castigo;
	}
	public void setMonto_castigo(Integer monto_castigo) {
		this.monto_castigo = monto_castigo;
	}
	public String getCant_srv() {
		return cant_srv;
	}
	public void setCant_srv(String cant_srv) {
		this.cant_srv = cant_srv;
	}
	
}
