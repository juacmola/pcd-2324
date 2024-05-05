package ejercicio4;

import messagepassing.MailBox;

/**La clase comprador es usada por cada uno de los hilos comprador. Se
 * encargará de enviar y recibir mensajes popr sus buzones. Establecerá
 * conversación con el controlador. Antes del constructor hemos decidido
 * crear las variables locales.
 * 
 * @author Joaquín Gálvez Díaz - Effect3
 * @author Jorge Urbelz Alonso-Cortés - juacmola
 */
public class HiloComprador extends Thread{
	private MailBox buzonPregunta;
	private MailBox buzonCajaA;
	private MailBox buzonCajaB;
	private MailBox buzonAbandonoCaja;
	private MailBox[] arrayBuzonComunicacion;
	private MailBox imprimir;
	
	private int id;
	private String tiempo;
	private String caja;
	private String mensajeAsignacion;
	private String mensajeConfirmacion;
	private int mensajeImprimir;

	/**El constructor recogerá todos los buzones creados por el programa
	 * principal y los ligará a otros buzones, además del id del hilo.
	 * @param id - Sirve para saber que hilo está imprimiendo
	 * @param pregunta - Envía las veces que un comprador pregunta por una caja
	 * @param cajaA - Envía los mensajes pertenecientes al buzón A
	 * @param cajaB - Envía los mensajes pertenecientes al buzón B
	 * @param abandono - Envía los mensajes de abandono de una caja
	 * @param array - Recoge los mensajes del controlador a él específicamente
	 * @param imprimir - Sirve para pedir permiso de impresión
	 */
	public HiloComprador(int id, MailBox pregunta, MailBox cajaA, MailBox cajaB,
												MailBox abandono, MailBox[] array, MailBox imprimir) {
		this.id=id;
		this.buzonPregunta=pregunta;
		this.buzonCajaA=cajaA;
		this.buzonCajaB=cajaB;
		this.buzonAbandonoCaja=abandono;
		this.arrayBuzonComunicacion = array;
		this.imprimir = imprimir;
		
		this.tiempo="";
		this.caja="";
	}

	/**Cada comprador tendrá 5 fases a la hora de comprar (están recogidas dentro
	 * del método. Lo recorreremos un total de 5 veces, ya que nos lo pide
	 * expresamente el enunciado. Aquí se realiza la comunicación con el
	 * controlador. Se recogeran sus mensajes en el buzón de la posición del
	 * array que le corresponda, y se enviarán otros mensajes al controlador a
	 * través de alguno de los buzones (cada buzón contiene un tipo de mensaje
	 * específico).
	 */
	public void run() {
		for(int i = 0; i < 5; i++) {
			// PASO 1: REALIZA LA COMPRA
			try { Thread.sleep((long)(Math.random() * 1000)); } 
			catch (InterruptedException e) { e.printStackTrace(); }				


			
			// PASO 2: SOLICITAR CAJA
			// PRIMERO PREGUNTA POR LA CAJA QUE ESTÉ LIBRE
			buzonPregunta.send(id);
			mensajeAsignacion = (String) arrayBuzonComunicacion[id].receive();
			String[] partes = mensajeAsignacion.split(",");
			tiempo = partes[0];
			caja = partes[1];
			
			
			// LUEGO SOLICITA ENTRAR EN ESA CAJA
			if (caja.equals("A")) buzonCajaA.send(id);
			else buzonCajaB.send(id);
			mensajeConfirmacion = (String) arrayBuzonComunicacion[id].receive();

			
			
			// PASO 3: PAGAR EN CAJA
			try { Thread.sleep(Integer.parseInt(tiempo) * 1000); }
			catch (InterruptedException e) { e.printStackTrace(); }


			
			// PASO 4: LIBERAR CAJA
			buzonAbandonoCaja.send(id);
			mensajeConfirmacion = (String) arrayBuzonComunicacion[id].receive();

			
			// PASO 5: IMPRIMIR POR PANTALLA
			mensajeImprimir = (int) imprimir.receive();
			System.out.println("Persona " + (id+1) + " ha usado la caja " + caja);
			System.out.println("Tiempo de pago=" + tiempo);
			System.out.println("Thread.sleep(" + tiempo + ")");
			System.out.println("Persona " + (id+1) + " liberando la caja " + caja);
			imprimir.send(mensajeImprimir);
		}
	}
}
