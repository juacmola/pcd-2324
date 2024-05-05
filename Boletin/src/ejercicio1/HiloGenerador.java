package ejercicio1;

import java.util.Random;

/**
 * En esta clase se define la funcionalidad del proceso generador, el cuál introduce un número 
 * y una operación (codificada con un entero, suma = 1, resta = 2 y multiplicación = 3) alternativamente
 * en un array de enteros de 110 posiciones, "arrayValoresCompartido".
 * 
 * @author galve
 *
 */
public class HiloGenerador implements Runnable {
	private volatile int arrayValoresCompartido[];
	
	/**
	 * El constructor recibe un array de enteros que será rellenado por el método "run" de la clase.
	 * @param arrayValoresCompartido	array de enteros.
	 */
	public HiloGenerador(int arrayValoresCompartido[]) {
		this.arrayValoresCompartido = arrayValoresCompartido;
	}
	
	public void run() {
		int longitudSubArray = Ejercicio1.N_VALORES / Ejercicio1.N_CONSUMIDORES;
		Random r = new Random();
		int valor;
		
		for (int i = 0; i < Ejercicio1.N_VALORES; i++) {
			valor = i % longitudSubArray;
			
			if (valor % 2 == 0)
				arrayValoresCompartido[i] = r.nextInt(100) + 1;
			
			else
				arrayValoresCompartido[i] = r.nextInt(3) + 1;
		}
	}
}
