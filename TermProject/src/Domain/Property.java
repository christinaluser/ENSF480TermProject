package Domain;

public class Property {
    public String type;
    public int noBedrooms;
    public int noBathrooms;
    public boolean isFurnished;
    public String cityQuadrant;
    public String listingState;
    public double rent;
    public Date datePosted;

    Property(String type, int noBedrooms, int noBathrooms, boolean isFurnished,
     String cityQuadrant, String listingState, double rent, Date datePosted) {
        this.type = type;
        this.noBedrooms = noBedrooms;
        this.noBathrooms = noBathrooms;
        this.isFurnished = isFurnished;
        this.cityQuadrant = cityQuadrant;
        this.listingState = listingState;
        this.rent = rent;
        this.datePosted = datePosted;
    }
}