package empresa;

import es.uva.inf.poo.maps.GPSCoordinate;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 * Trayecto de los contenedores. Necesita muelle y puerto de origen, muelle y puerto de destino 
 * , fecha de inicio, fecha de fin y contiene funcionalidades como calcular el precio de un trayecto
 * para un contenedor, etc.
 * @author juatorr
 * @author hectori
 *
 */
public class Trayecto {
	
	private Muelle origen;
	private Puerto origenp;
	private Muelle destino;
	private Puerto destinop;
	private String fechaini;
	private String fechafin;
	
	
	public Trayecto (Muelle origen, Puerto origenp, Muelle destino, Puerto destinop, String fechaini, String fechafin){
		setDestino(destino);
		setDestinop(destinop);
		
		String partes2[] = fechafin.split("/");
		int anofin = Integer.parseInt(partes2[2]);
		int diafin = Integer.parseInt(partes2[0]);
		int mesfin = Integer.parseInt(partes2[1]);
		
		String partes[] = fechaini.split("/");
		int anoini = Integer.parseInt(partes[2]);
		int diaini = Integer.parseInt(partes[0]);
		int mesini = Integer.parseInt(partes[1]);
		
		
		
		if(anofin<anoini || (anoini == anofin && mesfin<mesini) || (anoini == anofin && mesini == mesfin && diafin<diaini)) {
			throw new IllegalArgumentException("La fecha final es menor que la inicial, situacion imposible");
		}
		
		
		setFechaini(fechaini);
		setFechafin(fechafin);
		setOrigen(origen);
		setOrigenp(origenp);
	}
	
	
	/**
	 * Introducimos el muelle de destino, que ¿tendrá un puerto asociado?
	 * @param destino
	 */
	public void setDestino(Muelle destino) {
			this.destino = destino;
	}
	
	/**
	 * Introducir la fecha de fin de trayecto
	 * @param fechafin
	 */
	
	public void setFechafin(String fechafin){
		String partesbuenas[] = fechafin.split("/");
		int anofin = Integer.parseInt(partesbuenas[2]);
		int diafin = Integer.parseInt(partesbuenas[0]);
		int mesfin = Integer.parseInt(partesbuenas[1]);
		if(anofin<1970 || diafin>31 || diafin<0 || mesfin<0 || mesfin>12) {
			throw new IllegalArgumentException("Fecha inicial incorrecta, escriba una fecha válida");
		}
		this.fechafin=fechafin;
	}
	
	/**
	 * Introducir la fecha de inicio 
	 * @param fechaini
	 */
	
	public void setFechaini(String fechaini){
		String partes[] = fechaini.split("/");
		int anoini = Integer.parseInt(partes[2]);
		int diaini = Integer.parseInt(partes[0]);
		int mesini = Integer.parseInt(partes[1]);
		if(anoini<1970 || diaini>31 || diaini<0 || mesini<0 || mesini>12) {
			throw new IllegalArgumentException("Fecha inicial incorrecta, escriba una fecha válida");
		}
		this.fechaini=fechaini;
	}
	
	/**
	 * Su funcion es indicarte el precio de un trayecto de un lugar a otro del mundo en euros. Hay que
	 * introducirle los euros que cuesta cada milla y los euros que cuesta el dia de trayecto.
	 * @param precioxdia
	 * @param precioxmilla
	 * @return
	 */
	
	public double precio(double precioxdia, double precioxmilla){
		if (precioxdia<0 || precioxmilla<0) {
			throw new IllegalArgumentException("El precio es negativo, situacion imposible");
		}
		long start = this.getFechainid();
		long stop = this.getFechafind();
		double costed = (stop-start)*precioxdia;
		double distancia = distancia();
		costed += (distancia*precioxmilla);
		return costed;
		
	}

	
	/**
	 * Nos proporciona la distancia en millas maritimas de un trayecto
	 * @return
	 */
	
	
	public double distancia() {
		
		GPSCoordinate locini = this.getOrigen().getLocal();
		GPSCoordinate locfin = this.getDestino().getLocal();
		return (locini.getDistanceTo(locfin))/1.852;
	
	}
	
	
	public String info() {
		String local = this.getOrigenp().getIdentificador().substring(3, 6);
		String pais = this.getOrigenp().getIdentificador().substring(0,2);
		String local2 = this.getDestinop().getIdentificador().substring(3, 6);
		String pais2 = this.getDestinop().getIdentificador().substring(0,2);


		return "Localidad de puerto origen: " + local + "\n" + "Pais de origen: " + pais + "\n" + "Fecha de inicio de trayecto: " + this.getFechaini()
		+ "\n\n" + "Localidad de destino: " + local2 + "\n" + "Pais de destino: " + pais2 + "\n" + "Fecha de final de trayecto: " + this.getFechafin();
	}
	
	
	/**
	 * Introducir muelle de origen con ¿puerto asociado?
	 * @param origen
	 */
	
	public void setOrigen(Muelle origen) {
		this.origen = origen;
	}
	
	/**
	 * Introduccion de puerto de origen
	 * @param origenp0
	 */
	
	public void setOrigenp(Puerto origenp0) {
		this.origenp = origenp0;
	}
	
	/**
	 * Introduccion de puerto de destino
	 * @param origenp1
	 */
	
	public void setDestinop(Puerto destinop) {
		this.destinop = destinop;
	}
	
	/**
	 * Sacamos el muelle destino con su puerto asociado ¿?
	 * @return
	 */
	
	
	public Muelle getDestino() {
		return destino;
	}
	
	/**
	 * Sacamos el muelle destino con su puerto asociado ¿?
	 * @return
	 */
	
	public long getFechafind() {
		String partes[] = fechafin.split("/");
	    Date fecha = new Date(Integer.parseInt(partes[2]), Integer.parseInt(partes[1]), Integer.parseInt(partes[0]));
		long dias = fecha.getTime();
		dias = TimeUnit.DAYS.convert(dias, TimeUnit.MILLISECONDS);
		return dias;
	}
	
	/**
	 * Sacamos la decha de inicio transformandola de numero a string
	 * @return
	 */
	
	public long getFechainid() {
		String partes[] = fechaini.split("/");
	    Date fecha = new Date(Integer.parseInt(partes[2]), Integer.parseInt(partes[1]), Integer.parseInt(partes[0]));
		long dias = fecha.getTime();
		dias = TimeUnit.DAYS.convert(dias, TimeUnit.MILLISECONDS);
		return dias;
	}
	
	
	/**
	 * Sacamos el puerto de origen
	 * @return
	 */
	
	public Puerto getOrigenp() {
		return origenp;
	}
	
	/**
	 * Sacamos el puerto de destino
	 * @return
	 */
	
	public Puerto getDestinop() {
		return destinop;
	}
	
	
	
	public String getFechafin(){
		return fechafin;
	}
	
	
	public String getFechaini() {
		return fechaini;
	}
	
	/**
	 * Sacamos el muelle de origen con su puerto asociado ¿?
	 * @return
	 */
	
	public Muelle getOrigen() {
		return origen;
	}
	
	
	
	
}
