package ejercicio2;

/**CruceP se encarga de toda la parafernalia de los peatones. Cada uno
 * de los hilos de los peatones recorrerá este código. Al principio se
 * encuentra la condición de entrada. Si el peatón no la cumple, es que
 * es su turno y puede empezar a cruzar. En cambio, si la cumple, debe
 * aguardar a que sea su turno.
 * 
 * @author Joaquín Gálvez Díaz - Effect3
 * @author Jorge Urbelz Alonso-Cortés - juacmola
 */
public class CruceP implements Runnable{
	public void run() {
		while(true) {
			try { 
				Main.mutex.acquire();
				if (Main.turno!=3 || Main.vehiculosCruzandoNS>0 || Main.vehiculosCruzandoEO>0 || Main.peatonCruzando>=10) {				
					Main.peatonesEsperando++;
					if (Main.turno==1 && Main.vehiculosCruzandoNS<4 && Main.vehiculosEsperandoNS>0) Main.SemaforoNS.release();
					else if (Main.turno==2 && Main.vehiculosCruzandoEO<4 && Main.vehiculosEsperandoEO>0) Main.SemaforoEO.release();			
					Main.mutex.release();
					Main.SemaforoPeaton.acquire();
					Main.peatonesEsperando--;
				}


				Main.peatonCruzando++;
				if (Main.peatonCruzando<10 && Main.peatonesEsperando>0) {
					Main.SemaforoPeaton.release();
				}else Main.mutex.release();


				Main.pantalla.acquire();
				System.out.println("Peatón cruzando");
				Main.pantalla.release();
				Thread.sleep(3000);

				Main.mutex.acquire();
				Main.peatonCruzando--;
				
				if (Main.peatonCruzando==0) {
					if (Main.turno==1 && Main.vehiculosEsperandoNS>0) Main.SemaforoNS.release();
					else if (Main.turno==2 && Main.vehiculosEsperandoEO>0) Main.SemaforoEO.release();
				}else if (Main.turno==3 && Main.peatonesEsperando>0) Main.SemaforoPeaton.release();


				Main.mutex.release();
				Thread.sleep(8000);
			
			} catch (InterruptedException e) {}
		}
	}
}
