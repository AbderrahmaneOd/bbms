package com.example.bbms.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Donor {
    private final StringProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty bloodType;

    private final StringProperty address;

    private final StringProperty  phone;

    public Donor()
    {
        id = new SimpleStringProperty(this, "id");
        firstName = new SimpleStringProperty(this, "firstName");
        lastName = new SimpleStringProperty(this, "lastName");
        address = new SimpleStringProperty(this, "address");
        bloodType = new SimpleStringProperty(this, "bloodType");
        phone = new SimpleStringProperty(this, "phone");

    }

    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }

    public StringProperty firstNameProperty() { return firstName; }
    public String getFirstName() { return firstName.get(); }
    public void setFirstName(String newName) { firstName.set(newName); }

    public StringProperty lastNameProperty() { return lastName; }
    public String getLastName() { return lastName.get(); }
    public void setLastName(String newName) { lastName.set(newName); }


    public StringProperty addressProperty() { return address; }
    public String getAddress() { return address.get(); }
    public void setAddress(String newAddress) { address.set(newAddress); }

    public StringProperty phoneProperty() { return phone; }
    public String getPhone() { return phone.get(); }
    public void setPhone(String newPhone) { phone.set(newPhone); }

    public StringProperty bloodTypeProperty() { return bloodType; }
    public String getBloodType() { return bloodType.get(); }
    public void setBloodType(String newName) { bloodType.set(newName); }

}
