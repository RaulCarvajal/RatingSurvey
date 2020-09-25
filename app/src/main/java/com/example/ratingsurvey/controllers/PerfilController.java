package com.example.ratingsurvey.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.ratingsurvey.db.dbHelper;
import com.example.ratingsurvey.dbconsts.perfilesConst;
import com.example.ratingsurvey.models.Perfil;

import java.util.Date;

public class PerfilController {

    final Context context;
    dbHelper dbh;
    SQLiteDatabase db;

    public PerfilController(Context cxt) {
        this.context = cxt;
        this.dbh = new dbHelper(cxt);
    }

    private void open() throws SQLException {
        db = dbh.getReadableDatabase();
    }

    public void close(){
        dbh.close();
    }

    public long create(Perfil perfil){

        ContentValues args = new ContentValues();
        args.put(perfilesConst.NOMBRES, perfil.getNombres());
        args.put(perfilesConst.APELLIDOS, perfil.getApellidos());
        args.put(perfilesConst.CORREO, perfil.getCorreo());
        args.put(perfilesConst.PASSWORD, perfil.getPassword());
        args.put(perfilesConst.CREATE_DATE, new Date().getTime());
        args.put(perfilesConst.UPDATE_DATE, new Date().getTime());

        open();
        long ret = db.insert(perfilesConst.TN_PERFILES,null,args);
        return ret;
    }

    public Cursor read(){
       open();
       Cursor res = db.query(perfilesConst.TN_PERFILES,
                    new String[]{perfilesConst.ID_PERFIL, perfilesConst.NOMBRES, perfilesConst.APELLIDOS, perfilesConst.CORREO, perfilesConst.PASSWORD, perfilesConst.CREATE_DATE, perfilesConst.UPDATE_DATE},
                    null,
                    null,
                    null,
                    null,
                    null
        );
       return res;
    }

    public Cursor readOne(long id){
        open();
        Cursor res = db.query(true, perfilesConst.TN_PERFILES,
                new String[]{
                        perfilesConst.ID_PERFIL,
                        perfilesConst.NOMBRES,
                        perfilesConst.APELLIDOS,
                        perfilesConst.CORREO,
                        perfilesConst.PASSWORD,
                        perfilesConst.CREATE_DATE,
                        perfilesConst.UPDATE_DATE
                },
                perfilesConst.ID_PERFIL+"="+id,
                null,
                null,
                null,
                null,
                null
                );

        if(res != null){
            res.moveToFirst();
        }
        return res;
    }

    public void update(Perfil encuesta){
        ContentValues args = new ContentValues();
        args.put(perfilesConst.NOMBRES, encuesta.getNombres());
        args.put(perfilesConst.APELLIDOS, encuesta.getApellidos());
        args.put(perfilesConst.CORREO, encuesta.getCorreo());
        args.put(perfilesConst.PASSWORD, encuesta.getPassword());
        args.put(perfilesConst.UPDATE_DATE, new Date().getTime());

        open();

        db.update(perfilesConst.TN_PERFILES, args,perfilesConst.ID_PERFIL+" = "+encuesta.getId_perfil(),null);

    }

    public boolean delete(int id){
        return true;
    }

    public Cursor login(String usr){
        open();
        Cursor res = db.query(true, perfilesConst.TN_PERFILES,
                new String[]{perfilesConst.ID_PERFIL,perfilesConst.PASSWORD},
                perfilesConst.CORREO+" = '"+usr+"';",
                null,
                null,
                null,
                null,
                null
        );

        if(res != null){
            res.moveToFirst();
        }
        return res;
    }

    public boolean existeCorreo(String usr){
        open();
        Cursor res = db.query(true, perfilesConst.TN_PERFILES,
                new String[]{perfilesConst.ID_PERFIL},
                perfilesConst.CORREO+" = '"+usr+"';",
                null,
                null,
                null,
                null,
                null
        );

        if(res != null){
            res.moveToFirst();
        }

        if(res.getCount()==0){
            return false;
        }else{
            return true;
        }
    }
}
