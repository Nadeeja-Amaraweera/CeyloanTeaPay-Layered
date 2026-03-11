package lk.ijse.ceylonteapay.dao;

import lk.ijse.ceylonteapay.dao.custom.OtherWorkDAO;
import lk.ijse.ceylonteapay.dao.custom.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return daoFactory==null? new DAOFactory():daoFactory;
    }

    public enum DAOType{
        EMPLOYEE,FACTORY,LAND,INCOME,OTHER_WORK,DAILY_TEA,PAYMENT,STOCK,TEA_RATE,DELIVERY,DELIVERY_STOCK
    }

    public SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case EMPLOYEE :
                return new EmployeeDAOImpl();
            case FACTORY:
                return new FactoryDAOImpl();
            case LAND:
                return new LandDAOImpl();
            case INCOME:
                return new IncomeDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case DAILY_TEA:
                return new DailyTeaDAOImpl();
            case OTHER_WORK:
                return new OtherWorkDAOImpl();
//            case PAYMENT:
//                return new PaymentDAOImpl();
//            case DELIVERY:
//                return new DeliveryDAOImpl();
//            case DELIVERY_STOCK:
//                return new DeliveryStockDAOImpl();
            case TEA_RATE:
                return new TeaRateDAOImpl();
            default:
                return null;
        }
    }
}
