package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class Administradores extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView incidencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administradores);

        incidencias = findViewById(R.id.tvIncidencias);

        obtenerDatos();

    }

    public void obtenerDatos() {

        db.collection("usuarios").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                ArrayList<String> huellas = new ArrayList<String>();
                for (QueryDocumentSnapshot q : queryDocumentSnapshots) {

                    if (!(q.get("incidencia").toString().equals("sin incidencias"))) {
                        huellas.add(q.getId() + "-- Incidencia: " + (q.get("incidencia").toString()));

                    }
                }

                for (String dato : huellas) {

                    incidencias.setText(incidencias.getText().toString() + dato + "\n\n");


           }

            }
        });

    }

    public void salir(View v) {

        finish();

    }

}