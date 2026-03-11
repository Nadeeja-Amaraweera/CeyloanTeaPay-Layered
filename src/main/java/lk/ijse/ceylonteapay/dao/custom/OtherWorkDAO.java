package lk.ijse.ceylonteapay.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CrudDAO;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;
import lk.ijse.ceylonteapay.dto.LandDTO;
import lk.ijse.ceylonteapay.dto.OtherWorkDTO;
import lk.ijse.ceylonteapay.entity.OtherWork;

import java.sql.SQLException;

public interface OtherWorkDAO extends CrudDAO<OtherWork> {

    public Double loadOtherWorkByMonth(int selectedMonthNumber, int selectedEmpId) throws SQLException;
}
