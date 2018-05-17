package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.ModelRanking;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author iran
 */
public class RankingController implements Initializable {

    @FXML
    private Button avatar1;
    @FXML
    private Button avatar2;
    @FXML
    private Button avatar3;
    @FXML
    private Button avatar4;
    @FXML
    private Button avatar5;
    @FXML
    private Button avatar6;
    @FXML
    private Button avatar7;
    @FXML
    private Button avatar8;
    @FXML
    private Button avatar9;
    @FXML
    private Button avatar10;
    @FXML
    private ImageView avatarMaior;

    private ModelRanking modelRanking;
    @FXML
    private TextField nomeJogador;
    @FXML
    private Button iniciar;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;
    @FXML
    private ImageView img7;
    @FXML
    private ImageView img8;
    @FXML
    private ImageView img9;
    @FXML
    private ImageView img10;
    @FXML
    private Label nome1;
    @FXML
    private Label pontos1;
    @FXML
    private Label nome2;
    @FXML
    private Label pontos2;
    @FXML
    private Label nome3;
    @FXML
    private Label pontos3;
    @FXML
    private Label nome4;
    @FXML
    private Label pontos4;
    @FXML
    private Label nome5;
    @FXML
    private Label pontos5;
    @FXML
    private Label nome6;
    @FXML
    private Label pontos6;
    @FXML
    private Label nome7;
    @FXML
    private Label pontos7;
    @FXML
    private Label nome8;
    @FXML
    private Label pontos8;
    @FXML
    private Label nome9;
    @FXML
    private Label pontos9;
    @FXML
    private Label nome10;
    @FXML
    private Label pontos10;
    @FXML
    private Pane painelRanking;
    @FXML
    private AnchorPane janela;
    @FXML
    private Label nome;
    @FXML
    private Label labelRanking;
    @FXML
    private Label titulo;
    @FXML
    private Button sair;
    @FXML
    private HBox posicao1;
    @FXML
    private HBox posicao2;
    @FXML
    private HBox posicao3;
    @FXML
    private HBox posicao4;
    @FXML
    private HBox posicao5;
    @FXML
    private HBox posicao6;
    @FXML
    private HBox posicao7;
    @FXML
    private HBox posicao8;
    @FXML
    private HBox posicao9;
    @FXML
    private HBox posicao10;
    @FXML
    private Label colunaJogador;
    @FXML
    private Label colunaPontos;
    @FXML
    private HBox hboxRanking;
    @FXML
    private HBox tituloColunas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        modelRanking = new ModelRanking(avatarMaior, nomeJogador, iniciar, avatar1,
                avatar2, avatar3, avatar4, avatar5, avatar6, avatar7, avatar8, avatar9, avatar10,
                img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, nome1, nome2, nome3, nome4,
                nome5, nome6, nome7, nome8, nome9, nome10, pontos1, pontos2, pontos3, pontos4, pontos5,
                pontos6, pontos7, pontos8, pontos9, pontos10, painelRanking);

        modelRanking.selecionarDefaultAvatar(1);
        try {
            modelRanking.atualizarRanking();
        } catch (IOException ex) {
            Logger.getLogger(RankingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread() {
            public void run() {
                titulo.getStyleClass().add("fonte42");
                nome.getStyleClass().add("fonte34");
                labelRanking.getStyleClass().add("fonte30");
                nomeJogador.getStyleClass().add("fonte30");
                colunaJogador.getStyleClass().add("fonte22");
                colunaPontos.getStyleClass().add("fonte22");
            }
        }.start();
    }

    @FXML
    private void trocarAvatar(ActionEvent event) throws IOException {
        modelRanking.trocarAvatar(event);
    }

    @FXML
    private void verificarTexto(KeyEvent event) {
        modelRanking.verificarTexto(event);
    }

    @FXML
    private void iniciarJogo(ActionEvent event) throws IOException {
        modelRanking.iniciarJogo(event);
    }

    @FXML
    private void avatarEscolhido(MouseEvent event) {
        modelRanking.avatarEscolhido(event);
    }

    @FXML
    private void efeitoMouse(MouseEvent event) {
        modelRanking.efeitoMouse(event);
    }

    @FXML
    private void botaoClicado(MouseEvent event) {
        modelRanking.efeitoBotaoClicado();
    }

    @FXML
    private void sair(ActionEvent event) {
        modelRanking.sairDoJogo(event);
    }

    /**
     * Verifica se a tecla Enter foi pressionada
     *
     * @param event disparado quando uma tecla do teclado é pressionada
     */
    @FXML
    private void verificarTeclaPressionada(KeyEvent event) {
        modelRanking.verificarTeclaPressionada(event);
    }

    /**
     * Inicia o jogo carregando as informções presentes na posição clicada pelo
     * jogador
     *
     * @param event
     */
    @FXML
    private void iniciarJogoRanking(MouseEvent event) throws IOException {
        modelRanking.iniciarJogoRanking(event);
    }

}
