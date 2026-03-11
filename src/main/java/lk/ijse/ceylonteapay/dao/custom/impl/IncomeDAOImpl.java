package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.IncomeDAO;
import lk.ijse.ceylonteapay.dto.IncomeDTO;
import lk.ijse.ceylonteapay.entity.Income;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeDAOImpl implements IncomeDAO {
//    @Override
//    public Income getAllTeaSalary(int month, int year) throws Exception {
//        ResultSet rs = CRUDUtil.execute("SELECT SUM(teaSalary) AS total_tea, SUM(expenseSalary) AS total_expense FROM Payment WHERE MONTH(Payment_Date) = ? AND YEAR(Payment_Date) = ?",month,year);
//
//        ObservableList<Income> list = FXCollections.observableArrayList();
//
//        while (rs.next()){
//            list.add(new Income(
//                    rs.getDouble("total_tea"),
//                    rs.getDouble("total_expense")
//            ));
//            double allTeaSalary = rs.getDouble("total_tea");
//            double allOtherWorkSalary = rs.getDouble("total_expense");
//
//            return new Income(allTeaSalary,allOtherWorkSalary);
//        }
//        return new Income(0,0);
//    }

    @Override
    public ObservableList<Income> getAll() throws SQLException, ClassNotFoundException {

        ObservableList<Income> list = FXCollections.observableArrayList();

        ResultSet rs = CRUDUtil.execute("SELECT * FROM Income");

        while (rs.next()){
            int incomeId = rs.getInt("incomeId");
            String month = rs.getString("Month");
            int year = rs.getInt("Year");
            double teaSalary = rs.getDouble("teaSalary");
            double otherWorkSalary = rs.getDouble("otherWorkSalary");
            double thisMonthIncome = rs.getDouble("thisMonthIncome");
            double finalIncome = rs.getDouble("finalIncome");

            Income income = new Income(incomeId,month,year,teaSalary,otherWorkSalary,thisMonthIncome,finalIncome);
            list.add(income);
        }
        return list;
    }

    @Override
    public boolean save(Income dto) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO Income (month, year, teaSalary, otherWorkSalary, thisMonthIncome, finalIncome) VALUES (?, ?, ?, ?, ?, ?) ",
                dto.getMonth(),
                dto.getYear(),
                dto.getTeaSalary(),
                dto.getOtherWorkSalary(),
                dto.getThisMonthIncome(),
                dto.getFinalIncome());
    }

    @Override
    public boolean update(Income dto) throws SQLException, ClassNotFoundException, Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM Income WHERE incomeId = ?",id);
    }
}
