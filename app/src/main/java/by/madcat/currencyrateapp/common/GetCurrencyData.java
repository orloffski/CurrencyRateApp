package by.madcat.currencyrateapp.common;

import android.icu.text.SimpleDateFormat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GetCurrencyData {

    private static final String DAILYEXRATE = "DailyExRates";
    private static final String CURRENCY = "Currency";
    private static final String CHARCODE = "CharCode";
    private static final String SCALE = "Scale";
    private static final String NAME = "Name";
    private static final String RATE = "Rate";

    public static List<Currency> getData(String date) throws IOException, XmlPullParserException {

        List<Currency> currencies = new ArrayList<>();
        Currency currency = null;
        String currDate = "";

        XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
        URL url = new URL(CurrencyAppConstants.URL_PATH + date);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.connect();

        if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            new Exception("HTTP request error");
            return null;
        }

        pullParser.setInput(new InputStreamReader(urlConnection.getInputStream()));

        int eventType = pullParser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType){
                case XmlPullParser.START_TAG:
                    if(pullParser.getName().equalsIgnoreCase(DAILYEXRATE)){
                        if(pullParser.getAttributeCount() == 0)
                            break;

                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        currDate = sdf.format(Date.parse(pullParser.getAttributeValue(0)));
                    }else if(pullParser.getName().equalsIgnoreCase(CURRENCY)){
                        currency = new Currency();
                        currency.setLastDate(currDate);
                    }else if(pullParser.getName().equalsIgnoreCase(CHARCODE)){
                        currency.setCurrencyCode(pullParser.nextText());
                    }else if(pullParser.getName().equalsIgnoreCase(SCALE)){
                        currency.setCurrencyDescription(String.valueOf(pullParser.nextText()));
                    }else if(pullParser.getName().equalsIgnoreCase(NAME)){
                        currency.setCurrencyDescription(currency.getCurrencyDescription() + " " + pullParser.nextText());
                    }else if(pullParser.getName().equalsIgnoreCase(RATE)){
                        currency.setCurrencyLastRate(String.valueOf(pullParser.nextText()));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if(pullParser.getName().equalsIgnoreCase(CURRENCY)){
                        currencies.add(currency);
                        currency = new Currency();
                    }
                default:
                    break;
            }

            pullParser.next();
            eventType = pullParser.getEventType();
        }

        if(currencies.size() == 0)
            return null;

        return currencies;
    }
}
