package lk.ijse.ceylonteapay.dao;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dto.DeliveryCartTM;
import lk.ijse.ceylonteapay.dto.FactoryDTO;
import lk.ijse.ceylonteapay.dto.StockDTO;

public interface DeliveryTeaDAO {
    public ObservableList<FactoryDTO> getComboFactory()throws Exception;

    public ObservableList<StockDTO> getComboStock()throws Exception;

    public boolean placeOrder(ObservableList<DeliveryCartTM> cartList) throws Exception;

    public void printDeliveryTea(int selectedMonthNo, int selectedYear);
}
