package com.example.velhagame;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import javax.swing.*;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class JogoController {
    @FXML
    public GridPane tabuleiro;
    public TextField teste;

    public void change() {
        float tableN = parseFloat(teste.getText());
        double sizeC = (1/tableN) * tabuleiro.getWidth();
        double sizeR = (1/tableN) * tabuleiro.getHeight();
        ColumnConstraints column = new ColumnConstraints(sizeC);
        RowConstraints row = new RowConstraints(sizeR);
        for(int i = 0;i < (tableN - 1); i++) {
            tabuleiro.getColumnConstraints().add(column);
            tabuleiro.getRowConstraints().add(row);
        };
    }
}
