package lk.ijse.ceylonteapay.dao;

import lk.ijse.ceylonteapay.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueryDAOImpl implements QueryDAO{
    @Override
    public ResultSet getAllTeaFields() throws Exception {
        DBConnection dbc = DBConnection.getInstance();
        Connection conn = dbc.getConnection();

        String sql = "SELECT Tea.Tea_ID, Tea.Emp_ID, Employee.Name AS EmployeeName, " +
                "Tea.Lnd_ID, Land.LandName AS LandName, Tea.Date_Collected, " +
                "Tea.Full_Weight, Tea.Bag_Weight, Tea.Water_Weight, Tea.Total_Weight, Tea.Quality " +
                "FROM Tea " +
                "JOIN Employee ON Tea.Emp_ID = Employee.EmpID " +
                "JOIN Land ON Tea.Lnd_ID = Land.LndID " +
                "ORDER BY Tea.Tea_ID DESC";

        PreparedStatement pstm = conn.prepareStatement(sql);

        return pstm.executeQuery();
    }

    @Override
    public ResultSet getAllOtherWorkFields() throws Exception {
        DBConnection dbc = DBConnection.getInstance();
        Connection conn = dbc.getConnection();

        String sql = "SELECT OtherWork.Work_ID, OtherWork.Emp_ID, Employee.Name AS EmployeeName, " +
                "OtherWork.Lnd_ID, Land.LandName AS LandName, OtherWork.Date, OtherWork.Details ,OtherWork.Salary " +
                "FROM OtherWork " +
                "JOIN Employee ON OtherWork.Emp_ID = Employee.EmpID " +
                "JOIN Land ON OtherWork.Lnd_ID = Land.LndID " +
                "ORDER BY OtherWork.Work_ID DESC";

        PreparedStatement pstm = conn.prepareStatement(sql);

        return pstm.executeQuery();
    }
}
