package guishinti.unicesumarpresencial;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Turno {

    static String dataHoje;
    private static String[] paramTurno = {"M","T","N"};
    static String[] turnos = {"Manhã", "Tarde", "Noite"};

    public static String getParamTurnos(Integer num){
        String param = "";
        try {
            param = URLEncoder.encode("|"+paramTurno[num],"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return param;
    }

    public static void getPeriodo(Spinner sp_turno){

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        try {
            Date Manha = sdf.parse("12:00:00");
            Date Noite = sdf.parse("18:00:00");
            String hAtual = sdf.format(c.getTime());
            Date hora = sdf.parse(hAtual);

            if (hora.before(Manha)) {
                sp_turno.setSelection(0);
            }
            if (hora.after(Manha) && hora.before(Noite)) {
                sp_turno.setSelection(1);
            }
            if (hora.after(Noite)) {
                sp_turno.setSelection(2);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getDiaAtual(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
             return URLEncoder.encode(df.format(c.getTime()), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }

    }

    public static void populaSpinner(Spinner sp_turno, Context context) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context,
                R.layout.spinner_item, Turno.turnos);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_turno.setAdapter(dataAdapter);

    }

}
