package fr.btsciel.td_convertisseur;

import javafx.animation.RotateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public AnchorPane anchorPane;
    public TextField textField_Haut;
    public TextField textField_Final;
    public Button boutonConvertion;
    public ComboBox<ConversionDevice> comboSelection = new ComboBox<>();
    public RotateTransition rotation;
    public DecimalFormat df = new DecimalFormat("0.00");
    public ArrayList<ConversionDevice> conversionDevise = new ArrayList<>();
    public Label label_Bas;
    public Label label_Haut;

    public void Convertion() {
        try {
            ConversionDevice devise = comboSelection.getValue();
            double valeur_Conversion;
            if (textField_Haut.isDisabled()) {
                valeur_Conversion = Double.parseDouble(textField_Final.getText()) / devise.getTaux();
                textField_Haut.setText(df.format(valeur_Conversion));
            } else {
                valeur_Conversion = Double.parseDouble(textField_Haut.getText()) * devise.getTaux();
                textField_Final.setText(df.format(valeur_Conversion));
            }
        } catch (NumberFormatException e) {
            //alerteFormat();
        }
    }
    public void comboSelection() {
        ConversionDevice devise = comboSelection.getValue();
        boolean element = comboSelection.getSelectionModel().getSelectedIndex() % 2 == 1;
        rotation.setByAngle(180);
        rotation.play();
        textField_Haut.setDisable(element);
        textField_Final.setDisable(!element);
        textField_Haut.clear();
        textField_Final.clear();
    }

    public void fabriqueDonnees() {
        conversionDevise.add(new ConversionDevice("Euro/Dollars", "Euro", "Dollars US", 1.11));
        conversionDevise.add(new ConversionDevice("Dollars/Euro", "Dollars", "Euro", 1.11));
    }

    public void initConvertion(ConversionDevice conversionDevise) {
        label_Haut.setText(conversionDevise.getSource());
        label_Bas.setText(conversionDevise.getCible());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rotation = new RotateTransition(Duration.seconds(0.5), boutonConvertion);
        comboSelection.getItems().addAll(conversionDevise);
        comboSelection.setValue(conversionDevise.get(0));
        initConvertion(conversionDevise.get(0));
        boutonConvertion.setOnAction(event -> Convertion());
        comboSelection.setOnAction(event -> comboSelection());
        fabriqueDonnees();
    }
}
