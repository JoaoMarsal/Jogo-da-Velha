package com.example.velhagame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Set;

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
        for (int i = 0; i < x; i++) {
            tabuleiro.getColumnConstraints().add(column);
            tabuleiro.getRowConstraints().add(row);
        }
        //Variable that keeps tracks of whose turn it is
        Settings.turn = 0;
    }

    public void placeImage(float x) {
        //Discovering the size for every image
        double sizeC = tabuleiro.getWidth() / x;
        double sizeR = tabuleiro.getHeight() / x;
        //Getting access to each and every tile, this double loop does the trick
        for (int l = 0; l < x; l++) {
            for (int c = 0; c < x; c++) {
                //Creates ImageView
                ImageView imgView = new ImageView("file:src/main/resources/com/example/velhagame/imagens/unused.png");                    //ImageView's are clickable
                tabuleiroMapped[l][c] = 0; //0 is for no player on this spot
                int finalL = l;
                int finalC = c;
                imgView.setOnMouseClicked(clicou -> {
                    //Checks if space empty
                    if (tabuleiroMapped[finalL][finalC] == 0) {
                        //turn == even -> X ; turn == odd -> O
                        if ((Settings.turn % 2) == 0) {
                            imgView.setImage(new Image("file:src/main/resources/com/example/velhagame/imagens/used-X.png"));
                            tabuleiroMapped[finalL][finalC] = 1; //1 is for X player spot
                            indicador.setText("O");
                            System.out.println(finalL + "; " + finalC + ": xis\n");
                            horizontal(1, finalL);
                            vertical(1, finalC);
                            diagonalPrincipal(1);
                            diagonalSecundaria(1);
                            checkForTie(2);
                        } else {
                            imgView.setImage(new Image("file:src/main/resources/com/example/velhagame/imagens/used-O.png"));
                            tabuleiroMapped[finalL][finalC] = 2; //2 is for O player spot
                            indicador.setText("X");
                            System.out.print(finalL + "; " + finalC + ": bola\n");
                            horizontal(2, finalL);
                            vertical(2, finalC);
                            diagonalPrincipal(2);
                            diagonalSecundaria(2);
                            checkForTie(1);
                        }
                        Settings.turn = Settings.turn + 1;
                    }
                });
                //Sets the size
                imgView.setFitWidth(sizeC);
                imgView.setFitHeight(sizeR);
                tabuleiro.add(imgView, c, l);
            }
        }
    }

    ;

    //Working on the tile checkers
    public void horizontal(int player, int line) {
        int count = 0;
        for (int j = 0; j < Settings.tableSize; j++) {
            if (tabuleiroMapped[line][j] == player) {
                count = count + 1;
            }
            ;
        }
        //Checking if amount of tile's match table size
        if (count == Settings.tableSize) {
            System.out.println("Jogador " + player + " venceu");
            try {
                winnerAlert(player);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    ;

    //Working on the tile checkers
    public void vertical(int player, int column) {
        int count = 0;
        for (int j = 0; j < Settings.tableSize; j++) {
            if (tabuleiroMapped[j][column] == player) {
                count = count + 1;
            }
            ;
        }
        //Checking if amount of tile's match table size
        if (count == Settings.tableSize) {
            System.out.println("Jogador " + player + " venceu");
            try {
                winnerAlert(player);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    ;

    public void diagonalPrincipal(int player) {
        int count = 0;
        for (int j = 0; j < Settings.tableSize; j++) {
            if (tabuleiroMapped[j][j] == player) {
                count = count + 1;
            }
            ;
        }
        //Checking if amount of tile's match table size
        if (count == Settings.tableSize) {
            System.out.println("Jogador " + player + " venceu");
            try {
                winnerAlert(player);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    ;

    public void diagonalSecundaria(int player) {
        int count = 0;
        int column = Settings.tableSize - 1;
        for (int j = 0; j < Settings.tableSize; j++) {
            if (tabuleiroMapped[j][column] == player) {
                count = count + 1;
            }
            column = column - 1;
        }
        //Checking if amount of tile's match table size
        if (count == Settings.tableSize) {
            System.out.println("Jogador " + player + " venceu");
            try {
                winnerAlert(player);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    ;

    static Stage stage = new Stage();

    //Showing propper "you won" screen
    private void winnerAlert(int player) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("winner.fxml"));
        Scene scene = new Scene(loader.load(), 300, 200);
        WinnerController controller = loader.getController();
        if (player == 1) {
            controller.alterText(1);
        } else if (player == 2) {
            controller.alterText(2);
        }
        stage.setScene(scene);
        stage.show();
    }
    //Showing propper tie screen
    private void tieAlert() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tie.fxml"));
        Scene scene = new Scene(loader.load(), 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    //Alternative on checking for tie
    //This function is an experiment on a minimaxing strategy to validate ties

    private void checkForTie(int nextPlayer) {
        /*
        Firstly, to use the minimax strategy, the program would need to keep track of all possible
        places the next player can put his piece
        */
        boolean holdBool = false;
        for(int i = 0; i < Settings.tableSize; i++){
            for(int j = 0; j < Settings.tableSize; j++){
                //This double loop will do the trick to check for all places
                if(tabuleiroMapped[i][j] == 0){
                //This will point to an empty place the game can be played
                    tabuleiroMapped[i][j] = nextPlayer; //The place is now taken for the next player
                    //Here, a function for checking the whole possible next board is needed
                    if(!holdBool) {
                        holdBool = checkBoard();
                    }
                    tabuleiroMapped[i][j] = 0;
                }
                if(holdBool){
                    break;
                }
            }
            if(holdBool){
                break;
            }
        }
        if(!holdBool){
            System.out.println("Empate\n");
            try {
                tieAlert();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }  else {
            System.out.println("Ainda pode ganhar\n");
        }
    }

    private boolean checkBoard() {
        //This is a boolean that starts as false, if it finds a possible win, it changes to true and breaks
        boolean winPossible = false;
        int hold;
        for(int j = 0; j < Settings.tableSize; j++){
            hold = 0;
            for(int i = 0; i < Settings.tableSize; i++){
                if(hold == 0 && tabuleiroMapped[j][i] != 0){
                    hold = tabuleiroMapped[j][i];
                }
                if(tabuleiroMapped[j][i] != hold && tabuleiroMapped[j][i] != 0){
                    break;
                } else if(i == (Settings.tableSize - 1)){
                    winPossible = true;
                    return winPossible;
                }
            }
        }
        for(int j = 0; j < Settings.tableSize; j++){
            hold = 0;
            for(int i = 0; i < Settings.tableSize; i++){
                if(hold == 0 && tabuleiroMapped[i][j] != 0){
                    hold = tabuleiroMapped[i][j];
                }
                if(tabuleiroMapped[i][j] != hold && tabuleiroMapped[i][j] != 0){
                    break;
                } else if(i == (Settings.tableSize - 1)){
                    winPossible = true;
                    return winPossible;
                }
            }
        }
        hold = 0;
        for(int j = 0; j < Settings.tableSize; j++){
            if(hold == 0 && tabuleiroMapped[j][j] != 0){
                hold = tabuleiroMapped[j][j];
            } else if(tabuleiroMapped[j][j] != hold && tabuleiroMapped[j][j] != 0){
                break;
            } else if(j == Settings.tableSize - 1){
                winPossible = true;
                return winPossible;
            }
        }
        hold = 0;
        for(int j = 0; j < Settings.tableSize; j++){
            if(hold == 0 && tabuleiroMapped[j][(Settings.tableSize - 1 - j)] != 0){
                hold = tabuleiroMapped[j][Settings.tableSize - 1 - j];
            } else if(tabuleiroMapped[j][Settings.tableSize - 1 - j] != hold && tabuleiroMapped[j][(Settings.tableSize - 1 - j)] != 0){
                break;
            } else if(j == Settings.tableSize - 1){
                winPossible = true;
                return winPossible;
            }
        }
        return winPossible;
    }
}
