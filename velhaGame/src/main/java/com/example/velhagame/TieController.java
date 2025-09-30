package com.example.velhagame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class TieController {
    public void fechar() throws IOException {
        JogoController.stage.close();
        //Restart application
        FXMLLoader loader = new FXMLLoader(WinnerController.class.getResource("main.fxml"));
        Scene scene = new Scene(loader.load());
        VelhaGameApplication.carregaCena(scene);

    }
}
