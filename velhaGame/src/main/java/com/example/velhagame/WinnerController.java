package com.example.velhagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class WinnerController {
    @FXML
    public Label jogador;
    public ImageView gif;

    //Alternating player's figure
    public void alterText(int player){
        if(player == 1){
            jogador.setText("X");
        } else if(player == 2){
            jogador.setText("O");
        }
    }

    //Close window
    public void fechar() throws IOException {
        JogoController.stage.close();
        //Restart application
        FXMLLoader loader = new FXMLLoader(WinnerController.class.getResource("main.fxml"));
        Scene scene = new Scene(loader.load());
        VelhaGameApplication.carregaCena(scene);
    }

}
