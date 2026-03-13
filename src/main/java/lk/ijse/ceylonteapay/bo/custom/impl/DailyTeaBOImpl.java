package lk.ijse.ceylonteapay.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.custom.DailyTeaBO;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.DAOFactory;
import lk.ijse.ceylonteapay.dao.QueryDAO;
import lk.ijse.ceylonteapay.dao.QueryDAOImpl;
import lk.ijse.ceylonteapay.dao.custom.DailyTeaDAO;
import lk.ijse.ceylonteapay.dto.DailyTeaDTO;
import lk.ijse.ceylonteapay.entity.DailyTea;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DailyTeaBOImpl implements DailyTeaBO {
    DailyTeaDAO dailyTeaDAO = (DailyTeaDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DAILY_TEA);

    @Override
    public boolean saveDailyTea(DailyTeaDTO dailyTeaDTO) throws Exception {


        return dailyTeaDAO.save(new DailyTea(
                dailyTeaDTO.getEmpID()
                ,dailyTeaDTO.getEmpName()
                ,dailyTeaDTO.getLndID()
                ,dailyTeaDTO.getLndName()
                , Date.valueOf(dailyTeaDTO.getDateCollected()).toLocalDate()
                ,dailyTeaDTO.getFullWeight()
                ,dailyTeaDTO.getBagWeight()
                ,dailyTeaDTO.getWaterWeight()
                ,dailyTeaDTO.getTotalWeight()
                ,dailyTeaDTO.getQuality()));
    }

    @Override
    public boolean updateDailyTea(DailyTeaDTO dailyTeaDTO) throws Exception {

        return dailyTeaDAO.update(new DailyTea(
                dailyTeaDTO.getEmpID()
                ,dailyTeaDTO.getLndID()
                ,Date.valueOf(dailyTeaDTO.getDateCollected())
                ,dailyTeaDTO.getFullWeight()
                ,dailyTeaDTO.getBagWeight()
                ,dailyTeaDTO.getWaterWeight()
                ,dailyTeaDTO.getTotalWeight()
                ,dailyTeaDTO.getQuality()
                ,dailyTeaDTO.getTeaID()));
    }

    @Override
    public boolean deleteDailyTea(String teaID) throws Exception {

        return dailyTeaDAO.delete(teaID);
    }

    @Override
    public ObservableList<DailyTeaDTO> getAllDailyTea() throws Exception {

        ObservableList<DailyTea> dailyTeas = dailyTeaDAO.getAll();
        ObservableList<DailyTeaDTO> list = FXCollections.observableArrayList();

        for (DailyTea dailyTea:dailyTeas){
            DailyTeaDTO dailyTeaDTO = new DailyTeaDTO(
                    dailyTea.getTea_ID(), dailyTea.getEmp_ID(), dailyTea.getEmpName(),  // include empName
                    dailyTea.getLnd_ID(), dailyTea.getLandName(),        // include landName
                    dailyTea.getDate_Collected(), dailyTea.getFull_Weight(), dailyTea.getBag_Weight(), dailyTea.getWater_Weight(), dailyTea.getTotal_Weight(), dailyTea.getQuality()
            );
            list.add(dailyTeaDTO);
        }
        return list;



    }

    public Integer checkTeaId(int teaId) throws Exception {
        Integer id = dailyTeaDAO.checkTeaId(teaId);

        if (id != null) {
            return id;
        }
        return null;
    }

    public Double loadTeaSalaryByMonth(int selectedMonthNumber, int selectedEmpId) throws SQLException {

        Double totalWeight = dailyTeaDAO.loadTeaSalaryByMonth(selectedMonthNumber, selectedEmpId);

        if (totalWeight != null) {
            return totalWeight;
        }
        return 0.0;

    }


}
