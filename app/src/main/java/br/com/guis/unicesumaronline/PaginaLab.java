package br.com.guis.unicesumaronline;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PaginaLab extends Pagina{

    static String aula;
    static Element labo;

    PaginaLab(String html){
        super(html);
    }

    public static void getPaginaLabs(){

        Document doc = Jsoup.parse(html.toString());
        Elements blocos = doc.select("table[class=bloco]");

        for (int i = 0; i < blocos.size(); i++){

            Element bloco = blocos.get(i);
            String nBloco = bloco.select("tr").get(0).select("th").get(0).html();

            Elements labs = bloco.select("table[class=tableReserva");
            for (int j = 0; j < labs.size();j++){

                labo = labs.get(j);
                String nLab = labo.select("tr").get(0).select("td").html();
                Lab.array.add(new Lab(nBloco, nLab, aula(1), aula(2)));

            }

        }
    }

    public static String aula(int pos){

        if (labo.select("tr").get(pos).select("div[class=reserva ]").select("span").hasClass("disponivel")) {
            aula = labo.select("tr").get(pos).select("div[class=reserva ]").select("span").text();
        }else {
            aula = labo.select("tr").get(pos).select("div[class=reserva ]").text();
        }
        return aula;

    }
}
