package lk.ijse.ceylonteapay.dao;

import lk.ijse.ceylonteapay.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDUtil {
    public static <T>T execute(String sql,Object... ob) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        for (int i = 0; i < ob.length; i++) {
            pstm.setObject(i+1,ob[i]);
        }
        if (sql.startsWith("SELECT")){
            return (T)pstm.executeQuery();
        }else {
            return (T)(Boolean)(pstm.executeUpdate()>0);
        }
    }
}
