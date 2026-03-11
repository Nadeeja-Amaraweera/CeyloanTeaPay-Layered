package lk.ijse.ceylonteapay.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.SuperBO;
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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface PaymentBO extends SuperBO {
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception ;

    public ObservableList<PaymentDTO> getAllPayment() throws Exception ;


    public boolean updatePayment(PaymentDTO paymentDTO) throws Exception ;


    public void printPaymentReport(int selectedMonthNo, int selectedYear) ;


    public boolean deletePayment(String id) throws Exception;
}
