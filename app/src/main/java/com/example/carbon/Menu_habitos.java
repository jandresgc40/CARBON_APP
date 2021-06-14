package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu_habitos extends AppCompatActivity {

    Bundle bundle;
    String idDocumento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_habitos);

        bundle = getIntent().getExtras();
        idDocumento = bundle.getString("idDocumento");

    }

    public void abrirTransporte(View v) {

        Intent intent = new Intent(this, CuestionarioTransporte.class);
        intent.putExtra("idDocumento", idDocumento);
        startActivity(intent);


    }

    public void abrirDieta(View v) {

        Intent intent = new Intent(this, MenuDieta.class);
        intent.putExtra("idDocumento", idDocumento);
        startActivity(intent);

    }

    public void abrirHogar(View v) {

        Intent intent = new Intent(this, CuestionarioHogar.class);
        intent.putExtra("idDocumento", idDocumento);
        startActivity(intent);

    }


}