package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author iran
 */
public class PopUpNivelFinalizadoController implements Initializable {

    @FXML
    private Button nivel1;
    @FXML
    private Button nivel2;
    @FXML
    private Button nivel3;
    @FXML
    private Button sair;
    @FXML
    private Button reiniciar;
    private Button botaoClicado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    /**
     * Retorna o botão que foi clicado
     * @return botao
     */
    public Button getBotaoClicado() {
        return this.botaoClicado;
    }

    /**
     * Salva o botão que foi clicado pelo jogador e fecha o pop up
     *
     * @param event botão clicado
     */
    @FXML
    private void tratarBotaoClicado(ActionEvent event) {
        this.botaoClicado = (Button) event.getSource();
        ((Stage) botaoClicado.getScene().getWindow()).close();
    }

    /**
     * Bloqueia o nível finalizado
     *
     * @param nivel nivel que deve ser bloqueado
     */
    public void bloquearNivel(int nivel) {
        switch (nivel) {
            case 1:
                nivel1.setDisable(true);
                break;
            case 2:
                nivel2.setDisable(true);
                break;
            case 3:
                nivel3.setDisable(true);
                break;            
        }
    }
    
}
