package lk.ijse.MobileShop.dto;

public class CustomEntityDTO {

    private String customer_order_id;
    private String customer_name;
    private String time;
    private String date;
    private String payment;
    private String itemId;
    private String itemName;
    private String itemQTY;
    private String itemPrice;
    private String itemSellingPrice;
    private String employee_name;
    private String employee_id;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQTY() {
        return itemQTY;
    }

    public void setItemQTY(String itemQTY) {
        this.itemQTY = itemQTY;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemSellingPrice() {
        return itemSellingPrice;
    }

    public void setItemSellingPrice(String itemSellingPrice) {
        this.itemSellingPrice = itemSellingPrice;
    }


    public CustomEntityDTO(String customer_order_id, String itemId, String itemName, String itemQTY, String itemPrice, String itemSellingPrice) {
        this.customer_order_id = customer_order_id;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQTY = itemQTY;
        this.itemPrice = itemPrice;
        this.itemSellingPrice = itemSellingPrice;
    }

    public CustomEntityDTO(String time, String date, String employee_name, String employee_id) {
        this.time = time;
        this.date = date;
        this.employee_name = employee_name;
        this.employee_id = employee_id;
    }

    public CustomEntityDTO(String customer_order_id, String customer_name, String time, String date, String payment) {
        this.customer_order_id = customer_order_id;
        this.customer_name = customer_name;
        this.time = time;
        this.date = date;
        this.payment = payment;
    }

    public CustomEntityDTO() {
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getCustomer_order_id() {
        return customer_order_id;
    }

    public void setCustomer_order_id(String customer_order_id) {
        this.customer_order_id = customer_order_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
