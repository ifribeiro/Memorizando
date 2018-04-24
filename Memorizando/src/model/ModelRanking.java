package model;

import controller.PrincipalController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author iran
 */
public class ModelRanking {

    @FXML
    private ImageView avatarMaior;

    @FXML
    private TextField nomeJogador;
    @FXML
    private Stage janela;
    private FXMLLoader fxmloader;
    private Parent cenaPrincipal;
    private PrincipalController principalController;

    @FXML
    private Button iniciar;

    private int avatar;

    @FXML
    private Button avatar1;
    @FXML
    private Button avatar2;
    @FXML
    private Button avatar3;
    @FXML
    private Button avatar4;
    @FXML
    private Button avatar5;
    @FXML
    private Button avatar6;
    @FXML
    private Button avatar7;
    @FXML
    private Button avatar8;
    @FXML
    private Button avatar9;
    @FXML
    private Button avatar10;
    private Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;
    @FXML
    private ImageView img7;
    @FXML
    private ImageView img8;
    @FXML
    private ImageView img9;
    @FXML
    private ImageView img10;
    @FXML
    private Label nome1;
    @FXML
    private Label pontos1;
    @FXML
    private Label nome2;
    @FXML
    private Label pontos2;
    @FXML
    private Label nome3;
    @FXML
    private Label pontos3;
    @FXML
    private Label nome4;
    @FXML
    private Label pontos4;
    @FXML
    private Label nome5;
    @FXML
    private Label pontos5;
    @FXML
    private Label nome6;
    @FXML
    private Label pontos6;
    @FXML
    private Label nome7;
    @FXML
    private Label pontos7;
    @FXML
    private Label nome8;
    @FXML
    private Label pontos8;
    @FXML
    private Label nome9;
    @FXML
    private Label pontos9;
    @FXML
    private Label nome10;
    @FXML
    private Label pontos10;
    @FXML
    private Pane painelRanking;

    private ArrayList listaRanking = new ArrayList();
    private BufferedReader br;
    ArrayList idNomesJogador = new ArrayList();

    public ModelRanking(ImageView avatarMaior, TextField nomeJogador, Button iniciar, Button av1,
            Button av2, Button av3, Button av4, Button av5, Button av6, Button av7, Button av8,
            Button av9, Button av10, ImageView i1, ImageView i2, ImageView i3, ImageView i4,
            ImageView i5, ImageView i6, ImageView i7, ImageView i8, ImageView i9, ImageView i10,
            Label nm1, Label nm2, Label nm3, Label nm4, Label nm5, Label nm6, Label nm7, Label nm8,
            Label nm9, Label nm10, Label pt1, Label pt2, Label pt3, Label pt4, Label pt5, Label pt6,
            Label pt7, Label pt8, Label pt9, Label pt10, Pane painelRanking) {
        this.avatarMaior = avatarMaior;
        this.nomeJogador = nomeJogador;
        this.iniciar = iniciar;
        this.avatar = 0;
        this.avatar1 = av1;
        this.avatar2 = av2;
        this.avatar3 = av3;
        this.avatar4 = av4;
        this.avatar5 = av5;
        this.avatar6 = av6;
        this.avatar7 = av7;
        this.avatar8 = av8;
        this.avatar9 = av9;
        this.avatar10 = av10;
        this.img1 = i1;
        this.img2 = i2;
        this.img3 = i3;
        this.img4 = i4;
        this.img5 = i5;
        this.img6 = i6;
        this.img7 = i7;
        this.img8 = i8;
        this.img9 = i9;
        this.img10 = i10;
        this.nome1 = nm1;
        this.nome2 = nm2;
        this.nome3 = nm3;
        this.nome4 = nm4;
        this.nome5 = nm5;
        this.nome6 = nm6;
        this.nome7 = nm7;
        this.nome8 = nm8;
        this.nome9 = nm9;
        this.nome10 = nm10;
        this.pontos1 = pt1;
        this.pontos2 = pt2;
        this.pontos3 = pt3;
        this.pontos4 = pt4;
        this.pontos5 = pt5;
        this.pontos6 = pt6;
        this.pontos7 = pt7;
        this.pontos8 = pt8;
        this.pontos9 = pt9;
        this.pontos10 = pt10;
        this.br = null;
        this.painelRanking = painelRanking;
    }

    public void trocarAvatar(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow();
        String nomeBotao = ((Button) event.getSource()).getId();
        int numeroBotao = Integer.parseInt(nomeBotao.substring(6));
        URL arquivoImg = getClass().getResource("imagens/icones/" + numeroBotao + ".png");
        avatarMaior.setImage(new Image(arquivoImg.toString()));
        this.avatar = numeroBotao;
        nomeJogador.requestFocus();
    }

    public void verificarTextoInserido(InputMethodEvent event) {
        System.out.println("Nomejogador " + nomeJogador.getText());
    }

    /**
     * Verifica se o jogador digitou o seu nome na caixa de texto para habilitar
     * o botão de iniciar o jogo
     *
     * @param event tamanho do texto alterado
     */
    public void verificarTexto(KeyEvent event) {
        if (!nomeJogador.getText().isEmpty()) {
            iniciar.setDisable(false);
        } else {
            iniciar.setDisable(true);
        }

    }

    /**
     * Inicia um novo jogo
     *
     * @param event botão iniciar é clicado ou o jogador clica em uma das
     * posições do ranking
     * @throws IOException
     */
    public void iniciarJogo(ActionEvent event) throws IOException {
        if (!jogadorExiste()) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow();
            fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Principal.fxml"));
            cenaPrincipal = (Parent) fxmloader.load();
            principalController = fxmloader.<PrincipalController>getController();

            principalController.setFase(1);
            principalController.setNivel(1);
            principalController.iniciarJogo();
            principalController.setIconeAvatar(avatar);
            principalController.setNomeJogador(nomeJogador.getText());
            Scene scene = new Scene(cenaPrincipal, 1200, 700);
            janela.setScene(scene);
            janela.setFullScreen(true);
            janela.setFullScreenExitHint("");
            janela.show();
        } else {
            Alert confirmacaoSaida = new Alert(Alert.AlertType.WARNING,
                    "Esse nome já foi usado!");
            Button botaoOk = (Button) confirmacaoSaida.getDialogPane().lookupButton(ButtonType.OK);

            botaoOk.setText("Ok");

            confirmacaoSaida.setTitle(null);
            confirmacaoSaida.setHeaderText(null);
            confirmacaoSaida.setContentText("Esse nome já foi usado!");
            Optional<ButtonType> resposta = confirmacaoSaida.showAndWait();
            if (ButtonType.OK.equals(resposta.get())) {
                nomeJogador.setText("");
                destacarPosicaoJogador();

            }

        }

    }

    /**
     * Troca a imagem do avatar escolhido pelo jogador
     *
     * @param event
     */
    public void avatarEscolhido(MouseEvent event) {
        efeitoBotaoClicado();
        Button b_temp = (Button) event.getSource();
        String id = b_temp.getId().substring(6);
        String efeito = "-fx-background-color:\n"
                + "        linear-gradient(#f0ff35, #a9ff00),\n"
                + "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);\n"
                + "    -fx-background-radius: 75%;\n"
                + "    -fx-background-insets: 0, 1;\n"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n"
                + "    -fx-text-fill: #395306;";
        switch (id) {
            case "1":
                b_temp.setStyle(efeito);
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "2":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "3":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "4":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "5":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "6":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "7":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "8":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar9.setStyle("-fx-background-color: transparent;");
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "9":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                this.avatar10.setStyle("-fx-background-color: transparent;");
                break;
            case "10":
                this.avatar1.setStyle("-fx-background-color: transparent;");
                this.avatar2.setStyle("-fx-background-color: transparent;");
                this.avatar3.setStyle("-fx-background-color: transparent;");
                this.avatar4.setStyle("-fx-background-color: transparent;");
                this.avatar5.setStyle("-fx-background-color: transparent;");
                this.avatar6.setStyle("-fx-background-color: transparent;");
                this.avatar7.setStyle("-fx-background-color: transparent;");
                this.avatar8.setStyle("-fx-background-color: transparent;");
                this.avatar9.setStyle("-fx-background-color: transparent;");
                b_temp.setStyle(efeito);
                break;
        }
    }

    public void efeitoMouse(MouseEvent event) {
        tocarEfeito("transicaoBotao");

    }

    /**
     * Seleciona o primeiro avatar como padrão
     *
     * @param i numero do avatar escolhido como padrão
     */
    public void selecionarDefaultAvatar(int i) {
        avatar1.setStyle("-fx-background-color:\n"
                + "        linear-gradient(#f0ff35, #a9ff00),\n"
                + "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);\n"
                + "    -fx-background-radius: 75%;\n"
                + "    -fx-background-insets: 0, 1;\n"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n"
                + "    -fx-text-fill: #395306;");
    }

    public void atualizarRanking() throws IOException {

        //Array com os ids das imagens dos avatares no ranking
        ArrayList idImagens = new ArrayList();
        idImagens.add(img1);
        idImagens.add(img2);
        idImagens.add(img3);
        idImagens.add(img4);
        idImagens.add(img5);
        idImagens.add(img6);
        idImagens.add(img7);
        idImagens.add(img8);
        idImagens.add(img9);
        idImagens.add(img10);

        idNomesJogador.add(nome1);
        idNomesJogador.add(nome2);
        idNomesJogador.add(nome3);
        idNomesJogador.add(nome4);
        idNomesJogador.add(nome5);
        idNomesJogador.add(nome6);
        idNomesJogador.add(nome7);
        idNomesJogador.add(nome8);
        idNomesJogador.add(nome9);
        idNomesJogador.add(nome10);

        ArrayList idPontos = new ArrayList();
        idPontos.add(pontos1);
        idPontos.add(pontos2);
        idPontos.add(pontos3);
        idPontos.add(pontos4);
        idPontos.add(pontos5);
        idPontos.add(pontos6);
        idPontos.add(pontos7);
        idPontos.add(pontos8);
        idPontos.add(pontos9);
        idPontos.add(pontos10);

        br = null;
        try {
            File arquivo = new File("ranking.txt");
            if (arquivo.exists() == false) {

                FileWriter fw = new FileWriter("ranking.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter arquivoSaida = new PrintWriter(bw);
                bw.close();
            }
            br = new BufferedReader(new FileReader(arquivo));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaRanking = ordenarRanking();//pega o valor ordeanado do ranking
        String r1;
        int j = 0;
        while (j <= (listaRanking.size() - 1) && j < 10) {
            String[] vetor = (String[]) listaRanking.get(j);
            //atualizada os avatares
            URL arquivoImg = getClass().getResource("imagens/icones/" + (Integer.parseInt(vetor[0])) + ".png");
            ((ImageView) idImagens.get(j)).setImage(new Image(arquivoImg.toString()));
            //atualiza o nome do jogador
            ((Label) idNomesJogador.get(j)).setText(vetor[1]);
            //atualiza a pontuação
            ((Label) idPontos.get(j)).setText(vetor[2]);
            j++;

        }
        br.close();

    }

    /**
     * Retorna um arraylist contendo vetores em ordem descrescente pela
     * pontuação
     *
     * @return lista ordenada
     * @throws IOException
     */
    public ArrayList ordenarRanking() throws IOException {
        String r1;
        ArrayList listaOriginal = new ArrayList();
        ArrayList interno = new ArrayList();

        while (br.ready()) {

            String linha = br.readLine();
            String[] split = linha.split(">");//separa a linha em 3 partes
            String part1 = split[0];//numero do avatar
            String part2 = split[1];//nome do jogador
            String part3 = split[2];//pontuação            
            System.out.println("parte " + part3);
            //interno.add(part1);
            //interno.add(part2);
            //interno.add(part3);
            listaOriginal.add(split);
        }
        listaOriginal.stream().forEach((o) -> {
            System.out.println(Arrays.toString((String[]) o));
        });
        System.out.println("Lista size " + listaOriginal.size());
        Collections.sort(listaOriginal, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                int i = 0;

                String[] s1 = (String[]) o1;
                String[] s2 = (String[]) o2;

                return Integer.parseInt(s1[2]) > Integer.parseInt(s2[2]) ? -1 : (Integer.parseInt(s2[2]) > Integer.parseInt(s1[2]) ? +1 : 0);

            }
        });

        return listaOriginal;
    }

    /**
     * Toca o efeito do botao clicado
     */
    public void efeitoBotaoClicado() {
        tocarEfeito("botaoClicado");
    }

    /**
     * Toca um audio rápido
     *
     * @param efeito som que deve ser tocado
     */
    public void tocarEfeito(String efeito) {
        String caminhoAudio = "";
        caminhoAudio = "_audios/efeitos/" + efeito + ".mp3";
        URL file = getClass().getResource(caminhoAudio);
        media = new Media(file.toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.play();
                //setTocando(true);
            }
        });
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                //setTocando(false);
                mediaPlayer.dispose();
            }
        });
    }

    /**
     * Sai do jogo
     *
     * @param event botão sair é clicado
     */
    @FXML
    public void sairDoJogo(ActionEvent event) {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow();
        //função para encerrar todos os processos quando o usuário clicar no "X"
        Alert confirmacaoSaida = new Alert(Alert.AlertType.CONFIRMATION,
                "Deseja mesmo sair do jogo?");
        Button botaoSair = (Button) confirmacaoSaida.getDialogPane().lookupButton(ButtonType.OK);
        Button botaoNao = (Button) confirmacaoSaida.getDialogPane().lookupButton(ButtonType.CANCEL);
        botaoSair.setText("Sim");
        botaoNao.setText("Não");
        confirmacaoSaida.setTitle(null);
        confirmacaoSaida.setHeaderText(null);
        confirmacaoSaida.setContentText("Deseja mesmo sair do jogo?");
        Optional<ButtonType> resposta = confirmacaoSaida.showAndWait();
        if (ButtonType.OK.equals(resposta.get())) {
            janela.close();
            System.exit(0);
        }
    }

    /**
     * Inicia o jogo caso a tecla pressionada tenha sido a tecla Enter
     *
     * @param event disparado quando uma tecla é pressionada
     */
    public void verificarTeclaPressionada(KeyEvent event) {

        if ((event.getCode().equals(KeyCode.ENTER)) && (!nomeJogador.getText().isEmpty())) {
            iniciar.fire();
        }
    }

    public void iniciarJogoRanking(MouseEvent event) throws IOException {
        ImageView avatarRanking = (ImageView) ((HBox) event.getSource()).getChildren().get(0);
        Label nomeRankingJogador = (Label) ((HBox) event.getSource()).getChildren().get(1);
        if (!nomeRankingJogador.getText().isEmpty()) {
            janela = (Stage) (avatarMaior).getScene().getWindow();
            fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Principal.fxml"));
            cenaPrincipal = (Parent) fxmloader.load();
            principalController = fxmloader.<PrincipalController>getController();
            principalController.setFase(1);
            principalController.setNivel(1);
            principalController.iniciarJogo();
            principalController.setIconeAvatar(avatarRanking.getImage());
            principalController.setNomeJogador(nomeRankingJogador.getText());
            Scene scene = new Scene(cenaPrincipal, 1200, 700);
            janela.setScene(scene);
            janela.setFullScreen(true);
            janela.setFullScreenExitHint("");
            janela.show();
        }

    }

    /**
     * Verifica se o nome digitado pelo jogador já existe no ranking
     *
     * @return true ou false
     */
    private boolean jogadorExiste() {
        boolean existe = false;
        Label tempLabel = null;
        System.out.println(((Label) idNomesJogador.get(0)).getText());
        for (int i = 0; i < 10; i++) {
            tempLabel = ((Label) idNomesJogador.get(i));
            if (tempLabel.getText().equals(nomeJogador.getText())) {
                return true;
            }
        }

        return existe;
    }

    private void destacarPosicaoJogador() {
        Label tempLabel = ((Label) idNomesJogador.get(0));
        for (int i = 0; i < 10; i++) {
            if (tempLabel.getText().equals(nomeJogador.getText())) {
                //tempLabel.getParent().setStyle("-fx-background-color: black;");
                break;
            }
            tempLabel = ((Label) idNomesJogador.get(i));
        }

    }
}
