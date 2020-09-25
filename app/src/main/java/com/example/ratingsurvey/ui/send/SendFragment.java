package com.example.ratingsurvey.ui.send;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ratingsurvey.DateManagment.DateManagment;
import com.example.ratingsurvey.Inicio;
import com.example.ratingsurvey.R;
import com.example.ratingsurvey.controllers.PerfilController;
import com.example.ratingsurvey.controllers.SesionController;
import com.example.ratingsurvey.models.Perfil;
import com.example.ratingsurvey.models.Sesion;

public class SendFragment extends Fragment{

    private SendViewModel sendViewModel;

    EditText correo, pin, nombre, apellido, fecha_creacion, fecha_actualizacion;

    PerfilController pc;
    SesionController sc;

    Button actualizar;

    Perfil perfil;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel = ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);

        pc =  new PerfilController(getContext());
        sc =  new SesionController(getContext());

        correo = (EditText) root.findViewById(R.id.correo);
        pin = (EditText) root.findViewById(R.id.pin);
        nombre = (EditText) root.findViewById(R.id.nombres);
        apellido = (EditText) root.findViewById(R.id.apellidos);
        fecha_creacion = (EditText) root.findViewById(R.id.fecha_creacion);
        fecha_actualizacion = (EditText) root.findViewById(R.id.fecha_actualización);

        actualizar = (Button) root.findViewById(R.id.btn_actualizar);

        disableEditText(fecha_actualizacion);
        disableEditText(fecha_creacion);

        actualizar.setOnClickListener(
            new View.OnClickListener() {
                public void onClick(View v) {
                    actualizar();
                }
            }
        );

        perfil = getPerfil();

        correo.setText(perfil.getCorreo());
        pin.setText(perfil.getPassword());
        nombre.setText(perfil.getNombres());
        apellido.setText(perfil.getApellidos());
        fecha_creacion.setText(DateManagment.getStringDate(perfil.getCreate_date()+""));
        fecha_actualizacion.setText(sameFecha( perfil.getCreate_date()+"", perfil.getUpdate_date()+"" ));

        return root;
    }

    private long getSesionFkId(){
        Cursor res = sc.read();
        res.moveToFirst();
        long p_id = res.getLong(2);
        res.close();
        sc.close();
        return p_id;
    }

    private boolean valCorreo(String correo){
        if(!correo.contains("@") || correo.isEmpty()){
            Toast.makeText(getContext(),getString(R.string.toast_correo_valido), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isEmpty(String str, String name) {
        if(str.isEmpty()){
            Toast.makeText(getContext(), getString(R.string.toast_campo)+" "+name+" "+getString(R.string.toast_vacio), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void actualizar(){
        if(
                valCorreo(correo.getText().toString()) &&
                isEmpty(pin.getText().toString(),getString(R.string.toast_pin)) &&
                isEmpty(nombre.getText().toString(),getString(R.string.toast_nombres)) &&
                isEmpty(apellido.getText().toString(),getString(R.string.toast_apellidos))
        ){
            perfil.setCorreo(correo.getText().toString());
            perfil.setPassword(pin.getText().toString());
            perfil.setApellidos(apellido.getText().toString());
            perfil.setNombres(nombre.getText().toString());

            pc.update(perfil);
            pc.close();

            Toast.makeText(getContext(), getString(R.string.toast_editar), Toast.LENGTH_LONG).show();
        }
    }

    public Perfil getPerfil(){
        Cursor res = pc.readOne(getSesionFkId());
        res.moveToFirst();
        Perfil perfil = new Perfil(
                res.getLong(0),
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getLong(5),
                res.getLong(6)
        );
        res.close();
        pc.close();
        return perfil;
    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
    }

    public String sameFecha(String fecha1, String fecha2){
        if(!fecha1.equals(fecha2)){
            return DateManagment.getStringDateHour(fecha2);
        }
        return getString(R.string.aun_no_se_registran);
    }
}