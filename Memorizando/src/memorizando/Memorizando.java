package memorizando;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author iran
 */
public class Memorizando extends Application {

    @FXML
    private Stage janela;

    @Override
    public void start(Stage janela) throws IOException {
        Parent cenaInicial = FXMLLoader.load(getClass().getResource("/interfaces/Principal.fxml"));
        
        Scene cena = new Scene(cenaInicial, 1366, 768);
        janela.setTitle("Memorizando");
        janela.setScene(cena);
        janela.setFullScreen(true);
        janela.setFullScreenExitHint("");
        janela.show();
        //janela.centerOnScreen();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
