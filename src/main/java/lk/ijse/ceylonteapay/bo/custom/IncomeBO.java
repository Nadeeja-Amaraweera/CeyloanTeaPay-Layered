package lk.ijse.ceylonteapay.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.SuperBO;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dto.IncomeDTO;
import lk.ijse.ceylonteapay.entity.Income;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IncomeBO extends SuperBO {

    public ObservableList<IncomeDTO> getAll() throws Exception ;

    public boolean save(IncomeDTO dto) throws Exception;

    public boolean delete(String id) throws Exception ;
}
