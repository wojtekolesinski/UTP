package zad1;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class TravelData {
    private List<Offer> offers;

    public TravelData(File dataDirectory) {
        offers = new ArrayList<>();

        try {
            Files.walk(dataDirectory.toPath()).filter(Files::isRegularFile).forEach(path -> {
                try {
                    Files.lines(path).forEach(line -> {
                        String[] data = line.split("\t");
                        Locale locale = Locale.forLanguageTag(data[0].replace("_", "-"));
                        Locale country = Arrays.stream(Locale.getAvailableLocales())
                                .filter(loc -> loc.getDisplayCountry(locale).equals(data[1]))
                                .findFirst()
                                .orElseThrow(IllegalStateException::new);

                        Date departureDate = null;
                        Date arrivalDate = null;
                        BigDecimal price = null;
                        try {
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            departureDate = df.parse(data[2]);
                            arrivalDate = df.parse(data[3]);
                            price = BigDecimal.valueOf(NumberFormat.getInstance(locale).parse(data[5]).doubleValue());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        ResourceBundle bundle = ResourceBundle.getBundle("zad1.locales.places", locale);

                        Place place = Place.valueOf(bundle
                            .keySet()
                            .stream()
                            .filter(key -> bundle.getString(key).equals(data[4]))
                            .findFirst()
                            .orElseThrow(IllegalStateException::new)
                            .toUpperCase());

                        offers.add(new Offer(
                            locale,
                            country,
                            departureDate,
                            arrivalDate,
                            place,
                            price,
                            Currency.getInstance(data[6])));
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getOffersDescriptionsList(String languageTag, String dateFormat) {
        Locale locale = Locale.forLanguageTag(languageTag.replace('_', '-'));
        List<String> offersDescriptions = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        ResourceBundle bundle = ResourceBundle.getBundle("zad1.locales.places", locale);

        for (Offer offer: offers) {
            String description = offer.getCountry().getDisplayCountry(locale) + " "
                + df.format(offer.getDepartureDate()) + " "
                + df.format(offer.getReturnDate()) + " "
                + bundle.getString(offer.getPlace().getName()) + " "
                + NumberFormat.getInstance(locale).format(offer.getPrice()) + " "
                + offer.getCurrency();

            offersDescriptions.add(description);
        }

        return offersDescriptions;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
