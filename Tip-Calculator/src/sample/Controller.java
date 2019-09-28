package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.text.NumberFormat;

public class Controller {
    private final String currency = NumberFormat.getCurrencyInstance().getCurrency().getSymbol();
    private int tipPercentage = 0;
    private int currentTip = 0;
    private int currentPrice = 0;

    @FXML
    private TextField tfPrice;

    @FXML
    private TextField people;

    @FXML
    private Slider sliderPercent;

    @FXML
    private Label labelTip;

    @FXML
    private Label labelTotal;

    @FXML
    private Label perPerson;

    @FXML
    private Label labelPercentage;

    @FXML
    private Button onCalculate;

    @FXML
    private void handleOnCalculate (final ActionEvent event) {
        calculate();
    }

    @FXML
    void initialize() {
        labelTip.setText(currency + "0");
        labelTotal.setText(currency + "0");
        perPerson.setText(currency + "0");
        onlyNumber(tfPrice);

        sliderPercent.setMax(31);
        sliderPercent.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue.intValue() % 5 == 0) {
                tipPercentage = oldValue.intValue();
                labelPercentage.setText(tipPercentage + "%");
            }
        });
    }

    private void onlyNumber(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void calculate() {
        if (!tfPrice.getText().isBlank()) {
            currentPrice = Integer.valueOf(tfPrice.getText());
            currentTip = ((int) (currentPrice / 100.0 * tipPercentage));
            labelTip.setText(currency + currentTip);

            int total = currentPrice + currentTip;
            labelTotal.setText(currency + total);
            perPerson.setText(currency + total/Integer.valueOf(people.getText()));
        }
    }
}