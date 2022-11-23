package com.example.bbms.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BloodStock {
    private final StringProperty id;
    private final StringProperty bloodType;
    private final StringProperty quantity;

    public BloodStock()
    {
        id = new SimpleStringProperty(this, "id");
        bloodType = new SimpleStringProperty(this, "bloodType");
        quantity = new SimpleStringProperty(this, "quantity");
    }

    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }

    public StringProperty bloodTypeProperty() { return bloodType; }
    public String getBloodType() { return bloodType.get(); }
    public void setBloodType(String newName) { bloodType.set(newName); }

    public StringProperty quantityProperty() { return quantity; }
    public String getQuantity() { return quantity.get(); }
    public void setQuantity(String newAddress) { quantity.set(newAddress); }


}
