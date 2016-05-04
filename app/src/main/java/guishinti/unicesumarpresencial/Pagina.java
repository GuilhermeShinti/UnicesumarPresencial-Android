package guishinti.unicesumarpresencial;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Pagina {
    protected static String html;
    protected Document document;

    Pagina(String html) {
        this.html = html;
        document = Jsoup.parse(html);
    }

    //verificar retorno de erro cesumar
//    public boolean isAcessoPermitido(){
//        return document.getElementsByAttributeValueContaining("name", "ErroSistema").isEmpty();
//    }

    @Override
    public String toString() {
        return html;
    }
}
