package ejercicio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HiloGenerador implements Runnable {
	private volatile ArrayList<Integer> arrayValoresCompartido;
	
	public HiloGenerador(ArrayList<Integer> arrayValoresCompartido) {
		this.arrayValoresCompartido = arrayValoresCompartido;
	}
	
	public void run(){
		Random r = new Random();
		
		for (int i = 0; i < 10; i++) {
			Collections.addAll(arrayValoresCompartido, r.nextInt(100)+1, r.nextInt(3)+1, r.nextInt(100)+1, r.nextInt(3)+1,
					r.nextInt(100)+1, r.nextInt(3)+1, r.nextInt(100)+1, r.nextInt(3)+1, r.nextInt(100)+1, r.nextInt(3)+1, r.nextInt(100)+1);
		}
		
	}
}
