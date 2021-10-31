package utente;

public abstract class Utente {
	private final String nome;
	private final String cognome;
	
	public Utente(String n, String s) {
		nome = n;
		cognome = s;
	}
	
	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

}
