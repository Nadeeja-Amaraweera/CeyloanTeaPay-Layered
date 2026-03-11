package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.TeaRateDAO;
import lk.ijse.ceylonteapay.db.DBConnection;
import lk.ijse.ceylonteapay.dto.TeaRateDTO;
import lk.ijse.ceylonteapay.entity.TeaRate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeaRateDAOImpl implements TeaRateDAO {
    @Override
    public boolean save(TeaRate entity) throws Exception {

        return CRUDUtil.execute("INSERT INTO TeaRate (Month,Year,ratePerKg) VALUES (?,?,?)",entity.getMonth(),entity.getYear(),entity.getRatePerKg());
    }

    @Override
    public boolean update(TeaRate dto) throws SQLException, ClassNotFoundException, Exception {
        return false;
    }

    @Override
    public ObservableList<TeaRate> getAll() throws Exception {

        ObservableList<TeaRate> list = FXCollections.observableArrayList();

        ResultSet rs = CRUDUtil.execute("SELECT * FROM TeaRate ORDER BY Year DESC");

        while (rs.next()){
            int rateId = rs.getInt("rateId");
            String month = rs.getString("Month");
            int year = rs.getInt("Year");
            double ratePerKg = rs.getDouble("ratePerKg");

            TeaRate teaRate = new TeaRate(rateId,month,year,ratePerKg);
            list.add(teaRate);
        }
        return list;

    }

    @Override
    public boolean delete(String id) throws Exception {

        return CRUDUtil.execute("DELETE FROM TeaRate WHERE rateId = ?",id);
    }
}
