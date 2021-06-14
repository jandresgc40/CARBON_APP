package com.example.carbon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView ivhuella;
    TextView textViewDatoAleatorio;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String email;
    String idDocumento;
    Bundle bundle;
    String huella;
    String huellaA;
    String huellaT;
    String huellaV;
    Button button6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivhuella = findViewById(R.id.imageViewHuella);
        textViewDatoAleatorio = findViewById(R.id.textViewDatoAleatorio);
        button6 = findViewById(R.id.button6);

        bundle = getIntent().getExtras();
        email = bundle.getString("email");

        String [] parte = email.split("@");
        idDocumento = parte[0];

        actualizarDatos();

    }

    public void abrirHabitos(View v) {

        Intent intent = new Intent(this, Menu_habitos.class);
        intent.putExtra("idDocumento", idDocumento);
       // intent.putExtra("huella", huella);
        intent.putExtra("huellaA", huellaA);
        intent.putExtra("huellaT", huellaT);
        intent.putExtra("huellaV", huellaV);
        startActivity(intent);

    }

    public void abrirGuia(View v) {

        Intent intent = new Intent(this, GuiaLista.class);
        intent.putExtra("idDocumento", idDocumento);
        startActivity(intent);

    }

    public void abrirRanking(View v) {

        Intent intent = new Intent(this, Ranking.class);
        intent.putExtra("idDocumento", idDocumento);
        startActivity(intent);

    }


    public void actualizarDatos() {

        DocumentReference docRef = db.collection("usuarios").document(idDocumento);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        huellaA = document.get("huellaA").toString();
                        huellaT = document.get("huellaT").toString();
                        huellaV = document.get("huellaV").toString();

                        Float huellaAF = Float.parseFloat(huellaA);
                        Float huellaTF = Float.parseFloat(huellaT);
                        Float huellaVF = Float.parseFloat(huellaV);

                        Float huellaTotal = huellaAF + huellaTF + huellaVF;

                        huella = String.valueOf(huellaTotal);

                        if (huellaTotal >= 6 ) {
                            ivhuella.setImageResource(R.drawable.medidor_6);
                        }

                        if (huellaTotal >= 5 && huellaTotal < 6 ) {
                            ivhuella.setImageResource(R.drawable.medidor_5);
                        }

                        if (huellaTotal >= 4 && huellaTotal < 5 ) {
                            ivhuella.setImageResource(R.drawable.medidor_4);
                        }

                        if (huellaTotal >= 3 && huellaTotal < 4 ) {
                            ivhuella.setImageResource(R.drawable.medidor_3);
                        }

                        if (huellaTotal >= 2 && huellaTotal < 3 ) {
                            ivhuella.setImageResource(R.drawable.medidor_2);
                        }

                        if (huellaTotal >= 1 && huellaTotal < 2 ) {
                            ivhuella.setImageResource(R.drawable.medidor_1);
                        }

                        if (huellaTotal >= 0 && huellaTotal < 1 ) {
                            ivhuella.setImageResource(R.drawable.medidor_0);
                        }


                        Map<String, Object> usuario = new HashMap<>();
                        usuario.put("huella", String.valueOf(huella));
                        db.collection("usuarios").document(idDocumento).update(usuario);


                    } else {

                        String dos = "2.0";
                        String tres = "3.0";
                        String siete = "7.0";

                        Map<String, Object> usuario = new HashMap<>();
                        usuario.put("email", email);
                        usuario.put("huellaT", dos);
                        usuario.put("huellaA", tres);
                        usuario.put("huellaV", dos);
                        usuario.put("huella", siete);
                        usuario.put("incidencia", "sin incidencias");
                        db.collection("usuarios").document(idDocumento).set(usuario);

                        ivhuella.setImageResource(R.drawable.medidor_6);

                        dialogo();

                    }
                } else {

                    Toast.makeText(MainActivity.this, "get failed with " + task.getException(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        establecerDatosAleatorios();

    }

    public void recargar(View V) throws InterruptedException {

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotacion);
        animation.setFillAfter(true);
        button6.startAnimation(animation);
        actualizarDatos();

    }

    public void establecerDatosAleatorios() {

        int num;

        ArrayList datos = new ArrayList();
        datos.add("En la Amazonía, el 1% de las especies de árboles almacena el 50% del carbono de la región.");
        datos.add("Solo el 0.7% de los bosques del mundo son manglares costeros, y almacenan hasta 10 veces más carbono por hectárea que los bosques tropicales.");
        datos.add("En 2018 se dió la concentración más alta de CO2 en 3 millones de años.");

        Random r = new Random();
        num = r.nextInt(datos.size());

        textViewDatoAleatorio.setText(datos.get(num).toString());


    }

    public void desgloseHuella(View v) {

        Intent intent = new Intent(this, DesgloseHuella.class);
        intent.putExtra("idDocumento", idDocumento);
        // intent.putExtra("huella", huella);
        intent.putExtra("huellaA", huellaA);
        intent.putExtra("huellaT", huellaT);
        intent.putExtra("huellaV", huellaV);
        startActivity(intent);

    }

    public void abrirIncidencia(View v) {

        Intent intent = new Intent(this, Incidencias.class);
        intent.putExtra("idDocumento", idDocumento);
        startActivity(intent);

    }


    public void dialogo() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bienvenido a Carbon!");
        builder.setMessage("Al ser la primera vez que inicias sesión deberás empezar el cuestionario para calcular tu huella de carbono! " +
                            "Entra en uno de los hábitos y realiza los cuestionarios hasta completar los tres!" +
                "No olvides pulsar el botón de la esquina superior derecha (\ud83d\udd03) en la pantalla de inicio para guardar y actualizar los nuevos datos.");
        builder.setPositiveButton("Venga vamos!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Intent intent = new Intent(MainActivity.this, Menu_habitos.class);
                intent.putExtra("idDocumento", idDocumento);
                // intent.putExtra("huella", huella);
                intent.putExtra("huellaA", huellaA);
                intent.putExtra("huellaT", huellaT);
                intent.putExtra("huellaV", huellaV);
                startActivity(intent);

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


}