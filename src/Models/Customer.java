package Models;

import java.util.Calendar;
import java.util.Date;

public class Customer {
    private int ID;
    private String Name;
    private Date DateOfBirth;
    private String CitizenID;

    public Customer() {
    }

    public Customer(int ID, String name, Date dateOfBirth, String citizenID) {
        this.ID = ID;
        Name = name;
        DateOfBirth = dateOfBirth;
        CitizenID = citizenID;
    }

    public Customer(Customer customer1) {
        ID = customer1.ID;
        Name = customer1.Name;
        DateOfBirth = customer1.DateOfBirth;
        CitizenID = customer1.CitizenID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getCitizenID() {
        return CitizenID;
    }

    public void setCitizenID(String citizenID) {
        CitizenID = citizenID;
    }
}
