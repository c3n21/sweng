package dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import dao.exceptions.DaoUnrecoverableException;
import utente.Impiegato;
import utils.ConfigurationManager;

/**
 * ImpiegatoDao
 */
public class ImpiegatoDao implements InterfaceDao<Impiegato> {
    private static Connection connection = null;

    private static PreparedStatement GET     = null;
    private static PreparedStatement GET_ALL = null;
    private static PreparedStatement INSERT  = null;
    private static PreparedStatement UPDATE  = null;
    private static PreparedStatement DELETE = null;

    public ImpiegatoDao() {
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

            ImpiegatoDao.connection = connection;

            if (ImpiegatoDao.GET == null) {
                ImpiegatoDao.GET = connection.prepareStatement(
                    "SELECT * FROM utenti WHERE id=? AND password=?"
                );
            }

            if (ImpiegatoDao.GET_ALL == null) {
                ImpiegatoDao.GET_ALL = connection.prepareStatement(
                    "SELECT * FROM utenti"
                );
            }

            if (ImpiegatoDao.INSERT == null) {
                ImpiegatoDao.INSERT = connection.prepareStatement(
                    "INSERT INTO utenti(nome, cognome, password, tipo) VALUES(?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
                );
            }

            if (ImpiegatoDao.UPDATE == null) {
                ImpiegatoDao.UPDATE = connection.prepareStatement(
                    "SELECT * FROM utenti WHERE nome=? cognome=?"
                );
            }

            if (ImpiegatoDao.DELETE == null) {
                ImpiegatoDao.DELETE = connection.prepareStatement(
                    "DELETE FROM utenti WHERE id=? AND nome=? AND cognome=? AND password=?"
                );
            }

        } catch (SQLException e) {
            throw new DaoUnrecoverableException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Impiegato> get(Object[] params) {
        Optional<Impiegato> result = Optional.empty();
        try {
            for (int i = 0; i < 2; i++) {
                
                if(params[i] instanceof Integer) {

                    int arg = (int) params[i];
                    ImpiegatoDao.GET.setInt(i+1, arg);
                } else if(params[i] instanceof String) {
                    ImpiegatoDao.GET.setString(i+1, (String) params[i]);
                }
            }
        } catch (SQLException e) {
            throw new DaoUnrecoverableException(e.getMessage(), e.getCause());
        }

        try (ResultSet rs = ImpiegatoDao.GET.executeQuery()) {
            // if (!rs.isBeforeFirst() && rs.next()) {
            if (rs.next()) {
                int id          = rs.getInt("id");
                String nome     = rs.getString("nome");
                String cognome  = rs.getString("cognome");
                String password = rs.getString("password");
                result = Optional.of(new Impiegato(id, nome, cognome, password));
            }
        } catch(SQLException e) {
            throw new DaoUnrecoverableException(e.getMessage(), e.getCause());
        }

        return result;
    }

    @Override
    public Optional<Impiegato> save(Impiegato impiegato) {
        Optional<Impiegato> result = Optional.empty();
        try {
            ImpiegatoDao.INSERT.setString(1, impiegato.getNome());
            ImpiegatoDao.INSERT.setString(2, impiegato.getCognome());
            ImpiegatoDao.INSERT.setString(3, impiegato.getPassword());
            ImpiegatoDao.INSERT.setString(4, "I");

            if(ImpiegatoDao.INSERT.execute()) {
                throw new DaoUnrecoverableException("Il risultato non dovrebbe essere true (ResultSet).");
            }

            try (ResultSet rs = ImpiegatoDao.INSERT.getGeneratedKeys()) {
                rs.next();
                result = Optional.of(impiegato.setId(rs.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void update(Impiegato t, String[] params) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Impiegato t) {
        try {

            // "DELETE FROM utenti WHERE id=? AND nome=? AND cognome=? AND password=?"
            ImpiegatoDao.DELETE.setInt(1, t.getId());
            ImpiegatoDao.DELETE.setString(2, t.getNome());
            ImpiegatoDao.DELETE.setString(3, t.getCognome());
            ImpiegatoDao.DELETE.setString(4, t.getPassword());

            if(ImpiegatoDao.DELETE.execute()) {
                throw new DaoUnrecoverableException("Il risultato non dovrebbe essere true (ResultSet).");
            }

        } catch (SQLException e) {
            throw new DaoUnrecoverableException("Il risultato non dovrebbe essere true (ResultSet).");
        }
    }
}
