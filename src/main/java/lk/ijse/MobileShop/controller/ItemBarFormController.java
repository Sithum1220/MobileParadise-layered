package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.ItemBarBO;
import lk.ijse.MobileShop.bo.custom.impl.ItemBarBOImpl;
import lk.ijse.MobileShop.dto.ItemDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemBarFormController implements Initializable {

    ItemBarBO itemBarBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.ITEM_BAR);
    @FXML
    private JFXButton btnupdate;
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

    public void setData(String id) {
        System.out.println(id);
        try {
            ItemDTO itemDTO = itemBarBO.getItemData(id);
            txtItemCode.setText(itemDTO.getItem_code());
            txtName.setText(itemDTO.getItem_name());
            txtCatagory.setText(itemDTO.getCategory());
            txtPrice.setText(String.valueOf(itemDTO.getUnit_price()));
            txtQty.setText(String.valueOf(itemDTO.getQty()));


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void ViewOnMouseClick(MouseEvent event) throws IOException {
        ItemViewFormController.setId(txtItemCode.getText());
        ItemFormController.getInstance().detailsIcon.setVisible(true);
        ItemFormController.getInstance().addIcon.setVisible(false);
        ItemFormController.getInstance().AddUpdatePane.setTranslateX(-5);
        ItemFormController.getInstance().closePane.setTranslateX(-10);
        FXMLLoader loader = new FXMLLoader(ItemViewFormController.class.getResource("/view/ItemViewForm.fxml"));
        Parent root = loader.load();
        ItemFormController.getInstance().AddUpdatePane.getChildren().add(root);
        ItemFormController.getInstance().btnAddtxt.setText(" Details");

    }


    @FXML
    void mousEntered(MouseEvent event) {

    }

    @FXML
    void mouseExited(MouseEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) throws IOException {
        ItemUpdateFormController.setId(txtItemCode.getText());
        ItemFormController.getInstance().updateIcon.setVisible(true);
        ItemFormController.getInstance().addIcon.setVisible(false);
        ItemFormController.getInstance().AddUpdatePane.setTranslateX(-5);
        ItemFormController.getInstance().closePane.setTranslateX(-10);
        FXMLLoader loader = new FXMLLoader(ItemViewFormController.class.getResource("/view/ItemUpdateForm.fxml"));
        Parent root = loader.load();
        ItemFormController.getInstance().AddUpdatePane.getChildren().add(root);
        ItemFormController.getInstance().btnAddtxt.setText("Update");
    }

    public void mouseKeyEntered(MouseEvent mouseEvent) {
        btnupdate.setVisible(true);
    }

    public void mouseKeyExited(MouseEvent mouseEvent) {
        btnupdate.setVisible(false);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnupdate.setVisible(false);
    }
}
