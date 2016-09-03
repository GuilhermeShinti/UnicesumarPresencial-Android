package br.com.guis.unicesumaronline;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by Guilherme Shinti on 15/08/2016.
 */
public class PaginaFreq extends Pagina {

    PaginaFreq(String html) {
        super(html);
    }

    public static String getFreq() {

        Document doc = Jsoup.parse(html.toString());
        return "";
    }
}
