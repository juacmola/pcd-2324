package ejercicio3;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorMesa {
	private ReentrantLock l = new ReentrantLock(true);
	private Condition colaMesa1 = l.newCondition();
	private Condition colaMesa2 = l.newCondition();
	private Condition colaMesa3 = l.newCondition();
	private Condition colaMesa4 = l.newCondition();
	private LinkedList<Integer> tiempoMesa = new LinkedList<>();
	
	public MonitorMesa() {
		for (int i = 0; i < 3; i++) {
			tiempoMesa.add(0);
		}
	}
	
	//Getters
	public List<Integer> getTiempoMesa() {
		return Collections.unmodifiableList(tiempoMesa);
	}
	
	public int mesaMenorTiempoEspera() {
		int tiempoActual = 0;
		int mesaActual = 0;
		
		for (int i = 0; i < tiempoMesa.length; i++) {
			if (tiempoMesa[i] < tiempoActual) {
				tiempoActual = tiempoMesa[i];
				mesaActual = i;
			}
		}
		
		return mesaActual;
	}
	
	public void solicitarMesa() {
		
	}
	
}
