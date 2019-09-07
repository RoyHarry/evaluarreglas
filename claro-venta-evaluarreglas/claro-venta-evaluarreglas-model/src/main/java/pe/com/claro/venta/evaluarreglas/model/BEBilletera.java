package pe.com.claro.venta.evaluarreglas.model;

public class BEBilletera {
	public BEBilletera() {
	}

	private int idBilletera;

	public final int getidBilletera() {
		return idBilletera;
	}

	public final void setidBilletera(int value) {
		idBilletera = value;
	}

	private String billetera;

	public final String getbilletera() {
		return billetera;
	}

	public final void setbilletera(String value) {
		billetera = value;
	}

	private int nroPlanes;

	public final int getnroPlanes() {
		return nroPlanes;
	}

	public final void setnroPlanes(int value) {
		nroPlanes = value;
	}

	private double monto;

	public final double getmonto() {
		return monto;
	}

	public final void setmonto(double value) {
		monto = value;
	}

	public BEBilletera(int idBilletera, String billetera) {
		this.setidBilletera(idBilletera);
		this.setbilletera(billetera);
	}

	public BEBilletera(int idBilletera, int nroPlanes) {
		this.setidBilletera(idBilletera);
		this.setnroPlanes(nroPlanes);
	}

	public enum TIPO_BILLETERA {
		MOVIL(2), INTERNET(4), CLAROTV(8), TELEFONIA(16), BAM(32), TRIPLEPLAY(28);

		public static final int SIZE = java.lang.Integer.SIZE;

		private int intValue;
		private static java.util.HashMap<Integer, TIPO_BILLETERA> mappings;

		private static java.util.HashMap<Integer, TIPO_BILLETERA> getMappings() {
			if (mappings == null) {
				synchronized (TIPO_BILLETERA.class) {
					mappings = new java.util.HashMap<>();
				}
			}
			return mappings;
		}

		private TIPO_BILLETERA(int value) {
			intValue = value;
			getMappings().put(value, this);
		}

		public int getValue() {
			return intValue;
		}

		public static TIPO_BILLETERA forValue(int value) {
			return getMappings().get(value);
		}
	}
}
