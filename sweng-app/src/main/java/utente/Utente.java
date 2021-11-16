package utente;

public abstract class Utente {
	private final String nome;
	private final String cognome;
	
	public Utente(String nome, String cognome) {
		this.nome    = nome;
		this.cognome = cognome;
	}
	
	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

}
