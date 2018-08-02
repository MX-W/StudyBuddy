package pme.ai.fhe.de.studybuddy.utilities;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Class for inscribing the X-axis value labels user specific. A object of this class is
 * handed over to every chart, to customize the axis.
 */
public class XAxisValueFormatterWithStringArray implements IAxisValueFormatter {

    private String[] values;

    /**
     *
     * @param values Array of Strings that contains the axis labels
     */
    public XAxisValueFormatterWithStringArray(String[] values) {
        this.values = values;
    }

    /**
     * Gets called internally from the Chart-API.
     * @param value which axis index
     * @param axis which axis
     * @return axis label
     */
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return values[(int) value];
    }

}
