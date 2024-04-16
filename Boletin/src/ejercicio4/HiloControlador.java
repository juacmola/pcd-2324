package ejercicio4;

import messagepassing.CommunicationScheme;

public class HiloControlador extends Thread{
	private CommunicationScheme buzonCC;
	private int tiempo;
	private String caja;
	
	public HiloControlador(CommunicationScheme buzonCC) {
		this.buzonCC=buzonCC;
		this.tiempo=0;
		this.caja="Ninguna";
	}
	
	public void run() {
		while(true) {
			
			
			tiempo = (int)(Math.random()*10);
			if (tiempo >= 5) caja = "A";
			else caja = "B";
//			buzon.send(caja,tiempo);
			
		}
		
	}
}
