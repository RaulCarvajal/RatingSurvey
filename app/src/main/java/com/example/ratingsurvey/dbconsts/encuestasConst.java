package com.example.ratingsurvey.dbconsts;

public class encuestasConst {

    public static final String TN_ENCUESTAS = "encuestas";
    public static final String ID_ENCUESTA = "id_encuesta";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String MENSAJE = "mensaje";
    public static final String TYPE_ICONS = "type_icons";
    public static final String UPDATE_DATE = "update_date";
    public static final String CREATE_DATE = "create_date";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String AVERAGE_MARK = "average_mark";
    public static final String FK_PERFIL = "fk_perfil";
    public static final String ID_BUSQUEDA = "id_busqueda";

    public static final String TABLE_ENCUESTAS = "CREATE TABLE "+TN_ENCUESTAS+" ( " +
            ID_ENCUESTA+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            NOMBRE+" TEXT, " +
            DESCRIPCION+" TEXT, " +
            MENSAJE+" TEXT, " +
            TYPE_ICONS+" INTEGER, " +
            UPDATE_DATE+" TEXT, " +
            CREATE_DATE+" TEXT, " +
            START_DATE+" TEXT, " +
            END_DATE+" TEXT, " +
            AVERAGE_MARK+" REAL, " +
            FK_PERFIL+" INTEGER, " +
            ID_BUSQUEDA+" TEXT" +
            ");";
}
