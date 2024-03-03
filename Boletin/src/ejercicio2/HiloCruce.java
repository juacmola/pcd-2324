package ejercicio2;

public class HiloCruce extends Thread{
	public void run() {
		for(int i=0;i<5;i++){
			System.out.println("Semáforo verde para vehículos NS");
			Main.SemaforoNS.release();
			try { Thread.sleep(3000); } catch (InterruptedException e){}
			
			System.out.println("Semáforo verde para vehículos EO");
			Main.SemaforoEO.release();
			try { Thread.sleep(3000); } catch (InterruptedException e) {}
			
			System.out.println("Semáforo verde para peatones");
			Main.SemaforoPeaton.release();
			try { Thread.sleep(3000); } catch (InterruptedException e){}
		
		}
	}
}
