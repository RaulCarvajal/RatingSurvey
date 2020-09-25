package com.example.ratingsurvey.models;

import java.util.Date;

public class Perfil {

    private long id_perfil;
    private String nombres;
    private String apellidos;
    private String correo;
    private String password;
    private long update_date;
    private long create_date;

    public Perfil(String nombres, String apellidos, String correo, String password, long update_date, long create_date) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.update_date = update_date;
        this.create_date = create_date;
        this.password = password;
    }

    public Perfil(long id_perfil, String nombres, String apellidos, String correo, String password, long update_date, long create_date) {
        this.id_perfil = id_perfil;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.update_date = update_date;
        this.create_date = create_date;
        this.password = password;
    }

    public Perfil(String nombres, String apellidos, String correo, String password) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.password = password;
    }

    public long getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(long update_date) {
        this.update_date = update_date;
    }

    public long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(long create_date) {
        this.create_date = create_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
