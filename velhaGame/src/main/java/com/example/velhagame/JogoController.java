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

    public void change(float x) {
        //Deleting every tile of the board before recharging
        tabuleiro.getColumnConstraints().clear();
        tabuleiro.getRowConstraints().clear();
        //Everytime it loads jogo.fxml, table is reloaded, no need to check for reseting this table
        //Divides the table so that every tile has the same size
        double sizeC = tabuleiro.getWidth() / x;
        double sizeR = tabuleiro.getHeight() / x;
        ColumnConstraints column = new ColumnConstraints(sizeC);
        RowConstraints row = new RowConstraints(sizeR);
        //Creates tiles
        for(int i = 0;i < x; i++) {
            tabuleiro.getColumnConstraints().add(column);
            tabuleiro.getRowConstraints().add(row);
        };
        
    }
}
