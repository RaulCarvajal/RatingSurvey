package com.example.ratingsurvey;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.ratingsurvey.controllers.PerfilController;
import com.example.ratingsurvey.controllers.SesionController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

public class Inicio extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    public TextView nav_name, nav_email;
    public SesionController sc;
    public PerfilController pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                startActivity(new Intent(getApplicationContext(), NuevaEncuesta.class));
            }
        });

        //Sesión controller
        sc = new SesionController(this);
        pc = new PerfilController(this);

        //Contenedor
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        //Menú
        NavigationView navigationView = findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        nav_name =  (TextView) view.findViewById(R.id.nav_name);
        nav_email =  (TextView) view.findViewById(R.id.nav_email);

        nav_name.setText(getNameString());
        nav_email.setText(getEmailString());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.nav_tools,
                R.id.nav_share,
                R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();

        //Fragment donde se insertará el contenido
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
/**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio, menu);
        return true;
    }
*/
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public String getNameString(){
        Cursor res = pc.readOne(getSessionId());
        String name = res.getString(1);
        res.close();
        pc.close();
        return name;
    }

    public String getEmailString(){
        Cursor res = pc.readOne(getSessionId());
        String name = res.getString(3);
        res.close();
        pc.close();
        return name;
    }

    public long getSessionId(){
        Cursor res = sc.read();
        res.moveToFirst();
        long id = Long.parseLong(res.getInt(2)+"");
        res.close();
        sc.close();

        return id;
    }

}
