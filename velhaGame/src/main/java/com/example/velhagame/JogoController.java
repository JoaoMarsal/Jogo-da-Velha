package com.example.velhagame;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.GridPane;

import javax.swing.*;

import static java.lang.Integer.parseInt;

public class JogoController {
    @FXML
    public GridPane tabuleiro;
    public TextField teste;

    public void change() {
        int tableN = parseInt(teste.getText());
        



    }
}
