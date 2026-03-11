package lk.ijse.ceylonteapay.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.SuperBO;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dto.StockDTO;
import lk.ijse.ceylonteapay.entity.Stock;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface StockBO extends SuperBO {

    public boolean saveStock(StockDTO stockDTO) throws Exception ;

    public ObservableList<StockDTO> getStock() throws Exception ;

    public boolean updateStock(StockDTO stockDTO) throws Exception;

    public boolean deleteStock(String id) throws Exception;

    public ObservableList<StockDTO> getComboStock() throws Exception;

    public List<StockDTO> getStockSummary() throws SQLException, Exception;

}
