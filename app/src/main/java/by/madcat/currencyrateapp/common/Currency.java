package by.madcat.currencyrateapp.common;

public class Currency {

    private int position;
    private boolean include;
    private String CurrencyCode;
    private String lastDate;
    private String prevDate;

    public Currency(int position, boolean include, String currencyCode) {
        this.position = position;
        this.include = include;
        CurrencyCode = currencyCode;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isInclude() {
        return include;
    }

    public void setInclude(boolean include) {
        this.include = include;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getPrevDate() {
        return prevDate;
    }

    public void setPrevDate(String prevDate) {
        this.prevDate = prevDate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "position=" + position +
                ", include=" + include +
                ", CurrencyCode='" + CurrencyCode + '\'' +
                '}';
    }
}
