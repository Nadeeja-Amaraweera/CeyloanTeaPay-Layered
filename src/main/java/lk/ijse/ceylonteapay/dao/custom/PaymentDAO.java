package lk.ijse.ceylonteapay.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CrudDAO;
import lk.ijse.ceylonteapay.dao.SuperDAO;
import lk.ijse.ceylonteapay.dto.PaymentDTO;
import lk.ijse.ceylonteapay.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment> {

    public void printPaymentReport(int selectedMonthNo, int selectedYear);

}
