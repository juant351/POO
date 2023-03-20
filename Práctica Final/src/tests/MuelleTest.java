package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import empresa.Contenedor;
import empresa.Muelle;
import empresa.Puerto;
import empresa.Trayecto;
import es.uva.inf.poo.maps.GPSCoordinate;

public class MuelleTest {

	@Test
	public void testMuelleIntGPSCoordinateBooleanInt() {
		int idEsperado = 22;
		boolean estadoEsperado = true;
		GPSCoordinate localEsperado = new GPSCoordinate(-67.657, 87.99);
		int numPlazasEsperado = 20;
		Muelle muelle = new Muelle(idEsperado, localEsperado, estadoEsperado, numPlazasEsperado);
		
		assertEquals("Id erroneo", idEsperado, muelle.getId());
		assertEquals("Estado erroneo", estadoEsperado, muelle.getEstado());
		assertEquals("Coordenadas erroneas", localEsperado, muelle.getLocal());
		assertEquals("Lista de plazas erronea", numPlazasEsperado, muelle.plazas());
	}

	@Test
	public void testMuelle() {
		
	}

	@Test
	public void testMuelleLleno() {
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), true, 2);
		ArrayList<Contenedor> temp = new ArrayList<Contenedor>(4);
		for(int i = 0; i < 4; i++) {
			temp.add(new Contenedor("codigo",true, true, 120, 160,300));
		}
		for(int i = 0; i < 2; i++ ) {
			muelle.getListaPlazas().add(temp);
		}
		boolean esperado = true;
		boolean resultado = muelle.muelleLleno();
		
		assertEquals("No calcula si esta lleno", esperado, resultado);
	}

	@Test
	public void testPlazasVacias() {
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), true, 2);
		int esperado = 2;
		int resultado = muelle.plazasVacias();
		
		assertEquals("No coincide el numero de plazas vacias", esperado, resultado);
	}

	@Test
	public void testPlazasSemiLlenas() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlazasLlenas() {
		int esperado = 2;
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), true, 2);
		ArrayList<Contenedor> temp = new ArrayList<Contenedor>(4);
		for(int i = 0; i < 4; i++) {
			temp.add(new Contenedor("codigo",true, true, 120, 160,300));
		}
		for(int i = 0; i < 2; i++ ) {
			muelle.getListaPlazas().add(temp);
		}
		int resultado = muelle.plazasLlenas();
		
		assertEquals("No coincide el numero de plazas llenas", esperado, resultado);
	}

	@Test
	public void testPlazaCont() {
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), true, 2);
		String codigo = "codigo";
		int posicionEsperada = 1;
		Contenedor contenedor = new Contenedor("codigo",true,true,15,30,40);
		Contenedor contene = new Contenedor("codigo1",true,true,15,30,40);
		muelle.getListaPlazas().get(0).set(0, contene);
		muelle.getListaPlazas().get(0).set(1, contene);
		muelle.getListaPlazas().get(0).set(2, contene);
		muelle.getListaPlazas().get(0).set(3, contene);
		muelle.getListaPlazas().get(1).set(0, contene);
		muelle.getListaPlazas().get(1).set(1, contene);
		muelle.getListaPlazas().get(1).set(2, contenedor);
		
		
		int PlazaCont = muelle.plazaCont(codigo);
		
		assertEquals("Distinta plaza", posicionEsperada, PlazaCont);
	}

	@Test
	public void testNivPlazaCont() {
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), true, 1);
		String codigo = "codigo";
		int posicionEsperada = 1;
		Contenedor contenedor = new Contenedor("codigo",true,true,15,30,40);
		Contenedor contene = new Contenedor("codigo1",true,true,15,30,40);
		muelle.getListaPlazas().get(0).set(0, contene);
		muelle.getListaPlazas().get(0).set(1, contenedor);
		muelle.getListaPlazas().get(0).set(2, contene);
		muelle.getListaPlazas().get(0).set(3, contene);
		
		
		int nivPlazaCont = muelle.nivPlazaCont(codigo);
		
		assertEquals("Distinto nivel de plaza", posicionEsperada, nivPlazaCont);
		
	}

	@Test
	public void testPlazas() {
		int numPlazas = 20;
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), true, numPlazas);
		
		assertEquals("Distinto numero de plazas", numPlazas, muelle.plazas());
		
	}

	@Test
	public void testAñadirCont() {
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), true, 1);
		Contenedor contenedor = new Contenedor("codigo",true,true,15,30,40);
		for(ArrayList<Contenedor> plaza : muelle.getListaPlazas()) {
			plaza.add(0, contenedor);
			plaza.add(1, contenedor);
			plaza.add(2, contenedor);
		}
		muelle.añadirCont(contenedor);
		ArrayList<ArrayList<Contenedor>> listaEsperada = new ArrayList<ArrayList<Contenedor>>(1);
		for(ArrayList<Contenedor> plaza1 : listaEsperada) {
			plaza1.add(0, contenedor);
			plaza1.add(1, contenedor);
			plaza1.add(2, contenedor);
			plaza1.add(3, contenedor);
		}
		
		assertEquals("No se ha añadido el contenedor", muelle.getListaPlazas(), listaEsperada);
	}

	@Test
	public void testEliminarCont() {
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), true, 1);
		Contenedor contenedor = new Contenedor("codigo",true,true,15,30,40);
		Contenedor contene = new Contenedor("codigo1",true,true,15,30,40);
		for(ArrayList<Contenedor> plaza : muelle.getListaPlazas()) {
			plaza.add(0, contene);
			plaza.add(1,contene);
			plaza.add(2, contenedor);
			plaza.add(3, contene);
		}
		String codigo = "codigo";
		muelle.eliminarCont(codigo);
		
		ArrayList<ArrayList<Contenedor>> listaEsperada = new ArrayList<ArrayList<Contenedor>>(1);
		for(ArrayList<Contenedor> plaza : listaEsperada) {
			plaza.add(0, contene);
			plaza.add(1, contene);
			plaza.add(2, contene);
		}
		assertEquals("No se ha eliminado el contenedor", muelle.getListaPlazas(), listaEsperada);
	}

	@Test
	public void testSetEstado() {
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), true, 20);
		boolean nuevoEstado = false;
		muelle.setEstado(nuevoEstado);
		boolean estadoDevuelto = muelle.getEstado();
		
		assertEquals("Estados diferentes", nuevoEstado, estadoDevuelto);
	}

	@Test
	public void testSetId() {
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), true, 20);
		int nuevaId = 34;
		muelle.setId(nuevaId);
		int IdDevuelta = muelle.getId();
		
		assertEquals("Identificaciones diferentes", nuevaId, IdDevuelta);
	}

	@Test
	public void testSetLocal() {
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), true, 20);
		GPSCoordinate nuevoLocal = new GPSCoordinate(-88.90, 42);
		muelle.setLocal(nuevoLocal);
		GPSCoordinate localDevuelto = muelle.getLocal();
		
		assertEquals("Puntos diferentes", nuevoLocal, localDevuelto);
	}

	@Test
	public void testSetListaPlazas() {
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), true, 20);
		ArrayList<ArrayList<Contenedor>> nuevaLista = new ArrayList<ArrayList<Contenedor>>(22);
		muelle.setListaPlazas(nuevaLista);
		ArrayList<ArrayList<Contenedor>> listaDevuelta = muelle.getListaPlazas();
		
		assertEquals("Listas diferentes", nuevaLista, listaDevuelta);
	}

	@Test
	public void testGetEstado() {
		boolean estadoEsperado = true;
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), estadoEsperado, 20 );
		boolean resultado = muelle.getEstado();
		
		assertEquals("Estado erroneo", estadoEsperado, resultado);
	}

	@Test
	public void testGetId() {
		int idEsperado = 22;
		Muelle muelle = new Muelle(idEsperado, new GPSCoordinate(-67.657, 87.99), true, 20);
		int resultado = muelle.getId();
		
		assertEquals("Id erroneo", idEsperado, resultado);
	}

	@Test
	public void testGetLocal() {
		GPSCoordinate localEsperado = new GPSCoordinate(-67.657,87.99);
		Muelle muelle = new Muelle(22, localEsperado, true, 20);
		GPSCoordinate resultado = muelle.getLocal();
		
		assertEquals("Coordenadas erroneas", localEsperado, resultado);
	}

	@Test
	public void testGetListaPlazas() {
		ArrayList<ArrayList<Contenedor>> listaPlazasEsperado = new ArrayList<ArrayList<Contenedor>>(20);
		ArrayList<Contenedor> temp = new ArrayList<Contenedor>(4);
		for(int i = 0; i < 4; i++) {
			temp.add(null);
		}
		for(int i = 0; i < 20; i++ ) {
			listaPlazasEsperado.add(temp);
		}
		Muelle muelle = new Muelle(22, new GPSCoordinate(-67.657, 87.99), true, 20);
		ArrayList<ArrayList<Contenedor>> resultado = muelle.getListaPlazas();
		
		assertEquals("Lista de plazas erronea", listaPlazasEsperado, resultado);
	}

}
