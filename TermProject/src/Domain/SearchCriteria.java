package Domain;

public class SearchCriteria {
    public String type;
    public int noBedrooms;
    public int noBathrooms;
    public boolean isFurnished;
    public String cityQuadrant;
    public double priceRange;

    public SearchCriteria(String type, int noBedrooms, int noBathrooms, boolean isFurnished,
             String cityQuadrant, double priceRange) {
        this.type = type;
        this.noBedrooms = noBedrooms;
        this.noBathrooms = noBathrooms;
        this.isFurnished = isFurnished;
        this.cityQuadrant = cityQuadrant;
        this.priceRange = priceRange;
    }
}
