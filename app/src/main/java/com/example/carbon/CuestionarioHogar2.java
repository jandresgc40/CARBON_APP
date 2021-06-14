package com.example.carbon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CuestionarioHogar2 extends AppCompatActivity {

    String idDocumento;
    Bundle bundle;
    float huellaSeccion;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario_hogar2);

        bundle = getIntent().getExtras();
        idDocumento = bundle.getString("idDocumento");
        huellaSeccion = bundle.getFloat("huellaSeccion");

    }

    public void gasNatural(View v) {

        huellaSeccion = huellaSeccion + 0.8f;
        actualizarDatos();
        dialogo();

    }

    public void radiador(View v) {

        huellaSeccion = huellaSeccion - 0.8f;
        actualizarDatos();
        dialogo();

    }

    public void chimenea(View v) {

        huellaSeccion = huellaSeccion - 0.9f;
        actualizarDatos();
        dialogo();

    }


    public void dialogo() {

        String pattern = "##.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        float huellaSeccionMostrar = 2 - huellaSeccion;

        if (huellaSeccionMostrar < 0) {

            huellaSeccionMostrar = 0;

        }

        String format = decimalFormat.format(huellaSeccionMostrar);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hábitos de hogar actualizados!");
        builder.setMessage("Ahora mismo estás ahorrando " + format + " toneladas, sigue así!");
        builder.setPositiveButton("Genial!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                finish();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void actualizarDatos() {

        if (huellaSeccion < 0) {

            huellaSeccion = 0;

        }

        Map<String, Object> usuario = new HashMap<>();
        usuario.put("huellaV", String.valueOf(huellaSeccion));

        db.collection("usuarios").document(idDocumento).update(usuario);

        dialogo();

    }

}