package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

public class CuestionarioHogar extends AppCompatActivity {

    float huellaSeccion = 2;
    String[] comunidades = {"Andalucía", "Aragón", "Canarias", "Cantabria", "Castilla y León", "Castilla-La Mancha (Oeste)", "Castilla-La Mancha (Este)", "Cataluña", "Ceuta", "Comunidad Valenciana", "Comunidad de Madrid (Oeste)","Comunidad de Madrid (Este)", "Extremadura (Norte)", "Extremadura (Sur)", "Galicia", "Islas Baleares", "La Rioja", "Melilla", "Navarra", "País Vasco", "Principado de Asturias", "Región de Murcia"};
    String idDocumento;
    Bundle bundle;
    Spinner comunidadesSpinner;
    Switch verde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario_hogar);

        bundle = getIntent().getExtras();
        idDocumento = bundle.getString("idDocumento");

        comunidadesSpinner = (Spinner) findViewById(R.id.spinnerComunidades);
        comunidadesSpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, comunidades));
        verde = findViewById(R.id.switch1);

    }

    public void calcularHuella() {

        String comunidadSeleccionada = comunidadesSpinner.getSelectedItem().toString();

        if (!verde.isChecked()) {

            switch (comunidadSeleccionada) {

                case "Principado de Asturias":
                case "Cantabria":
                    huellaSeccion = huellaSeccion - 1.0f;
                    break;

                case "Andalucía":
                case "Aragón":
                case "Cataluña":
                case "Canarias":
                case "Islas Baleares":
                case "Ceuta":
                case "Melilla":
                case "Región de Murcia":
                    huellaSeccion = huellaSeccion - 0.6f;
                    break;

                case "Castilla y León":
                case "Comunidad Valenciana":
                case "Comunidad de Madrid":
                case "Extremadura (Norte)":
                case "La Rioja":
                case "Navarra":
                case "País Vasco":
                case "Castilla-La Mancha (Este)":
                case "Comunidad de Madrid (Oeste)":
                    huellaSeccion = huellaSeccion - 0.8f;
                    break;



            }

        }else {

            huellaSeccion = huellaSeccion - 2f;

        }

    }

    public void continuar(View v) {

        calcularHuella();

        Intent intent = new Intent(this, CuestionarioHogar2.class);
        intent.putExtra("huellaSeccion", huellaSeccion);
        intent.putExtra("idDocumento", idDocumento);
        startActivity(intent);
        finish();

    }

}