package pme.ai.fhe.de.studybuddy.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewDebug;

import com.facebook.stetho.Stetho;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class Overview extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Stetho.initializeWithDefaults(this);
        openMenu();
        loadPieChart();






    }


    boolean loadPieChart()
    {


        UserData dataset = controller.getUserData(); //gets all User Data

        List<Lecture> pieDataset = controller.getLecturesByCourseId(dataset.getCourseId()); //gets List with All Courses from User

        int numberOfCategories = controller.getNumberOfCategories();
        int [] categories = new int[numberOfCategories]; //Array Counts Categories


        Log.i("ID:", Integer.toString(dataset.getCourseId()));

        for(Lecture l : pieDataset)
        {
            //todo if abfrage, ob kurs bestanden wurde
            //todo mit credit points multiplizieren
            categories[l.getCategoryId()-1]++;

        }


        List<PieEntry> piechartentry = new ArrayList<>(); //list of entrys
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);

        //Add Data
        for(int i = 0; i<numberOfCategories; i++)
        {
            if(categories[i]>0)
            {
                float value = (float) categories[i];
                piechartentry.add(new PieEntry(value, controller.getCategorieNameByID(categories[i])+1));
            }
        }

        //((float) categories[i]/ (float) piechartentry.size())*100

        /*piechartentry.add(new PieEntry(26.7f, "Medien"));
        piechartentry.add(new PieEntry(24.0f, "Allgemein"));
        piechartentry.add(new PieEntry(30.8f, "Sonstiges"));*/


        // all the chart settings
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
        l.setTextSize(14f);
        l.setTextColor(Color.BLACK);
        pieChart.animateXY(500, 500); // animate horizontal and vertical 500 milliseconds
        pieChart.invalidate(); // refresh

        return true;
    }


}
