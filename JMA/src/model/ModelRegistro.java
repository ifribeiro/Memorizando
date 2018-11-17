/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 *
 * @author iran
 */
public class ModelRegistro {

    private String emailUsuario;
    private String senhaUsuario;

    public ModelRegistro() {
        this.emailUsuario = "";
        this.senhaUsuario = "";
    }

    public boolean registrarPC(String emailUsuario, String senhaUsuario) throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost/programas?user=root&password=anabolero";
        //String jdbcUrl = "jdbc:mysql://localhost/programas?user=root";
        Connection con = DriverManager.getConnection(jdbcUrl);
        Statement stmt = con.createStatement();
        boolean sucesso = false;
        if (emailCadastrado(emailUsuario, stmt)) {
            if (isRegistrado(stmt)) {
                sucesso = true;
            } else {
                sucesso = atualizarRegistro(stmt, emailUsuario);
            }   
        } else {
            Alert confirmacaoSaida = new Alert(Alert.AlertType.WARNING,
                    "Insira o mesmo e-mail cadastrado na compra do jogo");
            Button botaoOK = (Button) confirmacaoSaida.getDialogPane().lookupButton(ButtonType.OK);
            botaoOK.setText("Fechar");            
            confirmacaoSaida.setTitle(null);
            confirmacaoSaida.setHeaderText(null);
            //confirmacaoSaida.setContentText("Deseja mesmo sair do jogo?");
            Optional<ButtonType> resposta = confirmacaoSaida.showAndWait();
            if (ButtonType.OK.equals(resposta.get())) {
                confirmacaoSaida.close();;
                
            }
        }

        return sucesso;
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
        return numero[2];
    }

    private String getNumeroRegistroLinux() {
        //fazer versao do linux
        return "1234567B";
    }

    private boolean emailCadastrado(String email, Statement stmt) throws SQLException {
        String emailRegSQL = "SELECT * FROM `jma` WHERE email='" + email + "'";
        ResultSet resultado = stmt.executeQuery(emailRegSQL);
        int rows = 0;
        boolean emailCadastrado = false;
        while (resultado.next()) {
            rows++;
        }
        if (rows > 0) {
            emailCadastrado = true;
        }
        System.out.println("Row " + rows);
        return emailCadastrado;
    }

    private boolean atualizarRegistro(Statement stmt, String email) throws SQLException {
        System.out.println("Atualizando registro...");
        boolean sucesso = false;
        String numeroRegistro = numeroRegistro();
        String SQL = "UPDATE `jma` SET"
                + "`nr_registro`= '" + numeroRegistro + "'"
                + "WHERE `email`= '" + email + "'";
        int cont = stmt.executeUpdate(SQL);
        if (cont >= 1) {
            sucesso = true;
        }
        return sucesso;
    }

    private boolean isRegistrado(Statement stmt) throws SQLException {
        String serialNumber = numeroRegistro();
        String numRegistro = "";
        boolean registrado = false;
        String SQL = "SELECT * FROM `jma` WHERE nr_registro='" + serialNumber + "'";
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

}
