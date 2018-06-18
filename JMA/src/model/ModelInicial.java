package model;

import controller.RankingController;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import controller.PopUpController;
import controller.SobreController;
import static java.lang.System.exit;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.WeakEventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.WindowEvent;

/**
 *
 * @author iran
 */
public class ModelInicial {

    private Stage janela;
    private FXMLLoader fxmloader;
    @FXML
    private RankingController rankingController;
    private Parent cenaPrincipal;
    private PopUpController popController;
    @FXML
    private MediaView videoExplicativo;
    private Media media;
    private MediaPlayer mediaPlayer;
    private FXMLLoader fxmlPopUp;

    public ModelInicial(MediaView videoExplicativo) {
        this.videoExplicativo = videoExplicativo;
    }

    @FXML
    public void iniciarJogo(ActionEvent botao) throws IOException {
        janela = (Stage) ((Button) botao.getSource()).getScene().getWindow();
        Rectangle2D tamanhoDisplay = Screen.getPrimary().getVisualBounds();
        Double comprimento = tamanhoDisplay.getWidth();
        Scene scene = null; 
       
        if(comprimento>1100){
            
            FXMLLoader wideScreen = new FXMLLoader(getClass().getResource("/interfaces/Ranking.fxml"));
            Parent cenaWidescreen = (Parent) wideScreen.load();           
            rankingController = wideScreen.<RankingController>getController();
            scene = new Scene(cenaWidescreen, 1366, 711);           
        }else{
            fxmloader = new FXMLLoader(getClass().getResource("/interfaces/RankingQuadrada.fxml"));
            cenaPrincipal = (Parent) fxmloader.load();
            rankingController = fxmloader.<RankingController>getController();
            scene = new Scene(cenaPrincipal, 1024, 711);
        }
        
        
        janela.setScene(scene);
        janela.setFullScreen(true);
        janela.setFullScreenExitHint("");
        
        janela.show();
        
    }

    /**
     * Sai do jogo
     *
     * @param event evento disparado quando o botão sair é clicado
     */
    public void sairDoJogo(ActionEvent event) {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow();
        //função para encerrar todos os processos quando o usuário clicar no "X"
        Alert confirmacaoSaida = new Alert(Alert.AlertType.CONFIRMATION,
                "Deseja mesmo sair do jogo?");
        Button botaoSair = (Button) confirmacaoSaida.getDialogPane().lookupButton(ButtonType.OK);
        Button botaoNao = (Button) confirmacaoSaida.getDialogPane().lookupButton(ButtonType.CANCEL);
        botaoSair.setText("Sim");
        botaoNao.setText("Não");
        confirmacaoSaida.setTitle(null);
        confirmacaoSaida.setHeaderText(null);
        confirmacaoSaida.setContentText("Deseja mesmo sair do jogo?");
        Optional<ButtonType> resposta = confirmacaoSaida.showAndWait();
        if (ButtonType.OK.equals(resposta.get())) {
            janela.close();
            System.exit(0);
        }
    }

    public void abrirSobre(ActionEvent event) {
        pararVideo();
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow();
        fxmlPopUp = new FXMLLoader(getClass().getResource("/interfaces/sobre.fxml"));
          
        Popup popup = new Popup();    
        
        try {
            popup.getContent().add((Parent) fxmlPopUp.load());
        } catch (IOException ex) {
            Logger.getLogger(ModelInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        SobreController sobreController = fxmlPopUp.getController();
        popup.show(janela);        
        popup.addEventHandler(ActionEvent.ACTION, new WeakEventHandler<>(evento -> {            
            popup.hide();     
            
        }));
        
        popup.setOnHidden((final WindowEvent e) -> {
            //fazer alguma coisa depois que fechar o sobre
        });

    }

    public void carregarVideo() {

        URL file = this.getClass().getResource("_video/videoExplicativo1.flv");

        media = new Media(file.toString());
        mediaPlayer = new MediaPlayer(media);
        //MediaView mediaView = new MediaView(mediaPlayer);
        //anchorPane.getChildren().add(mediaView);
        //mediaPlayer.setAutoPlay(true);
        videoExplicativo.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

    }

    public void pararVideo() {
        try{
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }catch(Exception e){
            
        }
            
        
    }
}
