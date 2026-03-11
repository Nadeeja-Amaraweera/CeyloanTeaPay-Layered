package lk.ijse.ceylonteapay.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.custom.OtherWorkBO;
import lk.ijse.ceylonteapay.dao.CRUDUtil;
import lk.ijse.ceylonteapay.dao.DAOFactory;
import lk.ijse.ceylonteapay.dao.QueryDAO;
import lk.ijse.ceylonteapay.dao.QueryDAOImpl;
import lk.ijse.ceylonteapay.dao.custom.OtherWorkDAO;
import lk.ijse.ceylonteapay.dto.OtherWorkDTO;
import lk.ijse.ceylonteapay.entity.OtherWork;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;

public class OtherWorkBOImpl implements OtherWorkBO {

    OtherWorkDAO otherWorkDAO = (OtherWorkDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.OTHER_WORK);

    @Override
    public ObservableList<OtherWorkDTO> getAllOtherWorks() throws Exception {

        ObservableList<OtherWork> otherWorks = otherWorkDAO.getAll();;
        ObservableList<OtherWorkDTO> otherWorkDTOs = FXCollections.observableArrayList();

        for (OtherWork otherWork : otherWorks) {
            otherWorkDTOs.add(new OtherWorkDTO(
                    otherWork.getWork_ID(),
                    otherWork.getEmp_ID(),
                    otherWork.getEmp_Name(), // include empName
                    otherWork.getLnd_ID(),
                    otherWork.getLand_Name(), // include landName
                    otherWork.getDate(),
                    otherWork.getDetails(),
                    otherWork.getSalary()
            ));

        }
        return otherWorkDTOs;
    }

    @Override
    public boolean saveOtherWork(OtherWorkDTO otherWorkDTO) throws Exception {

        return otherWorkDAO.save(new OtherWork(
                otherWorkDTO.getEmp_ID(),
                otherWorkDTO.getLnd_Id(),
                Date.valueOf(otherWorkDTO.getDate()),
                otherWorkDTO.getDetails(),
                otherWorkDTO.getSalary()));
    }

    @Override
    public boolean updateOtherWork(OtherWorkDTO otherWorkDTO) throws Exception {

        return otherWorkDAO.update(new OtherWork(otherWorkDTO.getEmp_ID(),
                otherWorkDTO.getLnd_Id(),
                Date.valueOf(otherWorkDTO.getDate()),
                otherWorkDTO.getDetails(),
                otherWorkDTO.getSalary(),
                otherWorkDTO.getWorkID()));
    }

    @Override
    public boolean deleteOtherWork(String WorkID) throws Exception {

        return otherWorkDAO.delete(WorkID);
    }

}
