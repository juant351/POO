package empresa;

import java.lang.Math;
import java.util.ArrayList;


/**
 * A partir de un codigo de contenedor con codigo de dueno, letra de equipamiento, numero de serie, digito de control
 * @author hectori
 * @author juatorr
 *
 */

public class Contenedor {

	private String codigo;
	private boolean estado;
	private boolean tapado;
	private double peso;
	private double pesomax;
	private double volumen;
	private ArrayList<Trayecto> trayectos;
	
	public Contenedor(String codigo, boolean estado, boolean tapado, double peso, double pesomax, double volumen) {
		setCodigo(codigo);
		setEstado(estado);
		setPesomax(pesomax);
		setPesoK(peso);
		setTapado(tapado);
		setVolumenM3(volumen);
		trayectos = new ArrayList<Trayecto>();
	}
	
	public Contenedor() {
		this.codigo="0";
		this.estado=false;
		this.pesomax=0;
		this.peso=0;
		this.tapado=false;
		this.volumen=0;
		trayectos = new ArrayList<Trayecto>();
		
	}
	
	
	/**
	 * Funcion que modifica el estado de nuestro contenedor de "en transito" a "en recogida" o de "en recogida" a "en transito"
	 */
	
	public boolean CambioEstado() {
		this.setEstado(!this.getEstado());
		return this.getEstado();
	}
	
	/**
	 * Funcion que modifica si el contenedor esta tapado o no
	 */
	
	public boolean CambioTapado() {
		this.setTapado(!this.getTapado());
		return this.getTapado();
	}
	
	
	public double preciotrayecto(double precioxdia, double precioxmilla) {
		double precio = 0;
		for ( Trayecto trayecto: trayectos) {
			precio += trayecto.precio(precioxdia, precioxmilla);
		}
		return precio;
	}
	
	
	public void MasRuta(Trayecto nuevo) {
		trayectos.add(nuevo);		
	}
	
	public Trayecto getRuta() {
		return trayectos.get(trayectos.size()-1);

	}
	
	public int sacoControl(String cadena) {
		double resultado = 0;
		int contador = 0 ;
		int multando= 0;
		while (contador<10) {
		char caracter = cadena.charAt(contador);
			if(caracter>='A' && caracter<='Z') {
				if (caracter=='B' || caracter=='A'){
					int letra = caracter;
					multando = letra - 55;
					resultado = multando * Math.pow(2, contador) + resultado;
				}else if (caracter >'B' && caracter<'L'){
					int letra = caracter;
					multando = letra - 54;
					resultado = multando * Math.pow(2, contador) + resultado;
				}else if (caracter >= 'L' && caracter<'V'){
					int letra = caracter;
					multando = letra - 53;
					resultado = multando * Math.pow(2, contador) + resultado;
				}else {
					int letra = caracter;
					multando = letra - 52;
					resultado = multando * Math.pow(2, contador) + resultado;
				}
				
			}else {
				int caracternum = caracter;
				caracternum-=48;
				resultado = caracternum * Math.pow(2, contador) + resultado;
			}
			
			contador++;
		}
		int parteint = (int)(resultado/11);
		parteint *=11;
		int resultadofin = (int)resultado;
		resultadofin = resultadofin-parteint;
		return resultadofin;
	}
	/**
	 * Sacamos el volumen en Metro cubicos
	 * @return
	 */
	
	public double getVolumenM3() {
		return volumen;
	}
	
	/**
	 * Sacamos el volumen en Pies cubicos por medio de algoritmo de conversion
	 * @return
	 */
	
	public double getVolumenP3() {
		return ((volumen)*35315)*Math.pow(10, -3);
	}
	
	/**
	 * Sacamos peso en kilos
	 * @return
	 */
	
	public double getPesoK() {
		return peso;
	}
	
	/**
	 * Sacamos peso en libras conviertiendo los kilos
	 * @return
	 */
	
	public double getPesoL(){
		return (peso*2.2046);
	}
	
	/**
	 * Introduccion del codigo
	 * @param codigo
	 */
	
	public void setCodigo(String codigo) {
		if (codigo.length()<10 || codigo.length() >11) {
			throw new IllegalArgumentException("El codigo del contenedor debe tener como minimo 10 digitos");
		}else if ('A'>codigo.charAt(0) || codigo.charAt(0)>'Z' || 'A'>codigo.charAt(1) || codigo.charAt(1)>'Z' || 'A'>codigo.charAt(2) || codigo.charAt(2)>'Z') {
			throw new IllegalArgumentException("El codigo de dueño no es correcto");
		}else if(codigo.charAt(3)!='U' && codigo.charAt(3)!= 'J' && codigo.charAt(3)!='Z'){
			throw new IllegalArgumentException("El equipamieno no es correcto");
		}
		this.codigo = codigo;
	}
	
	/**
	 * Introduccion del estado, transito o recogida
	 * @param estado
	 */
	
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	/**
	 * Introduccion de si esta tapado o no
	 * @param tapado
	 */
	
	public void setTapado(boolean tapado) {
		this.tapado = tapado;
	}
	



	/**
	 * Introducimos peso en Kilogramos
	 * @param peso
	 */
	
	
	public void setPesoK(double peso) {
		if (peso<0) {
			throw new IllegalArgumentException("El peso no puede ser inferior a 0");

		}else if (peso>this.getPesomax()) {
			throw new IllegalArgumentException("El peso no puede exceder el pesomaximo");

		}
		this.peso = peso;
	}
	
	
	/**
	 * Introducimos peso en Libras
	 * @param peso
	 */
	
	
	public void setPesoL(double peso) {
		double pesomax = this.getPesomax()*2.2046;
		if (peso<0) {
			throw new IllegalArgumentException("El peso no puede ser inferior a 0");
		}else if (peso>pesomax) {
			throw new IllegalArgumentException("El peso no puede exceder el pesomaximo");

		}
		this.peso = peso/2.2046;
	}
	
	/**
	 * Introducimos peso maximo
	 * @param pesomax
	 */
	
	
	public void setPesomax(double pesomax) {
		if (pesomax<0) {
			throw new IllegalArgumentException("El peso no puede ser inferior a 0");
		}
		this.pesomax = pesomax;
	}
	
	
	/**
	 * Introucimos volumen en Metros cubicos
	 * @param volumen
	 */
	
	public void setVolumenM3(double volumen) {
		if (volumen<0) {
			throw new IllegalArgumentException("El volumen no puede ser inferior a 0");
		}
		this.volumen = volumen;
	}
	
	
	/**
	 * Introucimos volumen en Pies cubicos y lo pasamos a metros cubicos por medio de algoritmo de conversion
	 * @param volumen
	 */
	
	public void setVolumenP3(double volumen) {
		if (volumen<0) {
			throw new IllegalArgumentException("El volumen no puede ser inferior a 0");
		}
		this.volumen = ((volumen)/35315)*Math.pow(10, 3);
		
	}
	
	/**
	 * Sacamos digito de control
	 * @return
	 */
	
	
	public int getControl() {
		String cadena = this.getCodigo();
		if (cadena.length()==10) {
			return sacoControl(cadena);
		}else { 
			return cadena.charAt(10)-48;
		
		}
		
	}
	
	/**
	 * Scamos digitos de equipamiento
	 * @return
	 */
	
	
	public char getEquipamiento() {
		return codigo.charAt(3);
	}
	
	/**
	 * Sacamos numero de serie
	 * @return
	 */
	
	
	public String getNumserie() {
		return codigo.substring(4,9);
	}
	
	/**
	 * Sacamos el peso
	 * @return
	 */
	
	
	public double getPeso() {
		return peso;
	}
	
	/**
	 * Sacamos el peso maximo
	 * @return
	 */
	
	
	public double getPesomax() {
		return pesomax;
	}
	
	
	/**
	 * Sacamos el codigo
	 * @return
	 */
	
	public String getCodigodueno() {
		return codigo.substring(0,3);
	}
	
	/**
	 * Sacamos si estÃ¡ tapado o no
	 * @return
	 */
	
	
	public boolean getTapado() {
		return tapado;
	}
	
	public boolean getEstado() {
		return estado;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	
	
}