package ejercicio2;

public class CruceP implements Runnable{
	
	public void run() {
		while(true) {
			try { Main.mutex.acquire(); } catch (InterruptedException e) {}					// wait(mutex)
			if (Main.vehiculosCruzando>0 || Main.peatonCruzando>=10) {				
				Main.peatonesEsperando++;														// nle++
				Main.mutex.release();															// signal(mutex)
				try { Main.SemaforoPeaton.acquire(); } catch (InterruptedException e) {}		// wait(SemaforoPeaton)
				Main.peatonesEsperando--;														// nle++
			}
			Main.peatonCruzando++;															// nl++
			if (Main.peatonesEsperando>0) {
				Main.SemaforoPeaton.release();												// signal(SemaforoPeaton)
			}else Main.mutex.release();														// signal(mutex)
			
			try { Main.mutex.acquire(); } catch (InterruptedException e) {}					// wait(mutex) -> Seccion Critica
			System.out.println("Peatón cruzando");													// Nos guardamos pantalla
			try {Thread.sleep(3000);}catch (InterruptedException e){}
			Main.peatonCruzando--;															// nl--
			
			if (Main.peatonCruzando==0 && Main.vehiculosEsperandoNS>0) {
				Main.SemaforoNS.release();													// signal(SemaforoNS)
			}else Main.mutex.release();														// signal(mutex)
			
			
			try {Thread.sleep(8000);} catch (InterruptedException e) {}
		}
	}
}
