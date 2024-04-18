package ejercicio4;

import messagepassing.CommunicationScheme;

public class HiloComprador extends Thread{
	private CommunicationScheme buzonPregunta;
	private CommunicationScheme buzonCajaA;
	private CommunicationScheme buzonCajaB;
	private CommunicationScheme buzonAbandonoCaja;
	private CommunicationScheme[] arrayBuzonComunicacion;
	private CommunicationScheme imprimir;
	
	private int id;
	private String tiempo;
	private String caja;
	private String mensajeAsignacion;
	private String mensajeConfirmacion;
	private String mensajeImprimir;

	public HiloComprador(int id, CommunicationScheme pregunta, CommunicationScheme cajaA, CommunicationScheme cajaB, CommunicationScheme abandono, CommunicationScheme[] array, CommunicationScheme imprimir) {
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
			int buzon = (int) imprimir.receive();
			System.out.println("Persona " + (id+1) + " ha usado la caja " + caja);
			System.out.println("Tiempo de pago=" + tiempo);
			System.out.println("Thread.sleep(" + tiempo + ")");
			System.out.println("Persona " + (id+1) + " liberando la caja " + caja);
			imprimir.send(buzon);
		}
	}
}
