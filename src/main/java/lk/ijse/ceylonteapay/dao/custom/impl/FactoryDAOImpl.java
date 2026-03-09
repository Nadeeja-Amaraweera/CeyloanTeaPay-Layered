package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.FactoryDAO;
import lk.ijse.ceylonteapay.dto.FactoryDTO;
import lk.ijse.ceylonteapay.entity.Factory;


import java.sql.ResultSet;
import java.sql.SQLException;

public class FactoryDAOImpl implements FactoryDAO {

    @Override
    public ObservableList<Factory> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT * FROM Factory ORDER BY FactoryId DESC");

        ObservableList<Factory> list = FXCollections.observableArrayList();

        while (rs.next()){
            int factoryId = rs.getInt("FactoryId");
            String factoryName = rs.getString("FactoryName");
            String factoryAddress = rs.getString("FactoryAddress");


            Factory factoryDTO = new Factory(factoryId,factoryName,factoryAddress);
            list.add(factoryDTO);
        }
        return list;
    }

    @Override
    public boolean save(Factory dto) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO Factory (FactoryName , FactoryAddress) VALUES (?, ?)",dto.getFactoryName(),dto.getFactoryAddress());

    }

    @Override
    public boolean update(Factory factoryDTO) throws Exception {

        return CRUDUtil.execute("UPDATE Factory SET FactoryName = ?, FactoryAddress = ? WHERE FactoryId = ?",factoryDTO.getFactoryName(),factoryDTO.getFactoryAddress(),factoryDTO.getFactoryId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM Factory WHERE FactoryId = ?",id);
    }

    @Override
    public ObservableList<Factory> getComboFactory() throws Exception {

        ResultSet rs = CRUDUtil.execute("SELECT FactoryId,FactoryName,FactoryAddress FROM Factory");

        ObservableList<Factory> list = FXCollections.observableArrayList();

        while (rs.next()){
            int id = rs.getInt("FactoryId");
            String name = rs.getString("FactoryName");
            String address = rs.getString("FactoryAddress");

            Factory Factory = new Factory(id,name,address);
            list.add(Factory);
        }
        return list;
    };

}
