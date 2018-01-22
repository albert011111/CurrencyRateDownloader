package CurrencyRateDownlaoder.controllers;

import CurrencyRateDownlaoder.database.CurrencyDao;
import CurrencyRateDownlaoder.database.CurrencyDaoImpl;
import CurrencyRateDownlaoder.database.domain.Currency;
import CurrencyRateDownlaoder.modelFx.CurrencyFxCheckbox;
import CurrencyRateDownlaoder.utils.Alerts;
import CurrencyRateDownlaoder.utils.ConverterCurrencyToFX;
import CurrencyRateDownlaoder.utils.CurrencyRatesDownloader;
import CurrencyRateDownlaoder.utils.FinalAdresses;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FormController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button testButtonOnAction;

    @FXML
    private TableView<CurrencyFxCheckbox> tableView;

    @FXML
    private TableColumn<CurrencyFxCheckbox, Number> idColumn;

    @FXML
    private TableColumn<CurrencyFxCheckbox, String> codeColumn;

    @FXML
    private TableColumn<CurrencyFxCheckbox, String> currentyNameColumn;

    @FXML
    private TableColumn<CurrencyFxCheckbox, Boolean> checkBoxColumn;


    private CurrencyDao currencyDao;
    private List<Currency> currencies;
    private ObservableList<CurrencyFxCheckbox> currenciesFx;


    private MainController mainController;


    public void initialize() {


        tableView.setEditable(true);
        //CurrencyRatesDownloader.parseData();
        currencyDao = new CurrencyDaoImpl();

        //downloads data from 2 tables and converts it to FX versions
        currencies = currencyDao.getAll(FinalAdresses.CURRENT_CURRENCY, FinalAdresses.LAST_CURRENCY);
        currenciesFx = getCurrencyFxList(currencies);

        //populates table with elements from observable list
        populateTableView(currenciesFx);

        //generates checkboxes in each row of table
        generateCheckboxesInTableView();

    }

    private List<CurrencyFxCheckbox> generateSelctedCurrencyList() {
        List<CurrencyFxCheckbox> selectedCurrencyList = this.tableView.getItems().stream().filter(currency -> currency.isSelected() == true).collect(Collectors.toList());
        return selectedCurrencyList;
    }

    private ObservableList<CurrencyFxCheckbox> getCurrencyFxList(List<Currency> currencies) {
        ObservableList<CurrencyFxCheckbox> currenciesFx = FXCollections.observableArrayList();
        for (Currency c : currencies) {
            currenciesFx.add(ConverterCurrencyToFX.getCurrencyFxCheckbox(c));
        }
        return currenciesFx;
    }

    private void generateCheckboxesInTableView() {
        this.checkBoxColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CurrencyFxCheckbox, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<CurrencyFxCheckbox, Boolean> param) {
                CurrencyFxCheckbox currencyFxCheckbox = param.getValue();
                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(currencyFxCheckbox.isSelected());

                booleanProperty.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        currencyFxCheckbox.setSelected(newValue);
                    }
                });
                return booleanProperty;
            }
        });
        this.checkBoxColumn.setCellFactory(new Callback<TableColumn<CurrencyFxCheckbox, Boolean>, TableCell<CurrencyFxCheckbox, Boolean>>() {
            @Override
            public TableCell<CurrencyFxCheckbox, Boolean> call(TableColumn<CurrencyFxCheckbox, Boolean> param) {
                CheckBoxTableCell<CurrencyFxCheckbox, Boolean> cell = new CheckBoxTableCell<>();
                return cell;
            }
        });
    }

    private void populateTableView(ObservableList<CurrencyFxCheckbox> currenciesFx) {
        this.tableView.setItems(currenciesFx);
        this.idColumn.setCellValueFactory(cell -> cell.getValue().idProperty());
        this.codeColumn.setCellValueFactory(cell -> cell.getValue().codeProperty());
        this.currentyNameColumn.setCellValueFactory(cell -> cell.getValue().currencyNameProperty());
    }

    @FXML
    void buttonOnAction() {
        Optional<ButtonType> result = Alerts.confirmationAlert("Czy zapisać wybrane elementy w bazie?");
        if (result.get() == ButtonType.OK) {
            //checks which checkboxes are selected and preparing list of selected items
            List<CurrencyFxCheckbox> selectedCurrencyFx = generateSelctedCurrencyList();

            List<String> paths = new ArrayList<>();
            selectedCurrencyFx.forEach(c -> paths.add(c.getCode().toLowerCase()));

            if (paths.isEmpty()) {
                Alerts.warningAlert("Brak wybranych elementow!");
            } else {
                for (int i = 0; i < paths.size(); i++) {
                    CurrencyRatesDownloader.downloadSelectedCurrency(paths.get(i));
                    Alerts.infoAlert("Elementy zapisano do bazy!");
                }
            }
        } else {
        }

    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
