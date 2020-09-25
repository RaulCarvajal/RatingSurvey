package com.example.ratingsurvey.dbconsts;

public class sesionConst {

    public static final String TN_SESION = "sesion";
    public static final String ID_SESION = "id_sesion";
    public static final String LOGIN_DATE = "login_date";
    public static final String FK_PERFIL = "fk_pefil";

    public static final String TABLE_SESION = "CREATE TABLE "+TN_SESION+"( " +
            ID_SESION+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            LOGIN_DATE+" TEXT," +
            FK_PERFIL+" INTEGER" +
            ");";

}
