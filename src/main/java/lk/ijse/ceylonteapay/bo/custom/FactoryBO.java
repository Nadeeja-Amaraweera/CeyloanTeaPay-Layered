package lk.ijse.ceylonteapay.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.SuperBO;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dto.FactoryDTO;
import lk.ijse.ceylonteapay.entity.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface FactoryBO extends SuperBO {
    public boolean saveFactory(FactoryDTO dto) throws Exception;

    public boolean updateFactory(FactoryDTO factoryDTO) throws Exception;

    public boolean deleteFactory(String id) throws Exception;

    public ObservableList<FactoryDTO> getAllFactory() throws Exception;

    public ObservableList<FactoryDTO> getComboFactory() throws Exception ;
}
