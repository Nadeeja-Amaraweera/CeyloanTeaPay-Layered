package lk.ijse.ceylonteapay.dao;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;
import lk.ijse.ceylonteapay.dto.LandDTO;
import lk.ijse.ceylonteapay.dto.OtherWorkDTO;

import java.sql.ResultSet;

public interface OtherWorkDAO {
    public ObservableList<OtherWorkDTO> getAllOtherWorkFields() throws Exception;

    public boolean addWorkField(OtherWorkDTO otherWorkDTO)throws Exception;

    public boolean updateWorkField(OtherWorkDTO otherWorkDTO)throws Exception;

    public boolean deleteWorkField(int WorkID)throws Exception;

    public ObservableList<EmployeeDTO> getEmployeeId() throws Exception;

    public ObservableList<LandDTO> getLandId()throws Exception;
}
