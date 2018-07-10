package pme.ai.fhe.de.studybuddy.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.activities.Overview;
import pme.ai.fhe.de.studybuddy.administration.DataController;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.UserData;
import pme.ai.fhe.de.studybuddy.utilities.XAxisValueFormatterWithStringArray;


public class LineChartFragment extends Fragment {

    LineChart lineView;
    private DataController controller;


    // newInstance constructor for creating fragment with arguments
    public static LineChartFragment newInstance(int page, String title) {
        LineChartFragment fragmentFirst = new LineChartFragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Overview oV = (Overview) getActivity();
        controller = oV.controller;

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_line_chart, container, false);

        lineView = (LineChart) view.findViewById(R.id.linechart);
        loadLineChart();
        return view;
    }

    void loadLineChart()
    {

        List<Entry> userCredits = new ArrayList<Entry>();
        List<Entry> standardCredits = new ArrayList<Entry>();

        UserData dataset = controller.getUserData(); //gets all User Data


        Entry semester = new Entry(0f, 0);
        userCredits.add(semester);
        Entry normalCredits = new Entry(0f, 0);
        standardCredits.add(normalCredits);

        int normalCreditsPerSemester = 0;
        int allCredits = 0;

        List<Lecture> pieDataset = controller.getAllLecturesWithGrade(dataset.getCourseId()); //gets List with All Courses from User


        for(int i = 0; i < dataset.getSemester(); i++)
        {


            for(Lecture l : pieDataset)
            {
                if(l.getSemesterPassed() == dataset.getCurrentSemesterId()-dataset.getSemester()+i+1)
                {
                    allCredits += l.getCredits();
                }

            }

            semester = new Entry(i+1, allCredits);
            userCredits.add(semester);

            normalCreditsPerSemester += 30;
            normalCredits = new Entry(i+1,normalCreditsPerSemester);
            standardCredits.add(normalCredits);
        }



        LineDataSet setComp1 = new LineDataSet(userCredits, "Deine erreichten Credit Points");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineDataSet setComp2 = new LineDataSet(standardCredits, "Ziel der Credits Points");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);

        setComp1.setColors(R.color.colorAccent);
        setComp2.setColors(R.color.colorRed);

        lineView.setTouchEnabled(false);


        // use the interface ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);

        //Set description
        Description description = new Description();
        description.setText(" ");
        lineView.setDescription(description);


        //Format the Axis
        String[] xAxisValues = new String[] {"", "Sem. 1", "Sem. 2", "Sem. 3", "Sem. 4", "Sem. 5", "Sem. 6"};

        XAxis xAxis = lineView.getXAxis();
        xAxis.setGranularity(1.0f);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new XAxisValueFormatterWithStringArray(xAxisValues));

        YAxis yAxisLeft = lineView.getAxisLeft();
        YAxis yAxisRight = lineView.getAxisRight();

        //todo
        yAxisRight.setGranularity(30);
        yAxisRight.setGranularityEnabled(true);
        yAxisLeft.setGranularity(30);
        yAxisLeft.setGranularityEnabled(true); // Required to enable granularity




        LineData data = new LineData(dataSets);
        lineView.setData(data);
        lineView.invalidate(); // refresh
    }
}