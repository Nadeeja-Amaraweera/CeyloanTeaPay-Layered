package lk.ijse.ceylonteapay.entity;

import java.time.LocalDate;
import java.time.Month;

public class Payment {
    private int paymentId;
    private int rateId;
    private int empId;
    private String empName;
    private double teaSalary;
    private double expenseSalary;
    private double finalSalary;
    private String salaryMonth;
    private LocalDate paymentDate;

    public Payment() {
    }

    public Payment(int paymentId, int rateId, int empId, String empName, double teaSalary, double expenseSalary, double finalSalary, String salaryMonth, LocalDate paymentDate) {
        this.paymentId = paymentId;
        this.rateId = rateId;
        this.empId = empId;
        this.empName = empName;
        this.teaSalary = teaSalary;
        this.expenseSalary = expenseSalary;
        this.finalSalary = finalSalary;
        this.salaryMonth = salaryMonth;
        this.paymentDate = paymentDate;
    }

    public Payment(int rateId, int empId, String empName, double teaSalary, double expenseSalary, double finalSalary, String salaryMonth, LocalDate paymentDate) {
        this.rateId = rateId;
        this.empId = empId;
        this.empName = empName;
        this.teaSalary = teaSalary;
        this.expenseSalary = expenseSalary;
        this.finalSalary = finalSalary;
        this.salaryMonth = salaryMonth;
        this.paymentDate = paymentDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public double getTeaSalary() {
        return teaSalary;
    }

    public void setTeaSalary(double teaSalary) {
        this.teaSalary = teaSalary;
    }

    public double getExpenseSalary() {
        return expenseSalary;
    }

    public void setExpenseSalary(double expenseSalary) {
        this.expenseSalary = expenseSalary;
    }

    public double getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(double finalSalary) {
        this.finalSalary = finalSalary;
    }

    public String getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(String salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
