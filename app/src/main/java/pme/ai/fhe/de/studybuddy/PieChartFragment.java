package pme.ai.fhe.de.studybuddy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import pme.ai.fhe.de.studybuddy.administration.DataController;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.UserData;

public class PieChartFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;

    PieChart pieView;

    public DataController controller;


    // newInstance constructor for creating fragment with arguments
    public static PieChartFragment newInstance(int page, String title) {
        PieChartFragment fragmentFirst = new PieChartFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

        controller = DataController.getInstance(getActivity().getApplication());



    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);

        pieView = (PieChart) view.findViewById(R.id.piechart);
        //TextView tvLabel = (TextView) view.findViewById(R.id.tvLabelPie);
        //tvLabel.setText(page + " -- " + title);
        loadPieChart();
        return view;
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
        //PieChart pieChart = (PieChart) findViewById(R.id.piechart);

        pieView.setEntryLabelColor(R.color.colorGrey);
        pieView.setEntryLabelTextSize(10.0f);

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
        pieView.setData(data2);

        pieEntrySet.setColors(ColorTemplate.VORDIPLOM_COLORS); //color of chart
        pieView.setUsePercentValues(true);

        Description description = new Description();
        description.setText("Deine bisherigen Leistungen nach Kategorien");
        pieView.setDescription(description);
        //pieChart.setPadding(0,0,0,0);
        //pieChart.setCenterText("Dein Studium");
        //pieChart.setHoleRadius(50.0f);
        //pieChart.setTransparentCircleRadius(25.0f); //size of transparence inner circle
        /*pieChart.setHoleColor(R.color.colorTransparentWhite);*/
        pieView.setDrawHoleEnabled(false);
        pieView.setTransparentCircleAlpha(200); //transparence of inner circle
        Legend legend = pieView.getLegend();
        legend.setFormSize(10f); // set the size of the legend forms/shapes
        legend.setForm(Legend.LegendForm.SQUARE); // set what type of form/shape should be used
        legend.setTextSize(14f);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTextColor(R.color.colorGrey);
        legend.setWordWrapEnabled(true);

        pieView.animateXY(500, 500); // animate horizontal and vertical 500 milliseconds
        pieView.invalidate(); // refresh

        return true;
    }
}