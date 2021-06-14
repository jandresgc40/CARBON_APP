package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PantallaConsejo extends AppCompatActivity {

    ImageView imageView;
    TextView textViewTitulo;
    TextView textViewTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_consejo);

        Bundle extras = getIntent().getExtras();

        String titulo = extras.getString("titulo");
        String texto = extras.getString("texto");
        int imagen = extras.getInt("imagen");

        imageView = findViewById(R.id.imageView5);
        textViewTitulo = findViewById(R.id.tvTitulo);
        textViewTexto = findViewById(R.id.tvTexto);

        imageView.setImageResource(imagen);
        textViewTitulo.setText(titulo);
        textViewTexto.setText(texto);


    }
}