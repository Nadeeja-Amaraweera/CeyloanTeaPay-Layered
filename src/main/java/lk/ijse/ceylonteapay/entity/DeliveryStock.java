package lk.ijse.ceylonteapay.entity;

public class DeliveryStock {
    private int deliveryStockId;
    private int stockId;
    private int deliveryQty;

    public DeliveryStock(int deliveryStockId, int stockId, int deliveryQty) {
        this.deliveryStockId = deliveryStockId;
        this.stockId = stockId;
        this.deliveryQty = deliveryQty;
    }

    public int getDeliveryStockId() {
        return deliveryStockId;
    }

    public void setDeliveryStockId(int deliveryStockId) {
        this.deliveryStockId = deliveryStockId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getDeliveryQty() {
        return deliveryQty;
    }

    public void setDeliveryQty(int deliveryQty) {
        this.deliveryQty = deliveryQty;
    }
}
