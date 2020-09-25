package com.example.ratingsurvey.ListsAdapters;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ratingsurvey.DateManagment.DateManagment;
import com.example.ratingsurvey.R;

public class VotosListAdapter extends BaseAdapter {

    private int votos[], iconos;
    private String fechas[];
    private Context context;
    private LayoutInflater inflater;
    ImageView img_voto;

    public  VotosListAdapter(Context context, int[] votos, String[] fechas, int iconos){
        this.context = context;
        this.votos = votos;
        this.iconos = iconos;
        this.fechas = fechas;
    }

    @Override
    public int getCount() {
        return votos.length;
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

        TextView txt_voto, txt_date;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.voto_list_item, parent, false);

        txt_voto = (TextView) itemView.findViewById(R.id.txt_voto);
        txt_date = (TextView) itemView.findViewById(R.id.txt_date);
        img_voto = (ImageView) itemView.findViewById(R.id.img_voto);

        txt_voto.setText(votos[position]+"");
        txt_date.setText(DateManagment.getStringDate(fechas[position]));
        setImagen(votos[position]);


        return itemView;
    }

    public void setImagen(int voto){
        switch (iconos){
            case 1://Caras
                setCaras(voto);
                break;
            case 2://Estrellas
                setEstrellas(voto);
                break;
            case 3://Corazones
                setCorazones(voto);
                break;
            case 4://Monedas
                setMonedas(voto);
                break;
        }
    }

    public void setCaras(int voto){
        switch (voto){
            case 25:
                img_voto.setImageResource(R.drawable.angry_f);
                break;
            case 50:
                img_voto.setImageResource(R.drawable.sad_f);
                break;
            case 75:
                img_voto.setImageResource(R.drawable.neutral_f);
                break;
            case 100:
                img_voto.setImageResource(R.drawable.happy_f);
                break;
        }
    }
    public void setEstrellas(int voto){
        switch (voto){
            case 25:
                img_voto.setImageResource(R.drawable.estrellas1);
                break;
            case 50:
                img_voto.setImageResource(R.drawable.estrellas2);
                break;
            case 75:
                img_voto.setImageResource(R.drawable.estrellas3);
                break;
            case 100:
                img_voto.setImageResource(R.drawable.estrellas4);
                break;
        }
    }
    public void setCorazones(int voto){
        switch (voto){
            case 25:
                img_voto.setImageResource(R.drawable.corazones1);
                break;
            case 50:
                img_voto.setImageResource(R.drawable.corazones2);
                break;
            case 75:
                img_voto.setImageResource(R.drawable.corazones3);
                break;
            case 100:
                img_voto.setImageResource(R.drawable.corazones4);
                break;
        }
    }
    public void setMonedas(int voto){
        switch (voto){
            case 25:
                img_voto.setImageResource(R.drawable.una_ban);
                break;
            case 50:
                img_voto.setImageResource(R.drawable.dos_ban);
                break;
            case 75:
                img_voto.setImageResource(R.drawable.tres_ban);
                break;
            case 100:
                img_voto.setImageResource(R.drawable.cuatro_ban);
                break;
        }
    }

}
