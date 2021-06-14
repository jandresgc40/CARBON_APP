package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CuestionarioTransporte extends AppCompatActivity {

    private ListView listView;
    float huellaSeccion = 2;
    String idDocumento;
    Bundle bundle;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario_transporte);

        bundle = getIntent().getExtras();
        idDocumento = bundle.getString("idDocumento");

        tv = findViewById(R.id.textView12);

        tv.setText("¿Qué medios de transporte utilizas en trayectos cortos?");


        ArrayList<Respuestas> respuestas = new ArrayList<Respuestas>();

        respuestas.add(new Respuestas( R.drawable.coche,"Coche"));
        respuestas.add(new Respuestas( R.drawable.bus,"Autobus / Tren"));
        respuestas.add(new Respuestas( R.drawable.caminar,"Caminando"));
        respuestas.add(new Respuestas( R.drawable.moto,"Moto"));
        respuestas.add(new Respuestas( R.drawable.patinete,"Patinete eléctrico"));

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

                    case 0: huellaSeccion = huellaSeccion - 0.2f;
                        break;
                    case 1: huellaSeccion = huellaSeccion - 0.5f;
                        break;
                    case 2: huellaSeccion = huellaSeccion - 1.5f;
                        break;
                    case 3: huellaSeccion = huellaSeccion - 0.4f;
                        break;
                    case 4: huellaSeccion = huellaSeccion - 1f;
                        break;

                }

                Intent intent = new Intent(CuestionarioTransporte.this, CuestionarioTransporte2.class);
                intent.putExtra("huellaSeccion", huellaSeccion);
                intent.putExtra("idDocumento", idDocumento);

                startActivity(intent);

                finish();

            }
        });

    }
}