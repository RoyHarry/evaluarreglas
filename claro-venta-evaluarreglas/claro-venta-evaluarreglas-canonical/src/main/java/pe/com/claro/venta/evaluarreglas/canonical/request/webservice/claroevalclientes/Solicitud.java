package pe.com.claro.venta.evaluarreglas.canonical.request.webservice.claroevalclientes;

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
	private ClienteWS clienteWS;
	private double costoTotalDeEquipos;	  
	private List<EquipoWS> equipoWS;	  
	private Date fechaEjecucion;
	private TipoSiNo flagDeLicitacion;
	private int horaEjecucion;	  
	private List<PlanProactivoWS> lCargoFijo;
	private Oferta oferta;
	private double precioTotalVentaDeEquipos;
	private PuntoDeVentaWS puntodeVenta;
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
	public ClienteWS getCliente() {
		return clienteWS;
	}
	public void setCliente(ClienteWS clienteWS) {
		this.clienteWS = clienteWS;
	}
	public double getCostoTotalDeEquipos() {
		return costoTotalDeEquipos;
	}
	public void setCostoTotalDeEquipos(double costoTotalDeEquipos) {
		this.costoTotalDeEquipos = costoTotalDeEquipos;
	}
	public List<EquipoWS> getEquipo() {
		return equipoWS;
	}
	public void setEquipo(List<EquipoWS> equipoWS) {
		this.equipoWS = equipoWS;
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
	public List<PlanProactivoWS> getlCargoFijo() {
		return lCargoFijo;
	}
	public void setlCargoFijo(List<PlanProactivoWS> lCargoFijo) {
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
	public PuntoDeVentaWS getPuntodeVenta() {
		return puntodeVenta;
	}
	public void setPuntodeVenta(PuntoDeVentaWS puntodeVenta) {
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
