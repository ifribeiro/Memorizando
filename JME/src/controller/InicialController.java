/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.ModelInicial;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

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
        de.getStyleClass().add("fonte64");
        memoria.getStyleClass().add("fonte64");
        auditiva.getStyleClass().add("fonte64");
        sobre.getStyleClass().add("fonte20");
        modelInicial = new ModelInicial();
        
        
    }    

    @FXML
    private void iniciarJogo(ActionEvent event) throws IOException {
        modelInicial.iniciarJogo(event);
    }

    @FXML
    private void abrirSobre(ActionEvent event) {
    }

    @FXML
    private void sair(ActionEvent event) {
        modelInicial.sairDoJogo(event);
    }
    
}
