package lk.ijse.MobileShop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Supplier {
    private String supplier_id;
    private String company_name;
    private String contact_number;
    private String email;
    private String location;
}
