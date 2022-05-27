package bg.tu_varna.sit;

import java.time.LocalDate;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.io.Serializable;

public class Change implements Serializable{
    private String type;
    private Product product;
    private LocalDate date;

    public Change(String type, Product product, LocalDate date) {
        this.type = type;
        this.product = product;
        this.date = date;
    }

    public Date getDate() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        return Date.from(date.atStartOfDay(defaultZoneId).toInstant());
    }

    public String toString(int count) {
        return "Change " + count +
                "\n -Type: '" + type + '\''
                + product.toString() +
                " -Date of change = " + date +
                "\n";
    }
}
