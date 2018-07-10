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


        Entry semester0 = new Entry(0f, 0);
        userCredits.add(semester0);
        Entry semester1 = new Entry(1f, 23);
        userCredits.add(semester1);
        Entry semester2 = new Entry(2f, 54);
        userCredits.add(semester2);
        Entry semester3 = new Entry(3f, 89);
        userCredits.add(semester3);
        Entry semester4 = new Entry(4f, 122);
        userCredits.add(semester4);
        Entry semester5 = new Entry(5f, 145);
        userCredits.add(semester5);
        Entry semester6 = new Entry(6f, 170);
        userCredits.add(semester6);




        Entry normalCreditsSem0 = new Entry(0f, 0);
        standardCredits.add(normalCreditsSem0);

        Entry normalCreditsSem1 = new Entry(1f, 30);
        standardCredits.add(normalCreditsSem1);

        Entry normalCreditsSem2 = new Entry(2f, 60);
        standardCredits.add(normalCreditsSem2);

        Entry normalCreditsSem3 = new Entry(3f, 90);
        standardCredits.add(normalCreditsSem3);

        Entry normalCreditsSem4 = new Entry(4f, 120);
        standardCredits.add(normalCreditsSem4);

        Entry normalCreditsSem5 = new Entry(5f, 150);
        standardCredits.add(normalCreditsSem5);

        Entry normalCreditsSem6 = new Entry(6f, 180);
        standardCredits.add(normalCreditsSem6);



        LineDataSet setComp1 = new LineDataSet(userCredits, "Deine erreichten Credit Points");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineDataSet setComp2 = new LineDataSet(standardCredits, "Ziel der Credits Points");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);

        setComp1.setColors(R.color.colorAccent);
        setComp2.setColors(R.color.colorRed);

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




        LineData data = new LineData(dataSets);
        lineView.setData(data);
        lineView.invalidate(); // refresh
    }
}