package com.example.ratingsurvey.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ratingsurvey.dbconsts.encuestasConst;
import com.example.ratingsurvey.dbconsts.perfilesConst;
import com.example.ratingsurvey.dbconsts.sesionConst;
import com.example.ratingsurvey.dbconsts.votosConst;

public class dbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "QualitySurvey.db";

    public dbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(perfilesConst.TABLE_PERFILES);
        db.execSQL(encuestasConst.TABLE_ENCUESTAS);
        db.execSQL(votosConst.TABLE_VOTOS);
        db.execSQL(sesionConst.TABLE_SESION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS perfiles;");
        db.execSQL("DROP TABLE IF EXISTS encuestas;");
        db.execSQL("DROP TABLE IF EXISTS votos;");
        db.execSQL("DROP TABLE IF EXISTS sesion;");

        onCreate(db);

    }
}
