package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.ItemBarBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.ItemDAO;
import lk.ijse.MobileShop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.entity.Item;

import java.sql.SQLException;

public class ItemBarBOImpl implements ItemBarBO {
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public ItemDTO getItemData(String id) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.getData(id);
        return new ItemDTO(
                item.getItem_code(),
                item.getItem_name(),
                item.getCategory(),
                item.getUnit_price(),
                item.getQty(),
                item.getBrand(),
                item.getDescription(),
                String.valueOf(item.getWarranty_month())
        );
    }
}
