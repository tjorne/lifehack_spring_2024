package app.entities;

public class Timeslot {
public String time;
    public String date;
    public Boolean booked;

    public static int counter = 0;
    public Timeslot(String _time, String _date, Boolean _booked) {
        time = _time;
        date = _date;
        booked = _booked;
        counter++;
        System.out.println(counter);
    }
}
