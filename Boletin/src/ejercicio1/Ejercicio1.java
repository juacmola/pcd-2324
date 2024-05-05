package ejercicio1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * La clase principal del programa. En ella se declaran todas las variables necesarias para la correcta
 * ejecución del programa.
 * 
 * @author Joaquín Gálvez Díaz - Effect3
 * @author Jorge Urbelz Alonso-Cortés - juacmola
 *
 */
public class Ejercicio1 {
	public static final int N_VALORES = 110;
	public static final int N_CONSUMIDORES = 10;
	
	public static ReentrantLock l = new ReentrantLock();
	
	/**
	 * Método principal del programa donde se realiza la ejecución de los distintos procesos que lo
	 * componen. El cuál sigue el siguiente orden: Primero se ejecuta el proceso generador, tras su
	 * finalización se ejecutan los procesos consumidores en concurrencia y por último, se ejecuta
	 * el proceso sumador.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int arrayValoresCompartido[] = new int[N_VALORES];
		int arrayResultadosCompartido[] = new int[N_CONSUMIDORES];
		HiloConsumidor consumidores[] = new HiloConsumidor[N_CONSUMIDORES];
		Thread tConsumidores[] = new Thread[N_CONSUMIDORES];
		
		HiloGenerador g = new HiloGenerador(arrayValoresCompartido);
		
		for (int i = 0; i < N_CONSUMIDORES; i++)
			consumidores[i] = new HiloConsumidor(i, arrayValoresCompartido, arrayResultadosCompartido);
		
		HiloSumador s = new HiloSumador(arrayResultadosCompartido);
			
		Thread tg = new Thread(g);
		
		for (int i = 0; i < N_CONSUMIDORES; i++)
			tConsumidores[i] = new Thread(consumidores[i]);
		
		Thread ts = new Thread(s);
			
		tg.start();
			
		try {
			tg.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < N_CONSUMIDORES; i++)
			tConsumidores[i].start();
			
		try {
			for (int i = 0; i < N_CONSUMIDORES; i++)
				tConsumidores[i].join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		ts.start();
				
		}
}
