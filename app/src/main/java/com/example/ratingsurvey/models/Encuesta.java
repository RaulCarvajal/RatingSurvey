package com.example.ratingsurvey.models;

import java.sql.Date;

public class Encuesta implements Comparable<Encuesta> {

    private long id_encuesta;
    private String nombre;
    private String descripcion;
    private String mensaje;
    private int type_icons;
    public long update_date;
    public long create_date;
    public long start_date;
    public long end_date;
    private float average_mark;
    private long fk_perfil;
    private String id_busqueda;

    public Encuesta() {
    }

    public Encuesta(long id_encuesta, String nombre, String descripcion, String mensaje, int type_icons, long update_date, long create_date, long start_date, long end_date, float average_mark, long fk_perfil, String id_busqueda) {
        this.id_encuesta = id_encuesta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.mensaje = mensaje;
        this.type_icons = type_icons;
        this.update_date = update_date;
        this.create_date = create_date;
        this.start_date = start_date;
        this.end_date = end_date;
        this.average_mark = average_mark;
        this.fk_perfil = fk_perfil;
        this.id_busqueda = id_busqueda;
    }

    public Encuesta(String nombre, String descripcion, String mensaje, int type_icons, long fk_perfil) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.mensaje = mensaje;
        this.type_icons = type_icons;
        this.fk_perfil = fk_perfil;
    }

    public Encuesta(long id_encuesta, String nombre, String descripcion, String mensaje, int type_icons) {
        this.id_encuesta = id_encuesta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.mensaje = mensaje;
        this.type_icons = type_icons;
    }

    public long getId_encuesta() {
        return id_encuesta;
    }

    public void setId_encuesta(int id_encuesta) {
        this.id_encuesta = id_encuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getType_icons() {
        return type_icons;
    }

    public void setType_icons(int type_icons) {
        this.type_icons = type_icons;
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

    public long getStart_date() {
        return start_date;
    }

    public void setStart_date(long start_date) {
        this.start_date = start_date;
    }

    public long getEnd_date() {
        return end_date;
    }

    public void setEnd_date(long end_date) {
        this.end_date = end_date;
    }

    public float getAverage_mark() {
        return average_mark;
    }

    public void setAverage_mark(float average_mark) {
        this.average_mark = average_mark;
    }

    public long getFk_perfil() {
        return fk_perfil;
    }

    public void setFk_perfil(int fk_perfil) {
        this.fk_perfil = fk_perfil;
    }

    public String getId_busqueda() {
        return id_busqueda;
    }

    @Override
    public int compareTo(Encuesta o) {
        if (end_date < o.getEnd_date()) {
            return -1;
        }
        if (end_date > o.getEnd_date()) {
            return 1;
        }
        return 0;
    }
}
