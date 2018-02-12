package CurrencyRateDownlaoder.utils;

import CurrencyRateDownlaoder.database.domain.Currency;
import CurrencyRateDownlaoder.modelFx.CurrencyFx;
import CurrencyRateDownlaoder.modelFx.CurrencyFxCheckbox;

public class ConverterCurrencyToFX {

    public static CurrencyFx getCurrencyFx(Currency currency) {
        CurrencyFx currencyFx = new CurrencyFx();
        currencyFx.setId(currency.getId());
        currencyFx.setCode(currency.getCode());
        currencyFx.setCurrencyName(currency.getCurrencyName());
        currencyFx.setValue(currency.getValue());
        currencyFx.setLastValue(currency.getLastValue());
        return currencyFx;
    }

    public static CurrencyFxCheckbox getCurrencyFxCheckbox(Currency currency) {
        CurrencyFxCheckbox fxCheckbox = new CurrencyFxCheckbox();
        fxCheckbox.setId(currency.getId());
        fxCheckbox.setCode(currency.getCode());
        fxCheckbox.setCurrencyName(currency.getCurrencyName());
        fxCheckbox.setSelected(false);
        return fxCheckbox;
    }

}
