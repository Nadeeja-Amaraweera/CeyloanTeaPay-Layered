package lk.ijse.ceylonteapay.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.db.DBConnection;
import lk.ijse.ceylonteapay.dto.IncomeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IncomeDAOImpl implements IncomeDAO{
    @Override
    public IncomeDTO getAllTeaSalary(int month, int year) throws Exception {
        ResultSet rs = CRUDUtil.execute("SELECT SUM(teaSalary) AS total_tea, SUM(expenseSalary) AS total_expense FROM Payment WHERE MONTH(Payment_Date) = ? AND YEAR(Payment_Date) = ?",month,year);

        ObservableList<IncomeDTO> list = FXCollections.observableArrayList();

        while (rs.next()){
            list.add(new IncomeDTO(
                    rs.getDouble("total_tea"),
                    rs.getDouble("total_expense")
            ));
            double allTeaSalary = rs.getDouble("total_tea");
            double allOtherWorkSalary = rs.getDouble("total_expense");

            return new IncomeDTO(allTeaSalary,allOtherWorkSalary);
        }
        return new IncomeDTO(0,0);
    }

    @Override
    public boolean savePayment(IncomeDTO incomeDTO) throws Exception {

        return CRUDUtil.execute("INSERT INTO Income (month, year, teaSalary, otherWorkSalary, thisMonthIncome, finalIncome) VALUES (?, ?, ?, ?, ?, ?) ",
                incomeDTO.getMonth(),
                incomeDTO.getYear(),
                incomeDTO.getTeaSalary(),
                incomeDTO.getOtherWorkSalary(),
                incomeDTO.getThisMonthIncome(),
                incomeDTO.getFinalIncome());
    }

    @Override
    public ObservableList<IncomeDTO> getAllIncomeFields() throws Exception {

        ResultSet rs = CRUDUtil.execute("SELECT * FROM Income");

        ObservableList<IncomeDTO> list = FXCollections.observableArrayList();

        while (rs.next()){
            int incomeId = rs.getInt("incomeId");
            String month = rs.getString("Month");
            int year = rs.getInt("Year");
            double teaSalary = rs.getDouble("teaSalary");
            double otherWorkSalary = rs.getDouble("otherWorkSalary");
            double thisMonthIncome = rs.getDouble("thisMonthIncome");
            double finalIncome = rs.getDouble("finalIncome");

            IncomeDTO incomeDTO = new IncomeDTO(incomeId,month,year,teaSalary,otherWorkSalary,thisMonthIncome,finalIncome);
            list.add(incomeDTO);
        }
        return list;
    }

    @Override
    public boolean deleteIncome(int id) throws Exception {

        return CRUDUtil.execute("DELETE FROM Income WHERE incomeId = ?",id);
    }
}
