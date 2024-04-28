package ejercicio2;

public class CruceV implements Runnable{
	public void run() {
		while(true) {
			try { 
				Main.mutex.acquire();
				if (Main.turno!=1 || Main.peatonCruzando>0 || Main.vehiculosCruzandoEO>0 || Main.vehiculosCruzandoNS>=4) {
					Main.vehiculosEsperandoNS++;
					if (Main.turno==2 && Main.vehiculosCruzandoEO<4 && Main.vehiculosEsperandoEO>0) Main.SemaforoEO.release();
					else if (Main.turno==3 && Main.peatonCruzando<10 && Main.peatonesEsperando>0) Main.SemaforoPeaton.release();
					Main.mutex.release();
					Main.SemaforoNS.acquire();
					Main.vehiculosEsperandoNS--;
				}
				
				Main.vehiculosCruzandoNS++;
				if (Main.vehiculosCruzandoNS<4 && Main.vehiculosEsperandoNS>0) {
					Main.SemaforoNS.release();
				}else Main.mutex.release();

				Main.pantalla.acquire();
				System.out.println("Vehículo cruzando dirección Norte-Sur");
				Main.pantalla.release();
				Thread.sleep(500);
				
				Main.mutex.acquire();
				Main.vehiculosCruzandoNS--;

				if (Main.vehiculosCruzandoNS==0) {
					if (Main.turno==2 && Main.vehiculosEsperandoEO>0) Main.SemaforoEO.release();
					else if (Main.turno==3 && Main.peatonesEsperando>0) Main.SemaforoPeaton.release();
				}else if (Main.turno==1 && Main.vehiculosEsperandoNS>0) Main.SemaforoNS.release();

				Main.mutex.release();
				Thread.sleep(7000);

				//*******************************************************************
				//*******************************************************************

				Main.mutex.acquire();
				if (Main.turno!=2 || Main.peatonCruzando>0 || Main.vehiculosCruzandoNS>0 || Main.vehiculosCruzandoEO>=4) {
					Main.vehiculosEsperandoEO++;
					if (Main.turno==1 && Main.vehiculosCruzandoNS<4 && Main.vehiculosEsperandoNS>0) Main.SemaforoNS.release();
					else if (Main.turno==3 && Main.peatonCruzando<10 && Main.peatonesEsperando>0) Main.SemaforoPeaton.release();
					Main.mutex.release();
					Main.SemaforoEO.acquire();
					Main.vehiculosEsperandoEO--;
				}	
				
				Main.vehiculosCruzandoEO++;
				if (Main.vehiculosCruzandoEO<4 && Main.vehiculosEsperandoEO>0) {
					Main.SemaforoEO.release();
				}else Main.mutex.release();

				Main.pantalla.acquire();
				System.out.println("Vehículo cruzando dirección Este-Oeste");
				Main.pantalla.release();
				Thread.sleep(500);
				
				Main.mutex.acquire();
				Main.vehiculosCruzandoEO--;

				if (Main.vehiculosCruzandoEO==0) {
					if (Main.turno==1 && Main.vehiculosEsperandoNS>0) Main.SemaforoNS.release();
					else if (Main.turno==3 && Main.peatonesEsperando>0) Main.SemaforoPeaton.release();
				}else if (Main.turno==2 && Main.vehiculosEsperandoEO>0) Main.SemaforoEO.release();


				Main.mutex.release();
				Thread.sleep(7000);
			
			} catch (InterruptedException e) {}
		}
	}
}
