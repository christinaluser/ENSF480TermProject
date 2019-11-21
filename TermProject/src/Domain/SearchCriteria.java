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

    public String getType()
    {
        return type;
    }

    public int getNoBedrooms()
    {
        return noBedrooms;
    }

    public boolean getIsFurnished()
    {
        return isFurnished;
    }

    public int getNoBathrooms()
    {
        return noBathrooms;
    }

    public String getCityQuadrant()
    {
        return cityQuadrant;
    }

    public double getPriceRange()
    {
        return priceRange;
    }
}
