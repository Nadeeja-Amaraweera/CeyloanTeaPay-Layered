package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.EmployeeDAO;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws Exception {

        return CRUDUtil.execute("INSERT INTO Employee (Name,NIC,Dob,Address,Gender,TelNo) VALUES (?,?,?,?,?,?)",employeeDTO.getName(),employeeDTO.getNic(),Date.valueOf(employeeDTO.getDob()),employeeDTO.getAddress(),employeeDTO.getGender(),employeeDTO.getTelNo());
    }

    @Override
    public boolean deleteEmployee(int id) throws Exception {
       return CRUDUtil.execute("DELETE FROM Employee WHERE EmpID = ?",id);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws Exception {

        return CRUDUtil.execute("UPDATE Employee SET Name = ?, NIC = ?, Dob = ?, Address = ?, Gender = ?, TelNo = ? WHERE EmpID = ?",employeeDTO.getName(),employeeDTO.getNic(),Date.valueOf(employeeDTO.getDob()),employeeDTO.getAddress(),employeeDTO.getGender(),employeeDTO.getTelNo(),employeeDTO.getId());
    }

    @Override
    public ObservableList<EmployeeDTO> getAllEmployees() throws Exception {

        ResultSet rs = CRUDUtil.execute("SELECT * FROM Employee ORDER BY EmpID DESC");

        ObservableList<EmployeeDTO> list = FXCollections.observableArrayList();

        while (rs.next()){
            int empId = rs.getInt("EmpID");
            String empName = rs.getString("Name");
            LocalDate empdob = rs.getDate("Dob").toLocalDate();
            String empNic = rs.getString("NIC");
            String empAddress = rs.getString("Address");
            String empGender = rs.getString("Gender");
            String empTelno = rs.getString("TelNo");

            EmployeeDTO employeeDTO = new EmployeeDTO(empId,empName,empdob,empNic,empAddress,empGender,empTelno);
            list.add(employeeDTO);
        }
        return list;
    }
}
