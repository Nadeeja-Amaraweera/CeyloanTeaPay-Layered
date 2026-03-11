package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.QueryDAO;
import lk.ijse.ceylonteapay.dao.QueryDAOImpl;
import lk.ijse.ceylonteapay.dao.custom.DailyTeaDAO;
import lk.ijse.ceylonteapay.dto.DailyTeaDTO;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;
import lk.ijse.ceylonteapay.dto.LandDTO;
import lk.ijse.ceylonteapay.entity.DailyTea;

import java.sql.*;
import java.time.LocalDate;

public class DailyTeaDAOImpl implements DailyTeaDAO {
    @Override
    public boolean save(DailyTea entity) throws Exception {

        return CRUDUtil.execute("INSERT INTO Tea (Emp_ID,EmpName, Lnd_ID,LandName, Date_Collected, Full_Weight, Bag_Weight, Water_Weight,Total_Weight, Quality) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)"
                ,entity.getEmp_ID()
                ,entity.getEmpName()
                ,entity.getLnd_ID()
                ,entity.getLandName()
                ,Date.valueOf(entity.getDate_Collected())
                ,entity.getFull_Weight()
                ,entity.getBag_Weight()
                ,entity.getWater_Weight()
                ,entity.getTotal_Weight()
                ,entity.getQuality());
    }

    @Override
    public boolean update(DailyTea entity) throws Exception {

        return CRUDUtil.execute("UPDATE Tea SET Emp_ID = ?, Lnd_ID = ?, Date_Collected = ?, Full_Weight = ?, Bag_Weight = ?, Water_Weight = ?,Total_Weight = ?,Quality = ? WHERE Tea_ID = ?"
                ,entity.getEmp_ID()
                ,entity.getLnd_ID()
                ,Date.valueOf(entity.getDate_Collected())
                ,entity.getFull_Weight()
                ,entity.getBag_Weight()
                ,entity.getWater_Weight()
                ,entity.getTotal_Weight()
                ,entity.getQuality()
                ,entity.getTea_ID());
    }

    @Override
    public boolean delete(String teaID) throws Exception {

        return CRUDUtil.execute("DELETE FROM Tea WHERE Tea_ID = ?",teaID);
    }

    @Override
    public Integer checkTeaId(int teaId) throws Exception {
        ResultSet resultSet =  CRUDUtil.execute("SELECT * FROM Tea WHERE Tea_ID = ?",teaId);

        if (resultSet.next()){
            return teaId;
        }
        return null;
    }

    @Override
    public ObservableList<DailyTea> getAll() throws Exception {
        QueryDAO queryDAO = new QueryDAOImpl();
        ResultSet rs = queryDAO.getAllTeaFields();

        ObservableList<DailyTea> list = FXCollections.observableArrayList();

        while (rs.next()) {

            int teaID = rs.getInt("Tea_ID");
            int empID = rs.getInt("Emp_ID");
            String empName = rs.getString("EmployeeName");   // FIXED
            int lndID = rs.getInt("Lnd_ID");
            String landName = rs.getString("LandName");      // FIXED
            LocalDate date = rs.getDate("Date_Collected").toLocalDate();
            double fullWeight = rs.getDouble("Full_Weight");
            double bagWeight = rs.getDouble("Bag_Weight");
            double waterWeight = rs.getDouble("Water_Weight");
            double totalWeight = rs.getDouble("Total_Weight");
            String quality = rs.getString("Quality");

            DailyTea tea = new DailyTea(
                    teaID, empID, empName,  // include empName
                    lndID, landName,        // include landName
                    date, fullWeight, bagWeight, waterWeight, totalWeight, quality
            );

            list.add(tea);
        }
        return list;
    }

    @Override
    public Double loadTeaSalaryByMonth(int selectedMonthNumber, int selectedEmpId) throws SQLException {

        String sql = "SELECT SUM(Total_Weight) AS totalWeight FROM Tea WHERE MONTH(Date_Collected) = ? AND Emp_ID = ?";

        ResultSet rs = CRUDUtil.execute(sql, selectedMonthNumber, selectedEmpId);

        if (rs.next()) {
            return rs.getDouble("totalWeight");
        }

        return 0.0;
    }
}
