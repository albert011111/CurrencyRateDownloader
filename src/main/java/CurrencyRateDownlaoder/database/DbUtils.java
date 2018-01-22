package CurrencyRateDownlaoder.database;

import CurrencyRateDownlaoder.utils.Alerts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Patryk on 2017-07-10.
 */
public class DbUtils {

    //singletone
    private static DbUtils instance = null;

    public static DbUtils getInstance() {
        if (instance == null) {
            instance = new DbUtils();
        }
        return instance;
    }

/*    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_MYSQL = "jdbc:mysql://localhost:3306/database1";*/

    public static final String SQLITE_DRIVER = "org.sqlite.JDBC";
    public static final String DATABASE_SQLITE = "jdbc:sqlite:Currency.db";
//    private static String user = "admin";
//    private static String pass = "admin";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private DbUtils() {
        try {
            Class.forName(SQLITE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_SQLITE);
        } catch (ClassNotFoundException e) {
            Alerts.warningAlert("Błąd połączenia z bazą danych!");
        } catch (SQLException e) {
            Alerts.warningAlert("Błąd połączenia z bazą danych!");
        }
        createTables();
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            Alerts.warningAlert("Błąd połączenia z bazą danych!");
        }
    }

    private void dropTable(String tableName) {
        String sql = "DROP TABLE IF EXISTS " + tableName;
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            Alerts.warningAlert("Błąd połączenia z bazą danych!");
        }
    }

    private void createTables() {
        String currentCurrencyTable =
                "CREATE TABLE IF NOT EXISTS CurrentCurrency ( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "code VARCHAR(5) NOT NULL, " +
                        "currencyName VARCHAR(50) NOT NULL," +
                        "value DOUBLE NOT NULL" +
                        " )";
        String lastCurrencyTable =
                "CREATE TABLE IF NOT EXISTS LastCurrency ( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "code VARCHAR(5) NOT NULL, " +
                        "currencyName VARCHAR(50) NOT NULL," +
                        "value DOUBLE NOT NULL" +
                        " )";
        String selectedCurrencyTable =
                "CREATE TABLE IF NOT EXISTS SelectedCurrency ( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "code VARCHAR(5) NOT NULL, " +
                        "currencyName VARCHAR(50) NOT NULL," +
                        "valAverage DOUBLE NOT NULL," +
                        "valMin DOUBLE NOT NULL," +
                        "valMax DOUBLE NOT NULL," +
                        "val1 DOUBLE NOT NULL," +
                        "val2 DOUBLE NOT NULL," +
                        "val3 DOUBLE NOT NULL," +
                        "val4 DOUBLE NOT NULL," +
                        "val5 DOUBLE NOT NULL," +
                        "val6 DOUBLE NOT NULL," +
                        "val7 DOUBLE NOT NULL," +
                        "val8 DOUBLE ," +
                        "val9 DOUBLE NOT NULL," +
                        "val10 DOUBLE NOT NULL," +
                        "date DATE NOT NULL" +
                        " )";

        try {
            dropTable("currentCurrency");
            dropTable("lastCurrency");
            dropTable("selectedCurrencyTable");
            connection.createStatement().execute(currentCurrencyTable);
            connection.createStatement().execute(lastCurrencyTable);
            connection.createStatement().execute(selectedCurrencyTable);
        } catch (SQLException e) {
            Alerts.warningAlert("Błąd połączenia z bazą danych!");
        }

    }


}
