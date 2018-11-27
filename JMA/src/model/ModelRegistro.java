/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

    public boolean registrarPC(String emailUsuario, String senhaUsuario) throws SQLException, NoSuchAlgorithmException, IOException {
        
        boolean sucesso = false;
        if (emailUsuario.isEmpty()) {
            mensagemInsiraEmail();
            return false;
        }
        if (senhaUsuario.isEmpty()) {
            mensagemInsiraSenha();
            return false;
        }
        String jdbcUrl = Funcoes.jdbcUrl;
        //String jdbcUrl = "jdbc:mysql://localhost/programas?user=root";
        Connection con = DriverManager.getConnection(jdbcUrl);
        Statement stmt = con.createStatement();
        if (emailCadastrado(emailUsuario, stmt)) {
            if (funcoes.isRegistrado(stmt)) {
                System.out.println("Email cadastrado");
                System.exit(0);
                sucesso = true;
                String registro = funcoes.numeroRegistro();
                funcoes.sincronizarRegistros(registro);
            } else if (primeiroRegistro(stmt, emailUsuario)) {
                System.out.println("Primeiro registro");
                sucesso = inserirRegistro(stmt, emailUsuario, senhaUsuario);
                String registro = funcoes.numeroRegistro();
                funcoes.salvarRegistro(registro);
                con.close();
            } else {
                mensagemAtualizacao();
                sucesso = atualizarRegistro(stmt, emailUsuario, senhaUsuario);
                String registro = funcoes.numeroRegistro();
                funcoes.sincronizarRegistros(registro);
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
                confirmacaoSaida.close();
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

    private boolean atualizarRegistro(Statement stmt, String email, String senhaUsuario) throws SQLException, NoSuchAlgorithmException {
        boolean sucesso = false;
        if (senhaConfere(stmt, email, senhaUsuario)) {
            String numeroRegistro = funcoes.numeroRegistro();
            String SQL = "UPDATE `jma` SET"
                    + "`nr_registro`= '" + numeroRegistro + "'"
                    + "WHERE `email`= '" + email + "'";
            int cont = stmt.executeUpdate(SQL);
            if (cont >= 1) {
                sucesso = true;
            }
        } else {
            System.out.println("A senha nao confere");
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

    /**
     * Verifica se a senha esta em branco
     *
     * @return
     */
    private boolean primeiroRegistro(Statement stmt, String email) throws SQLException {
        boolean primeiroRegistro = false;
        System.out.println("email " + email);
        String sqlSenha = "SELECT `senha` FROM `jma` WHERE `email`='" + email + "'";
        ResultSet rs = stmt.executeQuery(sqlSenha);
        String senhaUsuario = "";
        int rows = 0;
        while (rs.next()) {
            senhaUsuario = rs.getString("senha");
            rows++;
        }
        if (senhaUsuario.isEmpty()) {
            primeiroRegistro = true;
            System.out.println("Primeiro registro falso");
        }
        return primeiroRegistro;
    }

    /**
     * Insere um usuario no banco
     *
     * @param stmt
     * @param emailUsuario
     * @param senhaUsuario
     * @return
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     */
    private boolean inserirRegistro(Statement stmt, String emailUsuario, String senhaUsuario) throws NoSuchAlgorithmException, SQLException {
        String senha = funcoes.getSecurePassword(senhaUsuario);
        boolean sucesso = false;
        String numeroRegistro = funcoes.numeroRegistro();
        String SQL = "UPDATE `jma` SET"
                + "`nr_registro`= '" + numeroRegistro + "', senha = '" + senha + "'"
                + "WHERE `email`= '" + emailUsuario + "'";
        int cont = stmt.executeUpdate(SQL);
        if (cont >= 1) {
            sucesso = true;
        }
        return sucesso;
    }

    private boolean senhaConfere(Statement stmt, String emailUsuario, String senhaUsuario) throws SQLException, NoSuchAlgorithmException {
        boolean sucesso = false;
        String senhaComp = "";
        String senhaHash = funcoes.getSecurePassword(senhaUsuario);
        String senhaIgual = "SELECT senha FROM `jma` WHERE `email`='" + emailUsuario + "'";
        ResultSet rs = stmt.executeQuery(senhaIgual);
        while (rs.next()) {
            senhaComp = rs.getString("senha");
        }
        if (senhaComp.equals(senhaHash)) {
            sucesso = true;
        }
        return sucesso;
    }

    private void mensagemInsiraEmail() {
        Alert confirmacaoSaida = new Alert(Alert.AlertType.WARNING,
                "O campo e-mail não pode ficar vazio");
        Button botaoOK = (Button) confirmacaoSaida.getDialogPane().lookupButton(ButtonType.OK);
        botaoOK.setText("Fechar");
        confirmacaoSaida.setTitle(null);
        confirmacaoSaida.setHeaderText(null);
        //confirmacaoSaida.setContentText("Deseja mesmo sair do jogo?");
        Optional<ButtonType> resposta = confirmacaoSaida.showAndWait();
        if (ButtonType.OK.equals(resposta.get())) {
            confirmacaoSaida.close();
        }
    }

    private void mensagemInsiraSenha() {
        Alert confirmacaoSaida = new Alert(Alert.AlertType.WARNING,
                    "O campo senha não pode ficar vazio");
            Button botaoOK = (Button) confirmacaoSaida.getDialogPane().lookupButton(ButtonType.OK);
            botaoOK.setText("Fechar");
            confirmacaoSaida.setTitle(null);
            confirmacaoSaida.setHeaderText(null);
            //confirmacaoSaida.setContentText("Deseja mesmo sair do jogo?");
            Optional<ButtonType> resposta = confirmacaoSaida.showAndWait();
            if (ButtonType.OK.equals(resposta.get())) {
                confirmacaoSaida.close();
            }
    }
}
