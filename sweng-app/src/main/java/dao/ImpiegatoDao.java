package dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.exceptions.DaoGenericException;
import dao.exceptions.DaoUnexpectedException;
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

    public ImpiegatoDao() throws DaoUnexpectedException {
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
                    "SELECT * FROM impiegati WHERE nome=? AND cognome=? AND password=?"
                );
            }

            if (ImpiegatoDao.GET_ALL == null) {
                ImpiegatoDao.GET_ALL = connection.prepareStatement(
                    "SELECT * FROM impiegati"
                );
            }

            if (ImpiegatoDao.INSERT == null) {
                ImpiegatoDao.INSERT = connection.prepareStatement(
                    "INSERT INTO impiegati(nome, cognome, password) VALUES(?, ?, ?);"
                );
            }

            if (ImpiegatoDao.UPDATE == null) {
                ImpiegatoDao.UPDATE = connection.prepareStatement(
                    "SELECT * FROM impiegati WHERE nome=? cognome=?"
                );
            }

            if (ImpiegatoDao.DELETE == null) {
                ImpiegatoDao.DELETE = connection.prepareStatement(
                    "DELETE FROM impiegati WHERE id=? AND nome=? AND cognome=? AND password=?"
                );
            }

        } catch (SQLException e) {
            throw new DaoUnexpectedException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Impiegato> get(String[] params) throws DaoGenericException {
        List<Impiegato> result = new ArrayList<>();
        try {
            ImpiegatoDao.GET.setString(1, params[0]);
            ImpiegatoDao.GET.setString(2, params[1]);
            ImpiegatoDao.GET.setString(3, params[2]);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ResultSet rs = ImpiegatoDao.GET.executeQuery()) {
            while(rs.next()) {

                int id          = rs.getInt("id");
                String nome     = rs.getString("nome");
                String cognome  = rs.getString("cognome");
                String password = rs.getString("password");

                result.add(new Impiegato(id, nome, cognome, password));
            }

        } catch(SQLException e) {
            throw new DaoGenericException("SQLException", e.getCause());
        }

        return result;
    }

    @Override
    public void save(Impiegato impiegato) throws DaoGenericException {
        try {
            ImpiegatoDao.INSERT.setString(1, impiegato.getNome());
            ImpiegatoDao.INSERT.setString(2, impiegato.getCognome());
            ImpiegatoDao.INSERT.setString(3, impiegato.getPassword());

            if(ImpiegatoDao.INSERT.execute()) {
                throw new DaoUnexpectedException("Il risultato non dovrebbe essere true (ResultSet).");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Impiegato t, String[] params) throws DaoGenericException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Impiegato t) throws DaoUnexpectedException {
        try {

            // "DELETE FROM utenti WHERE id=? AND nome=? AND cognome=? AND password=?"
            ImpiegatoDao.DELETE.setInt(1, t.getId());
            ImpiegatoDao.DELETE.setString(2, t.getNome());
            ImpiegatoDao.DELETE.setString(3, t.getCognome());
            ImpiegatoDao.DELETE.setString(4, t.getPassword());

            if(ImpiegatoDao.DELETE.execute()) {
                throw new DaoUnexpectedException("Il risultato non dovrebbe essere true (ResultSet).");
            }

            System.out.println(
                String.format("Risultato: %d", ImpiegatoDao.DELETE.getUpdateCount())
            );
        } catch (SQLException e) {
            throw new DaoUnexpectedException("Il risultato non dovrebbe essere true (ResultSet).");
        }
    }

    
}
