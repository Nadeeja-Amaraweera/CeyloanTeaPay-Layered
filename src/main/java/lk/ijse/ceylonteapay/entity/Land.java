package lk.ijse.ceylonteapay.entity;

public class Land {
    private int lndID;
    private String lndName;
    private String lndNo;

    public Land(int lndID, String lndName, String lndNo) {
        this.lndID = lndID;
        this.lndName = lndName;
        this.lndNo = lndNo;
    }

    public Land() {
    }

    public int getLndID() {
        return lndID;
    }

    public void setLndID(int lndID) {
        this.lndID = lndID;
    }

    public String getLndName() {
        return lndName;
    }

    public void setLndName(String lndName) {
        this.lndName = lndName;
    }

    public String getLndNo() {
        return lndNo;
    }

    public void setLndNo(String lndNo) {
        this.lndNo = lndNo;
    }
}
