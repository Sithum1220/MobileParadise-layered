package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.ItemUpdateBO;
import lk.ijse.MobileShop.bo.custom.impl.ItemUpdateBOImpl;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.controller.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemUpdateFormController implements Initializable {
    private static String id;
    public JFXTextField txtItemName;
    public JFXTextField txtCatagory;
    public JFXTextField txtBrand;
    public JFXTextField txtDiscription;
    public JFXButton btnAdd;
    public JFXTextField txtWarrantyMonth;
    public JFXTextField txtSellingPrice;
    public JFXTextField txtQty;
    ItemUpdateBO itemUpdateBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.ITEM_UPDATE);

    public static void setId(String id) {
        ItemUpdateFormController.id = id;
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        System.out.println("mula");
        ItemFormController.getInstance().updateIcon.setVisible(false);
        ItemFormController.getInstance().detailsIcon.setVisible(false);
        ItemFormController.getInstance().addIcon.setVisible(true);
        ItemFormController.getInstance().closePane.setTranslateX(-1500);
        ItemFormController.getInstance().AddUpdatePane.setTranslateX(-1500);
        ItemFormController.getInstance().AddUpdatePane.getChildren().clear();
        ItemFormController.getInstance().btnAddtxt.setText("    New");

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItem_name(txtItemName.getText());
        itemDTO.setQty(Integer.parseInt(txtQty.getText()));
        itemDTO.setUnit_price(Double.parseDouble(txtSellingPrice.getText()));
        itemDTO.setDescription(txtDiscription.getText());
        itemDTO.setCategory(txtCatagory.getText());
        itemDTO.setWarranty_Month(txtWarrantyMonth.getText());
        itemDTO.setBrand(txtBrand.getText());
        itemDTO.setItem_code(id);
        System.out.println(id);
        if (itemUpdateBO.itemUpdate(itemDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
            ItemFormController.getInstance().loadAllIds();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }
    }

    private void setData() {
        try {
            ItemDTO itemDTO = itemUpdateBO.getItemData(id);
            txtItemName.setText(itemDTO.getItem_name());
            txtCatagory.setText(itemDTO.getCategory());
            txtDiscription.setText(itemDTO.getDescription());
            txtBrand.setText(itemDTO.getBrand());
            txtWarrantyMonth.setText(itemDTO.getWarranty_Month());
            txtQty.setText(String.valueOf(itemDTO.getQty()));
            txtSellingPrice.setText(String.valueOf(itemDTO.getUnit_price()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }

    public void txtItemNameKeyRelease(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtItemName, txtItemName.getText(), "^[a-zA-Z-'`]+[ a-zA-Z-'`]$", "-fx-text-fill: white");

    }

    public void txtCatagoryKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtCatagory, txtCatagory.getText(), "^[a-zA-Z-'`]+[ a-zA-Z-'`]$", "-fx-text-fill: white");

    }

    public void txtBrandKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtBrand, txtBrand.getText(), "^[a-zA-Z-'`]+[ a-zA-Z-'`]$", "-fx-text-fill: white");
    }

    public void txtDescriptionKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtDiscription, txtDiscription.getText(), "^[a-zA-Z-'`]+[ a-zA-Z-'`]$", "-fx-text-fill: white");
    }

    public void txtWarrentyKeyRelease(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtWarrantyMonth, txtWarrantyMonth.getText(), "^0*(\\d{1,9})$", "-fx-text-fill: white");
    }

    public void txtSellingPrice(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtSellingPrice, txtSellingPrice.getText(), "^(\\d+\\.\\d{1,2})$", "-fx-text-fill: white");

    }

    public void txtQtyKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtQty, txtQty.getText(), "^0*(\\d{1,9})$", "-fx-text-fill: white");

    }
}
