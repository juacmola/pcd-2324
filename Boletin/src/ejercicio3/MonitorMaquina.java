package ejercicio3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorMaquina {
	private ReentrantLock l = new ReentrantLock(true);
	private Condition colaMaquina = l.newCondition();
	private int maquinas[] = new int[3];
	
	public MonitorMaquina() {
		for (int i = 0; i < maquinas.length; i++) {
			maquinas[i] = -1;
		}
	}
	
	private int indexOf(int n) {
		int index = -1;
		for (int i = 0; i < maquinas.length; i++) {
			if (maquinas[i] == n) {
				index = i;
				return index;
			}
		}
		
		return index;
	}
	
	public int solicitarMaquina(int id, int tiempoMaquina, int tiempoMesa, MonitorMesa monitorMesa) throws InterruptedException {
		l.lock();
		int mesaAsignada = -1;
		int numeroMesa, numeroMaquina, maquina;
		try {
			maquina = indexOf(id);
			if (maquina >= 0) {
				mesaAsignada = monitorMesa.mesaMenorTiempoEspera();
				numeroMaquina = maquina + 1;
				System.out.println("Cliente " + id + " ha solicitado su servicio en la máquina: " + numeroMaquina);
				System.out.println("Tiempo en solicitar el servicio: " + tiempoMaquina);
				numeroMesa = mesaAsignada + 1;
				System.out.println("Será atendido en la mesa: " + numeroMesa);
				System.out.println("Tiempo en la mesa: " + tiempoMesa);
				numeroMesa = 1;
				System.out.printf("Tiempo de espera en la mesa" + numeroMesa + " = " + monitorMesa.getTiempoColaMesa()[0]);
				numeroMesa++;
				for (int i = 1; i < monitorMesa.getTiempoColaMesa().length; i++) {
					System.out.printf(", mesa" + numeroMesa + " = " + monitorMesa.getTiempoColaMesa()[i]);
					numeroMesa++;
				}
				System.out.println("\n");
				monitorMesa.introducirClienteColaMesa(mesaAsignada, tiempoMesa);
				maquinas[maquina] = -1;
				colaMaquina.signal();
			}
			
			else {
				while(indexOf(-1) == -1) {
					colaMaquina.await();
				}
				
				//Codigo para facilitar la depuracion
				numeroMaquina = indexOf(-1) + 1;
				System.out.println("Cliente " + id + " ha adquirido la máquina: " + numeroMaquina);
				System.out.println();
				
				maquinas[indexOf(-1)] = id;
			}
			
		} finally {
			l.unlock();
		}
		
		return mesaAsignada;
	}
	
}
