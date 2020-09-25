package com.example.ratingsurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ratingsurvey.controllers.EncuestaController;
import com.example.ratingsurvey.controllers.SesionController;
import com.example.ratingsurvey.models.Encuesta;
import com.google.android.material.snackbar.Snackbar;

public class NuevaEncuesta extends AppCompatActivity {

    EditText nombre;
    EditText descripcion;
    EditText pregunta;

    RadioButton rb_caras;
    RadioButton rb_estrellas;
    RadioButton rb_corazones;
    RadioButton rb_monedas;

    RadioGroup rg_iconos;

    SesionController sc;
    EncuestaController ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_encuesta);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sc = new SesionController(getApplicationContext());
        ec = new EncuestaController(getApplicationContext());

        nombre = (EditText) findViewById(R.id.txt_nombre);
        descripcion = (EditText) findViewById(R.id.txt_descripcion);
        pregunta = (EditText) findViewById(R.id.txt_pregunta);
        rb_caras = (RadioButton) findViewById(R.id.rb_caras);
        rb_corazones = (RadioButton) findViewById(R.id.rb_corazones);
        rb_estrellas = (RadioButton) findViewById(R.id.rb_estrellas);
        rb_monedas = (RadioButton) findViewById(R.id.rb_monedas);
        rg_iconos = (RadioGroup) findViewById(R.id.rg_iconos);



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void showValues(View view){

        if(isEmpty(nombre.getText().toString(),"nombre") &&
           isEmpty(descripcion.getText().toString(),"descripción") &&
           isEmpty(pregunta.getText().toString(),"pregunta") &&
           isEmpty(getRadioGroupValue()+"","tipo de icono")){

            Encuesta encuesta;
            String cad = "";

            encuesta = new Encuesta(nombre.getText().toString(),descripcion.getText().toString(),pregunta.getText().toString(),getRadioGroupValue(),getIdPerfil());

            cad = encuesta.getNombre()+"\n";
            cad += encuesta.getDescripcion()+"\n";
            cad += encuesta.getMensaje()+"\n";
            cad += encuesta.getType_icons()+"\n";
            cad += encuesta.getFk_perfil()+"\n";

            //Toast.makeText(getApplicationContext(),cad,Toast.LENGTH_LONG).show();

            guardar(encuesta,view);
        }
    }

    private int getRadioGroupValue(){
        if(rb_caras.isChecked()){
            return 1;
        }else if(rb_estrellas.isChecked()){
            return 2;
        }else if(rb_corazones.isChecked()){
            return 3;
        }else if(rb_monedas.isChecked()){
            return 4;
        }
        return -1;
    }

    public void guardar(Encuesta encuesta, View view){
        try{
            ec.create(encuesta);
            Toast.makeText(getApplicationContext(), getString(R.string.toast_guardar), Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),getString(R.string.toast_error),Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(getApplicationContext(),Inicio.class);
        startActivity(intent);

    }

    private boolean isEmpty(String o, String name){
        if(o.isEmpty() || o.equals("-1")){
            Toast.makeText(getApplicationContext(),getString(R.string.toast_campo)+name+getString(R.string.toast_vacio),Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    public long getIdPerfil(){
        Cursor c = sc.read();
        c.moveToFirst();
        long fk_id = c.getLong(2);
        c.close();
        sc.close();

        return fk_id;
    }
}
