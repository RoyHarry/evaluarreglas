package pe.com.claro.venta.evaluarreglas.model;

import java.util.ArrayList;

public class CalcFacturacionPromedioBean {
	private ArrayList<Double> listaCalcFacturacionPromedio;
	
	private Pvu3Play6ConDetalleLineaBeanResponse obj3PlayDetalleLineaPVU;
	
	private Double montoFacturadoTotal;

	public ArrayList<Double> getListaCalcFacturacionPromedio() {
		return listaCalcFacturacionPromedio;
	}

	public void setListaCalcFacturacionPromedio(ArrayList<Double> listaCalcFacturacionPromedio) {
		this.listaCalcFacturacionPromedio = listaCalcFacturacionPromedio;
	}

	public Pvu3Play6ConDetalleLineaBeanResponse getObj3PlayDetalleLineaPVU() {
		return obj3PlayDetalleLineaPVU;
	}

	public void setObj3PlayDetalleLineaPVU(Pvu3Play6ConDetalleLineaBeanResponse obj3PlayDetalleLineaPVU) {
		this.obj3PlayDetalleLineaPVU = obj3PlayDetalleLineaPVU;
	}

	public Double getMontoFacturadoTotal() {
		return montoFacturadoTotal;
	}

	public void setMontoFacturadoTotal(Double montoFacturadoTotal) {
		this.montoFacturadoTotal = montoFacturadoTotal;
	}
	
	
}
