package Domain;

public class Property {
    public int propertyId;
    public String type;
    public int noBedrooms;
    public int noBathrooms;
    public boolean isFurnished;
    public String cityQuadrant;
    public String listingState;
    public double rent;
    //public Date datePosted;
    // I REMOVED ADDRESS bc i havent configured it in the db yet

    public String toString() {
        return "ID: " + propertyId + "\tType: " + type + "\tno. Bedrooms: " + noBedrooms + "\t no. Bathrooms: " + noBathrooms + "\n";
    }

    public Property(int propertyId, String type, int noBedrooms, int noBathrooms, boolean isFurnished,
     String cityQuadrant, String listingState, double rent) {
        this.propertyId = propertyId;
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

}