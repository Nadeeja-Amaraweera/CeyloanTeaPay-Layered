package lk.ijse.ceylonteapay.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.ceylonteapay.db.DBConnection;
import lk.ijse.ceylonteapay.dto.LandDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LandDAOImpl implements LandDAO{
    @Override
    public boolean saveLand(LandDTO landDTO) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();

        String sql = "INSERT INTO Land (LandName,LandNo) VALUES (?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,landDTO.getLndName());
        pstm.setString(2,landDTO.getLndNo());

        int result = pstm.executeUpdate();

        return result>0;
    }

    @Override
    public ObservableList<LandDTO> getAllLands() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Land ORDER BY LndID DESC");
        ResultSet rs = ps.executeQuery();

        ObservableList<LandDTO> list = FXCollections.observableArrayList();

        while (rs.next()){
            int lndID = rs.getInt("LndID");
            String landName = rs.getString("LandName");
            String landNo = rs.getString("LandNo");


            LandDTO landDTO = new LandDTO(lndID,landName,landNo);
            list.add(landDTO);
        }
        return  list;
    }

    @Override
    public boolean updateLand(LandDTO landDTO) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Land SET LandName = ?, LandNo = ? WHERE LndID = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,landDTO.getLndName());
        pstm.setString(2,landDTO.getLndNo());
        pstm.setInt(3,landDTO.getLndID());
        int result = pstm.executeUpdate();

        return result>0;
    }

    @Override
    public boolean deleteLand(int id) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();


        String sql = "DELETE FROM Land WHERE LndID = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,id);

        int result = pstm.executeUpdate();

        return result>0;
    }
}
