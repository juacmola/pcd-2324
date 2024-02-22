package ejercicio2;

public class HiloV extends Thread {
	public void run() {
		for(int i=0;i<3;i++){
			try{
				if (Ejercicio2.vehiculosCruzandoNS <= 4) {
					Ejercicio2.vehiculosCruzandoNS++;
					System.out.println("Hay un coche cruzando");
					Ejercicio2.vehiculosTotales++;
				}
				Thread.sleep(500);
			}
			catch (InterruptedException e){}
			//System.out.println("Liberando hilo");
			Ejercicio2.vehiculosCruzandoNS--;
			
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			try{
				if (Ejercicio2.vehiculosCruzandoEO <= 4) {
					Ejercicio2.vehiculosCruzandoEO++;
					System.out.println("Hay un coche cruzando");
				}
				Thread.sleep(500);
			}
			catch (InterruptedException e){}
			//System.out.println("Liberando hilo");
			Ejercicio2.vehiculosCruzandoEO--;
			
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
