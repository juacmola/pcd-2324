package ejercicio4;

import messagepassing.MailBox;

public class Main {
//	private static int testigo;
	public static void main(String[] args) {
		MailBox buzonPregunta = new MailBox();
		MailBox cajaA = new MailBox();
		MailBox cajaB = new MailBox();
		MailBox buzonRespuesta = new MailBox();
		MailBox imprimir = new MailBox();
		
		MailBox[] arrayBuzon = new MailBox[30];
		for(int i = 0; i < 30; i++) arrayBuzon[i] = new MailBox(); 
		
//		imprimir.send(testigo);
		
		HiloControlador controlador = new HiloControlador(buzonPregunta, cajaA, cajaB, buzonRespuesta, arrayBuzon, imprimir);
		controlador.start();
		
		HiloComprador[] compradores = new HiloComprador[30];
		
		for(int i=0;i<30;i++) compradores[i]=new HiloComprador(i, buzonPregunta, cajaA, cajaB, buzonRespuesta, arrayBuzon, imprimir);
		for(int i=0; i< 30; i++) compradores[i].start();
	}

}
