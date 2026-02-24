package lk.ijse.ceylonteapay.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CrudDAO;
import lk.ijse.ceylonteapay.dto.IncomeDTO;

public interface IncomeDAO extends CrudDAO<IncomeDTO> {
    public IncomeDTO getAllTeaSalary(int month, int year)throws Exception;

}
