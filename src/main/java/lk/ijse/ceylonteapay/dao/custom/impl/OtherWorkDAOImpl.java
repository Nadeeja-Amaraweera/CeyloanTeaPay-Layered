package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.QueryDAO;
import lk.ijse.ceylonteapay.dao.QueryDAOImpl;
import lk.ijse.ceylonteapay.dao.custom.OtherWorkDAO;
import lk.ijse.ceylonteapay.db.DBConnection;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;
import lk.ijse.ceylonteapay.dto.LandDTO;
import lk.ijse.ceylonteapay.dto.OtherWorkDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class OtherWorkDAOImpl implements OtherWorkDAO {
    @Override
    public ObservableList<OtherWorkDTO> getAllOtherWorkFields() throws Exception {
        QueryDAO queryDAO = new QueryDAOImpl();
        ResultSet rs = queryDAO.getAllOtherWorkFields();

        ObservableList<OtherWorkDTO> list = FXCollections.observableArrayList();

        while (rs.next()) {

            int workID = rs.getInt("Work_ID");
            int empID = rs.getInt("Emp_ID");
            String empName = rs.getString("EmployeeName");      // NEW

            int lndID = rs.getInt("Lnd_ID");
            String landName = rs.getString("LandName");         // NEW

            LocalDate date = rs.getDate("Date").toLocalDate();
            String details = rs.getString("Details");
            double salary = rs.getDouble("Salary");

            OtherWorkDTO dto = new OtherWorkDTO(
                    workID, empID, empName,
                    lndID, landName,
                    date, details , salary
            );

            list.add(dto); //FIXED (use add, not addAll)
        }

        return list;
    }

    @Override
    public boolean addWorkField(OtherWorkDTO otherWorkDTO) throws Exception {

        return CRUDUtil.execute("INSERT INTO OtherWork (Emp_ID,Lnd_ID,Date,Details,Salary) VALUES (?,?,?,?,?)",
                otherWorkDTO.getEmp_ID(),
                otherWorkDTO.getLnd_Id(),
                Date.valueOf(otherWorkDTO.getDate()),
                otherWorkDTO.getDetails(),
                otherWorkDTO.getSalary()
                );
    }

    @Override
    public boolean updateWorkField(OtherWorkDTO otherWorkDTO) throws Exception {

        return CRUDUtil.execute("UPDATE OtherWork SET Emp_ID = ?, Lnd_ID = ?, Date = ?, Details = ?,Salary = ? WHERE Work_ID = ?",
                otherWorkDTO.getEmp_ID(),
                otherWorkDTO.getLnd_Id(),
                Date.valueOf(otherWorkDTO.getDate()),
                otherWorkDTO.getDetails(),
                otherWorkDTO.getSalary(),
                otherWorkDTO.getWorkID()
                );
    }

    @Override
    public boolean deleteWorkField(int WorkID) throws Exception {

        return CRUDUtil.execute("DELETE FROM OtherWork WHERE Work_ID = ?",WorkID);
    }

    @Override
    public ObservableList<EmployeeDTO> getEmployeeId() throws Exception {

        ResultSet rs = CRUDUtil.execute("SELECT EmpID, Name FROM Employee");

        ObservableList<EmployeeDTO> idList = FXCollections.observableArrayList();

        while (rs.next()) {
            idList.add(new EmployeeDTO(
                    rs.getInt("EmpID"),
                    rs.getString("Name")
            ));
        }
        return idList;
    }

    @Override
    public ObservableList<LandDTO> getLandId() throws Exception {

        ObservableList<LandDTO> idList = FXCollections.observableArrayList();

        ResultSet rs = CRUDUtil.execute("SELECT * FROM Land");

        while (rs.next()){
            idList.add(new LandDTO(
                    rs.getInt("LndID"),
                    rs.getString("LandName"),
                    rs.getString("LandNo")
            ));
        }
        return idList;
    }
}
