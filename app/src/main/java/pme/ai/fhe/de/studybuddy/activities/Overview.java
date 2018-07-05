package pme.ai.fhe.de.studybuddy.activities;

import android.os.Bundle;

import com.facebook.stetho.Stetho;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
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
        int [] categories = new int[numberOfCategories + 1]; //Array Counts Categories


        //Log.i("ID:", Integer.toString(dataset.getCourseId()));

        for(Lecture l : pieDataset)
        {
            //todo if abfrage, ob kurs bestanden wurde
            //todo mit credit points multiplizieren
            categories[l.getCategoryId()]++;

        }


        List<PieEntry> pieChartEntry = new ArrayList<>(); //list of entrys
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setEntryLabelColor(R.color.colorGrey);
        pieChart.setEntryLabelTextSize(10.0f);

        //Add Data
        for(int i = 1; i<=numberOfCategories; i++)
        {
            if(categories[i]>0)
            {
                float value = (float) categories[i];
                pieChartEntry.add(new PieEntry(value, controller.getCategorieNameByID(i)));
            }
        }

        //((float) categories[i]/ (float) piechartentry.size())*100

        /*piechartentry.add(new PieEntry(26.7f, "Medien"));
        piechartentry.add(new PieEntry(24.0f, "Allgemein"));
        piechartentry.add(new PieEntry(30.8f, "Sonstiges"));*/


        // all the chart settings
        PieDataSet pieEntrySet = new PieDataSet(pieChartEntry, "Übersicht");
        PieData data2 = new PieData(pieEntrySet);
        pieChart.setData(data2);

        pieEntrySet.setColors(ColorTemplate.VORDIPLOM_COLORS); //color of chart
        pieChart.setUsePercentValues(true);

        Description description = new Description();
        description.setText("Deine bisherigen Leistungen nach Kategorien");
        pieChart.setDescription(description);
        //pieChart.setPadding(0,0,0,0);
        //pieChart.setCenterText("Dein Studium");
        //pieChart.setHoleRadius(50.0f);
        //pieChart.setTransparentCircleRadius(25.0f); //size of transparence inner circle
        /*pieChart.setHoleColor(R.color.colorTransparentWhite);*/
        pieChart.setDrawHoleEnabled(false);
        pieChart.setTransparentCircleAlpha(200); //transparence of inner circle
        Legend legend = pieChart.getLegend();
        legend.setFormSize(10f); // set the size of the legend forms/shapes
        legend.setForm(Legend.LegendForm.SQUARE); // set what type of form/shape should be used
        legend.setTextSize(14f);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTextColor(R.color.colorGrey);
        legend.setWordWrapEnabled(true);

        pieChart.animateXY(500, 500); // animate horizontal and vertical 500 milliseconds
        pieChart.invalidate(); // refresh

        return true;
    }


}
