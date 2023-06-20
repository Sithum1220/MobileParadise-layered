package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.CustomerOrderBarBO;
import lk.ijse.MobileShop.bo.custom.impl.CustomerOrderBarBOImpl;
import lk.ijse.MobileShop.db.DBConnection;
import lk.ijse.MobileShop.dto.CustomEntityDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

public class CustomerOrderBarFormController {

    @FXML
    public Text txtOderId;

    @FXML
    public Text txtCustomer;

    @FXML
    public Text txtDate;

    @FXML
    public Text txtTime;

    @FXML
    public Text txtPrice;
    public JFXButton btnupdate;
    public JFXButton btnDelete;
    CustomerOrderBarBO customerOrderBarBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.CUSTOMER_ORDER_BAR);

    @FXML
    void ViewOnMouseClick(MouseEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("/Users/sithumimesh/Desktop/MobileShopNew/src/main/resources/asses/report/Supplier_Order.jrxml");
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(Arrays.asList(new Object()));

            HashMap hm = new HashMap();
            hm.put("id", txtOderId.getText());

            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, connection);
            JasperViewer.viewReport(jasperPrint, false);
//
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData(String id) {
        try {
            System.out.println("getDataView");
            CustomEntityDTO entityDTO = customerOrderBarBO.getCustomerOrderData(id);
            txtOderId.setText(entityDTO.getCustomer_order_id());
            txtDate.setText(entityDTO.getDate());
            txtPrice.setText(entityDTO.getPayment());
            txtTime.setText(entityDTO.getTime());
            txtCustomer.setText(entityDTO.getCustomer_name());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }
}
