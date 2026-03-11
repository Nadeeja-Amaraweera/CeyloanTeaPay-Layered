package lk.ijse.ceylonteapay.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.custom.PaymentBO;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.DAOFactory;
import lk.ijse.ceylonteapay.dao.custom.PaymentDAO;
import lk.ijse.ceylonteapay.db.DBConnection;
import lk.ijse.ceylonteapay.dto.PaymentDTO;
import lk.ijse.ceylonteapay.entity.Payment;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception {

//        String sql = "INSERT INTO Payment (rateId, empId, empName, teaSalary, expenseSalary, finalSalary, SalaryMonth, Payment_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//
//        return CRUDUtil.execute(
//                sql,
//                entity.getRateId(),
//                entity.getEmpId(),
//                entity.getEmpName(),
//                entity.getTeaSalary(),
//                entity.getExpenseSalary(),
//                entity.getFinalSalary(),
//                String.valueOf(entity.getSalaryMonth()),
//                Date.valueOf(entity.getPaymentDate())
//        );

        return paymentDAO.save(new Payment(paymentDTO.getRateId(), paymentDTO.getEmployeeId(), paymentDTO.getEmployeeName(), paymentDTO.getTeaSalary(), paymentDTO.getExpenseSalary(), paymentDTO.getFinalSalary(), String.valueOf(paymentDTO.getMonth()), paymentDTO.getDate()));
    }

    @Override
    public ObservableList<PaymentDTO> getAllPayment() throws Exception {

//        ResultSet rs = CRUDUtil.execute("SELECT * FROM Payment ORDER BY paymentId DESC");
//
//        ObservableList<Payment> list = FXCollections.observableArrayList();
//
//        while (rs.next()) {
//
//            int payid = rs.getInt("paymentId");
//            int rateid = rs.getInt("rateId");
//            int empid = rs.getInt("empId");
//            String empName = rs.getString("empName");
//            double teaSalary = rs.getDouble("teaSalary");
//            double otherSalary = rs.getDouble("expenseSalary");
//            double finalSalary = rs.getDouble("finalSalary");
//            String month = rs.getString("SalaryMonth");
//            LocalDate paymentDate = rs.getDate("Payment_Date").toLocalDate();
//
//            Payment payment = new Payment(
//                    payid,
//                    rateid,
//                    empid,
//                    empName,
//                    teaSalary,
//                    otherSalary,
//                    finalSalary,
//                    month,
//                    paymentDate
//            );
//
//            list.add(payment);
//        }

        ObservableList<Payment> payments = paymentDAO.getAll();
        ObservableList<PaymentDTO> list = FXCollections.observableArrayList();

        for (Payment payment : payments) {
            PaymentDTO dto = new PaymentDTO(
                    payment.getPaymentId(),
                    payment.getRateId(),
                    payment.getEmpId(),
                    payment.getEmpName(),
                    payment.getTeaSalary(),
                    payment.getExpenseSalary(),
                    payment.getFinalSalary(),
                    payment.getSalaryMonth(),
                    payment.getPaymentDate()
            );
            list.add(dto);
        }

        return list;
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) throws Exception {

//        String sql = "UPDATE Payment SET rateId = ?, empId = ?, empName = ?, teaSalary = ?, expenseSalary = ?, finalSalary = ?, SalaryMonth = ?, Payment_Date = ? WHERE paymentId = ?";
//
//        return CRUDUtil.execute(
//                sql,
//                entity.getRateId(),
//                entity.getEmpId(),
//                entity.getEmpName(),
//                entity.getTeaSalary(),
//                entity.getExpenseSalary(),
//                entity.getFinalSalary(),
//                String.valueOf(entity.getSalaryMonth()),
//                Date.valueOf(entity.getPaymentDate()),
//                entity.getPaymentId()
//        );

        return paymentDAO.update(new Payment(paymentDTO.getPaymentId(), paymentDTO.getRateId(), paymentDTO.getEmployeeId(), paymentDTO.getEmployeeName(), paymentDTO.getTeaSalary(), paymentDTO.getExpenseSalary(), paymentDTO.getFinalSalary(), String.valueOf(paymentDTO.getMonth()), paymentDTO.getDate()));
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

    @Override
    public boolean deletePayment(String id) throws Exception {

        return paymentDAO.delete(id);
    }
}
