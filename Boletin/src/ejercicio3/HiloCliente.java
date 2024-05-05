package ejercicio3;

/**
 * En esta clase se define la funcionalidad de los hilos clientes, cada uno de ellos, solicitará
 * la primera máquina que encuentre disponible para seleccionar un servicio.
 * Tras la selección, se dirigirá a la cola de la mesa con menor tiempo de espera para realizar la gestión
 * que desea.
 * 
 * @author Joaquín Gálvez Díaz - Effect3
 * @author Jorge Urbelz Alonso-Cortés - juacmola
 */
public class HiloCliente extends Thread{
	private int id;
	private int tiempoMaquina;
	private int tiempoMesa;
	private MonitorMaquina monitorMaquina;
	private MonitorMesa monitorMesa;
	
	/**
	 * El constructor de la clase recibe el identificador del cliente,
	 * el tiempo que tardará en seleccionar el servicio en la máquina,
	 * el tiempo que tardará en realizar la gestión en la mesa
	 * y los monitores que definen la funcionalidad que gestiona el acceso a las máquinas y a las mesas.
	 * 
	 * @param id	entero que representa el identificador del cliente.
	 * @param tiempoMaquina		entero que representa el tiempo en la máquina.
	 * @param tiempoMesa		entero que representea el tiempo en la mesa.
	 * @param monitorMaquina	monitor de tipo máquina.
	 * @param monitorMesa		monitor de tipo mesa.
	 */
	public HiloCliente(int id, int tiempoMaquina, int tiempoMesa, MonitorMaquina monitorMaquina, MonitorMesa monitorMesa) {
		this.id = id;
		this.tiempoMaquina = tiempoMaquina;
		this.tiempoMesa = tiempoMesa;
		this.monitorMaquina = monitorMaquina;
		this.monitorMesa = monitorMesa;
	}
	
	public void run() {
		try {
			int maquinaAsignada = monitorMaquina.solicitarMaquina(id);
			Thread.sleep(tiempoMaquina);
			monitorMaquina.liberarMaquina(maquinaAsignada);
			
			int mesaAsignada = monitorMesa.solicitarMesa(id, maquinaAsignada, tiempoMaquina, tiempoMesa);
			Thread.sleep(tiempoMesa);
			monitorMesa.liberarMesa(mesaAsignada, tiempoMesa);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
