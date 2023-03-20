package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import empresa.Contenedor;
import empresa.Muelle;
import empresa.Puerto;
import es.uva.inf.poo.maps.GPSCoordinate;

public class PuertoTest {

	@Test
	public void testPuerto() {
		String idEsperado = "";
		double latEsperado = 0;
		double longEsperado = 0;
		Puerto puerto = new Puerto(idEsperado, latEsperado, longEsperado);
		
		assertEquals("Id erroneo", idEsperado, puerto.getIdentificador());
		assertEquals("Latitud erronea", latEsperado, puerto.getLatitud(), 0);
		assertEquals("Longitud erronea", longEsperado, puerto.getLongitud(), 0);
	}

	@Test
	public void testPuertoStringDoubleDouble() {
		String idEsperado = "ESP-VAL";
		double latEsperado = 50;
		double longEsperado = 50;
		Puerto puerto = new Puerto(idEsperado, latEsperado, longEsperado);
		
		assertEquals("Id erroneo", idEsperado, puerto.getIdentificador());
		assertEquals("Latitud erronea", latEsperado, puerto.getLatitud(), 0);
		assertEquals("Longitud erronea", longEsperado, puerto.getLongitud(), 0);
	}

	@Test
	public void testGetIdentificador() {
		String idEsperado = "ESP-VAL";
		Puerto puerto = new Puerto(idEsperado, 50, 50);
		String resultado = puerto.getIdentificador();
		
		assertEquals("Identificador distinto", idEsperado, resultado);
	}

	@Test
	public void testSetIdentificador() {
		Puerto puerto = new Puerto("ESP-VAL",50,50);
		String nuevoId = "ESP-MAD";
		puerto.setIdentificador(nuevoId);
		String resultado = puerto.getIdentificador();
		
		assertEquals(nuevoId, resultado);
	}

	@Test
	public void testGetLatitud() {
		double latEsperado = 50;
		Puerto puerto = new Puerto("ESP-VAL", 50, 50);
		double resultado = puerto.getLatitud();
		
		assertEquals("Latitud erronea", latEsperado, resultado, 0);
	}

	@Test
	public void testSetLatitud() {
		Puerto puerto = new Puerto("ESP-VAL",50,50);
		double nuevoLat = 50;
		puerto.setLatitud(nuevoLat);
		double resultado = puerto.getLatitud();
		
		assertEquals(nuevoLat, resultado, 0);
	}

	@Test
	public void testGetLongitud() {
		double longEsperado = 50;
		Puerto puerto = new Puerto("ESP-VAL", 50, 50);
		double resultado = puerto.getLongitud();
		
		assertEquals(longEsperado, resultado, 0);
	}

	@Test
	public void testSetLongitud() {
		Puerto puerto = new Puerto("ESP-VAL",50,50);
		double nuevoLong = 50;
		puerto.setLongitud(nuevoLong);
		double resultado = puerto.getLongitud();
		
		assertEquals(nuevoLong, resultado, 0);
	}

	@Test
    public void testGetGPS() {
		Puerto puerto = new Puerto("ESP-VAL",50,50);
        assertEquals(new GPSCoordinate(50,50),puerto.getGPS());
    }

	@Test
	public void testAñadirMuelle() {
		Puerto puerto = new Puerto("ESP-VAL",50,50);
		int numMuelles = 1;
		puerto.añadirMuelle(8);
		
		assertEquals(numMuelles, puerto.numMuelles());
	}

	@Test
	public void testEliminarMuelle() {
		Puerto puerto = new Puerto("ESP-VAL",50,50);
		int numMuelles = 2;
		puerto.añadirMuelle(8);
		puerto.añadirMuelle(4);
		puerto.añadirMuelle(7);
		puerto.eliminarMuelle(1);
		
		assertEquals(numMuelles, puerto.numMuelles());
	}

	@Test
	public void testPuertoLleno() {
		Puerto puerto = new Puerto("ESP-VAL",50,50);
		Muelle muelle = new Muelle(0, new GPSCoordinate(-67.657, 87.99), true, 1);
		ArrayList<Contenedor> temp = new ArrayList<Contenedor>(4);
		for(int i = 0; i < 4; i++) {
			temp.add(new Contenedor("codigo",true, true, 120, 160,300));
		}
		for(int i = 0; i < 1; i++ ) {
			muelle.getListaPlazas().add(temp);
		}
		puerto.añadirMuelle(4);
		
		boolean esperado = true;
		boolean resultado = puerto.puertoLleno();
		
		assertEquals(esperado, resultado);
	}

	@Test
	public void testMuellesOp() {
		Puerto puerto = new Puerto("ESP-VAL",50,50);
		Muelle muelle = new Muelle(0, new GPSCoordinate(-67.657, 87.99), true, 1);
		Muelle muelle2 = new Muelle(1, new GPSCoordinate(-67.658, 87.99), true, 1);
		puerto.añadirMuelle(4);
		puerto.añadirMuelle(2);
		
		ArrayList<Muelle> puertoEsperado = new ArrayList<Muelle>();
		puertoEsperado.add(muelle);
		puertoEsperado.add(muelle2);
		
		
		assertEquals(puertoEsperado, puerto.muellesOp());
	}

	@Test
	public void testMuellesDisp() {
		Puerto puerto = new Puerto("ESP-VAL",50,50);
		Muelle muelle = new Muelle(0, new GPSCoordinate(-67.657, 87.99), true, 1);
		Muelle muelle2 = new Muelle(1, new GPSCoordinate(-67.658, 87.99), false, 1);
		puerto.añadirMuelle(4);
		puerto.añadirMuelle(2);
		
		ArrayList<Muelle> puertoEsperado = new ArrayList<Muelle>();
		puertoEsperado.add(muelle);
		
		
		assertEquals(puertoEsperado, puerto.muellesDisp());
	}

	@Test
    public void testMuellesCercanosX() {
		Puerto puerto = new Puerto("ESP-VAL",10,10);
		puerto.añadirMuelle(4);
        assertEquals(1,puerto.muellesCercanos(new GPSCoordinate(10,10), 100).size());
    }

}
