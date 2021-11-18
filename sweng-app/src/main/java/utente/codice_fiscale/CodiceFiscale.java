package utente.codice_fiscale;

/**
 * TODO:
 * switch a Calendar
 */

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utente.Sesso;

public final class CodiceFiscale {
    private char codice[];
    private static int tab_dispari[] = {
        1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 1, 0, 5, 7, 9, 13, 15, 17, 19, 21,
        2, 4, 18, 20, 11, 3, 6, 8, 12, 14, 16, 10, 22, 25, 24, 23
    };

    private static char tab_mesi[] = {
        'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T'
    };

    /**
     * Crea un codice fiscale a partire da uno fornito.
     * Non viene controllata la conformità per quanto riguarda il codice catastale
     * dei comuni italiani o degli stati esteri, ma solo se il formato è valido
     * (quindi lettere e numeri al posto giusto).
     *
     * @param cf codice fiscale correttamente formattato
     *
     */
    public CodiceFiscale(String cf) {
        if (cf.length() > 16 || cf.length() < 16) {
            throw new 
                IllegalArgumentException(
                        String.format(
                            "Lunghezza '%d' di CF '%s' non valido",
                            cf,
                            cf.length()
                            )
                        );
        }

        this.codice = cf.toCharArray();

        Pattern pattern = Pattern.compile("[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}");
        Matcher matcher = pattern.matcher(new String(codice));

        if (!matcher.find()) {
            throw new 
                IllegalArgumentException(
                        String.format("Formato CF '%s' non valido", cf)
                        );
        }
    }

    /**
     * Verifica se il carattere è o no una vocale
     * 
     * @return true se e' una vocale, false altrimenti
     */
    private static boolean isVocale(char c) {
        c = Character.toUpperCase(c);

        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    /**
     * Restituisce la stringa iniziale con tante X quante ne servono per arrivare
     * a comporre una stringa lunga 3.
     * Se la stringa ha già la dimensione necessaria, la stringa restituita rimane
     * invariata.
     *
     * @param string
     *
     * @return string se la stringa iniziale ha lunghezza maggiore di 3, altrimenti
     * la stringa iniziale.
     */
    private static String riempi(String string) {
        String res = string;
        if (string.length() < 3) {
        	for (int i = 0; i < 3 - string.length(); i++) {
        		res += "X";
        	}	
        }

        return res;
    }

    /**
     * Calcola il codice fiscale usando i parametri in ingresso.
     * Per i parametri di tipo String non e' necessario che siano esclusivamente maiuscole o minuscole.
     *
     * @param nome
     * @param cognome
     * @param data_nascita
     * @param nazione_nascita
     * @param comune_nascita
     * @param sesso
     *
     * @return String uppercase con il codice fiscale calcolato in base ai parametri di ingresso
     */
    public static String calcola(
            String nome,
            String cognome,
            Date data_nascita,
            String nazione_nascita,
            String comune_nascita,
            Sesso sesso
            ) {
        int somma = 0;
        char codice[] = new char[16];
        cognome       = riempi(cognome.toUpperCase());
        nome          = riempi(nome.toUpperCase().replace(" ", ""));//unisce eventuali secondi nomi

        int c = 0;
        for (int i = 0; i < cognome.length() && c < 3; i++) {
            if (!isVocale(cognome.charAt(i))) {
                codice[c] = cognome.charAt(i);
                c++;
            }
        }

        if (c < 3) {
            for (int i = 0; i < 3; i++) {
                codice[i] = cognome.charAt(i);
            }
        }

        c = 0;
        StringBuilder cons = new StringBuilder("");
        //TODO: usa lambda
        for (int i = 0; i < nome.length() && cons.length() < 4; i++) {
            if (!isVocale(nome.charAt(i))) {
                cons.append(nome.charAt(i));
            }
        }

        if (cons.length() >= 4) { //4+ consonanti
            c = 0;
            for (int i = 0; i < 4; i++) {
                if (i != 1) {
                    codice[3 + c] = cons.charAt(i);
                    c++;
                }
            }
        } else if (cons.length() == 3) { //3 consonanti -> prendi as is
            for (int i = 0; i < cons.length(); i++) {
                codice[3 + i] = cons.charAt(i);
            }
        } else { //consonanti non sufficienti
            for (int j = 0; j < 3; j++) {
                codice[3 + j] = nome.charAt(j);
            }
        }

        String birth_year = Integer.toString(data_nascita.getYear());
        codice[6]         = birth_year.charAt(2);
        codice[7]         = birth_year.charAt(3);

        codice[8]         = CodiceFiscale.tab_mesi[data_nascita.getMonth()];

        String birth_day  = String.format("%02d", data_nascita.getDay() +
                (sesso == Sesso.MASCHIO? 0 : 40));

        codice[9]         =  birth_day.charAt(0);
        codice[10]        =  birth_day.charAt(1);

        char codice_catastale[] = CodiceFiscale.findCodiceCatastale(
            nazione_nascita,
            comune_nascita
        );
        
        codice[11] = codice_catastale[0];
        codice[12] = codice_catastale[1];
        codice[13] = codice_catastale[2];
        codice[14] = codice_catastale[3];

        for (int i = 0; i < 15; i ++) {
            if (i % 2 == 0) { //dispari
                somma += mapDispari(codice[i]);
            } else { //pari
                somma += mapPari(codice[i]);
            }
        }

        codice[15] = (char) (((somma % 26) + 65));

        return new String(codice).toUpperCase();
    }

    /**
     * Restituisce un char[4] corrispondente al comune italiano o stato estero.
     *
     * @param nazione qualsiasi stato del mondo tutto minuscolo
     * @param comune viene considerato solo se nazione == "italia", altrimenti viene ignorato
     *
     * @return char[4] corrispondente al comune italiano oppure allo stato estero
     */
    private static char[] findCodiceCatastale(
        String nazione,
        String comune
    ) {
        String codice_catastale = "";
        String find;
        String path;

        if (nazione.toUpperCase().equals("ITALIA")) {
            find = comune.toUpperCase();
            path = "comuni.txt";
        } else {
            find = nazione.toUpperCase();
            path = "stati_esteri.txt";
        }

        try(Scanner scanner = new Scanner(
            CodiceFiscale.class.getClassLoader().getResourceAsStream(path)
        )) {
            
            while(scanner.hasNext() && codice_catastale.equals("")) {
                String line = scanner.nextLine();
                String tmp[] = line.split(":");
                if (tmp.length != 2) {
                    throw new RuntimeException(
                        String.format(
                            "File '%s' malformato (line = '%s')", path, line
                        )
                    );
                }

                if (tmp[0].toUpperCase().equals(find)) {
                    codice_catastale = tmp[1];
                }
            }
        }

        char result[] = codice_catastale.toCharArray();

        if (result.length != 4) {
            throw new RuntimeException(
                String.format(
                    "Codice catastale '%s' non valido in '%s'",
                    codice_catastale, path
                )
            );
        }

        return result;
    }

    /**
     * Restituisce la mappatura in intero seguendo la tabella dei caratteri alfanumerici pari.
     *
     * @param c carattere che deve essere mappato in intero
     *
     * @return int che rappresenta la mappatura di c
     *
     * @see <a href="https://it.wikipedia.org/wiki/Codice_fiscale#Generazione_del_codice_fiscale"> Generazione codice fiscale </a>
     */
    private static int mapDispari(char c) {
        int res = 0;

        if (Character.isDigit(c)) {
            int i = Character.getNumericValue(c);
            res = CodiceFiscale.tab_dispari[i];
        } else {
            if(!Character.isAlphabetic(c)) {
                throw new IllegalArgumentException(
                    String.format(
                        "c = %c non Ã© una lettera dell'alfabeto o un numero",
                        c)
                );
            } else {
                res = CodiceFiscale.tab_dispari[((int) c - 55)];
            }
        }

        return res;
    }

    /**
     * Restituisce la mappatura in intero seguendo la tabella dei caratteri alfanumerici pari.
     *
     * @param c carattere che deve essere mappato in intero
     *
     * @return int che rappresenta la mappatura di c
     *
     * @see <a href="https://it.wikipedia.org/wiki/Codice_fiscale#Generazione_del_codice_fiscale"> Generazione codice fiscale </a>
     */
    private static int mapPari(char c) {
        int res = 0;

        if (Character.isDigit(c)) {
            res = Character.getNumericValue(c);
        } else {
            if(!Character.isAlphabetic(c)) {
                throw new IllegalArgumentException(
                    String.format(
                        "c = %c non e una lettera dell'alfabeto o un numero",
                        c)
                );
            } else {
                res = (int) c  - 65;
            }
        }

        return res;
    }

    /**
     * Controlla che il codice fiscale dato sia corretto
     *
     * @param cf codice fiscale da controllare
     * @param nome,
     * @param cognome,
     * @param data_nascita,
     * @param nazione_nascita,
     * @param comune_nascita,
     * @param sesso
     *
     * @return true se è corretto, false altrimenti
     */
    public /*@ pure @*/ static boolean controlla(
        CodiceFiscale cf,
        String nome,
        String cognome,
        Date data_nascita,
        String nazione_nascita,
        String comune_nascita,
        Sesso sesso
    ) {
        String expected = new String(CodiceFiscale.calcola(
            nome, cognome, data_nascita, nazione_nascita, comune_nascita, sesso
        ));

        return new String(cf.codice).equals(expected);
    }
}
