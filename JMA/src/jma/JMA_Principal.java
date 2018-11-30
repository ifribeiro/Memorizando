package jma;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import controller.InicialController;
import controller.RegistroController;
import model.Funcoes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Screen;

/**
 *
 * @author iran
 */
public class JMA_Principal extends Application {

    private InicialController inicial;
    private RegistroController registroController;
    @FXML
    private Stage janela;
    private Connection con = null;
    private boolean semConexao = false;
    
    @Override
    public void init(){
        String jdbcUrl = Funcoes.jdbcUrl;        
        try {
            con = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException ex) {
            semConexao = true;
        }
    }
    

    @Override
    public void start(Stage janela) throws IOException, SQLException {       
        Funcoes funcoes = new Funcoes();
        if (funcoes.arquivoRegistroExiste()) {
            if (semConexao) {
                iniciarJogoOffline(funcoes, janela);
            } else {
                iniciarJogo(funcoes, con, janela);
            }
        } else {
            System.out.println("Arquivo não existe");
            if(semConexao==true){
                funcoes.mensagemErroConexao();
            }           
            //se conseguir conectar
            if (!semConexao) {
                System.out.println("Conseguiu conectar");
                iniciarJogo(funcoes, con, janela);             
                
            } else {
                System.out.println("Algo deu errado... ");
                System.exit(0);
            }

        }

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void iniciarJogo(Funcoes funcoes, Connection con, Stage janela) throws SQLException, SQLException, IOException {
        Image image = new Image(this.getClass().getResourceAsStream("jmaico.png"));        
        janela.getIcons().add(image);
        Statement stmt = con.createStatement();
        Font.loadFont(getClass().getResource("/fontes/Choko.ttf").toExternalForm(), 42);
        Rectangle2D tamanhoDisplay = Screen.getPrimary().getVisualBounds();
        Double comprimento = tamanhoDisplay.getWidth();
        Scene cena = null;
        if (!funcoes.isRegistrado(stmt)) {
            FXMLLoader interfaceRegistro = new FXMLLoader(getClass().getResource("/interfaces/RegistroPC.fxml"));
            Parent cenaInicial = (Parent) interfaceRegistro.load();
            registroController = interfaceRegistro.<RegistroController>getController();
            cena = new Scene(cenaInicial, 480, 300);

            janela.setTitle("Jogo de Memória Auditiva");
            janela.setScene(cena);
            janela.setFullScreenExitHint("");
            janela.resizableProperty().setValue(Boolean.FALSE);
            janela.show();

        } else {
            String registro = funcoes.numeroRegistro();
            funcoes.sincronizarRegistros(registro);
            if (comprimento > 1100) {
                FXMLLoader interfaceWideScreen = new FXMLLoader(getClass().getResource("/interfaces/Inicial.fxml"));
                Parent cenaInicial = (Parent) interfaceWideScreen.load();
                inicial = interfaceWideScreen.<InicialController>getController();
                cena = new Scene(cenaInicial, 1366, 768);
            } else {
                FXMLLoader interfaceQuadrada = new FXMLLoader(getClass().getResource("/interfaces/InicialQuadrada.fxml"));
                Parent cenaInicial = (Parent) interfaceQuadrada.load();
                inicial = interfaceQuadrada.<InicialController>getController();
                cena = new Scene(cenaInicial, 1024, 711);
            }

            janela.setTitle("Jogo de Memória Auditiva");

            janela.setScene(cena);
            janela.setFullScreen(true);
            janela.setFullScreenExitHint("");
            janela.show();

        }
    }

    public void iniciarJogoOffline(Funcoes funcoes, Stage janela) throws IOException {

        Font.loadFont(getClass().getResource("/fontes/Choko.ttf").toExternalForm(), 42);
        Rectangle2D tamanhoDisplay = Screen.getPrimary().getVisualBounds();
        Double comprimento = tamanhoDisplay.getWidth();
        Scene cena = null;
        if (comprimento > 1100) {
            FXMLLoader interfaceWideScreen = new FXMLLoader(getClass().getResource("/interfaces/Inicial.fxml"));
            Parent cenaInicial = (Parent) interfaceWideScreen.load();
            inicial = interfaceWideScreen.<InicialController>getController();
            cena = new Scene(cenaInicial, 1366, 768);
        } else {
            FXMLLoader interfaceQuadrada = new FXMLLoader(getClass().getResource("/interfaces/InicialQuadrada.fxml"));
            Parent cenaInicial = (Parent) interfaceQuadrada.load();
            inicial = interfaceQuadrada.<InicialController>getController();
            cena = new Scene(cenaInicial, 1024, 711);
        }

        janela.setTitle("Jogo de Memória Auditiva");

        janela.setScene(cena);
        janela.setFullScreen(true);
        janela.setFullScreenExitHint("");
        janela.show();

    } 

}
