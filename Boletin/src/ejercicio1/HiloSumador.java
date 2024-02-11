package ejercicio1;

public class HiloSumador implements Runnable {
	private volatile int[] arrayResultadosCompartido;
	
	public HiloSumador(int[] arrayResultadosCompartido){
		this.arrayResultadosCompartido = arrayResultadosCompartido;
	}
	
	public void run() {
		int total = 0;
		
		for (int valor : arrayResultadosCompartido)
			total = total + valor;
		
		System.out.println("Total: " + total);
	}
}
