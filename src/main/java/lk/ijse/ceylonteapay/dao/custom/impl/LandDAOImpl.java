package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.LandDAO;
import lk.ijse.ceylonteapay.dto.LandDTO;

import java.sql.ResultSet;

public class LandDAOImpl implements LandDAO {
    @Override
    public boolean saveLand(LandDTO landDTO) throws Exception {

        return CRUDUtil.execute("INSERT INTO Land (LandName,LandNo) VALUES (?,?)",landDTO.getLndName(),landDTO.getLndNo());
    }

    @Override
    public ObservableList<LandDTO> getAllLands() throws Exception {

        ResultSet rs = CRUDUtil.execute("SELECT * FROM Land ORDER BY LndID DESC");

        ObservableList<LandDTO> list = FXCollections.observableArrayList();

        while (rs.next()){
            int lndID = rs.getInt("LndID");
            String landName = rs.getString("LandName");
            String landNo = rs.getString("LandNo");


            LandDTO landDTO = new LandDTO(lndID,landName,landNo);
            list.add(landDTO);
        }
        return list;
    }

    @Override
    public boolean updateLand(LandDTO landDTO) throws Exception {


        return CRUDUtil.execute("UPDATE Land SET LandName = ?, LandNo = ? WHERE LndID = ?",landDTO.getLndName(),landDTO.getLndNo(),landDTO.getLndID());
    }

    @Override
    public boolean deleteLand(int id) throws Exception {


        return CRUDUtil.execute("DELETE FROM Land WHERE LndID = ?",id);
    }
}
