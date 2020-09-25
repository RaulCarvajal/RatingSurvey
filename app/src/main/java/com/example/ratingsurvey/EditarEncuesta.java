package com.example.ratingsurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ratingsurvey.controllers.EncuestaController;
import com.example.ratingsurvey.controllers.SesionController;
import com.example.ratingsurvey.models.Encuesta;

public class EditarEncuesta extends AppCompatActivity {

    EditText nombre, descripcion, pregunta;

    RadioButton rb_caras, rb_estrellas, rb_corazones, rb_monedas;

    RadioGroup rg_iconos;

    SesionController sc;
    EncuestaController ec;

    long id_encuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_encuesta);

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

        Intent intent = getIntent();

        nombre.setText(intent.getStringExtra("nombre"));
        descripcion.setText(intent.getStringExtra("descripcion"));
        pregunta.setText(intent.getStringExtra("pregunta"));
        setTipoIconos(intent.getIntExtra("iconos",-1));
        id_encuesta = intent.getLongExtra("id",-1);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void setTipoIconos(int iconos){
        switch (iconos){
            case 1:
                rb_caras.setChecked(true);
                break;
            case 2:
                rb_estrellas.setChecked(true);
                break;
            case 3:
                rb_corazones.setChecked(true);
                break;
            case 4:
                rb_monedas.setChecked(true);
                break;
        }
    }

    private boolean isEmpty(String o, String name){
        if(o.isEmpty() || o.equals("-1")){
            Toast.makeText(getApplicationContext(),getString(R.string.toast_campo)+name+getString(R.string.toast_vacio),Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    private int getRadioGroupValue(){
        if(rb_caras.isChecked()){
            return 1;
        }else if(rb_estrellas.isChecked()){
            return 2;
        }else if(rb_corazones.isChecked()){
            return 3;
        }
        return -1;
    }

    public void showValues(View view){

        if(isEmpty(nombre.getText().toString(),"nombre") &&
                isEmpty(descripcion.getText().toString(),"descripción") &&
                isEmpty(pregunta.getText().toString(),"pregunta") &&
                isEmpty(getRadioGroupValue()+"","tipo de icono")){

            Encuesta encuesta;

            encuesta = new Encuesta(id_encuesta, nombre.getText().toString(), descripcion.getText().toString(), pregunta.getText().toString(), getRadioGroupValue());

            guardar(encuesta,view);
        }
    }

    public void guardar(Encuesta encuesta, View view){
        try{
            ec.update(encuesta);
            Toast.makeText(getApplicationContext(), getString(R.string.toast_editar), Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),getString(R.string.toast_error),Toast.LENGTH_SHORT).show();
        }

        EditarEncuesta.this.finish();

    }
}
