package utente;

import utils.Indirizzo;

public class Elettore extends Utente {

	private final Indirizzo residenza;
	
	public Elettore(String n, String s, Indirizzo i) {
		super(n, s);
		residenza = i;
	}

	public Indirizzo getResidenza() {
		return residenza;
	}
	

}
