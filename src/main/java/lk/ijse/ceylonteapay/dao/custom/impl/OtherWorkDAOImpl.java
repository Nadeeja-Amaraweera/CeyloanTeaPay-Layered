package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        DBConnection dbc = DBConnection.getInstance();
        Connection conn = dbc.getConnection();

        String sql = "SELECT OtherWork.Work_ID, OtherWork.Emp_ID, Employee.Name AS EmployeeName, " +
                "OtherWork.Lnd_ID, Land.LandName AS LandName, OtherWork.Date, OtherWork.Details ,OtherWork.Salary " +
                "FROM OtherWork " +
                "JOIN Employee ON OtherWork.Emp_ID = Employee.EmpID " +
                "JOIN Land ON OtherWork.Lnd_ID = Land.LndID " +
                "ORDER BY OtherWork.Work_ID DESC";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ObservableList<OtherWorkDTO> list = FXCollections.observableArrayList();

        ResultSet rs = pstm.executeQuery();
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
        DBConnection dbc = DBConnection.getInstance();
        Connection conn = dbc.getConnection();

        String sql = "INSERT INTO OtherWork (Emp_ID,Lnd_ID,Date,Details,Salary) VALUES (?,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,otherWorkDTO.getEmp_ID());
        pstm.setInt(2,otherWorkDTO.getLnd_Id());
        pstm.setDate(3, Date.valueOf(otherWorkDTO.getDate()));
        pstm.setString(4,otherWorkDTO.getDetails());
        pstm.setDouble(5,otherWorkDTO.getSalary());
        int result = pstm.executeUpdate();

        return result>0;
    }

    @Override
    public boolean updateWorkField(OtherWorkDTO otherWorkDTO) throws Exception {
        DBConnection dbc = DBConnection.getInstance();
        Connection conn = dbc.getConnection();

        String sql = "UPDATE OtherWork SET Emp_ID = ?, Lnd_ID = ?, Date = ?, Details = ?,Salary = ? WHERE Work_ID = ?";
        PreparedStatement pstm = conn.prepareCall(sql);
        pstm.setInt(1,otherWorkDTO.getEmp_ID());
        pstm.setInt(2,otherWorkDTO.getLnd_Id());
        pstm.setDate(3,Date.valueOf(otherWorkDTO.getDate()));
        pstm.setString(4, otherWorkDTO.getDetails());
        pstm.setDouble(5,otherWorkDTO.getSalary());
        pstm.setInt(6,otherWorkDTO.getWorkID());

        int result = pstm.executeUpdate();

        return result>0;
    }

    @Override
    public boolean deleteWorkField(int WorkID) throws Exception {
        DBConnection dbc = DBConnection.getInstance();
        Connection conn = dbc.getConnection();

        String sql ="DELETE FROM OtherWork WHERE Work_ID = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,WorkID);

        int result = pstm.executeUpdate();

        return result>0;
    }

    @Override
    public ObservableList<EmployeeDTO> getEmployeeId() throws Exception {
        ObservableList<EmployeeDTO> idList = FXCollections.observableArrayList();

        DBConnection dbc = DBConnection.getInstance();
        Connection conn = dbc.getConnection();

        String sql = "SELECT EmpID, Name FROM Employee";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

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

        DBConnection dbc = DBConnection.getInstance();
        Connection conn = dbc.getConnection();

        String sql = "SELECT * FROM Land";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

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
