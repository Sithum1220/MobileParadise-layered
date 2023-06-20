package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.SalaryBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.EmployeeAttendaceDAO;
import lk.ijse.MobileShop.dao.custom.EmployeeDAO;
import lk.ijse.MobileShop.dao.custom.EmployeeSalaryDAO;
import lk.ijse.MobileShop.dao.custom.impl.EmployeeAttendanceDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.EmployeeSalaryDAOImpl;
import lk.ijse.MobileShop.dto.EmployeeDTO;
import lk.ijse.MobileShop.dto.SalaryDTO;
import lk.ijse.MobileShop.entity.Employee;
import lk.ijse.MobileShop.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {
    EmployeeDAO employeeDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE);
    EmployeeSalaryDAO employeeSalaryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE_SALARY);
    EmployeeAttendaceDAO employeeAttendaceDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE_ATTENDANCE);


    @Override
    public ArrayList<String> getAllEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAllId();
    }

    @Override
    public EmployeeDTO getAllEmployeeData(String id) throws SQLException, ClassNotFoundException {
        Employee employee =  employeeDAO.getData(id);
        return new EmployeeDTO(
                employee.getEmployee_id(),
                employee.getFirst_name(),
                employee.getLast_name(),
                employee.getStreet(),
                employee.getCity(),
                employee.getLane(),
                employee.getRole(),
                employee.getDate(),
                employee.getNic(),
                employee.getEmail(),
                employee.getContact_number()
        );
    }

    @Override
    public ArrayList<String> getAllEmployeeIdBySalary() throws SQLException, ClassNotFoundException {
        return employeeSalaryDAO.getAllEmpId();
    }

    @Override
    public boolean saveEmloyeeSalary(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException {
        return employeeSalaryDAO.save(new Salary(
                salaryDTO.getEmployee_id(),
                salaryDTO.getSalary_id(),
                Double.parseDouble(salaryDTO.getSalary()),
                salaryDTO.getMonthly_Attendance_count(),
                salaryDTO.getDate(),
                salaryDTO.getTime()
        ));
    }

    @Override
    public SalaryDTO getAllSalaryData(String id) throws SQLException, ClassNotFoundException {
        Salary salary = employeeSalaryDAO.getData(id);
        return new SalaryDTO(
                salary.getEmployee_id(),
                salary.getSalary_id(),
                String.valueOf(salary.getSalary()),
                salary.getMonthly_Attendance_count(),
                salary.getDate(),
                salary.getTime()
        );
    }

    @Override
    public ArrayList<String> getAllSalaryId() throws SQLException, ClassNotFoundException {
        return employeeSalaryDAO.getAllId();
    }

    @Override
    public String getAttendanceCountThisMonth(String id) throws SQLException, ClassNotFoundException {
        return employeeAttendaceDAO.getAttendanceCountThisMonth(id);
    }
}
