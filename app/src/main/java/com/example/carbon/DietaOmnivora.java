package com.example.carbon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class DietaOmnivora extends AppCompatActivity {

    String [] frecuencias = {"De vez en cuando","Habitualmente","Con mucha frecuencia"};
    float huellaSeccion = 3;
    String idDocumento;
    Bundle bundle;

    CheckBox checkBoxPescado, checkBoxPollo, checkBoxCerdo, checkBoxTernera;
    Spinner spinnerPescado, spinnerPollo, spinnerCerdo, spinnerTernera;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieta_omnivora);

        bundle = getIntent().getExtras();
        idDocumento = bundle.getString("idDocumento");

        Spinner frecuenciasSemanales1 = (Spinner) findViewById(R.id.spinnerPollo);
        Spinner frecuenciasSemanales2 = (Spinner) findViewById(R.id.spinnerPescado);
        Spinner frecuenciasSemanales3 = (Spinner) findViewById(R.id.spinnerTernera);
        Spinner frecuenciasSemanales4 = (Spinner) findViewById(R.id.spinnerCerdo);

        frecuenciasSemanales1.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, frecuencias));
        frecuenciasSemanales2.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, frecuencias));
        frecuenciasSemanales3.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, frecuencias));
        frecuenciasSemanales4.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, frecuencias));

        spinnerPescado = findViewById(R.id.spinnerPescado);
        spinnerPollo = findViewById(R.id.spinnerPollo);
        spinnerCerdo = findViewById(R.id.spinnerCerdo );
        spinnerTernera = findViewById(R.id.spinnerTernera);

        checkBoxPescado = findViewById(R.id.checkBoxPescado);
        checkBoxPollo = findViewById(R.id.checkBoxPollo);
        checkBoxCerdo = findViewById(R.id.checkBoxCerdo);
        checkBoxTernera = findViewById(R.id.checkBoxTernera);

    }

    public void calcularHuella() {

        if (checkBoxPescado.isChecked()) {

            String frecuencia = spinnerPescado.getSelectedItem().toString();

            switch (frecuencia) {

                case "De vez en cuando": huellaSeccion = huellaSeccion - 0.5f;
                break;
                case "Habitualmente": huellaSeccion = huellaSeccion - 0.3f;
                break;
                case "Con mucha frecuencia": huellaSeccion = huellaSeccion - 0.1f;
                break;


            } }

        if (checkBoxPollo.isChecked()) {

            String frecuencia = spinnerPollo.getSelectedItem().toString();

            switch (frecuencia) {

                case "De vez en cuando": huellaSeccion = huellaSeccion - 0.9f;
                    break;
                case "Habitualmente": huellaSeccion = huellaSeccion - 0.7f;
                    break;
                case "Con mucha frecuencia": huellaSeccion = huellaSeccion - 0.5f;
                    break;

            } }

        if (checkBoxCerdo.isChecked()) {

            String frecuencia = spinnerCerdo.getSelectedItem().toString();

            switch (frecuencia) {

                case "De vez en cuando": huellaSeccion = huellaSeccion - 0.6f;
                    break;
                case "Habitualmente": huellaSeccion = huellaSeccion - 0.3f;
                    break;
                case "Con mucha frecuencia": huellaSeccion = huellaSeccion - 0.1f;
                    break;

            } }

        if (checkBoxTernera.isChecked()) {

            String frecuencia = spinnerTernera.getSelectedItem().toString();

            switch (frecuencia) {

                case "De vez en cuando": huellaSeccion = huellaSeccion - 0.1f;
                    break;
                case "Habitualmente": huellaSeccion = huellaSeccion + 0.3f;
                    break;
                case "Con mucha frecuencia": huellaSeccion = huellaSeccion + 0.6f;
                    break;

            } }


        if (huellaSeccion < 0) {

            huellaSeccion = 0;

        }

        Map<String, Object> usuario = new HashMap<>();
        usuario.put("huellaA", String.valueOf(huellaSeccion));

        db.collection("usuarios").document(idDocumento).update(usuario);


    }

    public void continuar( View v ){

        calcularHuella();
        dialogo();

    }

    public void dialogo() {

        String pattern = "##.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        float huellaSeccionMostrar = 3 - huellaSeccion;

        if (huellaSeccion >= 3) {

            huellaSeccionMostrar = 0;

        }

        String format = decimalFormat.format(huellaSeccionMostrar);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hábitos de alimentacion actualizados!");
        builder.setMessage("Ahora mismo estás ahorrando " + format + " toneladas, sigue así!");
        builder.setPositiveButton("Genial!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                finish();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

}