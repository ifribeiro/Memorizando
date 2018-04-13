package model;

import controller.RankingController;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
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
        //Popup popup = new Popup();
        
        
        
        
        

        //popup.show(janela);
        
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
}
