package CurrencyRateDownlaoder.utils;

import CurrencyRateDownlaoder.database.domain.Currency;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;


public class ReportGenerator {

    public static final String REPORT_PATH = "currencyReport.txt";

    public static void generateReport(Map<Currency, Double> sortedMap) {

        File file = new File(REPORT_PATH);

        try {
            PrintWriter writer = new PrintWriter(file);
            Currency currency = null;
            double value = 0.0;
            sortedMap.forEach((k, v) -> {
                writer.println("|" + k.getCode() + "|" + k.getCurrencyName() + "|" + v);
            });
            writer.close();
            Alerts.infoAlert("Raport został zapisany!");
        } catch (FileNotFoundException e) {
            Alerts.errorAlert("Nie znaleziono ścieżki pliku!");
        }

    }


}
