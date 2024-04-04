package ejercicio2;

public class CruceP implements Runnable{
//	- TODO:  La gestión del mutex de variables compartidas no se hace bien en el bloque 
//				de código que representar el cruzar. Cogéis el mutex para ir a dormir, 
//				haciendo que ningún otro peatón pueda gestionar nada de forma concurrente durante ese rato

	public void run() {
		while(true) {
			try { Main.mutex.acquire(); } catch (InterruptedException e) {}					// wait(mutex)
//			- TODO: En el protocolo de entrada también hay que comprobar si es mi turno.
//			- TODO: Separar el nº de vehículos cruzando y esperando en dos, un grupo para NS, otro para EO
//			- TODO: Liberar a otros hilos que no sean peatones en caso de que no se puedan desbloquear peatones y se den las condiciones correctas
			if (Main.vehiculosCruzando>0 || Main.peatonCruzando>=10) {				
				Main.peatonesEsperando++;													// nle++
				Main.mutex.release();														// signal(mutex)
				try { Main.SemaforoPeaton.acquire(); } catch (InterruptedException e) {}	// wait(SemaforoPeaton)
				Main.peatonesEsperando--;													// nle++
			}
			Main.peatonCruzando++;															// nl++
			if (Main.peatonesEsperando>0) {
				Main.SemaforoPeaton.release();												// signal(SemaforoPeaton)
			}else Main.mutex.release();														// signal(mutex)
			
//			- TODO: La pantalla no la gestionáis bien cuando el peatón cruza
			try { Main.pantalla.acquire(); } catch (InterruptedException e) {}					// wait(mutex) -> Seccion Critica
			System.out.println("Peatón cruzando");											// Nos guardamos pantalla
			Main.pantalla.release();
			try {Thread.sleep(3000);}catch (InterruptedException e){}
			Main.peatonCruzando--;															// nl--
			
//			- TODO: Mismo para el protocolo de salida, no solo se desbloquea a otros peatones. 
//					Ejemplo, ¿qué pasaría con el último peatón que pasa? Ahora mismo se gestiona 
//					desde el hilo cruce, pero esto como comentaba antes puede dar problemas.
			if (Main.peatonCruzando==0 && Main.vehiculosEsperandoNS>0) {
				Main.SemaforoNS.release();													// signal(SemaforoNS)
			}else Main.mutex.release();														// signal(mutex)
			
			
			try {Thread.sleep(8000);} catch (InterruptedException e) {}
		}
	}
}
