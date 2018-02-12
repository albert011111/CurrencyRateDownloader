package CurrencyRateDownlaoder.modelFx;

import javafx.beans.property.*;


public class CurrencyFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty code = new SimpleStringProperty();
    private StringProperty currencyName = new SimpleStringProperty();
    private DoubleProperty value = new SimpleDoubleProperty();
    private DoubleProperty lastValue = new SimpleDoubleProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getCode() {
        return code.get();
    }

    public StringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public String getCurrencyName() {
        return currencyName.get();
    }

    public StringProperty currencyNameProperty() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName.set(currencyName);
    }

    public double getValue() {
        return value.get();
    }

    public DoubleProperty valueProperty() {
        return value;
    }

    public void setValue(double value) {
        this.value.set(value);
    }

    @Override
    public String toString() {
        return "CurrencyFx{" +
                "id=" + id +
                ", code=" + code +
                ", currencyName=" + currencyName +
                ", value=" + value +
                '}';
    }

    public double getLastValue() {
        return lastValue.get();
    }

    public DoubleProperty lastValueProperty() {
        return lastValue;
    }

    public void setLastValue(double lastValue) {
        this.lastValue.set(lastValue);
    }

}
