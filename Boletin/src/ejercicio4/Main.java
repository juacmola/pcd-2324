package ejercicio4;

import messagepassing.MailBox;

/**La clase principal del programa. Se encargará de inicializar todas
 * la variable de la pantalla (para activarla) y los buzones.
 */
public class Main {
	private static int activaImpresora;
	/**Crea los buzones (como habrá 30 cleintes debemos crear un array de
	 * 30 posiciones, uno para cada cliente. Aquí recibiran sus mensajes),
	 * el hilo controlador y los 30 hilos de los clientes/compradores.
	 * @param args
	 */
	public static void main(String[] args) {
		MailBox buzonPregunta = new MailBox();
		MailBox cajaA = new MailBox();
		MailBox cajaB = new MailBox();
		MailBox buzonRespuesta = new MailBox();
		MailBox imprimir = new MailBox();
		
		MailBox[] arrayBuzon = new MailBox[30];
		for(int i = 0; i < 30; i++) arrayBuzon[i] = new MailBox(); 
		
		imprimir.send(activaImpresora);
		
		HiloControlador controlador = new HiloControlador(buzonPregunta, cajaA, cajaB, buzonRespuesta, 
																																											arrayBuzon);
		controlador.start();
		
		HiloComprador[] compradores = new HiloComprador[30];
		
		for(int i=0;i<30;i++) {
			compradores[i]=new HiloComprador(i, buzonPregunta, cajaA, cajaB, buzonRespuesta, 
																																arrayBuzon, imprimir);
			compradores[i].start();
		}
	}

}
