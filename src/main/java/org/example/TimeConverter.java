package org.example;

import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class TimeConverter {
    public static TimeZone parseTimeZoneOffset(String offset) {
        if (offset.startsWith("UTC ") || offset.startsWith("UTC+")) {
            Integer hoursOffset = Integer.parseInt(offset.substring(4));
            if (hoursOffset != null) {
                int offsetMillis = hoursOffset * 60 * 60 * 1000;
                return new SimpleTimeZone(offsetMillis, offset);
            }
        } else if (offset.startsWith("UTC-")) {
            Integer hoursOffset = Integer.parseInt(offset.substring(4));
            if (hoursOffset != null) {
                int offsetMillis = -hoursOffset * 60 * 60 * 1000;
                return new SimpleTimeZone(offsetMillis, offset);
            }
        }

        return TimeZone.getTimeZone(offset);
    }
}