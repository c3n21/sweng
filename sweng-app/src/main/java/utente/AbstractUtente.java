package utente;

import sessione.Sessione;

/**
* Rappresentazione di un utente del sistema con la sola facoltà di visualizzare
* {@link Sessione}
*/

public abstract class AbstractUtente {
    private final int id;
    private final String nome;
    private final String cognome;
    private String password;
    private Sessione current_sessione;

    public AbstractUtente(int id, String nome, String cognome, String password) {
	this.id		      = id;
	this.nome	      = nome;
	this.cognome	      = cognome;
	this.password	      = password;
	this.current_sessione = null;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Sessione getCurrentSessione() {
	return current_sessione;
    }

    public String getNome() {
	return nome;
    }

    public String getCognome() {
	return cognome;
    }

    public void visualizza(Sessione sessione) {
	this.current_sessione = sessione;
    }

    @Override
    public String toString() {
	return String.format("Impiegato[id: %d, nome: %s, cognome: %s]", this.getId(), this.getNome(), this.getCognome());
    }
}
