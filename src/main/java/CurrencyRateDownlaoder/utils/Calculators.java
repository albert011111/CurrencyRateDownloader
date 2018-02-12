package CurrencyRateDownlaoder.utils;

import CurrencyRateDownlaoder.database.domain.Currency;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Calculators {
    public static Map<Currency, Double> getBiggestDifference(List<Currency> currencyList) {
        Map<Currency, Double> results = new LinkedHashMap<>();
        double v1;
        double v2;
        double difference;

        for (Currency c : currencyList) {
            v1 = c.getValue();
            v2 = c.getLastValue();
            difference = v1 - v2;
            results.put(c, difference);
        }
        return results;
    }

    public static double calcAvgValue(List<Double> list) {
        double sum = 0;
        for (Double aDouble : list) {
            sum += aDouble;
        }
        return sum / (list.size());
    }
}
