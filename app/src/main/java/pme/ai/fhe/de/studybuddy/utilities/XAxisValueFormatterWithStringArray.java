package pme.ai.fhe.de.studybuddy.utilities;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class XAxisValueFormatterWithStringArray implements IAxisValueFormatter {

    private String[] mValues;

    public XAxisValueFormatterWithStringArray(String[] values) {
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mValues[(int) value];
    }

}
