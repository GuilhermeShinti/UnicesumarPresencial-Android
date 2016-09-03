package br.com.guis.unicesumaronline;

import android.widget.ListView;

import java.util.ArrayList;

public class Lab extends Bloco{

    private String laboratorio;
    private String horarioUm;
    private String horarioDois;
    static LabAdapter adapter;
    static ArrayList<Lab> array = new ArrayList<>();

    public Lab(String bloco, String laboratorio, String primeiro, String segundo) {
        super(bloco);
        this.bloco = bloco;
        this.laboratorio = laboratorio;
        this.horarioUm = primeiro;
        this.horarioDois = segundo;
    }

    public Lab() {
        super();
    }

    public String getBloco() {
        return bloco;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public String getHorarioUm() {
        return horarioUm;
    }

    public String getHorarioDois() {
        return horarioDois;
    }

    public static void setList(LaboratoriosActivity context, ListView lv_labs){
        adapter = new LabAdapter(context, array);
        adapter.notifyDataSetChanged();
        lv_labs.setAdapter(adapter);
    }


}
