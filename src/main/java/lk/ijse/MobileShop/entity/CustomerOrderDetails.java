package lk.ijse.MobileShop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CustomerOrderDetails {
    private String item_id;
    private String customer_order_id;
    private int qty;
    private double price;
}
