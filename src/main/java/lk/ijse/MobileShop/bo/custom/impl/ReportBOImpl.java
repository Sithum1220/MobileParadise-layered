package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.ReportBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.EmployeeSalaryDAO;
import lk.ijse.MobileShop.dao.custom.impl.EmployeeSalaryDAOImpl;

import java.sql.SQLException;

public class ReportBOImpl implements ReportBO {
    EmployeeSalaryDAO employeeSalaryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE_SALARY);

    @Override
    public String getAllSalaryPaidEmployeeCount() throws SQLException, ClassNotFoundException {
        return employeeSalaryDAO.getAllCount();
    }
}
