package controller;

import berszam.DolgozoDao;
import berszam.model.Dolgozo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UjdolgController {

    @FXML
    private TextField inputNev;

    @FXML
    private TextField inputPozicio;

    @FXML
    private TextField inputAlapber;

    @FXML
    private Label labelDolgInput;

    public void backAction(ActionEvent actionEvent) {
        backToMain(actionEvent);
    }

    public void saveAction(ActionEvent actionEvent) {
        if (uresmezo()) {
            labelDolgInput.setVisible(true);
            labelDolgInput.setText("Mezők kitöltése kötelező!");
        } else if (!ervenyesInputok()) {
            labelDolgInput.setVisible(true);
            labelDolgInput.setText("Hibás kitöltés!");
        } else {
            DolgozoDao dolgozoDao = DolgozoDao.getInstance();
            Dolgozo dolgozo = readDolgozoInput();
            dolgozoDao.persist(dolgozo);
            backToMain(actionEvent);
        }
    }

    private Dolgozo readDolgozoInput() {
        Dolgozo dolgozo = Dolgozo.builder()
                .nev(inputNev.getText())
                .pozicio(inputPozicio.getText())
                .alapber(Integer.parseInt(inputAlapber.getText()))
                .build();
        return dolgozo;
    }

    private boolean uresmezo() {
        return inputNev.getText().isEmpty() ||
                inputPozicio.getText().isEmpty() ||
                inputAlapber.getText().isEmpty();
    }

    private boolean ervenyesInputok() {
        if (!(ervenyesSzoveginput(inputNev.getText()) &&
                ervenyesSzoveginput(inputPozicio.getText()) &&
                ervenyesSzaminput(inputAlapber.getText()))) {
            return false;
        }
        if (Integer.parseInt(inputAlapber.getText()) <= 0) {
            return false;
        }
        return true;
    }

    private boolean ervenyesSzoveginput(String s) {
        return !s.matches("[0-9]+");
    }

    private boolean ervenyesSzaminput(String s) {
        return !s.matches("\".*\\\\d.*\"");
    }

    private void backToMain(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainmenu.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
