package com.example.ratingsurvey.ListsAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ratingsurvey.DateManagment.DateManagment;
import com.example.ratingsurvey.R;

public class EncuestasListAdapter extends BaseAdapter {

    private String [] nombres;
    private String [] preguntas;
    private String [] fechas;
    private String [] promedio;

    Context context;
    LayoutInflater inflater;

    public EncuestasListAdapter(String[] nombres, String[] preguntas, String[] fechas, String[] promedio, Context context) {
        this.nombres = nombres;
        this.preguntas = preguntas;
        this.fechas = fechas;
        this.promedio = promedio;
        this.context = context;
    }

    @Override
    public int getCount() {
        return nombres.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView txtNombre;
        TextView txtPregunta;
        TextView txtFecha;
        TextView txtPromedio;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.encuesta_list_item, parent, false);

        // Locate the TextViews in listview_item.xml
        txtNombre = (TextView) itemView.findViewById(R.id.txtNombreG);
        txtPregunta = (TextView) itemView.findViewById(R.id.txtPreguntaG);
        txtFecha = (TextView) itemView.findViewById(R.id.txtFechaG);
        txtPromedio = (TextView) itemView.findViewById(R.id.txtPromG);

        // Capture position and set to the TextViews
        txtNombre.setText(nombres[position]);
        txtPregunta.setText(preguntas[position]);
        txtFecha.setText(DateManagment.getStringDate(fechas[position]));
        txtPromedio.setText(promedio[position]+"%");

        return itemView;
    }

}
