package lk.ijse.ceylonteapay.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.custom.StockBO;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.DAOFactory;
import lk.ijse.ceylonteapay.dao.custom.StockDAO;
import lk.ijse.ceylonteapay.dto.FactoryDTO;
import lk.ijse.ceylonteapay.dto.StockDTO;
import lk.ijse.ceylonteapay.entity.Factory;
import lk.ijse.ceylonteapay.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockBOImpl implements StockBO {
    StockDAO stockDAO = (StockDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STOCK);

    @Override
    public boolean saveStock(StockDTO stockDTO) throws Exception {
        return stockDAO.save(new Stock(stockDTO.getDate(), stockDTO.getQuality(), stockDTO.getQuantity(), stockDTO.getAvailableQuantity()));
    }

    @Override
    public ObservableList<StockDTO> getStock() throws Exception {
        ObservableList<Stock> stocks = stockDAO.getAll();
        ObservableList<StockDTO> stockDTOS = FXCollections.observableArrayList();

        for (Stock stock:stocks){
            StockDTO stockDTO = new StockDTO(stock.getId(),stock.getDate(),stock.getQuality(),stock.getQuantity(),stock.getAvailableQuantity());
            stockDTOS.add(stockDTO);
        }
        return stockDTOS;
    }

    @Override
    public boolean updateStock(StockDTO stockDTO) throws Exception {
        return stockDAO.update(new Stock(stockDTO.getDate(),stockDTO.getQuality(),stockDTO.getQuantity(),stockDTO.getAvailableQuantity()));
    }

    @Override
    public boolean deleteStock(String id) throws Exception {
        return stockDAO.delete(id);
    }

    @Override
    public ObservableList<StockDTO> getComboStock() throws Exception {
        ObservableList<Stock> stocks = stockDAO.getAll();
        ObservableList<StockDTO> stockDTOS = FXCollections.observableArrayList();

        for (Stock stock:stocks){
            StockDTO stockDTO = new StockDTO(stock.getId(),stock.getDate(),stock.getQuality());
            stockDTOS.add(stockDTO);
        }
        return stockDTOS;
    }

    @Override
    public List<StockDTO> getStockSummary() throws Exception {

        List<Stock> list = stockDAO.getStockSummary();

        List<StockDTO> stockDTOS = new ArrayList<>();

        for (Stock stock : list) {

            StockDTO stockDTO = new StockDTO(
                    stock.getQuality(),
                    stock.getQuantity(),
                    stock.getAvailableQuantity()
            );

            stockDTOS.add(stockDTO);
        }

        return stockDTOS;
    }

}
