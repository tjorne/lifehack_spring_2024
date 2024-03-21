package app.entities.unitconverter;

public class TimeConverter
{
    public double secondsToMinutes(double seconds)
        {
        return seconds / 60;
        }

    public double secondsToHours(double seconds)
        {
        return seconds / 3600;
        }

    public double secondsToDays(double seconds)
        {
        return seconds / 86400;
        }

    public double secondsToWeeks(double seconds)
        {
        return seconds / 604800;
        }

    public double minutesToSeconds(double minutes)
        {
        return minutes * 60;
        }

    public double minutesToHours(double minutes)
        {
        return minutes / 60;
        }

    public double minutesToDays(double minutes)
        {
        return minutes / 1440;
        }

    public double minutesToWeeks(double minutes)
        {
        return minutes / 10080;
        }

    public double hoursToSeconds(double hours)
        {
        return hours * 3600;
        }

    public double hoursToMinutes(double hours)
        {
        return hours * 60;
        }

    public double hoursToDays(double hours)
        {
        return hours / 24;
        }

    public double hoursToWeeks(double hours)
        {
        return hours / 168;
        }

    public double daysToSeconds(double days)
        {
        return days * 86400;
        }

    public double daysToMinutes(double days)
        {
        return days * 1440;
        }

    public double daysToHours(double days)
        {
        return days * 24;
        }

    public double daysToWeeks(double days)
        {
        return days / 7;
        }

    public double weeksToSeconds(double weeks)
        {
        return weeks * 604800;
        }

    public double weeksToMinutes(double weeks)
        {
        return weeks * 10080;
        }

    public double weeksToHours(double weeks)
        {
        return weeks * 168;
        }

    public double weeksToDays(double weeks)
        {
        return weeks * 7;
        }


}