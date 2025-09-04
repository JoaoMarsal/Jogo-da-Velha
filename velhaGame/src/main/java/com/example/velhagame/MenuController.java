package com.example.velhagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private void abrirInicial() throws IOException{
        FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("jogo.fxml"));
        Scene cena = new Scene(loader.load());
        JogoController controller = loader.getController();
        VelhaGameApplication.carregaCena(cena);
        controller.change((float) Settings.tableSize);
        controller.placeImage((float) Settings.tableSize);
    };

    @FXML
    private void abrirOpcoes() throws IOException{
        FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("opcoes.fxml"));
        Scene cena = new Scene(loader.load());
        OpcoesController controller = loader.getController();
        controller.carregaSlider(3, 15);
        VelhaGameApplication.carregaCena(cena);
    }

    public void abrirCreditos(ActionEvent actionEvent) {
    }
}