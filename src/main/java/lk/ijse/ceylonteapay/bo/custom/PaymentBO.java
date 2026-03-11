package lk.ijse.ceylonteapay.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.SuperBO;
import lk.ijse.ceylonteapay.dto.IncomeDTO;
import lk.ijse.ceylonteapay.dto.PaymentDTO;


public interface PaymentBO extends SuperBO {
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception ;

    public ObservableList<PaymentDTO> getAllPayment() throws Exception ;


    public boolean updatePayment(PaymentDTO paymentDTO) throws Exception ;


    public void printPaymentReport(int selectedMonthNo, int selectedYear) ;


    public boolean deletePayment(String id) throws Exception;

    IncomeDTO getMonthlySalarySummary(int month, int year) throws Exception;
}
