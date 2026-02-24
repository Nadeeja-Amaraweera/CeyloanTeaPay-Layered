package lk.ijse.ceylonteapay.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.custom.FactoryDAO;
import lk.ijse.ceylonteapay.dto.FactoryDTO;


import java.sql.ResultSet;

public class FactoryDAOImpl implements FactoryDAO {
    @Override
    public boolean addFactory(FactoryDTO factoryDTO) throws Exception {

        return CRUDUtil.execute("INSERT INTO Factory (FactoryName , FactoryAddress) VALUES (?, ?)",factoryDTO.getFactoryName(),factoryDTO.getFactoryAddress());
    }

    @Override
    public ObservableList<FactoryDTO> getAllFactories() throws Exception {

        ResultSet rs = CRUDUtil.execute("SELECT * FROM Factory ORDER BY FactoryId DESC");

        ObservableList<FactoryDTO> list = FXCollections.observableArrayList();

        while (rs.next()){
            int factoryId = rs.getInt("FactoryId");
            String factoryName = rs.getString("FactoryName");
            String factoryAddress = rs.getString("FactoryAddress");


            FactoryDTO factoryDTO = new FactoryDTO(factoryId,factoryName,factoryAddress);
            list.add(factoryDTO);
        }
        return list;
    }

    @Override
    public boolean update(FactoryDTO factoryDTO) throws Exception {

        return CRUDUtil.execute("UPDATE Factory SET FactoryName = ?, FactoryAddress = ? WHERE FactoryId = ?",factoryDTO.getFactoryName(),factoryDTO.getFactoryAddress(),factoryDTO.getFactoryId());
    }

    @Override
    public boolean deleteFactory(int id) throws Exception {

        return CRUDUtil.execute("DELETE FROM Factory WHERE FactoryId = ?",id);
    }
}
