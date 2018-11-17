/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.ModelRegistro;

/**
 * FXML Controller class
 *
 * @author iran
 */
public class RegistroController implements Initializable {

    @FXML
    private Button registrar;
    
    private ModelRegistro modelRegistro = null;
    @FXML
    private TextField email;
    @FXML
    private PasswordField senha;
    
    private Stage janela;
    
    private InicialController inicial;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelRegistro = new ModelRegistro();
    }    

    @FXML
    private void registrarPC(ActionEvent event) throws SQLException, SQLException, IOException {
        
        Rectangle2D tamanhoDisplay = Screen.getPrimary().getVisualBounds();
        Double comprimento = tamanhoDisplay.getWidth();
        Scene cena = null;
        
        String emailUsuario = email.getText();
        String senhaUsuario = senha.getText();
        if(modelRegistro.registrarPC(emailUsuario,senhaUsuario)){
            janela =  (Stage) registrar.getScene().getWindow();
            janela.close();
            if (comprimento > 1100) {
                FXMLLoader interfaceWideScreen = new FXMLLoader(getClass().getResource("/interfaces/Inicial.fxml"));
                Parent cenaInicial = (Parent) interfaceWideScreen.load();
                inicial = interfaceWideScreen.<InicialController>getController();
                cena = new Scene(cenaInicial, 1366, 768);
            } else {
                FXMLLoader interfaceQuadrada = new FXMLLoader(getClass().getResource("/interfaces/InicialQuadrada.fxml"));
                Parent cenaInicial = (Parent) interfaceQuadrada.load();
                inicial = interfaceQuadrada.<InicialController>getController();
                cena = new Scene(cenaInicial, 1024, 711);
            }
            janela.setTitle("Jogo de Memória Auditiva");

            janela.setScene(cena);
            janela.setFullScreen(true);
            janela.setFullScreenExitHint("");
            janela.show();
        }
        
    }
    
}
