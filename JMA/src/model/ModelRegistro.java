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
    private Funcoes funcoes = new Funcoes();

    public ModelRegistro() {
        this.emailUsuario = "";
        this.senhaUsuario = "";
    }

    public boolean registrarPC(String emailUsuario, String senhaUsuario) throws SQLException {
        String jdbcUrl = Funcoes.jdbcUrl;
        //String jdbcUrl = "jdbc:mysql://localhost/programas?user=root";
        Connection con = DriverManager.getConnection(jdbcUrl);
        Statement stmt = con.createStatement();
        boolean sucesso = false;
        
        if (emailCadastrado(emailUsuario, stmt)) {
            if (funcoes.isRegistrado(stmt)) {
                sucesso = true;
            } else {
                mensagemAtualizacao();
                sucesso = atualizarRegistro(stmt, emailUsuario);
                con.close();
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
        String numeroRegistro = funcoes.numeroRegistro();
        String SQL = "UPDATE `jma` SET"
                + "`nr_registro`= '" + numeroRegistro + "'"
                + "WHERE `email`= '" + email + "'";
        int cont = stmt.executeUpdate(SQL);
        if (cont >= 1) {
            sucesso = true;
        }
        return sucesso;
    }

    private void mensagemAtualizacao() {
        Alert confirmacaoSaida = new Alert(Alert.AlertType.CONFIRMATION,
                "Você já possui um computador registrado com esse e-mail, deseja atualizar o registro?");
        Button botaoSIM = (Button) confirmacaoSaida.getDialogPane().lookupButton(ButtonType.OK);
        Button botaoNAO = (Button) confirmacaoSaida.getDialogPane().lookupButton(ButtonType.CANCEL);
        botaoSIM.setText("Sim");
        botaoNAO.setText("Não");
        confirmacaoSaida.setTitle(null);
        confirmacaoSaida.setHeaderText(null);
        //confirmacaoSaida.setContentText("Deseja mesmo sair do jogo?");
        Optional<ButtonType> resposta = confirmacaoSaida.showAndWait();
        if (ButtonType.CANCEL.equals(resposta.get())) {
            confirmacaoSaida.close();
        }
    }

}
