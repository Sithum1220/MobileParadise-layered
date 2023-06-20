package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.ItemAddBO;
import lk.ijse.MobileShop.bo.custom.impl.ItemAddBOImpl;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.controller.utill.RegexUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemAddFormController {

    public JFXTextField txtWarrantyMonth;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXTextField txtItemName;
    @FXML
    private JFXTextField txtCatagory;
    @FXML
    private JFXTextField txtBrand;
    @FXML
    private JFXTextField txtDiscription;
    @FXML
    private JFXTextField txtPrice;
    @FXML
    private JFXTextField txtQty;

    ItemAddBO itemAddBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.ITEM_ADD);
    @FXML
    void AddOnAction(ActionEvent event) {
        ItemFormController.getInstance().AddUpdatePane.setTranslateX(-1500);
        ItemFormController.getInstance().AddUpdatePane.getChildren().clear();
        ItemFormController.getInstance().closePane.setTranslateX(-1500);
        try {
            if (itemAddBO.saveItem(new ItemDTO(
                    nextId(),
                    txtItemName.getText(),
                    txtCatagory.getText(),
                    0.0,
                    0,
                    txtBrand.getText(),
                    txtDiscription.getText(),
                    txtWarrantyMonth.getText()
            ))) {
                ItemFormController.getInstance().loadAllIds();
                new Alert(Alert.AlertType.CONFIRMATION, "Added ").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Wrong ").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId() {
        try {
            ArrayList<String> allId = itemAddBO.getAllItem();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
            }
            try {
                String[] s00s = lastId.split("I00");
                int idIndex = Integer.parseInt(s00s[1]);
                idIndex++;
                return "I00" + idIndex;
            } catch (Exception e) {
                return "I001";
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

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
}
