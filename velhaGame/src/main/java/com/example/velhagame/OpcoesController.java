package com.example.velhagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

import static java.lang.Integer.parseInt;

public class OpcoesController {
    @FXML
    public Slider slider;
    public Label valor;

    public void carregaSlider(int min, int max) {
        slider.setMin(min);
        slider.setMax(max);
        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            valor.setText("Value: " + newVal.intValue());
        });
    }

    public void salvarConfigurações() {
        Settings.tableSize = (int) slider.getValue();
    }
}
