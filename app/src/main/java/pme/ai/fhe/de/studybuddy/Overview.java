package pme.ai.fhe.de.studybuddy;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;

public class Overview extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        openMenu();
        loadPieChart();






    }


    boolean loadPieChart()
    {
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);

        List<PieEntry> piechartentry = new ArrayList<>(); //list of entrys

        piechartentry.add(new PieEntry(18.5f, "Programmieren"));
        piechartentry.add(new PieEntry(26.7f, "Medien"));
        piechartentry.add(new PieEntry(24.0f, "Allgemein"));
        piechartentry.add(new PieEntry(30.8f, "Sonstiges"));


        PieDataSet pieentryset = new PieDataSet(piechartentry, "Übersicht");
        PieData data2 = new PieData(pieentryset);
        pieChart.setData(data2);

        pieentryset.setColors(ColorTemplate.VORDIPLOM_COLORS); //color of chart
        pieChart.setUsePercentValues(true);
        //pieChart.setCenterText("Dein Studium");
        pieChart.setHoleRadius(30.0f);
        pieChart.setTransparentCircleRadius(35.0f); //size of transparence inner circle
        pieChart.setTransparentCircleAlpha(200); //transparence of inner circle

        Legend l = pieChart.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.SQUARE); // set what type of form/shape should be used
        l.setTextSize(20f);
        l.setTextColor(Color.BLACK);
        pieChart.animateXY(500, 500); // animate horizontal and vertical 500 milliseconds
        pieChart.invalidate(); // refresh

        return true;
    }


}
