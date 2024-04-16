package ejercicio4;

import java.io.Serializable;

public class CajaTiempo implements Serializable {
	private int tiempo;
	private String caja;
	
	public CajaTiempo(int t, String c) {
		this.tiempo=t;
		this.caja=c;
	}
	
	public int getTiempo() {
		return tiempo;
	}
	
	public String getCaja() {
		return caja;
	}
}
