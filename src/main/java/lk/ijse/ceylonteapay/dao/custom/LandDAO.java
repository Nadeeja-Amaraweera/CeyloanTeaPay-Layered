package lk.ijse.ceylonteapay.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dto.LandDTO;

public interface LandDAO {
    public boolean saveLand(LandDTO landDTO)throws Exception;

    public ObservableList<LandDTO> getAllLands()throws Exception;

    public boolean updateLand(LandDTO landDTO)throws Exception;

    public boolean deleteLand(int id)throws Exception;
}
