package lk.ijse.MobileShop.dao;

import lk.ijse.MobileShop.dao.custom.CustomerDAO;
import lk.ijse.MobileShop.dao.custom.UserDAO;
import lk.ijse.MobileShop.dao.custom.impl.*;

import static lk.ijse.MobileShop.dao.DAOFactory.DAOType.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType{
        CUSTOMER,CUSTOMER_ORDER,CUSTOMER_ORDER_DETAILS,EMPLOYEE_ATTENDANCE,EMPLOYEE,EMPLOYEE_SALARY,ITEM,QUERY,REPAIR,
        SUPPLIER,SUPPLIER_ORDER,SUPPLIER_ORDER_DETAILS,USER
    }

    public <T extends SuperDAO>T getDAO(DAOType daoType){
        switch (daoType){
            case CUSTOMER :
                return (T) new CustomerDAOImpl();
            case CUSTOMER_ORDER:
                return (T) new CustomerOrderDAOImpl();
            case CUSTOMER_ORDER_DETAILS:
                return (T) new CustomerOrderDetailsDAOImpl();
            case EMPLOYEE_ATTENDANCE:
                return (T) new EmployeeAttendanceDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case EMPLOYEE_SALARY:
                return (T) new EmployeeSalaryDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case REPAIR:
                return (T) new RepairDAOImpl();
            case SUPPLIER:
                return (T) new SupplierDAOImpl();
            case SUPPLIER_ORDER:
                return (T) new SupplierOrderDAOImpl();
            case SUPPLIER_ORDER_DETAILS:
                return (T) new SupplierOrderDetailsDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            default:
                return null;
        }
    }

}
