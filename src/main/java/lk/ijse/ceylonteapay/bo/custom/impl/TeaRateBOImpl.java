package lk.ijse.ceylonteapay.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.custom.TeaRateBO;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.DAOFactory;
import lk.ijse.ceylonteapay.dao.custom.TeaRateDAO;
import lk.ijse.ceylonteapay.dto.TeaRateDTO;
import lk.ijse.ceylonteapay.entity.TeaRate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeaRateBOImpl implements TeaRateBO {

    TeaRateDAO teaRateDAO = (TeaRateDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TEA_RATE);

    @Override
    public boolean saveTeaRate(TeaRateDTO entity) throws Exception {

        return teaRateDAO.save(new TeaRate(entity.getMonth(),entity.getYear(),entity.getRate()));
    }

    @Override
    public ObservableList<TeaRateDTO> getAllTeaRate() throws Exception {

        ObservableList<TeaRate> teaRates = teaRateDAO.getAll();
        ObservableList<TeaRateDTO> teaRateDTOS = FXCollections.observableArrayList();

        for (TeaRate teaRate:teaRates){
            TeaRateDTO teaRateDTO = new TeaRateDTO(teaRate.getRateId(),teaRate.getMonth(),teaRate.getYear(),teaRate.getRatePerKg());
            teaRateDTOS.add(teaRateDTO);
        }
        return teaRateDTOS;

    }

    @Override
    public boolean deleteTeaRate(String id) throws Exception {

        return teaRateDAO.delete(id);
    }

}
