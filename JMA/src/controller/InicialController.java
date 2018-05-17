/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import model.ModelInicial;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author iran
 */
public class InicialController implements Initializable {
    @FXML
    private Button iniciar;
    
    private ModelInicial modelInicial;
    @FXML
    private Button sobre;
    @FXML
    private Label jogo;
    @FXML
    private Label de;
    @FXML
    private Label auditiva;
    @FXML
    private Label memoria;
    @FXML
    private Button sair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jogo.getStyleClass().add("fonte64");    
        sobre.getStyleClass().add("fonte20");
        modelInicial = new ModelInicial();
        
        
    }    

    @FXML
    private void iniciarJogo(ActionEvent event) throws IOException {
        modelInicial.iniciarJogo(event);
    }

    @FXML
    private void abrirSobre(ActionEvent event) {
        modelInicial.abrirSobre(event);
    }

    @FXML
    private void sair(ActionEvent event) {
        modelInicial.sairDoJogo(event);
    }
    
}
