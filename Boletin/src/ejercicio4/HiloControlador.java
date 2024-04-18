package ejercicio4;

import messagepassing.CommunicationScheme;
import messagepassing.MailBox;
import messagepassing.Selector;

public class HiloControlador extends Thread{
	private MailBox buzonPregunta;
	private MailBox buzonCajaA;
	private MailBox buzonCajaB;
	private MailBox buzonAbandonoCaja;
	private CommunicationScheme[] arrayBuzonComunicacion;
	private CommunicationScheme imprimir;
	
	private Selector s;
	
	private int tiempo;
	private String devuelveAsignacion;
	private int idPreguntaCaja;
	private int idCajaA;
	private int idCajaB;
	
	private int idAbandono;
	private boolean cajaAOcupada;
	private boolean cajaBOcupada;
	private boolean impresoraOcupada;
	
	private static int activaImpresora;
	
	
	public HiloControlador(MailBox pregunta, MailBox A, MailBox B, MailBox abandono, CommunicationScheme[] array, CommunicationScheme imprimir) {
		this.buzonPregunta=pregunta;
		this.buzonCajaA=A;
		this.buzonCajaB=B;
		this.buzonAbandonoCaja=abandono;
		this.arrayBuzonComunicacion= array;
		this.tiempo=0;
		this.devuelveAsignacion="";
		this.imprimir = imprimir;
		this.cajaAOcupada=false;
		this.cajaBOcupada=false;
		this.impresoraOcupada=false;
		
		s = new Selector();
		s.addSelectable(pregunta, false);
		s.addSelectable(A, false);
		s.addSelectable(B, false);
		s.addSelectable(abandono, false);
	}
	
	public void run() {
		imprimir.send(activaImpresora);
		while(true) {
			buzonPregunta.setGuardValue(true);
			buzonCajaA.setGuardValue(!cajaAOcupada);
			buzonCajaB.setGuardValue(!cajaBOcupada);
			buzonAbandonoCaja.setGuardValue(true);
			switch(s.selectOrBlock()){
				case 1: 
					idPreguntaCaja = (int) buzonPregunta.receive();
					tiempo = (int)(Math.random()*10) + 1;
					if (tiempo >= 5) devuelveAsignacion = Integer.toString(tiempo) + ",A";
					else devuelveAsignacion = Integer.toString(tiempo) + ",B";
					arrayBuzonComunicacion[idPreguntaCaja].send(devuelveAsignacion);
					break;
				case 2: 
					idCajaA = (int) buzonCajaA.receive();
					cajaAOcupada = true;
					arrayBuzonComunicacion[idCajaA].send("ok");
					break;
				case 3: 
					idCajaB = (int) buzonCajaB.receive();
					cajaBOcupada = true;
					arrayBuzonComunicacion[idCajaB].send("ok");
					break;
				case 4:
					idAbandono = (int) buzonAbandonoCaja.receive();
					if (idCajaA == idAbandono) cajaAOcupada=false; 
					else cajaBOcupada=false;
					arrayBuzonComunicacion[idAbandono].send("ok");
					break;
			}
		}
	}
}

/*
package ejercicio4;

import messagepassing.CommunicationScheme;
import messagepassing.MailBox;
import messagepassing.Selectable;
import messagepassing.Selector;

public class HiloControlador extends Thread{
	private CommunicationScheme buzonPregunta;
	private MailBox buzonCajaA;
	private MailBox buzonCajaB;
	private CommunicationScheme buzonAbandonoCaja;
	private CommunicationScheme[] arrayBuzonComunicacion;
	
	private Selector s;
	
	private int tiempo;
	private String devuelveAsignacion;
	private int idPreguntaCaja;
	private int idCajaA;
	private int idCajaB;
	
	private int idAbandono;
	private String mensajeSolicitud;
	private String mensajeLiberacion;
	private boolean cajaAOcupada;
	private boolean cajaBOcupada;
	
	
	
	public HiloControlador(CommunicationScheme pregunta, MailBox cajaA, MailBox cajaB, CommunicationScheme abandono, CommunicationScheme[] array) {
		this.buzonPregunta=pregunta;
		this.buzonCajaA=cajaA;
		this.buzonCajaB=cajaB;
		this.buzonAbandonoCaja=abandono;
		this.arrayBuzonComunicacion= array;
		this.tiempo=0;
		this.devuelveAsignacion="";
		this.cajaAOcupada=false;
		this.cajaBOcupada=false;
		
		s = new Selector();
		s.addSelectable(buzonCajaA, false);
		s.addSelectable(buzonCajaB, false);
	}
	
	public void run() {
		while(true) {
			
			// COMPRADOR PREGUNTA POR UNA CAJA
			idPreguntaCaja = (int) buzonPregunta.receive();
			
			tiempo = (int)(Math.random()*10) + 1;
			if (tiempo >= 5) devuelveAsignacion = Integer.toString(tiempo) + ",A";
			else devuelveAsignacion = Integer.toString(tiempo) + ",B";
			arrayBuzonComunicacion[idPreguntaCaja].send(devuelveAsignacion);
			
			
			
			// COMPRADOR SOLICITA UNA CAJA
			buzonCajaA.setGuardValue(!cajaAOcupada);
			buzonCajaB.setGuardValue(!cajaBOcupada);
			switch(s.selectOrBlock()){
				case 1: 
					idCajaA = (int) buzonCajaA.receive();
					cajaAOcupada = true;
					arrayBuzonComunicacion[idCajaA].send("ok");
					break;
				case 2: 
					idCajaB = (int) buzonCajaB.receive();
					cajaBOcupada = true;
					arrayBuzonComunicacion[idCajaB].send("ok");
					break;
			}
			
			
			// COMPRADOR LIBERA UNA CAJA
			idAbandono = (int) buzonAbandonoCaja.receive();
			if (idCajaA == idAbandono) cajaAOcupada=false; 
			else cajaBOcupada=false;
			arrayBuzonComunicacion[idAbandono].send("ok");
		}
		
	}
}

*/