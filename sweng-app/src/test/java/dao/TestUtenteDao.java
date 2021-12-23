package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import dao.exceptions.DaoGenericException;
import utente.Sesso;
import utente.Utente;

/**
 * Unit test for {@link ConfigurationManager}
 */
public class TestUtenteDao {


    @Test
    public void testSaveAndGet() throws DaoGenericException
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
        UtenteDao dao = new UtenteDao();
        String[] params = {"Test", "Utente", "password"};
        dao.save(test);

        List<Utente> utenti = dao.get(params);
        assertEquals(1, utenti.size(), "Più di un utente");
        dao.delete(test);
    }
}
