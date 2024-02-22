package ejercicio2;

public class HiloP extends Thread {
	public void run() {
		for(int i=0;i<3;i++) {
			try{
				if (Ejercicio2.peatonCruzando <= 10) {
					Ejercicio2.peatonCruzando++;
					System.out.println("Hay un peaton cruzando");
					Ejercicio2.peatonesTotales++;
				}
				Thread.sleep(3000);
			}
			catch (InterruptedException e){}
			//System.out.println("Liberando hilo");
			Ejercicio2.peatonCruzando--;
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
