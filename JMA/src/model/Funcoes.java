/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Popup;
import javax.swing.filechooser.FileSystemView;

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

    /**
     * Retorna o numero de registro de um SO
     *
     * @return string com o numero
     */
    public String numeroRegistro() {
        String SO = getSO();//versao do SO
        String numeroRegistro = "";
        if (SO.contains("WINDOWS")) {
            numeroRegistro = getNumeroRegistroWindows();
        } else if (SO.contains("LINUX")) {
            numeroRegistro = getNumeroRegistroLinux();
        }
        return numeroRegistro;
    }

    /**
     * Retorna
     *
     * @return
     */
    public String getSO() {
        String SO = "";
        String nome = System.getProperty("os.name");
        System.out.println("Nome " + nome);
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
        String serial = "";
        //serial = getBaseBoard();
        if (serial.isEmpty()) {
            serial = getBiosNumber();
        }

        if (serial.isEmpty()) {

        }

        return serial;
    }

    public String getNumeroRegistroLinux() {
        //String numeroRegistro = getMacSistema();
        String numeroRegistro = "1234567";
        return numeroRegistro;
    }

    private String getBaseBoard() {
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
        System.out.println("Numero " + numero[1].trim());
        return numero[1].trim();
    }

    public String getSecurePassword(String passwordToHash) throws NoSuchAlgorithmException {
        String generatedPassword = null;
        byte salt[] = getSalt();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    //Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = {-35, -23, 9, -16, 68, 66, 30, 7, 35, 67, 108, 28, -24, -70, -70, -14};
        return salt;
    }

    public void sincronizarRegistros(String numeroRegisrto) throws IOException {
        String caminho = caminhoArquivo();
        String caminhoPasta = caminhoPasta();
        File pasta = new File(caminhoPasta);
        boolean pastaExiste = false;
        if (pasta.isDirectory()) {
            pastaExiste = true;
        } else {
            pastaExiste = new File(caminhoPasta).mkdirs();
            System.out.println("Pasta existe "+pastaExiste);            
        }
        if (pastaExiste) {
            File arquivo = new File(caminho);
            try{
                arquivo.delete();
            }
            catch(Exception ex){
                System.out.println("Ex "+ex);
            }
            
            arquivo.createNewFile();
            FileWriter fw = new FileWriter(arquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(numeroRegisrto);
            bw.flush();
            fw.close();
        }

        //apagar o arquivo de registro
        //salvar o novo arquivo de registro
    }

    public boolean arquivoRegistroExiste() throws FileNotFoundException, IOException {
        boolean existe = false;
        String caminho = "";
        caminho = caminhoArquivo();
        File a = new File(caminho);
        if (a.exists() && !a.isDirectory()) {
            existe = true;
        }      
        return existe;
    }

    public String caminhoArquivo() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] roots = fsv.getRoots();
        String home = fsv.getHomeDirectory().getAbsolutePath();             
        String caminho = "";        
        for (int i = 0; i < roots.length; i++) {
            caminho = roots[i].toString();
        }
        if (getSO().contains("WINDOWS")) {
            caminho = caminho.replace("Desktop", "jma\\registro.txt");
        } else if (getSO().contains("LINUX")) {            
            caminho = home+"/jma/registro.txt";            
        }
        return caminho;
    }

    public String caminhoPasta() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] roots = fsv.getRoots();
        String home = fsv.getHomeDirectory().getAbsolutePath();
        String caminho = "";
        for (int i = 0; i < roots.length; i++) {
            caminho = roots[i].toString();
        }
        if (getSO().contains("WINDOWS")) {
            caminho = caminho.replace("Desktop", "jma\\");
        } else if (getSO().contains("LINUX")) {
            System.out.println("Caminho "+caminho);
            caminho = home+"/jma/";            
        }
        return caminho;
    }

    public void salvarRegistro(String numeroRegistro) throws IOException {
        String caminhoPasta = caminhoPasta();
        String caminhoArquivo = caminhoArquivo();
        boolean criarPasta = new File(caminhoPasta).mkdirs();
        System.out.println("Criar pasta "+criarPasta);        
        if (criarPasta) {
            File arquivoRegistro = new File(caminhoArquivo);
            arquivoRegistro.createNewFile();
            FileWriter fw = new FileWriter(arquivoRegistro, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(numeroRegistro);
            bw.flush();
            fw.close();
        }

    }

    public static String getMacSistema() {
        try {
            String mac = getMacLinux("eth0");
            if (mac == null) {
                mac = getMacLinux("eth1");
                if (mac == null) {
                    mac = getMacLinux("eth2");
                    if (mac == null) {
                        mac = getMacLinux("usb0");
                        if (mac == null) {
                            mac = getMacLinux("wlan0");
                            if (mac == null) {
                                if (mac == null) {
                                    mac = getMacLinux("wlan1");
                                    if (mac == null) {
                                        mac = getMacLinux("wlan2");
                                        if (mac == null) {
                                            if (mac == null) {
                                                mac = getMacLinux("enp1s0");
                                                if (mac == null) {
                                                    mac = getMacLinux("wlp2s0");
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }

                    }
                }
            }
            return mac;

        } catch (Exception E) {
            System.err.println("System Mac Exp : " + E.getMessage());
            return null;
        }
    }

    /**
     * Method for get MAc of Linux Machine
     *
     * @param name
     * @return
     */
    private static String getMacLinux(String name) {
        try {
            NetworkInterface network = NetworkInterface.getByName(name);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            return (sb.toString());
        } catch (Exception E) {
            System.err.println("System Linux MAC Exp : " + E.getMessage());
            return null;
        }
    }
    
    public void mensagemErroConexao() {
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
}
