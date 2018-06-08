package controller;

import model.ModelPopUpFase;
import model.ClasseEstatica;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author iran
 */
public class PopUpController implements Initializable {

    @FXML
    private Button sair;
    @FXML
    private Button reiniciar;
    @FXML
    private Button continuar;
    public Stage stage;
    private Button botaoClicado = new Button();
    @FXML
    private Label pontuacaoJogador;
    @FXML
    private ImageView estrela1;
    @FXML
    private ImageView estrela2;
    @FXML
    private ImageView estrela3;
    
    private ModelPopUpFase modelPopUp;
    @FXML
    private Label labelParabens;
    
    private Popup popUp;
    
    public ClasseEstatica classe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPopUp = new ModelPopUpFase(estrela1,estrela2,estrela3,pontuacaoJogador,continuar);
        classe = new ClasseEstatica();
        popUp = null;
        
       
    }
    /**
     * Retorna qual dos três botões do pop up foi clicado
     * @return botão clicado
     */
    public Button getBotaoClicado() {
        return botaoClicado;
    }  
    
    /**
     * Salva o botão que foi clicado pelo jogador e fecha o pop up
     * @param event botão clicado
     */
    @FXML
    public void tratarBotaoClicado(ActionEvent event) {
        this.botaoClicado = (Button)event.getSource();
        ClasseEstatica.idBotao = botaoClicado.getId();        
        System.out.println(ClasseEstatica.idBotao);
    }
    
    
    /**
     * Define a pontuação que aparecerá no pop-up
     * @param pontuacao pontuação do jogador no nível
     */
    public void setPontuacaoJogador(int pontuacao, Double tempo, int cliques,int fase, int nivel){
        modelPopUp.setPontuacaoJogador(pontuacao,tempo,cliques,fase,nivel);
        
    }

    public long getPontuacaoJogador() {
        return modelPopUp.getPontuacaoJogador();
    }

    public void setPopUp(Popup popUp) {
        this.popUp = popUp;
    }
}
