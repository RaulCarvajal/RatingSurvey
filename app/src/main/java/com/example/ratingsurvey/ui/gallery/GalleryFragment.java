package com.example.ratingsurvey.ui.gallery;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ratingsurvey.Inicio;
import com.example.ratingsurvey.ListsAdapters.EncuestasListAdapter;
import com.example.ratingsurvey.ListsAdapters.VacioListAdapter;
import com.example.ratingsurvey.MainActivity;
import com.example.ratingsurvey.R;
import com.example.ratingsurvey.VerEncuesta;
import com.example.ratingsurvey.controllers.EncuestaController;
import com.example.ratingsurvey.controllers.SesionController;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class GalleryFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private GalleryViewModel galleryViewModel;

    private EncuestaController ec;
    private SesionController sc;

    private EncuestasListAdapter listAdapter;
    private VacioListAdapter vacioAdapter;
    private ListView lista;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        ec = new EncuestaController(getContext());
        sc = new SesionController(getContext());

        lista = root.findViewById(R.id.listViewEncuestas);
        setArraysLists();

        lista.setOnItemClickListener(this);

        AdView adView = (AdView) root.findViewById(R.id.adView3);
        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(getContext(),"Id - "+i+" Id - "+l,Toast.LENGTH_LONG).show();
        if(getNumeroEncuestas()>0){
            Intent intent =  new Intent(getContext(), VerEncuesta.class);
            intent.putExtra("id",i);
            startActivity(intent);
        }
    }


    private void setArraysLists(){
        int n = getNumeroEncuestas();
        String [] nombres = new String[n];
        String [] pregunta = new String[n];
        String [] fechas = new String[n];
        String [] promedio = new String[n];

        if(n>0){
            int index = 0;

            Cursor res = ec.readByClient(getIdPerfil());
            res.moveToFirst();
            while (!res.isAfterLast()){
                nombres[index] = res.getString(1);
                pregunta[index] = res.getString(3);
                fechas[index] = res.getString(5);
                promedio[index] = res.getInt(9)+"";

                res.moveToNext();
                index++;
            }
            res.close();
            ec.close();

            listAdapter = new EncuestasListAdapter(nombres,pregunta,fechas,promedio,getContext());
            lista.setAdapter(listAdapter);
        }else{
            vacioAdapter = new VacioListAdapter(getContext());
            lista.setAdapter(vacioAdapter);
        }
    }

    public int getNumeroEncuestas(){
        Cursor c = ec.readByClient(getIdPerfil());
        c.moveToFirst();
        int count = c.getCount();
        c.close();
        ec.close();

        return count;
    }

    public long getIdPerfil(){
        Cursor c = sc.read();
        c.moveToFirst();
        long fk_id = c.getLong(2);
        c.close();
        sc.close();

        return fk_id;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResume(){
        super.onResume();
        setArraysLists();
    }
}