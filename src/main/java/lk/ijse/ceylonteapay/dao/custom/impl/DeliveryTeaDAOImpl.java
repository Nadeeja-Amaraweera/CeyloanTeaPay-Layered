package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.DeliveryTeaDAO;
import lk.ijse.ceylonteapay.db.DBConnection;
import lk.ijse.ceylonteapay.dto.DeliveryCartTM;
import lk.ijse.ceylonteapay.dto.FactoryDTO;
import lk.ijse.ceylonteapay.dto.StockDTO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DeliveryTeaDAOImpl implements DeliveryTeaDAO {
    @Override
    public ObservableList<FactoryDTO> getComboFactory() throws Exception {

        ResultSet rs = CRUDUtil.execute("SELECT FactoryId,FactoryName FROM Factory");

        ObservableList<FactoryDTO> list = FXCollections.observableArrayList();

        while (rs.next()){
            list.add(new FactoryDTO(
                    rs.getInt("FactoryId"),
                    rs.getString("FactoryName")
            ));
        }
        return list;
    }

    @Override
    public ObservableList<StockDTO> getComboStock() throws Exception {

        ResultSet rs = CRUDUtil.execute("SELECT id,date,quality FROM Stock");

        ObservableList<StockDTO> list = FXCollections.observableArrayList();

        while (rs.next()){
            list.add(new StockDTO(
                    rs.getInt("id"),
                    rs.getDate("date").toLocalDate(),
                    rs.getString("quality")
            ));
        }
        return list;

    }

    @Override
    public boolean placeOrder(ObservableList<DeliveryCartTM> cartList) throws Exception {
        Connection con = DBConnection.getInstance().getConnection();
        con.setAutoCommit(false);

        try {
            //Insert DELIVERY only ONCE
            DeliveryCartTM firstItem = cartList.get(0);

            PreparedStatement deliveryStmt = con.prepareStatement(
                    "INSERT INTO Delivery (deliveryFactoryId, deliveryFactoryName, deliveryDate) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            deliveryStmt.setInt(1, firstItem.getFactoryId());
            deliveryStmt.setString(2, firstItem.getFactoryName());
            deliveryStmt.setDate(3, Date.valueOf(firstItem.getDate()));
            deliveryStmt.executeUpdate();

            //Get generated deliveryId
            ResultSet keys = deliveryStmt.getGeneratedKeys();
            if (!keys.next()) {
                throw new SQLException("Failed to generate delivery ID");
            }
            int deliveryId = keys.getInt(1);

            //Process cart items
            for (DeliveryCartTM item : cartList) {

                // 🔒 Lock stock row
                ResultSet rs = CRUDUtil.execute(
                        "SELECT availableQuantity FROM Stock WHERE id=? FOR UPDATE",
                        item.getStockId()
                );

                rs.next();

                if (rs.getInt("availableQuantity") < item.getQty()) {
                    throw new RuntimeException(
                            "Not enough stock for Stock ID: " + item.getStockId()
                    );
                }

                //Insert into DeliveryStock Associate Table
                CRUDUtil.execute(
                        "INSERT INTO DeliveryStock (deliveryStockId, stockId, deliveryQty) VALUES (?,?,?)",
                        deliveryId,
                        item.getStockId(),
                        item.getQty()
                );

                //Update Stock
                CRUDUtil.execute(
                        "UPDATE Stock SET availableQuantity = availableQuantity - ? WHERE id=?",
                        item.getQty(),
                        item.getStockId()
                );
            }

            con.commit();
            return true;

        } catch (Exception e) {
            con.rollback();
            throw e;

        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public void printDeliveryTea(int selectedMonthNo, int selectedYear) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();

            InputStream reportObject = getClass().getResourceAsStream("/lk/ijse/ceylonteapay/reports/delivery.jrxml");

            if (reportObject == null) {
                throw new RuntimeException("customer.jrxml NOT FOUND");
            }

            JasperReport jr  = JasperCompileManager.compileReport(reportObject); // this is method throws JRException

            Map<String,Object> params = new HashMap<>();
            params.put("DELIVERY_MONTH",selectedMonthNo);
            params.put("DELIVERY_YEAR",selectedYear);

            JasperPrint jp = JasperFillManager.fillReport(jr , params , conn); // fill report (jasperreport, params ,connection)

            JasperViewer.viewReport(jp,false);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
