package ejercicio2;

public class CruceV implements Runnable{
//	- TODO:  La gestión del mutex de variables compartidas no se hace bien en el bloque 
//				de código que representar el cruzar. Cogéis el mutex para ir a dormir, 
//				haciendo que ningún otro peatón pueda gestionar nada de forma concurrente durante ese rato
	public void run() {
		while(true) {
			try { Main.mutex.acquire(); } catch (InterruptedException e) {}					// wait(mutex)
//			- TODO: En el protocolo de entrada también hay que comprobar si es mi turno.
//			- TODO: Separar el nº de vehículos cruzando y esperando en dos, un grupo para NS, otro para EO
//			- TODO: Liberar a otros hilos que no sean peatones en caso de que no se puedan desbloquear peatones y se den las condiciones correctas
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
			
//			- TODO: La pantalla no la gestionáis bien cuando el vehículo cruza
			try { Main.pantalla.acquire(); } catch (InterruptedException e) {}					// wait(mutex)
			System.out.println("Vehículo cruzando dirección Norte-Sur");
			Main.pantalla.release();
			try { Thread.sleep(500);}catch (InterruptedException e){}
			Main.vehiculosCruzando--;
						
//			- TODO: Mismo para el protocolo de salida, no solo se desbloquea a otros peatones. 
//			Ejemplo, ¿qué pasaría con el último peatón que pasa? Ahora mismo se gestiona 
//			desde el hilo cruce, pero esto como comentaba antes puede dar problemas.
			if (Main.vehiculosCruzando==0 && Main.vehiculosEsperandoEO>0) {
				Main.SemaforoEO.release();													// signal(SemaforoNS)
			}else Main.mutex.release();														// signal(mutex)
			
			
			try {Thread.sleep(7000);} catch (InterruptedException e) {}
			
			//*******************************************************************
			//*******************************************************************
			
//			- TODO: En el protocolo de entrada también hay que comprobar si es mi turno.
//			- TODO: Separar el nº de vehículos cruzando y esperando en dos, un grupo para NS, otro para EO
//			- TODO: Liberar a otros hilos que no sean peatones en caso de que no se puedan desbloquear peatones y se den las condiciones correctas
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
			
//			- TODO: La pantalla no la gestionáis bien cuando el vehículo cruza
			try { Main.pantalla.acquire(); } catch (InterruptedException e) {}					// wait(mutex)
			System.out.println("Vehículo cruzando dirección Este-Oeste");
			Main.pantalla.release();
			try { Thread.sleep(500);}catch (InterruptedException e){}
			Main.vehiculosCruzando--;
			
//			- TODO: Mismo para el protocolo de salida, no solo se desbloquea a otros peatones. 
//			Ejemplo, ¿qué pasaría con el último peatón que pasa? Ahora mismo se gestiona 
//			desde el hilo cruce, pero esto como comentaba antes puede dar problemas.
			if (Main.vehiculosCruzando==0 && Main.peatonesEsperando>0) {
				Main.SemaforoPeaton.release();												// signal(SemaforoNS)
			}else Main.mutex.release();														// signal(mutex)
			
			
			try {Thread.sleep(7000);} catch (InterruptedException e) {}
				
		}
	}
}
