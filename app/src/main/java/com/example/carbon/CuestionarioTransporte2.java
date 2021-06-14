package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.example.carbon.MainActivity;

public class CuestionarioTransporte2 extends AppCompatActivity {

    private ListView listView;
    Bundle bundle;
    float huellaSeccion;
    String idDocumento;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario_transporte);

        ArrayList<Respuestas> respuestas = new ArrayList<Respuestas>();

        respuestas.add(new Respuestas( R.drawable.coche,"Coche"));
        respuestas.add(new Respuestas( R.drawable.bus,"Autobus / Tren"));
        respuestas.add(new Respuestas( R.drawable.avion,"Avión"));

        bundle = getIntent().getExtras();
        huellaSeccion = bundle.getFloat("huellaSeccion");
        idDocumento = bundle.getString("idDocumento");


        listView = (ListView) findViewById(R.id.ListView_listado);

        listView.setAdapter(new MiAdaptador(this, R.layout.lista_items, respuestas) {
            @Override
            public void onEntrada(Object entrada, View view) {

                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.tvRespuesta);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((Respuestas) entrada).getEnunciado());

                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.ivImagen);
                    if (imagen_entrada != null)
                        imagen_entrada.setImageResource(((Respuestas) entrada).getImg());
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {

                switch (posicion) {

                    case 0: huellaSeccion = huellaSeccion - 0.3f;
                        break;
                    case 1: huellaSeccion = huellaSeccion - 0.6f;
                        break;
                    case 2: huellaSeccion = huellaSeccion - 0.1f;
                        break;

                }

                actualizarDatos();
            }
        });


    }

    public void actualizarDatos() {

        if (huellaSeccion < 0) {
            huellaSeccion = 0;
        }

        Map<String, Object> usuario = new HashMap<>();
        usuario.put("huellaT", String.valueOf(huellaSeccion));

        db.collection("usuarios").document(idDocumento).update(usuario);

        dialogo();

    }

    public void dialogo() {

        String pattern = "##.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        huellaSeccion = 2 - huellaSeccion;

        String format = decimalFormat.format(huellaSeccion);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hábitos de transporte actualizados!");
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