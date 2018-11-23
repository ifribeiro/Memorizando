package model;

import controller.PopUpController;
import controller.PopUpGameOverController;
import controller.PopUpNivelFinalizadoController;
import java.io.IOException;
import controller.RankingController;
import controller.PrincipalController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author iran
 */
public class ModelPrincipal {

    private String arrayVogais[] = new String[]{"a", "e", "i", "o", "u"};
    private final String arraySilabasSimples[] = {
        "al", "am", "an", "ar", "as", "ba", "be", "bi", "bo", "bu", "ca",
        "ção", "ce", "ci", "ções", "cu", "da", "di", "do",
        "em", "en", "er", "es", "fa", "fe", "fi", "fo", "fu", "ga", "ge", "gi",
        "go", "gu", "im", "in", "ir", "is", "jan", "je", "ji", "jo", "ju",
        "la", "le", "li", "lo", "ma", "me", "mi", "mo", "mu", "na", "ne",
        "ni", "no", "nu", "om", "on", "or", "os",
        "pa", "pe", "pi", "po", "pu", "ra", "re", "ri", "ro",
        "ru", "sa", "se", "si", "so", "su", "ta", "te", "ti", "to", "tu",
        "um", "ur", "va", "ve", "vi", "vo", "vu", "xa", "xe", "xi", "xo", "za", "ze", "zo"};
    private final String arraySilabasComplexas1[] = {
        "bal", "bam", "ban", "bão", "bar", "bas", "bel", "bem", "ber", "bes",
        "bil", "bin", "bir", "bis", "bol", "bom", "bor", "bos", "bum", "bur",
        "bus", "cães", "cal", "cam", "can", "cão", "cas", "cel", "cen", "cer",
        "ces", "che", "cin", "cir", "cis", "ções", "col", "com", "con", "cor",
        "cos", "cul", "cum", "cur", "cus", "cuz", "dan", "dão", "dar", "das",
        "den", "der", "des", "dim", "dis", "don", "dor", "dos", "dum", "fal",
        "fan", "far", "fel", "fen", "fer", "fes", "fil", "fim", "fir", "fol",
        "fon", "for", "fos", "fun", "fus", "gal", "gam", "gan", "gão", "gar",
        "gel", "gem", "gen", "ger", "gil", "gin", "gir", "gol", "gon", "gor",
        "gua", "jan", "jão", "jar", "jas", "jor", "jun", "jus", "lam", "lan",
        "lão", "lar", "las", "lem", "len", "ler", "lim", "lin", "lom", "lon",
        "los", "lus", "luz", "mal", "man", "mão", "mãos", "mar", "mel", "mem",
        "men", "mer", "mês", "min", "mir", "mis", "mol", "mon", "mor", "mos",
        "mul", "mun", "mus", "nal", "não", "nas", "nel", "ner", "nil", "nir",
        "noz", "pães", "pal", "pan", "pão", "par", "pas", "paz", "pel", "pen",
        "per", "pes", "pim", "pin", "pis", "pol", "pom", "pon", "por", "pos",
        "pul", "pum", "ram", "ran", "ras", "rel", "ren", "res", "rio", "rir",
        "ris", "rom", "ron", "ros", "rou", "sal", "sam", "san", "são", "sar",
        "sel", "sem", "sen", "ser", "sim", "sis", "sol", "som", "son", "sor",
        "sun", "sur", "sus", "tal", "tam", "tan", "tão", "tar", "tas", "tel",
        "tem", "ten", "ter", "tes", "til", "tim", "tin", "tir", "tol", "tom",
        "ton", "tor", "tum", "tur", "val", "vam", "vão", "var", "vas", "vel",
        "vem", "ven", "ver", "ves", "vin", "vir", "vis", "von", "vul", "xam",
        "xão", "xar", "xer", "xis", "zal", "zão", "zar", "zer", "zes", "zin",
        "zol", "zom", "zul", "zum"
    };

    private final String arraySilabasComplexas2[] = {
        "ble", "bli", "blo", "blu", "bra", "bre", "bri", "bro", "bru",
        "cla", "cle", "cli", "clo", "clu", "cra", "cre", "cri", "cro",
        "cru", "dra", "dre", "dri", "dro", "dru", "fla", "fle", "flo",
        "flu", "fra", "fre", "fri", "fro", "fru", "gla", "glo", "gra",
        "gre", "gri", "gro", "gru", "pla", "plo", "plu", "pra", "pre",
        "pri", "pro", "pru", "tle", "tlo", "tra", "tre", "tri", "tro",
        "tru", "vre", "vro"};

    private final String arrayPalavrasSimples[] = {
        "amarelo", "apito", "árvore", "bala", "banana", "batata",
        "biruta", "boca", "bola", "bolita", "bolo", "boné", "boneca",
        "bonito", "boto", "bula", "bule", "cabelo", "café", "cama", "camelo", "camisa", "capacete",
        "casa", "casaco", "cavalo", "cereja", "cola", "copo",
        "coruja", "dado", "dedo", "duro", "escola", "escova", "faca", "fada", "foca",
        "fogo", "galo", "gato", "gelado", "gelo", "jaca", "jacaré", "janela",
        "lata", "leite", "lixo", "lua", "luta", "luva", "macaco", "mala",
        "menino", "mesa", "moeda", "música", "neve", "novela", "ovo", "parede", "pato",
        "pé", "pelado", "pena", "pera", "peteca", "piano", "pipa", "pipoca", "pirulito",
        "pulo", "rato", "rei", "rico", "robô", "roda", "rosa", "rua", "sábado", "sapato",
        "sapo", "sino", "sofá", "suco", "tapete", "tatu", "telefone", "teto", "tijolo", "tomate",
        "urso", "uva", "vaca", "vagalume", "vela", "xícara", "xixi"
    };

    private final String arraySilabasComplexas3[] = {
        "bran", "bras", "bres", "brin", "bron", "bros", "brus", "chão",
        "clas", "clos", "cres", "cris", "cros", "crus", "guam", "guar",
        "lheu", "plas", "ples", "pran", "pras", "pren", "pres", "prin",
        "pron", "tlas", "tras", "tres", "tris", "tros"};

    private final String arrayPalavrasComplexas[] = {
        "abelha", "aberto", "alegria", "animal", "antena", "apontador",
        "aranha", "arroz", "azul", "banha", "barco", "batom", "bíblia", "biblioteca",
        "bicicleta", "biscoito", "bloco", "blusa", "bolha", "borboleta", "braço",
        "brasil", "brinco", "brinquedo", "bronca", "bruxa", "cachorro", "caderno",
        "calça", "carro", "carroça", "cenoura", "chinelo", "chocolate", "cobertor",
        "cobra", "computador", "engraçado", "espinho", "farol", "fechado", "ficha",
        "filho", "flauta", "flor", "floresta", "foguete", "folha", "fralda", "fruta",
        "galinha", "girassol", "gravata", "graveto", "guitarra", "impressora", "jardim",
        "jegue", "joelho", "lâmpada", "laranja", "leão", "limão", "língua", "linha",
        "livro", "malvado", "mamão", "maravilha", "minhoca", "mochila", "nariz",
        "ninho", "noivo", "nublado", "olho", "palhaço", "passagem", "passarinho",
        "peixe", "pilha", "pinguim", "placa", "planta", "praça", "prato", "princesa",
        "príncipe", "pulseira", "régua", "retrato", "segredo", "sol", "sonho",
        "tartaruga", "telefone", "terra", "tigre", "tornozelo", "vidro"
    };

    private ArrayList listaRanking;

    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;
    @FXML
    private Button b10;
    @FXML
    private Button b11;
    @FXML
    private Button b12;
    @FXML
    private Button b13;
    @FXML
    private Button b14;
    @FXML
    private Button b15;
    @FXML
    private Button b16;

    @FXML
    private ProgressBar barraTempo;

    @FXML
    private Button fase1;
    @FXML
    private Button fase2;
    @FXML
    private Button fase3;
    @FXML
    private Button fase4;
    @FXML
    private Button fase5;
    @FXML
    private Button fase6;
    @FXML
    private Button fase7;
    @FXML
    private Button nivel1;
    @FXML
    private Button nivel2;
    @FXML
    private Button nivel3;

    private Timer timer;
    private double tempo;
    private Media media;
    private Stage janela;
    private Label pontuacao;
    private Label nomeJogador;
    private ImageView iconeAvatar;
    private String botao1, botao2;
    private Button btemp1, btemp2;
    private ArrayList novasOpcoes;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView = new MediaView();
    private String arrayOpcoes[] = new String[16];    
    private PrincipalController principalController = null;
    private ArrayList arrayBotoes = new ArrayList<String>();
    private boolean tocando = false, gameOver, timerIniciado;
    private RankingController rankingController = null;
    private EventHandler<ActionEvent> evento1Botao, evento2Botao, eventoSomAcerto,
            eventoProximoNivel, eventoFimNivel, eventoSomBotao;
    private int acerto, erro, fase, nivel, cliquesTotais, cliques, avatar, pontos, pontuacaoTotal;
    private Group grupoNivel1;
    private Group grupoNivel2;
    private Group grupoNivel3;

    FXMLLoader fxmlPopUp;
    Parent popUppppp = null;
    @FXML
    private ImageView imagemFundo;

    private ArrayList listaBotoes, fasesBloqueadasNivel1, fasesBloqueadasNivel2,
            fasesBloqueadasNivel3, niveisBloqueados;
    private ArrayList<Long> pontuacoesNivel1, pontuacoesNivel2, pontuacoesNivel3;

    private boolean mostrando;
    private boolean jogadorExiste;

    @FXML
    private ImageView imgFase1;
    @FXML
    private ImageView imgFase2;
    @FXML
    private ImageView imgFase3;
    @FXML
    private ImageView imgFase4;
    @FXML
    private ImageView imgFase5;
    @FXML
    private ImageView imgFase6;
    @FXML
    private ImageView imgFase7;
    @FXML
    private Label ptFase1;
    @FXML
    private Label ptFase2;
    @FXML
    private Label ptFase3;
    @FXML
    private Label ptFase4;
    @FXML
    private Label ptFase5;
    @FXML
    private Label ptFase6;
    @FXML
    private Label ptFase7;
    Image faseBloqueada = new Image(getClass().getResourceAsStream("imagens/icones/nivelFechado.png"));
    Image faseAberta = new Image(getClass().getResourceAsStream("imagens/icones/nivelAberto.png"));

    @FXML
    private Button menuInicial;
    @FXML
    private Button sair;
    Label vetorLabel[];

    @FXML
    private ImageView pt1;
    @FXML
    private ImageView pt2;
    @FXML
    private ImageView pt3;
    @FXML
    private ImageView pt4;
    @FXML
    private ImageView pt5;
    @FXML
    private ImageView pt6;
    @FXML
    private ImageView pt7;
    @FXML
    private ImageView pt8;
    private boolean rankingAtualizado;
    private FXMLLoader fxmloader;
    private Parent cenaPrincipal;

    //private BufferedReader br = null;
    public ModelPrincipal(Button b1, Button b2, Button b3, Button b4, Button b5,
            Button b6, Button b7, Button b8, Button b9, Button b10, Button b11, Button b12,
            Button b13, Button b14, Button b15, Button b16,
            ProgressBar barraTempo, Button f1, Button f2, Button f3, Button f4,
            Button f5, Button f6, Button f7, ImageView icoA, Label pont,
            Label nmJogador, Button n1, Button n2, Button n3, Group grupoNivel1,
            Group grupoNivel2, Group grupoNivel3, ImageView imagemFundo, ImageView imgFase1,
            ImageView imgFase2, ImageView imgFase3, ImageView imgFase4, ImageView imgFase5,
            ImageView imgFase6, ImageView imgFase7, Label ptFase1, Label ptFase2, Label ptFase3,
            Label ptFase4, Label ptFase5, Label ptFase6, Label ptFase7, Button menuInicial,
            Button sair, ImageView pt1, ImageView pt2, ImageView pt3, ImageView pt4,
            ImageView pt5, ImageView pt6, ImageView pt7, ImageView pt8) throws FileNotFoundException {
        this.vetorLabel = new Label[]{ptFase1, ptFase2, ptFase3, ptFase4, ptFase5, ptFase6, ptFase7,};
        // this.br = new BufferedReader(new FileReader("ranking.txt"));
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.b5 = b5;
        this.b6 = b6;
        this.b7 = b7;
        this.b8 = b8;
        this.b9 = b9;
        this.b10 = b10;
        this.b11 = b11;
        this.b12 = b12;
        this.b13 = b13;
        this.b14 = b14;
        this.b15 = b15;
        this.b16 = b16;
        this.erro = 0;
        this.fase = 1;
        this.nivel = 1;
        this.avatar = 1;
        this.acerto = 0;
        this.cliques = 0;
        this.botao1 = "";
        this.botao2 = "";
        this.fase1 = f1;
        this.fase2 = f2;
        this.fase3 = f3;
        this.fase4 = f4;
        this.fase5 = f5;
        this.fase6 = f6;
        this.fase7 = f7;
        this.gameOver = false;
        this.cliquesTotais = 0;
        this.timerIniciado = false;
        this.pontuacao = pont;
        this.pontuacaoTotal = 0;
        this.barraTempo = barraTempo;
        this.nomeJogador = nmJogador;
        this.iconeAvatar = icoA;
        this.nivel1 = n1;
        this.nivel2 = n2;
        this.nivel3 = n3;
        this.listaRanking = new ArrayList();
        this.grupoNivel1 = grupoNivel1;
        this.grupoNivel2 = grupoNivel2;
        this.grupoNivel3 = grupoNivel3;
        this.fxmlPopUp = null;
        this.imagemFundo = imagemFundo;
        listaBotoes = new ArrayList();
        this.fasesBloqueadasNivel1 = new ArrayList();
        this.fasesBloqueadasNivel2 = new ArrayList();
        this.fasesBloqueadasNivel3 = new ArrayList();
        this.pontuacoesNivel1 = new ArrayList<Long>(7);
        this.pontuacoesNivel2 = new ArrayList<Long>(7);
        this.pontuacoesNivel3 = new ArrayList<Long>(7);
        this.tempo = 1.0;
        this.mostrando = false;
        this.niveisBloqueados = new ArrayList(3);
        this.jogadorExiste = false;
        this.imgFase1 = imgFase1;
        this.imgFase2 = imgFase2;
        this.imgFase3 = imgFase3;
        this.imgFase4 = imgFase4;
        this.imgFase5 = imgFase5;
        this.imgFase6 = imgFase6;
        this.imgFase7 = imgFase7;
        this.ptFase1 = ptFase1;
        this.ptFase2 = ptFase2;
        this.ptFase3 = ptFase3;
        this.ptFase4 = ptFase4;
        this.ptFase5 = ptFase5;
        this.ptFase6 = ptFase6;
        this.ptFase7 = ptFase7;
        this.menuInicial = menuInicial;
        this.sair = sair;
        this.pt1 = pt1;
        this.pt2 = pt2;
        this.pt3 = pt3;
        this.pt4 = pt4;
        this.pt5 = pt5;
        this.pt6 = pt6;
        this.pt7 = pt7;
        this.pt8 = pt8;
        iniciarPontuacoesLabels();
    }

    /**
     * Realiza as operações referentes aos cliques nos botões centrais do jogo
     *
     * @param evento quando um dos botões é clicado
     */
    public void verificarClique(ActionEvent evento) {
        if (primeiroClique() && !getTimerIniciado()) {
            iniciarTimer();
        }
        String nomeBotao = ((Button) evento.getSource()).getId();
        cliques++;
        //verifica se é o primeiro clique
        if (cliques == 1) {
            //tocar audio do botao clicado
            tocarAudioBotao(evento);
            btemp1 = ((Button) evento.getSource());//grava qual botao foi clicado
            botao1 = arrayOpcoes[Integer.parseInt(nomeBotao.substring(1)) - 1];//salva o audio contido no botao
            setCorBotao(btemp1, "#ffff00");//botao fica amarelo

        } else if (cliques == 2) { //verifica se é o segundo clique
            btemp2 = ((Button) evento.getSource());
            botao2 = arrayOpcoes[Integer.parseInt(nomeBotao.substring(1)) - 1];
            if (botao1.equals(botao2) && (!btemp1.equals(btemp2))) {//verifica se os dois botoes não são correspondentes
                incrementarAcerto();
                evento1Botao = (ActionEvent event) -> {
                    setCorBotao(btemp2, "#00EE00");
                    setCorBotao(btemp1, "#00EE00");
                    tocarAudioBotao(evento);
                    //setTocando(true);
                };
                evento2Botao = (ActionEvent event) -> {
                    btemp1.setDisable(true);
                    btemp2.setDisable(true);
                    setCorBotao(btemp1, "#00000");
                    setCorBotao(btemp2, "#00000");
                    listaBotoes.add(btemp1);
                    listaBotoes.add(btemp2);
                };

                eventoSomAcerto = (ActionEvent event) -> {
                    tocarEfeitoAcerto();
                    try {
                        verificarTerminoNivel();
                    } catch (IOException ex) {
                        Logger.getLogger(ModelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                };
                new Timeline(new KeyFrame(Duration.seconds(0), evento1Botao),
                        new KeyFrame(Duration.seconds(0.4), evento2Botao),
                        new KeyFrame(Duration.seconds(0.2), eventoSomAcerto)).play();

                //aumentar um pouco o tempo de acordo a fase 
            } else {//os dois botões ficam vermelhos
                evento1Botao = (ActionEvent event) -> {
                    setCorBotao(btemp2, "#ff0000");
                    setCorBotao(btemp1, "#ff0000");
                    tocarAudioBotao(evento);
                    //setTocando(true);
                };
                evento2Botao = (ActionEvent event) -> {
                    setCorBotao(btemp1, "#00000");
                    setCorBotao(btemp2, "#00000");
                };

                new Timeline(
                        new KeyFrame(Duration.seconds(0), evento1Botao),
                        new KeyFrame(Duration.seconds(0.5), evento2Botao)).play();
                incrementarErro();
            }
            //se tiver errado
            //"desvira" os dois audios
            //incrementa erros
            this.cliquesTotais = cliquesTotais + cliques;
            cliques = 0;
        }
        //quando não houver mais botoes 
        //gerar novas opcoes
    }

    /**
     * Muda para o nível de um dos 3 botões de que foi clicado
     *
     * @param event
     * @throws java.io.IOException
     */
    public void alterarNivel(ActionEvent event) throws IOException {
        String nomeBotao = ((Button) event.getSource()).getId();
        int numBotao = Integer.parseInt(nomeBotao.substring(5));
        if (!(numBotao == getNivel())) {
            switch (nomeBotao) {
                case "selec1":
                case "nivel1":
                    grupoNivel1.setVisible(true);
                    grupoNivel2.setVisible(false);
                    grupoNivel3.setVisible(false);
                    break;
                case "selec2":
                case "nivel2":
                    grupoNivel1.setVisible(false);
                    grupoNivel2.setVisible(true);
                    grupoNivel3.setVisible(false);
                    break;
                case "selec3":
                case "nivel3":
                    grupoNivel1.setVisible(false);
                    grupoNivel2.setVisible(false);
                    grupoNivel3.setVisible(true);
                    break;
            }
            exibirBotoes(nivel);
            setAcerto(0);
            setPrimeiroClique();
            setNivel(numBotao);
            setFase(getFaseDisponivel());
            destacarNivel(getNivel());
            resetarAcertos();
            gerarOpcoes(getFase());
            atualizarBloqueios();
            atualizarPontosFase();
            if (timerIniciado) {
                reiniciarTimer();
            }

        }
    }

    /**
     * Altera para a fase anterior
     *
     * @param event
     */
    public void faseAnterior(ActionEvent event) {
        if (fase == 2) {
            this.fase = fase - 1;
            gerarOpcoes(fase);
            exibirBotoes(getNivel());
        } else {
            if (!(fase == 1)) {
                gerarOpcoes(fase);
                exibirBotoes(getNivel());
                this.fase = fase - 1;
            }
        }
        destacarBotao(fase);
    }

    /**
     * Gera novas opções aleatórias para os botões, baseadas nas fase que o
     * jogador se encontra
     *
     * @param fase fase atual do jogador
     */
    public void gerarOpcoes(int fase) {
        int i = 0;
        int proxValor = 0;
        int numeroFonemas = 0;
        int contador = 0;
        int numeroFonemasVetores = 0;
        novasOpcoes = new ArrayList(); //recebe os índices para as novas opções do array correspondente à fase
        ArrayList indicesUtilizados = new ArrayList();//array que receberá os índices que já foram utilizados ????
        ArrayList indicesFonemasUtilizados = new ArrayList();
        Random indice = new Random();//gerador de índices aleatorios
        switch (getNivel()) {
            case 1:
                numeroFonemas = 4;
                break;
            case 2:
                numeroFonemas = 5;
                break;
            case 3:
                numeroFonemas = 8;
                break;
        }

        switch (getFase()) {
            case 1://vogais
                numeroFonemasVetores = 5;
                break;
            case 2://silabas simples
                numeroFonemasVetores = 94;
                break;
            case 3://palavras simples
                numeroFonemasVetores = 99;
                break;
            case 4://silabas complexas1
                numeroFonemasVetores = 244;
                break;
            case 5://silabas complexas2
                numeroFonemasVetores = 57;
                break;
            case 6://silabascomplexas 3
                numeroFonemasVetores = 30;
                break;
            case 7://palavras
                numeroFonemasVetores = 101;
                break;

        }
        while (i < numeroFonemas) {
            proxValor = indice.nextInt(numeroFonemasVetores);//proximo fonema
            if (!indicesUtilizados.contains(proxValor)) {//se o índice ainda não foi utilizado                
                novasOpcoes.add(proxValor);//adiciona o indice no array
                indicesUtilizados.add(proxValor);//adiciona o indice utilizado no vetor de utilizados
                i++;
            } else {//se o índice já foi utilizado
                if (getNivel() == 3 && getFase() == 1) {//verifica se o jogador está no nível 3 e no grupo 1 dos fonemas
                    novasOpcoes.add(proxValor);
                    i++;
                }
            }

        }
        while (contador < numeroFonemas * 2) {
            for (int j = 0; j < novasOpcoes.size(); j++) {

                int posicao1 = indice.nextInt(numeroFonemas * 2);
                int posicao2 = indice.nextInt(numeroFonemas * 2);
                while ((posicao1 == posicao2)
                        || ((indicesFonemasUtilizados.contains(posicao1)
                        || indicesFonemasUtilizados.contains(posicao2)))) {
                    posicao1 = indice.nextInt(numeroFonemas * 2);
                    posicao2 = indice.nextInt(numeroFonemas * 2);
                }
                indicesFonemasUtilizados.add(posicao1);
                indicesFonemasUtilizados.add(posicao2);
                arrayOpcoes[posicao1] = getFonemaArray(fase, j);
                arrayOpcoes[posicao2] = getFonemaArray(fase, j);
                contador = contador + 2;

            }
        }
    }

    public void iniciarJogo() {
        setTimerIniciado(false);
        setPrimeiroClique();
        gerarOpcoes(getFase());
        destacarBotao(getFase());
        destacarNivel(getNivel());
    }

    public void setFase(int fase) {
        this.fase = fase;
        destacarBotao(fase);
    }

    /**
     * Retorna a fase em que o jogador está
     *
     * @return
     */
    public int getFase() {
        return this.fase;
    }

    public void setNivel(int i) {
        this.nivel = i;
    }

    public int getAvatar() {
        return this.avatar;
    }

    public void setAvatar(int avatar) {
        if (avatar == 0) {
            this.avatar = 1;
        } else {
            this.avatar = avatar;
        }
    }

    public int getNivel() {
        return this.nivel;
    }

    public String getFonemaArray(int fase, int posicaoVetor) {
        String fonema = "";
        switch (fase) {
            case 1:
                fonema = arrayVogais[(int) novasOpcoes.get(posicaoVetor)];
                break;
            case 2:
                fonema = arraySilabasSimples[(int) novasOpcoes.get(posicaoVetor)];
                break;
            case 3:
                fonema = arrayPalavrasSimples[(int) novasOpcoes.get(posicaoVetor)];
                break;
            case 4:
                fonema = arraySilabasComplexas1[(int) novasOpcoes.get(posicaoVetor)];
                break;
            case 5:
                fonema = arraySilabasComplexas2[(int) novasOpcoes.get(posicaoVetor)];
                break;
            case 6:
                fonema = arraySilabasComplexas3[(int) novasOpcoes.get(posicaoVetor)];
                break;
            case 7:
                fonema = arrayPalavrasComplexas[(int) novasOpcoes.get(posicaoVetor)];
                break;
        }

        return fonema;
    }

    public void proximaFase(ActionEvent event) {
        if (fase == 6) {
            this.fase = fase + 1;
            gerarOpcoes(fase);
            exibirBotoes(getNivel());
        } else {
            if (!(fase == 7)) {
                this.fase = fase + 1;
                gerarOpcoes(fase);
                exibirBotoes(getNivel());
            }
        }
        destacarBotao(fase);
    }

    /**
     * Reproduz o áudio do botão clicado
     *
     * @param evento disparado quando um botão é clicado
     */
    public void tocarAudioBotao(ActionEvent evento) {
        String nomeBotao = ((Button) evento.getSource()).getId();
        int posicaoAudio = Integer.parseInt(nomeBotao.substring(1));
        String audio = arrayOpcoes[posicaoAudio - 1];
        String caminhoAudio = "";
        switch (getFase()) {
            case 1:
                caminhoAudio = "_audios/audios_vogais/" + audio + ".mp3";
                break;
            case 2:
                caminhoAudio = "_audios/audios_silabasSimples/" + audio + ".mp3";
                break;
            case 3:
                caminhoAudio = "_audios/audios_palavrasSimples/" + audio + ".mp3";
                break;
            case 4:
                caminhoAudio = "_audios/audios_silabasComplexas1/" + audio + ".mp3";
                break;
            case 5:
                caminhoAudio = "_audios/audios_silabasComplexas2/" + audio + ".mp3";
                break;
            case 6:
                caminhoAudio = "_audios/audios_silabasComplexas3/" + audio + ".mp3";
                break;
            case 7:
                caminhoAudio = "_audios/audios_palavrasComplexas/" + audio + ".mp3";
                break;
        }
        System.out.println("Audio " + caminhoAudio);
        URL file = getClass().getResource(caminhoAudio);
        media = new Media(file.toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.play();
                setTocando(true);
            }

        });
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                setTocando(false);
                mediaPlayer.dispose();
            }
        });
    }

    public void incrementarAcerto() {
        this.acerto++;
        incrementarPontuacao(acerto);
    }

    public void setAcerto(int acerto) {
        this.acerto = acerto;
    }

    public int getAcertos() {
        return this.acerto;
    }

    public void setTocando(boolean valor) {
        this.tocando = valor;
    }

    public boolean isTocando() {
        return tocando;
    }

    /**
     * Verifica se o jogador atual já está no ranking
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private boolean jogadorExiste() throws FileNotFoundException, IOException {
        jogadorExiste = false;
        FileReader fr = new FileReader("ranking.txt");
        BufferedReader br = new BufferedReader(fr);
        while (br.ready()) {
            String l = br.readLine();
            if (l.contains(nomeJogador.getText())) {
                jogadorExiste = true;
                break;
            }
        }
        return jogadorExiste;
    }

    public void incrementarErro() {
        this.erro++;
    }

    /**
     * Habilita todos os botões do nivel
     *
     * @param nivel nivel que terá seus botões exibidos
     */
    public void exibirBotoes(int nivel) {
        setCorBotao(btemp1, "#00000");
        switch (nivel) {
            case 1:

                listaBotoes.stream().forEach((botaoDesativado) -> {
                    ((Button) botaoDesativado).setDisable(false);
                    setCorBotao(((Button) botaoDesativado), "#00000");
                });
                break;
            case 2:
                listaBotoes.add(b9);
                listaBotoes.add(b10);
                listaBotoes.stream().forEach((botaoDesativado) -> {
                    ((Button) botaoDesativado).setDisable(false);
                    setCorBotao(((Button) botaoDesativado), "#00000");
                });
                break;
            case 3:
                listaBotoes.add(b11);
                listaBotoes.add(b12);
                listaBotoes.add(b13);
                listaBotoes.add(b14);
                listaBotoes.add(b15);
                listaBotoes.add(b16);
                listaBotoes.stream().forEach((botaoDesativado) -> {
                    ((Button) botaoDesativado).setDisable(false);
                    setCorBotao(((Button) botaoDesativado), "#00000");
                });
                break;
        }
    }

    public Double getTempo() {
        return this.tempo;
    }

    /**
     * Define o tempo inicial que será descrescido de acordo o jogo
     *
     * @param tempo valor do tempo inicial
     */
    public void setTempoInicial(Double tempo) {
        this.tempo = tempo;
    }

    public void iniciarTimer() {
        setTimerIniciado(true);
        timer = new Timer();
        //criação da tarefa que vai executar durante 1 segundo

        timer.scheduleAtFixedRate(new TimerTask() {
            Double tempo = getTempo();

            @Override
            public void run() {
                //Platform.runLater para alterar elementos da interface do javaFX
                Platform.runLater(() -> {
                    if (!isGameOver()) {
                        tempo = tempo - getTaxaReducaoTempo();
                        setBarraTempo(tempo);
                        setTempoJogador(tempo);
                    }
                    if (mostrandoPopUp()) {
                        timer.cancel();
                        timer.purge();
                    }
                    if (tempo < 0) {
                        setGameOver(true);
                        timer.cancel();
                        if (!rankingAtualizado) {
                            salvarPontuacaoRanking();
                            rankingAtualizado = false;
                        }

                        setBarraTempo(0.0);
                        try {
                            janela = (Stage) imagemFundo.getScene().getWindow();
                            exibirPopUpGameOver(janela);

                        } catch (IOException ex) {
                            Logger.getLogger(ModelPrincipal.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //timer.purge();
                });
            }
        }, 0, 1000);
    }

    public void setBarraTempo(Double tempo) {
        if (tempo < 0.9 || tempo == 1.0) {
            barraTempo.setStyle("-fx-accent: #00EE00");
        }
        if (tempo < 0.8) {
            barraTempo.setStyle("-fx-accent: #00CD00");
        }
        if (tempo < 0.7) {
            barraTempo.setStyle("-fx-accent: #FFFF00");
        }
        if (tempo < 0.6) {
            barraTempo.setStyle("-fx-accent: #EEEE00");
        }
        if (tempo < 0.5) {
            barraTempo.setStyle("-fx-accent: #CDCD00");
        }
        if (tempo < 0.4) {
            barraTempo.setStyle("-fx-accent: #FFA500");
        }
        if (tempo < 0.3) {
            barraTempo.setStyle("-fx-accent: #FF6347");
        }
        if (tempo < 0.2) {
            barraTempo.setStyle("-fx-accent: #FF4500");
        }
        if (tempo < 0.1) {
            barraTempo.setStyle("-fx-accent: #FF0000");
        }

        barraTempo.setProgress(tempo);
    }

    public void setDesabilitado(boolean valor) {
        //botoes de niveis
        nivel1.setDisable(valor);
        nivel2.setDisable(valor);
        nivel3.setDisable(valor);

        //botoes de fase
        fase1.setDisable(valor);
        fase2.setDisable(valor);
        fase3.setDisable(valor);
        fase4.setDisable(valor);
        fase5.setDisable(valor);
        fase6.setDisable(valor);
        fase7.setDisable(valor);

        //menu e sair
        menuInicial.setDisable(valor);
        sair.setDisable(valor);
    }

    public boolean primeiroClique() {
        return this.cliquesTotais == 0;
    }

    /**
     * Define a quantidade de cliques
     */
    public void setPrimeiroClique() {
        this.cliques = 0;
        this.cliquesTotais = 0;
    }

    public void setGameOver(boolean valor) {
        this.gameOver = valor;
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public int getPontos() {
        return this.pontos;
    }

    public int getPontuacaoTotal() {
        long total = 0;
        for (long i : pontuacoesNivel1) {
            total = total + i;
        }
        for (long i : pontuacoesNivel2) {
            total = total + i;
        }
        for (long i : pontuacoesNivel3) {
            total = total + i;
        }
        System.out.println("Total " + total);
        return (int) total;
    }

    /**
     * *
     * Define a cor do botao
     *
     * @param botao botao clicado
     * @param cor cor que deve ser aplicada ao botão
     */
    public void setCorBotao(Button botao, String cor) {
        try {
            botao.setStyle("-fx-background-color: " + cor);
        } catch (Exception e) {

        }

    }

    /**
     * Define que o timer foi iniciado ou não
     *
     * @param b true ou false
     */
    public void setTimerIniciado(boolean b) {
        this.timerIniciado = b;
    }

    /**
     * Verifica se o timer foi iniciado
     *
     * @return true ou false
     */
    public boolean getTimerIniciado() {
        return this.timerIniciado;
    }

    public Double getTaxaReducaoTempo() {
        Double taxa = 0.0;
        switch (getNivel()) {
            case 1:
                taxa = 0.03;
                break;
            case 2:
                taxa = 0.018;
                break;
            case 3:
                taxa = 0.015;
                break;
        }
        return taxa;

    }

    /**
     * Verifica se o jogador terminou a fase
     *
     * @throws IOException
     */
    public void verificarTerminoNivel() throws IOException {
        janela = (Stage) imagemFundo.getScene().getWindow();
        boolean terminou = false;
        switch (getNivel()) {
            case 1:
                if (getAcertos() == 4) {
                    terminou = true;
                }
                break;
            case 2:
                if (getAcertos() == 5) {
                    terminou = true;
                }
                break;
            case 3:
                if (getAcertos() == 8) {
                    terminou = true;
                }
                break;
        }
        if (terminou) {
            tocarEfeitoAcertoFinal();
            exibirPopUp(janela);
            setAcerto(0);
            mediaPlayer.dispose();
        }

    }

    public void mudarFase(ActionEvent event) {
        String botaoClicado = ((Button) event.getSource()).getId();
        int botaoFase = Integer.parseInt(botaoClicado.substring(4));
        switch (botaoFase) {
            case 1:
                fase1.setId("_btn1");
                fase2.setId("fase2");
                fase3.setId("fase3");
                fase4.setId("fase4");
                fase5.setId("fase5");
                fase6.setId("fase6");
                fase7.setId("fase7");
                break;
            case 2:
                fase1.setId("fase1");
                fase2.setId("_btn2");
                fase3.setId("fase3");
                fase4.setId("fase4");
                fase5.setId("fase5");
                fase6.setId("fase6");
                fase7.setId("fase7");
                break;
            case 3:
                fase1.setId("fase1");
                fase2.setId("fase2");
                fase3.setId("_btn3");
                fase4.setId("fase4");
                fase5.setId("fase5");
                fase6.setId("fase6");
                fase7.setId("fase7");
                break;
            case 4:
                fase1.setId("fase1");
                fase2.setId("fase2");
                fase3.setId("fase3");
                fase4.setId("_btn4");
                fase5.setId("fase5");
                fase6.setId("fase6");
                fase7.setId("fase7");
                break;
            case 5:
                fase1.setId("fase1");
                fase2.setId("fase2");
                fase3.setId("fase3");
                fase4.setId("fase4");
                fase5.setId("_btn5");
                fase6.setId("fase6");
                fase7.setId("fase7");
                break;
            case 6:
                fase1.setId("fase1");
                fase2.setId("fase2");
                fase3.setId("fase3");
                fase4.setId("fase4");
                fase5.setId("fase5");
                fase6.setId("_btn6");
                fase7.setId("fase7");
                break;
            case 7:
                fase1.setId("fase1");
                fase2.setId("fase2");
                fase3.setId("fase3");
                fase4.setId("fase4");
                fase5.setId("fase5");
                fase6.setId("fase6");
                fase7.setId("_btn7");
                break;
        }
        setFase(botaoFase);
        gerarOpcoes(botaoFase);
        exibirBotoes(getNivel());
    }

    /**
     * Destaca qual fase está sendo jogada
     *
     * @param fase fase a ser destacada
     */
    public void destacarBotao(int fase) {
        switch (fase) {
            case 1:
                imgFase1.setImage(faseAberta);
                fase1.setId("_btn1");
                fase2.setId("fase2");
                fase3.setId("fase3");
                fase4.setId("fase4");
                fase5.setId("fase5");
                fase6.setId("fase6");
                fase7.setId("fase7");
                break;
            case 2:
                imgFase2.setImage(faseAberta);
                fase1.setId("fase1");
                fase2.setId("_btn2");
                fase3.setId("fase3");
                fase4.setId("fase4");
                fase5.setId("fase5");
                fase6.setId("fase6");
                fase7.setId("fase7");
                break;
            case 3:
                imgFase3.setImage(faseAberta);
                fase1.setId("fase1");
                fase2.setId("fase2");
                fase3.setId("_btn3");
                fase4.setId("fase4");
                fase5.setId("fase5");
                fase6.setId("fase6");
                fase7.setId("fase7");
                break;
            case 4:
                imgFase4.setImage(faseAberta);
                fase1.setId("fase1");
                fase2.setId("fase2");
                fase3.setId("fase3");
                fase4.setId("_btn4");
                fase5.setId("fase5");
                fase6.setId("fase6");
                fase7.setId("fase7");
                break;
            case 5:
                imgFase5.setImage(faseAberta);
                fase1.setId("fase1");
                fase2.setId("fase2");
                fase3.setId("fase3");
                fase4.setId("fase4");
                fase5.setId("_btn5");
                fase6.setId("fase6");
                fase7.setId("fase7");
                break;
            case 6:
                imgFase6.setImage(faseAberta);
                fase1.setId("fase1");
                fase2.setId("fase2");
                fase3.setId("fase3");
                fase4.setId("fase4");
                fase5.setId("fase5");
                fase6.setId("_btn6");
                fase7.setId("fase7");
                break;
            case 7:
                imgFase7.setImage(faseAberta);
                fase1.setId("fase1");
                fase2.setId("fase2");
                fase3.setId("fase3");
                fase4.setId("fase4");
                fase5.setId("fase5");
                fase6.setId("fase6");
                fase7.setId("_btn7");
                break;
        }
    }

    public void destacarNivel(int nivel) {
        switch (nivel) {
            case 1:
                nivel1.setId("selec1");
                nivel2.setId("nivel2");
                nivel3.setId("nivel3");
                break;
            case 2:
                nivel2.setId("selec2");
                nivel1.setId("nivel1");
                nivel3.setId("nivel3");
                break;
            case 3:
                nivel3.setId("selec3");
                nivel2.setId("nivel2");
                nivel1.setId("nivel1");
                break;
        }
    }

    /**
     * Define um íncone para o avatar do jogador
     *
     * @param avatar valor do avatar escolhido
     */
    public void setIconeAvatar(int avatar) {
        setAvatar(avatar);
        URL arquivoImg = getClass().getResource("imagens/icones/" + getAvatar() + ".png");
        this.iconeAvatar.setImage(new Image(arquivoImg.toString()));
    }

    /**
     * Define o avatar clicado no ranking
     *
     * @param imagem
     */
    public void setIconeAvatar(Image imagem) {
        this.iconeAvatar.setImage(imagem);
    }

    /**
     * Incrementa a pontuação do jogador
     *
     * @param acerto quantidade de acertos
     */
    public void incrementarPontuacao(int acerto) {
        this.pontos = pontos + 10;
        if (acerto == 1) {
            pt1.setVisible(true);
        }
        if (acerto == 2) {
            pt2.setVisible(true);
        }
        if (acerto == 3) {
            pt3.setVisible(true);
        }
        if (acerto == 4) {
            pt4.setVisible(true);
        }
        if (acerto == 5) {
            pt5.setVisible(true);
        }
        if (acerto == 6) {
            pt6.setVisible(true);
        }
        if (acerto == 7) {
            pt7.setVisible(true);
        }
        if (acerto == 8) {
            pt8.setVisible(true);
        }
        //pontuacao.setText(pontos + " pts");
    }

    /**
     * Define o nome do jogador
     *
     * @param text novo nome do jogador
     */
    public void setNomeJogador(String text) {
        this.nomeJogador.setText(text);
    }

    /**
     * Toca o efeito de acerto quando um par é formado
     */
    public void tocarEfeitoAcerto() {
        tocarEfeito("efeitoAcerto");
    }

    /**
     * Toca efeito de palmas no final da fase
     */
    public void tocarEfeitoAcertoFinal() {
        tocarEfeito("palmas");
    }

    /**
     * Volta para o menu com os avatares
     *
     * @param event mouse clicado
     * @throws IOException
     */
    public void menuInicial(ActionEvent event) throws IOException {
        janela = (Stage) barraTempo.getScene().getWindow();
        Rectangle2D tamanhoDisplay = Screen.getPrimary().getVisualBounds();
        Double comprimento = tamanhoDisplay.getWidth();
        Scene scene = null;
        if(comprimento>1100){
            fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Ranking.fxml"));
            cenaPrincipal = (Parent) fxmloader.load();
            rankingController = fxmloader.<RankingController>getController();
            scene = new Scene(cenaPrincipal, 1200, 700);
        }else{
            fxmloader = new FXMLLoader(getClass().getResource("/interfaces/RankingQuadrada.fxml"));
            cenaPrincipal = (Parent) fxmloader.load();
            rankingController = fxmloader.<RankingController>getController();
            scene = new Scene(cenaPrincipal, 1024, 711);
        }       
        if (timerIniciado) {
            timer.cancel();
        }        
        janela.setScene(scene);
        janela.setFullScreen(true);
        janela.setFullScreenExitHint("");
        janela.show();
    }

    /**
     * Ordena o ranking de pontuações
     *
     * @param br bufferedreader que acessou o arquivo do ranking
     * @throws IOException lança uma exceção caso o arquivo não exista
     */
    public void ordenarRanking(BufferedReader br) throws IOException {
        String r1;
        ArrayList listaOriginal = new ArrayList();
        while (br.ready()) {
            String linha = br.readLine();
            String[] split = linha.split(">");//separa a linha em 3 partes
            String part1 = split[0];//numero do avatar
            String part2 = split[1];//nome do jogador
            String part3 = split[2];//pontuação            
            listaOriginal.add(split);//lê todas as linhas do arquivo
        }
    }

    /**
     * Toca um efeito sonoro
     *
     * @param efeito nome do efeito sonoro
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
                setTocando(true);
            }
        });
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                setTocando(false);
            }
        });
    }

    /**
     * Sai do jogo quando o botão sair é clicado
     *
     *
     */
    public void sairDoJogo() {
        janela = (Stage) grupoNivel1.getScene().getWindow();

        //função para encerrar todos os processos quando o usuário clicar no "X"
        Alert confirmacaoSaida = new Alert(AlertType.CONFIRMATION,
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

    public void salvarPontuacaoRanking() {
        String linha = "\n";
        String SO = System.getProperty("os.name");
        if (SO.contains("Windows")) {
            System.out.println("Entrou aqui");
            linha = "\r\n";
        }
        try {
            FileWriter fw = new FileWriter("ranking.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (jogadorExiste()) {
                System.out.println("Jogador existe sim");
                maiorPontuacao("ranking.txt");
            } else {
                maiorPontuacao("ranking.txt");
                System.out.println("Entrou no eslo");
                bw.append(getAvatar() + ">" + nomeJogador.getText()
                        + ">" + getPontuacaoTotal() + linha);
            }
            bw.flush();
            bw.close();
            FileReader fr = new FileReader("ranking.txt");
            BufferedReader br = new BufferedReader(fr, 100000);
            ordenarRanking(br);
        } catch (IOException ex) {

        }
    }

    /**
     * Exibe o pop up mostrando a pontuação do jogador, permitindo que ele saia
     * do jogo, renicie a fase ou continue o jogo
     *
     * @param janela parametro do tipo Stage que define em qual janela o pop up
     * será exibido
     */
    private void exibirPopUp(Stage janela) {
        setMostrandoPopUp(true);
        setDesabilitado(true);
        fxmlPopUp = new FXMLLoader(getClass().getResource("/interfaces/popUpGrupoFinalizado.fxml"));

        try {
            Funcoes.popup.getContent().add((Parent) fxmlPopUp.load());
        } catch (IOException ex) {
            Logger.getLogger(ModelInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        PopUpController popUpController = fxmlPopUp.getController();
        Funcoes.popup.show(janela);
        popUpController.setPontuacaoJogador(pontos, getTempo(), cliquesTotais, getFase(), getNivel());
        Funcoes.popup.addEventHandler(ActionEvent.ANY, new WeakEventHandler<>(evento -> {
            Funcoes.popup.hide();
        }));

        Funcoes.popup.addEventHandler(MouseEvent.MOUSE_CLICKED, new WeakEventHandler<>(evento -> {
            String botaoClicado = Funcoes.idBotao;
            if (!botaoClicado.isEmpty()) {
                Funcoes.popup.hide();
            }
        }));

        Funcoes.popup.setOnHidden((final WindowEvent event) -> {
            String botaoClicado = Funcoes.idBotao;
            System.out.println("Botao clicado " + botaoClicado);
            switch (botaoClicado) {
                case "sair":
                    sairDoJogo();
                    break;
                case "continuar":

                    setDesabilitado(false);
                    int fasesBloqueadas = 0;//
                    bloquearFase(getFase());//bloqueaia a fase que foi finalizada
                    long pontosFase = popUpController.getPontuacaoJogador();//recebe o ponto da fase
                    setPontosLabelFase(pontosFase);
                    switch (getNivel()) {
                        case 1:
                            fasesBloqueadas = fasesBloqueadasNivel1.size();
                            pontuacoesNivel1.add(getFase(), pontosFase);//adiciona a pontuação à fase
                            break;
                        case 2:
                            fasesBloqueadas = fasesBloqueadasNivel2.size();
                            pontuacoesNivel2.add(getFase(), pontosFase);
                            break;
                        case 3:
                            fasesBloqueadas = fasesBloqueadasNivel3.size();
                            pontuacoesNivel3.add(getFase(), pontosFase);
                            break;
                    }
                    salvarPontuacaoRanking();
                    rankingAtualizado = true;
                    if (fasesBloqueadas != 7) {

                        setMostrandoPopUp(false);
                        setBarraTempo(1.0);
                        zerarPontuacao();
                        setTempoInicial(1.0);
                        setFase(getFaseDisponivel());
                        exibirBotoes(getNivel());
                        iniciarJogo();
                        getPontuacaoTotal();
                    } else {

                        exibirPopUpNivel(janela);
                    }
                    break;
                case "reiniciar":
                    setDesabilitado(false);
                    setMostrandoPopUp(false);
                    setFase(getFase());
                    zerarPontuacao();
                    setTempoInicial(1.0);
                    exibirBotoes(getNivel());
                    iniciarJogo();
                    iniciarTimer();
                    //reiniciar o nível
                    break;
            }
            Funcoes.idBotao = "";
        });
    }

    /**
     * Exibe um pop quando o jogador finaliza o nível
     *
     * @param janela janela principal do jogo
     */
    private void exibirPopUpNivel(Stage janela) {
        setMostrandoPopUp(true);
        setDesabilitado(true);
        fxmlPopUp = new FXMLLoader(getClass().getResource("/interfaces/popUpNivelFinalizado.fxml"));
        Popup popup = new Popup();
        try {
            popup.getContent().add((Parent) fxmlPopUp.load());
        } catch (IOException ex) {
            Logger.getLogger(ModelInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        PopUpNivelFinalizadoController popUpController = fxmlPopUp.getController();
        popup.show(janela);

        popup.addEventHandler(ActionEvent.ACTION, new WeakEventHandler<>(evento -> {
            setDesabilitado(false);
            popup.hide();
        }));

        popUpController.bloquearNivel(getNivel());
        popUpController.atualizarBloqueios(niveisBloqueados);
        popup.setOnHidden((final WindowEvent event) -> {
            String botaoClicado = Funcoes.idBotao;
            System.out.println("Botao clicado " + botaoClicado);
            switch (botaoClicado) {
                case "sair":
                    sairDoJogo();
                    break;
                case "reiniciar":
                    setDesabilitado(false);
                    setMostrandoPopUp(false);
                    setFase(1);
                    desbloquearFases();
                    exibirBotoes(getNivel());
                    zerarPontuacaoLabelFases();
                    setTempoInicial(1.0);
                    atualizarPontosFase();
                    zerarPontuacao();
                    reiniciarTimer();
                    //reiniciar o nível
                    break;
                case "nivel1":
                    bloquearNivel(getNivel());
                    zerarPontuacao();
                    setMostrandoPopUp(false);
                    nivel1.fire();
                    break;
                case "nivel2":
                    zerarPontuacao();
                    bloquearNivel(getNivel());
                    setMostrandoPopUp(false);
                    nivel2.fire();
                    break;
                case "nivel3":
                    setMostrandoPopUp(false);
                    zerarPontuacao();
                    bloquearNivel(getNivel());
                    nivel3.fire();
                    break;
            }
        });
    }

    /**
     * Exibe um pop quando o jogador finaliza o nível
     *
     * @param janela janela principal do jogo
     */
    public void exibirPopUpGameOver(Stage janela) throws IOException {
        setMostrandoPopUp(true);
        setDesabilitado(true);
        fxmlPopUp = new FXMLLoader(getClass().getResource("/interfaces/PopUpGameOver.fxml"));

        Popup popup = new Popup();

        try {
            popup.getContent().add((Parent) fxmlPopUp.load());
        } catch (IOException ex) {
            Logger.getLogger(ModelInicial.class.getName()).log(Level.SEVERE, null, ex);
        }

        PopUpGameOverController popUpController = fxmlPopUp.getController();
        popup.show(janela);
        popUpController.atualizarRanking();

        popup.addEventHandler(MouseEvent.MOUSE_CLICKED, new WeakEventHandler<>(evento -> {
            String botaoClicado = Funcoes.idBotao;
            if (!botaoClicado.isEmpty()) {
                popup.hide();
            }
        }));
        popup.addEventHandler(ActionEvent.ACTION, new WeakEventHandler<>(evento -> {
            popup.hide();
        }));

        popup.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent event) {
                setDesabilitado(false);
                String botaoClicado = Funcoes.idBotao;
                System.out.println("");
                switch (botaoClicado) {
                    case "sair":
                        sairDoJogo();
                        break;
                    case "reiniciarJogo":
                        setFase(1);
                        desbloquearFases();
                        setNivel(1);
                        setTempoInicial(1.0);
                        exibirBotoes(getNivel());
                        zerarPontuacao();
                        setTimerIniciado(false);
                        setMostrandoPopUp(false);
                        setGameOver(false);
                        setPrimeiroClique();
                        setBarraTempo(1.0);
                        iniciarPontuacoesLabels();
                        atualizarPontuacaoTodasFases();
                        reiniciarTimer();
                        break;
                    case "menuInicial":
                        timer.cancel();
                        menuInicial.fire();
                        break;
                }
                Funcoes.idBotao = "";
            }

        });
    }

    /**
     * Retorna se algum pop up está sendo mostrado na tela
     *
     * @return booelan true or false
     */
    public boolean mostrandoPopUp() {
        return this.mostrando;
    }

    /**
     * Define se um pop up está sendo exibido na tela
     *
     * @param mostrando parâmetro true ou false
     */
    public void setMostrandoPopUp(boolean mostrando) {
        this.mostrando = mostrando;
    }

    /**
     * Bloqueia uma fase já finalizada
     *
     * @param fase fase a ser bloqueada
     */
    public void bloquearFase(int fase) {
        Button faseTemp = null;
        switch (fase) {
            case 1:
                imgFase1.setImage(faseBloqueada);
                fase1.setDisable(true);
                //fase1.setGraphic(imgFase1);
                faseTemp = fase1;
                break;
            case 2:
                imgFase2.setImage(faseBloqueada);
                fase2.setDisable(true);
                faseTemp = fase2;
                break;
            case 3:
                imgFase3.setImage(faseBloqueada);
                fase3.setDisable(true);
                faseTemp = fase3;
                break;
            case 4:
                imgFase4.setImage(faseBloqueada);
                fase4.setDisable(true);
                faseTemp = fase4;
                break;
            case 5:
                imgFase5.setImage(faseBloqueada);
                fase5.setDisable(true);
                faseTemp = fase5;
                break;
            case 6:
                imgFase6.setImage(faseBloqueada);
                fase6.setDisable(true);
                faseTemp = fase6;
                break;
            case 7:
                imgFase7.setImage(faseBloqueada);
                fase7.setDisable(true);
                faseTemp = fase7;
                break;
        }

        switch (getNivel()) {
            case 1:
                fasesBloqueadasNivel1.add(faseTemp);
                break;
            case 2:
                fasesBloqueadasNivel2.add(faseTemp);
                break;
            case 3:
                fasesBloqueadasNivel3.add(faseTemp);
                break;
        }

    }

    /**
     * Bloqueia um nível já finalizado
     *
     * @param nivel nivel a ser bloqueado
     */
    public void bloquearNivel(int nivel) {
        switch (nivel) {
            case 1:
                nivel1.setDisable(true);
                niveisBloqueados.add(nivel1);
                break;
            case 2:
                nivel2.setDisable(true);
                niveisBloqueados.add(nivel2);
                break;
            case 3:
                nivel3.setDisable(true);
                niveisBloqueados.add(nivel2);
                break;
        }
    }

    /**
     * Desbloqueia todas as fases bloqueadas
     */
    public void desbloquearFases() {
        switch (getNivel()) {
            case 1:
                for (Object fases : fasesBloqueadasNivel1) {
                    ((Button) fases).setDisable(false);
                    int faseBloq = Integer.parseInt(((Button) fases).getId().substring(4));
                    atualizarFases(faseBloq, false);
                }
                break;
            case 2:
                for (Object fases : fasesBloqueadasNivel2) {
                    ((Button) fases).setDisable(false);
                    int faseBloq = Integer.parseInt(((Button) fases).getId().substring(4));
                    atualizarFases(faseBloq, false);
                }
                break;
            case 3:
                for (Object fases : fasesBloqueadasNivel3) {
                    ((Button) fases).setDisable(false);
                    int faseBloq = Integer.parseInt(((Button) fases).getId().substring(4));
                    atualizarFases(faseBloq, false);
                }
                break;
        }
    }

    /**
     * Enche a barra de tempo e espera o jogador clicar em um botão para
     * continuar o jogo
     */
    public void reiniciarTimer() {
        setBarraTempo(1.0);
        barraTempo.setProgress(1.0);
        timer.cancel();
        timer.purge();
        this.tempo = 1.0;
        iniciarJogo();
    }

    /**
     * Quando o layout é alterado atualiza as fases que estão bloqueadas
     */
    public void atualizarBloqueios() {
        fase1.setDisable(false);
        fase2.setDisable(false);
        fase3.setDisable(false);
        fase4.setDisable(false);
        fase5.setDisable(false);
        fase6.setDisable(false);
        fase7.setDisable(false);
        switch (getNivel()) {
            case 1:
                for (Object faseTemp : fasesBloqueadasNivel1) {
                    ((Button) faseTemp).setDisable(true);
                    int faseBloq = Integer.parseInt(((Button) faseTemp).getId().substring(4));
                    atualizarFases(faseBloq, true);
                }
                break;

            case 2:
                for (Object faseTemp : fasesBloqueadasNivel2) {
                    ((Button) faseTemp).setDisable(true);
                    int faseBloq = Integer.parseInt(((Button) faseTemp).getId().substring(4));
                    atualizarFases(faseBloq, true);
                }
                break;
            case 3:
                for (Object faseTemp : fasesBloqueadasNivel3) {
                    ((Button) faseTemp).setDisable(true);
                    int faseBloq = Integer.parseInt(((Button) faseTemp).getId().substring(4));
                    atualizarFases(faseBloq, true);
                }
                break;
        }
        for (Object nivelTemp : niveisBloqueados) {
            ((Button) nivelTemp).setDisable(true);
        }
    }

    public void atualizarPontosFase() {
        vetorLabel[0].setText("0");
        vetorLabel[1].setText("0");
        vetorLabel[2].setText("0");
        vetorLabel[3].setText("0");
        vetorLabel[4].setText("0");
        vetorLabel[5].setText("0");
        vetorLabel[6].setText("0");

        switch (getNivel()) {
            case 1:
                for (int j = 1; j < pontuacoesNivel1.size(); j++) {
                    if (!pontuacoesNivel1.get(j).equals((long) 0)) {
                        vetorLabel[j - 1].setText("" + pontuacoesNivel1.get(j));
                    }
                }
                break;

            case 2:
                for (int j = 1; j < pontuacoesNivel2.size(); j++) {
                    if (!pontuacoesNivel2.get(j).equals((long) 0)) {
                        vetorLabel[j - 1].setText("" + pontuacoesNivel2.get(j));
                    }
                }
                break;
            case 3:
                for (int j = 1; j < pontuacoesNivel3.size(); j++) {
                    if (!pontuacoesNivel3.get(j).equals((long) 0)) {
                        vetorLabel[j - 1].setText("" + pontuacoesNivel3.get(j));
                    }
                }
                break;
        }
    }

    private void atualizarPontuacaoTodasFases() {
        vetorLabel[0].setText("0");
        vetorLabel[1].setText("0");
        vetorLabel[2].setText("0");
        vetorLabel[3].setText("0");
        vetorLabel[4].setText("0");
        vetorLabel[5].setText("0");
        vetorLabel[6].setText("0");
    }

    /**
     * Retorna a menor fase disponível para quando o jogador trocar de nível
     *
     * @return
     */
    private int getFaseDisponivel() {
        int faseDisponivel = 1;
        ArrayList<Integer> arrayBloqueados = new ArrayList<Integer>();
        switch (getNivel()) {
            case 1:
                System.out.println("Nivel 1");
                if (!fasesBloqueadasNivel1.isEmpty()) {
                    //fasesBloqueadasNivel1
                    for (int k = 0; k < fasesBloqueadasNivel1.size(); k++) {
                        String idFase = ((Button) fasesBloqueadasNivel1.get(k)).getId();
                        int numeroFase = Integer.parseInt(idFase.substring(4));
                        System.out.println("Nume " + numeroFase);
                        arrayBloqueados.add(numeroFase);
                    }
                }
                break;
            case 2:
                System.out.println("Nivel 2");
                if (!fasesBloqueadasNivel2.isEmpty()) {
                    for (int k = 0; k < fasesBloqueadasNivel2.size(); k++) {
                        String idFase = ((Button) fasesBloqueadasNivel2.get(k)).getId();
                        int numeroFase = Integer.parseInt(idFase.substring(4));
                        arrayBloqueados.add(numeroFase);
                    }
                }
                System.out.println("Bloqueados " + arrayBloqueados.isEmpty());
                break;
            case 3:
                if (!fasesBloqueadasNivel3.isEmpty()) {
                    for (int k = 0; k < fasesBloqueadasNivel3.size(); k++) {
                        String idFase = ((Button) fasesBloqueadasNivel3.get(k)).getId();
                        int numeroFase = Integer.parseInt(idFase.substring(4));
                        arrayBloqueados.add(numeroFase);
                    }
                }
                break;
        }

        if (arrayBloqueados.isEmpty()) {
            return 1;
        }

        for (int j = 1; j <= 7; j++) {
            if (!arrayBloqueados.contains((Integer) j)) {
                faseDisponivel = j;
                break;
            }
        }
        arrayBloqueados.clear();
        return faseDisponivel;
    }

    public boolean maiorPontuacao(String arquivo) throws IOException {
        String novaLinha = "\n";
        String SO = System.getProperty("os.name");
        if (SO.contains("Windows")) {
            novaLinha = "\r\n";
        }
        boolean maior = false;
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(arquivo);
            fw = new FileWriter("rankingTemp.txt", true);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModelPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr, 100000);
        BufferedWriter bw = new BufferedWriter(fw);

        //loop que copia todos os registros de um arquivo para um arquivo temporario
        //caso o jogador atual já esteja no ranking a sua pontuação será atualizada se for maior
        //que a pontuação presente no ranking
        while (br.ready()) {
            String linha = br.readLine();
            String[] split = linha.split(">");//separa a linha em 3 partes
            String part1 = split[0];//numero do avatar
            String part2 = split[1];//nome do jogador
            String part3 = split[2];//pontuação  
            System.out.println(part1 + " " + part2 + " " + part3);
            if (split[1].equals(nomeJogador.getText()) && (getPontuacaoTotal() > Integer.parseInt(part3))) {
                part3 = "" + getPontuacaoTotal();
                maior = true;
                System.out.println("Maior");
                bw.append(part1 + ">" + part2 + ">" + part3 + novaLinha);
            } else {
                bw.append(linha + novaLinha);
            }

        }
        br.close();
        bw.close();
        fr.close();
        fw.close();
        try {
            File fileToDelete = new File("ranking.txt");
            formatarArquivo("ranking.txt");
            fr = new FileReader("rankingTemp.txt");
            fw = new FileWriter("ranking.txt", true);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModelPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        br = new BufferedReader(fr);
        bw = new BufferedWriter(fw);
        while (br.ready()) {
            String linha = br.readLine();
            String[] split = linha.split(">");//separa a linha em 3 partes
            String part1 = split[0];//numero do avatar
            String part2 = split[1];//nome do jogador
            String part3 = split[2];//pontuação
            bw.append(linha + novaLinha);
        }
        formatarArquivo("rankingTemp.txt");
        br.close();
        bw.close();

        return maior;
    }

    public void setJogadorExiste(boolean existe) {
        this.jogadorExiste = existe;
    }

    public void zerarPontuacao() {
        this.pontos = 0;
        //pontuacao.setText(pontos + " pts");
        resetarAcertos();
        setBarraTempo(1.0);
    }

    public void atualizarFases(int faseBloq, boolean bloqueio) {
        if (bloqueio) {
            switch (faseBloq) {
                case 1:
                    imgFase1.setImage(faseBloqueada);
                    break;
                case 2:
                    imgFase2.setImage(faseBloqueada);
                    break;
                case 3:
                    imgFase3.setImage(faseBloqueada);
                    break;
                case 4:
                    imgFase4.setImage(faseBloqueada);
                    break;
                case 5:
                    imgFase5.setImage(faseBloqueada);
                    break;
                case 6:
                    imgFase6.setImage(faseBloqueada);
                    break;
                case 7:
                    imgFase7.setImage(faseBloqueada);
                    break;

            }
        } else {
            switch (faseBloq) {
                case 1:
                    imgFase1.setImage(faseAberta);
                    break;
                case 2:
                    imgFase2.setImage(faseAberta);
                    break;
                case 3:
                    imgFase3.setImage(faseAberta);
                    break;
                case 4:
                    imgFase4.setImage(faseAberta);
                    break;
                case 5:
                    imgFase5.setImage(faseAberta);
                    break;
                case 6:
                    imgFase6.setImage(faseAberta);
                    break;
                case 7:
                    imgFase7.setImage(faseAberta);
                    break;

            }
        }
    }

    /**
     * Define o tempo que o jogador terminou a fase
     *
     * @param tempo
     */
    public void setTempoJogador(Double tempo) {
        this.tempo = tempo;
    }

    /**
     * Retorna o tempo do jogador na fase
     *
     * @return tempo
     */
    public Double getTempoJogador() {
        return this.tempo;
    }

    public void setPontosLabelFase(Long pontosFase) {
        switch (getFase()) {
            case 1:
                ptFase1.setText("" + pontosFase);
                break;
            case 2:
                ptFase2.setText("" + pontosFase);
                break;
            case 3:
                ptFase3.setText("" + pontosFase);
                break;
            case 4:
                ptFase4.setText("" + pontosFase);
                break;
            case 5:
                ptFase5.setText("" + pontosFase);
                break;
            case 6:
                ptFase6.setText("" + pontosFase);
                break;
            case 7:
                ptFase7.setText("" + pontosFase);
                break;

        }
    }

    public void iniciarPontuacoesLabels() {
        for (int j = 0; j < 7; j++) {
            pontuacoesNivel1.add((long) 0);
            pontuacoesNivel2.add((long) 0);
            pontuacoesNivel3.add((long) 0);
        }
    }

    private void zerarAcertos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void atualizarNiveis(int nivelBloq, boolean bloqueio) {
        if (bloqueio) {
            switch (nivelBloq) {
                case 1:
                    nivel1.setDisable(true);
                    break;
                case 2:
                    nivel2.setDisable(true);
                    break;
                case 3:
                    nivel3.setDisable(true);
                    break;
            }
        }
    }

    private void zerarPontuacaoLabelFases() {
        switch (getNivel()) {
            case 1:
                Collections.fill(pontuacoesNivel1, (long) 0);
                break;
            case 2:
                Collections.fill(pontuacoesNivel2, (long) 0);
                break;
            case 3:
                Collections.fill(pontuacoesNivel3, (long) 0);
                break;
        }
    }

    private void resetarAcertos() {
        if (getNivel() == 1 || getNivel() == 2 || getNivel() == 3) {
            pt1.setVisible(false);
            pt2.setVisible(false);
            pt3.setVisible(false);
            pt4.setVisible(false);
        }
        if (getNivel() == 2 || getNivel() == 3) {
            pt5.setVisible(false);
        }
        if (getNivel() == 3) {
            pt6.setVisible(false);
            pt7.setVisible(false);
            pt8.setVisible(false);
        }
    }

    private void salvarPontuacaoFase() {
        String linha = "\n";
        String SO = System.getProperty("os.name");
        if (SO.contains("Windows")) {
            System.out.println("Entrou aqui");
            linha = "\r\n";
        }

        try {
            FileWriter fw = new FileWriter("rankingTemp.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            bw.append(getAvatar() + ">" + nomeJogador.getText()
                    + ">" + getPontuacaoTotal() + linha);

            bw.flush();
            bw.close();
            fw.flush();
            fw.close();
        } catch (IOException ex) {

        }
    }

    private void formatarArquivo(String file) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append("");
        } catch (IOException ex) {
            Logger.getLogger(ModelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
