package lk.ijse.ceylonteapay.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.SuperBO;
import lk.ijse.ceylonteapay.dto.LandDTO;
import lk.ijse.ceylonteapay.entity.Land;

import java.sql.SQLException;

public interface LandBO extends SuperBO {
    public ObservableList<LandDTO> getAllLands() throws SQLException, ClassNotFoundException;

    public boolean saveLand(LandDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateLand(LandDTO dto) throws SQLException, ClassNotFoundException, Exception;

    public boolean deleteLand(String id) throws SQLException, ClassNotFoundException;
}
