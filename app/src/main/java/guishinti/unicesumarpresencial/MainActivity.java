package guishinti.unicesumarpresencial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bt_lab, bt_nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_lab = (Button)findViewById(R.id.bt_lab);
        bt_lab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,LaboratoriosActivity.class);
                startActivity(it);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);
            }
        });

        bt_nota = (Button)findViewById(R.id.bt_nota);
        bt_nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(it);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Intent it = new Intent(MainActivity.this, LaboratoriosActivity.class);
//        startActivity(it);
//        finish();
//    }


}
