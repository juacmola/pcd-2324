package ejercicio2;

public class HiloCruce extends Thread{	
//	- DONE: En lugar de hacer un signal sobre el semáforo, lo correcto sería que el cruce gestione una variable 
//	"turno" que se actualice. Si lo hacemos así, podríamos liberar a un hilo cuando no debería.
	
	public void run() {
		for(int i=0;i<5;i++){
			try { Main.pantalla.acquire(); } catch (InterruptedException e) {}
			System.out.println("Semáforo verde para vehículos NS");
			Main.turno=1;
			Main.pantalla.release();
			try { Thread.sleep(3000); } catch (InterruptedException e){}
			
			try { Main.pantalla.acquire(); } catch (InterruptedException e) {}
			System.out.println("Semáforo verde para vehículos EO");
			Main.turno=2;
			Main.pantalla.release();
			try { Thread.sleep(3000); } catch (InterruptedException e) {}
			
			try { Main.pantalla.acquire(); } catch (InterruptedException e) {}
			System.out.println("Semáforo verde para peatones");
			Main.turno=3;
			Main.pantalla.release();
			try { Thread.sleep(3000); } catch (InterruptedException e){}
		
		}
	}
}
