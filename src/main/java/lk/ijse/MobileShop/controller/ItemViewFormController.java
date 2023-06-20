package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.ItemViewBO;
import lk.ijse.MobileShop.bo.custom.impl.ItemViewBOImpl;
import lk.ijse.MobileShop.dto.ItemDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemViewFormController implements Initializable {


    private static String id;
    public JFXButton btncancel;
    public Text txtName;
    public Text txtCategory;
    public Text txtQty;
    public Text txtPrice;
    public Text txtId;
    public Text txtBrand;
    public Text txtDescription;
    public Text txtDescription1;
    public JFXButton btnComform;
    public Text txtWrranty;
    private JFXBadge btnAddtxt;

    ItemViewBO itemViewBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.ITEM_VIEW);

    public static void setId(String id) {
        ItemViewFormController.id = id;
    }

    public void CloseOnAction(ActionEvent actionEvent) {
        ItemFormController.getInstance().updateIcon.setVisible(false);
        ItemFormController.getInstance().detailsIcon.setVisible(false);
        ItemFormController.getInstance().addIcon.setVisible(true);
        ItemFormController.getInstance().closePane.setTranslateX(-1500);
        ItemFormController.getInstance().AddUpdatePane.setTranslateX(-1500);
        ItemFormController.getInstance().AddUpdatePane.getChildren().clear();
        ItemFormController.getInstance().btnAddtxt.setText("    New");
    }

    public void setData() {
        try {
            ItemDTO itemDTO = itemViewBO.gataItemData(id);
            txtId.setText(itemDTO.getItem_code());
            txtName.setText(itemDTO.getItem_name());
            txtCategory.setText(itemDTO.getCategory());
            txtPrice.setText(String.valueOf(itemDTO.getUnit_price()));
            txtQty.setText(String.valueOf(itemDTO.getQty()));
            txtBrand.setText(itemDTO.getBrand());
            txtDescription.setText(itemDTO.getDescription());
            txtWrranty.setText(itemDTO.getWarranty_Month());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
