package app.entities.unitconverter;

public class TimeConverter
{
    public static double secondsToMinutes(double seconds)
        {
        return seconds / 60;
        }

    public static double secondsToHours(double seconds)
        {
        return seconds / 3600;
        }

    public static double secondsToDays(double seconds)
        {
        return seconds / 86400;
        }

    public static double secondsToWeeks(double seconds)
        {
        return seconds / 604800;
        }

    public static double minutesToSeconds(double minutes)
        {
        return minutes * 60;
        }

    public static double minutesToHours(double minutes)
        {
        return minutes / 60;
        }

    public static double minutesToDays(double minutes)
        {
        return minutes / 1440;
        }

    public static double minutesToWeeks(double minutes)
        {
        return minutes / 10080;
        }

    public static double hoursToSeconds(double hours)
        {
        return hours * 3600;
        }

    public static double hoursToMinutes(double hours)
        {
        return hours * 60;
        }

    public static double hoursToDays(double hours)
        {
        return hours / 24;
        }

    public static double hoursToWeeks(double hours)
        {
        return hours / 168;
        }

    public static double daysToSeconds(double days)
        {
        return days * 86400;
        }

    public static double daysToMinutes(double days)
        {
        return days * 1440;
        }

    public static double daysToHours(double days)
        {
        return days * 24;
        }

    public static double daysToWeeks(double days)
        {
        return days / 7;
        }

    public static double weeksToSeconds(double weeks)
        {
        return weeks * 604800;
        }

    public static double weeksToMinutes(double weeks)
        {
        return weeks * 10080;
        }

    public static double weeksToHours(double weeks)
        {
        return weeks * 168;
        }

    public static double weeksToDays(double weeks)
        {
        return weeks * 7;
        }


}