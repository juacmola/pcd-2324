package ejercicio2;

public class CruceV implements Runnable{
	
	public void run() {
		while(true) {
			try { Main.mutex.acquire(); } catch (InterruptedException e) {}					// wait(mutex)
			if (Main.peatonCruzando>0 || Main.vehiculosCruzando>=4) {
				Main.vehiculosEsperandoNS++;
				Main.mutex.release();															// signal(mutex)
				try { Main.SemaforoNS.acquire(); } catch (InterruptedException e) {}			// wait(SemaforoNS)
				Main.vehiculosEsperandoNS--;
			}
			Main.vehiculosCruzando++;
			if (Main.vehiculosEsperandoNS>0) {
				Main.SemaforoNS.release();													// signal(SemaforoPeaton)
			}else Main.mutex.release();
			
			try { Main.mutex.acquire(); } catch (InterruptedException e) {}					// wait(mutex)
			System.out.println("Vehículo cruzando dirección Norte-Sur");
			try { Thread.sleep(500);}catch (InterruptedException e){}
			Main.vehiculosCruzando--;
			
			if (Main.vehiculosCruzando==0 && Main.vehiculosEsperandoEO>0) {
				Main.SemaforoEO.release();													// signal(SemaforoNS)
			}else Main.mutex.release();														// signal(mutex)
			
			
			try {Thread.sleep(7000);} catch (InterruptedException e) {}
			
			
			try { Main.mutex.acquire(); } catch (InterruptedException e) {}					// wait(mutex)
			if (Main.peatonCruzando>0 || Main.vehiculosCruzando>=4) {
				Main.vehiculosEsperandoEO++;
				Main.mutex.release();															// signal(mutex)
				try { Main.SemaforoEO.acquire(); } catch (InterruptedException e) {}			// wait(SemaforoEO)
				Main.vehiculosEsperandoEO--;
			}	
			Main.vehiculosCruzando++;
			if (Main.vehiculosEsperandoEO>0) {
				Main.SemaforoEO.release();													// signal(SemaforoEO)
			}else Main.mutex.release();
						
			try { Main.mutex.acquire(); } catch (InterruptedException e) {}					// wait(mutex)
			System.out.println("Vehículo cruzando dirección Este-Oeste");
			try { Thread.sleep(500);}catch (InterruptedException e){}
			Main.vehiculosCruzando--;
			
			if (Main.vehiculosCruzando==0 && Main.peatonesEsperando>0) {
				Main.SemaforoPeaton.release();												// signal(SemaforoNS)
			}else Main.mutex.release();														// signal(mutex)
			
			
			try {Thread.sleep(7000);} catch (InterruptedException e) {}
				
		}
	}
}
