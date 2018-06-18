/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/**
 *
 * @author iran
 */
public class ModelPopUpGrupo {

    @FXML
    private Label pontuacaoJogador;
    @FXML
    private ImageView estrela1;
    @FXML
    private ImageView estrela2;
    @FXML
    private ImageView estrela3;

    @FXML
    private Button continuar;

    long pontuacaoFase = 0;
    Image estrelaAmarela = new Image(getClass().getResourceAsStream("imagens/icones/estrelaAmarela.png"));
    Image estrelaCinza = new Image(getClass().getResourceAsStream("imagens/icones/estrelaCinza.png"));
    private EventHandler<ActionEvent> eventoEstrela1, eventoEstrela2, eventoEstrela3;
    Image imgEstrela1 = null, imgEstrela2 = null, imgEstrela3 = null;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView = new MediaView();
    private Timer timer;
    private int i = 0;

    public ModelPopUpGrupo(ImageView estrela1, ImageView estrela2, ImageView estrela3, Label pontuacaoJogador, Button continuar) {
        this.estrela1 = estrela1;
        this.estrela2 = estrela2;
        this.estrela3 = estrela3;
        this.pontuacaoJogador = pontuacaoJogador;
        this.pontuacaoFase = 0;

    }
    
    public void setPontuacaoJogador(int pontuacao, Double tempo, int cliques, int grupo, int nivel) {
        double ajusteGrupo = 1;
        bloquearBotaoContinuar(grupo);//bloqueia o botão continuar que aparece no popup
        if(grupo==3 || grupo == 4){
            ajusteGrupo = 1.5;
        }
        Double pontosRanking = (((pontuacao * (tempo) + pontuacao) / cliques) * 100) * ajusteGrupo;
        pontuacaoFase = Math.round(pontosRanking);
        atualizarPontuacao();
        setQuantidadeEstrelas(Math.round(pontosRanking), nivel);
    }

    private void setQuantidadeEstrelas(long round, int nivel) {
        int max = 0, med = 0, min = 0;

        switch (nivel) {
            case 1:
                max = 400;
                med = 200;
                min = 100;
                break;
            case 2:
                max = 380;
                med = 180;
                min = 80;
                break;
            case 3:
                max = 300;
                med = 100;
                min = 0;
                break;
        }
        if (round > max) {
            imgEstrela1 = estrelaAmarela;
            imgEstrela2 = estrelaAmarela;
            imgEstrela3 = estrelaAmarela;
        } else if (round >= med && round <= max) {
            imgEstrela1 = estrelaAmarela;
            imgEstrela2 = estrelaAmarela;
            imgEstrela3 = estrelaCinza;
        } else if (round >= min && round < med) {
            imgEstrela1 = estrelaAmarela;
            imgEstrela2 = estrelaCinza;
            imgEstrela3 = estrelaCinza;
        } else if (round < 100) {
            imgEstrela1 = estrelaCinza;
            imgEstrela2 = estrelaCinza;
            imgEstrela3 = estrelaCinza;
        }

        eventoEstrela1 = (ActionEvent event) -> {
            estrela1.setImage(imgEstrela1);
            tocarAudioEfeito("estrela01");
        };
        eventoEstrela2 = (ActionEvent event) -> {
            estrela2.setImage(imgEstrela2);
            tocarAudioEfeito("estrela02");
        };
        eventoEstrela3 = (ActionEvent event) -> {
            estrela3.setImage(imgEstrela3);
            if (!imgEstrela3.equals(estrelaCinza)) {
                tocarAudioEfeito("estrela03");
            }
        };

        new Timeline(new KeyFrame(Duration.seconds(0.5), eventoEstrela1),
                new KeyFrame(Duration.seconds(1.5), eventoEstrela2),
                new KeyFrame(Duration.seconds(2.5), eventoEstrela3)).play();
    }

    public void bloquearBotaoContinuar(int fase) {
        if (fase == 7) {

        }
    }

    public long getPontuacaoJogador() {
        return pontuacaoFase;
    }

    /**
     * Toca um efeito sonoro
     *
     * @param efeito nome do efeito sonoro
     */
    public void tocarAudioEfeito(String efeito) {
        String caminhoAudio = "";
        caminhoAudio = "_audios/efeitos/" + efeito + ".mp3";
        URL file = getClass().getResource(caminhoAudio);
        media = new Media(file.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

    }

    public void atualizarPontuacao() {
        i = 0;
        timer = new Timer();
        //criação da tarefa que vai executar durante 1 segundo
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                //Platform.runLater para alterar elementos da interface do javaFX
                Platform.runLater(() -> {
                    if (i < pontuacaoFase) {
                        i = i + 1;
                        pontuacaoJogador.setText("" + i);
                    }else{
                        System.out.println("Timer popup cancelado");
                        timer.cancel();
                    }
                    

                });
            }
        }, 0, 5);
    }

}
