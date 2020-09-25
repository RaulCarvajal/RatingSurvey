package com.example.ratingsurvey;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratingsurvey.controllers.EncuestaController;
import com.example.ratingsurvey.controllers.VotoController;
import com.example.ratingsurvey.models.Encuesta;
import com.example.ratingsurvey.models.Voto;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.snackbar.Snackbar;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CapturaVotosFS extends AppCompatActivity {

    /**
     VARIABLES ENCUESTA
     **/
    TextView txtPregunta;

    ImageButton uno,dos,tres,cuatro;

    String pregunta ;
    int iconos, voto = 0, votos = 0, count = 1, exitCount = 0;

    long id;

    VotoController vc;
    EncuestaController ec;
    Encuesta encuesta;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_votos_fs);
        getSupportActionBar().hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Intent intent = getIntent();
        pregunta = intent.getStringExtra("pregunta");
        iconos = intent.getIntExtra("iconos",-1);
        id = intent.getLongExtra("id",-1);
        votos = (int) intent.getFloatExtra("avg",-1);

        if(votos == 0){
            count = 0;
        }

        txtPregunta = (TextView) findViewById(R.id.txtPreguntaG);
        uno = (ImageButton) findViewById(R.id.btnuno);
        dos = (ImageButton) findViewById(R.id.btndos);
        tres = (ImageButton) findViewById(R.id.btntres);
        cuatro = (ImageButton) findViewById(R.id.btncuatro);

        vc = new VotoController(getApplicationContext());
        ec = new EncuestaController(getApplicationContext());

        Toast.makeText(getApplicationContext(),getString(R.string.mensaje_salir),Toast.LENGTH_LONG).show();

        txtPregunta.setText(pregunta);

        typeIcons(iconos);

        AdView adView = (AdView) findViewById(R.id.adView4);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }


    public void typeIcons(int type){
        switch (type){
            case 2:
                uno.setImageResource(R.drawable.nf_star);
                dos.setImageResource(R.drawable.nf_star);
                tres.setImageResource(R.drawable.nf_star);
                cuatro.setImageResource(R.drawable.nf_star);
                break;
            case 3:
                uno.setImageResource(R.drawable.nf_heart);
                dos.setImageResource(R.drawable.nf_heart);
                tres.setImageResource(R.drawable.nf_heart);
                cuatro.setImageResource(R.drawable.nf_heart);
                break;
            case 4:
                uno.setImageResource(R.drawable.una);
                dos.setImageResource(R.drawable.dos);
                tres.setImageResource(R.drawable.tres);
                cuatro.setImageResource(R.drawable.cuatro);
                break;
        }
    }

    public void eventUno(View view){
        switch (iconos){
            case 2:
                uno.setImageResource(R.drawable.f_star);
                break;
            case 3:
                uno.setImageResource(R.drawable.f_heart);
                break;
        }
        restartButtons();
        voto = 25;
        saveVoto();
    }

    public void eventDos(View view){
        switch (iconos){
            case 2:
                uno.setImageResource(R.drawable.f_star);
                dos.setImageResource(R.drawable.f_star);
                break;
            case 3:
                uno.setImageResource(R.drawable.f_heart);
                dos.setImageResource(R.drawable.f_heart);
                break;
        }
        restartButtons();
        voto = 50;
        saveVoto();
    }

    public void eventTres(View view){
        switch (iconos){
            case 2:
                uno.setImageResource(R.drawable.f_star);
                dos.setImageResource(R.drawable.f_star);
                tres.setImageResource(R.drawable.f_star);
                break;
            case 3:
                uno.setImageResource(R.drawable.f_heart);
                dos.setImageResource(R.drawable.f_heart);
                tres.setImageResource(R.drawable.f_heart);
                break;
        }
        restartButtons();
        voto = 75;
        saveVoto();
    }

    public void eventCuatro(View view){
        switch (iconos){
            case 2:
                uno.setImageResource(R.drawable.f_star);
                dos.setImageResource(R.drawable.f_star);
                tres.setImageResource(R.drawable.f_star);
                cuatro.setImageResource(R.drawable.f_star);
                break;
            case 3:
                uno.setImageResource(R.drawable.f_heart);
                dos.setImageResource(R.drawable.f_heart);
                tres.setImageResource(R.drawable.f_heart);
                cuatro.setImageResource(R.drawable.f_heart);
                break;
        }
        restartButtons();
        voto = 100;
        saveVoto();
    }

    public void snack(long id){
        View view = findViewById(R.id.fullscreen_content);
        Snackbar.make(view, count+ getString(R.string.snack_gracias), Snackbar.LENGTH_SHORT).show();
    }

    public void restartButtons(){
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        switch (iconos){
                            case 2:
                                uno.setImageResource(R.drawable.nf_star);
                                dos.setImageResource(R.drawable.nf_star);
                                tres.setImageResource(R.drawable.nf_star);
                                cuatro.setImageResource(R.drawable.nf_star);
                                break;
                            case 3:
                                uno.setImageResource(R.drawable.nf_heart);
                                dos.setImageResource(R.drawable.nf_heart);
                                tres.setImageResource(R.drawable.nf_heart);
                                cuatro.setImageResource(R.drawable.nf_heart);
                                break;

                        }
                    }
                }, 2000);
    }

    public void saveVoto(){
        count++;
        votos += voto;

        long idv = vc.create(new Voto(voto,(int)id));

        voto = 0;

        snack(idv);

        vc.close();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if( exitCount > 1 ){
                if(exitCount == 3){
                    return alertDialog();
                }
                Toast.makeText(getApplicationContext(),getString(R.string.toast_atras),Toast.LENGTH_LONG).show();
            }
            exitCount ++;
        }
        //para las demas cosas, se reenvia el evento al listener habitual
        super.onKeyDown(keyCode, event);
        return false;
    }

    public void actualizarEncuesta(long id, float prom){
        ec.updatePromedio(prom,id);
        ec.close();
    }

    public boolean alertDialog(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getString(R.string.salir))
                .setMessage(getString(R.string.salir_pregunta))
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        //Salir
                        exitCount++;
                    }
                })//sin listener
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        //Salir
                        //Toast.makeText(getApplicationContext(),"Id - "+id+"\nCount - "+count+"\nVotos - "+votos+"\nPromedio - "+(votos/count),Toast.LENGTH_LONG).show();
                        actualizarEncuesta(id, (votos/count));
                        CapturaVotosFS.this.finish();
                    }
                })
                .show();

        // Si el listener devuelve true, significa que el evento esta procesado, y nadie debe hacer nada mas
        return true;
    }
}
