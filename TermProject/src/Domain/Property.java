package Domain;

import java.util.Date;

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

    public Property(int propertyId, String type, Address address, int noBedrooms, int noBathrooms, boolean isFurnished,
                    String cityQuadrant, String listingState, double rent, Date datePosted) {
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
    }

    public int getPropertyId()
    {
        return propertyId;
    }

    public String toString() {
        return "ID: " + propertyId + "\tType: " + type + "\tno. Bedrooms: " + noBedrooms + "\t no. Bathrooms: " + noBathrooms + "\n";
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


    public void setState(String newState) {
        listingState = newState;
    }
}