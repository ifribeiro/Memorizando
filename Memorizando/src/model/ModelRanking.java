package model;

import controller.PrincipalController;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author iran
 */
public class ModelRanking {
    
    @FXML
    private ImageView avatarMaior;
    
    @FXML
    private TextField nomeJogador;
    private Stage janela;
    private FXMLLoader fxmloader;
    private Parent cenaPrincipal;
    private PrincipalController principalController;
    
    @FXML
    private Button iniciar;
    
    private int avatar;
    
    public ModelRanking(ImageView avatarMaior, TextField nomeJogador, Button iniciar) {
        this.avatarMaior = avatarMaior;
        this.nomeJogador = nomeJogador;
        this.iniciar = iniciar;
        this.avatar = 0;
    }
    
    public void trocarAvatar(ActionEvent event) {
        String nomeBotao = ((Button) event.getSource()).getId();
        int numeroBotao = Integer.parseInt(nomeBotao.substring(6));
        URL arquivoImg = getClass().getResource("imagens/icones/" + numeroBotao + ".png");
        avatarMaior.setImage(new Image(arquivoImg.toString()));
        this.avatar = numeroBotao;
        
    }
    
    public void verificarTextoInserido(InputMethodEvent event) {
        System.out.println("Nomejogador " + nomeJogador.getText());
    }
    
    public void verificarTexto(KeyEvent event) {
        if (!nomeJogador.getText().isEmpty()) {
            iniciar.setDisable(false);
        } else {
            iniciar.setDisable(true);
        }
        
    }
    
    public void iniciarJogo(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow();
        fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Principal.fxml"));
        cenaPrincipal = (Parent) fxmloader.load();
        principalController = fxmloader.<PrincipalController>getController();
        principalController.setFase(1);
        principalController.setNivel(1);
        principalController.iniciarJogo();
        principalController.setIconeAvatar(avatar);
        principalController.setNomeJogador(nomeJogador.getText());
        Scene scene = new Scene(cenaPrincipal, 1200, 700);
        janela.setScene(scene);
        janela.setFullScreen(true);
        janela.setFullScreenExitHint("");
        janela.show();
        
    }
}
