/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ModelPrincipal;

/**
 * FXML Controller class
 *
 * @author iran
 */
public class PrincipalNivel2Controller implements Initializable {

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
    private ProgressBar barraTempo;

    private ModelPrincipal modelPrincipal;
    @FXML
    private Button fase1;
    @FXML
    private Button fase2;
    @FXML
    private Button fase3;
    @FXML
    private Button fase4;
    @FXML
    private Button fase5;
    @FXML
    private Button fase6;
    @FXML
    private Button fase7;
    @FXML
    private ImageView iconeAvatar;
    @FXML
    private Label pontuacao;
    @FXML
    private Label nomeJogador;
    @FXML
    private Button menuInicial;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image(getClass().getResourceAsStream("som32.png"));
        Image nivel1Icon = new Image(getClass().getResourceAsStream("nivel132.png"));
        Image nivel2Icon = new Image(getClass().getResourceAsStream("nivel232.png"));
        Image nivel3Icon = new Image(getClass().getResourceAsStream("nivel332.png"));
        Image proximaFase = new Image(getClass().getResourceAsStream("proximaFase64.png"));
        Image faseAnterior = new Image(getClass().getResourceAsStream("faseAnterior64.png"));
        
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
        
        nivel1.setGraphic(new ImageView(nivel1Icon));
        nivel2.setGraphic(new ImageView(nivel2Icon));
        nivel3.setGraphic(new ImageView(nivel3Icon));
        botaoProximaFase.setGraphic(new ImageView(proximaFase));
        botaoFaseAnterior.setGraphic(new ImageView(faseAnterior));
        botaoFaseAnterior.setVisible(false);
        barraTempo.setStyle("-fx-accent: #00FF00");
        try {
            modelPrincipal = new ModelPrincipal(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, botaoFaseAnterior,
                    botaoProximaFase, barraTempo,fase1, fase2, fase3, fase4, fase5,fase6,fase7,
                    iconeAvatar,pontuacao,nomeJogador, nivel1, nivel2, nivel3); //construtor modelPrincipal do nivel 2
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrincipalNivel2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void alterarNivel(ActionEvent event) throws IOException {
        modelPrincipal.alterarNivel(event);
    }

    @FXML
    private void faseAnterior(ActionEvent event) {
        modelPrincipal.faseAnterior(event);
    }

    public void setFase(int fase) {
        modelPrincipal.setFase(fase);
    }

    public void setNivel(int i) {
        modelPrincipal.setNivel(i);
    }

    @FXML
    private void mudarFase(ActionEvent event) {
        modelPrincipal.mudarFase(event);
    }
    
    public void setIconeAvatar(int avatar) {
        modelPrincipal.setIconeAvatar(avatar);
    }

    public void setNomeJogador(String text) {
        modelPrincipal.setNomeJogador(text);
    }

    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelPrincipal.menuInicial(event);
    }
}
