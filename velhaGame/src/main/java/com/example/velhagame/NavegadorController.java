package com.example.velhagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class NavegadorController {
    public void abreMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("menu.fxml"));
        Scene cena = new Scene(loader.load());
        VelhaGameApplication.carregaCena(cena);
    }
}
