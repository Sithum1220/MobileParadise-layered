package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.SupplierOrderUpdateBO;
import lk.ijse.MobileShop.bo.custom.impl.SupplierOrderUpdateBOImpl;
import lk.ijse.MobileShop.dto.CustomEntityDTO;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.dto.SuplierOrderDetailsDTO;
import lk.ijse.MobileShop.dto.SupplierOrderDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierOrderUpdateFormController implements Initializable {

    private static String orderId;
    private static SupplierOrderUpdateFormController controller;
    public JFXComboBox cmbItemId;
    public JFXTextField txtPayment;
    public JFXTextField txtQty;
    public JFXTextField txtItemPrice;
    public JFXTextField txtSellingPrice;
    public JFXButton btnAdd;
    public JFXTextField txtReturnQty;
    public Text returnQtylbl;
    public Text buyingQtylbl;
    public JFXButton btnApply;
    public Text totalLbl;
    public Text txtTotal;
    SupplierOrderUpdateBO supplierOrderUpdateBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.SUPPLIER_ORDER_UPDATE);

    public SupplierOrderUpdateFormController() {
        controller = this;
    }

    public static SupplierOrderUpdateFormController getInstance() {
        return controller;
    }

    public static void setOrderId(String id) {
        SupplierOrderUpdateFormController.orderId = id;
    }

    public void cmbItemIdOnAction(ActionEvent actionEvent) {

    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        SupplierFormController.getInstance().AddUpdatePane.getChildren().clear();
        SupplierFormController.getInstance().AddUpdatePane.setTranslateX(-1000);
        SupplierFormController.getInstance().closePane.setTranslateX(-1100);
        SupplierFormController.getInstance().btnOrders.setText("     New Orders");
        SupplierFormController.getInstance().newOrderIcon.setVisible(true);
        SupplierFormController.getInstance().updateIcon.setVisible(false);

        CustomEntityDTO customEntityDTO = new CustomEntityDTO();
        customEntityDTO.setItemId(String.valueOf(cmbItemId.getValue()));
        customEntityDTO.setPayment(txtPayment.getText());
    }

    public void setCmbItemId() {
        try {
            ArrayList<String> list = supplierOrderUpdateBO.getAllItemId();
            cmbItemId.getItems().addAll(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCmbItemId();
        setData();
        txtTotal.setVisible(false);
        totalLbl.setVisible(false);
    }

    public void setData() {
        try {
            SuplierOrderDetailsDTO details = supplierOrderUpdateBO.getAllSupplierOrderDetailsData(orderId);
            SupplierOrderDTO supplierOrderDTO = supplierOrderUpdateBO.getAllSupplierOrderData(orderId);
            ItemDTO itemDTO = supplierOrderUpdateBO.getAllItemData(details.getItem_code());
            System.out.println(details.getItem_code());
            cmbItemId.setValue(details.getItem_code());
            txtQty.setText(details.getQuantity());
            txtItemPrice.setText(details.getUnit_price());
            txtPayment.setText(supplierOrderDTO.getPayment());
            txtSellingPrice.setText(String.valueOf(itemDTO.getUnit_price()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnApplyOnAction(ActionEvent actionEvent) {
        totalLbl.setVisible(true);
        txtTotal.setVisible(true);
        returnQtylbl.setVisible(false);
        buyingQtylbl.setVisible(true);
    }
}
