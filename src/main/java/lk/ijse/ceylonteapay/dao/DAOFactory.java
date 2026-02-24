package lk.ijse.ceylonteapay.dao;

import lk.ijse.ceylonteapay.dao.custom.impl.EmployeeDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return daoFactory==null? new DAOFactory():daoFactory;
    }

    public enum DAOType{
        EMPLOYEE,FACTORY,LAND,INCOME,OTHER_WORK,DAILY_TEA,PAYMENT,STOCK,TEA_RATE
    }

    public SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case EMPLOYEE :
                return new EmployeeDAOImpl();
            default:
                return null;
        }
    }
}
