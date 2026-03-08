package lk.ijse.ceylonteapay.entity;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private LocalDate dob;
    private String nic;
    private String address;
    private String gender;
    private String telNo;

    public Employee(int id, String name, LocalDate dob, String nic, String address, String gender, String telNo) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.nic = nic;
        this.address = address;
        this.gender = gender;
        this.telNo = telNo;
    }

    public Employee() {
    }

    public Employee(String name, String nic, LocalDate dob, String address, String gender, String telNo) {
        this.name = name;
        this.nic = nic;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.telNo = telNo;
    }

    public Employee(String name, String nic, LocalDate dob, String address, String gender, String telNo, int id) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.telNo = telNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
}
