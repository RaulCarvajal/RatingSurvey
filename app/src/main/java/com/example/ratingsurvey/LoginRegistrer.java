package com.example.ratingsurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.ratingsurvey.controllers.PerfilController;
import com.example.ratingsurvey.controllers.SesionController;
import com.example.ratingsurvey.models.Perfil;
import com.example.ratingsurvey.models.Sesion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginRegistrer extends AppCompatActivity{


    LinearLayout nombreLay;
    LinearLayout apellidoLay;
    Button nuevo;
    Button entrar;

    EditText correo;
    EditText pin;
    EditText nombre;
    EditText apellido;

    boolean show = false;

    PerfilController pc;
    SesionController sc;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registrer);

        getSupportActionBar().hide();

        pc =  new PerfilController(getApplicationContext());
        sc =  new SesionController(getApplicationContext());

        nombreLay = (LinearLayout) findViewById(R.id.nombresLay);
        apellidoLay = (LinearLayout) findViewById(R.id.apellidosLay);
        nuevo = (Button) findViewById(R.id.nuevo);
        entrar = (Button) findViewById(R.id.entrar);

        correo = (EditText) findViewById(R.id.correo);
        pin = (EditText) findViewById(R.id.pin);
        nombre = (EditText) findViewById(R.id.nombres);
        apellido = (EditText) findViewById(R.id.apellidos);
        sesionExists();
        userExists();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sesionExists();
    }

    public void nuevo(View view){
        show = !show;
        if(show){
            nuevo.setText("Tengo una cuenta");
            entrar.setText("Registrarme y entrar");
            nombreLay.setVisibility(View.VISIBLE);
            apellidoLay.setVisibility(View.VISIBLE);
        }else{
            nuevo.setText("Soy nuevo");
            entrar.setText("Entrar");
            nombreLay.setVisibility(View.INVISIBLE);
            apellidoLay.setVisibility(View.INVISIBLE);
        }
    }

    public void entrar(View view){
        if(!valCorreo(correo.getText().toString())){
            Toast.makeText(this,getString(R.string.toast_correo_valido),Toast.LENGTH_SHORT).show();
        }else{
            if(show){
                if(correoExiste(correo.getText().toString()) && isEmpty(pin.getText().toString(),getString(R.string.toast_pin)) && isEmpty(nombre.getText().toString(),getString(R.string.toast_nombres)) && isEmpty(apellido.getText().toString(),getString(R.string.toast_apellidos))){
                    long p_id = pc.create(new Perfil(nombre.getText().toString(),apellido.getText().toString(),correo.getText().toString(),pin.getText().toString()));
                    long s_id = sc.create(new Sesion(p_id));

                    Intent intent = new Intent(this, Inicio.class);
                    intent.putExtra("ID",p_id);
                    startActivity(intent);
                }
            }else{
                if(tryLogin(correo.getText().toString(),pin.getText().toString())==-1){
                    Toast.makeText(this,getString(R.string.toast_pin_incorrecto),Toast.LENGTH_SHORT).show();
                }else if(tryLogin(correo.getText().toString(),pin.getText().toString())==-2){
                    Toast.makeText(this,R.string.toast_correo_no_registrado,Toast.LENGTH_SHORT).show();
                }else{
                    long id = tryLogin(correo.getText().toString(),pin.getText().toString());
                    long s_id = sc.create(new Sesion(id));
                    Intent intent = new Intent(this, Inicio.class);
                    intent.putExtra("ID",id);
                    startActivity(intent);
                }
            }
        }
    }

    private boolean valCorreo(String correo){
        if(!correo.contains("@") || correo.isEmpty()){
            return false;
        }
        return true;
    }

    private boolean isEmpty(String str, String name) {
        if(str.isEmpty()){
            Toast.makeText(this,getString(R.string.toast_campo)+name+getString(R.string.toast_vacio), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private int numUsers(){
        Cursor res = pc.read();
        int n = res.getCount();
        res.close();
        pc.close();

        return n;
    }

    private int numSession(){
        Cursor res = sc.read();
        int n = res.getCount();
        res.close();
        sc.close();

        return n;
    }

    private void userExists(){
        if(numUsers()==0){
            show = true;
            nuevo.setVisibility(View.INVISIBLE);
            entrar.setText("Registrarme y entrar");
            nombreLay.setVisibility(View.VISIBLE);
            apellidoLay.setVisibility(View.VISIBLE);
        }
    }

    private void sesionExists(){
        if(numSession()!=0){
            Intent intent = new Intent(this, Inicio.class);
            intent.putExtra("ID",getSesionFkId());
            startActivity(intent);
        }
    }

    private long getSesionFkId(){
        Cursor res = sc.read();
        res.moveToFirst();
        long p_id = res.getLong(2);
        res.close();
        sc.close();
        return p_id;
    }

    private boolean correoExiste(String correo){
        if(pc.existeCorreo(correo)){
            pc.close();
            Toast.makeText(this,getString(R.string.toast_el_correo)+correo+getString(R.string.toast_correo_ya_registrado),Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    private long tryLogin(String usr,String pin){
        Cursor res = pc.login(usr);
        pc.close();
        if(res.getCount()==0){
            return -2;
        }else{
            res.moveToFirst();
            long id = res.getLong(0);
            String pass = res.getString(1);
            res.close();

            if(pass.equals(pin)){
                return id;
            }else{
                Log.w("######","id "+ id +" -> "+ pass + "==" + pin);

                return -1;
            }
        }
    }

}
