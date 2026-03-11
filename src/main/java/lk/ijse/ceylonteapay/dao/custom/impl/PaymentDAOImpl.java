package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.PaymentDAO;
import lk.ijse.ceylonteapay.db.DBConnection;
import lk.ijse.ceylonteapay.dto.PaymentDTO;
import lk.ijse.ceylonteapay.entity.Income;
import lk.ijse.ceylonteapay.entity.Payment;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment entity) throws Exception {

        String sql = "INSERT INTO Payment (rateId, empId, empName, teaSalary, expenseSalary, finalSalary, SalaryMonth, Payment_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        return CRUDUtil.execute(
                sql,
                entity.getRateId(),
                entity.getEmpId(),
                entity.getEmpName(),
                entity.getTeaSalary(),
                entity.getExpenseSalary(),
                entity.getFinalSalary(),
                String.valueOf(entity.getSalaryMonth()),
                Date.valueOf(entity.getPaymentDate())
        );
    }

    @Override
    public ObservableList<Payment> getAll() throws Exception {

        ResultSet rs = CRUDUtil.execute("SELECT * FROM Payment ORDER BY paymentId DESC");

        ObservableList<Payment> list = FXCollections.observableArrayList();

        while (rs.next()) {

            int payid = rs.getInt("paymentId");
            int rateid = rs.getInt("rateId");
            int empid = rs.getInt("empId");
            String empName = rs.getString("empName");
            double teaSalary = rs.getDouble("teaSalary");
            double otherSalary = rs.getDouble("expenseSalary");
            double finalSalary = rs.getDouble("finalSalary");
            String month = rs.getString("SalaryMonth");
            LocalDate paymentDate = rs.getDate("Payment_Date").toLocalDate();

            Payment payment = new Payment(
                    payid,
                    rateid,
                    empid,
                    empName,
                    teaSalary,
                    otherSalary,
                    finalSalary,
                    month,
                    paymentDate
            );

            list.add(payment);
        }

        return list;
    }

    @Override
    public boolean update(Payment entity) throws Exception {

        String sql = "UPDATE Payment SET rateId = ?, empId = ?, empName = ?, teaSalary = ?, expenseSalary = ?, finalSalary = ?, SalaryMonth = ?, Payment_Date = ? WHERE paymentId = ?";

        return CRUDUtil.execute(
                sql,
                entity.getRateId(),
                entity.getEmpId(),
                entity.getEmpName(),
                entity.getTeaSalary(),
                entity.getExpenseSalary(),
                entity.getFinalSalary(),
                String.valueOf(entity.getSalaryMonth()),
                Date.valueOf(entity.getPaymentDate()),
                entity.getPaymentId()
        );
    }

    @Override
    public Income getMonthlySalarySummary(int month, int year) throws Exception {
        ResultSet rs = CRUDUtil.execute(
                "SELECT SUM(teaSalary) AS total_tea, SUM(expenseSalary) AS total_expense " +
                        "FROM Payment WHERE MONTH(Payment_Date) = ? AND YEAR(Payment_Date) = ?",
                month, year
        );

        if (rs.next()) {

            double teaSalary = rs.getDouble("total_tea");
            double expenseSalary = rs.getDouble("total_expense");

            return new Income(teaSalary, expenseSalary);
        }

        return new Income(0, 0);
    }

    @Override
    public boolean delete(String id) throws SQLException {

        String sql = "DELETE FROM Payment WHERE paymentId = ?";

        return CRUDUtil.execute(sql, id);
    }


}
