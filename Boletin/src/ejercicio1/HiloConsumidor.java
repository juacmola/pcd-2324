package ejercicio1;

import java.util.ArrayList;

public class HiloConsumidor implements Runnable{
	private int id;
	private volatile ArrayList<Integer> arrayValoresCompartido;
	private volatile int[] arrayResultadosCompartido;
		
	public HiloConsumidor(int id, ArrayList<Integer> arrayValoresCompartido, int[] arrayResultadosCompartido){
		this.id = id;
		this.arrayValoresCompartido = arrayValoresCompartido;
		this.arrayResultadosCompartido = arrayResultadosCompartido;
	}
	
	public void run() {
		int resultado = arrayValoresCompartido.get(id*11);
		int steps = 0;
		int operador, operando;
		int i = id*11 + 1;
		
		while (steps < 5) {
			operador = arrayValoresCompartido.get(i);
			i++;
			operando = arrayValoresCompartido.get(i);
			i++;
			
			switch (operador) {
				case 1:
					resultado = resultado + operando;
					break;
				
				case 2:
					resultado = resultado - operando;
					break;
					
				case 3:
					resultado = resultado * operando;
					break;

			}
			
			steps++;
		}
		
		System.out.println("Hilo " + id + " : " + resultado);
		arrayResultadosCompartido[id] = resultado;
	}
}
