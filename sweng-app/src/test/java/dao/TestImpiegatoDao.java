package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import utente.Impiegato;
import dao.exceptions.DaoGenericException;

/**
 * Unit test for {@link ConfigurationManager}
 */
public class TestImpiegatoDao {

    @Test
    public void testSaveAndGet() throws DaoGenericException
    {
        Impiegato test = new Impiegato(-1234, "Test", "Impiegato", "password");
        ImpiegatoDao dao = new ImpiegatoDao();
        String[] params = {"Test", "Impiegato", "password"};
        dao.save(test);

        List<Impiegato> impiegati = dao.get(params);
        assertEquals(1, impiegati.size(), "Più di un impiegato");
        dao.delete(test);
    }
}
