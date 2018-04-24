package model;

import controller.PopUpController;
import controller.PopUpGameOverController;
import controller.PopUpNivelFinalizadoController;
import java.io.IOException;
import controller.RankingController;
import controller.PrincipalController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Popup;
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
        "bra", "bras", "bres", "brin", "bron", "bros", "brus", "chão",
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
    private Button botaoFaseAnterior;
    @FXML
    private Button botaoProximaFase;
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
    private String ArrayNivel1[] = new String[16];
    private String[] ArrayNivel2 = new String[16];
    private String[] ArrayNivel3 = new String[16];
    private PrincipalController principalController = null;
    private ArrayList arrayBotoes = new ArrayList<String>();
    private boolean tocando = false, gameOver, timerIniciado;
    private RankingController rankingController = null;
    private EventHandler<ActionEvent> evento1Botao, evento2Botao, eventoSomAcerto,
            eventoProximoNivel, eventoFimNivel, eventoSomBotao;
    private int acerto, erro, fase, nivel, cliquesTotais, cliques, avatar, pontos;
    BufferedReader br;
    private Group grupoNivel1;
    private Group grupoNivel2;
    private Group grupoNivel3;

    FXMLLoader fxmlPopUp;
    Parent popUp = null;
    @FXML
    private ImageView imagemFundo;

    private ArrayList listaBotoes, fasesBloqueadasNivel1, fasesBloqueadasNivel2,
            fasesBloqueadasNivel3, niveisBloquedos;

    private boolean mostrando;

    public ModelPrincipal(Button b1, Button b2, Button b3, Button b4, Button b5,
            Button b6, Button b7, Button b8, Button b9, Button b10, Button b11, Button b12,
            Button b13, Button b14, Button b15, Button b16, Button faseAnterior, Button proximaFase,
            ProgressBar barraTempo, Button f1, Button f2, Button f3, Button f4,
            Button f5, Button f6, Button f7, ImageView icoA, Label pont,
            Label nmJogador, Button n1, Button n2, Button n3, Group grupoNivel1,
            Group grupoNivel2, Group grupoNivel3, ImageView imagemFundo) throws FileNotFoundException {
        this.br = new BufferedReader(new FileReader("ranking.txt"));
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
        this.barraTempo = barraTempo;
        this.nomeJogador = nmJogador;
        this.iconeAvatar = icoA;
        this.botaoProximaFase = proximaFase;
        this.botaoFaseAnterior = faseAnterior;
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
        tempo = 1.0;
        this.mostrando = false;
        this.niveisBloquedos = new ArrayList();
    }

    public void verificarOpcao(ActionEvent evento) {
        if (primeiroClique() && !getTimerIniciado()) {
            iniciarTimer();
            setTimerIniciado(true);
        }
        String nomeBotao = ((Button) evento.getSource()).getId();
        cliques++;
        //verifica se é o primeiro clique
        if (cliques == 1) {
            //tocar audio do botao clicado
            tocarAudioBotao(evento);
            btemp1 = ((Button) evento.getSource());//grava qual botao foi clicado
            botao1 = ArrayNivel1[Integer.parseInt(nomeBotao.substring(1)) - 1];//salva o audio contido no botao
            setCorBotao(btemp1, "#ffff00");

        } else if (cliques == 2) { //verifica se é o segundo clique
            btemp2 = ((Button) evento.getSource());
            botao2 = ArrayNivel1[Integer.parseInt(nomeBotao.substring(1)) - 1];
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
                        new KeyFrame(Duration.seconds(0.7), evento2Botao),
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
        Parent cenaPrincipal = null;
        FXMLLoader fxmloader = null;
        String nomeBotao = ((Button) event.getSource()).getId();
        int numBotao = Integer.parseInt(nomeBotao.substring(5));
        System.out.println("Num " + numBotao);

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

            
            setNivel(numBotao);
            setFase(getFaseDisponivel());
            destacarNivel(getNivel());
            gerarOpcoes(getFase());
            atualizarBloqueios();
            if (timerIniciado) {
                reiniciarTimer();
            }

        }
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
            case 7://palavras complexas
                numeroFonemasVetores = 101;
                break;

        }
        while (i < numeroFonemas) {
            proxValor = indice.nextInt(numeroFonemasVetores);//o valor do next int corresponde a quantidade de fonemas 
            if (!indicesUtilizados.contains(proxValor)) {//se o índice ainda não foi utilizado                
                novasOpcoes.add(proxValor);//adiciona o indice no array
                indicesUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                i++;
            } else {
                if (getNivel() == 3 && getFase() == 1) {
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
                ArrayNivel1[posicao1] = getFonemaArray(fase, j);
                ArrayNivel1[posicao2] = getFonemaArray(fase, j);
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
        if (fase == 7) {
            botaoProximaFase.setVisible(false);
        }
        if (fase == 1) {
            botaoFaseAnterior.setVisible(false);
        } else {
            botaoFaseAnterior.setVisible(true);
        }
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

        botaoFaseAnterior.setVisible(true);
        if (fase == 6) {
            this.fase = fase + 1;
            botaoProximaFase.setVisible(false);
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
     * Altera para a fase anterior
     *
     * @param event
     */
    public void faseAnterior(ActionEvent event) {
        botaoProximaFase.setVisible(true);
        if (fase == 2) {
            this.fase = fase - 1;
            gerarOpcoes(fase);
            exibirBotoes(getNivel());
            botaoFaseAnterior.setVisible(false);
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
     * Reproduz o áudio do botão clicado
     *
     * @param evento disparado quando um botão é clicado
     */
    private void tocarAudioBotao(ActionEvent evento) {
        String nomeBotao = ((Button) evento.getSource()).getId();
        int posicaoAudio = Integer.parseInt(nomeBotao.substring(1));
        String audio = ArrayNivel1[posicaoAudio - 1];
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
        System.out.println("Audio " + audio);
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

    public void incrementarErro() {
        this.erro++;
    }

    /**
     * Habilita todos os botões do nivel
     *
     * @param nivel nivel que terá seus botões exibidos
     */
    public void exibirBotoes(int nivel) {

        switch (nivel) {
            case 1:
                System.out.println("Entrou aqui");
                for (int k = 0; k < 8; k++) {
                    ((Button) listaBotoes.get(k)).setDisable(false);
                }
                break;
            case 2:
                listaBotoes.add(b9);
                listaBotoes.add(b10);
                for (int k = 0; k < 10; k++) {
                    ((Button) listaBotoes.get(k)).setDisable(false);
                }
                break;
            case 3:
                for (int k = 0; k < 16; k++) {
                    ((Button) listaBotoes.get(k)).setDisable(false);
                }
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
        timer = new Timer();
        //criação da tarefa que vai executar durante 1 segundo
        timer.scheduleAtFixedRate(new TimerTask() {
            Double tempo = getTempo();

            @Override
            public void run() {
                //Platform.runLater para alterar elementos da interface do javaFX
                Platform.runLater(() -> {
                    if (!isGameOver()) {
                        tempo = tempo - 0.016;
                        setBarraTempo(tempo);
                    }
                    if (mostrandoPopUp()) {
                        timer.cancel();
                    }
                    if (tempo < 0) {
                        setGameOver(true);
                        timer.cancel();
                        //File arquivo = new File();
                        try {
                            FileWriter fw = new FileWriter("ranking.txt", true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter arquivoSaida = new PrintWriter(bw);
                            String linha = "" + getAvatar() + ">" + nomeJogador.getText() + ">" + getPontos() + "\n";
                            bw.append(linha);
                            bw.close();

                            FileReader fr = new FileReader("ranking.txt");
                            BufferedReader br = new BufferedReader(fr, 100000);
                            ordenarRanking(br);
                        } catch (Exception e) {

                        }

                        setBarraTempo(0.0);
                        try {
                            janela = (Stage) imagemFundo.getScene().getWindow();
                            exibirPopUpGameOver(janela);
                        } catch (IOException ex) {
                            Logger.getLogger(ModelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
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

    public boolean primeiroClique() {
        return this.cliquesTotais == 0;
    }

    /**
     * Define a quantidade de cliques
     */
    public void setPrimeiroClique() {
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

    public void setCorBotao(Button botao, String cor) {
        System.out.println("aqui");
        botao.setStyle("-fx-background-color: " + cor);
    }

    public void setTimerIniciado(boolean b) {
        this.timerIniciado = b;
    }

    private boolean getTimerIniciado() {
        return this.timerIniciado;
    }

    /**
     * Verifica se o jogador terminou a fase
     *
     * @throws IOException
     */
    private void verificarTerminoNivel() throws IOException {
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
        if (botaoFase > 1) {
            botaoFaseAnterior.setVisible(true);
        }
        if (botaoFase < 7) {
            botaoProximaFase.setVisible(true);
        }
        if (botaoFase == 7) {
            botaoProximaFase.setVisible(false);
        }

        switch (botaoFase) {
            case 1:
                fase1.setId("_btn1");
                fase2.setId("fase2");
                fase3.setId("fase3");
                fase4.setId("fase4");
                fase5.setId("fase5");
                fase6.setId("fase6");
                fase7.setId("fase7");
                botaoFaseAnterior.setVisible(false);
                break;
            case 2:
                fase1.setId("fase1");
                fase2.setId("_btn2");
                fase3.setId("fase3");
                fase4.setId("fase4");
                fase5.setId("fase5");
                fase6.setId("fase6");
                fase7.setId("fase7");
                botaoFaseAnterior.setVisible(true);
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
                botaoProximaFase.setVisible(true);
                break;
            case 7:
                fase1.setId("fase1");
                fase2.setId("fase2");
                fase3.setId("fase3");
                fase4.setId("fase4");
                fase5.setId("fase5");
                fase6.setId("fase6");
                fase7.setId("_btn7");
                botaoProximaFase.setVisible(false);
                break;
        }
        setFase(botaoFase);
        gerarOpcoes(botaoFase);
        System.out.println("Nivel " + getNivel());
        exibirBotoes(getNivel());
    }

    private void destacarBotao(int fase) {
        switch (fase) {
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
        System.out.println("Pontos " + pontos);
        this.pontos = pontos + 50;
        pontuacao.setText(pontos + " pts");
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
    private void tocarEfeitoAcertoFinal() {
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
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Ranking.fxml"));
        Parent cenaPrincipal = (Parent) fxmloader.load();
        rankingController = fxmloader.<RankingController>getController();
        Scene scene = new Scene(cenaPrincipal, 1200, 700);
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

    /**
     * Exibe o pop up mostrando a pontuação do jogador, permitindo que ele saia
     * do jogo, renicie a fase ou continue o jogo
     *
     * @param janela parametro do tipo Stage que define em qual janela o pop up
     * será exibido
     */
    private void exibirPopUp(Stage janela) {
        setMostrandoPopUp(true);
        Stage stage = new Stage();
        FXMLLoader fxmlPopUp = new FXMLLoader(getClass().getResource("/interfaces/popUpFaseFinalizada.fxml"));

        try {
            popUp = (Parent) fxmlPopUp.load();
        } catch (IOException ex) {
            Logger.getLogger(ModelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        Popup popup = new Popup();

        popup.getContent();

        stage.setScene(new Scene(popUp));
        stage.setTitle("Pop up");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(janela.getOwner());
        PopUpController popUpController = fxmlPopUp.getController();
        popUpController.setPontuacaoJogador(pontos);
        stage.setAlwaysOnTop(true);
        stage.show();
        stage.toFront();
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent event) {

                Button botaoClicado = popUpController.getBotaoClicado();
                switch (botaoClicado.getId()) {
                    case "sair":
                        sairDoJogo();
                        break;
                    case "continuar":
                        setMostrandoPopUp(false);
                        barraTempo.setProgress(1.0);
                        setBarraTempo(1.0);
                        bloquearFase(getFase());
                        setFase(getFase() + 1);
                        exibirBotoes(getNivel());
                        iniciarJogo();
                        break;
                    case "reiniciar":
                        setFase(getFase());
                        exibirBotoes(getNivel());
                        iniciarJogo();
                        //reiniciar o nível                        
                        break;
                }

            }

        });
    }

    /**
     * Exibe um pop quando o jogador finaliza o nível
     *
     * @param janela janela principal do jogo
     */
    public void exibirPopUpNivel(Stage janela) {
        setMostrandoPopUp(true);
        Stage stage = new Stage();
        FXMLLoader fxmlPopUp = new FXMLLoader(getClass().getResource("/interfaces/popUpNivelFinalizado.fxml"));

        try {
            popUp = (Parent) fxmlPopUp.load();
        } catch (IOException ex) {
            Logger.getLogger(ModelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        Popup popup = new Popup();

        popup.getContent();

        stage.setScene(new Scene(popUp));
        stage.setTitle("");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(janela.getOwner());
        PopUpNivelFinalizadoController popUpController = fxmlPopUp.getController();
        stage.setAlwaysOnTop(true);
        stage.show();
        stage.toFront();
        popUpController.bloquearNivel(getNivel());
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent event) {

                Button botaoClicado = popUpController.getBotaoClicado();
                switch (botaoClicado.getId()) {
                    case "sair":
                        sairDoJogo();
                        break;
                    case "reiniciar":
                        setFase(1);
                        desbloquearFases();
                        exibirBotoes(getNivel());
                        iniciarJogo();
                        //reiniciar o nível                        
                        break;
                    case "nivel1":
                        nivel1.fire();
                        break;
                    case "nivel2":
                        nivel2.fire();
                        break;
                    case "nivel3":
                        nivel3.fire();
                        break;
                }
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
        Stage stage = new Stage();
        FXMLLoader fxmlPopUp = new FXMLLoader(getClass().getResource("/interfaces/PopUpGameOver.fxml"));

        try {
            popUp = (Parent) fxmlPopUp.load();
        } catch (IOException ex) {
            Logger.getLogger(ModelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        Popup popup = new Popup();

        popup.getContent();

        stage.setScene(new Scene(popUp));
        stage.setTitle("");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(janela.getOwner());
        PopUpGameOverController popUpController = fxmlPopUp.getController();
        popUpController.atualizarRanking();
        stage.setAlwaysOnTop(true);
        stage.show();
        stage.toFront();
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent event) {

                Button botaoClicado = popUpController.getBotaoClicado();
                switch (botaoClicado.getId()) {
                    case "sair":
                        sairDoJogo();
                        break;
                    case "reiniciarJogo":
                        setFase(1);
                        desbloquearFases();
                        setNivel(1);
                        exibirBotoes(getNivel());
                        zerarPontuacaoJogador();
                        iniciarJogo();
                        setTimerIniciado(false);
                        setPrimeiroClique();
                        //reiniciar o nível                        
                        break;
                    case "nivel1":
                        nivel1.fire();
                        break;
                    case "nivel2":
                        nivel2.fire();
                        break;
                    case "nivel3":
                        nivel3.fire();
                        break;
                }
            }

        });
    }

    public void zerarPontuacaoJogador() {

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
                fase1.setDisable(true);
                faseTemp = fase1;
                break;
            case 2:
                fase2.setDisable(true);
                faseTemp = fase2;
                break;
            case 3:
                fase3.setDisable(true);
                faseTemp = fase3;
                break;
            case 4:
                fase4.setDisable(true);
                faseTemp = fase4;
                break;
            case 5:
                fase5.setDisable(true);
                faseTemp = fase5;
                break;
            case 6:
                fase6.setDisable(true);
                faseTemp = fase6;
                break;
            case 7:
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
                break;
            case 2:
                nivel1.setDisable(true);
                break;
            case 3:
                nivel1.setDisable(true);
                break;
        }
    }

    /**
     * Desbloqueia todas as fases bloqueadas
     */
    public void desbloquearFases() {

    }

    /**
     * Enche a barra de tempo e espera o jogador clicar em um botão para
     * continuar o jogo
     */
    public void reiniciarTimer() {
        setBarraTempo(1.0);
        barraTempo.setProgress(1.0);
        timer.cancel();
        iniciarJogo();
    }

    /**
     * Quando o layout é alterado atualiza as fases que estão bloqueadas
     */
    private void atualizarBloqueios() {
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
                }
                break;

            case 2:
                for (Object faseTemp : fasesBloqueadasNivel2) {
                    ((Button) faseTemp).setDisable(true);
                }
                break;
            case 3:
                for (Object faseTemp : fasesBloqueadasNivel3) {
                    ((Button) faseTemp).setDisable(true);
                }
                break;
        }
    }

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
                System.out.println("Bloqueados "+arrayBloqueados.isEmpty());
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
            System.out.println("Vazio");
            return 1;
        }

        for (int j = 1; j <= 7; j++) {
            if (!arrayBloqueados.contains((Integer) j)) {
                faseDisponivel = j;
                System.out.println("Não contem");
                break;
            }
        }
        arrayBloqueados.clear();
        System.out.println("Fase disponivel " + faseDisponivel);
        return faseDisponivel;
    }

}
