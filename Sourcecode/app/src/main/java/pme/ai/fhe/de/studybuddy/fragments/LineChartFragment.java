package pme.ai.fhe.de.studybuddy.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.activities.OverviewActivity;
import pme.ai.fhe.de.studybuddy.administration.DataController;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.UserData;
import pme.ai.fhe.de.studybuddy.utilities.XAxisValueFormatterWithStringArray;


/**
 * The class handles a fragment, which creates a line with userspecific data out of the database.
 * It shows the credit points the user reached every semester compared with the count of credits he should have
 */
public class LineChartFragment extends Fragment {

    LineChart lineView;
    private DataController controller;


    /**
     * @param page page of the fragment
     * @param title title of the fragment
     * @return returns the fragment
     */
    public static LineChartFragment newInstance(int page, String title) {
        LineChartFragment fragmentFirst = new LineChartFragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed

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
     * Inflate the view for the fragment based on layout XML
     * @param inflater the inflater used in the context
     * @param container the conteiner used in the context
     * @param savedInstanceState the actual state of the instances
     * @return returns the view that the function has created
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_line_chart, container, false);

        lineView = (LineChart) view.findViewById(R.id.linechart);
        loadLineChart();
        return view;
    }


    /**
     * The function loadLineChart is called when the view of the fragment is created. It loads the data out of the database
     * and sets all configurations for the chart.
     */
    private void loadLineChart()
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
                    if(l.getGrade() <= 4.0) {
                        allCredits += l.getCredits();
                    }
                }

            }

            semester = new Entry(i+1, allCredits);
            userCredits.add(semester);

            normalCreditsPerSemester += 30;
            normalCredits = new Entry(i+1,normalCreditsPerSemester);
            standardCredits.add(normalCredits);
        }



        LineDataSet setComp1 = new LineDataSet(userCredits, "Deine CP");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineDataSet setComp2 = new LineDataSet(standardCredits, "Reguläre CP");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);

        int color1 = ContextCompat.getColor(getActivity(), R.color.colorAccent);
        int color2 = ContextCompat.getColor(getActivity(), R.color.colorPrimary);

        setComp1.setColor(color1);
        setComp2.setColor(color2);

        lineView.setTouchEnabled(false);
        lineView.setNoDataText("Noch keine bestandene Prüfung");



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

        yAxisRight.setGranularity(30);
        yAxisRight.setGranularityEnabled(true);
        yAxisLeft.setGranularity(30);
        yAxisLeft.setGranularityEnabled(true); // Required to enable granularity


        LineData data = new LineData(dataSets);

        if(allCredits > 0)
        {
            lineView.setData(data);

        }
        lineView.invalidate(); // refresh
    }
}