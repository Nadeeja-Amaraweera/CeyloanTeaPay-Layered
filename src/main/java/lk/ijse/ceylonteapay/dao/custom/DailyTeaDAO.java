package lk.ijse.ceylonteapay.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.CrudDAO;
import lk.ijse.ceylonteapay.dto.DailyTeaDTO;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;
import lk.ijse.ceylonteapay.dto.LandDTO;
import lk.ijse.ceylonteapay.entity.DailyTea;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DailyTeaDAO extends CrudDAO<DailyTea> {

    public Integer checkTeaId(int teaId)throws Exception;

    public Double loadTeaSalaryByMonth(int selectedMonthNumber, int selectedEmpId) throws SQLException;

}
