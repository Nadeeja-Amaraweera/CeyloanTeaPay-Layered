package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.LandDAO;
import lk.ijse.ceylonteapay.dto.LandDTO;
import lk.ijse.ceylonteapay.entity.Land;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LandDAOImpl implements LandDAO {

    @Override
    public ObservableList<Land> getAll() throws SQLException, ClassNotFoundException {
        ObservableList<Land> list = FXCollections.observableArrayList();


        ResultSet rs = CRUDUtil.execute("SELECT * FROM Land ORDER BY LndID DESC");


        while (rs.next()){
            int lndID = rs.getInt("LndID");
            String landName = rs.getString("LandName");
            String landNo = rs.getString("LandNo");


            Land landDTO = new Land(lndID,landName,landNo);
            list.add(landDTO);
        }
        return list;
    }

    @Override
    public boolean save(Land dto) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO Land (LandName,LandNo) VALUES (?,?)",dto.getLndName(),dto.getLndNo());

    }

    @Override
    public boolean update(Land dto) throws SQLException, ClassNotFoundException, Exception {
        return CRUDUtil.execute("UPDATE Land SET LandName = ?, LandNo = ? WHERE LndID = ?",dto.getLndName(),dto.getLndNo(),dto.getLndID());

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM Land WHERE LndID = ?",id);

    }
}
