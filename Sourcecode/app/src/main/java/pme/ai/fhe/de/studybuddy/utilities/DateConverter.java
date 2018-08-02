package pme.ai.fhe.de.studybuddy.utilities;

import android.arch.persistence.room.TypeConverter;

import java.util.GregorianCalendar;


/**
 * This class exists because there is no possibility to store a Date-type in a Room Database.
 * So it was decided to store DateTime as milliseconds. This methods provide a chance to convert
 * between milliseconds and Date.
 * It is implemented in the models, where needed, through the @TypeConverter annotation.
 *
 * @see pme.ai.fhe.de.studybuddy.model.UserData#lastLogin
 */
public class DateConverter {

    /**
     * Method for converting a long into a the date type GregorianCalendar.
     * @param value Time in milliseconds (usually from database)
     * @return GregorianCalendar which represents the milliseconds as a date
     */
    @TypeConverter
    public static GregorianCalendar fromTimestamp(Long value) {
        if (value == null)
            return new GregorianCalendar();
        else {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(value);
            return calendar;
        }
    }

    /**
     * Method for converting the date type GregorianCalendar into long.
     * @param calendar GregorianCalendar which represents a specific date
     * @return The value of the transferred date in milliseconds
     */
    @TypeConverter
    public static Long millisToTimestamp(GregorianCalendar calendar) {
        return calendar == null ? null : calendar.getTimeInMillis();
    }
}
