package com.example.carbon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Incidencias extends AppCompatActivity {

    Bundle bundle;
    String idDocumento;
    EditText editTextIncidencia;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencias);

        bundle = getIntent().getExtras();
        idDocumento = bundle.getString("idDocumento");
        editTextIncidencia = findViewById(R.id.etIncidencia);

    }

    public void enviar(View v) {

        Map<String, Object> usuario = new HashMap<>();
        usuario.put("incidencia", editTextIncidencia.getText().toString());
        db.collection("usuarios").document(idDocumento).update(usuario);
        dialogo();

    }

    public void dialogo() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Info");
        builder.setMessage("Muchas gracias por enviarnos tu incidencia! Esto nos ayuda a mejorar tu experiencia. Un admisistrador la revisará lo antes posible.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}