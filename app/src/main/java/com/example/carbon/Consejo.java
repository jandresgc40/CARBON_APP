package com.example.carbon;

public class Consejo {

    private String titulo;
    private String texto;
    private int imagen;

    public Consejo(String titulo, String texto, int imagen) {
        this.titulo = titulo;
        this.texto = texto;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
