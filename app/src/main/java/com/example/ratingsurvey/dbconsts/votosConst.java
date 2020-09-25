package com.example.ratingsurvey.dbconsts;

public class votosConst {

    public static final String TN_VOTOS = "votos";
    public static final String ID_VOTO = "id_voto";
    public static final String VOTO = "voto";
    public static final String DATETIME = "datetime";
    public static final String FK_ENCUESTA = "fk_encuesta";
    public static final String FK_PERFIL = "fk_perfil";

    public static final String TABLE_VOTOS = "CREATE TABLE "+TN_VOTOS+"( " +
                                ID_VOTO+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                                VOTO+" INTEGER," +
                                DATETIME+" TEXT," +
                                FK_ENCUESTA+" INTEGER," +
                                FK_PERFIL+" INTEGER" +
                                ");";

}
