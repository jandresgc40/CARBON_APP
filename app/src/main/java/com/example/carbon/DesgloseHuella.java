package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.firestore.FirebaseFirestore;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;
import lecho.lib.hellocharts.model.SliceValue;
import android.graphics.Color;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DesgloseHuella extends AppCompatActivity {

    Bundle bundle;
    String idDocumento;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    float huellaA;
    float huellaT;
    float huellaV;
    PieChartView pieChartView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desglose_huella);

        bundle = getIntent().getExtras();
        idDocumento = bundle.getString("idDocumento");
        bundle = getIntent().getExtras();
        huellaA = Float.parseFloat(bundle.getString("huellaA"));
        bundle = getIntent().getExtras();
        huellaT =  Float.parseFloat(bundle.getString("huellaT"));
        bundle = getIntent().getExtras();
        huellaV = Float.parseFloat( bundle.getString("huellaV"));

        PieChartView pieChartView = findViewById(R.id.chart);

        pieChartView = findViewById(R.id.chart);

        DecimalFormat formato1 = new DecimalFormat("##.###");

        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(huellaA, Color.LTGRAY).setLabel("" + formato1.format(huellaA)));
        pieData.add(new SliceValue(huellaT, Color.GRAY).setLabel("" + formato1.format(huellaT)));
        pieData.add(new SliceValue(huellaV, Color.BLACK).setLabel("" + formato1.format(huellaV)));

        float huellaTotal = huellaA + huellaT + huellaV;

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(20);
        pieChartData.setHasCenterCircle(true).setCenterText1(String.valueOf(huellaTotal).substring(0,3)).setCenterText1FontSize(70).setCenterText1Color(Color.parseColor("#FFFFFF"));
        pieChartView.setPieChartData(pieChartData);


    }





}