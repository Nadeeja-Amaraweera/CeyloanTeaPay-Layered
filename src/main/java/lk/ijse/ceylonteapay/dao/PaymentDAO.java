package lk.ijse.ceylonteapay.dao;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dto.PaymentDTO;

import java.sql.SQLException;

public interface PaymentDAO {
    public boolean savePayment(PaymentDTO paymentDTO)throws Exception;

    public ObservableList<PaymentDTO> loadPaymentTable()throws Exception;

    public boolean updatePayment(PaymentDTO paymentDTO) throws Exception;

    public void printPaymentReport(int selectedMonthNo, int selectedYear);

    public Double loadOtherWorkByMonth(int selectedMonthNumber, int selectedEmpId) throws SQLException;

    public Double loadTeaSalaryByMonth(int selectedMonthNumber, int selectedEmpId) throws SQLException;

    public boolean deletePayment(int id) throws SQLException;

}
