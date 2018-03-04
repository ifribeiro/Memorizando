package model;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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
        "um", "ur", "va", "ve", "vi", "vo", "vu", "xa", "xe", "xi", "xo", "za", "ze", "zi"};
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
    private Button botaoFaseAnterior;
    @FXML
    private Button botaoProximaFase;
    @FXML
    private ProgressBar barraTempo;

    private Timer timer;
    private Media media;
    private String botao1, botao2;
    private Button btemp1, btemp2;
    private ArrayList novasOpcoes;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView = new MediaView();
    private String ArrayNivel1[] = new String[16];
    private boolean tocando = false, gameOver, timerIniciado;
    private ArrayList arrayBotoes = new ArrayList<String>();
    private int acerto, erro, fase, nivel, cliquesTotais, cliques;
    private EventHandler<ActionEvent> evento1Botao, evento2Botao;

    public ModelPrincipal(Button b1, Button b2, Button b3, Button b4, Button b5,
            Button b6, Button b7, Button b8, Button faseAnterior, Button proximaFase,
            ProgressBar barraTempo) {
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.b5 = b5;
        this.b6 = b6;
        this.b7 = b7;
        this.b8 = b8;
        this.erro = 0;
        this.fase = 1;
        this.nivel = 1;
        this.acerto = 0;
        this.cliques = 0;
        this.botao1 = "";
        this.botao2 = "";
        this.gameOver = false;
        this.timerIniciado = false;
        this.cliquesTotais = 0;
        this.barraTempo = barraTempo;
        this.botaoProximaFase = proximaFase;
        this.botaoFaseAnterior = faseAnterior;
    }

    public void verificarOpcao(ActionEvent evento) {
        if (primeiroClique() && !getTimerIniciado()) {
            System.out.println("Primeiro clique");
            iniciarTimer();
            setTimerIniciado(true);
        }

        //if (!isTocando()) {
        String nomeBotao = ((Button) evento.getSource()).getId();
        cliques++;
        //iniciar timer
        //verificar em qual fase está        
        if (cliques == 1) {
            tocarAudioBotao(evento);
            //tocar audio do botao clicado
            tocarAudioBotao(evento);
            btemp1 = ((Button) evento.getSource());
            botao1 = ArrayNivel1[Integer.parseInt(nomeBotao.substring(1)) - 1];

        } else if (cliques == 2) {
            btemp2 = ((Button) evento.getSource());
            botao2 = ArrayNivel1[Integer.parseInt(nomeBotao.substring(1)) - 1];

            if (botao1.equals(botao2) && (!btemp1.equals(btemp2))) {

                incrementarAcerto();
                evento1Botao = (ActionEvent event) -> {
                    setCorBotao(btemp2, "#00EE00");
                    setCorBotao(btemp1, "#00EE00");
                    tocarAudioBotao(evento);
                    //setTocando(true);
                };
                evento2Botao = (ActionEvent event) -> {
                    btemp1.setVisible(false);
                    btemp2.setVisible(false);
                    setCorBotao(btemp1, "#00000");
                    setCorBotao(btemp2, "#00000");
                    verificarTerminoNivel();
                };
                new Timeline(
                        new KeyFrame(Duration.seconds(0), evento1Botao),
                        new KeyFrame(Duration.seconds(0.5), evento2Botao)).play();
                //deixar os dois botoes invisiveis
                //aumentar um pouco o tempo de acordo a fase 

            } else {
                tocarAudioBotao(evento);
                incrementarErro();
            }

            //se tiver errado
            //"desvira" os dois audios
            //incrementa erros
            this.cliquesTotais = cliquesTotais + cliques;
            System.out.println("Cliques totais: " + cliquesTotais);
            cliques = 0;
        }
        //}

        //quando não houver mais botoes 
        //gerar novas opcoes
    }

    /**
     * Muda para o nível de um dos 3 botões de que foi clicado
     *
     * @param event
     */
    public void alterarNivel(ActionEvent event) {
        String nomeBotao = ((Button) event.getSource()).getId();
        switch (nomeBotao) {
            case "nivel1":
                System.out.println("Nivel1");
                setNivel(1);
                break;
            case "nivel2":
                System.out.println("Nivel2");
                setNivel(2);
                gerarOpcoes(fase);
                break;
            case "nivel3":
                System.out.println("Nivel3");
                setNivel(3);
                break;
        }
        //mudar fxml
    }

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
        System.out.println(numeroFonemas);
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
        System.out.println(Arrays.toString(ArrayNivel1));
        System.out.println("Novas opcoes " + novasOpcoes);
    }

    public void iniciarJogo() {
        gerarOpcoes(getFase());
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public int getFase() {
        return this.fase;
    }

    public void setNivel(int i) {
        this.nivel = i;
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
                gerarOpcoes(fase);
                exibirBotoes(getNivel());
                this.fase = fase + 1;
                botaoProximaFase.setText(fase + "");
            }

        }
        System.out.println("FAse " + fase);

    }

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
    }

    private void tocarAudioBotao(ActionEvent evento) {
        String nomeBotao = ((Button) evento.getSource()).getId();
        int posicaoAudio = Integer.parseInt(nomeBotao.substring(1));
        String audio = ArrayNivel1[posicaoAudio - 1];
        System.out.println("Audio " + audio);
        String caminhoAudio = "";
        switch (getFase()) {
            case 1:
                caminhoAudio = "_audios/audios_vogais/" + audio + ".mp3";
                break;
            case 2:
                caminhoAudio = "_audios/audios_silabasSimples/" + audio + ".mp3";
                break;
            case 3:
                caminhoAudio = "src/model/_audios/audios_palavrasSimples/" + audio;
                break;
            case 4:
                caminhoAudio = "src/model/_audios/audios_silabasComplexas1/" + audio;
                break;
            case 5:
                caminhoAudio = "src/model/_audios/audios_silabasComplexas2/" + audio;
                break;
            case 6:
                caminhoAudio = "src/model/_audios/audios_silabasComplexas3/" + audio;
                break;
            case 7:
                caminhoAudio = "src/model/_audios/audios_palavrasComplexas/" + audio;
                break;
        }
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

    public void exibirBotoes(int nivel) {
        switch (nivel) {
            case 1:
                b1.setVisible(true);
                b2.setVisible(true);
                b3.setVisible(true);
                b4.setVisible(true);
                b5.setVisible(true);
                b6.setVisible(true);
                b7.setVisible(true);
                b8.setVisible(true);
                break;
            case 2:
                b1.setVisible(true);
                b2.setVisible(true);
                b3.setVisible(true);
                b4.setVisible(true);
                b5.setVisible(true);
                b6.setVisible(true);
                b7.setVisible(true);
                b8.setVisible(true);
                break;
            case 3:
                break;

        }
    }

    public void iniciarTimer() {
        timer = new Timer();
        //criação da tarefa que vai executar durante 1 segundo
        timer.scheduleAtFixedRate(new TimerTask() {

            double i = 1.0;

            @Override
            public void run() {
                System.out.println(i);
                //Platform.runLater para alterar elementos da interface do javaFX
                Platform.runLater(() -> {
                    if (!isGameOver()) {
                        i = i - 0.016;
                        setBarraTempo(i);
                    }
                    System.out.println("i " + i);

                    if (i < 0) {
                        System.out.println("Game over :(");
                        setGameOver(true);
                        timer.cancel();
                        setBarraTempo(0.0);
                    }
                });
            }
        }, 0, 1000);
    }

    public void setBarraTempo(Double tempo) {

        if (tempo < 0.9) {
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

    private boolean primeiroClique() {
        return this.cliquesTotais == 0;
    }

    public void setGameOver(boolean valor) {
        this.gameOver = valor;
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public void setCorBotao(Button botao, String cor) {
        botao.setStyle("-fx-background-color: " + cor);
    }

    public void setTimerIniciado(boolean b) {
        this.timerIniciado = b;
    }

    private boolean getTimerIniciado() {
        return this.timerIniciado;
    }

    private void verificarTerminoNivel() {

        switch (getNivel()) {
            case 1:
                if (getAcertos() == 4) {
                    exibirBotoes(1);
                    gerarOpcoes(getFase());
                    setAcerto(0);
                    mediaPlayer.dispose();
                }

                break;
            case 2:
                if (getAcertos() == 5) {
                    exibirBotoes(1);
                }
                break;
            case 3:
                if (getAcertos() == 8) {
                    exibirBotoes(1);
                }
                break;
        }

    }

}
