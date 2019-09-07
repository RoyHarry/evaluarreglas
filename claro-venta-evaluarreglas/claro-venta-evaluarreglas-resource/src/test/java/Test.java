import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.venta.evaluarreglas.domain.util.Utilitarios;

public class Test {
	private static final Logger LOG = LoggerFactory.getLogger(Test.class);
	public static void main(String[] args) {				
		Utilitarios utilitarios = new Utilitarios();
		try {
			System.out.println(utilitarios.calcularCantidadMesesTranscurridos("", "25/09/2017"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
