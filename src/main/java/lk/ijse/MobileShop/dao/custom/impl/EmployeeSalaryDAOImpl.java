package lk.ijse.MobileShop.dao.custom.impl;

import lk.ijse.MobileShop.dao.custom.EmployeeSalaryDAO;
import lk.ijse.MobileShop.dao.custom.impl.utill.SQLUtill;
import lk.ijse.MobileShop.entity.Salary;
import lk.ijse.MobileShop.dao.custom.impl.utill.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeSalaryDAOImpl implements EmployeeSalaryDAO {
    @Override
    public boolean save(Salary salary) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO salary VALUES (?,?,?,?,?,?)",
                salary.getEmployee_id(),
                salary.getSalary_id(),
                salary.getSalary(),
                salary.getMonthly_Attendance_count(),
                salary.getDate(),
                salary.getTime()
        );
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();
        ResultSet set = SQLUtill.execute("SELECT salary_id FROM salary ORDER BY LENGTH(salary_id),salary_id;");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids;
    }

    @Override
    public ArrayList<String> getSearchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Salary getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM salary where salary_id=?", id);
        Salary salary = new Salary();
        while (set.next()) {
            salary.setSalary_id(set.getString(2));
            salary.setEmployee_id(set.getString(1));
            salary.setSalary(Double.parseDouble(set.getString(3)));
            salary.setMonthly_Attendance_count(set.getString(4));
            salary.setDate(set.getString(5));
            salary.setTime(set.getString(6));
        }
        return salary;
    }

    @Override
    public String getAllCount() throws SQLException, ClassNotFoundException {
        String dateToDay = DateTimeUtil.dateNow();
        String[] d = dateToDay.split("-");
        ResultSet set= SQLUtill.execute("SELECT COUNT(salary_id) FROM salary WHERE date LIKE ?",d[0]+"-"+d[1]+"-%");
        while (set.next()){
            return set.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Salary salary) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> getAllEmpId() throws SQLException, ClassNotFoundException {

        ArrayList<String>ids=new ArrayList<>();
        String date=DateTimeUtil.dateNow();
        String[] arDate = date.split("-");
        ResultSet set= SQLUtill.execute("SELECT employee_id FROM salary WHERE date LIKE ?",arDate[0]+"-"+arDate[1]+"%");
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids;
    }
}
