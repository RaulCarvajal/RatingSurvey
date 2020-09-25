package com.example.ratingsurvey.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ratingsurvey.CapturaVotos;
import com.example.ratingsurvey.DateManagment.DateManagment;
import com.example.ratingsurvey.R;
import com.example.ratingsurvey.controllers.EncuestaController;
import com.example.ratingsurvey.controllers.SesionController;
import com.example.ratingsurvey.controllers.VotoController;
import com.example.ratingsurvey.models.Encuesta;
import com.example.ratingsurvey.ui.slideshow.SlideshowFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private TextView encuestas;

    private EncuestaController ec;
    private SesionController sc;

    private Encuesta[] encuestas_;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ec = new EncuestaController(getContext());
        sc = new SesionController(getContext());

        encuestas = root.findViewById(R.id.txtNumEnc);

        getEncuestas();

        int nenc = getNumeroEncuestas();

        encuestas.setText(nenc+"");

        AdView adView = (AdView) root.findViewById(R.id.adView2);
        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        return root;
    }

    public int getNumeroEncuestas(){
        return encuestas_.length;
    }

    public long getIdPerfil(){
        Cursor c = sc.read();
        c.moveToFirst();
        long fk_id = c.getLong(2);
        c.close();
        sc.close();

        return fk_id;
    }

    private void getEncuestas(){
        Cursor c = ec.readByClient(getIdPerfil());
        c.moveToFirst();
        encuestas_ = new Encuesta[c.getCount()];
        int index = 0;
        while (!c.isAfterLast()){
            encuestas_[index] = new Encuesta(
                    c.getLong(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getInt(4),
                    c.getLong(6),
                    c.getLong(5),
                    c.getLong(7),
                    c.getLong(8),
                    c.getFloat(9),
                    c.getLong(10),
                    c.getString(11));
            index++;
            c.moveToNext();
        }

        c.close();
        ec.close();
    }
}