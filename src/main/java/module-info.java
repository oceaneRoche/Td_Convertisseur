module fr.btsciel.td_convertisseur {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.btsciel.td_convertisseur to javafx.fxml;
    exports fr.btsciel.td_convertisseur;
}