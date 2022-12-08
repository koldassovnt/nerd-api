package kz.nkoldassov.nerdapi.util;

import lombok.NonNull;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;

public class DateUtil {

    public static final long SEC_IN_MILLIS = 1000L;
    public static final long MINUTE_IN_MILLIS = SEC_IN_MILLIS * 60;
    public static final long HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60;
    public static final long DAY_IN_MILLIS = HOUR_IN_MILLIS * 24;

    public static String millisToStr(long millis) {
        List<String> ret = new ArrayList<>();

        long days = millis / DAY_IN_MILLIS;
        ret.add(days + "дн");

        millis -= days * DAY_IN_MILLIS;

        long hours = millis / HOUR_IN_MILLIS;
        ret.add(hours + "ч");

        millis -= hours * HOUR_IN_MILLIS;

        long minutes = millis / MINUTE_IN_MILLIS;
        ret.add(minutes + "м");

        millis -= minutes * MINUTE_IN_MILLIS;

        long seconds = millis / SEC_IN_MILLIS;
        ret.add(seconds + "с");

        millis -= seconds * SEC_IN_MILLIS;

        ret.add(millis + "мс");

        while (true) {

            if (ret.isEmpty()) {
                return "0";
            }

            if (ret.get(0).charAt(0) == '0') {
                ret.remove(0);
            }

            if (ret.isEmpty()) {
                return "0";
            }

            if (ret.get(ret.size() - 1).charAt(0) == '0') {
                ret.remove(ret.size() - 1);
            } else {
                return String.join(" ", ret);
            }

        }
    }

    public static boolean isWeekend(Calendar calendar) {
        var weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return weekDay == Calendar.SUNDAY || weekDay == Calendar.SATURDAY;
    }

    public static TimeZone jsOffsetInMinutesToTimeZone(int jsOffsetInMinutes) {
        int rightOffset = -jsOffsetInMinutes;

        int sign = 1;
        if (rightOffset < 0) {
            sign = -1;
            rightOffset = -rightOffset;
        }

        int offsetInHours = rightOffset / 60;
        int offsetInMinutes = rightOffset % 60;

        var zoneId = ZoneId.ofOffset("GMT", ZoneOffset.ofHoursMinutes(sign * offsetInHours, sign * offsetInMinutes));

        return TimeZone.getTimeZone(zoneId.getId());

    }

    @SuppressWarnings("SpellCheckingInspection")
    public static final String UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    public static final String DATE_FORMAT = "dd.MM.yyyy";

    public static TimeZone utc() {
        return TimeZone.getTimeZone("UTC");
    }

    public static String toUTC(Date dateTime) {
        if (dateTime == null) {
            return null;
        }
        var sdf = new SimpleDateFormat(UTC_FORMAT);
        sdf.setTimeZone(utc());
        return sdf.format(dateTime);
    }

    public static Optional<Date> parseStrictFormat(String dateTimeStrictFormatted) {
        if (dateTimeStrictFormatted == null) {
            return Optional.empty();
        }

        try {
            return Optional.of(new SimpleDateFormat(UTC_FORMAT).parse(dateTimeStrictFormatted));
        } catch (ParseException e) {
            return Optional.empty();
        }
    }

    @SuppressWarnings("DuplicatedCode")
    public static Date loadStartOfYear(int year) {
        var calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date loadEndOfYear(int year) {
        var calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    @SuppressWarnings("DuplicatedCode")
    public static Date loadStartOfMonth(int year, int month) {
        var calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    @SuppressWarnings("DuplicatedCode")
    public static Date loadEndOfMonth(int year, int month) {
        var calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static String weekDayNameByNumber(int weekDayNumber) {
        return switch (weekDayNumber) {
            case 0 -> "Воскресенье";
            case 1 -> "Понедельник";
            case 2 -> "Вторник";
            case 3 -> "Среда";
            case 4 -> "Четверг";
            case 5 -> "Пятница";
            case 6 -> "Суббота";
            default -> "";
        };
    }

    public static Date addMinutes(@NonNull Date date, int minutes, @NonNull TimeZone timeZone) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeZone(timeZone);
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public static String dateTimeDDMMYYYHHMMSS(Date date) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return df.format(date);
    }

    public static String dateTimeDDMMYYYHHMM(Date date) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return df.format(date);
    }

    public static String dateTimeDDMMYYY(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(date);
    }

    public static String period(Date from, Date to) {
        return dateTimeDDMMYYYHHMMSS(from) + " - " + dateTimeDDMMYYYHHMMSS(to);
    }

    public static Date start(Date date, TimeZone timeZone) {
        var cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        var time = cal.getTime();
        var dif = timeZone.getOffset(date.getTime());
        return new Date(time.getTime() - dif);
    }

    public static Date end(Date date, TimeZone timeZone) {
        var cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        var time = cal.getTime();
        var dif = timeZone.getOffset(date.getTime());
        return new Date(time.getTime() - dif);
    }

    @SuppressWarnings("Duplicates")
    public static Date cutTime(Date d) {
        return toBeginOfDay(d);
    }

    public static Date startSec(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();

    }

    public static Date endSec(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 1000);
        return cal.getTime();

    }

    @SuppressWarnings("Duplicates")
    public static Date toBeginOfDay(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date minusDays(Date date, int days) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }


    public static Date addSecond(Date date, int second) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    public static Date addMillisecond(Date date, int millis) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.MILLISECOND, millis);
        return cal.getTime();
    }

    public static Date minusSecond(Date date, int second) {
        return addSecond(date, -second);
    }

    public static Timestamp toBeginDay(Date date, TimeZone timeZone) {
        if (date == null) {
            return null;
        }
        Objects.requireNonNull(timeZone, "timeZone");
        var calendar = new GregorianCalendar();
        calendar.setTimeZone(timeZone);
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return new Timestamp(calendar.getTime().getTime());
    }

    public static Timestamp toEndDay(Date date, TimeZone timeZone) {
        if (date == null) {
            return null;
        }
        Objects.requireNonNull(timeZone, "timeZone");
        var calendar = new GregorianCalendar();
        calendar.setTimeZone(timeZone);
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        return new Timestamp(calendar.getTime().getTime());
    }

    public static String formatByPatternOrNull(String date, String pattern) {
        if (date == null) {
            return null;
        }

        var formatted = date.trim();

        try {
            var formatter = DateTimeFormatter.ofPattern(pattern)
                    .withResolverStyle(ResolverStyle.SMART);
            formatted = formatter.format(formatter.parse(formatted));
        } catch (DateTimeException e) {
            return null;
        }

        return formatted;
    }

}
