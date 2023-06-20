package lk.ijse.MobileShop.dao.custom.impl;

import lk.ijse.MobileShop.dao.custom.EmployeeAttendaceDAO;
import lk.ijse.MobileShop.dao.custom.impl.utill.SQLUtill;
import lk.ijse.MobileShop.entity.EmployeeAttendance;
import lk.ijse.MobileShop.dao.custom.impl.utill.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeAttendanceDAOImpl implements EmployeeAttendaceDAO {

    @Override
    public boolean save(EmployeeAttendance employeeAttendance) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO employee_attendance VALUES (?,?,?)",
                employeeAttendance.getEmployee_id(),
                DateTimeUtil.timeNow(),
                DateTimeUtil.dateNow()
        );
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT employee_id FROM employee_attendance WHERE date=?", DateTimeUtil.dateNow());
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getSearchId(String id) throws SQLException, ClassNotFoundException {
        String SearchId = id + "%";
        ResultSet set = SQLUtill.execute("SELECT employee_id FROM employee_attendance where employee_id LIKE ? AND date = ? ", SearchId, DateTimeUtil.dateNow());
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public EmployeeAttendance getData(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getAllCount() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }

    @Override
    public boolean update(EmployeeAttendance employeeAttendance) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }

    @Override
    public boolean isEmployeeExsist(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM employee WHERE employee_id=?", id);
        if (set.next()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmployeeTodayExsist(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM employee_attendance WHERE employee_id=? and date=?", id, DateTimeUtil.dateNow());
        if (set.next()) {
            return false;
        }
        return true;
    }

    @Override
    public String getAttendanceCountThisMonth(String employee_id) throws SQLException, ClassNotFoundException {
        String dateNow = DateTimeUtil.dateNow();
        String[] date = dateNow.split("-");
        ResultSet set = SQLUtill.execute("SELECT COUNT(employee_id) FROM employee_attendance WHERE employee_id=? and date LIKE ?", employee_id, date[0] + "-" + date[1] + "-%");

        while (set.next()) {
            System.out.println(set.getString(1));
            return set.getString(1);
        }
        return "0";
    }

    @Override
    public String getAllTodayAttendanceCount() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT COUNT(employee_id) FROM employee_attendance WHERE date=?", DateTimeUtil.dateNow());
        if (set.next()) {
            return set.getString(1);
        }
        return null;
    }
}
