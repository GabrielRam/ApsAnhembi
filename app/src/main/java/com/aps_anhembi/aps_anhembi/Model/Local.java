package com.aps_anhembi.aps_anhembi.Model;

public class Local {
    int id;
    String idUsuario;
    String latitude;
    String longitude;
    String titulo;

    public Local() {

    }

    public Local(int id, String idUsuario, String latitude, String longitude, String titulo) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.latitude = latitude;
        this.longitude = longitude;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
