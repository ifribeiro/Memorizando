package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.event.ActionEvent;

/**
 *
 * @author iran
 */
public class ModelPrincipal {

    private int cliques;
    private String arrayVogais[];
    private ArrayList arrayBotoes = new ArrayList<String>();
    private ArrayList novasOpcoes;
    private String ArrayNivel1[]= new String[8];
    private int nivel;
    private int fase;

    public ModelPrincipal() {
        this.cliques = 0;
        this.arrayVogais = new String[]{"a", "e", "i", "o", "u"};
        this.nivel = 1;
        this.fase = 1;
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
        //mudar fxml
    }

    public void tocarAudioBotao(ActionEvent evento) {

    }

    public void gerarOpcoes(int fase) {
        int i = 0;
        int proxValor = 0;
        int numeroFonemas = 0;
        int contador = 0;
        novasOpcoes = new ArrayList(); //recebe os índices para as novas opções do array correspondente à fase
        ArrayList indicesUtilizados = new ArrayList();//array que receberá os índices que já foram utilizados ????
        ArrayList indicesFonemasUtilizados = new ArrayList();
        Random indice = new Random();//gerador de índices aleatorios

        switch (nivel) {
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
        switch (fase) {
            case 1:
                while (i < numeroFonemas) {
                    proxValor = indice.nextInt(5);
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
                        ArrayNivel1[posicao1] = arrayVogais[(int)novasOpcoes.get(j)];
                        ArrayNivel1[posicao2] = arrayVogais[(int)novasOpcoes.get(j)];                        
                        contador = contador + 2;
                    }
                    
                    
                }
                System.out.println(Arrays.toString(ArrayNivel1));
                
                break;
            case 2:
                break;

        }
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

}
