package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import empresa.Puerto;
import empresa.Muelle;
import empresa.Trayecto;
import empresa.Contenedor;
import es.uva.inf.poo.maps.GPSCoordinate;
import java.util.ArrayList;


import org.junit.Test;

public class TrayectoTest {
	private Puerto p1 = new Puerto();
	private Muelle m1 = new Muelle();
	private GPSCoordinate local = new GPSCoordinate(5.0,6.0);
	private Muelle muelle = new Muelle(23, local, true, 5);
	private Muelle muelle2 = new Muelle(24, local, true, 5);
	private Puerto puerto = new Puerto("ES-VAL", 6,7);
	private Puerto puerto2 = new Puerto("ES-AND", 6,7);
	
	


	@Test
	public void testPrecio() {
		
		Trayecto va = new Trayecto (muelle, puerto, muelle2, puerto2, "31/1/2010", "31/1/2010");
		assertEquals(0, va.precio(6.7,3.4), 0.1);
		Trayecto va2 = new Trayecto (muelle, puerto, muelle2, puerto2, "24/1/2010", "25/1/2010");
		assertEquals("No devuelve el precio correcto", 6.7, va2.precio(6.7,3.4), 0.1);

	}
	 
	@Test
	public void testDistancia() {
		
		GPSCoordinate local1 = new GPSCoordinate(7.0,8.0);
		Muelle muelle1 = new Muelle(23, local1, true, 5);
		Trayecto va2 = new Trayecto (muelle1, puerto, muelle2, puerto2, "31/1/2010", "31/1/2010");
		assertEquals("No devuelve la distancia correcta", 168.98, va2.distancia(), 0.1);

	}

	@Test
	public void testInfo() {
		Trayecto va = new Trayecto (muelle, puerto, muelle2, puerto2, "20/1/2010", "25/1/2010");
		assertEquals("No devuelve toda la info o la devuelve de manera equivocada", "Localidad de puerto origen: " + "VAL" + "\n" + "Pais de origen: " + "ES" + "\n" + "Fecha de inicio de trayecto: " + "20/1/2010"
		+ "\n\n" + "Localidad de destino: " + "AND" + "\n" + "Pais de destino: " + "ES" + "\n" + "Fecha de final de trayecto: " + "25/1/2010", va.info());
	}

	@Test
	public void testGetDestino() {
		Trayecto va = new Trayecto (muelle, puerto, muelle2, puerto2, "20/1/2010", "25/1/2010");
		assertEquals("No devuelve el Destino(muelle) adecuado", muelle2, va.getDestino());
		
	}
	
	@Test
	public void testSetDestino() {
		Trayecto va = new Trayecto (muelle, puerto, muelle2, puerto2, "20/1/2010", "25/1/2010");
		va.setDestino(muelle);
		assertEquals("No devuelve el Destino(muelle) adecuado", muelle, va.getDestino());
		
	}
	
	@Test
	public void testGetFechaini() {
		Trayecto va = new Trayecto (m1, p1, m1, p1, "21/1/1990", "9/8/2011");
		assertEquals("No devuelve la fecha de inicio correcta", "21/1/1990", va.getFechaini());
	}
	
	@Test
	public void testSetFechaini() {
		Trayecto va = new Trayecto (m1, p1, m1, p1, "21/1/1990", "9/8/2011");
		va.setFechaini("11/4/2012");
		assertEquals("No se introduce la fecha escrita", "11/4/2012", va.getFechaini());
	}
	
	@Test
	public void testGetFechafin() {
		Trayecto va = new Trayecto (m1, p1, m1, p1, "21/1/1990", "9/8/2011");
		assertEquals("No devuelve la fecha de fin de trayecto correcta", "9/8/2011", va.getFechafin());
	}
	
	@Test
	public void testSetFechafin() {
		Trayecto va = new Trayecto (m1, p1, m1, p1, "21/1/1990", "9/8/2011");
		va.setFechafin("11/4/1990");
		assertEquals("No se introduce la fecha escrita", "11/4/1990", va.getFechafin());
	}
	

	@Test
	public void testGetFechafind() {
		Trayecto va = new Trayecto (m1, p1, m1, p1, "21/1/1990", "9/8/2011");
		assertEquals("No devuelve los dias correspondientes a la transformacion correctos", 709185, va.getFechafind());
	}

	@Test
	public void testGetOrigenp() {
		Trayecto va = new Trayecto (muelle, puerto, muelle2, puerto2, "20/1/2010", "25/1/2010");
		assertEquals("No devuelve el puerto de origen correcto",puerto,va.getOrigenp());
	}
	
	@Test
	public void testSetOrigenp() {
		Trayecto va = new Trayecto (muelle, puerto, muelle2, puerto2, "20/1/2010", "25/1/2010");
		va.setOrigenp(puerto2);
		assertEquals("No se introduce el puerto origen escrito", puerto2,va.getOrigenp());
	}

	@Test
	public void testGetDestinop() {
		Trayecto va = new Trayecto (muelle, puerto, muelle2, puerto2, "20/1/2010", "25/1/2010");
		assertEquals("No devuelve el puerto destino correcto", puerto2,va.getDestinop());
	}
	
	@Test
	public void testSetDestinop() {
		Trayecto va = new Trayecto (muelle, puerto, muelle2, puerto2, "20/1/2010", "25/1/2010");
		va.setDestinop(puerto);
		assertEquals("No se introduce el puerto destino escrito", puerto,va.getDestinop());
	}

	@Test
	public void testGetOrigen() {
		Trayecto va = new Trayecto (muelle, puerto, muelle2, puerto2, "20/1/2010", "25/1/2010");
		assertEquals("No devuelve el muelle de origen correspondiente", muelle,va.getOrigen());
	}
	@Test
	public void testSetOrigen() {
		Trayecto va = new Trayecto (muelle, puerto, muelle2, puerto2, "20/1/2010", "25/1/2010");
		va.setOrigen(muelle2);
		assertEquals("No s introduce el muelle escrito", muelle2,va.getOrigen());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTrayectofechasMayorQue() {
		Trayecto va = new Trayecto (m1, p1, m1, p1, "21/1/2013", "31/1/2010");
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTrayectofechas() {
		Trayecto va = new Trayecto (muelle, puerto, muelle2, puerto2, "42/1/2013", "31/1/2010");
		Trayecto va1 = new Trayecto (m1, p1, m1, p1, "24/-5/2013", "31/1/2010");
		Trayecto va2 = new Trayecto (m1, p1, m1, p1, "24/1/1550", "31/1/2010");


	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testTrayectoFechasFormato() {
		Trayecto va = new Trayecto (m1, p1, m1, p1, "21/1", "31/1/2010");
		
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testPrecioNegativo() {
		Trayecto va = new Trayecto (muelle, puerto, muelle2, puerto2, "31/1/2010", "31/1/2010");
		va.precio(-2.3, 3.5);
		
	}

}
