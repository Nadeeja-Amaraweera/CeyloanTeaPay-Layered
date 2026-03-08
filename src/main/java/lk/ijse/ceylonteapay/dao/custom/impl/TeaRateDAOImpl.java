package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.TeaRateDAO;
import lk.ijse.ceylonteapay.db.DBConnection;
import lk.ijse.ceylonteapay.dto.TeaRateDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeaRateDAOImpl implements TeaRateDAO {
    @Override
    public boolean addTeaRate(TeaRateDTO teaRateDTO) throws Exception {

        return CRUDUtil.execute("INSERT INTO TeaRate (Month,Year,ratePerKg) VALUES (?,?,?)",teaRateDTO.getMonth(),teaRateDTO.getYear(),teaRateDTO.getRate());
    }

    @Override
    public ObservableList<TeaRateDTO> loadTeaRate() throws Exception {

        ObservableList<TeaRateDTO> list = FXCollections.observableArrayList();

        ResultSet rs = CRUDUtil.execute("SELECT * FROM TeaRate ORDER BY Year DESC");

        while (rs.next()){
            int rateId = rs.getInt("rateId");
            String month = rs.getString("Month");
            int year = rs.getInt("Year");
            double ratePerKg = rs.getDouble("ratePerKg");

            TeaRateDTO teaRateDTO = new TeaRateDTO(rateId,month,year,ratePerKg);
            list.add(teaRateDTO);
        }
        return list;

    }

    @Override
    public boolean deleteRate(int id) throws Exception {

        return CRUDUtil.execute("DELETE FROM TeaRate WHERE rateId = ?",id);
    }
}
