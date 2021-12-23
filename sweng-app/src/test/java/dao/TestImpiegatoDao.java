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
            
        Impiegato test = new Impiegato(-1234, "Test", "Impiegato", utils.Encryption.sha512("password"));
        final ImpiegatoDao dao = new ImpiegatoDao();

        Optional<Impiegato> impiegato = dao.save(test);
        Object[] params = {impiegato.get().getId(), impiegato.get().getPassword()};

        impiegato = dao.get(params);

        assertTrue(impiegato.isPresent());
        // dao.delete(impiegato.get());
    }
}
