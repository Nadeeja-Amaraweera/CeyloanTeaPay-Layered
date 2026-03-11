package lk.ijse.ceylonteapay.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.SuperBO;
import lk.ijse.ceylonteapay.dto.DailyTeaDTO;
import lk.ijse.ceylonteapay.entity.DailyTea;

import java.sql.Date;

public interface DailyTeaBO extends SuperBO {

    public boolean saveDailyTea(DailyTeaDTO dailyTeaDTO) throws Exception ;

    public boolean updateDailyTea(DailyTeaDTO dailyTeaDTO) throws Exception ;

    public boolean deleteDailyTea(String teaID) throws Exception ;

    public ObservableList<DailyTeaDTO> getAllDailyTea() throws Exception ;

    public Integer checkTeaId(int teaId)throws Exception;

    public Double loadTeaSalaryByMonth(int selectedMonthNumber, int selectedEmpId) throws Exception;
}
