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

    @Override
    public void start(Stage janela) throws IOException, SQLException {
        boolean semConexao = false;
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Nao achou o driver");
        }
        String jdbcUrl = Funcoes.jdbcUrl;
        Funcoes funcoes = new Funcoes();
        if (funcoes.arquivoRegistroExiste()) {
            System.out.println("Arquivo existe");
            try {
                con = DriverManager.getConnection(jdbcUrl);                
            } catch (SQLException ex) {
                System.out.println("Algo deu errado... " + ex);
                semConexao = true;
            }
            if(semConexao){
                iniciarJogoOffline(funcoes, janela);
            }else{
                iniciarJogo(funcoes, con, janela);
            }       
        } else {
            try {
                con = DriverManager.getConnection(jdbcUrl);
            } catch (SQLException ex) {
                mensagemErroConexao();
                semConexao = true;
            }
            if (!semConexao) {
                iniciarJogo(funcoes, con, janela);
            } else {
                System.out.println("Algo deu errado... ");
                System.exit(0);
            }

        }

    }

    private void mensagemErroConexao() {
        Alert confirmacaoSaida = new Alert(Alert.AlertType.ERROR,
                "Não foi possível conectar com nossos serviços, por favor, verifique sua conexão e tente novamente");
        Button botaoSIM = (Button) confirmacaoSaida.getDialogPane().lookupButton(ButtonType.OK);
        botaoSIM.setText("Sim");
        confirmacaoSaida.setTitle(null);
        confirmacaoSaida.setHeaderText(null);
        //confirmacaoSaida.setContentText("Deseja mesmo sair do jogo?");
        Optional<ButtonType> resposta = confirmacaoSaida.showAndWait();
        if (ButtonType.OK.equals(resposta.get())) {
            confirmacaoSaida.close();

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void iniciarJogo(Funcoes funcoes, Connection con, Stage janela) throws SQLException, SQLException, IOException {
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
            janela.show();

        } else {
            String registro = funcoes.getNumeroRegistroWindows();
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
