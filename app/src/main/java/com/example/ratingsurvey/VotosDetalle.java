package com.example.ratingsurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ratingsurvey.ListsAdapters.VotosListAdapter;
import com.example.ratingsurvey.controllers.VotoController;

public class VotosDetalle extends AppCompatActivity {

    int iconos;
    long id;
    VotoController vc;

    int[] votos;
    String[] dates;

    ListView lista;

    VotosListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votos_detalle);

        lista = (ListView) findViewById(R.id.lista);

        Intent intent = getIntent();
        vc = new VotoController(getApplicationContext());
        iconos = intent.getIntExtra("iconos",-1);
        id = intent.getLongExtra("id_encuesta", -1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getVotos(id);

        adapter = new VotosListAdapter(getApplicationContext(),votos,dates,iconos);
        lista.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void getVotos(long id){
        Cursor res = vc.read(id);
        votos =  new int[res.getCount()];
        dates =  new String[res.getCount()];
        /**res.moveToLast();
        while (res.isAfterLast()){

            res.moveToPrevious();
        }*/
        int index = 0;
        res.moveToFirst();
        while (!res.isAfterLast()){
            votos[index] = res.getInt(1);
            dates[index] = res.getString(2);
            index++;
            res.moveToNext();
        }
        res.close();
        vc.close();


    }
}
