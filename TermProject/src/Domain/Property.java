package Domain;

import java.util.Date;

public class Property {
    public int propertyId;
    public String type;
    public Address address;
    public int noBedrooms;
    public int noBathrooms;
    public boolean isFurnished;
    public String cityQuadrant;
    public String listingState;
    public double rent;
    public Date datePosted;

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

    public String getType()
    {
        return type;
    }

    public Address getAddress()
    {
        return address;
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

    public Date getDatePosted()
    {
        return datePosted;
    }


}