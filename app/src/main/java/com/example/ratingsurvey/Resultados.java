package com.example.ratingsurvey;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ratingsurvey.controllers.EncuestaController;
import com.example.ratingsurvey.controllers.VotoController;
import com.example.ratingsurvey.models.Encuesta;

import org.json.JSONException;
import org.json.JSONObject;

public class Resultados extends AppCompatActivity {

    int iconos, avg = 0;
    String pregunta, nombre, descripcion, id_busqueda;
    long id, fecha_c, fecha_a, fecha_f;
    Button btnRep;

    int v100 = 0, v75 = 0, v50 = 0, v25 = 0, total = 0;

    VotoController vc;
    EncuestaController ec;

    TextView txtavg, txtv100, txtv75, txtv50, txtv25, po100, po75, po50, po25, ttotal;
    ProgressBar pb100, pb75, pb50, pb25;
    ImageView img100, img75, img50, img25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vc = new VotoController(getApplicationContext());
        ec = new EncuestaController(getApplicationContext());

        txtavg = (TextView) findViewById(R.id.avg);
        txtv100 = (TextView) findViewById(R.id.v100);
        txtv75= (TextView) findViewById(R.id.v75);
        txtv50 = (TextView) findViewById(R.id.v50);
        txtv25 = (TextView) findViewById(R.id.v25);
        po100 = (TextView) findViewById(R.id.po100);
        po75 = (TextView) findViewById(R.id.po75);
        po50 = (TextView) findViewById(R.id.po50);
        po25 = (TextView) findViewById(R.id.po25);
        ttotal = (TextView) findViewById(R.id.total);
        pb100 = (ProgressBar) findViewById(R.id.pb100);
        pb75 = (ProgressBar) findViewById(R.id.pb75);
        pb50 = (ProgressBar) findViewById(R.id.pb50);
        pb25 = (ProgressBar) findViewById(R.id.pb25);
        img100 = (ImageView) findViewById(R.id.img100);
        img75 = (ImageView) findViewById(R.id.img75);
        img50 = (ImageView) findViewById(R.id.img50);
        img25 = (ImageView) findViewById(R.id.img25);
        btnRep =  (Button) findViewById(R.id.button3);


        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        descripcion = intent.getStringExtra("descripcion");
        pregunta = intent.getStringExtra("pregunta");
        id_busqueda = intent.getStringExtra("id_busqueda");
        iconos = intent.getIntExtra("iconos",-1);
        id = intent.getLongExtra("id",-1);
        avg = (int) intent.getFloatExtra("avg",-1);
        fecha_c = intent.getLongExtra("fecha_c",-1);
        fecha_a = intent.getLongExtra("fecha_a",-1);
        fecha_f = intent.getLongExtra("fecha_f",-1);


        txtavg.setText(avg+"%");

        if(!id_busqueda.equals("")){
            btnRep.setText(getText(R.string.actualizar_reporte));
        }

        getVotos(id);

        //Toast.makeText(getApplicationContext(),"100 - "+v100+"\n75 - "+v75+"\n50 - "+v50+"\n25 - "+v25+"\nT - "+total, Toast.LENGTH_LONG).show();
        setTxtView();
        setImageIcons(iconos);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void setImageIcons(int type){
        switch (type){
            case 3:
                img100.setImageResource(R.drawable.corazones4);
                img75.setImageResource(R.drawable.corazones3);
                img50.setImageResource(R.drawable.corazones2);
                img25.setImageResource(R.drawable.corazones1);
                break;
            case 2:
                img100.setImageResource(R.drawable.estrellas4);
                img75.setImageResource(R.drawable.estrellas3);
                img50.setImageResource(R.drawable.estrellas2);
                img25.setImageResource(R.drawable.estrellas1);
                break;
            case 4:
                img100.setImageResource(R.drawable.cuatro_ban);
                img75.setImageResource(R.drawable.tres_ban);
                img50.setImageResource(R.drawable.dos_ban);
                img25.setImageResource(R.drawable.una_ban);
                break;

        }
    }

    public void getVotos(long id){
        Cursor res = vc.read(id);
        res.moveToFirst();
        while (!res.isAfterLast()){
            int voto = res.getInt(1);
            addVotos(voto);
            total++;
            res.moveToNext();
        }
        res.close();
        vc.close();
    }

    public void addVotos(int voto){
        switch ( voto ){
            case 100:
                v100++;
                break;
            case 75:
                v75++;
                break;
            case 50:
                v50++;
                break;
            case 25:
                v25++;
                break;
        }
    }

    public void setTxtView(){
        txtv100.setText(v100+"");
        txtv75.setText(v75+"");
        txtv50.setText(v50+"");
        txtv25.setText(v25+"");

        double p100 = (v100*100)/total;
        double p75 = (v75*100)/total;
        double p50 = (v50*100)/total;
        double p25 = (v25*100)/total;


        pb100.setProgress((int)Math.ceil(p100));
        pb75.setProgress((int)Math.ceil(p75));
        pb50.setProgress((int)Math.ceil(p50));
        pb25.setProgress((int)Math.ceil(p25));

        po100.setText((int)Math.ceil(p100)+"%");
        po75.setText((int)Math.ceil(p75)+"%");
        po50.setText((int)Math.ceil(p50)+"%");
        po25.setText((int)Math.ceil(p25)+"%");

        ttotal.setText(total+"");

        //Toast.makeText(getApplicationContext(),"v100 - "+v100+" total - "+total+" - "+p100, Toast.LENGTH_LONG).show();
    }

    public void gotoDetalle(View view){
        Intent intent =  new Intent(this, VotosDetalle.class);
        intent.putExtra("iconos",iconos);
        intent.putExtra("id_encuesta",id);
        startActivity(intent);
    }

    public void getReporte(View view){
        JSONObject data = new JSONObject();
        JSONObject fechas = new JSONObject();
        JSONObject votos = new JSONObject();
        try {
            data.put("nombre",nombre);
            data.put("descripcion",descripcion);
            data.put("mensaje",pregunta);
            data.put("tipo_iconos",iconos);
            fechas.put("creacion",fecha_c);
            fechas.put("actualizacion",fecha_a);
            fechas.put("fin",fecha_f);
            data.put("fechas",fechas);
            votos.put("promedio",avg);
            votos.put("v100",Integer.parseInt(txtv100.getText().toString().split("%")[0]));
            votos.put("v75",Integer.parseInt(txtv75.getText().toString().split("%")[0]));
            votos.put("v50",Integer.parseInt(txtv50.getText().toString().split("%")[0]));
            votos.put("v25",Integer.parseInt(txtv25.getText().toString().split("%")[0]));
            data.put("votos", votos);
            if(id_busqueda.equals("")){
                guardar(data);
            }else{
                data.put("id_busqueda", id_busqueda);
                actualizar(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //Metodo http POST
    private String link = "http://18.189.206.61:56/encuesta/";
    private RequestQueue requestQueue;

    public void guardar(JSONObject data){
        String endpoint = link+"guardar";
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endpoint, data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ec.updateIdBusqueda(response.getJSONObject("detail").getString("id_busqueda"),id);
                            ec.close();
                            alertDialog(response.getJSONObject("detail").getString("id_busqueda"));
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(request);
    }

    public void actualizar(JSONObject data){
        String endpoint = link+"actualizar";
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, endpoint, data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        alertDialog(id_busqueda);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(request);
    }

    public void alertDialog(String id){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle(getText(R.string.exito))
                .setMessage(
                        getString(R.string.alert_report1)+" "+id+"\n"+getString(R.string.alert_report2)
                )
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which){

                    }
                })
                .show();
    }

}
