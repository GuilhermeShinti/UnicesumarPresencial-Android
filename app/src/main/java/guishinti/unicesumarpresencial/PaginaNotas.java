package guishinti.unicesumarpresencial;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Guilherme Shinti on 01/05/2016.
 */
public class PaginaNotas extends Pagina {

    static Elements linhasDisciplina;

    PaginaNotas(String html) {
        super(html);
    }

    public static void getDisciplinas(){

        Document doc = Jsoup.parse(html.toString());

        Element tableNota = doc.getElementsByTag("table").get(3);
        linhasDisciplina = tableNota.select("tr");

        for (int i=1;i<linhasDisciplina.size();i++){
            String disciplina = linhasDisciplina.get(i).select("td").get(1).text();
            Disciplina.array.add(new Disciplina(disciplina, getNotas(i)));
        }
    }

    public static ArrayList getNotas(int i){
        ArrayList notas = new ArrayList();
        Document doc = Jsoup.parse(html);
        Element tableNota=doc.getElementsByTag("table").get(3);
//            Elements colInfo = tableNota.select("tr").get(0).select("td");
        Elements numCol=tableNota.select("tr").get(i).select("td");

        for (int j=2;j<numCol.size()-1;j++){
            String nota = numCol.get(j).text().toString();
//                String info = colInfo.get(j).text().toString().toUpperCase();

                notas.add(nota);
        }

        return notas;
    }
}
