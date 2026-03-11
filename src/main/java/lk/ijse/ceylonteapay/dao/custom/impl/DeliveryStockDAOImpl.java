package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.DeliveryStockDAO;
import lk.ijse.ceylonteapay.entity.DeliveryStock;

import java.sql.SQLException;

public class DeliveryStockDAOImpl implements DeliveryStockDAO {
    public boolean saveDeliveryStock(int generatedId, int stockId, int qty) throws Exception {
        return CRUDUtil.execute(
                "INSERT INTO DeliveryStock (deliveryStockId, stockId, deliveryQty) VALUES (?,?,?)",
                generatedId,
                stockId,
                qty
        );
    }

    @Override
    public ObservableList<DeliveryStock> getAll() throws SQLException, ClassNotFoundException, Exception {
        return null;
    }

    @Override
    public boolean save(DeliveryStock dto) throws SQLException, ClassNotFoundException, Exception {
        return false;
    }

    @Override
    public boolean update(DeliveryStock dto) throws SQLException, ClassNotFoundException, Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException, Exception {
        return false;
    }
}
