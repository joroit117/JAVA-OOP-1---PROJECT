package bg.tu_varna.sit;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class Location implements Serializable{
    private Map<Set<String>, Map<Set<String>, Array>> location = new HashMap();
    private char section;
    private int shelf;
    private int number;

    public Location(char section, int shelf, int number) {
        this.section = section;
        this.shelf = shelf;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Location:" +
                "\n     ->Section: " + section +
                "\n     ->Shelf: " + shelf +
                "\n     ->Number: " + number;
    }
}
