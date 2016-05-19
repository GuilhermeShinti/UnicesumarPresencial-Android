package guishinti.unicesumarpresencial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    static SharedPreferences prefs;
    Button bt_entrar, bt_load;
    EditText et_pass, et_ra;
    TextView tv_erro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_ra = (EditText)findViewById(R.id.et_ra);
        et_pass = (EditText)findViewById(R.id.et_pass);
        tv_erro = (TextView)findViewById(R.id.tv_erro);
        bt_load = (Button)findViewById(R.id.bt_load);
        bt_entrar = (Button)findViewById(R.id.bt_entrar);
        bt_entrar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                new AsyncDataClass().execute();
            }
        });
        prefs = getSharedPreferences("config", MODE_PRIVATE);
    }

    private boolean isPresencial() {
        int tamanho = et_ra.getText().length();
        String str = et_ra.getText().toString();
        str.replace("-", "");
        str = str.substring(tamanho - 1, tamanho);
        return (Integer.parseInt(str) != 5) && (Integer.parseInt(str) != 4);
    }

    private class AsyncDataClass extends AsyncTask<String, Void, String> {

        Conexao con = new Conexao();
        Pagina html;
        String pass;
        String ra;
        String resposta = "";

        private boolean isCamposValidos() {
            return !et_ra.getText().toString().trim().equals("") && !et_pass.getText().toString().trim().equals("");
        }

        protected void onPreExecute(){
            super.onPreExecute();
            bt_entrar.setVisibility(View.INVISIBLE);
            bt_load.setVisibility(View.VISIBLE);
            ra = et_ra.getText().toString();
            pass = et_pass.getText().toString();
        }

        protected String doInBackground(String... paramVarArgs) {
            if (isCamposValidos()) {
                if (isPresencial()) {
                    html = new Pagina(con.Login(ra, pass));
                    resposta = html.toString();
                }else{
                    resposta = "Apenas Presencial!";
                }
            }else{
                resposta = "Campos Inválidos!";
            }
            return resposta;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if ((result.equals("")) || (result == null)) {
                System.out.println("Server connection failed");
                tv_erro.setText("Verifique sua Conexãoo!");
                tv_erro.setVisibility(View.VISIBLE);
            }else{
                if (resposta.equals("Campos Inválidos!")) {
                    tv_erro.setText(resposta);
                    tv_erro.setVisibility(View.VISIBLE);
                }
                else if (resposta.equals("Apenas Presencial!")) {
                    tv_erro.setText(resposta);
                    tv_erro.setVisibility(View.VISIBLE);
                }
                else if (html.senhaIncorreta()) {
                    System.out.println("Senha Incorreta");
                    tv_erro.setText("Senha Incorreta!");
                    tv_erro.setVisibility(View.VISIBLE);
                }
                else if (html.naoExiste()) {
                    System.out.println("RA ou Senha incorreta");
                    tv_erro.setText("RA não existe!");
                    tv_erro.setVisibility(View.VISIBLE);
                }
                else {
                    tv_erro.setVisibility(View.INVISIBLE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("ra", ra);
                    editor.putString("senha", pass);
//                    editor.putBoolean("logado", true);
                    editor.commit();
                    Intent it = new Intent(LoginActivity.this, NotasActivity.class);
                    startActivity(it);
                    overridePendingTransition(R.anim.right_in,R.anim.left_out);
                }
            }
            bt_load.setVisibility(View.INVISIBLE);
            bt_entrar.setVisibility(View.VISIBLE);
        }
    }
}
