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
public class PrincipalNivel3Controller implements Initializable {

    @FXML
    private Button nivel1;
    @FXML
    private Button nivel2;
    @FXML
    private Button nivel3;
    @FXML
    private Group grupoBotoes;
    @FXML
    private Button b5;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b1;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b10;
    @FXML
    private Button b8;
    @FXML
    private Button b2;
    @FXML
    private Button b9;
    @FXML
    private Button botaoProximaFase;
    @FXML
    private Button botaoFaseAnterior;
    @FXML
    private Button b11;
    @FXML
    private Button b16;
    @FXML
    private Button b13;
    @FXML
    private Button b14;
    @FXML
    private Button b12;
    @FXML
    private Button b15;
    @FXML
    private ProgressBar barraTempo;

    private ModelPrincipal modelPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image(getClass().getResourceAsStream("som32.png"));
        b1.setGraphic(new ImageView(image));
        b2.setGraphic(new ImageView(image));
        b3.setGraphic(new ImageView(image));
        b4.setGraphic(new ImageView(image));
        b5.setGraphic(new ImageView(image));
        b6.setGraphic(new ImageView(image));
        b7.setGraphic(new ImageView(image));
        b8.setGraphic(new ImageView(image));
        b9.setGraphic(new ImageView(image));
        b10.setGraphic(new ImageView(image));
        b11.setGraphic(new ImageView(image));
        b12.setGraphic(new ImageView(image));
        b13.setGraphic(new ImageView(image));
        b14.setGraphic(new ImageView(image));
        b15.setGraphic(new ImageView(image));
        botaoFaseAnterior.setVisible(false);
        barraTempo.setStyle("-fx-accent: #00FF00");
        modelPrincipal = new ModelPrincipal(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15,b16, botaoFaseAnterior,
                botaoProximaFase, barraTempo); //construtor modelPrincipal do nivel 2
        iniciarJogo();
    }

    @FXML
    public void verificarOpcao(ActionEvent evento) {
        modelPrincipal.verificarOpcao(evento);
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
    public void proximaFase(ActionEvent evento) {
        modelPrincipal.proximaFase(evento);
    }

    public void atualizarTela() {

    }

    @FXML
    public void alterarNivel(ActionEvent evento) throws IOException {
        modelPrincipal.alterarNivel(evento);
    }

    @FXML
    private void faseAnterior(ActionEvent event) {
        modelPrincipal.faseAnterior(event);
    }

    public void setFase(int fase) {
        modelPrincipal.setFase(fase);
    }

    public void setNivel(int i) {
        System.out.println("Entrou aqui nivek" +i);
        modelPrincipal.setNivel(i);
    }

}
