package com.alivelife;

/**
 * Created by MATRIX on 31/10/16.
 */

public class Alumno {
    private String nombre;
    private String matricula;
    private String grupo;
    private String anio;
    private Boolean estrella;
    private int imagenEst;
    private  int imagenFoto;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public void setEstrella(Boolean estrella) {
        this.estrella = estrella;
    }

    public void setImagenFoto(int foto) {
        this.imagenFoto = foto;
    }

    public Alumno(int imagenEst, String nombre, String matricula, String grupo, String anio, boolean est, int imagenFoto) {
        this.imagenEst = imagenEst;
        this.nombre = nombre;
        this.imagenFoto = imagenFoto;
        this.matricula=matricula;
        this.grupo=grupo;
        this.anio=anio;
        this.estrella=false;

    }

    public int getImagenEst() {
        return imagenEst;
    }
    public int getImagenFoto() {
        return imagenFoto;
    }
    public String getNombre() {
        return nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getAnio() {
        return anio;
    }

    public Boolean getEstrella() {
        return estrella;
    }
}
