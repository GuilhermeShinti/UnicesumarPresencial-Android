package guishinti.unicesumarpresencial;

import android.widget.ListView;
import java.util.ArrayList;

public class Disciplina
{
    static DisciplinasAdapter adapter;
    static ArrayList<Disciplina> array = new ArrayList();
    static ArrayList<ArrayList> notas = new ArrayList();
    String nome;

    public Disciplina(String nome, ArrayList arrayNotas) {
        this.nome = nome;
        notas.add(arrayNotas);
    }

    public static void setList(NotasActivity context, ListView lv_disciplina) {
        adapter = new DisciplinasAdapter(context, array);
        adapter.notifyDataSetChanged();
        lv_disciplina.setAdapter(adapter);
    }

    public String getNome() {
        return this.nome;
    }
}