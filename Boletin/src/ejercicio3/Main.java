package ejercicio3;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Random r = new Random();
		MonitorMaquina monitorMaquina = new MonitorMaquina();
		MonitorMesa monitorMesa = new MonitorMesa();
		HiloCliente clientes[] = new HiloCliente[50];
		
		for (int i = 0; i < clientes.length; i++) {
			clientes[i] = new HiloCliente(i, r.nextInt(1000), r.nextInt(1000), monitorMaquina, monitorMesa);
			clientes[i].start();
		}
		
		
	}
}
