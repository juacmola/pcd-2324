package ejercicio1;

/**
 * En esta clase se define la funcionalidad del proceso sumador, el cuál lee el array de enteros llamado
 * "arrayResultadosCompartido" para sumar todos sus valores e imprimir por pantalla el resultado.
 * 
 * @author galve
 *
 */
public class HiloSumador implements Runnable {
	private volatile int arrayResultadosCompartido[];
	
	/**
	 * El constructor recibe un array de enteros para sumar sus valores en el método "run".
	 * 
	 * @param arrayResultadosCompartido		array de enteros.
	 */
	public HiloSumador(int arrayResultadosCompartido[]){
		this.arrayResultadosCompartido = arrayResultadosCompartido; 
	}
	
	public void run() {
		int total = 0;
		
		for (int valor : arrayResultadosCompartido)
			total += valor;
		
		System.out.println("Total: " + total);
	}
}
