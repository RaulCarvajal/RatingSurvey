package com.example.ratingsurvey.models;

import java.sql.Date;

public class Voto {
    public long voto;
    public Date datetime;
    public long fk_encuesta;

    public Voto(long voto, Date datetime, long fk_encuesta) {
        this.voto = voto;
        this.datetime = datetime;
        this.fk_encuesta = fk_encuesta;
    }

    public Voto(int voto, int fk_encuesta) {
        this.voto = voto;
        this.fk_encuesta = fk_encuesta;
    }

    public long getVoto() {
        return voto;
    }

    public void setVoto(long voto) {
        this.voto = voto;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public long getFk_encuesta() {
        return fk_encuesta;
    }

    public void setFk_encuesta(long fk_encuesta) {
        this.fk_encuesta = fk_encuesta;
    }

}
