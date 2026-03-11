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
import lk.ijse.ceylonteapay.entity.OtherWork;

import java.sql.*;
import java.time.LocalDate;

public class OtherWorkDAOImpl implements OtherWorkDAO {
    @Override
    public ObservableList<OtherWork> getAll() throws Exception {
        QueryDAO queryDAO = new QueryDAOImpl();
        ResultSet rs = queryDAO.getAllOtherWorkFields();

        ObservableList<OtherWork> list = FXCollections.observableArrayList();

        while (rs.next()) {

            int workID = rs.getInt("Work_ID");
            int empID = rs.getInt("Emp_ID");
            String empName = rs.getString("EmployeeName");      // NEW

            int lndID = rs.getInt("Lnd_ID");
            String landName = rs.getString("LandName");         // NEW

            LocalDate date = rs.getDate("Date").toLocalDate();
            String details = rs.getString("Details");
            double salary = rs.getDouble("Salary");

            OtherWork dto = new OtherWork(
                    workID, empID, empName,
                    lndID, landName,
                    date, details , salary
            );

            list.add(dto); //FIXED (use add, not addAll)
        }

        return list;
    }

    @Override
    public boolean save(OtherWork otherWorkDTO) throws Exception {

        return CRUDUtil.execute("INSERT INTO OtherWork (Emp_ID,Lnd_ID,Date,Details,Salary) VALUES (?,?,?,?,?)",
                otherWorkDTO.getEmp_ID(),
                otherWorkDTO.getLnd_ID(),
                Date.valueOf(otherWorkDTO.getDate()),
                otherWorkDTO.getDetails(),
                otherWorkDTO.getSalary()
                );
    }

    @Override
    public boolean update(OtherWork otherWorkDTO) throws Exception {

        return CRUDUtil.execute("UPDATE OtherWork SET Emp_ID = ?, Lnd_ID = ?, Date = ?, Details = ?,Salary = ? WHERE Work_ID = ?",
                otherWorkDTO.getEmp_ID(),
                otherWorkDTO.getLnd_ID(),
                Date.valueOf(otherWorkDTO.getDate()),
                otherWorkDTO.getDetails(),
                otherWorkDTO.getSalary(),
                otherWorkDTO.getWork_ID()
                );
    }

    @Override
    public boolean delete(String WorkID) throws Exception {

        return CRUDUtil.execute("DELETE FROM OtherWork WHERE Work_ID = ?",WorkID);
    }

    @Override
    public Double loadOtherWorkByMonth(int selectedMonthNumber, int selectedEmpId) throws SQLException {

        String sql = "SELECT Salary AS DbotherWorkSalary FROM OtherWork WHERE MONTH(Date) = ? AND Emp_ID = ?";

        ResultSet rs = CRUDUtil.execute(sql, selectedMonthNumber, selectedEmpId);

        if (rs.next()) {
            return rs.getDouble("DbotherWorkSalary");
        }

        return 0.0;
    }
}
