package Domain;

public class Property {
    public String type;
    public int noBedrooms;
    public int noBathrooms;
    public boolean isFurnished;
    public String cityQuadrant;
    public String listingState;
    public double rent;
    //public Date datePosted;

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

    protected String getType()
    {
        return type;
    }

    protected int getNoBedrooms()
    {
        return noBedrooms;
    }

    protected int getNoBathrooms()
    {
        return noBathrooms;
    }

    protected String getCityQuadrant()
    {
        return cityQuadrant;
    }

    protected double getRent()
    {
        return rent;
    }

}