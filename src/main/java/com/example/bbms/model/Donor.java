package com.example.bbms.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Donor {
    private final StringProperty id;
    private final StringProperty fullName;
    private final StringProperty bloodType;

    private final StringProperty address;

    private final StringProperty  phone;
    private final StringProperty  email;

    public Donor()
    {
        id = new SimpleStringProperty(this, "id");
        fullName = new SimpleStringProperty(this, "firstName");
        address = new SimpleStringProperty(this, "address");
        bloodType = new SimpleStringProperty(this, "bloodType");
        phone = new SimpleStringProperty(this, "phone");
        email = new SimpleStringProperty(this, "email");

    }

    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }

    public StringProperty fullNameProperty() { return fullName; }
    public String getFullName() { return fullName.get(); }
    public void setFullName(String newName) { fullName.set(newName); }


    public StringProperty addressProperty() { return address; }
    public String getAddress() { return address.get(); }
    public void setAddress(String newAddress) { address.set(newAddress); }

    public StringProperty phoneProperty() { return phone; }
    public String getPhone() { return phone.get(); }
    public void setPhone(String newPhone) { phone.set(newPhone); }

    public StringProperty bloodTypeProperty() { return bloodType; }
    public String getBloodType() { return bloodType.get(); }
    public void setBloodType(String newName) { bloodType.set(newName); }

    public StringProperty emailProperty() { return email; }
    public String getEmail() { return email.get(); }
    public void setEmail(String newEmail) { email.set(newEmail); }

}
