package com.example.ratingsurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratingsurvey.DateManagment.DateManagment;
import com.example.ratingsurvey.controllers.EncuestaController;
import com.example.ratingsurvey.models.Encuesta;

public class VerEncuesta extends AppCompatActivity {

    EncuestaController ec;
    Encuesta encuesta;

    TextView nombre,descr, pregun, tipoico, created, updated, lastvoto, promedio,id_busqueda, textDescargar;

    protected int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_encuesta);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ec = new EncuestaController(getApplicationContext());
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        nombre = (TextView) findViewById(R.id.txtNombreG);
        descr = (TextView) findViewById(R.id.txtDesc);
        pregun = (TextView) findViewById(R.id.txtPreguntaG);
        tipoico = (TextView) findViewById(R.id.txtTipoIco);
        created = (TextView) findViewById(R.id.txtFechaCreacion);
        updated = (TextView) findViewById(R.id.txtFechaActualizacion);
        lastvoto = (TextView) findViewById(R.id.txtFechaUltimoVoto);
        promedio = (TextView) findViewById(R.id.txtPromedio);
        id_busqueda = (TextView) findViewById(R.id.txtIdEncuesta);
        textDescargar = (TextView) findViewById(R.id.descargar);

        getEncuestaAndSetValues();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void getEncuestaAndSetValues(){
        encuesta = getEncuesta(id);

        nombre.setText(encuesta.getNombre());
        descr.setText(encuesta.getDescripcion());
        pregun.setText(encuesta.getMensaje());
        tipoico.setText(getTipoIconos(encuesta.getType_icons()));
        created.setText(DateManagment.getStringDate(encuesta.getCreate_date()+""));
        updated.setText(sameFecha(encuesta.getCreate_date()+"",encuesta.getUpdate_date()+""));
        lastvoto.setText(sameFecha(encuesta.getCreate_date()+"",encuesta.getEnd_date()+""));
        promedio.setText(encuesta.getAverage_mark()+"/100");
        if(encuesta.getId_busqueda().equals("")){
            id_busqueda.setText(getText(R.string.no_reportes));

        }else{
            id_busqueda.setText(encuesta.getId_busqueda());
        }
    }

    public Encuesta getEncuesta(long id ){

        Cursor res = ec.readOne(id+1);
        Encuesta encuesta = new Encuesta(
                res.getLong(0),
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getInt(4),
                res.getLong(5),
                res.getLong(6),
                res.getLong(7),
                res.getLong(8),
                res.getFloat(9),
                res.getLong(10),
                res.getString(11));
        res.close();
        ec.close();
        return encuesta;
    }

    public String getTipoIconos(int i){
        switch (i){
            case 1:
                return getString(R.string.faces);
            case 2:
                return getString(R.string.star);
            case 3:
                return getString(R.string.heart);
            case 4:
                return getString(R.string.coins);

        }
        return "";
    }

    public String sameFecha(String fecha1, String fecha2){
        if(!fecha1.equals(fecha2)){
            return DateManagment.getStringDateHour(fecha2);
        }
        return getString(R.string.aun_no_se_registran);
    }

    public void voteCapture(View view){
        Intent intent =  new Intent(this, CapturaVotosFS.class);
        intent.putExtra("pregunta",encuesta.getMensaje());
        intent.putExtra("iconos",encuesta.getType_icons());
        intent.putExtra("id",encuesta.getId_encuesta());
        intent.putExtra("avg",encuesta.getAverage_mark());
        startActivity(intent);
    }

    public void verResultados(View view){
        if(encuesta.getAverage_mark() == 0.0){
            Toast.makeText(getApplicationContext(),getText(R.string.mensaje_sin_votos), Toast.LENGTH_LONG).show();
        }else{
            Intent intent =  new Intent(this, Resultados.class);
            intent.putExtra("nombre",encuesta.getNombre());
            intent.putExtra("descripcion",encuesta.getDescripcion());
            intent.putExtra("pregunta",encuesta.getMensaje());
            intent.putExtra("iconos",encuesta.getType_icons());
            intent.putExtra("id",encuesta.getId_encuesta());
            intent.putExtra("avg",encuesta.getAverage_mark());
            intent.putExtra("fecha_c",encuesta.getCreate_date());
            intent.putExtra("fecha_a",encuesta.getUpdate_date());
            intent.putExtra("fecha_f",encuesta.getEnd_date());
            intent.putExtra("id_busqueda",encuesta.getId_busqueda());
            startActivity(intent);
        }
    }

    public void irActualizar(View view){
       Intent intent =  new Intent(this, EditarEncuesta.class);
        intent.putExtra("nombre",encuesta.getNombre());
        intent.putExtra("descripcion",encuesta.getDescripcion());
        intent.putExtra("pregunta",encuesta.getMensaje());
        intent.putExtra("iconos",encuesta.getType_icons());
        intent.putExtra("id",encuesta.getId_encuesta());
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        getEncuestaAndSetValues();
    }

    public void descargar(View view){
        if(encuesta.getId_busqueda().equals("")){
            Toast.makeText(getApplicationContext(), getText(R.string.no_reportes),Toast.LENGTH_LONG).show();
        }else{
            Uri uri = Uri.parse("http://18.189.206.61:55/search/"+encuesta.getId_busqueda()+"/true");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }
    public void compartir(View view){
        String uri = "http://18.189.206.61:55/search/"+encuesta.getId_busqueda()+"/true";
        Intent compartir = new Intent(android.content.Intent.ACTION_SEND);
        compartir.setType("text/plain");
        String mensaje = "Te comparto los resultados de mi encuesta "+encuesta.getNombre()+", esta disponible en "+uri;
        compartir.putExtra(android.content.Intent.EXTRA_SUBJECT, "Empleos Baja App");
        compartir.putExtra(android.content.Intent.EXTRA_TEXT, mensaje);
        startActivity(Intent.createChooser(compartir, "Compartir vía"));
    }
}
