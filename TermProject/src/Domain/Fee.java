package Domain;

import java.util.Date;

public class Fee {
    public double amount;
    public Date startDate;
    public Date endDate;

    public Fee(double amount) {
        this.amount = amount;
    }
}