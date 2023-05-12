package Models;

import java.sql.*;

public class AccountModel {
    public Account getAccount(String AccountID) throws SQLException, ClassNotFoundException {
    	Connection con = JDBC.getConnection("jdbc:mySQL://localhost:3306/data");
		Statement stmt = con.createStatement();
        String query = String.format("SELECT * FROM account WHERE Account_ID=%s",AccountID);
        ResultSet res =stmt.executeQuery(query);


        if (res.next())
        {
            Account account = new Account();
            account.setID(res.getString("Account_ID"));
            account.setCustomerID(res.getInt("Customer_ID"));
            account.setBankName(res.getString("Bank"));
            con.close();
            return account;
        }
        else
        {
            con.close();
            return null;
        }
    }
}
