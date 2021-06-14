package com.example.carbon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class MiAdaptador extends BaseAdapter {

    private ArrayList<?> respuestas;
    private int R_layout_IdView;
    private Context contexto;

    public MiAdaptador(Context contexto, int R_layout_IdView, ArrayList<?> respuestas) {
        super();
        this.contexto = contexto;
        this.respuestas = respuestas;
        this.R_layout_IdView = R_layout_IdView;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup pariente) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }
        onEntrada (respuestas.get(posicion), view);
        return view;
    }

    @Override
    public int getCount() {
        return respuestas.size();
    }

    @Override
    public Object getItem(int posicion) {
        return respuestas.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    /** Devuelve cada una de las entradas con cada una de las vistas a la que debe de ser asociada
     * @param entrada La entrada que será la asociada a la view. La entrada es del tipo del paquete/handler
     * @param view View particular que contendrá los datos del paquete/handler
     */
    public abstract void onEntrada(Object entrada, View view);
}

