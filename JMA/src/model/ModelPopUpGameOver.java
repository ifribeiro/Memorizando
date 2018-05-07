/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author iran
 */
public class ModelPopUpGameOver {

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
    private Label nome3;
    @FXML
    private Label nome4;
    @FXML
    private Label nome5;
    @FXML
    private Label nome6;
    @FXML
    private Label nome7;
    @FXML
    private Label nome8;
    @FXML
    private Label nome9;
    @FXML
    private Label nome10;
    @FXML
    private Label pontos2;
    @FXML
    private Label pontos3;
    @FXML
    private Label pontos4;
    @FXML
    private Label pontos5;
    @FXML
    private Label pontos6;
    @FXML
    private Label pontos7;
    @FXML
    private Label pontos8;
    @FXML
    private Label pontos9;
    @FXML
    private Label pontos10;

    private BufferedReader br;
    private ArrayList listaRanking = new ArrayList();
    public ModelPopUpGameOver(ImageView img1, ImageView img2, ImageView img3,
            ImageView img4, ImageView img5, ImageView img6, ImageView img7,
            ImageView img8, ImageView img9, ImageView img10, Label nome1,
            Label nome2, Label nome3, Label nome4, Label nome5, Label nome6,
            Label nome7, Label nome8, Label nome9, Label nome10, Label pontos1,
            Label pontos2, Label pontos3, Label pontos4, Label pontos5, Label pontos6,
            Label pontos7, Label pontos8, Label pontos9, Label pontos10) {
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.img5 = img5;
        this.img6 = img6;
        this.img7 = img7;
        this.img8 = img8;
        this.img9 = img9;
        this.img10 = img10;

        this.nome1 = nome1;
        this.nome2 = nome2;
        this.nome3 = nome3;
        this.nome4 = nome4;
        this.nome5 = nome5;
        this.nome6 = nome6;
        this.nome7 = nome7;
        this.nome8 = nome8;
        this.nome9 = nome9;
        this.nome10 = nome10;

        this.pontos1 = pontos1;
        this.pontos2 = pontos2;
        this.pontos3 = pontos3;
        this.pontos4 = pontos4;
        this.pontos5 = pontos5;
        this.pontos6 = pontos6;
        this.pontos7 = pontos7;
        this.pontos8 = pontos8;
        this.pontos9 = pontos9;
        this.pontos10 = pontos10;

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

        ArrayList idNomesJogador = new ArrayList();
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
            br = new BufferedReader(new FileReader("ranking.txt"));
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
        Collections.sort(listaOriginal, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                String[] s1 = (String[]) o1;
                String[] s2 = (String[]) o2;
                return Integer.parseInt(s1[2]) > Integer.parseInt(s2[2]) ? -1 : (Integer.parseInt(s2[2]) > Integer.parseInt(s1[2]) ? +1 : 0);
            }
        });  
        
        return listaOriginal;
    }

}
