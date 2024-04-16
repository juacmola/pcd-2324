package ejercicio4;

import messagepassing.MailBox;

public class Main {

	public static void main(String[] args) {
		MailBox buzonControladorComprador = new MailBox();
		MailBox buzonR2 = new MailBox();
		
		
		HiloControlador controlador = new HiloControlador(buzonControladorComprador);
		controlador.start();
		
		HiloComprador[] compradores = new HiloComprador[30];
		
		for(int i=0;i<30;i++) {
			compradores[i]=new HiloComprador(i, buzonControladorComprador);
		}
		for(int i=0; i< 30; i++) {
			compradores[i].start();
		}
	}

}
