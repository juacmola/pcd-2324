package ejercicio2;

public class CruceP implements Runnable{
	private int tempTotal;
	private int temp;
	
	public void run() {
		tempTotal = Ejercicio2.peatonesTotales;
		while(tempTotal<100) {
			temp = Ejercicio2.peatonCruzando;
			if (HiloCruce.esPVerde() && temp<10) {
				temp++;
				Ejercicio2.peatonCruzando = temp;
				System.out.println("Peatón cruzando");
				
				try {Thread.sleep(3000);}catch (InterruptedException e){}
				
				tempTotal = Ejercicio2.peatonesTotales;
				tempTotal++;
				Ejercicio2.peatonesTotales = tempTotal;
				System.out.println("Peatones Totales " + tempTotal);
				
				temp--;
				Ejercicio2.peatonCruzando = temp;
				
				try {Thread.sleep(8000);} catch (InterruptedException e) {}
			}
		}
	}
}
