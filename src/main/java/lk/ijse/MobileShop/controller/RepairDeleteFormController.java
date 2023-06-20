package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.RepairDeleteBO;
import lk.ijse.MobileShop.bo.custom.impl.RepairDeleteBOImpl;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.dto.RepairDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RepairDeleteFormController implements Initializable {
    private static String id;
    private static JFXButton delete;
    public JFXTextField txtItemId;
    public JFXTextField txtOrderId;
    public Text txtRepairId;
    public Text txtName;
    public Text txtSteet;
    public Text txtModelNum;
    public Text txtImeiNum;
    public Text txtStatus;
    public Text txtreturnDate;
    public Text txtContactNumber;
    public Text txtErrorType;
    public JFXButton btnDelete;
    public Text txtCustomerId;
    RepairDeleteBO repairDeleteBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.REPAIR_DELETE);

    public static void setDeleteBtn(JFXButton btnDelete) {
        RepairDeleteFormController.delete = btnDelete;
    }

    public static void setId(String id) {
        RepairDeleteFormController.id = id;
    }

    public void CloseOnAction(ActionEvent event) {
        CustomerOrderFormController.getInstance().AddUpdatePane.setTranslateX(-2000);
        CustomerOrderFormController.getInstance().AddUpdatePane.getChildren().clear();
        CustomerOrderFormController.getInstance().closePane.setTranslateX(-2000);
        CustomerOrderFormController.getInstance().addIcon.setVisible(true);
        CustomerOrderFormController.getInstance().detailsIcon.setVisible(false);
        CustomerOrderFormController.getInstance().deleteIcon.setVisible(false);
        CustomerOrderFormController.getInstance().btnNew.setText("New Repair");
    }

    public void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println(id);
        if (!txtreturnDate.getText().equals("")) {
            new Alert(Alert.AlertType.ERROR, "This Repair is cannot delete").show();
        } else {
            if (repairDeleteBO.deleteRepair(id)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Done").show();
                CustomerOrderFormController.getInstance().loadTablaData();

            } else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        }
        CustomerOrderFormController.getInstance().AddUpdatePane.setTranslateX(-2000);
        CustomerOrderFormController.getInstance().AddUpdatePane.getChildren().clear();
        CustomerOrderFormController.getInstance().closePane.setTranslateX(-2000);
        CustomerOrderFormController.getInstance().addIcon.setVisible(true);
        CustomerOrderFormController.getInstance().detailsIcon.setVisible(false);
        CustomerOrderFormController.getInstance().deleteIcon.setVisible(false);
        CustomerOrderFormController.getInstance().btnNew.setText("New Repair");
    }

    private void setData() {
        try {
            RepairDTO repairDTO = repairDeleteBO.getAllRepairData(id);
            CustomerDTO customerDTO = repairDeleteBO.getCustomerData(repairDTO.getCustomer_id());
            txtName.setText(customerDTO.getFirst_name() + " " + customerDTO.getLast_name());
            txtContactNumber.setText(customerDTO.getContact_number());

            txtRepairId.setText(repairDTO.getRepair_id());
            txtErrorType.setText(repairDTO.getError_type());
            txtreturnDate.setText(repairDTO.getReturn_give_date());
            txtStatus.setText(repairDTO.getStatus());
            txtCustomerId.setText(repairDTO.getCustomer_id());
            txtImeiNum.setText(repairDTO.getImei_number());
            txtModelNum.setText(repairDTO.getModel_number());


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
