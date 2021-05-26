package controller;

import berszam.DolgozoDao;
import berszam.model.Dolgozo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static controller.SzamfejtUtil.*;


public class MainController {

    Dolgozo kivDolg;

    @FXML
    private TextField inputMunkanapok;
    @FXML
    private TextField inputLedolgnapok;
    @FXML
    private ComboBox<Dolgozo> dropdownMunkavallalo;
    @FXML
    private Text textNettober;
    @FXML
    private Text textSzja;
    @FXML
    private Text textTb;
    @FXML
    private Text textNyj;
    @FXML
    private Text textSzha;
    @FXML
    private Text textSzhj;
    @FXML
    private Label labelNapokInput;

    public void ujdolgozoOpen(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ujdolgmenu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void calc(ActionEvent actionEvent) {
        if (uresmezo() || kivDolg == null) {
            labelNapokInput.setVisible(true);
            labelNapokInput.setText("Mezők kitöltése kötelező!");
            return;
        }
        if (ervenytelenInput()) {
            labelNapokInput.setVisible(true);
            labelNapokInput.setText("Hibás kitöltés!");
            return;
        }
        eredmenyMutat();
    }

    private void eredmenyMutat() {
        int munkanapok = Integer.parseInt(inputMunkanapok.getText());
        int ledolgNapok = Integer.parseInt(inputLedolgnapok.getText());
        int szamfejtettBrutto = aranyositBrutto(
                kivDolg.getAlapber(),
                munkanapok,
                ledolgNapok);
        textNettober.setText(String.valueOf(nettober(szamfejtettBrutto)));
        textSzja.setText(String.valueOf(szja(szamfejtettBrutto)));
        textTb.setText(String.valueOf(tb(szamfejtettBrutto)));
        textNyj.setText(String.valueOf(nyj(szamfejtettBrutto)));
        textSzha.setText(String.valueOf(szocho(szamfejtettBrutto)));
        textSzhj.setText(String.valueOf(szakkepz(szamfejtettBrutto)));
    }

    private boolean uresmezo() {
        return inputMunkanapok.getText().isEmpty() || inputLedolgnapok.getText().isEmpty();
    }

    private boolean ervenytelenInput() {
        String munkaNapok = inputMunkanapok.getText();
        String ledolgNapok = inputLedolgnapok.getText();

        if (munkaNapok.matches("\".*\\\\d.*\"") && ledolgNapok.matches("\".*\\\\d.*\"")) {
            return true;
        }
        if (!ervenyesNapok(Integer.parseInt(munkaNapok), Integer.parseInt(ledolgNapok))) {
            return true;
        }
        return false;
    }

    private boolean ervenyesNapok(int munkanapok, int ledolgnapok) {
        return (munkanapok <= 31 && ledolgnapok <= munkanapok && munkanapok > 0 && ledolgnapok >= 0);
    }

    private void uresit() {
        inputMunkanapok.setText("");
        inputLedolgnapok.setText("");
        textNettober.setText("");
        textSzja.setText("");
        textTb.setText("");
        textNyj.setText("");
        textSzha.setText("");
        textSzhj.setText("");
    }

    @FXML
    public void initialize() {
        DolgozoDao dolgozoDao = DolgozoDao.getInstance();
        List<Dolgozo> dolgozok = dolgozoDao.findAll();
        for (Dolgozo d : dolgozok
        ) {
            dropdownMunkavallalo.getItems().add(d);
        }
        dropdownMunkavallalo.setOnAction(e -> kivalaszt());
    }

    private void kivalaszt() {
        kivDolg = dropdownMunkavallalo.getValue();
        uresit();
    }
}
