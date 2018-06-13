/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import model.ModelInicial;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author iran
 */
public class InicialController implements Initializable {

    @FXML
    private Button iniciar;

    private ModelInicial modelInicial;
    @FXML
    private Button sobre;
    @FXML
    private Label jogo;
    @FXML
    private Button sair;
    @FXML
    private MediaView videoExplicativo;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jogo.getStyleClass().add("fonte64");
        sobre.getStyleClass().add("fonte20");
        URL file = this.getClass().getResource("_video/videoExplicativo1.mp4");      
        
        /*
        media = new Media(file.toString());
        mediaPlayer = new MediaPlayer(media);
        //MediaView mediaView = new MediaView(mediaPlayer);
        //anchorPane.getChildren().add(mediaView);
        mediaPlayer.setAutoPlay(true);
        videoExplicativo.setMediaPlayer(mediaPlayer);
        //mediaPlayer.play();*/
        modelInicial = new ModelInicial(videoExplicativo);
        //modelInicial.carregarVideo();
        

    }

    @FXML
    private void iniciarJogo(ActionEvent event) throws IOException {
        modelInicial.iniciarJogo(event);
    }

    @FXML
    private void abrirSobre(ActionEvent event) {
        modelInicial.abrirSobre(event);
    }

    @FXML
    private void sair(ActionEvent event) {
        modelInicial.sairDoJogo(event);
    }

    public void carregarVideo() {
        modelInicial.carregarVideo();
    }

}
