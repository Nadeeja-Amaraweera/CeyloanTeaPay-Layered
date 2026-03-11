package lk.ijse.ceylonteapay.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.custom.IncomeBO;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.DAOFactory;
import lk.ijse.ceylonteapay.dao.custom.IncomeDAO;
import lk.ijse.ceylonteapay.dao.custom.PaymentDAO;
import lk.ijse.ceylonteapay.dto.IncomeDTO;
import lk.ijse.ceylonteapay.entity.Income;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeBOImpl implements IncomeBO {

    IncomeDAO incomeDAO = (IncomeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INCOME);

    @Override
    public ObservableList<IncomeDTO> getAll() throws Exception {

        ObservableList<Income> incomes = incomeDAO.getAll();;
        ObservableList<IncomeDTO> incomeDTOS = FXCollections.observableArrayList();

        for (Income income : incomes) {
            incomeDTOS.add(new IncomeDTO(
                    income.getIncomeId(),
                    income.getMonth(),
                    income.getYear(),
                    income.getTeaSalary(),
                    income.getOtherWorkSalary(),
                    income.getThisMonthIncome(),
                    income.getFinalIncome()
            ));
        }
        return incomeDTOS;
    }

    @Override
    public boolean save(IncomeDTO dto) throws Exception {

        return incomeDAO.save(new Income(
                dto.getMonth(),
                dto.getYear(),
                dto.getTeaSalary(),
                dto.getOtherWorkSalary(),
                dto.getThisMonthIncome(),
                dto.getFinalIncome()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return incomeDAO.delete(id);
    }

}
