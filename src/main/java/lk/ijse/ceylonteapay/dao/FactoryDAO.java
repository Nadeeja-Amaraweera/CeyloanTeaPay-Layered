package lk.ijse.ceylonteapay.dao;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dto.FactoryDTO;

public interface FactoryDAO {
    public boolean addFactory(FactoryDTO factoryDTO)throws Exception;

    public ObservableList<FactoryDTO> getAllFactories()throws Exception;

    public boolean update(FactoryDTO factoryDTO)throws Exception;

    public boolean deleteFactory(int id)throws Exception;
}
