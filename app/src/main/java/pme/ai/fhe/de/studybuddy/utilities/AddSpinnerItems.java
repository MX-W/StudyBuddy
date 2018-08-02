package pme.ai.fhe.de.studybuddy.utilities;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import pme.ai.fhe.de.studybuddy.R;


/**
 * The class AddSpinnerItems is a utility-class. It adds a List of items to an ArrayAdapter with
 * a specific layout so that the ArrayAdapter can be assigned to a view item spinner. This class
 * exists for code-reuse purposes.
 */
public class AddSpinnerItems {

    /**
     * Public method that creates an array adapter out of a given context and a list of items and
     * returns the adapter.
     * @param context Context in which the adapter should be used
     * @param itemList List of items to be assigned to the adapter
     * @return The array adapter which is assigned to a spinner
     */
    public static ArrayAdapter<String> setArrayAdapter(Context context, List<String> itemList) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                R.layout.simple_custom_spinner_item, itemList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return dataAdapter;
    }
}
