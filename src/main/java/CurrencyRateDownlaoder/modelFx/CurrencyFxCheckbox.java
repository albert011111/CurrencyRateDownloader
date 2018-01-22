package CurrencyRateDownlaoder.modelFx;

import javafx.beans.property.*;

/**
 * Created by Patryk on 2017-07-20.
 */
public class CurrencyFxCheckbox {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty code = new SimpleStringProperty();
    private StringProperty currencyName = new SimpleStringProperty();
    private BooleanProperty selected = new SimpleBooleanProperty();

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

    public boolean isSelected() {
        return selected.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", code=" + code +
                ", selected=" + selected;
    }
}
