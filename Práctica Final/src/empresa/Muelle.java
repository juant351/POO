package empresa;


import java.util.ArrayList;

import es.uva.inf.poo.maps.GPSCoordinate;

/**
 * Muelle que almacenará un identificador, una localización y un estado de ocupación. Permite saber el numero
 * de plazas que hay en el muelle, las ocupadas, las semi-llenas y las completas. Permite localizar plazas
 * , contenedores, asignar localizaciones de éstos, sacarlos e introducirlos.
 * 
 * @author juatorr
 * @author hectori
 *
 */


public class Muelle {
	
	private int id;
	private GPSCoordinate local;
	private boolean estado;
	private ArrayList<ArrayList<Contenedor>> listaPlazas;
	private int numPlazas;
	
	/**
	 * Creacion de un muelle a partir de un identificador, un punto de coordenadas constituido por una latitud y una
	 * longitud, un estado (disponible o no) y un numero de plazas para asignarle a ese muelle.
	 * 
	 * @param id
	 * @param local
	 * @param estado
	 * @param numPlazas
	 */
	public Muelle(int id, GPSCoordinate local, boolean estado, int numPlazas){
		setNumPlazas(numPlazas);
		listaPlazas =  new ArrayList<ArrayList<Contenedor>>(numPlazas);
		ArrayList<Contenedor> temp = new ArrayList<Contenedor>(4);
		for(int i = 0; i < 4; i++) {
			temp.add(null);
		}
		for(int i = 0; i < numPlazas; i++ ) {
			listaPlazas.add(temp);
		}
		setEstado(estado);
		setId(id);
		setLocal(local);
	}
	
	/**
	 * Creacion de un muelle vacio
	 */
	public Muelle() {
		listaPlazas = new ArrayList<ArrayList<Contenedor>>();
		setEstado(false);
		setId(0);
		setLocal(null);
	}
	/**
	 * Obtencion de la ocupacion del muelle, es decir si esta lleno o no. En caso de que sus plazas totales esten llenas
	 * el muelle estara lleno.
	 * 
	 * @param muelle
	 * @return true si el muelle esta lleno
	 * @return false si el muelle no esta lleno
	 */
	public boolean muelleLleno() {
		int plazasllenas = plazasLlenas();
		if(plazasllenas == plazas()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Obtencion del numero de plazas vacias del muelle. Una plaza esta vacia si sus cuatro espacios estan vacios.
	 * Si la posicion de mas abajo de la plaza esta vacia, quiere decir que toda la plaza esta vacia.
	 * 
	 * @return cont  nº plazas vacias.
	 */
	public int plazasVacias() {
		int cont = 0;
		for(ArrayList<Contenedor> plaza : listaPlazas) {
			if(plaza.size()==0) {
				cont++;
			}
		}
		return numPlazas;
	}
	
	/**
	 * Obtecion del numero de plazas semillenas del muelle. Una plaza esta semillena si al menos tiene un contenedor
	 * y no tiene las cuatro plazas ocupadas.
	 * 
	 * @return cont numero de plazas semillenas
	 */
	public int plazasSemiLlenas() {
		int cont = 0;
		for(ArrayList<Contenedor> plaza : listaPlazas) {
			if(plaza.isEmpty()) {
				break;
			}
			for(Contenedor contenedor : plaza) {
				if(!contenedor.getTapado()) {
					break;
				}
			}
			if(plaza.size()<4) {
				cont++;
			}
		}
		return cont;
	}
	
	/**
	 * Obtencion del numero de plazas llenas del muelle.
	 * 
	 * @return cont numero de plazas llenas.
	 */
	public int plazasLlenas(){
		int cont = 0;
		for(ArrayList<Contenedor> plaza : listaPlazas) {
			if(plaza.size()==4) {
				cont++;
			}
		}
		return cont;
	}
	
	/**
	 * A partir del codigo de un contenedor, se busca en las plazas dicho contenedor y si se encuentra se indica
	 * en que plaza esta.
	 * 
	 * @param codigo
	 * @return plazaCont la posicion de la plaza en la que esta el contenedor.
	 * @return -1 si no se ha encontrado el contenedor.
	 */	
	public int plazaCont(String codigo) {
		int plazaCont;
		for(ArrayList<Contenedor> plaza : listaPlazas) {
			for(Contenedor contenedor : plaza) {
				if(contenedor.getCodigo().equals(codigo)) {
					plazaCont = listaPlazas.indexOf(plaza);
					return plazaCont;
				}
			}
		}
		return -1;
	}
	
	/**
	 * A partir del codigo de un contenedor, se busca en las plazas dicho contenedor y si se encuentra se indica
	 * en que nivel de la plaza esta.
	 * 
	 * @param codigo
	 * @return nivPlazaCont posicion del nivel de plaza en la que esta el contenedor.
	 * @return -1 si no se ha encontrado el contenedor.
	 */
	public int nivPlazaCont(String codigo) {
		int nivPlazaCont;
		for(ArrayList<Contenedor> plaza : listaPlazas) {
			for(Contenedor contenedor : plaza) {
				if(contenedor.getCodigo().equals(codigo)) {
					nivPlazaCont = plaza.indexOf(contenedor);
					return nivPlazaCont;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Devuelve el numero de plazas que hay en el muelle.
	 *
	 * @return listaPlazas.size tamaño de la lista de plazas, es decir el nº plazas que hay en total.
	 */
	public int plazas() {
		return numPlazas;
	}
	
	/**
	 * Asignar un contenedor a una plaza y apilarlo encima de otro si es posible.
	 * 
	 * @param contenedor
	 */
	public void añadirCont(Contenedor contenedor) {
		int plazasLlenas = plazasLlenas();
		if(numPlazas != plazasLlenas) {
			for(ArrayList<Contenedor> plaza : listaPlazas) {
				for(Contenedor contene : plaza) {
					if(contene.equals(null) && plaza.get(plaza.indexOf(contene)-1).getTapado()) {
						plaza.add(contenedor);
						return;
					}
				}
			}
		}
	}

	/**
	 * Eliminar un contenedor de una plaza en caso de que este. Para comprobar si un contenedor esta en la palza lo hacemos
	 * comparando su codigo.
	 * 
	 * @param codigo
	 */
	public void eliminarCont(String codigo) {
		for(ArrayList<Contenedor> plaza : listaPlazas) {
			for(Contenedor contenedor : plaza) {
				if(contenedor.getCodigo().equals(codigo)) {
					plaza.remove(contenedor);
					return;
				}
			}
		}
	}
	
	/**
	 * Asignacion del estado del muelle.
	 * 
	 * @param estado true si esta disponible, en caso contrario false.
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	/**
	 * Asignacion del identificador del muelle.
	 * 
	 * @param id
	 */
	public void setId(int id) {
		if(id < 0) {
			throw new IllegalArgumentException("El identificador no puede ser negativo");
		}
		else if(id > 99) {
			throw new IllegalArgumentException("El identificador solo puede ser de dos digitos");
		}
		else {
			this.id = id;
		}
	}
	
	/**
	 * Asignacion del punto GPS (coordenadas) del muelle. El punto esta compuetso por una longitud y una latitud.
	 * 
	 * @param local
	 */
	public void setLocal(GPSCoordinate local) {
		this.local = local;
	}

	/**
	 * Asignar lista de plazas. Cada plaza esta compuesta por una lista de 4 contenedores.
	 * 
	 * @param plazas
	 */
	public void setListaPlazas(ArrayList<ArrayList<Contenedor>> listaPlazas) {
		this.listaPlazas = listaPlazas;
	}
	
	/**
	 * Obtener estado del muelle. Si es true, significa que el muelle esta operativo, en caso contrario el 
	 * muelle no esta operativo.
	 * 
	 * @return estado
	 */
	public boolean getEstado() {
		return estado;
	}
	
	/**
	 * Obtener identificacion del muelle, la cual esta compuesta por un numero de dos digitos.
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Obtener localizacion del muelle. Se obtiene un punto, el cual son sus coordenadas, definidas por una
	 * latitud y una longitud.
	 * 
	 * @return local
	 */
	public GPSCoordinate getLocal() {
		return local;
	}
	

	/**
	 * Obtener lista de plazas del muelle. Cada plaza esta compuesta por una lista de 4 contenedores.
	 * 
	 * @return listaPlazas.
	 */
	public ArrayList<ArrayList<Contenedor>> getListaPlazas() {
		return listaPlazas;
	}
	
	/**
	 * Se establece el numero de plazas que tendra el muelle.
	 * 
	 * @param numPlazas
	 */
	public void setNumPlazas(int numPlazas) {
		if(numPlazas < 0) {
			throw new IllegalArgumentException("Al menos una plaza por muelle");
		}
		this.numPlazas = numPlazas;
	}
	
	public int getNumPlazas() {
		return numPlazas;
	}
	
	
}
