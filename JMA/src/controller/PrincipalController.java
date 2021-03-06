package controller;

import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.text.Font;
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

    private ModelPrincipal modelPrincipal;
    @FXML
    private ProgressBar barraTempo;
    @FXML
    private Button b10;
    @FXML
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
    @FXML
    private ImageView iconeAvatar;
    private Label pontuacao;
    @FXML
    private Label nomeJogador;
    @FXML
    private Button menuInicial;
    @FXML
    private Button sair;
    @FXML
    private Label titulo;
    @FXML
    private Group grupoNivel1;
    @FXML
    private Group grupoNivel2;
    @FXML
    private Group grupoNivel3;
    @FXML
    private Button b13;
    @FXML
    private Button b14;
    @FXML
    private Button b15;
    @FXML
    private Button b16;
    @FXML
    private Button b12;
    @FXML
    private Button b11;
    @FXML
    private ImageView imagemFundo;
    @FXML
    private ImageView imgFase1;
    @FXML
    private ImageView imgFase2;
    @FXML
    private ImageView imgFase3;
    @FXML
    private ImageView imgFase4;
    @FXML
    private ImageView imgFase5;
    @FXML
    private ImageView imgFase6;
    @FXML
    private ImageView imgFase7;
    @FXML
    private Label ptFase1;
    @FXML
    private Label ptFase2;
    @FXML
    private Label ptFase3;
    @FXML
    private Label ptFase4;
    @FXML
    private Label ptFase5;
    @FXML
    private Label ptFase6;
    @FXML
    private Label ptFase7;
    @FXML
    private ImageView pt1;
    @FXML
    private ImageView pt2;
    @FXML
    private ImageView pt3;
    @FXML
    private ImageView pt4;
    @FXML
    private ImageView pt5;
    @FXML
    private ImageView pt6;
    @FXML
    private ImageView pt7;
    @FXML
    private ImageView pt8;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image botoes = new Image(getClass().getResourceAsStream("som32.png"));        
        ImageView iconeSom = new ImageView(botoes);      
        barraTempo.setStyle("-fx-accent: #00FF00");        
        try {
            modelPrincipal = new ModelPrincipal(b1, b2, b3, b4, b5, b6, b7, b8,b9,
                    b10,b11,b12,b13,b14,b15,b16,barraTempo, fase1, fase2, fase3, 
                    fase4, fase5,fase6,fase7, iconeAvatar,pontuacao,nomeJogador,
                    nivel1, nivel2, nivel3, grupoNivel1,grupoNivel2,grupoNivel3,
                    imagemFundo,imgFase1,imgFase2,imgFase3,imgFase4,imgFase5,imgFase6,
                    imgFase7,ptFase1,ptFase2,ptFase3,ptFase4,ptFase5,ptFase6,ptFase7,
                    menuInicial,sair,pt1,pt2,pt3,pt4,pt5,pt6,pt7,pt8); //construtor modelPrincipal do nível 1            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nomeJogador.getStyleClass().add("fonte30");
//        pontuacao.getStyleClass().add("fonte30");
        titulo.getStyleClass().add("fonte42");
        ptFase1.getStyleClass().add("fonte13");
        ptFase2.getStyleClass().add("fonte13");
        ptFase3.getStyleClass().add("fonte13");
        ptFase4.getStyleClass().add("fonte13");
        ptFase5.getStyleClass().add("fonte13");
        ptFase6.getStyleClass().add("fonte13");
        ptFase7.getStyleClass().add("fonte13");
        iniciarJogo();
        
        
    }

    @FXML
    public void verificarOpcao(ActionEvent event) {
        modelPrincipal.verificarClique(event);

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

    @FXML
    private void sair(ActionEvent event) {
        modelPrincipal.sairDoJogo();
    }
    
    public void setIconeAvatar(Image imagem){
        modelPrincipal.setIconeAvatar(imagem);
    }

    public void setJogadorExiste() {
        modelPrincipal.setJogadorExiste(true);
    }

}
