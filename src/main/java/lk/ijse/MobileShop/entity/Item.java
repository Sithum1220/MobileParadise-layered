package lk.ijse.MobileShop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Item {
    private String item_code;
    private String item_name;
    private String category;
    private double unit_price;
    private int qty;
    private String brand;
    private String description;
    private int Warranty_month;

}
