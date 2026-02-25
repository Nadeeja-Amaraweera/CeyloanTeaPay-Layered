package lk.ijse.ceylonteapay.dao;

import java.sql.ResultSet;

public interface QueryDAO {
    ResultSet getAllTeaFields() throws Exception;

    ResultSet getAllOtherWorkFields() throws Exception;
}
