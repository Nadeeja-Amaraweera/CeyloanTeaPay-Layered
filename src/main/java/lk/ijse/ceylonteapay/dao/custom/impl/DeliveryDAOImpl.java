package lk.ijse.ceylonteapay.dao.custom.impl;

import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.DeliveryDAO;
import lk.ijse.ceylonteapay.entity.Delivery;

import java.sql.Date;
import java.sql.ResultSet;

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
    public boolean saveDelivery(Delivery entity) throws Exception {

        String stmt = "INSERT INTO Delivery (deliveryFactoryId, deliveryFactoryName, deliveryDate) VALUES (?,?,?)";

        return CRUDUtil.execute(stmt,
                entity.getDeliveryFactoryId(),
                entity.getDeliveryFactoryName(),
                Date.valueOf(entity.getDeliveryDate()));
    }
}
