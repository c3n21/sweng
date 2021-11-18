package utente;

import java.util.Date;

import utente.codice_fiscale.CodiceFiscale;
import utente.Sesso;

public class Elettore extends Utente {

    private final Date data_nascita;
	private final String comune;
    private final String nazione;
    private final Sesso sesso;
    private final String codice_fiscale;
    private /*@ spec_public @*/ boolean voto;
	
    //@ requires nome != null && cognome != null && !nome.equals("") && !cognome.equals("");
    //@ requires sesso == Sesso.MASCHIO || sesso == Sesso.FEMMINA;
    //@ requires (new Date()).compareTo(data_nascita) > 0;
    //@ requires (nazione_nascita.toUpperCase().equals("ITALIA") && !comune_nascita.equals("")) || (!nazione_nascita.equals(""));
    //@ requires CodiceFiscale.controlla(codice_fiscale, nome, cognome, data_nascita, nazione_nascita, comune_nascita, sesso) == true;
    public Elettore(
            String nome,
            String cognome,
            Date data_nascita,
            String comune_nascita,
            String nazione_nascita,
            Sesso sesso,
            String codice_fiscale
            ) {
        super(nome, cognome);
        this.codice_fiscale = codice_fiscale;
        this.comune         = comune_nascita;
        this.data_nascita   = data_nascita;
        this.nazione        = nazione_nascita;
        this.sesso          = sesso;
        this.voto           = false;
    }

    /**
     * Verifica se this e' maggiorenne o no.
     *
     * @return true se this e' maggiorenne, false altrimenti
     */
    public /*@ pure @*/ boolean isMaggiorenne() {
        return (new Date()).getTime() - data_nascita.getTime() >= 18.0 * 365.0 * 24.0 * 60.0 * 60.0 * 1000.0;
    }

    //@ requires voto == false;
    /**
     * Esprime il voto dell'elettore se non l'ha ancora fatto.
     * TODO: eccezione se il voto e' gia' stato espresso?
     */
    public /*@ pure @*/ void esprimi_voto() {
        if (!voto) {
            this.voto = true;
        }
    }

    public static void main (String[] args) {

        Elettore.TestNomeCognome();
        Elettore.TestSesso();
        Elettore.TestMaggiorenne();
        Elettore.TestLuogoNascita();
        Elettore.TestCodiceFiscale();
        Elettore.TestEsprimiVoto();

    }
    
    public static void TestNomeCognome() {
        System.out.println("Test: nome e cognome sono null");
        new Elettore(null, null, null, null, null, null, null);
    }
    
    public static void TestSesso() {
        String nome1 = "Pietro";
        String cognome1 = "Tornaindietro";
        Date data_nascita1 = new Date(2000, 0, 1);
        String cf1 = "TRNPTR00A01H199G";
        String comune1 = "Ravenna";
        String nazione1 = "Italia";
    	
        System.out.println("Test: sesso null");
        new Elettore(nome1, cognome1, data_nascita1, "Ravenna", "Italia", null, cf1);
    }
    
    public static void TestMaggiorenne() {
        String nome2 = "Mimmo";
        String cognome2 = "Mammo";
        Date data_nascita2 = new Date(2010, 0, 1);
        String cf2 = "MMMMMM10A01H199Y";
        
        System.out.println("Test: maggiorenne");
        Elettore mimmo = new Elettore(nome2, cognome2, data_nascita2, "Ravenna", "Italia", null, cf2);

        System.out.println("Test: elettore isMaggiorenne?");
        System.out.println("\tisMaggiorenne? expected false, got " + mimmo.isMaggiorenne());
    }
    
    public static void TestLuogoNascita() {
    	
        String nome1 = "Pietro";
        String cognome1 = "Tornaindietro";
        Date data_nascita1 = new Date(2000, 0, 1);
        String cf1 = "TRNPTR00A01H199G";
        String comune1 = "Ravenna";
        String nazione1 = "Italia";
    	
        System.out.println("Test: comune = null anche se italiano");
        new Elettore(nome1, cognome1, data_nascita1, null, "Italia", null, cf1);
    }
    
    public static void TestEsprimiVoto() {
        String nome1 = "Pietro";
        String cognome1 = "Tornaindietro";
        Date data_nascita1 = new Date(2000, 0, 1);
        String cf1 = "TRNPTR00A01H199G";
        String comune1 = "Ravenna";
        String nazione1 = "Italia";
        
        Elettore ok = new Elettore(nome1, cognome1, data_nascita1, "Ravenna", "Italia", Sesso.MASCHIO, cf1);
    	
        System.out.println("Test: elettore idoneo puo' votare solo una volta");
    	ok.esprimi_voto();
    	ok.esprimi_voto();
    }
    
    public static void TestCodiceFiscale() {
        String nome1 = "Pietro";
        String cognome1 = "Tornaindietro";
        Date data_nascita1 = new Date(2000, 0, 1);
        String cf1 = "TRNPTR00A01H199G";
        String comune1 = "Ravenna";
        String nazione1 = "Italia";
    	
        String nome3 = "Immigrato";
        String cognome3 = "Clandestino";
        Date data_nascita3 = new Date(2000, 0, 1);
        String cf3 = "CLNMGR00A01Z210K"; //luogo di nascita Cina
        
        System.out.println("Test: CF elettore italiano idoneo");
        String generated_cf1 = CodiceFiscale.calcola(
            nome1,
            cognome1,
            data_nascita1,
            "Italia",
            "Ravenna",
            Sesso.MASCHIO
        );
        System.out.println(
            String.format("\tCF1 = '%s' and Generated = '%s', CF1 == Generated? ",
                cf1, generated_cf1
            ) +
            cf1.equals(generated_cf1)
        );

        System.out.println("Test: CF elettore straniero idoneo");
        String generated_cf3 = CodiceFiscale.calcola(
            nome3,
            cognome3,
            data_nascita3,
            "Cina",
            null,
            Sesso.MASCHIO
        );
        System.out.println(
            String.format("\tCF3 = '%s' and Generated = '%s', CF3 == Generated? ",
                cf3, generated_cf3
            ) +
            cf3.equals(generated_cf3)
        );

        Date ok = new Date(2000, 0, 1);
        System.out.println("Test: CF elettore italiano idoneo con nome e cognome < 3");
        String generated_cf4 = CodiceFiscale.calcola(
            "ll",
            "ll",
            ok,
            "Italia",
            "Ravenna",
            Sesso.MASCHIO
        );
        System.out.println(
            String.format("\tCF4 = '%s' and Generated = '%s', CF4 == Generated? ",
                "LLXLLX00A01H199N", generated_cf4
            ) +
            "LLXLLX00A01H199N".equals(generated_cf4)
        );
    }
    
    public static void TestElettore() {
        String nome1 = "Pietro";
        String cognome1 = "Tornaindietro";
        Date data_nascita1 = new Date(2000, 0, 1);
        String cf1 = "TRNPTR00A01H199G";
        String comune1 = "Ravenna";
        String nazione1 = "Italia";
    	
        String nome3 = "Immigrato";
        String cognome3 = "Clandestino";
        Date data_nascita3 = new Date(2000, 0, 1);
        String cf3 = "CLNMGR00A01Z210K"; //luogo di nascita Cina
        
        System.out.println("Test: elettore italiano idoneo");
        Elettore elettore_ita_ido = new Elettore(nome1, cognome1, data_nascita1, "Ravenna", "Italia", Sesso.MASCHIO, cf1);

        System.out.println("Test: elettore straniero idoneo");
        Elettore elettore_stra_ido = new Elettore(nome3, cognome3, data_nascita3, null, "Cina", Sesso.MASCHIO, cf3);
    }
}
