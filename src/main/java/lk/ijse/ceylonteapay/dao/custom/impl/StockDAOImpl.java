package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.StockDAO;
import lk.ijse.ceylonteapay.dto.FactoryDTO;
import lk.ijse.ceylonteapay.dto.StockDTO;
import lk.ijse.ceylonteapay.entity.Stock;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {

    @Override
    public boolean save(Stock stockDTO) throws Exception {

        return CRUDUtil.execute("INSERT INTO Stock (date,quality,quantity,availableQuantity) VALUES (?,?,?,?)", stockDTO.getDate(), stockDTO.getQuality(), stockDTO.getQuantity(), stockDTO.getAvailableQuantity());
    }

    @Override
    public ObservableList<Stock> getAll() throws Exception {
        ResultSet rs = CRUDUtil.execute("SELECT * FROM Stock ORDER BY id DESC");

        ObservableList<Stock> list = FXCollections.observableArrayList();

        while (rs.next()) {
            int stockId = rs.getInt("id");
            LocalDate date = rs.getDate("date").toLocalDate();
            String quality = rs.getString("quality");
            int qty = rs.getInt("quantity");
            int avaQty = rs.getInt("availableQuantity");

            Stock stock = new Stock(stockId, date, quality, qty, avaQty);
            list.add(stock);
        }
        return list;
    }

    @Override
    public boolean update(Stock stockDTO) throws Exception {

        return CRUDUtil.execute("UPDATE Stock SET date = ?, quality = ? ,quantity = ?, availableQuantity = ? WHERE id = ?", Date.valueOf(stockDTO.getDate()), stockDTO.getQuality(), stockDTO.getQuantity(), stockDTO.getAvailableQuantity(), stockDTO.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM Stock WHERE id = ?", id);
    }

    @Override
    public List<Stock> getStockSummary() throws SQLException {

        List<Stock> list = new ArrayList<>();

        ResultSet rs = CRUDUtil.execute("SELECT quality, quantity,availableQuantity FROM Stock");

        while (rs.next()) {
            list.add(new Stock(
                    null, // date not needed
                    rs.getString("quality"),
                    rs.getInt("quantity"),
                    rs.getInt("availableQuantity")
            ));
        }
        return list;
    }

    @Override
    public int getAvailableQty(int stockId) throws Exception {
        ResultSet rs = CRUDUtil.execute("SELECT availableQuantity FROM Stock WHERE id=? FOR UPDATE", stockId);
        if (rs.next()) {
            return rs.getInt("availableQuantity");
        }
        return 0; // or throw an exception if stock not found
    }

    @Override
    public boolean updateAvailableQty(int qty, int stockId) throws Exception {
        return CRUDUtil.execute(
                "UPDATE Stock SET availableQuantity = availableQuantity - ? WHERE id=?",
                qty,
                stockId
        );
    }


}
