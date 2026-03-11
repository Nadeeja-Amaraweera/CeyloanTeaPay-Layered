package lk.ijse.ceylonteapay.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.SuperBO;
import lk.ijse.ceylonteapay.dto.TeaRateDTO;
import lk.ijse.ceylonteapay.entity.TeaRate;

public interface TeaRateBO extends SuperBO {

    public boolean saveTeaRate(TeaRateDTO entity) throws Exception ;

    public ObservableList<TeaRateDTO> getAllTeaRate() throws Exception ;

    public boolean deleteTeaRate(String id) throws Exception ;
}
