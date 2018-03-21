/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.RankingController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author iran
 */
public class ModelInicial {

    private Stage janela;
    private FXMLLoader fxmloader;
    private RankingController rankingController;
    private Parent cenaPrincipal;

    public ModelInicial() {

    }
    @FXML
    public void iniciarJogo(ActionEvent botao) throws IOException {
        janela = (Stage) ((Button) botao.getSource()).getScene().getWindow();        
        fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Ranking.fxml"));
        cenaPrincipal = (Parent) fxmloader.load();
        rankingController = fxmloader.<RankingController>getController();        
        Scene scene = new Scene(cenaPrincipal, 1200, 700);
        janela.setScene(scene);
        janela.setFullScreen(true);
        janela.setFullScreenExitHint("");
        janela.show();
    }

}
