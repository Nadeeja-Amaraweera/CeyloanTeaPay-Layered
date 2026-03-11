package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.PaymentDAO;
import lk.ijse.ceylonteapay.db.DBConnection;
import lk.ijse.ceylonteapay.dto.PaymentDTO;
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
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception {

        String sql = "INSERT INTO Payment (rateId, empId, empName, teaSalary, expenseSalary, finalSalary, SalaryMonth, Payment_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        return CRUDUtil.execute(
                sql,
                paymentDTO.getRateId(),
                paymentDTO.getEmployeeId(),
                paymentDTO.getEmployeeName(),
                paymentDTO.getTeaSalary(),
                paymentDTO.getExpenseSalary(),
                paymentDTO.getFinalSalary(),
                String.valueOf(paymentDTO.getMonth()),
                Date.valueOf(paymentDTO.getDate())
        );
    }

    @Override
    public ObservableList<PaymentDTO> loadPaymentTable() throws Exception {

        ResultSet rs = CRUDUtil.execute("SELECT * FROM Payment ORDER BY paymentId DESC");

        ObservableList<PaymentDTO> list = FXCollections.observableArrayList();

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

            PaymentDTO paymentDTO = new PaymentDTO(
                    payid,
                    rateid,
                    empid,
                    empName,
                    teaSalary,
                    otherSalary,
                    finalSalary,
                    Month.valueOf(month),
                    paymentDate
            );

            list.add(paymentDTO);
        }

        return list;
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) throws Exception {

        String sql = "UPDATE Payment SET rateId = ?, empId = ?, empName = ?, teaSalary = ?, expenseSalary = ?, finalSalary = ?, SalaryMonth = ?, Payment_Date = ? WHERE paymentId = ?";

        return CRUDUtil.execute(
                sql,
                paymentDTO.getRateId(),
                paymentDTO.getEmployeeId(),
                paymentDTO.getEmployeeName(),
                paymentDTO.getTeaSalary(),
                paymentDTO.getExpenseSalary(),
                paymentDTO.getFinalSalary(),
                String.valueOf(paymentDTO.getMonth()),
                Date.valueOf(paymentDTO.getDate()),
                paymentDTO.getPaymentId()
        );
    }

    @Override
    public void printPaymentReport(int selectedMonthNo, int selectedYear) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();

            InputStream reportObject = getClass().getResourceAsStream("/lk/ijse/ceylonteapay/reports/payment.jrxml");

            if (reportObject == null) {
                throw new RuntimeException("❌ customer.jrxml NOT FOUND");
            }

            JasperReport jr  = JasperCompileManager.compileReport(reportObject); // this is method throws JRException

            Map<String,Object> params = new HashMap<>();
            params.put("PAYMENT_MONTH",selectedMonthNo);
            params.put("PAYMENT_YEAR",selectedYear);

            JasperPrint jp = JasperFillManager.fillReport(jr , params , conn); // fill report (jasperreport, params ,connection)

            JasperViewer.viewReport(jp,false);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public Double loadOtherWorkByMonth(int selectedMonthNumber, int selectedEmpId) throws SQLException {
//
//        String sql = "SELECT Salary AS DbotherWorkSalary FROM OtherWork WHERE MONTH(Date) = ? AND Emp_ID = ?";
//
//        ResultSet rs = CRUDUtil.execute(sql, selectedMonthNumber, selectedEmpId);
//
//        if (rs.next()) {
//            return rs.getDouble("DbotherWorkSalary");
//        }
//
//        return 0.0;
//    }
//
//    @Override
//    public Double loadTeaSalaryByMonth(int selectedMonthNumber, int selectedEmpId) throws SQLException {
//
//        String sql = "SELECT SUM(Total_Weight) AS totalWeight FROM Tea WHERE MONTH(Date_Collected) = ? AND Emp_ID = ?";
//
//        ResultSet rs = CRUDUtil.execute(sql, selectedMonthNumber, selectedEmpId);
//
//        if (rs.next()) {
//            return rs.getDouble("totalWeight");
//        }
//
//        return 0.0;
//    }

    @Override
    public boolean deletePayment(int id) throws SQLException {

        String sql = "DELETE FROM Payment WHERE paymentId = ?";

        return CRUDUtil.execute(sql, id);
    }
}
