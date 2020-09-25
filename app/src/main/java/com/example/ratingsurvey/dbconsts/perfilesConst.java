package com.example.ratingsurvey.dbconsts;

public class perfilesConst {

    public static final String TN_PERFILES = "perfiles";
    public static final String ID_PERFIL = "id_perfil";
    public static final String NOMBRES = "nombres";
    public static final String APELLIDOS = "apellidos";
    public static final String CORREO = "correo";
    public static final String PASSWORD = "password";
    public static final String CREATE_DATE = "create_date";
    public static final String UPDATE_DATE= "update_date";

    public static final String TABLE_PERFILES = "CREATE TABLE "+TN_PERFILES+" ( " +
                ID_PERFIL+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOMBRES+" TEXT," +
                APELLIDOS+" TEXT," +
                CORREO+" TEXT," +
                PASSWORD+" TEXT," +
                UPDATE_DATE+" TEXT," +
                CREATE_DATE+" TEXT" +
            ");";

}
