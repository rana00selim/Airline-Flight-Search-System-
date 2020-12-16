package packageAir;

import java.text.DecimalFormat;
import java.util.GregorianCalendar;


public class Flight {

    GregorianCalendar date;
    String from;
    String to;
    String carrier;
    double price;

    Flight(GregorianCalendar date, String from, String to, String carrier, double price){
        this.date = date;
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.price = price;

    }

    String writer(){
        int dd = date.getTime().getDate();
        int mm = date.getTime().getMonth()+1;
        int yyyy = date.getTime().getYear()+1900;
        int h = date.getTime().getHours();
        int m = date.getTime().getMinutes();

        return new DecimalFormat("00").format(dd) + "-" + new DecimalFormat("00").format(mm) +
                "-" +  new DecimalFormat("0000").format(yyyy) + "," + new DecimalFormat("00").format(h) +
                ":" + new DecimalFormat("00").format(m) + "," +  from + "," + to + "," + carrier + "," + price;
    }

    @Override
    public String toString() {
        return  "Date: " + date.getTime() +
                " | From: " + from +
                " | To: " + to +
                " | Carrier: " + carrier +
                " | Price: " + price +" TL" + '\n' ;
    }
}
