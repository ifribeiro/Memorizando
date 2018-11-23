/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.stage.Popup;

/**
 *
 * @author iran
 */
public class Funcoes {
    public static String idBotao = "";
    public static Popup popup = new Popup();
    public static String jdbcUrl = "jdbc:mysql://johnny.heliohost.org/ifreitas_programas?user=ifreitas_useredu&password=usereduhelios";
    public Funcoes() {
        
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
    /**
     * Retorna o numero de registro de um SO
     * @return string com o numero
     */
    public String numeroRegistro() {
        String SO = getSO();//versao do SO
        String numeroRegistro = "";
        if(SO.contains("WINDOWS")){
            numeroRegistro = getNumeroRegistroWindows();
        }else if(SO.contains("LINUX")){
            numeroRegistro = getNumeroRegistroLinux();
        }        
        return numeroRegistro;
    }

    public String getSO() {
        String SO = "";
        String nome = System.getProperty("os.name");
        System.out.println("Nome "+nome);
        nome = nome.toUpperCase();
        switch (nome) {
            case "WINDOWS 7":
                SO = "WINDOWS 7";
                break;
            case "WINDOWS 10":
                SO = "WINDOWS 10";
                break;
            default:
                SO = "LINUX";
                break;
        }
        return SO;
    }

    public String getNumeroRegistroWindows() {
        String result = "";
        String serial = getBaseBoard();
        if(serial.isEmpty()){
            serial = getBiosNumber();
        }
        
        if(serial.isEmpty()){
            
        }
        
        return serial;
    }

    public String getNumeroRegistroLinux() {
        //fazer versao do linux
        return "123456";
    }
    
    private String getBaseBoard(){
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
        String[] numero = result.split("\\s+");        
        return numero[1].trim();
    }

    private String getBiosNumber() {
        String result = "";
        try {
            Process p = Runtime.getRuntime().exec("wmic bios get serialnumber");
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (IOException ex) {

        }        
        String[] numero = result.split("\\s+");
        System.out.println("Numero "+numero[1].trim());
        return numero[1].trim();
    }
    
}
