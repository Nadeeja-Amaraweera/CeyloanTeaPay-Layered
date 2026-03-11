package lk.ijse.ceylonteapay.entity;

import java.sql.Date;
import java.time.LocalDate;

public class OtherWork {
    private int Work_ID;
    private int Emp_ID;
    private String Emp_Name;
    private int Lnd_ID;
    private String Land_Name;
    private LocalDate Date;
    private String Details;
    private double Salary;

    public OtherWork(int work_ID, int emp_ID, int lnd_ID, LocalDate date, String details, double salary) {
        Work_ID = work_ID;
        Emp_ID = emp_ID;
        Lnd_ID = lnd_ID;
        Date = date;
        Details = details;
        Salary = salary;
    }

    public OtherWork() {
    }

    public OtherWork(int workID, int empID, String empName, int lndID, String landName, LocalDate date, String details, double salary) {
        Work_ID = workID;
        Emp_ID = empID;
        Emp_Name = empName;
        Lnd_ID = lndID;
        Land_Name = landName;
        Date = date;
        Details = details;
        Salary = salary;
    }

    public OtherWork(int empId, int lndId, Date date, String details, double salary) {
        Emp_ID = empId;
        Lnd_ID = lndId;
        Date = date.toLocalDate();
        Details = details;
        Salary = salary;
    }

    public OtherWork(int empId, int lndId, Date date, String details, double salary, int workId) {
        Emp_ID = empId;
        Lnd_ID = lndId;
        Date = date.toLocalDate();
        Details = details;
        Salary = salary;
        Work_ID = workId;
    }

    public String getEmp_Name() {
        return Emp_Name;
    }

    public void setEmp_Name(String emp_Name) {
        Emp_Name = emp_Name;
    }

    public String getLand_Name() {
        return Land_Name;
    }

    public void setLand_Name(String land_Name) {
        Land_Name = land_Name;
    }

    public int getWork_ID() {
        return Work_ID;
    }

    public void setWork_ID(int work_ID) {
        Work_ID = work_ID;
    }

    public int getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(int emp_ID) {
        Emp_ID = emp_ID;
    }

    public int getLnd_ID() {
        return Lnd_ID;
    }

    public void setLnd_ID(int lnd_ID) {
        Lnd_ID = lnd_ID;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }
}
