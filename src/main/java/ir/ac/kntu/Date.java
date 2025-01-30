package ir.ac.kntu;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    private int hours;
    private int minutes;
    private int second;

    public Date() {

    }

    public Date(int day, int year, int month, int hours, int minutes, int second) {
        setDay(day);
        setMonth(month);
        setYear(year);
        setHours(hours);
        setMinutes(minutes);
        setSecond(second);
    }

    public Date(String year, String month, String day) {
        setYear(Integer.parseInt(year));
        setMonth(Integer.parseInt(month));
        setDay(Integer.parseInt(day));
        setHours(0);
        setMinutes(0);
        setSecond(0);
    }

    public int getHours() {
        return this.hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
        if (hours > 23) {
            this.hours = 23;
        }
    }

    public int getMinutes() {
        return this.minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSecond() {
        return this.second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
        if (day > 31) {
            this.day = 31;
        }
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
        if (month > 12) {
            this.month = 12;
        }
    }

    @Override
    public int compareTo(Date date) {
        Date date1 = (Date) date;
        if (this.year != date1.year) {
            return year - date1.year;
        }
        if (this.month != date1.month) {
            return month - date1.month;
        }
        if (this.day != date1.day) {
            return day - date1.day;
        }
        if (this.hours != date1.hours) {
            return hours - date1.hours;
        }
        if (this.minutes != date1.minutes) {
            return minutes - date1.minutes;
        }
        return second - date1.second;

    }

    public int sub() {
        return this.getYear() * 365 + this.getMonth() * 30 + this.getDay();
    }

    @Override
    public String toString() {
        return "{" +
                " day='" + getDay() + "'" +
                ", year='" + getYear() + "'" +
                ", month='" + getMonth() + "'" +
                ", hours='" + getHours() + "'" +
                ", minutes='" + getMinutes() + "'" +
                ", second='" + getSecond() + "'" +
                "}";
    }

}
