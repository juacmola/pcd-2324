package ejercicio2;

/**La clase HiloCruce se encargará de del cruce del semáforo. Cada
 * cinco segundos irá alterando el valor de la variable turno. Cuando
 * turno = 1, es el momento de cruce de los vehículos provenientes del
 * Norte. Cuando turno = 2, le toca a los que vengan del Este. Por
 * último, los peatones cruzarán cuando turno = 3.
 */
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
