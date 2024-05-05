package ejercicio4;

import messagepassing.MailBox;
import messagepassing.Selector;

/**La clase controlador se encarga de recoger los mensajes de los compradores
 * y enviarles otros devuelta. Definirá de manera aleatoria el tiempo que un
 * comprador estará en una caja (dependiendo del valor aleatorio se le envía
 * a la caja A o la B). Antes del constructor hemos decidido crear las variables
 * locales.
 * 
 * @author Joaquín Gálvez Díaz - Effect3
 * @author Jorge Urbelz Alonso-Cortés - juacmola
 */
public class HiloControlador extends Thread{
	private MailBox buzonPregunta;
	private MailBox buzonCajaA;
	private MailBox buzonCajaB;
	private MailBox buzonAbandonoCaja;
	private MailBox[] arrayBuzonComunicacion;
	
	private Selector s;
	
	private int tiempo;
	private String devuelveAsignacion;
	private int idPreguntaCaja;
	private int idCajaA;
	private int idCajaB;
	
	private int idAbandono;
	private boolean cajaAOcupada;
	private boolean cajaBOcupada;
	
	
	/** El constructor recogerá todos los buzones creados por el programa
	 * principal y los ligará a otros buzones.
	 * @param pregunta - Recoge las veces que un comprador pregunta por una caja
	 * @param A - Recoge los mensajes pertenecientes al buzón A
	 * @param B - Recoge los mensajes pertenecientes al buzón B
	 * @param abandono - Recoge los mensajes de abandono de una caja
	 * @param array - Envía un mensaje a un comprador específico
	 */
	public HiloControlador(MailBox pregunta, MailBox A, MailBox B, MailBox abandono, MailBox[] array) {
		this.buzonPregunta=pregunta;
		this.buzonCajaA=A;
		this.buzonCajaB=B;
		this.buzonAbandonoCaja=abandono;
		this.arrayBuzonComunicacion= array;
		this.tiempo=0;
		this.devuelveAsignacion="";
		this.cajaAOcupada=false;
		this.cajaBOcupada=false;
		
		s = new Selector();
		s.addSelectable(pregunta, false);
		s.addSelectable(A, false);
		s.addSelectable(B, false);
		s.addSelectable(abandono, false);
	}
	
	/**El controlador recibe todo tipo de mensajes por parte de los compradores,
	 * a los que tendrá que responder una cosa u otra. El proceso se realiza
	 * mediante un select (visto en clase de teoría). Proviene de una librería
	 * creada exclusivamente para esta práctica. Dependiendo de unas condiciones
	 * u otras, el controlador abre uno de los buzones y recorre el código de esa
	 * opción.
	 */
	public void run() {
		while(true) {
			buzonPregunta.setGuardValue(true);
			buzonCajaA.setGuardValue(!cajaAOcupada);
			buzonCajaB.setGuardValue(!cajaBOcupada);
			buzonAbandonoCaja.setGuardValue(true);
			switch(s.selectOrBlock()){
				case 1: 
					idPreguntaCaja = (int) buzonPregunta.receive();
					tiempo = (int)(Math.random()*10) + 1;
					if (tiempo >= 5) devuelveAsignacion = Integer.toString(tiempo) + ",A";
					else devuelveAsignacion = Integer.toString(tiempo) + ",B";
					arrayBuzonComunicacion[idPreguntaCaja].send(devuelveAsignacion);
					break;
				case 2: 
					idCajaA = (int) buzonCajaA.receive();
					cajaAOcupada = true;
					arrayBuzonComunicacion[idCajaA].send("ok");
					break;
				case 3: 
					idCajaB = (int) buzonCajaB.receive();
					cajaBOcupada = true;
					arrayBuzonComunicacion[idCajaB].send("ok");
					break;
				case 4:
					idAbandono = (int) buzonAbandonoCaja.receive();
					if (idCajaA == idAbandono) cajaAOcupada=false; 
					else cajaBOcupada=false;
					arrayBuzonComunicacion[idAbandono].send("ok");
					break;
			}
		}
	}
}

