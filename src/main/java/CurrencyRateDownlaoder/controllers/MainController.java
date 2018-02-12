package CurrencyRateDownlaoder.controllers;

import CurrencyRateDownlaoder.database.CurrencyDao;
import CurrencyRateDownlaoder.database.CurrencyDaoImpl;
import CurrencyRateDownlaoder.database.domain.Currency;
import CurrencyRateDownlaoder.modelFx.CurrencyFx;
import CurrencyRateDownlaoder.utils.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MainController {

    @FXML
    private Button formButton;
    @FXML
    private Label firstDifferenceLabel;
    @FXML
    private Label secondDifferenceLabel;
    @FXML
    private Label thirdDifferenceLabel;
    @FXML
    private Label secondCodeLabel;
    @FXML
    private Label thirdCodeLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label secondNameLabel;
    @FXML
    private Label thirdNameLabel;
    @FXML
    private Label firstCodeLabel;
    @FXML
    private TableView<CurrencyFx> tableView;
    @FXML
    private TableColumn<CurrencyFx, Number> idColumn;
    @FXML
    private TableColumn<CurrencyFx, String> codeColumn;
    @FXML
    private TableColumn<CurrencyFx, String> currentyNameColumn;
    @FXML
    private TableColumn<CurrencyFx, Number> currentValueColumn;
    @FXML
    private TableColumn<CurrencyFx, Number> lastValueColumn;

    private CurrencyDao currencyDao;
    private List<Currency> currencies;

    @FXML
    public void initialize() {
        setCaspianStyle();
        CurrencyRatesDownloader.parseData();
        currencyDao = new CurrencyDaoImpl();
        //downloads data from 2 tables and converts it to FX versions
        currencies = currencyDao.getAll(FinalAdresses.CURRENT_CURRENCY, FinalAdresses.LAST_CURRENCY);
        ObservableList<CurrencyFx> currenciesFx = getCurrencyFxList(currencies);
        populateTableView(currenciesFx);
        selectRowsWithRaiseCurrency();
        Map<Currency, Double> sortedMap = getSortedCurrencyMap(currencies);
        List<Map.Entry<Currency, Double>> getBiggestCurrencyIncreases = sortedMap.entrySet().stream().limit(3).collect(Collectors.toList());
        initBottomTextFields(getBiggestCurrencyIncreases);
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    private void setCaspianStyle() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    private void initBottomTextFields(List<Map.Entry<Currency, Double>> list) {
        this.firstCodeLabel.setText(list.get(0).getKey().getCode());
        this.secondCodeLabel.setText(list.get(1).getKey().getCode());
        this.thirdCodeLabel.setText(list.get(2).getKey().getCode());

        this.firstNameLabel.setText(list.get(0).getKey().getCurrencyName());
        this.secondNameLabel.setText(list.get(1).getKey().getCurrencyName());
        this.thirdNameLabel.setText(list.get(2).getKey().getCurrencyName());

        this.firstDifferenceLabel.setText("+" + list.get(0).getValue());
        this.secondDifferenceLabel.setText("+" + list.get(1).getValue());
        this.thirdDifferenceLabel.setText("+" + list.get(2).getValue());
    }

    public void buttonOnAction() {
        Optional<ButtonType> result = Alerts.confirmationAlert("Czy chcesz zapisać dane do pliku .txt?");
        if (result.get() == ButtonType.OK) {
            ReportGenerator.generateReport(getSortedCurrencyMap(currencies));
        }
    }

    public void formConfigureOnAction() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FormController.fxml"));
        Parent root;
        try {
            root = loader.load();
            stage.setScene(new Scene(root, Color.DARKGREY));
            stage.setTitle("Konfiguracja raportu");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            Alerts.errorAlert("Brak podanej ścieżki pliku!");
        }

        FormController formController = loader.getController();
        formController.setMainController(this);
    }

    private Map<Currency, Double> getSortedCurrencyMap(List<Currency> currencies) {
        Map<Currency, Double> map = Calculators.getBiggestDifference(currencies);
        for (Map.Entry entry : map.entrySet()) {
        }
        Map<Currency, Double> currencyMap = sortByValue(map);
        for (Map.Entry entry : currencyMap.entrySet()) {
        }
        return currencyMap;
    }

    private void selectRowsWithRaiseCurrency() {
        this.tableView.setRowFactory(row -> new TableRow<CurrencyFx>() {
            @Override
            protected void updateItem(CurrencyFx item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setStyle("");
                } else if (item.valueProperty().get() - item.lastValueProperty().getValue() > 0) {
                    setStyle("-fx-background-color: chartreuse;");
                } else {
                    setStyle("");
                }
            }
        });
    }

    private void populateTableView(ObservableList<CurrencyFx> currenciesFx) {
        this.tableView.setItems(currenciesFx);
        this.idColumn.setCellValueFactory(cell -> cell.getValue().idProperty());
        this.codeColumn.setCellValueFactory(cell -> cell.getValue().codeProperty());
        this.currentyNameColumn.setCellValueFactory(cell -> cell.getValue().currencyNameProperty());
        this.currentValueColumn.setCellValueFactory(cell -> cell.getValue().valueProperty());
        this.lastValueColumn.setCellValueFactory(cell -> cell.getValue().lastValueProperty());
    }

    private ObservableList<CurrencyFx> getCurrencyFxList(List<Currency> currencies) {
        ObservableList<CurrencyFx> currenciesFx = FXCollections.observableArrayList();
        for (Currency c : currencies) {
            currenciesFx.add(ConverterCurrencyToFX.getCurrencyFx(c));
        }
        return currenciesFx;
    }

    private List<CurrencyFx> getCurrencies(List<CurrencyFx> cur) {
        ObservableList<CurrencyFx> currencies = FXCollections.observableArrayList();
        currencies.setAll(cur);
        return currencies;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortedMap) {
        List<Map.Entry<K, V>> list = new LinkedList<>(unsortedMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int result = 0;
                if (o1.getValue().compareTo(o2.getValue()) < 0) {
                    result = 1;
                } else if (o1.getValue().compareTo(o2.getValue()) == 0) {
                    result = 0;
                } else if (o1.getValue().compareTo(o2.getValue()) > 0) {
                    result = -1;
                }
                return result;
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Iterator<Map.Entry<K, V>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<K, V> entry = it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
