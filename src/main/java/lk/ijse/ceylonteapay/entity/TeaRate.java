package lk.ijse.ceylonteapay.entity;

public class TeaRate {
    private int rateId;
    private String month;
    private int year;
    private double ratePerKg;

    public TeaRate(int rateId, String month, int year, double ratePerKg) {
        this.rateId = rateId;
        this.month = month;
        this.year = year;
        this.ratePerKg = ratePerKg;
    }

    public TeaRate() {
    }

    public TeaRate(String month, int year, double rate) {
        this.month = month;
        this.year = year;
        this.ratePerKg = rate;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRatePerKg() {
        return ratePerKg;
    }

    public void setRatePerKg(double ratePerKg) {
        this.ratePerKg = ratePerKg;
    }
}
