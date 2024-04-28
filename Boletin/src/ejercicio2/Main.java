package ejercicio2;

import java.util.concurrent.Semaphore;

public class Main {
	public static int vehiculosCruzandoNS = 0;
	public static int vehiculosCruzandoEO = 0;
	public static int peatonCruzando = 0;
	
	public static int vehiculosEsperandoNS = 0;
	public static int vehiculosEsperandoEO = 0;
	public static int peatonesEsperando = 0;
	
	public static int turno = 0;						// 1 = NS, 2 = EO, 3 = P
	
	public static Semaphore SemaforoNS = new Semaphore(0);
	public static Semaphore SemaforoEO = new Semaphore(0);
	public static Semaphore SemaforoPeaton = new Semaphore(0);
	public static Semaphore mutex = new Semaphore(1);
	public static Semaphore pantalla = new Semaphore(1);

	public static void main(String[] args) throws InterruptedException {
		CruceP cruceP = new CruceP();
		CruceV cruceV = new CruceV();

		Thread cruce = new HiloCruce();        
		Thread[] hilosPeatones = new Thread[100];
		Thread[] hilosVehiculos = new Thread[50];

		cruce.start();

		for(int i=0; i < 50; i++) {
			hilosVehiculos[i] = new Thread(cruceV);
			hilosVehiculos[i].start();
		}

		for (int i = 0; i < 100; i++) {
			hilosPeatones[i] = new Thread(cruceP);
			hilosPeatones[i].start();
		}

		try {
			cruce.join();
			for (Thread hiloPeaton : hilosPeatones) { hiloPeaton.join(); }
			for (Thread hiloVehiculo : hilosVehiculos) { hiloVehiculo.join(); }
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin del hilo principal");
	}
}
