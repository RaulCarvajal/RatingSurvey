package com.example.ratingsurvey.models;

import java.util.Date;

public class Sesion {

    private long id_sesion;
    private Date login_date;
    private long fk_perfil;

    public Sesion(long fk_perfil) {
        this.fk_perfil = fk_perfil;
    }

    public long getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(int id_sesion) {
        this.id_sesion = id_sesion;
    }

    public Date getLogin_date() {
        return login_date;
    }

    public void setLogin_date(Date login_date) {
        this.login_date = login_date;
    }

    public long getFk_perfil() {
        return fk_perfil;
    }

    public void setFk_perfil(int fk_perfil) {
        this.fk_perfil = fk_perfil;
    }
}
