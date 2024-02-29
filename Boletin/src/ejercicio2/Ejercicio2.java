package ejercicio2;

import java.util.concurrent.Semaphore;

public class Ejercicio2 {
	public static int vehiculosCruzandoNS = 0;
	public static int vehiculosCruzandoEO = 0;
	public static int peatonCruzando = 0;
	
	public static int vehiculosTotales=0;
	public static int peatonesTotales=0;
	
	public static Semaphore NS = new Semaphore(1);
	public static Semaphore EO = new Semaphore(0);
	public static Semaphore P = new Semaphore(0);
	
    public static void main(String[] args) throws InterruptedException {
    	CruceP cruceP = new CruceP();
    	CruceV cruceV = new CruceV();
    	
    	Thread cruce = new HiloCruce();        
        Thread[] hilosPeatones = new Thread[15];
        Thread[] hilosVehiculos = new Thread[8];
        
        cruce.start();
               
        for (int i = 0; i < 15; i++) {
        	hilosVehiculos[i] = new Thread(cruceV);
            hilosVehiculos[i].start();
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
