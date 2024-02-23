package ejercicio2;

public class CruceP implements Runnable{
	private int temp;
	private int temp2;
	
	public void run() {
		temp = Ejercicio2.peatonesTotales;
		while(temp<2) {
			temp2 = Ejercicio2.peatonCruzando;
			if (HiloCruce.esPVerde() && temp2<10) {
				temp2++;
				Ejercicio2.peatonCruzando = temp2;
				System.out.println("Peatón cruzando");
				
				try {Thread.sleep(3000);}catch (InterruptedException e){}
				
				temp = Ejercicio2.peatonesTotales;
				temp++;
				Ejercicio2.peatonesTotales = temp;
				System.out.println("Peatones Totales " + temp);
				
				temp2--;
				Ejercicio2.peatonCruzando = temp2;
				
				try {Thread.sleep(8000);} catch (InterruptedException e) {}
			}
		}
	}
}
