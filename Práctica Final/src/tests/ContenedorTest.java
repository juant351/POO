package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import empresa.Contenedor;
import empresa.Muelle;
import empresa.Puerto;
import empresa.Trayecto;
import es.uva.inf.poo.maps.GPSCoordinate;

public class ContenedorTest {
	
	private Contenedor pruebas = new Contenedor("AAAJ123456", true, true, 45, 1000, 23);
	private GPSCoordinate local = new GPSCoordinate(5.0,7.0);
	private Muelle muelle = new Muelle(23, local, true, 5);
	private Muelle muelle2 = new Muelle(24, local, true, 5);
	private Puerto puerto = new Puerto("ES-VAL", 6,7);
	private Puerto puerto2 = new Puerto("ES-AND", 6,7);
	private Trayecto va = new Trayecto (muelle, puerto, muelle2, puerto2, "20/1/2010", "25/1/2010");
	


	@Test
	public void testContenedorvacio() {
		Contenedor pruebas2 = new Contenedor();
		assertEquals("Si que se introduce el Contenedor", "0", pruebas2.getCodigo());
	}
	
	
	@Test
	public void testCambioEstado() {
		assertEquals("No cambia el estado", false, pruebas.CambioEstado());
	}

	@Test
	public void testCambioTapado() {
		assertEquals("No cambia el eestado de tapado", false, pruebas.CambioTapado());
	}

	@Test
	public void testPreciotrayecto() {
		pruebas.MasRuta(va);
		assertEquals("No devuelve el precio correcto", 12.5, pruebas.preciotrayecto(2.5, 5.8), 0.1);
	}

	@Test
	public void testMasRuta() {
		pruebas.MasRuta(va);
		assertEquals("No añade bien la ruta a la lista", va, pruebas.getRuta());
	}

	@Test
	public void testSacoControl() {
		Contenedor pruebas = new Contenedor("EYUU568437", true, true, 45, 1000, 23);
		assertEquals ("El digito no es correcto o el que se añade no es el correcto", 5, pruebas.getControl());
	}

	@Test
	public void testGetVolumenM3() {
		assertEquals("No devuelve el volumen correcto", 23, pruebas.getVolumenM3(), 0.1);
	}

	@Test
	public void testGetVolumenP3() {
		assertEquals("No devuelve el volumen en pies cubicos correspondientes", 812.237, pruebas.getVolumenP3(), 0.1);
	}

	@Test
	public void testGetPesoK() {
		assertEquals("No devuelve el peso en Kg correspondiente", 45, pruebas.getPesoK(), 0.1);
	}

	@Test
	public void testGetPesoL() {
		assertEquals("No devuelve el peso en Libras correspondiente", 99.208, pruebas.getPesoL(), 0.1);
	}

	@Test
	public void testSetCodigo() {
		pruebas.setCodigo("EYUU568437");
		assertEquals("No introduce el codigo correspondiente", "EYUU568437", pruebas.getCodigo());
	}

	@Test
	public void testSetEstado() {
		pruebas.setEstado(false);
		assertEquals("No introduce el estado correspondiente", false, pruebas.getEstado());

	}

	@Test
	public void testSetTapado() {
		pruebas.setTapado(false);
		assertEquals("No introduce el estado de tapado correspondiente", false, pruebas.getTapado());

	}

	@Test
	public void testSetPesoK() {
		pruebas.setPesoK(32);
		assertEquals("No introduce el peso correspondiente", 32, pruebas.getPeso(), 0.1);

	}

	@Test
	public void testSetPesoL() {
		pruebas.setPesoL(65);
		assertEquals("No introduce el peso en libras correspondiente", 65, pruebas.getPesoL(), 0.1);

	}

	@Test
	public void testSetPesomax() {
		pruebas.setPesomax(2000);
		assertEquals("No introduce el peso maximo correspondiente", 2000, pruebas.getPesomax(),0.1);

	}

	@Test
	public void testSetVolumenM3() {
		pruebas.setVolumenM3(67);
		assertEquals("No introduce el volumen correspondiente", 67, pruebas.getVolumenM3(), 0.1);

	}

	@Test
	public void testSetVolumenP3() {
		pruebas.setVolumenP3(35);
		assertEquals("No introduce el volumen en pies cubicos correspondiente", 35, pruebas.getVolumenP3(), 0.1);

	}

	@Test
	public void testGetControl() {
		assertEquals("No devuelve el digito de control correspondiente", 9, pruebas.getControl());

	}
	
	@Test
	public void testGetControl2() {
		Contenedor pruebas2 = new Contenedor("AAAJ1234569", true, true, 45, 1000, 23);

		assertEquals("No devuelve el digito de control correspondiente", 9, pruebas2.getControl());

	}

	@Test
	public void testGetEquipamiento() {
		assertEquals("No devuelve el equipamiento correspondiente", 'J', pruebas.getEquipamiento());

	}

	@Test
	public void testGetNumserie() {
		assertEquals("No devuelve el numero de serie correspondiente", "12345", pruebas.getNumserie());

	}

	@Test
	public void testGetPeso() {
		assertEquals("No devuelve el peso correspondiente", 45, pruebas.getPeso(), 0.1);

	}

	@Test
	public void testGetPesomax() {
		assertEquals("No devuelve el peso maximo correspondiente", 1000, pruebas.getPesomax(), 0.1);

	}

	@Test
	public void testGetCodigodueno() {
		assertEquals("No devuelve el codigo de dueño correspondiente", "AAA", pruebas.getCodigodueno());

	}

	@Test
	public void testGetTapado() {
		assertEquals("No devuelve el estado tapado correspondiente", true, pruebas.getTapado());

	}

	@Test
	public void testGetEstado() {
		assertEquals("No devuelve el estado correspondiente", true, pruebas.getEstado());

	}

	@Test
	public void testGetCodigo() {
		assertEquals("No devuelve el codigo correspondiente", "AAAJ123456", pruebas.getCodigo());

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setCodigoError() {
		Contenedor pruebas3 = new Contenedor("AAAJ123456568", true, true, 45, 1000, 23);

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setCodigoError2() {
		Contenedor pruebas3 = new Contenedor("1AAJ123456", true, true, 45, 1000, 23);

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setCodigoError3() {
		Contenedor pruebas3 = new Contenedor("AAAX123456", true, true, 45, 1000, 23);

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setPesoError() {
		Contenedor pruebas3 = new Contenedor("AAAJ123456", true, true, -45, 1000, 23);

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setPesoError2() {
		Contenedor pruebas3 = new Contenedor("AAAJ123456", true, true, 2000, 1000, 23);

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setPesoError3() {
		pruebas.setPesoL(-5);

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setPesoError4() {
		pruebas.setPesoL(9000);

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setPesoError5() {
		Contenedor pruebas3 = new Contenedor("AAAJ123456", true, true, 45, -1000, 23);

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setVolumenError() {
		pruebas.setVolumenM3(-9000);

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setVolumenError2() {
		pruebas.setVolumenP3(-9000);

	}
	
	


}
