package dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;

import dao.exceptions.DaoUnrecoverableException;
import utente.Utente;
import utils.ConfigurationManager;
import utente.Sesso;

/**
 * UtenteDao
 */
public class UtenteDao implements InterfaceDao<Utente>{
    private static Connection connection = null;

    private static PreparedStatement GET     = null;
    private static PreparedStatement GET_ALL = null;
    private static PreparedStatement INSERT  = null;
    private static PreparedStatement UPDATE  = null;
    private static PreparedStatement DELETE  = null;

    public UtenteDao() {
        ConfigurationManager configurationManager = null;

        try {
            configurationManager = ConfigurationManager.getInstance();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(
                configurationManager.getDatabaseHost(),
                configurationManager.getDatabaseUser(),
                configurationManager.getDatabasePassword()
            );

            UtenteDao.connection = connection;

            if (UtenteDao.GET == null) {
                UtenteDao.GET = connection.prepareStatement(
                    "SELECT * FROM utenti WHERE id=? AND password=?"
                );
            }

            if (UtenteDao.GET_ALL == null) {
                UtenteDao.GET_ALL = connection.prepareStatement(
                    "SELECT * FROM utenti"
                );
            }

            if (UtenteDao.INSERT == null) {
                UtenteDao.INSERT = connection.prepareStatement(
                    "INSERT INTO utenti(nome, cognome, data_nascita, sesso, comune, nazione, codice_fiscale, password, tipo) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
                );
            }

            if (UtenteDao.UPDATE == null) {
                UtenteDao.UPDATE = connection.prepareStatement(
                    "SELECT * FROM utenti WHERE nome=? cognome=?"
                );
            }

            if (UtenteDao.DELETE == null) {
                UtenteDao.DELETE = connection.prepareStatement(
                    "DELETE FROM utenti WHERE id=? AND nome=? AND cognome=? AND password=?"
                );
            }
        } catch (SQLException e) {
            throw new DaoUnrecoverableException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public Optional<Utente> get(Object[] params) {
        Optional<Utente> result = Optional.empty();
        try {

            for (int i = 0; i < 2; i++) {
                
                if(params[i] instanceof Integer) {

                    int arg = (int) params[i];
                    UtenteDao.GET.setInt(i+1, arg);
                } else if(params[i] instanceof String) {
                    UtenteDao.GET.setString(i+1, (String) params[i]);
                }
            }
        } catch (SQLException e) {
            throw new DaoUnrecoverableException(e.getMessage(), e.getCause());
        }

        try (ResultSet rs = UtenteDao.GET.executeQuery()) {
            // if (!rs.isBeforeFirst() && rs.next()) {
            if (rs.next()) {
                int id          = rs.getInt("id");
                String nome     = rs.getString("nome");
                String cognome  = rs.getString("cognome");
                String password = rs.getString("password");
                Sesso sesso = rs.getString("sesso").equals("M")? Sesso.MASCHIO : Sesso.FEMMINA;

                result = Optional.of(
                    new Utente(
                        id, nome, cognome, sesso,
                        LocalDate.parse(rs.getString("data_nascita")),
                        rs.getString("comune"),
                        rs.getString("nazione"),
                        rs.getString("codice_fiscale"),
                        password
                    )
                );
            }

        } catch(SQLException e) {
            throw new DaoUnrecoverableException(e.getMessage(), e.getCause());
        }

        return result;
    }

    @Override
    public Optional<Utente> save(Utente utente) {
        Optional<Utente> result = Optional.empty();

                    // "INSERT INTO utenti(nome, cognome, data_nascita, sesso, comune, nazione, codice_fiscale, password) VALUES(?, ?, ?, ?, ?, ?, ?);"
        try {
            UtenteDao.INSERT.setString(1, utente.getNome());
            UtenteDao.INSERT.setString(2, utente.getCognome());
            UtenteDao.INSERT.setString(3, utente.getDataNascita().toString());
            UtenteDao.INSERT.setString(4, utente.getSesso().get());
            UtenteDao.INSERT.setString(5, utente.getComune());
            UtenteDao.INSERT.setString(6, utente.getNazione());
            UtenteDao.INSERT.setString(7, utente.getCodiceFiscale());
            UtenteDao.INSERT.setString(8, utente.getPassword());
            UtenteDao.INSERT.setString(9, "U");
            
            if(UtenteDao.INSERT.execute()) {
                throw new DaoUnrecoverableException("Il risultato non dovrebbe essere true (ResultSet).");
            } 

            try (ResultSet rs = UtenteDao.INSERT.getGeneratedKeys()) {
                rs.next();
                result = Optional.of(utente.setId(rs.getInt(1)));
            }

        } catch (SQLException e) {
            throw new DaoUnrecoverableException(e.getMessage(), e.getCause());
        }
        
        return result;
    }

    @Override
    public void update(Utente t, String[] params) {

    }

    @Override
    public void delete(Utente t) {
        try {

                    // "DELETE FROM utenti WHERE id=? AND nome=? AND cognome=? AND password=?"
            UtenteDao.DELETE.setInt(1, t.getId());
            UtenteDao.DELETE.setString(2, t.getNome());
            UtenteDao.DELETE.setString(3, t.getCognome());
            UtenteDao.DELETE.setString(4, t.getPassword());

            if(UtenteDao.DELETE.execute()) {
                throw new DaoUnrecoverableException("Il risultato non dovrebbe essere true (ResultSet).");
            }
        } catch (SQLException e) {
            throw new DaoUnrecoverableException(e.getMessage(), e.getCause());
        }
    }

    
}
