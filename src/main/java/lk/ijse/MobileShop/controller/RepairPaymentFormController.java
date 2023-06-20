package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.RepairPaymentBO;
import lk.ijse.MobileShop.bo.custom.impl.RepairPaymentBOImpl;
import lk.ijse.MobileShop.dto.CustomerOrderDTO;
import lk.ijse.MobileShop.dao.custom.impl.utill.DateTimeUtil;
import lk.ijse.MobileShop.controller.utill.RegexUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepairPaymentFormController {

    public JFXButton btnAdd;
    @FXML
    private JFXTextField txtrepairId;
    @FXML
    private JFXTextField txtPayment;
    @FXML
    private JFXTextField txtCustomerId;
    RepairPaymentBO repairPaymentBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.REPAIR_PAYMENT);

    @FXML
    void btnPayOnAction(ActionEvent event) {

        try {
            String status = repairPaymentBO.getRepairWarrentyStatus(txtrepairId.getText());
            if (status.equals("Warranty")) {
                new Alert(Alert.AlertType.WARNING, "Something Wong").show();
            } else {

                CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO();

                customerOrderDTO.setDate(DateTimeUtil.dateNow());
                customerOrderDTO.setTime(DateTimeUtil.timeNow());
                customerOrderDTO.setCustomer_id(txtCustomerId.getText());
                customerOrderDTO.setPayment(txtPayment.getText());
                customerOrderDTO.setRepair_id(txtrepairId.getText());
                customerOrderDTO.setCustomer_order_id(id());

                try {
                    if (repairPaymentBO.repairPayment(customerOrderDTO)) {

                        new Alert(Alert.AlertType.INFORMATION, "Ok").show();
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
            CustomerOrderFormController.getInstance().paymentPane.setTranslateX(-2000);
            CustomerOrderFormController.getInstance().closePaymentPane.setTranslateX(-2000);
            CustomerOrderFormController.getInstance().paymentPane.getChildren().clear();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private String id() {
        try {
            ArrayList<String> allId = repairPaymentBO.getAllCustomerOrderId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("O00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                System.out.println(idIndex);
                return "O00" + idIndex;
            } catch (Exception e) {
                return "O001";
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void repairOnAction(ActionEvent actionEvent) {

    }

    public void priceKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtPayment, txtPayment.getText(), "^(\\d+\\.\\d{1,2})$", "-fx-text-fill: white");

    }
}
