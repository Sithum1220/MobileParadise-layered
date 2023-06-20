package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.EmployeeUpdateBO;
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

public class EmployeeUpdateBOImpl implements EmployeeUpdateBO {
    EmployeeDAO employeeDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE);
    UserDAO userDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);


    @Override
    public EmployeeDTO getAllEmployeeData(String id) throws SQLException, ClassNotFoundException {
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
    public UserDTO getAllUserData(String id) throws SQLException, ClassNotFoundException {
        User user = userDAO.getData(id);
        return new UserDTO(
                user.getEmployee_id(),
                user.getUser_name(),
                user.getPassword(),
                user.getRole()
        );
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(
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
    public boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(
                userDTO.getEmployee_id(),
                userDTO.getUser_name(),
                userDTO.getPassword(),
                userDTO.getRole()
        ));
    }
}
