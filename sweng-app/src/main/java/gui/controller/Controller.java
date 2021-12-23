package gui.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.ImpiegatoDao;
import dao.UtenteDao;
import dao.exceptions.DaoGenericException;
import dao.exceptions.DaoUnexpectedException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utente.Utente;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button lblEntra;

    @FXML
    private Label lblMessaggio;

    @FXML
    private TextField lblPassword;

    @FXML
    private TextField lblUserID;

    @FXML
    public void handleLogin(ActionEvent event) throws DaoUnexpectedException {

        lblMessaggio.setVisible(true);
        int user_id = Integer.parseInt(lblUserID.getText());
        String password = lblPassword.getText();

        String hash_password = utils.Encryption.sha512(password);
        UtenteDao utenteDao = new UtenteDao();
        ImpiegatoDao impiegatoDao = new ImpiegatoDao();
        Object[] params = {user_id, hash_password};

        Optional<Utente> result_utente = utenteDao.get(params);
        if (!result_utente.isPresent()) {
            lblMessaggio.setText("Credenziali errate o utente non esistente");
        }

        Utente utente = result_utente.get();

        if (utente.getId() != user_id || !utente.getPassword().equals(hash_password)) {
            lblMessaggio.setText("Errore utente");
            return;
        }

        lblMessaggio.setText(
            String.format("Benvenuto %s %s", utente.getNome(), utente.getCognome())
        );
    }

    @FXML
    public void initialize() {
        assert lblEntra != null : "fx:id=\"lblEntra\" was not injected: check your FXML file 'login.fxml'.";
        assert lblMessaggio != null : "fx:id=\"lblMessaggio\" was not injected: check your FXML file 'login.fxml'.";
        assert lblPassword != null : "fx:id=\"lblPassword\" was not injected: check your FXML file 'login.fxml'.";
        assert lblUserID != null : "fx:id=\"lblUserID\" was not injected: check your FXML file 'login.fxml'.";

    }

}
