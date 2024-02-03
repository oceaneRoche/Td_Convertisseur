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
    public Label label_Haut;
    public Label label_Bas;
    public TextField textField_Haut;
    public TextField textField_Bas;
    public Button boutonConvertion;
    public ComboBox<String> comboSelection = new ComboBox<>();
    public double valeurConversion;
    public RotateTransition rotation;
    public DecimalFormat df = new DecimalFormat("0.00");
    public ArrayList<ConversionDevice> conversionDevices = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rotation = new RotateTransition(Duration.seconds(0.5), boutonConvertion);
        fabriqueDonnees();
        remplirComboBoxAvecPrompts();
        configurerValeurInitiale();
        boutonConvertion.setOnAction(event -> effectuerConversion());
        comboSelection.setOnAction(event -> gererSelectionCombo());
    }

    private void remplirComboBoxAvecPrompts() {
        for (ConversionDevice conversionDevice : conversionDevices) {
            comboSelection.getItems().add(conversionDevice.getPrompt());
        }
    }

    private void configurerValeurInitiale() {
        comboSelection.setValue(conversionDevices.get(0).getPrompt());
        initConvertion(conversionDevices.get(0));
    }

    private void effectuerConversion() {
        try {
            ConversionDevice deviseSelectionnee = obtenirDeviseSelectionnee();
            if (deviseSelectionnee != null) {
                double valeurInput = textField_Haut.isDisabled() ?
                        Double.parseDouble(textField_Bas.getText()) :
                        Double.parseDouble(textField_Haut.getText());

                valeurConversion = textField_Haut.isDisabled() ?
                        valeurInput / deviseSelectionnee.getTaux() :
                        valeurInput * deviseSelectionnee.getTaux();

                if (textField_Haut.isDisabled()) {
                    textField_Haut.setText(df.format(valeurConversion).replaceAll(",", "."));
                } else {
                    textField_Bas.setText(df.format(valeurConversion).replaceAll(",", "."));
                }
            }
        } catch (NumberFormatException ignored) {
            // Gérer l'exception (éventuellement log ou afficher un message)
        }
    }

    private void gererSelectionCombo() {
        boolean elementImpair = comboSelection.getSelectionModel().getSelectedIndex() % 2 == 1;
        rotation.setByAngle(180);
        rotation.play();
        textField_Haut.setDisable(elementImpair);
        textField_Bas.setDisable(!elementImpair);
        textField_Haut.clear();
        textField_Bas.clear();
    }

    private ConversionDevice obtenirDeviseSelectionnee() {
        for (ConversionDevice devise : conversionDevices) {
            if (comboSelection.getValue().equals(devise.getPrompt())) {
                return devise;
            }
        }
        return null;
    }

    private void fabriqueDonnees() {
        conversionDevices.add(new ConversionDevice("Euro/Dollars", "Euro", "Dollars", 1.11));
        conversionDevices.add(new ConversionDevice("Dollars/Euro", "Dollars", "Euro", 1.11));
    }

    private void initConvertion(ConversionDevice conversionDevice) {
        label_Haut.setText(conversionDevice.getSource());
        label_Bas.setText(conversionDevice.getCible());
    }
}
