package com.example.bbms.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Camp {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty  phone;
    private final StringProperty  organizedBy;
    private final StringProperty  startDate;
    private final StringProperty  endDate;

    public Camp() {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        address = new SimpleStringProperty(this, "address");
        phone = new SimpleStringProperty(this, "phone");
        organizedBy = new SimpleStringProperty(this, "organizedBy");
        startDate = new SimpleStringProperty(this, "startDate");
        endDate = new SimpleStringProperty(this, "endDate");
    }public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }

    public StringProperty nameProperty() { return name; }
    public String getName() { return name.get(); }
    public void setName(String newName) { name.set(newName); }

    public StringProperty addressProperty() { return address; }
    public String getAddress() { return address.get(); }
    public void setAddress(String newAddress) { address.set(newAddress); }

    public StringProperty phoneProperty() { return phone; }
    public String getphone() { return phone.get(); }
    public void setPhone(String newPhone) { phone.set(newPhone); }

    public StringProperty organizedByProperty() { return organizedBy; }
    public String getOrganizedBy() { return organizedBy.get(); }
    public void setOrganizedBy(String newOrganizedBy) { organizedBy.set(newOrganizedBy); }

    public StringProperty startDateProperty() { return startDate; }
    public String getStartDate() { return startDate.get(); }
    public void setStartDate(String newStartDate) { startDate.set(newStartDate); }

    public StringProperty endDateProperty() { return endDate; }
    public String getEndDate() { return endDate.get(); }
    public void setEndDate(String newEndDate) { endDate.set(newEndDate); }
}
