package zad1;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class Offer {

    private Locale locale;
    private Locale country;
    private Date departureDate;
    private Date returnDate;
    private Place place;
    private BigDecimal price;
    private Currency currency;

    public Offer(Locale locale, Locale country, Date departureDate, Date returnDate, Place place, BigDecimal price, Currency currency) {
        this.locale = locale;
        this.country = country;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.place = place;
        this.price = price;
        this.currency = currency;
    }

    public Locale getLocale() {
        return locale;
    }

    public Locale getCountry() {
        return country;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Place getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "locale=" + locale +
                ", country=" + country +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", place=" + place +
                ", price=" + price +
                ", currency=" + currency +
                '}';
    }
}
