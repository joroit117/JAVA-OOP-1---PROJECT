package bg.tu_varna.sit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.io.Serializable;
import java.util.stream.Collectors;

public class Warehouse implements Serializable {
    private List<Product> products;
    private List<Change> changes;

    public Warehouse() {
        this.products = new ArrayList<>();
        this.changes = new ArrayList<>();
    }

    public void add(Product product){
        products.add(product);
        changes.add(new Change("Add", product, LocalDate.now()));
    }

    public List<Change> log(Date startDate, Date endDate){
        List<Change> validChanges = new ArrayList<>();
        for (Change change : changes) {
            if(change.getDate().compareTo(startDate) > 0 && change.getDate().compareTo(endDate) < 0){
                validChanges.add(change);
            }
        }
        return validChanges;
    }

    public void remove(String productName, double quantity){
        List<Product> productsToRemove = new ArrayList<>();
        for (Product product : this.products) {
            if(product.getName().equals(productName)){
                productsToRemove.add(product);
            }
        }
        Collections.sort(productsToRemove, new Comparator<Product>() {
            public int compare(Product o1, Product o2) {
                return o1.getExpirationDate().compareTo(o2.getExpirationDate());
            }
        });
        System.out.println(productsToRemove);
    }

    public void print()
    {
        if(!products.isEmpty()){
            for(Product product : products){
                System.out.println(product.toString());
            }
        }else{
            System.out.println("Warehouse is empty!");
        }
        System.out.println("Press Enter key to get back to menu...");
        try {
            System.in.read();
        }
        catch(Exception e)
        {}
    }

    public void clean(){
        products.clear();
        changes.clear();
    }

}
