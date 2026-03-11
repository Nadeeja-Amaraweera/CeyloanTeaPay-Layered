package lk.ijse.ceylonteapay.bo;

import lk.ijse.ceylonteapay.bo.custom.StockBO;
import lk.ijse.ceylonteapay.bo.custom.impl.*;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        return boFactory==null? new BOFactory():boFactory;
    }

    public enum BOType{
        EMPLOYEE,FACTORY,LAND,INCOME,OTHER_WORK,DAILY_TEA,PAYMENT,STOCK,TEA_RATE
    }

    public SuperBO getBO(BOFactory.BOType daoType){
        switch (daoType){
            case EMPLOYEE :
                return new EmployeeBOImpl();
            case FACTORY:
                return new FactoryBOImpl();
            case LAND:
                return new LandBOImpl();
            case STOCK:
                return new StockBOImpl();
            case OTHER_WORK:
                return new OtherWorkBOImpl();
            case DAILY_TEA:
                return new DailyTeaBOImpl();
            default:
                return null;
        }
    }
}
