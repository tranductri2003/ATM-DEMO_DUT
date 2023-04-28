package Models;

import java.sql.*;

public class CardModel {
    public Card getCard(String CardNumber,String Password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://127.0.0.1:3306/data";
        Connection con= DriverManager.getConnection(url,"root","");
        Statement stmt =con.createStatement();
        String query = String.format("SELECT * FROM card WHERE Card_ID=%s AND Pin=%s",CardNumber,Password);
        ResultSet res =stmt.executeQuery(query);

        if (res.next())
        {
            Card card = new Card();
            card.setCardID(res.getString("Card_ID"));
            card.setPassword(res.getString("Pin"));
            card.setAccountID(res.getString("Account_ID"));
            card.setAmount(res.getDouble("Amount"));
            con.close();
            return card;
        }
        else
        {
            con.close();
            return null;
        }
    }

    public void changePassword(Card card) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://127.0.0.1:3306/data";
        Connection con= DriverManager.getConnection(url,"root","");
        Statement stmt =con.createStatement();
        String query = String.format("UPDATE card SET pin =%s WHERE Card_ID =%s",card.getPassword(),card.getCardID());
        stmt.executeUpdate(query);
        stmt.close();
    }

    public void withdraw(Card card) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://127.0.0.1:3306/data";
        Connection con= DriverManager.getConnection(url,"root","");
        Statement stmt =con.createStatement();
        String query = String.format("UPDATE card SET Amount =%s WHERE Card_ID =%s",card.getAmount(),card.getCardID());
        stmt.executeUpdate(query);
        stmt.close();
    }

    public void deposit(Card card) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://127.0.0.1:3306/data";
        Connection con= DriverManager.getConnection(url,"root","");
        Statement stmt =con.createStatement();
        String query = String.format("UPDATE card SET Amount =%s WHERE Card_ID =%s",card.getAmount(),card.getCardID());
        stmt.executeUpdate(query);
        stmt.close();
    }
}
