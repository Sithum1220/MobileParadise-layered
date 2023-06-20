package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.EmployeeViewBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.EmployeeDAO;
import lk.ijse.MobileShop.dao.custom.UserDAO;
import lk.ijse.MobileShop.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.UserDAOImpl;
import lk.ijse.MobileShop.dto.EmployeeDTO;
import lk.ijse.MobileShop.dto.UserDTO;
import lk.ijse.MobileShop.entity.Employee;
import lk.ijse.MobileShop.entity.User;

import java.sql.SQLException;

public class EmployeeViewBOImpl implements EmployeeViewBO {
    EmployeeDAO employeeDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE);
    UserDAO userDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);

    @Override
    public EmployeeDTO getEmployeeData(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.getData(id);
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
    public UserDTO getUserData(String id) throws SQLException, ClassNotFoundException {
        User user = userDAO.getData(id);
        return new UserDTO(
                user.getEmployee_id(),
                user.getUser_name(),
                user.getPassword(),
                user.getRole()
        );
    }
}
