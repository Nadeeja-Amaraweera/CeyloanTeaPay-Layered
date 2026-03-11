package lk.ijse.ceylonteapay.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.custom.EmployeeBO;
import lk.ijse.ceylonteapay.dao.DAOFactory;
import lk.ijse.ceylonteapay.dao.custom.EmployeeDAO;
import lk.ijse.ceylonteapay.dto.EmployeeDTO;
import lk.ijse.ceylonteapay.entity.Employee;

import java.sql.SQLException;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);
    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws Exception {
        return employeeDAO.save(new Employee(employeeDTO.getName(),employeeDTO.getNic(), employeeDTO.getDob(),employeeDTO.getAddress(),employeeDTO.getGender(),employeeDTO.getTelNo()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws Exception {
        return employeeDAO.update(new Employee(employeeDTO.getName(),employeeDTO.getNic(),employeeDTO.getDob(),employeeDTO.getAddress(),employeeDTO.getGender(),employeeDTO.getTelNo(),employeeDTO.getId()));
    }

    @Override
    public boolean deleteEmployee(String id) throws Exception {
        return employeeDAO.delete(id);
    }

    @Override
    public ObservableList<EmployeeDTO> getAllEmployees() throws Exception {
        ObservableList<Employee> employees = employeeDAO.getAll();
        ObservableList<EmployeeDTO> employeeDTOS = FXCollections.observableArrayList();

        for (Employee employee:employees){
            EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(),employee.getName(),employee.getDob(),employee.getNic(),employee.getAddress(),employee.getGender(),employee.getTelNo());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }
}
