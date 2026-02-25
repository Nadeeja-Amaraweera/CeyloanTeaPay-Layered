package lk.ijse.ceylonteapay.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dto.DailyTeaDTO;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;
import lk.ijse.ceylonteapay.dto.LandDTO;

import java.sql.ResultSet;

public interface DailyTeaDAO {
    public boolean addTeaField(DailyTeaDTO teaDTO) throws Exception;

    public boolean updateTeaField(DailyTeaDTO teaDTO)throws Exception;

    public boolean deleteTeaField(int teaID)throws Exception;

    public Integer checkTeaId(int teaId)throws Exception;

    public ObservableList<DailyTeaDTO> getAllTeaFields() throws Exception;

    public ObservableList<EmployeeDTO> getEmployeeId() throws Exception;

    public ObservableList<LandDTO> getLandId()throws Exception;


}
