/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import model.ModelPopUpGameOver;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.ClasseEstatica;

/**
 * FXML Controller class
 *
 * @author iran
 */
public class PopUpGameOverController implements Initializable {
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
    private Label nome3;
    @FXML
    private Label nome4;
    @FXML
    private Label nome5;
    @FXML
    private Label nome6;
    @FXML
    private Label nome7;
    @FXML
    private Label nome8;
    @FXML
    private Label nome9;
    @FXML
    private Label nome10;
    @FXML
    private Label pontos2;
    @FXML
    private Label pontos3;
    @FXML
    private Label pontos4;
    @FXML
    private Label pontos5;
    @FXML
    private Label pontos6;
    @FXML
    private Label pontos7;
    @FXML
    private Label pontos8;
    @FXML
    private Label pontos9;
    @FXML
    private Label pontos10;
    @FXML
    private Button sair;
    @FXML
    private Button reiniciarJogo;
    @FXML
    private Button menuInicial;
    private Button botaoClicado;
    
    private ModelPopUpGameOver modelGameOver;
    
    public ClasseEstatica classe;
    @FXML
    private Label tituloRanking;
    @FXML
    private Label colunaJogador;
    @FXML
    private Label colunaPontos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelGameOver = new ModelPopUpGameOver(img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,
            nome1,nome2,nome3,nome4,nome5,nome6,nome7,nome8,nome9,nome10,
            pontos1,pontos2,pontos3,pontos4,pontos5,pontos6,pontos7,pontos8,pontos9,pontos10);
        
        tituloRanking.getStyleClass().add("fonte32"); 
       // colunaJogador.getStyleClass().add("fonte16"); 
        //colunaPontos.getStyleClass().add("fonte16"); 
        //nome1.getStyleClass().add("fonte13");
        //nome1.getStyleClass().add("fonte13");
        //nome1.getStyleClass().add("fonte13");
       // nome1.getStyleClass().add("fonte13");
        //nome1.getStyleClass().add("fonte13");
        //nome1.getStyleClass().add("fonte13");
        //nome1.getStyleClass().add("fonte13");
        //nome1.getStyleClass().add("fonte13");
        //nome1.getStyleClass().add("fonte13");
        //nome1.getStyleClass().add("fonte13");
        
    }
    
    /**
     * Retorna o botão que foi clicado
     * @return botao
     */
    public Button getBotaoClicado() {
        return this.botaoClicado;
    }

    @FXML
    private void tratarBotoaoClicado(ActionEvent event) {
        this.botaoClicado = (Button) event.getSource();
        classe.idBotao = botaoClicado.getId();
    }

    public void atualizarRanking() throws IOException {
        modelGameOver.atualizarRanking();
    }
    
    
    
}
