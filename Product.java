package bg.tu_varna.sit;

import java.util.Date;
import java.io.Serializable;

public class Product implements Serializable{
    private String name;
    private Date expirationDate;
    private Date entryDate;
    private String manufacturerName;
    private Units unit;
    private double availability;
    private Location location;
    private String comment;

    public Product(String name, Date expirationDate, Date entryDate, String manufacturerName,
                   Units unit, double availability, Location location, String comment) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.entryDate = entryDate;
        this.manufacturerName = manufacturerName;
        this.unit = unit;
        this.availability = availability;
        this.location = location;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public double getAvailability() {
        return availability;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    @Override
    public String toString() {
        return "\n -Product" +
                "\n    Product name: '" + name + '\'' +
                "\n    Expiration date: " + expirationDate +
                "\n    Entry date: " + entryDate +
                "\n    Manufacturer name: '" + manufacturerName + '\'' +
                "\n    Unit: " + unit +
                "\n    Availability: " + availability +
                "\n    " + location.toString() +
                "\n    Comment: '" + comment + '\'' + "\n";
    }

    public int compareTo(Product p) {
        return getExpirationDate().compareTo(p.getExpirationDate());
    }

}
