package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class Ranking extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String idDocumento;
    Bundle bundle;
    TextView ranking;
    TextView posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        bundle = getIntent().getExtras();
        idDocumento = bundle.getString("idDocumento");

        ranking = findViewById(R.id.textView5);
        posicion = findViewById(R.id.tvPosicion);

        obtenerDatos();

    }

    public void obtenerDatos() {

        db.collection("usuarios").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                ArrayList<String> huellas = new ArrayList<String>();
                for (QueryDocumentSnapshot q : queryDocumentSnapshots) {
                    if (q.getId().toString().equals(idDocumento)) {
                        huellas.add((q.get("huella").toString()).substring(0, 3) + "  -----------  " + "YO");
                    } else {
                        huellas.add((q.get("huella").toString()).substring(0, 3) + "  -----------  " + q.getId());
                    }
                }

                Collections.sort(huellas);

                int contador = 1;

                for (String dato : huellas) {
                    ranking.setText(ranking.getText().toString() + contador + "    " + dato + "\n\n");
                    if (dato.substring(18, 20).equals("YO")) {
                        posicion.setText(String.valueOf(contador));
                    }

                    contador++;
                }
            }
        });

    }

    public void recargar(View v) {

        ranking.setText("");
        obtenerDatos();

    }
}