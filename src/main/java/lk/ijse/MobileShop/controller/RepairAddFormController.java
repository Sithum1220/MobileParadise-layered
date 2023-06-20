package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.RepairAddBO;
import lk.ijse.MobileShop.bo.custom.impl.RepairAddBOImpl;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.dto.CustomerOrderDTO;
import lk.ijse.MobileShop.dto.RepairDTO;
import lk.ijse.MobileShop.dao.custom.impl.utill.DateTimeUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RepairAddFormController implements Initializable {
    RepairAddBO repairAddBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.REPAIR_ADD);
    @FXML
    private JFXTextField txtModelNumber;
    @FXML
    private JFXTextField txtImei;
    @FXML
    private JFXTextField txtError;
    @FXML
    private JFXComboBox cmbCustomerId;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXComboBox cmbStatus;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtOrderId;
    @FXML
    private JFXTextField txtItemId;

    @FXML
    void addOnAction(ActionEvent event) {
        Thread thread = new Thread(() -> {
            CustomerOrderFormController.getInstance().AddUpdatePane.setTranslateX(-2000);
            CustomerOrderFormController.getInstance().closePane.setTranslateX(-2000);
        });
        thread.start();

        if (cmbStatus.getValue().equals("Warranty")) {
            try {

                ItemDTO itemDTO = repairAddBO.getAllItemData(txtItemId.getText());

                if (itemDTO.getItem_code() != null) {
                    try {
                        System.out.println(itemDTO.getWarranty_Month());
                        int countOfMonth = Integer.parseInt(itemDTO.getWarranty_Month());
                        System.out.println("getData");
                        CustomerOrderDTO customerOrderDTO = repairAddBO.getOrderDate(txtOrderId.getText());

                        System.out.println(customerOrderDTO);
                        String date = customerOrderDTO.getDate();

                        System.out.println(date);
                        String[] split = date.split("-");

                        int month = Integer.parseInt(split[1]);
                        int year = Integer.parseInt(split[0]);

                        for (int i = 0; i < countOfMonth; i++) {
                            if (month <= 12) {
                                month++;
                                if (month == 13) {
                                    month = 01;
                                    year++;
                                }
                            } else {
                                year++;
                                month = 01;
                            }
                        }
                        String dateNow = DateTimeUtil.dateNow();
                        String[] dateSplit = dateNow.split("-");

                        if (Integer.parseInt(dateSplit[0]) <= year & Integer.parseInt(dateSplit[1]) <= month) {

                            if (Integer.parseInt(dateSplit[0]) == year & Integer.parseInt(dateSplit[1]) == month) {

                                if (Integer.parseInt(split[2]) < Integer.parseInt(dateSplit[2])) {
                                    add(event);
                                    System.out.println("year == month == but Date Is < OK");
                                } else {
                                    error(event);
                                    System.out.println("year != month != but Date Is !< error");
                                }
                            } else {
                                add(event);
                                System.out.println("year > month > OK");
                            }
                        } else {
                            error(event);
                            System.out.println("error");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                } else {
                    System.out.println("Can't Find This Item Code, Please Check Your Warranty Card ");
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Can't Find This Item Code, Please Check Your Warranty Card ");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.setAlwaysOnTop(true);
                    stage.toFront();
                    alert.show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                add(event);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void add(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("Ok");
        RepairDTO repairDTO = new RepairDTO();
        repairDTO.setRepair_id(id());
        repairDTO.setStatus((String) cmbStatus.getValue());
        repairDTO.setCustomer_id((String) cmbCustomerId.getValue());
        repairDTO.setError_type(txtError.getText());
        repairDTO.setImei_number(txtImei.getText());
        repairDTO.setGiven_date(DateTimeUtil.dateNow());
        repairDTO.setModel_number(txtModelNumber.getText());
        repairDTO.setReturn_give_date(null);

        if (repairAddBO.saveRepair(repairDTO)) {
            new Alert(Alert.AlertType.INFORMATION, "Ok").show();
            CustomerOrderFormController.getInstance().loadTablaData();
        }
    }

    private void error(ActionEvent event) {
        System.out.println("Warranty is Out OF Date");
        new Alert(Alert.AlertType.ERROR, "Warranty is Out OF Date").show();
    }

    private String id() {
        try {
            ArrayList<String> allId = repairAddBO.getAllRepairId();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i);
                System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("R00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                System.out.println(idIndex);
                return "R00" + idIndex;
            } catch (Exception e) {
                return "R001";
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        try {
            CustomerDTO customerDTO = repairAddBO.getCustomerData((String) cmbCustomerId.getValue());
            txtCustomerName.setText(customerDTO.getFirst_name() + " " + customerDTO.getLast_name());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbStatusOnAction(ActionEvent event) {
        if (cmbStatus.getValue().equals("Warranty")) {
            txtItemId.setVisible(true);
            txtOrderId.setVisible(true);
        } else {
            txtItemId.setVisible(false);
            txtOrderId.setVisible(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCustomerIds();
        loadStatus();
    }

    private void loadStatus() {
        ArrayList<String> status = new ArrayList<>();
        status.add("Warranty");
        status.add("Repair");
        cmbStatus.getItems().addAll(status);
    }

    private void loadCustomerIds() {
        try {
            ArrayList<String> allId = repairAddBO.getAllCustomerId();
            cmbCustomerId.getItems().addAll(allId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
