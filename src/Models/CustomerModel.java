package Models;

import java.sql.*;

public class CustomerModel {
    public Customer getUser(String AccountID) throws SQLException, ClassNotFoundException {
    	Connection con = JDBC.getConnection("jdbc:mySQL://localhost:3306/data");
		Statement stmt = con.createStatement();
        String query = String.format("SELECT * FROM account WHERE Account_ID=%s LIMIT 1",AccountID);
        ResultSet res =stmt.executeQuery(query);

        if (res.next())
        {
            String customerID= res.getString("Customer_ID");
            query = String.format("SELECT * FROM customer WHERE Customer_ID=%s",customerID);
            res=stmt.executeQuery(query);
        }


        if (res.next())
        {
            Customer customer = new Customer();
            customer.setID(res.getInt("Customer_ID"));
            customer.setName(res.getString("Name"));
            customer.setDateOfBirth(res.getDate("DateOfBirth"));
            customer.setCitizenID(res.getString("Citizen"));
            con.close();
            return customer;
        }
        else
        {
            con.close();
            return null;
        }
    }
}
