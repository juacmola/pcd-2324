package ejercicio3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Esta clase representa la funcionalidad que gestiona el acceso a las 4 mesas disponibles,
 * cada una con una cola propia.
 * Cuenta con 2 métodos principales para solicitar y liberar una mesa.
 * 
 * @author Joaquín Gálvez Díaz - Effect3
 * @author Jorge Urbelz Alonso-Cortés - juacmola
 */
public class MonitorMesa {
	private static final int N_MESAS = 4;
	
	private ReentrantLock l = new ReentrantLock(true);
	private Condition colaMesa[] = new Condition[N_MESAS];
	private int tiempoColaMesa[] = new int[N_MESAS];
	private int clienteColaMesa[] = new int[N_MESAS];
	
	/**
	 * El constructor inicializa a 0 el array que registra el tiempo de cola de cada mesa y
	 * a -1 el array que registra que clientes están ocupando las mesas.
	 */
	public MonitorMesa() {
		for (int i = 0; i < N_MESAS; i++) {
			tiempoColaMesa[i] = 0;
			clienteColaMesa[i] = -1;
			colaMesa[i] = l.newCondition();
		}
	}
	
	/**
	 * Este método devuelve un entero que representa la mesa con menor tiempo de espera.
	 * 
	 * @return mesaActual entero.
	 */
	private int mesaMenorTiempoEspera() {
		int tiempoActual = tiempoColaMesa[0];
		int mesaActual = 0;
		
		for (int i = 1; i < N_MESAS; i++) {
			if (tiempoColaMesa[i] < tiempoActual) {
				tiempoActual = tiempoColaMesa[i];
				mesaActual = i;
			}
		}

		return mesaActual;
	}
	
	/**
	 * Método para imprimir por pantalla los tiempos de espera que le llevarán al cliente
	 * a tomar la decisión de a qué mesa dirigirse y solicitar el acceso a ella.
	 * 
	 * @param id 	entero que representa el identificador del cliente.
	 * @param maquinaAsignada	entero que representa la máquina que ha sido asginada al cliente.
	 * @param tiempoMaquina		entero que representa el tiempo en la máquina.
	 * @param tiempoMesa		entero que representa el tiempo en la mesa.
	 * @return mesaAsignada		entero que representa la mesa que ha sido asginada al cliente.
	 * @throws InterruptedException
	 */
	public int solicitarMesa(int id, int maquinaAsignada, int tiempoMaquina, int tiempoMesa) throws InterruptedException {
		l.lock();
		int mesaAsignada;
		try {
			mesaAsignada = mesaMenorTiempoEspera();
			int numeroMaquina = maquinaAsignada + 1;
			System.out.println("Cliente " + id + " ha solicitado su servicio en la máquina: " + numeroMaquina);
			System.out.println("Tiempo en solicitar el servicio: " + tiempoMaquina);
			int numeroMesa = mesaAsignada + 1;
			System.out.println("Será atendido en la mesa: " + numeroMesa);
			System.out.println("Tiempo en la mesa = " + tiempoMesa);
			numeroMesa = 1;
			System.out.printf("Tiempo de espera en la mesa" + numeroMesa + "= " + tiempoColaMesa[0]);
			numeroMesa++;
			for (int i = 1; i < N_MESAS; i++) {
				System.out.printf(", mesa" + numeroMesa + "= " + tiempoColaMesa[i]);
				numeroMesa++;
			}
			System.out.println("\n");
			
			tiempoColaMesa[mesaAsignada] = tiempoColaMesa[mesaAsignada] + tiempoMesa;
			
			while(clienteColaMesa[mesaAsignada] > -1) {
				colaMesa[mesaAsignada].await();
			}
			
			clienteColaMesa[mesaAsignada] = id;
			
		} finally {
			l.unlock();
		}
		
		return mesaAsignada;
	}
	
	/**
	 * Método mediante el cuál un cliente abandona una mesa.
	 * En caso de que haya otro cliente durmiendo en la cola de esta mesa porque esté a la espera
	 * de solicitarla se le despertará.
	 * 
	 * @param mesaAsignada		entero que representa la mesa que ha sido asginada al cliente.
	 * @param tiempoMesa		entero que representa el tiempo en la mesa.
	 */
	public void liberarMesa(int mesaAsignada, int tiempoMesa) {
		l.lock();
		try {
			clienteColaMesa[mesaAsignada] = -1;
			tiempoColaMesa[mesaAsignada] = tiempoColaMesa[mesaAsignada] - tiempoMesa;
			colaMesa[mesaAsignada].signal();
			
		} finally {
			l.unlock();
		}
	}
}
