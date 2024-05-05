package ejercicio3;

import java.util.Random;

/**
 * La clase principal del programa. En ella, se declaran e inicializan los monitores y los hilos clientes.
 * 
 * @author galve
 *
 */
public class Main {
	private static final int N_CLIENTES = 50;
	
	/**
	 * Método main donde se realiza la ejecución del programa principal.
	 * 
	 * @param args	.
	 */
	public static void main(String[] args) {
		Random r = new Random();
		MonitorMaquina monitorMaquina = new MonitorMaquina();
		MonitorMesa monitorMesa = new MonitorMesa();
		HiloCliente clientes[] = new HiloCliente[N_CLIENTES]; 
		
		for (int i = 0; i < N_CLIENTES; i++) {
			clientes[i] = new HiloCliente(i, r.nextInt(1000), r.nextInt(1000), monitorMaquina, monitorMesa);
			clientes[i].start();
		}
	}
}
