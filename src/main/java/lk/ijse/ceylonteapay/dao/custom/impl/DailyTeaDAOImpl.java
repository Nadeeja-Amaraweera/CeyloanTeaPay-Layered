package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.QueryDAO;
import lk.ijse.ceylonteapay.dao.QueryDAOImpl;
import lk.ijse.ceylonteapay.dao.custom.DailyTeaDAO;
import lk.ijse.ceylonteapay.db.DBConnection;
import lk.ijse.ceylonteapay.dto.DailyTeaDTO;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;
import lk.ijse.ceylonteapay.dto.LandDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class DailyTeaDAOImpl implements DailyTeaDAO {
    @Override
    public boolean addTeaField(DailyTeaDTO teaDTO) throws Exception {

        return CRUDUtil.execute("INSERT INTO Tea (Emp_ID,EmpName, Lnd_ID,LandName, Date_Collected, Full_Weight, Bag_Weight, Water_Weight,Total_Weight, Quality) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)"
                ,teaDTO.getEmpID()
                ,teaDTO.getEmpName()
                ,teaDTO.getLndID()
                ,teaDTO.getLndName()
                ,Date.valueOf(teaDTO.getDateCollected())
                ,teaDTO.getFullWeight()
                ,teaDTO.getBagWeight()
                ,teaDTO.getWaterWeight()
                ,teaDTO.getTotalWeight()
                ,teaDTO.getQuality());
    }

    @Override
    public boolean updateTeaField(DailyTeaDTO teaDTO) throws Exception {

        return CRUDUtil.execute("UPDATE Tea SET Emp_ID = ?, Lnd_ID = ?, Date_Collected = ?, Full_Weight = ?, Bag_Weight = ?, Water_Weight = ?,Total_Weight = ?,Quality = ? WHERE Tea_ID = ?"
                ,teaDTO.getEmpID()
                ,teaDTO.getLndID()
                ,Date.valueOf(teaDTO.getDateCollected())
                ,teaDTO.getFullWeight()
                ,teaDTO.getBagWeight()
                ,teaDTO.getWaterWeight()
                ,teaDTO.getTotalWeight()
                ,teaDTO.getQuality()
                ,teaDTO.getTeaID());
    }

    @Override
    public boolean deleteTeaField(int teaID) throws Exception {

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
    public ObservableList<DailyTeaDTO> getAllTeaFields() throws Exception {
        QueryDAO queryDAO = new QueryDAOImpl();
        ResultSet rs = queryDAO.getAllTeaFields();

        ObservableList<DailyTeaDTO> list = FXCollections.observableArrayList();

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

            DailyTeaDTO teaDTO = new DailyTeaDTO(
                    teaID, empID, empName,  // include empName
                    lndID, landName,        // include landName
                    date, fullWeight, bagWeight, waterWeight, totalWeight, quality
            );

            list.add(teaDTO);
        }
        return list;
    }

    @Override
    public ObservableList<EmployeeDTO> getEmployeeId() throws Exception {

        ObservableList<EmployeeDTO> idList = FXCollections.observableArrayList();

        ResultSet rs = CRUDUtil.execute("SELECT EmpID, Name FROM Employee");

        while(rs.next()){
            idList.add(new EmployeeDTO(
                    rs.getInt("EmpID"),
                    rs.getString("Name")
            ));
        }
        return idList;
    }

    @Override
    public ObservableList<LandDTO> getLandId() throws Exception {

        ResultSet rs = CRUDUtil.execute("SELECT * FROM Land");

        ObservableList<LandDTO> idList = FXCollections.observableArrayList();

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
