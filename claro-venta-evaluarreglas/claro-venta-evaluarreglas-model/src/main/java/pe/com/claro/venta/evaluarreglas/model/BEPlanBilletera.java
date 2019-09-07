package pe.com.claro.venta.evaluarreglas.model;

import java.util.ArrayList;

public class BEPlanBilletera {
	private String plan;
	public final String getplan()
	{
		return plan;
	}
	public final void setplan(String value)
	{
		plan = value;
	}
	private String cuenta;
	public final String getcuenta()
	{
		return cuenta;
	}
	public final void setcuenta(String value)
	{
		cuenta = value;
	}
	private int idBilletera;
	public final int getidBilletera()
	{
		return idBilletera;
	}
	public final void setidBilletera(int value)
	{
		idBilletera = value;
	}
	private int nroPlanes;
	public final int getnroPlanes()
	{
		return nroPlanes;
	}
	public final void setnroPlanes(int value)
	{
		nroPlanes = value;
	}
	private double CF;
	public final double getCF()
	{
		return CF;
	}
	public final void setCF(double value)
	{
		CF = value;
	}
	private double montoFacturado;
	public final double getmontoFacturado()
	{
		return montoFacturado;
	}
	public final void setmontoFacturado(double value)
	{
		montoFacturado = value;
	}
	private double montoNoFacturado;
	public final double getmontoNoFacturado()
	{
		return montoNoFacturado;
	}
	public final void setmontoNoFacturado(double value)
	{
		montoNoFacturado = value;
	}
	private TIPO_PLAN tipoPlan = TIPO_PLAN.values()[0];
	public final TIPO_PLAN gettipoPlan()
	{
		return tipoPlan;
	}
	public final void settipoPlan(TIPO_PLAN value)
	{
		tipoPlan = value;
	}
	private TIPO_FACTURADOR tipoFacturador = TIPO_FACTURADOR.values()[0];
	public final TIPO_FACTURADOR gettipoFacturador()
	{
		return tipoFacturador;
	}
	public final void settipoFacturador(TIPO_FACTURADOR value)
	{
		tipoFacturador = value;
	}
	private ArrayList<BEBilletera> oBilletera;
	
	public final ArrayList<BEBilletera> getoBilletera()
	{
		return oBilletera;
	}
	public final void setoBilletera(ArrayList<BEBilletera> value)
	{
		oBilletera = value;
	}
	
	private String nroSEC;
	public final String getnroSEC()
	{
		return nroSEC;
	}
	public final void setnroSEC(String value)
	{
		nroSEC = value;
	} 

	public enum TIPO_PLAN
	{
		MOVIL(1),
		DATOS(2);

		public static final int SIZE = java.lang.Integer.SIZE;

		private int intValue;
		private static java.util.HashMap<Integer, TIPO_PLAN> mappings;
		private static java.util.HashMap<Integer, TIPO_PLAN> getMappings()
		{
			if (mappings == null)
			{
				synchronized (TIPO_PLAN.class)
				{					
					mappings = new java.util.HashMap<>();					
				}
			}
			return mappings;
		}

		private TIPO_PLAN(int value)
		{
			intValue = value;
			getMappings().put(value, this);
		}

		public int getValue()
		{
			return intValue;
		}

		public static TIPO_PLAN forValue(int value)
		{
			return getMappings().get(value);
		}
	}
	public enum TIPO_FACTURADOR
	{
		BSCS(0),
		SGA(1);

		public static final int SIZE = java.lang.Integer.SIZE;

		private int intValue;
		private static java.util.HashMap<Integer, TIPO_FACTURADOR> mappings;
		private static java.util.HashMap<Integer, TIPO_FACTURADOR> getMappings()
		{
			if (mappings == null)
			{
				synchronized (TIPO_FACTURADOR.class)
				{
					mappings = new java.util.HashMap<>();					
				}
			}
			return mappings;
		}

		private TIPO_FACTURADOR(int value)
		{
			intValue = value;
			getMappings().put(value, this);
		}

		public int getValue()
		{
			return intValue;
		}

		public static TIPO_FACTURADOR forValue(int value)
		{
			return getMappings().get(value);
		}
	}
	public enum TIPO_SISTEMA
	{
		BSCS(0),
		SGA(1),
		SISACT(2);

		public static final int SIZE = java.lang.Integer.SIZE;

		private int intValue;
		private static java.util.HashMap<Integer, TIPO_SISTEMA> mappings;
		private static java.util.HashMap<Integer, TIPO_SISTEMA> getMappings()
		{
			if (mappings == null)
			{
				synchronized (TIPO_SISTEMA.class)
				{
					mappings = new java.util.HashMap<>();
				}
			}
			return mappings;
		}

		private TIPO_SISTEMA(int value)
		{
			intValue = value;
			getMappings().put(value, this);
		}

		public int getValue()
		{
			return intValue;
		}

		public static TIPO_SISTEMA forValue(int value)
		{
			return getMappings().get(value);
		}
	}
}
