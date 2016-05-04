package guishinti.unicesumarpresencial;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import org.apache.http.client.methods.HttpGet;

public class LaboratoriosActivity extends AppCompatActivity {

    RelativeLayout rl_telaLoading;
    RelativeLayout rl_telaErro;
    ProgressBar progressBar;
    Button bt_refresh;
    Spinner sp_turno;
    ListView lv_labs;
    String turnoParam = "";
    Boolean sp_status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorios);

        rl_telaLoading = (RelativeLayout) findViewById(R.id.rl_telaLoading);
        rl_telaErro = (RelativeLayout) findViewById(R.id.rl_telaErro);
        progressBar = (ProgressBar) findViewById(R.id.pb_loading);
        bt_refresh = (Button) findViewById(R.id.bt_refresh);
        bt_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading();
            }
        });

        sp_turno = (Spinner) findViewById(R.id.sp_turno);
        lv_labs = (ListView) findViewById(R.id.lv_labs);

        Turno.populaSpinner(sp_turno,this);
        Turno.getPeriodo(sp_turno);

        sp_turno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sp_status == true) {
                    sp_status = false;
                    turnoParam = Turno.getParamTurnos(sp_turno.getSelectedItemPosition());
                    loading();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        loading();
    }

    public void loading(){
        if (Conexao.verificaConexao(this)){
            Lab.array.clear();
            lv_labs.setVisibility(View.INVISIBLE);
            rl_telaErro.setVisibility(View.INVISIBLE);
            rl_telaLoading.setVisibility(View.VISIBLE);
            new AsyncDataClass().execute();
        }else{
            error();
        }
    }

    public void error(){
        lv_labs.setVisibility(View.INVISIBLE);
        rl_telaLoading.setVisibility(View.INVISIBLE);
        rl_telaErro.setVisibility(View.VISIBLE);
    }

    private class AsyncDataClass extends AsyncTask<String, Void, String> {

        Pagina html;
        Conexao con = new Conexao();
        private String host = "http://www.unicesumar.edu.br";
        private String labs = "/informaticav2/horario.php?dados=";

        @Override
        protected String doInBackground(String... params) {

            System.out.println(host+labs+Turno.getDiaAtual()+turnoParam);
            HttpGet url= new HttpGet(host+labs+Turno.getDiaAtual()+turnoParam);

            html = new Pagina(con.get(url));
            return html.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("Resulted Value: " + result);
            if(result.equals("") || result == null){
                sp_status = true;
                System.out.println("Server connection failed");
                error();
            }else{
                sp_status = true;
                PaginaLab.getPaginaLabs();
                Lab.setList(LaboratoriosActivity.this,lv_labs);
                rl_telaLoading.setVisibility(View.INVISIBLE);
                rl_telaErro.setVisibility(View.INVISIBLE);
                lv_labs.setVisibility(View.VISIBLE);
            }

        }

    }

}
