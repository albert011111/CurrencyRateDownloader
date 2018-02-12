package CurrencyRateDownlaoder.utils;

import CurrencyRateDownlaoder.database.CurrencyDao;
import CurrencyRateDownlaoder.database.CurrencyDaoImpl;
import CurrencyRateDownlaoder.database.domain.Currency;
import CurrencyRateDownlaoder.database.domain.SelectedCurrency;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CurrencyRatesDownloader {

    public static String generateNbpPath(String currencyCode) {
        String path = "http://api.nbp.pl/api/exchangerates/rates/a/" + currencyCode.toLowerCase() + "/last/10/";
        return path;
    }

    public static void downloadSelectedCurrencyFromNbpApi(String path) {
        JSONParser parser = new JSONParser();
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        ArrayList<JSONArray> ratesJsonArrays = new ArrayList<>();
        ArrayList<Currency> selectedCurrency = new ArrayList<>();

        SelectedCurrency currencySelected = null;
        CurrencyDao currencyDao = new CurrencyDaoImpl();

        try {
            URL nbbURL = new URL(generateNbpPath(path));
            URLConnection nbpConnection = nbbURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(nbpConnection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                JSONObject val = (JSONObject) parser.parse(inputLine);
                JSONArray jsonArray = (JSONArray) val.get("rates");
                List<Double> rating = new LinkedList<>();

                for (Object a : jsonArray) {
                    JSONObject cur = (JSONObject) a;
                    rating.add((Double) cur.get("mid"));
                }

                currencySelected = new SelectedCurrency();
                currencySelected.setCode((String) val.get("code"));
                currencySelected.setCurrencyName((String) val.get("currency"));
                currencySelected.setValAvg(Calculators.calcAvgValue(rating));
                currencySelected.setValMax(rating.stream().max((o1, o2) -> o1.compareTo(o2)).get());
                currencySelected.setValMin(rating.stream().min((o1, o2) -> o1.compareTo(o2)).get());
                currencySelected.setVal1(rating.get(0));
                currencySelected.setVal2(rating.get(1));
                currencySelected.setVal3(rating.get(2));
                currencySelected.setVal4(rating.get(3));
                currencySelected.setVal5(rating.get(4));
                currencySelected.setVal6(rating.get(5));
                currencySelected.setVal7(rating.get(6));
                currencySelected.setVal8(rating.get(7));
                currencySelected.setVal9(rating.get(8));
                currencySelected.setVal10(rating.get(9));
                currencyDao.addSelectedCurrency(currencySelected);
            }
            in.close();
        } catch (ParseException | IOException e) {
            Alerts.errorAlert("Nieprawidłowy adres lub brak dostępu do internetu.");
        }
    }

    public static void parseData() {
        JSONParser parser = new JSONParser();
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        ArrayList<JSONArray> ratesJsonArrays = new ArrayList<>();
        Currency currency;
        CurrencyDao currencyDao = new CurrencyDaoImpl();

        try {
            URL nbbURL = new URL("http://api.nbp.pl/api/exchangerates/tables/a/last/2/?format=json");
            URLConnection nbpConnection = nbbURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(nbpConnection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                JSONArray val = (JSONArray) parser.parse(inputLine);
                for (int i = 0; i < val.size(); i++) {
                    jsonObjects.add((JSONObject) val.get(i));
                }
                for (int i = 0; i < jsonObjects.size(); i++) {
                    ratesJsonArrays.add((JSONArray) jsonObjects.get(i).get("rates"));
                }
                for (int i = 0; i < ratesJsonArrays.size(); i++) {
                    JSONArray array = ratesJsonArrays.get(i);
                    if (i == 0) {
                        for (Object o : array) {
                            JSONObject rating = (JSONObject) o;
                            currency = new Currency();
                            currency.setCode(rating.get("code").toString());
                            currency.setCurrencyName(rating.get("currency").toString());
                            currency.setValue(Double.parseDouble(rating.get("mid").toString()));
                            currencyDao.addCurrentCurrency(currency);
                        }
                    } else {
                        for (Object o : array) {
                            JSONObject rating = (JSONObject) o;
                            currency = new Currency();
                            currency.setCode(rating.get("code").toString());
                            currency.setCurrencyName(rating.get("currency").toString());
                            currency.setValue(Double.parseDouble(rating.get("mid").toString()));
                            currencyDao.addLastCurrency(currency);
                        }
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static List<Currency> downloadActualCurrency() {
        JSONParser parser = new JSONParser();
        Currency currency;
        CurrencyDaoImpl currencyDao = new CurrencyDaoImpl();
        List<Currency> jsonObjects = new ArrayList<>();

        try {
            URL nbbURL = new URL("http://api.nbp.pl/api/exchangerates/tables/a");
            URLConnection nbpConnection = nbbURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(nbpConnection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                JSONArray val = (JSONArray) parser.parse(inputLine);
                for (Object o : val) {
                    JSONObject obj = (JSONObject) o;
                    JSONArray rates = (JSONArray) obj.get("rates");
                    for (Object oo : rates) {
                        JSONObject xx = (JSONObject) oo;
                        currency = new Currency();
                        currency.setCode(xx.get("code").toString());
                        currency.setCurrencyName(xx.get("currency").toString());
                        currency.setValue(Double.parseDouble(xx.get("mid").toString()));
                        jsonObjects.add(currency);
                    }
                }
            }
            in.close();
            return jsonObjects;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return jsonObjects;
    }
}
