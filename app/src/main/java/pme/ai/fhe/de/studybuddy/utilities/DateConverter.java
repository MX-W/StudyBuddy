package pme.ai.fhe.de.studybuddy.utilities;

import android.arch.persistence.room.TypeConverter;

import java.util.GregorianCalendar;

public class DateConverter {

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

    @TypeConverter
    public static Long millisToTimestamp(GregorianCalendar calendar) {
        return calendar == null ? null : calendar.getTimeInMillis();
    }
}
