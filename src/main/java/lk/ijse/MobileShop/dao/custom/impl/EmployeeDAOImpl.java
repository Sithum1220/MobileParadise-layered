package lk.ijse.MobileShop.dao.custom.impl;

import lk.ijse.MobileShop.dao.custom.EmployeeDAO;
import lk.ijse.MobileShop.dto.EmployeeDTO;
import lk.ijse.MobileShop.dao.custom.impl.utill.SQLUtill;
import lk.ijse.MobileShop.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT employee_id FROM employee ORDER BY LENGTH(employee_id),employee_id;");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;

    }

    @Override
    public Employee getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM employee where employee_id=?", id);
        Employee employee = new Employee();
        if (set.next()) {
            employee.setEmployee_id(set.getString(1));
            employee.setFirst_name(set.getString(2));
            employee.setLast_name(set.getString(3));
            employee.setStreet(set.getString(4));
            employee.setCity(set.getString(5));
            employee.setLane(set.getString(6));
            employee.setRole(set.getString(7));
            employee.setDate(set.getString(8));
            employee.setNic(set.getString(9));
            employee.setEmail(set.getString(10));
            employee.setContact_number(set.getString(11));
        }
        return employee;
    }

    @Override
    public ArrayList<String> getSearchId(String text) throws SQLException, ClassNotFoundException {
        String SearchId = text + "%";
        ResultSet set = SQLUtill.execute("SELECT employee_id FROM employee where employee_id LIKE ? or first_name LIKE ? or last_name LIKE ?", SearchId, SearchId, SearchId);
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("insert into employee VALUES (?,?,?,?,?,?,?,?,?,?,?)",
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
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM employee WHERE employee_id=?", id);
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE  employee SET " +
                        "first_name=?," +
                        "last_name=?," +
                        "street=? ," +
                        "city=? ," +
                        "lane=? ," +
                        "role =?," +
                        "nic=?," +
                        "email=? " +
                        "WHERE employee_id=?",
                employee.getFirst_name(),
                employee.getLast_name(),
                employee.getStreet(),
                employee.getCity(),
                employee.getLane(),
                employee.getRole(),
                employee.getNic(),
                employee.getEmail(),
                employee.getEmployee_id()
        );
    }

    @Override
    public String getAllCount() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT COUNT(employee_id) FROM employee");
        String count = null;
        if (set.next()) {
            count = set.getString(1);
        }
        return count;
    }

    @Override
    public String getAllAdminCount() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT COUNT(employee_id) FROM employee WHERE role = ?", "Admin");
        String count = null;
        if (set.next()) {
            count = set.getString(1);
        }
        return count;
    }
}
