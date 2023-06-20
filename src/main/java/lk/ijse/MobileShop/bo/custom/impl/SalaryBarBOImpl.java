package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.SalaryBarBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.EmployeeSalaryDAO;
import lk.ijse.MobileShop.dao.custom.impl.EmployeeSalaryDAOImpl;
import lk.ijse.MobileShop.dto.SalaryDTO;
import lk.ijse.MobileShop.entity.Salary;

import java.sql.SQLException;

public class SalaryBarBOImpl implements SalaryBarBO {
    EmployeeSalaryDAO employeeSalaryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE_SALARY);

    @Override
    public SalaryDTO getAllSalaryData(String id) throws SQLException, ClassNotFoundException {
        Salary salary = employeeSalaryDAO.getData(id);
        return new SalaryDTO(
                salary.getEmployee_id(),
                salary.getSalary_id(),
                salary.getMonthly_Attendance_count(),
                salary.getDate(),
                salary.getTime(),
                String.valueOf(salary.getSalary())
        );
    }
}
