package lk.ijse.ceylonteapay.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.custom.FactoryBO;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.DAOFactory;
import lk.ijse.ceylonteapay.dao.custom.EmployeeDAO;
import lk.ijse.ceylonteapay.dao.custom.FactoryDAO;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;
import lk.ijse.ceylonteapay.dto.FactoryDTO;
import lk.ijse.ceylonteapay.entity.Employee;
import lk.ijse.ceylonteapay.entity.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FactoryBOImpl implements FactoryBO {
    FactoryDAO factoryDAO = (FactoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.FACTORY);
    @Override
    public boolean saveFactory(FactoryDTO dto) throws Exception {
        return factoryDAO.save(new Factory(dto.getFactoryName(),dto.getFactoryAddress()));
    }

    @Override
    public boolean updateFactory(FactoryDTO factoryDTO) throws Exception {
        return factoryDAO.update(new Factory(factoryDTO.getFactoryId(),factoryDTO.getFactoryName(),factoryDTO.getFactoryAddress()));
    }

    @Override
    public boolean deleteFactory(String id) throws SQLException, ClassNotFoundException {
        return factoryDAO.delete(id);
    }

    @Override
    public ObservableList<FactoryDTO> getAllFactory() throws Exception {
        ObservableList<Factory> factories = factoryDAO.getAll();
        ObservableList<FactoryDTO> factoryDTOS = FXCollections.observableArrayList();

        for (Factory factory:factories){
            FactoryDTO factoryDTO = new FactoryDTO(factory.getFactoryId(),factory.getFactoryName(),factory.getFactoryAddress());
            factoryDTOS.add(factoryDTO);
        }
        return factoryDTOS;
    }

    @Override
    public ObservableList<FactoryDTO> getComboFactory() throws Exception {

        ObservableList<Factory> factories = factoryDAO.getComboFactory();
        ObservableList<FactoryDTO> factoryDTOS = FXCollections.observableArrayList();

        for (Factory factory:factories){
            FactoryDTO factoryDTO = new FactoryDTO(factory.getFactoryId(),factory.getFactoryName(),factory.getFactoryAddress());
            factoryDTOS.add(factoryDTO);
        }
        return factoryDTOS;

    }
}
