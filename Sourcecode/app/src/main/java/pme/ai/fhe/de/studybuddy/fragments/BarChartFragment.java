package pme.ai.fhe.de.studybuddy.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.activities.OverviewActivity;
import pme.ai.fhe.de.studybuddy.administration.DataController;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.UserData;
import pme.ai.fhe.de.studybuddy.utilities.ChartValueFormatter;
import pme.ai.fhe.de.studybuddy.utilities.XAxisValueFormatterWithStringArray;

/**
 * The class handles a fragment, which creates a barchart with userspecific data out of the database.
 * It describes the quantity of each kind of grades, that the user passed
 */
public class BarChartFragment extends Fragment {

    // Store instance variables
    BarChart barView;
    private DataController controller;

    /**
     * @param page page of the fragment
     * @param title title of the fragment
     * @return returns the fragment
     */
    public static BarChartFragment newInstance(int page, String title) {
        BarChartFragment fragmentFirst = new BarChartFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }


    /**
     * Store instance variables based on arguments passed
     * @param savedInstanceState the actual state of the instances
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        OverviewActivity oV = (OverviewActivity) getActivity();
        controller = oV.controller;

    }

    /**
     *
     * @param inflater the inflater used in the context
     * @param container the conteiner used in the context
     * @param savedInstanceState the actual state of the instances
     * @return returns the view that the function has created
     */
    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bar_chart, container, false);

        barView = (BarChart) view.findViewById(R.id.barchart);
        loadBarChart();

        return view;
    }


    /**
     * The function loadBarChart is called when the view of the fragment is created. It loads the data out of the database
     * and sets all configurations for the chart.
     */
    private void loadBarChart()
    {


        //get all the data for the chart
        UserData dataset = controller.getUserData(); //gets all User Data
        List<Lecture> lecturesWithGrades = controller.getAllLecturesWithGrade(dataset.getCourseId()); //gets List with All graded Courses from User
        int [] grades = new int[5]; //Array Counts numbers of each grade

        for(Lecture l : lecturesWithGrades) //count of each grade and saved in array
        {
            if(l.getGrade() > 0 && l.getGrade() < 1.56)
            {
                grades[0]++;
            }
            else if(l.getGrade() < 2.56)
            {
                grades[1]++;
            }
            else if(l.getGrade() < 3.56)
            {
                grades[2]++;
            }
            else if(l.getGrade() < 4.01)
            {
                grades[3]++;
            }
            else if (l.getGrade() > 4.0)
            {
                grades[4]++;
            }
        }

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, grades[0]));
        entries.add(new BarEntry(1f, grades[1]));
        entries.add(new BarEntry(2f, grades[2]));
        entries.add(new BarEntry(3f, grades[3]));
        entries.add(new BarEntry(4f, grades[4]));


        BarDataSet set = new BarDataSet(entries, "Jeweils die Anzahl deiner bisher erreichten Notenstufen");


        int color1 = ContextCompat.getColor(getActivity(), R.color.colorAccent);
        set.setColor(color1);


        barView.setTouchEnabled(false);

        set.setValueFormatter(new ChartValueFormatter());


        //Set the right Values for both Axes
        YAxis yAxisLeft = barView.getAxisLeft();
        YAxis yAxisRight = barView.getAxisRight();

        yAxisRight.setGranularity(1.0f);
        yAxisRight.setGranularityEnabled(true);
        yAxisLeft.setGranularity(1.0f);
        yAxisLeft.setGranularityEnabled(true); // Required to enable granularity

        String[] xAxisValues = new String[] {"Note 1", "Note 2", "Note 3", "Note 4", "Note 5"};

        XAxis xAxis = barView.getXAxis();
        xAxis.setGranularity(1.0f);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new XAxisValueFormatterWithStringArray(xAxisValues));


        //set Description
        Description description = new Description();
        description.setText(" ");
        barView.setDescription(description);
        barView.setNoDataText("Noch keine bestandene Prüfung");



        //Set the chart
        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width

        //animation
        barView.animateXY( 500, 500);

        int count = 0;
        for(int i = 0; i<4;i++)
        {
            count += grades[i];
        }
        if(count>0)
        {
            barView.setData(data);
        }

        barView.setFitBars(true); // make the x-axis fit exactly all bars
        barView.invalidate(); // refresh

    }




}