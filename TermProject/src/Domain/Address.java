package Domain;

public class Address {

    private int propertyNumber;
    private String streetName;
    private String postalCode;

    public Address (int propertyNumber, String streetName, String postalCode){
        this.propertyNumber = propertyNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
    }

    public int getPropertyNumber()
    {
        return propertyNumber;
    }

    public String getStreetName()
    {
        return streetName;
    }

    public String getPostalCode()
    {
        return postalCode;
    }
}
