package pme.ai.fhe.de.studybuddy.utilities;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * The ChartValueFormatter Class is an Utility Class. It formattes the value of the x-Axis and the y-Axis
 */
public class ChartValueFormatter implements IValueFormatter {

    private DecimalFormat mFormat;

    /**
     * The constructor make a new format, which only use one decimal
     */
    public ChartValueFormatter() {
        mFormat = new DecimalFormat("###,###,##0"); // use one decimal
    }

    /**
     *
     * @param value The specific value, which should ne formatted
     * @param entry Entry, where the value is from
     * @param dataSetIndex The index of the dataset
     * @param viewPortHandler The viewPortHandler which is actually used
     * @return return the formatted Value as a String
     */
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        // write your logic here
        return mFormat.format(value) + "x";
    }
}
