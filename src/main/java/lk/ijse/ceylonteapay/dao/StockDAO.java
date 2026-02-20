package lk.ijse.ceylonteapay.dao;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dto.StockDTO;

import java.sql.SQLException;
import java.util.List;

public interface StockDAO {
    public boolean saveStock(StockDTO stockDTO) throws Exception;

    public ObservableList<StockDTO> getStock() throws Exception;

    public boolean updateStock(StockDTO stockDTO) throws Exception;

    public boolean deleteStock(int id) throws Exception;

    public List<StockDTO> getStockSummary() throws SQLException;
}
