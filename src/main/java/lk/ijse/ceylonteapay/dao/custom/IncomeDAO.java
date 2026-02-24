package lk.ijse.ceylonteapay.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.dto.IncomeDTO;

public interface IncomeDAO {
    public IncomeDTO getAllTeaSalary(int month, int year)throws Exception;

    public boolean savePayment(IncomeDTO incomeDTO)throws Exception;

    public ObservableList<IncomeDTO> getAllIncomeFields() throws Exception;

    public boolean deleteIncome(int id)throws Exception;
}
