package pe.com.claro.venta.evaluarreglas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Solicitud implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buroConsultado;
	private double cargoFijoDeBolsa;
	private Cliente cliente;
	private double costoTotalDeEquipos;	  
	private List<Equipo> equipo;	  
	private Date fechaEjecucion;
	private TipoSiNo flagDeLicitacion;
	private int horaEjecucion;	  
	private List<PlanProactivo> lCargoFijo;
	private Oferta oferta;
	private double precioTotalVentaDeEquipos;
	private PuntoDeVenta puntodeVenta;
	private int riesgoTotalEquipo;
	private String tipoDeBolsa;
	private String tipoDeDespacho;
	private String tipoDeOperacion;
	private int totalPlanes;
	private String transaccion;
	public String getBuroConsultado() {
		return buroConsultado;
	}
	public void setBuroConsultado(String buroConsultado) {
		this.buroConsultado = buroConsultado;
	}
	public double getCargoFijoDeBolsa() {
		return cargoFijoDeBolsa;
	}
	public void setCargoFijoDeBolsa(double cargoFijoDeBolsa) {
		this.cargoFijoDeBolsa = cargoFijoDeBolsa;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public double getCostoTotalDeEquipos() {
		return costoTotalDeEquipos;
	}
	public void setCostoTotalDeEquipos(double costoTotalDeEquipos) {
		this.costoTotalDeEquipos = costoTotalDeEquipos;
	}
	public List<Equipo> getEquipo() {
		return equipo;
	}
	public void setEquipo(List<Equipo> equipo) {
		this.equipo = equipo;
	}
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}
	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
	public TipoSiNo getFlagDeLicitacion() {
		return flagDeLicitacion;
	}
	public void setFlagDeLicitacion(TipoSiNo flagDeLicitacion) {
		this.flagDeLicitacion = flagDeLicitacion;
	}
	public int getHoraEjecucion() {
		return horaEjecucion;
	}
	public void setHoraEjecucion(int horaEjecucion) {
		this.horaEjecucion = horaEjecucion;
	}
	public List<PlanProactivo> getlCargoFijo() {
		return lCargoFijo;
	}
	public void setlCargoFijo(List<PlanProactivo> lCargoFijo) {
		this.lCargoFijo = lCargoFijo;
	}
	public Oferta getOferta() {
		return oferta;
	}
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	public double getPrecioTotalVentaDeEquipos() {
		return precioTotalVentaDeEquipos;
	}
	public void setPrecioTotalVentaDeEquipos(double precioTotalVentaDeEquipos) {
		this.precioTotalVentaDeEquipos = precioTotalVentaDeEquipos;
	}
	public PuntoDeVenta getPuntodeVenta() {
		return puntodeVenta;
	}
	public void setPuntodeVenta(PuntoDeVenta puntodeVenta) {
		this.puntodeVenta = puntodeVenta;
	}
	public int getRiesgoTotalEquipo() {
		return riesgoTotalEquipo;
	}
	public void setRiesgoTotalEquipo(int riesgoTotalEquipo) {
		this.riesgoTotalEquipo = riesgoTotalEquipo;
	}
	public String getTipoDeBolsa() {
		return tipoDeBolsa;
	}
	public void setTipoDeBolsa(String tipoDeBolsa) {
		this.tipoDeBolsa = tipoDeBolsa;
	}
	public String getTipoDeDespacho() {
		return tipoDeDespacho;
	}
	public void setTipoDeDespacho(String tipoDeDespacho) {
		this.tipoDeDespacho = tipoDeDespacho;
	}
	public String getTipoDeOperacion() {
		return tipoDeOperacion;
	}
	public void setTipoDeOperacion(String tipoDeOperacion) {
		this.tipoDeOperacion = tipoDeOperacion;
	}
	public int getTotalPlanes() {
		return totalPlanes;
	}
	public void setTotalPlanes(int totalPlanes) {
		this.totalPlanes = totalPlanes;
	}
	public String getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}
	
	
}
