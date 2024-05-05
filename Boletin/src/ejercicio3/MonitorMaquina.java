package ejercicio3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Esta clase representa la funcionalidad que gestiona el acceso a las 3 máquinas disponibles.
 * Cuenta con 2 métodos principales para solicitar y liberar una máquina.
 * 
 * @author galve
 *
 */
public class MonitorMaquina {
	private static final int N_MAQUINAS = 3;
	
	private ReentrantLock l = new ReentrantLock(true);
	private Condition colaMaquina = l.newCondition();
	private int maquinas[] = new int[N_MAQUINAS];
	
	/**
	 * El constructor inicializa a -1 el array que registra que clientes están ocupando las máquinas.
	 */
	public MonitorMaquina() {
		for (int i = 0; i < N_MAQUINAS; i++) {
			maquinas[i] = -1;
		}
	}
	
	/**
	 * Este método devuelve la primera máquina que está disponible (0, 1 o 2) o -1 si todas las máquinas
	 * están ocupadas.
	 * 
	 * @param n		entero.
	 * @return maquinaAsignada		entero.
	 */
	private int getMaquinaDisponible(int n) {
		int maquinaAsignada = -1;
		
		for (int i = 0; i < N_MAQUINAS; i++) {
			if (maquinas[i] == n) {
				maquinaAsignada = i;
				return maquinaAsignada;
			}
		}
		
		return maquinaAsignada;
	}
	
	/**
	 * Método para solicitar el acceso a una máquina.
	 * 
	 * @param id	identificador del cliente.
	 * @return maquinaAsignada		entero que representa la máquina que ha sido asginada al cliente.
	 * @throws InterruptedException .
	 */
	public int solicitarMaquina(int id) throws InterruptedException {
		l.lock();
		int maquinaAsignada;
		
		try {
			while(getMaquinaDisponible(-1) == -1) {
				colaMaquina.await();
			}
			
			maquinaAsignada = getMaquinaDisponible(-1);
			maquinas[maquinaAsignada] = id;
			
		} finally {
			l.unlock();
		}
		
		return maquinaAsignada;
	}
	
	/**
	 * Método mediante el cuál un cliente abandona una máquina.
	 * En caso de que haya algún cliente durmiendo porque esté a la espera de solicitar una máquina se le
	 * despertará.
	 * 
	 * @param maquinaAsignada	entero que representa la máquina que ha sido asginada al cliente.
	 */
	public void liberarMaquina(int maquinaAsignada) {
		l.lock();
		try {
			maquinas[maquinaAsignada] = -1;
			colaMaquina.signal();
			
		} finally {
			l.unlock();
		}
	}
}
