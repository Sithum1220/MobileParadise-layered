package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.ItemUpdateBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.ItemDAO;
import lk.ijse.MobileShop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.entity.Item;

import java.sql.SQLException;

public class ItemUpdateBOImpl implements ItemUpdateBO {
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    @Override
    public boolean itemUpdate(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(
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
