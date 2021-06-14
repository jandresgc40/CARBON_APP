package com.example.carbon;

public class Respuestas {

    private int img;
    private String enunciado;

    public Respuestas(int img, String enunciado) {
        this.img = img;
        this.enunciado = enunciado;
    }


    public Respuestas() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
}
