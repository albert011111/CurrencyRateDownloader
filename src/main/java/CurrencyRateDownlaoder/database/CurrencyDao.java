package CurrencyRateDownlaoder.database;


import CurrencyRateDownlaoder.database.domain.Currency;
import CurrencyRateDownlaoder.database.domain.CurrencySelected;

import java.util.List;

public interface CurrencyDao {
    List<Currency> getAll(String tableName1, String tableName2);

    Currency getCurrencyById(int id, String tableName);

    void addCurrentCurrency(Currency currency);

    void addLastCurrency(Currency currency);

    void addSelectedCurrency(CurrencySelected currency);
}
