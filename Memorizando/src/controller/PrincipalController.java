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
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ModelPrincipal;

/**
 * FXML Controller class
 *
 * @author iran
 */
public class PrincipalController implements Initializable {

    @FXML
    private Button nivel1;
    @FXML
    private Button nivel2;
    @FXML
    private Button nivel3;
    @FXML
    private Group grupoBotoes;
    @FXML
    private Button b4;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b1;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b8;
    @FXML
    private Button b7;
    @FXML
    private Button botaoProximaFase;
    @FXML
    private Button botaoFaseAnterior;
    
    private ModelPrincipal modelPrincipal;
    @FXML
    private ProgressBar barraTempo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image(getClass().getResourceAsStream("som32.png"));
        ImageView iconeSom = new ImageView(image);
        b1.setGraphic(new ImageView(image));
        b2.setGraphic(new ImageView(image));
        b3.setGraphic(new ImageView(image));
        b4.setGraphic(new ImageView(image));
        b5.setGraphic(new ImageView(image));
        b6.setGraphic(new ImageView(image));
        b7.setGraphic(new ImageView(image));
        b8.setGraphic(new ImageView(image));
        modelPrincipal = new ModelPrincipal();
        iniciarJogo();

        // TODO
    }

    @FXML
    public void verificarOpcao(ActionEvent event) {
        modelPrincipal.verificarOpcao(event);

    }

    public void gerarNovasOpcoes() {

    }

    public void reduzirDificuldade() {

    }

    public void alterarFase() {
       

    }

    public void reiniciarJogo() {

    }

    public void iniciarJogo() {
        modelPrincipal.iniciarJogo();
    }

    public void aumentarPontuacao() {

    }

    @FXML
    public void proximaFase(ActionEvent event) {

    }

    public void atualizarTela() {

    }

    @FXML
    private void faseAnterior(ActionEvent event) {
    }

    @FXML
    private void alterarNivel(ActionEvent event) {
        modelPrincipal.alterarNivel(event);
    }

    @FXML
    private void trocarLayoutJogo(ActionEvent event) {
    }
}
