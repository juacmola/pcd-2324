package ejercicio2;

import java.util.concurrent.Semaphore;

public class Main {
	public static int vehiculosCruzando = 0;		// nl
	public static int peatonCruzando = 0;			// nl
	
	public static int vehiculosEsperandoNS=0;			// nle
	public static int vehiculosEsperandoEO=0;			// nle
	public static int peatonesEsperando=0;			// nle
	
	public static Semaphore SemaforoNS = new Semaphore(0);
	public static Semaphore SemaforoEO = new Semaphore(0);
	public static Semaphore SemaforoPeaton = new Semaphore(0);
	public static Semaphore mutex = new Semaphore(1);
	
    public static void main(String[] args) throws InterruptedException {
    	CruceP cruceP = new CruceP();
    	CruceV cruceV = new CruceV();
    	
    	Thread cruce = new HiloCruce();        
        Thread[] hilosPeatones = new Thread[15];
        Thread[] hilosVehiculos = new Thread[8];
        
        cruce.start();
              
        for(int i=0; i < 8; i++) {
        	hilosVehiculos[i] = new Thread(cruceV);
            hilosVehiculos[i].start();
        }
        
        for (int i = 0; i < 15; i++) {
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
