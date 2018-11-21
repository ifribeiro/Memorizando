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
import model.ClasseEstatica;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.Rectangle2D;
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
        String jdbcUrl = ClasseEstatica.jdbcUrl;
        //String jdbcUrl = "jdbc:mysql://localhost/programas?user=root";
        Connection con = DriverManager.getConnection(jdbcUrl);
        Statement stmt = con.createStatement();

        Font.loadFont(getClass().getResource("/fontes/Choko.ttf").toExternalForm(), 42);
        Rectangle2D tamanhoDisplay = Screen.getPrimary().getVisualBounds();
        Double comprimento = tamanhoDisplay.getWidth();
        Scene cena = null;

        if (!isRegistrado(stmt)) {
            FXMLLoader interfaceRegistro = new FXMLLoader(getClass().getResource("/interfaces/RegistroPC.fxml"));
            Parent cenaInicial = (Parent) interfaceRegistro.load();
            registroController = interfaceRegistro.<RegistroController>getController();
            cena = new Scene(cenaInicial, 480, 300);
            janela.setTitle("Jogo de Memória Auditiva");
            janela.setScene(cena);
            janela.setFullScreenExitHint("");
            janela.show();

        } else {
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

    public boolean isRegistrado(Statement stmt) throws SQLException {
        String serialNumber = numeroRegistro();       
        String numRegistro = "";
        boolean registrado = false;
        String SQL = "SELECT * FROM `jma` WHERE nr_registro='" +serialNumber+ "'";
        ResultSet resultado = stmt.executeQuery(SQL);
        while (resultado.next()) {            
            numRegistro = resultado.getString("nr_registro");
        }
        if (numRegistro.isEmpty()) {           
            registrado = false;
        } else {
            if (serialNumber.equals(numRegistro)) {
                registrado = true;
            }

        }
        return registrado;
    }

    private String numeroRegistro() {
        String SO = getSO();//versao do SO
        String numeroRegistro = "";
        switch (SO) {
            case "WINDOWS":
                numeroRegistro = getNumeroRegistroWindows();
                break;
            case "LINUX":
                numeroRegistro = getNumeroRegistroLinux();
                break;
        }
        return numeroRegistro;
    }

    private String getSO() {
        String SO = "";
        String nome = System.getProperty("os.name");
        System.out.println("Nome "+nome);
        nome = nome.toUpperCase();
        if (nome.contains("WINDOWS")) {
            SO = "WINDOWS";
        } else {
            SO = "LINUX";
        }
        return SO;
    }

    private String getNumeroRegistroWindows() {
        String result = "";
        try {
            Process p = Runtime.getRuntime().exec("wmic baseboard get serialnumber");
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (IOException ex) {

        }
        String[] numero = result.split(" ");
        return numero[1].trim();
    }

    private String getNumeroRegistroLinux() {
        //fazer versao do linux
        return "123456";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
