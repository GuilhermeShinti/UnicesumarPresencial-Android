package br.com.guis.unicesumaronline;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Pagina {
    protected static String html;
    protected Document document;

    Pagina(String html) {
        this.html = html;
        document = Jsoup.parse(html);
    }

//    public boolean isAcessoPermitido(){
//        return document.getElementsByAttributeValueContaining("name", "ErroSistema").isEmpty();
//    }

    public String getAluno() {
        return document.getElementsByTag("table").get(2).select("tr").select("td").get(1).text();
    }

    public String getCursoAluno() {
        return document.getElementsByTag("table").get(2).select("tr").select("td").get(0).text();
    }

    public boolean naoExiste() {
        return document.select("script").toString().equals("<script language=\"JavaScript\">self.alert('Seu n?mero de matr?cula n?o existe ou foi digitado incorretamente ou aluno n?o est? ativo.');self.location.href = \"logon.asp\";</script>");
    }

    public boolean paginaExpirada() {
        return document.select("script").toString().equals("<script language=\"JavaScript\">alert('Seu tempo expirou. Por favor entre novamente');location.href='logon.asp';</script>");
    }

    public boolean senhaIncorreta() {
        return document.select("script").toString().equals("<script language=\"JavaScript\">self.alert('Senha incorreta, tente novamente!');self.location.href = \"logon.asp\";</script>");
    }


    @Override
    public String toString() {
        return html;
    }
}
