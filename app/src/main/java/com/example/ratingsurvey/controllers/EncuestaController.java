package com.example.ratingsurvey.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.ratingsurvey.db.dbHelper;
import com.example.ratingsurvey.dbconsts.perfilesConst;
import com.example.ratingsurvey.models.Encuesta;

import com.example.ratingsurvey.dbconsts.encuestasConst;

import java.util.Date;


public class EncuestaController {

    final Context context;
    dbHelper dbh;
    SQLiteDatabase db;

    public EncuestaController(Context cxt) {
        this.context = cxt;
        this.dbh = new dbHelper(cxt);
    }

    public void open() throws SQLException {
        db = dbh.getWritableDatabase();
    }

    //---Cerrar la base de datos---
    public void close(){
        dbh.close();
    }

    public long create(Encuesta encuesta){

        ContentValues args = new ContentValues();
        args.put(encuestasConst.NOMBRE,encuesta.getNombre());
        args.put(encuestasConst.DESCRIPCION,encuesta.getDescripcion());
        args.put(encuestasConst.MENSAJE,encuesta.getMensaje());
        args.put(encuestasConst.TYPE_ICONS,encuesta.getType_icons());
        args.put(encuestasConst.CREATE_DATE, new Date().getTime());
        args.put(encuestasConst.UPDATE_DATE, new Date().getTime());
        args.put(encuestasConst.START_DATE, new Date().getTime());
        args.put(encuestasConst.END_DATE, new Date().getTime());
        args.put(encuestasConst.AVERAGE_MARK, 0);
        args.put(encuestasConst.FK_PERFIL,encuesta.getFk_perfil());
        args.put(encuestasConst.ID_BUSQUEDA,"");

        open();
        long ret = db.insert(encuestasConst.TN_ENCUESTAS,null,args);
        return ret;
    }

    public Cursor readByClient(long id){
        open();
        Cursor res = db.query(encuestasConst.TN_ENCUESTAS,
                new String[]{
                        encuestasConst.ID_ENCUESTA,
                        encuestasConst.NOMBRE,
                        encuestasConst.DESCRIPCION,
                        encuestasConst.MENSAJE,
                        encuestasConst.TYPE_ICONS,
                        encuestasConst.CREATE_DATE,
                        encuestasConst.UPDATE_DATE,
                        encuestasConst.START_DATE,
                        encuestasConst.END_DATE,
                        encuestasConst.AVERAGE_MARK,
                        encuestasConst.FK_PERFIL,
                        encuestasConst.ID_BUSQUEDA
                },
                encuestasConst.FK_PERFIL + "=" + id ,
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
        Cursor res = db.query(encuestasConst.TN_ENCUESTAS,
                new String[]{
                        encuestasConst.ID_ENCUESTA,
                        encuestasConst.NOMBRE,
                        encuestasConst.DESCRIPCION,
                        encuestasConst.MENSAJE,
                        encuestasConst.TYPE_ICONS,
                        encuestasConst.CREATE_DATE,
                        encuestasConst.UPDATE_DATE,
                        encuestasConst.START_DATE,
                        encuestasConst.END_DATE,
                        encuestasConst.AVERAGE_MARK,
                        encuestasConst.FK_PERFIL,
                        encuestasConst.ID_BUSQUEDA
                },
                encuestasConst.ID_ENCUESTA + "=" + id ,
                null,
                null,
                null,
                null,
                null
        );
        res.moveToFirst();
        return res;
    }

    public void updatePromedio(float AVG, long id){
        ContentValues args = new ContentValues();
        args.put(encuestasConst.END_DATE, new Date().getTime());
        args.put(encuestasConst.AVERAGE_MARK, AVG);

        open();
        db.update(encuestasConst.TN_ENCUESTAS, args, encuestasConst.ID_ENCUESTA+" = "+id, null);

    }

    public void updateIdBusqueda(String id_busqueda, long id){
        ContentValues args = new ContentValues();
        args.put(encuestasConst.ID_BUSQUEDA,id_busqueda);
        open();
        db.update(encuestasConst.TN_ENCUESTAS,args, encuestasConst.ID_ENCUESTA+" = "+id,null);
    }

    public boolean delete(int id){
        return true;
    }

    public void update(Encuesta encuesta){
        ContentValues args = new ContentValues();
        args.put(encuestasConst.NOMBRE,encuesta.getNombre());
        args.put(encuestasConst.DESCRIPCION,encuesta.getDescripcion());
        args.put(encuestasConst.MENSAJE,encuesta.getMensaje());
        args.put(encuestasConst.TYPE_ICONS,encuesta.getType_icons());
        args.put(encuestasConst.UPDATE_DATE, new Date().getTime());

        open();
        db.update(encuestasConst.TN_ENCUESTAS, args,encuestasConst.ID_ENCUESTA + " = "+encuesta.getId_encuesta(), null);
    }

}
