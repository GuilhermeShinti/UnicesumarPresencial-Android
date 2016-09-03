package br.com.guis.unicesumaronline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LabAdapter extends ArrayAdapter<Lab> {

    public LabAdapter(Context context, ArrayList<Lab> labs) {
        super(context, 0, labs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Lab lab = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.labcard, parent, false);
        }

        TextView tv_lab = (TextView)convertView.findViewById(R.id.tv_lab);
        TextView tv_bloco = (TextView)convertView.findViewById(R.id.tv_bloco);
        TextView tv_primeiro = (TextView)convertView.findViewById(R.id.tv_primeiro);
        TextView tv_segundo = (TextView)convertView.findViewById(R.id.tv_segundo);
        tv_lab.setText(lab.getLaboratorio());
        tv_bloco.setText(lab.getBloco());
        tv_primeiro.setText(String.valueOf(lab.getHorarioUm()));
        tv_segundo.setText(String.valueOf(lab.getHorarioDois()));

        return convertView;
    }
}
