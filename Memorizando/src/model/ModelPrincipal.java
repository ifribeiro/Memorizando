package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 *
 * @author iran
 */
public class ModelPrincipal {

    private int cliques;
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
    
    private ArrayList arrayBotoes = new ArrayList<String>();
    private ArrayList novasOpcoes;
    private String ArrayNivel1[] = new String[16];
    private int nivel;
    private int fase;

    public ModelPrincipal() {
        this.cliques = 0;        
        this.nivel = 1;
        this.fase = 2;
    }

    public void verificarOpcao(ActionEvent evento) {
        cliques++;
        //iniciar timer
        //verificar em qual fase está
        tocarAudioBotao(evento);
        if (cliques == 1) {
            //tocar audio do botao clicado
            System.out.println("Clicou uma vez");

        } else if (cliques == 2) {
            System.out.println("Clicou duas vezes");
            //tocar audio do botao clicado
            //se tiver certo
            //computar acerto
            //deixar os dois botoes invisiveis
            //aumentar um pouco o tempo

            //se tiver errado
            //"desvira" os dois audios
            //incrementa erros
            cliques = 0;
        }

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

    public void tocarAudioBotao(ActionEvent evento) {

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
                System.out.println("OOOOOOOO");
                numeroFonemas = 5;
                break;
            case 3:
                numeroFonemas = 8;
                break;
        }
        System.out.println(numeroFonemas);

        switch (getFase()) {
            case 1:
                numeroFonemasVetores = 5;
                break;
            case 2:
                numeroFonemasVetores = 95;
                break;

        }

        while (i < numeroFonemas) {
            System.out.println("Entrpiasfasd ");
            proxValor = indice.nextInt(numeroFonemasVetores);//o valor do next int corresponde a quantidade de fonemas 
            if (!indicesUtilizados.contains(proxValor)) {//se o índice ainda não foi utilizado
                novasOpcoes.add(proxValor);//adiciona o indice no array
                indicesUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                i++;
            }
        }

        while (contador < numeroFonemas * 2) {
            System.out.println("Entrpi aaasfj");
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

    private void setNivel(int i) {
        this.nivel = i;
    }
    
    public int getNivel(){
        return this.nivel;
    }
    
    public String getFonemaArray(int fase, int posicaoVetor){
        String fonema = "";
        switch(fase){
            case 1:
                fonema = arrayVogais[(int) novasOpcoes.get(posicaoVetor)];
                break;
            case 2:
                fonema = arraySilabasSimples[(int) novasOpcoes.get(posicaoVetor)];
                break;
        }
        
        return fonema;
    }
}
