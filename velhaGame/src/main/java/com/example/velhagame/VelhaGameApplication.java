package com.example.velhagame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VelhaGameApplication extends Application {

    private static Stage janela;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VelhaGameApplication.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Jogo da Velha");
        janela = stage;
        carregaCena(scene);
    }

    public static void carregaCena(Scene cena){
        janela.setScene(cena);
        janela.show();
    }
    public static void main(String[] args) {
        launch();
    }
}