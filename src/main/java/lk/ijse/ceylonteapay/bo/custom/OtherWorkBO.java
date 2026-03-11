package lk.ijse.ceylonteapay.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.bo.SuperBO;
import lk.ijse.ceylonteapay.dto.OtherWorkDTO;
import lk.ijse.ceylonteapay.entity.OtherWork;

import java.sql.Date;

public interface OtherWorkBO extends SuperBO {
    public ObservableList<OtherWorkDTO> getAllOtherWorks() throws Exception ;

    public boolean saveOtherWork(OtherWorkDTO otherWorkDTO) throws Exception ;


    public boolean updateOtherWork(OtherWorkDTO otherWorkDTO) throws Exception ;


    public boolean deleteOtherWork(String WorkID) throws Exception ;
}
