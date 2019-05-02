package by.madcat.currencyrateapp.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrencyAppConstants {
    public static final String URL_PATH = "http://www.nbrb.by/Services/XmlExRates.aspx?ondate=";
    public static final List<String> DEFAULT_CURRENCY = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("USD");
                add("EUR");
                add("RUB");
            }});
}
