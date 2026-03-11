package lk.ijse.ceylonteapay.entity;

import java.sql.Date;
import java.time.LocalDate;

public class DailyTea {
    private int Tea_ID;
    private int Emp_ID;
    private String EmpName;
    private int Lnd_ID;
    private String LandName;
    private LocalDate Date_Collected;
    private double Full_Weight;
    private double Bag_Weight;
    private double Water_Weight;
    private double Total_Weight;
    private String Quality;

    public DailyTea(int empID, int lndID, Date date, double fullWeight, double bagWeight, double waterWeight, double totalWeight, String quality, int teaID) {
        Emp_ID = empID;
        Lnd_ID = lndID;
        Date_Collected = date.toLocalDate();
        Full_Weight = fullWeight;
        Bag_Weight = bagWeight;
        Water_Weight = waterWeight;
        Total_Weight = totalWeight;
        Quality = quality;
        Tea_ID = teaID;
    }

    public DailyTea(int tea_ID, int emp_ID, String empName, int lnd_ID, String landName, LocalDate date_Collected, double full_Weight, double bag_Weight, double water_Weight, double total_Weight, String quality) {
        Tea_ID = tea_ID;
        Emp_ID = emp_ID;
        EmpName = empName;
        Lnd_ID = lnd_ID;
        LandName = landName;
        Date_Collected = date_Collected;
        Full_Weight = full_Weight;
        Bag_Weight = bag_Weight;
        Water_Weight = water_Weight;
        Total_Weight = total_Weight;
        Quality = quality;
    }

    public DailyTea(int empId, String empName, int lndId, String landName, LocalDate date, double fullWeight, double bagWeight, double waterWeight, double totalWeight, String quality) {
        Emp_ID = empId;
        EmpName = empName;
        Lnd_ID = lndId;
        LandName = landName;
        Date_Collected = date;
        Full_Weight = fullWeight;
        Bag_Weight = bagWeight;
        Water_Weight = waterWeight;
        Total_Weight = totalWeight;
        Quality = quality;
    }

    public int getTea_ID() {
        return Tea_ID;
    }

    public void setTea_ID(int tea_ID) {
        Tea_ID = tea_ID;
    }

    public int getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(int emp_ID) {
        Emp_ID = emp_ID;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public int getLnd_ID() {
        return Lnd_ID;
    }

    public void setLnd_ID(int lnd_ID) {
        Lnd_ID = lnd_ID;
    }

    public String getLandName() {
        return LandName;
    }

    public void setLandName(String landName) {
        LandName = landName;
    }

    public LocalDate getDate_Collected() {
        return Date_Collected;
    }

    public void setDate_Collected(LocalDate date_Collected) {
        Date_Collected = date_Collected;
    }

    public double getFull_Weight() {
        return Full_Weight;
    }

    public void setFull_Weight(double full_Weight) {
        Full_Weight = full_Weight;
    }

    public double getBag_Weight() {
        return Bag_Weight;
    }

    public void setBag_Weight(double bag_Weight) {
        Bag_Weight = bag_Weight;
    }

    public double getWater_Weight() {
        return Water_Weight;
    }

    public void setWater_Weight(double water_Weight) {
        Water_Weight = water_Weight;
    }

    public double getTotal_Weight() {
        return Total_Weight;
    }

    public void setTotal_Weight(double total_Weight) {
        Total_Weight = total_Weight;
    }

    public String getQuality() {
        return Quality;
    }

    public void setQuality(String quality) {
        Quality = quality;
    }
}
