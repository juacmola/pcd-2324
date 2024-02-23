package ejercicio2;

public class CruceV implements Runnable{
	private int temp;
	private int tempNS;
	private int tempEO;
	
	public void run() {
		temp = Ejercicio2.vehiculosTotales;
		while(temp<2) {
			tempNS = Ejercicio2.vehiculosCruzandoNS;
			if (HiloCruce.esNSVerde() && tempNS<4) {
				tempNS++;
				Ejercicio2.vehiculosCruzandoNS = tempNS;
				System.out.println("Vehículo cruzando dirección Norte-Sur");
				
				try { Thread.sleep(500);}catch (InterruptedException e){}
				
				temp = Ejercicio2.vehiculosTotales;
				temp++;
				Ejercicio2.vehiculosTotales = temp;
				System.out.println("Vehículos Totales " + temp);
				
				tempNS--;
				Ejercicio2.vehiculosCruzandoNS = tempNS;
				
				try {Thread.sleep(7000);} catch (InterruptedException e) {}
			}else if (HiloCruce.esEOVerde() && tempEO<4) {
				tempEO++;
				Ejercicio2.vehiculosCruzandoEO = tempEO;
				System.out.println("Vehículo cruzando dirección Este-Oeste");
				
				try { Thread.sleep(500);}catch (InterruptedException e){}
				
				temp = Ejercicio2.vehiculosTotales;
				temp++;
				Ejercicio2.vehiculosTotales = temp;
				System.out.println("Vehículos Totales " + temp);
				
				tempEO--;
				Ejercicio2.vehiculosCruzandoEO = tempEO;
				
				try {Thread.sleep(7000);} catch (InterruptedException e) {}
			}
		}
	}
}
