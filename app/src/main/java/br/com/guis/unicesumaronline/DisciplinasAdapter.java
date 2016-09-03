package br.com.guis.unicesumaronline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class DisciplinasAdapter extends ArrayAdapter<Disciplina> {
    public DisciplinasAdapter(Context context, ArrayList<Disciplina> arrayDisciplinas) {
        super(context, 0, arrayDisciplinas);
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Disciplina disciplina = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.disciplinacard, null);
            ((TextView)convertView.findViewById(R.id.tv_disciplina)).setText(disciplina.getNome());
            ((TextView)convertView.findViewById(R.id.tv_bim1)).setText(Disciplina.notas.get(position).get(0).toString());
            ((TextView)convertView.findViewById(R.id.tv_bim2)).setText(Disciplina.notas.get(position).get(1).toString());
            ((TextView)convertView.findViewById(R.id.tv_s1b1)).setText(Disciplina.notas.get(position).get(2).toString());
            ((TextView)convertView.findViewById(R.id.tv_s1b2)).setText(Disciplina.notas.get(position).get(3).toString());
            ((TextView)convertView.findViewById(R.id.tv_bim3)).setText(Disciplina.notas.get(position).get(4).toString());
            ((TextView)convertView.findViewById(R.id.tv_bim4)).setText(Disciplina.notas.get(position).get(5).toString());
            ((TextView)convertView.findViewById(R.id.tv_s2b3)).setText(Disciplina.notas.get(position).get(6).toString());
            ((TextView)convertView.findViewById(R.id.tv_s2b4)).setText(Disciplina.notas.get(position).get(7).toString());
        }
        return convertView;
    }
}