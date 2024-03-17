package ejercicio3;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorMesa {
	private ReentrantLock l = new ReentrantLock(true);
	private Condition colaMesa[] = new Condition[4];
	private int tiempoColaMesa[] = new int[4];
	private int clienteColaMesa[] = new int[4];
	
	public MonitorMesa() {
		for (int i = 0; i < colaMesa.length; i++) {
			tiempoColaMesa[i] = 0;
			clienteColaMesa[i] = -1;
			colaMesa[i] = l.newCondition();
		}
	}
	
	//Getters
	public int[] getTiempoColaMesa() {
		l.lock();
		try {
			return Arrays.copyOf(tiempoColaMesa, tiempoColaMesa.length);
		} finally {
			l.unlock();
		}
	}
	
	public void introducirClienteColaMesa(int mesaAsignada, int tiempoMesa) {
		l.lock();
		try {
			tiempoColaMesa[mesaAsignada] = tiempoColaMesa[mesaAsignada] + tiempoMesa;
		} finally {
			l.unlock();
		}
	}
	
	public int mesaMenorTiempoEspera() {
		l.lock();
		int tiempoActual = tiempoColaMesa[0];
		int mesaActual = 0;
		try {
			for (int i = 1; i < tiempoColaMesa.length; i++) {
				if (tiempoColaMesa[i] < tiempoActual) {
					tiempoActual = tiempoColaMesa[i];
					mesaActual = i;
				}
			}
			
		} finally {
			l.unlock();
		}

		return mesaActual;
	}
	
	public void solicitarMesa(int id, int mesaAsignada, int tiempoMesa) throws InterruptedException {
		l.lock();
		try {
			if (clienteColaMesa[mesaAsignada] == id) {
				clienteColaMesa[mesaAsignada] = -1;
				tiempoColaMesa[mesaAsignada] = tiempoColaMesa[mesaAsignada] - tiempoMesa;
				
				//Codigo para facilitar la depuracion
				System.out.println("Cliente " + id + " ha salido del banco.");
				int numeroMesa = 1;
				System.out.printf("Tiempo de espera en la mesa" + numeroMesa + " = " + tiempoColaMesa[0]);
				numeroMesa++;
				for (int i = 1; i < tiempoColaMesa.length; i++) {
					System.out.printf(", mesa" + numeroMesa + " = " + tiempoColaMesa[i]);
					numeroMesa++;
				}
				System.out.println("\n");
				
				colaMesa[mesaAsignada].signal();
			}
			
			else {
				while(clienteColaMesa[mesaAsignada] > -1) {
					colaMesa[mesaAsignada].await();
				}
				
				clienteColaMesa[mesaAsignada] = id;
				
				//Codigo para facilitar la depuracion
				int numeroMesa = mesaAsignada + 1;
				System.out.println("Cliente " + id + " ha adquirido la mesa" + numeroMesa);
				System.out.println();
			}
	
		} finally {
			l.unlock();
		}
	}
	
}
