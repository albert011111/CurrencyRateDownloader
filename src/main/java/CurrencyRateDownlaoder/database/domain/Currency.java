package CurrencyRateDownlaoder.database.domain;


public class Currency {
    private int id;
    private String code;
    private String currencyName;
    private double value;
    private double lastValue;
    private boolean selected;


    public Currency() {
    }

    public Currency(int id, String code, String currencyName) {
        this.id = id;
        this.code = code;
        this.currencyName = currencyName;
    }

    public Currency(int id, String code, String currencyName, boolean selected) {
        this.id = id;
        this.code = code;
        this.currencyName = currencyName;
        this.selected = selected;
    }

    public Currency(int id, String code, String currencyName, double value, double lastValue) {
        this.id = id;
        this.code = code;
        this.currencyName = currencyName;
        this.value = value;
        this.lastValue = lastValue;

    }


    @Override
    public String toString() {
        return id + " " + code + " " + currencyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getLastValue() {
        return lastValue;
    }

    public void setLastValue(double lastValue) {
        this.lastValue = lastValue;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
