package dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import utente.Impiegato;

/**
 * Unit test for {@link ImpiegatoDao}
 */
public class TestImpiegatoDao {

    @Test
    public void testSaveAndGet()
    {
        Impiegato test = new Impiegato(-1234, "Test", "Impiegato", "password");
        final ImpiegatoDao dao = new ImpiegatoDao();

        Optional<Impiegato> impiegato = dao.save(test);
        System.out.println(impiegato.get());
        Object[] params = {impiegato.get().getId(), "password"};

        impiegato = dao.get(params);
        System.out.println(impiegato.get());

        assertTrue(impiegato.isPresent());
        dao.delete(impiegato.get());
    }
}
