package pme.ai.fhe.de.studybuddy.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.Module;

/**
 * This Activity shows all modules whith their courses in a table
 */
public class ModulesActivity extends MenuActivity {

    private TableLayout tableLayout;

    /**
     * connects the layout with the xml files and opens the menu
     * also the table is updated and makes the colordesign of the table
     * @param savedInstanceState reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
        setTitle("Modulübersicht");

        setupTableLayout();

        tableLayout = (TableLayout) findViewById(R.id.tableElement);

        List<Module> allModules = controller.getModulesByCourseId(controller.getUserData().getCourseId());

        for (Module module : allModules) {

            TextView placeholder = new TextView(this);
            placeholder.setHeight(8);

            tableLayout.addView(placeholder);

            updateTable("", module.getName(), module.getCredits(), getResources().getColor(R.color.colorDarkerGrey), Typeface.BOLD);
            List<Lecture> lecturesForModule = controller.getLectureByModuleId(module.getModuleId());

            for( int i = 0; i < lecturesForModule.size(); i++) {
                int color = 0;
                String gradeString = "";
                Lecture lecture = lecturesForModule.get(i);
                float grade = lecture.getGrade();

                if(i%2 == 0) {
                    color = getResources().getColor(R.color.colorLightGrey);
                } else {
                    color = getResources().getColor(R.color.colorMiddleGrey);
                }
                if(grade == -1.0) {
                    gradeString = "bestanden";
                } else if(grade != 0.0f) {
                    gradeString = String.valueOf(lecture.getGrade());
                } else {
                    gradeString = "";
                }

                updateTable(gradeString, lecture.getName(), lecture.getCredits(), color, Typeface.NORMAL);
            }

        }

        openMenu();
    }


    /**
     * layouts the table with the used configurations
     */
    private void setupTableLayout() {
        findViewById(R.id.tableSemester).setVisibility(View.GONE);
        findViewById(R.id.tableSum).setVisibility(View.GONE);
        findViewById(R.id.tableSecondHorizontalLine).setVisibility(View.GONE);

        TextView headingLeft = (TextView) findViewById(R.id.tableLecture);
        headingLeft.setText("Modul/Veranstaltung");
        headingLeft.setTypeface(null, Typeface.BOLD);

        TextView headingCenter = (TextView) findViewById(R.id.tableCredits);
        headingCenter.setGravity(Gravity.START);
        headingCenter.setTypeface(null, Typeface.BOLD);

        TextView headingRight = (TextView) findViewById(R.id.tableGrade);
        headingRight.setTypeface(null, Typeface.BOLD);
    }

    /**
     *
     * @param gradeText grade in textform
     * @param lectureName name of the actual lecture
     * @param credits credits which you get after passing
     * @param background background color of the row
     * @param fontStyle style of the font of the row
     */
    private void updateTable(String gradeText, String lectureName, int credits, int background, int fontStyle) {

        TableRow newRow = new TableRow(this);
        newRow.setBackgroundColor(background);

        TextView lectureNameView = new TextView(this);
        lectureNameView.setText(lectureName);
        lectureNameView.setPadding(3,3,3,3);
        lectureNameView.setTextColor(getResources().getColor(R.color.colorBlack));
        lectureNameView.setTypeface(null, fontStyle);

        TextView lectureCreditsView = new TextView(this);
        lectureCreditsView.setText(String.valueOf(credits));
        lectureCreditsView.setPadding(3,3,3,3);
        lectureCreditsView.setGravity(Gravity.END);
        lectureCreditsView.setTextColor(getResources().getColor(R.color.colorBlack));
        lectureCreditsView.setTypeface(null, fontStyle);

        TextView gradeView = new TextView(this);
        gradeView.setText(gradeText);
        gradeView.setPadding(3,3,3,3);
        gradeView.setGravity(Gravity.END);
        gradeView.setTextColor(getResources().getColor(R.color.colorBlack));
        gradeView.setTypeface(null, fontStyle);

        newRow.addView(lectureNameView);
        newRow.addView(lectureCreditsView);
        newRow.addView(gradeView);

        tableLayout.addView(newRow);
    }
}
