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
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 *
 * @author iran
 */
public class JMA_Principal extends Application {

    private InicialController inicial;
    @FXML
    private Stage janela;

    @Override
    public void start(Stage janela) throws IOException {
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
            System.out.println("Entrou aqui");
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
