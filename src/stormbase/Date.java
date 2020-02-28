package stormbase;

public class Date {
	int day, month, yr;

    Date(int d, int m, int yr){
        this.day=d;
        this.month=m;
        this.yr=yr;
    }
    Date() {
		this.day=1;
        this.month=1;
        this.yr=2020;
	}
    Date(Date ent) {
		this.day=ent.day;
        this.month=ent.month;
        this.yr=ent.yr;
	}
	public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYr() {
        return yr;
    }

    public void setYr(int yr) {
        this.yr = yr;
    }

    @Override
    public String toString() {
        return  day +
                "/" + month +
                "/" + yr;
    }
}
