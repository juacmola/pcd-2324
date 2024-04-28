package ejercicio2;

public class HiloCruce extends Thread{	
	public void run() {
		for(int i=0;i<5;i++){
			try { 
				Main.turno=1;
				Main.pantalla.acquire(); 
				System.out.println("Semáforo verde para vehículos NS");
				Main.pantalla.release();
				Thread.sleep(5000);
				
				Main.turno=2;
				Main.pantalla.acquire();
				System.out.println("Semáforo verde para vehículos EO");
				Main.pantalla.release();
				Thread.sleep(5000);
				
				Main.turno=3;
				Main.pantalla.acquire();
				System.out.println("Semáforo verde para peatones");
				Main.pantalla.release();
				Thread.sleep(5000);
				
			} catch (InterruptedException e) {}
		}
	}
}
