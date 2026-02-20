package lk.ijse.ceylonteapay.dao;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;

public interface EmployeeDAO {
    public boolean saveEmployee(EmployeeDTO employeeDTO)throws Exception;

    public boolean deleteEmployee(int id)throws Exception;

    public boolean updateEmployee(EmployeeDTO employeeDTO)throws Exception;

    public ObservableList<EmployeeDTO> getAllEmployees()throws Exception;
}
