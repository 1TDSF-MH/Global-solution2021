package br.com.fiap.to;

import java.util.ArrayList;
import java.util.List;


public class ReservaTO {
	private List<int[]> reserva = new ArrayList<>();
	private int cdReserva;
	private int cdAssoc;
	
	
	public List<int[]> getReserva() {
		return reserva;
	}

	public void setReserva(List<int[]> reserva) {
		this.reserva = reserva;
	}
	
	public void setReserva(int[] e) {
		this.reserva.add(e);
	}

	public ReservaTO() {
	
	}

	
	public ReservaTO(List<int[]> reserva, int cdReserva, int cdAssoc) {
		super();
		this.reserva = reserva;
		this.cdReserva = cdReserva;
		this.cdAssoc = cdAssoc;
	}

	public int getCdReserva() {
		return cdReserva;
	}

	public void setCdReserva(int cdReserva) {
		this.cdReserva = cdReserva;
	}

	public int getCdAssoc() {
		return cdAssoc;
	}

	public void setCdAssoc(int cdAssoc) {
		this.cdAssoc = cdAssoc;
	}

	@Override
	public String toString() {
		return "ReservaTO [reserva=" + reserva + ", cdReserva=" + cdReserva + ", cdAssoc=" + cdAssoc + "]";
	}


	
}
