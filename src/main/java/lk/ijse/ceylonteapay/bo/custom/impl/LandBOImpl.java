package lk.ijse.ceylonteapay.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.custom.LandBO;
import lk.ijse.ceylonteapay.dao.DAOFactory;
import lk.ijse.ceylonteapay.dao.custom.LandDAO;
import lk.ijse.ceylonteapay.dto.FactoryDTO;
import lk.ijse.ceylonteapay.dto.LandDTO;
import lk.ijse.ceylonteapay.entity.Factory;
import lk.ijse.ceylonteapay.entity.Land;

import java.sql.SQLException;

public class LandBOImpl implements LandBO {

    LandDAO landDAO = (LandDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LAND);
    @Override
    public ObservableList<LandDTO> getAllLands() throws SQLException, ClassNotFoundException {
        ObservableList<Land> lands = landDAO.getAll();
        ObservableList<LandDTO> landDTOS = FXCollections.observableArrayList();

        for (Land land:lands){
//            lndID,landName,landNo
            LandDTO landDTO = new LandDTO(land.getLndID(),land.getLndName(),land.getLndNo());
            landDTOS.add(landDTO);
        }
        return landDTOS;
    }

    @Override
    public boolean saveLand(LandDTO dto) throws SQLException, ClassNotFoundException {
        return landDAO.save(new Land(dto.getLndName(),dto.getLndNo()));
    }

    @Override
    public boolean updateLand(LandDTO dto) throws SQLException, ClassNotFoundException, Exception {
        return landDAO.update(new Land(dto.getLndID(),dto.getLndName(),dto.getLndNo()));
    }

    @Override
    public boolean deleteLand(String id) throws SQLException, ClassNotFoundException {
        return landDAO.delete(id);
    }
}
