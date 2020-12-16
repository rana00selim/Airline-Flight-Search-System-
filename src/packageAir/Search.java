package packageAir;

import java.util.GregorianCalendar;
import java.util.*;

import static packageAir.InterfaceClass.id;

public class Search {

    static GregorianCalendar createDate() {
        Scanner input = new Scanner(System.in);//DATE KONTROLLERİ
        System.out.println("Now you will create a date, ");
        System.out.println("Day: ");
        int dd = input.nextInt();
        while (dd <= 0 || dd > 31) {//Hocam gerekirse Şubat'ı dikkate alan versiyonunu da yazabiliriz.
            System.out.println("Please enter a valid number");
            dd = input.nextInt();
        }
        System.out.println("Month: (As number) ");
        int mm = input.nextInt();
        while (mm <= 0 || mm > 12) {
            System.out.println("Please enter a valid number");
            mm = input.nextInt();
        }
        System.out.println("Year: ");
        int yyyy = input.nextInt();
        while (yyyy <= 0) {
            System.out.println("Please enter a valid number");
            yyyy = input.nextInt();
        }
        System.out.println("Hour: ");
        int h = input.nextInt();
        while (mm <= 0 || mm > 23) {
            System.out.println("Please enter a valid number");
            h = input.nextInt();
        }
        System.out.println("Minute: ");
        int m = input.nextInt();
        while (mm < 0 || mm > 59) {
            System.out.println("Please enter a valid number");
            m = input.nextInt();
        }
        return new GregorianCalendar (yyyy, (mm-1), dd, h, m);
    }

    static void insertFlight(TreeMap<Integer, Flight> allFlights) {
        Scanner input = new Scanner(System.in);
        showAllFlight(allFlights);
        System.out.println("Please enter the informations of the flight, ");
        System.out.println("From: ");
        String from = input.next();
        System.out.println("To: ");
        String to = input.next();
        System.out.println("Carrier: ");
        String carrier = input.next();
        System.out.println("Price: ");
        Double price = input.nextDouble();
        while (price < 0){
            System.out.println("Price must be bigger or equal to zero!");
            price = input.nextDouble();
        }
        Flight flight = new Flight(createDate(), upperFirst(from), upperFirst(to), upperFirst(carrier), price);
        allFlights.put(id++, flight);
        System.out.println(flight.toString());
    }

        static void removeFlight(TreeMap<Integer, Flight> allFlights) {
        Scanner input = new Scanner(System.in);
        showAllFlight(allFlights);
        System.out.println("Enter the ID number of the flight you want to delete: ");
        int ans = input.nextInt();
        if (allFlights.containsKey(ans)) {
            allFlights.remove(ans);
        } else {
            System.out.println("Invalid key!");
        }
    }

    static void showAllFlight(TreeMap<Integer, Flight> allFlights) {
        for (Map.Entry<Integer, Flight> entry : allFlights.entrySet())
            System.out.println("Flight ID " + entry.toString());
    }

    static void showDateFlight(TreeMap<Integer, Flight> allFlights, GregorianCalendar date) {
        TreeMap<GregorianCalendar, Flight> dates = new TreeMap<>();
        for (Map.Entry<Integer, Flight> entry : allFlights.entrySet()) {
            dates.put(entry.getValue().date, entry.getValue());
        }
        if (dates.containsKey(date)) {
            System.out.println("These are the flights we found for you: ");
            for (Map.Entry<GregorianCalendar, Flight> d : dates.entrySet()) {
                if (d.getValue().date.getTime().getDate() == date.getTime().getDate() && d.getValue().date.getTime().getMonth() == date.getTime().getMonth() && d.getValue().date.getTime().getYear() == date.getTime().getYear()) {
                    System.out.println(d.toString());
                }
            }
        }
         if (!dates.containsKey(date)){
            System.out.println("We couldn't find the flight in that date. These are the closest dates: ");
            try {
                System.out.println("Previous: " + dates.lowerKey(date).getTime().toString());
            }catch (NullPointerException e){
                System.out.println("There are no such previous date.");
            }
            try {
                 System.out.println("Next: " + dates.higherKey(date).getTime().toString());
             }catch (NullPointerException e){
                 System.out.println("There are no such next date.");
             }
        }
    }

    static void showCityFlights(TreeMap<Integer, Flight> allFlights, String from){
        TreeMap<String, Flight> fCity = new TreeMap<>();
        for (Map.Entry<Integer, Flight> entry : allFlights.entrySet()) {
            fCity.put(entry.getValue().from, entry.getValue());
        }
        if (fCity.containsKey(from)) {
            System.out.println("These are the flights we found for you: ");
            for (Map.Entry<String, Flight> entry : fCity.entrySet()) {
                if (entry.getValue().from.equals(from)){
                    System.out.println(entry.toString());
                }
            }
        } else {
            System.out.println("Sorry, we don't have that flight.");
        }
    }

    static void showCityDateFlights(TreeMap<Integer, Flight> allFlights, String from, GregorianCalendar date){//Dates ve from değişsin
        TreeMap<String, Flight> fCity = new TreeMap<>();
        for (Map.Entry<Integer, Flight> entry : allFlights.entrySet()) {
            fCity.put(entry.getValue().from, entry.getValue());
        }
        if (fCity.containsKey(from)) {
            System.out.println("These are the flights we found for you: ");
            for (Map.Entry<String, Flight> d : fCity.entrySet()) {
                if (d.getValue().date.getTime().getDay() == date.getTime().getDay() && d.getValue().date.getTime().getMonth() == date.getTime().getMonth() && d.getValue().date.getTime().getYear() == date.getTime().getYear()) {
                    System.out.println(d.toString());
                }
            }
        } else {
            System.out.println("Sorry, we don't have that flight.");
        }
    }

    static void show2Dates(TreeMap<Integer, Flight> allFlights, GregorianCalendar date, GregorianCalendar date2){
        TreeMap<GregorianCalendar, Flight> dates = new TreeMap<>();
        for (Map.Entry<Integer, Flight> entry : allFlights.entrySet()) {
            dates.put(entry.getValue().date, entry.getValue());
        }
        if (dates.subMap(date, date2).toString() != null){
            System.out.println("These are the flights we found for you: ");
            System.out.println(dates.subMap(date,date2).values().toString());
        }else {
            System.out.println("Sorry, we don't any flight in that time zone.");
        }
    }

    static void lessPrice(TreeMap<Integer, Flight> allFlights, double price){
        TreeMap<Double, Flight> prices = new TreeMap<>();
        for (Map.Entry<Integer, Flight> entry : allFlights.entrySet()) {
            prices.put(entry.getValue().price, entry.getValue());
        }if (prices.floorKey(price) != null){
            System.out.println("These are the flights we found for you: ");
            for (Map.Entry<Double, Flight> entry : prices.entrySet()) {
                if (entry.getKey() <= price) {
                    System.out.println(entry.toString());
                }
            }
        }else {
            System.out.println("Sorry, we don't any flight at that price.");
        }
    }
    static String upperFirst(String str){
        if (str.length() > 1){
            String str2 = str.substring(0, 1).toUpperCase() + str.substring(1);
            return str2;
        }else {
            return str;
        }

    }

}


