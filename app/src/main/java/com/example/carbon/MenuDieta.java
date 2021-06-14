package com.example.carbon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class MenuDieta extends AppCompatActivity {

    float huellaSeccion = 3;
    String idDocumento;
    Bundle bundle;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dieta);

        bundle = getIntent().getExtras();
        idDocumento = bundle.getString("idDocumento");


    }

    public void abrirDietaOmnivora(View v) {

        Intent intent = new Intent(this, DietaOmnivora.class);
        intent.putExtra("idDocumento", idDocumento);
        startActivity(intent);
        finish();

    }

    public void abrirDietaVegetariana(View v) {

        Intent intent = new Intent(this, DietaVegetariana.class);
        intent.putExtra("idDocumento", idDocumento);
        startActivity(intent);
        finish();

    }

    public void abrirDietaVegana(View v) {

        Float dietaVegana = huellaSeccion - 2.5f;
        dialogoDietaVegana();

        Map<String, Object> usuario = new HashMap<>();
        usuario.put("huellaA", String.valueOf(dietaVegana));
        db.collection("usuarios").document(idDocumento).update(usuario);


    }

    public void dialogoDietaVegana() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hábitos de alimentación actualizados!");
        builder.setMessage("Ahora mismo estás ahorrando " + (huellaSeccion - 0.5f) + " toneladas, sigue así!");
        builder.setPositiveButton("Genial!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}