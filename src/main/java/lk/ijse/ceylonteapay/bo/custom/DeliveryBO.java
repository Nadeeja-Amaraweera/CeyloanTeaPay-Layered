package lk.ijse.ceylonteapay.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.SuperBO;
import lk.ijse.ceylonteapay.dao.custom.DeliveryDAO;
import lk.ijse.ceylonteapay.dao.custom.impl.DeliveryDAOImpl;
import lk.ijse.ceylonteapay.dao.custom.impl.DeliveryStockDAOImpl;
import lk.ijse.ceylonteapay.dao.custom.impl.StockDAOImpl;
import lk.ijse.ceylonteapay.db.DBConnection;
import lk.ijse.ceylonteapay.dto.DeliveryCartTM;
import lk.ijse.ceylonteapay.entity.Delivery;
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

public interface DeliveryBO extends SuperBO {
    public boolean placeOrder(ObservableList<DeliveryCartTM> cartList) throws Exception ;

    public void printDeliveryTea(int selectedMonthNo, int selectedYear) ;
}
