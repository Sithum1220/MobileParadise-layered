package lk.ijse.MobileShop.dao.custom.impl;

import lk.ijse.MobileShop.dao.custom.UserDAO;
import lk.ijse.MobileShop.dao.custom.impl.utill.SQLUtill;
import lk.ijse.MobileShop.dto.UserDTO;
import lk.ijse.MobileShop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("insert into user VALUES (?,?,?,?)",
                user.getEmployee_id(),
                user.getUser_name(),
                user.getPassword(),
                user.getRole());

    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getSearchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM user where employee_id=?", id);

        User user = new User();
        while (set.next()) {
            user.setUser_name(set.getString(2));
            user.setPassword(set.getString(3));
        }
        return user;
    }

    @Override
    public String getAllCount() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM user WHERE employee_id=?", id);

    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE user SET " + "user_name=?," + "password=?," + "role=?" + "WHERE employee_id=?",
                user.getUser_name(),
                user.getPassword(),
                user.getRole(),
                user.getEmployee_id());

    }
}
