package guishinti.unicesumarpresencial;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.apache.http.client.methods.HttpGet;

public class NotasActivity extends AppCompatActivity {
    
    Button bt_refresh;
    ImageView iv_refresh;
    ListView lv_disciplinas;
    RelativeLayout rl_telaErro, rl_telaLoading;
    TextView tv_aluno, tv_curso;

    public void error() {
        lv_disciplinas.setVisibility(View.INVISIBLE);
        rl_telaLoading.setVisibility(View.INVISIBLE);
        rl_telaErro.setVisibility(View.VISIBLE);
    }

    public void loading() {
        if (Conexao.verificaConexao(getBaseContext())) {
            Disciplina.array.clear();
            Disciplina.notas.clear();
            lv_disciplinas.setVisibility(View.INVISIBLE);
            rl_telaErro.setVisibility(View.INVISIBLE);
            rl_telaLoading.setVisibility(View.VISIBLE);
            new AsyncDataClass().execute();
            return;
        }
        error();
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        tv_aluno = (TextView)findViewById(R.id.tv_aluno);
        tv_curso = (TextView)findViewById(R.id.tv_curso);
        iv_refresh = (ImageView)findViewById(R.id.iv_refresh);
        iv_refresh.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                loading();
            }
        });
        lv_disciplinas = (ListView)findViewById(R.id.lv_disciplinas);
        rl_telaLoading = (RelativeLayout)findViewById(R.id.rl_loading);
        rl_telaErro = (RelativeLayout)findViewById(R.id.rl_erro);
        bt_refresh = (Button)rl_telaErro.findViewById(R.id.bt_refresh);
        bt_refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loading();
            }
        });
        loading();
    }

    private class AsyncDataClass extends AsyncTask<String, Void, String> {

        Pagina html;
        Conexao con = new Conexao();
        private String host = "https://aluno.unicesumar.edu.br";
        private String notas = "/lyceump/aonline/notas_freq.asp";

        protected String doInBackground(String... paramVarArgs) {
            HttpGet url = new HttpGet(host + notas);

            html = new Pagina(con.get(url, "ISO-8859-1"));
            if (html.paginaExpirada()) {
                System.out.println("Expirado!");
                con.Login(LoginActivity.prefs.getString("ra", null), LoginActivity.prefs.getString("senha", null));
                html = new Pagina(con.get(url, "ISO-8859-1"));
            }
            System.out.println(LoginActivity.prefs.getString("ra", null) + "  " + LoginActivity.prefs.getString("senha", null));
            return html.toString();
        }

        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            System.out.println("Resulted Value: " + result);
            if (this.html.paginaExpirada()) {
                System.out.println("Expirado!");
            }
            if ((result.equals("")) || (result == null)) {
                System.out.println("Server connection failed");
                return;
            } else{
                tv_aluno.setText(html.getAluno());
                tv_curso.setText(html.getCursoAluno());
                PaginaNotas.getDisciplinas();
                Disciplina.setList(NotasActivity.this, lv_disciplinas);
                rl_telaLoading.setVisibility(View.INVISIBLE);
                rl_telaErro.setVisibility(View.INVISIBLE);
                lv_disciplinas.setVisibility(View.VISIBLE);
            }

        }

    }
}
