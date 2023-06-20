package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.CustomerPlaceOrderBO;
import lk.ijse.MobileShop.bo.custom.impl.CustomerPlaceOrderBOImpl;
import lk.ijse.MobileShop.db.DBConnection;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.dto.CustomerOrderDetailsDTO;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.dto.CustomerOrderDTO;
import lk.ijse.MobileShop.dao.custom.impl.utill.DateTimeUtil;
import lk.ijse.MobileShop.controller.utill.RegexUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerPlaceOrderFormController implements Initializable {

    private static CustomerPlaceOrderFormController controller;
    public Text txtPrice;
    public JFXButton btnDone;
    public JFXButton btnAdd;
    public Text txtEmail;
    public Text txtCustomerName;
    public JFXTextField txtOderQty;
    public Text txtQty;
    public Text txtItemName;
    public JFXComboBox cmbCustomerId;
    public JFXComboBox cmbItemsIds;

    CustomerPlaceOrderBO customerPlaceOrderBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.CUSTOMER_PLACE_ORDER);
    ArrayList<CustomerOrderDetailsDTO> list = new ArrayList<>();
    private String id = null;

    public CustomerPlaceOrderFormController() {
        controller = this;
    }

    public static CustomerPlaceOrderFormController getInstance() {
        return controller;
    }

    private static void sendEmail(String orderId, String mail) {
        String to = mail;
        System.out.println("Process to Mail");
        String from = "mobileparadise.hikkaduwa@gmail.com";
        final String username = "mobileparadise.hikkaduwa";//change accordingly
        final String password = "tqmyflgojwiecbkz";//change accordingly

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("The Bill Of Your Order");

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<h1 style=\"color: deepskyblue;text-align: center;font-weight: bold\">MOBILE PARADISE</h1>\n" +
                    "<p style=\"text-align: center\">If you come to our company, you can repair mobile phones, mobile phone accessories and\n" +
                    "    mobile phones with a very high quality guarantee.</p>\n" +
                    "<p style=\"text-align: center\">The mobile phone accessories you buy from us will not be accepted back as we have checked them in your presence and we will not be responsible for any damage caused to the mobile phone and accessories by you.</p>\n" +
                    "<h1 style=\"font-size: 20px;text-align: center\">Thank you for shoping with us ! </h1>";
            messageBodyPart.setContent(htmlText, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);

           /* // second part (the image)
            messageBodyPart = new MimeBodyPart();
            System.out.println("1");
            DataSource fds = new FileDataSource(
                    "/Users/sithumimesh/Documents/CustomerBill/CustomerBill" +orderId+".pdf");

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<pdf>");*/

            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            String filename = "/Users/sithumimesh/Documents/CustomerBill/CustomerBill" + orderId + ".pdf";//change accordingly
            DataSource source = new FileDataSource(filename);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(filename);

            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(messageBodyPart2);

            // put everything together
            message.setContent(multipart);
            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printPdf(String orderId, String mail) {
        String fileNameJrxml = "/Users/sithumimesh/Documents/MobileShopNew/src/main/resources/asses/report/CustomeBill.jrxml";
        String fileNamePdf = "/Users/sithumimesh/Documents/CustomerBill/CustomerBill" + orderId + ".pdf";

        try {
            JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            String first_language = "Java";
            String second_language = "Structured text";
            HashMap hm = new HashMap();
            hm.put("id", orderId);

            JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperReport, hm, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jprint, fileNamePdf);
            System.out.println("Successfully completed the export");

            sendEmail(orderId, mail);

        } catch (Exception e) {
            System.out.println("fuck");
            System.out.print("Exception:" + e);
        }
    }

    public void btnDoneOnAction(ActionEvent event) {

        CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO(
                id,
                String.valueOf(cmbCustomerId.getValue()),
                DateTimeUtil.timeNow(),
                DateTimeUtil.dateNow(),
                total(),
                null
        );

        try {
            if (customerPlaceOrderBO.placeCustomerOrder(list, customerOrderDTO)) {
                back();
                CustomerOrderFormController.getInstance().loadAllIds();
                new Alert(Alert.AlertType.INFORMATION, "ok").show();

                Thread thread = new Thread(() -> {
                    try {
                        CustomerDTO customerDTO = customerPlaceOrderBO.getCustomerData(customerOrderDTO.getCustomer_id());
                        printPdf(customerOrderDTO.getCustomer_order_id(), customerDTO.getEmail());
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                });
                thread.start();
                CustomerOrderFormController.getInstance().setTodayOrderCount();
            } else {
                new Alert(Alert.AlertType.WARNING, "error").show();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        System.out.println("hu");
    }

    public void btnAddOnAction(ActionEvent event) throws IOException {

        if (list.isEmpty()) {
            CustomerOrderFormController.getInstance().tablePane.setTranslateX(-95);
            System.out.println("AAa");
            Parent root = FXMLLoader.load(getClass().getResource("/view/SupplierCustomerOrderTableForm.fxml"));
            Scene scene = btnAdd.getScene();
            root.translateYProperty().set(scene.getHeight());

            CustomerOrderFormController.getInstance().tablePane.getChildren().add(root);

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(t -> {
                CustomerOrderFormController.getInstance().tablePane.getChildren().remove(CustomerOrderFormController.getInstance().tablePane);
            });
            timeline.play();
        }


        CustomerOrderDetailsDTO details = new CustomerOrderDetailsDTO();
        boolean isNotDuplicate = false;
        if (list.isEmpty()) {
            isNotDuplicate = true;
        } else {
            isNotDuplicate = false;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getItem_id().equals(cmbItemsIds.getValue())) {
                System.out.println(Double.valueOf(txtPrice.getText()));
                System.out.println(Double.valueOf(txtQty.getText()));
                double updateTotal = Double.valueOf(txtPrice.getText()) * Double.valueOf(txtOderQty.getText());
                System.out.println(updateTotal);
                list.get(i).setPrice(String.valueOf(Double.valueOf(list.get(i).getPrice()) + updateTotal));
                list.get(i).setQty(String.valueOf(Double.valueOf(list.get(i).getQty()) + Double.valueOf(txtOderQty.getText())));
                clearText();
                isNotDuplicate = false;
            } else {
                isNotDuplicate = true;
            }
        }
        if (isNotDuplicate) {
            details.setCustomer_order_id(id);
            details.setPrice(String.valueOf(Double.valueOf(txtPrice.getText()) * Double.valueOf(txtOderQty.getText())));
            details.setQty(txtOderQty.getText());
            details.setItem_id(String.valueOf(cmbItemsIds.getValue()));
            list.add(details);
            clearText();
        }
        loadAllItems();
    }

    public void orderQtyOnKeyRelease(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtOderQty, txtOderQty.getText(), "^0*(\\d{1,9})$", "-fx-text-fill: white");
    }

    public void cmbCustomerOnAction(ActionEvent event) {
        try {
            CustomerDTO customerDTO = customerPlaceOrderBO.getCustomerData(String.valueOf(cmbCustomerId.getValue()));
            txtEmail.setText(customerDTO.getEmail());
            txtCustomerName.setText(customerDTO.getFirst_name() + " " + customerDTO.getLast_name());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbItemIdsOnAction(ActionEvent event) {
        try {
            ItemDTO itemDTO = customerPlaceOrderBO.getItemData(String.valueOf(cmbItemsIds.getValue()));
            txtItemName.setText(itemDTO.getItem_name());
            txtPrice.setText(String.valueOf(itemDTO.getUnit_price()));
            txtQty.setText(String.valueOf(itemDTO.getQty()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void backOnAction(ActionEvent event) {
        back();
    }

    public void back() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(CustomerOrderFormController.getInstance().orderPane);
        slide.setToX(-1000);
        slide.play();
        CustomerOrderFormController.getInstance().orderPane.setTranslateX(0);
        slide.setOnFinished((ActionEvent e) -> {
            CustomerOrderFormController.getInstance().orderPane.getChildren().clear();
        });

        TranslateTransition slide2 = new TranslateTransition();
        slide2.setDuration(Duration.seconds(0.4));
        slide2.setNode(CustomerOrderFormController.getInstance().tablePane);
        slide2.setToX(-1000);
        slide2.play();
        CustomerOrderFormController.getInstance().tablePane.setTranslateX(0);
        slide.setOnFinished((ActionEvent e) -> {
            CustomerOrderFormController.getInstance().tablePane.getChildren().clear();
        });
    }

    private String total() {
        double total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += Double.valueOf(list.get(i).getPrice());
        }
        return String.valueOf(total);
    }

    private void clearText() {
        txtPrice.setText("");
        txtQty.setText("");
        txtOderQty.setText("");
        txtItemName.setText("");
        cmbItemsIds.getItems().clear();
        setAllItemsIds();
    }

    private void loadAllItems() {
        SupplierCustomerOrderTableFormController.getInstance().vBox.getChildren().clear();
        for (int i = 0; i < list.size(); i++) {
            setList(
                    list.get(i).getItem_id(),
                    getItemName(list.get(i).getItem_id()),
                    list.get(i).getQty(),
                    list.get(i).getPrice()
            );
        }
    }

    private String getItemName(String itemId) {
        try {
            ItemDTO itemDTO = customerPlaceOrderBO.getItemData(itemId);
            return itemDTO.getItem_name();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setList(String id, String name, String qty, String price) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerFormController.class.getResource("/view/CustomerOrderCartBarForm.fxml"));
            Parent root = loader.load();
            CustomerOrderCartBarFormController controller = loader.getController();
            controller.setData(id, name, qty, price);
            SupplierCustomerOrderTableFormController.getInstance().vBox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAllCustomerIds();
        setAllItemsIds();
        id = id();
    }

    private void setAllItemsIds() {
        try {
            ArrayList<String> ids = customerPlaceOrderBO.getAllItemId();
            cmbItemsIds.getItems().setAll(ids);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setAllCustomerIds() {
        try {
            ArrayList<String> ids = customerPlaceOrderBO.getAllCustomerId();
            cmbCustomerId.getItems().setAll(ids);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String id() {
        try {
            ArrayList<String> allId = customerPlaceOrderBO.getAllCustomerOrderId();
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

    public void remove(String id) {
        SupplierCustomerOrderTableFormController.getInstance().vBox.getChildren().clear();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getItem_id().equals(id)) {
                list.remove(i);
            }
        }
        loadAllItems();
    }
}
