package pme.ai.fhe.de.studybuddy.utilities;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import pme.ai.fhe.de.studybuddy.R;

public class AddSpinnerItems {

    public static ArrayAdapter<String> setArrayAdapter(Context context, List<String> itemList) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                R.layout.simple_custom_spinner_item, itemList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return dataAdapter;
    }
}
