package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.SupplierUpdateBO;
import lk.ijse.MobileShop.bo.custom.impl.SupplierUpdateBOImpl;
import lk.ijse.MobileShop.dto.SupplierDTO;
import lk.ijse.MobileShop.controller.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierUpdateFormController implements Initializable {

    public static String id;
    public JFXButton btnUpdate;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXTextField txtCompanyName;
    @FXML
    private JFXTextField txtContactNumber;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtLocation;
    SupplierUpdateBO supplierUpdateBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.SUPPLIER_UPDATE);

    public static void setId(String id) {
        SupplierUpdateFormController.id = id;
    }

    @FXML
    void UpdateOnAction(ActionEvent event) {
        try {
            if (supplierUpdateBO.updateSupplier(new SupplierDTO(
                    id,
                    txtCompanyName.getText(),
                    txtContactNumber.getText(),
                    txtEmail.getText(),
                    txtLocation.getText()
            ))) {
                SupplierFormController.getInstance().loadAllIds();
                new Alert(Alert.AlertType.CONFIRMATION, "Updated ").show();
                SupplierFormController.getInstance().closePane.setTranslateX(-1100);
                SupplierFormController.getInstance().AddUpdatePane.setTranslateX(-1500);
                SupplierFormController.getInstance().AddUpdatePane.getChildren().clear();
                SupplierFormController.getInstance().newSupplier.setText("       New Supplier");
                SupplierFormController.getInstance().addIcon.setVisible(true);
                SupplierFormController.getInstance().updateIcon.setVisible(false);
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Wrong ").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadItem();
        System.out.println(id);
    }

    public void loadItem() {
        try {
            SupplierDTO supplierDTO = supplierUpdateBO.getAllSupplierData(id);
            txtCompanyName.setText(supplierDTO.getCompany_name());
            txtEmail.setText(supplierDTO.getEmail());
            txtLocation.setText(supplierDTO.getLocation());
            txtContactNumber.setText(supplierDTO.getContact_number());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtCompanyNameKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnUpdate, txtCompanyName, txtCompanyName.getText(), "^[a-zA-Z-'`]+[ a-zA-Z-'`]$", "-fx-text-fill: white");

    }

    public void txtContactKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnUpdate, txtContactNumber, txtContactNumber.getText(), "^(?:0|94|\\+94)?(?:7(0|1|2|4|5|6|7|8)\\d)\\d{6}$", "-fx-text-fill: white");

    }

    public void txtEmailKeyRelease(KeyEvent keyEvent) {
        RegexUtil.regex(btnUpdate, txtEmail, txtEmail.getText(), "^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}$", "-fx-text-fill: white");

    }

    public void txtLocationKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnUpdate, txtLocation, txtLocation.getText(), "^[a-zA-Z-'`]+[ a-zA-Z-'`]$", "-fx-text-fill: white");

    }
}
