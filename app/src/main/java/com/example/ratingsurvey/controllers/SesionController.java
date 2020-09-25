package com.example.ratingsurvey.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.ratingsurvey.db.dbHelper;
import com.example.ratingsurvey.dbconsts.sesionConst;
import com.example.ratingsurvey.models.Sesion;

import java.util.Date;

public class SesionController {

    final Context context;
    dbHelper dbh;
    SQLiteDatabase db;

    public SesionController(Context cxt) {
        this.context = cxt;
        this.dbh = new dbHelper(cxt);
    }

    private void open() throws SQLException {
        db = dbh.getReadableDatabase();
    }

    public void close(){
        dbh.close();
    }


    public long create(Sesion sesion) {
        ContentValues args = new ContentValues();

        args.put(sesionConst.FK_PERFIL,sesion.getFk_perfil());
        args.put(sesionConst.LOGIN_DATE, new Date().getTime());
        open();
        return db.insert(sesionConst.TN_SESION,null,args);
    }

    public Cursor read() {
        open();
        return  db.query(sesionConst.TN_SESION, new String[]{sesionConst.ID_SESION,sesionConst.LOGIN_DATE,sesionConst.FK_PERFIL},null,null,null,null,null);
    }

    public void closeSession(){
        open();
        db.execSQL("DELETE FROM "+sesionConst.TN_SESION+";");
    }

    public long getIdPerfil(){
        Cursor c = read();
        c.moveToFirst();
        long fk_id = c.getLong(2);
        c.close();
        close();

        return fk_id;
    }
}
