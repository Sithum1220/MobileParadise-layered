package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.EmployeeAddBO;
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
import java.util.ArrayList;

public class EmployeeAddBOImpl implements EmployeeAddBO {
    EmployeeDAO employeeDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE);
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(
                employeeDTO.getEmployee_id(),
                employeeDTO.getFirst_name(),
                employeeDTO.getLast_name(),
                employeeDTO.getStreet(),
                employeeDTO.getCity(),
                employeeDTO.getLane(),
                employeeDTO.getRole(),
                employeeDTO.getDate(),
                employeeDTO.getNic(),
                employeeDTO.getEmail(),
                employeeDTO.getContact_number()
        ));
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(
                userDTO.getEmployee_id(),
                userDTO.getUser_name(),
                userDTO.getPassword(),
                userDTO.getRole()
        ));
    }

    @Override
    public String getAllEmployeeCount() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAllCount();
    }

    @Override
    public ArrayList<String> getAllEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAllId();
    }
}
