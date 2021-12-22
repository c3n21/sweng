package dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
                    "SELECT * FROM utenti WHERE nome=? AND cognome=? AND password=?"
                );
            }

            if (UtenteDao.GET_ALL == null) {
                UtenteDao.GET_ALL = connection.prepareStatement(
                    "SELECT * FROM utenti"
                );
            }

            if (UtenteDao.INSERT == null) {
                UtenteDao.INSERT = connection.prepareStatement(
                    "INSERT INTO utenti(nome, cognome, data_nascita, sesso, comune, nazione, codice_fiscale, password) VALUES(?, ?, ?, ?, ?, ?, ?, ?);"
                );
            }

            if (UtenteDao.UPDATE == null) {
                UtenteDao.UPDATE = connection.prepareStatement(
                    "SELECT * FROM utenti WHERE nome=? cognome=?"
                );
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public List<Utente> get(String[] params) throws DaoGenericException {
        List<Utente> result = new ArrayList<>();
        try {
            UtenteDao.GET.setString(1, params[0]);
            UtenteDao.GET.setString(2, params[1]);
            UtenteDao.GET.setString(3, params[2]);
        } catch (SQLException e) {
            throw new DaoUnexpectedException(e.getMessage(), e.getCause());
        }

        try (ResultSet rs = UtenteDao.GET.executeQuery()) {
            while(rs.next()) {

                int id          = rs.getInt("id");
                String nome     = rs.getString("nome");
                String cognome  = rs.getString("cognome");
                String password = rs.getString("password");

                Sesso sesso = rs.getString("sesso").equals("M")? Sesso.MASCHIO : Sesso.FEMMINA;
                
                result.add(
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
            throw new DaoGenericException(e.getMessage(), e.getCause());
        }

        return result;
    }

    @Override
    public void save(Utente utente) throws DaoGenericException {

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
            
            if(UtenteDao.INSERT.execute()) {
                throw new DaoUnexpectedException("Il risultato non dovrebbe essere true (ResultSet).");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Utente t, String[] params) {

    }

    @Override
    public void delete(Utente t) {

    }

    
}
