package dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import utente.Sesso;
import utente.Utente;

/**
 * Unit test for {@link ConfigurationManager}
 */
public class TestUtenteDao {


    @Test
    public void testSaveAndGet()
    {
        Utente test = new Utente(
            -1234, "Test", "Utente",

            Sesso.MASCHIO,
            LocalDate.now(),
            "Comune",
            "Nazione",
            "ASKDJLHAJKSLFHHH",
            "password"
        );
        final UtenteDao dao = new UtenteDao();
        Optional<Utente> utente = dao.save(test);
        System.out.println(utente.get());
        Object[] params = {utente.get().getId(), "password"};

        utente = dao.get(params);
        System.out.println(utente.get());

        assertTrue(utente.isPresent());
        dao.delete(utente.get());
    }
}
