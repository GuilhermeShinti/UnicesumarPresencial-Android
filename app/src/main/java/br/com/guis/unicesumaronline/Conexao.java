package br.com.guis.unicesumaronline;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Conexao {
    public static HttpClient httpclient = null;
    private static HttpResponse response;

    Conexao() {
        if (httpclient == null) {
            httpclient = new DefaultHttpClient();
        }
    }

    public String Login(String raValue, String passValue) {
        String line = "";
        StringBuilder sb = new StringBuilder();
        final HttpPost httppost = new HttpPost("https://aluno.unicesumar.edu.br/lyceump/aonline/middle_logon.asp");
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
        nameValuePairs.add(new BasicNameValuePair("txtnumero_matricula", raValue));
        nameValuePairs.add(new BasicNameValuePair("txtsenha_tac", passValue));
        nameValuePairs.add(new BasicNameValuePair("cmdEnviar", "    OK    "));


        try {
            int hardTimeout = 20; // seconds
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (httppost != null) {
                        httppost.abort();
                    }
                }
            };
            new Timer(true).schedule(task, hardTimeout * 1000);

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);

            Charset inputCharset = Charset.forName("ISO-8859-1");

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), inputCharset));
            try {
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("TOTAL:" + sb);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String get(final HttpGet url, String charset) {
        String line = "";
        StringBuilder sb = new StringBuilder();
        try
        {
            int hardTimeout = 20; // seconds
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (url != null) {
                        url.abort();
                    }
                }
            };
            new Timer(true).schedule(task, hardTimeout * 1000);

            response = httpclient.execute(url);
            Charset inputCharset = Charset.forName(charset);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), inputCharset));
            try {
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("TOTAL:" + sb);

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static boolean verificaConexao(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null) && (activeNetworkInfo.isConnected());
    }

}
