package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import utente.Impiegato;
import dao.exceptions.DaoGenericException;
import dao.exceptions.DaoUnexpectedException;

/**
 * Unit test for {@link ImpiegatoDao}
 */
public class TestImpiegatoDao {

    @Test
    public void testSaveAndGet() throws DaoGenericException, DaoUnexpectedException
    {
        Impiegato test = new Impiegato(-1234, "Test", "Impiegato", "password");
        final ImpiegatoDao dao = new ImpiegatoDao();
        String[] params = {"Test", "Impiegato", "password"};
        dao.save(test);

        List<Impiegato> impiegati = dao.get(params);
        assertEquals(1, impiegati.size(), "Più di un impiegato");
        impiegati.forEach(arg0 -> {
            try {
                dao.delete(arg0);
            } catch (DaoUnexpectedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
}
