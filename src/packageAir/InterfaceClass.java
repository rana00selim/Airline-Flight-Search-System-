package packageAir;

import java.io.*;
import java.util.*;
import java.util.GregorianCalendar;

import static packageAir.Search.*;

public class InterfaceClass {
    static int id = 1;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int ans = 1;
        int opt;
        TreeMap<Integer,Flight> allFlights = new TreeMap<>();

        try {
            File myObj = new File("AllFlights.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] stringArray = data.split(",");
                int dd = Integer.parseInt(stringArray[0].substring(0,2));
                int mm = Integer.parseInt(stringArray[0].substring(3,5));
                int yyyy = Integer.parseInt(stringArray[0].substring(6,10));
                int h = Integer.parseInt(stringArray[1].substring(0,2));
                int m = Integer.parseInt(stringArray[1].substring(3,5));
                GregorianCalendar date1 = new GregorianCalendar (yyyy, (mm-1), dd, h, m);
                String from = stringArray[2];
                String to = stringArray[3];
                String carrier = stringArray[4];
                double price = Double.parseDouble(stringArray[5]);
                Flight flight = new Flight(date1, from, to, carrier, price);
                allFlights.put(id++, flight);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Welcome to Isık Airlines, these are your options about our flights; ");
        while (ans > -1 && ans < 8)       {
            System.out.println("----------MENU----------\n" +
                    "0)     Show all flights \n" +
                    "1)     Insert and remove flight information\n" +
                    "2)     Search for flights by date, however if the given date does not exist in the table show the closest before and after dates which have flight.\n" +
                    "3)     Search for flights by from city\n" +
                    "4)     Search for flights with both from city and date.\n" +
                    "5)     Search for flights between two dates.\n" +
                    "6)     Search for flights less than a given price in a given date.\n" +
                    "7)     Save all changes to the .txt file\n"+
                    "8)     Exit.");
            ans = input.nextInt();

            if (ans == 0){
                showAllFlight(allFlights);
            }

            else if (ans == 1) {
                System.out.println("To insert press 0, to remove press 1");
                opt = input.nextInt();
                if (opt == 0) {
                    if (allFlights.isEmpty()) {
                        System.out.println("There Sorry, we don't have any flight at the moment. no flight");
                    }
                    insertFlight(allFlights);
                } else if (opt == 1) {
                    if (allFlights.isEmpty()) {
                        System.out.println("Sorry, we don't have any flight at the moment.");
                    }else {
                        removeFlight(allFlights);
                    }
                } else {
                    System.out.println("Invalid option!");
                }
            }

            else if (ans == 2) {
                if (allFlights.isEmpty()) {
                    System.out.println("Sorry, we don't have any flight at the moment.");
                } else {
                    GregorianCalendar date2 = createDate();
                    showDateFlight(allFlights, date2);
            }


            }else if (ans == 3){
                if (allFlights.isEmpty()) {
                    System.out.println("Sorry, we don't have any flight at the moment.");
                } else {
                    System.out.println("Which city do you want to search?");
                    String from = input.next();
                    if (from.substring(0, 1).contains("i")){
                        from = from.substring(0, 1).replace("i","I") + from.substring(1);
                    }
                    showCityFlights(allFlights,upperFirst(from));
                }
            }

            else if (ans == 4) {
                if (allFlights.isEmpty()) {
                    System.out.println("Sorry, we don't have any flight at the moment.");
                } else {
                    System.out.println("Which city do you want to search?");
                    String from = input.next();
                    if (from.substring(0, 1).contains("i")){
                        from = from.substring(0, 1).replace("i","I") + from.substring(1);
                    }
                    GregorianCalendar date3 = createDate();
                    showCityDateFlights(allFlights, upperFirst(from), date3);
                }
            }

            else if (ans == 5) {
                if (allFlights.isEmpty()) {
                    System.out.println("Sorry, we don't have any flight at the moment.");
                } else {
                    GregorianCalendar date = createDate();
                    System.out.println("Now for the second,");
                    GregorianCalendar date2 = createDate();
                    show2Dates(allFlights, date, date2);
                }
            }

            else if (ans == 6){
                if (allFlights.isEmpty()) {
                    System.out.println("Sorry, we don't have any flight at the moment.");
                } else {
                    System.out.println("Please enter a top price: ");
                    double price = input.nextInt();
                    lessPrice(allFlights,price);
                }
            }

            else {
                try {
                    FileWriter fw = new FileWriter("AllFlights.txt");
                    PrintWriter pw = new PrintWriter(fw);

                    for (Flight f: allFlights.values()) {
                            pw.println(f.writer());
                    }
                    pw.close();
                    System.out.println("Successfully saved.");
                }catch (IOException e){
                    System.out.println("Error at the saving!");
                }
            }
        }
        if (ans == 8){
            System.out.println("Exiting...");
        }if (ans < 1 || ans > 8){
            System.out.println("Invalid option! ");
        }
    }
}
