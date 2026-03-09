package lk.ijse.ceylonteapay.dao.custom.impl;

import lk.ijse.ceylonteapay.dao.CRUDUtil;

public class DeliveryStockDAOImpl {
    public boolean saveDeliveryStock(int generatedId, int stockId, int qty) throws Exception {
        return CRUDUtil.execute(
                "INSERT INTO DeliveryStock (deliveryStockId, stockId, deliveryQty) VALUES (?,?,?)",
                generatedId,
                stockId,
                qty
        );
    }
}
