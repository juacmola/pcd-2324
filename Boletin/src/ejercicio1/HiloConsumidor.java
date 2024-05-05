package ejercicio1;

/**
 * En esta clase se define la funcionalidad de los procesos consumidores.
 * Cada uno de ellos, lee 11 posiciones consecutivas del array de enteros "arrayValoresCompartido",
 * calcula el resultado de las operaciones, lo almacena en otro array de enteros llamado
 * "arrayResultadosCompartido" e imprime por pantalla su id junto al resultado obtenido. 
 * 
 * @author galve
 *
 */
public class HiloConsumidor implements Runnable{
	private int id;
	private volatile int arrayValoresCompartido[];
	private volatile int arrayResultadosCompartido[];
	
	/**
	 * El constructor recibe el identificador del proceso y 2 arrays de enteros,
	 * uno para leer las operaciones y otro para almacenar el resultado calculado.
	 * 
	 * @param id	entero que representa el identificador del proceso.
	 * @param arrayValoresCompartido	array de enteros.
	 * @param arrayResultadosCompartido		array de enteros.
	 */
	public HiloConsumidor(int id, int arrayValoresCompartido[], int arrayResultadosCompartido[]){
		this.id = id;
		this.arrayValoresCompartido = arrayValoresCompartido; 
		this.arrayResultadosCompartido = arrayResultadosCompartido;
	}
	
	public void run() {
		int longitudSubArray = Ejercicio1.N_VALORES / Ejercicio1.N_CONSUMIDORES;
		int inicioSubArray = id * longitudSubArray;
		int finalSubArray = inicioSubArray + Ejercicio1.N_CONSUMIDORES;
		int resultado = arrayValoresCompartido[inicioSubArray];
		int operador, operando;
		
		for (int i = inicioSubArray + 1; i < finalSubArray; i += 2) {
			operador = arrayValoresCompartido[i]; 
			operando = arrayValoresCompartido[i + 1];
			
			switch (operador) {
				case 1:
					resultado += operando;
					break;
				
				case 2:
					resultado -= operando;
					break;
					
				case 3:
					resultado *= operando;
					break;

			}
		}
		
		Ejercicio1.l.lock();
		try {
			System.out.println("Hilo " + id + " : " + resultado);
		} finally {
			Ejercicio1.l.unlock();
		}
		
		arrayResultadosCompartido[id] = resultado;
	}
}
