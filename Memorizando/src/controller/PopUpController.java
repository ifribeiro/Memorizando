/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author iran
 */
public class PopUpController implements Initializable {

    @FXML
    private Button sair;
    @FXML
    private Button reiniciar;
    @FXML
    private Button continuar;
    public Stage stage;
    private Button botaoClicado = new Button();
    @FXML
    private Label pontuacaoJogador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    public Button getBotaoClicado() {
        return botaoClicado;
    }  
    
    @FXML
    private void tratarBotaoClicado(ActionEvent event) {
        this.botaoClicado = (Button)event.getSource();
        ((Stage)botaoClicado.getScene().getWindow()).close();
    }
    
    public void setPontuacaoJogador(int pontuacao){
        pontuacaoJogador.setText(""+pontuacao);
    }

}
