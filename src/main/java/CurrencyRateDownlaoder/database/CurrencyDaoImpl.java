package CurrencyRateDownlaoder.database;


import CurrencyRateDownlaoder.database.domain.Currency;
import CurrencyRateDownlaoder.database.domain.CurrencySelected;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDaoImpl implements CurrencyDao {

    public List<Currency> getAll(String tableName1, String tableName2) {
        String sql = "SELECT cc.id,cc.code,cc.currencyName,cc.value,lc.value " + "FROM " + tableName1 + " cc, " + tableName2 + " lc ON cc.id = lc.id";

        List<Currency> currencies = null;
        try {

            ResultSet resultSet = DbUtils.getInstance().getConnection().createStatement().executeQuery(sql);
            currencies = new ArrayList<>();
            while (resultSet.next()) {
                Currency currency = new Currency(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDouble(5));
                currency.setId(resultSet.getInt(1));
                currency.setCode(resultSet.getString(2));
                currency.setCurrencyName(resultSet.getString(3));
                currency.setValue(resultSet.getDouble(4));
                currency.setLastValue(resultSet.getDouble(5));
                currencies.add(currency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Curr z getAll: " + currencies);
        return currencies;
    }


    @Override
    public Currency getCurrencyById(int id, String tableName) {
        String getSql = "SELECT * FROM " + tableName + " " + "WHERE id =?";

        try {
            PreparedStatement prep = DbUtils.getInstance().getConnection().prepareStatement(getSql);
            prep.setInt(1, id);
            ResultSet resultSet = prep.executeQuery();

            if (resultSet.next()) {
                Currency currency = new Currency(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDouble(5));
                currency.setId(resultSet.getInt(1));
                currency.setCode(resultSet.getString(2));
                currency.setCurrencyName(resultSet.getString(3));
                currency.setValue(resultSet.getDouble(4));
                currency.setLastValue(resultSet.getDouble(5));
                return currency;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addCurrentCurrency(Currency currency) {
        String sql = "INSERT INTO CurrentCurrency(code,currencyName,value) VALUES(?,?,?);";
        try {
            createCurrencyStatement(currency, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addLastCurrency(Currency currency) {
        String sql = "INSERT INTO LastCurrency(code,currencyName,value) VALUES(?,?,?);";
        try {
            createCurrencyStatement(currency, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createCurrencyStatement(Currency currency, String sql) throws SQLException {
        PreparedStatement statement = DbUtils.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, currency.getCode());
        statement.setString(2, currency.getCurrencyName());
        statement.setDouble(3, currency.getValue());
        statement.execute();
    }

    @Override
    public void addSelectedCurrency(CurrencySelected currency) {
        String sql = "INSERT INTO SelectedCurrency(code,currencyName,valAverage,valMax,valMin,val1,val2,val3,val4,val5,val6,val7,val8,val9,val10,DATE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement statement = DbUtils.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, currency.getCode());
            statement.setString(2, currency.getCurrencyName());
            statement.setDouble(3, currency.getValAvg());
            statement.setDouble(4, currency.getValMax());
            statement.setDouble(5, currency.getValMin());
            statement.setDouble(6, currency.getVal1());
            statement.setDouble(7, currency.getVal2());
            statement.setDouble(8, currency.getVal3());
            statement.setDouble(9, currency.getVal4());
            statement.setDouble(10, currency.getVal5());
            statement.setDouble(11, currency.getVal6());
            statement.setDouble(12, currency.getVal7());
            statement.setDouble(13, currency.getVal8());
            statement.setDouble(14, currency.getVal9());
            statement.setDouble(15, currency.getVal10());
            statement.setDate(16, new java.sql.Date((new java.util.Date().getTime())));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
