package com.example.velhagame;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import javax.swing.*;

import java.util.List;
import java.util.Objects;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class JogoController {
    @FXML
    public GridPane tabuleiro;
    public Label indicador;

    //Mapping the board into a 2D array
    public int[][] tabuleiroMapped = new int[Settings.tableSize][Settings.tableSize];

    //Functions to prepare the board
    //Resize the board
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
        }
        //Variable that keeps tracks of whose turn it is
        Settings.turn = 0;
    }

    public void placeImage(float x){
        //Discovering the size for every image
        double sizeC = tabuleiro.getWidth() / x;
        double sizeR = tabuleiro.getHeight() / x;
        //Getting access to each and every tile, this double loop does the trick
        for(int l = 0; l < x; l++){
            for(int c = 0; c < x; c++){
                //Creates ImageView
                ImageView imgView = new ImageView("file:src/main/resources/com/example/velhagame/imagens/unused.png");                    //ImageView's are clickable
                tabuleiroMapped[l][c] = 0; //0 is for no player on this spot
                int finalL = l;
                int finalC = c;
                imgView.setOnMouseClicked(clicou -> {
                        //turn == even -> X ; turn == odd -> O
                            if((Settings.turn % 2) == 0){
                                imgView.setImage(new Image("file:src/main/resources/com/example/velhagame/imagens/used-X.png"));
                                tabuleiroMapped[finalL][finalC] = 1; //1 is for X player spot
                                indicador.setText("O");
                                System.out.println(finalL + "; " + finalC + ": xis\n");
                                horizontal(1, finalL);
                                vertical(1, finalC);
                                diagonalPrincipal(1);
                                diagonalSecundaria(1);
                            } else {
                                imgView.setImage(new Image("file:src/main/resources/com/example/velhagame/imagens/used-O.png"));
                                tabuleiroMapped[finalL][finalC] = 2; //2 is for O player spot
                                indicador.setText("X");
                                System.out.print(finalL + "; " + finalC + ": bola\n");
                                horizontal(2, finalL);
                                vertical(2, finalC);
                                diagonalPrincipal(2);
                                diagonalSecundaria(2);
                            }
                            Settings.turn = Settings.turn + 1;
                    });
                //Sets the size
                imgView.setFitWidth(sizeC);
                imgView.setFitHeight(sizeR);
                tabuleiro.add(imgView, c, l);
            }
        }
    };

    //Working on the tile checkers
    public void horizontal(int player, int line){
        int count = 0;
        for(int j = 0; j < Settings.tableSize; j++){
            if(tabuleiroMapped[line][j] == player){
                count = count + 1;
            };
        }
        //Checking if amount of tile's match table size
        if(count == Settings.tableSize){
            System.out.println("Jogador " + player + " venceu");
        }
    };

    //Working on the tile checkers
    public void vertical(int player, int column){
        int count = 0;
        for(int j = 0; j < Settings.tableSize; j++){
            if(tabuleiroMapped[j][column] == player){
                count = count + 1;
            };
        }
        //Checking if amount of tile's match table size
        if(count == Settings.tableSize){
            System.out.println("Jogador " + player + " venceu");
        }
    };

    public void diagonalPrincipal(int player){
        int count = 0;
        for(int j = 0; j < Settings.tableSize; j++){
            if(tabuleiroMapped[j][j] == player){
                count = count + 1;
            };
        }
        //Checking if amount of tile's match table size
        if(count == Settings.tableSize){
            System.out.println("Jogador " + player + " venceu");
        }
    };

    public void diagonalSecundaria(int player){
      int count = 0;
      int column = Settings.tableSize - 1;
      for(int j = 0; j < Settings.tableSize; j++){
          if(tabuleiroMapped[j][column] == player){
              count = count + 1;
          }
          column = column - 1;
      }
      //Checking if amount of tile's match table size
        if(count == Settings.tableSize){
            System.out.println("Jogador " + player + " venceu");
        }
    };
}
