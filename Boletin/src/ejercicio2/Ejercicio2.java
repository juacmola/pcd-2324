package ejercicio2;

import java.util.Iterator;
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
        Thread CP = new Thread(cruceP);
        Thread CV = new Thread(cruceV);
        
        cruce.start();
        CP.start();
        CV.start();
        
        try {
        	cruce.join();
        	CP.join();
        	CV.join();
        }catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("Fin del hilo principal");
    }
}
