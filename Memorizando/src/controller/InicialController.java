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

/**
 * FXML Controller class
 *
 * @author iran
 */
public class InicialController implements Initializable {
    @FXML
    private Button iniciar;
    
    private ModelInicial modelInicial;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelInicial = new ModelInicial();
    }    

    @FXML
    private void iniciarJogo(ActionEvent event) throws IOException {
        modelInicial.iniciarJogo(event);
    }
    
}
