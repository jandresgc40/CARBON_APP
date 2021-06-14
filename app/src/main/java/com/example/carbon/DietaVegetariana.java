package com.example.carbon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class DietaVegetariana extends AppCompatActivity {

    float huellaSeccion = 3;
    String idDocumento;
    Bundle bundle;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    CheckBox checkBoxHuevos, checkBoxLacteos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieta_vegetariana);

        bundle = getIntent().getExtras();
        idDocumento = bundle.getString("idDocumento");

        checkBoxHuevos = findViewById(R.id.checkBoxHuevos);
        checkBoxLacteos = findViewById(R.id.checkBoxLacteos);

    }


    public void seleccionarHuevos(View v) {
        checkBoxHuevos.setChecked(!checkBoxHuevos.isChecked());
    }

    public void seleccionarLacteos(View v) {
        checkBoxLacteos.setChecked(!checkBoxLacteos.isChecked());
    }

    public void calcularHuella() {

        huellaSeccion = huellaSeccion - 1.2f;

        if (checkBoxHuevos.isChecked()) {

            huellaSeccion = huellaSeccion - 0.5f;

            }

        if (checkBoxLacteos.isChecked()) {

            huellaSeccion = huellaSeccion - 0.1f;

            }

        Map<String, Object> usuario = new HashMap<>();
        usuario.put("huellaA", String.valueOf(huellaSeccion));

        db.collection("usuarios").document(idDocumento).update(usuario);


        dialogo();

    }

    public void continuar(View v) {
        calcularHuella();
    }

    public void dialogo() {

        String pattern = "##.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hábitos de alimentación actualizados!");
        builder.setMessage("Ahora mismo estás ahorrando " +  decimalFormat.format(( 3f - huellaSeccion)) + " toneladas, sigue así!");
        builder.setPositiveButton("Genial!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


}

