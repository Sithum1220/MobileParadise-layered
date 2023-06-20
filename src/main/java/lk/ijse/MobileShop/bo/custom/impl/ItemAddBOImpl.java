package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.ItemAddBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.ItemDAO;
import lk.ijse.MobileShop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemAddBOImpl implements ItemAddBO {
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(
                itemDTO.getItem_code(),
                itemDTO.getItem_name(),
                itemDTO.getCategory(),
                itemDTO.getUnit_price(),
                itemDTO.getQty(),
                itemDTO.getBrand(),
                itemDTO.getDescription(),
                Integer.parseInt(itemDTO.getWarranty_Month())
        ));
    }

    @Override
    public ArrayList<String> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllId();
    }
}
