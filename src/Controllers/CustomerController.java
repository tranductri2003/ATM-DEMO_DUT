package Controllers;

import Models.Account;
import Models.Customer;
import Models.CustomerModel;

import java.sql.SQLException;

public class CustomerController {
    public Customer getUser (String AccountID) throws ClassNotFoundException, SQLException {
        CustomerModel model = new CustomerModel();
        Customer customer = model.getUser(AccountID);
        return customer;
    }
}
