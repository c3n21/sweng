package utente;

import java.time.LocalDate;

import sessione.Sessione;
import utente.codice_fiscale.CodiceFiscale;
import utente.Sesso;

public class Utente extends AbstractUtente {

    private final LocalDate data_nascita;
    private final String comune;
    private final String nazione;
    private final Sesso sesso;
    private final String codice_fiscale;
    private boolean voto;

    //@ requires nome != null && cognome != null
    //@ requires sesso == Sesso.MASCHIO || sesso == Sesso.FEMMINA
    //@ requires ((new Date().getYear()) - data_nascita.getYear()) >= 18
    //@ requires (new Date()).compareTo(data_nascita) > 0
    //@ requires (nazione.equals("Italia") && !comune.equals("")) || (!nazione.equals(""))
    public Utente(
        int id,
        String nome,
        String cognome,
        Sesso sesso,
        LocalDate data_nascita,
        String comune,
        String nazione,
        String codice_fiscale,
        String password
    ) {
        super(id, nome, cognome, password);
        this.codice_fiscale = codice_fiscale;
        this.comune         = comune;
        this.data_nascita   = data_nascita;
        this.nazione        = nazione;
        this.sesso          = sesso;
        this.voto           = false;
    }

    /**
     * Verifica se this e' maggiorenne o no.
     *
     * @return true se this e' maggiorenne, false altrimenti
    */
//     public /*@ pure @*/ boolean isMaggiorenne() {
//         return (new Date()).getTime() - data_nascita.getTime() >= 18.0 * 365.0 * 24.0 * 60.0 * 60.0 * 1000.0;
//     }

    public LocalDate getDataNascita() {
        return data_nascita;
    }

    public String getComune() {
        return comune;
    }

    public String getNazione() {
        return nazione;
    }

    public Sesso getSesso() {
        return sesso;
    }

    public String getCodiceFiscale() {
        return codice_fiscale;
    }

    public Utente setId(int id) {
        return new Utente(id, this.getNome(), this.getCognome(), sesso, data_nascita, comune, nazione, codice_fiscale, this.getPassword());
    }

    /**
     * Esprime il voto dell'utente se non l'ha ancora fatto.
     * TODO: eccezione se il voto e' gia' stato espresso?
    */
    //@ requires voto == false
    public /*@ pure @*/ void esprimi_voto() {
        if (!voto) {
            this.voto = true;
        }
    }

    public void iscriviCandidato(Sessione sessione) {

    }
}
