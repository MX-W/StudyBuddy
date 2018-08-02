package pme.ai.fhe.de.studybuddy.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.activities.OverviewActivity;
import pme.ai.fhe.de.studybuddy.administration.DataController;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.UserData;

/**
* The class handles a fragment, which creates a line with userspecific data out of the database.
* It compares the ratio between the categories. It shows how many credit points the user reached in every category
*/
public class PieChartFragment extends Fragment {

    PieChart pieView;
    private DataController controller;



    /**
     * newInstance constructor for creating fragment with arguments
     * @param page page of the fragment
     * @param title title of the fragment
     * @return returns the fragment
     */
    public static PieChartFragment newInstance(int page, String title) {
        PieChartFragment fragmentFirst = new PieChartFragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    /**
     * Store instance variables based on arguments passed
     * @param savedInstanceState the actual state of the instances
     */    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OverviewActivity oV = (OverviewActivity) getActivity();
        controller = oV.controller;

    }

    /**
     * Inflate the view for the fragment based on layout XML
     * @param inflater the inflater used in the context
     * @param container the conteiner used in the context
     * @param savedInstanceState the actual state of the instances
     * @return returns the view that the function has created
     */    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);

        pieView = (PieChart) view.findViewById(R.id.piechart);
        loadPieChart();
        return view;
    }


    /**
     * The function loadPieChart is called when the view of the fragment is created. It loads the data out of the database
     * and sets all configurations for the chart.
     */
    private void loadPieChart()
    {


        UserData dataset = controller.getUserData(); //gets all User Data

        List<Lecture> pieDataset = controller.getAllLecturesWithGrade(dataset.getCourseId()); //gets List with All Courses from User

        int numberOfCategories = controller.getNumberOfCategories();
        int [] categories = new int[numberOfCategories + 1]; //Array Counts Categories

        for(Lecture l : pieDataset)
        {
            if(l.getGrade() <= 4.0) {
                categories[l.getCategoryId()]+=l.getCredits();
            }
        }


        List<PieEntry> pieChartEntry = new ArrayList<>(); //list of entrys

        pieView.setEntryLabelColor(R.color.colorLightGrey);
        pieView.setEntryLabelTextSize(11.0f);
        pieView.setNoDataText("Noch keine bestandene Prüfung");

        //Add Data
        for(int i = 1; i<=numberOfCategories; i++)
        {
            if(categories[i]>0)
            {
                float value = (float) categories[i];
                pieChartEntry.add(new PieEntry(value, controller.getCategorieNameByID(i)));
            }
        }


        // all the chart settings
        PieDataSet pieEntrySet = new PieDataSet(pieChartEntry, "Übersicht");
        PieData data2 = new PieData(pieEntrySet);

        int count = 0;
        for(int i = 0; i<numberOfCategories;i++)
        {
            count += categories[i];
        }
        if(count>0)
        {
            pieView.setData(data2);
        }

        pieEntrySet.setValueFormatter(new PercentFormatter());


        pieEntrySet.setColors(ColorTemplate.VORDIPLOM_COLORS); //color of chart
        pieView.setUsePercentValues(true);


        Description description = new Description();
        description.setText("Deine bisherigen Leistungen nach Kategorien");
        pieView.setDescription(description);
        pieView.setDrawHoleEnabled(false);
        pieView.setTransparentCircleAlpha(200); //transparence of inner circle
        Legend legend = pieView.getLegend();
        legend.setFormSize(10f); // set the size of the legend forms/shapes
        legend.setForm(Legend.LegendForm.SQUARE); // set what type of form/shape should be used
        legend.setTextSize(14f);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTextColor(R.color.colorLightGrey);
        legend.setWordWrapEnabled(true);

        pieView.setTouchEnabled(false);

        pieView.animateXY(500, 500); // animate horizontal and vertical 500 milliseconds
        pieView.invalidate(); // refresh

    }
}