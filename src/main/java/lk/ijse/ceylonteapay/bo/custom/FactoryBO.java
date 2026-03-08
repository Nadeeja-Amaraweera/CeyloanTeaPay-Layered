package lk.ijse.ceylonteapay.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.SuperBO;
import lk.ijse.ceylonteapay.dto.FactoryDTO;
import lk.ijse.ceylonteapay.entity.Factory;

import java.sql.SQLException;

public interface FactoryBO extends SuperBO {
    public boolean saveFactory(FactoryDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateFactory(FactoryDTO factoryDTO) throws Exception;

    public boolean deleteFactory(String id) throws SQLException, ClassNotFoundException;

    public ObservableList<FactoryDTO> getAllFactory() throws SQLException, ClassNotFoundException;
}
