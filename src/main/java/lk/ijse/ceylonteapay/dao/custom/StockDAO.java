package lk.ijse.ceylonteapay.dao.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.CrudDAO;
import lk.ijse.ceylonteapay.dto.FactoryDTO;
import lk.ijse.ceylonteapay.dto.StockDTO;
import lk.ijse.ceylonteapay.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface StockDAO extends CrudDAO<Stock> {

    public List<Stock> getStockSummary() throws Exception;

    public ObservableList<Stock> getComboStock() throws Exception;

    public int getAvailableQty(int stockId) throws Exception ;

    public boolean updateAvailableQty(int qty, int stockId) throws Exception ;

}
