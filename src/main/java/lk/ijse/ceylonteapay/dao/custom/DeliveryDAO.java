package lk.ijse.ceylonteapay.dao.custom;

import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.entity.Delivery;

import java.sql.Date;
import java.sql.ResultSet;

public interface DeliveryDAO {

    public int generateDeliveryId() throws Exception;


    public boolean saveDelivery(Delivery entity) throws Exception;
}
