package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.LoginScene2BO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.QueryDAO;
import lk.ijse.MobileShop.dao.custom.impl.QueryDAOImpl;

import java.sql.SQLException;

public class LoginScene2BOImpl implements LoginScene2BO {
    QueryDAO queryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException {
        return queryDAO.checkUsernameAndPassword(userName,password);
    }
}
