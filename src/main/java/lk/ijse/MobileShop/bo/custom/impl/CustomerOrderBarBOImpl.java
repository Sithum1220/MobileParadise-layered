package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.CustomerOrderBarBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.QueryDAO;
import lk.ijse.MobileShop.dao.custom.impl.QueryDAOImpl;
import lk.ijse.MobileShop.dto.CustomEntityDTO;

import java.sql.SQLException;

public class CustomerOrderBarBOImpl implements CustomerOrderBarBO {
    QueryDAO queryDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public CustomEntityDTO getCustomerOrderData(String id) throws SQLException, ClassNotFoundException {
        return queryDAO.getCustomerOrderData(id);
    }
}
