package ejercicio2;

import java.util.Iterator;
import java.util.concurrent.Semaphore;

public class Ejercicio2 {
	public static int vehiculosCruzandoNS = 0;
	public static int vehiculosCruzandoEO = 0;
	public static int peatonCruzando = 0;
	
	public static int vehiculosTotales=0;
	public static int peatonesTotales=0;
	//public static Semaphore V = new Semaphore(1);
	//public static Semaphore P = new Semaphore(0);
	
	public static boolean NS = true;
	public static boolean EO = false;
	public static boolean P = false;
	
    public static void main(String[] args) throws InterruptedException {
//        Thread cruceV = new HiloV();
//        Thread cruceP = new HiloP();

//        cruceV.start();
//        cruceP.start();
        
        //while(vehiculosTotales<=50 || peatonesTotales<=100){
        for (int i=0; i<4; i++) {
        	Thread.sleep(1000);
        	if (NS) {
        		System.out.println("Cruzando NS");
        		NS=false;
        		EO=true;
        	}
        	else if (EO) {
        		System.out.println("Cruzando EO");
        		EO=false;
        		P=true;
        	}
        	else {
        		System.out.println("Cruzando P");
        		P=false;
        		NS=true;
        	}
        }
        
        
        
        
        /*
        try {
        	cruceV.join();
        	cruceP.join();
        }catch (InterruptedException e) {
			e.printStackTrace();
		}*/
        System.out.println("Fin del hilo principal");
    }
}
