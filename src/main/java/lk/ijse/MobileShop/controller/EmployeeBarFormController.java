package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.EmployeeBarBO;
import lk.ijse.MobileShop.bo.custom.impl.EmployeeBarBOImpl;
import lk.ijse.MobileShop.dto.EmployeeDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeBarFormController implements Initializable {
    private static EmployeeBarFormController controller;
    public Text txtId;
    public Text txtName;
    public Text txtRole;
    public Text txtCity;

    public JFXButton btnDelete;
    public JFXButton btnupdate;

    EmployeeBarBO employeeBarBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.EMPLOYEE_BAR);

    public EmployeeBarFormController() {
        controller = this;
    }

    public static EmployeeBarFormController getInstance() {
        return controller;
    }

    public void setData(String id) {
        try {
            EmployeeDTO employeeDTO = employeeBarBO.getEmployeeData(id);
            txtId.setText(employeeDTO.getEmployee_id());
            txtName.setText(employeeDTO.getFirst_name() + " " + employeeDTO.getLast_name());
            txtRole.setText(employeeDTO.getRole());
            txtCity.setText(employeeDTO.getCity());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void mousEntered(MouseEvent mouseEvent) {
        btnDelete.setVisible(true);
        btnupdate.setVisible(true);
    }

    public void mouseExited(MouseEvent mouseEvent) {
        btnDelete.setVisible(false);
        btnupdate.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnDelete.setVisible(false);
        btnupdate.setVisible(false);
    }

    public void btnDeleteOnAction(ActionEvent event) throws IOException {
        EmployeeDeleteFormController.setId(txtId.getText());
        EmployeeFormController.getInstance().deleteIcon.setVisible(true);
        EmployeeFormController.getInstance().newIcon.setVisible(false);
        EmployeeFormController.getInstance().optionLbl.setText("Delete");
        EmployeeFormController.getInstance().tempPane.setTranslateX(-55);
        EmployeeFormController.getInstance().closePane.setTranslateX(0);
        FXMLLoader loader = new FXMLLoader(EmployeeFormController.class.getResource("/view/EmployeeDeleteForm.fxml"));
        Parent root = loader.load();
        EmployeeFormController.getInstance().tempPane.getChildren().add(root);
    }

    public void updateOnAction(ActionEvent event) throws IOException {
        EmployeeUpdateFormController.setId(txtId.getText());
        EmployeeFormController.getInstance().deleteIcon.setVisible(false);
        EmployeeFormController.getInstance().newIcon.setVisible(false);
        EmployeeFormController.getInstance().updateIcon.setVisible(true);
        EmployeeFormController.getInstance().optionLbl.setText("Update");
        EmployeeFormController.getInstance().tempPane.setTranslateX(-55);
        EmployeeFormController.getInstance().closePane.setTranslateX(0);
        FXMLLoader loader = new FXMLLoader(EmployeeFormController.class.getResource("/view/EmployeeUpdateForm.fxml"));
        Parent root = loader.load();
        EmployeeFormController.getInstance().tempPane.getChildren().add(root);

    }

    public void ViewOnMouseClick(MouseEvent mouseEvent) throws IOException {
    }

    public void btnViewOnAction(ActionEvent actionEvent) throws IOException {
        EmployeeViewFormController.setId(txtId.getText());

        EmployeeFormController.getInstance().deleteIcon.setVisible(false);
        EmployeeFormController.getInstance().newIcon.setVisible(false);
        EmployeeFormController.getInstance().detailsIcon.setVisible(true);
        EmployeeFormController.getInstance().optionLbl.setText("Details");
        EmployeeFormController.getInstance().tempPane.setTranslateX(-55);
        EmployeeFormController.getInstance().closePane.setTranslateX(0);
        FXMLLoader loader = new FXMLLoader(EmployeeFormController.class.getResource("/view/EmployeeViewForm.fxml"));
        Parent root = loader.load();
        EmployeeFormController.getInstance().tempPane.getChildren().add(root);
    }
}
