package lk.ijse.MobileShop.dao.custom.impl;
import lk.ijse.MobileShop.dao.custom.QueryDAO;
import lk.ijse.MobileShop.dao.custom.impl.utill.SQLUtill;
import lk.ijse.MobileShop.dto.CustomEntityDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public CustomEntityDTO getCustomerOrderData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT o.customer_order_id,c.first_name,c.last_name,o.time,o.date,o.payment FROM customer_order o join customer c on c.customer_id = o.customer_id where customer_order_id=?", id);
        CustomEntityDTO entityDTO = new CustomEntityDTO();
        if (set.next()) {
            entityDTO.setCustomer_order_id(set.getString(1));
            entityDTO.setCustomer_name(set.getString(2) + " " + set.getString(3));
            entityDTO.setTime(set.getString(4));
            entityDTO.setDate(set.getString(5));
            entityDTO.setPayment(set.getString(6));
        }
        return entityDTO;
    }

    @Override
    public ArrayList<String> searchCustomerOrdersData(String id) throws SQLException, ClassNotFoundException {
        String SearchId = id + "%";
        ResultSet set = SQLUtill.execute("SELECT o.customer_order_id FROM customer_order o join customer c on c.customer_id = o.customer_id where customer_order_id LIKE ? or c.first_name LIKE ? or o.customer_id LIKE ? ", SearchId, SearchId, SearchId);
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public CustomEntityDTO getEmployeeAttendanceData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT e.first_name,e.last_name , a.employee_id ,a.time,a.date FROM employee_attendance a INNER JOIN  mobileparadise.employee e on a.employee_id = e.employee_id WHERE a.employee_id=?",id);
        CustomEntityDTO attendance = null;
        if (set.next()){
            attendance= new CustomEntityDTO(
                    set.getString(3),
                    set.getString(1) + " " + set.getString(2),
                    set.getString(4),
                    set.getString(5)

            );
        }

        return attendance;
    }

    @Override
    public String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT e.role FROM user u INNER JOIN employee e on u.employee_id = e.employee_id WHERE user_name=? AND password=?", userName, password);

        if (set.next()) {
            return set.getString(1);
        } else {
            return "No";
        }
    }
}
