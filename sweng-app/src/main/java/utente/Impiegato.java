package utente;


public class Impiegato extends AbstractUtente {

	public Impiegato(int id, String nome, String cognome, String password) {
		super(id, nome, cognome, password);
	}

	public Impiegato setId(int id) {
		return new Impiegato(id, this.getNome(), this.getCognome(), this.getPassword());
	}

}
