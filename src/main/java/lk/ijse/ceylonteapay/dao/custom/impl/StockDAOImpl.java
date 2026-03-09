package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.StockDAO;
import lk.ijse.ceylonteapay.dto.StockDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    @Override
    public boolean saveStock(StockDTO stockDTO) throws Exception {

        return CRUDUtil.execute("INSERT INTO Stock (date,quality,quantity,availableQuantity) VALUES (?,?,?,?)", stockDTO.getDate(), stockDTO.getQuality(), stockDTO.getQuantity(), stockDTO.getAvailableQuantity());
    }

    @Override
    public ObservableList<StockDTO> getStock() throws Exception {
        ResultSet rs = CRUDUtil.execute("SELECT * FROM Stock ORDER BY id DESC");

        ObservableList<StockDTO> list = FXCollections.observableArrayList();

        while (rs.next()) {
            int stockId = rs.getInt("id");
            LocalDate date = rs.getDate("date").toLocalDate();
            String quality = rs.getString("quality");
            int qty = rs.getInt("quantity");
            int avaQty = rs.getInt("availableQuantity");

            StockDTO stockDTO = new StockDTO(stockId, date, quality, qty, avaQty);
            list.add(stockDTO);
        }
        return list;
    }

    @Override
    public boolean updateStock(StockDTO stockDTO) throws Exception {

        return CRUDUtil.execute("UPDATE Stock SET date = ?, quality = ? ,quantity = ?, availableQuantity = ? WHERE id = ?", Date.valueOf(stockDTO.getDate()), stockDTO.getQuality(), stockDTO.getQuantity(), stockDTO.getAvailableQuantity(), stockDTO.getId());
    }

    @Override
    public boolean deleteStock(int id) throws Exception {

        return CRUDUtil.execute("DELETE FROM Stock WHERE id = ?", id);
    }

    @Override
    public List<StockDTO> getStockSummary() throws SQLException {

        List<StockDTO> list = new ArrayList<>();

        ResultSet rs = CRUDUtil.execute("SELECT quality, quantity,availableQuantity FROM Stock");

        while (rs.next()) {
            list.add(new StockDTO(
                    null, // date not needed
                    rs.getString("quality"),
                    rs.getInt("quantity"),
                    rs.getInt("availableQuantity")
            ));
        }
        return list;
    }

    public int getAvailableQty(int stockId) throws Exception {;
        ResultSet rs = CRUDUtil.execute("SELECT availableQuantity FROM Stock WHERE id=? FOR UPDATE", stockId);
        if (rs.next()) {
            return rs.getInt("availableQuantity");
        }
        return 0; // or throw an exception if stock not found
    }

    public boolean updateAvailableQty(int qty, int stockId) throws Exception {
        return CRUDUtil.execute(
                "UPDATE Stock SET availableQuantity = availableQuantity - ? WHERE id=?",
                qty,
                stockId
        );
    }
}
