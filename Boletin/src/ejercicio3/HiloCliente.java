package ejercicio3;

public class HiloCliente extends Thread{
	private int id;
	private int tiempoMaquina;
	private int tiempoMesa;
	private MonitorMaquina monitorMaquina;
	private MonitorMesa monitorMesa;
	
	public HiloCliente(int id, int tiempoMaquina, int tiempoMesa, MonitorMaquina monitorMaquina, MonitorMesa monitorMesa) {
		this.id = id;
		this.tiempoMaquina = tiempoMaquina;
		this.tiempoMesa = tiempoMesa;
		this.monitorMaquina = monitorMaquina;
		this.monitorMesa = monitorMesa;
	}
	
	public void run() {
		try {
			monitorMaquina.solicitarMaquina(id, tiempoMaquina, tiempoMesa, monitorMesa);
			Thread.sleep(tiempoMaquina);
			
			//Codigo para facilitar la depuracion
			System.out.println("Cliente " + id + " ha finalizado la operacion en la maquina.");
			System.out.println();
			
			int mesaAsignada = monitorMaquina.solicitarMaquina(id, tiempoMaquina, tiempoMesa, monitorMesa);
			
			monitorMesa.solicitarMesa(id, mesaAsignada, tiempoMesa);
			Thread.sleep(tiempoMesa);
			
			//Codigo para facilitar la depuracion
			System.out.println("Cliente " + id + " ha finalizado la operacion en la mesa.");
			System.out.println();
			
			monitorMesa.solicitarMesa(id, mesaAsignada, tiempoMesa);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
