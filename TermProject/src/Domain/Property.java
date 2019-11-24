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
    // I REMOVED ADDRESS bc i havent configured it in the db yet

    public Property(int propertyId, String type, int noBedrooms, int noBathrooms, boolean isFurnished,
                    String cityQuadrant, String listingState, double rent, Date datePosted) {
        this.propertyId = propertyId;
        this.type = type;
        this.noBedrooms = noBedrooms;
        this.noBathrooms = noBathrooms;
        this.isFurnished = isFurnished;
        this.cityQuadrant = cityQuadrant;
        this.listingState = listingState;
        this.rent = rent;
        this.datePosted = datePosted;
        // REMOVED DATE POSTED
    }

    public Property(String type, int noBedrooms, int noBathrooms, boolean isFurnished,
                    String cityQuadrant, String listingState, double rent) {
        this.type = type;
        this.noBedrooms = noBedrooms;
        this.noBathrooms = noBathrooms;
        this.isFurnished = isFurnished;
        this.cityQuadrant = cityQuadrant;
        this.listingState = listingState;
        this.rent = rent;
        //this.datePosted = datePosted;
        // REMOVED DATE POSTED
    }

    public String toString() {
        return "ID: " + propertyId + "\tType: " + type + "\tno. Bedrooms: " + noBedrooms + "\t no. Bathrooms: " + noBathrooms + "\n";
    }

    public String getType()
    {
        return type;
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
}