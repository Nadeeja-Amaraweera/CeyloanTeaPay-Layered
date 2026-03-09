package lk.ijse.ceylonteapay.dao.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.CrudDAO;
import lk.ijse.ceylonteapay.dto.FactoryDTO;
import lk.ijse.ceylonteapay.entity.Factory;

import java.sql.ResultSet;

public interface FactoryDAO extends CrudDAO<Factory> {
    public ObservableList<Factory> getComboFactory() throws Exception ;

}
