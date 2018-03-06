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
import javafx.scene.input.MouseEvent;
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
    private Button b10;
    private Button b9;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image botoes = new Image(getClass().getResourceAsStream("som32.png"));
        Image nivel1Icon = new Image(getClass().getResourceAsStream("nivel132.png"));
        Image nivel2Icon = new Image(getClass().getResourceAsStream("nivel232.png"));
        Image nivel3Icon = new Image(getClass().getResourceAsStream("nivel332.png"));
        ImageView iconeSom = new ImageView(botoes);
        //botões do primeiro e segundo nivel
        b1.setGraphic(new ImageView(botoes));
        b2.setGraphic(new ImageView(botoes));
        b3.setGraphic(new ImageView(botoes));
        b4.setGraphic(new ImageView(botoes));
        b5.setGraphic(new ImageView(botoes));
        b6.setGraphic(new ImageView(botoes));
        b7.setGraphic(new ImageView(botoes));
        b8.setGraphic(new ImageView(botoes));
        nivel1.setGraphic(new ImageView(nivel1Icon));
        nivel2.setGraphic(new ImageView(nivel2Icon));
        nivel3.setGraphic(new ImageView(nivel3Icon));
        botaoFaseAnterior.setVisible(false);
        barraTempo.setStyle("-fx-accent: #00FF00");
        modelPrincipal = new ModelPrincipal(b1, b2, b3, b4, b5, b6, b7, b8, botaoFaseAnterior,
              botaoProximaFase, barraTempo, fase1, fase2, fase3, fase4, fase5,fase6,fase7); //construtor modelPrincipal do nível 1    

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
        modelPrincipal.proximaFase(event);
    }

    public void atualizarTela() {

    }

    @FXML
    private void faseAnterior(ActionEvent event) {
        modelPrincipal.faseAnterior(event);
    }

    @FXML
    private void alterarNivel(ActionEvent event) throws IOException {
        modelPrincipal.alterarNivel(event);
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

}
