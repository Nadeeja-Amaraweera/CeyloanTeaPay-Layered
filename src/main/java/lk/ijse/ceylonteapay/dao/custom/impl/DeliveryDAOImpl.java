package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.DeliveryDAO;
import lk.ijse.ceylonteapay.entity.Delivery;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryDAOImpl implements DeliveryDAO {
    @Override
    public int generateDeliveryId() throws Exception {

        String iquery = "SELECT LAST_INSERT_ID() AS id FROM Delivery";
        ResultSet rs = CRUDUtil.execute(iquery);

        if (rs.next()) {
            return rs.getInt("id");
        }
        return 0;
    }


    @Override
    public ObservableList<Delivery> getAll() throws SQLException, ClassNotFoundException, Exception {
        return null;
    }

    @Override
    public boolean save(Delivery entity) throws SQLException, ClassNotFoundException, Exception {
        String stmt = "INSERT INTO Delivery (deliveryFactoryId, deliveryFactoryName, deliveryDate) VALUES (?,?,?)";

        return CRUDUtil.execute(stmt,
                entity.getDeliveryFactoryId(),
                entity.getDeliveryFactoryName(),
                Date.valueOf(entity.getDeliveryDate()));
    }

    @Override
    public boolean update(Delivery dto) throws SQLException, ClassNotFoundException, Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException, Exception {
        return false;
    }
}
