package ejercicio2;

public class HiloCruce extends Thread{
	private static boolean semaforoNSVerde = true;
	private static boolean semaforoEOVerde = false;
	private static boolean semaforoPVerde = false;
	
	public void run() {
		for(int i=0;i<5;i++){
			try {
				Ejercicio2.NS.acquire();
				semaforoNSVerde = true;
				System.out.println("Semáforo verde para vehículos NS");
				Thread.sleep(5000);
			} catch (InterruptedException e){}
			System.out.println("Liberando semáforo vehiculos EO");
			semaforoNSVerde=false;
			Ejercicio2.EO.release();
			
			try {
				Ejercicio2.EO.acquire();
				semaforoEOVerde = true;
				System.out.println("Semáforo verde para vehículos EO");
				Thread.sleep(5000);
			} catch (InterruptedException e){}
			System.out.println("Liberando semáforo peatones");
			semaforoEOVerde = false;
			Ejercicio2.P.release();
			
			try {
				Ejercicio2.P.acquire();
				semaforoPVerde = true;
				System.out.println("Semáforo verde para peatones");
				Thread.sleep(5000);
			} catch (InterruptedException e){}
			System.out.println("Liberando semáforo vehiculos NS");
			semaforoPVerde = false;
			Ejercicio2.NS.release();
			
		}
	}
	
	public static boolean esNSVerde () {
		return semaforoNSVerde;
	}
	
	public static boolean esEOVerde () {
		return semaforoEOVerde;
	}
	
	public static boolean esPVerde () {
		return semaforoPVerde;
	}
}
