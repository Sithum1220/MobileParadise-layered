package lk.ijse.MobileShop.bo;

import lk.ijse.MobileShop.bo.custom.AdminDashBoardBO;
import lk.ijse.MobileShop.bo.custom.impl.*;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.SuperDAO;
import lk.ijse.MobileShop.dao.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType{
        ADMIN_DASHBOARD,CASHIER_DASHBOARD,CUSTOMER_ADD,CUSTOMER_BAR,CUSTOMER,CUSTOMER_DELETE,CUSTOMER_ORDER_BAR,
        CUSTOMER_ORDER,CUSTOMER_PLACE_ORDER,CUSTOMER_UPDATE,CUSTOMER_VIEW,EMPLOYEE_ADD,EMPLOYEE_ATTENDANCE_BAR,
        EMPLOYEE_ATTENDANCE,EMPLOYEE_BAR,EMPLOYEE,EMPLOYEE_DELETE,EMPLOYEE_UPDATE,EMPLOYEE_VIEW,ITEM_ADD,ITEM_BAR,ITEM,
        ITEM_CASHIER_VIEW_BAR,ITEM_CASHIER_VIEW,ITEM_UPDATE,ITEM_VIEW,LOGIN_SCEAN2,REPAIR_ADD,REPAIR_BAR,REPAIR_DELETE,
        REPAIR_PAYMENT,REPAIR_VIEW,REPORT,REPORT_VIEW,SALARY_BAR,SALARY,SUPPLIER_ADD,SUPPLIER_ALL_ORDER_BAR,SUPPLIER_BAR,
        SUPPLIER,SUPPLIER_DELETE,SUPPLIER_ORDER,SUPPLIER_ORDER_UPDATE,SUPPLIER_UPDATE
    }

    public <T extends SuperBO>T getBO(BOFactory.BOType boType){
        switch (boType){
            case ADMIN_DASHBOARD :
                return (T) new AdminDashBoardBOImpl();
            case CASHIER_DASHBOARD:
                return (T) new CashierDashboardBOImpl();
            case CUSTOMER_ADD:
                return (T) new CustomerAddBOImpl();
            case CUSTOMER_BAR:
                return (T) new CustomerBarBOImpl();
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case CUSTOMER_DELETE:
                return (T) new CustomerDeleteBOImpl();
            case CUSTOMER_ORDER_BAR:
                return (T) new CustomerOrderBarBOImpl();
            case CUSTOMER_ORDER:
                return (T) new CustomerOrderBOImpl();
            case CUSTOMER_PLACE_ORDER:
                return (T) new CustomerPlaceOrderBOImpl();
            case CUSTOMER_UPDATE:
                return (T) new CustomerUpdateBOImpl();
            case CUSTOMER_VIEW:
                return (T) new CustomerViewBOImpl();
            case EMPLOYEE_ADD :
                return (T) new EmployeeAddBOImpl();
            case EMPLOYEE_ATTENDANCE_BAR:
                return (T) new EmployeeAttendanceBarBOImpl();
            case EMPLOYEE_ATTENDANCE:
                return (T) new EmployeeAttendanceBOImpl();
            case EMPLOYEE_BAR:
                return (T) new EmployeeBarBOImpl();
            case EMPLOYEE:
                return (T) new EmployeeBOImpl();
            case EMPLOYEE_DELETE:
                return (T) new EmployeeDeleteBOImpl();
            case EMPLOYEE_UPDATE:
                return (T) new EmployeeUpdateBOImpl();
            case EMPLOYEE_VIEW:
                return (T) new EmployeeViewBOImpl();
            case ITEM_ADD:
                return (T) new ItemAddBOImpl();
            case ITEM_BAR:
                return (T) new ItemBarBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ITEM_CASHIER_VIEW_BAR:
                return (T) new ItemCashierViewBarBOImpl();
            case ITEM_CASHIER_VIEW:
                return (T) new ItemCashierViewBOImpl();
            case ITEM_UPDATE :
                return (T) new ItemUpdateBOImpl();
            case ITEM_VIEW:
                return (T) new ItemViewBOImpl();
            case LOGIN_SCEAN2:
                return (T) new LoginScene2BOImpl();
            case REPAIR_ADD:
                return (T) new RepairAddBOImpl();
            case REPAIR_BAR:
                return (T) new RepairBarBOImpl();
            case REPAIR_DELETE:
                return (T) new RepairDeleteBOImpl();
            case REPAIR_PAYMENT:
                return (T) new RepairPaymentBOImpl();
            case REPAIR_VIEW:
                return (T) new RepairViewBOImpl();
            case REPORT:
                return (T) new ReportBOImpl();
            case REPORT_VIEW:
                return (T) new ReportViewBOImpl();
            case SALARY_BAR:
                return (T) new SalaryBarBOImpl();
            case SALARY:
                return (T) new SalaryBOImpl();
            case SUPPLIER_ADD:
                return (T) new SupplierAddBOImpl();
            case SUPPLIER_ALL_ORDER_BAR:
                return (T) new SupplierAllOrderBarBOImpl();
            case SUPPLIER_BAR:
                return (T) new SupplierBarBOImpl();
            case SUPPLIER:
                return (T) new SupplierBOImpl();
            case SUPPLIER_DELETE:
                return (T) new SupplierDeleteBOImpl();
            case SUPPLIER_ORDER:
                return (T) new SupplierOrderBOImpl();
            case SUPPLIER_ORDER_UPDATE:
                return (T) new SupplierOrderUpdateBOImpl();
            case SUPPLIER_UPDATE:
                return (T) new SupplierUpdateBOImpl();
            default:
                return null;
        }
    }
}
