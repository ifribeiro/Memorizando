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
import java.io.InputStream;
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
        Font f = null, f1 = null;
        try {
            f = Font.loadFont(new FileInputStream(new File("src/fontes/Choko.ttf")), 64);
            f1 = Font.loadFont(new FileInputStream(new File("src/fontes/Choko.ttf")), 20);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        jogo.setFont(f);        
        de.setFont(f);
        memoria.setFont(f);
        auditiva.setFont(f);
        sobre.setFont(f1);
        
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
    }
    
}
