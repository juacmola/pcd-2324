package ejercicio1;

import java.util.ArrayList;

public class Ejercicio1 {
	
	public static void main(String[] args) {
		ArrayList<Integer> arrayValoresCompartido = new ArrayList<>(110);
		int[] arrayResultadosCompartido = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			
		HiloGenerador g = new HiloGenerador(arrayValoresCompartido);
		HiloConsumidor c0 = new HiloConsumidor(0, arrayValoresCompartido, arrayResultadosCompartido);
		HiloConsumidor c1 = new HiloConsumidor(1, arrayValoresCompartido, arrayResultadosCompartido);
		HiloConsumidor c2 = new HiloConsumidor(2, arrayValoresCompartido, arrayResultadosCompartido);
		HiloConsumidor c3 = new HiloConsumidor(3, arrayValoresCompartido, arrayResultadosCompartido);
		HiloConsumidor c4 = new HiloConsumidor(4, arrayValoresCompartido, arrayResultadosCompartido);
		HiloConsumidor c5 = new HiloConsumidor(5, arrayValoresCompartido, arrayResultadosCompartido);
		HiloConsumidor c6 = new HiloConsumidor(6, arrayValoresCompartido, arrayResultadosCompartido);
		HiloConsumidor c7 = new HiloConsumidor(7, arrayValoresCompartido, arrayResultadosCompartido);
		HiloConsumidor c8 = new HiloConsumidor(8, arrayValoresCompartido, arrayResultadosCompartido);
		HiloConsumidor c9 = new HiloConsumidor(9, arrayValoresCompartido, arrayResultadosCompartido);
		HiloSumador s = new HiloSumador(arrayResultadosCompartido);
			
		Thread tg = new Thread(g);
		Thread tc0 = new Thread(c0);
		Thread tc1 = new Thread(c1);
		Thread tc2 = new Thread(c2);
		Thread tc3 = new Thread(c3);
		Thread tc4 = new Thread(c4);
		Thread tc5 = new Thread(c5);
		Thread tc6 = new Thread(c6);
		Thread tc7 = new Thread(c7);
		Thread tc8 = new Thread(c8);
		Thread tc9 = new Thread(c9);
		Thread ts = new Thread(s);
			
		tg.start();
			
		try {
			tg.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		tc0.start();
		tc1.start();
		tc2.start();
		tc3.start();
		tc4.start();
		tc5.start();
		tc6.start();
		tc7.start();
		tc8.start();
		tc9.start();
			
		try {
			tc0.join();
			tc1.join();
			tc2.join();
			tc3.join();
			tc4.join();
			tc5.join();
			tc6.join();
			tc7.join();
			tc8.join();
			tc9.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		ts.start();
				
		}
	}
