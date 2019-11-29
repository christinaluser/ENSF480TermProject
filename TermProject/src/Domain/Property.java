package Domain;

import java.util.Calendar;
import java.sql.Date;

public class Property {
    private int propertyId;
    private String type;
    private int noBedrooms;
    private int noBathrooms;
    private boolean isFurnished;
    private String cityQuadrant;
    private String listingState;
    private double rent;
    private Date datePosted;
    private Fee fee;
    private Address address;
    private String email;

    public Property(int propertyId, String type, Address address, int noBedrooms, int noBathrooms, boolean isFurnished,
                    String cityQuadrant, String listingState, double rent, Date datePosted, String email) {
        this.propertyId = propertyId;
        this.type = type;
        this.address = address;
        this.noBedrooms = noBedrooms;
        this.noBathrooms = noBathrooms;
        this.isFurnished = isFurnished;
        this.cityQuadrant = cityQuadrant;
        this.listingState = listingState;
        this.rent = rent;
        this.datePosted = datePosted;
        this.email = email;
    }

    public Property(String type, int houseNumber, String street, String postalCode, String cityQuadrant,
                    int noBedrooms, int noBathrooms, boolean isFurnished, double rent) {
        this.propertyId = 5 + (int)(Math.random() * ((1000000 - 5) + 1));
        this.type = type;
        this.address = new Address(houseNumber, street, postalCode);
        this.cityQuadrant = cityQuadrant;
        this.noBedrooms = noBedrooms;
        this.noBathrooms = noBathrooms;
        this.isFurnished = isFurnished;
        this.rent = rent;
        this.listingState = "suspended";
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        datePosted = sqlDate;
    }

    public int getPropertyId()
    {
        return propertyId;
    }

    public String toString() {
        return propertyId + "/" + type + "/" + rent + "/" + address.getPropertyNumber() + "/" + address.getStreetName()
                + "/" + address.getPostalCode() + "/" + cityQuadrant + "/" + noBedrooms + "/" + noBathrooms + "/" + isFurnished ;
    }

    public String toStringManager() {
        return propertyId + "/" + type + "/" + rent + "/" + address.getPropertyNumber() + "/" + address.getStreetName()
                + "/" + address.getPostalCode() + "/" + cityQuadrant + "/" + noBedrooms + "/" + noBathrooms + "/" + isFurnished + "/" + listingState;
    }

    public String getType()
    {
        return type;
    }

    public Address getAddress()
    {
        return address;
    }

    public String getAddressParts() {
        return address.getPropertyNumber() + "/" + address.getStreetName() + "/" + address.getPostalCode();
    }

    public int getNoBedrooms()
    {
        return noBedrooms;
    }

    public int getNoBathrooms()
    {
        return noBathrooms;
    }

    public boolean getIsFurnished()
    {
        return isFurnished;
    }

    public String getCityQuadrant()
    {
        return cityQuadrant;
    }

    public String getListingState()
    {
        return listingState;
    }

    public double getRent()
    {
        return rent;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public Date getDatePosted()
    {
        return datePosted;
    }

    public String getEmail()
    {
        return email;
    }

    public void setState(String newState) {
        listingState = newState;
    }
}