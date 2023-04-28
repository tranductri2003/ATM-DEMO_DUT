package Models;

public class Account {
    private String ID;
    private int CustomerID;
    private String BankName;

    public Account() {
    }

    public Account(String ID, int customerID, String bankName) {
        this.ID = ID;
        CustomerID = customerID;
        BankName = bankName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }
}
