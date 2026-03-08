package lk.ijse.ceylonteapay.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.SuperBO;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;
import lk.ijse.ceylonteapay.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException, Exception;

    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    public ObservableList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException;

}
