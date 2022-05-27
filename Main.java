package bg.tu_varna.sit;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        mainMenuExecute();

        /*String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Product cheese = new Product("Cows", simpleDateFormat.parse("28-03-2022"), simpleDateFormat.parse("01-06-2022"), "Bella",
                Units.kg, 20, new Location('A', 3, 10), "Perfect quality cow cheese!");

        warehouse.add(cheese);*/


        //open D:\\IT\\Files\\d.txt




       /* List<Change> changes;
        changes = warehouse.log(simpleDateFormat.parse("01-01-2022"), simpleDateFormat.parse("27-0-2022"));
        for (Change change : changes) {
            int count = 1;
            System.out.println(change.toString(count));
            count++;
        }*/

        //warehouse.print();
    }

    public static String mainMenu() throws IOException {
        System.out.println("Open");
        System.out.println("Help");
        System.out.println("Exit");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String choice = reader.readLine();
        return choice;
    }

    public static void mainMenuExecute() throws IOException, ParseException {
        String option = mainMenu();
        String command = option.split(" ")[0];
        if(command.equalsIgnoreCase("open")){
            Map.Entry<Warehouse, File> pair = open(option);
            insideMenuExecute(pair);
        }else if(command.equalsIgnoreCase("help")){
            printHelpInfo();
            mainMenuExecute();
        }else if(command.equalsIgnoreCase("exit")){
            System.exit(0);
        }
    }


    public static String insideMenu() throws IOException {
        System.out.println("\n------------------MENU--------------------");
        System.out.println("| File options:    |  Warehouse options: |");
        System.out.println("| -Close           |  -Print             |");
        System.out.println("| -Save            |  -Add               |");
        System.out.println("| -Save as         |  -Remove            |");
        System.out.println("| -Help            |  -Log <from> <to>   |");
        System.out.println("| -Exit            |  -Clean             |");
        System.out.println("------------------------------------------\n");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String choice = reader.readLine();
        return choice;
    }

    public static void insideMenuExecute(Map.Entry<Warehouse, File> pair) throws IOException, ParseException {
        String option = insideMenu();
        String command = option.split(" ")[0];
        if(command.equalsIgnoreCase("close")){
            System.out.println("Successfully closed " + pair.getValue().getName());
            mainMenuExecute();
        }else if(command.equalsIgnoreCase("save")){
            new FileOutputStream(pair.getValue()).close();
            FileOutputStream f = new FileOutputStream(pair.getValue());
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(pair.getKey());

            o.close();
            f.close();
            System.out.println("Successfully saved file!");
            insideMenuExecute(pair);
        }else if(command.equalsIgnoreCase("save as")){
            String filePath = option.split(" ")[1];
            new FileOutputStream(filePath).close();
            FileOutputStream f = new FileOutputStream(filePath);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(pair.getKey());

            o.close();
            f.close();
            System.out.println("Successfully saved file!");
            insideMenuExecute(pair);
        }else if(command.equalsIgnoreCase("help")){
            printHelpInfo();
            mainMenuExecute();
        }else if(command.equalsIgnoreCase("exit")){
            System.exit(0);
        }else if(command.equalsIgnoreCase("print")){
            pair.getKey().print();
            insideMenuExecute(pair);
        }else if(command.equalsIgnoreCase("add")){
            add(pair);
            insideMenuExecute(pair);
        }else if(command.equalsIgnoreCase("remove")){
            String productName = option.split(" ")[1];
            double quantity = Double.parseDouble(option.split(" ")[2]);
            pair.getKey().remove(productName, quantity);
            insideMenuExecute(pair);
        }else if(command.equalsIgnoreCase("log")){
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String from = option.split(" ")[1];
            String to = option.split(" ")[2];
            List<Change> changes = pair.getKey().log(simpleDateFormat.parse(from), simpleDateFormat.parse(to));
            changes.toString();
            insideMenuExecute(pair);
        }else if(command.equalsIgnoreCase("clean")){
            pair.getKey().clean();
            insideMenuExecute(pair);
        }
    }

    public static Map.Entry<Warehouse, File> open(String option){
        FileInputStream fi = null;
        ObjectInputStream oi = null;
        Warehouse warehouse = new Warehouse();
        String filePath = option.split(" ")[1];
        File data = new File(filePath);
        try {
            fi = new FileInputStream(data);
            oi = new ObjectInputStream(fi);

            warehouse = (Warehouse) oi.readObject();
            if(oi != null){
                oi.close();
            }
            if(fi != null){
                fi.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(warehouse == null){
            warehouse = new Warehouse();
        }
        System.out.println("Successfully opened " + data.getName());
        Map.Entry<Warehouse,File> pair=new AbstractMap.SimpleEntry<>(warehouse,data);
        return pair;
    }

    public static void printHelpInfo(){
        System.out.println("> help");
        System.out.println("open <file>       opens <file>");
        System.out.println("close             closes currently opened file");
        System.out.println("save              saves the currently open file");
        System.out.println("save as           saves the currently open file in <file>");
        System.out.println("help              prints this information");
        System.out.println("exit              exits the program");
        System.out.println("Press Enter key to get back to main menu...");
        try {
            System.in.read();
        }
        catch(Exception e)
        {}
    }

    public static Map.Entry<Warehouse, File> add(Map.Entry<Warehouse, File> pair) throws ParseException, IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        System.out.println("Please enter product name: ");
        String productName = reader.readLine();
        System.out.println("Please expiration date of product in format/dd-mm-yyyy/: ");
        String expirationDate = reader.readLine();
        System.out.println("Please entry date of product in format/dd-mm-yyyy/: ");
        String entryDate = reader.readLine();
        System.out.println("Please enter manufacturer name: ");
        String manufacturerName = reader.readLine();
        String unit = "";
        int i = 0;
        while(!unit.equalsIgnoreCase("kg") && !unit.equalsIgnoreCase("l")){
            if(i > 0){
                System.out.println("Please enter a VALID unit kilograms or liters(kg/l): ");
            }else{
                System.out.println("Please enter unit kilograms or liters(kg/l): ");
            }
            unit = reader.readLine();
            i++;
        }
        System.out.println("Please enter product availability: ");
        int availability = Integer.parseInt(reader.readLine());
        System.out.println("Please enter product location in warehouse: ");
        System.out.println(" -Please enter section(A-Z): ");
        String sectionString = reader.readLine();
        char section = sectionString.charAt(0);
        System.out.println(" -Please enter shelf number(1-10): ");
        int shelf = Integer.parseInt(reader.readLine());
        System.out.println(" -Please enter number(1-25): ");
        int number = Integer.parseInt(reader.readLine());
        System.out.println("Please enter product comment: ");
        String comment = reader.readLine();

        Product product = new Product(
                productName,
                simpleDateFormat.parse(expirationDate),
                simpleDateFormat.parse(entryDate),
                manufacturerName,
                Units.valueOf(unit),
                availability,
                new Location(section, shelf, number),
                comment);

        pair.getKey().add(product);
        System.out.println("Product added successfully!");
        return pair;
    }
}
