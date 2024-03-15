package ejercicio3;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorMaquina {
	private final static int N_MAQUINAS = 3;
	private ReentrantLock l = new ReentrantLock(true);
	private Condition colaMaquina = l.newCondition();
	//private int maquinas[] = new int[3];
	private LinkedList<Integer> maquinas = new LinkedList<>();
	private MonitorMesa monitorMesa = new MonitorMesa();
	
	public MonitorMaquina() {
		//for (int i = 0; i < maquinas.length; i++) {
			//maquinas[i] = -1;
		//}
		
		for (int i = 0; i < 3; i++) {
			maquinas.add(-1);
		}
	}
	
	private int codigoMaquina(int id) {
		int codigo = -2;
		
		if (maquinas.contains(id)) {
			codigo = maquinas.indexOf(id);
		}
		
		else if (maquinas.contains(-1)) {
			codigo = maquinas.indexOf(-1);
		}
		
		return codigo;
	}
	
	public void solicitarMaquina(int id, int tiempoMaquina, int tiempoMesa) throws InterruptedException {
		l.lock();
		try {
			int codigo = codigoMaquina(id);
			if (codigo >= 0) {
				System.out.println("Cliente " + id + " ha solicitado su servicio en la máquina: " + codigo);
				System.out.println("Tiempo en solicitar el servicio: " + tiempoMaquina);
				//DONE: Añadir en el print la mesa que menos tiempo de cola tenga
				System.out.println("Será atendido en la mesa: " + monitorMesa.mesaMenorTiempoEspera());
				System.out.println("Tiempo en la mesa: " + tiempoMesa);
				//DONE: Añadir tiempos de las mesas antes de entrar en una de ellas
				System.out.println("Tiempo de espera en la mesa1 = " + monitorMesa.getTiempoMesa(). + ", mesa2 = " + + ", mesa3 = " + + ", mesa4 = ");
				//TODO: Finalizar tratamiento
			}
			
			else {
				while(codigoMaquina(id) == -2) {
					colaMaquina.await();
				}
				
				maquinas.add(codigo, id);
				colaMaquina.signalAll();
			}
			
		} finally {
			l.unlock();
		}
	}
	
}
