package com.example.ratingsurvey.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.ratingsurvey.db.dbHelper;
import com.example.ratingsurvey.dbconsts.votosConst;
import com.example.ratingsurvey.models.Voto;

import java.util.Date;

public class VotoController {

    final Context context;
    dbHelper dbh;
    SQLiteDatabase db;

    public VotoController(Context cxt) {
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

    public long create(Voto voto){
        open();
        ContentValues args = new ContentValues();
        args.put(votosConst.VOTO, voto.getVoto());
        args.put(votosConst.FK_ENCUESTA, voto.getFk_encuesta());
        args.put(votosConst.DATETIME, new Date().getTime());

        return db.insert(votosConst.TN_VOTOS, null, args);
    }

    public Cursor read(long id){
        open();
        Cursor res = db.query(votosConst.TN_VOTOS,
                new String[]{
                        votosConst.ID_VOTO,
                        votosConst.VOTO,
                        votosConst.DATETIME
                },
                votosConst.FK_ENCUESTA + "=" + id ,
                null,
                null,
                null,
                null,
                null
        );
        return res;
    }

}
