package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.EmployeeAttendanceBO;
import lk.ijse.MobileShop.bo.custom.impl.EmployeeAttendanceBOImpl;
import lk.ijse.MobileShop.dto.EmployeeAttendanceDTO;
import lk.ijse.MobileShop.controller.utill.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeAttendanceFormController implements Initializable {
    private static EmployeeAttendanceFormController employeeAttendance;

    public JFXTextField txtSearch;
    @FXML
    private Text txtallEmployee;
    @FXML
    private VBox vBox;
    @FXML
    private JFXTextField txtId;
    EmployeeAttendanceBO employeeAttendanceBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.EMPLOYEE_ATTENDANCE);
    public EmployeeAttendanceFormController() {
        employeeAttendance = this;
    }

    public static EmployeeAttendanceFormController getInstance() {
        return employeeAttendance;
    }

    @FXML
    void backOnAction(ActionEvent event) {
        try {
            Navigation.switchNavigation("CashierDashBordForm.fxml", event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void idOnKeyReleased(KeyEvent event) {

        System.out.println(txtId.getText());
        try {
            if (employeeAttendanceBO.employeeExist(txtId.getText())) {
                if (employeeAttendanceBO.employeeTodayExist(txtId.getText())) {
                    if (employeeAttendanceBO.saveEmployeeAttendance(new EmployeeAttendanceDTO(txtId.getText()))) {
                        txtId.setText("");
                        EmployeeAttendanceFormController.getInstance().loadAllIds();
                        setCount();
                        new Alert(Alert.AlertType.CONFIRMATION, "Added").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something Wrong").show();
                    }
                } else {
                    txtId.setText("");
                    new Alert(Alert.AlertType.ERROR, "Already Added").show();
                }

            }

            System.out.println(txtId.getText());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchOnKeyReleased(KeyEvent event) {
        vBox.getChildren().clear();
        ArrayList<String> ids = null;
        try {
            ids = employeeAttendanceBO.getSearchId(txtSearch.getText());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < ids.size(); i++) {
            loadAllEmployee(ids.get(i));
        }
    }

    public void loadAllIds() {
        vBox.getChildren().clear();
        try {
            ArrayList<String> id = employeeAttendanceBO.getAllAttendanceEmployeeId();
            for (int i = 0; i < id.size(); i++) {
                loadAllEmployee(id.get(i));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllEmployee(String id) {

        try {
            FXMLLoader loader = new FXMLLoader(EmployeeAttendanceFormController.class.getResource("/view/EmployeeAttendanceBarFrom.fxml"));
            Parent root = loader.load();
            EmployeeAttendanceBarController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllIds();
        setCount();
    }

    public void closeOnMouseClick(MouseEvent mouseEvent) {

    }

    public void setCount() {
        try {
            String count = employeeAttendanceBO.getAllTodayEmployeeAttendanceCount();
            txtallEmployee.setText(count);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
