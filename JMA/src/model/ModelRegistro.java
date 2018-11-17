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
        boolean sucesso = false;
        String jdbcUrl = "jdbc:mysql://localhost/programas?user=root&password=anabolero";
        //String jdbcUrl = "jdbc:mysql://localhost/programas?user=root";
        Connection con = DriverManager.getConnection(jdbcUrl);
        Statement stmt = con.createStatement();
        String numeroRegistro = numeroRegistro();
        this.senhaUsuario = senhaUsuario;
        this.emailUsuario = emailUsuario;
        String SQL = "INSERT INTO `jma` "
                + "(`email`,`senha`,`nr_registro`) "
                + "VALUES('"+emailUsuario+"','"+senhaUsuario+"','"+numeroRegistro+"')";
        int cont = stmt.executeUpdate(SQL);        
        if(cont>=1){
            sucesso = true;
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
        return "1234567";
    }

}
