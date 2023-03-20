package empresa;

import java.util.ArrayList;

import es.uva.inf.poo.maps.GPSCoordinate;
/**
 * Gestion de un puerto de una localidad. Se proporcionan servicios acerca de los muelles de dicho puerto.
 * @author juatorr
 * @author hectori
 *
 */
public class Puerto {
	
	private String identificador;
	private double latitud;
	private double longitud;
	private ArrayList<Muelle> listaMuelles;
	
	/**
	 * Generacion de un puerto nulo/vacio.
	 */
	public Puerto() {
		listaMuelles = new ArrayList<Muelle>();
		identificador = "";
		latitud = 0;
		longitud = 0;
	}
	
	/**
	 * Generacion de un puerto. Se le asigna un identificador, y su posicion a partir de la latitud y longitud que
	 * se desee.
	 * 
	 * @param identificador
	 * @param latitud
	 * @param longitud
	 */
	public Puerto(String identificador, double latitud, double longitud) {
		setIdentificador(identificador);
		setLatitud(latitud);
		setLongitud(longitud);
		listaMuelles =  new ArrayList<Muelle>();
		
	}
	
	/**
	 * Consulta del identificador del puerto, compuesto por dos letras mayusculas con el nombre del pais, un guion
	 * y finalmente tres letras mayusculas con el codigo de la localidad.
	 * 
	 * @return identificador
	 */
	public String getIdentificador() {
		return identificador;
	}
	
	/**
	 * Establecer el identificador del puerto, compuesto por dos letras mayusculas con el nombre del pais, un guion
	 * y finalmente tres letras mayusculas con el codigo de la localidad.
	 * 
	 * @param identificador
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	/**
	 * Consulta de la latitud en la que se encuentra el puerto.
	 * 
	 * @return latitud
	 */
	public double getLatitud() {
		return latitud;
	}
	
	/**
	 * Establecer la latitud del puerto.
	 * 
	 * @param latitud
	 */
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	/**
	 * Consulta de la longitud en la que se encuentra el puerto.
	 * 
	 * @return longitud
	 */
	public double getLongitud() {
		return longitud;
	}
	
	/**
	 * Establecer la longitud en la que se encuentra el puerto.
	 * 
	 * @param longitud
	 */
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	/**
	 * Creacion de las coordenandas del puerto.
	 * 
	 * @return GPSCoordinate 
	 */
	public GPSCoordinate getGPS() {
		return new GPSCoordinate(getLatitud(), getLongitud());
	}
	
	
	/**
	 * Añadir nuevo muelle al puerto. La identificacion de cada muelle viene dada por la posicion en la que se encuentra
	 * en la lista de muelles. Es decir que para crear uno nuevo, su identificacion sera su posicion en la lista.
	 * 
	 * @param numPlazas
	 */
	public void añadirMuelle(int numPlazas) {
		int ideMuelle = listaMuelles.size();
		Muelle nuevoMuelle = new Muelle(ideMuelle, getGPS(), true, numPlazas);
		listaMuelles.add(nuevoMuelle);
	}
	
	/**
	 * Se elimina un muelle del puerto a partir del identificador del muelle.
	 * 
	 * @param id
	 */
	public void eliminarMuelle(int id) {
		for(Muelle muelle: listaMuelles) {
			if(muelle.getId()==id) {
				listaMuelles.remove(muelle);
			}
		}
	}
	
	/**
	 * Se consulta el numero de muelles que hay en total en el puerto.
	 * 
	 * @return listaMuelles.size() nº muelles en el puerto
	 */
	public int numMuelles() {
		return listaMuelles.size();
	}
	
	/**
	 * Obtener si un puerto esta completo o no. Un puerto esta completo cuando no entran mas contenedores, es decir
	 * que las plazas estan llenas y por ende los muelles tambien.
	 * 
	 * @return true si el puerto esta lleno
	 * @return false si el puerto no esta lleno
	 */
	public boolean puertoLleno() {
		int cont = 0;
		for(Muelle muelle: listaMuelles) {
			if(muelle.muelleLleno()) {
				cont ++;
			}
		}
		if(cont == listaMuelles.size()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Se obtiene una lista de muelles operativos a partir de los muelles totales que dispone el puerto.
	 * 
	 * @return muellesOp lista de muelles operativos
	 */
	public ArrayList<Muelle> muellesOp(){
		ArrayList<Muelle> muellesOp = new ArrayList<Muelle>();
		for(Muelle muelle: listaMuelles) {
			if(muelle.getEstado()) {
				muellesOp.add(muelle);
			}
		}
		return muellesOp;
	}
	
	/**
	 * Se obtiene una lista de muelles disponibles, es decir muelles con espacio. Un muelle tiene espacio si no
	 * esta lleno.
	 * 
	 * @return muellesDisp lista de muelles disponibles
	 */
	public ArrayList<Muelle> muellesDisp(){
		ArrayList<Muelle> muellesDisp = new ArrayList<Muelle>();
		for(Muelle muelle : listaMuelles) {
			if(muelle.muelleLleno()==false) {
				muellesDisp.add(muelle);
			}
		}
		return muellesDisp;
	}
	
	/**
	 * Obtencion de una lista de muelles que se encuentran a una distancia inferior dada de un punto GPS.
	 * 
	 * @param puntoGPS
	 * @return muellesCercanos
	 */
	public ArrayList<Muelle> muellesCercanos(GPSCoordinate puntoGPS, double distancia){
		ArrayList<Muelle> muellesCercanos =  new ArrayList<Muelle>();
		for(Muelle muelle : listaMuelles) {
			if(puntoGPS.getDistanceTo(muelle.getLocal())< distancia) {
				muellesCercanos.add(muelle);
			}
		}
		return muellesCercanos;
	}
}