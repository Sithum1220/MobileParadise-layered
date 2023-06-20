package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.ItemCashierViewBarBO;
import lk.ijse.MobileShop.bo.custom.impl.ItemCashierViewBarBOImpl;
import lk.ijse.MobileShop.dto.ItemDTO;

import java.sql.SQLException;

public class ItemCashierViewBarFormController {
    public Text txtBrand;
    public Text txtWarranty;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private Text txtItemCode;
    @FXML
    private Text txtName;
    @FXML
    private Text txtQty;
    @FXML
    private Text txtCatagory;
    @FXML
    private Text txtPrice;

    ItemCashierViewBarBO itemCashierViewBarBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.ITEM_CASHIER_VIEW_BAR);
    public void setData(String id) {
        System.out.println(id);
        try {
            ItemDTO itemDTO = itemCashierViewBarBO.getAllItemData(id);
            txtItemCode.setText(itemDTO.getItem_code());
            txtName.setText(itemDTO.getItem_name());
            txtCatagory.setText(itemDTO.getCategory());
            txtPrice.setText(String.valueOf(itemDTO.getUnit_price()));
            txtQty.setText(String.valueOf(itemDTO.getQty()));
            txtBrand.setText(itemDTO.getBrand());
            txtWarranty.setText(itemDTO.getWarranty_Month());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void ViewOnMouseClick(MouseEvent mouseEvent) {

    }
}
