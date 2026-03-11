package lk.ijse.ceylonteapay.entity;

public class Income {
    private int incomeId;
    private String month;
    private int year;
    private double teaSalary;
    private double otherWorkSalary;
    private double thisMonthIncome;
    private double finalIncome;

    public Income(int incomeId, String month, int year, double teaSalary, double otherWorkSalary, double thisMonthIncome, double finalIncome) {
        this.incomeId = incomeId;
        this.month = month;
        this.year = year;
        this.teaSalary = teaSalary;
        this.otherWorkSalary = otherWorkSalary;
        this.thisMonthIncome = thisMonthIncome;
        this.finalIncome = finalIncome;
    }

    public Income(String month, int year, double teaSalary, double otherWorkSalary, double thisMonthIncome, double finalIncome) {
        this.month = month;
        this.year = year;
        this.teaSalary = teaSalary;
        this.otherWorkSalary = otherWorkSalary;
        this.thisMonthIncome = thisMonthIncome;
        this.finalIncome = finalIncome;
    }

    public Income() {
    }

    public Income(double allTeaSalary, double allOtherWorkSalary) {
        this.teaSalary = allTeaSalary;
        this.otherWorkSalary = allOtherWorkSalary;
    }

    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
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

    public double getTeaSalary() {
        return teaSalary;
    }

    public void setTeaSalary(double teaSalary) {
        this.teaSalary = teaSalary;
    }

    public double getOtherWorkSalary() {
        return otherWorkSalary;
    }

    public void setOtherWorkSalary(double otherWorkSalary) {
        this.otherWorkSalary = otherWorkSalary;
    }

    public double getThisMonthIncome() {
        return thisMonthIncome;
    }

    public void setThisMonthIncome(double thisMonthIncome) {
        this.thisMonthIncome = thisMonthIncome;
    }

    public double getFinalIncome() {
        return finalIncome;
    }

    public void setFinalIncome(double finalIncome) {
        this.finalIncome = finalIncome;
    }
}
