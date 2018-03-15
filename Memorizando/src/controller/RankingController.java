/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.ModelRanking;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author iran
 */
public class RankingController implements Initializable {
    @FXML
    private Button avatar1;
    @FXML
    private Button avatar2;
    @FXML
    private Button avatar3;
    @FXML
    private Button avatar4;
    @FXML
    private Button avatar5;
    @FXML
    private Button avatar6;
    @FXML
    private Button avatar7;
    @FXML
    private Button avatar8;
    @FXML
    private Button avatar9;
    @FXML
    private Button avatar10;
    @FXML
    private ImageView avatarMaior;

    private ModelRanking modelRanking;
    @FXML
    private TextField nomeJogador;
    @FXML
    private Button iniciar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelRanking = new ModelRanking(avatarMaior,nomeJogador,iniciar);
    }    

    @FXML
    private void trocarAvatar(ActionEvent event) {
        modelRanking.trocarAvatar(event);
    }
    
    @FXML
    private void verificarTexto(KeyEvent event) {
        modelRanking.verificarTexto(event);
    }

    @FXML
    private void iniciarJogo(ActionEvent event) throws IOException {
        modelRanking.iniciarJogo(event);
    }
    
}
