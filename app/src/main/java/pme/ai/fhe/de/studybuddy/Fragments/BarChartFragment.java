package pme.ai.fhe.de.studybuddy.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.activities.Overview;
import pme.ai.fhe.de.studybuddy.administration.DataController;

public class BarChartFragment extends Fragment {
    // Store instance variables

    BarChart barView;
    private DataController controller;

    // newInstance constructor for creating fragment with arguments
    public static BarChartFragment newInstance(int page, String title) {
        BarChartFragment fragmentFirst = new BarChartFragment();
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


        Overview oV = (Overview) getActivity();
        controller = oV.controller;

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bar_chart, container, false);

        barView = (BarChart) view.findViewById(R.id.barchart);
        loadBarChart();

        return view;
    }

    void loadBarChart()
    {

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 7f));
        entries.add(new BarEntry(1f, 4f));
        entries.add(new BarEntry(2f, 3f));
        entries.add(new BarEntry(3f, 9f));

        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        barView.setData(data);
        barView.setFitBars(true); // make the x-axis fit exactly all bars
        barView.invalidate(); // refresh

    }
}