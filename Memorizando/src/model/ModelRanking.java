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
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    private Media media;
    private MediaPlayer mediaPlayer;

    public ModelRanking(ImageView avatarMaior, TextField nomeJogador, Button iniciar, Button av1,
            Button av2, Button av3, Button av4, Button av5, Button av6, Button av7, Button av8,
            Button av9, Button av10) {
        this.avatarMaior = avatarMaior;
        this.nomeJogador = nomeJogador;
        this.iniciar = iniciar;
        this.avatar = 0;
        this.avatar1 = av1;
        this.avatar2 = av2;
        this.avatar3 = av3;
        this.avatar4 = av4;
        this.avatar5 = av5;
        this.avatar6 = av6;
        this.avatar7 = av7;
        this.avatar8 = av8;
        this.avatar9 = av9;
        this.avatar10 = av10;

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

    public void avatarEscolhido(MouseEvent event) {
        Button b_temp = (Button) event.getSource();
        String id = b_temp.getId().substring(6);
        String efeito = "-fx-background-color:\n" +
"        linear-gradient(#f0ff35, #a9ff00),\n" +
"        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);\n" +
"    -fx-background-radius: 75%;\n" +
"    -fx-background-insets: 0, 1;\n" +
"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n" +
"    -fx-text-fill: #395306;";
        switch (id) {
            case "1":
                b_temp.setStyle(efeito);
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "2":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);                
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "3":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "4":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "5":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "6":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "7":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "8":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "9":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "10":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                break;
        }        
    }

    public void efeitoMouse(MouseEvent event) {        
        String caminhoAudio = "";
        caminhoAudio = "_audios/efeitos/transicaoBotao.mp3";
        URL file = getClass().getResource(caminhoAudio);
        media = new Media(file.toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.play();
                //setTocando(true);
            }
        });
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                //setTocando(false);
                mediaPlayer.dispose();
            }
        });
    }
}
