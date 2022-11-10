package com.example.bbms.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hospital {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty  email;
    private final StringProperty  password;

    public Hospital() {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        address = new SimpleStringProperty(this, "address");
        email = new SimpleStringProperty(this, "email");
        password = new SimpleStringProperty(this, "password");
    }public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }

    public StringProperty nameProperty() { return name; }
    public String getName() { return name.get(); }
    public void setName(String newName) { name.set(newName); }

    public StringProperty addressProperty() { return address; }
    public String getAddress() { return address.get(); }
    public void setAddress(String newAddress) { address.set(newAddress); }

    public StringProperty emailProperty() { return email; }
    public String getEmail() { return email.get(); }
    public void setEmail(String newEmail) { email.set(newEmail); }

    public StringProperty passwordProperty() { return password; }
    public String getPassword() { return password.get(); }
    public void setPassword(String newPassword) { password.set(newPassword); }

}

