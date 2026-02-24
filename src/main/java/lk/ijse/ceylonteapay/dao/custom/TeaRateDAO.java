package lk.ijse.ceylonteapay.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dto.TeaRateDTO;

public interface TeaRateDAO {
    public boolean addTeaRate(TeaRateDTO teaRateDTO) throws Exception;

    public ObservableList<TeaRateDTO> loadTeaRate()throws Exception;

    public boolean deleteRate(int id) throws Exception;
}
