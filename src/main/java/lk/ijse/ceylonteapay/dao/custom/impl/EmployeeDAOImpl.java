package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.EmployeeDAO;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public ObservableList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
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

    @Override
    public boolean save(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO Employee (Name,NIC,Dob,Address,Gender,TelNo) VALUES (?,?,?,?,?,?)",dto.getName(),dto.getNic(),Date.valueOf(dto.getDob()),dto.getAddress(),dto.getGender(),dto.getTelNo());
    }

    @Override
    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("UPDATE Employee SET Name = ?, NIC = ?, Dob = ?, Address = ?, Gender = ?, TelNo = ? WHERE EmpID = ?",dto.getName(),dto.getNic(),Date.valueOf(dto.getDob()),dto.getAddress(),dto.getGender(),dto.getTelNo(),dto.getId());

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM Employee WHERE EmpID = ?",id);

    }
}
