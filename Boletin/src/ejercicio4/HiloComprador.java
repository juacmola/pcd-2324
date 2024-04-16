package ejercicio4;

import messagepassing.CommunicationScheme;

public class HiloComprador extends Thread{
	private CommunicationScheme buzonCC;
	private int id;
	private int tiempo;
	private String caja;
	private String mensajeConfirmacion;
	private String mensajeImprimir;
	
	public HiloComprador(int id, CommunicationScheme buzonCC) {
		this.id=id;
		this.buzonCC=buzonCC;
		this.tiempo=0;
		this.caja="Ninguna";
	}
	
	public void run() {
		
		while(true) {
			for(int i = 0; i < 5; i++) {
				
				// PASO 1: REALIZA LA COMPRA
				try { Thread.sleep((long)(Math.random() * 1000)); } 
				catch (InterruptedException e) { e.printStackTrace(); }				
				
				
				// PASO 2: SOLICITAR CAJA
//				caja, tiempo= buzon.receive();
				
				
				// PASO 3: PAGAR EN CAJA
				try { Thread.sleep(tiempo); }
				catch (InterruptedException e) { e.printStackTrace(); }
				
				
				// PASO 4: LIBERAR CAJA
				while (!mensajeConfirmacion.equals("ok")) {
//					buzon.send(caja);
//					mensajeConfirmacion = buzon.receive();
				}
				
				
				// PASO 5: IMPRIMIR POR PANTALLA
				while(!mensajeImprimir.equals("ok")) {
//					mensajeImprimir = buzonImprimir.receive();
				}
				System.out.println("Persona " + (id+1) + " ha usado la caja " + caja);
				System.out.println("Tiempo de pago=" + tiempo);
				System.out.println("Thread.sleep(" + tiempo + ")");
				System.out.println("Persona " + id + " liberando la caja " + caja);
//				buzonCC.send();
			}
		}
	}
}
