package sessione;

import utente.Impiegato;

/**
 * Sessione
 */
public class Sessione {

    private String nome;
    private Impiegato creatore;

    public Sessione(String nome, Impiegato creatore) {
        this.nome = nome;
        this.creatore = creatore;
    }
}
